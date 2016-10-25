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

// compiled from: ImprintValue.java
public class bd implements Serializable, Cloneable, y<bd, e> {
    public static final Map<e, ah> e;
    private static final ca f;
    private static final br g;
    private static final br h;
    private static final br i;
    private static final Map<Class<? extends cc>, cd> j;
    public String a;
    public long b;
    public String c;
    byte d;
    private e[] k;

    // compiled from: ImprintValue.java
    private static class a extends ce<bd> {
        private a() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            bd bdVar = (bd) yVar;
            bdVar.c();
            f;
            buVar.a();
            if (bdVar.a != null && bdVar.a()) {
                buVar.a(g);
                buVar.a(bdVar.a);
            }
            buVar.a(h);
            buVar.a(bdVar.b);
            if (bdVar.c != null) {
                buVar.a(i);
                buVar.a(bdVar.c);
            }
            buVar.c();
            buVar.b();
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            bd bdVar = (bd) yVar;
            buVar.d();
            while (true) {
                br f = buVar.f();
                if (f.b != null) {
                    switch (f.c) {
                        case SimpleLog.LOG_LEVEL_TRACE:
                            if (f.b == (byte) 11) {
                                bdVar.a = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_DEBUG:
                            if (f.b == 10) {
                                bdVar.b = buVar.n();
                                bdVar.b();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case MqttConnectOptions.MQTT_VERSION_3_1:
                            if (f.b == (byte) 11) {
                                bdVar.c = buVar.p();
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
                    if (w.a(bdVar.d, 0)) {
                        bdVar.c();
                        return;
                    }
                    throw new bv(new StringBuilder("Required field 'ts' was not found in serialized data! Struct: ").append(toString()).toString());
                }
            }
        }
    }

    // compiled from: ImprintValue.java
    private static class b implements cd {
        private b() {
        }

        public final /* synthetic */ cc a() {
            return new a();
        }
    }

    // compiled from: ImprintValue.java
    private static class c extends cf<bd> {
        private c() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            bd bdVar = (bd) yVar;
            cb cbVar = (cb) buVar;
            cbVar.a(bdVar.b);
            cbVar.a(bdVar.c);
            BitSet bitSet = new BitSet();
            if (bdVar.a()) {
                bitSet.set(0);
            }
            cbVar.a(bitSet, 1);
            if (bdVar.a()) {
                cbVar.a(bdVar.a);
            }
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            bd bdVar = (bd) yVar;
            cb cbVar = (cb) buVar;
            bdVar.b = cbVar.n();
            bdVar.b();
            bdVar.c = cbVar.p();
            if (cbVar.b(1).get(0)) {
                bdVar.a = cbVar.p();
            }
        }
    }

    // compiled from: ImprintValue.java
    private static class d implements cd {
        private d() {
        }

        public final /* synthetic */ cc a() {
            return new c();
        }
    }

    // compiled from: ImprintValue.java
    public enum e implements ad {
        VALUE((short) 1, "value"),
        TS((short) 2, "ts"),
        GUID((short) 3, com.xunlei.analytics.c.e.a);
        private static final Map<String, u.aly.bd.e> d;
        private final short e;
        private final String f;

        static {
            String str = "value";
            a = new u.aly.bd.e("VALUE", 0, (short) 1, "value");
            str = "ts";
            b = new u.aly.bd.e("TS", 1, (short) 2, "ts");
            str = com.xunlei.analytics.c.e.a;
            c = new u.aly.bd.e("GUID", 2, (short) 3, com.xunlei.analytics.c.e.a);
            g = new u.aly.bd.e[]{a, b, c};
            d = new HashMap();
            Iterator it = EnumSet.allOf(u.aly.bd.e.class).iterator();
            while (it.hasNext()) {
                u.aly.bd.e eVar = (u.aly.bd.e) it.next();
                d.put(eVar.b(), eVar);
            }
        }

        public static u.aly.bd.e a(int i) {
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

        public static u.aly.bd.e b(int i) {
            u.aly.bd.e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException(new StringBuilder("Field ").append(i).append(" doesn't exist!").toString());
        }

        public static u.aly.bd.e a(String str) {
            return (u.aly.bd.e) d.get(str);
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
        f = new ca("ImprintValue");
        g = new br("value", (byte) 11, (short) 1);
        h = new br("ts", (byte) 10, (short) 2);
        i = new br(com.xunlei.analytics.c.e.a, (byte) 11, (short) 3);
        Map hashMap = new HashMap();
        j = hashMap;
        hashMap.put(ce.class, new b());
        j.put(cf.class, new d());
        hashMap = new EnumMap(e.class);
        hashMap.put(e.a, new ah("value", (byte) 2, new ai((byte) 11)));
        hashMap.put(e.b, new ah("ts", (byte) 1, new ai((byte) 10)));
        hashMap.put(e.c, new ah(com.xunlei.analytics.c.e.a, (byte) 1, new ai((byte) 11)));
        e = Collections.unmodifiableMap(hashMap);
        ah.a(bd.class, e);
    }

    public bd() {
        this.d = (byte) 0;
        this.k = new e[]{e.a};
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
        StringBuilder stringBuilder = new StringBuilder("ImprintValue(");
        Object obj = 1;
        if (a()) {
            stringBuilder.append("value:");
            if (this.a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.a);
            }
            obj = null;
        }
        if (obj == null) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("ts:");
        stringBuilder.append(this.b);
        stringBuilder.append(", ");
        stringBuilder.append("guid:");
        if (this.c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }

    public final void c() throws ac {
        if (this.c == null) {
            throw new bv(new StringBuilder("Required field 'guid' was not present! Struct: ").append(toString()).toString());
        }
    }
}
