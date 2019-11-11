package top.funning.app.service.order.list;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.dal.OrderDAL;
import top.funning.app.database.table.Order;
import top.funning.app.service.FnService;
import top.knxy.library.BaseService;

import java.util.List;

public class C1005 extends FnService {

    public String userId;

    @Override
    public void run() throws Exception {

        SqlSession session = getSqlSession();
        OrderDAL mapper = session.getMapper(OrderDAL.class);
        List<Order> orders = mapper.getListByUserId(userId,shopId);
        this.data = new OrderCollection(orders);

        createSuccess(this);
    }

}
