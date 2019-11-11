package top.funning.app.service.good.delete;

import net.sf.oval.constraint.NotNull;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.cache.GoodCache;
import top.funning.app.database.dal.GoodDAL;
import top.funning.app.service.FnService;
import top.knxy.library.ServiceException;

public class M1012 extends FnService {
    @NotNull
    public String id;

    @Override
    protected void run() throws Exception {

        int result;
        SqlSession session = getSqlSession();
        GoodDAL gDal = session.getMapper(GoodDAL.class);
        result = gDal.delete(id,shopId);
        session.commit();
        if (result < 1) {

            throw new ServiceException("删除失败 good id = " + id);
        }

        //删除缓存
        GoodCache.clear(shopId);

        createSuccess(this);
    }


}
