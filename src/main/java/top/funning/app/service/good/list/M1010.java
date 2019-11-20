package top.funning.app.service.good.list;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.dal.GoodDAL;
import top.funning.app.database.model.Page;
import top.funning.app.service.FnService;
import top.knxy.library.utils.ServiceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class M1010 extends FnService {

    public String page;

    @Override
    protected void run() throws Exception {
        SqlSession session = getSqlSession();
        GoodDAL goodDAL = session.getMapper(GoodDAL.class);
        Page pModel = new Page(page);

        this.data = new Data(goodDAL.getList(pModel.getIndex(), pModel.getSize(),header.shopId));

        createSuccess(this);
    }

    public static class Data {
        private List<Good> dataList;

        public Data(List<Map> list) throws Exception {
            dataList = new ArrayList<>(list.size());
            for (Map map : list) {
                dataList.add(ServiceUtils.mapToBean(map, Good.class));
            }
        }

        public List<Good> getDataList() {
            return dataList;
        }

        public void setDataList(List<Good> dataList) {
            this.dataList = dataList;
        }

        public static class Good {
            private String id;
            private String name;
            private String price;
            private String type;
            private String typeName;
            private String state;
            private String stateStr;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
                if ("1".equals(state)) {
                    setStateStr("上架中");
                } else if ("2".equals(state)) {
                    setStateStr("已下架");
                }
            }

            public String getStateStr() {
                return stateStr;
            }

            public void setStateStr(String stateStr) {
                this.stateStr = stateStr;
            }
        }
    }
}
