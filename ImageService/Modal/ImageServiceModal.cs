//using ImageService.Infrastructure;
using System;
using System.Collections.Generic;
using System.Drawing;
//using System.Drawing.Imaging;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading;
using System.Threading.Tasks;

namespace ImageService.Modal
{
    public class ImageServiceModal : IImageServiceModal
    {
        #region Members
        private string m_OutputFolder;            // The Output Folder
        private int m_thumbnailSize;              // The Size Of The Thumbnail Size

        #endregion
        public string AddFile(string path, out bool result)
        {
            try
            {
                System.IO.File.Create(m_OutputFolder + path);
                result = true;
                string res = m_OutputFolder + path; // the new dir of the file.
                return (res) ;
            }
            catch(Exception e) { //If there Was an exeption It didn't work;
                result = false;
                string res = "Failure!";
                return (res);
            }
        }

        public string CreateFolder(string path, out bool result)
        {
            try
            {
                System.IO.Directory.CreateDirectory(m_OutputFolder + path);
                result = true;
                return (m_OutputFolder + path); // returns the new path.
            }
            catch (Exception e)
            { //If there Was an exeption It didn't work;
                result = false;
                string res = "Failure!";
                return(res);
            }
        }

        public string MoveFile(string oldPath, string newPath, out bool result)
        {
            try {
                File.Move(oldPath,m_OutputFolder + newPath);
                result = true;
                return (m_OutputFolder + newPath);
            }
            catch(Exception e)
            {
                result = false;
                return ("Failure");
            }
            }

        ImageServiceModal(string outputFolder, int thumbnailSize)
        {
            m_OutputFolder = outputFolder;
            m_thumbnailSize = thumbnailSize;
        }
    }
}
