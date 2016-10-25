package u.aly;

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
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: Session.java
public class bn implements Serializable, Cloneable, y<bn, e> {
    public static final Map<e, ah> i;
    private static final ca j;
    private static final br k;
    private static final br l;
    private static final br m;
    private static final br n;
    private static final br o;
    private static final br p;
    private static final br q;
    private static final Map<Class<? extends cc>, cd> r;
    public String a;
    public long b;
    public long c;
    public long d;
    public List<bi> e;
    public List<bg> f;
    public bo g;
    byte h;
    private e[] s;

    // compiled from: Session.java
    private static class a extends ce<bn> {
        private a() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            bn bnVar = (bn) yVar;
            bnVar.g();
            j;
            buVar.a();
            if (bnVar.a != null) {
                buVar.a(k);
                buVar.a(bnVar.a);
            }
            buVar.a(l);
            buVar.a(bnVar.b);
            buVar.a(m);
            buVar.a(bnVar.c);
            buVar.a(n);
            buVar.a(bnVar.d);
            if (bnVar.e != null && bnVar.d()) {
                buVar.a(o);
                buVar.a(new bs((byte) 12, bnVar.e.size()));
                for (bi biVar : bnVar.e) {
                    biVar.b(buVar);
                }
            }
            if (bnVar.f != null && bnVar.e()) {
                buVar.a(p);
                buVar.a(new bs((byte) 12, bnVar.f.size()));
                for (bg bgVar : bnVar.f) {
                    bgVar.b(buVar);
                }
            }
            if (bnVar.g != null && bnVar.f()) {
                buVar.a(q);
                bnVar.g.b(buVar);
            }
            buVar.c();
            buVar.b();
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            bn bnVar = (bn) yVar;
            buVar.d();
            while (true) {
                br f = buVar.f();
                if (f.b != null) {
                    bs h;
                    int i;
                    switch (f.c) {
                        case SimpleLog.LOG_LEVEL_TRACE:
                            if (f.b == 11) {
                                bnVar.a = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_DEBUG:
                            if (f.b == (byte) 10) {
                                bnVar.b = buVar.n();
                                bnVar.a();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case MqttConnectOptions.MQTT_VERSION_3_1:
                            if (f.b == (byte) 10) {
                                bnVar.c = buVar.n();
                                bnVar.b();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case MqttConnectOptions.MQTT_VERSION_3_1_1:
                            if (f.b == (byte) 10) {
                                bnVar.d = buVar.n();
                                bnVar.c();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_ERROR:
                            if (f.b == (byte) 15) {
                                h = buVar.h();
                                bnVar.e = new ArrayList(h.b);
                                for (i = 0; i < h.b; i++) {
                                    bi biVar = new bi();
                                    biVar.a(buVar);
                                    bnVar.e.add(biVar);
                                }
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_FATAL:
                            if (f.b == (byte) 15) {
                                h = buVar.h();
                                bnVar.f = new ArrayList(h.b);
                                for (i = 0; i < h.b; i++) {
                                    bg bgVar = new bg();
                                    bgVar.a(buVar);
                                    bnVar.f.add(bgVar);
                                }
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_OFF:
                            if (f.b == 12) {
                                bnVar.g = new bo();
                                bnVar.g.a(buVar);
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
                    if (!w.a(bnVar.h, 0)) {
                        throw new bv(new StringBuilder("Required field 'start_time' was not found in serialized data! Struct: ").append(toString()).toString());
                    } else if (!w.a(bnVar.h, 1)) {
                        throw new bv(new StringBuilder("Required field 'end_time' was not found in serialized data! Struct: ").append(toString()).toString());
                    } else if (w.a(bnVar.h, SimpleLog.LOG_LEVEL_DEBUG)) {
                        bnVar.g();
                        return;
                    } else {
                        throw new bv(new StringBuilder("Required field 'duration' was not found in serialized data! Struct: ").append(toString()).toString());
                    }
                }
            }
        }
    }

    // compiled from: Session.java
    private static class b implements cd {
        private b() {
        }

        public final /* synthetic */ cc a() {
            return new a();
        }
    }

    // compiled from: Session.java
    private static class c extends cf<bn> {
        private c() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            bn bnVar = (bn) yVar;
            cb cbVar = (cb) buVar;
            cbVar.a(bnVar.a);
            cbVar.a(bnVar.b);
            cbVar.a(bnVar.c);
            cbVar.a(bnVar.d);
            BitSet bitSet = new BitSet();
            if (bnVar.d()) {
                bitSet.set(0);
            }
            if (bnVar.e()) {
                bitSet.set(1);
            }
            if (bnVar.f()) {
                bitSet.set(SimpleLog.LOG_LEVEL_DEBUG);
            }
            cbVar.a(bitSet, MqttConnectOptions.MQTT_VERSION_3_1);
            if (bnVar.d()) {
                cbVar.a(bnVar.e.size());
                for (bi biVar : bnVar.e) {
                    biVar.b(cbVar);
                }
            }
            if (bnVar.e()) {
                cbVar.a(bnVar.f.size());
                for (bg bgVar : bnVar.f) {
                    bgVar.b(cbVar);
                }
            }
            if (bnVar.f()) {
                bnVar.g.b(cbVar);
            }
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            int i = 0;
            bn bnVar = (bn) yVar;
            cb cbVar = (cb) buVar;
            bnVar.a = cbVar.p();
            bnVar.b = cbVar.n();
            bnVar.a();
            bnVar.c = cbVar.n();
            bnVar.b();
            bnVar.d = cbVar.n();
            bnVar.c();
            BitSet b = cbVar.b(MqttConnectOptions.MQTT_VERSION_3_1);
            if (b.get(0)) {
                bs bsVar = new bs((byte) 12, cbVar.m());
                bnVar.e = new ArrayList(bsVar.b);
                for (int i2 = 0; i2 < bsVar.b; i2++) {
                    bi biVar = new bi();
                    biVar.a(cbVar);
                    bnVar.e.add(biVar);
                }
            }
            if (b.get(1)) {
                bs bsVar2 = new bs((byte) 12, cbVar.m());
                bnVar.f = new ArrayList(bsVar2.b);
                while (i < bsVar2.b) {
                    bg bgVar = new bg();
                    bgVar.a(cbVar);
                    bnVar.f.add(bgVar);
                    i++;
                }
            }
            if (b.get(SimpleLog.LOG_LEVEL_DEBUG)) {
                bnVar.g = new bo();
                bnVar.g.a(cbVar);
            }
        }
    }

    // compiled from: Session.java
    private static class d implements cd {
        private d() {
        }

        public final /* synthetic */ cc a() {
            return new c();
        }
    }

    // compiled from: Session.java
    public enum e implements ad {
        ID((short) 1, AgooConstants.MESSAGE_ID),
        START_TIME((short) 2, "start_time"),
        END_TIME((short) 3, "end_time"),
        DURATION((short) 4, "duration"),
        PAGES((short) 5, "pages"),
        LOCATIONS((short) 6, "locations"),
        TRAFFIC((short) 7, "traffic");
        private static final Map<String, u.aly.bn.e> h;
        private final short i;
        private final String j;

        static {
            String str = AgooConstants.MESSAGE_ID;
            a = new u.aly.bn.e("ID", 0, (short) 1, AgooConstants.MESSAGE_ID);
            String str2 = "start_time";
            b = new u.aly.bn.e("START_TIME", 1, (short) 2, "start_time");
            str2 = "end_time";
            c = new u.aly.bn.e("END_TIME", 2, (short) 3, "end_time");
            str2 = "duration";
            d = new u.aly.bn.e("DURATION", 3, (short) 4, "duration");
            str2 = "pages";
            e = new u.aly.bn.e("PAGES", 4, (short) 5, "pages");
            str = "locations";
            f = new u.aly.bn.e("LOCATIONS", 5, (short) 6, "locations");
            String str3 = "traffic";
            g = new u.aly.bn.e("TRAFFIC", 6, (short) 7, "traffic");
            k = new u.aly.bn.e[]{a, b, c, d, e, f, g};
            h = new HashMap();
            Iterator it = EnumSet.allOf(u.aly.bn.e.class).iterator();
            while (it.hasNext()) {
                u.aly.bn.e eVar = (u.aly.bn.e) it.next();
                h.put(eVar.b(), eVar);
            }
        }

        public static u.aly.bn.e a(int i) {
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
                default:
                    return null;
            }
        }

        public static u.aly.bn.e b(int i) {
            u.aly.bn.e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException(new StringBuilder("Field ").append(i).append(" doesn't exist!").toString());
        }

        public static u.aly.bn.e a(String str) {
            return (u.aly.bn.e) h.get(str);
        }

        private e(short s, String str) {
            this.i = s;
            this.j = str;
        }

        public final short a() {
            return this.i;
        }

        public final String b() {
            return this.j;
        }
    }

    static {
        j = new ca("Session");
        k = new br(AgooConstants.MESSAGE_ID, (byte) 11, (short) 1);
        l = new br("start_time", (byte) 10, (short) 2);
        m = new br("end_time", (byte) 10, (short) 3);
        n = new br("duration", (byte) 10, (short) 4);
        o = new br("pages", (byte) 15, (short) 5);
        p = new br("locations", (byte) 15, (short) 6);
        q = new br("traffic", (byte) 12, (short) 7);
        Map hashMap = new HashMap();
        r = hashMap;
        hashMap.put(ce.class, new b());
        r.put(cf.class, new d());
        hashMap = new EnumMap(e.class);
        hashMap.put(e.a, new ah(AgooConstants.MESSAGE_ID, (byte) 1, new ai((byte) 11)));
        hashMap.put(e.b, new ah("start_time", (byte) 1, new ai((byte) 10)));
        hashMap.put(e.c, new ah("end_time", (byte) 1, new ai((byte) 10)));
        hashMap.put(e.d, new ah("duration", (byte) 1, new ai((byte) 10)));
        hashMap.put(e.e, new ah("pages", (byte) 2, new aj(new al(bi.class))));
        hashMap.put(e.f, new ah("locations", (byte) 2, new aj(new al(bg.class))));
        hashMap.put(e.g, new ah("traffic", (byte) 2, new al(bo.class)));
        i = Collections.unmodifiableMap(hashMap);
        ah.a(bn.class, i);
    }

    public bn() {
        this.h = (byte) 0;
        this.s = new e[]{e.e, e.f, e.g};
    }

    public final bn a(long j) {
        this.b = j;
        a();
        return this;
    }

    public final void a() {
        this.h = (byte) (this.h | 1);
    }

    public final bn b(long j) {
        this.c = j;
        b();
        return this;
    }

    public final void b() {
        this.h = (byte) (this.h | 2);
    }

    public final bn c(long j) {
        this.d = j;
        c();
        return this;
    }

    public final void c() {
        this.h = (byte) (this.h | 4);
    }

    public final boolean d() {
        return this.e != null;
    }

    public final boolean e() {
        return this.f != null;
    }

    public final boolean f() {
        return this.g != null;
    }

    public final void a(bu buVar) throws ac {
        ((cd) r.get(buVar.s())).a().b(buVar, this);
    }

    public final void b(bu buVar) throws ac {
        ((cd) r.get(buVar.s())).a().a(buVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Session(");
        stringBuilder.append("id:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("start_time:");
        stringBuilder.append(this.b);
        stringBuilder.append(", ");
        stringBuilder.append("end_time:");
        stringBuilder.append(this.c);
        stringBuilder.append(", ");
        stringBuilder.append("duration:");
        stringBuilder.append(this.d);
        if (d()) {
            stringBuilder.append(", ");
            stringBuilder.append("pages:");
            if (this.e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.e);
            }
        }
        if (e()) {
            stringBuilder.append(", ");
            stringBuilder.append("locations:");
            if (this.f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f);
            }
        }
        if (f()) {
            stringBuilder.append(", ");
            stringBuilder.append("traffic:");
            if (this.g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.g);
            }
        }
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }

    public final void g() throws ac {
        if (this.a == null) {
            throw new bv(new StringBuilder("Required field 'id' was not present! Struct: ").append(toString()).toString());
        } else if (this.g != null) {
            bo.c();
        }
    }
}
