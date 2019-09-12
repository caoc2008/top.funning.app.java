package top.funning.app.service.login.pwd.reset;

import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotNull;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.Redis;
import top.funning.app.database.dal.AdminDAL;
import top.funning.app.database.table.Admin;
import top.funning.app.service.FnService;
import top.knxy.library.ServiceException;
import top.knxy.library.utils.PwdUtils;
import top.knxy.library.utils.TextUtils;

public class M1027 extends FnService {

    @NotNull
    public String redisCode;

    @Length(min = 6,max = 18)
    public String password;

    @Override
    protected void run() throws Exception {

        String email = Redis.get(redisCode);
        if (TextUtils.isEmpty(email)) {
            createError(this, "链接错误或者过期");
        }

        SqlSession sqlSession = getSqlSession();
        AdminDAL dal = sqlSession.getMapper(AdminDAL.class);
        Admin admin = dal.getAdminByUserName(email);

        admin.setFail(0);
        admin.setSalt(PwdUtils.createSalt());
        admin.setPassword(PwdUtils.sha1(password + admin.getSalt()));

        int row = dal.updatePassword(admin);
        sqlSession.commit();
        if (row < 1) {
            throw new ServiceException("row < 1");
        }
        Redis.del(redisCode);
        createSuccess(this);
    }
}
