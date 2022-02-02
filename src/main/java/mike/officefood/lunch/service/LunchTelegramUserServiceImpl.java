package mike.officefood.lunch.service;

import mike.officefood.lunch.repository.LunchTelegramUserRepository;
import mike.officefood.lunch.repository.entity.LunchTelegramUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LunchTelegramUserServiceImpl implements LunchTelegramUserService {
    private final LunchTelegramUserRepository lunchTelegramUserRepository;

    @Autowired
    public LunchTelegramUserServiceImpl(LunchTelegramUserRepository lunchTelegramUserRepository) {
        this.lunchTelegramUserRepository = lunchTelegramUserRepository;
    }

    @Override
    public void save(LunchTelegramUser lunchTelegramUser) {
        lunchTelegramUserRepository.save(lunchTelegramUser);
    }

    @Override
    public List<LunchTelegramUser> retrieveAllActiveUsers() {
        return lunchTelegramUserRepository.findAllByActiveTrue();
    }

    @Override
    public LunchTelegramUser findByChatId(String chatId) {
        return lunchTelegramUserRepository.findByChatId(chatId);
    }
}
