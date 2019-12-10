package top.funning.app.service.address.getTop;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.dal.AddressDAL;
import top.funning.app.database.table.Address;
import top.funning.app.service.FnService;
import top.funning.app.service.address.Translator;

public class C1030 extends FnService {


    public String userId;

    @Override
    protected void run() throws Exception {
        SqlSession sqlSession = getSqlSession();
        AddressDAL dal = sqlSession.getMapper(AddressDAL.class);
        Address address = dal.getTop(userId, header.shopId);

        if (address == null) {
            createResult(this, 1001, "地址不存在");
            return;
        }

        this.data = Translator.INSTANCES.toAddress(address);

        createSuccess(this);
    }

}
