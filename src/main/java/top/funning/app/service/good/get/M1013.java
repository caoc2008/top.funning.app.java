package top.funning.app.service.good.get;

import net.sf.oval.constraint.NotNull;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.dal.GoodDAL;
import top.funning.app.database.dal.GoodTypeDAL;
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
        GoodTypeDAL gtDal = session.getMapper(GoodTypeDAL.class);
        Good good = ServiceUtils.mapToBean(gDal.getGoodForAdmin(id,shopId), Good.class);
        if (good == null) {
            createError(this, "没有这个商品");
            return;
        }

        Data data = new Data(good, gtDal.getUsefulList(shopId));
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
        private List<GoodType> typeList;
        private String detail;

        public Data(Good good, List<Map> maps) throws Exception {
            this.id = good.id;
            this.name = good.name;
            this.description = good.description;
            this.imageUrl = good.imageUrl;
            this.price = good.price;
            this.detail = good.content;
            this.state = good.state;
            if ("1".equals(good.state)) {
                this.stateStr = "出售中";
            } else if ("2".equals(good.state)) {
                this.stateStr = "已下架";
            }
            this.type = good.type;
            this.typeStr = (TextUtils.isEmpty(good.typeStr) ? " - - " : good.typeStr);

            this.typeList = new ArrayList<>(maps.size());

            for (Map map : maps) {
                this.typeList.add(ServiceUtils.mapToBean(map, GoodType.class));
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

        public List<GoodType> getTypeList() {
            return typeList;
        }

        public void setTypeList(List<GoodType> typeList) {
            this.typeList = typeList;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }
    }

    public static class GoodType {
        private String id;
        private String name;

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

    public static class Good {
        private String id;
        private String name;
        private String description;
        private String imageUrl;
        private String price;
        private String content;
        private String state;
        private String type;
        private String typeStr;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
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

    }


}
