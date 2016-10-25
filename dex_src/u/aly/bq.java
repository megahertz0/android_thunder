package u.aly;

import com.umeng.message.proguard.j;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: UMEnvelope.java
public class bq implements Serializable, Cloneable, y<bq, e> {
    public static final Map<e, ah> l;
    private static final ca m;
    private static final br n;
    private static final br o;
    private static final br p;
    private static final br q;
    private static final br r;
    private static final br s;
    private static final br t;
    private static final br u;
    private static final br v;
    private static final br w;
    private static final Map<Class<? extends cc>, cd> x;
    public String a;
    public String b;
    public String c;
    public int d;
    public int e;
    public int f;
    public ByteBuffer g;
    public String h;
    public String i;
    public int j;
    byte k;
    private e[] y;

    // compiled from: UMEnvelope.java
    private static class a extends ce<bq> {
        private a() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            bq bqVar = (bq) yVar;
            bqVar.f();
            m;
            buVar.a();
            if (bqVar.a != null) {
                buVar.a(n);
                buVar.a(bqVar.a);
            }
            if (bqVar.b != null) {
                buVar.a(o);
                buVar.a(bqVar.b);
            }
            if (bqVar.c != null) {
                buVar.a(p);
                buVar.a(bqVar.c);
            }
            buVar.a(q);
            buVar.a(bqVar.d);
            buVar.a(r);
            buVar.a(bqVar.e);
            buVar.a(s);
            buVar.a(bqVar.f);
            if (bqVar.g != null) {
                buVar.a(t);
                buVar.a(bqVar.g);
            }
            if (bqVar.h != null) {
                buVar.a(u);
                buVar.a(bqVar.h);
            }
            if (bqVar.i != null) {
                buVar.a(v);
                buVar.a(bqVar.i);
            }
            if (bqVar.d()) {
                buVar.a(w);
                buVar.a(bqVar.j);
            }
            buVar.c();
            buVar.b();
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            bq bqVar = (bq) yVar;
            buVar.d();
            while (true) {
                br f = buVar.f();
                if (f.b != null) {
                    switch (f.c) {
                        case SimpleLog.LOG_LEVEL_TRACE:
                            if (f.b == (byte) 11) {
                                bqVar.a = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_DEBUG:
                            if (f.b == (byte) 11) {
                                bqVar.b = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case MqttConnectOptions.MQTT_VERSION_3_1:
                            if (f.b == (byte) 11) {
                                bqVar.c = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case MqttConnectOptions.MQTT_VERSION_3_1_1:
                            if (f.b == (byte) 8) {
                                bqVar.d = buVar.m();
                                bqVar.a();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_ERROR:
                            if (f.b == (byte) 8) {
                                bqVar.e = buVar.m();
                                bqVar.b();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_FATAL:
                            if (f.b == (byte) 8) {
                                bqVar.f = buVar.m();
                                bqVar.c();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_OFF:
                            if (f.b == (byte) 11) {
                                bqVar.g = buVar.q();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SpdyProtocol.PUBKEY_SEQ_ADASH:
                            if (f.b == (byte) 11) {
                                bqVar.h = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SpdyProtocol.PUBKEY_PSEQ_ADASH:
                            if (f.b == (byte) 11) {
                                bqVar.i = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SpdyProtocol.PUBKEY_SEQ_OPEN:
                            if (f.b == (byte) 8) {
                                bqVar.j = buVar.m();
                                bqVar.e();
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
                    if (!w.a(bqVar.k, 0)) {
                        throw new bv(new StringBuilder("Required field 'serial_num' was not found in serialized data! Struct: ").append(toString()).toString());
                    } else if (!w.a(bqVar.k, 1)) {
                        throw new bv(new StringBuilder("Required field 'ts_secs' was not found in serialized data! Struct: ").append(toString()).toString());
                    } else if (w.a(bqVar.k, SimpleLog.LOG_LEVEL_DEBUG)) {
                        bqVar.f();
                        return;
                    } else {
                        throw new bv(new StringBuilder("Required field 'length' was not found in serialized data! Struct: ").append(toString()).toString());
                    }
                }
            }
        }
    }

    // compiled from: UMEnvelope.java
    private static class b implements cd {
        private b() {
        }

        public final /* synthetic */ cc a() {
            return new a();
        }
    }

    // compiled from: UMEnvelope.java
    private static class c extends cf<bq> {
        private c() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            bq bqVar = (bq) yVar;
            cb cbVar = (cb) buVar;
            cbVar.a(bqVar.a);
            cbVar.a(bqVar.b);
            cbVar.a(bqVar.c);
            cbVar.a(bqVar.d);
            cbVar.a(bqVar.e);
            cbVar.a(bqVar.f);
            cbVar.a(bqVar.g);
            cbVar.a(bqVar.h);
            cbVar.a(bqVar.i);
            BitSet bitSet = new BitSet();
            if (bqVar.d()) {
                bitSet.set(0);
            }
            cbVar.a(bitSet, 1);
            if (bqVar.d()) {
                cbVar.a(bqVar.j);
            }
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            bq bqVar = (bq) yVar;
            cb cbVar = (cb) buVar;
            bqVar.a = cbVar.p();
            bqVar.b = cbVar.p();
            bqVar.c = cbVar.p();
            bqVar.d = cbVar.m();
            bqVar.a();
            bqVar.e = cbVar.m();
            bqVar.b();
            bqVar.f = cbVar.m();
            bqVar.c();
            bqVar.g = cbVar.q();
            bqVar.h = cbVar.p();
            bqVar.i = cbVar.p();
            if (cbVar.b(1).get(0)) {
                bqVar.j = cbVar.m();
                bqVar.e();
            }
        }
    }

    // compiled from: UMEnvelope.java
    private static class d implements cd {
        private d() {
        }

        public final /* synthetic */ cc a() {
            return new c();
        }
    }

    // compiled from: UMEnvelope.java
    public enum e implements ad {
        VERSION((short) 1, "version"),
        ADDRESS((short) 2, "address"),
        SIGNATURE((short) 3, "signature"),
        SERIAL_NUM((short) 4, "serial_num"),
        TS_SECS((short) 5, "ts_secs"),
        LENGTH((short) 6, "length"),
        ENTITY((short) 7, "entity"),
        GUID((short) 8, com.xunlei.analytics.c.e.a),
        CHECKSUM((short) 9, "checksum"),
        CODEX((short) 10, "codex");
        private static final Map<String, u.aly.bq.e> k;
        private final short l;
        private final String m;

        static {
            String str = "version";
            a = new u.aly.bq.e("VERSION", 0, (short) 1, "version");
            String str2 = "address";
            b = new u.aly.bq.e("ADDRESS", 1, (short) 2, "address");
            str2 = "signature";
            c = new u.aly.bq.e("SIGNATURE", 2, (short) 3, "signature");
            str2 = "serial_num";
            d = new u.aly.bq.e("SERIAL_NUM", 3, (short) 4, "serial_num");
            str2 = "ts_secs";
            e = new u.aly.bq.e("TS_SECS", 4, (short) 5, "ts_secs");
            str = "length";
            f = new u.aly.bq.e("LENGTH", 5, (short) 6, "length");
            String str3 = "entity";
            g = new u.aly.bq.e("ENTITY", 6, (short) 7, "entity");
            str3 = com.xunlei.analytics.c.e.a;
            h = new u.aly.bq.e("GUID", 7, (short) 8, com.xunlei.analytics.c.e.a);
            str3 = "checksum";
            i = new u.aly.bq.e("CHECKSUM", 8, (short) 9, "checksum");
            str3 = "codex";
            j = new u.aly.bq.e("CODEX", 9, (short) 10, "codex");
            n = new u.aly.bq.e[]{a, b, c, d, e, f, g, h, i, j};
            k = new HashMap();
            Iterator it = EnumSet.allOf(u.aly.bq.e.class).iterator();
            while (it.hasNext()) {
                u.aly.bq.e eVar = (u.aly.bq.e) it.next();
                k.put(eVar.b(), eVar);
            }
        }

        public static u.aly.bq.e a(int i) {
            switch (i) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    return a;
                case SimpleLog.LOG_LEVEL_DEBUG:
                    return b;
                case MqttConnectOptions.MQTT_VERSION_3_1:
                    return c;
                case MqttConnectOptions.MQTT_VERSION_3_1_1:
                    return d;
                case SimpleLog.LOG_LEVEL_ERROR:
                    return e;
                case SimpleLog.LOG_LEVEL_FATAL:
                    return f;
                case SimpleLog.LOG_LEVEL_OFF:
                    return g;
                case SpdyProtocol.PUBKEY_SEQ_ADASH:
                    return h;
                case SpdyProtocol.PUBKEY_PSEQ_ADASH:
                    return i;
                case SpdyProtocol.PUBKEY_SEQ_OPEN:
                    return j;
                default:
                    return null;
            }
        }

        public static u.aly.bq.e b(int i) {
            u.aly.bq.e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException(new StringBuilder("Field ").append(i).append(" doesn't exist!").toString());
        }

        public static u.aly.bq.e a(String str) {
            return (u.aly.bq.e) k.get(str);
        }

        private e(short s, String str) {
            this.l = s;
            this.m = str;
        }

        public final short a() {
            return this.l;
        }

        public final String b() {
            return this.m;
        }
    }

    static {
        m = new ca("UMEnvelope");
        n = new br("version", (byte) 11, (short) 1);
        o = new br("address", (byte) 11, (short) 2);
        p = new br("signature", (byte) 11, (short) 3);
        q = new br("serial_num", (byte) 8, (short) 4);
        r = new br("ts_secs", (byte) 8, (short) 5);
        s = new br("length", (byte) 8, (short) 6);
        t = new br("entity", (byte) 11, (short) 7);
        u = new br(com.xunlei.analytics.c.e.a, (byte) 11, (short) 8);
        v = new br("checksum", (byte) 11, (short) 9);
        w = new br("codex", (byte) 8, (short) 10);
        Map hashMap = new HashMap();
        x = hashMap;
        hashMap.put(ce.class, new b());
        x.put(cf.class, new d());
        hashMap = new EnumMap(e.class);
        hashMap.put(e.a, new ah("version", (byte) 1, new ai((byte) 11)));
        hashMap.put(e.b, new ah("address", (byte) 1, new ai((byte) 11)));
        hashMap.put(e.c, new ah("signature", (byte) 1, new ai((byte) 11)));
        hashMap.put(e.d, new ah("serial_num", (byte) 1, new ai((byte) 8)));
        hashMap.put(e.e, new ah("ts_secs", (byte) 1, new ai((byte) 8)));
        hashMap.put(e.f, new ah("length", (byte) 1, new ai((byte) 8)));
        hashMap.put(e.g, new ah("entity", (byte) 1, new ai((byte) 11, true)));
        hashMap.put(e.h, new ah(com.xunlei.analytics.c.e.a, (byte) 1, new ai((byte) 11)));
        hashMap.put(e.i, new ah("checksum", (byte) 1, new ai((byte) 11)));
        hashMap.put(e.j, new ah("codex", (byte) 2, new ai((byte) 8)));
        l = Collections.unmodifiableMap(hashMap);
        ah.a(bq.class, l);
    }

    public bq() {
        this.k = (byte) 0;
        this.y = new e[]{e.j};
    }

    public final void a() {
        this.k = (byte) (this.k | 1);
    }

    public final void b() {
        this.k = (byte) (this.k | 2);
    }

    public final void c() {
        this.k = (byte) (this.k | 4);
    }

    public final boolean d() {
        return w.a(this.k, MqttConnectOptions.MQTT_VERSION_3_1);
    }

    public final void e() {
        this.k = (byte) (this.k | 8);
    }

    public final void a(bu buVar) throws ac {
        ((cd) x.get(buVar.s())).a().b(buVar, this);
    }

    public final void b(bu buVar) throws ac {
        ((cd) x.get(buVar.s())).a().a(buVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("UMEnvelope(");
        stringBuilder.append("version:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("address:");
        if (this.b == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.b);
        }
        stringBuilder.append(", ");
        stringBuilder.append("signature:");
        if (this.c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("serial_num:");
        stringBuilder.append(this.d);
        stringBuilder.append(", ");
        stringBuilder.append("ts_secs:");
        stringBuilder.append(this.e);
        stringBuilder.append(", ");
        stringBuilder.append("length:");
        stringBuilder.append(this.f);
        stringBuilder.append(", ");
        stringBuilder.append("entity:");
        if (this.g == null) {
            stringBuilder.append("null");
        } else {
            aa.a(this.g, stringBuilder);
        }
        stringBuilder.append(", ");
        stringBuilder.append("guid:");
        if (this.h == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.h);
        }
        stringBuilder.append(", ");
        stringBuilder.append("checksum:");
        if (this.i == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.i);
        }
        if (d()) {
            stringBuilder.append(", ");
            stringBuilder.append("codex:");
            stringBuilder.append(this.j);
        }
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }

    public final void f() throws ac {
        if (this.a == null) {
            throw new bv(new StringBuilder("Required field 'version' was not present! Struct: ").append(toString()).toString());
        } else if (this.b == null) {
            throw new bv(new StringBuilder("Required field 'address' was not present! Struct: ").append(toString()).toString());
        } else if (this.c == null) {
            throw new bv(new StringBuilder("Required field 'signature' was not present! Struct: ").append(toString()).toString());
        } else if (this.g == null) {
            throw new bv(new StringBuilder("Required field 'entity' was not present! Struct: ").append(toString()).toString());
        } else if (this.h == null) {
            throw new bv(new StringBuilder("Required field 'guid' was not present! Struct: ").append(toString()).toString());
        } else if (this.i == null) {
            throw new bv(new StringBuilder("Required field 'checksum' was not present! Struct: ").append(toString()).toString());
        }
    }
}
