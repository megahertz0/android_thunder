package com.umeng.message.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import com.umeng.common.UmLog;
import com.umeng.common.UmengMessageDeviceConfig;
import com.umeng.message.PushAgent;
import com.umeng.message.proguard.g;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.common.yunbo.XLYunboMassage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.android.spdy.SpdyRequest;
import org.eclipse.paho.client.mqttv3.MqttTopic;

// compiled from: NetworkHelper.java
public class b {
    public static final int a = 1;
    public static final int b = 2;
    public static final int c = 3;
    private static final String i;
    private final int d;
    private String e;
    private String f;
    private int g;
    private Context h;

    static {
        i = b.class.getName();
    }

    public b(Context context) {
        this.d = 1;
        this.f = "10.0.0.172";
        this.g = 80;
        this.h = context;
        this.e = a(context);
    }

    private boolean a() {
        if (this.h.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", this.h.getPackageName()) != 0) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.h.getSystemService("connectivity")).getActiveNetworkInfo();
            if (!(activeNetworkInfo == null || activeNetworkInfo.getType() == 1)) {
                String extraInfo = activeNetworkInfo.getExtraInfo();
                if (extraInfo != null && (extraInfo.equals("cmwap") || extraInfo.equals("3gwap") || extraInfo.equals("uniwap"))) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public byte[] a(byte[] bArr, String str) {
        Throwable th;
        HttpURLConnection httpURLConnection = null;
        try {
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection2.setRequestMethod(SpdyRequest.POST_METHOD);
                httpURLConnection2.setReadTimeout(XLYunboMassage.MSG_TASKCREATED);
                httpURLConnection2.setConnectTimeout(XLYunboMassage.MSG_TASKCREATED);
                httpURLConnection2.setRequestProperty("Msg-Type", "envelope");
                httpURLConnection2.setDoOutput(true);
                httpURLConnection2.setChunkedStreamingMode(0);
                OutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection2.getOutputStream());
                bufferedOutputStream.write(bArr);
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                InputStream bufferedInputStream = new BufferedInputStream(httpURLConnection2.getInputStream());
                byte[] a = g.a(bufferedInputStream);
                try {
                    g.b(bufferedInputStream);
                    if (httpURLConnection2 == null) {
                        return a;
                    }
                    httpURLConnection2.disconnect();
                    return a;
                } catch (Exception e) {
                    Exception exception = e;
                    httpURLConnection = httpURLConnection2;
                    r0 = a;
                    r1 = exception;
                } catch (Throwable th2) {
                    httpURLConnection = httpURLConnection2;
                    th = th2;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                r1 = e2;
                HttpURLConnection httpURLConnection3 = httpURLConnection2;
                r0 = null;
                httpURLConnection = httpURLConnection3;
            } catch (Throwable th22) {
                httpURLConnection = httpURLConnection2;
                th = th22;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Exception e3) {
            r1 = e3;
            r0 = null;
            try {
                Exception exception2;
                UmLog.e(i, new StringBuilder("sendMessage:").append(exception2.getMessage()).toString());
                exception2.printStackTrace();
                if (httpURLConnection == null) {
                    byte[] bArr2;
                    return bArr2;
                }
                httpURLConnection.disconnect();
                return bArr2;
            } catch (Throwable th3) {
                th = th3;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        }
    }

    private String a(Context context) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("3.0.1");
        stringBuffer.append(MqttTopic.TOPIC_LEVEL_SEPARATOR);
        stringBuffer.append("3.0.1");
        stringBuffer.append(" ");
        try {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(UmengMessageDeviceConfig.getApplicationLable(context));
            stringBuffer2.append(MqttTopic.TOPIC_LEVEL_SEPARATOR);
            stringBuffer2.append(UmengMessageDeviceConfig.getAppVersionName(context));
            stringBuffer2.append(" ");
            stringBuffer2.append(Build.MODEL);
            stringBuffer2.append(MqttTopic.TOPIC_LEVEL_SEPARATOR);
            stringBuffer2.append(VERSION.RELEASE);
            stringBuffer2.append(" ");
            stringBuffer2.append(g.a(PushAgent.getInstance(context).getMessageAppkey()));
            stringBuffer.append(URLEncoder.encode(stringBuffer2.toString(), CharsetConvert.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }
}
