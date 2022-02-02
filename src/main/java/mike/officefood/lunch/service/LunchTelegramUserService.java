package mike.officefood.lunch.service;

import mike.officefood.lunch.repository.entity.LunchTelegramUser;

import java.util.List;
import java.util.Optional;

public interface LunchTelegramUserService {
    void save(LunchTelegramUser lunchTelegramUser);

    List<LunchTelegramUser> retrieveAllActiveUsers();

    LunchTelegramUser findByChatId(String chatId);
}
