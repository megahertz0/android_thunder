package com.xiaomi.common.logger.thrift.mfs;

import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.android.spdy.SpdyAgent;
import org.apache.thrift.b;
import org.apache.thrift.meta_data.e;
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.g;
import org.apache.thrift.protocol.i;
import org.apache.thrift.protocol.k;

public class d implements Serializable, Cloneable, b<d, a> {
    public static final Map<a, org.apache.thrift.meta_data.b> a;
    private static final k b;
    private static final c c;
    private static final c d;
    private static final c e;
    private static final c f;
    private static final c g;
    private static final c h;
    private static final c i;
    private String j;
    private int k;
    private int l;
    private long m;
    private int n;
    private Map<String, Integer> o;
    private Map<Integer, Integer> p;
    private BitSet q;

    static {
        b = new k("LandNodeInfo");
        c = new c("ip", (byte) 11, (short) 1);
        d = new c("failed_count", (byte) 8, (short) 2);
        e = new c("success_count", (byte) 8, (short) 3);
        f = new c("duration", (byte) 10, (short) 4);
        g = new c("size", (byte) 8, (short) 5);
        h = new c("exp_info", (byte) 13, (short) 6);
        i = new c("http_info", (byte) 13, (short) 7);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.a, new org.apache.thrift.meta_data.b("ip", (byte) 1, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.b, new org.apache.thrift.meta_data.b("failed_count", (byte) 1, new org.apache.thrift.meta_data.c((byte) 8)));
        enumMap.put(a.c, new org.apache.thrift.meta_data.b("success_count", (byte) 1, new org.apache.thrift.meta_data.c((byte) 8)));
        enumMap.put(a.d, new org.apache.thrift.meta_data.b("duration", (byte) 1, new org.apache.thrift.meta_data.c((byte) 10)));
        enumMap.put(a.e, new org.apache.thrift.meta_data.b("size", (byte) 1, new org.apache.thrift.meta_data.c((byte) 8)));
        enumMap.put(a.f, new org.apache.thrift.meta_data.b("exp_info", (byte) 2, new e((byte) 13, new org.apache.thrift.meta_data.c((byte) 11), new org.apache.thrift.meta_data.c((byte) 8))));
        enumMap.put(a.g, new org.apache.thrift.meta_data.b("http_info", (byte) 2, new e((byte) 13, new org.apache.thrift.meta_data.c((byte) 8), new org.apache.thrift.meta_data.c((byte) 8))));
        a = Collections.unmodifiableMap(enumMap);
        org.apache.thrift.meta_data.b.a(d.class, a);
    }

    public d() {
        this.q = new BitSet(4);
    }

    public d a(int i) {
        this.k = i;
        a(true);
        return this;
    }

    public d a(long j) {
        this.m = j;
        c(true);
        return this;
    }

    public d a(String str) {
        this.j = str;
        return this;
    }

    public d a(Map<String, Integer> map) {
        this.o = map;
        return this;
    }

