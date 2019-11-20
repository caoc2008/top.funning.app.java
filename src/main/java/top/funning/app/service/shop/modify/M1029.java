package top.funning.app.service.shop.modify;

import net.sf.oval.constraint.Digits;
import net.sf.oval.constraint.Length;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.dal.ShopDAL;
import top.funning.app.database.table.Shop;
import top.funning.app.service.FnService;
import top.knxy.library.ServiceException;

public class M1029 extends FnService {


    @Length(max = 18, min = 18)
    public String wechatAppid;
    @Length(max = 32, min = 32)
    public String wechatSecret;
    @Length(max = 32, min = 1)
    public String wcpayMchId;
    @Length(max = 32, min = 32)
    public String wcpayApiKey;
    @Length(max = 32, min = 1)
    public String appName;
    @Digits
    public String phoneNum;

    @Override
    protected void run() throws Exception {
        SqlSession sqlSession = getSqlSession();
        ShopDAL shopDAL = sqlSession.getMapper(ShopDAL.class);
        Shop shop = shopDAL.get(header.shopId);
        shop.setWechatAppid(wechatAppid);
        shop.setWechatSecret(wechatSecret);
        shop.setWcpayMchId(wcpayMchId);
        shop.setWcpayApiKey(wcpayApiKey);
        shop.setAppName(appName);
        shop.setPhoneNum(phoneNum);

        int row = shopDAL.update(shop);
        sqlSession.commit();
        if (row < 1) {
            throw new ServiceException("row < 1");
        }

        createSuccess(this);
    }
}
