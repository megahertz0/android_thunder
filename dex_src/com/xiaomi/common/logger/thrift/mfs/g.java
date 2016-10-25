package com.xiaomi.common.logger.thrift.mfs;

import com.umeng.message.proguard.j;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.thrift.b;
import org.apache.thrift.meta_data.d;
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.i;
import org.apache.thrift.protocol.k;

public class g implements Serializable, Cloneable, b<g, a> {
    public static final Map<a, org.apache.thrift.meta_data.b> a;
    private static final k b;
    private static final c c;
    private static final c d;
    private String e;
    private List<h> f;

    public enum a {
        HOST((short) 1, "host"),
        LAND_NODE_INFO((short) 2, "land_node_info");
        private static final Map<String, com.xiaomi.common.logger.thrift.mfs.g.a> c;
        private final short d;
        private final String e;

        static {
            String str = "host";
            a = new com.xiaomi.common.logger.thrift.mfs.g.a("HOST", 0, (short) 1, "host");
            str = "land_node_info";
            b = new com.xiaomi.common.logger.thrift.mfs.g.a("LAND_NODE_INFO", 1, (short) 2, "land_node_info");
            f = new com.xiaomi.common.logger.thrift.mfs.g.a[]{a, b};
            c = new HashMap();
            Iterator it = EnumSet.allOf(com.xiaomi.common.logger.thrift.mfs.g.a.class).iterator();
            while (it.hasNext()) {
                com.xiaomi.common.logger.thrift.mfs.g.a aVar = (com.xiaomi.common.logger.thrift.mfs.g.a) it.next();
                c.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.d = s;
            this.e = str;
        }

        public final String a() {
            return this.e;
        }
    }

    static {
        b = new k("PassportHostInfo");
        c = new c("host", (byte) 11, (short) 1);
        d = new c("land_node_info", (byte) 15, (short) 2);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.a, new org.apache.thrift.meta_data.b("host", (byte) 1, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.b, new org.apache.thrift.meta_data.b("land_node_info", (byte) 1, new d((byte) 15, new org.apache.thrift.meta_data.g((byte) 12, h.class))));
        a = Collections.unmodifiableMap(enumMap);
        org.apache.thrift.meta_data.b.a(g.class, a);
    }

    public void a(f fVar) {
        fVar.g();
        while (true) {
            c i = fVar.i();
            if (i.b != null) {
                switch (i.c) {
                    case SimpleLog.LOG_LEVEL_TRACE:
                        if (i.b == 11) {
                            this.e = fVar.w();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case SimpleLog.LOG_LEVEL_DEBUG:
                        if (i.b == 15) {
                            org.apache.thrift.protocol.d m = fVar.m();
                            this.f = new ArrayList(m.b);
                            for (int i2 = 0; i2 < m.b; i2++) {
                                h hVar = new h();
                                hVar.a(fVar);
                                this.f.add(hVar);
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

    public boolean a(g gVar) {
        if (gVar == null) {
            return false;
        }
        boolean a = a();
        boolean a2 = gVar.a();
        if ((a || a2) && (!a || !a2 || !this.e.equals(gVar.e))) {
            return false;
        }
        a = b();
        a2 = gVar.b();
        return !(a || a2) || (a && a2 && this.f.equals(gVar.f));
    }

    public int b(g gVar) {
        if (!getClass().equals(gVar.getClass())) {
            return getClass().getName().compareTo(gVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(gVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.c.a(this.e, gVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(gVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.c.a(this.f, gVar.f);
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
            for (h hVar : this.f) {
                hVar.b(fVar);
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
        return b((g) obj);
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof g)) ? a((g) obj) : false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("PassportHostInfo(");
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
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }
}
