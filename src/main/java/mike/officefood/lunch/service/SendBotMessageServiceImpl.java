package mike.officefood.lunch.service;

import mike.officefood.lunch.bot.LunchTelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendDice;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButtonPollType;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiValidationException;

import java.util.Arrays;
import java.util.List;

@Service
public class SendBotMessageServiceImpl implements SendBotMessageService{
    @Autowired
    private LunchTelegramBot lunchTelegramBot;

    @Override
    public Integer sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);


        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("TEXT1");

        button1.setCallbackData("/test asdasdas");

        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("TEXT2");
        button2.setCallbackData("/help ");

        List<InlineKeyboardButton> row1 = Arrays.asList(button1, button2);

        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText("TEXT3");
        button3.setCallbackData("/asdas asdasdasdasd");

        InlineKeyboardButton button4 = new InlineKeyboardButton();
        button4.setText("TEXT4");
        button4.setCallbackData("/yyyyyy ");

        List<InlineKeyboardButton> row2 = Arrays.asList(button3, button4);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(Arrays.asList(row1, row2));



        sendMessage.setReplyMarkup(inlineKeyboardMarkup);


        try {
            Message execute = lunchTelegramBot.execute(sendMessage);
            return execute.getMessageId();
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean editMessage(Integer messageId, String chatId, String newMessage) {
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setMessageId(messageId);
        editMessageText.setChatId(chatId);
        editMessageText.enableHtml(true);
        editMessageText.setText(newMessage);

        try {
            lunchTelegramBot.execute(editMessageText);
            return Boolean.TRUE;
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        return Boolean.FALSE;
    }

    @Override
    public Boolean removeMessage(Integer messageId, String chatId) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(chatId);
        deleteMessage.setMessageId(messageId);

        try {
            return lunchTelegramBot.execute(deleteMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }
}
