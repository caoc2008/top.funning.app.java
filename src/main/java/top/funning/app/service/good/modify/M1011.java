package top.funning.app.service.good.modify;

import com.google.gson.Gson;
import net.sf.oval.constraint.NotNull;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.cache.GoodCache;
import top.funning.app.database.dal.GoodDAL;
import top.funning.app.database.dal.GoodTypeDAL;
import top.funning.app.database.table.Good;
import top.funning.app.database.table.GoodType;
import top.funning.app.service.FnService;
import top.knxy.library.utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class M1011 extends FnService {

    @NotNull
    public String id;
    @NotNull
    public String name;
    public String description;
    @NotNull
    public String price;
    public String state;
    @NotNull
    public String imageUrl;
    public String type;
    @NotNull
    public Detail detail;


    public static class Detail {

        public Content content;
        public Header header;

        public static class Content {
            public List<String> imageList;
        }

        public static class Header {
            public List<String> imageList;
        }
    }

    @Override
    protected void run() throws Exception {
        if (!TextUtils.isNumeric(state) ||
                detail == null ||
                detail.header == null ||
                detail.header.imageList.isEmpty()
        ) {
            createError(this);
            return;
        }

        if (detail.content.imageList == null) {
            detail.content.imageList = new ArrayList<>();
        }


        Good good = new Good();
        good.setId(Integer.valueOf(id));
        good.setShopId(Integer.valueOf(header.shopId));
        good.setName(name);
        good.setDescription(description);
        good.setPrice(price);
        good.setState(Integer.valueOf(state));
        if (TextUtils.isNumeric(type)) good.setType(Integer.valueOf(type));
        good.setImageUrl(imageUrl);

        Content content = new Content();

        content.header = new Content.Header();
        content.header.imageList = new ArrayList<>();
        for (String s : detail.header.imageList) {
            content.header.imageList.add(s);
        }

        content.detail = new Content.Detail();
        content.detail.imageList = new ArrayList<>();
        for (String s : detail.content.imageList) {
            content.detail.imageList.add(s);
        }

        good.setContent(new Gson().toJson(content));

        SqlSession session = getSqlSession();

        if (good.getType() > 1) {
            GoodTypeDAL gtDal = session.getMapper(GoodTypeDAL.class);
            GoodType type = gtDal.get(good.getType(),header.shopId);
            if(type == null){
                createError(this);
                return;
            }
        }


        GoodDAL gDal = session.getMapper(GoodDAL.class);
        int row = gDal.update(good);
        session.commit();

        if (row < 1) {
            createError(this);
            return;
        }

        //删除缓存
        GoodCache.clear(header.shopId);
        createSuccess(this);
    }

    public static class Content {
        public Header header;
        public Detail detail;

        public static class Header {
            public List<String> imageList;
        }

        public static class Detail {
            public List<String> imageList;
        }
    }
}
