package mike.officefood.lunch.service;

import mike.officefood.lunch.repository.entity.LunchMenu;
import mike.officefood.lunch.repository.entity.LunchOrder;
import mike.officefood.lunch.repository.entity.LunchOrderDetail;
import mike.officefood.lunch.repository.entity.LunchTelegramUser;

import java.util.Date;
import java.util.List;

public interface LunchOrderService {
    void saveOrder(LunchOrder order);

    List<LunchOrder> findAllByDate(Date date);

    LunchOrder findByDate(Date date);

    void addDetailOrder(LunchOrderDetail orderDetail);

    void clearDetailOrder(LunchOrder lunchOrder, LunchTelegramUser user);

    LunchMenu findMenu(String str);
}
