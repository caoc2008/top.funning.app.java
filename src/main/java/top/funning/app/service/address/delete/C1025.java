package top.funning.app.service.address.delete;

import net.sf.oval.constraint.Digits;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.Size;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.dal.AddressDAL;
import top.funning.app.service.FnService;

public class C1025 extends FnService {

    @Digits
    public String id;
    @Length(min = 32, max = 32)
    public String userId;

    @Override
    protected void run() throws Exception {
        SqlSession session = getSqlSession();
        AddressDAL dal = getSqlSession().getMapper(AddressDAL.class);
        dal.delete(id, userId, header.shopId);
        session.commit();
        createSuccess(this);
    }
}
