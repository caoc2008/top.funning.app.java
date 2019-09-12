package top.funning.app.service.login.register;

import net.sf.oval.constraint.Email;
import net.sf.oval.constraint.NotNull;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.dal.AdminDAL;
import top.funning.app.database.dal.ShopDAL;
import top.funning.app.database.table.Admin;
import top.funning.app.database.table.Shop;
import top.funning.app.service.FnService;
import top.knxy.library.ServiceException;
import top.knxy.library.utils.PwdUtils;

public class M1025 extends FnService {
    @Email
    public String username;
    @NotNull
    public String password;

    @Override
    protected void run() throws Exception {
        SqlSession sqlSession = getSqlSession();
        AdminDAL adminDAL = sqlSession.getMapper(AdminDAL.class);
        ShopDAL shopDAL = sqlSession.getMapper(ShopDAL.class);

        Shop shop = new Shop();
        int row1 = shopDAL.add(shop);
        sqlSession.commit();
        if (row1 != 1) {
            throw new ServiceException("row < 1");
        }

        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setSalt(PwdUtils.createSalt());
        admin.setPassword(PwdUtils.sha1(password + admin.getSalt()));
        admin.setShopId(shop.getId());
        int row2 = adminDAL.add(admin);
        sqlSession.commit();
        if (row2 != 1) {
            throw new ServiceException("row < 1");
        }
        this.data = admin;

        createSuccess(this);
    }
}
