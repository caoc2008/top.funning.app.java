package top.funning.app.service.good.type.delete;

import net.sf.oval.constraint.Digits;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.cache.GoodCache;
import top.funning.app.database.dal.GoodDAL;
import top.funning.app.database.dal.GoodTypeDAL;
import top.funning.app.service.FnService;

public class M1009 extends FnService {
    @Digits
    public String id;

    @Override
    protected void run() throws Exception {

        SqlSession session = getSqlSession();
        GoodTypeDAL dal = session.getMapper(GoodTypeDAL.class);
        int result = dal.delete(id, header.shopId);

        GoodDAL goodDAL = session.getMapper(GoodDAL.class);
        goodDAL.updateState(id, header.shopId);

        GoodCache.refresh(header.shopId);
        session.commit();

        if (result < 1) {
            throw new Exception();
        }

        createSuccess(this);
    }
}
