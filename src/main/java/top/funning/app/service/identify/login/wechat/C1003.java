package top.funning.app.service.identify.login.wechat;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import net.sf.oval.constraint.NotNull;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.database.dal.ShopDAL;
import top.funning.app.database.dal.UserDAL;
import top.funning.app.database.table.Shop;
import top.funning.app.database.table.User;
import top.funning.app.service.FnService;
import top.knxy.library.ServiceException;
import top.knxy.library.utils.TextUtils;
import top.knxy.library.utils.WebUtils;

import java.util.HashMap;
import java.util.Map;

public class C1003 extends FnService {
    @NotNull
    public String jsCode;

    @Override
    public void run() throws Exception {

        SqlSession session = getSqlSession();
        ShopDAL shopDAL = session.getMapper(ShopDAL.class);
        Shop shop = shopDAL.get(header.shopId);
        if (TextUtils.isEmpty(shop.getWechatAppid()) || TextUtils.isEmpty(shop.getWechatSecret())) {
            createResult(this, -6001,"小程序信息未填写");
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("appid", shop.getWechatAppid());
        map.put("secret", shop.getWechatSecret());
        map.put("js_code", jsCode);
        map.put("grant_type", "authorization_code");
        String str = WebUtils.get("https://api.weixin.qq.com/sns/jscode2session", map);

        Gson gson = new Gson();
        WeiXinResponseModel wxrp = gson.fromJson(str, WeiXinResponseModel.class);
        if (!TextUtils.isEmpty(wxrp.errcode) && !"0".equals(wxrp.errcode)) {
            createError(this, wxrp.errmsg);
            return;
        }

        UserDAL mapper = session.getMapper(UserDAL.class);
        //1. getGood user info form db
        User user = mapper.getUserByOpenId(wxrp.openid, header.shopId);
        if (user == null) {
            //2. 将记录插入到数据库中
            user = new User();
            user.setOpenId(wxrp.openid);
            user.setShopId(Integer.valueOf(header.shopId));
            int result = mapper.insert(user);
            session.commit();
            if (result < 1) {

                throw new ServiceException("登录失败");
            }
        }

        this.data = new Data(wxrp, user.getId());
        createSuccess(this);
    }

    public static class Data {
        public String openid;
        public String sessionKey;
        public String userId;

        public Data(WeiXinResponseModel wxrp, int id) {
            this.openid = wxrp.openid;
            this.sessionKey = wxrp.sessionKey;
            this.userId = String.valueOf(id);
        }
    }

    public static class WeiXinResponseModel {
        public String openid;
        @SerializedName("session_key")
        public String sessionKey;
        public String unionid;
        public String errcode;
        public String errmsg;
    }
}
