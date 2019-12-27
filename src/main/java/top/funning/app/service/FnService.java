package top.funning.app.service;

import com.google.gson.Gson;
import top.funning.app.config.C;
import top.knxy.library.BaseService;
import top.knxy.library.service.notification.sms.CL1006;
import top.knxy.library.utils.LogUtils;

import java.util.HashMap;

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
            notifyDeveloped();
            e.printStackTrace();
            createError(this, "发生错误了");
        } finally {
            if (sqlSession != null)
                sqlSession.close();
        }
    }

    private void notifyDeveloped() {
        CL1006 service = new CL1006();
        service.aliyunId = C.Aliyun.id;
        service.aliyunSecrety = C.Aliyun.secrety;
        service.phone = C.App.phone;
        service.templateCode = "SMS_181490246";

        HashMap<String, String> map = new HashMap<>();
        map.put("projectName", C.App.name);
        service.templateParam = new Gson().toJson(map);
        service.SignName = "饭宁";
        service.start();
    }
}
