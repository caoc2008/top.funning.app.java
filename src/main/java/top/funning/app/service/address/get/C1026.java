package top.funning.app.service.address.get;

import net.sf.oval.constraint.Digits;
import net.sf.oval.constraint.Length;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.dal.AddressDAL;
import top.funning.app.database.table.Address;
import top.funning.app.service.FnService;
import top.funning.app.service.address.Translator;

public class C1026 extends FnService {

    @Digits
    public String id;


    @Length(min = 32, max = 32)
    public String userId;

    @Override
    protected void run() throws Exception {
        SqlSession sqlSession = getSqlSession();
        AddressDAL dal = sqlSession.getMapper(AddressDAL.class);
        Address address = dal.get(id, userId, shopId);

        this.data = Translator.INSTANCES.toAddress(address);

        createSuccess(this);
    }

}
