package top.funning.app.service.shop.get;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.config.C;
import top.funning.app.database.dal.ShopDAL;
import top.funning.app.database.table.Shop;
import top.funning.app.service.FnService;

public class M1030 extends FnService {

    @Override
    protected void run() throws Exception {
        SqlSession sqlSession = getSqlSession();
        ShopDAL shopDAL =  sqlSession.getMapper(ShopDAL.class);
        Shop shop = shopDAL.get(shopId);
        shop.setLogoUrl(C.App.imageHost + shop.getLogoUrl());
        this.data = shop;
        createSuccess(this);
    }
}
