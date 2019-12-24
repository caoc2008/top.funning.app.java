package top.funning.app.service;

import top.funning.app.config.C;
import top.knxy.library.BaseService;
import top.knxy.library.service.notification.CL1005;
import top.knxy.library.utils.LogUtils;

public abstract class FnService extends BaseService {

    public Header header;

    public static class Header {
        public String shopId;
    }


    public void start() {
        try {
            valid();
            run();
        } catch (Exception e) {
            LogUtils.e(this.getClass().getSimpleName(), "service exception");
            CL1005.doService(C.App.phone,C.App.name);
            e.printStackTrace();
            createError(this, "发生错误了");
        } finally {
            if (sqlSession != null)
                sqlSession.close();
        }
    }
}
