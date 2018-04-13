using System;

namespace ImageService.Modal
{
    public class LoggingModal : ILoggingModal
    {
        /// <summary>
        /// constructor
        /// </summary>
        public LoggingModal(){}

        public event EventHandler<MessageRecievedEventArgs> MessageRecieved;

        /// <summary>
        /// runs the log over the msg received (logs it out)
        /// </summary>
        /// <param name="msg"> the message for the log</param>
        public void Log(MessageRecievedEventArgs msg)
        {
            MessageRecieved.Invoke(this, msg);
        }
    }
}
