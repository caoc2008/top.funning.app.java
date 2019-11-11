package top.funning.app.bean;

import java.io.Serializable;

public class WcSessionInfo implements Serializable {
    public String openId;
    public String sessionKey;
    public String userId;

    public WcSessionInfo(String openId, String sessionKey, String userId) {
        this.openId = openId;
        this.sessionKey = sessionKey;
        this.userId = userId;
    }
}
