package mike.officefood.service;

import mike.officefood.repository.entity.LunchMenu;
import mike.officefood.repository.entity.LunchOrder;
import mike.officefood.repository.entity.LunchOrderDetail;
import mike.officefood.repository.entity.TelegramUser;

import java.util.Date;
import java.util.List;

public interface LunchOrderService {
    boolean startOrder(LunchOrder lunchOrder);

    boolean stopOrder(LunchOrder lunchOrder);

    List<LunchOrder> findAllByDate(Date date);

    LunchOrder findByDate(Date date);

    boolean makeOrder(LunchOrderDetail orderDetail);

    boolean clearDetailOrder(LunchOrder lunchOrder, TelegramUser user);

    LunchMenu findMenu(String str);

    //OperationResult showMenu(CommandContext context);

    //OperationResult showMyOrder(CommandContext context);
}
