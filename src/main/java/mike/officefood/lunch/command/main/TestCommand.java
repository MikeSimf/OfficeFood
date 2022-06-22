package mike.officefood.lunch.command.main;

import mike.officefood.lunch.command.Command;
import mike.officefood.lunch.command.CommandWithMessageService;
import mike.officefood.lunch.command.config.BotCommand;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@BotCommand(name = "/test")
public class TestCommand extends CommandWithMessageService implements Command {
    public static Integer ID_MESSAGE = null;

    @Override
    public void execute(Update update) {
        String message = "";
        String chatId = "";
        if (update.hasCallbackQuery()) {
            message = update.getCallbackQuery().getMessage().getText().trim();
            chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        } else {
            message = update.getMessage().getText().trim();
            chatId = update.getMessage().getChatId().toString();
        }


        if (ID_MESSAGE != null) {
            Boolean result = sendBotMessageService.editMessage(ID_MESSAGE, chatId, message);
            if (result) System.out.println("ИСПРАВИЛ СВОЁ");
        } else {
            Integer messageId = sendBotMessageService.sendMessage(chatId, message);
            if (messageId != null) ID_MESSAGE = messageId;
            System.out.println("MESSAGE_ID" + ID_MESSAGE);
        }

        Integer messageInputId = update.getMessage().getMessageId();
        Boolean resultDelete = sendBotMessageService.removeMessage(messageInputId, chatId);
        if (resultDelete) System.out.println("УДАЛИЛ ЧУЖОЕ");



    }
}

