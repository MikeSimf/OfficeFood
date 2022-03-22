package mike.officefood.lunch.command.order;

import mike.officefood.lunch.ContextBuilder;
import mike.officefood.lunch.OperationContext;
import mike.officefood.lunch.command.Command;
import mike.officefood.lunch.command.CommandWithMessageService;
import mike.officefood.lunch.command.config.BotCommand;
import mike.officefood.lunch.repository.entity.LunchTelegramUser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Date;

@Component
@BotCommand(name = "/stop")
public class StopOrderCommand extends CommandWithMessageService implements Command {
    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        LunchTelegramUser lunchTelegramUser = lunchTelegramUserService.findByChatId(chatId);
        //TODO: надо бы дать возможность открывать заказ заранее
        Date date = new Date();

        OperationContext context = new ContextBuilder()
                .setChatId(chatId)
                .setUser(lunchTelegramUser)
                .setChatType(update.getMessage().getChat().getType())
                .addParam("orderDate", date)
                .build();

        String resultMessage = lunchOrderService.stopOrder(context).getResult();
        sendBotMessageService.sendMessage(chatId, resultMessage);
    }
}