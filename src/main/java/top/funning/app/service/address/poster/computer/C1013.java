package top.funning.app.service.address.poster.computer;

import com.google.gson.Gson;
import net.sf.oval.constraint.NotNull;
import top.funning.app.service.FnService;
import top.funning.app.service.order.pay.aliyun.C1033;
import top.knxy.library.BaseService;
import top.knxy.library.ServiceException;
import top.knxy.library.utils.LocationUtils;
import top.knxy.library.utils.LogUtils;
import top.knxy.library.utils.WebUtils;

import java.math.BigDecimal;
import java.net.URLEncoder;

public class C1013 extends FnService {

    public static final String TAG = "C1013.address.compute";

    private final static double locationLat = 23.091333102548455;
    private final static double locationLng = 113.33717442876233;

    @NotNull
    public String countryName;

    @NotNull
    public String provinceName;

    @NotNull
    public String cityName;

    @NotNull
    public String detailInfo;

    @Override
    protected void run() throws Exception {
        //计算运费
        String url = "http://api.map.baidu.com/geocoder/v2/?address=" +
                URLEncoder.encode(countryName + provinceName + cityName + detailInfo, "UTF-8") +
                "&output=json&ak=tj3qu8wHTAFgQ3OmZbl8CLzTznki2VGR";
        C1033.LocationInfo locationInfo = new Gson().fromJson(WebUtils.get(url), C1033.LocationInfo.class);

        if (locationInfo.status != 0) {
            throw new ServiceException("get location fail. " + url);
        }

        double distance = LocationUtils.getDistance(
                locationInfo.result.location.lat,
                locationInfo.result.location.lng,
                locationLat,
                locationLng
        );
        distance = distance / 1000;

        LogUtils.i(TAG, String.format("distance = %s , lat1 = %s , lng2 = %s , lat2 = %s , lng2 = %s",
                distance,
                locationInfo.result.location.lat, locationInfo.result.location.lng,
                locationLat, locationLng));

        LogUtils.i(TAG, "distance = " + distance);

        if (distance < 10) {
            this.data = new Data("0");
        } else {
            this.data = new Data("6");
        }
        createSuccess(this);
    }

    public static class Data {
        public String poster;

        public Data(String poster) {
            this.poster = poster;
        }
    }
}
