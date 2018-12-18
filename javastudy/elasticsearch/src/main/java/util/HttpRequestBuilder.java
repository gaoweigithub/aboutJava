package util;

import io.netty.handler.codec.http.HttpMethod;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.alibaba.fastjson.util.IOUtils.UTF8;

public class HttpRequestBuilder {
    public HttpRequestBuilder setHeader(Map<String, String> header) {
        this.header = header;
        return this;
    }

    public HttpRequestBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public HttpRequestBuilder setMethod(HttpMethod method) {
        this.method = method;
        return this;
    }

    public HttpRequestBuilder setContentType(ContentType contentType) {
        this.contentType = contentType;
        return this;
    }

    public HttpRequestBuilder setData(String data) {
        this.data = data;
        return this;
    }

    public HttpRequestBuilder setParams(Map<String, Object> params) {
        this.params = params;
        return this;
    }

    public HttpRequestBuilder setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public HttpRequestBuilder setConnetTimeOut(int connetTimeOut) {
        this.connetTimeOut = connetTimeOut;
        return this;
    }

    private HttpRequestBuilder() {
    }

    public static HttpRequestBuilder CreateNew() {
        return new HttpRequestBuilder();
    }

    private Map<String, String> header;


    private String data;
    private String url;
    private HttpMethod method;
    private ContentType contentType;
    private Map<String, Object> params;
    private int timeout = 30000;//30s
    private int connetTimeOut = 30000;//30s


    public HttpRequestBase Build() throws Exception {
        if (url == null) {
            throw new Exception("url 不能为空");
        }
        HttpRequestBase requestBase;

        if (method == HttpMethod.DELETE || method == HttpMethod.GET) {
            if (params != null && params.size() > 0) {
                //拼接参数
                if (url.getBytes()[url.getBytes().length - 1] != '?') {
                    url += "?";
                }
                StringBuilder sb = new StringBuilder(url);
                for (String key : params.keySet()) {
                    sb.append("&").append(key).append("=").append(URLEncoder.encode(params.get(key).toString(), "utf-8"));
                }
                url = sb.toString();
            }
        }

        if (method == HttpMethod.GET) {
            requestBase = new HttpGet(url);
        } else if (method == HttpMethod.POST) {
            requestBase = new HttpPost(url);
        } else if (method == HttpMethod.PUT) {
            requestBase = new HttpPut(url);
        } else if (method == HttpMethod.DELETE) {
            requestBase = new HttpDelete(url);
        } else {
            throw new Exception("不支持的请求方式");
        }
        //contentType
        if (contentType != null) {
            //检查header
            if (header == null) {
                header = new HashMap<>();
            }
            if (!header.containsKey("Content-Type")) {
                header.put("Content-Type", null);
            }
            header.put("Content-Type", contentType.getMimeType());
        }

        if (header != null) {
            for (String key : header.keySet()) {
                requestBase.addHeader(key, header.get(key));
            }
        }
        if (data != null) {
            if (method == HttpMethod.POST || method == HttpMethod.PUT) {
                HttpEntity entity = new StringEntity(data, "UTF-8");
                ((HttpEntityEnclosingRequest) requestBase).setEntity(entity);
            } else {
                throw new Exception(method.name() + "不支持传入data");
            }
        }
        if (method == HttpMethod.POST || method == HttpMethod.PUT) {
            if (contentType == ContentType.APPLICATION_FORM_URLENCODED) {
                if (params != null) {
                    List<BasicNameValuePair> list = new LinkedList<>();
                    for (String key : params.keySet()) {
                        list.add(new BasicNameValuePair(key, params.get(key).toString()));
                    }
                    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, UTF8);
                    ((HttpEntityEnclosingRequest) requestBase).setEntity(entity);
                }
            }
        }
//        else if (method == HttpMethod.DELETE || method == HttpMethod.GET) {
//            if (params != null && params.size() > 0) {
//                //拼接参数
//                if (url.getBytes()[url.getBytes().length - 1] != '?') {
//                    url += "?";
//                }
//                StringBuilder sb = new StringBuilder(url);
//                for (String key : params.keySet()) {
//                    sb.append("&").append(key).append("=").append(params.get(key));
//                }
//                url = sb.toString();
//            }
//        }
        requestBase.setConfig(RequestConfig.custom()
                .setConnectTimeout(connetTimeOut)
                .setSocketTimeout(timeout)
                .setConnectionRequestTimeout(timeout).build());

        return requestBase;
    }
}
