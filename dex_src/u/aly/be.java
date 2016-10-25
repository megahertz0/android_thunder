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

// compiled from: InstantMsg.java
public class be implements Serializable, Cloneable, y<be, e> {
    public static final Map<e, ah> e;
    private static final ca f;
    private static final br g;
    private static final br h;
    private static final br i;
    private static final br j;
    private static final Map<Class<? extends cc>, cd> k;
    public String a;
    public List<av> b;
    public List<ax> c;
    public List<ax> d;
    private e[] l;

    // compiled from: InstantMsg.java
    private static class a extends ce<be> {
        private a() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            be beVar = (be) yVar;
            beVar.d();
            f;
            buVar.a();
            if (beVar.a != null) {
                buVar.a(g);
                buVar.a(beVar.a);
            }
            if (beVar.b != null && beVar.a()) {
                buVar.a(h);
                buVar.a(new bs((byte) 12, beVar.b.size()));
                for (av avVar : beVar.b) {
                    avVar.b(buVar);
                }
            }
            if (beVar.c != null && beVar.b()) {
                buVar.a(i);
                buVar.a(new bs((byte) 12, beVar.c.size()));
                for (ax axVar : beVar.c) {
                    axVar.b(buVar);
                }
            }
            if (beVar.d != null && beVar.c()) {
                buVar.a(j);
                buVar.a(new bs((byte) 12, beVar.d.size()));
                for (ax axVar2 : beVar.d) {
                    axVar2.b(buVar);
                }
            }
            buVar.c();
            buVar.b();
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            be beVar = (be) yVar;
            buVar.d();
            while (true) {
                br f = buVar.f();
                if (f.b != null) {
                    bs h;
                    int i;
                    ax axVar;
                    switch (f.c) {
                        case SimpleLog.LOG_LEVEL_TRACE:
                            if (f.b == 11) {
                                beVar.a = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_DEBUG:
                            if (f.b == (byte) 15) {
                                h = buVar.h();
                                beVar.b = new ArrayList(h.b);
                                for (i = 0; i < h.b; i++) {
                                    av avVar = new av();
                                    avVar.a(buVar);
                                    beVar.b.add(avVar);
                                }
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case MqttConnectOptions.MQTT_VERSION_3_1:
                            if (f.b == (byte) 15) {
                                h = buVar.h();
                                beVar.c = new ArrayList(h.b);
                                for (i = 0; i < h.b; i++) {
                                    axVar = new ax();
                                    axVar.a(buVar);
                                    beVar.c.add(axVar);
                                }
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case MqttConnectOptions.MQTT_VERSION_3_1_1:
                            if (f.b == (byte) 15) {
                                h = buVar.h();
                                beVar.d = new ArrayList(h.b);
                                for (i = 0; i < h.b; i++) {
                                    axVar = new ax();
                                    axVar.a(buVar);
                                    beVar.d.add(axVar);
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
                    beVar.d();
                    return;
                }
            }
        }
    }

    // compiled from: InstantMsg.java
    private static class b implements cd {
        private b() {
        }

        public final /* synthetic */ cc a() {
            return new a();
        }
    }

    // compiled from: InstantMsg.java
    private static class c extends cf<be> {
        private c() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            be beVar = (be) yVar;
            cb cbVar = (cb) buVar;
            cbVar.a(beVar.a);
            BitSet bitSet = new BitSet();
            if (beVar.a()) {
                bitSet.set(0);
            }
            if (beVar.b()) {
                bitSet.set(1);
            }
            if (beVar.c()) {
                bitSet.set(SimpleLog.LOG_LEVEL_DEBUG);
            }
            cbVar.a(bitSet, MqttConnectOptions.MQTT_VERSION_3_1);
            if (beVar.a()) {
                cbVar.a(beVar.b.size());
                for (av avVar : beVar.b) {
                    avVar.b(cbVar);
                }
            }
            if (beVar.b()) {
                cbVar.a(beVar.c.size());
                for (ax axVar : beVar.c) {
                    axVar.b(cbVar);
                }
            }
            if (beVar.c()) {
                cbVar.a(beVar.d.size());
                for (ax axVar2 : beVar.d) {
                    axVar2.b(cbVar);
                }
            }
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            int i;
            int i2 = 0;
            be beVar = (be) yVar;
            buVar = (cb) buVar;
            beVar.a = buVar.p();
            BitSet b = buVar.b(MqttConnectOptions.MQTT_VERSION_3_1);
            if (b.get(0)) {
                bs bsVar;
                bsVar = new bs((byte) 12, buVar.m());
                beVar.b = new ArrayList(bsVar.b);
                for (i = 0; i < bsVar.b; i++) {
                    av avVar = new av();
                    avVar.a(buVar);
                    beVar.b.add(avVar);
                }
            }
            if (b.get(1)) {
                bsVar = new bs((byte) 12, buVar.m());
                beVar.c = new ArrayList(bsVar.b);
                for (i = 0; i < bsVar.b; i++) {
                    ax axVar = new ax();
                    axVar.a(buVar);
                    beVar.c.add(axVar);
                }
            }
            if (b.get(SimpleLog.LOG_LEVEL_DEBUG)) {
                bs bsVar2 = new bs((byte) 12, buVar.m());
                beVar.d = new ArrayList(bsVar2.b);
                while (i2 < bsVar2.b) {
                    ax axVar2 = new ax();
                    axVar2.a(buVar);
                    beVar.d.add(axVar2);
                    i2++;
                }
            }
        }
    }

    // compiled from: InstantMsg.java
    private static class d implements cd {
        private d() {
        }

        public final /* synthetic */ cc a() {
            return new c();
        }
    }

    // compiled from: InstantMsg.java
    public enum e implements ad {
        ID((short) 1, AgooConstants.MESSAGE_ID),
        ERRORS((short) 2, "errors"),
        EVENTS((short) 3, "events"),
        GAME_EVENTS((short) 4, "game_events");
        private static final Map<String, u.aly.be.e> e;
        private final short f;
        private final String g;

        static {
            String str = AgooConstants.MESSAGE_ID;
            a = new u.aly.be.e("ID", 0, (short) 1, AgooConstants.MESSAGE_ID);
            str = "errors";
            b = new u.aly.be.e("ERRORS", 1, (short) 2, "errors");
            str = "events";
            c = new u.aly.be.e("EVENTS", 2, (short) 3, "events");
            str = "game_events";
            d = new u.aly.be.e("GAME_EVENTS", 3, (short) 4, "game_events");
            h = new u.aly.be.e[]{a, b, c, d};
            e = new HashMap();
            Iterator it = EnumSet.allOf(u.aly.be.e.class).iterator();
            while (it.hasNext()) {
                u.aly.be.e eVar = (u.aly.be.e) it.next();
                e.put(eVar.b(), eVar);
            }
        }

        public static u.aly.be.e a(int i) {
            switch (i) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    return a;
                case SimpleLog.LOG_LEVEL_DEBUG:
                    return b;
                case MqttConnectOptions.MQTT_VERSION_3_1:
                    return c;
                case MqttConnectOptions.MQTT_VERSION_3_1_1:
                    return d;
                default:
                    return null;
            }
        }

        public static u.aly.be.e b(int i) {
            u.aly.be.e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException(new StringBuilder("Field ").append(i).append(" doesn't exist!").toString());
        }

        public static u.aly.be.e a(String str) {
            return (u.aly.be.e) e.get(str);
        }

        private e(short s, String str) {
            this.f = s;
            this.g = str;
        }

        public final short a() {
            return this.f;
        }

        public final String b() {
            return this.g;
        }
    }

    static {
        f = new ca("InstantMsg");
        g = new br(AgooConstants.MESSAGE_ID, (byte) 11, (short) 1);
        h = new br("errors", (byte) 15, (short) 2);
        i = new br("events", (byte) 15, (short) 3);
        j = new br("game_events", (byte) 15, (short) 4);
        Map hashMap = new HashMap();
        k = hashMap;
        hashMap.put(ce.class, new b());
        k.put(cf.class, new d());
        hashMap = new EnumMap(e.class);
        hashMap.put(e.a, new ah(AgooConstants.MESSAGE_ID, (byte) 1, new ai((byte) 11)));
        hashMap.put(e.b, new ah("errors", (byte) 2, new aj(new al(av.class))));
        hashMap.put(e.c, new ah("events", (byte) 2, new aj(new al(ax.class))));
        hashMap.put(e.d, new ah("game_events", (byte) 2, new aj(new al(ax.class))));
        e = Collections.unmodifiableMap(hashMap);
        ah.a(be.class, e);
    }

    public be() {
        this.l = new e[]{e.b, e.c, e.d};
    }

    public final boolean a() {
        return this.b != null;
    }

    public final boolean b() {
        return this.c != null;
    }

    public final boolean c() {
        return this.d != null;
    }

    public final void a(bu buVar) throws ac {
        ((cd) k.get(buVar.s())).a().b(buVar, this);
    }

    public final void b(bu buVar) throws ac {
        ((cd) k.get(buVar.s())).a().a(buVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("InstantMsg(");
        stringBuilder.append("id:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        if (a()) {
            stringBuilder.append(", ");
            stringBuilder.append("errors:");
            if (this.b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.b);
            }
        }
        if (b()) {
            stringBuilder.append(", ");
            stringBuilder.append("events:");
            if (this.c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.c);
            }
        }
        if (c()) {
            stringBuilder.append(", ");
            stringBuilder.append("game_events:");
            if (this.d == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.d);
            }
        }
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }

    public final void d() throws ac {
        if (this.a == null) {
            throw new bv(new StringBuilder("Required field 'id' was not present! Struct: ").append(toString()).toString());
        }
    }
}
