package top.funning.app.service.order.refund.client;

import net.sf.oval.constraint.NotNull;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.config.OrderPayState;
import top.funning.app.controller.admin.OrderReminder;
import top.funning.app.database.dal.OrderDAL;
import top.funning.app.database.table.Order;
import top.funning.app.service.FnService;
import top.knxy.library.BaseService;

public class C1008 extends FnService {

    public String userId;
    @NotNull
    public String id;

    @Override
    public void run() throws Exception {
        Order order = new Order();
        order.setState(OrderPayState.refunding);
        order.setUserId(userId);
        order.setId(id);

        SqlSession session = getSqlSession();
        int result = session.getMapper(OrderDAL.class).changeStateByUser(order);
        session.commit();

        if (result < 1) {
            createError(this, "申请退款失败");
            return;
        }

        OrderReminder.broadcast();

        createSuccess(this);

    }
}
