package top.funning.app.service.pay.confirm.alipay;

import net.sf.oval.constraint.NotNull;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.config.OrderPayMethod;
import top.funning.app.controller.admin.OrderReminder;
import top.funning.app.database.dal.OrderDAL;
import top.funning.app.database.table.Order;
import top.funning.app.service.FnService;
import top.knxy.library.ServiceException;
import top.knxy.library.oval.Length;

import java.math.BigDecimal;
import java.util.Date;

public class C1035 extends FnService {


    @Length(value = 32)
    public String outTradeNo;
    @NotNull
    public String totalAmount;
    @NotNull
    public String buyerPayAmount;


    @Override
    protected void run() throws Exception {

        SqlSession session = getSqlSession();
        String orderId = outTradeNo;
        OrderDAL dal = session.getMapper(OrderDAL.class);
        Order order = dal.getOrder(orderId, header.shopId);

        BigDecimal wcMoney = new BigDecimal(buyerPayAmount);
        BigDecimal ownMoney = new BigDecimal(order.getAmount());

        if (wcMoney.compareTo(ownMoney) != 0) {
            throw new ServiceException("交易金额不对等,order id = " + orderId);
        }

        order.setPayDT(new Date());
        order.setState(2);
        order.setShopId(Integer.valueOf(header.shopId));
        order.setPayMethod(OrderPayMethod.ALIPAY);
        int result = dal.update(order);
        session.commit();
        if (result < 1) {
            throw new ServiceException("订单状态修改失败,order id = " + orderId);
        }

        OrderReminder.broadcast();
        createSuccess(this);

    }
}
