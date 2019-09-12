package top.funning.app.service.index.count;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.dal.OrderDAL;
import top.funning.app.service.FnService;
import top.knxy.library.BaseService;

public class M1017 extends FnService {
    @Override
    protected void run() throws Exception {
        SqlSession session = getSqlSession();
        Data data = new Data();
        this.data = data;

        OrderDAL oDal = session.getMapper(OrderDAL.class);
        data.normalUnDoCount = String.valueOf(oDal.getUnDoNumber(shopId));

        createSuccess(this);
    }

    public static class Data {
        public String normalUnDoCount;
        public String groupUnDoCount;
    }
}
