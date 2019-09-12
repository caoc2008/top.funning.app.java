package top.funning.app.service.good.search;

import top.funning.app.database.cache.Good;
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

        data.dataList = Good.search(word,shopId);
        this.data = data;
        createSuccess(this);
    }

    public static class Data {

        private ArrayList<Good.Item> dataList;

        public ArrayList<Good.Item> getDataList() {
            return dataList;
        }

        public void setDataList(ArrayList<Good.Item> dataList) {
            this.dataList = dataList;
        }
    }
}
