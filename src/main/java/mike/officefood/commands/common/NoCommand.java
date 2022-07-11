package mike.officefood.commands.common;

import mike.officefood.main.config.BotCommand;
import mike.officefood.main.config.Command;
import mike.officefood.main.config.CommandWithMessageService;
import mike.officefood.main.context.CommandContext;
import mike.officefood.main.result.CommandResult;
import mike.officefood.main.result.SendSimpleMessage;
import org.springframework.stereotype.Component;

@Component
@BotCommand(name = "/nocommand")
public class NoCommand extends CommandWithMessageService implements Command {
    public static final String NO_MESSAGE = "Я поддерживаю команды, начинающиеся со слеша(/).\n"
            + "Чтобы посмотреть список команд введите /help";

    public CommandResult execute(CommandContext context) {
        return new SendSimpleMessage(context.getChatId(), NO_MESSAGE);
    }
}
