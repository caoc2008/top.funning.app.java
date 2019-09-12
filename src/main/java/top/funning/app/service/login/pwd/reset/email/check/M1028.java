package top.funning.app.service.login.pwd.reset.email.check;

import net.sf.oval.constraint.NotNull;
import top.funning.app.database.Redis;
import top.knxy.library.BaseService;
import top.knxy.library.utils.TextUtils;

public class M1028 extends BaseService {
    @NotNull
    public String redisCode;

    @Override
    protected void run() throws Exception {
        String email = Redis.get(redisCode);
        if (TextUtils.isEmpty(email)) {
            createError(this, "链接错误或者过期");
            return;
        }

        createSuccess(this);
    }
}
