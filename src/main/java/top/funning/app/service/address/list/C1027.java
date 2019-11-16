package top.funning.app.service.address.list;

import net.sf.oval.constraint.Length;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.dal.AddressDAL;
import top.funning.app.database.table.Address;
import top.funning.app.service.FnService;
import top.funning.app.service.address.ReturnEntity;
import top.funning.app.service.address.Translator;

import java.util.List;

public class C1027 extends FnService {

    @Length(max = 32, min = 32)
    public String userId;

    @Override
    protected void run() throws Exception {
        SqlSession sqlSession = getSqlSession();
        AddressDAL dal = sqlSession.getMapper(AddressDAL.class);
        List<Address> list = dal.getList(userId, shopId);

        Data data = new Data();
        data.dataList = Translator.INSTANCES.toAddressList(list);
        this.data = data;

        createSuccess(this);
    }

    public static class Data {
        public List<ReturnEntity> dataList;
    }
}
