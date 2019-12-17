package top.funning.app.service.order.pay.wechat;

import net.sf.oval.constraint.NotNull;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.config.C;
import top.funning.app.database.dal.OrderDAL;
import top.funning.app.database.dal.ShopDAL;
import top.funning.app.database.dal.UserDAL;
import top.funning.app.database.table.Order;
import top.funning.app.database.table.Shop;
import top.funning.app.database.table.User;
import top.funning.app.service.FnService;
import top.funning.app.service.address.poster.computer.C1013;
import top.knxy.library.config.Code;
import top.knxy.library.ServiceException;
import top.knxy.library.utils.*;
import top.knxy.library.service.wechat.pay.CL1001;

import java.math.BigDecimal;

public class C1010 extends FnService {
    public static final String TAG = "Order.Pay.C1010";

    private final static double locationLat = 23.091333102548455;
    private final static double locationLng = 113.33717442876233;

    @NotNull
    public String openId;
    @NotNull
    public String userId;
    @NotNull
    public String id;

    public String note;
    @NotNull
    public Address address;

    public static class Address {
        public String provinceName;
        public String cityName;
        public String countyName;
        public String detailInfo;
        public String telNumber;
        public String userName;
        public String nationalCode;
        public String postalCode;
    }


    @Override
    public void run() throws Exception {
        if (address == null) {
            createError(this, "没有地址");
            return;
        }
        if (TextUtils.isEmpty(address.userName)) {
            createError(this, "没有姓名");
            return;
        }
        if (TextUtils.isEmpty(address.telNumber)) {
            createError(this, "没有电话号码");
            return;
        }
        if (TextUtils.isEmpty(address.detailInfo)) {
            createError(this, "没有详细地址");
            return;
        }
        if (TextUtils.isEmpty(address.provinceName)) {
            createError(this, "没有区域");
            return;
        }
        if (TextUtils.isEmpty(address.cityName)) {
            createError(this, "没有城市");
            return;
        }
        if (TextUtils.isEmpty(address.provinceName)) {
            createError(this, "没有省份");
            return;
        }


        //查询订单
        SqlSession session = getSqlSession();
        OrderDAL mapper = session.getMapper(OrderDAL.class);

        Order order = new Order();
        order.setId(id);
        order.setUserId(userId);
        order.setShopId(Integer.valueOf(header.shopId));
        order = mapper.getOrderByUser(order);
        if (order == null) {
            throw new ServiceException("没有订单 id = " + id);
        }

        //计算运费
        C1013 c1034 = new C1013();
        c1034.countryName = address.countyName;
        c1034.provinceName = address.provinceName;
        c1034.cityName = address.cityName;
        c1034.detailInfo = address.detailInfo;
        c1034.start();
        if (c1034.code != Code.Service.SUCCESS) {
            throw new ServiceException(c1034.msg);
        }

        C1013.Data data = (C1013.Data) c1034.data;
        String poster = data.poster;

        order.setNote(note);
        order.setPoster(poster);
        order.setAmount(new BigDecimal(poster).add(new BigDecimal(order.getPrice())).toString());

        order.setProvinceName(address.provinceName);
        order.setCityName(address.cityName);
        order.setCountyName(address.countyName);
        order.setDetailInfo(address.detailInfo);
        order.setTelNumber(address.telNumber);
        order.setUserName(address.userName);
        order.setNationalCode(address.nationalCode);
        order.setPostalCode(address.postalCode);
        order.setShopId(Integer.valueOf(header.shopId));

        int result = mapper.update(order);
        session.commit();
        if (result < 1) {
            throw new ServiceException("订单修改失败 order id = " + id);
        }

        //支付
        UserDAL dal = session.getMapper(UserDAL.class);
        User user = dal.getUser(userId, header.shopId);
        if (user == null) {
            throw new ServiceException("没有用户 user id = " + userId);
        }

        String money = new BigDecimal(order.getAmount()).multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_UP).toString();

        ShopDAL shopDAL = session.getMapper(ShopDAL.class);
        Shop shop = shopDAL.get(header.shopId);

        if (TextUtils.isEmpty(shop.getWechatAppid()) || TextUtils.isEmpty(shop.getWcpayMchId()) || TextUtils.isEmpty(shop.getWcpayApiKey())) {
            createResult(this, -6001, "小程序信息未填写");
            return;
        }

        CL1001 cl1001 = new CL1001();
        cl1001.openId = openId;
        cl1001.money = money;
        cl1001.orderId = order.getId();
        cl1001.description = "购买商品";
        cl1001.attach = "C1010";
        cl1001.appid = shop.getWechatAppid();
        cl1001.mchId = shop.getWcpayMchId();
        cl1001.apiKey = shop.getWcpayApiKey();
        cl1001.ipAddress = C.App.ip;
        cl1001.domain = C.App.domain;
        cl1001.notifyUrl = C.App.domain + C.WCPay.notifyUrl;
        cl1001.start();
        if (cl1001.code == Code.Service.SUCCESS) {
            this.data = cl1001.data;
            createSuccess(this);
        } else {
            createResult(this, cl1001.code, cl1001.msg);
        }
    }

    public static class LocationInfo {


        public int status;
        public Result result;

        public static class Result {

            public Location location;
            public int precise;
            public int confidence;
            public int comprehension;
            public String level;

            public static class Location {
                public double lng;
                public double lat;
            }
        }
    }

}
