package mike.officefood.service;

import mike.officefood.repository.LunchMenuRepository;
import mike.officefood.repository.LunchOrderDetailRepository;
import mike.officefood.repository.LunchOrderRepository;
import mike.officefood.repository.entity.LunchMenu;
import mike.officefood.repository.entity.LunchOrder;
import mike.officefood.repository.entity.LunchOrderDetail;
import mike.officefood.repository.entity.UserRole;
import mike.officefood.repository.entity.TelegramUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class LunchOrderServiceImpl implements LunchOrderService {
    private final LunchOrderRepository lunchOrderRepository;
    private final LunchOrderDetailRepository lunchOrderDetailRepository;
    private final LunchMenuRepository lunchMenuRepository;

    private final Integer DEFAULT_AMOUNT = 1;

    @Autowired
    public LunchOrderServiceImpl(LunchOrderRepository lunchOrderRepository,
            LunchOrderDetailRepository lunchOrderDetailRepository, LunchMenuRepository lunchMenuRepository) {
        this.lunchOrderRepository = lunchOrderRepository;
        this.lunchOrderDetailRepository = lunchOrderDetailRepository;
        this.lunchMenuRepository = lunchMenuRepository;
    }

    private boolean isAdminRole(UserRole role) {
        return UserRole.ADMIN.equals(role);
    }

    private boolean isUnregisterRole(UserRole role) {
        return UserRole.ANONYMOUS.equals(role);
    }

    /*private OperationResult noAdminRoleMessage() {
        return new OperationResult("У вас нет прав на выполнение данной операции");
    }

    private OperationResult unregisterRoleMessage() {
        return new OperationResult("Для выполнения данной команды надо зарегистрироваться");
    }*/



    public boolean startOrder(LunchOrder order) {
        System.out.println("OperationResult start...");
        /*
        if (!isAdminRole(context.getRole())) {
            return noAdminRoleMessage();
        }

        if (isUnregisterRole(context.getRole())) {
            return unregisterRoleMessage();
        }*/

        //Date date = (Date) Optional.of(context.getParam("orderDate")).orElse(new Date());

        //LunchOrder order = lunchOrderRepository.findLunchOrderByDate(date);

        /*if (order == null) {
            LunchOrder newOrder = new LunchOrder();
            newOrder.setState("START");
            newOrder.setDate(date);
            newOrder.setPayer(context.getUser());
            lunchOrderRepository.save(newOrder);
        } else {
            order.setState("START");
            lunchOrderRepository.save(order);
        }*/
        order.setState("START");
        try {
            lunchOrderRepository.save(order);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        System.out.println("Открыт сбор заказа на сегодня");
        return true;
    }

    public boolean stopOrder(LunchOrder order) {

       /* Date date = (Date) Optional.of(context.getParam("orderDate")).orElse(new Date());

        LunchOrder order = lunchOrderRepository.findLunchOrderByDate(date);

        if (order == null) {
            LunchOrder newOrder = new LunchOrder();
            newOrder.setState("STOP");
            newOrder.setDate(date);
            newOrder.setPayer(context.getUser());
            lunchOrderRepository.save(newOrder);
        } else {
            order.setState("STOP");
            lunchOrderRepository.save(order);
        }*/

        order.setState("STOP");
        try {
            lunchOrderRepository.save(order);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        System.out.println("Сбор заказа на сегодня закрыт");
        return true;

    }

    @Override
    public List<LunchOrder> findAllByDate(Date date) {
        return lunchOrderRepository.findAllByDate(date);
    }

    @Override
    public boolean makeOrder(LunchOrderDetail orderDetail) {
        return false;
        /*if (isUnregisterRole(context.getRole())) {
            return unregisterRoleMessage();
        }
        Date date = (Date) Optional.of(context.getParam("orderDate")).orElse(new Date());
        LunchOrder order = this.findByDate(date);

        if (order == null) {
            return new OperationResult("Прием заказов еще не начался");
        }

        if (!"START".equals(order.getState())) {
            return new OperationResult("Прием заказа на сегодня закрыт");
        }

        String menuTag = (String) Optional.of(context.getParam("menu")).orElse("");
        if ("".equals(menuTag)) {
            return new OperationResult("Не указана позиция");
        }

        LunchMenu lunchMenu = this.findMenu(menuTag);
        if (lunchMenu == null) {
            return new OperationResult("Такой позиции не найдено");
        }

        Integer amount = (Integer) Optional.of(context.getParam("amount")).orElse(DEFAULT_AMOUNT);

        LunchOrderDetail orderDetail = new LunchOrderDetail();
        orderDetail.setOrder(order);
        orderDetail.setClient(context.getUser());
        orderDetail.setMenu(lunchMenu);
        orderDetail.setCost(lunchMenu.getCost());
        orderDetail.setAmount(amount);
        orderDetail.setSum(lunchMenu.getCost().multiply(BigDecimal.valueOf(amount)));

        lunchOrderDetailRepository.save(orderDetail);
        return new OperationResult("Добавлена позиция \"" + orderDetail.getMenu().getName()
                + "\" в количестве " + orderDetail.getAmount());*/
    }

    @Override
    public boolean clearDetailOrder(LunchOrder lunchOrder, TelegramUser user) {
        List<LunchOrderDetail> orderDetails = lunchOrderDetailRepository.findAllByOrder(lunchOrder);
        orderDetails.stream()
                .filter(orderDetail -> orderDetail.getClient().equals(user))
                .forEach(orderDetail -> lunchOrderDetailRepository.delete(orderDetail));
        return true;
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

    /*@Override
    public OperationResult showMenu(CommandContext context) {
        Iterable<LunchMenu> menus = lunchMenuRepository.findAll();
        StringBuffer result = new StringBuffer();

        menus.forEach(lunchMenu -> {
            result.append(lunchMenu.getUniqCode() + " " + lunchMenu.getShortName() + " " + lunchMenu.getCost().toString() + "р.\n");
        });

        return new OperationResult(result.toString());
    }

    @Override
    @Transactional
    public OperationResult showMyOrder(CommandContext context) {
        Date date = (Date) Optional.of(context.getParam("orderDate")).orElse(new Date());
        LunchOrder order = this.findByDate(date);

        if (order == null) {
            return new OperationResult("Прием заказов еще не начался");
        }

        if (order.getDetails().size() == 0) {
            return new OperationResult("Вы еще ничего не заказали");
        }

        StringBuffer result = new StringBuffer();
        order.getDetails()
                .stream()
                .filter(detail -> {return detail.getClient().equals(context.getUser());})
                .forEach(detail -> {
                    result.append(detail.getMenu().getShortName() + " стоимость:" + detail.getSum().toString() + "р.\n");
                });

        return new OperationResult(result.toString());
    }*/
}
