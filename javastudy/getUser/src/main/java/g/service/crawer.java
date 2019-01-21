package g.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import g.common.HttpClientHelper;
import g.common.HttpRequestBuilder;
import g.model.userInfo;
import org.springframework.http.HttpMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class crawer {
    public static void main(String[] args) throws Exception {

//        Host: smartops.zj.chinamobile.com
//        Connection: keep-alive
//        Content-Length: 49
//        Accept: application/json, text/javascript, */*; q=0.01
//        Origin: http://smartops.zj.chinamobile.com
//        X-Requested-With: XMLHttpRequest
//        User-Agent: Mozilla/5.0 (Linux; Android 7.0; PRO 6 Plus Build/NRD90M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/51.0.2704.110 Mobile Safari/537.36/Worklight/7.0.0.0
//        Content-Type: application/x-www-form-urlencoded;charset=UTF-8
//        Referer: http://smartops.zj.chinamobile.com/hkbappm/hkbmobile/marketMap.html?city_id=570&org_id=40009065&op_id=20287340&product_no=15105704896
//        Accept-Encoding: gzip, deflate
//        Accept-Language: zh-CN,en-US;q=0.8
//        Cookie: JSESSIONID=72FC2BB5F5FFFD4EE2CFF2259C3F9B8C; SESSION_COOKIE=10.78.200.174:31095

        HeaderHelper headerHelper = new HeaderHelper("JSESSIONID=72FC2BB5F5FFFD4EE2CFF2259C3F9B8C; SESSION_COOKIE=10.78.200.174:31095");
        HttpRequestBuilder builder = HttpRequestBuilder.CreateNew()
                .setUrl("http://smartops.zj.chinamobile.com/hkbappm/OutCallListController/queryUserAmount.do")
                .setHeader(headerHelper.getUserListHeader())
                .setData("opId=20287340&busiType='0'&activityId=&cityId=570")
                .setMethod(HttpMethod.POST);
        String userList = HttpClientHelper.getHttpClient().execute(builder.Build());
        JSONArray users = JSON.parseArray(userList);
        Map<String, userInfo> result = new HashMap<>();

        for (Object jo : users) {
            JSONObject jsonObject = (JSONObject) jo;
            String id = jsonObject.getString("id");
            if (!result.containsKey(id)) {
                userInfo userInfo = new userInfo();
                userInfo.setRecList(new ArrayList<>());
                result.put(id, userInfo);
            }
        }
        System.out.println("查找到：" + users.size() + "数据");


    }
}
