package top.funning.app.service.order.finish;

import net.sf.oval.constraint.NotNull;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.config.OrderPayState;
import top.funning.app.database.dal.OrderDAL;
import top.funning.app.database.table.Order;
import top.funning.app.service.FnService;
import top.knxy.library.BaseService;
import top.knxy.library.oval.Length;


public class M1005 extends FnService {
    @Length(value = 32)
    public String id;


    @Override
    protected void run() throws Exception {

        SqlSession session = getSqlSession();
        OrderDAL operation = session.getMapper(OrderDAL.class);
        Order order = operation.getState(id, header.shopId);

        if (order.getState() != 2) {
            throw new Exception("修改不合法");
        }

        order.setState(OrderPayState.finish);
        order.setShopId(Integer.valueOf(header.shopId));
        operation.changeState(order);
        session.commit();

        createSuccess(this);
    }
}
