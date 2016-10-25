package u.aly;

import com.umeng.message.proguard.j;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: Imprint.java
public class bc implements Serializable, Cloneable, y<bc, e> {
    public static final Map<e, ah> e;
    private static final ca f;
    private static final br g;
    private static final br h;
    private static final br i;
    private static final Map<Class<? extends cc>, cd> j;
    public Map<String, bd> a;
    public int b;
    public String c;
    byte d;

    // compiled from: Imprint.java
    private static class a extends ce<bc> {
        private a() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            bc bcVar = (bc) yVar;
            bcVar.c();
            f;
            buVar.a();
            if (bcVar.a != null) {
                buVar.a(g);
                buVar.a(new bt((byte) 11, (byte) 12, bcVar.a.size()));
                for (Entry entry : bcVar.a.entrySet()) {
                    buVar.a((String) entry.getKey());
                    ((bd) entry.getValue()).b(buVar);
                }
            }
            buVar.a(h);
            buVar.a(bcVar.b);
            if (bcVar.c != null) {
                buVar.a(i);
                buVar.a(bcVar.c);
            }
            buVar.c();
            buVar.b();
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            bc bcVar = (bc) yVar;
            buVar.d();
            while (true) {
                br f = buVar.f();
                if (f.b != null) {
                    switch (f.c) {
                        case SimpleLog.LOG_LEVEL_TRACE:
                            if (f.b == 13) {
                                bt g = buVar.g();
                                bcVar.a = new HashMap(g.c * 2);
                                for (int i = 0; i < g.c; i++) {
                                    String p = buVar.p();
                                    bd bdVar = new bd();
                                    bdVar.a(buVar);
                                    bcVar.a.put(p, bdVar);
                                }
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_DEBUG:
                            if (f.b == 8) {
                                bcVar.b = buVar.m();
                                bcVar.b();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case MqttConnectOptions.MQTT_VERSION_3_1:
                            if (f.b == 11) {
                                bcVar.c = buVar.p();
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
                    if (w.a(bcVar.d, 0)) {
                        bcVar.c();
                        return;
                    }
                    throw new bv(new StringBuilder("Required field 'version' was not found in serialized data! Struct: ").append(toString()).toString());
                }
            }
        }
    }

    // compiled from: Imprint.java
    private static class b implements cd {
        private b() {
        }

        public final /* synthetic */ cc a() {
            return new a();
        }
    }

    // compiled from: Imprint.java
    private static class c extends cf<bc> {
        private c() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            bc bcVar = (bc) yVar;
            cb cbVar = (cb) buVar;
            cbVar.a(bcVar.a.size());
            for (Entry entry : bcVar.a.entrySet()) {
                cbVar.a((String) entry.getKey());
                ((bd) entry.getValue()).b(cbVar);
            }
            cbVar.a(bcVar.b);
            cbVar.a(bcVar.c);
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            bc bcVar = (bc) yVar;
            cb cbVar = (cb) buVar;
            bt btVar = new bt((byte) 11, (byte) 12, cbVar.m());
            bcVar.a = new HashMap(btVar.c * 2);
            for (int i = 0; i < btVar.c; i++) {
                String p = cbVar.p();
                bd bdVar = new bd();
                bdVar.a(cbVar);
                bcVar.a.put(p, bdVar);
            }
            bcVar.b = cbVar.m();
            bcVar.b();
            bcVar.c = cbVar.p();
        }
    }

    // compiled from: Imprint.java
    private static class d implements cd {
        private d() {
        }

        public final /* synthetic */ cc a() {
            return new c();
        }
    }

    // compiled from: Imprint.java
    public enum e implements ad {
        PROPERTY((short) 1, "property"),
        VERSION((short) 2, "version"),
        CHECKSUM((short) 3, "checksum");
        private static final Map<String, u.aly.bc.e> d;
        private final short e;
        private final String f;

        static {
            String str = "property";
            a = new u.aly.bc.e("PROPERTY", 0, (short) 1, "property");
            str = "version";
            b = new u.aly.bc.e("VERSION", 1, (short) 2, "version");
            str = "checksum";
            c = new u.aly.bc.e("CHECKSUM", 2, (short) 3, "checksum");
            g = new u.aly.bc.e[]{a, b, c};
            d = new HashMap();
            Iterator it = EnumSet.allOf(u.aly.bc.e.class).iterator();
            while (it.hasNext()) {
                u.aly.bc.e eVar = (u.aly.bc.e) it.next();
                d.put(eVar.b(), eVar);
            }
        }

        public static u.aly.bc.e a(int i) {
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

        public static u.aly.bc.e b(int i) {
            u.aly.bc.e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException(new StringBuilder("Field ").append(i).append(" doesn't exist!").toString());
        }

        public static u.aly.bc.e a(String str) {
            return (u.aly.bc.e) d.get(str);
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
        f = new ca("Imprint");
        g = new br("property", (byte) 13, (short) 1);
        h = new br("version", (byte) 8, (short) 2);
        i = new br("checksum", (byte) 11, (short) 3);
        Map hashMap = new HashMap();
        j = hashMap;
        hashMap.put(ce.class, new b());
        j.put(cf.class, new d());
        hashMap = new EnumMap(e.class);
        hashMap.put(e.a, new ah("property", (byte) 1, new ak(new ai((byte) 11), new al(bd.class))));
        hashMap.put(e.b, new ah("version", (byte) 1, new ai((byte) 8)));
        hashMap.put(e.c, new ah("checksum", (byte) 1, new ai((byte) 11)));
        e = Collections.unmodifiableMap(hashMap);
        ah.a(bc.class, e);
    }

    public bc() {
        this.d = (byte) 0;
    }

    public final boolean a() {
        return this.a != null;
    }

    public final void b() {
        this.d = (byte) (this.d | 1);
    }

    public final void a(bu buVar) throws ac {
        ((cd) j.get(buVar.s())).a().b(buVar, this);
    }

    public final void b(bu buVar) throws ac {
        ((cd) j.get(buVar.s())).a().a(buVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Imprint(");
        stringBuilder.append("property:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("version:");
        stringBuilder.append(this.b);
        stringBuilder.append(", ");
        stringBuilder.append("checksum:");
        if (this.c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }

    public final void c() throws ac {
        if (this.a == null) {
            throw new bv(new StringBuilder("Required field 'property' was not present! Struct: ").append(toString()).toString());
        } else if (this.c == null) {
            throw new bv(new StringBuilder("Required field 'checksum' was not present! Struct: ").append(toString()).toString());
        }
    }
}
