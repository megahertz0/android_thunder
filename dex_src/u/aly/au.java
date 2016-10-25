package u.aly;

import com.umeng.message.proguard.j;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.xllib.R;
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

// compiled from: DeviceInfo.java
public class au implements Serializable, Cloneable, y<au, e> {
    private static final br A;
    private static final br B;
    private static final br C;
    private static final br D;
    private static final br E;
    private static final br F;
    private static final br G;
    private static final br H;
    private static final br I;
    private static final br J;
    private static final br K;
    private static final br L;
    private static final Map<Class<? extends cc>, cd> M;
    public static final Map<e, ah> s;
    private static final ca t;
    private static final br u;
    private static final br v;
    private static final br w;
    private static final br x;
    private static final br y;
    private static final br z;
    private byte N;
    private e[] O;
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public bk i;
    public boolean j;
    public boolean k;
    public String l;
    public String m;
    public long n;
    public String o;
    public String p;
    public String q;
    public String r;

    // compiled from: DeviceInfo.java
    private static class a extends ce<au> {
        private a() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            au auVar = (au) yVar;
            auVar.v();
            t;
            buVar.a();
            if (auVar.a != null && auVar.a()) {
                buVar.a(u);
                buVar.a(auVar.a);
            }
            if (auVar.b != null && auVar.b()) {
                buVar.a(v);
                buVar.a(auVar.b);
            }
            if (auVar.c != null && auVar.c()) {
                buVar.a(w);
                buVar.a(auVar.c);
            }
            if (auVar.d != null && auVar.d()) {
                buVar.a(x);
                buVar.a(auVar.d);
            }
            if (auVar.e != null && auVar.e()) {
                buVar.a(y);
                buVar.a(auVar.e);
            }
            if (auVar.f != null && auVar.f()) {
                buVar.a(z);
                buVar.a(auVar.f);
            }
            if (auVar.g != null && auVar.g()) {
                buVar.a(A);
                buVar.a(auVar.g);
            }
            if (auVar.h != null && auVar.h()) {
                buVar.a(B);
                buVar.a(auVar.h);
            }
            if (auVar.i != null && auVar.i()) {
                buVar.a(C);
                auVar.i.b(buVar);
            }
            if (auVar.j()) {
                buVar.a(D);
                buVar.a(auVar.j);
            }
            if (auVar.l()) {
                buVar.a(E);
                buVar.a(auVar.k);
            }
            if (auVar.l != null && auVar.n()) {
                buVar.a(F);
                buVar.a(auVar.l);
            }
            if (auVar.m != null && auVar.o()) {
                buVar.a(G);
                buVar.a(auVar.m);
            }
            if (auVar.p()) {
                buVar.a(H);
                buVar.a(auVar.n);
            }
            if (auVar.o != null && auVar.r()) {
                buVar.a(I);
                buVar.a(auVar.o);
            }
            if (auVar.p != null && auVar.s()) {
                buVar.a(J);
                buVar.a(auVar.p);
            }
            if (auVar.q != null && auVar.t()) {
                buVar.a(K);
                buVar.a(auVar.q);
            }
            if (auVar.r != null && auVar.u()) {
                buVar.a(L);
                buVar.a(auVar.r);
            }
            buVar.c();
            buVar.b();
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            au auVar = (au) yVar;
            buVar.d();
            while (true) {
                br f = buVar.f();
                if (f.b != null) {
                    switch (f.c) {
                        case SimpleLog.LOG_LEVEL_TRACE:
                            if (f.b == (byte) 11) {
                                auVar.a = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_DEBUG:
                            if (f.b == (byte) 11) {
                                auVar.b = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case MqttConnectOptions.MQTT_VERSION_3_1:
                            if (f.b == (byte) 11) {
                                auVar.c = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case MqttConnectOptions.MQTT_VERSION_3_1_1:
                            if (f.b == (byte) 11) {
                                auVar.d = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_ERROR:
                            if (f.b == (byte) 11) {
                                auVar.e = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_FATAL:
                            if (f.b == (byte) 11) {
                                auVar.f = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_OFF:
                            if (f.b == (byte) 11) {
                                auVar.g = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SpdyProtocol.PUBKEY_SEQ_ADASH:
                            if (f.b == (byte) 11) {
                                auVar.h = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SpdyProtocol.PUBKEY_PSEQ_ADASH:
                            if (f.b == 12) {
                                auVar.i = new bk();
                                auVar.i.a(buVar);
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SpdyProtocol.PUBKEY_SEQ_OPEN:
                            if (f.b == (byte) 2) {
                                auVar.j = buVar.j();
                                auVar.k();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SpdyProtocol.PUBKEY_PSEQ_OPEN:
                            if (f.b == (byte) 2) {
                                auVar.k = buVar.j();
                                auVar.m();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case R.styleable.Toolbar_titleMargins:
                            if (f.b == (byte) 11) {
                                auVar.l = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case R.styleable.Toolbar_titleMarginStart:
                            if (f.b == (byte) 11) {
                                auVar.m = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case R.styleable.Toolbar_titleMarginEnd:
                            if (f.b == 10) {
                                auVar.n = buVar.n();
                                auVar.q();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case R.styleable.Toolbar_titleMarginTop:
                            if (f.b == (byte) 11) {
                                auVar.o = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SpdyProtocol.CUSTOM:
                            if (f.b == (byte) 11) {
                                auVar.p = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case R.styleable.Toolbar_maxButtonHeight:
                            if (f.b == (byte) 11) {
                                auVar.q = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case R.styleable.Toolbar_collapseIcon:
                            if (f.b == (byte) 11) {
                                auVar.r = buVar.p();
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
                    auVar.v();
                    return;
                }
            }
        }
    }

    // compiled from: DeviceInfo.java
    private static class b implements cd {
        private b() {
        }

        public final /* synthetic */ cc a() {
            return new a();
        }
    }

    // compiled from: DeviceInfo.java
    private static class c extends cf<au> {
        private c() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            au auVar = (au) yVar;
            cb cbVar = (cb) buVar;
            BitSet bitSet = new BitSet();
            if (auVar.a()) {
                bitSet.set(0);
            }
            if (auVar.b()) {
                bitSet.set(1);
            }
            if (auVar.c()) {
                bitSet.set(SimpleLog.LOG_LEVEL_DEBUG);
            }
            if (auVar.d()) {
                bitSet.set(MqttConnectOptions.MQTT_VERSION_3_1);
            }
            if (auVar.e()) {
                bitSet.set(MqttConnectOptions.MQTT_VERSION_3_1_1);
            }
            if (auVar.f()) {
                bitSet.set(SimpleLog.LOG_LEVEL_ERROR);
            }
            if (auVar.g()) {
                bitSet.set(SimpleLog.LOG_LEVEL_FATAL);
            }
            if (auVar.h()) {
                bitSet.set(SimpleLog.LOG_LEVEL_OFF);
            }
            if (auVar.i()) {
                bitSet.set(SpdyProtocol.PUBKEY_SEQ_ADASH);
            }
            if (auVar.j()) {
                bitSet.set(SpdyProtocol.PUBKEY_PSEQ_ADASH);
            }
            if (auVar.l()) {
                bitSet.set(SpdyProtocol.PUBKEY_SEQ_OPEN);
            }
            if (auVar.n()) {
                bitSet.set(SpdyProtocol.PUBKEY_PSEQ_OPEN);
            }
            if (auVar.o()) {
                bitSet.set(R.styleable.Toolbar_titleMargins);
            }
            if (auVar.p()) {
                bitSet.set(R.styleable.Toolbar_titleMarginStart);
            }
            if (auVar.r()) {
                bitSet.set(R.styleable.Toolbar_titleMarginEnd);
            }
            if (auVar.s()) {
                bitSet.set(R.styleable.Toolbar_titleMarginTop);
            }
            if (auVar.t()) {
                bitSet.set(SpdyProtocol.CUSTOM);
            }
            if (auVar.u()) {
                bitSet.set(R.styleable.Toolbar_maxButtonHeight);
            }
            cbVar.a(bitSet, R.styleable.Toolbar_collapseIcon);
            if (auVar.a()) {
                cbVar.a(auVar.a);
            }
            if (auVar.b()) {
                cbVar.a(auVar.b);
            }
            if (auVar.c()) {
                cbVar.a(auVar.c);
            }
            if (auVar.d()) {
                cbVar.a(auVar.d);
            }
            if (auVar.e()) {
                cbVar.a(auVar.e);
            }
            if (auVar.f()) {
                cbVar.a(auVar.f);
            }
            if (auVar.g()) {
                cbVar.a(auVar.g);
            }
            if (auVar.h()) {
                cbVar.a(auVar.h);
            }
            if (auVar.i()) {
                auVar.i.b(cbVar);
            }
            if (auVar.j()) {
                cbVar.a(auVar.j);
            }
            if (auVar.l()) {
                cbVar.a(auVar.k);
            }
            if (auVar.n()) {
                cbVar.a(auVar.l);
            }
            if (auVar.o()) {
                cbVar.a(auVar.m);
            }
            if (auVar.p()) {
                cbVar.a(auVar.n);
            }
            if (auVar.r()) {
                cbVar.a(auVar.o);
            }
            if (auVar.s()) {
                cbVar.a(auVar.p);
            }
            if (auVar.t()) {
                cbVar.a(auVar.q);
            }
            if (auVar.u()) {
                cbVar.a(auVar.r);
            }
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            au auVar = (au) yVar;
            cb cbVar = (cb) buVar;
            BitSet b = cbVar.b(R.styleable.Toolbar_collapseIcon);
            if (b.get(0)) {
                auVar.a = cbVar.p();
            }
            if (b.get(1)) {
                auVar.b = cbVar.p();
            }
            if (b.get(SimpleLog.LOG_LEVEL_DEBUG)) {
                auVar.c = cbVar.p();
            }
            if (b.get(MqttConnectOptions.MQTT_VERSION_3_1)) {
                auVar.d = cbVar.p();
            }
            if (b.get(MqttConnectOptions.MQTT_VERSION_3_1_1)) {
                auVar.e = cbVar.p();
            }
            if (b.get(SimpleLog.LOG_LEVEL_ERROR)) {
                auVar.f = cbVar.p();
            }
            if (b.get(SimpleLog.LOG_LEVEL_FATAL)) {
                auVar.g = cbVar.p();
            }
            if (b.get(SimpleLog.LOG_LEVEL_OFF)) {
                auVar.h = cbVar.p();
            }
            if (b.get(SpdyProtocol.PUBKEY_SEQ_ADASH)) {
                auVar.i = new bk();
                auVar.i.a(cbVar);
            }
            if (b.get(SpdyProtocol.PUBKEY_PSEQ_ADASH)) {
                auVar.j = cbVar.j();
                auVar.k();
            }
            if (b.get(SpdyProtocol.PUBKEY_SEQ_OPEN)) {
                auVar.k = cbVar.j();
                auVar.m();
            }
            if (b.get(SpdyProtocol.PUBKEY_PSEQ_OPEN)) {
                auVar.l = cbVar.p();
            }
            if (b.get(R.styleable.Toolbar_titleMargins)) {
                auVar.m = cbVar.p();
            }
            if (b.get(R.styleable.Toolbar_titleMarginStart)) {
                auVar.n = cbVar.n();
                auVar.q();
            }
            if (b.get(R.styleable.Toolbar_titleMarginEnd)) {
                auVar.o = cbVar.p();
            }
            if (b.get(R.styleable.Toolbar_titleMarginTop)) {
                auVar.p = cbVar.p();
            }
            if (b.get(SpdyProtocol.CUSTOM)) {
                auVar.q = cbVar.p();
            }
            if (b.get(R.styleable.Toolbar_maxButtonHeight)) {
                auVar.r = cbVar.p();
            }
        }
    }

    // compiled from: DeviceInfo.java
    private static class d implements cd {
        private d() {
        }

        public final /* synthetic */ cc a() {
            return new c();
        }
    }

    // compiled from: DeviceInfo.java
    public enum e implements ad {
        DEVICE_ID((short) 1, "device_id"),
        IDMD5((short) 2, com.umeng.common.a.e),
        MAC_ADDRESS((short) 3, "mac_address"),
        OPEN_UDID((short) 4, "open_udid"),
        MODEL((short) 5, "model"),
        CPU((short) 6, "cpu"),
        OS((short) 7, SocializeProtocolConstants.PROTOCOL_KEY_OS),
        OS_VERSION((short) 8, "os_version"),
        RESOLUTION((short) 9, "resolution"),
        IS_JAILBROKEN((short) 10, "is_jailbroken"),
        IS_PIRATED((short) 11, "is_pirated"),
        DEVICE_BOARD((short) 12, "device_board"),
        DEVICE_BRAND((short) 13, "device_brand"),
        DEVICE_MANUTIME((short) 14, "device_manutime"),
        DEVICE_MANUFACTURER((short) 15, "device_manufacturer"),
        DEVICE_MANUID((short) 16, "device_manuid"),
        DEVICE_NAME((short) 17, "device_name"),
        WP_DEVICE((short) 18, "wp_device");
        private static final Map<String, u.aly.au.e> s;
        private final short t;
        private final String u;

        static {
            String str = "device_id";
            a = new u.aly.au.e("DEVICE_ID", 0, (short) 1, "device_id");
            String str2 = com.umeng.common.a.e;
            b = new u.aly.au.e("IDMD5", 1, (short) 2, com.umeng.common.a.e);
            str2 = "mac_address";
            c = new u.aly.au.e("MAC_ADDRESS", 2, (short) 3, "mac_address");
            str2 = "open_udid";
            d = new u.aly.au.e("OPEN_UDID", 3, (short) 4, "open_udid");
            str2 = "model";
            e = new u.aly.au.e("MODEL", 4, (short) 5, "model");
            str = "cpu";
            f = new u.aly.au.e("CPU", 5, (short) 6, "cpu");
            String str3 = SocializeProtocolConstants.PROTOCOL_KEY_OS;
            g = new u.aly.au.e("OS", 6, (short) 7, SocializeProtocolConstants.PROTOCOL_KEY_OS);
            str3 = "os_version";
            h = new u.aly.au.e("OS_VERSION", 7, (short) 8, "os_version");
            str3 = "resolution";
            i = new u.aly.au.e("RESOLUTION", 8, (short) 9, "resolution");
            str3 = "is_jailbroken";
            j = new u.aly.au.e("IS_JAILBROKEN", 9, (short) 10, "is_jailbroken");
            str3 = "is_pirated";
            k = new u.aly.au.e("IS_PIRATED", 10, (short) 11, "is_pirated");
            str3 = "device_board";
            l = new u.aly.au.e("DEVICE_BOARD", 11, (short) 12, "device_board");
            str3 = "device_brand";
            m = new u.aly.au.e("DEVICE_BRAND", 12, (short) 13, "device_brand");
            str3 = "device_manutime";
            n = new u.aly.au.e("DEVICE_MANUTIME", 13, (short) 14, "device_manutime");
            str3 = "device_manufacturer";
            o = new u.aly.au.e("DEVICE_MANUFACTURER", 14, (short) 15, "device_manufacturer");
            str3 = "device_manuid";
            p = new u.aly.au.e("DEVICE_MANUID", 15, (short) 16, "device_manuid");
            str3 = "device_name";
            q = new u.aly.au.e("DEVICE_NAME", 16, (short) 17, "device_name");
            str3 = "wp_device";
            r = new u.aly.au.e("WP_DEVICE", 17, (short) 18, "wp_device");
            v = new u.aly.au.e[]{a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r};
            s = new HashMap();
            Iterator it = EnumSet.allOf(u.aly.au.e.class).iterator();
            while (it.hasNext()) {
                u.aly.au.e eVar = (u.aly.au.e) it.next();
                s.put(eVar.b(), eVar);
            }
        }

        public static u.aly.au.e a(int i) {
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
                case R.styleable.Toolbar_titleMargins:
                    return l;
                case R.styleable.Toolbar_titleMarginStart:
                    return m;
                case R.styleable.Toolbar_titleMarginEnd:
                    return n;
                case R.styleable.Toolbar_titleMarginTop:
                    return o;
                case SpdyProtocol.CUSTOM:
                    return p;
                case R.styleable.Toolbar_maxButtonHeight:
                    return q;
                case R.styleable.Toolbar_collapseIcon:
                    return r;
                default:
                    return null;
            }
        }

        public static u.aly.au.e b(int i) {
            u.aly.au.e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException(new StringBuilder("Field ").append(i).append(" doesn't exist!").toString());
        }

        public static u.aly.au.e a(String str) {
            return (u.aly.au.e) s.get(str);
        }

        private e(short s, String str) {
            this.t = s;
            this.u = str;
        }

        public final short a() {
            return this.t;
        }

        public final String b() {
            return this.u;
        }
    }

    static {
        t = new ca("DeviceInfo");
        u = new br("device_id", (byte) 11, (short) 1);
        v = new br(com.umeng.common.a.e, (byte) 11, (short) 2);
        w = new br("mac_address", (byte) 11, (short) 3);
        x = new br("open_udid", (byte) 11, (short) 4);
        y = new br("model", (byte) 11, (short) 5);
        z = new br("cpu", (byte) 11, (short) 6);
        A = new br(SocializeProtocolConstants.PROTOCOL_KEY_OS, (byte) 11, (short) 7);
        B = new br("os_version", (byte) 11, (short) 8);
        C = new br("resolution", (byte) 12, (short) 9);
        D = new br("is_jailbroken", (byte) 2, (short) 10);
        E = new br("is_pirated", (byte) 2, (short) 11);
        F = new br("device_board", (byte) 11, (short) 12);
        G = new br("device_brand", (byte) 11, (short) 13);
        H = new br("device_manutime", (byte) 10, (short) 14);
        I = new br("device_manufacturer", (byte) 11, (short) 15);
        J = new br("device_manuid", (byte) 11, (short) 16);
        K = new br("device_name", (byte) 11, (short) 17);
        L = new br("wp_device", (byte) 11, (short) 18);
        Map hashMap = new HashMap();
        M = hashMap;
        hashMap.put(ce.class, new b());
        M.put(cf.class, new d());
        hashMap = new EnumMap(e.class);
        hashMap.put(e.a, new ah("device_id", (byte) 2, new ai((byte) 11)));
        hashMap.put(e.b, new ah(com.umeng.common.a.e, (byte) 2, new ai((byte) 11)));
        hashMap.put(e.c, new ah("mac_address", (byte) 2, new ai((byte) 11)));
        hashMap.put(e.d, new ah("open_udid", (byte) 2, new ai((byte) 11)));
        hashMap.put(e.e, new ah("model", (byte) 2, new ai((byte) 11)));
        hashMap.put(e.f, new ah("cpu", (byte) 2, new ai((byte) 11)));
        hashMap.put(e.g, new ah(SocializeProtocolConstants.PROTOCOL_KEY_OS, (byte) 2, new ai((byte) 11)));
        hashMap.put(e.h, new ah("os_version", (byte) 2, new ai((byte) 11)));
        hashMap.put(e.i, new ah("resolution", (byte) 2, new al(bk.class)));
        hashMap.put(e.j, new ah("is_jailbroken", (byte) 2, new ai((byte) 2)));
        hashMap.put(e.k, new ah("is_pirated", (byte) 2, new ai((byte) 2)));
        hashMap.put(e.l, new ah("device_board", (byte) 2, new ai((byte) 11)));
        hashMap.put(e.m, new ah("device_brand", (byte) 2, new ai((byte) 11)));
        hashMap.put(e.n, new ah("device_manutime", (byte) 2, new ai((byte) 10)));
        hashMap.put(e.o, new ah("device_manufacturer", (byte) 2, new ai((byte) 11)));
        hashMap.put(e.p, new ah("device_manuid", (byte) 2, new ai((byte) 11)));
        hashMap.put(e.q, new ah("device_name", (byte) 2, new ai((byte) 11)));
        hashMap.put(e.r, new ah("wp_device", (byte) 2, new ai((byte) 11)));
        s = Collections.unmodifiableMap(hashMap);
        ah.a(au.class, s);
    }

    public au() {
        this.N = (byte) 0;
        this.O = new e[]{e.a, e.b, e.c, e.d, e.e, e.f, e.g, e.h, e.i, e.j, e.k, e.l, e.m, e.n, e.o, e.p, e.q, e.r};
    }

    public final boolean a() {
        return this.a != null;
    }

    public final boolean b() {
        return this.b != null;
    }

    public final boolean c() {
        return this.c != null;
    }

    public final boolean d() {
        return this.d != null;
    }

    public final boolean e() {
        return this.e != null;
    }

    public final boolean f() {
        return this.f != null;
    }

    public final boolean g() {
        return this.g != null;
    }

    public final boolean h() {
        return this.h != null;
    }

    public final boolean i() {
        return this.i != null;
    }

    public final boolean j() {
        return w.a(this.N, 0);
    }

    public final void k() {
        this.N = (byte) (this.N | 1);
    }

    public final boolean l() {
        return w.a(this.N, 1);
    }

    public final void m() {
        this.N = (byte) (this.N | 2);
    }

    public final boolean n() {
        return this.l != null;
    }

    public final boolean o() {
        return this.m != null;
    }

    public final boolean p() {
        return w.a(this.N, SimpleLog.LOG_LEVEL_DEBUG);
    }

    public final void q() {
        this.N = (byte) (this.N | 4);
    }

    public final boolean r() {
        return this.o != null;
    }

    public final boolean s() {
        return this.p != null;
    }

    public final boolean t() {
        return this.q != null;
    }

    public final boolean u() {
        return this.r != null;
    }

    public final void a(bu buVar) throws ac {
        ((cd) M.get(buVar.s())).a().b(buVar, this);
    }

    public final void b(bu buVar) throws ac {
        ((cd) M.get(buVar.s())).a().a(buVar, this);
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("DeviceInfo(");
        Object obj2 = 1;
        if (a()) {
            stringBuilder.append("device_id:");
            if (this.a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.a);
            }
            obj2 = null;
        }
        if (b()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("idmd5:");
            if (this.b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.b);
            }
            obj2 = null;
        }
        if (c()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("mac_address:");
            if (this.c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.c);
            }
            obj2 = null;
        }
        if (d()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("open_udid:");
            if (this.d == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.d);
            }
            obj2 = null;
        }
        if (e()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("model:");
            if (this.e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.e);
            }
            obj2 = null;
        }
        if (f()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("cpu:");
            if (this.f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f);
            }
            obj2 = null;
        }
        if (g()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("os:");
            if (this.g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.g);
            }
            obj2 = null;
        }
        if (h()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("os_version:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
            obj2 = null;
        }
        if (i()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("resolution:");
            if (this.i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.i);
            }
            obj2 = null;
        }
        if (j()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("is_jailbroken:");
            stringBuilder.append(this.j);
            obj2 = null;
        }
        if (l()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("is_pirated:");
            stringBuilder.append(this.k);
            obj2 = null;
        }
        if (n()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("device_board:");
            if (this.l == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.l);
            }
            obj2 = null;
        }
        if (o()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("device_brand:");
            if (this.m == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.m);
            }
            obj2 = null;
        }
        if (p()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("device_manutime:");
            stringBuilder.append(this.n);
            obj2 = null;
        }
        if (r()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("device_manufacturer:");
            if (this.o == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.o);
            }
            obj2 = null;
        }
        if (s()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("device_manuid:");
            if (this.p == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.p);
            }
            obj2 = null;
        }
        if (t()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("device_name:");
            if (this.q == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.q);
            }
        } else {
            obj = obj2;
        }
        if (u()) {
            if (obj == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("wp_device:");
            if (this.r == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.r);
            }
        }
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }

    public final void v() throws ac {
        if (this.i != null) {
            bk.c();
        }
    }
}
