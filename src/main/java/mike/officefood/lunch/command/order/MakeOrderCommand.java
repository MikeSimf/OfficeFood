package mike.officefood.lunch.command.order;

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
@BotCommand(name = "/m")
public class MakeOrderCommand extends CommandWithMessageService implements Command {
    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String[] messages = update.getMessage().getText().trim().split(" ");
        Integer defaultValue = 1;

        Date date = new Date();

        LunchTelegramUser lunchTelegramUser = lunchTelegramUserService.findByChatId(chatId);
        if (lunchTelegramUser == null) {
            sendBotMessageService.sendMessage(chatId, "Для старта заказа надо зарегистрироваться");
            return;
        }

        LunchOrder order = lunchOrderService.findByDate(date);
        if (order == null) {
            sendBotMessageService.sendMessage(chatId, "Прием заказов еще не начался");
            return;
        }

        LunchOrderDetail orderDetail = new LunchOrderDetail();
        orderDetail.setOrder(order);
        if (messages.length > 1) {
            String tag = messages[1];
            LunchMenu lunchMenu = lunchOrderService.findMenu(tag);
            if (lunchMenu == null) {
                sendBotMessageService.sendMessage(chatId, "Такой позиции не найдено");
                return;
            }
            orderDetail.setMenu(lunchMenu);
        } else {
            sendBotMessageService.sendMessage(chatId, "Не указана позиция");
            return;
        }

        if (messages.length > 2) {
            Integer count = (messages[2] == null) ? defaultValue : Integer.parseInt(messages[2]);
            orderDetail.setAmount(count);
            orderDetail.setSum(orderDetail.getMenu().getCost().multiply(BigDecimal.valueOf(count)));
        } else {
            orderDetail.setAmount(defaultValue);
            orderDetail.setSum(orderDetail.getMenu().getCost().multiply(BigDecimal.valueOf(defaultValue)));
        }

        lunchOrderService.addDetailOrder(orderDetail);

        sendBotMessageService.sendMessage(chatId, "Добавлена позиция " + orderDetail.getMenu().getName()
                + " в количестве " + orderDetail.getAmount());
    }
}
