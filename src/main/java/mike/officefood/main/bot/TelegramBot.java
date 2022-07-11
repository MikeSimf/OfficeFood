package mike.officefood.main.bot;

import mike.officefood.main.context.ContextBuilder;
import mike.officefood.main.context.CommandContext;
import mike.officefood.main.result.CommandResult;
import mike.officefood.main.result.CommandResultPerformer;
import mike.officefood.main.config.BotCommandContainer;
import mike.officefood.service.SendBotMessageService;
import mike.officefood.service.SendBotMessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Autowired
    private BotCommandContainer botCommandContainer;

    private final SendBotMessageService sendBotMessageService = new SendBotMessageServiceImpl(this);

    private final CommandResultPerformer commandResultPerformer =
            new CommandResultPerformer(this.sendBotMessageService);

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

            CommandContext context = new ContextBuilder()
                    .setChatId(update.getMessage().getChatId().toString())
                    .setMessage(message)
                    .setChatType(update.getMessage().getChat().getType())
                    .build();

            CommandResult commandResult = botCommandContainer.executeCommand(context);
            commandResultPerformer.performCommandResult(commandResult);
        }
    }
}
