package com.xunlei.downloadprovidercommon.b.a;

import android.text.TextUtils;
import com.android.volley.Request;
import com.android.volley.f;
import com.android.volley.l;
import com.android.volley.r;
import com.android.volley.r.a;
import com.android.volley.r.b;
import com.tencent.connect.common.Constants;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import org.android.spdy.SpdyAgent;

// compiled from: SigRequest.java
public abstract class c<T> extends Request<T> {
    protected static final String a;
    protected static final String b;
    protected com.xunlei.downloadprovidercommon.b.b.c c;
    private final b<T> d;
    private final HashMap<String, String> e;

    protected abstract r<T> parseNetworkResponse(l lVar);

    static {
        a = String.format("application/x-www-form-urlencoded; charset=%s", new Object[]{"utf-8"});
        b = String.format("application/json; charset=%s", new Object[]{"utf-8"});
    }

    public c(int i, String str, String str2, b<T> bVar, a aVar) {
        String str3 = com.umeng.a.d;
        switch (i) {
            case SpdyAgent.ACCS_TEST_SERVER:
                str3 = Constants.HTTP_GET;
                break;
            case SpdyAgent.ACCS_ONLINE_SERVER:
                str3 = Constants.HTTP_POST;
                break;
        }
        this(i, new com.xunlei.downloadprovidercommon.b.b.c(str3, str, str2), bVar, aVar);
    }

    private c(int i, com.xunlei.downloadprovidercommon.b.b.c cVar, b<T> bVar, a aVar) {
        super(i, cVar.a(), aVar);
        this.e = new d(this);
        this.d = bVar;
        this.c = cVar;
        setShouldCache(false);
        setRetryPolicy(new f(5000, 1, 1.0f));
    }

    public Map<String, String> getHeaders() throws com.android.volley.a {
        return this.e;
    }

    public String getPostBodyContentType() {
        return getBodyContentType();
    }

    public byte[] getPostBody() throws com.android.volley.a {
        return getBody();
    }

    public String getBodyContentType() {
        return a;
    }

    public byte[] getBody() throws com.android.volley.a {
        if (!(this.c == null || getMethod() == 0)) {
            String c = this.c.c();
            if (TextUtils.isEmpty(c)) {
                c = this.c.b();
            }
            try {
                return c.getBytes("utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    protected void deliverResponse(T t) {
        if (this.d != null) {
            this.d.onResponse(t);
        }
    }
}
