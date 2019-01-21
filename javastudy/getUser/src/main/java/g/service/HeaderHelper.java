package g.service;

import java.util.HashMap;
import java.util.Map;

public class HeaderHelper {
    private String cookie;

    public HeaderHelper(String cookie) {
        this.cookie = cookie;
    }

    public Map<String, String> getUserListHeader() {
        Map<String, String> header = new HashMap<>();
        header.put("Host", "smartops.zj.chinamobile.com");
        header.put("Connection", "keep-alive");
        header.put("Accept", "application/json, text/javascript, */*; q=0.01");
        header.put("Origin", "http://smartops.zj.chinamobile.com");
        header.put("X-Requested-With", "XMLHttpRequest");
        header.put("User-Agent", "Mozilla/5.0 (Linux; Android 7.0; PRO 6 Plus Build/NRD90M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/51.0.2704.110 Mobile Safari/537.36/Worklight/7.0.0.0");
        header.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        header.put("Referer", "http://smartops.zj.chinamobile.com/hkbappm/hkbmobile/marketMap.html?city_id=570&org_id=40009065&op_id=20287340&product_no=15105704896");
        header.put("Accept-Encoding", "gzip, deflate");
        header.put("Accept-Language", "zh-CN,en-US;q=0.8");
        header.put("Cookie", cookie);
        return header;
    }
}
