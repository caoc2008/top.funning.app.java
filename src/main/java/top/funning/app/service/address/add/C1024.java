package top.funning.app.service.address.add;

import net.sf.oval.constraint.Length;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.dal.AddressDAL;
import top.funning.app.database.table.Address;
import top.funning.app.service.FnService;
import top.knxy.library.BaseService;

public class C1024 extends FnService {


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
    @Length(min = 32, max = 32)
    public String userId;

    @Override
    protected void run() throws Exception {
        SqlSession session = getSqlSession();
        AddressDAL dal = session.getMapper(AddressDAL.class);

        Address address = new Address();
        address.setName(name);
        address.setPhone(phone);
        address.setProvince(province);
        address.setCity(city);
        address.setArea(area);
        address.setDetail(detail);
        address.setPoster(poster);
        address.setUserId(userId);
        address.setShopId(header.shopId);
        dal.add(address);
        session.commit();

        createSuccess(this);
    }
}
