package u.aly;

import com.umeng.message.proguard.j;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
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

// compiled from: Response.java
public class bl implements Serializable, Cloneable, y<bl, e> {
    public static final Map<e, ah> e;
    private static final ca f;
    private static final br g;
    private static final br h;
    private static final br i;
    private static final Map<Class<? extends cc>, cd> j;
    public int a;
    public String b;
    public bc c;
    byte d;
    private e[] k;

    // compiled from: Response.java
    private static class a extends ce<bl> {
        private a() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            bl blVar = (bl) yVar;
            blVar.d();
            f;
            buVar.a();
            buVar.a(g);
            buVar.a(blVar.a);
            if (blVar.b != null && blVar.b()) {
                buVar.a(h);
                buVar.a(blVar.b);
            }
            if (blVar.c != null && blVar.c()) {
                buVar.a(i);
                blVar.c.b(buVar);
            }
            buVar.c();
            buVar.b();
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            bl blVar = (bl) yVar;
            buVar.d();
            while (true) {
                br f = buVar.f();
                if (f.b != null) {
                    switch (f.c) {
                        case SimpleLog.LOG_LEVEL_TRACE:
                            if (f.b == 8) {
                                blVar.a = buVar.m();
                                blVar.a();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_DEBUG:
                            if (f.b == 11) {
                                blVar.b = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case MqttConnectOptions.MQTT_VERSION_3_1:
                            if (f.b == 12) {
                                blVar.c = new bc();
                                blVar.c.a(buVar);
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
                    if (w.a(blVar.d, 0)) {
                        blVar.d();
                        return;
                    }
                    throw new bv(new StringBuilder("Required field 'resp_code' was not found in serialized data! Struct: ").append(toString()).toString());
                }
            }
        }
    }

    // compiled from: Response.java
    private static class b implements cd {
        private b() {
        }

        public final /* synthetic */ cc a() {
            return new a();
        }
    }

    // compiled from: Response.java
    private static class c extends cf<bl> {
        private c() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            bl blVar = (bl) yVar;
            cb cbVar = (cb) buVar;
            cbVar.a(blVar.a);
            BitSet bitSet = new BitSet();
            if (blVar.b()) {
                bitSet.set(0);
            }
            if (blVar.c()) {
                bitSet.set(1);
            }
            cbVar.a(bitSet, SimpleLog.LOG_LEVEL_DEBUG);
            if (blVar.b()) {
                cbVar.a(blVar.b);
            }
            if (blVar.c()) {
                blVar.c.b(cbVar);
            }
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            bl blVar = (bl) yVar;
            cb cbVar = (cb) buVar;
            blVar.a = cbVar.m();
            blVar.a();
            BitSet b = cbVar.b(SimpleLog.LOG_LEVEL_DEBUG);
            if (b.get(0)) {
                blVar.b = cbVar.p();
            }
            if (b.get(1)) {
                blVar.c = new bc();
                blVar.c.a(cbVar);
            }
        }
    }

    // compiled from: Response.java
    private static class d implements cd {
        private d() {
        }

        public final /* synthetic */ cc a() {
            return new c();
        }
    }

    // compiled from: Response.java
    public enum e implements ad {
        RESP_CODE((short) 1, "resp_code"),
        MSG((short) 2, SocializeProtocolConstants.PROTOCOL_KEY_MSG),
        IMPRINT((short) 3, "imprint");
        private static final Map<String, u.aly.bl.e> d;
        private final short e;
        private final String f;

        static {
            String str = "resp_code";
            a = new u.aly.bl.e("RESP_CODE", 0, (short) 1, "resp_code");
            str = SocializeProtocolConstants.PROTOCOL_KEY_MSG;
            b = new u.aly.bl.e("MSG", 1, (short) 2, SocializeProtocolConstants.PROTOCOL_KEY_MSG);
            str = "imprint";
            c = new u.aly.bl.e("IMPRINT", 2, (short) 3, "imprint");
            g = new u.aly.bl.e[]{a, b, c};
            d = new HashMap();
            Iterator it = EnumSet.allOf(u.aly.bl.e.class).iterator();
            while (it.hasNext()) {
                u.aly.bl.e eVar = (u.aly.bl.e) it.next();
                d.put(eVar.b(), eVar);
            }
        }

        public static u.aly.bl.e a(int i) {
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

        public static u.aly.bl.e b(int i) {
            u.aly.bl.e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException(new StringBuilder("Field ").append(i).append(" doesn't exist!").toString());
        }

        public static u.aly.bl.e a(String str) {
            return (u.aly.bl.e) d.get(str);
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
        f = new ca("Response");
        g = new br("resp_code", (byte) 8, (short) 1);
        h = new br(SocializeProtocolConstants.PROTOCOL_KEY_MSG, (byte) 11, (short) 2);
        i = new br("imprint", (byte) 12, (short) 3);
        Map hashMap = new HashMap();
        j = hashMap;
        hashMap.put(ce.class, new b());
        j.put(cf.class, new d());
        hashMap = new EnumMap(e.class);
        hashMap.put(e.a, new ah("resp_code", (byte) 1, new ai((byte) 8)));
        hashMap.put(e.b, new ah(SocializeProtocolConstants.PROTOCOL_KEY_MSG, (byte) 2, new ai((byte) 11)));
        hashMap.put(e.c, new ah("imprint", (byte) 2, new al(bc.class)));
        e = Collections.unmodifiableMap(hashMap);
        ah.a(bl.class, e);
    }

    public bl() {
        this.d = (byte) 0;
        this.k = new e[]{e.b, e.c};
    }

    public final void a() {
        this.d = (byte) (this.d | 1);
    }

    public final boolean b() {
        return this.b != null;
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
        StringBuilder stringBuilder = new StringBuilder("Response(");
        stringBuilder.append("resp_code:");
        stringBuilder.append(this.a);
        if (b()) {
            stringBuilder.append(", ");
            stringBuilder.append("msg:");
            if (this.b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.b);
            }
        }
        if (c()) {
            stringBuilder.append(", ");
            stringBuilder.append("imprint:");
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
        if (this.c != null) {
            this.c.c();
        }
    }
}
