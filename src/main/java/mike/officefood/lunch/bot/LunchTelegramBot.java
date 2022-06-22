package mike.officefood.lunch.bot;

import mike.officefood.lunch.command.BotCommandContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class LunchTelegramBot extends TelegramLongPollingBot {

    private static final String COMMAND_PREFIX = "/";
    private static final String UNKNOWN_COMMAND = "/unknown";
    private static final String NO_COMMAND = "/nocommand";

    @Autowired
    private BotCommandContainer botCommandContainer;

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText() || update.hasCallbackQuery() && update.getCallbackQuery().getMessage().hasText()) {
            String message = "";
            if (update.hasCallbackQuery()) {
                message = update.getCallbackQuery().getMessage().getText().trim();
            } else {
                message = update.getMessage().getText().trim();
            }
            System.out.println("TOTAL MESSAGE = "+message);

            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();
                if (botCommandContainer.hasCommand(commandIdentifier)) {
                    botCommandContainer.getCommand(commandIdentifier).execute(update);
                } else {
                    botCommandContainer.getCommand(UNKNOWN_COMMAND).execute(update);
                }

            } else {
                botCommandContainer.getCommand(NO_COMMAND).execute(update);
            }
        }
    }
}
