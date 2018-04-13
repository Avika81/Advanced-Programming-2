using ImageService.Commands;
using ImageService.Modal;
using System.Collections.Generic;

namespace ImageService.Controller
{
    public class ImageController : IImageController
    {
        private Dictionary<CommandEnum, ICommand> commands; // keeps a dictonary of available commands

        /// <summary>
        /// calls the Execute of the command wanted with the args
        /// </summary>
        /// <param name="commandID"> represents the command wanted to be handed</param>
        /// <param name="args"> the arguments for the command </param>
        /// <param name="resultSuccesful">out bool which represents the result of the command </param>
        /// <returns></returns>
        public string ExecuteCommand(CommandEnum commandID, string[] args, out bool resultSuccesful)
        {
            return commands[commandID].Execute(args, out resultSuccesful);
        }

        /// <summary>
        /// constructor
        /// </summary>
        /// <param name="modal"> the modal which will be used for the initializtion.</param>
        public ImageController(IImageModal modal)
        {
            NewFileCommand newFile = new NewFileCommand(modal);
            commands = new Dictionary<CommandEnum, ICommand>
            {
                { CommandEnum.NewFileCommand, newFile }
            };
        }
    }
}
