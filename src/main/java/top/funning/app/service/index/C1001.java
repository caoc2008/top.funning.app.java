package top.funning.app.service.index;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.config.C;
import top.funning.app.database.dal.GoodDAL;
import top.funning.app.database.dal.GoodTypeDAL;
import top.funning.app.database.Redis;
import top.funning.app.database.table.Good;
import top.funning.app.database.table.GoodType;
import top.funning.app.service.FnService;
import top.funning.app.service.good.get.M1013;
import top.knxy.library.config.V;
import top.knxy.library.utils.ServiceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class C1001 extends FnService {

    @Override
    public void run() throws Exception {
        SqlSession session = getSqlSession();
        GoodTypeDAL gtDal = session.getMapper(GoodTypeDAL.class);
        List<GoodType> goodTypeList = gtDal.getUsefulList(shopId);
        GoodDAL gDal = session.getMapper(GoodDAL.class);
        List<Good> goodList = gDal.getUsefulList(shopId);


        Data data = new Data();
        data.typeList = new ArrayList<>();
        Map<String, Data.Type> typeMap = new HashMap<>();

        for (GoodType goodType : goodTypeList) {
            Data.Type type = new Data.Type(goodType);
            typeMap.put(String.valueOf(goodType.getId()), type);
            data.typeList.add(type);
        }

        for (Good good : goodList) {
            Data.Type.Good dtg = new Data.Type.Good(good);
            Data.Type type = typeMap.get(String.valueOf(good.getType()));
            type.goodList.add(dtg);
        }

        data.postImageUrl = Redis.get(shopId + "_" + V.postImageUrl);
        this.data = data;
        createSuccess(this);
    }

    public static class Data {

        public String postImageUrl;

        public List<Type> typeList;

        public static class Type {
            public String id;
            public String name;
            public List<Good> goodList;

            public Type(GoodType goodType) {
                this.id = String.valueOf(goodType.getId());
                this.name = goodType.getName();
                this.goodList = new ArrayList<>();
            }

            public static class Good {
                public String id;
                public String name;
                public String description;
                public String imageUrl;
                public String price;

                public Good(top.funning.app.database.table.Good good) {
                    this.id = String.valueOf(good.getId());
                    this.name = good.getName();
                    this.description = good.getDescription();
                    this.imageUrl = C.App.imageHost + good.getImageUrl();
                    this.price = good.getPrice();
                }
            }
        }
    }
}
