package com.xiaomi.xmpush.thrift;

import com.umeng.message.proguard.j;
import com.xunlei.xllib.R;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.android.agoo.common.AgooConstants;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.thrift.b;
import org.apache.thrift.meta_data.d;
import org.apache.thrift.meta_data.g;
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.i;
import org.apache.thrift.protocol.k;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class n implements Serializable, Cloneable, b<n, a> {
    public static final Map<a, org.apache.thrift.meta_data.b> l;
    private static final k m;
    private static final c n;
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
    public String a;
    public j b;
    public String c;
    public String d;
    public String e;
    public m f;
    public long g;
    public String h;
    public String i;
    public List<String> j;
    public String k;
    private BitSet y;

    public enum a {
        DEBUG((short) 1, "debug"),
        TARGET((short) 2, "target"),
        ID((short) 3, AgooConstants.MESSAGE_ID),
        APP_ID((short) 4, "appId"),
        CMD_NAME((short) 5, "cmdName"),
        REQUEST((short) 6, "request"),
        ERROR_CODE((short) 7, "errorCode"),
        REASON((short) 8, "reason"),
        PACKAGE_NAME((short) 9, "packageName"),
        CMD_ARGS((short) 10, "cmdArgs"),
        CATEGORY((short) 12, "category");
        private static final Map<String, com.xiaomi.xmpush.thrift.n.a> l;
        private final short m;
        private final String n;

        static {
            String str = "debug";
            a = new com.xiaomi.xmpush.thrift.n.a("DEBUG", 0, (short) 1, "debug");
            String str2 = "target";
            b = new com.xiaomi.xmpush.thrift.n.a("TARGET", 1, (short) 2, "target");
            str2 = AgooConstants.MESSAGE_ID;
            c = new com.xiaomi.xmpush.thrift.n.a("ID", 2, (short) 3, AgooConstants.MESSAGE_ID);
            str2 = "appId";
            d = new com.xiaomi.xmpush.thrift.n.a("APP_ID", 3, (short) 4, "appId");
            str2 = "cmdName";
            e = new com.xiaomi.xmpush.thrift.n.a("CMD_NAME", 4, (short) 5, "cmdName");
            str = "request";
            f = new com.xiaomi.xmpush.thrift.n.a("REQUEST", 5, (short) 6, "request");
            String str3 = "errorCode";
            g = new com.xiaomi.xmpush.thrift.n.a("ERROR_CODE", 6, (short) 7, "errorCode");
            str3 = "reason";
            h = new com.xiaomi.xmpush.thrift.n.a("REASON", 7, (short) 8, "reason");
            str3 = "packageName";
            i = new com.xiaomi.xmpush.thrift.n.a("PACKAGE_NAME", 8, (short) 9, "packageName");
            str3 = "cmdArgs";
            j = new com.xiaomi.xmpush.thrift.n.a("CMD_ARGS", 9, (short) 10, "cmdArgs");
            str3 = "category";
            k = new com.xiaomi.xmpush.thrift.n.a("CATEGORY", 10, (short) 12, "category");
            o = new com.xiaomi.xmpush.thrift.n.a[]{a, b, c, d, e, f, g, h, i, j, k};
            l = new HashMap();
            Iterator it = EnumSet.allOf(com.xiaomi.xmpush.thrift.n.a.class).iterator();
            while (it.hasNext()) {
                com.xiaomi.xmpush.thrift.n.a aVar = (com.xiaomi.xmpush.thrift.n.a) it.next();
                l.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.m = s;
            this.n = str;
        }

        public final String a() {
            return this.n;
        }
    }

    static {
        m = new k("XmPushActionCommandResult");
        n = new c("debug", (byte) 11, (short) 1);
        o = new c("target", (byte) 12, (short) 2);
        p = new c(AgooConstants.MESSAGE_ID, (byte) 11, (short) 3);
        q = new c("appId", (byte) 11, (short) 4);
        r = new c("cmdName", (byte) 11, (short) 5);
        s = new c("request", (byte) 12, (short) 6);
        t = new c("errorCode", (byte) 10, (short) 7);
        u = new c("reason", (byte) 11, (short) 8);
        v = new c("packageName", (byte) 11, (short) 9);
        w = new c("cmdArgs", (byte) 15, (short) 10);
        x = new c("category", (byte) 11, (short) 12);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.a, new org.apache.thrift.meta_data.b("debug", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.b, new org.apache.thrift.meta_data.b("target", (byte) 2, new g((byte) 12, j.class)));
        enumMap.put(a.c, new org.apache.thrift.meta_data.b(AgooConstants.MESSAGE_ID, (byte) 1, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.d, new org.apache.thrift.meta_data.b("appId", (byte) 1, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.e, new org.apache.thrift.meta_data.b("cmdName", (byte) 1, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.f, new org.apache.thrift.meta_data.b("request", (byte) 2, new g((byte) 12, m.class)));
        enumMap.put(a.g, new org.apache.thrift.meta_data.b("errorCode", (byte) 1, new org.apache.thrift.meta_data.c((byte) 10)));
        enumMap.put(a.h, new org.apache.thrift.meta_data.b("reason", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.i, new org.apache.thrift.meta_data.b("packageName", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.j, new org.apache.thrift.meta_data.b("cmdArgs", (byte) 2, new d((byte) 15, new org.apache.thrift.meta_data.c((byte) 11))));
        enumMap.put(a.k, new org.apache.thrift.meta_data.b("category", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        l = Collections.unmodifiableMap(enumMap);
        org.apache.thrift.meta_data.b.a(n.class, l);
    }

    public n() {
        this.y = new BitSet(1);
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
                        if (i.b == (byte) 12) {
                            this.f = new m();
                            this.f.a(fVar);
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case SimpleLog.LOG_LEVEL_OFF:
                        if (i.b == 10) {
                            this.g = fVar.u();
                            a(true);
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
                        if (i.b == 15) {
                            org.apache.thrift.protocol.d m = fVar.m();
                            this.j = new ArrayList(m.b);
                            for (int i2 = 0; i2 < m.b; i2++) {
                                this.j.add(fVar.w());
                            }
                            fVar.n();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case R.styleable.Toolbar_titleMargins:
                        if (i.b == (byte) 11) {
                            this.k = fVar.w();
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
                if (h()) {
                    o();
                    return;
                }
                throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'errorCode' was not found in serialized data! Struct: ").append(toString()).toString());
            }
        }
    }

    public void a(boolean z) {
        this.y.set(0, z);
    }

    public boolean a() {
        return this.a != null;
    }

    public boolean a(n nVar) {
        if (nVar == null) {
            return false;
        }
        boolean a = a();
        boolean a2 = nVar.a();
        if ((a || a2) && (!a || !a2 || !this.a.equals(nVar.a))) {
            return false;
        }
        a = b();
        a2 = nVar.b();
        if ((a || a2) && (!a || !a2 || !this.b.a(nVar.b))) {
            return false;
        }
        a = c();
        a2 = nVar.c();
        if ((a || a2) && (!a || !a2 || !this.c.equals(nVar.c))) {
            return false;
        }
        a = d();
        a2 = nVar.d();
        if ((a || a2) && (!a || !a2 || !this.d.equals(nVar.d))) {
            return false;
        }
        a = f();
        a2 = nVar.f();
        if ((a || a2) && (!a || !a2 || !this.e.equals(nVar.e))) {
            return false;
        }
        a = g();
        a2 = nVar.g();
        if (((a || a2) && (!a || !a2 || !this.f.a(nVar.f))) || this.g != nVar.g) {
            return false;
        }
        a = i();
        a2 = nVar.i();
        if ((a || a2) && (!a || !a2 || !this.h.equals(nVar.h))) {
            return false;
        }
        a = j();
        a2 = nVar.j();
        if ((a || a2) && (!a || !a2 || !this.i.equals(nVar.i))) {
            return false;
        }
        a = l();
        a2 = nVar.l();
        if ((a || a2) && (!a || !a2 || !this.j.equals(nVar.j))) {
            return false;
        }
        a = n();
        a2 = nVar.n();
        return !(a || a2) || (a && a2 && this.k.equals(nVar.k));
    }

    public int b(n nVar) {
        if (!getClass().equals(nVar.getClass())) {
            return getClass().getName().compareTo(nVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(nVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.c.a(this.a, nVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(nVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.c.a(this.b, nVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(nVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.c.a(this.c, nVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(nVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.c.a(this.d, nVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(nVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.c.a(this.e, nVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(nVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.c.a(this.f, nVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(h()).compareTo(Boolean.valueOf(nVar.h()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (h()) {
            compareTo = org.apache.thrift.c.a(this.g, nVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(i()).compareTo(Boolean.valueOf(nVar.i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (i()) {
            compareTo = org.apache.thrift.c.a(this.h, nVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(j()).compareTo(Boolean.valueOf(nVar.j()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (j()) {
            compareTo = org.apache.thrift.c.a(this.i, nVar.i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(l()).compareTo(Boolean.valueOf(nVar.l()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (l()) {
            compareTo = org.apache.thrift.c.a(this.j, nVar.j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(n()).compareTo(Boolean.valueOf(nVar.n()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (n()) {
            compareTo = org.apache.thrift.c.a(this.k, nVar.k);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(f fVar) {
        o();
        fVar.a(m);
        if (this.a != null && a()) {
            fVar.a(n);
            fVar.a(this.a);
            fVar.b();
        }
        if (this.b != null && b()) {
            fVar.a(o);
            this.b.b(fVar);
            fVar.b();
        }
        if (this.c != null) {
            fVar.a(p);
            fVar.a(this.c);
            fVar.b();
        }
        if (this.d != null) {
            fVar.a(q);
            fVar.a(this.d);
            fVar.b();
        }
        if (this.e != null) {
            fVar.a(r);
            fVar.a(this.e);
            fVar.b();
        }
        if (this.f != null && g()) {
            fVar.a(s);
            this.f.b(fVar);
            fVar.b();
        }
        fVar.a(t);
        fVar.a(this.g);
        fVar.b();
        if (this.h != null && i()) {
            fVar.a(u);
            fVar.a(this.h);
            fVar.b();
        }
        if (this.i != null && j()) {
            fVar.a(v);
            fVar.a(this.i);
            fVar.b();
        }
        if (this.j != null && l()) {
            fVar.a(w);
            fVar.a(new org.apache.thrift.protocol.d((byte) 11, this.j.size()));
            for (String str : this.j) {
                fVar.a(str);
            }
            fVar.e();
            fVar.b();
        }
        if (this.k != null && n()) {
            fVar.a(x);
            fVar.a(this.k);
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
        return b((n) obj);
    }

    public boolean d() {
        return this.d != null;
    }

    public String e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof n)) ? a((n) obj) : false;
    }

    public boolean f() {
        return this.e != null;
    }

    public boolean g() {
        return this.f != null;
    }

    public boolean h() {
        return this.y.get(0);
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.h != null;
    }

    public boolean j() {
        return this.i != null;
    }

    public List<String> k() {
        return this.j;
    }

    public boolean l() {
        return this.j != null;
    }

    public String m() {
        return this.k;
    }

    public boolean n() {
        return this.k != null;
    }

    public void o() {
        if (this.c == null) {
            throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'id' was not present! Struct: ").append(toString()).toString());
        } else if (this.d == null) {
            throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'appId' was not present! Struct: ").append(toString()).toString());
        } else if (this.e == null) {
            throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'cmdName' was not present! Struct: ").append(toString()).toString());
        }
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionCommandResult(");
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
        stringBuilder.append(", ");
        stringBuilder.append("cmdName:");
        if (this.e == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.e);
        }
        if (g()) {
            stringBuilder.append(", ");
            stringBuilder.append("request:");
            if (this.f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f);
            }
        }
        stringBuilder.append(", ");
        stringBuilder.append("errorCode:");
        stringBuilder.append(this.g);
        if (i()) {
            stringBuilder.append(", ");
            stringBuilder.append("reason:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
        }
        if (j()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.i);
            }
        }
        if (l()) {
            stringBuilder.append(", ");
            stringBuilder.append("cmdArgs:");
            if (this.j == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.j);
            }
        }
        if (n()) {
            stringBuilder.append(", ");
            stringBuilder.append("category:");
            if (this.k == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.k);
            }
        }
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }
}
