package mike.officefood.repository;

import mike.officefood.repository.entity.LunchMenu;
import org.springframework.data.repository.CrudRepository;

public interface LunchMenuRepository extends CrudRepository<LunchMenu, Long> {
    LunchMenu findLunchMenuByUniqCode(String code);

    LunchMenu findLunchMenuByShortName(String name);

}
