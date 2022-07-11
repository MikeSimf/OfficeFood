package mike.officefood.main.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import mike.officefood.service.SendBotMessageService;

@Data
@AllArgsConstructor
public class SendSimpleMessage implements CommandResult{
    private String chatId;
    private String message;

    public void perform(SendBotMessageService sendBotMessageService) {
        sendBotMessageService.sendMessage(chatId, message);
    }
}