package g.common;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class HttpClientHelper {

    private static HttpClientHelper instance = new HttpClientHelper();;
    private static Lock lock = new ReentrantLock();
    private static PoolingHttpClientConnectionManager pool;

    //    private static CloseableHttpClient httpClient;
    static {
        pool = new PoolingHttpClientConnectionManager();
        pool.setMaxTotal(500);
        pool.setDefaultMaxPerRoute(50);
    }

    public HttpClientHelper() {
        instance = this;
    }

    public static HttpClientHelper getHttpClient() {
//        if (instance == null) {
//            lock.lock();
//            try {
//                instance = new HttpClientHelper();
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                lock.unlock();
//            }
//        }
        return instance;
    }

    public byte[] executeAndReturnByte(HttpRequestBase request) throws Exception {
        HttpEntity entity;
        CloseableHttpResponse response = null;
        byte[] base = new byte[0];
        if (request == null) {
            return base;
        }
//        try (CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(pool).build()) {
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(pool).build();
        response = httpClient.execute(request);
        entity = response.getEntity();
        String encode = ("" + response.getFirstHeader("Content-Encoding")).toLowerCase();
        if (encode.indexOf("gzip") > 0) {
            entity = new GzipDecompressingEntity(entity);
        }
        base = EntityUtils.toByteArray(entity);
        EntityUtils.consumeQuietly(entity);
//        }finally {
////            response.close();
//        }
        return base;
    }

    public String execute(HttpRequestBase request) throws Exception {
        byte[] base = executeAndReturnByte(request);
        if (base == null) {
            return null;
        }
        String result = new String(base, "UTF-8");
        return result;
    }

    public String execute(HttpRequestBuilder builder, String dsl) throws Exception {
        return execute(builder.Build());
    }

    public <T> T execute(HttpRequestBuilder builder, Class<T> classz) throws Exception {
        String response = execute(builder.Build());
        T result = JSON.parseObject(response, classz);
        return result;
    }
}
