package top.funning.app.config;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import top.knxy.library.utils.FileUtils;

import java.io.File;
import java.io.FileReader;
import java.util.Date;

public class C {


    public static class App {
        public static final boolean isDebug = getBool("app.isDebug");
        public static final String name = getValue("app.name");
        public static final String ip = getValue("app.ip");
        public static final String imageHost = getValue("app.image.host");
        public static String domain;

        static {
            if (isDebug) {
                domain = "http://127.0.0.1:" + getValue("server.port");
            } else {
                domain = getValue("app.domain");
            }
        }
    }

    public static class Baidu {
        public static final String mapApiUrl = getValue("baidu.mapApiUrl");
        public static final String key = getValue("baidu.key");
    }

    public static class QiNiu {
        public static final String accessKey = getValue("qiniu.accessKey");
        public static final String secretKey = getValue("qiniu.secretKey");
        public static final String bucket = getValue("qiniu.bucket");
    }

    public static class Aliyun {
        public static final String id = getValue("al.accessKey.id");
        public static final String secrety = getValue("al.accessKey.secret");
    }

    public static class Redis {
        public static final String host = getValue("spring.redis.host");
        public static final int port = Integer.valueOf(getValue("spring.redis.port"));
        public static final String password = getValue("spring.redis.password");
        public static final int db = Integer.valueOf(getValue("spring.redis.db"));
    }

    public static class AliPay {
        public static final String appId = getValue("alipay.appId");
        public static final String publicKey = getValue("alipay.publicKey");
        public static final String serverPublicKey = getValue("alipay.serverPublicKey");
        public static final String privateKey = getValue("alipay.privateKey");
        public static final String notifyUrl = getValue("alipay.notifyUrl");
    }

    public static class WCPay {
        public static String p12Path;
        public static String notifyUrl = getValue("wcpay.notifyUrl");

        static {
            if (C.App.isDebug) {
                p12Path = "/Users/faddenyin/workspace/private/wechat_pay/";
            } else {
                p12Path = getValue("wcpay.p12Path");
            }
        }
    }

    private static String getValue(String key) {
        try {
            return String.valueOf(FileUtils.getPropertyMap("application.properties").get(key));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean getBool(String key) {
        return Boolean.valueOf(getValue(key));
    }
}
