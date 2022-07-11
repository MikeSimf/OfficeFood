package mike.officefood.commands.common;

import mike.officefood.main.config.BotCommand;
import mike.officefood.main.config.Command;
import mike.officefood.main.config.CommandWithMessageService;
import mike.officefood.main.context.CommandContext;
import mike.officefood.main.result.CommandResult;
import mike.officefood.main.result.SendSimpleMessage;
import org.springframework.stereotype.Component;

@Component
@BotCommand(name = "/test")
public class TestCommandOld extends CommandWithMessageService implements Command {
    public static Integer ID_MESSAGE = null;

    public CommandResult execute(CommandContext context) {
        return new SendSimpleMessage(context.getChatId(), "TEST");
    }
    /*
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



    }*/
}

