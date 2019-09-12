package top.funning.app.service.good.get;

import com.google.gson.Gson;
import net.sf.oval.constraint.NotNull;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.dal.GoodDAL;
import top.funning.app.service.FnService;
import top.knxy.library.utils.ServiceUtils;
import top.knxy.library.utils.TextUtils;

import java.util.List;

public class C1009 extends FnService {
    @NotNull
    public String id;

    @Override
    protected void run() throws Exception {
        SqlSession session = getSqlSession();
        Good good = ServiceUtils.mapToBean(session.getMapper(GoodDAL.class).getGoodForUser(id,shopId), Good.class);

        if (good == null) {
            createError(this);
            return;
        }

        String content = good.getContent();
        if (TextUtils.isEmpty(content)) {
            content = "{}";
        }

        Gson gson = new Gson();
        Data data = gson.fromJson(content, Data.class);
        data.id = String.valueOf(good.getId());
        data.name = good.getName();
        data.description = good.getDescription();
        data.imageUrl = good.getImageUrl();
        data.price = good.getPrice();

        this.data = data;
        createSuccess(this);

    }

    public static class Data {
        public String id;
        public String name;
        public String description;
        public String imageUrl;
        public String price;
        public Header header;
        public Detail detail;

        public static class Header {
            public List<String> imageList;
        }

        public static class Detail {
            public List<String> imageList;
        }
    }

    public static class Good {
        public String id;
        public String name;
        public String description;
        public String imageUrl;
        public String price;
        public String content;

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
    }

}
