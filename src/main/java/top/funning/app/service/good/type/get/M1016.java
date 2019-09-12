package top.funning.app.service.good.type.get;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.dal.GoodTypeDAL;
import top.funning.app.service.FnService;
import top.funning.app.service.good.get.M1013;
import top.knxy.library.utils.ServiceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class M1016 extends FnService {
    @Override
    protected void run() throws Exception {
        SqlSession session = getSqlSession();
        GoodTypeDAL gtDal = session.getMapper(GoodTypeDAL.class);

        List<Map> maps = gtDal.getUsefulList(shopId);

        List<M1013.GoodType> list = new ArrayList<>(maps.size());
        for (Map map : maps) {
            list.add(ServiceUtils.mapToBean(map, M1013.GoodType.class));
        }

        if (list == null || list.isEmpty()) {
            createError(this, "商品分组为空，请先添加商品分组");
            return;
        }

        M1013.GoodType item = list.get(0);
        Data data = new Data();
        data.type = item.getId();
        data.typeStr = item.getName();
        data.typeList = list;
        this.data = data;

        session.commit();
        createSuccess(this);
    }

    public static class Data {
        private String type;
        private String typeStr;
        private List<M1013.GoodType> typeList;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTypeStr() {
            return typeStr;
        }

        public void setTypeStr(String typeStr) {
            this.typeStr = typeStr;
        }

        public List<M1013.GoodType> getTypeList() {
            return typeList;
        }

        public void setTypeList(List<M1013.GoodType> typeList) {
            this.typeList = typeList;
        }
    }
}
