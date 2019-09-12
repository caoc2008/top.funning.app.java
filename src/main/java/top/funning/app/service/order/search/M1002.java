package top.funning.app.service.order.search;

import com.google.gson.Gson;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.dal.OrderDAL;
import top.funning.app.database.table.Order;
import top.funning.app.service.FnService;
import top.funning.app.service.order.list.OrderCollection;
import top.knxy.library.BaseService;
import top.knxy.library.utils.TextUtils;

public class M1002 extends FnService {

    public String id;

    @Override
    protected void run() throws Exception {
        if (TextUtils.isEmpty(id)) {
            createError(this);
            return;
        }

        SqlSession session = getSqlSession();
        OrderDAL operation = session.getMapper(OrderDAL.class);
        Order order = operation.getOrder(id,shopId);

        this.data = OrderCollection.createOrder(order,new Gson());

        createSuccess(this);
    }
}
