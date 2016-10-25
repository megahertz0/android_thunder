package com.alipay.android.phone.mrpc.core;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieManager;
import anet.channel.util.HttpConstant;
import com.alipay.sdk.util.h;
import com.xunlei.tdlive.R;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.Callable;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public final class q implements Callable<u> {
    private static final HttpRequestRetryHandler e;
    protected l a;
    protected Context b;
    protected o c;
    String d;
    private HttpUriRequest f;
    private HttpContext g;
    private CookieStore h;
    private CookieManager i;
    private AbstractHttpEntity j;
    private HttpHost k;
    private URL l;
    private int m;
    private boolean n;
    private boolean o;
    private String p;
    private String q;

    static {
        e = new ad();
    }

    public q(l lVar, o oVar) {
        this.g = new BasicHttpContext();
        this.h = new BasicCookieStore();
        this.m = 0;
        this.n = false;
        this.o = false;
        this.p = null;
        this.a = lVar;
        this.b = this.a.a;
        this.c = oVar;
    }

    private static long a(String[] strArr) {
        int i = 0;
        while (i < strArr.length) {
            if ("max-age".equalsIgnoreCase(strArr[i]) && strArr[i + 1] != null) {
                try {
                    return Long.parseLong(strArr[i + 1]);
                } catch (Exception e) {
                }
            }
            i++;
        }
        return 0;
    }

    private static HttpUrlHeader a(HttpResponse httpResponse) {
        HttpUrlHeader httpUrlHeader = new HttpUrlHeader();
        for (Header header : httpResponse.getAllHeaders()) {
            httpUrlHeader.setHead(header.getName(), header.getValue());
        }
        return httpUrlHeader;
    }

    private u a(HttpResponse httpResponse, int i, String str) {
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream;
        String str2 = null;
        new StringBuilder("\u5f00\u59cbhandle\uff0chandleResponse-1,").append(Thread.currentThread().getId());
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null && httpResponse.getStatusLine().getStatusCode() == 200) {
            new StringBuilder("200\uff0c\u5f00\u59cb\u5904\u7406\uff0chandleResponse-2,threadid = ").append(Thread.currentThread().getId());
            try {
                OutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                try {
                    String str3;
                    long currentTimeMillis = System.currentTimeMillis();
                    a(entity, byteArrayOutputStream2);
                    byte[] toByteArray = byteArrayOutputStream2.toByteArray();
                    this.o = false;
                    this.a.c(System.currentTimeMillis() - currentTimeMillis);
                    this.a.a((long) toByteArray.length);
                    new StringBuilder("res:").append(toByteArray.length);
                    u pVar = new p(a(httpResponse), i, str, toByteArray);
                    currentTimeMillis = b(httpResponse);
                    Header contentType = httpResponse.getEntity().getContentType();
                    if (contentType != null) {
                        HashMap a = a(contentType.getValue());
                        str2 = (String) a.get("charset");
                        str3 = (String) a.get("Content-Type");
                    } else {
                        str3 = null;
                    }
                    pVar.b(str3);
                    pVar.a(str2);
                    pVar.a(System.currentTimeMillis());
                    pVar.b(currentTimeMillis);
                    try {
                        byteArrayOutputStream2.close();
                        return pVar;
                    } catch (IOException e) {
                        throw new RuntimeException("ArrayOutputStream close error!", e.getCause());
                    }
                } catch (Throwable th2) {
                    th = th2;
                    OutputStream outputStream = byteArrayOutputStream2;
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                byteArrayOutputStream = null;
                th = th4;
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e2) {
                        throw new RuntimeException("ArrayOutputStream close error!", e2.getCause());
                    }
                }
                throw th;
            }
        } else if (entity != null) {
            return null;
        } else {
            httpResponse.getStatusLine().getStatusCode();
            return null;
        }
    }

    private static HashMap<String, String> a(String str) {
        HashMap<String, String> hashMap = new HashMap();
        String[] split = str.split(h.b);
        int length = split.length;
        for (int i = 0; i < length; i++) {
            String str2 = split[i];
            String[] split2 = str2.indexOf(R.styleable.AppCompatTheme_popupWindowStyle) == -1 ? new String[]{"Content-Type", str2} : str2.split("=");
            hashMap.put(split2[0], split2[1]);
        }
        return hashMap;
    }

    private void a(HttpEntity httpEntity, OutputStream outputStream) {
        Closeable a = b.a(httpEntity);
        long contentLength = httpEntity.getContentLength();
        try {
            byte[] bArr = new byte[2048];
            while (true) {
                int read = a.read(bArr);
                if (read == -1 || this.c.h()) {
                    break;
                }
                outputStream.write(bArr, 0, read);
                if (this.c.f() != null && contentLength > 0) {
                    this.c.f();
                }
            }
            outputStream.flush();
            r.a(a);
        } catch (Exception e) {
            e.getCause();
            throw new IOException(new StringBuilder("HttpWorker Request Error!").append(e.getLocalizedMessage()).toString());
        } catch (Throwable th) {
        }
    }

    private static long b(HttpResponse httpResponse) {
        long j = 0;
        Header firstHeader = httpResponse.getFirstHeader(HttpConstant.CACHE_CONTROL);
        if (firstHeader != null) {
            String[] split = firstHeader.getValue().split("=");
            if (split.length >= 2) {
                try {
                    return a(split);
                } catch (NumberFormatException e) {
                }
            }
        }
        firstHeader = httpResponse.getFirstHeader("Expires");
        return firstHeader != null ? b.b(firstHeader.getValue()) - System.currentTimeMillis() : j;
    }

    private URI b() {
        String a = this.c.a();
        if (this.d != null) {
            a = this.d;
        }
        if (a != null) {
            return new URI(a);
        }
        throw new RuntimeException("url should not be null");
    }

    private HttpUriRequest c() {
        if (this.f != null) {
            return this.f;
        }
        if (this.j == null) {
            byte[] b = this.c.b();
            CharSequence b2 = this.c.b(HttpConstant.GZIP);
            if (b != null) {
                if (TextUtils.equals(b2, "true")) {
                    this.j = b.a(b);
                } else {
                    this.j = new ByteArrayEntity(b);
                }
                this.j.setContentType(this.c.c());
            }
        }
        HttpEntity httpEntity = this.j;
        if (httpEntity != null) {
            HttpUriRequest httpPost = new HttpPost(b());
            httpPost.setEntity(httpEntity);
            this.f = httpPost;
        } else {
            this.f = new HttpGet(b());
        }
        return this.f;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.alipay.android.phone.mrpc.core.u d() {
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.android.phone.mrpc.core.q.d():com.alipay.android.phone.mrpc.core.u");
        /*
        this = this;
        r14 = 6;
        r13 = 3;
        r12 = 2;
        r4 = 1;
        r5 = 0;
    L_0x0005:
        r2 = r15.b;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r3 = "connectivity";
        r2 = r2.getSystemService(r3);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2 = (android.net.ConnectivityManager) r2;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r3 = r2.getAllNetworkInfo();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        if (r3 != 0) goto L_0x0047;
    L_0x0016:
        r2 = r5;
    L_0x0017:
        if (r2 != 0) goto L_0x0060;
    L_0x0019:
        r2 = new com.alipay.android.phone.mrpc.core.HttpException;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r3 = 1;
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r6 = "The network is not available";
        r2.<init>(r3, r6);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        throw r2;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
    L_0x0027:
        r2 = move-exception;
        r15.e();
        r3 = r15.c;
        r3 = r3.f();
        if (r3 == 0) goto L_0x003e;
    L_0x0033:
        r3 = r15.c;
        r3.f();
        r2.getCode();
        r2.getMsg();
    L_0x003e:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        throw r2;
    L_0x0047:
        r6 = r3.length;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2 = r5;
    L_0x0049:
        if (r2 >= r6) goto L_0x04e0;
    L_0x004b:
        r7 = r3[r2];	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        if (r7 == 0) goto L_0x005d;
    L_0x004f:
        r8 = r7.isAvailable();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        if (r8 == 0) goto L_0x005d;
    L_0x0055:
        r7 = r7.isConnectedOrConnecting();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        if (r7 == 0) goto L_0x005d;
    L_0x005b:
        r2 = r4;
        goto L_0x0017;
    L_0x005d:
        r2 = r2 + 1;
        goto L_0x0049;
    L_0x0060:
        r2 = r15.c;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2 = r2.f();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        if (r2 == 0) goto L_0x006d;
    L_0x0068:
        r2 = r15.c;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2.f();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
    L_0x006d:
        r2 = r15.c;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2 = r2.d();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        if (r2 == 0) goto L_0x00a1;
    L_0x0075:
        r3 = r2.isEmpty();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        if (r3 != 0) goto L_0x00a1;
    L_0x007b:
        r3 = r2.iterator();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
    L_0x007f:
        r2 = r3.hasNext();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        if (r2 == 0) goto L_0x00a1;
    L_0x0085:
        r2 = r3.next();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2 = (org.apache.http.Header) r2;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r6 = r15.c();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r6.addHeader(r2);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        goto L_0x007f;
    L_0x0093:
        r2 = move-exception;
        r3 = new java.lang.RuntimeException;
        r4 = "Url parser error!";
        r2 = r2.getCause();
        r3.<init>(r4, r2);
        throw r3;
    L_0x00a1:
        r2 = r15.c();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        com.alipay.android.phone.mrpc.core.b.a(r2);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2 = r15.c();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        com.alipay.android.phone.mrpc.core.b.b(r2);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2 = r15.c();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r3 = "cookie";
        r6 = r15.i();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r7 = r15.c;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r7 = r7.a();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r6 = r6.getCookie(r7);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2.addHeader(r3, r6);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2 = r15.g;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r3 = "http.cookie-store";
        r6 = r15.h;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2.setAttribute(r3, r6);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2 = r15.a;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2 = r2.a();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r3 = e;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2.a(r3);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2 = new java.lang.StringBuilder;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r3 = "By Http/Https to request. operationType=";
        r2.<init>(r3);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r3 = r15.f();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2 = r2.append(r3);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r3 = " url=";
        r2 = r2.append(r3);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r3 = r15.f;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r3 = r3.getURI();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r3 = r3.toString();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2.append(r3);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2 = r15.a;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2 = r2.a();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r8 = r2.getParams();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r9 = "http.route.default-proxy";
        r2 = r15.b;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r3 = 0;
        r10 = "connectivity";
        r2 = r2.getSystemService(r10);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2 = (android.net.ConnectivityManager) r2;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2 = r2.getActiveNetworkInfo();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        if (r2 == 0) goto L_0x04dd;
    L_0x0123:
        r2 = r2.isAvailable();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        if (r2 == 0) goto L_0x04dd;
    L_0x0129:
        r10 = android.net.Proxy.getDefaultHost();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r11 = android.net.Proxy.getDefaultPort();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        if (r10 == 0) goto L_0x04dd;
    L_0x0133:
        r2 = new org.apache.http.HttpHost;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2.<init>(r10, r11);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
    L_0x0138:
        if (r2 == 0) goto L_0x0150;
    L_0x013a:
        r3 = r2.getHostName();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r10 = "127.0.0.1";
        r3 = android.text.TextUtils.equals(r3, r10);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        if (r3 == 0) goto L_0x0150;
    L_0x0147:
        r3 = r2.getPort();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r10 = 8087; // 0x1f97 float:1.1332E-41 double:3.9955E-320;
        if (r3 != r10) goto L_0x0150;
    L_0x014f:
        r2 = 0;
    L_0x0150:
        r8.setParameter(r9, r2);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2 = r15.k;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        if (r2 == 0) goto L_0x0238;
    L_0x0157:
        r2 = r15.k;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
    L_0x0159:
        r3 = r15.g();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r8 = 80;
        if (r3 != r8) goto L_0x016e;
    L_0x0161:
        r2 = new org.apache.http.HttpHost;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r3 = r15.h();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r3 = r3.getHost();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2.<init>(r3);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
    L_0x016e:
        r3 = r15.a;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r3 = r3.a();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r8 = r15.f;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r9 = r15.g;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r3 = r3.execute(r2, r8, r9);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r8 = java.lang.System.currentTimeMillis();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2 = r15.a;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r6 = r8 - r6;
        r2.b(r6);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2 = r15.h;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2 = r2.getCookies();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r6 = r15.c;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r6 = r6.e();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        if (r6 == 0) goto L_0x019c;
    L_0x0195:
        r6 = r15.i();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r6.removeAllCookie();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
    L_0x019c:
        r6 = r2.isEmpty();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        if (r6 != 0) goto L_0x0257;
    L_0x01a2:
        r6 = r2.iterator();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
    L_0x01a6:
        r2 = r6.hasNext();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        if (r2 == 0) goto L_0x0257;
    L_0x01ac:
        r2 = r6.next();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2 = (org.apache.http.cookie.Cookie) r2;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r7 = r2.getDomain();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        if (r7 == 0) goto L_0x01a6;
    L_0x01b8:
        r7 = new java.lang.StringBuilder;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r7.<init>();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r8 = r2.getName();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r7 = r7.append(r8);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r8 = "=";
        r7 = r7.append(r8);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r8 = r2.getValue();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r7 = r7.append(r8);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r8 = "; domain=";
        r7 = r7.append(r8);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r8 = r2.getDomain();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r7 = r7.append(r8);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2 = r2.isSecure();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        if (r2 == 0) goto L_0x0253;
    L_0x01e9:
        r2 = "; Secure";
    L_0x01ec:
        r2 = r7.append(r2);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2 = r2.toString();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r7 = r15.i();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r8 = r15.c;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r8 = r8.a();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r7.setCookie(r8, r2);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2 = android.webkit.CookieSyncManager.getInstance();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2.sync();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        goto L_0x01a6;
    L_0x0209:
        r2 = move-exception;
        r15.e();
        r3 = r15.c;
        r3 = r3.f();
        if (r3 == 0) goto L_0x0222;
    L_0x0215:
        r3 = r15.c;
        r3.f();
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x0222:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r12);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x0238:
        r2 = r15.h();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r3 = new org.apache.http.HttpHost;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r8 = r2.getHost();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r9 = r15.g();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2 = r2.getProtocol();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r3.<init>(r8, r9, r2);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r15.k = r3;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2 = r15.k;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        goto L_0x0159;
    L_0x0253:
        r2 = "";
        goto L_0x01ec;
    L_0x0257:
        r2 = r3.getStatusLine();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r6 = r2.getStatusCode();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2 = r3.getStatusLine();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r7 = r2.getReasonPhrase();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r6 == r2) goto L_0x02bd;
    L_0x026b:
        r2 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        if (r6 != r2) goto L_0x02bb;
    L_0x026f:
        r2 = r4;
    L_0x0270:
        if (r2 != 0) goto L_0x02bd;
    L_0x0272:
        r2 = new com.alipay.android.phone.mrpc.core.HttpException;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r6 = r3.getStatusLine();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r6 = r6.getStatusCode();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r3 = r3.getStatusLine();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r3 = r3.getReasonPhrase();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2.<init>(r6, r3);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        throw r2;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
    L_0x028c:
        r2 = move-exception;
        r15.e();
        r3 = r15.c;
        r3 = r3.f();
        if (r3 == 0) goto L_0x02a5;
    L_0x0298:
        r3 = r15.c;
        r3.f();
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x02a5:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r12);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x02bb:
        r2 = r5;
        goto L_0x0270;
    L_0x02bd:
        r3 = r15.a(r3, r6, r7);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r6 = -1;
        if (r3 == 0) goto L_0x02d1;
    L_0x02c5:
        r2 = r3.b();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        if (r2 == 0) goto L_0x02d1;
    L_0x02cb:
        r2 = r3.b();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2 = r2.length;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r6 = (long) r2;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
    L_0x02d1:
        r8 = -1;
        r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r2 != 0) goto L_0x02ed;
    L_0x02d7:
        r2 = r3 instanceof com.alipay.android.phone.mrpc.core.p;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        if (r2 == 0) goto L_0x02ed;
    L_0x02db:
        r0 = r3;
        r0 = (com.alipay.android.phone.mrpc.core.p) r0;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2 = r0;
        r2 = r2.a();	 Catch:{ Exception -> 0x04da, HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d }
        r6 = "Content-Length";
        r2 = r2.getHead(r6);	 Catch:{ Exception -> 0x04da, HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d }
        java.lang.Long.parseLong(r2);	 Catch:{ Exception -> 0x04da, HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d }
    L_0x02ed:
        r2 = r15.c;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2 = r2.a();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        if (r2 == 0) goto L_0x0316;
    L_0x02f5:
        r6 = r15.f();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r6 = android.text.TextUtils.isEmpty(r6);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        if (r6 != 0) goto L_0x0316;
    L_0x02ff:
        r6 = new java.lang.StringBuilder;	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r6.<init>();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2 = r6.append(r2);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r6 = "#";
        r2 = r2.append(r6);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r6 = r15.f();	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
        r2.append(r6);	 Catch:{ HttpException -> 0x0027, URISyntaxException -> 0x0093, SSLHandshakeException -> 0x0209, SSLPeerUnverifiedException -> 0x028c, SSLException -> 0x0317, ConnectionPoolTimeoutException -> 0x0346, ConnectTimeoutException -> 0x0375, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d4, HttpHostConnectException -> 0x0404, UnknownHostException -> 0x042d, IOException -> 0x045e, NullPointerException -> 0x048d, Exception -> 0x04b3 }
    L_0x0316:
        return r3;
    L_0x0317:
        r2 = move-exception;
        r15.e();
        r3 = r15.c;
        r3 = r3.f();
        if (r3 == 0) goto L_0x0330;
    L_0x0323:
        r3 = r15.c;
        r3.f();
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x0330:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r14);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x0346:
        r2 = move-exception;
        r15.e();
        r3 = r15.c;
        r3 = r3.f();
        if (r3 == 0) goto L_0x035f;
    L_0x0352:
        r3 = r15.c;
        r3.f();
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x035f:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r13);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x0375:
        r2 = move-exception;
        r15.e();
        r3 = r15.c;
        r3 = r3.f();
        if (r3 == 0) goto L_0x038e;
    L_0x0381:
        r3 = r15.c;
        r3.f();
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x038e:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r13);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x03a4:
        r2 = move-exception;
        r15.e();
        r3 = r15.c;
        r3 = r3.f();
        if (r3 == 0) goto L_0x03bd;
    L_0x03b0:
        r3 = r15.c;
        r3.f();
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x03bd:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = 4;
        r4 = java.lang.Integer.valueOf(r4);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x03d4:
        r2 = move-exception;
        r15.e();
        r3 = r15.c;
        r3 = r3.f();
        if (r3 == 0) goto L_0x03ed;
    L_0x03e0:
        r3 = r15.c;
        r3.f();
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x03ed:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = 5;
        r4 = java.lang.Integer.valueOf(r4);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x0404:
        r2 = move-exception;
        r15.e();
        r3 = r15.c;
        r3 = r3.f();
        if (r3 == 0) goto L_0x041d;
    L_0x0410:
        r3 = r15.c;
        r3.f();
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x041d:
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = 8;
        r4 = java.lang.Integer.valueOf(r4);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x042d:
        r2 = move-exception;
        r15.e();
        r3 = r15.c;
        r3 = r3.f();
        if (r3 == 0) goto L_0x0446;
    L_0x0439:
        r3 = r15.c;
        r3.f();
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x0446:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = 9;
        r4 = java.lang.Integer.valueOf(r4);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x045e:
        r2 = move-exception;
        r15.e();
        r3 = r15.c;
        r3 = r3.f();
        if (r3 == 0) goto L_0x0477;
    L_0x046a:
        r3 = r15.c;
        r3.f();
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x0477:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r14);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x048d:
        r2 = move-exception;
        r15.e();
        r3 = r15.m;
        if (r3 > 0) goto L_0x049d;
    L_0x0495:
        r2 = r15.m;
        r2 = r2 + 1;
        r15.m = r2;
        goto L_0x0005;
    L_0x049d:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r5);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x04b3:
        r2 = move-exception;
        r15.e();
        r3 = r15.c;
        r3 = r3.f();
        if (r3 == 0) goto L_0x04cc;
    L_0x04bf:
        r3 = r15.c;
        r3.f();
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x04cc:
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r5);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x04da:
        r2 = move-exception;
        goto L_0x02ed;
    L_0x04dd:
        r2 = r3;
        goto L_0x0138;
    L_0x04e0:
        r2 = r5;
        goto L_0x0017;
        */
    }

    private void e() {
        if (this.f != null) {
            this.f.abort();
        }
    }

    private String f() {
        if (!TextUtils.isEmpty(this.q)) {
            return this.q;
        }
        this.q = this.c.b("operationType");
        return this.q;
    }

    private int g() {
        URL h = h();
        return h.getPort() == -1 ? h.getDefaultPort() : h.getPort();
    }

    private URL h() {
        if (this.l != null) {
            return this.l;
        }
        this.l = new URL(this.c.a());
        return this.l;
    }

    private CookieManager i() {
        if (this.i != null) {
            return this.i;
        }
        this.i = CookieManager.getInstance();
        return this.i;
    }

    public final o a() {
        return this.c;
    }

    public final /* synthetic */ Object call() {
        return d();
    }
}
