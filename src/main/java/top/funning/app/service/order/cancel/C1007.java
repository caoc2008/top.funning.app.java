package top.funning.app.service.order.cancel;

import net.sf.oval.constraint.NotNull;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.config.OrderPayState;
import top.funning.app.database.dal.OrderDAL;
import top.funning.app.database.table.Order;
import top.funning.app.service.FnService;
import top.knxy.library.BaseService;
import top.knxy.library.utils.TextUtils;

public class C1007 extends FnService {

    @NotNull
    public String userId;
    @NotNull
    public String id;

    @Override
    public void run() throws Exception {
        Order order = new Order();
        order.setState(OrderPayState.cancel);
        order.setUserId(userId);
        order.setId(id);
        order.setShopId(Integer.valueOf(header.shopId));

        SqlSession session = getSqlSession();
        int result = session.getMapper(OrderDAL.class).changeStateByUser(order);
        session.commit();


        if (result < 1) {
            createError(this, "申请退款失败");
            return;
        }

        createSuccess(this);
    }


}
