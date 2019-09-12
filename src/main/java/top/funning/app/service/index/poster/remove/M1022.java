package top.funning.app.service.index.poster.remove;

import top.funning.app.database.Redis;
import top.funning.app.service.FnService;
import top.knxy.library.BaseService;
import top.knxy.library.config.V;

public class M1022 extends FnService {

    @Override
    public void run() throws Exception {

        Redis.del(shopId + "_" + V.postImageUrl);
        createSuccess(this);
    }

}
