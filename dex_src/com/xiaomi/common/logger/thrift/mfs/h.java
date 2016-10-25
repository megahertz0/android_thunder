package com.xiaomi.common.logger.thrift.mfs;

import com.umeng.message.proguard.j;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.thrift.b;
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.g;
import org.apache.thrift.protocol.i;
import org.apache.thrift.protocol.k;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class h implements Serializable, Cloneable, b<h, a> {
    public static final Map<a, org.apache.thrift.meta_data.b> a;
    private static final k b;
    private static final c c;
    private static final c d;
    private static final c e;
    private int f;
    private int g;
    private int h;
    private BitSet i;

    public enum a {
        IP((short) 1, "ip"),
        EID((short) 2, "eid"),
        RT((short) 3, "rt");
        private static final Map<String, com.xiaomi.common.logger.thrift.mfs.h.a> d;
        private final short e;
        private final String f;

        static {
            String str = "ip";
            a = new com.xiaomi.common.logger.thrift.mfs.h.a("IP", 0, (short) 1, "ip");
            str = "eid";
            b = new com.xiaomi.common.logger.thrift.mfs.h.a("EID", 1, (short) 2, "eid");
            str = "rt";
            c = new com.xiaomi.common.logger.thrift.mfs.h.a("RT", 2, (short) 3, "rt");
            g = new com.xiaomi.common.logger.thrift.mfs.h.a[]{a, b, c};
            d = new HashMap();
            Iterator it = EnumSet.allOf(com.xiaomi.common.logger.thrift.mfs.h.a.class).iterator();
            while (it.hasNext()) {
                com.xiaomi.common.logger.thrift.mfs.h.a aVar = (com.xiaomi.common.logger.thrift.mfs.h.a) it.next();
                d.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.e = s;
            this.f = str;
        }

        public final String a() {
            return this.f;
        }
    }

    static {
        b = new k("PassportLandNodeInfo");
        c = new c("ip", (byte) 8, (short) 1);
        d = new c("eid", (byte) 8, (short) 2);
        e = new c("rt", (byte) 8, (short) 3);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.a, new org.apache.thrift.meta_data.b("ip", (byte) 1, new org.apache.thrift.meta_data.c((byte) 8)));
        enumMap.put(a.b, new org.apache.thrift.meta_data.b("eid", (byte) 1, new org.apache.thrift.meta_data.c((byte) 8)));
        enumMap.put(a.c, new org.apache.thrift.meta_data.b("rt", (byte) 1, new org.apache.thrift.meta_data.c((byte) 8)));
        a = Collections.unmodifiableMap(enumMap);
        org.apache.thrift.meta_data.b.a(h.class, a);
    }

    public h() {
        this.i = new BitSet(3);
    }

    public void a(f fVar) {
        fVar.g();
        while (true) {
            c i = fVar.i();
            if (i.b != null) {
                switch (i.c) {
                    case SimpleLog.LOG_LEVEL_TRACE:
                        if (i.b == (byte) 8) {
                            this.f = fVar.t();
                            a(true);
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case SimpleLog.LOG_LEVEL_DEBUG:
                        if (i.b == (byte) 8) {
                            this.g = fVar.t();
                            b(true);
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case MqttConnectOptions.MQTT_VERSION_3_1:
                        if (i.b == (byte) 8) {
                            this.h = fVar.t();
                            c(true);
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
                if (!a()) {
                    throw new g(new StringBuilder("Required field 'ip' was not found in serialized data! Struct: ").append(toString()).toString());
                } else if (!b()) {
                    throw new g(new StringBuilder("Required field 'eid' was not found in serialized data! Struct: ").append(toString()).toString());
                } else if (c()) {
                    d();
                    return;
                } else {
                    throw new g(new StringBuilder("Required field 'rt' was not found in serialized data! Struct: ").append(toString()).toString());
                }
            }
        }
    }

    public void a(boolean z) {
        this.i.set(0, z);
    }

    public boolean a() {
        return this.i.get(0);
    }

    public boolean a(h hVar) {
        return hVar != null && this.f == hVar.f && this.g == hVar.g && this.h == hVar.h;
    }

    public int b(h hVar) {
        if (!getClass().equals(hVar.getClass())) {
            return getClass().getName().compareTo(hVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(hVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.c.a(this.f, hVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(hVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.c.a(this.g, hVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(hVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.c.a(this.h, hVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(f fVar) {
        d();
        fVar.a(b);
        fVar.a(c);
        fVar.a(this.f);
        fVar.b();
        fVar.a(d);
        fVar.a(this.g);
        fVar.b();
        fVar.a(e);
        fVar.a(this.h);
        fVar.b();
        fVar.c();
        fVar.a();
    }

    public void b(boolean z) {
        this.i.set(1, z);
    }

    public boolean b() {
        return this.i.get(1);
    }

    public void c(boolean z) {
        this.i.set(SimpleLog.LOG_LEVEL_DEBUG, z);
    }

    public boolean c() {
        return this.i.get(SimpleLog.LOG_LEVEL_DEBUG);
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((h) obj);
    }

    public void d() {
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof h)) ? a((h) obj) : false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("PassportLandNodeInfo(");
        stringBuilder.append("ip:");
        stringBuilder.append(this.f);
        stringBuilder.append(", ");
        stringBuilder.append("eid:");
        stringBuilder.append(this.g);
        stringBuilder.append(", ");
        stringBuilder.append("rt:");
        stringBuilder.append(this.h);
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }
}
