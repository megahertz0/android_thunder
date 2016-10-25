package com.mediav.ads.sdk.adcore;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.alipay.sdk.authjs.a;
import com.mediav.ads.sdk.adcore.HttpRequester.Listener;
import com.mediav.ads.sdk.log.MVLog;
import com.tencent.connect.common.Constants;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.sdk.IHost;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

// compiled from: HttpRequester.java
class HttpRunable implements Runnable {
    private final int NET_TIMEOUT;
    private Context context;
    private Handler handler;
    private Boolean isUsecache;
    private Listener listener;
    private String urlString;

    public HttpRunable(String str, Handler handler, Listener listener, Context context, Boolean bool) {
        this.NET_TIMEOUT = 1000;
        this.urlString = null;
        this.handler = null;
        this.listener = null;
        this.context = null;
        this.isUsecache = Boolean.valueOf(true);
        this.urlString = str;
        this.handler = handler;
        this.listener = listener;
        this.context = context;
        this.isUsecache = bool;
    }

    public void run() {
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        BufferedInputStream bufferedInputStream;
        byte[] bytes;
        Object obj = null;
        Message message = new Message();
        HashMap hashMap = new HashMap();
        hashMap.put(a.c, this.listener);
        try {
            HttpCacher httpCacher = HttpCacher.get(this.context);
            try {
                obj = httpCacher.getAsBinary(this.urlString);
            } catch (Exception e) {
                Exception e2 = e;
                MVLog.e(new StringBuilder("Cache error").append(e2.getMessage()).toString());
                if (obj == null) {
                }
                if (this.isUsecache.booleanValue()) {
                    if (HttpRequester.isOpenLog.booleanValue()) {
                        MVLog.d("\u5f02\u6b65:\u7f13\u5b58\u672a\u547d\u4e2d");
                    }
                } else if (HttpRequester.isOpenLog.booleanValue()) {
                    MVLog.d("\u5f02\u6b65:\u4e0d\u4f7f\u7528\u7f13\u5b58");
                }
                httpURLConnection = (HttpURLConnection) new URL(this.urlString).openConnection();
                httpURLConnection.setConnectTimeout(IHost.HOST_NOFITY_REFRESH_LIST);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setRequestMethod(Constants.HTTP_GET);
                httpURLConnection.setDoInput(true);
                if (httpURLConnection.getResponseCode() == 200) {
                    inputStream = httpURLConnection.getInputStream();
                    bufferedInputStream = new BufferedInputStream(inputStream);
                    bytes = getBytes(inputStream);
                    bufferedInputStream.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    message.what = 0;
                    hashMap.put(SocializeConstants.JSON_DATA, bytes);
                    message.obj = hashMap;
                    httpCacher.put(this.urlString, bytes, (int) HttpCacher.TIME_DAY);
                } else {
                    message.what = 1;
                    hashMap.put("error", String.valueOf(httpURLConnection.getResponseCode()));
                    message.obj = hashMap;
                    httpURLConnection.disconnect();
                }
                this.handler.dispatchMessage(message);
            }
        } catch (Exception e3) {
            e2 = e3;
            httpCacher = null;
            MVLog.e(new StringBuilder("Cache error").append(e2.getMessage()).toString());
            if (obj == null) {
            }
            if (this.isUsecache.booleanValue()) {
                if (HttpRequester.isOpenLog.booleanValue()) {
                    MVLog.d("\u5f02\u6b65:\u4e0d\u4f7f\u7528\u7f13\u5b58");
                }
            } else if (HttpRequester.isOpenLog.booleanValue()) {
                MVLog.d("\u5f02\u6b65:\u7f13\u5b58\u672a\u547d\u4e2d");
            }
            httpURLConnection = (HttpURLConnection) new URL(this.urlString).openConnection();
            httpURLConnection.setConnectTimeout(IHost.HOST_NOFITY_REFRESH_LIST);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod(Constants.HTTP_GET);
            httpURLConnection.setDoInput(true);
            if (httpURLConnection.getResponseCode() == 200) {
                message.what = 1;
                hashMap.put("error", String.valueOf(httpURLConnection.getResponseCode()));
                message.obj = hashMap;
                httpURLConnection.disconnect();
            } else {
                inputStream = httpURLConnection.getInputStream();
                bufferedInputStream = new BufferedInputStream(inputStream);
                bytes = getBytes(inputStream);
                bufferedInputStream.close();
                inputStream.close();
                httpURLConnection.disconnect();
                message.what = 0;
                hashMap.put(SocializeConstants.JSON_DATA, bytes);
                message.obj = hashMap;
                httpCacher.put(this.urlString, bytes, (int) HttpCacher.TIME_DAY);
            }
            this.handler.dispatchMessage(message);
        }
        if (obj == null && this.isUsecache.booleanValue()) {
            if (HttpRequester.isOpenLog.booleanValue()) {
                MVLog.d("\u5f02\u6b65:\u7f13\u5b58\u547d\u4e2d");
            }
            message.what = 0;
            hashMap.put(SocializeConstants.JSON_DATA, obj);
            message.obj = hashMap;
        } else {
            if (this.isUsecache.booleanValue()) {
                if (HttpRequester.isOpenLog.booleanValue()) {
                    MVLog.d("\u5f02\u6b65:\u7f13\u5b58\u672a\u547d\u4e2d");
                }
            } else if (HttpRequester.isOpenLog.booleanValue()) {
                MVLog.d("\u5f02\u6b65:\u4e0d\u4f7f\u7528\u7f13\u5b58");
            }
            try {
                httpURLConnection = (HttpURLConnection) new URL(this.urlString).openConnection();
                httpURLConnection.setConnectTimeout(IHost.HOST_NOFITY_REFRESH_LIST);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setRequestMethod(Constants.HTTP_GET);
                httpURLConnection.setDoInput(true);
                if (httpURLConnection.getResponseCode() == 200) {
                    inputStream = httpURLConnection.getInputStream();
                    bufferedInputStream = new BufferedInputStream(inputStream);
                    bytes = getBytes(inputStream);
                    bufferedInputStream.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    message.what = 0;
                    hashMap.put(SocializeConstants.JSON_DATA, bytes);
                    message.obj = hashMap;
                    if (this.isUsecache.booleanValue() && httpCacher != null) {
                        httpCacher.put(this.urlString, bytes, (int) HttpCacher.TIME_DAY);
                    }
                } else {
                    message.what = 1;
                    hashMap.put("error", String.valueOf(httpURLConnection.getResponseCode()));
                    message.obj = hashMap;
                    httpURLConnection.disconnect();
                }
            } catch (Throwable e4) {
                message.what = 2;
                hashMap.put("error", e4.getMessage());
                message.obj = hashMap;
                MVLog.e(1, new StringBuilder("HttpRunable Run Error,url: ").append(this.urlString).toString(), e4);
            }
        }
        this.handler.dispatchMessage(message);
    }

    private byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr, 0, JsInterface.MSG_JS_COLLECT_WEBSITE);
            if (read == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
            byteArrayOutputStream.flush();
        }
    }
}
