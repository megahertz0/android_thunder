package u.aly;

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
import java.util.Map.Entry;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: UALogEntry.java
public class bp implements Serializable, Cloneable, y<bp, e> {
    private static final Map<Class<? extends cc>, cd> A;
    public static final Map<e, ah> m;
    private static final ca n;
    private static final br o;
    private static final br p;
    private static final br q;
    private static final br r;
    private static final br s;
    private static final br t;
    private static final br u;
    private static final br v;
    private static final br w;
    private static final br x;
    private static final br y;
    private static final br z;
    private e[] B;
    public as a;
    public ar b;
    public au c;
    public bh d;
    public ap e;
    public List<be> f;
    public List<bn> g;
    public bc h;
    public bb i;
    public aq j;
    public at k;
    public Map<String, Integer> l;

    // compiled from: UALogEntry.java
    private static class a extends ce<bp> {
        private a() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            bp bpVar = (bp) yVar;
            bpVar.j();
            n;
            buVar.a();
            if (bpVar.a != null) {
                buVar.a(o);
                bpVar.a.b(buVar);
            }
            if (bpVar.b != null) {
                buVar.a(p);
                bpVar.b.b(buVar);
            }
            if (bpVar.c != null) {
                buVar.a(q);
                bpVar.c.b(buVar);
            }
            if (bpVar.d != null) {
                buVar.a(r);
                bpVar.d.b(buVar);
            }
            if (bpVar.e != null && bpVar.a()) {
                buVar.a(s);
                bpVar.e.b(buVar);
            }
            if (bpVar.f != null && bpVar.c()) {
                buVar.a(t);
                buVar.a(new bs((byte) 12, bpVar.f.size()));
                for (be beVar : bpVar.f) {
                    beVar.b(buVar);
                }
            }
            if (bpVar.g != null && bpVar.d()) {
                buVar.a(u);
                buVar.a(new bs((byte) 12, bpVar.g.size()));
                for (bn bnVar : bpVar.g) {
                    bnVar.b(buVar);
                }
            }
            if (bpVar.h != null && bpVar.e()) {
                buVar.a(v);
                bpVar.h.b(buVar);
            }
            if (bpVar.i != null && bpVar.f()) {
                buVar.a(w);
                bpVar.i.b(buVar);
            }
            if (bpVar.j != null && bpVar.g()) {
                buVar.a(x);
                bpVar.j.b(buVar);
            }
            if (bpVar.k != null && bpVar.h()) {
                buVar.a(y);
                bpVar.k.b(buVar);
            }
            if (bpVar.l != null && bpVar.i()) {
                buVar.a(z);
                buVar.a(new bt((byte) 11, (byte) 8, bpVar.l.size()));
                for (Entry entry : bpVar.l.entrySet()) {
                    buVar.a((String) entry.getKey());
                    buVar.a(((Integer) entry.getValue()).intValue());
                }
            }
            buVar.c();
            buVar.b();
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            bp bpVar = (bp) yVar;
            buVar.d();
            while (true) {
                br f = buVar.f();
                if (f.b != null) {
                    bs h;
                    int i;
                    switch (f.c) {
                        case SimpleLog.LOG_LEVEL_TRACE:
                            if (f.b == (byte) 12) {
                                bpVar.a = new as();
                                bpVar.a.a(buVar);
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_DEBUG:
                            if (f.b == (byte) 12) {
                                bpVar.b = new ar();
                                bpVar.b.a(buVar);
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case MqttConnectOptions.MQTT_VERSION_3_1:
                            if (f.b == (byte) 12) {
                                bpVar.c = new au();
                                bpVar.c.a(buVar);
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case MqttConnectOptions.MQTT_VERSION_3_1_1:
                            if (f.b == (byte) 12) {
                                bpVar.d = new bh();
                                bpVar.d.a(buVar);
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_ERROR:
                            if (f.b == (byte) 12) {
                                bpVar.e = new ap();
                                bpVar.e.a(buVar);
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_FATAL:
                            if (f.b == (byte) 15) {
                                h = buVar.h();
                                bpVar.f = new ArrayList(h.b);
                                for (i = 0; i < h.b; i++) {
                                    be beVar = new be();
                                    beVar.a(buVar);
                                    bpVar.f.add(beVar);
                                }
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_OFF:
                            if (f.b == (byte) 15) {
                                h = buVar.h();
                                bpVar.g = new ArrayList(h.b);
                                for (i = 0; i < h.b; i++) {
                                    bn bnVar = new bn();
                                    bnVar.a(buVar);
                                    bpVar.g.add(bnVar);
                                }
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SpdyProtocol.PUBKEY_SEQ_ADASH:
                            if (f.b == (byte) 12) {
                                bpVar.h = new bc();
                                bpVar.h.a(buVar);
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SpdyProtocol.PUBKEY_PSEQ_ADASH:
                            if (f.b == (byte) 12) {
                                bpVar.i = new bb();
                                bpVar.i.a(buVar);
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SpdyProtocol.PUBKEY_SEQ_OPEN:
                            if (f.b == (byte) 12) {
                                bpVar.j = new aq();
                                bpVar.j.a(buVar);
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SpdyProtocol.PUBKEY_PSEQ_OPEN:
                            if (f.b == (byte) 12) {
                                bpVar.k = new at();
                                bpVar.k.a(buVar);
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case R.styleable.Toolbar_titleMargins:
                            if (f.b == 13) {
                                bt g = buVar.g();
                                bpVar.l = new HashMap(g.c * 2);
                                for (i = 0; i < g.c; i++) {
                                    bpVar.l.put(buVar.p(), Integer.valueOf(buVar.m()));
                                }
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
                    bpVar.j();
                    return;
                }
            }
        }
    }

    // compiled from: UALogEntry.java
    private static class b implements cd {
        private b() {
        }

        public final /* synthetic */ cc a() {
            return new a();
        }
    }

    // compiled from: UALogEntry.java
    private static class c extends cf<bp> {
        private c() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            bp bpVar = (bp) yVar;
            buVar = (cb) buVar;
            bpVar.a.b(buVar);
            bpVar.b.b(buVar);
            bpVar.c.b(buVar);
            bpVar.d.b(buVar);
            BitSet bitSet = new BitSet();
            if (bpVar.a()) {
                bitSet.set(0);
            }
            if (bpVar.c()) {
                bitSet.set(1);
            }
            if (bpVar.d()) {
                bitSet.set(SimpleLog.LOG_LEVEL_DEBUG);
            }
            if (bpVar.e()) {
                bitSet.set(MqttConnectOptions.MQTT_VERSION_3_1);
            }
            if (bpVar.f()) {
                bitSet.set(MqttConnectOptions.MQTT_VERSION_3_1_1);
            }
            if (bpVar.g()) {
                bitSet.set(SimpleLog.LOG_LEVEL_ERROR);
            }
            if (bpVar.h()) {
                bitSet.set(SimpleLog.LOG_LEVEL_FATAL);
            }
            if (bpVar.i()) {
                bitSet.set(SimpleLog.LOG_LEVEL_OFF);
            }
            buVar.a(bitSet, SpdyProtocol.PUBKEY_SEQ_ADASH);
            if (bpVar.a()) {
                bpVar.e.b(buVar);
            }
            if (bpVar.c()) {
                buVar.a(bpVar.f.size());
                for (be beVar : bpVar.f) {
                    beVar.b(buVar);
                }
            }
            if (bpVar.d()) {
                buVar.a(bpVar.g.size());
                for (bn bnVar : bpVar.g) {
                    bnVar.b(buVar);
                }
            }
            if (bpVar.e()) {
                bpVar.h.b(buVar);
            }
            if (bpVar.f()) {
                bpVar.i.b(buVar);
            }
            if (bpVar.g()) {
                bpVar.j.b(buVar);
            }
            if (bpVar.h()) {
                bpVar.k.b(buVar);
            }
            if (bpVar.i()) {
                buVar.a(bpVar.l.size());
                for (Entry entry : bpVar.l.entrySet()) {
                    buVar.a((String) entry.getKey());
                    buVar.a(((Integer) entry.getValue()).intValue());
                }
            }
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            bs bsVar;
            int i;
            int i2 = 0;
            bp bpVar = (bp) yVar;
            buVar = (cb) buVar;
            bpVar.a = new as();
            bpVar.a.a(buVar);
            bpVar.b = new ar();
            bpVar.b.a(buVar);
            bpVar.c = new au();
            bpVar.c.a(buVar);
            bpVar.d = new bh();
            bpVar.d.a(buVar);
            BitSet b = buVar.b(SpdyProtocol.PUBKEY_SEQ_ADASH);
            if (b.get(0)) {
                bpVar.e = new ap();
                bpVar.e.a(buVar);
            }
            if (b.get(1)) {
                bsVar = new bs((byte) 12, buVar.m());
                bpVar.f = new ArrayList(bsVar.b);
                for (i = 0; i < bsVar.b; i++) {
                    be beVar = new be();
                    beVar.a(buVar);
                    bpVar.f.add(beVar);
                }
            }
            if (b.get(SimpleLog.LOG_LEVEL_DEBUG)) {
                bsVar = new bs((byte) 12, buVar.m());
                bpVar.g = new ArrayList(bsVar.b);
                for (i = 0; i < bsVar.b; i++) {
                    bn bnVar = new bn();
                    bnVar.a(buVar);
                    bpVar.g.add(bnVar);
                }
            }
            if (b.get(MqttConnectOptions.MQTT_VERSION_3_1)) {
                bpVar.h = new bc();
                bpVar.h.a(buVar);
            }
            if (b.get(MqttConnectOptions.MQTT_VERSION_3_1_1)) {
                bpVar.i = new bb();
                bpVar.i.a(buVar);
            }
            if (b.get(SimpleLog.LOG_LEVEL_ERROR)) {
                bpVar.j = new aq();
                bpVar.j.a(buVar);
            }
            if (b.get(SimpleLog.LOG_LEVEL_FATAL)) {
                bpVar.k = new at();
                bpVar.k.a(buVar);
            }
            if (b.get(SimpleLog.LOG_LEVEL_OFF)) {
                bt btVar = new bt((byte) 11, (byte) 8, buVar.m());
                bpVar.l = new HashMap(btVar.c * 2);
                while (i2 < btVar.c) {
                    bpVar.l.put(buVar.p(), Integer.valueOf(buVar.m()));
                    i2++;
                }
            }
        }
    }

    // compiled from: UALogEntry.java
    private static class d implements cd {
        private d() {
        }

        public final /* synthetic */ cc a() {
            return new c();
        }
    }

    // compiled from: UALogEntry.java
    public enum e implements ad {
        CLIENT_STATS((short) 1, "client_stats"),
        APP_INFO((short) 2, "app_info"),
        DEVICE_INFO((short) 3, "device_info"),
        MISC_INFO((short) 4, "misc_info"),
        ACTIVATE_MSG((short) 5, "activate_msg"),
        INSTANT_MSGS((short) 6, "instant_msgs"),
        SESSIONS((short) 7, "sessions"),
        IMPRINT((short) 8, "imprint"),
        ID_TRACKING((short) 9, "id_tracking"),
        ACTIVE_USER((short) 10, "active_user"),
        CONTROL_POLICY((short) 11, "control_policy"),
        GROUP_INFO((short) 12, "group_info");
        private static final Map<String, u.aly.bp.e> m;
        private final short n;
        private final String o;

        static {
            String str = "client_stats";
            a = new u.aly.bp.e("CLIENT_STATS", 0, (short) 1, "client_stats");
            String str2 = "app_info";
            b = new u.aly.bp.e("APP_INFO", 1, (short) 2, "app_info");
            str2 = "device_info";
            c = new u.aly.bp.e("DEVICE_INFO", 2, (short) 3, "device_info");
            str2 = "misc_info";
            d = new u.aly.bp.e("MISC_INFO", 3, (short) 4, "misc_info");
            str2 = "activate_msg";
            e = new u.aly.bp.e("ACTIVATE_MSG", 4, (short) 5, "activate_msg");
            str = "instant_msgs";
            f = new u.aly.bp.e("INSTANT_MSGS", 5, (short) 6, "instant_msgs");
            String str3 = "sessions";
            g = new u.aly.bp.e("SESSIONS", 6, (short) 7, "sessions");
            str3 = "imprint";
            h = new u.aly.bp.e("IMPRINT", 7, (short) 8, "imprint");
            str3 = "id_tracking";
            i = new u.aly.bp.e("ID_TRACKING", 8, (short) 9, "id_tracking");
            str3 = "active_user";
            j = new u.aly.bp.e("ACTIVE_USER", 9, (short) 10, "active_user");
            str3 = "control_policy";
            k = new u.aly.bp.e("CONTROL_POLICY", 10, (short) 11, "control_policy");
            str3 = "group_info";
            l = new u.aly.bp.e("GROUP_INFO", 11, (short) 12, "group_info");
            p = new u.aly.bp.e[]{a, b, c, d, e, f, g, h, i, j, k, l};
            m = new HashMap();
            Iterator it = EnumSet.allOf(u.aly.bp.e.class).iterator();
            while (it.hasNext()) {
                u.aly.bp.e eVar = (u.aly.bp.e) it.next();
                m.put(eVar.b(), eVar);
            }
        }

        public static u.aly.bp.e a(int i) {
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
                case SpdyProtocol.PUBKEY_PSEQ_OPEN:
                    return k;
                case R.styleable.Toolbar_titleMargins:
                    return l;
                default:
                    return null;
            }
        }

        public static u.aly.bp.e b(int i) {
            u.aly.bp.e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException(new StringBuilder("Field ").append(i).append(" doesn't exist!").toString());
        }

        public static u.aly.bp.e a(String str) {
            return (u.aly.bp.e) m.get(str);
        }

        private e(short s, String str) {
            this.n = s;
            this.o = str;
        }

        public final short a() {
            return this.n;
        }

        public final String b() {
            return this.o;
        }
    }

    static {
        n = new ca("UALogEntry");
        o = new br("client_stats", (byte) 12, (short) 1);
        p = new br("app_info", (byte) 12, (short) 2);
        q = new br("device_info", (byte) 12, (short) 3);
        r = new br("misc_info", (byte) 12, (short) 4);
        s = new br("activate_msg", (byte) 12, (short) 5);
        t = new br("instant_msgs", (byte) 15, (short) 6);
        u = new br("sessions", (byte) 15, (short) 7);
        v = new br("imprint", (byte) 12, (short) 8);
        w = new br("id_tracking", (byte) 12, (short) 9);
        x = new br("active_user", (byte) 12, (short) 10);
        y = new br("control_policy", (byte) 12, (short) 11);
        z = new br("group_info", (byte) 13, (short) 12);
        Map hashMap = new HashMap();
        A = hashMap;
        hashMap.put(ce.class, new b());
        A.put(cf.class, new d());
        hashMap = new EnumMap(e.class);
        hashMap.put(e.a, new ah("client_stats", (byte) 1, new al(as.class)));
        hashMap.put(e.b, new ah("app_info", (byte) 1, new al(ar.class)));
        hashMap.put(e.c, new ah("device_info", (byte) 1, new al(au.class)));
        hashMap.put(e.d, new ah("misc_info", (byte) 1, new al(bh.class)));
        hashMap.put(e.e, new ah("activate_msg", (byte) 2, new al(ap.class)));
        hashMap.put(e.f, new ah("instant_msgs", (byte) 2, new aj(new al(be.class))));
        hashMap.put(e.g, new ah("sessions", (byte) 2, new aj(new al(bn.class))));
        hashMap.put(e.h, new ah("imprint", (byte) 2, new al(bc.class)));
        hashMap.put(e.i, new ah("id_tracking", (byte) 2, new al(bb.class)));
        hashMap.put(e.j, new ah("active_user", (byte) 2, new al(aq.class)));
        hashMap.put(e.k, new ah("control_policy", (byte) 2, new al(at.class)));
        hashMap.put(e.l, new ah("group_info", (byte) 2, new ak(new ai((byte) 11), new ai((byte) 8))));
        m = Collections.unmodifiableMap(hashMap);
        ah.a(bp.class, m);
    }

    public bp() {
        this.B = new e[]{e.e, e.f, e.g, e.h, e.i, e.j, e.k, e.l};
    }

    public final boolean a() {
        return this.e != null;
    }

    public final int b() {
        return this.f == null ? 0 : this.f.size();
    }

    public final void a(be beVar) {
        if (this.f == null) {
            this.f = new ArrayList();
        }
        this.f.add(beVar);
    }

    public final boolean c() {
        return this.f != null;
    }

    public final boolean d() {
        return this.g != null;
    }

    public final boolean e() {
        return this.h != null;
    }

    public final boolean f() {
        return this.i != null;
    }

    public final boolean g() {
        return this.j != null;
    }

    public final boolean h() {
        return this.k != null;
    }

    public final boolean i() {
        return this.l != null;
    }

    public final void a(bu buVar) throws ac {
        ((cd) A.get(buVar.s())).a().b(buVar, this);
    }

    public final void b(bu buVar) throws ac {
        ((cd) A.get(buVar.s())).a().a(buVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("UALogEntry(");
        stringBuilder.append("client_stats:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("app_info:");
        if (this.b == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.b);
        }
        stringBuilder.append(", ");
        stringBuilder.append("device_info:");
        if (this.c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("misc_info:");
        if (this.d == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.d);
        }
        if (a()) {
            stringBuilder.append(", ");
            stringBuilder.append("activate_msg:");
            if (this.e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.e);
            }
        }
        if (c()) {
            stringBuilder.append(", ");
            stringBuilder.append("instant_msgs:");
            if (this.f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f);
            }
        }
        if (d()) {
            stringBuilder.append(", ");
            stringBuilder.append("sessions:");
            if (this.g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.g);
            }
        }
        if (e()) {
            stringBuilder.append(", ");
            stringBuilder.append("imprint:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
        }
        if (f()) {
            stringBuilder.append(", ");
            stringBuilder.append("id_tracking:");
            if (this.i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.i);
            }
        }
        if (g()) {
            stringBuilder.append(", ");
            stringBuilder.append("active_user:");
            if (this.j == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.j);
            }
        }
        if (h()) {
            stringBuilder.append(", ");
            stringBuilder.append("control_policy:");
            if (this.k == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.k);
            }
        }
        if (i()) {
            stringBuilder.append(", ");
            stringBuilder.append("group_info:");
            if (this.l == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.l);
            }
        }
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }

    public final void j() throws ac {
        if (this.a == null) {
            throw new bv(new StringBuilder("Required field 'client_stats' was not present! Struct: ").append(toString()).toString());
        } else if (this.b == null) {
            throw new bv(new StringBuilder("Required field 'app_info' was not present! Struct: ").append(toString()).toString());
        } else if (this.c == null) {
            throw new bv(new StringBuilder("Required field 'device_info' was not present! Struct: ").append(toString()).toString());
        } else if (this.d == null) {
            throw new bv(new StringBuilder("Required field 'misc_info' was not present! Struct: ").append(toString()).toString());
        } else {
            if (this.a != null) {
                as.e();
            }
            if (this.b != null) {
                this.b.j();
            }
            if (this.c != null) {
                this.c.v();
            }
            if (this.d != null) {
                bh.o();
            }
            if (this.e != null) {
                ap.b();
            }
            if (this.h != null) {
                this.h.c();
            }
            if (this.i != null) {
                this.i.c();
            }
            if (this.j != null) {
                this.j.a();
            }
            if (this.k != null) {
                this.k.b();
            }
        }
    }
}
