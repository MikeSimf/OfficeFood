package mike.officefood.commands.common;

import mike.officefood.main.config.BotCommand;
import mike.officefood.main.config.Command;
import mike.officefood.main.config.CommandWithMessageService;
import mike.officefood.main.context.CommandContext;
import mike.officefood.main.result.CommandResult;
import mike.officefood.main.result.SendSimpleMessage;
import org.springframework.stereotype.Component;

@Component
@BotCommand(name = "/unknown")
public class UnknownCommand extends CommandWithMessageService implements Command {
    public static final String UNKNOWN_MESSAGE = "\"Я не знаю этой команды:(\\nЧтобы посмотреть список команд введите"
            + " /help\"";

    public CommandResult execute(CommandContext context) {
        return new SendSimpleMessage(context.getChatId(), UNKNOWN_MESSAGE);
    }
}