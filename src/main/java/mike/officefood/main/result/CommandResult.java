package mike.officefood.main.result;

import mike.officefood.service.SendBotMessageService;

public interface CommandResult {
    public void perform(SendBotMessageService sendBotMessageService);
}
