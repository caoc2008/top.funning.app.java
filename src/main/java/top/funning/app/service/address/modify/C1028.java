package top.funning.app.service.address.modify;

import net.sf.oval.constraint.Digits;
import net.sf.oval.constraint.Length;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.dal.AddressDAL;
import top.funning.app.database.table.Address;
import top.funning.app.service.FnService;
import top.knxy.library.oval.Number;

public class C1028 extends FnService {

    @Number
    public String id;
    @Length(min = 0, max = 16)
    public String name;
    @Length(min = 0, max = 32)
    public String phone;
    @Length(min = 0, max = 16)
    public String province;
    @Length(min = 0, max = 16)
    public String city;
    @Length(min = 0, max = 16)
    public String area;
    @Length(min = 0, max = 64)
    public String detail;
    @Length(min = 0, max = 8)
    public String poster;

    public String userId;

    @Override
    protected void run() throws Exception {
        SqlSession sqlSession = getSqlSession();
        AddressDAL dal = sqlSession.getMapper(AddressDAL.class);
        Address address = new Address();
        address.setId(id);
        address.setName(name);
        address.setPhone(phone);
        address.setProvince(province);
        address.setCity(city);
        address.setArea(area);
        address.setDetail(detail);
        address.setPoster(poster);
        address.setUserId(userId);
        address.setShopId(header.shopId);
        dal.update(address);
        sqlSession.commit();

        createSuccess(this);
    }
}
