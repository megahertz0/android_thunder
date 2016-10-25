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
import org.android.agoo.common.AgooConstants;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.thrift.b;
import org.apache.thrift.meta_data.d;
import org.apache.thrift.meta_data.g;
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.i;
import org.apache.thrift.protocol.k;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class e implements Serializable, Cloneable, b<e, a> {
    public static final Map<a, org.apache.thrift.meta_data.b> d;
    private static final k e;
    private static final c f;
    private static final c g;
    private static final c h;
    public int a;
    public List<g> b;
    public c c;
    private BitSet i;

    public enum a {
        VERSION((short) 1, "version"),
        CONFIG_ITEMS((short) 2, "configItems"),
        TYPE((short) 3, AgooConstants.MESSAGE_TYPE);
        private static final Map<String, com.xiaomi.xmpush.thrift.e.a> d;
        private final short e;
        private final String f;

        static {
            String str = "version";
            a = new com.xiaomi.xmpush.thrift.e.a("VERSION", 0, (short) 1, "version");
            str = "configItems";
            b = new com.xiaomi.xmpush.thrift.e.a("CONFIG_ITEMS", 1, (short) 2, "configItems");
            str = AgooConstants.MESSAGE_TYPE;
            c = new com.xiaomi.xmpush.thrift.e.a("TYPE", 2, (short) 3, AgooConstants.MESSAGE_TYPE);
            g = new com.xiaomi.xmpush.thrift.e.a[]{a, b, c};
            d = new HashMap();
            Iterator it = EnumSet.allOf(com.xiaomi.xmpush.thrift.e.a.class).iterator();
            while (it.hasNext()) {
                com.xiaomi.xmpush.thrift.e.a aVar = (com.xiaomi.xmpush.thrift.e.a) it.next();
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
        e = new k("NormalConfig");
        f = new c("version", (byte) 8, (short) 1);
        g = new c("configItems", (byte) 15, (short) 2);
        h = new c(AgooConstants.MESSAGE_TYPE, (byte) 8, (short) 3);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.a, new org.apache.thrift.meta_data.b("version", (byte) 1, new org.apache.thrift.meta_data.c((byte) 8)));
        enumMap.put(a.b, new org.apache.thrift.meta_data.b("configItems", (byte) 1, new d((byte) 15, new g((byte) 12, g.class))));
        enumMap.put(a.c, new org.apache.thrift.meta_data.b(AgooConstants.MESSAGE_TYPE, (byte) 1, new org.apache.thrift.meta_data.a((byte) 16, c.class)));
        d = Collections.unmodifiableMap(enumMap);
        org.apache.thrift.meta_data.b.a(e.class, d);
    }

    public e() {
        this.i = new BitSet(1);
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
                        if (i.b == 15) {
                            org.apache.thrift.protocol.d m = fVar.m();
                            this.b = new ArrayList(m.b);
                            for (int i2 = 0; i2 < m.b; i2++) {
                                g gVar = new g();
                                gVar.a(fVar);
                                this.b.add(gVar);
                            }
                            fVar.n();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case MqttConnectOptions.MQTT_VERSION_3_1:
                        if (i.b == (byte) 8) {
                            this.c = c.a(fVar.t());
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
                if (b()) {
                    f();
                    return;
                }
                throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'version' was not found in serialized data! Struct: ").append(toString()).toString());
            }
        }
    }

    public void a(boolean z) {
        this.i.set(0, z);
    }

    public boolean a(e eVar) {
        if (eVar == null || this.a != eVar.a) {
            return false;
        }
        boolean c = c();
        boolean c2 = eVar.c();
        if ((c || c2) && (!c || !c2 || !this.b.equals(eVar.b))) {
            return false;
        }
        c = e();
        c2 = eVar.e();
        return !(c || c2) || (c && c2 && this.c.equals(eVar.c));
    }

    public int b(e eVar) {
        if (!getClass().equals(eVar.getClass())) {
            return getClass().getName().compareTo(eVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(eVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.c.a(this.a, eVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(eVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.c.a(this.b, eVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(eVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.c.a(this.c, eVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(f fVar) {
        f();
        fVar.a(e);
        fVar.a(f);
        fVar.a(this.a);
        fVar.b();
        if (this.b != null) {
            fVar.a(g);
            fVar.a(new org.apache.thrift.protocol.d((byte) 12, this.b.size()));
            for (g gVar : this.b) {
                gVar.b(fVar);
            }
            fVar.e();
            fVar.b();
        }
        if (this.c != null) {
            fVar.a(h);
            fVar.a(this.c.a());
            fVar.b();
        }
        fVar.c();
        fVar.a();
    }

    public boolean b() {
        return this.i.get(0);
    }

    public boolean c() {
        return this.b != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((e) obj);
    }

    public c d() {
        return this.c;
    }

    public boolean e() {
        return this.c != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof e)) ? a((e) obj) : false;
    }

    public void f() {
        if (this.b == null) {
            throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'configItems' was not present! Struct: ").append(toString()).toString());
        } else if (this.c == null) {
            throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'type' was not present! Struct: ").append(toString()).toString());
        }
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("NormalConfig(");
        stringBuilder.append("version:");
        stringBuilder.append(this.a);
        stringBuilder.append(", ");
        stringBuilder.append("configItems:");
        if (this.b == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.b);
        }
        stringBuilder.append(", ");
        stringBuilder.append("type:");
        if (this.c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }
}
