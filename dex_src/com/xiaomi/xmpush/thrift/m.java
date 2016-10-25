package com.xiaomi.xmpush.thrift;

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

public class m implements Serializable, Cloneable, b<m, a> {
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
    public String a;
    public j b;
    public String c;
    public String d;
    public String e;
    public List<String> f;
    public String g;
    public String h;

    public enum a {
        DEBUG((short) 1, "debug"),
        TARGET((short) 2, "target"),
        ID((short) 3, AgooConstants.MESSAGE_ID),
        APP_ID((short) 4, "appId"),
        CMD_NAME((short) 5, "cmdName"),
        CMD_ARGS((short) 6, "cmdArgs"),
        PACKAGE_NAME((short) 7, "packageName"),
        CATEGORY((short) 9, "category");
        private static final Map<String, com.xiaomi.xmpush.thrift.m.a> i;
        private final short j;
        private final String k;

        static {
            String str = "debug";
            a = new com.xiaomi.xmpush.thrift.m.a("DEBUG", 0, (short) 1, "debug");
            String str2 = "target";
            b = new com.xiaomi.xmpush.thrift.m.a("TARGET", 1, (short) 2, "target");
            str2 = AgooConstants.MESSAGE_ID;
            c = new com.xiaomi.xmpush.thrift.m.a("ID", 2, (short) 3, AgooConstants.MESSAGE_ID);
            str2 = "appId";
            d = new com.xiaomi.xmpush.thrift.m.a("APP_ID", 3, (short) 4, "appId");
            str2 = "cmdName";
            e = new com.xiaomi.xmpush.thrift.m.a("CMD_NAME", 4, (short) 5, "cmdName");
            str = "cmdArgs";
            f = new com.xiaomi.xmpush.thrift.m.a("CMD_ARGS", 5, (short) 6, "cmdArgs");
            String str3 = "packageName";
            g = new com.xiaomi.xmpush.thrift.m.a("PACKAGE_NAME", 6, (short) 7, "packageName");
            str3 = "category";
            h = new com.xiaomi.xmpush.thrift.m.a("CATEGORY", 7, (short) 9, "category");
            l = new com.xiaomi.xmpush.thrift.m.a[]{a, b, c, d, e, f, g, h};
            i = new HashMap();
            Iterator it = EnumSet.allOf(com.xiaomi.xmpush.thrift.m.a.class).iterator();
            while (it.hasNext()) {
                com.xiaomi.xmpush.thrift.m.a aVar = (com.xiaomi.xmpush.thrift.m.a) it.next();
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
        j = new k("XmPushActionCommand");
        k = new c("debug", (byte) 11, (short) 1);
        l = new c("target", (byte) 12, (short) 2);
        m = new c(AgooConstants.MESSAGE_ID, (byte) 11, (short) 3);
        n = new c("appId", (byte) 11, (short) 4);
        o = new c("cmdName", (byte) 11, (short) 5);
        p = new c("cmdArgs", (byte) 15, (short) 6);
        q = new c("packageName", (byte) 11, (short) 7);
        r = new c("category", (byte) 11, (short) 9);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.a, new org.apache.thrift.meta_data.b("debug", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.b, new org.apache.thrift.meta_data.b("target", (byte) 2, new g((byte) 12, j.class)));
        enumMap.put(a.c, new org.apache.thrift.meta_data.b(AgooConstants.MESSAGE_ID, (byte) 1, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.d, new org.apache.thrift.meta_data.b("appId", (byte) 1, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.e, new org.apache.thrift.meta_data.b("cmdName", (byte) 1, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.f, new org.apache.thrift.meta_data.b("cmdArgs", (byte) 2, new d((byte) 15, new org.apache.thrift.meta_data.c((byte) 11))));
        enumMap.put(a.g, new org.apache.thrift.meta_data.b("packageName", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.h, new org.apache.thrift.meta_data.b("category", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        i = Collections.unmodifiableMap(enumMap);
        org.apache.thrift.meta_data.b.a(m.class, i);
    }

    public m a(String str) {
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
                        if (i.b == 15) {
                            org.apache.thrift.protocol.d m = fVar.m();
                            this.f = new ArrayList(m.b);
                            for (int i2 = 0; i2 < m.b; i2++) {
                                this.f.add(fVar.w());
                            }
                            fVar.n();
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
                    case SpdyProtocol.PUBKEY_PSEQ_ADASH:
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
                i();
                return;
            }
        }
    }

    public boolean a() {
        return this.a != null;
    }

    public boolean a(m mVar) {
        if (mVar == null) {
            return false;
        }
        boolean a = a();
        boolean a2 = mVar.a();
        if ((a || a2) && (!a || !a2 || !this.a.equals(mVar.a))) {
            return false;
        }
        a = b();
        a2 = mVar.b();
        if ((a || a2) && (!a || !a2 || !this.b.a(mVar.b))) {
            return false;
        }
        a = c();
        a2 = mVar.c();
        if ((a || a2) && (!a || !a2 || !this.c.equals(mVar.c))) {
            return false;
        }
        a = d();
        a2 = mVar.d();
        if ((a || a2) && (!a || !a2 || !this.d.equals(mVar.d))) {
            return false;
        }
        a = e();
        a2 = mVar.e();
        if ((a || a2) && (!a || !a2 || !this.e.equals(mVar.e))) {
            return false;
        }
        a = f();
        a2 = mVar.f();
        if ((a || a2) && (!a || !a2 || !this.f.equals(mVar.f))) {
            return false;
        }
        a = g();
        a2 = mVar.g();
        if ((a || a2) && (!a || !a2 || !this.g.equals(mVar.g))) {
            return false;
        }
        a = h();
        a2 = mVar.h();
        return !(a || a2) || (a && a2 && this.h.equals(mVar.h));
    }

    public int b(m mVar) {
        if (!getClass().equals(mVar.getClass())) {
            return getClass().getName().compareTo(mVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(mVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.c.a(this.a, mVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(mVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.c.a(this.b, mVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(mVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.c.a(this.c, mVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(mVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.c.a(this.d, mVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(mVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.c.a(this.e, mVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(mVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.c.a(this.f, mVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(mVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.c.a(this.g, mVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(h()).compareTo(Boolean.valueOf(mVar.h()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (h()) {
            compareTo = org.apache.thrift.c.a(this.h, mVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public m b(String str) {
        this.d = str;
        return this;
    }

    public void b(f fVar) {
        i();
        fVar.a(j);
        if (this.a != null && a()) {
            fVar.a(k);
            fVar.a(this.a);
            fVar.b();
        }
        if (this.b != null && b()) {
            fVar.a(l);
            this.b.b(fVar);
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
        if (this.e != null) {
            fVar.a(o);
            fVar.a(this.e);
            fVar.b();
        }
        if (this.f != null && f()) {
            fVar.a(p);
            fVar.a(new org.apache.thrift.protocol.d((byte) 11, this.f.size()));
            for (String str : this.f) {
                fVar.a(str);
            }
            fVar.e();
            fVar.b();
        }
        if (this.g != null && g()) {
            fVar.a(q);
            fVar.a(this.g);
            fVar.b();
        }
        if (this.h != null && h()) {
            fVar.a(r);
            fVar.a(this.h);
            fVar.b();
        }
        fVar.c();
        fVar.a();
    }

    public boolean b() {
        return this.b != null;
    }

    public m c(String str) {
        this.e = str;
        return this;
    }

    public boolean c() {
        return this.c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((m) obj);
    }

    public void d(String str) {
        if (this.f == null) {
            this.f = new ArrayList();
        }
        this.f.add(str);
    }

    public boolean d() {
        return this.d != null;
    }

    public m e(String str) {
        this.g = str;
        return this;
    }

    public boolean e() {
        return this.e != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof m)) ? a((m) obj) : false;
    }

    public m f(String str) {
        this.h = str;
        return this;
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

    public void i() {
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
        StringBuilder stringBuilder = new StringBuilder("XmPushActionCommand(");
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
        if (f()) {
            stringBuilder.append(", ");
            stringBuilder.append("cmdArgs:");
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
            stringBuilder.append("category:");
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
