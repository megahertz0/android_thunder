package com.qq.e.comm.net.rr;

import com.tencent.connect.common.Constants;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;

public interface Request {

    public enum Method {
        GET,
        POST;

        static {
            GET = new com.qq.e.comm.net.rr.Request.Method(Constants.HTTP_GET, 0);
            POST = new com.qq.e.comm.net.rr.Request.Method(Constants.HTTP_POST, 1);
            a = new com.qq.e.comm.net.rr.Request.Method[]{GET, POST};
        }
    }

    void addHeader(String str, String str2);

    void addQuery(String str, String str2);

    int getConnectionTimeOut();

    Map<String, String> getHeaders();

    Method getMethod();

    byte[] getPostData() throws Exception;

    int getPriority();

    Map<String, String> getQuerys();

    int getSocketTimeOut();

    String getUrl();

    String getUrlWithParas();

    Response initResponse(HttpUriRequest httpUriRequest, HttpResponse httpResponse);

    boolean isAutoClose();

    void setConnectionTimeOut(int i);

    void setSocketTimeOut(int i);
}
