package top.funning.app.database.table;

public class Shop {
    private int id;
    private String appName;
    private String phoneNum;
    private String logoUrl;
    private String wcpayMchId;
    private String wcpayApiKey;
    private String wechatAppid;
    private String wechatSecret;
    private String p12FileName;

    public String getP12FileName() {
        return p12FileName;
    }

    public void setP12FileName(String p12FileName) {
        this.p12FileName = p12FileName;
    }

    public String getWcpayMchId() {
        return wcpayMchId;
    }

    public void setWcpayMchId(String wcpayMchId) {
        this.wcpayMchId = wcpayMchId;
    }

    public String getWcpayApiKey() {
        return wcpayApiKey;
    }

    public void setWcpayApiKey(String wcpayApiKey) {
        this.wcpayApiKey = wcpayApiKey;
    }

    public String getWechatAppid() {
        return wechatAppid;
    }

    public void setWechatAppid(String wechatAppid) {
        this.wechatAppid = wechatAppid;
    }

    public String getWechatSecret() {
        return wechatSecret;
    }

    public void setWechatSecret(String wechatSecret) {
        this.wechatSecret = wechatSecret;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
