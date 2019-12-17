package top.funning.app.service.address.getPcd;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import top.funning.app.service.FnService;
import top.knxy.library.config.Code;
import top.knxy.library.config.D;
import top.knxy.library.utils.FileUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//抄自：https://blog.csdn.net/weixin_43865866/article/details/102917670

public class C1034 extends FnService {

    public static void main(String[] args) throws Exception {
        C1034 service = new C1034();
        service.start();
        if (service.code == Code.Service.SUCCESS) {
            FileUtils.fileWrite("/Users/faddenyin/workspace/a.json", new Gson().toJson(service.data));
        }
    }

    @Override
    protected void run() throws Exception {
        String htmlText = getHtml("http://xzqh.mca.gov.cn/map");
        JSONArray aLLCodeList = getALLCode(htmlText);
        JSONArray dataList = getShengCode(aLLCodeList);
        Data data = Translate(dataList);
        this.data = data;
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

    public Data Translate(JSONArray ps) {
        Data data = new Data();
        data.provinces = new ArrayList<>();
        for (int i = 0; i < ps.size(); i++) {
            Data.Province province = new Data.Province();
            data.provinces.add(province);

            Map<String, Object> mapP = (Map<String, Object>) ps.get(i);
            province.adcode = mapP.get("xzqhdm").toString();
            province.name = mapP.get("name").toString();

            Object objP = mapP.get("NextLevel");
            if (objP == null) {
                continue;
            }
            JSONArray cs = (JSONArray) objP;
            province.cities = new ArrayList<>(cs.size());

            for (int j = 0; j < cs.size(); j++) {
                Data.Province.City city = new Data.Province.City();
                province.cities.add(city);
                Map<String, Object> mapC = (Map<String, Object>) cs.get(j);
                city.adcode = mapC.get("xzqhdm").toString();
                city.name = mapC.get("name").toString();

                Object objC = mapC.get("NextLevel");
                if (objC == null) {
                    continue;
                }
                JSONArray ds = (JSONArray) objC;
                city.districts = new ArrayList<>(ds.size());

                for (int z = 0; z < ds.size(); z++) {
                    Data.Province.City.District district = new Data.Province.City.District();
                    city.districts.add(district);
                    Map<String, Object> mapD = (Map<String, Object>) ds.get(z);

                    district.adcode = mapD.get("xzqhdm").toString();
                    district.name = mapD.get("name").toString();
                }
            }
        }

        return data;
    }


    //获取网页内容
    public String getHtml(String address) throws IOException {
        URL url = new URL(address);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(), "GBK"));
        String inputLine;
        String result = "";
        while ((inputLine = bufferedReader.readLine()) != null) {
            result += (result.equals("") ? "" : "\n");
            result += inputLine;
        }
        bufferedReader.close();
        return result;
    }

    //获取全部行政区划代码
    public JSONArray getALLCode(String htmlText) {
        int starIndex = htmlText.indexOf("[{");
        int endIndex = htmlText.indexOf("}]");

        String str = htmlText.substring(starIndex, endIndex + 2);
        JSONArray list = (JSONArray) JSON.parse(str);
        return list;
    }

    //获取省级行政区划
    public JSONArray getShengCode(JSONArray aLLCodeList) {
        JSONArray sheng = new JSONArray();

        for (int j = 0; j < aLLCodeList.size(); j++) {
            String code = aLLCodeList.getJSONObject(j).get("code").toString();

            if (code.substring(2, 6).equals("0000")) {
                int i = 0;
                Map map = new HashMap();

                map.put("xzqhdm", aLLCodeList.getJSONObject(j).get("code").toString());
                map.put("jp", aLLCodeList.getJSONObject(j).get("jp").toString());
                map.put("py", aLLCodeList.getJSONObject(j).get("qp").toString());
                map.put("name", aLLCodeList.getJSONObject(j).get("cName").toString());
                map.put("level", "1");

                aLLCodeList.remove(j);
                j--;

                JSONArray shi = getShiCode(aLLCodeList, code);
                map.put("NextLevel", shi);
                sheng.add(i, map);
            }
        }
        return sheng;
    }

    //获取市级行政区划
    public JSONArray getShiCode(JSONArray aLLCodeList, String shengCode) {
        JSONArray shi = new JSONArray();

        for (int i = 0; i < aLLCodeList.size(); i++) {
            String code = aLLCodeList.getJSONObject(i).get("code").toString();

            if (shengCode.equals("110000") || shengCode.equals("310000") || shengCode.equals("120000") || shengCode.equals("500000")) {
                String shengDm = shengCode.substring(0, 2) + "01";

                if (code.substring(0, 4).equals(shengDm)) {
                    Map map = new HashMap();
                    int j = 0;

                    map.put("xzqhdm", aLLCodeList.getJSONObject(i).get("code").toString());
                    map.put("jp", aLLCodeList.getJSONObject(i).get("jp").toString());
                    map.put("py", aLLCodeList.getJSONObject(i).get("qp").toString());
                    map.put("name", aLLCodeList.getJSONObject(i).get("cName").toString());
                    map.put("level", "2");
                    map.put("NextLevel", new JSONArray());

                    shi.add(j, map);
                    aLLCodeList.remove(i);
                    i--;
                }

            } else {
                if (code.substring(0, 2).equals(shengCode.substring(0, 2)) && code.substring(4, 6).equals("00")) {
                    Map map = new HashMap();
                    int j = 0;

                    map.put("xzqhdm", aLLCodeList.getJSONObject(i).get("code").toString());
                    map.put("jp", aLLCodeList.getJSONObject(i).get("jp").toString());
                    map.put("py", aLLCodeList.getJSONObject(i).get("qp").toString());
                    map.put("name", aLLCodeList.getJSONObject(i).get("cName").toString());
                    map.put("level", "2");

                    aLLCodeList.remove(i);
                    i--;

                    JSONArray xian = getXianCode(aLLCodeList, code);
                    map.put("NextLevel", xian);
                    shi.add(j, map);
                }

            }

        }
        return shi;
    }

    //获取区县行政区划
    public JSONArray getXianCode(JSONArray aLLCodeList, String shiCode) {
        JSONArray xian = new JSONArray();

        for (int i = 0; i < aLLCodeList.size(); i++) {
            String code = aLLCodeList.getJSONObject(i).get("code").toString();

            if (code.substring(0, 4).equals(shiCode.substring(0, 4)) && !code.substring(2, 4).equals("00")) {
                Map map = new HashMap();
                int j = 0;

                map.put("xzqhdm", aLLCodeList.getJSONObject(i).get("code").toString());
                map.put("jp", aLLCodeList.getJSONObject(i).get("jp").toString());
                map.put("py", aLLCodeList.getJSONObject(i).get("qp").toString());
                map.put("name", aLLCodeList.getJSONObject(i).get("cName").toString());
                map.put("level", "3");

                xian.add(j, map);
                aLLCodeList.remove(i);
                i--;
            }
        }
        return xian;
    }
}



