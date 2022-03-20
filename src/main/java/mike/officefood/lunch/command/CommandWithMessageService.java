package mike.officefood.lunch.command;

import mike.officefood.lunch.service.LunchOrderService;
import mike.officefood.lunch.service.LunchTelegramUserService;
import mike.officefood.lunch.service.SendBotMessageService;
import org.springframework.beans.factory.annotation.Autowired;

public class CommandWithMessageService {
    @Autowired
    protected SendBotMessageService sendBotMessageService;

    @Autowired
    protected LunchTelegramUserService lunchTelegramUserService;

    @Autowired
    protected LunchOrderService lunchOrderService;
}
