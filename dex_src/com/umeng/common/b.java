package com.umeng.common;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import com.umeng.message.PushAgent;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.OauthHelper;
import org.android.spdy.SpdyProtocol;
import org.json.JSONObject;

// compiled from: Header.java
public class b {
    public static final String C = "Android";
    public static final String D = "Android";
    private static final String E;
    public String A;
    public String B;
    private final String F;
    private final String G;
    private final String H;
    private final String I;
    private final String J;
    private final String K;
    private final String L;
    private final String M;
    private final String N;
    private final String O;
    private final String P;
    private final String Q;
    private final String R;
    private final String S;
    private final String T;
    private final String U;
    private final String V;
    private final String W;
    private final String X;
    private final String Y;
    private final String Z;
    public String a;
    private final String aa;
    private final String ab;
    private final String ac;
    private final String ad;
    private final String ae;
    private final String af;
    private final String ag;
    public String b;
    public String c;
    public String d;
    public String e;
    public long f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public String n;
    public String o;
    public String p;
    public String q;
    public String r;
    public String s;
    public String t;
    public int u;
    public String v;
    public String w;
    public String x;
    public String y;
    public String z;

    static {
        E = b.class.getName();
    }

    public b() {
        this.F = OauthHelper.APP_KEY;
        this.G = a.d;
        this.H = "device_id";
        this.I = a.e;
        this.J = "mc";
        this.K = "req_time";
        this.L = "android_id";
        this.M = "serial_number";
        this.N = "device_model";
        this.O = SocializeProtocolConstants.PROTOCOL_KEY_OS;
        this.P = "os_version";
        this.Q = "resolution";
        this.R = "cpu";
        this.S = "gpu_vender";
        this.T = "gpu_renderer";
        this.U = "app_version";
        this.V = a.f;
        this.W = "package_name";
        this.X = "sdk_type";
        this.Y = a.h;
        this.Z = "timezone";
        this.aa = "country";
        this.ab = "language";
        this.ac = "access";
        this.ad = "access_subtype";
        this.ae = "carrier";
        this.af = "wrapper_type";
        this.ag = "wrapper_version";
    }

    public b(String str, String str2) {
        this.F = OauthHelper.APP_KEY;
        this.G = a.d;
        this.H = "device_id";
        this.I = a.e;
        this.J = "mc";
        this.K = "req_time";
        this.L = "android_id";
        this.M = "serial_number";
        this.N = "device_model";
        this.O = SocializeProtocolConstants.PROTOCOL_KEY_OS;
        this.P = "os_version";
        this.Q = "resolution";
        this.R = "cpu";
        this.S = "gpu_vender";
        this.T = "gpu_renderer";
        this.U = "app_version";
        this.V = a.f;
        this.W = "package_name";
        this.X = "sdk_type";
        this.Y = a.h;
        this.Z = "timezone";
        this.aa = "country";
        this.ab = "language";
        this.ac = "access";
        this.ad = "access_subtype";
        this.ae = "carrier";
        this.af = "wrapper_type";
        this.ag = "wrapper_version";
        this.a = str;
        this.b = str2;
    }

    private void d(JSONObject jSONObject) throws Exception {
        this.a = jSONObject.getString(OauthHelper.APP_KEY);
        this.c = jSONObject.getString("device_id");
        this.d = jSONObject.getString(a.e);
        if (jSONObject.has("mc")) {
            this.e = jSONObject.getString("mc");
        }
        if (jSONObject.has(a.d)) {
            this.b = jSONObject.getString(a.d);
        }
        if (jSONObject.has("req_time")) {
            this.f = jSONObject.getLong("req_time");
        }
    }

    private void e(JSONObject jSONObject) throws Exception {
        String string;
        String str = null;
        this.i = jSONObject.has("device_model") ? jSONObject.getString("device_model") : null;
        if (jSONObject.has(SocializeProtocolConstants.PROTOCOL_KEY_OS)) {
            string = jSONObject.getString(SocializeProtocolConstants.PROTOCOL_KEY_OS);
        } else {
            string = null;
        }
        this.j = string;
        if (jSONObject.has("os_version")) {
            string = jSONObject.getString("os_version");
        } else {
            string = null;
        }
        this.k = string;
        if (jSONObject.has("resolution")) {
            string = jSONObject.getString("resolution");
        } else {
            string = null;
        }
        this.l = string;
        if (jSONObject.has("cpu")) {
            string = jSONObject.getString("cpu");
        } else {
            string = null;
        }
        this.m = string;
        if (jSONObject.has("gpu_vender")) {
            string = jSONObject.getString("gpu_vender");
        } else {
            string = null;
        }
        this.n = string;
        if (jSONObject.has("gpu_renderer")) {
            string = jSONObject.getString("gpu_renderer");
        } else {
            string = null;
        }
        this.o = string;
        if (jSONObject.has("android_id")) {
            string = jSONObject.getString("android_id");
        } else {
            string = null;
        }
        this.g = string;
        if (jSONObject.has("serial_number")) {
            str = jSONObject.getString("serial_number");
        }
        this.h = str;
    }

