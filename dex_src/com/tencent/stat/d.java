package com.tencent.stat;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import anet.channel.util.HttpConstant;
import com.sina.weibo.sdk.component.GameManager;
import com.tencent.stat.a.e;
import com.tencent.stat.a.f;
import com.tencent.stat.common.StatLogger;
import com.tencent.stat.common.k;
import com.tencent.wxop.stat.common.StatConstants;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.thundersniffer.sniff.SniffingResourceGroup;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import org.android.spdy.SpdyAgent;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

class d {
    private static StatLogger c;
    private static long d;
    private static d e;
    private static Context f;
    DefaultHttpClient a;
    Handler b;

    static {
        c = k.b();
        d = -1;
        e = null;
        f = null;
    }

    private d() {
        this.a = null;
        this.b = null;
        try {
            HandlerThread handlerThread = new HandlerThread("StatDispatcher");
            handlerThread.start();
            d = handlerThread.getId();
            this.b = new Handler(handlerThread.getLooper());
            HttpParams basicHttpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(basicHttpParams, 10000);
            HttpConnectionParams.setSoTimeout(basicHttpParams, 10000);
            this.a = new DefaultHttpClient(basicHttpParams);
            this.a.setKeepAliveStrategy(new e(this));
            if (StatConfig.b() != null) {
                this.a.getParams().setParameter("http.route.default-proxy", StatConfig.b());
            }
        } catch (Object th) {
            c.e(th);
        }
    }

    static Context a() {
        return f;
    }

    static void a(Context context) {
        f = context.getApplicationContext();
    }

    static synchronized d b() {
        d dVar;
        synchronized (d.class) {
            if (e == null) {
                e = new d();
            }
            dVar = e;
        }
        return dVar;
    }

    void a(e eVar, c cVar) {
        b(Arrays.asList(new String[]{eVar.d()}), cVar);
    }

