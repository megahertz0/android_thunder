package com.xiaomi.xmpush.thrift;

import com.umeng.message.proguard.j;
import com.xunlei.xllib.R;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.android.agoo.common.AgooConstants;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.thrift.b;
import org.apache.thrift.meta_data.e;
import org.apache.thrift.meta_data.g;
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.i;
import org.apache.thrift.protocol.k;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class w implements Serializable, Cloneable, b<w, a> {
    public static final Map<a, org.apache.thrift.meta_data.b> m;
    private static final k n;
    private static final c o;
    private static final c p;
    private static final c q;
    private static final c r;
    private static final c s;
    private static final c t;
    private static final c u;
    private static final c v;
    private static final c w;
    private static final c x;
    private static final c y;
    private static final c z;
    private BitSet A;
    public String a;
    public j b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public h h;
    public boolean i;
    public Map<String, String> j;
    public String k;
    public String l;

    public enum a {
        DEBUG((short) 1, "debug"),
        TARGET((short) 2, "target"),
        ID((short) 3, AgooConstants.MESSAGE_ID),
        APP_ID((short) 4, "appId"),
        PACKAGE_NAME((short) 5, "packageName"),
        TOPIC((short) 6, "topic"),
        ALIAS_NAME((short) 7, "aliasName"),
        MESSAGE((short) 8, j.C),
        NEED_ACK((short) 9, "needAck"),
        PARAMS((short) 10, "params"),
        CATEGORY((short) 11, "category"),
        USER_ACCOUNT((short) 12, "userAccount");
        private static final Map<String, com.xiaomi.xmpush.thrift.w.a> m;
        private final short n;
        private final String o;

        static {
            String str = "debug";
            a = new com.xiaomi.xmpush.thrift.w.a("DEBUG", 0, (short) 1, "debug");
            String str2 = "target";
            b = new com.xiaomi.xmpush.thrift.w.a("TARGET", 1, (short) 2, "target");
            str2 = AgooConstants.MESSAGE_ID;
            c = new com.xiaomi.xmpush.thrift.w.a("ID", 2, (short) 3, AgooConstants.MESSAGE_ID);
            str2 = "appId";
            d = new com.xiaomi.xmpush.thrift.w.a("APP_ID", 3, (short) 4, "appId");
            str2 = "packageName";
            e = new com.xiaomi.xmpush.thrift.w.a("PACKAGE_NAME", 4, (short) 5, "packageName");
            str = "topic";
            f = new com.xiaomi.xmpush.thrift.w.a("TOPIC", 5, (short) 6, "topic");
            String str3 = "aliasName";
            g = new com.xiaomi.xmpush.thrift.w.a("ALIAS_NAME", 6, (short) 7, "aliasName");
            str3 = j.C;
            h = new com.xiaomi.xmpush.thrift.w.a("MESSAGE", 7, (short) 8, j.C);
            str3 = "needAck";
            i = new com.xiaomi.xmpush.thrift.w.a("NEED_ACK", 8, (short) 9, "needAck");
            str3 = "params";
            j = new com.xiaomi.xmpush.thrift.w.a("PARAMS", 9, (short) 10, "params");
            str3 = "category";
            k = new com.xiaomi.xmpush.thrift.w.a("CATEGORY", 10, (short) 11, "category");
            str3 = "userAccount";
            l = new com.xiaomi.xmpush.thrift.w.a("USER_ACCOUNT", 11, (short) 12, "userAccount");
            p = new com.xiaomi.xmpush.thrift.w.a[]{a, b, c, d, e, f, g, h, i, j, k, l};
            m = new HashMap();
            Iterator it = EnumSet.allOf(com.xiaomi.xmpush.thrift.w.a.class).iterator();
            while (it.hasNext()) {
                com.xiaomi.xmpush.thrift.w.a aVar = (com.xiaomi.xmpush.thrift.w.a) it.next();
                m.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.n = s;
            this.o = str;
        }

        public final String a() {
            return this.o;
        }
    }

    static {
        n = new k("XmPushActionSendMessage");
        o = new c("debug", (byte) 11, (short) 1);
        p = new c("target", (byte) 12, (short) 2);
        q = new c(AgooConstants.MESSAGE_ID, (byte) 11, (short) 3);
        r = new c("appId", (byte) 11, (short) 4);
        s = new c("packageName", (byte) 11, (short) 5);
        t = new c("topic", (byte) 11, (short) 6);
        u = new c("aliasName", (byte) 11, (short) 7);
        v = new c(j.C, (byte) 12, (short) 8);
        w = new c("needAck", (byte) 2, (short) 9);
        x = new c("params", (byte) 13, (short) 10);
        y = new c("category", (byte) 11, (short) 11);
        z = new c("userAccount", (byte) 11, (short) 12);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.a, new org.apache.thrift.meta_data.b("debug", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.b, new org.apache.thrift.meta_data.b("target", (byte) 2, new g((byte) 12, j.class)));
        enumMap.put(a.c, new org.apache.thrift.meta_data.b(AgooConstants.MESSAGE_ID, (byte) 1, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.d, new org.apache.thrift.meta_data.b("appId", (byte) 1, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.e, new org.apache.thrift.meta_data.b("packageName", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.f, new org.apache.thrift.meta_data.b("topic", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.g, new org.apache.thrift.meta_data.b("aliasName", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.h, new org.apache.thrift.meta_data.b(j.C, (byte) 2, new g((byte) 12, h.class)));
        enumMap.put(a.i, new org.apache.thrift.meta_data.b("needAck", (byte) 2, new org.apache.thrift.meta_data.c((byte) 2)));
        enumMap.put(a.j, new org.apache.thrift.meta_data.b("params", (byte) 2, new e((byte) 13, new org.apache.thrift.meta_data.c((byte) 11), new org.apache.thrift.meta_data.c((byte) 11))));
        enumMap.put(a.k, new org.apache.thrift.meta_data.b("category", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.l, new org.apache.thrift.meta_data.b("userAccount", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        m = Collections.unmodifiableMap(enumMap);
        org.apache.thrift.meta_data.b.a(w.class, m);
    }

    public w() {
        this.A = new BitSet(1);
        this.i = true;
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
                        if (i.b == (byte) 12) {
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
                        if (i.b == (byte) 12) {
                            this.h = new h();
                            this.h.a(fVar);
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case SpdyProtocol.PUBKEY_PSEQ_ADASH:
                        if (i.b == 2) {
                            this.i = fVar.q();
                            a(true);
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case SpdyProtocol.PUBKEY_SEQ_OPEN:
                        if (i.b == 13) {
                            org.apache.thrift.protocol.e k = fVar.k();
                            this.j = new HashMap(k.c * 2);
                            for (int i2 = 0; i2 < k.c; i2++) {
                                this.j.put(fVar.w(), fVar.w());
                            }
                            fVar.l();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case SpdyProtocol.PUBKEY_PSEQ_OPEN:
                        if (i.b == (byte) 11) {
                            this.k = fVar.w();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case R.styleable.Toolbar_titleMargins:
                        if (i.b == (byte) 11) {
                            this.l = fVar.w();
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
                t();
                return;
            }
        }
    }

    public void a(boolean z) {
        this.A.set(0, z);
    }

    public boolean a() {
        return this.a != null;
    }

    public boolean a(w wVar) {
        if (wVar == null) {
            return false;
        }
        boolean a = a();
        boolean a2 = wVar.a();
        if ((a || a2) && (!a || !a2 || !this.a.equals(wVar.a))) {
            return false;
        }
        a = b();
        a2 = wVar.b();
        if ((a || a2) && (!a || !a2 || !this.b.a(wVar.b))) {
            return false;
        }
        a = d();
        a2 = wVar.d();
        if ((a || a2) && (!a || !a2 || !this.c.equals(wVar.c))) {
            return false;
        }
        a = f();
        a2 = wVar.f();
        if ((a || a2) && (!a || !a2 || !this.d.equals(wVar.d))) {
            return false;
        }
        a = g();
        a2 = wVar.g();
        if ((a || a2) && (!a || !a2 || !this.e.equals(wVar.e))) {
            return false;
        }
        a = i();
        a2 = wVar.i();
        if ((a || a2) && (!a || !a2 || !this.f.equals(wVar.f))) {
            return false;
        }
        a = k();
        a2 = wVar.k();
        if ((a || a2) && (!a || !a2 || !this.g.equals(wVar.g))) {
            return false;
        }
        a = m();
        a2 = wVar.m();
        if ((a || a2) && (!a || !a2 || !this.h.a(wVar.h))) {
            return false;
        }
        a = n();
        a2 = wVar.n();
        if ((a || a2) && (!a || !a2 || this.i != wVar.i)) {
            return false;
        }
        a = o();
        a2 = wVar.o();
        if ((a || a2) && (!a || !a2 || !this.j.equals(wVar.j))) {
            return false;
        }
        a = q();
        a2 = wVar.q();
        if ((a || a2) && (!a || !a2 || !this.k.equals(wVar.k))) {
            return false;
        }
        a = s();
        a2 = wVar.s();
        return !(a || a2) || (a && a2 && this.l.equals(wVar.l));
    }

    public int b(w wVar) {
        if (!getClass().equals(wVar.getClass())) {
            return getClass().getName().compareTo(wVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(wVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.c.a(this.a, wVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(wVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.c.a(this.b, wVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(wVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.c.a(this.c, wVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(wVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.c.a(this.d, wVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(wVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.c.a(this.e, wVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(i()).compareTo(Boolean.valueOf(wVar.i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (i()) {
            compareTo = org.apache.thrift.c.a(this.f, wVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(k()).compareTo(Boolean.valueOf(wVar.k()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (k()) {
            compareTo = org.apache.thrift.c.a(this.g, wVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m()).compareTo(Boolean.valueOf(wVar.m()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m()) {
            compareTo = org.apache.thrift.c.a(this.h, wVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(n()).compareTo(Boolean.valueOf(wVar.n()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (n()) {
            compareTo = org.apache.thrift.c.a(this.i, wVar.i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(o()).compareTo(Boolean.valueOf(wVar.o()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (o()) {
            compareTo = org.apache.thrift.c.a(this.j, wVar.j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(q()).compareTo(Boolean.valueOf(wVar.q()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (q()) {
            compareTo = org.apache.thrift.c.a(this.k, wVar.k);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(s()).compareTo(Boolean.valueOf(wVar.s()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (s()) {
            compareTo = org.apache.thrift.c.a(this.l, wVar.l);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(f fVar) {
        t();
        fVar.a(n);
        if (this.a != null && a()) {
            fVar.a(o);
            fVar.a(this.a);
            fVar.b();
        }
        if (this.b != null && b()) {
            fVar.a(p);
            this.b.b(fVar);
            fVar.b();
        }
        if (this.c != null) {
            fVar.a(q);
            fVar.a(this.c);
            fVar.b();
        }
        if (this.d != null) {
            fVar.a(r);
            fVar.a(this.d);
            fVar.b();
        }
        if (this.e != null && g()) {
            fVar.a(s);
            fVar.a(this.e);
            fVar.b();
        }
        if (this.f != null && i()) {
            fVar.a(t);
            fVar.a(this.f);
            fVar.b();
        }
        if (this.g != null && k()) {
            fVar.a(u);
            fVar.a(this.g);
            fVar.b();
        }
        if (this.h != null && m()) {
            fVar.a(v);
            this.h.b(fVar);
            fVar.b();
        }
        if (n()) {
            fVar.a(w);
            fVar.a(this.i);
            fVar.b();
        }
        if (this.j != null && o()) {
            fVar.a(x);
            fVar.a(new org.apache.thrift.protocol.e((byte) 11, (byte) 11, this.j.size()));
            for (Entry entry : this.j.entrySet()) {
                fVar.a((String) entry.getKey());
                fVar.a((String) entry.getValue());
            }
            fVar.d();
            fVar.b();
        }
        if (this.k != null && q()) {
            fVar.a(y);
            fVar.a(this.k);
            fVar.b();
        }
        if (this.l != null && s()) {
            fVar.a(z);
            fVar.a(this.l);
            fVar.b();
        }
        fVar.c();
        fVar.a();
    }

    public boolean b() {
        return this.b != null;
    }

    public String c() {
        return this.c;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((w) obj);
    }

    public boolean d() {
        return this.c != null;
    }

    public String e() {
        return this.d;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof w)) ? a((w) obj) : false;
    }

    public boolean f() {
        return this.d != null;
    }

    public boolean g() {
        return this.e != null;
    }

    public String h() {
        return this.f;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f != null;
    }

    public String j() {
        return this.g;
    }

    public boolean k() {
        return this.g != null;
    }

    public h l() {
        return this.h;
    }

    public boolean m() {
        return this.h != null;
    }

    public boolean n() {
        return this.A.get(0);
    }

    public boolean o() {
        return this.j != null;
    }

    public String p() {
        return this.k;
    }

    public boolean q() {
        return this.k != null;
    }

    public String r() {
        return this.l;
    }

    public boolean s() {
        return this.l != null;
    }

    public void t() {
        if (this.c == null) {
            throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'id' was not present! Struct: ").append(toString()).toString());
        } else if (this.d == null) {
            throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'appId' was not present! Struct: ").append(toString()).toString());
        }
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionSendMessage(");
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
        if (g()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.e);
            }
        }
        if (i()) {
            stringBuilder.append(", ");
            stringBuilder.append("topic:");
            if (this.f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f);
            }
        }
        if (k()) {
            stringBuilder.append(", ");
            stringBuilder.append("aliasName:");
            if (this.g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.g);
            }
        }
        if (m()) {
            stringBuilder.append(", ");
            stringBuilder.append("message:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
        }
        if (n()) {
            stringBuilder.append(", ");
            stringBuilder.append("needAck:");
            stringBuilder.append(this.i);
        }
        if (o()) {
            stringBuilder.append(", ");
            stringBuilder.append("params:");
            if (this.j == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.j);
            }
        }
        if (q()) {
            stringBuilder.append(", ");
            stringBuilder.append("category:");
            if (this.k == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.k);
            }
        }
        if (s()) {
            stringBuilder.append(", ");
            stringBuilder.append("userAccount:");
            if (this.l == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.l);
            }
        }
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }
}
