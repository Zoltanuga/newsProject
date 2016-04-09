package by.itacademy.navigation;


public class CommandFactory {
    public static Command getCommand(String paramCommand) {
        CommandsEnum currentCommand = CommandsEnum.valueOf(paramCommand);
        return currentCommand.createCommand();
    }
}
