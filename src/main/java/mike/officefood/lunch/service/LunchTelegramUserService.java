package mike.officefood.lunch.service;

import mike.officefood.lunch.OperationContext;
import mike.officefood.lunch.OperationResult;
import mike.officefood.lunch.repository.entity.LunchTelegramUser;

import java.util.List;
import java.util.Optional;

public interface LunchTelegramUserService {
    OperationResult addUser(OperationContext context);

    List<LunchTelegramUser> retrieveAllActiveUsers();

    LunchTelegramUser findByChatId(String chatId);
}
