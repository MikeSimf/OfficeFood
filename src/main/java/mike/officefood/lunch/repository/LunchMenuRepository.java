package mike.officefood.lunch.repository;

import mike.officefood.lunch.repository.entity.LunchMenu;
import org.springframework.data.repository.CrudRepository;

public interface LunchMenuRepository extends CrudRepository<LunchMenu, Long> {

}
