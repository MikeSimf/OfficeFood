package mike.officefood.commands.order;

import mike.officefood.main.config.Command;
import mike.officefood.main.config.CommandWithMessageService;
import mike.officefood.main.context.ContextBuilder;
import mike.officefood.main.context.CommandContext;

import mike.officefood.main.config.BotCommand;
import mike.officefood.main.result.CommandResult;
import mike.officefood.main.result.SendSimpleMessage;
import mike.officefood.repository.entity.TelegramUser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Date;

@Component
@BotCommand(name = "/start")
public class StartOrderCommand extends CommandWithMessageService implements Command {
    @Override
    public CommandResult execute(CommandContext context) {
        String chatId = context.getChatId();
        TelegramUser telegramUser = context.getUser();
        //TODO: надо бы дать возможность открывать заказ заранее
        Date date = new Date();

        //String resultMessage = lunchOrderService.startOrder(context).getResult();
        String resultMessage = "start order";
        return new SendSimpleMessage(context.getChatId(), resultMessage);
    }
}