    private void f(JSONObject jSONObject) throws Exception {
        String string;
        String str = null;
        this.p = jSONObject.has("app_version") ? jSONObject.getString("app_version") : null;
        if (jSONObject.has(a.f)) {
            string = jSONObject.getString(a.f);
        } else {
            string = null;
        }
        this.q = string;
        if (jSONObject.has("package_name")) {
            str = jSONObject.getString("package_name");
        }
        this.r = str;
    }

    private void g(JSONObject jSONObject) throws Exception {
        this.s = jSONObject.getString("sdk_type");
        this.t = jSONObject.getString(a.h);
    }

    private void h(JSONObject jSONObject) throws Exception {
        String string;
        String str = null;
        this.u = jSONObject.has("timezone") ? jSONObject.getInt("timezone") : SpdyProtocol.PUBKEY_SEQ_ADASH;
        if (jSONObject.has("country")) {
            string = jSONObject.getString("country");
        } else {
            string = null;
        }
        this.v = string;
        if (jSONObject.has("language")) {
            str = jSONObject.getString("language");
        }
        this.w = str;
    }

    private void i(JSONObject jSONObject) throws Exception {
        String string;
        String str = null;
        if (jSONObject.has("access")) {
            string = jSONObject.getString("access");
        } else {
            string = null;
        }
        this.x = string;
        if (jSONObject.has("access_subtype")) {
            string = jSONObject.getString("access_subtype");
        } else {
            string = null;
        }
        this.y = string;
        if (jSONObject.has("carrier")) {
            str = jSONObject.getString("carrier");
        }
        this.z = str;
    }

    private void j(JSONObject jSONObject) throws Exception {
        String string;
        String str = null;
        if (jSONObject.has("wrapper_type")) {
            string = jSONObject.getString("wrapper_type");
        } else {
            string = null;
        }
        this.A = string;
        if (jSONObject.has("wrapper_version")) {
            str = jSONObject.getString("wrapper_version");
        }
        this.B = str;
    }

    public void a(JSONObject jSONObject) throws Exception {
        if (jSONObject != null) {
            d(jSONObject);
            e(jSONObject);
            f(jSONObject);
            g(jSONObject);
            h(jSONObject);
            i(jSONObject);
            j(jSONObject);
        }
    }

    private void k(JSONObject jSONObject) throws Exception {
        jSONObject.put(OauthHelper.APP_KEY, this.a);
        if (this.a == null || 24 != this.a.length()) {
            this.c = com.umeng.message.proguard.b.a(this.c, "utf-8");
        } else {
            this.c = com.umeng.message.proguard.b.a(this.c, "utf-8", this.a.substring(0, SpdyProtocol.CUSTOM));
        }
        jSONObject.put("device_id", this.c);
        jSONObject.put(a.e, this.d);
        if (this.b != null) {
            jSONObject.put(a.d, this.b);
        }
        if (this.e != null) {
            jSONObject.put("mc", this.e);
        }
        if (this.f > 0) {
            jSONObject.put("req_time", this.f);
        }
        if (this.g != null) {
            jSONObject.put("android_id", this.g);
        }
        if (this.h != null) {
            jSONObject.put("serial_number", this.h);
        }
    }

    private void l(JSONObject jSONObject) throws Exception {
        jSONObject.put(OauthHelper.APP_KEY, this.a);
        if (this.b != null) {
            jSONObject.put(a.d, this.b);
        }
    }

    private void m(JSONObject jSONObject) throws Exception {
        if (this.i != null) {
            jSONObject.put("device_model", this.i);
        }
        if (this.j != null) {
            jSONObject.put(SocializeProtocolConstants.PROTOCOL_KEY_OS, this.j);
        }
        if (this.k != null) {
            jSONObject.put("os_version", this.k);
        }
        if (this.l != null) {
            jSONObject.put("resolution", this.l);
        }
        if (this.m != null) {
            jSONObject.put("cpu", this.m);
        }
        if (this.n != null) {
            jSONObject.put("gpu_vender", this.n);
        }
        if (this.o != null) {
            jSONObject.put("gpu_vender", this.o);
        }
    }

