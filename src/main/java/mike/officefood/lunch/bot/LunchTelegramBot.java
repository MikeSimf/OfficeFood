package mike.officefood.lunch.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class LunchTelegramBot extends TelegramLongPollingBot {

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
        if (update.hasMessage() && update.getMessage().hasText()) {
            String botAnswer = "";

            String message = update.getMessage().getText().trim();
            String chatId = update.getMessage().getChatId().toString();

            String chatName = update.getMessage().getChat().getTitle();
            Message chat = update.getMessage();
            System.out.println("chat = "+ chat.toString());



            SendMessage sm = new SendMessage();
            sm.setChatId(chatId);


            botAnswer += (chat.getFrom().getFirstName() != null) ? "тебя зовут " + chat.getFrom().getFirstName() :
                    "не знаю кто ты";
            botAnswer += (chat.getFrom().getUserName() != null) ? "\nтвой ник " + chat.getFrom().getUserName() : "";

            if (chatName != null) {
                botAnswer += "\n ты из чата " + chatName;
            } else {
                botAnswer += "\n ты пишешь лично";
            }

            sm.setText(botAnswer);

            try {
                execute(sm);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }
}
