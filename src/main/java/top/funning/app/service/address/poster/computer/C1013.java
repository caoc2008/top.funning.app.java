package top.funning.app.service.address.poster.computer;

import com.google.gson.Gson;
import top.funning.app.config.C;
import top.funning.app.service.FnService;
import top.funning.app.service.order.pay.C1010;
import top.knxy.library.BaseService;
import top.knxy.library.ServiceException;
import top.knxy.library.utils.WebUtils;

import java.util.HashMap;
import java.util.Map;

public class C1013 extends FnService {
    public String address;

    @Override
    protected void run() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("address", address);
        map.put("output", "json");
        map.put("ak", C.Baidu.key);
        C1010.LocationInfo locationInfo = new Gson().fromJson(WebUtils.get(C.Baidu.mapApiUrl, map), C1010.LocationInfo.class);

        if (locationInfo.status != 0) {
            throw new ServiceException("get location fail. address : " + address + " . Response" + new Gson().toJson(locationInfo));
        }

        Data data = new Data();
        this.data = data;

        data.lat = String.valueOf(locationInfo.result.location.lat);
        data.lng = String.valueOf(locationInfo.result.location.lng);
        createSuccess(this);
    }

    public static class Data {
        public String lat;
        public String lng;
    }

}

