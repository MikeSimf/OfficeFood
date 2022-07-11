package mike.officefood.repository;

import mike.officefood.repository.entity.LunchOrder;
import mike.officefood.repository.entity.LunchOrderDetail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LunchOrderDetailRepository extends CrudRepository<LunchOrderDetail, Long> {
    List<LunchOrderDetail> findAllByOrder(LunchOrder order);
}
