package com.ruoyi.common.utils.baidu;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.baidu.domain.IP.Geocoder;
import com.ruoyi.common.utils.baidu.domain.IP.ParaGeo;
import com.ruoyi.common.utils.baidu.domain.weather.DomesticWeather;
import com.ruoyi.common.utils.baidu.domain.weather.Weather;
import com.ruoyi.common.utils.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Chas
 * @date 2023-1
 */

public class BaiduUtils {
    @Value("${baidu.map.ak}")
    private final static String AK="qOODeQG4eQRtkrNor1lFe4rLS6sWEhDt";
    private final static String Geocoder_url="https://api.map.baidu.com/geocoding/v3/";
    private final static String Weather_url = "https://api.map.baidu.com/weather/v1/?";
    private final static Logger logger = LoggerFactory.getLogger("baiduService");



    /**
     * 正地理编码
     *
     * "address=江苏省泰州市靖江市斜桥镇美尔惠服饰&output=json&ak=qOODeQG4eQRtkrNor1lFe4rLS6sWEhDt&extension_analys_level=1"
     *
     * sendGet中存在日志
     * @param  paraGeo
     * @return Geocoder
     */
    public static Geocoder getGeocoder(ParaGeo paraGeo){
        String s = HttpUtils.sendGet(Geocoder_url,paraGeo.ObjectToPara(),"utf-8");
        Geocoder geocoder = JSONObject.parseObject(s, Geocoder.class);
        return geocoder;
    }

    public static Weather getWeather(DomesticWeather weather){
        return null;
    }




    public static void main(String[] args) {
//        ParaGeo paraGeo = new ParaGeo();
//        paraGeo.setAk(AK);
//        paraGeo.setAddress("江苏省泰州市靖江市斜桥镇美尔惠服饰");
//        paraGeo.setOutput("json");
//        Geocoder geocoder = getGeocoder(paraGeo);
//        geocoder.getResult();
//
//        String s = HttpUtils.sendGet("https://api.map.baidu.com/place/v2/search?query=景点&region=广州市&output=json&ak=qOODeQG4eQRtkrNor1lFe4rLS6sWEhDt");
//        System.out.println(s);
//        String APP_ID = "20230411001637577";
//        String SECURITY_KEY = "E9tdbkYH75MOAyUfoyVQ";
//        TransApi api = new TransApi(APP_ID, SECURITY_KEY);
//
//        String query = "小池池最棒哦";
//        System.out.println(api.getTransResult(query, "auto", "en"));



        String xxx="https://api.map.baidu.com/weather_abroad/v1/?district_id=AFG10003003001&data_type=all&ak=qOODeQG4eQRtkrNor1lFe4rLS6sWEhDt";
        String s = HttpUtils.sendGet(xxx);
        System.out.println(s);
    }
}
