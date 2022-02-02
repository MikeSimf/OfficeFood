package mike.officefood.lunch.repository;

import mike.officefood.lunch.repository.entity.LunchTelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LunchTelegramUserRepository extends CrudRepository<LunchTelegramUser, Long> {
    List<LunchTelegramUser> findAllByActiveTrue();

    LunchTelegramUser findByChatId(String chatId);
}
