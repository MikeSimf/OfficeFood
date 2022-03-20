package mike.officefood.lunch.repository;

import mike.officefood.lunch.repository.entity.LunchOrderDetail;
import org.springframework.data.repository.CrudRepository;

public interface LunchOrderDetailRepository extends CrudRepository<LunchOrderDetail, Long> {

}
