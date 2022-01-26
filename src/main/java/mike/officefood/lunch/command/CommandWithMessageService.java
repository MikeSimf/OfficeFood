package mike.officefood.lunch.command;

import mike.officefood.lunch.service.SendBotMessageService;
import org.springframework.beans.factory.annotation.Autowired;

public class CommandWithMessageService {
    @Autowired
    protected SendBotMessageService sendBotMessageService;

}
