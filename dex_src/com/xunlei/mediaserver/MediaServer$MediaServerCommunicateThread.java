package com.xunlei.mediaserver;

import java.io.IOException;
import javax.net.ssl.SSLHandshakeException;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

private class MediaServer$MediaServerCommunicateThread extends Thread {
    String finalUrl;
    MediaServer$OnVodTaskErrorListener onVodTaskErrorListener;
    final /* synthetic */ MediaServer this$0;
    private String type;

    public MediaServer$MediaServerCommunicateThread(MediaServer mediaServer, String str, String str2) {
        this.this$0 = mediaServer;
        this.finalUrl = null;
        this.finalUrl = str;
        this.type = str2;
    }

    public MediaServer$MediaServerCommunicateThread(MediaServer mediaServer, String str, MediaServer$OnVodTaskErrorListener mediaServer$OnVodTaskErrorListener) {
        this.this$0 = mediaServer;
        this.finalUrl = null;
        this.finalUrl = str;
        this.onVodTaskErrorListener = mediaServer$OnVodTaskErrorListener;
    }

    public void run() {
        if (this.finalUrl != null) {
            HttpRequestRetryHandler anonymousClass_1 = new HttpRequestRetryHandler() {
                public boolean retryRequest(IOException iOException, int i, HttpContext httpContext) {
                    if (!(i >= 0 || (iOException instanceof NoHttpResponseException) || (iOException instanceof SSLHandshakeException))) {
                        boolean z;
                        if (((HttpRequest) httpContext.getAttribute("http.request")) instanceof HttpEntityEnclosingRequest) {
                            z = false;
                        } else {
                            Object obj = 1;
                        }
                        if (z) {
                        }
                    }
                    return false;
                }
            };
            HttpUriRequest httpGet = new HttpGet(this.finalUrl);
            HttpClient defaultHttpClient = new DefaultHttpClient();
            ((DefaultHttpClient) defaultHttpClient).setHttpRequestRetryHandler(anonymousClass_1);
            try {
                HttpResponse execute = defaultHttpClient.execute(httpGet);
                if (execute.getStatusLine().getStatusCode() != 200) {
                    return;
                }
                if (this.type == null || !"playVodUrl".equals(this.type)) {
                    String toString = EntityUtils.toString(execute.getEntity());
                    if (toString != null && toString.contains("ret")) {
                        JSONObject jSONObject = new JSONObject(toString);
                        if (jSONObject.getInt("ret") == 0) {
                            int i = jSONObject.getJSONObject("resp").getInt("vod_resp_code");
                            if (this.onVodTaskErrorListener != null) {
                                this.onVodTaskErrorListener.onGetVodTaskErrorCode(i);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
