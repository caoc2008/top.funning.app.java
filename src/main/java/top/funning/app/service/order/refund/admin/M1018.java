package top.funning.app.service.order.refund.admin;

import net.sf.oval.constraint.NotNull;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.config.C;
import top.funning.app.database.dal.OrderDAL;
import top.funning.app.database.dal.ShopDAL;
import top.funning.app.database.table.Order;
import top.funning.app.database.table.Shop;
import top.funning.app.service.FnService;
import top.knxy.library.BaseService;
import top.knxy.library.config.Code;
import top.knxy.library.ServiceException;
import top.knxy.library.utils.TextUtils;
import top.knxy.library.service.wechat.refund.CL1003;

import java.math.BigDecimal;

public class M1018 extends FnService {

    public static final String TAG = "Normal.Order.Refund.Admin";

    @NotNull
    public String id;

    @Override
    protected void run() throws Exception {

        SqlSession session = getSqlSession();
        OrderDAL operation = session.getMapper(OrderDAL.class);
        Order order = operation.getOrder(id, shopId);

        if (order.getState() != 4) {

            throw new ServiceException("修改不合法");
        }

        ShopDAL shopDAL = session.getMapper(ShopDAL.class);
        Shop shop = shopDAL.get(shopId);
        if (TextUtils.isEmpty(shop.getWechatAppid()) || TextUtils.isEmpty(shop.getWcpayMchId()) || TextUtils.isEmpty(shop.getWcpayApiKey())) {
            createResult(this, -6001, "小程序信息未填写");
            return;
        }

        CL1003 cl1003 = new CL1003();
        cl1003.appid = shop.getWechatAppid();
        cl1003.mchId = shop.getWcpayMchId();
        cl1003.apiKey = shop.getWcpayApiKey();
        cl1003.orderId = order.getId();
        cl1003.p12FilePath = C.WCPay.p12Path + shop.getP12FileName();
        cl1003.money = new BigDecimal(order.getAmount()).multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_UP).toString();
        cl1003.start();
        if (cl1003.code == Code.Service.SUCCESS) {
            order.setState(6);
            int row = operation.changeState(order);
            session.commit();
            if (row < 1) {
                throw new ServiceException("row<1");
            }
            createSuccess(this);
        } else {
            createResult(this, cl1003.code, cl1003.msg);
        }

    }
}
