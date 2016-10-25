package com.xiaomi.xmpush.thrift;

import com.umeng.message.proguard.j;
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
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.thrift.b;
import org.apache.thrift.meta_data.d;
import org.apache.thrift.meta_data.g;
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.i;
import org.apache.thrift.protocol.k;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class q implements Serializable, Cloneable, b<q, a> {
    public static final Map<a, org.apache.thrift.meta_data.b> d;
    private static final k e;
    private static final c f;
    private static final c g;
    private static final c h;
    public List<e> a;
    public long b;
    public String c;
    private BitSet i;

    public enum a {
        NORMAL_CONFIGS((short) 1, "normalConfigs"),
        APP_ID((short) 4, "appId"),
        PACKAGE_NAME((short) 5, "packageName");
        private static final Map<String, com.xiaomi.xmpush.thrift.q.a> d;
        private final short e;
        private final String f;

        static {
            String str = "normalConfigs";
            a = new com.xiaomi.xmpush.thrift.q.a("NORMAL_CONFIGS", 0, (short) 1, "normalConfigs");
            String str2 = "appId";
            b = new com.xiaomi.xmpush.thrift.q.a("APP_ID", 1, (short) 4, "appId");
            str2 = "packageName";
            c = new com.xiaomi.xmpush.thrift.q.a("PACKAGE_NAME", 2, (short) 5, "packageName");
            g = new com.xiaomi.xmpush.thrift.q.a[]{a, b, c};
            d = new HashMap();
            Iterator it = EnumSet.allOf(com.xiaomi.xmpush.thrift.q.a.class).iterator();
            while (it.hasNext()) {
                com.xiaomi.xmpush.thrift.q.a aVar = (com.xiaomi.xmpush.thrift.q.a) it.next();
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
        e = new k("XmPushActionNormalConfig");
        f = new c("normalConfigs", (byte) 15, (short) 1);
        g = new c("appId", (byte) 10, (short) 4);
        h = new c("packageName", (byte) 11, (short) 5);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.a, new org.apache.thrift.meta_data.b("normalConfigs", (byte) 1, new d((byte) 15, new g((byte) 12, e.class))));
        enumMap.put(a.b, new org.apache.thrift.meta_data.b("appId", (byte) 2, new org.apache.thrift.meta_data.c((byte) 10)));
        enumMap.put(a.c, new org.apache.thrift.meta_data.b("packageName", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        d = Collections.unmodifiableMap(enumMap);
        org.apache.thrift.meta_data.b.a(q.class, d);
    }

    public q() {
        this.i = new BitSet(1);
    }

    public List<e> a() {
        return this.a;
    }

    public void a(f fVar) {
        fVar.g();
        while (true) {
            c i = fVar.i();
            if (i.b != null) {
                switch (i.c) {
                    case SimpleLog.LOG_LEVEL_TRACE:
                        if (i.b == 15) {
                            org.apache.thrift.protocol.d m = fVar.m();
                            this.a = new ArrayList(m.b);
                            for (int i2 = 0; i2 < m.b; i2++) {
                                e eVar = new e();
                                eVar.a(fVar);
                                this.a.add(eVar);
                            }
                            fVar.n();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case MqttConnectOptions.MQTT_VERSION_3_1_1:
                        if (i.b == 10) {
                            this.b = fVar.u();
                            a(true);
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case SimpleLog.LOG_LEVEL_ERROR:
                        if (i.b == 11) {
                            this.c = fVar.w();
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

    public void a(boolean z) {
        this.i.set(0, z);
    }

    public boolean a(q qVar) {
        if (qVar == null) {
            return false;
        }
        boolean b = b();
        boolean b2 = qVar.b();
        if ((b || b2) && (!b || !b2 || !this.a.equals(qVar.a))) {
            return false;
        }
        b = c();
        b2 = qVar.c();
        if ((b || b2) && (!b || !b2 || this.b != qVar.b)) {
            return false;
        }
        b = d();
        b2 = qVar.d();
        return !(b || b2) || (b && b2 && this.c.equals(qVar.c));
    }

    public int b(q qVar) {
        if (!getClass().equals(qVar.getClass())) {
            return getClass().getName().compareTo(qVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(qVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.c.a(this.a, qVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(qVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.c.a(this.b, qVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(qVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.c.a(this.c, qVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(f fVar) {
        e();
        fVar.a(e);
        if (this.a != null) {
            fVar.a(f);
            fVar.a(new org.apache.thrift.protocol.d((byte) 12, this.a.size()));
            for (e eVar : this.a) {
                eVar.b(fVar);
            }
            fVar.e();
            fVar.b();
        }
        if (c()) {
            fVar.a(g);
            fVar.a(this.b);
            fVar.b();
        }
        if (this.c != null && d()) {
            fVar.a(h);
            fVar.a(this.c);
            fVar.b();
        }
        fVar.c();
        fVar.a();
    }

    public boolean b() {
        return this.a != null;
    }

    public boolean c() {
        return this.i.get(0);
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((q) obj);
    }

    public boolean d() {
        return this.c != null;
    }

    public void e() {
        if (this.a == null) {
            throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'normalConfigs' was not present! Struct: ").append(toString()).toString());
        }
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof q)) ? a((q) obj) : false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("XmPushActionNormalConfig(");
        stringBuilder.append("normalConfigs:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        if (c()) {
            stringBuilder.append(", ");
            stringBuilder.append("appId:");
            stringBuilder.append(this.b);
        }
        if (d()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.c);
            }
        }
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }
}
