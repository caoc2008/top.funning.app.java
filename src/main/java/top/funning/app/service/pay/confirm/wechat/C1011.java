package top.funning.app.service.pay.confirm.wechat;

import org.apache.ibatis.session.SqlSession;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import top.funning.app.config.C;
import top.funning.app.config.OrderPayMethod;
import top.funning.app.config.OrderPayState;
import top.funning.app.controller.admin.OrderReminder;
import top.funning.app.database.dal.OrderDAL;
import top.funning.app.database.dal.ShopDAL;
import top.funning.app.database.table.Order;
import top.funning.app.database.table.Shop;
import top.funning.app.service.FnService;
import top.knxy.library.BaseService;
import top.knxy.library.ServiceException;
import top.knxy.library.utils.LogUtils;
import top.knxy.library.utils.ServiceUtils;
import top.knxy.library.utils.TextUtils;
import top.knxy.library.utils.XmlUtils;
import top.knxy.library.vehicle.wepay.ConfirmInfo;

import java.math.BigDecimal;
import java.util.*;

/**
 * https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=9_7
 */
public class C1011 extends FnService {
    public final static String TAG = "C1011";

    public String body;

    @Override
    protected void run() throws Exception {
        Document doc = DocumentHelper.parseText(body); // 将字符串转为XML
        Element rootElt = doc.getRootElement(); // 获取根节点

        TreeMap<Object, Object> tMap = new TreeMap<>();
        List<Element> elements = rootElt.elements();
        for (Element element : elements) {
            element.getName();
            tMap.put(element.getName(), element.getData().toString());
            LogUtils.i(TAG, element.getName() + ":" + element.getData().toString());
        }

        SqlSession session = getSqlSession();
        ShopDAL shopDAL = session.getMapper(ShopDAL.class);
        Shop shop = shopDAL.get(header.shopId);
        if (TextUtils.isEmpty(shop.getWcpayApiKey())) {
            createResult(this, -6001, "小程序信息未填写");
            return;
        }

        String sign = ServiceUtils.getWXPaySignValue(tMap, shop.getWcpayApiKey());

        if (!sign.equals(tMap.get("sign"))) {
            throw new ServiceException("签名失败 order id (out_trade_no) = " + tMap.get("out_trade_no"));
        }

        ConfirmInfo info = XmlUtils.xmlStrToBean(body, ConfirmInfo.class);
        if (!"SUCCESS".equals(info.returnCode)) {
            throw new ServiceException(info.returnMsg);
        }

        if (TextUtils.isEmpty(info.outTradeNo) || info.outTradeNo.length() < 32) {
            throw new ServiceException("order id (out_trade_no) : " + info.outTradeNo + " is illegal");
        }

        if ("C1010".equals(info.attach)) {
            String orderId = info.outTradeNo;
            OrderDAL dal = session.getMapper(OrderDAL.class);
            Order order = dal.getOrder(orderId, header.shopId);

            BigDecimal wcMoney = new BigDecimal(info.cashFee);
            BigDecimal ownMoney = new BigDecimal(order.getAmount()).multiply(new BigDecimal(100));

            if (wcMoney.compareTo(ownMoney) != 0) {
                throw new ServiceException("交易金额不对等,order id = " + orderId);
            }

            order.setPayDT(new Date());
            order.setState(OrderPayState.doing);
            order.setShopId(Integer.valueOf(header.shopId));
            order.setPayMethod(OrderPayMethod.WECHAT);
            int result = dal.update(order);
            session.commit();
            if (result < 1) {
                throw new ServiceException("订单状态修改失败,order id = " + orderId);
            }

            OrderReminder.broadcast();
            createSuccess(this);
        }
    }
}
