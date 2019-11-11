package top.funning.app.database.cache;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.dal.GoodDAL;
import top.funning.app.database.table.Good;
import top.knxy.library.utils.MyBatisUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodCache {

    private final static Map<String, List<Item>> data = new HashMap<>();

    public static void clear(String shopId) {
        List<Item> list = data.get(shopId);
        if (list != null)
            list.clear();
    }


    public static ArrayList<Item> search(String keyword, String shopId) throws Exception {
        List<Item> goods = data.get(shopId);

        if (goods == null) {
            goods = new ArrayList<>();
            data.put(shopId, goods);
        }

        if (goods.isEmpty()) {
            SqlSession session = MyBatisUtils.getSession();
            GoodDAL dal = session.getMapper(GoodDAL.class);
            List<Good> list = dal.getSearchList(shopId);
            for (Good good : list) {
                goods.add(new Item(good));
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

    public static void refresh(String shopId) throws Exception {
        List<Item> goods = data.get(shopId);

        if (goods == null) {
            return;
        }
        goods.clear();

        SqlSession session = MyBatisUtils.getSession();
        GoodDAL dal = session.getMapper(GoodDAL.class);
        List<Good> list = dal.getSearchList(shopId);
        for (Good good : list) {
            goods.add(new Item(good));
        }
        session.close();
    }

    private static Item copy(Item item) {
        Item newItem = new Item();
        newItem.id = item.getId();
        newItem.name = item.getName();
        newItem.imageUrl = item.getImageUrl();
        newItem.price = item.getPrice();
        return newItem;
    }


    public static class Item {
        private String id;
        private String name;
        private String imageUrl;
        private String price;

        public Item() {
        }

        public Item(Good good) {
            this.id = String.valueOf(good.getId());
            this.name = good.getName();
            this.imageUrl = good.getImageUrl();
            this.price = good.getPrice();
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
