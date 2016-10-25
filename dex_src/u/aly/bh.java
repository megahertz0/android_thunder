package u.aly;

import com.umeng.message.proguard.j;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: MiscInfo.java
public class bh implements Serializable, Cloneable, y<bh, e> {
    public static final Map<e, ah> k;
    private static final ca l;
    private static final br m;
    private static final br n;
    private static final br o;
    private static final br p;
    private static final br q;
    private static final br r;
    private static final br s;
    private static final br t;
    private static final br u;
    private static final br v;
    private static final Map<Class<? extends cc>, cd> w;
    public int a;
    public String b;
    public String c;
    public double d;
    public double e;
    public String f;
    public int g;
    public String h;
    public ao i;
    public String j;
    private byte x;
    private e[] y;

    // compiled from: MiscInfo.java
    private static class a extends ce<bh> {
        private a() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            bh bhVar = (bh) yVar;
            bh.o();
            l;
            buVar.a();
            if (bhVar.a()) {
                buVar.a(m);
                buVar.a(bhVar.a);
            }
            if (bhVar.b != null && bhVar.c()) {
                buVar.a(n);
                buVar.a(bhVar.b);
            }
            if (bhVar.c != null && bhVar.d()) {
                buVar.a(o);
                buVar.a(bhVar.c);
            }
            if (bhVar.e()) {
                buVar.a(p);
                buVar.a(bhVar.d);
            }
            if (bhVar.g()) {
                buVar.a(q);
                buVar.a(bhVar.e);
            }
            if (bhVar.f != null && bhVar.i()) {
                buVar.a(r);
                buVar.a(bhVar.f);
            }
            if (bhVar.j()) {
                buVar.a(s);
                buVar.a(bhVar.g);
            }
            if (bhVar.h != null && bhVar.l()) {
                buVar.a(t);
                buVar.a(bhVar.h);
            }
            if (bhVar.i != null && bhVar.m()) {
                buVar.a(u);
                buVar.a(bhVar.i.a());
            }
            if (bhVar.j != null && bhVar.n()) {
                buVar.a(v);
                buVar.a(bhVar.j);
            }
            buVar.c();
            buVar.b();
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            bh bhVar = (bh) yVar;
            buVar.d();
            while (true) {
                br f = buVar.f();
                if (f.b != null) {
                    switch (f.c) {
                        case SimpleLog.LOG_LEVEL_TRACE:
                            if (f.b == (byte) 8) {
                                bhVar.a = buVar.m();
                                bhVar.b();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_DEBUG:
                            if (f.b == (byte) 11) {
                                bhVar.b = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case MqttConnectOptions.MQTT_VERSION_3_1:
                            if (f.b == (byte) 11) {
                                bhVar.c = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case MqttConnectOptions.MQTT_VERSION_3_1_1:
                            if (f.b == (byte) 4) {
                                bhVar.d = buVar.o();
                                bhVar.f();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_ERROR:
                            if (f.b == (byte) 4) {
                                bhVar.e = buVar.o();
                                bhVar.h();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_FATAL:
                            if (f.b == (byte) 11) {
                                bhVar.f = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_OFF:
                            if (f.b == (byte) 8) {
                                bhVar.g = buVar.m();
                                bhVar.k();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SpdyProtocol.PUBKEY_SEQ_ADASH:
                            if (f.b == (byte) 11) {
                                bhVar.h = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SpdyProtocol.PUBKEY_PSEQ_ADASH:
                            if (f.b == (byte) 8) {
                                bhVar.i = ao.a(buVar.m());
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SpdyProtocol.PUBKEY_SEQ_OPEN:
                            if (f.b == (byte) 11) {
                                bhVar.j = buVar.p();
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
                    bh.o();
                    return;
                }
            }
        }
    }

    // compiled from: MiscInfo.java
    private static class b implements cd {
        private b() {
        }

        public final /* synthetic */ cc a() {
            return new a();
        }
    }

    // compiled from: MiscInfo.java
    private static class c extends cf<bh> {
        private c() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            bh bhVar = (bh) yVar;
            cb cbVar = (cb) buVar;
            BitSet bitSet = new BitSet();
            if (bhVar.a()) {
                bitSet.set(0);
            }
            if (bhVar.c()) {
                bitSet.set(1);
            }
            if (bhVar.d()) {
                bitSet.set(SimpleLog.LOG_LEVEL_DEBUG);
            }
            if (bhVar.e()) {
                bitSet.set(MqttConnectOptions.MQTT_VERSION_3_1);
            }
            if (bhVar.g()) {
                bitSet.set(MqttConnectOptions.MQTT_VERSION_3_1_1);
            }
            if (bhVar.i()) {
                bitSet.set(SimpleLog.LOG_LEVEL_ERROR);
            }
            if (bhVar.j()) {
                bitSet.set(SimpleLog.LOG_LEVEL_FATAL);
            }
            if (bhVar.l()) {
                bitSet.set(SimpleLog.LOG_LEVEL_OFF);
            }
            if (bhVar.m()) {
                bitSet.set(SpdyProtocol.PUBKEY_SEQ_ADASH);
            }
            if (bhVar.n()) {
                bitSet.set(SpdyProtocol.PUBKEY_PSEQ_ADASH);
            }
            cbVar.a(bitSet, SpdyProtocol.PUBKEY_SEQ_OPEN);
            if (bhVar.a()) {
                cbVar.a(bhVar.a);
            }
            if (bhVar.c()) {
                cbVar.a(bhVar.b);
            }
            if (bhVar.d()) {
                cbVar.a(bhVar.c);
            }
            if (bhVar.e()) {
                cbVar.a(bhVar.d);
            }
            if (bhVar.g()) {
                cbVar.a(bhVar.e);
            }
            if (bhVar.i()) {
                cbVar.a(bhVar.f);
            }
            if (bhVar.j()) {
                cbVar.a(bhVar.g);
            }
            if (bhVar.l()) {
                cbVar.a(bhVar.h);
            }
            if (bhVar.m()) {
                cbVar.a(bhVar.i.a());
            }
            if (bhVar.n()) {
                cbVar.a(bhVar.j);
            }
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            bh bhVar = (bh) yVar;
            cb cbVar = (cb) buVar;
            BitSet b = cbVar.b(SpdyProtocol.PUBKEY_SEQ_OPEN);
            if (b.get(0)) {
                bhVar.a = cbVar.m();
                bhVar.b();
            }
            if (b.get(1)) {
                bhVar.b = cbVar.p();
            }
            if (b.get(SimpleLog.LOG_LEVEL_DEBUG)) {
                bhVar.c = cbVar.p();
            }
            if (b.get(MqttConnectOptions.MQTT_VERSION_3_1)) {
                bhVar.d = cbVar.o();
                bhVar.f();
            }
            if (b.get(MqttConnectOptions.MQTT_VERSION_3_1_1)) {
                bhVar.e = cbVar.o();
                bhVar.h();
            }
            if (b.get(SimpleLog.LOG_LEVEL_ERROR)) {
                bhVar.f = cbVar.p();
            }
            if (b.get(SimpleLog.LOG_LEVEL_FATAL)) {
                bhVar.g = cbVar.m();
                bhVar.k();
            }
            if (b.get(SimpleLog.LOG_LEVEL_OFF)) {
                bhVar.h = cbVar.p();
            }
            if (b.get(SpdyProtocol.PUBKEY_SEQ_ADASH)) {
                bhVar.i = ao.a(cbVar.m());
            }
            if (b.get(SpdyProtocol.PUBKEY_PSEQ_ADASH)) {
                bhVar.j = cbVar.p();
            }
        }
    }

    // compiled from: MiscInfo.java
    private static class d implements cd {
        private d() {
        }

        public final /* synthetic */ cc a() {
            return new c();
        }
    }

    // compiled from: MiscInfo.java
    public enum e implements ad {
        TIME_ZONE((short) 1, "time_zone"),
        LANGUAGE((short) 2, "language"),
        COUNTRY((short) 3, "country"),
        LATITUDE((short) 4, "latitude"),
        LONGITUDE((short) 5, "longitude"),
        CARRIER((short) 6, "carrier"),
        LATENCY((short) 7, "latency"),
        DISPLAY_NAME((short) 8, "display_name"),
        ACCESS_TYPE((short) 9, "access_type"),
        ACCESS_SUBTYPE((short) 10, "access_subtype");
        private static final Map<String, u.aly.bh.e> k;
        private final short l;
        private final String m;

        static {
            String str = "time_zone";
            a = new u.aly.bh.e("TIME_ZONE", 0, (short) 1, "time_zone");
            String str2 = "language";
            b = new u.aly.bh.e("LANGUAGE", 1, (short) 2, "language");
            str2 = "country";
            c = new u.aly.bh.e("COUNTRY", 2, (short) 3, "country");
            str2 = "latitude";
            d = new u.aly.bh.e("LATITUDE", 3, (short) 4, "latitude");
            str2 = "longitude";
            e = new u.aly.bh.e("LONGITUDE", 4, (short) 5, "longitude");
            str = "carrier";
            f = new u.aly.bh.e("CARRIER", 5, (short) 6, "carrier");
            String str3 = "latency";
            g = new u.aly.bh.e("LATENCY", 6, (short) 7, "latency");
            str3 = "display_name";
            h = new u.aly.bh.e("DISPLAY_NAME", 7, (short) 8, "display_name");
            str3 = "access_type";
            i = new u.aly.bh.e("ACCESS_TYPE", 8, (short) 9, "access_type");
            str3 = "access_subtype";
            j = new u.aly.bh.e("ACCESS_SUBTYPE", 9, (short) 10, "access_subtype");
            n = new u.aly.bh.e[]{a, b, c, d, e, f, g, h, i, j};
            k = new HashMap();
            Iterator it = EnumSet.allOf(u.aly.bh.e.class).iterator();
            while (it.hasNext()) {
                u.aly.bh.e eVar = (u.aly.bh.e) it.next();
                k.put(eVar.b(), eVar);
            }
        }

        public static u.aly.bh.e a(int i) {
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
                case SimpleLog.LOG_LEVEL_FATAL:
                    return f;
                case SimpleLog.LOG_LEVEL_OFF:
                    return g;
                case SpdyProtocol.PUBKEY_SEQ_ADASH:
                    return h;
                case SpdyProtocol.PUBKEY_PSEQ_ADASH:
                    return i;
                case SpdyProtocol.PUBKEY_SEQ_OPEN:
                    return j;
                default:
                    return null;
            }
        }

        public static u.aly.bh.e b(int i) {
            u.aly.bh.e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException(new StringBuilder("Field ").append(i).append(" doesn't exist!").toString());
        }

        public static u.aly.bh.e a(String str) {
            return (u.aly.bh.e) k.get(str);
        }

        private e(short s, String str) {
            this.l = s;
            this.m = str;
        }

        public final short a() {
            return this.l;
        }

        public final String b() {
            return this.m;
        }
    }

    static {
        l = new ca("MiscInfo");
        m = new br("time_zone", (byte) 8, (short) 1);
        n = new br("language", (byte) 11, (short) 2);
        o = new br("country", (byte) 11, (short) 3);
        p = new br("latitude", (byte) 4, (short) 4);
        q = new br("longitude", (byte) 4, (short) 5);
        r = new br("carrier", (byte) 11, (short) 6);
        s = new br("latency", (byte) 8, (short) 7);
        t = new br("display_name", (byte) 11, (short) 8);
        u = new br("access_type", (byte) 8, (short) 9);
        v = new br("access_subtype", (byte) 11, (short) 10);
        Map hashMap = new HashMap();
        w = hashMap;
        hashMap.put(ce.class, new b());
        w.put(cf.class, new d());
        hashMap = new EnumMap(e.class);
        hashMap.put(e.a, new ah("time_zone", (byte) 2, new ai((byte) 8)));
        hashMap.put(e.b, new ah("language", (byte) 2, new ai((byte) 11)));
        hashMap.put(e.c, new ah("country", (byte) 2, new ai((byte) 11)));
        hashMap.put(e.d, new ah("latitude", (byte) 2, new ai((byte) 4)));
        hashMap.put(e.e, new ah("longitude", (byte) 2, new ai((byte) 4)));
        hashMap.put(e.f, new ah("carrier", (byte) 2, new ai((byte) 11)));
        hashMap.put(e.g, new ah("latency", (byte) 2, new ai((byte) 8)));
        hashMap.put(e.h, new ah("display_name", (byte) 2, new ai((byte) 11)));
        hashMap.put(e.i, new ah("access_type", (byte) 2, new ag(ao.class)));
        hashMap.put(e.j, new ah("access_subtype", (byte) 2, new ai((byte) 11)));
        k = Collections.unmodifiableMap(hashMap);
        ah.a(bh.class, k);
    }

    public bh() {
        this.x = (byte) 0;
        this.y = new e[]{e.a, e.b, e.c, e.d, e.e, e.f, e.g, e.h, e.i, e.j};
    }

    public final boolean a() {
        return w.a(this.x, 0);
    }

    public final void b() {
        this.x = (byte) (this.x | 1);
    }

    public final boolean c() {
        return this.b != null;
    }

    public final boolean d() {
        return this.c != null;
    }

    public final boolean e() {
        return w.a(this.x, 1);
    }

    public final void f() {
        this.x = (byte) (this.x | 2);
    }

    public final boolean g() {
        return w.a(this.x, SimpleLog.LOG_LEVEL_DEBUG);
    }

    public final void h() {
        this.x = (byte) (this.x | 4);
    }

    public final boolean i() {
        return this.f != null;
    }

    public final boolean j() {
        return w.a(this.x, MqttConnectOptions.MQTT_VERSION_3_1);
    }

    public final void k() {
        this.x = (byte) (this.x | 8);
    }

    public final boolean l() {
        return this.h != null;
    }

    public final boolean m() {
        return this.i != null;
    }

    public final boolean n() {
        return this.j != null;
    }

    public final void a(bu buVar) throws ac {
        ((cd) w.get(buVar.s())).a().b(buVar, this);
    }

    public final void b(bu buVar) throws ac {
        ((cd) w.get(buVar.s())).a().a(buVar, this);
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("MiscInfo(");
        Object obj2 = 1;
        if (a()) {
            stringBuilder.append("time_zone:");
            stringBuilder.append(this.a);
            obj2 = null;
        }
        if (c()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("language:");
            if (this.b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.b);
            }
            obj2 = null;
        }
        if (d()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("country:");
            if (this.c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.c);
            }
            obj2 = null;
        }
        if (e()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("latitude:");
            stringBuilder.append(this.d);
            obj2 = null;
        }
        if (g()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("longitude:");
            stringBuilder.append(this.e);
            obj2 = null;
        }
        if (i()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("carrier:");
            if (this.f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f);
            }
            obj2 = null;
        }
        if (j()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("latency:");
            stringBuilder.append(this.g);
            obj2 = null;
        }
        if (l()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("display_name:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
            obj2 = null;
        }
        if (m()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("access_type:");
            if (this.i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.i);
            }
        } else {
            obj = obj2;
        }
        if (n()) {
            if (obj == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("access_subtype:");
            if (this.j == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.j);
            }
        }
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }

    public static void o() throws ac {
    }
}
