package mike.officefood.lunch.command;

import mike.officefood.lunch.command.config.BotCommand;
import mike.officefood.lunch.repository.entity.LunchTelegramUser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Component
@BotCommand(name = "/regme")
public class RegmeCommand extends CommandWithMessageService implements Command{

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String resultMessage = "";

        LunchTelegramUser lunchTelegramUser = lunchTelegramUserService.findByChatId(chatId);
        System.out.println("aaa"+lunchTelegramUser);
        if (lunchTelegramUser == null) {
            LunchTelegramUser telegramUser = new LunchTelegramUser();
            telegramUser.setActive(true);
            telegramUser.setChatId(chatId);
            lunchTelegramUserService.save(telegramUser);
            resultMessage = "Пользователь успешно зарегистрирован";
        } else {
            lunchTelegramUser.setActive(true);
            lunchTelegramUserService.save(lunchTelegramUser);
            resultMessage = "Пользователь уже есть";
        }

        sendBotMessageService.sendMessage(chatId, resultMessage);

    }
}
