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
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: AppInfo.java
public class ar implements Serializable, Cloneable, y<ar, e> {
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
    private static final br x;
    private static final Map<Class<? extends cc>, cd> y;
    private e[] A;
    public String a;
    public String b;
    public int c;
    public String d;
    public bm e;
    public String f;
    public String g;
    public String h;
    public String i;
    public int j;
    public String k;
    private byte z;

    // compiled from: AppInfo.java
    private static class a extends ce<ar> {
        private a() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            ar arVar = (ar) yVar;
            arVar.j();
            m;
            buVar.a();
            if (arVar.a != null) {
                buVar.a(n);
                buVar.a(arVar.a);
            }
            if (arVar.b != null && arVar.a()) {
                buVar.a(o);
                buVar.a(arVar.b);
            }
            if (arVar.b()) {
                buVar.a(p);
                buVar.a(arVar.c);
            }
            if (arVar.d != null && arVar.d()) {
                buVar.a(q);
                buVar.a(arVar.d);
            }
            if (arVar.e != null) {
                buVar.a(r);
                buVar.a(arVar.e.a());
            }
            if (arVar.f != null) {
                buVar.a(s);
                buVar.a(arVar.f);
            }
            if (arVar.g != null) {
                buVar.a(t);
                buVar.a(arVar.g);
            }
            if (arVar.h != null && arVar.e()) {
                buVar.a(u);
                buVar.a(arVar.h);
            }
            if (arVar.i != null && arVar.f()) {
                buVar.a(v);
                buVar.a(arVar.i);
            }
            if (arVar.g()) {
                buVar.a(w);
                buVar.a(arVar.j);
            }
            if (arVar.k != null && arVar.i()) {
                buVar.a(x);
                buVar.a(arVar.k);
            }
            buVar.c();
            buVar.b();
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            ar arVar = (ar) yVar;
            buVar.d();
            while (true) {
                br f = buVar.f();
                if (f.b != null) {
                    switch (f.c) {
                        case SimpleLog.LOG_LEVEL_TRACE:
                            if (f.b == (byte) 11) {
                                arVar.a = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_DEBUG:
                            if (f.b == (byte) 11) {
                                arVar.b = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case MqttConnectOptions.MQTT_VERSION_3_1:
                            if (f.b == (byte) 8) {
                                arVar.c = buVar.m();
                                arVar.c();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case MqttConnectOptions.MQTT_VERSION_3_1_1:
                            if (f.b == (byte) 11) {
                                arVar.d = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_ERROR:
                            if (f.b == (byte) 8) {
                                arVar.e = bm.a(buVar.m());
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_FATAL:
                            if (f.b == (byte) 11) {
                                arVar.f = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_OFF:
                            if (f.b == (byte) 11) {
                                arVar.g = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SpdyProtocol.PUBKEY_SEQ_ADASH:
                            if (f.b == (byte) 11) {
                                arVar.h = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SpdyProtocol.PUBKEY_PSEQ_ADASH:
                            if (f.b == (byte) 11) {
                                arVar.i = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SpdyProtocol.PUBKEY_SEQ_OPEN:
                            if (f.b == (byte) 8) {
                                arVar.j = buVar.m();
                                arVar.h();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SpdyProtocol.PUBKEY_PSEQ_OPEN:
                            if (f.b == (byte) 11) {
                                arVar.k = buVar.p();
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
                    arVar.j();
                    return;
                }
            }
        }
    }

    // compiled from: AppInfo.java
    private static class b implements cd {
        private b() {
        }

        public final /* synthetic */ cc a() {
            return new a();
        }
    }

    // compiled from: AppInfo.java
    private static class c extends cf<ar> {
        private c() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            ar arVar = (ar) yVar;
            cb cbVar = (cb) buVar;
            cbVar.a(arVar.a);
            cbVar.a(arVar.e.a());
            cbVar.a(arVar.f);
            cbVar.a(arVar.g);
            BitSet bitSet = new BitSet();
            if (arVar.a()) {
                bitSet.set(0);
            }
            if (arVar.b()) {
                bitSet.set(1);
            }
            if (arVar.d()) {
                bitSet.set(SimpleLog.LOG_LEVEL_DEBUG);
            }
            if (arVar.e()) {
                bitSet.set(MqttConnectOptions.MQTT_VERSION_3_1);
            }
            if (arVar.f()) {
                bitSet.set(MqttConnectOptions.MQTT_VERSION_3_1_1);
            }
            if (arVar.g()) {
                bitSet.set(SimpleLog.LOG_LEVEL_ERROR);
            }
            if (arVar.i()) {
                bitSet.set(SimpleLog.LOG_LEVEL_FATAL);
            }
            cbVar.a(bitSet, SimpleLog.LOG_LEVEL_OFF);
            if (arVar.a()) {
                cbVar.a(arVar.b);
            }
            if (arVar.b()) {
                cbVar.a(arVar.c);
            }
            if (arVar.d()) {
                cbVar.a(arVar.d);
            }
            if (arVar.e()) {
                cbVar.a(arVar.h);
            }
            if (arVar.f()) {
                cbVar.a(arVar.i);
            }
            if (arVar.g()) {
                cbVar.a(arVar.j);
            }
            if (arVar.i()) {
                cbVar.a(arVar.k);
            }
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            ar arVar = (ar) yVar;
            cb cbVar = (cb) buVar;
            arVar.a = cbVar.p();
            arVar.e = bm.a(cbVar.m());
            arVar.f = cbVar.p();
            arVar.g = cbVar.p();
            BitSet b = cbVar.b(SimpleLog.LOG_LEVEL_OFF);
            if (b.get(0)) {
                arVar.b = cbVar.p();
            }
            if (b.get(1)) {
                arVar.c = cbVar.m();
                arVar.c();
            }
            if (b.get(SimpleLog.LOG_LEVEL_DEBUG)) {
                arVar.d = cbVar.p();
            }
            if (b.get(MqttConnectOptions.MQTT_VERSION_3_1)) {
                arVar.h = cbVar.p();
            }
            if (b.get(MqttConnectOptions.MQTT_VERSION_3_1_1)) {
                arVar.i = cbVar.p();
            }
            if (b.get(SimpleLog.LOG_LEVEL_ERROR)) {
                arVar.j = cbVar.m();
                arVar.h();
            }
            if (b.get(SimpleLog.LOG_LEVEL_FATAL)) {
                arVar.k = cbVar.p();
            }
        }
    }

    // compiled from: AppInfo.java
    private static class d implements cd {
        private d() {
        }

        public final /* synthetic */ cc a() {
            return new c();
        }
    }

    // compiled from: AppInfo.java
    public enum e implements ad {
        KEY((short) 1, "key"),
        VERSION((short) 2, "version"),
        VERSION_INDEX((short) 3, "version_index"),
        PACKAGE_NAME((short) 4, "package_name"),
        SDK_TYPE((short) 5, "sdk_type"),
        SDK_VERSION((short) 6, com.umeng.common.a.h),
        CHANNEL((short) 7, com.umeng.common.a.d),
        WRAPPER_TYPE((short) 8, "wrapper_type"),
        WRAPPER_VERSION((short) 9, "wrapper_version"),
        VERTICAL_TYPE((short) 10, "vertical_type"),
        APP_SIGNATURE((short) 11, "app_signature");
        private static final Map<String, u.aly.ar.e> l;
        private final short m;
        private final String n;

        static {
            String str = "key";
            a = new u.aly.ar.e("KEY", 0, (short) 1, "key");
            String str2 = "version";
            b = new u.aly.ar.e("VERSION", 1, (short) 2, "version");
            str2 = "version_index";
            c = new u.aly.ar.e("VERSION_INDEX", 2, (short) 3, "version_index");
            str2 = "package_name";
            d = new u.aly.ar.e("PACKAGE_NAME", 3, (short) 4, "package_name");
            str2 = "sdk_type";
            e = new u.aly.ar.e("SDK_TYPE", 4, (short) 5, "sdk_type");
            str = com.umeng.common.a.h;
            f = new u.aly.ar.e("SDK_VERSION", 5, (short) 6, com.umeng.common.a.h);
            String str3 = com.umeng.common.a.d;
            g = new u.aly.ar.e("CHANNEL", 6, (short) 7, com.umeng.common.a.d);
            str3 = "wrapper_type";
            h = new u.aly.ar.e("WRAPPER_TYPE", 7, (short) 8, "wrapper_type");
            str3 = "wrapper_version";
            i = new u.aly.ar.e("WRAPPER_VERSION", 8, (short) 9, "wrapper_version");
            str3 = "vertical_type";
            j = new u.aly.ar.e("VERTICAL_TYPE", 9, (short) 10, "vertical_type");
            str3 = "app_signature";
            k = new u.aly.ar.e("APP_SIGNATURE", 10, (short) 11, "app_signature");
            o = new u.aly.ar.e[]{a, b, c, d, e, f, g, h, i, j, k};
            l = new HashMap();
            Iterator it = EnumSet.allOf(u.aly.ar.e.class).iterator();
            while (it.hasNext()) {
                u.aly.ar.e eVar = (u.aly.ar.e) it.next();
                l.put(eVar.b(), eVar);
            }
        }

        public static u.aly.ar.e a(int i) {
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
                case SpdyProtocol.PUBKEY_PSEQ_OPEN:
                    return k;
                default:
                    return null;
            }
        }

        public static u.aly.ar.e b(int i) {
            u.aly.ar.e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException(new StringBuilder("Field ").append(i).append(" doesn't exist!").toString());
        }

        public static u.aly.ar.e a(String str) {
            return (u.aly.ar.e) l.get(str);
        }

        private e(short s, String str) {
            this.m = s;
            this.n = str;
        }

        public final short a() {
            return this.m;
        }

        public final String b() {
            return this.n;
        }
    }

    static {
        m = new ca("AppInfo");
        n = new br("key", (byte) 11, (short) 1);
        o = new br("version", (byte) 11, (short) 2);
        p = new br("version_index", (byte) 8, (short) 3);
        q = new br("package_name", (byte) 11, (short) 4);
        r = new br("sdk_type", (byte) 8, (short) 5);
        s = new br(com.umeng.common.a.h, (byte) 11, (short) 6);
        t = new br(com.umeng.common.a.d, (byte) 11, (short) 7);
        u = new br("wrapper_type", (byte) 11, (short) 8);
        v = new br("wrapper_version", (byte) 11, (short) 9);
        w = new br("vertical_type", (byte) 8, (short) 10);
        x = new br("app_signature", (byte) 11, (short) 11);
        Map hashMap = new HashMap();
        y = hashMap;
        hashMap.put(ce.class, new b());
        y.put(cf.class, new d());
        hashMap = new EnumMap(e.class);
        hashMap.put(e.a, new ah("key", (byte) 1, new ai((byte) 11)));
        hashMap.put(e.b, new ah("version", (byte) 2, new ai((byte) 11)));
        hashMap.put(e.c, new ah("version_index", (byte) 2, new ai((byte) 8)));
        hashMap.put(e.d, new ah("package_name", (byte) 2, new ai((byte) 11)));
        hashMap.put(e.e, new ah("sdk_type", (byte) 1, new ag(bm.class)));
        hashMap.put(e.f, new ah(com.umeng.common.a.h, (byte) 1, new ai((byte) 11)));
        hashMap.put(e.g, new ah(com.umeng.common.a.d, (byte) 1, new ai((byte) 11)));
        hashMap.put(e.h, new ah("wrapper_type", (byte) 2, new ai((byte) 11)));
        hashMap.put(e.i, new ah("wrapper_version", (byte) 2, new ai((byte) 11)));
        hashMap.put(e.j, new ah("vertical_type", (byte) 2, new ai((byte) 8)));
        hashMap.put(e.k, new ah("app_signature", (byte) 2, new ai((byte) 11)));
        l = Collections.unmodifiableMap(hashMap);
        ah.a(ar.class, l);
    }

    public ar() {
        this.z = (byte) 0;
        this.A = new e[]{e.b, e.c, e.d, e.h, e.i, e.j, e.k};
    }

    public final boolean a() {
        return this.b != null;
    }

    public final boolean b() {
        return w.a(this.z, 0);
    }

    public final void c() {
        this.z = (byte) (this.z | 1);
    }

    public final boolean d() {
        return this.d != null;
    }

    public final boolean e() {
        return this.h != null;
    }

    public final boolean f() {
        return this.i != null;
    }

    public final boolean g() {
        return w.a(this.z, 1);
    }

    public final void h() {
        this.z = (byte) (this.z | 2);
    }

    public final boolean i() {
        return this.k != null;
    }

    public final void a(bu buVar) throws ac {
        ((cd) y.get(buVar.s())).a().b(buVar, this);
    }

    public final void b(bu buVar) throws ac {
        ((cd) y.get(buVar.s())).a().a(buVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("AppInfo(");
        stringBuilder.append("key:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        if (a()) {
            stringBuilder.append(", ");
            stringBuilder.append("version:");
            if (this.b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.b);
            }
        }
        if (b()) {
            stringBuilder.append(", ");
            stringBuilder.append("version_index:");
            stringBuilder.append(this.c);
        }
        if (d()) {
            stringBuilder.append(", ");
            stringBuilder.append("package_name:");
            if (this.d == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.d);
            }
        }
        stringBuilder.append(", ");
        stringBuilder.append("sdk_type:");
        if (this.e == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.e);
        }
        stringBuilder.append(", ");
        stringBuilder.append("sdk_version:");
        if (this.f == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f);
        }
        stringBuilder.append(", ");
        stringBuilder.append("channel:");
        if (this.g == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.g);
        }
        if (e()) {
            stringBuilder.append(", ");
            stringBuilder.append("wrapper_type:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
        }
        if (f()) {
            stringBuilder.append(", ");
            stringBuilder.append("wrapper_version:");
            if (this.i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.i);
            }
        }
        if (g()) {
            stringBuilder.append(", ");
            stringBuilder.append("vertical_type:");
            stringBuilder.append(this.j);
        }
        if (i()) {
            stringBuilder.append(", ");
            stringBuilder.append("app_signature:");
            if (this.k == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.k);
            }
        }
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }

    public final void j() throws ac {
        if (this.a == null) {
            throw new bv(new StringBuilder("Required field 'key' was not present! Struct: ").append(toString()).toString());
        } else if (this.e == null) {
            throw new bv(new StringBuilder("Required field 'sdk_type' was not present! Struct: ").append(toString()).toString());
        } else if (this.f == null) {
            throw new bv(new StringBuilder("Required field 'sdk_version' was not present! Struct: ").append(toString()).toString());
        } else if (this.g == null) {
            throw new bv(new StringBuilder("Required field 'channel' was not present! Struct: ").append(toString()).toString());
        }
    }
}
