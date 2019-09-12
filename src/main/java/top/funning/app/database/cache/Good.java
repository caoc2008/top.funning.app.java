package top.funning.app.database.cache;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.dal.GoodDAL;
import top.knxy.library.utils.MyBatisUtils;
import top.knxy.library.utils.ServiceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Good {

    private final static Map<String, List<Item>> data = new HashMap<>();

    public static void clear(String shopId) {
        List<Item> list = data.get(shopId);
        if (list != null)
            list.clear();
    }


    public static ArrayList<Item> search(String keyword, String shopId) throws Exception {
        List<Item> goods = data.get(shopId);

        if (goods.isEmpty()) {
            SqlSession session = MyBatisUtils.getSession();
            GoodDAL dal = session.getMapper(GoodDAL.class);
            List<Map> list = dal.getSearchList(shopId);
            for (Map map : list) {
                goods.add(ServiceUtils.mapToBean(map, Item.class));
            }
            session.close();
        }

        ArrayList<Item> result = new ArrayList<>();
        for (Item i : goods) {
            String name = i.name;
            if (name.contains(keyword)) {
                if (name.equals(keyword)) {
                    result.add(0, copy(i));
                } else {
                    result.add(copy(i));
                }
            }
        }
        return result;
    }

    private static Item copy(Item preitem) {
        Item newItem = new Item();
        newItem.id = preitem.id;
        newItem.name = preitem.name;
        newItem.imageUrl = preitem.imageUrl;
        newItem.price = preitem.price;
        return newItem;
    }

    public static class Item {
        private String id;
        private String name;
        private String imageUrl;
        private String price;

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

    }
}
