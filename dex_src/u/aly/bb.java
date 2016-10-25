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
import java.util.Map.Entry;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: IdTracking.java
public class bb implements Serializable, Cloneable, y<bb, e> {
    public static final Map<e, ah> d;
    private static final ca e;
    private static final br f;
    private static final br g;
    private static final br h;
    private static final Map<Class<? extends cc>, cd> i;
    public Map<String, ba> a;
    public List<az> b;
    public String c;
    private e[] j;

    // compiled from: IdTracking.java
    private static class a extends ce<bb> {
        private a() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            bb bbVar = (bb) yVar;
            bbVar.c();
            e;
            buVar.a();
            if (bbVar.a != null) {
                buVar.a(f);
                buVar.a(new bt((byte) 11, (byte) 12, bbVar.a.size()));
                for (Entry entry : bbVar.a.entrySet()) {
                    buVar.a((String) entry.getKey());
                    ((ba) entry.getValue()).b(buVar);
                }
            }
            if (bbVar.b != null && bbVar.a()) {
                buVar.a(g);
                buVar.a(new bs((byte) 12, bbVar.b.size()));
                for (az azVar : bbVar.b) {
                    azVar.b(buVar);
                }
            }
            if (bbVar.c != null && bbVar.b()) {
                buVar.a(h);
                buVar.a(bbVar.c);
            }
            buVar.c();
            buVar.b();
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            bb bbVar = (bb) yVar;
            buVar.d();
            while (true) {
                br f = buVar.f();
                if (f.b != null) {
                    int i;
                    switch (f.c) {
                        case SimpleLog.LOG_LEVEL_TRACE:
                            if (f.b == 13) {
                                bt g = buVar.g();
                                bbVar.a = new HashMap(g.c * 2);
                                for (i = 0; i < g.c; i++) {
                                    String p = buVar.p();
                                    ba baVar = new ba();
                                    baVar.a(buVar);
                                    bbVar.a.put(p, baVar);
                                }
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_DEBUG:
                            if (f.b == 15) {
                                bs h = buVar.h();
                                bbVar.b = new ArrayList(h.b);
                                for (i = 0; i < h.b; i++) {
                                    az azVar = new az();
                                    azVar.a(buVar);
                                    bbVar.b.add(azVar);
                                }
                                bbVar.a(true);
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case MqttConnectOptions.MQTT_VERSION_3_1:
                            if (f.b == 11) {
                                bbVar.c = buVar.p();
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
                    bbVar.c();
                    return;
                }
            }
        }
    }

    // compiled from: IdTracking.java
    private static class b implements cd {
        private b() {
        }

        public final /* synthetic */ cc a() {
            return new a();
        }
    }

    // compiled from: IdTracking.java
    private static class c extends cf<bb> {
        private c() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            bb bbVar = (bb) yVar;
            cb cbVar = (cb) buVar;
            cbVar.a(bbVar.a.size());
            for (Entry entry : bbVar.a.entrySet()) {
                cbVar.a((String) entry.getKey());
                ((ba) entry.getValue()).b(cbVar);
            }
            BitSet bitSet = new BitSet();
            if (bbVar.a()) {
                bitSet.set(0);
            }
            if (bbVar.b()) {
                bitSet.set(1);
            }
            cbVar.a(bitSet, SimpleLog.LOG_LEVEL_DEBUG);
            if (bbVar.a()) {
                cbVar.a(bbVar.b.size());
                for (az azVar : bbVar.b) {
                    azVar.b(cbVar);
                }
            }
            if (bbVar.b()) {
                cbVar.a(bbVar.c);
            }
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            int i = 0;
            bb bbVar = (bb) yVar;
            cb cbVar = (cb) buVar;
            bt btVar = new bt((byte) 11, (byte) 12, cbVar.m());
            bbVar.a = new HashMap(btVar.c * 2);
            for (int i2 = 0; i2 < btVar.c; i2++) {
                String p = cbVar.p();
                ba baVar = new ba();
                baVar.a(cbVar);
                bbVar.a.put(p, baVar);
            }
            BitSet b = cbVar.b(SimpleLog.LOG_LEVEL_DEBUG);
            if (b.get(0)) {
                bs bsVar = new bs((byte) 12, cbVar.m());
                bbVar.b = new ArrayList(bsVar.b);
                while (i < bsVar.b) {
                    az azVar = new az();
                    azVar.a(cbVar);
                    bbVar.b.add(azVar);
                    i++;
                }
                bbVar.a(true);
            }
            if (b.get(1)) {
                bbVar.c = cbVar.p();
            }
        }
    }

    // compiled from: IdTracking.java
    private static class d implements cd {
        private d() {
        }

        public final /* synthetic */ cc a() {
            return new c();
        }
    }

    // compiled from: IdTracking.java
    public enum e implements ad {
        SNAPSHOTS((short) 1, "snapshots"),
        JOURNALS((short) 2, "journals"),
        CHECKSUM((short) 3, "checksum");
        private static final Map<String, u.aly.bb.e> d;
        private final short e;
        private final String f;

        static {
            String str = "snapshots";
            a = new u.aly.bb.e("SNAPSHOTS", 0, (short) 1, "snapshots");
            str = "journals";
            b = new u.aly.bb.e("JOURNALS", 1, (short) 2, "journals");
            str = "checksum";
            c = new u.aly.bb.e("CHECKSUM", 2, (short) 3, "checksum");
            g = new u.aly.bb.e[]{a, b, c};
            d = new HashMap();
            Iterator it = EnumSet.allOf(u.aly.bb.e.class).iterator();
            while (it.hasNext()) {
                u.aly.bb.e eVar = (u.aly.bb.e) it.next();
                d.put(eVar.b(), eVar);
            }
        }

        public static u.aly.bb.e a(int i) {
            switch (i) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    return a;
                case SimpleLog.LOG_LEVEL_DEBUG:
                    return b;
                case MqttConnectOptions.MQTT_VERSION_3_1:
                    return c;
                default:
                    return null;
            }
        }

        public static u.aly.bb.e b(int i) {
            u.aly.bb.e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException(new StringBuilder("Field ").append(i).append(" doesn't exist!").toString());
        }

        public static u.aly.bb.e a(String str) {
            return (u.aly.bb.e) d.get(str);
        }

        private e(short s, String str) {
            this.e = s;
            this.f = str;
        }

        public final short a() {
            return this.e;
        }

        public final String b() {
            return this.f;
        }
    }