    public void a(f fVar) {
        fVar.g();
        while (true) {
            c i = fVar.i();
            if (i.b != null) {
                org.apache.thrift.protocol.e k;
                int i2;
                switch (i.c) {
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        if (i.b == 11) {
                            this.j = fVar.w();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        if (i.b == (byte) 8) {
                            this.k = fVar.t();
                            a(true);
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case XZBDevice.DOWNLOAD_LIST_FAILED:
                        if (i.b == (byte) 8) {
                            this.l = fVar.t();
                            b(true);
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case XZBDevice.DOWNLOAD_LIST_ALL:
                        if (i.b == 10) {
                            this.m = fVar.u();
                            c(true);
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                        if (i.b == (byte) 8) {
                            this.n = fVar.t();
                            d(true);
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case R.styleable.Toolbar_contentInsetEnd:
                        if (i.b == (byte) 13) {
                            k = fVar.k();
                            this.o = new HashMap(k.c * 2);
                            for (i2 = 0; i2 < k.c; i2++) {
                                this.o.put(fVar.w(), Integer.valueOf(fVar.t()));
                            }
                            fVar.l();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case R.styleable.Toolbar_contentInsetLeft:
                        if (i.b == (byte) 13) {
                            k = fVar.k();
                            this.p = new HashMap(k.c * 2);
                            for (i2 = 0; i2 < k.c; i2++) {
                                this.p.put(Integer.valueOf(fVar.t()), Integer.valueOf(fVar.t()));
                            }
                            fVar.l();
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
                if (!b()) {
                    throw new g(new StringBuilder("Required field 'failed_count' was not found in serialized data! Struct: ").append(toString()).toString());
                } else if (!c()) {
                    throw new g(new StringBuilder("Required field 'success_count' was not found in serialized data! Struct: ").append(toString()).toString());
                } else if (!d()) {
                    throw new g(new StringBuilder("Required field 'duration' was not found in serialized data! Struct: ").append(toString()).toString());
                } else if (e()) {
                    h();
                    return;
                } else {
                    throw new g(new StringBuilder("Required field 'size' was not found in serialized data! Struct: ").append(toString()).toString());
                }
            }
        }
    }

    public void a(boolean z) {
        this.q.set(0, z);
    }

    public boolean a() {
        return this.j != null;
    }

    public boolean a(d dVar) {
        if (dVar == null) {
            return false;
        }
        boolean a = a();
        boolean a2 = dVar.a();
        if (((a || a2) && (!a || !a2 || !this.j.equals(dVar.j))) || this.k != dVar.k || this.l != dVar.l || this.m != dVar.m || this.n != dVar.n) {
            return false;
        }
        a = f();
        a2 = dVar.f();
        if ((a || a2) && (!a || !a2 || !this.o.equals(dVar.o))) {
            return false;
        }
        a = g();
        a2 = dVar.g();
        return !(a || a2) || (a && a2 && this.p.equals(dVar.p));
    }

    public int b(d dVar) {
        if (!getClass().equals(dVar.getClass())) {
            return getClass().getName().compareTo(dVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(dVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.c.a(this.j, dVar.j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(dVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.c.a(this.k, dVar.k);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(dVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.c.a(this.l, dVar.l);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(dVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.c.a(this.m, dVar.m);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(dVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.c.a(this.n, dVar.n);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(dVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.c.a(this.o, dVar.o);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(dVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.c.a(this.p, dVar.p);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public d b(int i) {
        this.l = i;
        b(true);
        return this;
    }

    public void b(f fVar) {
        h();
        fVar.a(b);
        if (this.j != null) {
            fVar.a(c);
            fVar.a(this.j);
            fVar.b();
        }
        fVar.a(d);
        fVar.a(this.k);
        fVar.b();
        fVar.a(e);
        fVar.a(this.l);
        fVar.b();
        fVar.a(f);
        fVar.a(this.m);
        fVar.b();
        fVar.a(g);
        fVar.a(this.n);
        fVar.b();
        if (this.o != null && f()) {
            fVar.a(h);
            fVar.a(new org.apache.thrift.protocol.e((byte) 11, (byte) 8, this.o.size()));
            for (Entry entry : this.o.entrySet()) {
                fVar.a((String) entry.getKey());
                fVar.a(((Integer) entry.getValue()).intValue());
            }
            fVar.d();
            fVar.b();
        }
        if (this.p != null && g()) {
            fVar.a(i);
            fVar.a(new org.apache.thrift.protocol.e((byte) 8, (byte) 8, this.p.size()));
            for (Entry entry2 : this.p.entrySet()) {
                fVar.a(((Integer) entry2.getKey()).intValue());
                fVar.a(((Integer) entry2.getValue()).intValue());
            }
            fVar.d();
            fVar.b();
        }
        fVar.c();
        fVar.a();
    }

    public void b(boolean z) {
        this.q.set(1, z);
    }

    public boolean b() {
        return this.q.get(0);
    }

    public d c(int i) {
        this.n = i;
        d(true);
        return this;
    }

    public void c(boolean z) {
        this.q.set(XZBDevice.DOWNLOAD_LIST_RECYCLE, z);
    }

    public boolean c() {
        return this.q.get(1);
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((d) obj);
    }

    public void d(boolean z) {
        this.q.set(XZBDevice.DOWNLOAD_LIST_FAILED, z);
    }

    public boolean d() {
        return this.q.get(XZBDevice.DOWNLOAD_LIST_RECYCLE);
    }

    public boolean e() {
        return this.q.get(XZBDevice.DOWNLOAD_LIST_FAILED);
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof d)) ? a((d) obj) : false;
    }

    public boolean f() {
        return this.o != null;
    }

    public boolean g() {
        return this.p != null;
    }

    public void h() {
        if (this.j == null) {
            throw new g(new StringBuilder("Required field 'ip' was not present! Struct: ").append(toString()).toString());
        }
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("LandNodeInfo(");
        stringBuilder.append("ip:");
        if (this.j == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.j);
        }
        stringBuilder.append(", ");
        stringBuilder.append("failed_count:");
        stringBuilder.append(this.k);
        stringBuilder.append(", ");
        stringBuilder.append("success_count:");
        stringBuilder.append(this.l);
        stringBuilder.append(", ");
        stringBuilder.append("duration:");
        stringBuilder.append(this.m);
        stringBuilder.append(", ");
        stringBuilder.append("size:");
        stringBuilder.append(this.n);
        if (f()) {
            stringBuilder.append(", ");
            stringBuilder.append("exp_info:");
            if (this.o == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.o);
            }
        }
        if (g()) {
            stringBuilder.append(", ");
            stringBuilder.append("http_info:");
            if (this.p == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.p);
            }
        }
        stringBuilder.append(SocializeConstants.OP_CLOSE_PAREN);
        return stringBuilder.toString();
    }
}
