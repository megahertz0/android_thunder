package u.aly;

import com.umeng.message.proguard.j;
import com.umeng.message.util.HttpRequest;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: Location.java
public class bg implements Serializable, Cloneable, y<bg, e> {
    public static final Map<e, ah> e;
    private static final ca f;
    private static final br g;
    private static final br h;
    private static final br i;
    private static final Map<Class<? extends cc>, cd> j;
    public double a;
    public double b;
    public long c;
    byte d;

    // compiled from: Location.java
    private static class a extends ce<bg> {
        private a() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            bg bgVar = (bg) yVar;
            bg.d();
            f;
            buVar.a();
            buVar.a(g);
            buVar.a(bgVar.a);
            buVar.a(h);
            buVar.a(bgVar.b);
            buVar.a(i);
            buVar.a(bgVar.c);
            buVar.c();
            buVar.b();
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            bg bgVar = (bg) yVar;
            buVar.d();
            while (true) {
                br f = buVar.f();
                if (f.b != null) {
                    switch (f.c) {
                        case SimpleLog.LOG_LEVEL_TRACE:
                            if (f.b == (byte) 4) {
                                bgVar.a = buVar.o();
                                bgVar.a();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_DEBUG:
                            if (f.b == (byte) 4) {
                                bgVar.b = buVar.o();
                                bgVar.b();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case MqttConnectOptions.MQTT_VERSION_3_1:
                            if (f.b == 10) {
                                bgVar.c = buVar.n();
                                bgVar.c();
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
                    if (!w.a(bgVar.d, 0)) {
                        throw new bv(new StringBuilder("Required field 'lat' was not found in serialized data! Struct: ").append(toString()).toString());
                    } else if (!w.a(bgVar.d, 1)) {
                        throw new bv(new StringBuilder("Required field 'lng' was not found in serialized data! Struct: ").append(toString()).toString());
                    } else if (w.a(bgVar.d, SimpleLog.LOG_LEVEL_DEBUG)) {
                        bg.d();
                        return;
                    } else {
                        throw new bv(new StringBuilder("Required field 'ts' was not found in serialized data! Struct: ").append(toString()).toString());
                    }
                }
            }
        }
    }

    // compiled from: Location.java
    private static class b implements cd {
        private b() {
        }

        public final /* synthetic */ cc a() {
            return new a();
        }
    }

    // compiled from: Location.java
    private static class c extends cf<bg> {
        private c() {
        }

        public final /* bridge */ /* synthetic */ void a(bu buVar, y yVar) throws ac {
            bg bgVar = (bg) yVar;
            cb cbVar = (cb) buVar;
            cbVar.a(bgVar.a);
            cbVar.a(bgVar.b);
            cbVar.a(bgVar.c);
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            bg bgVar = (bg) yVar;
            cb cbVar = (cb) buVar;
            bgVar.a = cbVar.o();
            bgVar.a();
            bgVar.b = cbVar.o();
            bgVar.b();
            bgVar.c = cbVar.n();
            bgVar.c();
        }
    }

    // compiled from: Location.java
    private static class d implements cd {
        private d() {
        }

        public final /* synthetic */ cc a() {
            return new c();
        }
    }

    // compiled from: Location.java
    public enum e implements ad {
        LAT((short) 1, "lat"),
        LNG((short) 2, "lng"),
        TS((short) 3, "ts");
        private static final Map<String, u.aly.bg.e> d;
        private final short e;
        private final String f;

        static {
            String str = "lat";
            a = new u.aly.bg.e("LAT", 0, (short) 1, "lat");
            str = "lng";
            b = new u.aly.bg.e("LNG", 1, (short) 2, "lng");
            str = "ts";
            c = new u.aly.bg.e("TS", 2, (short) 3, "ts");
            g = new u.aly.bg.e[]{a, b, c};
            d = new HashMap();
            Iterator it = EnumSet.allOf(u.aly.bg.e.class).iterator();
            while (it.hasNext()) {
                u.aly.bg.e eVar = (u.aly.bg.e) it.next();
                d.put(eVar.b(), eVar);
            }
        }

        public static u.aly.bg.e a(int i) {
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

        public static u.aly.bg.e b(int i) {
            u.aly.bg.e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException(new StringBuilder("Field ").append(i).append(" doesn't exist!").toString());
        }

        public static u.aly.bg.e a(String str) {
            return (u.aly.bg.e) d.get(str);
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
        f = new ca(HttpRequest.r);
        g = new br("lat", (byte) 4, (short) 1);
        h = new br("lng", (byte) 4, (short) 2);
        i = new br("ts", (byte) 10, (short) 3);
        Map hashMap = new HashMap();
        j = hashMap;
        hashMap.put(ce.class, new b());
        j.put(cf.class, new d());
        hashMap = new EnumMap(e.class);
        hashMap.put(e.a, new ah("lat", (byte) 1, new ai((byte) 4)));
        hashMap.put(e.b, new ah("lng", (byte) 1, new ai((byte) 4)));
        hashMap.put(e.c, new ah("ts", (byte) 1, new ai((byte) 10)));
        e = Collections.unmodifiableMap(hashMap);
        ah.a(bg.class, e);
    }

    public bg() {
        this.d = (byte) 0;
    }

    public bg(double d, double d2, long j) {
        this();
        this.a = d;
        a();
        this.b = d2;
        b();
        this.c = j;
        c();
    }

    public final void a() {
        this.d = (byte) (this.d | 1);
    }

    public final void b() {
        this.d = (byte) (this.d | 2);
    }

    public final void c() {
        this.d = (byte) (this.d | 4);
    }

    public final void a(bu buVar) throws ac {
        ((cd) j.get(buVar.s())).a().b(buVar, this);
    }

    public final void b(bu buVar) throws ac {
        ((cd) j.get(buVar.s())).a().a(buVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Location(");
        stringBuilder.append("lat:");
        stringBuilder.append(this.a);
        stringBuilder.append(", ");
        stringBuilder.append("lng:");
        stringBuilder.append(this.b);
        stringBuilder.append(", ");
        stringBuilder.append("ts:");
        stringBuilder.append(this.c);
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }

    public static void d() throws ac {
    }
}
