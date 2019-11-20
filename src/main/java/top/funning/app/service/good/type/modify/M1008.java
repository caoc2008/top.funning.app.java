package top.funning.app.service.good.type.modify;

import net.sf.oval.constraint.Digits;
import net.sf.oval.constraint.NotBlank;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.cache.GoodCache;
import top.funning.app.database.dal.GoodTypeDAL;
import top.funning.app.database.table.GoodType;
import top.funning.app.service.FnService;
import top.knxy.library.BaseService;
import top.knxy.library.utils.TextUtils;

public class M1008 extends FnService {

    @Digits
    public String id;
    @NotBlank
    public String name;
    @Digits
    public String state;
    @Digits
    public String sort;

    @Override
    protected void run() throws Exception {
        SqlSession session = getSqlSession();
        GoodTypeDAL opreation = session.getMapper(GoodTypeDAL.class);

        GoodType type = new GoodType();
        type.setId(Integer.valueOf(id));
        type.setName(name);
        type.setState(Integer.valueOf(state));
        type.setShopId(Integer.valueOf(header.shopId));
        type.setSort(Integer.valueOf(sort));
        opreation.update(type);
        session.commit();

        GoodCache.refresh(header.shopId);

        createSuccess(this);
    }
}
