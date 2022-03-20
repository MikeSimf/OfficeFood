package mike.officefood.lunch.repository;

import mike.officefood.lunch.repository.entity.LunchOrder;
import mike.officefood.lunch.repository.entity.LunchOrderDetail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LunchOrderDetailRepository extends CrudRepository<LunchOrderDetail, Long> {
    List<LunchOrderDetail> findAllByOrder(LunchOrder order);
}
