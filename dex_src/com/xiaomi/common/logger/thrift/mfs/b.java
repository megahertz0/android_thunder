package com.xiaomi.common.logger.thrift.mfs;

import com.sina.weibo.sdk.component.WidgetRequestParam;
import com.tencent.open.GameAppOperation;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.editorpage.ShareActivity;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.android.spdy.SpdyAgent;
import org.apache.thrift.meta_data.f;
import org.apache.thrift.meta_data.g;
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.i;
import org.apache.thrift.protocol.j;
import org.apache.thrift.protocol.k;

public class b implements Serializable, Cloneable, org.apache.thrift.b<b, a> {
    public static final Map<a, org.apache.thrift.meta_data.b> a;
    private static final k b;
    private static final c c;
    private static final c d;
    private static final c e;
    private static final c f;
    private static final c g;
    private static final c h;
    private static final c i;
    private static final c j;
    private static final c k;
    private static final c l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private e r;
    private Set<a> s;
    private String t;
    private String u;
    private String v;

    static {
        b = new k("HttpApi");
        c = new c(WidgetRequestParam.REQ_PARAM_COMMENT_CATEGORY, (byte) 11, (short) 1);
        d = new c("uuid", (byte) 11, (short) 2);
        e = new c(GameAppOperation.QQFAV_DATALINE_VERSION, (byte) 11, (short) 3);
        f = new c("network", (byte) 11, (short) 4);
        g = new c("client_ip", (byte) 11, (short) 5);
        h = new c(ShareActivity.KEY_LOCATION, (byte) 12, (short) 6);
        i = new c("host_info", (byte) 14, (short) 7);
        j = new c("version_type", (byte) 11, (short) 8);
        k = new c(GameAppOperation.QQFAV_DATALINE_APPNAME, (byte) 11, (short) 9);
        l = new c(org.android.agoo.common.b.PROPERTY_APP_VERSION, (byte) 11, (short) 10);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.a, new org.apache.thrift.meta_data.b(WidgetRequestParam.REQ_PARAM_COMMENT_CATEGORY, (byte) 1, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.b, new org.apache.thrift.meta_data.b("uuid", (byte) 1, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.c, new org.apache.thrift.meta_data.b(GameAppOperation.QQFAV_DATALINE_VERSION, (byte) 1, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.d, new org.apache.thrift.meta_data.b("network", (byte) 1, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.e, new org.apache.thrift.meta_data.b("client_ip", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.f, new org.apache.thrift.meta_data.b(ShareActivity.KEY_LOCATION, (byte) 2, new g((byte) 12, e.class)));
        enumMap.put(a.g, new org.apache.thrift.meta_data.b("host_info", (byte) 2, new f((byte) 14, new g((byte) 12, a.class))));
        enumMap.put(a.h, new org.apache.thrift.meta_data.b("version_type", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.i, new org.apache.thrift.meta_data.b(GameAppOperation.QQFAV_DATALINE_APPNAME, (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.j, new org.apache.thrift.meta_data.b(org.android.agoo.common.b.PROPERTY_APP_VERSION, (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        a = Collections.unmodifiableMap(enumMap);
        org.apache.thrift.meta_data.b.a(b.class, a);
    }

    public b() {
        this.m = a.d;
        this.t = a.d;
        this.u = a.d;
        this.v = a.d;
    }

    public b a(e eVar) {
        this.r = eVar;
        return this;
    }

    public b a(String str) {
        this.m = str;
        return this;
    }

    public void a(a aVar) {
        if (this.s == null) {
            this.s = new HashSet();
        }
        this.s.add(aVar);
    }

    public void a(org.apache.thrift.protocol.f fVar) {
        fVar.g();
        while (true) {
            c i = fVar.i();
            if (i.b != null) {
                switch (i.c) {
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        if (i.b == (byte) 11) {
                            this.m = fVar.w();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        if (i.b == (byte) 11) {
                            this.n = fVar.w();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case XZBDevice.DOWNLOAD_LIST_FAILED:
                        if (i.b == (byte) 11) {
                            this.o = fVar.w();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case XZBDevice.DOWNLOAD_LIST_ALL:
                        if (i.b == (byte) 11) {
                            this.p = fVar.w();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                        if (i.b == (byte) 11) {
                            this.q = fVar.w();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case R.styleable.Toolbar_contentInsetEnd:
                        if (i.b == 12) {
                            this.r = new e();
                            this.r.a(fVar);
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case R.styleable.Toolbar_contentInsetLeft:
                        if (i.b == 14) {
                            j o = fVar.o();
                            this.s = new HashSet(o.b * 2);
                            for (int i2 = 0; i2 < o.b; i2++) {
                                a aVar = new a();
                                aVar.a(fVar);
                                this.s.add(aVar);
                            }
                            fVar.p();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case XZBDevice.Wait:
                        if (i.b == (byte) 11) {
                            this.t = fVar.w();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case XZBDevice.Pause:
                        if (i.b == (byte) 11) {
                            this.u = fVar.w();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case XZBDevice.Stop:
                        if (i.b == (byte) 11) {
                            this.v = fVar.w();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    default:
                        i.a(fVar, i.b);
                        break;
                }
                fVar.j();
            } else {
                fVar.h();
                l();
                return;
            }
        }
    }

    public boolean a() {
        return this.m != null;
    }

    public boolean a(b bVar) {
        if (bVar == null) {
            return false;
        }
        boolean a = a();
        boolean a2 = bVar.a();
        if ((a || a2) && (!a || !a2 || !this.m.equals(bVar.m))) {
            return false;
        }
        a = b();
        a2 = bVar.b();
        if ((a || a2) && (!a || !a2 || !this.n.equals(bVar.n))) {
            return false;
        }
        a = c();
        a2 = bVar.c();
        if ((a || a2) && (!a || !a2 || !this.o.equals(bVar.o))) {
            return false;
        }
        a = d();
        a2 = bVar.d();
        if ((a || a2) && (!a || !a2 || !this.p.equals(bVar.p))) {
            return false;
        }
        a = e();
        a2 = bVar.e();
        if ((a || a2) && (!a || !a2 || !this.q.equals(bVar.q))) {
            return false;
        }
        a = f();
        a2 = bVar.f();
        if ((a || a2) && (!a || !a2 || !this.r.a(bVar.r))) {
            return false;
        }
        a = h();
        a2 = bVar.h();
        if ((a || a2) && (!a || !a2 || !this.s.equals(bVar.s))) {
            return false;
        }
        a = i();
        a2 = bVar.i();
        if ((a || a2) && (!a || !a2 || !this.t.equals(bVar.t))) {
            return false;
        }
        a = j();
        a2 = bVar.j();
        if ((a || a2) && (!a || !a2 || !this.u.equals(bVar.u))) {
            return false;
        }
        a = k();
        a2 = bVar.k();
        return !(a || a2) || (a && a2 && this.v.equals(bVar.v));
    }

    public int b(b bVar) {
        if (!getClass().equals(bVar.getClass())) {
            return getClass().getName().compareTo(bVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(bVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.c.a(this.m, bVar.m);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(bVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.c.a(this.n, bVar.n);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(bVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.c.a(this.o, bVar.o);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(bVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.c.a(this.p, bVar.p);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(bVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.c.a(this.q, bVar.q);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(bVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.c.a(this.r, bVar.r);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(h()).compareTo(Boolean.valueOf(bVar.h()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (h()) {
            compareTo = org.apache.thrift.c.a(this.s, bVar.s);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(i()).compareTo(Boolean.valueOf(bVar.i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (i()) {
            compareTo = org.apache.thrift.c.a(this.t, bVar.t);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(j()).compareTo(Boolean.valueOf(bVar.j()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (j()) {
            compareTo = org.apache.thrift.c.a(this.u, bVar.u);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(k()).compareTo(Boolean.valueOf(bVar.k()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (k()) {
            compareTo = org.apache.thrift.c.a(this.v, bVar.v);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public b b(String str) {
        this.n = str;
        return this;
    }

    public void b(org.apache.thrift.protocol.f fVar) {
        l();
        fVar.a(b);
        if (this.m != null) {
            fVar.a(c);
            fVar.a(this.m);
            fVar.b();
        }
        if (this.n != null) {
            fVar.a(d);
            fVar.a(this.n);
            fVar.b();
        }
        if (this.o != null) {
            fVar.a(e);
            fVar.a(this.o);
            fVar.b();
        }
        if (this.p != null) {
            fVar.a(f);
            fVar.a(this.p);
            fVar.b();
        }
        if (this.q != null && e()) {
            fVar.a(g);
            fVar.a(this.q);
            fVar.b();
        }
        if (this.r != null && f()) {
            fVar.a(h);
            this.r.b(fVar);
            fVar.b();
        }
        if (this.s != null && h()) {
            fVar.a(i);
            fVar.a(new j((byte) 12, this.s.size()));
            for (a aVar : this.s) {
                aVar.b(fVar);
            }
            fVar.f();
            fVar.b();
        }
        if (this.t != null && i()) {
            fVar.a(j);
            fVar.a(this.t);
            fVar.b();
        }
        if (this.u != null && j()) {
            fVar.a(k);
            fVar.a(this.u);
            fVar.b();
        }
        if (this.v != null && k()) {
            fVar.a(l);
            fVar.a(this.v);
            fVar.b();
        }
        fVar.c();
        fVar.a();
    }

    public boolean b() {
        return this.n != null;
    }

    public b c(String str) {
        this.o = str;
        return this;
    }

    public boolean c() {
        return this.o != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((b) obj);
    }

    public b d(String str) {
        this.p = str;
        return this;
    }

    public boolean d() {
        return this.p != null;
    }

    public b e(String str) {
        this.q = str;
        return this;
    }

    public boolean e() {
        return this.q != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof b)) ? a((b) obj) : false;
    }

    public b f(String str) {
        this.t = str;
        return this;
    }

    public boolean f() {
        return this.r != null;
    }

    public int g() {
        return this.s == null ? 0 : this.s.size();
    }

    public b g(String str) {
        this.u = str;
        return this;
    }

    public b h(String str) {
        this.v = str;
        return this;
    }

    public boolean h() {
        return this.s != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.t != null;
    }

    public boolean j() {
        return this.u != null;
    }

    public boolean k() {
        return this.v != null;
    }

    public void l() {
        if (this.m == null) {
            throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'category' was not present! Struct: ").append(toString()).toString());
        } else if (this.n == null) {
            throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'uuid' was not present! Struct: ").append(toString()).toString());
        } else if (this.o == null) {
            throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'version' was not present! Struct: ").append(toString()).toString());
        } else if (this.p == null) {
            throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'network' was not present! Struct: ").append(toString()).toString());
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("HttpApi(");
        stringBuilder.append("category:");
        if (this.m == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.m);
        }
        stringBuilder.append(", ");
        stringBuilder.append("uuid:");
        if (this.n == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.n);
        }
        stringBuilder.append(", ");
        stringBuilder.append("version:");
        if (this.o == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.o);
        }
        stringBuilder.append(", ");
        stringBuilder.append("network:");
        if (this.p == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.p);
        }
        if (e()) {
            stringBuilder.append(", ");
            stringBuilder.append("client_ip:");
            if (this.q == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.q);
            }
        }
        if (f()) {
            stringBuilder.append(", ");
            stringBuilder.append("location:");
            if (this.r == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.r);
            }
        }
        if (h()) {
            stringBuilder.append(", ");
            stringBuilder.append("host_info:");
            if (this.s == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.s);
            }
        }
        if (i()) {
            stringBuilder.append(", ");
            stringBuilder.append("version_type:");
            if (this.t == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.t);
            }
        }
        if (j()) {
            stringBuilder.append(", ");
            stringBuilder.append("app_name:");
            if (this.u == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.u);
            }
        }
        if (k()) {
            stringBuilder.append(", ");
            stringBuilder.append("app_version:");
            if (this.v == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.v);
            }
        }
        stringBuilder.append(SocializeConstants.OP_CLOSE_PAREN);
        return stringBuilder.toString();
    }
}
