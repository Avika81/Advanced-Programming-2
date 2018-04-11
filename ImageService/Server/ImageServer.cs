using ImageService.Controller;
using ImageService.Controller.Handlers;
using ImageService.Infrastructure.Enums;
using ImageService.Logging;
using ImageService.Modal;
using System;
using System.Threading
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ImageService.Server
{
    public class ImageServer
    {
        
        #region Members
        private static ImageServer instance;
        private IImageController m_controller;
        private ILoggingService m_logging;
        CommandRecievedEventArgs input;
        #endregion

        #region Properties
        public event EventHandler<CommandRecievedEventArgs> CommandRecieved;// The event that notifies about a new Command being recieved
        #endregion

        //constructor - will run the server automaticly:
        private ImageServer() { }
        
        public static ImageServer getInstance()
        {
            if(instance== null)
            {
                instance = new ImageServer();
            }
            return instance;
        } // the server is singelton

        public static void StartWork(EventHandler<CommandRecievedEventArgs> CommandRecieved, IImageController m_controller, ILoggingService m_logging, CommandRecievedEventArgs input)
        {
            CommandRecieved.Invoke(m_logging, input);
        }

        public static void OnStart() {
            while (true)
            {
                bool res = true;
                Thread t = new Thread(() => StartWork(getInstance().CommandRecieved, getInstance().m_controller, getInstance().m_logging, getInstance().CommandRecievedEve));
            }
        }
    }
}

