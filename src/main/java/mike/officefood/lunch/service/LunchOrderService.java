package mike.officefood.lunch.service;

import mike.officefood.lunch.OperationContext;
import mike.officefood.lunch.OperationResult;
import mike.officefood.lunch.repository.entity.LunchMenu;
import mike.officefood.lunch.repository.entity.LunchOrder;
import mike.officefood.lunch.repository.entity.LunchOrderDetail;
import mike.officefood.lunch.repository.entity.LunchTelegramUser;

import java.util.Date;
import java.util.List;

public interface LunchOrderService {
    OperationResult startOrder(OperationContext context);

    OperationResult stopOrder(OperationContext context);

    List<LunchOrder> findAllByDate(Date date);

    LunchOrder findByDate(Date date);

    OperationResult makeOrder(OperationContext context);

    void clearDetailOrder(LunchOrder lunchOrder, LunchTelegramUser user);

    LunchMenu findMenu(String str);

    OperationResult showMenu(OperationContext context);

    OperationResult showMyOrder(OperationContext context);
}