    private void n(JSONObject jSONObject) throws Exception {
        if (this.i != null) {
            jSONObject.put("device_model", this.i);
        }
        if (this.j != null) {
            jSONObject.put(SocializeProtocolConstants.PROTOCOL_KEY_OS, this.j);
        }
        if (this.k != null) {
            jSONObject.put("os_version", this.k);
        }
    }

    private void o(JSONObject jSONObject) throws Exception {
        if (this.p != null) {
            jSONObject.put("app_version", this.p);
        }
        if (this.q != null) {
            jSONObject.put(a.f, this.q);
        }
        if (this.r != null) {
            jSONObject.put("package_name", this.r);
        }
    }

    private void p(JSONObject jSONObject) throws Exception {
        if (this.p != null) {
            jSONObject.put("app_version", this.p);
        }
        if (this.q != null) {
            jSONObject.put(a.f, this.q);
        }
    }

    private void q(JSONObject jSONObject) throws Exception {
        jSONObject.put("sdk_type", this.s);
        jSONObject.put(a.h, this.t);
    }

    private void r(JSONObject jSONObject) throws Exception {
        jSONObject.put("timezone", this.u);
        if (this.v != null) {
            jSONObject.put("country", this.v);
        }
        if (this.w != null) {
            jSONObject.put("language", this.w);
        }
    }

    private void s(JSONObject jSONObject) throws Exception {
        if (this.x != null) {
            jSONObject.put("access", this.x);
        }
        if (this.y != null) {
            jSONObject.put("access_subtype", this.y);
        }
        if (this.z != null) {
            jSONObject.put("carrier", this.z);
        }
    }

    private void t(JSONObject jSONObject) throws Exception {
        if (this.A != null) {
            jSONObject.put("wrapper_type", this.A);
        }
        if (this.B != null) {
            jSONObject.put("wrapper_version", this.B);
        }
    }

    public void b(JSONObject jSONObject) throws Exception {
        k(jSONObject);
        m(jSONObject);
        o(jSONObject);
        q(jSONObject);
        r(jSONObject);
        s(jSONObject);
        t(jSONObject);
    }

    public void c(JSONObject jSONObject) throws Exception {
        l(jSONObject);
        n(jSONObject);
        p(jSONObject);
        q(jSONObject);
        s(jSONObject);
    }

    public boolean a() {
        if (this.a == null) {
            UmLog.e(E, "missing appkey ");
            return false;
        } else if (this.c != null && this.d != null) {
            return true;
        } else {
            UmLog.e(E, "missing device id");
            return false;
        }
    }

    public void a(Context context, String... strArr) {
        if (strArr != null && strArr.length == 2) {
            this.a = strArr[0];
            this.b = strArr[1];
        }
        if (this.a == null) {
            this.a = PushAgent.getInstance(context).getMessageAppkey();
        }
        if (this.b == null) {
            this.b = PushAgent.getInstance(context).getMessageChannel();
        }
        this.c = UmengMessageDeviceConfig.getDeviceId(context);
        this.d = UmengMessageDeviceConfig.getDeviceIdMD5(context);
        this.e = UmengMessageDeviceConfig.getMac(context);
    }

    public void a(Context context) {
        this.i = Build.MODEL;
        this.j = D;
        this.k = VERSION.RELEASE;
        this.l = UmengMessageDeviceConfig.getResolution(context);
        this.m = UmengMessageDeviceConfig.getCPU();
        this.g = UmengMessageDeviceConfig.getAndroidId(context);
        this.h = UmengMessageDeviceConfig.getSerial_number();
    }

    public void b(Context context) {
        this.p = UmengMessageDeviceConfig.getAppVersionName(context);
        this.q = UmengMessageDeviceConfig.getAppVersionCode(context);
        this.r = UmengMessageDeviceConfig.getPackageName(context);
    }

    public void c(Context context) {
        this.s = D;
        this.t = "3.0.1";
    }

    public void d(Context context) {
        this.u = UmengMessageDeviceConfig.getTimeZone(context);
        String[] localeInfo = UmengMessageDeviceConfig.getLocaleInfo(context);
        this.v = localeInfo[0];
        this.w = localeInfo[1];
    }

    public void e(Context context) {
        String[] networkAccessMode = UmengMessageDeviceConfig.getNetworkAccessMode(context);
        this.x = networkAccessMode[0];
        this.y = networkAccessMode[1];
        this.z = UmengMessageDeviceConfig.getOperator(context);
    }

    public void b(Context context, String... strArr) {
        a(context, strArr);
        a(context);
        b(context);
        c(context);
        d(context);
        e(context);
    }

    public void c(Context context, String... strArr) {
        a(context, strArr);
        a(context);
        b(context);
        c(context);
        e(context);
    }

    public boolean b() {
        return (this.a == null || this.c == null) ? false : true;
    }
}
