using ImageService.Modal;
using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ImageService.Infrastructure;
using ImageService.Infrastructure.Enums;
using ImageService.Logging;
using ImageService.Logging.Modal;
using System.Text.RegularExpressions;

namespace ImageService.Controller.Handlers
{
    public class DirectoyHandler : IDirectoryHandler
    {
        #region Members
        private IImageController m_controller;              // The Image Processing Controller
        private ILoggingService m_logging;                  // Logging service
        private FileSystemWatcher m_dirWatcher;             // The Watcher of the Dir
        private string m_path;                              // The Path of directory
        private List<FileSystemWatcher> m_dirWatchers;      // The Watchers of the Dir based on extension watched.
        #endregion

        public event EventHandler<DirectoryCloseEventArgs> DirectoryClose; // The Event That Notifies that the Directory is being closed

        public DirectoyHandler(IImageController controller, ILoggingService logging, string path)
        {
            m_controller = controller;
            m_logging = logging;
            m_path = path;
            string[] filters = new string[] { "*.jpg", "*.png", "*.gif", "*.bmp" }; //the possible endings of a picture...

            m_dirWatchers = new List<FileSystemWatcher>(filters.Length);    //gets my list of watchers.

            m_logging.Log("Creating Directory handler", MessageTypeEnum.INFO);  //start log

            foreach (string filter in filters)
            {
                FileSystemWatcher fsw = new FileSystemWatcher(m_path);
                fsw.Filter = filter;
                fsw.Created += new FileSystemEventHandler(OnCreated);
                m_dirWatchers.Add(fsw);
                m_logging.Log("Filtering " + filter, MessageTypeEnum.INFO);
            }

        }

        public void StartHandleDirectory(string dirPath)             // The Function Recieves the directory to Handle
        {
            // Begin watching.
            foreach (FileSystemWatcher watcher in m_dirWatchers)
            {
                watcher.EnableRaisingEvents = true;
            }
        }

        public void OnCommandRecieved(object sender, CommandRecievedEventArgs e)
        {
            if (e.RequestDirPath != "*" && e.RequestDirPath != this.m_path) {
                return;
            }

            //In the future we would like to close specipc directory handler

            if (e.CommandID == (int)(CommandEnum.CloseCommand))
            {
                foreach (FileSystemWatcher watcher in m_dirWatchers)
                {
                    watcher.EnableRaisingEvents = false;
                    DirectoryClose?.Invoke(this, new DirectoryCloseEventArgs(e.RequestDirPath, "Directory closed"));
                }

            }
        }

        private void OnCreated(object source, FileSystemEventArgs e)
        {

            m_logging.Log("FILE DETECTED", Logging.Modal.MessageTypeEnum.INFO);


            bool succeed = true; //temp value

            Task<string> commandTask = new Task<string>(() => { return m_controller.ExecuteCommand((int)CommandEnum.NewFileCommand, new string[] { e.FullPath }, out succeed); });

            commandTask.Start();
            string message = commandTask.Result;
            commandTask.Wait();
            if (!succeed)
            {
                m_logging.Log(message, MessageTypeEnum.FAIL);
            }
        }
    }
}
