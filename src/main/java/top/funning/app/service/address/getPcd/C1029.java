package top.funning.app.service.address.getPcd;

import com.google.gson.Gson;
import top.funning.app.service.FnService;
import top.funning.app.service.address.Translator;
import top.knxy.library.utils.FileUtils;
import top.knxy.library.utils.WebUtils;

import java.util.ArrayList;
import java.util.List;

public class C1029 extends FnService {

    public static void main(String[] args) throws Exception {
        new C1029().run();
    }

    @Override
    protected void run() throws Exception {

        Translator translator = Translator.INSTANCES;
        Province p = WebUtils.getJson("http://datavmap-public.oss-cn-hangzhou.aliyuncs.com/areas/csv/100000_province.json", Province.class);
        Data data = new Data();
        data.provinces = new ArrayList<>(p.rows.size());

        for (Province.RowsBean pBean : p.rows) {
            Data.Province province = translator.toProvince(pBean);
            data.provinces.add(province);

            City c = null;
            try {
                c = WebUtils.getJson("http://datavmap-public.oss-cn-hangzhou.aliyuncs.com/areas/csv/" + province.adcode + "_city.json", City.class);
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }
            province.cities = new ArrayList<>(c.rows.size());

            for (City.RowsBean cBean : c.rows) {
                Data.Province.City city = translator.toCity(cBean);
                province.cities.add(city);

                District d = null;
                try {
                     d = WebUtils.getJson("http://datavmap-public.oss-cn-hangzhou.aliyuncs.com/areas/csv/" + city.adcode + "_district.json", District.class);
                }catch (Exception e){
                    e.printStackTrace();
                    continue;
                }
                city.districts = new ArrayList<>(d.rows.size());

                for (District.RowsBean dBean : d.rows) {
                    Data.Province.City.District district = translator.toDistrict(dBean);
                    city.districts.add(district);

                }
            }
        }

        this.data = data;

        FileUtils.fileWrite("/Users/faddenyin/workspace/a.json",new Gson().toJson(this.data));
        createSuccess(this);
    }

    public static class Data {

        public List<Province> provinces;

        public static class Province {
            public String adcode;
            public String name;
            public List<City> cities;

            public static class City {
                public String adcode;
                public String name;
                public List<District> districts;

                public static class District {
                    public String adcode;
                    public String name;
                }
            }
        }
    }

    public static class Province {
        public int total;
        public List<RowsBean> rows;

        public static class RowsBean {
            public String adcode;
            public String name;
        }
    }


    public static class City {
        public int total;
        public List<RowsBean> rows;

        public static class RowsBean {
            public String adcode;
            public String name;
        }
    }

    public static class District {
        public int total;
        public List<RowsBean> rows;

        public static class RowsBean {
            public String adcode;
            public String name;
        }
    }
}
