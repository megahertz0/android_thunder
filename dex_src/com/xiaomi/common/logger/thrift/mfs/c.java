package com.xiaomi.common.logger.thrift.mfs;

import com.umeng.message.proguard.j;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.thrift.b;
import org.apache.thrift.meta_data.g;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.i;
import org.apache.thrift.protocol.k;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class c implements Serializable, Cloneable, b<c, a> {
    public static final Map<a, org.apache.thrift.meta_data.b> a;
    private static final k b;
    private static final org.apache.thrift.protocol.c c;
    private static final org.apache.thrift.protocol.c d;
    private static final org.apache.thrift.protocol.c e;
    private static final org.apache.thrift.protocol.c f;
    private com.xiaomi.common.logger.thrift.a g;
    private String h;
    private b i;
    private f j;

    public enum a {
        COMMON((short) 1, "common"),
        CATEGORY((short) 2, "category"),
        HTTP_API((short) 3, "httpApi"),
        PASSPORT((short) 4, "passport");
        private static final Map<String, com.xiaomi.common.logger.thrift.mfs.c.a> e;
        private final short f;
        private final String g;

        static {
            String str = "common";
            a = new com.xiaomi.common.logger.thrift.mfs.c.a("COMMON", 0, (short) 1, "common");
            str = "category";
            b = new com.xiaomi.common.logger.thrift.mfs.c.a("CATEGORY", 1, (short) 2, "category");
            str = "httpApi";
            c = new com.xiaomi.common.logger.thrift.mfs.c.a("HTTP_API", 2, (short) 3, "httpApi");
            str = "passport";
            d = new com.xiaomi.common.logger.thrift.mfs.c.a("PASSPORT", 3, (short) 4, "passport");
            h = new com.xiaomi.common.logger.thrift.mfs.c.a[]{a, b, c, d};
            e = new HashMap();
            Iterator it = EnumSet.allOf(com.xiaomi.common.logger.thrift.mfs.c.a.class).iterator();
            while (it.hasNext()) {
                com.xiaomi.common.logger.thrift.mfs.c.a aVar = (com.xiaomi.common.logger.thrift.mfs.c.a) it.next();
                e.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.f = s;
            this.g = str;
        }

        public final String a() {
            return this.g;
        }
    }

    static {
        b = new k("HttpLog");
        c = new org.apache.thrift.protocol.c("common", (byte) 12, (short) 1);
        d = new org.apache.thrift.protocol.c("category", (byte) 11, (short) 2);
        e = new org.apache.thrift.protocol.c("httpApi", (byte) 12, (short) 3);
        f = new org.apache.thrift.protocol.c("passport", (byte) 12, (short) 4);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.a, new org.apache.thrift.meta_data.b("common", (byte) 1, new g((byte) 12, com.xiaomi.common.logger.thrift.a.class)));
        enumMap.put(a.b, new org.apache.thrift.meta_data.b("category", (byte) 1, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.c, new org.apache.thrift.meta_data.b("httpApi", (byte) 2, new g((byte) 12, b.class)));
        enumMap.put(a.d, new org.apache.thrift.meta_data.b("passport", (byte) 2, new g((byte) 12, f.class)));
        a = Collections.unmodifiableMap(enumMap);
        org.apache.thrift.meta_data.b.a(c.class, a);
    }

    public c() {
        this.h = BuildConfig.VERSION_NAME;
    }

    public c a(com.xiaomi.common.logger.thrift.a aVar) {
        this.g = aVar;
        return this;
    }

    public c a(b bVar) {
        this.i = bVar;
        return this;
    }

    public c a(String str) {
        this.h = str;
        return this;
    }

    public void a(f fVar) {
        fVar.g();
        while (true) {
            org.apache.thrift.protocol.c i = fVar.i();
            if (i.b != null) {
                switch (i.c) {
                    case SimpleLog.LOG_LEVEL_TRACE:
                        if (i.b == (byte) 12) {
                            this.g = new com.xiaomi.common.logger.thrift.a();
                            this.g.a(fVar);
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case SimpleLog.LOG_LEVEL_DEBUG:
                        if (i.b == 11) {
                            this.h = fVar.w();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case MqttConnectOptions.MQTT_VERSION_3_1:
                        if (i.b == (byte) 12) {
                            this.i = new b();
                            this.i.a(fVar);
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case MqttConnectOptions.MQTT_VERSION_3_1_1:
                        if (i.b == (byte) 12) {
                            this.j = new f();
                            this.j.a(fVar);
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
                e();
                return;
            }
        }
    }

    public boolean a() {
        return this.g != null;
    }

    public boolean a(c cVar) {
        if (cVar == null) {
            return false;
        }
        boolean a = a();
        boolean a2 = cVar.a();
        if ((a || a2) && (!a || !a2 || !this.g.a(cVar.g))) {
            return false;
        }
        a = b();
        a2 = cVar.b();
        if ((a || a2) && (!a || !a2 || !this.h.equals(cVar.h))) {
            return false;
        }
        a = c();
        a2 = cVar.c();
        if ((a || a2) && (!a || !a2 || !this.i.a(cVar.i))) {
            return false;
        }
        a = d();
        a2 = cVar.d();
        return !(a || a2) || (a && a2 && this.j.a(cVar.j));
    }

    public int b(c cVar) {
        if (!getClass().equals(cVar.getClass())) {
            return getClass().getName().compareTo(cVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(cVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.c.a(this.g, cVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(cVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.c.a(this.h, cVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(cVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.c.a(this.i, cVar.i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(cVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.c.a(this.j, cVar.j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(f fVar) {
        e();
        fVar.a(b);
        if (this.g != null) {
            fVar.a(c);
            this.g.b(fVar);
            fVar.b();
        }
        if (this.h != null) {
            fVar.a(d);
            fVar.a(this.h);
            fVar.b();
        }
        if (this.i != null && c()) {
            fVar.a(e);
            this.i.b(fVar);
            fVar.b();
        }
        if (this.j != null && d()) {
            fVar.a(f);
            this.j.b(fVar);
            fVar.b();
        }
        fVar.c();
        fVar.a();
    }

    public boolean b() {
        return this.h != null;
    }

    public boolean c() {
        return this.i != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((c) obj);
    }

    public boolean d() {
        return this.j != null;
    }

    public void e() {
        if (this.g == null) {
            throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'common' was not present! Struct: ").append(toString()).toString());
        } else if (this.h == null) {
            throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'category' was not present! Struct: ").append(toString()).toString());
        }
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof c)) ? a((c) obj) : false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("HttpLog(");
        stringBuilder.append("common:");
        if (this.g == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.g);
        }
        stringBuilder.append(", ");
        stringBuilder.append("category:");
        if (this.h == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.h);
        }
        if (c()) {
            stringBuilder.append(", ");
            stringBuilder.append("httpApi:");
            if (this.i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.i);
            }
        }
        if (d()) {
            stringBuilder.append(", ");
            stringBuilder.append("passport:");
            if (this.j == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.j);
            }
        }
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }
}
