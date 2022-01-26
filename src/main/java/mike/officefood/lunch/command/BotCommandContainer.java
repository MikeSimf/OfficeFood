package mike.officefood.lunch.command;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class BotCommandContainer {
    private HashMap<String, Command> commandHashMap = new HashMap<>();

    public void putCommand(String commandName, Command command){
        this.commandHashMap.put(commandName, command);
    }

    public Command getCommand(String commandName) {
        return commandHashMap.get(commandName);
    }

    public boolean hasCommand(String commandName) {
        return commandHashMap.containsKey(commandName);
    }
}
