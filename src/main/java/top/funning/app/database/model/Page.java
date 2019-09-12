package top.funning.app.database.model;

import top.knxy.library.utils.TextUtils;

public class Page {

    private int index;

    private int size = 20;

    public Page(String pString) throws Exception{
        int page = 1;
        if(!TextUtils.isEmpty(pString)){
            page = Integer.valueOf(pString);
        }

        if (page < 1) {
            throw new Exception("page < 1");
        }
        index = (page - 1) * size;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
