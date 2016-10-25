package com.xiaomi.xmpush.thrift;

import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import org.android.spdy.SpdyAgent;
import org.apache.thrift.b;
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.g;
import org.apache.thrift.protocol.i;
import org.apache.thrift.protocol.k;

public class j implements Serializable, Cloneable, b<j, a> {
    public static final Map<a, org.apache.thrift.meta_data.b> f;
    private static final k g;
    private static final c h;
    private static final c i;
    private static final c j;
    private static final c k;
    private static final c l;
    public long a;
    public String b;
    public String c;
    public String d;
    public boolean e;
    private BitSet m;

    static {
        g = new k("Target");
        h = new c("channelId", (byte) 10, (short) 1);
        i = new c("userId", (byte) 11, (short) 2);
        j = new c("server", (byte) 11, (short) 3);
        k = new c("resource", (byte) 11, (short) 4);
        l = new c("isPreview", (byte) 2, (short) 5);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.a, new org.apache.thrift.meta_data.b("channelId", (byte) 1, new org.apache.thrift.meta_data.c((byte) 10)));
        enumMap.put(a.b, new org.apache.thrift.meta_data.b("userId", (byte) 1, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.c, new org.apache.thrift.meta_data.b("server", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.d, new org.apache.thrift.meta_data.b("resource", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.e, new org.apache.thrift.meta_data.b("isPreview", (byte) 2, new org.apache.thrift.meta_data.c((byte) 2)));
        f = Collections.unmodifiableMap(enumMap);
        org.apache.thrift.meta_data.b.a(j.class, f);
    }

    public j() {
        this.m = new BitSet(2);
        this.a = 5;
        this.c = "xiaomi.com";
        this.d = a.d;
        this.e = false;
    }

    public void a(f fVar) {
        fVar.g();
        while (true) {
            c i = fVar.i();
            if (i.b != null) {
                switch (i.c) {
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        if (i.b == 10) {
                            this.a = fVar.u();
                            a(true);
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        if (i.b == (byte) 11) {
                            this.b = fVar.w();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case XZBDevice.DOWNLOAD_LIST_FAILED:
                        if (i.b == (byte) 11) {
                            this.c = fVar.w();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case XZBDevice.DOWNLOAD_LIST_ALL:
                        if (i.b == (byte) 11) {
                            this.d = fVar.w();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                        if (i.b == 2) {
                            this.e = fVar.q();
                            b(true);
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
                if (a()) {
                    f();
                    return;
                }
                throw new g(new StringBuilder("Required field 'channelId' was not found in serialized data! Struct: ").append(toString()).toString());
            }
        }
    }

    public void a(boolean z) {
        this.m.set(0, z);
    }

    public boolean a() {
        return this.m.get(0);
    }

    public boolean a(j jVar) {
        if (jVar == null || this.a != jVar.a) {
            return false;
        }
        boolean b = b();
        boolean b2 = jVar.b();
        if ((b || b2) && (!b || !b2 || !this.b.equals(jVar.b))) {
            return false;
        }
        b = c();
        b2 = jVar.c();
        if ((b || b2) && (!b || !b2 || !this.c.equals(jVar.c))) {
            return false;
        }
        b = d();
        b2 = jVar.d();
        if ((b || b2) && (!b || !b2 || !this.d.equals(jVar.d))) {
            return false;
        }
        b = e();
        b2 = jVar.e();
        return !(b || b2) || (b && b2 && this.e == jVar.e);
    }

    public int b(j jVar) {
        if (!getClass().equals(jVar.getClass())) {
            return getClass().getName().compareTo(jVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(jVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.c.a(this.a, jVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(jVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.c.a(this.b, jVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(jVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.c.a(this.c, jVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(jVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.c.a(this.d, jVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(jVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.c.a(this.e, jVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(f fVar) {
        f();
        fVar.a(g);
        fVar.a(h);
        fVar.a(this.a);
        fVar.b();
        if (this.b != null) {
            fVar.a(i);
            fVar.a(this.b);
            fVar.b();
        }
        if (this.c != null && c()) {
            fVar.a(j);
            fVar.a(this.c);
            fVar.b();
        }
        if (this.d != null && d()) {
            fVar.a(k);
            fVar.a(this.d);
            fVar.b();
        }
        if (e()) {
            fVar.a(l);
            fVar.a(this.e);
            fVar.b();
        }
        fVar.c();
        fVar.a();
    }

    public void b(boolean z) {
        this.m.set(1, z);
    }

    public boolean b() {
        return this.b != null;
    }

    public boolean c() {
        return this.c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((j) obj);
    }

    public boolean d() {
        return this.d != null;
    }

    public boolean e() {
        return this.m.get(1);
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof j)) ? a((j) obj) : false;
    }

    public void f() {
        if (this.b == null) {
            throw new g(new StringBuilder("Required field 'userId' was not present! Struct: ").append(toString()).toString());
        }
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Target(");
        stringBuilder.append("channelId:");
        stringBuilder.append(this.a);
        stringBuilder.append(", ");
        stringBuilder.append("userId:");
        if (this.b == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.b);
        }
        if (c()) {
            stringBuilder.append(", ");
            stringBuilder.append("server:");
            if (this.c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.c);
            }
        }
        if (d()) {
            stringBuilder.append(", ");
            stringBuilder.append("resource:");
            if (this.d == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.d);
            }
        }
        if (e()) {
            stringBuilder.append(", ");
            stringBuilder.append("isPreview:");
            stringBuilder.append(this.e);
        }
        stringBuilder.append(SocializeConstants.OP_CLOSE_PAREN);
        return stringBuilder.toString();
    }
}
