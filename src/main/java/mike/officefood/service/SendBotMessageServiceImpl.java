package mike.officefood.service;

import mike.officefood.main.bot.TelegramBot;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class SendBotMessageServiceImpl implements SendBotMessageService{
    private final TelegramBot telegramBot;

    public SendBotMessageServiceImpl(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @Override
    public Integer sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);

/*
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
        inlineKeyboardMarkup.setKeyboard(Arrays.asList(row1, row2));*/



      //  sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        System.out.println("OperationResult message = "+message);
        System.out.println("OperationResult sendMessage = "+sendMessage.toString());
        try {
            Message execute = telegramBot.execute(sendMessage);
            return execute.getMessageId();
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Boolean editMessage(Integer messageId, String chatId, String newMessage) {
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setMessageId(messageId);
        editMessageText.setChatId(chatId);
        editMessageText.enableHtml(true);
        editMessageText.setText(newMessage);

        try {
            telegramBot.execute(editMessageText);
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
            return telegramBot.execute(deleteMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }
}
