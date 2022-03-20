package mike.officefood.lunch.command.order;

import mike.officefood.lunch.command.Command;
import mike.officefood.lunch.command.CommandWithMessageService;
import mike.officefood.lunch.command.config.BotCommand;
import mike.officefood.lunch.repository.entity.LunchOrder;
import mike.officefood.lunch.repository.entity.LunchTelegramUser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Date;
import java.util.List;

@Component
@BotCommand(name = "/start_order")
public class StartOrderCommand extends CommandWithMessageService implements Command {
    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        Date date = new Date();

        LunchTelegramUser lunchTelegramUser = lunchTelegramUserService.findByChatId(chatId);
        if (lunchTelegramUser == null) {
            sendBotMessageService.sendMessage(chatId, "Для старта заказа надо зарегистрироваться");
            return;
        }
        //TODO: надо будет добавить права администрирование
        if (!lunchTelegramUser.getChatId().equals("363960924")) {
            sendBotMessageService.sendMessage(chatId,
                    "У вас нет прав на выполнение данной операции");
            return;
        }

        List<LunchOrder> orderList = lunchOrderService.findAllByDate(date);
        if (orderList.size() == 0) {
            LunchOrder order = new LunchOrder();
            order.setState("START");
            order.setDate(date);
            order.setPayer(lunchTelegramUser);
            lunchOrderService.saveOrder(order);
        } else {
            LunchOrder order = orderList.get(0);
            order.setState("START");
            lunchOrderService.saveOrder(order);
        }

        sendBotMessageService.sendMessage(chatId, "Открыт сбор заказа на сегодня");
    }
}
