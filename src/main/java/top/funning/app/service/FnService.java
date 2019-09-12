package top.funning.app.service;

import net.sf.oval.constraint.Digits;
import net.sf.oval.constraint.NotNull;
import top.knxy.library.BaseService;

public abstract class FnService extends BaseService {

    @Digits
    public String shopId;
}
