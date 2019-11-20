package top.funning.app.service.good.type.list;

import top.funning.app.database.dal.GoodTypeDAL;
import top.funning.app.database.table.GoodType;
import top.funning.app.service.FnService;
import top.knxy.library.BaseService;

import java.util.List;

public class M1007 extends FnService {


    @Override
    protected void run() throws Exception {
        Data data = new Data();
        data.typeList = getSqlSession().getMapper(GoodTypeDAL.class).getList(header.shopId);
        this.data = data;
        createSuccess(this);
    }

    public static class Data{

        private List<GoodType> typeList;

        public List<GoodType> getTypeList() {
            return typeList;
        }

        public void setTypeList(List<GoodType> typeList) {
            this.typeList = typeList;
        }
    }
}
