package com.xiaomi.xmpush.thrift;

import com.umeng.message.proguard.j;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.android.agoo.common.AgooConstants;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.thrift.b;
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.i;
import org.apache.thrift.protocol.k;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class g implements Serializable, Cloneable, b<g, a> {
    public static final Map<a, org.apache.thrift.meta_data.b> h;
    private static final k i;
    private static final c j;
    private static final c k;
    private static final c l;
    private static final c m;
    private static final c n;
    private static final c o;
    private static final c p;
    public int a;
    public int b;
    public boolean c;
    public int d;
    public long e;
    public String f;
    public boolean g;
    private BitSet q;

    public enum a {
        KEY((short) 1, "key"),
        TYPE((short) 2, AgooConstants.MESSAGE_TYPE),
        CLEAR((short) 3, "clear"),
        INT_VALUE((short) 4, "intValue"),
        LONG_VALUE((short) 5, "longValue"),
        STRING_VALUE((short) 6, "stringValue"),
        BOOL_VALUE((short) 7, "boolValue");
        private static final Map<String, com.xiaomi.xmpush.thrift.g.a> h;
        private final short i;
        private final String j;

        static {
            String str = "key";
            a = new com.xiaomi.xmpush.thrift.g.a("KEY", 0, (short) 1, "key");
            String str2 = AgooConstants.MESSAGE_TYPE;
            b = new com.xiaomi.xmpush.thrift.g.a("TYPE", 1, (short) 2, AgooConstants.MESSAGE_TYPE);
            str2 = "clear";
            c = new com.xiaomi.xmpush.thrift.g.a("CLEAR", 2, (short) 3, "clear");
            str2 = "intValue";
            d = new com.xiaomi.xmpush.thrift.g.a("INT_VALUE", 3, (short) 4, "intValue");
            str2 = "longValue";
            e = new com.xiaomi.xmpush.thrift.g.a("LONG_VALUE", 4, (short) 5, "longValue");
            str = "stringValue";
            f = new com.xiaomi.xmpush.thrift.g.a("STRING_VALUE", 5, (short) 6, "stringValue");
            String str3 = "boolValue";
            g = new com.xiaomi.xmpush.thrift.g.a("BOOL_VALUE", 6, (short) 7, "boolValue");
            k = new com.xiaomi.xmpush.thrift.g.a[]{a, b, c, d, e, f, g};
            h = new HashMap();
            Iterator it = EnumSet.allOf(com.xiaomi.xmpush.thrift.g.a.class).iterator();
            while (it.hasNext()) {
                com.xiaomi.xmpush.thrift.g.a aVar = (com.xiaomi.xmpush.thrift.g.a) it.next();
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
        i = new k("OnlineConfigItem");
        j = new c("key", (byte) 8, (short) 1);
        k = new c(AgooConstants.MESSAGE_TYPE, (byte) 8, (short) 2);
        l = new c("clear", (byte) 2, (short) 3);
        m = new c("intValue", (byte) 8, (short) 4);
        n = new c("longValue", (byte) 10, (short) 5);
        o = new c("stringValue", (byte) 11, (short) 6);
        p = new c("boolValue", (byte) 2, (short) 7);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.a, new org.apache.thrift.meta_data.b("key", (byte) 2, new org.apache.thrift.meta_data.c((byte) 8)));
        enumMap.put(a.b, new org.apache.thrift.meta_data.b(AgooConstants.MESSAGE_TYPE, (byte) 2, new org.apache.thrift.meta_data.c((byte) 8)));
        enumMap.put(a.c, new org.apache.thrift.meta_data.b("clear", (byte) 2, new org.apache.thrift.meta_data.c((byte) 2)));
        enumMap.put(a.d, new org.apache.thrift.meta_data.b("intValue", (byte) 2, new org.apache.thrift.meta_data.c((byte) 8)));
        enumMap.put(a.e, new org.apache.thrift.meta_data.b("longValue", (byte) 2, new org.apache.thrift.meta_data.c((byte) 10)));
        enumMap.put(a.f, new org.apache.thrift.meta_data.b("stringValue", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.g, new org.apache.thrift.meta_data.b("boolValue", (byte) 2, new org.apache.thrift.meta_data.c((byte) 2)));
        h = Collections.unmodifiableMap(enumMap);
        org.apache.thrift.meta_data.b.a(g.class, h);
    }

    public g() {
        this.q = new BitSet(6);
    }

    public int a() {
        return this.a;
    }

    public void a(f fVar) {
        fVar.g();
        while (true) {
            c i = fVar.i();
            if (i.b != null) {
                switch (i.c) {
                    case SimpleLog.LOG_LEVEL_TRACE:
                        if (i.b == (byte) 8) {
                            this.a = fVar.t();
                            a(true);
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case SimpleLog.LOG_LEVEL_DEBUG:
                        if (i.b == (byte) 8) {
                            this.b = fVar.t();
                            b(true);
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case MqttConnectOptions.MQTT_VERSION_3_1:
                        if (i.b == (byte) 2) {
                            this.c = fVar.q();
                            c(true);
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case MqttConnectOptions.MQTT_VERSION_3_1_1:
                        if (i.b == (byte) 8) {
                            this.d = fVar.t();
                            d(true);
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case SimpleLog.LOG_LEVEL_ERROR:
                        if (i.b == 10) {
                            this.e = fVar.u();
                            e(true);
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case SimpleLog.LOG_LEVEL_FATAL:
                        if (i.b == 11) {
                            this.f = fVar.w();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case SimpleLog.LOG_LEVEL_OFF:
                        if (i.b == (byte) 2) {
                            this.g = fVar.q();
                            f(true);
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
                n();
                return;
            }
        }
    }

    public void a(boolean z) {
        this.q.set(0, z);
    }

    public boolean a(g gVar) {
        if (gVar == null) {
            return false;
        }
        boolean b = b();
        boolean b2 = gVar.b();
        if ((b || b2) && (!b || !b2 || this.a != gVar.a)) {
            return false;
        }
        b = d();
        b2 = gVar.d();
        if ((b || b2) && (!b || !b2 || this.b != gVar.b)) {
            return false;
        }
        b = e();
        b2 = gVar.e();
        if ((b || b2) && (!b || !b2 || this.c != gVar.c)) {
            return false;
        }
        b = g();
        b2 = gVar.g();
        if ((b || b2) && (!b || !b2 || this.d != gVar.d)) {
            return false;
        }
        b = i();
        b2 = gVar.i();
        if ((b || b2) && (!b || !b2 || this.e != gVar.e)) {
            return false;
        }
        b = k();
        b2 = gVar.k();
        if ((b || b2) && (!b || !b2 || !this.f.equals(gVar.f))) {
            return false;
        }
        b = m();
        b2 = gVar.m();
        return !(b || b2) || (b && b2 && this.g == gVar.g);
    }

    public int b(g gVar) {
        if (!getClass().equals(gVar.getClass())) {
            return getClass().getName().compareTo(gVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(gVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.c.a(this.a, gVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(gVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.c.a(this.b, gVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(gVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.c.a(this.c, gVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(gVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.c.a(this.d, gVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(i()).compareTo(Boolean.valueOf(gVar.i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (i()) {
            compareTo = org.apache.thrift.c.a(this.e, gVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(k()).compareTo(Boolean.valueOf(gVar.k()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (k()) {
            compareTo = org.apache.thrift.c.a(this.f, gVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m()).compareTo(Boolean.valueOf(gVar.m()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m()) {
            compareTo = org.apache.thrift.c.a(this.g, gVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(f fVar) {
        n();
        fVar.a(i);
        if (b()) {
            fVar.a(j);
            fVar.a(this.a);
            fVar.b();
        }
        if (d()) {
            fVar.a(k);
            fVar.a(this.b);
            fVar.b();
        }
        if (e()) {
            fVar.a(l);
            fVar.a(this.c);
            fVar.b();
        }
        if (g()) {
            fVar.a(m);
            fVar.a(this.d);
            fVar.b();
        }
        if (i()) {
            fVar.a(n);
            fVar.a(this.e);
            fVar.b();
        }
        if (this.f != null && k()) {
            fVar.a(o);
            fVar.a(this.f);
            fVar.b();
        }
        if (m()) {
            fVar.a(p);
            fVar.a(this.g);
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

    public int c() {
        return this.b;
    }

    public void c(boolean z) {
        this.q.set(SimpleLog.LOG_LEVEL_DEBUG, z);
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((g) obj);
    }

    public void d(boolean z) {
        this.q.set(MqttConnectOptions.MQTT_VERSION_3_1, z);
    }

    public boolean d() {
        return this.q.get(1);
    }

    public void e(boolean z) {
        this.q.set(MqttConnectOptions.MQTT_VERSION_3_1_1, z);
    }

    public boolean e() {
        return this.q.get(SimpleLog.LOG_LEVEL_DEBUG);
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof g)) ? a((g) obj) : false;
    }

    public int f() {
        return this.d;
    }

    public void f(boolean z) {
        this.q.set(SimpleLog.LOG_LEVEL_ERROR, z);
    }

    public boolean g() {
        return this.q.get(MqttConnectOptions.MQTT_VERSION_3_1);
    }

    public long h() {
        return this.e;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.q.get(MqttConnectOptions.MQTT_VERSION_3_1_1);
    }

    public String j() {
        return this.f;
    }

    public boolean k() {
        return this.f != null;
    }

    public boolean l() {
        return this.g;
    }

    public boolean m() {
        return this.q.get(SimpleLog.LOG_LEVEL_ERROR);
    }

    public void n() {
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("OnlineConfigItem(");
        Object obj2 = 1;
        if (b()) {
            stringBuilder.append("key:");
            stringBuilder.append(this.a);
            obj2 = null;
        }
        if (d()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("type:");
            stringBuilder.append(this.b);
            obj2 = null;
        }
        if (e()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("clear:");
            stringBuilder.append(this.c);
            obj2 = null;
        }
        if (g()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("intValue:");
            stringBuilder.append(this.d);
            obj2 = null;
        }
        if (i()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("longValue:");
            stringBuilder.append(this.e);
            obj2 = null;
        }
        if (k()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("stringValue:");
            if (this.f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f);
            }
        } else {
            obj = obj2;
        }
        if (m()) {
            if (obj == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("boolValue:");
            stringBuilder.append(this.g);
        }
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }
}
