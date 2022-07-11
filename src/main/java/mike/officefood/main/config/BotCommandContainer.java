package mike.officefood.main.config;

import mike.officefood.main.context.CommandContext;
import mike.officefood.main.result.CommandResult;
import mike.officefood.main.result.SendSimpleMessage;
import mike.officefood.repository.entity.UserPermission;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class BotCommandContainer {

    private HashMap<String, CommandInfo> commandInfoMap = new HashMap<>();
    private final String COMMAND_PREFIX = "/";
    private final String UNKNOWN_COMMAND = "/unknown";
    private final String NO_COMMAND = "/nocommand";

    public void putCommand(Command command){
        CommandInfo commandInfo = new CommandInfo(command);
        this.commandInfoMap.put(commandInfo.name, commandInfo);
    }

    public boolean hasCommand(String commandName){
        return commandInfoMap.containsKey(commandName);
    }

    public CommandResult executeCommand(CommandContext context) {
        String message = context.getMessage();
        String commandIdentifier = "";
        if (message.startsWith(COMMAND_PREFIX)) {
            commandIdentifier = message.split(" ")[0].toLowerCase();
            if (!hasCommand(commandIdentifier)) commandIdentifier = UNKNOWN_COMMAND;
        } else {
            commandIdentifier = NO_COMMAND;
        }
        return executeCommand(commandIdentifier, context);
    }

    private CommandResult executeCommand(String commandName, CommandContext context){
        try {
            return commandInfoMap.get(commandName).getCommand().execute(context);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("FAILED INVOKE METHOD " + commandName);
            return new SendSimpleMessage(context.getChatId(), "FAILED INVOKE METHOD " + commandName);
        }
    }

    private class CommandInfo {
        private String name;
        private Command command;
        private UserPermission[] userPermissions;

        public CommandInfo(Command command) {
            BotCommand annotation = command.getClass().getAnnotation(BotCommand.class);
            this.name = annotation.name();
            this.command = command;
            this.userPermissions = annotation.permissions();
        }

        public UserPermission[] getUserPermissions() {
            return userPermissions;
        }

        public String getName() {
            return name;
        }

        public Command getCommand() {
            return command;
        }
    }
}
