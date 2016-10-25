package com.xiaomi.push.thrift;

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
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.g;
import org.apache.thrift.protocol.i;
import org.apache.thrift.protocol.k;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class b implements Serializable, Cloneable, org.apache.thrift.b<b, a> {
    public static final Map<a, org.apache.thrift.meta_data.b> k;
    private static final k l;
    private static final c m;
    private static final c n;
    private static final c o;
    private static final c p;
    private static final c q;
    private static final c r;
    private static final c s;
    private static final c t;
    private static final c u;
    private static final c v;
    public byte a;
    public int b;
    public int c;
    public String d;
    public String e;
    public int f;
    public String g;
    public String h;
    public int i;
    public int j;
    private BitSet w;

    public enum a {
        CHID((short) 1, "chid"),
        TYPE((short) 2, AgooConstants.MESSAGE_TYPE),
        VALUE((short) 3, "value"),
        CONNPT((short) 4, "connpt"),
        HOST((short) 5, "host"),
        SUBVALUE((short) 6, "subvalue"),
        ANNOTATION((short) 7, "annotation"),
        USER((short) 8, "user"),
        TIME((short) 9, AgooConstants.MESSAGE_TIME),
        CLIENT_IP((short) 10, "clientIp");
        private static final Map<String, com.xiaomi.push.thrift.b.a> k;
        private final short l;
        private final String m;

        static {
            String str = "chid";
            a = new com.xiaomi.push.thrift.b.a("CHID", 0, (short) 1, "chid");
            String str2 = AgooConstants.MESSAGE_TYPE;
            b = new com.xiaomi.push.thrift.b.a("TYPE", 1, (short) 2, AgooConstants.MESSAGE_TYPE);
            str2 = "value";
            c = new com.xiaomi.push.thrift.b.a("VALUE", 2, (short) 3, "value");
            str2 = "connpt";
            d = new com.xiaomi.push.thrift.b.a("CONNPT", 3, (short) 4, "connpt");
            str2 = "host";
            e = new com.xiaomi.push.thrift.b.a("HOST", 4, (short) 5, "host");
            str = "subvalue";
            f = new com.xiaomi.push.thrift.b.a("SUBVALUE", 5, (short) 6, "subvalue");
            String str3 = "annotation";
            g = new com.xiaomi.push.thrift.b.a("ANNOTATION", 6, (short) 7, "annotation");
            str3 = "user";
            h = new com.xiaomi.push.thrift.b.a("USER", 7, (short) 8, "user");
            str3 = AgooConstants.MESSAGE_TIME;
            i = new com.xiaomi.push.thrift.b.a("TIME", 8, (short) 9, AgooConstants.MESSAGE_TIME);
            str3 = "clientIp";
            j = new com.xiaomi.push.thrift.b.a("CLIENT_IP", 9, (short) 10, "clientIp");
            n = new com.xiaomi.push.thrift.b.a[]{a, b, c, d, e, f, g, h, i, j};
            k = new HashMap();
            Iterator it = EnumSet.allOf(com.xiaomi.push.thrift.b.a.class).iterator();
            while (it.hasNext()) {
                com.xiaomi.push.thrift.b.a aVar = (com.xiaomi.push.thrift.b.a) it.next();
                k.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.l = s;
            this.m = str;
        }

        public final String a() {
            return this.m;
        }
    }

    static {
        l = new k("StatsEvent");
        m = new c("chid", (byte) 3, (short) 1);
        n = new c(AgooConstants.MESSAGE_TYPE, (byte) 8, (short) 2);
        o = new c("value", (byte) 8, (short) 3);
        p = new c("connpt", (byte) 11, (short) 4);
        q = new c("host", (byte) 11, (short) 5);
        r = new c("subvalue", (byte) 8, (short) 6);
        s = new c("annotation", (byte) 11, (short) 7);
        t = new c("user", (byte) 11, (short) 8);
        u = new c(AgooConstants.MESSAGE_TIME, (byte) 8, (short) 9);
        v = new c("clientIp", (byte) 8, (short) 10);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.a, new org.apache.thrift.meta_data.b("chid", (byte) 1, new org.apache.thrift.meta_data.c((byte) 3)));
        enumMap.put(a.b, new org.apache.thrift.meta_data.b(AgooConstants.MESSAGE_TYPE, (byte) 1, new org.apache.thrift.meta_data.c((byte) 8)));
        enumMap.put(a.c, new org.apache.thrift.meta_data.b("value", (byte) 1, new org.apache.thrift.meta_data.c((byte) 8)));
        enumMap.put(a.d, new org.apache.thrift.meta_data.b("connpt", (byte) 1, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.e, new org.apache.thrift.meta_data.b("host", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.f, new org.apache.thrift.meta_data.b("subvalue", (byte) 2, new org.apache.thrift.meta_data.c((byte) 8)));
        enumMap.put(a.g, new org.apache.thrift.meta_data.b("annotation", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.h, new org.apache.thrift.meta_data.b("user", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.i, new org.apache.thrift.meta_data.b(AgooConstants.MESSAGE_TIME, (byte) 2, new org.apache.thrift.meta_data.c((byte) 8)));
        enumMap.put(a.j, new org.apache.thrift.meta_data.b("clientIp", (byte) 2, new org.apache.thrift.meta_data.c((byte) 8)));
        k = Collections.unmodifiableMap(enumMap);
        org.apache.thrift.meta_data.b.a(b.class, k);
    }

    public b() {
        this.w = new BitSet(6);
    }

    public b a(byte b) {
        this.a = b;
        a(true);
        return this;
    }

    public b a(int i) {
        this.b = i;
        b(true);
        return this;
    }

    public b a(String str) {
        this.d = str;
        return this;
    }

    public void a(f fVar) {
        fVar.g();
        while (true) {
            c i = fVar.i();
            if (i.b != null) {
                switch (i.c) {
                    case SimpleLog.LOG_LEVEL_TRACE:
                        if (i.b == 3) {
                            this.a = fVar.r();
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
                        if (i.b == (byte) 8) {
                            this.c = fVar.t();
                            c(true);
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case MqttConnectOptions.MQTT_VERSION_3_1_1:
                        if (i.b == (byte) 11) {
                            this.d = fVar.w();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case SimpleLog.LOG_LEVEL_ERROR:
                        if (i.b == (byte) 11) {
                            this.e = fVar.w();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case SimpleLog.LOG_LEVEL_FATAL:
                        if (i.b == (byte) 8) {
                            this.f = fVar.t();
                            d(true);
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case SimpleLog.LOG_LEVEL_OFF:
                        if (i.b == (byte) 11) {
                            this.g = fVar.w();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case SpdyProtocol.PUBKEY_SEQ_ADASH:
                        if (i.b == (byte) 11) {
                            this.h = fVar.w();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case SpdyProtocol.PUBKEY_PSEQ_ADASH:
                        if (i.b == (byte) 8) {
                            this.i = fVar.t();
                            e(true);
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case SpdyProtocol.PUBKEY_SEQ_OPEN:
                        if (i.b == (byte) 8) {
                            this.j = fVar.t();
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
                if (!a()) {
                    throw new g(new StringBuilder("Required field 'chid' was not found in serialized data! Struct: ").append(toString()).toString());
                } else if (!b()) {
                    throw new g(new StringBuilder("Required field 'type' was not found in serialized data! Struct: ").append(toString()).toString());
                } else if (c()) {
                    k();
                    return;
                } else {
                    throw new g(new StringBuilder("Required field 'value' was not found in serialized data! Struct: ").append(toString()).toString());
                }
            }
        }
    }

    public void a(boolean z) {
        this.w.set(0, z);
    }

    public boolean a() {
        return this.w.get(0);
    }

    public boolean a(b bVar) {
        if (bVar == null || this.a != bVar.a || this.b != bVar.b || this.c != bVar.c) {
            return false;
        }
        boolean d = d();
        boolean d2 = bVar.d();
        if ((d || d2) && (!d || !d2 || !this.d.equals(bVar.d))) {
            return false;
        }
        d = e();
        d2 = bVar.e();
        if ((d || d2) && (!d || !d2 || !this.e.equals(bVar.e))) {
            return false;
        }
        d = f();
        d2 = bVar.f();
        if ((d || d2) && (!d || !d2 || this.f != bVar.f)) {
            return false;
        }
        d = g();
        d2 = bVar.g();
        if ((d || d2) && (!d || !d2 || !this.g.equals(bVar.g))) {
            return false;
        }
        d = h();
        d2 = bVar.h();
        if ((d || d2) && (!d || !d2 || !this.h.equals(bVar.h))) {
            return false;
        }
        d = i();
        d2 = bVar.i();
        if ((d || d2) && (!d || !d2 || this.i != bVar.i)) {
            return false;
        }
        d = j();
        d2 = bVar.j();
        return !(d || d2) || (d && d2 && this.j == bVar.j);
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
            compareTo = org.apache.thrift.c.a(this.a, bVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(bVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.c.a(this.b, bVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(bVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.c.a(this.c, bVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(bVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.c.a(this.d, bVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(bVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.c.a(this.e, bVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(bVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.c.a(this.f, bVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(bVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.c.a(this.g, bVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(h()).compareTo(Boolean.valueOf(bVar.h()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (h()) {
            compareTo = org.apache.thrift.c.a(this.h, bVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(i()).compareTo(Boolean.valueOf(bVar.i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (i()) {
            compareTo = org.apache.thrift.c.a(this.i, bVar.i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(j()).compareTo(Boolean.valueOf(bVar.j()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (j()) {
            compareTo = org.apache.thrift.c.a(this.j, bVar.j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public b b(int i) {
        this.c = i;
        c(true);
        return this;
    }

    public b b(String str) {
        this.e = str;
        return this;
    }

    public void b(f fVar) {
        k();
        fVar.a(l);
        fVar.a(m);
        fVar.a(this.a);
        fVar.b();
        fVar.a(n);
        fVar.a(this.b);
        fVar.b();
        fVar.a(o);
        fVar.a(this.c);
        fVar.b();
        if (this.d != null) {
            fVar.a(p);
            fVar.a(this.d);
            fVar.b();
        }
        if (this.e != null && e()) {
            fVar.a(q);
            fVar.a(this.e);
            fVar.b();
        }
        if (f()) {
            fVar.a(r);
            fVar.a(this.f);
            fVar.b();
        }
        if (this.g != null && g()) {
            fVar.a(s);
            fVar.a(this.g);
            fVar.b();
        }
        if (this.h != null && h()) {
            fVar.a(t);
            fVar.a(this.h);
            fVar.b();
        }
        if (i()) {
            fVar.a(u);
            fVar.a(this.i);
            fVar.b();
        }
        if (j()) {
            fVar.a(v);
            fVar.a(this.j);
            fVar.b();
        }
        fVar.c();
        fVar.a();
    }

    public void b(boolean z) {
        this.w.set(1, z);
    }

    public boolean b() {
        return this.w.get(1);
    }

    public b c(int i) {
        this.f = i;
        d(true);
        return this;
    }

    public b c(String str) {
        this.g = str;
        return this;
    }

    public void c(boolean z) {
        this.w.set(SimpleLog.LOG_LEVEL_DEBUG, z);
    }

    public boolean c() {
        return this.w.get(SimpleLog.LOG_LEVEL_DEBUG);
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((b) obj);
    }

    public b d(int i) {
        this.i = i;
        e(true);
        return this;
    }

    public b d(String str) {
        this.h = str;
        return this;
    }

    public void d(boolean z) {
        this.w.set(MqttConnectOptions.MQTT_VERSION_3_1, z);
    }

    public boolean d() {
        return this.d != null;
    }

    public b e(int i) {
        this.j = i;
        f(true);
        return this;
    }

    public void e(boolean z) {
        this.w.set(MqttConnectOptions.MQTT_VERSION_3_1_1, z);
    }

    public boolean e() {
        return this.e != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof b)) ? a((b) obj) : false;
    }

    public void f(boolean z) {
        this.w.set(SimpleLog.LOG_LEVEL_ERROR, z);
    }

    public boolean f() {
        return this.w.get(MqttConnectOptions.MQTT_VERSION_3_1);
    }

    public boolean g() {
        return this.g != null;
    }

    public boolean h() {
        return this.h != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.w.get(MqttConnectOptions.MQTT_VERSION_3_1_1);
    }

    public boolean j() {
        return this.w.get(SimpleLog.LOG_LEVEL_ERROR);
    }

    public void k() {
        if (this.d == null) {
            throw new g(new StringBuilder("Required field 'connpt' was not present! Struct: ").append(toString()).toString());
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("StatsEvent(");
        stringBuilder.append("chid:");
        stringBuilder.append(this.a);
        stringBuilder.append(", ");
        stringBuilder.append("type:");
        stringBuilder.append(this.b);
        stringBuilder.append(", ");
        stringBuilder.append("value:");
        stringBuilder.append(this.c);
        stringBuilder.append(", ");
        stringBuilder.append("connpt:");
        if (this.d == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.d);
        }
        if (e()) {
            stringBuilder.append(", ");
            stringBuilder.append("host:");
            if (this.e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.e);
            }
        }
        if (f()) {
            stringBuilder.append(", ");
            stringBuilder.append("subvalue:");
            stringBuilder.append(this.f);
        }
        if (g()) {
            stringBuilder.append(", ");
            stringBuilder.append("annotation:");
            if (this.g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.g);
            }
        }
        if (h()) {
            stringBuilder.append(", ");
            stringBuilder.append("user:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
        }
        if (i()) {
            stringBuilder.append(", ");
            stringBuilder.append("time:");
            stringBuilder.append(this.i);
        }
        if (j()) {
            stringBuilder.append(", ");
            stringBuilder.append("clientIp:");
            stringBuilder.append(this.j);
        }
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }
}
