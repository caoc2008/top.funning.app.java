package top.funning.app.service;

import top.knxy.library.BaseService;

public abstract class FnService extends BaseService {

    public Header header;

    public static class Header {
        public String shopId;
    }
}
