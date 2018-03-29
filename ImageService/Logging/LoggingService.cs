
using ImageService.Logging.Modal;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ImageService.Logging
{
    public class LoggingService : ILoggingService
    {
        public event EventHandler<MessageRecievedEventArgs> MessageRecieved;
        private System.Diagnostics.EventLog eventLog;
        public void Log(string message, MessageTypeEnum type)
        {
            switch (type)
            {
                case MessageTypeEnum.INFO: eventLog.WriteEntry(message); break;
                case MessageTypeEnum.WARNING: eventLog.WriteEntry("Warning: " + message); break;
                case MessageTypeEnum.FAIL: eventLog.WriteEntry("Error: " + message); break;
            }
            
        }
        LoggingService()
        {
            eventLog = new System.Diagnostics.EventLog();
            try
            {
                if (!System.Diagnostics.EventLog.SourceExists("MySource"))
                {
                    System.Diagnostics.EventLog.CreateEventSource(
                        "MySource", "MyNewLog");
                }
                eventLog.Source = "MySource";
                eventLog.Log = "MyNewLog";
            }
            catch (System.Security.SecurityException)
            {
                eventLog.Source = "MySource";
                eventLog.Log = "MyNewLog";
            }

        }
    }
}