    static {
        e = new ca("IdTracking");
        f = new br("snapshots", (byte) 13, (short) 1);
        g = new br("journals", (byte) 15, (short) 2);
        h = new br("checksum", (byte) 11, (short) 3);
        Map hashMap = new HashMap();
        i = hashMap;
        hashMap.put(ce.class, new b());
        i.put(cf.class, new d());
        hashMap = new EnumMap(e.class);
        hashMap.put(e.a, new ah("snapshots", (byte) 1, new ak(new ai((byte) 11), new al(ba.class))));
        hashMap.put(e.b, new ah("journals", (byte) 2, new aj(new al(az.class))));
        hashMap.put(e.c, new ah("checksum", (byte) 2, new ai((byte) 11)));
        d = Collections.unmodifiableMap(hashMap);
        ah.a(bb.class, d);
    }

    public bb() {
        this.j = new e[]{e.b, e.c};
    }

    public final boolean a() {
        return this.b != null;
    }

    public final void a(boolean z) {
        if (!z) {
            this.b = null;
        }
    }

    public final boolean b() {
        return this.c != null;
    }

    public final void a(bu buVar) throws ac {
        ((cd) i.get(buVar.s())).a().b(buVar, this);
    }

    public final void b(bu buVar) throws ac {
        ((cd) i.get(buVar.s())).a().a(buVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("IdTracking(");
        stringBuilder.append("snapshots:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        if (a()) {
            stringBuilder.append(", ");
            stringBuilder.append("journals:");
            if (this.b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.b);
            }
        }
        if (b()) {
            stringBuilder.append(", ");
            stringBuilder.append("checksum:");
            if (this.c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.c);
            }
        }
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }

    public final void c() throws ac {
        if (this.a == null) {
            throw new bv(new StringBuilder("Required field 'snapshots' was not present! Struct: ").append(toString()).toString());
        }
    }
}
