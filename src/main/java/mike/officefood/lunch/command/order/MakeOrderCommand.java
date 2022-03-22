package mike.officefood.lunch.command.order;

import mike.officefood.lunch.ContextBuilder;
import mike.officefood.lunch.OperationContext;
import mike.officefood.lunch.command.Command;
import mike.officefood.lunch.command.CommandWithMessageService;
import mike.officefood.lunch.command.config.BotCommand;
import mike.officefood.lunch.repository.entity.LunchMenu;
import mike.officefood.lunch.repository.entity.LunchOrder;
import mike.officefood.lunch.repository.entity.LunchOrderDetail;
import mike.officefood.lunch.repository.entity.LunchTelegramUser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
@BotCommand(name = "/want")
public class MakeOrderCommand extends CommandWithMessageService implements Command {
    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        LunchTelegramUser lunchTelegramUser = lunchTelegramUserService.findByChatId(chatId);

        String[] messages = update.getMessage().getText().trim().split(" ");
        Integer amount = 1;
        String menuTag = "";
        Date orderDate = new Date();

        if (messages.length > 1) menuTag = messages[1];
        if (messages.length > 2 && messages[2] != null)
            try {
                amount = Integer.parseInt(messages[2]);
            } catch (Exception e) {
                amount = 1;
            }

        OperationContext context = new ContextBuilder()
                .setChatId(chatId)
                .setUser(lunchTelegramUser)
                .setChatType(update.getMessage().getChat().getType())
                .addParam("orderDate", orderDate)
                .addParam("menu", menuTag)
                .addParam("amount", amount)
                .build();

        String resultMessage = lunchOrderService.makeOrder(context).getResult();
        sendBotMessageService.sendMessage(chatId, resultMessage);
    }
}
