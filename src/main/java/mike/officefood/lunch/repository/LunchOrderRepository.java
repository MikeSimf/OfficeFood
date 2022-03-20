package mike.officefood.lunch.repository;

import mike.officefood.lunch.repository.entity.LunchOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface LunchOrderRepository extends CrudRepository<LunchOrder, Long> {
    List<LunchOrder> findAllByDate(Date date);

    LunchOrder findLunchOrderByDate(Date date);
}
