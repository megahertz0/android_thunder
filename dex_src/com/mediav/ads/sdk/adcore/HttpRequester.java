package com.mediav.ads.sdk.adcore;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.mediav.ads.sdk.log.MVLog;
import com.tencent.connect.common.Constants;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.sdk.IHost;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequester {
    private static Handler handler;
    public static Boolean isOpenLog;

    public static interface Listener {
        void onGetDataFailed(String str);

        void onGetDataSucceed(byte[] bArr);
    }

    static {
        isOpenLog = Boolean.valueOf(true);
        handler = null;
    }

    public static void getAsynData(Context context, String str, Boolean bool, Listener listener) {
        if (handler == null) {
            handler = new Handler() {
                public final void handleMessage(Message message) {
                    super.handleMessage(message);
                    handler.post(new ResultRunable(message));
                }
            };
        }
        new Thread(new HttpRunable(str, handler, listener, context, bool)).start();
    }

    public static byte[] getSyncData(Context context, String str, Boolean bool) {
        byte[] asBinary;
        HttpCacher httpCacher;
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        BufferedInputStream bufferedInputStream;
        byte[] bytes;
        Exception exception;
        try {
            HttpCacher httpCacher2 = HttpCacher.get(context);
            try {
                asBinary = httpCacher2.getAsBinary(str);
                httpCacher = httpCacher2;
            } catch (Exception e) {
                Exception e2 = e;
                MVLog.e(new StringBuilder("Cache error").append(e2.getMessage()).toString());
                asBinary = null;
                httpCacher = httpCacher2;
                if (asBinary != null) {
                }
                if (bool.booleanValue()) {
                    if (isOpenLog.booleanValue()) {
                        MVLog.d("\u540c\u6b65:\u4e0d\u4f7f\u7528\u7f13\u5b58");
                    }
                } else if (isOpenLog.booleanValue()) {
                    MVLog.d("\u540c\u6b65:\u7f13\u5b58\u672a\u547d\u4e2d");
                }
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                httpURLConnection.setConnectTimeout(IHost.HOST_NOFITY_REFRESH_LIST);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setRequestMethod(Constants.HTTP_GET);
                httpURLConnection.setDoInput(true);
                if (httpURLConnection.getResponseCode() == 200) {
                    httpURLConnection.disconnect();
                    return null;
                }
                inputStream = httpURLConnection.getInputStream();
                bufferedInputStream = new BufferedInputStream(inputStream);
                bytes = getBytes(inputStream);
                bufferedInputStream.close();
                inputStream.close();
                httpURLConnection.disconnect();
                httpCacher.put(str, bytes, (int) HttpCacher.TIME_DAY);
                return bytes;
            }
        } catch (Exception e3) {
            e2 = e3;
            httpCacher2 = null;
            MVLog.e(new StringBuilder("Cache error").append(e2.getMessage()).toString());
            asBinary = null;
            httpCacher = httpCacher2;
            if (asBinary != null) {
            }
            if (bool.booleanValue()) {
                if (isOpenLog.booleanValue()) {
                    MVLog.d("\u540c\u6b65:\u7f13\u5b58\u672a\u547d\u4e2d");
                }
            } else if (isOpenLog.booleanValue()) {
                MVLog.d("\u540c\u6b65:\u4e0d\u4f7f\u7528\u7f13\u5b58");
            }
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
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
                httpCacher.put(str, bytes, (int) HttpCacher.TIME_DAY);
                return bytes;
            }
            httpURLConnection.disconnect();
            return null;
        }
        if (asBinary != null || !bool.booleanValue()) {
            if (bool.booleanValue()) {
                if (isOpenLog.booleanValue()) {
                    MVLog.d("\u540c\u6b65:\u7f13\u5b58\u672a\u547d\u4e2d");
                }
            } else if (isOpenLog.booleanValue()) {
                MVLog.d("\u540c\u6b65:\u4e0d\u4f7f\u7528\u7f13\u5b58");
            }
            try {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
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
                    try {
                        if (bool.booleanValue() && httpCacher != null) {
                            httpCacher.put(str, bytes, (int) HttpCacher.TIME_DAY);
                        }
                        return bytes;
                    } catch (Exception e22) {
                        Exception exception2 = e22;
                        asBinary = bytes;
                        exception = exception2;
                        MVLog.e(exception.getMessage());
                        return asBinary;
                    }
                }
                httpURLConnection.disconnect();
                return null;
            } catch (Exception e222) {
                exception = e222;
                asBinary = null;
            }
        } else if (!isOpenLog.booleanValue()) {
            return asBinary;
        } else {
            MVLog.d("\u540c\u6b65:\u7f13\u5b58\u547d\u4e2d");
            return asBinary;
        }
    }

    private static byte[] getBytes(InputStream inputStream) throws IOException {
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
