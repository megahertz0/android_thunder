package u.aly;

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
import java.util.Map.Entry;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: Event.java
public class ax implements Serializable, Cloneable, y<ax, e> {
    public static final Map<e, ah> g;
    private static final ca h;
    private static final br i;
    private static final br j;
    private static final br k;
    private static final br l;
    private static final br m;
    private static final Map<Class<? extends cc>, cd> n;
    public String a;
    public Map<String, bj> b;
    public long c;
    public int d;
    public long e;
    byte f;
    private e[] o;

    // compiled from: Event.java
    private static class a extends ce<ax> {
        private a() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            ax axVar = (ax) yVar;
            axVar.f();
            h;
            buVar.a();
            if (axVar.a != null) {
                buVar.a(i);
                buVar.a(axVar.a);
            }
            if (axVar.b != null) {
                buVar.a(j);
                buVar.a(new bt((byte) 11, (byte) 12, axVar.b.size()));
                for (Entry entry : axVar.b.entrySet()) {
                    buVar.a((String) entry.getKey());
                    ((bj) entry.getValue()).b(buVar);
                }
            }
            if (axVar.a()) {
                buVar.a(k);
                buVar.a(axVar.c);
            }
            if (axVar.c()) {
                buVar.a(l);
                buVar.a(axVar.d);
            }
            buVar.a(m);
            buVar.a(axVar.e);
            buVar.c();
            buVar.b();
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            ax axVar = (ax) yVar;
            buVar.d();
            while (true) {
                br f = buVar.f();
                if (f.b != null) {
                    switch (f.c) {
                        case SimpleLog.LOG_LEVEL_TRACE:
                            if (f.b == 11) {
                                axVar.a = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_DEBUG:
                            if (f.b == 13) {
                                bt g = buVar.g();
                                axVar.b = new HashMap(g.c * 2);
                                for (int i = 0; i < g.c; i++) {
                                    String p = buVar.p();
                                    bj bjVar = new bj();
                                    bjVar.a(buVar);
                                    axVar.b.put(p, bjVar);
                                }
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case MqttConnectOptions.MQTT_VERSION_3_1:
                            if (f.b == (byte) 10) {
                                axVar.c = buVar.n();
                                axVar.b();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case MqttConnectOptions.MQTT_VERSION_3_1_1:
                            if (f.b == 8) {
                                axVar.d = buVar.m();
                                axVar.d();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_ERROR:
                            if (f.b == (byte) 10) {
                                axVar.e = buVar.n();
                                axVar.e();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        default:
                            by.a(buVar, f.b);
                            break;
                    }
                } else {
                    buVar.e();
                    if (w.a(axVar.f, SimpleLog.LOG_LEVEL_DEBUG)) {
                        axVar.f();
                        return;
                    }
                    throw new bv(new StringBuilder("Required field 'ts' was not found in serialized data! Struct: ").append(toString()).toString());
                }
            }
        }
    }

    // compiled from: Event.java
    private static class b implements cd {
        private b() {
        }

        public final /* synthetic */ cc a() {
            return new a();
        }
    }

    // compiled from: Event.java
    private static class c extends cf<ax> {
        private c() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            ax axVar = (ax) yVar;
            cb cbVar = (cb) buVar;
            cbVar.a(axVar.a);
            cbVar.a(axVar.b.size());
            for (Entry entry : axVar.b.entrySet()) {
                cbVar.a((String) entry.getKey());
                ((bj) entry.getValue()).b(cbVar);
            }
            cbVar.a(axVar.e);
            BitSet bitSet = new BitSet();
            if (axVar.a()) {
                bitSet.set(0);
            }
            if (axVar.c()) {
                bitSet.set(1);
            }
            cbVar.a(bitSet, SimpleLog.LOG_LEVEL_DEBUG);
            if (axVar.a()) {
                cbVar.a(axVar.c);
            }
            if (axVar.c()) {
                cbVar.a(axVar.d);
            }
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            ax axVar = (ax) yVar;
            cb cbVar = (cb) buVar;
            axVar.a = cbVar.p();
            bt btVar = new bt((byte) 11, (byte) 12, cbVar.m());
            axVar.b = new HashMap(btVar.c * 2);
            for (int i = 0; i < btVar.c; i++) {
                String p = cbVar.p();
                bj bjVar = new bj();
                bjVar.a(cbVar);
                axVar.b.put(p, bjVar);
            }
            axVar.e = cbVar.n();
            axVar.e();
            BitSet b = cbVar.b(SimpleLog.LOG_LEVEL_DEBUG);
            if (b.get(0)) {
                axVar.c = cbVar.n();
                axVar.b();
            }
            if (b.get(1)) {
                axVar.d = cbVar.m();
                axVar.d();
            }
        }
    }

    // compiled from: Event.java
    private static class d implements cd {
        private d() {
        }

        public final /* synthetic */ cc a() {
            return new c();
        }
    }

    // compiled from: Event.java
    public enum e implements ad {
        NAME((short) 1, SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_NAME),
        PROPERTIES((short) 2, "properties"),
        DURATION((short) 3, "duration"),
        ACC((short) 4, "acc"),
        TS((short) 5, "ts");
        private static final Map<String, u.aly.ax.e> f;
        private final short g;
        private final String h;

        static {
            String str = SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_NAME;
            a = new u.aly.ax.e("NAME", 0, (short) 1, SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_NAME);
            str = "properties";
            b = new u.aly.ax.e("PROPERTIES", 1, (short) 2, "properties");
            str = "duration";
            c = new u.aly.ax.e("DURATION", 2, (short) 3, "duration");
            str = "acc";
            d = new u.aly.ax.e("ACC", 3, (short) 4, "acc");
            String str2 = "ts";
            e = new u.aly.ax.e("TS", 4, (short) 5, "ts");
            i = new u.aly.ax.e[]{a, b, c, d, e};
            f = new HashMap();
            Iterator it = EnumSet.allOf(u.aly.ax.e.class).iterator();
            while (it.hasNext()) {
                u.aly.ax.e eVar = (u.aly.ax.e) it.next();
                f.put(eVar.b(), eVar);
            }
        }

        public static u.aly.ax.e a(int i) {
            switch (i) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    return a;
                case SimpleLog.LOG_LEVEL_DEBUG:
                    return b;
                case MqttConnectOptions.MQTT_VERSION_3_1:
                    return c;
                case MqttConnectOptions.MQTT_VERSION_3_1_1:
                    return d;
                case SimpleLog.LOG_LEVEL_ERROR:
                    return e;
                default:
                    return null;
            }
        }

        public static u.aly.ax.e b(int i) {
            u.aly.ax.e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException(new StringBuilder("Field ").append(i).append(" doesn't exist!").toString());
        }

        public static u.aly.ax.e a(String str) {
            return (u.aly.ax.e) f.get(str);
        }

        private e(short s, String str) {
            this.g = s;
            this.h = str;
        }

        public final short a() {
            return this.g;
        }

        public final String b() {
            return this.h;
        }
    }

    static {
        h = new ca("Event");
        i = new br(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_NAME, (byte) 11, (short) 1);
        j = new br("properties", (byte) 13, (short) 2);
        k = new br("duration", (byte) 10, (short) 3);
        l = new br("acc", (byte) 8, (short) 4);
        m = new br("ts", (byte) 10, (short) 5);
        Map hashMap = new HashMap();
        n = hashMap;
        hashMap.put(ce.class, new b());
        n.put(cf.class, new d());
        hashMap = new EnumMap(e.class);
        hashMap.put(e.a, new ah(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_NAME, (byte) 1, new ai((byte) 11)));
        hashMap.put(e.b, new ah("properties", (byte) 1, new ak(new ai((byte) 11), new al(bj.class))));
        hashMap.put(e.c, new ah("duration", (byte) 2, new ai((byte) 10)));
        hashMap.put(e.d, new ah("acc", (byte) 2, new ai((byte) 8)));
        hashMap.put(e.e, new ah("ts", (byte) 1, new ai((byte) 10)));
        g = Collections.unmodifiableMap(hashMap);
        ah.a(ax.class, g);
    }

    public ax() {
        this.f = (byte) 0;
        this.o = new e[]{e.c, e.d};
    }

    public final boolean a() {
        return w.a(this.f, 0);
    }

    public final void b() {
        this.f = (byte) (this.f | 1);
    }

    public final ax a(int i) {
        this.d = i;
        d();
        return this;
    }

    public final boolean c() {
        return w.a(this.f, 1);
    }

    public final void d() {
        this.f = (byte) (this.f | 2);
    }

    public final ax a(long j) {
        this.e = j;
        e();
        return this;
    }

    public final void e() {
        this.f = (byte) (this.f | 4);
    }

    public final void a(bu buVar) throws ac {
        ((cd) n.get(buVar.s())).a().b(buVar, this);
    }

    public final void b(bu buVar) throws ac {
        ((cd) n.get(buVar.s())).a().a(buVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Event(");
        stringBuilder.append("name:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("properties:");
        if (this.b == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.b);
        }
        if (a()) {
            stringBuilder.append(", ");
            stringBuilder.append("duration:");
            stringBuilder.append(this.c);
        }
        if (c()) {
            stringBuilder.append(", ");
            stringBuilder.append("acc:");
            stringBuilder.append(this.d);
        }
        stringBuilder.append(", ");
        stringBuilder.append("ts:");
        stringBuilder.append(this.e);
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }

    public final void f() throws ac {
        if (this.a == null) {
            throw new bv(new StringBuilder("Required field 'name' was not present! Struct: ").append(toString()).toString());
        } else if (this.b == null) {
            throw new bv(new StringBuilder("Required field 'properties' was not present! Struct: ").append(toString()).toString());
        }
    }
}
