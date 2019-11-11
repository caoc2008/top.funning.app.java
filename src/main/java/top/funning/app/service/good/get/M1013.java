package top.funning.app.service.good.get;

import net.sf.oval.constraint.NotNull;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.dal.GoodDAL;
import top.funning.app.database.dal.GoodTypeDAL;
import top.funning.app.database.table.Good;
import top.funning.app.database.table.GoodType;
import top.funning.app.service.FnService;
import top.knxy.library.utils.ServiceUtils;
import top.knxy.library.utils.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class M1013 extends FnService {
    @NotNull
    public String id;

    @Override
    protected void run() throws Exception {
        SqlSession session = getSqlSession();
        GoodDAL gDal = session.getMapper(GoodDAL.class);
        Good good = gDal.getGoodForAdmin(id, shopId);
        if (good == null) {
            createError(this, "没有这个商品");
            return;
        }

        GoodTypeDAL gtDal = session.getMapper(GoodTypeDAL.class);
        List<GoodType> gtList = gtDal.getUsefulList(shopId);

        Data data = new Data(good, gtList);
        this.data = data;

        createSuccess(this);
    }

    public static class Data {
        private String id;
        private String name;
        private String description;
        private String imageUrl;
        private String price;
        private String state;
        private String stateStr;
        private String type;
        private String typeStr;
        private List<Type> typeList;
        private String detail;

        public Data(Good good, List<GoodType> types) throws Exception {
            this.id = String.valueOf(good.getId());
            this.name = good.getName();
            this.description = good.getDescription();
            this.imageUrl = good.getImageUrl();
            this.price = good.getPrice();
            this.detail = good.getContent();
            this.state = String.valueOf(good.getState());
            if (1 == good.getState()) {
                this.stateStr = "出售中";
            } else if (2 == good.getState()) {
                this.stateStr = "已下架";
            }
            this.type = String.valueOf(good.getType());

            if (good.getType() < 1) {
                this.typeStr = " - - ";
            } else {
                for (GoodType type : types) {
                    if (type.getId() == good.getType()) {
                        this.typeStr = type.getName();
                        break;
                    }
                }
            }

            this.typeList = new ArrayList<>(types.size());

            for (GoodType type : types) {
                this.typeList.add(new Type(type));
            }

        }

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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getStateStr() {
            return stateStr;
        }

        public void setStateStr(String stateStr) {
            this.stateStr = stateStr;
        }

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

        public List<Type> getTypeList() {
            return typeList;
        }

        public void setTypeList(List<Type> typeList) {
            this.typeList = typeList;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public static class Type {
            private String id;
            private String name;

            public Type(GoodType type) {
                this.id = String.valueOf(type.getId());
                this.name = type.getName();
            }

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
        }
    }


}
