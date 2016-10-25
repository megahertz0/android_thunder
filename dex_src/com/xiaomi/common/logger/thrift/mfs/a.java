package com.xiaomi.common.logger.thrift.mfs;

import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.android.spdy.SpdyAgent;
import org.apache.thrift.b;
import org.apache.thrift.meta_data.d;
import org.apache.thrift.meta_data.g;
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.i;
import org.apache.thrift.protocol.k;

public class a implements Serializable, Cloneable, b<a, a> {
    public static final Map<a, org.apache.thrift.meta_data.b> a;
    private static final k b;
    private static final c c;
    private static final c d;
    private String e;
    private List<d> f;

    static {
        b = new k("HostInfo");
        c = new c(com.taobao.accs.internal.b.ELECTION_KEY_HOST, (byte) 11, (short) 1);
        d = new c("land_node_info", (byte) 15, (short) 2);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.a, new org.apache.thrift.meta_data.b(com.taobao.accs.internal.b.ELECTION_KEY_HOST, (byte) 1, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.b, new org.apache.thrift.meta_data.b("land_node_info", (byte) 1, new d((byte) 15, new g((byte) 12, d.class))));
        a = Collections.unmodifiableMap(enumMap);
        org.apache.thrift.meta_data.b.a(a.class, a);
    }

    public a a(String str) {
        this.e = str;
        return this;
    }

    public a a(List<d> list) {
        this.f = list;
        return this;
    }

    public void a(f fVar) {
        fVar.g();
        while (true) {
            c i = fVar.i();
            if (i.b != null) {
                switch (i.c) {
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        if (i.b == 11) {
                            this.e = fVar.w();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        if (i.b == 15) {
                            org.apache.thrift.protocol.d m = fVar.m();
                            this.f = new ArrayList(m.b);
                            for (int i2 = 0; i2 < m.b; i2++) {
                                d dVar = new d();
                                dVar.a(fVar);
                                this.f.add(dVar);
                            }
                            fVar.n();
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
                c();
                return;
            }
        }
    }

    public boolean a() {
        return this.e != null;
    }

    public boolean a(a aVar) {
        if (aVar == null) {
            return false;
        }
        boolean a = a();
        boolean a2 = aVar.a();
        if ((a || a2) && (!a || !a2 || !this.e.equals(aVar.e))) {
            return false;
        }
        a = b();
        a2 = aVar.b();
        return !(a || a2) || (a && a2 && this.f.equals(aVar.f));
    }

    public int b(a aVar) {
        if (!getClass().equals(aVar.getClass())) {
            return getClass().getName().compareTo(aVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(aVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.c.a(this.e, aVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(aVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.c.a(this.f, aVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(f fVar) {
        c();
        fVar.a(b);
        if (this.e != null) {
            fVar.a(c);
            fVar.a(this.e);
            fVar.b();
        }
        if (this.f != null) {
            fVar.a(d);
            fVar.a(new org.apache.thrift.protocol.d((byte) 12, this.f.size()));
            for (d dVar : this.f) {
                dVar.b(fVar);
            }
            fVar.e();
            fVar.b();
        }
        fVar.c();
        fVar.a();
    }

    public boolean b() {
        return this.f != null;
    }

    public void c() {
        if (this.e == null) {
            throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'host' was not present! Struct: ").append(toString()).toString());
        } else if (this.f == null) {
            throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'land_node_info' was not present! Struct: ").append(toString()).toString());
        }
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((a) obj);
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof a)) ? a((a) obj) : false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("HostInfo(");
        stringBuilder.append("host:");
        if (this.e == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.e);
        }
        stringBuilder.append(", ");
        stringBuilder.append("land_node_info:");
        if (this.f == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f);
        }
        stringBuilder.append(SocializeConstants.OP_CLOSE_PAREN);
        return stringBuilder.toString();
    }
}
