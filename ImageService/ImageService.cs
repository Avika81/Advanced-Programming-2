using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Linq;
using System.ServiceProcess;
using System.Text;
using System.Threading.Tasks;

namespace ImageService
{
    public partial class ImageService : ServiceBase
    {
#if DEBUG
        public void OnDebug()
        {
            OnStart(null);
        }
#endif
        public ImageService()
        {
            InitializeComponent();
            eventLog1 = new System.Diagnostics.EventLog();
            try
            {  
                if (!System.Diagnostics.EventLog.SourceExists("MySource"))
                {
                    System.Diagnostics.EventLog.CreateEventSource(
                        "MySource", "MyNewLog");
                }
                eventLog1.Source = "MySource";
                eventLog1.Log = "MyNewLog";
            }
            catch (System.Security.SecurityException)
            {
                eventLog1.Source = "MySource";
                eventLog1.Log = "MyNewLog";
            }
           
         }

        protected override void OnStart(string[] args)
        {
#if !DEBUG //In debug it won't work (don't have the needed security).
            eventLog1.WriteEntry("In OnStart");
#endif
        }

        protected override void OnStop()
        {
#if !DEBUG
            eventLog1.WriteEntry("In OnStop");
#endif
        }

        private void eventLog1_EntryWritten(object sender, EntryWrittenEventArgs e)
        {
            
        }
    }
}
