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

// compiled from: IdJournal.java
public class az implements Serializable, Cloneable, y<az, e> {
    public static final Map<e, ah> f;
    private static final ca g;
    private static final br h;
    private static final br i;
    private static final br j;
    private static final br k;
    private static final Map<Class<? extends cc>, cd> l;
    public String a;
    public String b;
    public String c;
    public long d;
    byte e;
    private e[] m;

    // compiled from: IdJournal.java
    private static class a extends ce<az> {
        private a() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            az azVar = (az) yVar;
            azVar.c();
            g;
            buVar.a();
            if (azVar.a != null) {
                buVar.a(h);
                buVar.a(azVar.a);
            }
            if (azVar.b != null && azVar.a()) {
                buVar.a(i);
                buVar.a(azVar.b);
            }
            if (azVar.c != null) {
                buVar.a(j);
                buVar.a(azVar.c);
            }
            buVar.a(k);
            buVar.a(azVar.d);
            buVar.c();
            buVar.b();
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            az azVar = (az) yVar;
            buVar.d();
            while (true) {
                br f = buVar.f();
                if (f.b != null) {
                    switch (f.c) {
                        case SimpleLog.LOG_LEVEL_TRACE:
                            if (f.b == (byte) 11) {
                                azVar.a = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_DEBUG:
                            if (f.b == (byte) 11) {
                                azVar.b = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case MqttConnectOptions.MQTT_VERSION_3_1:
                            if (f.b == (byte) 11) {
                                azVar.c = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case MqttConnectOptions.MQTT_VERSION_3_1_1:
                            if (f.b == 10) {
                                azVar.d = buVar.n();
                                azVar.b();
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
                    if (w.a(azVar.e, 0)) {
                        azVar.c();
                        return;
                    }
                    throw new bv(new StringBuilder("Required field 'ts' was not found in serialized data! Struct: ").append(toString()).toString());
                }
            }
        }
    }

    // compiled from: IdJournal.java
    private static class b implements cd {
        private b() {
        }

        public final /* synthetic */ cc a() {
            return new a();
        }
    }

    // compiled from: IdJournal.java
    private static class c extends cf<az> {
        private c() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            az azVar = (az) yVar;
            cb cbVar = (cb) buVar;
            cbVar.a(azVar.a);
            cbVar.a(azVar.c);
            cbVar.a(azVar.d);
            BitSet bitSet = new BitSet();
            if (azVar.a()) {
                bitSet.set(0);
            }
            cbVar.a(bitSet, 1);
            if (azVar.a()) {
                cbVar.a(azVar.b);
            }
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            az azVar = (az) yVar;
            cb cbVar = (cb) buVar;
            azVar.a = cbVar.p();
            azVar.c = cbVar.p();
            azVar.d = cbVar.n();
            azVar.b();
            if (cbVar.b(1).get(0)) {
                azVar.b = cbVar.p();
            }
        }
    }

    // compiled from: IdJournal.java
    private static class d implements cd {
        private d() {
        }

        public final /* synthetic */ cc a() {
            return new c();
        }
    }

    // compiled from: IdJournal.java
    public enum e implements ad {
        DOMAIN((short) 1, "domain"),
        OLD_ID((short) 2, "old_id"),
        NEW_ID((short) 3, "new_id"),
        TS((short) 4, "ts");
        private static final Map<String, u.aly.az.e> e;
        private final short f;
        private final String g;

        static {
            String str = "domain";
            a = new u.aly.az.e("DOMAIN", 0, (short) 1, "domain");
            str = "old_id";
            b = new u.aly.az.e("OLD_ID", 1, (short) 2, "old_id");
            str = "new_id";
            c = new u.aly.az.e("NEW_ID", 2, (short) 3, "new_id");
            str = "ts";
            d = new u.aly.az.e("TS", 3, (short) 4, "ts");
            h = new u.aly.az.e[]{a, b, c, d};
            e = new HashMap();
            Iterator it = EnumSet.allOf(u.aly.az.e.class).iterator();
            while (it.hasNext()) {
                u.aly.az.e eVar = (u.aly.az.e) it.next();
                e.put(eVar.b(), eVar);
            }
        }

        public static u.aly.az.e a(int i) {
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

        public static u.aly.az.e b(int i) {
            u.aly.az.e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException(new StringBuilder("Field ").append(i).append(" doesn't exist!").toString());
        }

        public static u.aly.az.e a(String str) {
            return (u.aly.az.e) e.get(str);
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
        g = new ca("IdJournal");
        h = new br("domain", (byte) 11, (short) 1);
        i = new br("old_id", (byte) 11, (short) 2);
        j = new br("new_id", (byte) 11, (short) 3);
        k = new br("ts", (byte) 10, (short) 4);
        Map hashMap = new HashMap();
        l = hashMap;
        hashMap.put(ce.class, new b());
        l.put(cf.class, new d());
        hashMap = new EnumMap(e.class);
        hashMap.put(e.a, new ah("domain", (byte) 1, new ai((byte) 11)));
        hashMap.put(e.b, new ah("old_id", (byte) 2, new ai((byte) 11)));
        hashMap.put(e.c, new ah("new_id", (byte) 1, new ai((byte) 11)));
        hashMap.put(e.d, new ah("ts", (byte) 1, new ai((byte) 10)));
        f = Collections.unmodifiableMap(hashMap);
        ah.a(az.class, f);
    }

    public az() {
        this.e = (byte) 0;
        this.m = new e[]{e.b};
    }

    public final boolean a() {
        return this.b != null;
    }

    public final void b() {
        this.e = (byte) (this.e | 1);
    }

    public final void a(bu buVar) throws ac {
        ((cd) l.get(buVar.s())).a().b(buVar, this);
    }

    public final void b(bu buVar) throws ac {
        ((cd) l.get(buVar.s())).a().a(buVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("IdJournal(");
        stringBuilder.append("domain:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        if (a()) {
            stringBuilder.append(", ");
            stringBuilder.append("old_id:");
            if (this.b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.b);
            }
        }
        stringBuilder.append(", ");
        stringBuilder.append("new_id:");
        if (this.c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("ts:");
        stringBuilder.append(this.d);
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }

    public final void c() throws ac {
        if (this.a == null) {
            throw new bv(new StringBuilder("Required field 'domain' was not present! Struct: ").append(toString()).toString());
        } else if (this.c == null) {
            throw new bv(new StringBuilder("Required field 'new_id' was not present! Struct: ").append(toString()).toString());
        }
    }
}
