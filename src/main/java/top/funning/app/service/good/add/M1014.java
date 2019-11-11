package top.funning.app.service.good.add;

import com.google.gson.Gson;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotNull;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.cache.GoodCache;
import top.funning.app.database.dal.GoodDAL;
import top.funning.app.database.table.Good;
import top.funning.app.service.FnService;
import top.funning.app.service.good.modify.M1011;
import top.knxy.library.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class M1014 extends FnService {
    @NotNull
    @Length(min = 1, max = 32)
    public String name;

    @Length(min = 0, max = 128)
    public String description;

    @NotNull
    public String price;

    @NotNull
    public String state;

    @NotNull
    @Length(min = 1, max = 128)
    public String imageUrl;

    @NotNull
    public String type;

    @NotNull
    public M1011.Detail detail;

    public static class Detail {

        public M1011.Detail.Content content;
        public M1011.Detail.Header header;

        public static class Content {
            public List<String> imageList;
        }

        public static class Header {
            public List<String> imageList;
        }
    }


    @Override
    protected void run() throws Exception {

        if (detail == null || detail.header == null || detail.header.imageList.isEmpty()) {
            throw new ServiceException();
        }

        if (detail.content.imageList == null) {
            detail.content.imageList = new ArrayList<>();
        }

        Good good = new Good();
        good.setName(name);
        good.setDescription(description);
        good.setPrice(price);
        good.setState(Integer.valueOf(state));
        good.setType(Integer.valueOf(type));
        good.setImageUrl(imageUrl);
        good.setShopId(Integer.valueOf(shopId));

        M1011.Content content = new M1011.Content();

        content.header = new M1011.Content.Header();
        content.header.imageList = new ArrayList<>();
        for (String s : detail.header.imageList) {
            content.header.imageList.add(s);
        }

        content.detail = new M1011.Content.Detail();
        content.detail.imageList = new ArrayList<>();
        for (String s : detail.content.imageList) {
            content.detail.imageList.add(s);
        }

        good.setContent(new Gson().toJson(content));

        SqlSession session = getSqlSession();
        GoodDAL gDal = session.getMapper(GoodDAL.class);
        int row = gDal.insert(good);
        session.commit();
        if (row < 1) {
            createError(this);
            return;
        }

        //删除缓存
        GoodCache.clear(shopId);

        createSuccess(this);

    }
}
