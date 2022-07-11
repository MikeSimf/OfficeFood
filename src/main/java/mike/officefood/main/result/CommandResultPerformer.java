package mike.officefood.main.result;

import mike.officefood.service.SendBotMessageService;
import org.springframework.stereotype.Component;

@Component
public class CommandResultPerformer {
    private SendBotMessageService sendBotMessageService;

    public CommandResultPerformer(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    public void performCommandResult(CommandResult commandResult) {
        commandResult.perform(sendBotMessageService);
    }
}
