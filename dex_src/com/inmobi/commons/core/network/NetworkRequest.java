package com.inmobi.commons.core.network;

import android.util.Base64;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.configs.f;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.c;
import com.inmobi.commons.core.utilities.info.b;
import com.inmobi.commons.core.utilities.uid.d;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashMap;
import java.util.Map;

public class NetworkRequest {
    private static final String d;
    protected Map<String, String> a;
    protected Map<String, String> b;
    public Map<String, String> c;
    private RequestType e;
    private String f;
    private d g;
    private int h;
    private int i;
    private boolean j;
    private boolean k;
    private byte[] l;
    private byte[] m;
    private boolean n;
    private long o;
    private boolean p;

    public enum RequestType {
        GET,
        POST
    }

    static {
        d = NetworkRequest.class.getSimpleName();
    }

    public NetworkRequest(RequestType requestType, String str, boolean z, d dVar) {
        this(requestType, str, z, dVar, false);
    }

    public NetworkRequest(RequestType requestType, String str, boolean z, d dVar, boolean z2) {
        this.a = new HashMap();
        this.b = new HashMap();
        this.c = new HashMap();
        this.h = 60000;
        this.i = 60000;
        this.j = true;
        this.n = true;
        this.o = -1;
        this.e = requestType;
        this.f = str;
        this.k = z;
        this.g = dVar;
        this.a.put("User-Agent", a.d());
        this.p = z2;
    }

    public void a(long j) {
        this.o = j;
    }

    public long f() {
        return this.o;
    }

    public boolean g() {
        return this.o != -1;
    }

    public void a(boolean z) {
        this.n = z;
    }

    public String h() {
        return this.f;
    }

    public void c(Map<String, String> map) {
        this.b.putAll(map);
    }

    public void d(Map<String, String> map) {
        this.c.putAll(map);
    }

    public Map<String, String> i() {
        c.a(this.a);
        return this.a;
    }

    public String j() {
        String str = this.f;
        String k = k();
        if (k == null || k.trim().length() == 0) {
            return str;
        }
        if (!str.contains("?")) {
            str = str + "?";
        }
        if (!(str.endsWith(com.alipay.sdk.sys.a.b) || str.endsWith("?"))) {
            str = str + com.alipay.sdk.sys.a.b;
        }
        return str + k;
    }

    public void a() {
        if (!this.n) {
            return;
        }
        if (this.e == RequestType.GET) {
            a(this.b);
        } else if (this.e == RequestType.POST) {
            a(this.c);
        }
    }

    public String k() {
        c.a(this.b);
        String a = c.a(this.b, com.alipay.sdk.sys.a.b);
        Logger.a(InternalLogLevel.INTERNAL, d, new StringBuilder("Get params: ").append(a).toString());
        return a;
    }

    public String l() {
        c.a(this.c);
        String a = c.a(this.c, com.alipay.sdk.sys.a.b);
        Logger.a(InternalLogLevel.INTERNAL, d, new StringBuilder("Post body url: ").append(h()).toString());
        Logger.a(InternalLogLevel.INTERNAL, d, new StringBuilder("Post body: ").append(a).toString());
        if (!q()) {
            return a;
        }
        a = a(a);
        Logger.a(InternalLogLevel.INTERNAL, d, new StringBuilder("Encrypted post body: ").append(a).toString());
        return a;
    }

    public boolean m() {
        return this.j;
    }

    public void b(boolean z) {
        this.j = z;
    }

    public RequestType n() {
        return this.e;
    }

    public int o() {
        return this.h;
    }

    public void b(int i) {
        this.h = i;
    }

    public void c(int i) {
        this.i = i;
    }

    public int p() {
        return this.i;
    }

    public boolean q() {
        return this.k;
    }

    public boolean r() {
        return this.p;
    }

    private void a(Map<String, String> map) {
        map.putAll(com.inmobi.commons.core.utilities.info.a.a().c());
        map.putAll(b.a());
        map.putAll(com.inmobi.commons.core.utilities.info.d.a());
        if (this.g == null) {
            return;
        }
        if (q()) {
            map.putAll(this.g.a());
        } else {
            map.putAll(this.g.b());
        }
    }

    private String a(String str) {
        byte[] a = com.inmobi.commons.core.utilities.a.b.a((int) XZBDevice.Wait);
        this.l = com.inmobi.commons.core.utilities.a.b.a((int) R.styleable.Toolbar_titleMarginBottom);
        this.m = com.inmobi.commons.core.utilities.a.b.b();
        Map hashMap = new HashMap();
        com.inmobi.commons.core.configs.a fVar = new f();
        com.inmobi.commons.core.configs.b.a().a(fVar, null);
        hashMap.put("sm", com.inmobi.commons.core.utilities.a.b.a(str, this.m, this.l, a, fVar.f(), fVar.e()));
        hashMap.put(IXAdRequestInfo.SN, fVar.g());
        return c.a(hashMap, com.alipay.sdk.sys.a.b);
    }

    protected byte[] a(byte[] bArr) {
        return com.inmobi.commons.core.utilities.a.b.a(Base64.decode(bArr, 0), this.m, this.l);
    }
}
