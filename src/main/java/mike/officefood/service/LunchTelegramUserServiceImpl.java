package mike.officefood.service;

import mike.officefood.repository.LunchTelegramUserRepository;
import mike.officefood.repository.entity.TelegramUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LunchTelegramUserServiceImpl implements LunchTelegramUserService {
    private final LunchTelegramUserRepository lunchTelegramUserRepository;

    @Autowired
    public LunchTelegramUserServiceImpl(LunchTelegramUserRepository lunchTelegramUserRepository) {
        this.lunchTelegramUserRepository = lunchTelegramUserRepository;
    }

    @Override
    public boolean addUser(TelegramUser user) {
        return lunchTelegramUserRepository.save(user) != null;
    }

    @Override
    public List<TelegramUser> retrieveAllActiveUsers() {
        return lunchTelegramUserRepository.findAllByActiveTrue();
    }

    @Override
    public TelegramUser findByChatId(String chatId) {
        return lunchTelegramUserRepository.findByChatId(chatId);
    }
}
