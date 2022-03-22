package mike.officefood.lunch.command.admin;

import mike.officefood.lunch.ContextBuilder;
import mike.officefood.lunch.OperationContext;
import mike.officefood.lunch.command.Command;
import mike.officefood.lunch.command.CommandWithMessageService;
import mike.officefood.lunch.command.config.BotCommand;
import mike.officefood.lunch.repository.entity.LunchTelegramUser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Component
@BotCommand(name = "/regme")
public class RegmeCommand extends CommandWithMessageService implements Command {

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        LunchTelegramUser lunchTelegramUser = lunchTelegramUserService.findByChatId(chatId);

        OperationContext context = new ContextBuilder()
                .setChatId(chatId)
                .setUser(lunchTelegramUser)
                .setChatType(update.getMessage().getChat().getType())
                .build();

        String resultMessage = lunchTelegramUserService.addUser(context).getResult();
        sendBotMessageService.sendMessage(chatId, resultMessage);

    }
}
