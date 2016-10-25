package com.xiaomi.xmpush.thrift;

import com.umeng.message.proguard.j;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
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
import org.apache.thrift.b;
import org.apache.thrift.meta_data.g;
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.i;
import org.apache.thrift.protocol.k;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class h implements Serializable, Cloneable, b<h, a> {
    public static final Map<a, org.apache.thrift.meta_data.b> i;
    private static final k j;
    private static final c k;
    private static final c l;
    private static final c m;
    private static final c n;
    private static final c o;
    private static final c p;
    private static final c q;
    private static final c r;
    public j a;
    public String b;
    public String c;
    public String d;
    public long e;
    public long f;
    public String g;
    public String h;
    private BitSet s;

    public enum a {
        TO((short) 1, SocializeProtocolConstants.PROTOCOL_KEY_TO),
        ID((short) 2, AgooConstants.MESSAGE_ID),
        APP_ID((short) 3, "appId"),
        PAYLOAD((short) 4, "payload"),
        CREATE_AT((short) 5, "createAt"),
        TTL((short) 6, "ttl"),
        COLLAPSE_KEY((short) 7, "collapseKey"),
        PACKAGE_NAME((short) 8, "packageName");
        private static final Map<String, com.xiaomi.xmpush.thrift.h.a> i;
        private final short j;
        private final String k;

        static {
            String str = SocializeProtocolConstants.PROTOCOL_KEY_TO;
            a = new com.xiaomi.xmpush.thrift.h.a("TO", 0, (short) 1, SocializeProtocolConstants.PROTOCOL_KEY_TO);
            String str2 = AgooConstants.MESSAGE_ID;
            b = new com.xiaomi.xmpush.thrift.h.a("ID", 1, (short) 2, AgooConstants.MESSAGE_ID);
            str2 = "appId";
            c = new com.xiaomi.xmpush.thrift.h.a("APP_ID", 2, (short) 3, "appId");
            str2 = "payload";
            d = new com.xiaomi.xmpush.thrift.h.a("PAYLOAD", 3, (short) 4, "payload");
            str2 = "createAt";
            e = new com.xiaomi.xmpush.thrift.h.a("CREATE_AT", 4, (short) 5, "createAt");
            str = "ttl";
            f = new com.xiaomi.xmpush.thrift.h.a("TTL", 5, (short) 6, "ttl");
            String str3 = "collapseKey";
            g = new com.xiaomi.xmpush.thrift.h.a("COLLAPSE_KEY", 6, (short) 7, "collapseKey");
            str3 = "packageName";
            h = new com.xiaomi.xmpush.thrift.h.a("PACKAGE_NAME", 7, (short) 8, "packageName");
            l = new com.xiaomi.xmpush.thrift.h.a[]{a, b, c, d, e, f, g, h};
            i = new HashMap();
            Iterator it = EnumSet.allOf(com.xiaomi.xmpush.thrift.h.a.class).iterator();
            while (it.hasNext()) {
                com.xiaomi.xmpush.thrift.h.a aVar = (com.xiaomi.xmpush.thrift.h.a) it.next();
                i.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.j = s;
            this.k = str;
        }

        public final String a() {
            return this.k;
        }
    }

    static {
        j = new k("PushMessage");
        k = new c(SocializeProtocolConstants.PROTOCOL_KEY_TO, (byte) 12, (short) 1);
        l = new c(AgooConstants.MESSAGE_ID, (byte) 11, (short) 2);
        m = new c("appId", (byte) 11, (short) 3);
        n = new c("payload", (byte) 11, (short) 4);
        o = new c("createAt", (byte) 10, (short) 5);
        p = new c("ttl", (byte) 10, (short) 6);
        q = new c("collapseKey", (byte) 11, (short) 7);
        r = new c("packageName", (byte) 11, (short) 8);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.a, new org.apache.thrift.meta_data.b(SocializeProtocolConstants.PROTOCOL_KEY_TO, (byte) 2, new g((byte) 12, j.class)));
        enumMap.put(a.b, new org.apache.thrift.meta_data.b(AgooConstants.MESSAGE_ID, (byte) 1, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.c, new org.apache.thrift.meta_data.b("appId", (byte) 1, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.d, new org.apache.thrift.meta_data.b("payload", (byte) 1, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.e, new org.apache.thrift.meta_data.b("createAt", (byte) 2, new org.apache.thrift.meta_data.c((byte) 10)));
        enumMap.put(a.f, new org.apache.thrift.meta_data.b("ttl", (byte) 2, new org.apache.thrift.meta_data.c((byte) 10)));
        enumMap.put(a.g, new org.apache.thrift.meta_data.b("collapseKey", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.h, new org.apache.thrift.meta_data.b("packageName", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        i = Collections.unmodifiableMap(enumMap);
        org.apache.thrift.meta_data.b.a(h.class, i);
    }

    public h() {
        this.s = new BitSet(2);
    }

    public void a(f fVar) {
        fVar.g();
        while (true) {
            c i = fVar.i();
            if (i.b != null) {
                switch (i.c) {
                    case SimpleLog.LOG_LEVEL_TRACE:
                        if (i.b == 12) {
                            this.a = new j();
                            this.a.a(fVar);
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
                        if (i.b == (byte) 10) {
                            this.e = fVar.u();
                            a(true);
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case SimpleLog.LOG_LEVEL_FATAL:
                        if (i.b == (byte) 10) {
                            this.f = fVar.u();
                            b(true);
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
                    default:
                        i.a(fVar, i.b);
                        break;
                }
                fVar.j();
            } else {
                fVar.h();
                m();
                return;
            }
        }
    }

    public void a(boolean z) {
        this.s.set(0, z);
    }

    public boolean a() {
        return this.a != null;
    }

    public boolean a(h hVar) {
        if (hVar == null) {
            return false;
        }
        boolean a = a();
        boolean a2 = hVar.a();
        if ((a || a2) && (!a || !a2 || !this.a.a(hVar.a))) {
            return false;
        }
        a = c();
        a2 = hVar.c();
        if ((a || a2) && (!a || !a2 || !this.b.equals(hVar.b))) {
            return false;
        }
        a = e();
        a2 = hVar.e();
        if ((a || a2) && (!a || !a2 || !this.c.equals(hVar.c))) {
            return false;
        }
        a = g();
        a2 = hVar.g();
        if ((a || a2) && (!a || !a2 || !this.d.equals(hVar.d))) {
            return false;
        }
        a = i();
        a2 = hVar.i();
        if ((a || a2) && (!a || !a2 || this.e != hVar.e)) {
            return false;
        }
        a = j();
        a2 = hVar.j();
        if ((a || a2) && (!a || !a2 || this.f != hVar.f)) {
            return false;
        }
        a = k();
        a2 = hVar.k();
        if ((a || a2) && (!a || !a2 || !this.g.equals(hVar.g))) {
            return false;
        }
        a = l();
        a2 = hVar.l();
        return !(a || a2) || (a && a2 && this.h.equals(hVar.h));
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
            compareTo = org.apache.thrift.c.a(this.a, hVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(hVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.c.a(this.b, hVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(hVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.c.a(this.c, hVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(hVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.c.a(this.d, hVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(i()).compareTo(Boolean.valueOf(hVar.i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (i()) {
            compareTo = org.apache.thrift.c.a(this.e, hVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(j()).compareTo(Boolean.valueOf(hVar.j()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (j()) {
            compareTo = org.apache.thrift.c.a(this.f, hVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(k()).compareTo(Boolean.valueOf(hVar.k()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (k()) {
            compareTo = org.apache.thrift.c.a(this.g, hVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(l()).compareTo(Boolean.valueOf(hVar.l()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (l()) {
            compareTo = org.apache.thrift.c.a(this.h, hVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public String b() {
        return this.b;
    }

    public void b(f fVar) {
        m();
        fVar.a(j);
        if (this.a != null && a()) {
            fVar.a(k);
            this.a.b(fVar);
            fVar.b();
        }
        if (this.b != null) {
            fVar.a(l);
            fVar.a(this.b);
            fVar.b();
        }
        if (this.c != null) {
            fVar.a(m);
            fVar.a(this.c);
            fVar.b();
        }
        if (this.d != null) {
            fVar.a(n);
            fVar.a(this.d);
            fVar.b();
        }
        if (i()) {
            fVar.a(o);
            fVar.a(this.e);
            fVar.b();
        }
        if (j()) {
            fVar.a(p);
            fVar.a(this.f);
            fVar.b();
        }
        if (this.g != null && k()) {
            fVar.a(q);
            fVar.a(this.g);
            fVar.b();
        }
        if (this.h != null && l()) {
            fVar.a(r);
            fVar.a(this.h);
            fVar.b();
        }
        fVar.c();
        fVar.a();
    }

    public void b(boolean z) {
        this.s.set(1, z);
    }

    public boolean c() {
        return this.b != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((h) obj);
    }

    public String d() {
        return this.c;
    }

    public boolean e() {
        return this.c != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof h)) ? a((h) obj) : false;
    }

    public String f() {
        return this.d;
    }

    public boolean g() {
        return this.d != null;
    }

    public long h() {
        return this.e;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.s.get(0);
    }

    public boolean j() {
        return this.s.get(1);
    }

    public boolean k() {
        return this.g != null;
    }

    public boolean l() {
        return this.h != null;
    }

    public void m() {
        if (this.b == null) {
            throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'id' was not present! Struct: ").append(toString()).toString());
        } else if (this.c == null) {
            throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'appId' was not present! Struct: ").append(toString()).toString());
        } else if (this.d == null) {
            throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'payload' was not present! Struct: ").append(toString()).toString());
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("PushMessage(");
        Object obj = 1;
        if (a()) {
            stringBuilder.append("to:");
            if (this.a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.a);
            }
            obj = null;
        }
        if (obj == null) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("id:");
        if (this.b == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.b);
        }
        stringBuilder.append(", ");
        stringBuilder.append("appId:");
        if (this.c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("payload:");
        if (this.d == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.d);
        }
        if (i()) {
            stringBuilder.append(", ");
            stringBuilder.append("createAt:");
            stringBuilder.append(this.e);
        }
        if (j()) {
            stringBuilder.append(", ");
            stringBuilder.append("ttl:");
            stringBuilder.append(this.f);
        }
        if (k()) {
            stringBuilder.append(", ");
            stringBuilder.append("collapseKey:");
            if (this.g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.g);
            }
        }
        if (l()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
        }
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }
}
