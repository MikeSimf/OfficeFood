package mike.officefood.commands.order;

import mike.officefood.main.config.Command;
import mike.officefood.main.config.CommandWithMessageService;
import mike.officefood.main.context.CommandContext;
import mike.officefood.main.config.BotCommand;
import mike.officefood.main.result.CommandResult;
import mike.officefood.main.result.SendSimpleMessage;
import mike.officefood.repository.entity.TelegramUser;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@BotCommand(name = "/menu")
public class ShowMenuCommand extends CommandWithMessageService implements Command {

    public CommandResult execute(CommandContext context) {
        String chatId = context.getChatId();
        TelegramUser telegramUser = context.getUser();

        Date orderDate = new Date();

        //String resultMessage = lunchOrderService.showMenu();
        String resultMessage = "смотри";
        return new SendSimpleMessage(context.getChatId(), resultMessage);
    }
}
