package com.xiaomi.common.logger.thrift.mfs;

import com.xunlei.xiazaibao.BuildConfig;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.thrift.b;
import org.apache.thrift.meta_data.g;
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.i;
import org.apache.thrift.protocol.j;
import org.apache.thrift.protocol.k;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class f implements Serializable, Cloneable, b<f, a> {
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
    private String k;
    private String l;
    private String m;
    private String n;
    private e o;
    private Set<g> p;

    public enum a {
        CATEGORY((short) 1, "category"),
        UUID((short) 2, "uuid"),
        VERSION((short) 3, "version"),
        NETWORK((short) 4, "network"),
        RID((short) 5, "rid"),
        LOCATION((short) 6, "location"),
        HOST_INFO((short) 7, "host_info");
        private static final Map<String, com.xiaomi.common.logger.thrift.mfs.f.a> h;
        private final short i;
        private final String j;

        static {
            String str = "category";
            a = new com.xiaomi.common.logger.thrift.mfs.f.a("CATEGORY", 0, (short) 1, "category");
            String str2 = "uuid";
            b = new com.xiaomi.common.logger.thrift.mfs.f.a("UUID", 1, (short) 2, "uuid");
            str2 = "version";
            c = new com.xiaomi.common.logger.thrift.mfs.f.a("VERSION", 2, (short) 3, "version");
            str2 = "network";
            d = new com.xiaomi.common.logger.thrift.mfs.f.a("NETWORK", 3, (short) 4, "network");
            str2 = "rid";
            e = new com.xiaomi.common.logger.thrift.mfs.f.a("RID", 4, (short) 5, "rid");
            str = "location";
            f = new com.xiaomi.common.logger.thrift.mfs.f.a("LOCATION", 5, (short) 6, "location");
            String str3 = "host_info";
            g = new com.xiaomi.common.logger.thrift.mfs.f.a("HOST_INFO", 6, (short) 7, "host_info");
            k = new com.xiaomi.common.logger.thrift.mfs.f.a[]{a, b, c, d, e, f, g};
            h = new HashMap();
            Iterator it = EnumSet.allOf(com.xiaomi.common.logger.thrift.mfs.f.a.class).iterator();
            while (it.hasNext()) {
                com.xiaomi.common.logger.thrift.mfs.f.a aVar = (com.xiaomi.common.logger.thrift.mfs.f.a) it.next();
                h.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.i = s;
            this.j = str;
        }

        public final String a() {
            return this.j;
        }
    }

    static {
        b = new k("Passport");
        c = new c("category", (byte) 11, (short) 1);
        d = new c("uuid", (byte) 11, (short) 2);
        e = new c("version", (byte) 11, (short) 3);
        f = new c("network", (byte) 11, (short) 4);
        g = new c("rid", (byte) 11, (short) 5);
        h = new c("location", (byte) 12, (short) 6);
        i = new c("host_info", (byte) 14, (short) 7);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.a, new org.apache.thrift.meta_data.b("category", (byte) 1, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.b, new org.apache.thrift.meta_data.b("uuid", (byte) 1, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.c, new org.apache.thrift.meta_data.b("version", (byte) 1, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.d, new org.apache.thrift.meta_data.b("network", (byte) 1, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.e, new org.apache.thrift.meta_data.b("rid", (byte) 1, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.f, new org.apache.thrift.meta_data.b("location", (byte) 2, new g((byte) 12, e.class)));
        enumMap.put(a.g, new org.apache.thrift.meta_data.b("host_info", (byte) 2, new org.apache.thrift.meta_data.f((byte) 14, new g((byte) 12, g.class))));
        a = Collections.unmodifiableMap(enumMap);
        org.apache.thrift.meta_data.b.a(f.class, a);
    }

    public f() {
        this.j = BuildConfig.VERSION_NAME;
    }

    public void a(org.apache.thrift.protocol.f fVar) {
        fVar.g();
        while (true) {
            c i = fVar.i();
            if (i.b != null) {
                switch (i.c) {
                    case SimpleLog.LOG_LEVEL_TRACE:
                        if (i.b == (byte) 11) {
                            this.j = fVar.w();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case SimpleLog.LOG_LEVEL_DEBUG:
                        if (i.b == (byte) 11) {
                            this.k = fVar.w();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case MqttConnectOptions.MQTT_VERSION_3_1:
                        if (i.b == (byte) 11) {
                            this.l = fVar.w();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case MqttConnectOptions.MQTT_VERSION_3_1_1:
                        if (i.b == (byte) 11) {
                            this.m = fVar.w();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case SimpleLog.LOG_LEVEL_ERROR:
                        if (i.b == (byte) 11) {
                            this.n = fVar.w();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case SimpleLog.LOG_LEVEL_FATAL:
                        if (i.b == 12) {
                            this.o = new e();
                            this.o.a(fVar);
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case SimpleLog.LOG_LEVEL_OFF:
                        if (i.b == 14) {
                            j o = fVar.o();
                            this.p = new HashSet(o.b * 2);
                            for (int i2 = 0; i2 < o.b; i2++) {
                                g gVar = new g();
                                gVar.a(fVar);
                                this.p.add(gVar);
                            }
                            fVar.p();
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
                h();
                return;
            }
        }
    }

    public boolean a() {
        return this.j != null;
    }

    public boolean a(f fVar) {
        if (fVar == null) {
            return false;
        }
        boolean a = a();
        boolean a2 = fVar.a();
        if ((a || a2) && (!a || !a2 || !this.j.equals(fVar.j))) {
            return false;
        }
        a = b();
        a2 = fVar.b();
        if ((a || a2) && (!a || !a2 || !this.k.equals(fVar.k))) {
            return false;
        }
        a = c();
        a2 = fVar.c();
        if ((a || a2) && (!a || !a2 || !this.l.equals(fVar.l))) {
            return false;
        }
        a = d();
        a2 = fVar.d();
        if ((a || a2) && (!a || !a2 || !this.m.equals(fVar.m))) {
            return false;
        }
        a = e();
        a2 = fVar.e();
        if ((a || a2) && (!a || !a2 || !this.n.equals(fVar.n))) {
            return false;
        }
        a = f();
        a2 = fVar.f();
        if ((a || a2) && (!a || !a2 || !this.o.a(fVar.o))) {
            return false;
        }
        a = g();
        a2 = fVar.g();
        return !(a || a2) || (a && a2 && this.p.equals(fVar.p));
    }

    public int b(f fVar) {
        if (!getClass().equals(fVar.getClass())) {
            return getClass().getName().compareTo(fVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(fVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.c.a(this.j, fVar.j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(fVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.c.a(this.k, fVar.k);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(fVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.c.a(this.l, fVar.l);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(fVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.c.a(this.m, fVar.m);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(fVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.c.a(this.n, fVar.n);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(fVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.c.a(this.o, fVar.o);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(fVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.c.a(this.p, fVar.p);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(org.apache.thrift.protocol.f fVar) {
        h();
        fVar.a(b);
        if (this.j != null) {
            fVar.a(c);
            fVar.a(this.j);
            fVar.b();
        }
        if (this.k != null) {
            fVar.a(d);
            fVar.a(this.k);
            fVar.b();
        }
        if (this.l != null) {
            fVar.a(e);
            fVar.a(this.l);
            fVar.b();
        }
        if (this.m != null) {
            fVar.a(f);
            fVar.a(this.m);
            fVar.b();
        }
        if (this.n != null) {
            fVar.a(g);
            fVar.a(this.n);
            fVar.b();
        }
        if (this.o != null && f()) {
            fVar.a(h);
            this.o.b(fVar);
            fVar.b();
        }
        if (this.p != null && g()) {
            fVar.a(i);
            fVar.a(new j((byte) 12, this.p.size()));
            for (g gVar : this.p) {
                gVar.b(fVar);
            }
            fVar.f();
            fVar.b();
        }
        fVar.c();
        fVar.a();
    }

    public boolean b() {
        return this.k != null;
    }

    public boolean c() {
        return this.l != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((f) obj);
    }

    public boolean d() {
        return this.m != null;
    }

    public boolean e() {
        return this.n != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof f)) ? a((f) obj) : false;
    }

    public boolean f() {
        return this.o != null;
    }

    public boolean g() {
        return this.p != null;
    }

    public void h() {
        if (this.j == null) {
            throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'category' was not present! Struct: ").append(toString()).toString());
        } else if (this.k == null) {
            throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'uuid' was not present! Struct: ").append(toString()).toString());
        } else if (this.l == null) {
            throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'version' was not present! Struct: ").append(toString()).toString());
        } else if (this.m == null) {
            throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'network' was not present! Struct: ").append(toString()).toString());
        } else if (this.n == null) {
            throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'rid' was not present! Struct: ").append(toString()).toString());
        }
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Passport(");
        stringBuilder.append("category:");
        if (this.j == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.j);
        }
        stringBuilder.append(", ");
        stringBuilder.append("uuid:");
        if (this.k == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.k);
        }
        stringBuilder.append(", ");
        stringBuilder.append("version:");
        if (this.l == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.l);
        }
        stringBuilder.append(", ");
        stringBuilder.append("network:");
        if (this.m == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.m);
        }
        stringBuilder.append(", ");
        stringBuilder.append("rid:");
        if (this.n == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.n);
        }
        if (f()) {
            stringBuilder.append(", ");
            stringBuilder.append("location:");
            if (this.o == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.o);
            }
        }
        if (g()) {
            stringBuilder.append(", ");
            stringBuilder.append("host_info:");
            if (this.p == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.p);
            }
        }
        stringBuilder.append(com.umeng.message.proguard.j.t);
        return stringBuilder.toString();
    }
}
