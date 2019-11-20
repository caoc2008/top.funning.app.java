package top.funning.app.service.order.create;

import com.google.gson.Gson;
import net.sf.oval.constraint.MinSize;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.dal.GoodDAL;
import top.funning.app.database.dal.OrderDAL;
import top.funning.app.database.table.Good;
import top.funning.app.database.table.Order;
import top.funning.app.service.FnService;
import top.knxy.library.BaseService;
import top.knxy.library.utils.ServiceUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class C1002 extends FnService {

    public String userId;
    @MinSize(value = 1)
    public List<Item> goodList;

    public static class Item {
        public Good body;
        public String amount;
    }

    @Override
    public void run() throws Exception {

        SqlSession session = getSqlSession();
        OrderDAL orderDAL = session.getMapper(OrderDAL.class);
        GoodDAL goodDAL = session.getMapper(GoodDAL.class);

        Data data = new Data();
        this.data = data;

        BigDecimal price = new BigDecimal(0);
        for (Item item : goodList) {
            Good good = goodDAL.get(String.valueOf(item.body.getId()),header.shopId);
            if (good == null) {
                createError(this, item.body.getName() + " 没货了");
                return;
            }
            item.body = good;
            price = new BigDecimal(good.getPrice()).multiply(new BigDecimal(item.amount)).add(price);
        }

        Gson gson = new Gson();

        Order order = new Order();
        order.setId(ServiceUtils.getUUid());
        order.setGoods(gson.toJson(goodList));
        order.setPrice(price.toString());
        order.setCreateDT(new Date());
        order.setUserId(userId);
        order.setState(1);
        order.setShopId(Integer.valueOf(header.shopId));

        int result = orderDAL.insert(order);
        session.commit();
        if (result < 1) {
            createError(this, "生成订单失败");
            return;
        }

        data.id = order.getId();
        createSuccess(this);
    }


    public static class Data {
        public String id;
    }
}
