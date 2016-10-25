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
import org.android.agoo.common.AgooConstants;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: Error.java
public class av implements Serializable, Cloneable, y<av, e> {
    public static final Map<e, ah> e;
    private static final ca f;
    private static final br g;
    private static final br h;
    private static final br i;
    private static final Map<Class<? extends cc>, cd> j;
    public long a;
    public String b;
    public aw c;
    byte d;
    private e[] k;

    // compiled from: Error.java
    private static class a extends ce<av> {
        private a() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            av avVar = (av) yVar;
            avVar.d();
            f;
            buVar.a();
            buVar.a(g);
            buVar.a(avVar.a);
            if (avVar.b != null) {
                buVar.a(h);
                buVar.a(avVar.b);
            }
            if (avVar.c != null && avVar.c()) {
                buVar.a(i);
                buVar.a(avVar.c.a());
            }
            buVar.c();
            buVar.b();
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            av avVar = (av) yVar;
            buVar.d();
            while (true) {
                br f = buVar.f();
                if (f.b != null) {
                    switch (f.c) {
                        case SimpleLog.LOG_LEVEL_TRACE:
                            if (f.b == 10) {
                                avVar.a = buVar.n();
                                avVar.b();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_DEBUG:
                            if (f.b == 11) {
                                avVar.b = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case MqttConnectOptions.MQTT_VERSION_3_1:
                            if (f.b == 8) {
                                avVar.c = aw.a(buVar.m());
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
                    if (w.a(avVar.d, 0)) {
                        avVar.d();
                        return;
                    }
                    throw new bv(new StringBuilder("Required field 'ts' was not found in serialized data! Struct: ").append(toString()).toString());
                }
            }
        }
    }

    // compiled from: Error.java
    private static class b implements cd {
        private b() {
        }

        public final /* synthetic */ cc a() {
            return new a();
        }
    }

    // compiled from: Error.java
    private static class c extends cf<av> {
        private c() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            av avVar = (av) yVar;
            cb cbVar = (cb) buVar;
            cbVar.a(avVar.a);
            cbVar.a(avVar.b);
            BitSet bitSet = new BitSet();
            if (avVar.c()) {
                bitSet.set(0);
            }
            cbVar.a(bitSet, 1);
            if (avVar.c()) {
                cbVar.a(avVar.c.a());
            }
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            av avVar = (av) yVar;
            cb cbVar = (cb) buVar;
            avVar.a = cbVar.n();
            avVar.b();
            avVar.b = cbVar.p();
            if (cbVar.b(1).get(0)) {
                avVar.c = aw.a(cbVar.m());
            }
        }
    }

    // compiled from: Error.java
    private static class d implements cd {
        private d() {
        }

        public final /* synthetic */ cc a() {
            return new c();
        }
    }

    // compiled from: Error.java
    public enum e implements ad {
        TS((short) 1, "ts"),
        CONTEXT((short) 2, "context"),
        SOURCE((short) 3, AgooConstants.MESSAGE_FROM_PKG);
        private static final Map<String, u.aly.av.e> d;
        private final short e;
        private final String f;

        static {
            String str = "ts";
            a = new u.aly.av.e("TS", 0, (short) 1, "ts");
            str = "context";
            b = new u.aly.av.e("CONTEXT", 1, (short) 2, "context");
            str = AgooConstants.MESSAGE_FROM_PKG;
            c = new u.aly.av.e("SOURCE", 2, (short) 3, AgooConstants.MESSAGE_FROM_PKG);
            g = new u.aly.av.e[]{a, b, c};
            d = new HashMap();
            Iterator it = EnumSet.allOf(u.aly.av.e.class).iterator();
            while (it.hasNext()) {
                u.aly.av.e eVar = (u.aly.av.e) it.next();
                d.put(eVar.b(), eVar);
            }
        }

        public static u.aly.av.e a(int i) {
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

        public static u.aly.av.e b(int i) {
            u.aly.av.e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException(new StringBuilder("Field ").append(i).append(" doesn't exist!").toString());
        }

        public static u.aly.av.e a(String str) {
            return (u.aly.av.e) d.get(str);
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
        f = new ca("Error");
        g = new br("ts", (byte) 10, (short) 1);
        h = new br("context", (byte) 11, (short) 2);
        i = new br(AgooConstants.MESSAGE_FROM_PKG, (byte) 8, (short) 3);
        Map hashMap = new HashMap();
        j = hashMap;
        hashMap.put(ce.class, new b());
        j.put(cf.class, new d());
        hashMap = new EnumMap(e.class);
        hashMap.put(e.a, new ah("ts", (byte) 1, new ai((byte) 10)));
        hashMap.put(e.b, new ah("context", (byte) 1, new ai((byte) 11)));
        hashMap.put(e.c, new ah(AgooConstants.MESSAGE_FROM_PKG, (byte) 2, new ag(aw.class)));
        e = Collections.unmodifiableMap(hashMap);
        ah.a(av.class, e);
    }

    public av() {
        this.d = (byte) 0;
        this.k = new e[]{e.c};
    }

    public final void b() {
        this.d = (byte) (this.d | 1);
    }

    public final boolean c() {
        return this.c != null;
    }

    public final void a(bu buVar) throws ac {
        ((cd) j.get(buVar.s())).a().b(buVar, this);
    }

    public final void b(bu buVar) throws ac {
        ((cd) j.get(buVar.s())).a().a(buVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Error(");
        stringBuilder.append("ts:");
        stringBuilder.append(this.a);
        stringBuilder.append(", ");
        stringBuilder.append("context:");
        if (this.b == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.b);
        }
        if (c()) {
            stringBuilder.append(", ");
            stringBuilder.append("source:");
            if (this.c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.c);
            }
        }
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }

    public final void d() throws ac {
        if (this.b == null) {
            throw new bv(new StringBuilder("Required field 'context' was not present! Struct: ").append(toString()).toString());
        }
    }
}
