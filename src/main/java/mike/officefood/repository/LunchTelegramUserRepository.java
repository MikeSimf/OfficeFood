package mike.officefood.repository;

import mike.officefood.repository.entity.TelegramUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LunchTelegramUserRepository extends CrudRepository<TelegramUser, Long> {
    List<TelegramUser> findAllByActiveTrue();

    TelegramUser findByChatId(String chatId);
}
