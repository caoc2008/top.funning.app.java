package top.funning.app.service.good.type.add;

import net.sf.oval.constraint.NotNull;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.dal.GoodTypeDAL;
import top.funning.app.service.FnService;
import top.knxy.library.BaseService;
import top.knxy.library.utils.TextUtils;

public class M1006 extends FnService {
    @NotNull
    public String name;

    @Override
    protected void run() throws Exception {
        SqlSession session = getSqlSession();
        GoodTypeDAL operation = session.getMapper(GoodTypeDAL.class);
        int row = operation.insert(name,shopId);
        session.commit();
        if (row < 1) {
            createError(this, "添加失败");
            return;
        }
        createSuccess(this);

    }
}
