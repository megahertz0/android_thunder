package com.xunlei.tdlive.util;

import com.a.a.c.c;
import com.android.volley.Request;
import com.android.volley.a;
import com.android.volley.l;
import com.android.volley.n;
import com.android.volley.r;
import com.android.volley.t;
import com.android.volley.u;
import com.android.volley.w;
import com.umeng.message.util.HttpRequest;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.tdlive.util.f.e;
import com.xunlei.tdlive.util.f.f;
import com.xunlei.tdlive.util.f.h;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.Map;
import org.apache.http.HttpEntity;

// compiled from: HttpUtils.java
private class f$d<T> extends Request<h<T>> {
    final /* synthetic */ f a;
    private String b;
    private f c;
    private e<T> d;
    private HttpEntity e;

    protected /* synthetic */ void deliverResponse(Object obj) {
        a((f$h) obj);
    }

    public f$d(f fVar, int i, f fVar2, String str, e<T> eVar) {
        this.a = fVar;
        super(i, str, eVar);
        this.d = null;
        this.e = null;
        this.c = fVar2;
        this.d = eVar;
        this.e = f.a(fVar2).a();
    }

    public String getUrl() {
        return (this.b == null || this.b.length() <= 0) ? super.getUrl() : this.b;
    }

    public byte[] getBody() throws a {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (this.e != null) {
            try {
                this.e.writeTo(byteArrayOutputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public Map<String, String> getHeaders() throws a {
        return f.b(this.c);
    }

    public String getBodyContentType() {
        return this.e.getContentType().getValue();
    }

    protected r<h<T>> parseNetworkResponse(l lVar) {
        try {
            f$h com_xunlei_tdlive_util_f_h = new f$h();
            String a = com.android.volley.toolbox.f.a(lVar.c, CharsetConvert.ISO_8859_1);
            if (a.equals(CharsetConvert.ISO_8859_1)) {
                a = CharsetConvert.UTF_8;
            }
            com_xunlei_tdlive_util_f_h.a = this.d.onParseResult(new String(lVar.b, a));
            com_xunlei_tdlive_util_f_h.b = lVar.a;
            com_xunlei_tdlive_util_f_h.c = "success";
            return r.a(com_xunlei_tdlive_util_f_h, com.android.volley.toolbox.f.a(lVar));
        } catch (UnsupportedEncodingException e) {
            return r.a(new n(lVar));
        } catch (Exception e2) {
            return r.a(new n(lVar));
        }
    }

    protected void a(h<T> hVar) {
        try {
            this.d.onResponse(hVar);
        } catch (Exception e) {
        }
    }

    public void deliverError(w wVar) {
        if ((wVar.getCause() instanceof IOException) || (wVar.getCause() instanceof UnknownHostException)) {
            t retryPolicy = getRetryPolicy();
            if (retryPolicy != null) {
                try {
                    retryPolicy.a(wVar);
                    c.a(null).a().a(this);
                    return;
                } catch (Exception e) {
                }
            }
        } else if ((wVar instanceof u) && wVar.a != null) {
            if ((wVar.a.a == 301 || wVar.a.a == 302) && wVar.a.c != null) {
                String str = (String) wVar.a.c.get("Set-Cookie");
                this.b = (String) wVar.a.c.get(HttpRequest.r);
                if (this.b != null) {
                    if (str != null) {
                        this.c.a("Cookie", str);
                    }
                    c.a(null).a().a(this);
                    return;
                }
            }
        }
        super.deliverError(wVar);
    }
}
