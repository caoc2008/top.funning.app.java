package top.funning.app.service.address.get;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.dal.AddressDAL;
import top.funning.app.database.table.Address;
import top.funning.app.service.FnService;
import top.funning.app.service.address.Translator;
import top.knxy.library.oval.Number;

public class C1026 extends FnService {

    @Number
    public String id;

    public String userId;

    @Override
    protected void run() throws Exception {
        SqlSession sqlSession = getSqlSession();
        AddressDAL dal = sqlSession.getMapper(AddressDAL.class);
        Address address = dal.get(id, userId, header.shopId);

        if (address == null) {
            createError(this, "地址不存在");
            return;
        }

        this.data = Translator.INSTANCES.toAddress(address);

        createSuccess(this);
    }

}
