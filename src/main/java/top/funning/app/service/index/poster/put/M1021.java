package top.funning.app.service.index.poster.put;

import net.sf.oval.constraint.NotNull;
import top.funning.app.config.C;
import top.funning.app.database.Redis;
import top.funning.app.service.FnService;
import top.knxy.library.BaseService;
import top.knxy.library.config.V;
import top.knxy.library.ServiceException;
import top.knxy.library.utils.TextUtils;

public class M1021 extends FnService {
    @NotNull
    public String fileName;

    @Override
    public void run() throws Exception {

        String value = C.App.imageHost + fileName;
        Redis.set(shopId + "_" + V.postImageUrl, value);
        createSuccess(this);
    }
}
