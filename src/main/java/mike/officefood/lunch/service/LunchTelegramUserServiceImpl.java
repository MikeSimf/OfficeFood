package mike.officefood.lunch.service;

import mike.officefood.lunch.OperationContext;
import mike.officefood.lunch.OperationResult;
import mike.officefood.lunch.repository.LunchTelegramUserRepository;
import mike.officefood.lunch.repository.entity.LunchRole;
import mike.officefood.lunch.repository.entity.LunchTelegramUser;
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
    public OperationResult addUser(OperationContext context) {
        if (!"private".equals(context.getChatType())) {
            return new OperationResult("Для регистрации напишите боту в личку");
        }

        LunchTelegramUser user = context.getUser();
        if (user == null) {
            LunchTelegramUser telegramUser = new LunchTelegramUser();
            telegramUser.setActive(true);
            telegramUser.setChatId(context.getChatId());
            telegramUser.setRole(LunchRole.LUNCH_USER);
            lunchTelegramUserRepository.save(telegramUser);
            return new OperationResult("Пользователь успешно зарегистрирован");
        } else {
            user.setActive(true);
            if (user.getRole() == null) {
                user.setRole(LunchRole.LUNCH_USER);
            }

            lunchTelegramUserRepository.save(user);
            return new OperationResult("Пользователь уже был зарегистрирован" );
        }
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
