package top.funning.app.service.identify.register.android;

import com.google.gson.Gson;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotNull;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.bean.SmsInfo;
import top.funning.app.database.Redis;
import top.funning.app.database.dal.ShopDAL;
import top.funning.app.database.dal.UserDAL;
import top.funning.app.database.table.Shop;
import top.funning.app.database.table.User;
import top.funning.app.service.FnService;
import top.knxy.library.utils.LogUtils;
import top.knxy.library.utils.PwdUtils;
import top.knxy.library.utils.TextUtils;

public class C1022 extends FnService {

    private final String TAG = "C1022";

    @NotNull
    public String phone;
    @Length(min = 6, max = 18)
    public String password;
    @Length(min = 6, max = 6)
    public String smsCode;
    @Length(min = 32, max = 32)
    public String smsCodeId;

    @Override
    protected void run() throws Exception {
        Gson gson = new Gson();
        String str = Redis.get(smsCodeId);
        if (TextUtils.isEmpty(str)) {
            createError(this, "验证码不存在或已过期");
            return;
        }

        SmsInfo smsInfo = gson.fromJson(str, SmsInfo.class);
        if (!phone.equals(smsInfo.getPhone())) {
            createError(this, "注册手机号与验证码手机号不一致");
            return;
        }

        if (smsInfo.getErrorTime() > 5) {
            Redis.del(smsCodeId);
            createError(this, "验证码已失效，请从新获取");
            return;
        }

        LogUtils.i(TAG, String.format("timeout = %s", System.currentTimeMillis() - smsInfo.getData()));
        if (smsInfo.getData() + 1000 * 60 * 15 < System.currentTimeMillis()) {
            Redis.del(smsCodeId);
            createError(this, "验证码已经过时");
            return;
        }

        if (!smsCode.equals(smsInfo.getCode())) {
            long timeout = 60 * 15 - (System.currentTimeMillis() - System.currentTimeMillis()) / 1000;
            LogUtils.i(TAG, String.format("timeout = %s", timeout));
            smsInfo.setErrorTime(smsInfo.getErrorTime() + 1);
            Redis.set(smsCodeId, gson.toJson(smsInfo), timeout);
            createError(this, "验证码错误");
            return;
        }

        SqlSession session = getSqlSession();
        ShopDAL shopDAL = session.getMapper(ShopDAL.class);
        Shop shop = shopDAL.get(String.valueOf(header.shopId));
        if (shop == null) {
            createError(this, "不存在该商铺");
            return;
        }

        String salt = PwdUtils.createSalt();
        password = PwdUtils.sha1(password + salt);

        UserDAL userDAL = session.getMapper(UserDAL.class);
        if (userDAL.getUserByPhone(phone, header.shopId) != null) {
            Redis.del(smsCodeId);
            createError(this, "手机号已注册");
            return;
        }


        User user = new User();
        user.setPhone(phone);
        user.setPassword(password);
        user.setSalt(salt);
        user.setShopId(Integer.valueOf(header.shopId));

        userDAL.insert(user);
        session.commit();
        Redis.del(smsCodeId);
        createSuccess(this);
    }
}
