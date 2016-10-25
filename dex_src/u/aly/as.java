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
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: ClientStats.java
public class as implements Serializable, Cloneable, y<as, e> {
    public static final Map<e, ah> e;
    private static final ca f;
    private static final br g;
    private static final br h;
    private static final br i;
    private static final Map<Class<? extends cc>, cd> j;
    public int a;
    public int b;
    public int c;
    byte d;
    private e[] k;

    // compiled from: ClientStats.java
    private static class a extends ce<as> {
        private a() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            as asVar = (as) yVar;
            as.e();
            f;
            buVar.a();
            buVar.a(g);
            buVar.a(asVar.a);
            buVar.a(h);
            buVar.a(asVar.b);
            if (asVar.c()) {
                buVar.a(i);
                buVar.a(asVar.c);
            }
            buVar.c();
            buVar.b();
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            as asVar = (as) yVar;
            buVar.d();
            while (true) {
                br f = buVar.f();
                if (f.b != null) {
                    switch (f.c) {
                        case SimpleLog.LOG_LEVEL_TRACE:
                            if (f.b == (byte) 8) {
                                asVar.a = buVar.m();
                                asVar.a();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_DEBUG:
                            if (f.b == (byte) 8) {
                                asVar.b = buVar.m();
                                asVar.b();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case MqttConnectOptions.MQTT_VERSION_3_1:
                            if (f.b == (byte) 8) {
                                asVar.c = buVar.m();
                                asVar.d();
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
                    if (!w.a(asVar.d, 0)) {
                        throw new bv(new StringBuilder("Required field 'successful_requests' was not found in serialized data! Struct: ").append(toString()).toString());
                    } else if (w.a(asVar.d, 1)) {
                        as.e();
                        return;
                    } else {
                        throw new bv(new StringBuilder("Required field 'failed_requests' was not found in serialized data! Struct: ").append(toString()).toString());
                    }
                }
            }
        }
    }

    // compiled from: ClientStats.java
    private static class b implements cd {
        private b() {
        }

        public final /* synthetic */ cc a() {
            return new a();
        }
    }

    // compiled from: ClientStats.java
    private static class c extends cf<as> {
        private c() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            as asVar = (as) yVar;
            cb cbVar = (cb) buVar;
            cbVar.a(asVar.a);
            cbVar.a(asVar.b);
            BitSet bitSet = new BitSet();
            if (asVar.c()) {
                bitSet.set(0);
            }
            cbVar.a(bitSet, 1);
            if (asVar.c()) {
                cbVar.a(asVar.c);
            }
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            as asVar = (as) yVar;
            cb cbVar = (cb) buVar;
            asVar.a = cbVar.m();
            asVar.a();
            asVar.b = cbVar.m();
            asVar.b();
            if (cbVar.b(1).get(0)) {
                asVar.c = cbVar.m();
                asVar.d();
            }
        }
    }

    // compiled from: ClientStats.java
    private static class d implements cd {
        private d() {
        }

        public final /* synthetic */ cc a() {
            return new c();
        }
    }

    // compiled from: ClientStats.java
    public enum e implements ad {
        SUCCESSFUL_REQUESTS((short) 1, "successful_requests"),
        FAILED_REQUESTS((short) 2, "failed_requests"),
        LAST_REQUEST_SPENT_MS((short) 3, "last_request_spent_ms");
        private static final Map<String, u.aly.as.e> d;
        private final short e;
        private final String f;

        static {
            String str = "successful_requests";
            a = new u.aly.as.e("SUCCESSFUL_REQUESTS", 0, (short) 1, "successful_requests");
            str = "failed_requests";
            b = new u.aly.as.e("FAILED_REQUESTS", 1, (short) 2, "failed_requests");
            str = "last_request_spent_ms";
            c = new u.aly.as.e("LAST_REQUEST_SPENT_MS", 2, (short) 3, "last_request_spent_ms");
            g = new u.aly.as.e[]{a, b, c};
            d = new HashMap();
            Iterator it = EnumSet.allOf(u.aly.as.e.class).iterator();
            while (it.hasNext()) {
                u.aly.as.e eVar = (u.aly.as.e) it.next();
                d.put(eVar.b(), eVar);
            }
        }

        public static u.aly.as.e a(int i) {
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

        public static u.aly.as.e b(int i) {
            u.aly.as.e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException(new StringBuilder("Field ").append(i).append(" doesn't exist!").toString());
        }

        public static u.aly.as.e a(String str) {
            return (u.aly.as.e) d.get(str);
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
        f = new ca("ClientStats");
        g = new br("successful_requests", (byte) 8, (short) 1);
        h = new br("failed_requests", (byte) 8, (short) 2);
        i = new br("last_request_spent_ms", (byte) 8, (short) 3);
        Map hashMap = new HashMap();
        j = hashMap;
        hashMap.put(ce.class, new b());
        j.put(cf.class, new d());
        hashMap = new EnumMap(e.class);
        hashMap.put(e.a, new ah("successful_requests", (byte) 1, new ai((byte) 8)));
        hashMap.put(e.b, new ah("failed_requests", (byte) 1, new ai((byte) 8)));
        hashMap.put(e.c, new ah("last_request_spent_ms", (byte) 2, new ai((byte) 8)));
        e = Collections.unmodifiableMap(hashMap);
        ah.a(as.class, e);
    }

    public as() {
        this.d = (byte) 0;
        this.k = new e[]{e.c};
        this.a = 0;
        this.b = 0;
    }

    public final void a() {
        this.d = (byte) (this.d | 1);
    }

    public final void b() {
        this.d = (byte) (this.d | 2);
    }

    public final boolean c() {
        return w.a(this.d, SimpleLog.LOG_LEVEL_DEBUG);
    }

    public final void d() {
        this.d = (byte) (this.d | 4);
    }

    public final void a(bu buVar) throws ac {
        ((cd) j.get(buVar.s())).a().b(buVar, this);
    }

    public final void b(bu buVar) throws ac {
        ((cd) j.get(buVar.s())).a().a(buVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("ClientStats(");
        stringBuilder.append("successful_requests:");
        stringBuilder.append(this.a);
        stringBuilder.append(", ");
        stringBuilder.append("failed_requests:");
        stringBuilder.append(this.b);
        if (c()) {
            stringBuilder.append(", ");
            stringBuilder.append("last_request_spent_ms:");
            stringBuilder.append(this.c);
        }
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }

    public static void e() throws ac {
    }
}
