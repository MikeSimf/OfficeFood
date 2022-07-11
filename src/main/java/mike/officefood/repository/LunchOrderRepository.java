package mike.officefood.repository;

import mike.officefood.repository.entity.LunchOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface LunchOrderRepository extends CrudRepository<LunchOrder, Long> {
    List<LunchOrder> findAllByDate(Date date);

    LunchOrder findLunchOrderByDate(Date date);
}
