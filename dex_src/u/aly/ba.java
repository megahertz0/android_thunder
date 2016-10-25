package u.aly;

import com.umeng.message.proguard.j;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: IdSnapshot.java
public class ba implements Serializable, Cloneable, y<ba, e> {
    public static final Map<e, ah> e;
    private static final ca f;
    private static final br g;
    private static final br h;
    private static final br i;
    private static final Map<Class<? extends cc>, cd> j;
    public String a;
    public long b;
    public int c;
    byte d;

    // compiled from: IdSnapshot.java
    private static class a extends ce<ba> {
        private a() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            ba baVar = (ba) yVar;
            baVar.c();
            f;
            buVar.a();
            if (baVar.a != null) {
                buVar.a(g);
                buVar.a(baVar.a);
            }
            buVar.a(h);
            buVar.a(baVar.b);
            buVar.a(i);
            buVar.a(baVar.c);
            buVar.c();
            buVar.b();
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            ba baVar = (ba) yVar;
            buVar.d();
            while (true) {
                br f = buVar.f();
                if (f.b != null) {
                    switch (f.c) {
                        case SimpleLog.LOG_LEVEL_TRACE:
                            if (f.b == 11) {
                                baVar.a = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_DEBUG:
                            if (f.b == 10) {
                                baVar.b = buVar.n();
                                baVar.a();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case MqttConnectOptions.MQTT_VERSION_3_1:
                            if (f.b == 8) {
                                baVar.c = buVar.m();
                                baVar.b();
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
                    if (!w.a(baVar.d, 0)) {
                        throw new bv(new StringBuilder("Required field 'ts' was not found in serialized data! Struct: ").append(toString()).toString());
                    } else if (w.a(baVar.d, 1)) {
                        baVar.c();
                        return;
                    } else {
                        throw new bv(new StringBuilder("Required field 'version' was not found in serialized data! Struct: ").append(toString()).toString());
                    }
                }
            }
        }
    }

    // compiled from: IdSnapshot.java
    private static class b implements cd {
        private b() {
        }

        public final /* synthetic */ cc a() {
            return new a();
        }
    }

    // compiled from: IdSnapshot.java
    private static class c extends cf<ba> {
        private c() {
        }

        public final /* bridge */ /* synthetic */ void a(bu buVar, y yVar) throws ac {
            ba baVar = (ba) yVar;
            cb cbVar = (cb) buVar;
            cbVar.a(baVar.a);
            cbVar.a(baVar.b);
            cbVar.a(baVar.c);
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            ba baVar = (ba) yVar;
            cb cbVar = (cb) buVar;
            baVar.a = cbVar.p();
            baVar.b = cbVar.n();
            baVar.a();
            baVar.c = cbVar.m();
            baVar.b();
        }
    }

    // compiled from: IdSnapshot.java
    private static class d implements cd {
        private d() {
        }

        public final /* synthetic */ cc a() {
            return new c();
        }
    }

    // compiled from: IdSnapshot.java
    public enum e implements ad {
        IDENTITY((short) 1, "identity"),
        TS((short) 2, "ts"),
        VERSION((short) 3, "version");
        private static final Map<String, u.aly.ba.e> d;
        private final short e;
        private final String f;

        static {
            String str = "identity";
            a = new u.aly.ba.e("IDENTITY", 0, (short) 1, "identity");
            str = "ts";
            b = new u.aly.ba.e("TS", 1, (short) 2, "ts");
            str = "version";
            c = new u.aly.ba.e("VERSION", 2, (short) 3, "version");
            g = new u.aly.ba.e[]{a, b, c};
            d = new HashMap();
            Iterator it = EnumSet.allOf(u.aly.ba.e.class).iterator();
            while (it.hasNext()) {
                u.aly.ba.e eVar = (u.aly.ba.e) it.next();
                d.put(eVar.b(), eVar);
            }
        }

        public static u.aly.ba.e a(int i) {
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

        public static u.aly.ba.e b(int i) {
            u.aly.ba.e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException(new StringBuilder("Field ").append(i).append(" doesn't exist!").toString());
        }

        public static u.aly.ba.e a(String str) {
            return (u.aly.ba.e) d.get(str);
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
        f = new ca("IdSnapshot");
        g = new br("identity", (byte) 11, (short) 1);
        h = new br("ts", (byte) 10, (short) 2);
        i = new br("version", (byte) 8, (short) 3);
        Map hashMap = new HashMap();
        j = hashMap;
        hashMap.put(ce.class, new b());
        j.put(cf.class, new d());
        hashMap = new EnumMap(e.class);
        hashMap.put(e.a, new ah("identity", (byte) 1, new ai((byte) 11)));
        hashMap.put(e.b, new ah("ts", (byte) 1, new ai((byte) 10)));
        hashMap.put(e.c, new ah("version", (byte) 1, new ai((byte) 8)));
        e = Collections.unmodifiableMap(hashMap);
        ah.a(ba.class, e);
    }

    public ba() {
        this.d = (byte) 0;
    }

    public final void a() {
        this.d = (byte) (this.d | 1);
    }

    public final void b() {
        this.d = (byte) (this.d | 2);
    }

    public final void a(bu buVar) throws ac {
        ((cd) j.get(buVar.s())).a().b(buVar, this);
    }

    public final void b(bu buVar) throws ac {
        ((cd) j.get(buVar.s())).a().a(buVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("IdSnapshot(");
        stringBuilder.append("identity:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("ts:");
        stringBuilder.append(this.b);
        stringBuilder.append(", ");
        stringBuilder.append("version:");
        stringBuilder.append(this.c);
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }

    public final void c() throws ac {
        if (this.a == null) {
            throw new bv(new StringBuilder("Required field 'identity' was not present! Struct: ").append(toString()).toString());
        }
    }
}
