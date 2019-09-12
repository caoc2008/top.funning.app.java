package top.funning.app.service;

import java.io.Serializable;

public class SessionInfo implements Serializable {
    public String openId;
    public String sessionKey;
    public String userId;

    public SessionInfo(String openId, String sessionKey, String userId) {
        this.openId = openId;
        this.sessionKey = sessionKey;
        this.userId = userId;
    }
}
