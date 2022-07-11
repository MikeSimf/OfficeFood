package mike.officefood.service;

import mike.officefood.repository.entity.TelegramUser;

public interface LunchMenuService {
    void addMenu(TelegramUser telegramUser);
}
