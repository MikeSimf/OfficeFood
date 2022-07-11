package mike.officefood.main.config;

import mike.officefood.service.LunchOrderService;
import mike.officefood.service.LunchTelegramUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandWithMessageService {
    @Autowired
    protected LunchTelegramUserService lunchTelegramUserService;

    @Autowired
    protected LunchOrderService lunchOrderService;
}
