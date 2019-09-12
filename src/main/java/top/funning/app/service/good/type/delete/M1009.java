package top.funning.app.service.good.type.delete;

import net.sf.oval.constraint.Digits;
import net.sf.oval.constraint.NotNull;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.dal.GoodTypeDAL;
import top.funning.app.service.FnService;
import top.knxy.library.BaseService;
import top.knxy.library.utils.TextUtils;

public class M1009 extends FnService {
    @Digits
    public String id;

    @Override
    protected void run() throws Exception {

        SqlSession session = getSqlSession();
        GoodTypeDAL dal = session.getMapper(GoodTypeDAL.class);
        int result = dal.delete(id,shopId);
        session.commit();

        if (result < 1) {
            throw new Exception();
        }

        createSuccess(this);
    }
}
