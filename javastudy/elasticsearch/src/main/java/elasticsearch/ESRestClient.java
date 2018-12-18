package elasticsearch;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.handler.codec.http.HttpMethod;
import org.apache.http.entity.ContentType;
import util.HttpClientHelper;
import util.HttpRequestBuilder;

import java.util.Map;


public class ESRestClient {
    private String nameAddr;

    public ESRestClient(String nameAddr) {
        this.nameAddr = nameAddr;
    }

    public boolean ExistIndex(ESHotelIndexEnum index) throws Exception {
        boolean exist = false;
        String indexname = index.getIndexNameWithDate();
        String url = nameAddr;
        if (url.getBytes()[url.length() - 1] != '/') {
            url += "/";
        }
        url += indexname;
        HttpRequestBuilder builder = HttpRequestBuilder.CreateNew()
                .setMethod(HttpMethod.GET)
                .setUrl(url)
                .setUrl(url);
        String response = HttpClientHelper.getHttpClient().execute(builder.Build());
        try {
            if (response != null) {
                JSONObject jsonObject = JSON.parseObject(response);
                if (jsonObject.get(indexname) != null) {
                    exist = true;
                }
            }
        } catch (Exception e) {
        }
        return exist;
    }


    /**
     * 创建索引
     *
     * @param index
     * @param setting
     * @return -1： 创建失败
     * 0 ：已存在的索引
     * 1：创建成功
     * @throws Exception
     */
    public boolean CreateIndex(ESHotelIndexEnum index, Map<String, Object> setting) throws Exception {
        boolean created = false;
        String url = getUrl(index, nameAddr);
        HttpRequestBuilder builder = HttpRequestBuilder.CreateNew()
                .setMethod(HttpMethod.PUT)
                .setContentType(ContentType.APPLICATION_JSON)
                .setUrl(url);
        if (setting != null) {
            builder.setData(JSON.toJSONString(setting));
        }
        try {
            String response = HttpClientHelper.getHttpClient().execute(builder.Build());
            if (response != null) {
                JSONObject jsonObject = JSON.parseObject(response);
                created = jsonObject.getBoolean("acknowledged");
            }
        } catch (Exception e) {
        }
        return created;
    }

    public boolean DeleteIndex(ESHotelIndexEnum index) {
        boolean delete = false;
        String url = getUrl(index, nameAddr);
        HttpRequestBuilder builder = HttpRequestBuilder.CreateNew()
                .setMethod(HttpMethod.DELETE)
                .setUrl(url);
        try {
            String response = HttpClientHelper.getHttpClient().execute(builder.Build());
            if (response != null) {
                JSONObject jsonObject = JSON.parseObject(response);
                delete = jsonObject.getBoolean("acknowledged");
            }
        } catch (Exception e) {
        }
        return delete;
    }

    /**
     * 向指定索引插入数据
     *
     * @param index
     * @param document
     * @return
     * @throws Exception
     */
    public boolean IndexInsert(ESHotelIndexEnum index, String document) throws Exception {
        String url = getUrl(index, nameAddr) + "/" + index.getTypeName();
        HttpRequestBuilder builder = HttpRequestBuilder.CreateNew()
                .setUrl(url)
                .setMethod(HttpMethod.POST)
                .setContentType(ContentType.APPLICATION_JSON)
                .setData(document);

        ESIndexInsertRsp rsp = HttpClientHelper.getHttpClient().execute(builder, ESIndexInsertRsp.class);
        if (rsp != null && rsp.getCreated()) {
            return true;
        }
        return false;
    }

    /**
     * 索引数据查询
     *
     * @param index
     * @param dsl
     * @return
     */
    public String IndexQuery(ESHotelIndexEnum index, String dsl) throws Exception {
        return IndexQuery(index.getIndexNameWithDate(), dsl);
    }

    /**
     * 索引数据查询
     *
     * @param index
     * @param dsl
     * @return
     */
    public String IndexQuery(String index, String dsl) throws Exception {
        String url = getUrl(index, nameAddr);
        url += "/_search";
        HttpRequestBuilder builder = HttpRequestBuilder.CreateNew()
                .setUrl(url)
                .setMethod(HttpMethod.POST)
                .setContentType(ContentType.APPLICATION_JSON)
                .setData(dsl);

        String response = HttpClientHelper.getHttpClient().execute(builder, dsl);
        return response;
    }

    static String getUrl(ESHotelIndexEnum index, String nameAddr) {
        String url = nameAddr;
        String indexName = index.getIndexNameWithDate();
        if (url.getBytes()[url.length() - 1] != '/') {
            url += "/";
        }
        url += indexName;
        return url;
    }

    static String getUrl(String index, String nameAddr) {
        String url = nameAddr;
        String indexName = index;
        if (url.getBytes()[url.length() - 1] != '/') {
            url += "/";
        }
        url += indexName;
        return url;
    }
}
