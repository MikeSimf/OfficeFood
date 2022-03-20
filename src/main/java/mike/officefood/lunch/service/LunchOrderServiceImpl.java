package mike.officefood.lunch.service;

import mike.officefood.lunch.repository.LunchMenuRepository;
import mike.officefood.lunch.repository.LunchOrderDetailRepository;
import mike.officefood.lunch.repository.LunchOrderRepository;
import mike.officefood.lunch.repository.entity.LunchMenu;
import mike.officefood.lunch.repository.entity.LunchOrder;
import mike.officefood.lunch.repository.entity.LunchOrderDetail;
import mike.officefood.lunch.repository.entity.LunchTelegramUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LunchOrderServiceImpl implements LunchOrderService {
    private final LunchOrderRepository lunchOrderRepository;
    private final LunchOrderDetailRepository lunchOrderDetailRepository;
    private final LunchMenuRepository lunchMenuRepository;

    @Autowired
    public LunchOrderServiceImpl(LunchOrderRepository lunchOrderRepository,
            LunchOrderDetailRepository lunchOrderDetailRepository, LunchMenuRepository lunchMenuRepository) {
        this.lunchOrderRepository = lunchOrderRepository;
        this.lunchOrderDetailRepository = lunchOrderDetailRepository;
        this.lunchMenuRepository = lunchMenuRepository;
    }

    @Override
    public void saveOrder(LunchOrder order) {
        lunchOrderRepository.save(order);
    }

    @Override
    public List<LunchOrder> findAllByDate(Date date) {
        return lunchOrderRepository.findAllByDate(date);
    }

    @Override
    public void addDetailOrder(LunchOrderDetail orderDetail) {
        lunchOrderDetailRepository.save(orderDetail);
    }

    @Override
    public void clearDetailOrder(LunchOrder lunchOrder, LunchTelegramUser user) {
        List<LunchOrderDetail> orderDetails = lunchOrderDetailRepository.findAllByOrder(lunchOrder);
        orderDetails.stream()
                .filter(orderDetail -> orderDetail.getClient().equals(user))
                .forEach(orderDetail -> lunchOrderDetailRepository.delete(orderDetail));
    }

    @Override
    public LunchMenu findMenu(String str) {
        LunchMenu lunchMenu = lunchMenuRepository.findLunchMenuByUniqCode(str);
        if (lunchMenu == null) {
            lunchMenu = lunchMenuRepository.findLunchMenuByShortName(str);
        }
        return lunchMenu;
    }

    @Override
    public LunchOrder findByDate(Date date) {
        return lunchOrderRepository.findLunchOrderByDate(date);
    }
}
