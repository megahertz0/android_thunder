package com.alipay.sdk.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.os.Build.VERSION;
import android.text.TextUtils;
import anet.channel.util.HttpConstant;
import com.alipay.sdk.cons.b;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.sina.weibo.sdk.component.GameManager;
import com.taobao.accs.utl.UtilityImpl;
import java.net.URL;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.params.HttpParams;

public final class a {
    public static final String a = "application/octet-stream;binary/octet-stream";
    public String b;
    private Context c;

    private a(Context context) {
        this(context, null);
    }

    public a(Context context, String str) {
        this.c = context;
        this.b = str;
    }

    private void a(String str) {
        this.b = str;
    }

    private String a() {
        return this.b;
    }

    private URL b() {
        try {
            return new URL(this.b);
        } catch (Exception e) {
            return null;
        }
    }

    public final HttpResponse a(byte[] bArr, List<Header> list) throws Throwable {
        HttpResponse a;
        Object obj = null;
        new StringBuilder("requestUrl : ").append(this.b);
        b a2 = b.a();
        if (a2 != null) {
            try {
                HttpUriRequest httpGet;
                HttpParams params = a2.c.getParams();
                String g;
                if (VERSION.SDK_INT >= 11) {
                    g = g();
                    if (g == null || g.contains("wap")) {
                        URL b = b();
                        if (b != null) {
                            b.a.equalsIgnoreCase(b.getProtocol());
                            Object property = System.getProperty("https.proxyHost");
                            String property2 = System.getProperty("https.proxyPort");
                            if (!TextUtils.isEmpty(property)) {
                                obj = new HttpHost(property, Integer.parseInt(property2));
                            }
                        }
                    }
                } else {
                    NetworkInfo f = f();
                    if (f != null && f.isAvailable() && f.getType() == 0) {
                        g = Proxy.getDefaultHost();
                        int defaultPort = Proxy.getDefaultPort();
                        if (g != null) {
                            obj = new HttpHost(g, defaultPort);
                        }
                    }
                }
                if (obj != null) {
                    params.setParameter("http.route.default-proxy", obj);
                }
                if (bArr == null || bArr.length == 0) {
                    httpGet = new HttpGet(this.b);
                } else {
                    httpGet = new HttpPost(this.b);
                    HttpEntity byteArrayEntity = new ByteArrayEntity(bArr);
                    byteArrayEntity.setContentType(a);
                    ((HttpPost) httpGet).setEntity(byteArrayEntity);
                    httpGet.addHeader("Accept-Charset", GameManager.DEFAULT_CHARSET);
                    httpGet.addHeader(HttpConstant.CONNECTION, "Keep-Alive");
                    httpGet.addHeader("Keep-Alive", "timeout=180, max=100");
                }
                if (list != null) {
                    for (Header header : list) {
                        httpGet.addHeader(header);
                    }
                }
                a = a2.a(httpGet);
                Header[] headers = a.getHeaders("X-Hostname");
                if (!(headers == null || headers.length <= 0 || headers[0] == null)) {
                    a.getHeaders("X-Hostname")[0].toString();
                }
                headers = a.getHeaders("X-ExecuteTime");
                if (!(headers == null || headers.length <= 0 || headers[0] == null)) {
                    a.getHeaders("X-ExecuteTime")[0].toString();
                }
            } catch (Throwable th) {
                if (a2 != null) {
                    try {
                        ClientConnectionManager connectionManager = a2.c.getConnectionManager();
                        if (connectionManager != null) {
                            connectionManager.shutdown();
                            b.b = null;
                        }
                    } catch (Throwable th2) {
                    }
                }
            }
        }
        return a;
    }

    private HttpHost c() {
        String g;
        if (VERSION.SDK_INT >= 11) {
            g = g();
            if (g != null && !g.contains("wap")) {
                return null;
            }
            URL b = b();
            if (b == null) {
                return null;
            }
            b.a.equalsIgnoreCase(b.getProtocol());
            Object property = System.getProperty("https.proxyHost");
            return !TextUtils.isEmpty(property) ? new HttpHost(property, Integer.parseInt(System.getProperty("https.proxyPort"))) : null;
        } else {
            NetworkInfo f = f();
            if (f == null || !f.isAvailable() || f.getType() != 0) {
                return null;
            }
            g = Proxy.getDefaultHost();
            return g != null ? new HttpHost(g, Proxy.getDefaultPort()) : null;
        }
    }

    private HttpHost d() {
        NetworkInfo f = f();
        if (f == null || !f.isAvailable() || f.getType() != 0) {
            return null;
        }
        String defaultHost = Proxy.getDefaultHost();
        return defaultHost != null ? new HttpHost(defaultHost, Proxy.getDefaultPort()) : null;
    }

    private HttpHost e() {
        String g = g();
        if (g != null && !g.contains("wap")) {
            return null;
        }
        URL b = b();
        if (b == null) {
            return null;
        }
        b.a.equalsIgnoreCase(b.getProtocol());
        Object property = System.getProperty("https.proxyHost");
        return !TextUtils.isEmpty(property) ? new HttpHost(property, Integer.parseInt(System.getProperty("https.proxyPort"))) : null;
    }

    private NetworkInfo f() {
        try {
            return ((ConnectivityManager) this.c.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception e) {
            return null;
        }
    }

    private String g() {
        try {
            NetworkInfo f = f();
            if (f == null || !f.isAvailable()) {
                return IXAdSystemUtils.NT_NONE;
            }
            return f.getType() == 1 ? UtilityImpl.NET_TYPE_WIFI : f.getExtraInfo().toLowerCase();
        } catch (Exception e) {
            return IXAdSystemUtils.NT_NONE;
        }
    }
}
