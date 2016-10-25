package com.xunlei.downloadprovider.b.b;

import com.umeng.message.util.HttpRequest;
import com.xunlei.common.register.XLRegErrorCode;
import com.xunlei.download.proguard.ai;
import com.xunlei.downloadprovider.b.c.e;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.Hashtable;
import org.android.spdy.SpdyProtocol;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HttpContext;

// compiled from: BpClientUrlLoader.java
public final class d extends e implements RedirectHandler {
    public b a;
    a b;
    public d c;
    public RedirectHandler d;
    public String e;
    int f;
    int g;
    Hashtable<String, String> h;
    private c j;
    private Header[] k;
    private boolean l;
    private int m;
    private Thread n;

    // compiled from: BpClientUrlLoader.java
    public static interface a {
        void a(int i, byte[] bArr);
    }

    // compiled from: BpClientUrlLoader.java
    public static interface b {
        void a(int i);
    }

    // compiled from: BpClientUrlLoader.java
    public static interface c {
    }

    // compiled from: BpClientUrlLoader.java
    public static interface d {
        void a(int i, Header[] headerArr, d dVar);
    }

    public d(String str) {
        this.l = false;
        this.n = null;
        this.e = str;
        this.f = 60000;
        this.g = 300000;
        this.l = false;
    }

    public final String a(String str) {
        if (this.k == null) {
            return null;
        }
        for (int i = 0; i < this.k.length; i++) {
            if (str.equalsIgnoreCase(this.k[i].getName())) {
                return this.k[i].getValue();
            }
        }
        return null;
    }

    public final void run() {
        try {
            this.n = Thread.currentThread();
            new StringBuilder("mUrl=").append(this.e);
            a();
        } catch (InterruptedException e) {
        }
    }

    public final void cancel() {
        super.cancel();
        this.j = null;
        this.c = null;
        this.a = null;
        this.b = null;
        if (this.n != null && !this.n.isInterrupted()) {
            this.n.interrupt();
            this.n = null;
        }
    }

    public final URI getLocationURI(HttpResponse httpResponse, HttpContext httpContext) throws ProtocolException {
        Header[] headers = httpResponse.getHeaders(HttpRequest.r);
        if (headers == null || headers.length == 0) {
            headers = httpResponse.getHeaders("location");
        }
        return (headers == null || headers.length == 0) ? null : URI.create(headers[0].getValue());
    }

    public final boolean isRedirectRequested(HttpResponse httpResponse, HttpContext httpContext) {
        switch (httpResponse.getStatusLine().getStatusCode()) {
            case XLRegErrorCode.REG_FORMAT_ERR_PARAM:
            case 302:
            case 303:
            case ai.a:
                return true;
            default:
                return false;
        }
    }

    private void a() throws InterruptedException {
        InputStream inputStream;
        InputStream inputStream2;
        Throwable th;
        Throwable th2;
        ByteArrayOutputStream byteArrayOutputStream = null;
        if (!this.l) {
            ByteArrayOutputStream byteArrayOutputStream2;
            try {
                DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
                HttpConnectionParams.setConnectionTimeout(defaultHttpClient.getParams(), this.f);
                HttpConnectionParams.setSoTimeout(defaultHttpClient.getParams(), this.g);
                if (this.d != null) {
                    defaultHttpClient.setRedirectHandler(this.d);
                }
                HttpUriRequest httpGet = new HttpGet(this.e);
                if (this.h != null) {
                    for (String str : this.h.keySet()) {
                        httpGet.addHeader(str, (String) this.h.get(str));
                    }
                }
                HttpResponse execute = defaultHttpClient.execute(httpGet);
                if (!Thread.interrupted()) {
                    int statusCode = execute.getStatusLine().getStatusCode();
                    this.k = execute.getAllHeaders();
                    if (this.c != null) {
                        this.c.a(statusCode, this.k, this);
                    }
                    if (!(statusCode == 200 || this.a == null)) {
                        this.a.a(statusCode);
                    }
                    if (statusCode != 200 || Thread.interrupted()) {
                        byteArrayOutputStream2 = null;
                        inputStream = null;
                    } else {
                        inputStream = execute.getEntity().getContent();
                        try {
                            byteArrayOutputStream2 = new ByteArrayOutputStream();
                            int i = SpdyProtocol.SLIGHTSSL_L7E;
                            try {
                                byte[] bArr = new byte[16384];
                                if (!Thread.interrupted()) {
                                    i = inputStream.read(bArr);
                                    while (-1 != i && !Thread.interrupted()) {
                                        byteArrayOutputStream2.write(bArr, 0, i);
                                        i = inputStream.read(bArr);
                                        if (this.m == 0) {
                                            Thread.sleep(200);
                                        }
                                    }
                                }
                                if (this.b != null) {
                                    this.b.a(statusCode, byteArrayOutputStream2.toByteArray());
                                }
                            } catch (Exception e) {
                                inputStream2 = inputStream;
                                if (this.a != null) {
                                    this.a.a(-1);
                                }
                                if (inputStream2 != null) {
                                    inputStream2.close();
                                }
                                if (byteArrayOutputStream2 == null) {
                                    byteArrayOutputStream2.close();
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                byteArrayOutputStream = byteArrayOutputStream2;
                                th2 = th;
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                                if (byteArrayOutputStream != null) {
                                    byteArrayOutputStream.close();
                                }
                                throw th2;
                            }
                        } catch (Exception e2) {
                            byteArrayOutputStream2 = byteArrayOutputStream;
                            inputStream2 = inputStream;
                            if (this.a != null) {
                                this.a.a(-1);
                            }
                            if (inputStream2 != null) {
                                inputStream2.close();
                            }
                            if (byteArrayOutputStream2 == null) {
                                byteArrayOutputStream2.close();
                            }
                        } catch (Throwable th4) {
                            th2 = th4;
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (byteArrayOutputStream != null) {
                                byteArrayOutputStream.close();
                            }
                            throw th2;
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e3) {
                        }
                    }
                    if (byteArrayOutputStream2 != null) {
                        try {
                            byteArrayOutputStream2.close();
                        } catch (Exception e4) {
                        }
                    }
                }
            } catch (Exception e5) {
                byteArrayOutputStream2 = null;
                try {
                    if (this.a != null) {
                        this.a.a(-1);
                    }
                    if (inputStream2 != null) {
                        try {
                            inputStream2.close();
                        } catch (Exception e6) {
                        }
                    }
                    if (byteArrayOutputStream2 == null) {
                        try {
                            byteArrayOutputStream2.close();
                        } catch (Exception e7) {
                        }
                    }
                } catch (Throwable th5) {
                    th = th5;
                    inputStream = inputStream2;
                    byteArrayOutputStream = byteArrayOutputStream2;
                    th2 = th;
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    throw th2;
                }
            } catch (Throwable th6) {
                th2 = th6;
                inputStream = null;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e8) {
                    }
                }
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception e9) {
                    }
                }
                throw th2;
            }
        }
    }
}
