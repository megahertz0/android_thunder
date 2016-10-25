package com.xiaomi.xmpush.thrift;

import com.umeng.message.proguard.j;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.android.agoo.common.AgooConstants;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.thrift.b;
import org.apache.thrift.meta_data.g;
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.i;
import org.apache.thrift.protocol.k;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class z implements Serializable, Cloneable, b<z, a> {
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
    public String a;
    public j b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;

    public enum a {
        DEBUG((short) 1, "debug"),
        TARGET((short) 2, "target"),
        ID((short) 3, AgooConstants.MESSAGE_ID),
        APP_ID((short) 4, "appId"),
        REG_ID((short) 5, "regId"),
        APP_VERSION((short) 6, "appVersion"),
        PACKAGE_NAME((short) 7, "packageName"),
        TOKEN((short) 8, "token"),
        DEVICE_ID((short) 9, "deviceId"),
        ALIAS_NAME((short) 10, "aliasName");
        private static final Map<String, com.xiaomi.xmpush.thrift.z.a> k;
        private final short l;
        private final String m;

        static {
            String str = "debug";
            a = new com.xiaomi.xmpush.thrift.z.a("DEBUG", 0, (short) 1, "debug");
            String str2 = "target";
            b = new com.xiaomi.xmpush.thrift.z.a("TARGET", 1, (short) 2, "target");
            str2 = AgooConstants.MESSAGE_ID;
            c = new com.xiaomi.xmpush.thrift.z.a("ID", 2, (short) 3, AgooConstants.MESSAGE_ID);
            str2 = "appId";
            d = new com.xiaomi.xmpush.thrift.z.a("APP_ID", 3, (short) 4, "appId");
            str2 = "regId";
            e = new com.xiaomi.xmpush.thrift.z.a("REG_ID", 4, (short) 5, "regId");
            str = "appVersion";
            f = new com.xiaomi.xmpush.thrift.z.a("APP_VERSION", 5, (short) 6, "appVersion");
            String str3 = "packageName";
            g = new com.xiaomi.xmpush.thrift.z.a("PACKAGE_NAME", 6, (short) 7, "packageName");
            str3 = "token";
            h = new com.xiaomi.xmpush.thrift.z.a("TOKEN", 7, (short) 8, "token");
            str3 = "deviceId";
            i = new com.xiaomi.xmpush.thrift.z.a("DEVICE_ID", 8, (short) 9, "deviceId");
            str3 = "aliasName";
            j = new com.xiaomi.xmpush.thrift.z.a("ALIAS_NAME", 9, (short) 10, "aliasName");
            n = new com.xiaomi.xmpush.thrift.z.a[]{a, b, c, d, e, f, g, h, i, j};
            k = new HashMap();
            Iterator it = EnumSet.allOf(com.xiaomi.xmpush.thrift.z.a.class).iterator();
            while (it.hasNext()) {
                com.xiaomi.xmpush.thrift.z.a aVar = (com.xiaomi.xmpush.thrift.z.a) it.next();
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
        l = new k("XmPushActionUnRegistration");
        m = new c("debug", (byte) 11, (short) 1);
        n = new c("target", (byte) 12, (short) 2);
        o = new c(AgooConstants.MESSAGE_ID, (byte) 11, (short) 3);
        p = new c("appId", (byte) 11, (short) 4);
        q = new c("regId", (byte) 11, (short) 5);
        r = new c("appVersion", (byte) 11, (short) 6);
        s = new c("packageName", (byte) 11, (short) 7);
        t = new c("token", (byte) 11, (short) 8);
        u = new c("deviceId", (byte) 11, (short) 9);
        v = new c("aliasName", (byte) 11, (short) 10);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.a, new org.apache.thrift.meta_data.b("debug", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.b, new org.apache.thrift.meta_data.b("target", (byte) 2, new g((byte) 12, j.class)));
        enumMap.put(a.c, new org.apache.thrift.meta_data.b(AgooConstants.MESSAGE_ID, (byte) 1, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.d, new org.apache.thrift.meta_data.b("appId", (byte) 1, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.e, new org.apache.thrift.meta_data.b("regId", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.f, new org.apache.thrift.meta_data.b("appVersion", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.g, new org.apache.thrift.meta_data.b("packageName", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.h, new org.apache.thrift.meta_data.b("token", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.i, new org.apache.thrift.meta_data.b("deviceId", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.j, new org.apache.thrift.meta_data.b("aliasName", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        k = Collections.unmodifiableMap(enumMap);
        org.apache.thrift.meta_data.b.a(z.class, k);
    }

    public z a(String str) {
        this.c = str;
        return this;
    }

    public void a(f fVar) {
        fVar.g();
        while (true) {
            c i = fVar.i();
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
                        if (i.b == 12) {
                            this.b = new j();
                            this.b.a(fVar);
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case MqttConnectOptions.MQTT_VERSION_3_1:
                        if (i.b == (byte) 11) {
                            this.c = fVar.w();
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
                        if (i.b == (byte) 11) {
                            this.f = fVar.w();
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
                        if (i.b == (byte) 11) {
                            this.i = fVar.w();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case SpdyProtocol.PUBKEY_SEQ_OPEN:
                        if (i.b == (byte) 11) {
                            this.j = fVar.w();
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
                k();
                return;
            }
        }
    }

    public boolean a() {
        return this.a != null;
    }

    public boolean a(z zVar) {
        if (zVar == null) {
            return false;
        }
        boolean a = a();
        boolean a2 = zVar.a();
        if ((a || a2) && (!a || !a2 || !this.a.equals(zVar.a))) {
            return false;
        }
        a = b();
        a2 = zVar.b();
        if ((a || a2) && (!a || !a2 || !this.b.a(zVar.b))) {
            return false;
        }
        a = c();
        a2 = zVar.c();
        if ((a || a2) && (!a || !a2 || !this.c.equals(zVar.c))) {
            return false;
        }
        a = d();
        a2 = zVar.d();
        if ((a || a2) && (!a || !a2 || !this.d.equals(zVar.d))) {
            return false;
        }
        a = e();
        a2 = zVar.e();
        if ((a || a2) && (!a || !a2 || !this.e.equals(zVar.e))) {
            return false;
        }
        a = f();
        a2 = zVar.f();
        if ((a || a2) && (!a || !a2 || !this.f.equals(zVar.f))) {
            return false;
        }
        a = g();
        a2 = zVar.g();
        if ((a || a2) && (!a || !a2 || !this.g.equals(zVar.g))) {
            return false;
        }
        a = h();
        a2 = zVar.h();
        if ((a || a2) && (!a || !a2 || !this.h.equals(zVar.h))) {
            return false;
        }
        a = i();
        a2 = zVar.i();
        if ((a || a2) && (!a || !a2 || !this.i.equals(zVar.i))) {
            return false;
        }
        a = j();
        a2 = zVar.j();
        return !(a || a2) || (a && a2 && this.j.equals(zVar.j));
    }

    public int b(z zVar) {
        if (!getClass().equals(zVar.getClass())) {
            return getClass().getName().compareTo(zVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(zVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.c.a(this.a, zVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(zVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.c.a(this.b, zVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(zVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.c.a(this.c, zVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(zVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.c.a(this.d, zVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(zVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.c.a(this.e, zVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(zVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.c.a(this.f, zVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(zVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.c.a(this.g, zVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(h()).compareTo(Boolean.valueOf(zVar.h()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (h()) {
            compareTo = org.apache.thrift.c.a(this.h, zVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(i()).compareTo(Boolean.valueOf(zVar.i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (i()) {
            compareTo = org.apache.thrift.c.a(this.i, zVar.i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(j()).compareTo(Boolean.valueOf(zVar.j()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (j()) {
            compareTo = org.apache.thrift.c.a(this.j, zVar.j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public z b(String str) {
        this.d = str;
        return this;
    }

    public void b(f fVar) {
        k();
        fVar.a(l);
        if (this.a != null && a()) {
            fVar.a(m);
            fVar.a(this.a);
            fVar.b();
        }
        if (this.b != null && b()) {
            fVar.a(n);
            this.b.b(fVar);
            fVar.b();
        }
        if (this.c != null) {
            fVar.a(o);
            fVar.a(this.c);
            fVar.b();
        }
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
        if (this.f != null && f()) {
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
        if (this.i != null && i()) {
            fVar.a(u);
            fVar.a(this.i);
            fVar.b();
        }
        if (this.j != null && j()) {
            fVar.a(v);
            fVar.a(this.j);
            fVar.b();
        }
        fVar.c();
        fVar.a();
    }

    public boolean b() {
        return this.b != null;
    }

    public z c(String str) {
        this.e = str;
        return this;
    }

    public boolean c() {
        return this.c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((z) obj);
    }

    public z d(String str) {
        this.g = str;
        return this;
    }

    public boolean d() {
        return this.d != null;
    }

    public z e(String str) {
        this.h = str;
        return this;
    }

    public boolean e() {
        return this.e != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof z)) ? a((z) obj) : false;
    }

    public boolean f() {
        return this.f != null;
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
        return this.i != null;
    }

    public boolean j() {
        return this.j != null;
    }

    public void k() {
        if (this.c == null) {
            throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'id' was not present! Struct: ").append(toString()).toString());
        } else if (this.d == null) {
            throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'appId' was not present! Struct: ").append(toString()).toString());
        }
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionUnRegistration(");
        Object obj2 = 1;
        if (a()) {
            stringBuilder.append("debug:");
            if (this.a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.a);
            }
            obj2 = null;
        }
        if (b()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("target:");
            if (this.b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.b);
            }
        } else {
            obj = obj2;
        }
        if (obj == null) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("id:");
        if (this.c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("appId:");
        if (this.d == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.d);
        }
        if (e()) {
            stringBuilder.append(", ");
            stringBuilder.append("regId:");
            if (this.e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.e);
            }
        }
        if (f()) {
            stringBuilder.append(", ");
            stringBuilder.append("appVersion:");
            if (this.f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f);
            }
        }
        if (g()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.g);
            }
        }
        if (h()) {
            stringBuilder.append(", ");
            stringBuilder.append("token:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
        }
        if (i()) {
            stringBuilder.append(", ");
            stringBuilder.append("deviceId:");
            if (this.i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.i);
            }
        }
        if (j()) {
            stringBuilder.append(", ");
            stringBuilder.append("aliasName:");
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
