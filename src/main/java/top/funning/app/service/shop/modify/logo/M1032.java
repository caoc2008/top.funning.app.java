package top.funning.app.service.shop.modify.logo;

import net.sf.oval.constraint.NotNull;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.dal.ShopDAL;
import top.funning.app.database.table.Shop;
import top.funning.app.service.FnService;
import top.knxy.library.ServiceException;

public class M1032 extends FnService {
    @NotNull
    public String fileName;

    @Override
    protected void run() throws Exception {
        SqlSession sqlSession = getSqlSession();
        ShopDAL dal = sqlSession.getMapper(ShopDAL.class);
        Shop shop = dal.get(shopId);
        shop.setLogoUrl(fileName);
        int row = dal.update(shop);
        sqlSession.commit();
        if (row < 1) {
            throw new ServiceException("row < 1");
        }
        createSuccess(this);
    }
}
