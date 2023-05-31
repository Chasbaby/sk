package com.ruoyi.common.utils.baidu.domain.panorama;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class SearchHttpAK {

    public static String URL = "https://api.map.baidu.com/panorama/v2?";

    public static String AK = "qOODeQG4eQRtkrNor1lFe4rLS6sWEhDt";

    public static void main(String[] args) throws Exception {

        SearchHttpAK snCal = new SearchHttpAK();

        Map params = new LinkedHashMap<String, String>();
        params.put("width", "512");
        params.put("height", "256");
        params.put("location", "117.645088,28.869585");
        params.put("fov", "180");
        params.put("ak", AK);

        snCal.requestGetAK(URL, params);
    }

    public void requestGetAK(String strUrl, Map<String, String> param) throws Exception {
        if (strUrl == null || strUrl.length() <= 0 || param == null || param.size() <= 0) {
            return;
        }

        StringBuffer queryString = new StringBuffer();
        queryString.append(strUrl);
        for (Map.Entry<?, ?> pair : param.entrySet()) {
            queryString.append(pair.getKey() + "=");
            queryString.append(URLEncoder.encode((String) pair.getValue(),
                    "UTF-8") + "&");
        }

        if (queryString.length() > 0) {
            queryString.deleteCharAt(queryString.length() - 1);
        }

        java.net.URL url = new URL(queryString.toString());
        System.out.println(queryString.toString());
        URLConnection httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.connect();

        InputStreamReader isr = new InputStreamReader(httpConnection.getInputStream());
        BufferedReader reader = new BufferedReader(isr);
        StringBuffer buffer = new StringBuffer();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();
        isr.close();
        System.out.println("AK: " + buffer.toString());
    }
}
