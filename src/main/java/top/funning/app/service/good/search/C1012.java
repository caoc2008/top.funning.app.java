package top.funning.app.service.good.search;

import top.funning.app.config.C;
import top.funning.app.database.cache.GoodCache;
import top.funning.app.service.FnService;
import top.knxy.library.utils.TextUtils;

import java.util.ArrayList;

public class C1012 extends FnService {
    public String word;

    @Override
    protected void run() throws Exception {
        Data data = new Data();
        if (TextUtils.isEmpty(word)) {
            data.dataList = new ArrayList<>();
            this.data = data;
            createSuccess(this);
            return;
        }

        data.dataList = GoodCache.search(word, header.shopId);
        for (GoodCache.Item item : data.dataList) {
            item.setImageUrl(C.App.imageHost + item.getImageUrl());
        }
        this.data = data;
        createSuccess(this);
    }

    public static class Data {

        private ArrayList<GoodCache.Item> dataList;

        public ArrayList<GoodCache.Item> getDataList() {
            return dataList;
        }

        public void setDataList(ArrayList<GoodCache.Item> dataList) {
            this.dataList = dataList;
        }
    }
}
