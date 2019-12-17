package top.funning.app.service.qiniu.uploadtoken.get;

import com.qiniu.util.Auth;
import top.funning.app.config.C;
import top.funning.app.service.FnService;
import top.knxy.library.BaseService;
import top.knxy.library.utils.ServiceUtils;

public class M1015 extends FnService {

    public String suffix;

    @Override
    protected void run() throws Exception {
        if (!"jpg".equals(suffix) && !"jpeg".equals(suffix) && !"png".equals(suffix)) {
            createError(this,"只支持 jpg 和 png 格式的图片");
            return;
        }

        String accessKey = C.QiNiu.accessKey;
        String secretKey = C.QiNiu.secretKey;
        String bucket = C.QiNiu.bucket;
        String key = ServiceUtils.getUUid() + "." + suffix;
        Auth auth = Auth.create(accessKey, secretKey);
        String token = auth.uploadToken(bucket, key);

        Data data = new Data();
        data.token = token;
        data.name = key;
        this.data = data;

        createSuccess(this);
    }

    public static class Data {
        public String token;
        public String name;
    }
}