    void a(List<String> list, c cVar) {
        Object obj = null;
        try {
            int i;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            for (int i2 = 0; i2 < list.size(); i2++) {
                stringBuilder.append((String) list.get(i2));
                if (i2 != list.size() - 1) {
                    stringBuilder.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                }
            }
            stringBuilder.append("]");
            String statReportUrl = StatConfig.getStatReportUrl();
            c.i(new StringBuilder("[").append(statReportUrl).append("]Send request(").append(stringBuilder.toString().length()).append("bytes):").append(stringBuilder.toString()).toString());
            HttpPost httpPost = new HttpPost(statReportUrl);
            httpPost.addHeader(HttpConstant.ACCEPT_ENCODING, HttpConstant.GZIP);
            httpPost.setHeader(HttpConstant.CONNECTION, "Keep-Alive");
            httpPost.removeHeaders(HttpConstant.CACHE_CONTROL);
            HttpHost a = k.a(f);
            if (a != null) {
                this.a.getParams().setParameter("http.route.default-proxy", k.a(f));
                httpPost.addHeader("X-Online-Host", StatConstants.MTA_SERVER);
                httpPost.addHeader(HttpConstant.ACCEPT, "*/*");
                httpPost.addHeader("Content-Type", "json");
                i = 1;
            }
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bytes = stringBuilder.toString().getBytes(GameManager.DEFAULT_CHARSET);
            int length = bytes.length;
            if (stringBuilder.length() >= 256) {
                if (a == null) {
                    httpPost.addHeader(HttpConstant.CONTENT_ENCODING, "rc4,gzip");
                } else {
                    httpPost.addHeader("X-Content-Encoding", "rc4,gzip");
                }
                byteArrayOutputStream.write(new byte[4]);
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                gZIPOutputStream.write(bytes);
                gZIPOutputStream.close();
                bytes = byteArrayOutputStream.toByteArray();
                ByteBuffer.wrap(bytes, 0, XZBDevice.DOWNLOAD_LIST_ALL).putInt(length);
                c.d(new StringBuilder("before Gzip:").append(length).append(" bytes, after Gzip:").append(bytes.length).append(" bytes").toString());
            } else if (a == null) {
                httpPost.addHeader(HttpConstant.CONTENT_ENCODING, "rc4");
            } else {
                httpPost.addHeader("X-Content-Encoding", "rc4");
            }
            httpPost.setEntity(new ByteArrayEntity(com.tencent.stat.common.e.a(bytes)));
            HttpResponse execute = this.a.execute(httpPost);
            if (obj != null) {
                this.a.getParams().removeParameter("http.route.default-proxy");
            }
            HttpEntity entity = execute.getEntity();
            i = execute.getStatusLine().getStatusCode();
            long contentLength = entity.getContentLength();
            c.i(new StringBuilder("recv response status code:").append(i).append(", content length:").append(contentLength).toString());
            if (contentLength == 0) {
                EntityUtils.toString(entity);
                if (i != 200) {
                    c.error(new StringBuilder("Server response error code:").append(i).toString());
                } else if (cVar != null) {
                    cVar.a();
                }
            } else if (contentLength > 0) {
                InputStream content = entity.getContent();
                DataInputStream dataInputStream = new DataInputStream(content);
                bytes = new byte[((int) entity.getContentLength())];
                dataInputStream.readFully(bytes);
                content.close();
                dataInputStream.close();
                Header firstHeader = execute.getFirstHeader(HttpConstant.CONTENT_ENCODING);
                if (firstHeader != null) {
                    if (firstHeader.getValue().equalsIgnoreCase("gzip,rc4")) {
                        bytes = com.tencent.stat.common.e.b(k.a(bytes));
                    } else if (firstHeader.getValue().equalsIgnoreCase("rc4,gzip")) {
                        bytes = k.a(com.tencent.stat.common.e.b(bytes));
                    } else if (firstHeader.getValue().equalsIgnoreCase(HttpConstant.GZIP)) {
                        bytes = k.a(bytes);
                    } else if (firstHeader.getValue().equalsIgnoreCase("rc4")) {
                        bytes = com.tencent.stat.common.e.b(bytes);
                    }
                }
                if (i == 200) {
                    try {
                        String str = new String(bytes, GameManager.DEFAULT_CHARSET);
                        c.d(str);
                        JSONObject jSONObject = new JSONObject(str);
                        if (!jSONObject.isNull("cfg")) {
                            StatConfig.a(jSONObject.getJSONObject("cfg"));
                        }
                        if (!(jSONObject.isNull("et") || jSONObject.isNull("st"))) {
                            c.d(new StringBuilder("get mid respone:").append(str).toString());
                            if (jSONObject.getInt("et") == f.b.a()) {
                                i = jSONObject.getInt("st");
                                switch (i) {
                                    case SniffingResourceGroup.PAGETYPE_NONE:
                                    case SpdyAgent.ACCS_TEST_SERVER:
                                        if (!jSONObject.isNull(DeviceInfo.TAG_MID)) {
                                            StatMid.updateDeviceInfo(f, jSONObject.getString(DeviceInfo.TAG_MID));
                                        }
                                        break;
                                    default:
                                        c.e(new StringBuilder("error type for st:").append(i).toString());
                                        break;
                                }
                            }
                        }
                    } catch (Throwable th) {
                        c.i(th.toString());
                    }
                    if (cVar != null) {
                        cVar.a();
                    }
                } else {
                    c.error(new StringBuilder("Server response error code:").append(i).append(", error:").append(new String(bytes, GameManager.DEFAULT_CHARSET)).toString());
                }
                content.close();
            } else {
                EntityUtils.toString(entity);
            }
            byteArrayOutputStream.close();
        } catch (Object th2) {
            c.e(th2);
            if (cVar != null) {
                try {
                    cVar.b();
                } catch (Object th22) {
                    c.e(th22);
                }
            }
        }
    }

    void b(List<String> list, c cVar) {
        if (!list.isEmpty() && this.b != null) {
            this.b.post(new f(this, list, cVar));
        }
    }
}
