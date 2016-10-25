package com.alipay.android.phone.mrpc.core;

import android.net.SSLCertificateSocketFactory;
import android.util.Base64;
import android.util.Log;
import anet.channel.util.HttpConstant;
import com.alipay.sdk.packet.d;
import com.alipay.sdk.util.h;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.taobao.accs.data.Message;
import com.tencent.bugly.crashreport.crash.BuglyBroadcastRecevier;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.security.Security;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.RequestWrapper;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HttpContext;

public final class b implements HttpClient {
    public static long a;
    private static String[] b;
    private static final HttpRequestInterceptor c;
    private final HttpClient d;
    private RuntimeException e;
    private volatile b f;

    private class a implements HttpRequestInterceptor {
        private a() {
        }

        public final void process(HttpRequest httpRequest, HttpContext httpContext) {
            b a = b.this.f;
            if (a != null && Log.isLoggable(b.this, a.b) && (httpRequest instanceof HttpUriRequest)) {
                Log.println(a.b, b.this, b.a((HttpUriRequest) httpRequest));
            }
        }
    }

    private static class b {
        private final String a;
        private final int b;
    }

    static {
        a = 160;
        b = new String[]{"text/", "application/xml", "application/json"};
        c = new c();
    }

    private b(ClientConnectionManager clientConnectionManager, HttpParams httpParams) {
        this.e = new IllegalStateException("AndroidHttpClient created and never closed");
        this.d = new d(this, clientConnectionManager, httpParams);
    }

