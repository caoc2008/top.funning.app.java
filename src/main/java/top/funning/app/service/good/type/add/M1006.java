package top.funning.app.service.good.type.add;

import net.sf.oval.constraint.Digits;
import net.sf.oval.constraint.NotNull;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.dal.GoodTypeDAL;
import top.funning.app.database.table.GoodType;
import top.funning.app.database.table.Shop;
import top.funning.app.service.FnService;
import top.knxy.library.BaseService;
import top.knxy.library.utils.TextUtils;

public class M1006 extends FnService {
    @NotNull
    public String name;
    @Digits
    public String sort;

    @Override
    protected void run() throws Exception {
        SqlSession session = getSqlSession();
        GoodTypeDAL operation = session.getMapper(GoodTypeDAL.class);
        GoodType goodType = new GoodType();
        goodType.setSort(Integer.valueOf(sort));
        goodType.setShopId(Integer.valueOf(shopId));
        goodType.setName(name);
        int row = operation.insert(goodType);
        session.commit();
        if (row < 1) {
            createError(this, "添加失败");
            return;
        }
        createSuccess(this);

    }
}
