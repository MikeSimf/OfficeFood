package mike.officefood.service;

import mike.officefood.repository.entity.TelegramUser;

import java.util.List;

public interface LunchTelegramUserService {
    boolean addUser(TelegramUser user);

    List<TelegramUser> retrieveAllActiveUsers();

    TelegramUser findByChatId(String chatId);
}