    public static b a(String str) {
        HttpParams basicHttpParams = new BasicHttpParams();
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUseExpectContinue(basicHttpParams, false);
        HttpConnectionParams.setStaleCheckingEnabled(basicHttpParams, true);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, com.alipay.sdk.data.a.d);
        HttpConnectionParams.setSoTimeout(basicHttpParams, com.tencent.bugly.BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH);
        HttpConnectionParams.setSocketBufferSize(basicHttpParams, Message.FLAG_REQ_BIT2);
        HttpClientParams.setRedirecting(basicHttpParams, true);
        HttpClientParams.setAuthenticating(basicHttpParams, false);
        HttpProtocolParams.setUserAgent(basicHttpParams, str);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme(HttpConstant.HTTP, PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme(com.alipay.sdk.cons.b.a, SSLCertificateSocketFactory.getHttpSocketFactory(com.tencent.bugly.BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH, null), 443));
        ClientConnectionManager threadSafeClientConnManager = new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
        ConnManagerParams.setTimeout(basicHttpParams, BuglyBroadcastRecevier.UPLOADLIMITED);
        ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean(10));
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, R.styleable.AppCompatTheme_buttonBarStyle);
        Security.setProperty("networkaddress.cache.ttl", WeiboAuthException.DEFAULT_AUTH_ERROR_CODE);
        HttpsURLConnection.setDefaultHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
        return new b(threadSafeClientConnManager, basicHttpParams);
    }

    public static InputStream a(HttpEntity httpEntity) {
        InputStream content = httpEntity.getContent();
        if (content == null) {
            return content;
        }
        Header contentEncoding = httpEntity.getContentEncoding();
        if (contentEncoding == null) {
            return content;
        }
        String value = contentEncoding.getValue();
        if (value == null) {
            return content;
        }
        InputStream inputStream;
        if (value.contains(HttpConstant.GZIP)) {
            GZIPInputStream gZIPInputStream = new GZIPInputStream(content);
        } else {
            inputStream = content;
        }
        return inputStream;
    }

    static /* synthetic */ String a(HttpUriRequest httpUriRequest) {
        Object uri;
        HttpEntity entity;
        OutputStream byteArrayOutputStream;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("curl ");
        Header[] allHeaders = httpUriRequest.getAllHeaders();
        int length = allHeaders.length;
        for (int i = 0; i < length; i++) {
            Header header = allHeaders[i];
            if (!header.getName().equals(HttpConstant.AUTHORIZATION) && !header.getName().equals("Cookie")) {
                stringBuilder.append("--header \"");
                stringBuilder.append(header.toString().trim());
                stringBuilder.append("\" ");
            }
        }
        URI uri2 = httpUriRequest.getURI();
        if (httpUriRequest instanceof RequestWrapper) {
            HttpRequest original = ((RequestWrapper) httpUriRequest).getOriginal();
            if (original instanceof HttpUriRequest) {
                uri = ((HttpUriRequest) original).getURI();
                stringBuilder.append(h.f);
                stringBuilder.append(uri);
                stringBuilder.append(h.f);
                if (httpUriRequest instanceof HttpEntityEnclosingRequest) {
                    entity = ((HttpEntityEnclosingRequest) httpUriRequest).getEntity();
                    if (entity != null && entity.isRepeatable()) {
                        if (entity.getContentLength() >= 1024) {
                            byteArrayOutputStream = new ByteArrayOutputStream();
                            entity.writeTo(byteArrayOutputStream);
                            if (b(httpUriRequest)) {
                                stringBuilder.append(" --data-ascii \"").append(byteArrayOutputStream.toString()).append(h.f);
                            } else {
                                stringBuilder.insert(0, new StringBuilder("echo '").append(Base64.encodeToString(byteArrayOutputStream.toByteArray(), XZBDevice.DOWNLOAD_LIST_RECYCLE)).append("' | base64 -d > /tmp/$$.bin; ").toString());
                                stringBuilder.append(" --data-binary @/tmp/$$.bin");
                            }
                        } else {
                            stringBuilder.append(" [TOO MUCH DATA TO INCLUDE]");
                        }
                    }
                }
                return stringBuilder.toString();
            }
        }
        URI uri3 = uri2;
        stringBuilder.append(h.f);
        stringBuilder.append(uri);
        stringBuilder.append(h.f);
        if (httpUriRequest instanceof HttpEntityEnclosingRequest) {
            entity = ((HttpEntityEnclosingRequest) httpUriRequest).getEntity();
            if (entity.getContentLength() >= 1024) {
                stringBuilder.append(" [TOO MUCH DATA TO INCLUDE]");
            } else {
                byteArrayOutputStream = new ByteArrayOutputStream();
                entity.writeTo(byteArrayOutputStream);
                if (b(httpUriRequest)) {
                    stringBuilder.append(" --data-ascii \"").append(byteArrayOutputStream.toString()).append(h.f);
                } else {
                    stringBuilder.insert(0, new StringBuilder("echo '").append(Base64.encodeToString(byteArrayOutputStream.toByteArray(), XZBDevice.DOWNLOAD_LIST_RECYCLE)).append("' | base64 -d > /tmp/$$.bin; ").toString());
                    stringBuilder.append(" --data-binary @/tmp/$$.bin");
                }
            }
        }
        return stringBuilder.toString();
    }

    public static AbstractHttpEntity a(byte[] bArr) {
        if (((long) bArr.length) < a) {
            return new ByteArrayEntity(bArr);
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gZIPOutputStream.write(bArr);
        gZIPOutputStream.close();
        AbstractHttpEntity byteArrayEntity = new ByteArrayEntity(byteArrayOutputStream.toByteArray());
        byteArrayEntity.setContentEncoding(HttpConstant.GZIP);
        new StringBuilder("gzip size:").append(bArr.length).append("->").append(byteArrayEntity.getContentLength());
        return byteArrayEntity;
    }

    public static void a(HttpRequest httpRequest) {
        httpRequest.addHeader(HttpConstant.ACCEPT_ENCODING, HttpConstant.GZIP);
    }

    public static long b(String str) {
        return k.a(str);
    }

    public static void b(HttpRequest httpRequest) {
        httpRequest.addHeader(HttpConstant.CONNECTION, "Keep-Alive");
    }

    private static boolean b(HttpUriRequest httpUriRequest) {
        int i;
        Header[] headers = httpUriRequest.getHeaders("content-encoding");
        if (headers != null) {
            int length = headers.length;
            for (i = 0; i < length; i++) {
                if (HttpConstant.GZIP.equalsIgnoreCase(headers[i].getValue())) {
                    return true;
                }
            }
        }
        Header[] headers2 = httpUriRequest.getHeaders(d.d);
        if (headers2 == null) {
            return true;
        }
        int length2 = headers2.length;
        for (i = 0; i < length2; i++) {
            Header header = headers2[i];
            String[] strArr = b;
            int length3 = strArr.length;
            for (int i2 = 0; i2 < length3; i2++) {
                if (header.getValue().startsWith(strArr[i2])) {
                    return false;
                }
            }
        }
        return true;
    }

    public final void a(HttpRequestRetryHandler httpRequestRetryHandler) {
        ((DefaultHttpClient) this.d).setHttpRequestRetryHandler(httpRequestRetryHandler);
    }

    public final <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler) {
        return this.d.execute(httpHost, httpRequest, responseHandler);
    }

    public final <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) {
        return this.d.execute(httpHost, httpRequest, responseHandler, httpContext);
    }

    public final <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler) {
        return this.d.execute(httpUriRequest, responseHandler);
    }

    public final <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) {
        return this.d.execute(httpUriRequest, responseHandler, httpContext);
    }

    public final HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest) {
        return this.d.execute(httpHost, httpRequest);
    }

    public final HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) {
        return this.d.execute(httpHost, httpRequest, httpContext);
    }

    public final HttpResponse execute(HttpUriRequest httpUriRequest) {
        return this.d.execute(httpUriRequest);
    }

    public final HttpResponse execute(HttpUriRequest httpUriRequest, HttpContext httpContext) {
        return this.d.execute(httpUriRequest, httpContext);
    }

    public final ClientConnectionManager getConnectionManager() {
        return this.d.getConnectionManager();
    }

    public final HttpParams getParams() {
        return this.d.getParams();
    }
}
