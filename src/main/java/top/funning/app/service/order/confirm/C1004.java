package top.funning.app.service.order.confirm;

import net.sf.oval.constraint.NotNull;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.dal.OrderDAL;
import top.funning.app.database.table.Order;
import top.funning.app.service.FnService;
import top.knxy.library.BaseService;
import top.knxy.library.ServiceException;

public class C1004 extends FnService {

    @NotNull
    public String userId;
    @NotNull
    public String id;

    @Override
    public void run() throws Exception {

        SqlSession session = getSqlSession();
        OrderDAL mapper = session.getMapper(OrderDAL.class);

        Order order = new Order();
        order.setId(id);
        order.setUserId(userId);
        order.setShopId(Integer.valueOf(shopId));
        order = mapper.getOrderByUser(order);
        if (order == null) {
            throw new ServiceException("没有订单 id = " + id);
        }

        if (order.getState() == 1) {
            order.setState(7);
            int result = mapper.changeState(order);
            session.commit();
            if (result < 1) {
                throw new ServiceException("订单修改失败 order id = " + id);
            }

            createSuccess(this);
        } else {
            throw new ServiceException("订单修改失败 state != 1");
        }
    }
}
