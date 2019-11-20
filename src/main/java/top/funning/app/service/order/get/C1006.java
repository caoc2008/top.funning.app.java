package top.funning.app.service.order.get;

import com.google.gson.Gson;
import net.sf.oval.constraint.NotNull;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.config.C;
import top.funning.app.database.dal.OrderDAL;
import top.funning.app.database.dal.ShopDAL;
import top.funning.app.database.table.Order;
import top.funning.app.database.table.Shop;
import top.funning.app.service.FnService;
import top.funning.app.service.order.list.OrderCollection;
import top.knxy.library.BaseService;
import top.knxy.library.config.Code;
import top.knxy.library.service.wechat.result.get.CL1002;
import top.knxy.library.utils.TextUtils;

public class C1006 extends FnService {

    @NotNull
    public String userId;

    @NotNull
    public String id;


    @Override
    public void run() throws Exception {
        SqlSession session = getSqlSession();
        OrderDAL dal = session.getMapper(OrderDAL.class);

        Order order = new Order();
        order.setUserId(userId);
        order.setId(id);
        order.setShopId(Integer.valueOf(header.shopId));

        order = dal.getOrderByUser(order);

        ShopDAL shopDAL = session.getMapper(ShopDAL.class);
        Shop shop = shopDAL.get(header.shopId);

        if (TextUtils.isEmpty(shop.getWechatAppid()) || TextUtils.isEmpty(shop.getWcpayMchId()) || TextUtils.isEmpty(shop.getWcpayApiKey())) {
            createResult(this, -6001, "小程序信息未填写");
            return;
        }

        if (order.getState() == 7) {
            CL1002 cl1002 = new CL1002();
            cl1002.id = id;
            cl1002.appid = shop.getWechatAppid();
            cl1002.mchId = shop.getWcpayMchId();
            cl1002.apiKey = shop.getWcpayApiKey();
            cl1002.start();
            if (cl1002.code == Code.Service.SUCCESS) {
                order.setState(2);
                dal.changeState(order);
                session.commit();
                createSuccess(this);
            } else {
                createResult(this, cl1002.code, cl1002.msg);
            }
        }

        this.data = OrderCollection.createOrder(order, new Gson());

        if (this.data == null) {
            createError(this);
            return;
        }
        createSuccess(this);
    }
}
