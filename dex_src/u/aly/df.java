package u.aly;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.a;
import com.umeng.fb.model.Constants;
import com.umeng.message.util.HttpRequest;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.common.yunbo.XLYunboMassage;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLEncoder;
import org.android.spdy.SpdyRequest;
import org.eclipse.paho.client.mqttv3.MqttTopic;

// compiled from: NetworkHelper.java
public final class df {
    dd a;
    private String b;
    private String c;
    private int d;
    private Context e;

    public df(Context context) {
        this.c = "10.0.0.172";
        this.d = 80;
        this.e = context;
        this.b = a(context);
    }

    public final byte[] a(byte[] bArr) {
        byte[] bArr2 = null;
        for (int i = 0; i < a.g.length; i++) {
            bArr2 = a(bArr, a.g[i]);
            if (bArr2 != null) {
                if (this.a != null) {
                    this.a.g();
                }
                return bArr2;
            }
            if (this.a != null) {
                this.a.h();
            }
        }
        return bArr2;
    }

    private boolean a() {
        if (this.e.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", this.e.getPackageName()) != 0) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.e.getSystemService("connectivity")).getActiveNetworkInfo();
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

    private byte[] a(byte[] bArr, String str) {
        HttpURLConnection httpURLConnection;
        Throwable e;
        try {
            if (this.a != null) {
                this.a.e();
            }
            if (a()) {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection(new Proxy(Type.HTTP, new InetSocketAddress(this.c, this.d)));
            } else {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            }
            try {
                httpURLConnection.setRequestProperty("X-Umeng-UTC", String.valueOf(System.currentTimeMillis()));
                httpURLConnection.setRequestProperty("X-Umeng-Sdk", this.b);
                httpURLConnection.setRequestProperty("Msg-Type", "envelope");
                httpURLConnection.setConnectTimeout(XLYunboMassage.MSG_TASKCREATED);
                httpURLConnection.setReadTimeout(30000);
                httpURLConnection.setRequestMethod(SpdyRequest.POST_METHOD);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setChunkedStreamingMode(0);
                if (Integer.parseInt(VERSION.SDK) < 8) {
                    System.setProperty("http.keepAlive", "false");
                }
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(bArr);
                outputStream.flush();
                outputStream.close();
                if (this.a != null) {
                    this.a.f();
                }
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == 200) {
                    Object headerField = httpURLConnection.getHeaderField(HttpRequest.l);
                    if (TextUtils.isEmpty(headerField) || !headerField.equalsIgnoreCase("application/thrift")) {
                        headerField = null;
                    } else {
                        responseCode = 1;
                    }
                    if (headerField != null) {
                        v.a(new StringBuilder("Send message to ").append(str).toString());
                        InputStream inputStream = httpURLConnection.getInputStream();
                        byte[] b = u.b(inputStream);
                        u.c(inputStream);
                        if (httpURLConnection == null) {
                            return b;
                        }
                        httpURLConnection.disconnect();
                        return b;
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return null;
                }
                v.c(new StringBuilder("status code: ").append(responseCode).toString());
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return null;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            httpURLConnection = null;
            try {
                v.b("IOException,Failed to send message.", e);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return null;
            } catch (Throwable th) {
                e = th;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw e;
            }
        } catch (Throwable th2) {
            e = th2;
            httpURLConnection = null;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw e;
        }
    }

    private static String a(Context context) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Constants.SDK_TYPE);
        stringBuffer.append(MqttTopic.TOPIC_LEVEL_SEPARATOR);
        stringBuffer.append("5.6.4");
        stringBuffer.append(" ");
        try {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(t.q(context));
            stringBuffer2.append(MqttTopic.TOPIC_LEVEL_SEPARATOR);
            stringBuffer2.append(t.b(context));
            stringBuffer2.append(" ");
            stringBuffer2.append(Build.MODEL);
            stringBuffer2.append(MqttTopic.TOPIC_LEVEL_SEPARATOR);
            stringBuffer2.append(VERSION.RELEASE);
            stringBuffer2.append(" ");
            stringBuffer2.append(u.a(AnalyticsConfig.getAppkey(context)));
            stringBuffer.append(URLEncoder.encode(stringBuffer2.toString(), CharsetConvert.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }
}
