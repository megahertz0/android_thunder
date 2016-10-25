package com.xiaomi.push.thrift;

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
import org.apache.thrift.meta_data.g;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.i;
import org.apache.thrift.protocol.k;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class c implements Serializable, Cloneable, b<c, a> {
    public static final Map<a, org.apache.thrift.meta_data.b> d;
    private static final k e;
    private static final org.apache.thrift.protocol.c f;
    private static final org.apache.thrift.protocol.c g;
    private static final org.apache.thrift.protocol.c h;
    public String a;
    public String b;
    public List<b> c;

    public enum a {
        UUID((short) 1, "uuid"),
        OPERATOR((short) 2, "operator"),
        EVENTS((short) 3, "events");
        private static final Map<String, com.xiaomi.push.thrift.c.a> d;
        private final short e;
        private final String f;

        static {
            String str = "uuid";
            a = new com.xiaomi.push.thrift.c.a("UUID", 0, (short) 1, "uuid");
            str = "operator";
            b = new com.xiaomi.push.thrift.c.a("OPERATOR", 1, (short) 2, "operator");
            str = "events";
            c = new com.xiaomi.push.thrift.c.a("EVENTS", 2, (short) 3, "events");
            g = new com.xiaomi.push.thrift.c.a[]{a, b, c};
            d = new HashMap();
            Iterator it = EnumSet.allOf(com.xiaomi.push.thrift.c.a.class).iterator();
            while (it.hasNext()) {
                com.xiaomi.push.thrift.c.a aVar = (com.xiaomi.push.thrift.c.a) it.next();
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
        e = new k("StatsEvents");
        f = new org.apache.thrift.protocol.c("uuid", (byte) 11, (short) 1);
        g = new org.apache.thrift.protocol.c("operator", (byte) 11, (short) 2);
        h = new org.apache.thrift.protocol.c("events", (byte) 15, (short) 3);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.a, new org.apache.thrift.meta_data.b("uuid", (byte) 1, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.b, new org.apache.thrift.meta_data.b("operator", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.c, new org.apache.thrift.meta_data.b("events", (byte) 1, new d((byte) 15, new g((byte) 12, b.class))));
        d = Collections.unmodifiableMap(enumMap);
        org.apache.thrift.meta_data.b.a(c.class, d);
    }

    public c(String str, List<b> list) {
        this();
        this.a = str;
        this.c = list;
    }

    public c a(String str) {
        this.b = str;
        return this;
    }

    public void a(f fVar) {
        fVar.g();
        while (true) {
            org.apache.thrift.protocol.c i = fVar.i();
            if (i.b != null) {
                switch (i.c) {
                    case SimpleLog.LOG_LEVEL_TRACE:
                        if (i.b == (byte) 11) {
                            this.a = fVar.w();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case SimpleLog.LOG_LEVEL_DEBUG:
                        if (i.b == (byte) 11) {
                            this.b = fVar.w();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case MqttConnectOptions.MQTT_VERSION_3_1:
                        if (i.b == 15) {
                            org.apache.thrift.protocol.d m = fVar.m();
                            this.c = new ArrayList(m.b);
                            for (int i2 = 0; i2 < m.b; i2++) {
                                b bVar = new b();
                                bVar.a(fVar);
                                this.c.add(bVar);
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
                d();
                return;
            }
        }
    }

    public boolean a() {
        return this.a != null;
    }

    public boolean a(c cVar) {
        if (cVar == null) {
            return false;
        }
        boolean a = a();
        boolean a2 = cVar.a();
        if ((a || a2) && (!a || !a2 || !this.a.equals(cVar.a))) {
            return false;
        }
        a = b();
        a2 = cVar.b();
        if ((a || a2) && (!a || !a2 || !this.b.equals(cVar.b))) {
            return false;
        }
        a = c();
        a2 = cVar.c();
        return !(a || a2) || (a && a2 && this.c.equals(cVar.c));
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
            compareTo = org.apache.thrift.c.a(this.a, cVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(cVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.c.a(this.b, cVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(cVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.c.a(this.c, cVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(f fVar) {
        d();
        fVar.a(e);
        if (this.a != null) {
            fVar.a(f);
            fVar.a(this.a);
            fVar.b();
        }
        if (this.b != null && b()) {
            fVar.a(g);
            fVar.a(this.b);
            fVar.b();
        }
        if (this.c != null) {
            fVar.a(h);
            fVar.a(new org.apache.thrift.protocol.d((byte) 12, this.c.size()));
            for (b bVar : this.c) {
                bVar.b(fVar);
            }
            fVar.e();
            fVar.b();
        }
        fVar.c();
        fVar.a();
    }

    public boolean b() {
        return this.b != null;
    }

    public boolean c() {
        return this.c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((c) obj);
    }

    public void d() {
        if (this.a == null) {
            throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'uuid' was not present! Struct: ").append(toString()).toString());
        } else if (this.c == null) {
            throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'events' was not present! Struct: ").append(toString()).toString());
        }
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof c)) ? a((c) obj) : false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("StatsEvents(");
        stringBuilder.append("uuid:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        if (b()) {
            stringBuilder.append(", ");
            stringBuilder.append("operator:");
            if (this.b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.b);
            }
        }
        stringBuilder.append(", ");
        stringBuilder.append("events:");
        if (this.c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }
}
