package com.xunlei.XLStat.b;

import android.content.Context;
import android.text.TextUtils;
import com.xunlei.XLStat.CommonStruct.XLStatInitStruct;
import com.xunlei.XLStat.XLStatLog.XLStatLog;
import com.xunlei.XLStat.i.a;
import com.xunlei.XLStat.j.b;
import com.xunlei.XLStat.j.e;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class c {
    private static String a;
    private static String b;
    private int c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private long k;
    private Context l;
    private XLStatInitStruct m;
    private boolean n;
    private byte[] o;

    static {
        a = "SystemHelper";
        b = "1.0.0.20";
    }

    public c(Context context, XLStatInitStruct xLStatInitStruct) {
        this.f = "android";
        this.n = false;
        XLStatLog.d(a, "SystemHelper", new StringBuilder("ctx: ").append(context).append(",  initStruct: ").append(xLStatInitStruct).toString());
        if (context == null || xLStatInitStruct == null) {
            this.l = null;
            this.m = null;
            this.n = false;
            return;
        }
        this.l = context;
        this.m = xLStatInitStruct;
        this.n = true;
    }

    public boolean a() {
        if (this.l == null || this.m == null) {
            XLStatLog.d(a, "setAppInfo", "\ufffd\ufffd\u0221\u03f5\u0373\ufffd\ufffd\u03e2\u02a7\ufffd\u0723\ufffd");
            this.n = false;
            return false;
        }
        this.c = b.f(this.l);
        this.d = b.a();
        this.e = b.c();
        double[] a = b.a(this.l);
        this.g = a[0] + "*" + a[1];
        this.i = this.m.installchannel;
        this.j = this.m.startupchannel;
        this.k = e();
        try {
            b();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.n = true;
        return this.n;
    }

    public void b() throws UnsupportedEncodingException {
        XLStatLog.d(a, "fillExtData", new StringBuilder("Imei: ").append(new String(b.b(this.l))).toString());
        XLStatLog.d(a, "fillExtData", new StringBuilder("providername: ").append(new String(b.e(this.l))).toString());
        XLStatLog.d(a, "fillExtData", new StringBuilder("Imsi: ").append(new String(b.d(this.l))).toString());
        String a = a.a(this.l).a();
        if (TextUtils.isEmpty(a)) {
            a = b.c(this.l);
        }
        String b = a.a(this.l).b();
        if (TextUtils.isEmpty(b)) {
            b = b.b(this.l);
        }
        this.h = URLEncoder.encode(new StringBuilder("providersName=").append(new String(b.e(this.l))).toString(), "utf-8") + "," + URLEncoder.encode(new StringBuilder("kernalVersion=").append(new String(b.b())).toString(), "utf-8") + "," + URLEncoder.encode(new StringBuilder("resulutionInfo=").append(new String(b.a(this.l)[2])).toString(), "utf-8") + "," + URLEncoder.encode(new StringBuilder("imei=").append(new String(b)).toString(), "utf-8") + "," + URLEncoder.encode(new StringBuilder("imsi=").append(new String(b.d(this.l))).toString(), "utf-8") + "," + URLEncoder.encode(new StringBuilder("model=").append(new String(b.d())).toString(), "utf-8") + "," + URLEncoder.encode(new StringBuilder("mac=").append(new String(a)).toString(), "utf-8");
        XLStatLog.d(a, "fillExtData", new StringBuilder("extData: ").append(this.h).toString());
    }

    public void c() {
        this.k = e();
    }

    public byte[] d() {
        Object obj;
        Object bytes;
        Object bytes2;
        Object bytes3;
        Object bytes4;
        Object bytes5;
        Object a;
        Object bytes6;
        Object bytes7;
        this.o = new byte[0];
        Object obj2 = new Object[2];
        System.arraycopy(b.a(this.c), 0, obj2, 0, SimpleLog.LOG_LEVEL_DEBUG);
        XLStatLog.d(a, "getBytes", new StringBuilder("this cpu: ").append(this.d).toString());
        if (this.d == null || BuildConfig.VERSION_NAME.equals(this.d) || this.d.length() <= 0) {
            obj = new Object[4];
            Arrays.fill(obj, (byte) 0);
        } else {
            bytes = this.d.getBytes();
            obj = new Object[(bytes.length + 4)];
            System.arraycopy(b.a(bytes.length), 0, obj, 0, MqttConnectOptions.MQTT_VERSION_3_1_1);
            System.arraycopy(bytes, 0, obj, MqttConnectOptions.MQTT_VERSION_3_1_1, bytes.length);
        }
        if (this.e == null || BuildConfig.VERSION_NAME.equals(this.e) || this.e.length() <= 0) {
            bytes = new Object[4];
            Arrays.fill(bytes, (byte) 0);
        } else {
            bytes2 = this.e.getBytes();
            bytes = new Object[(bytes2.length + 4)];
            System.arraycopy(b.a(bytes2.length), 0, bytes, 0, MqttConnectOptions.MQTT_VERSION_3_1_1);
            System.arraycopy(bytes2, 0, bytes, MqttConnectOptions.MQTT_VERSION_3_1_1, bytes2.length);
        }
        if (this.f == null || BuildConfig.VERSION_NAME.equals(this.f) || this.f.length() <= 0) {
            bytes2 = new Object[4];
            Arrays.fill(bytes2, (byte) 0);
        } else {
            bytes3 = this.f.getBytes();
            bytes2 = new Object[(bytes3.length + 4)];
            System.arraycopy(b.a(bytes3.length), 0, bytes2, 0, MqttConnectOptions.MQTT_VERSION_3_1_1);
            System.arraycopy(bytes3, 0, bytes2, MqttConnectOptions.MQTT_VERSION_3_1_1, bytes3.length);
        }
        if (this.g == null || BuildConfig.VERSION_NAME.equals(this.g) || this.g.length() <= 0) {
            bytes3 = new Object[4];
            Arrays.fill(bytes3, (byte) 0);
        } else {
            bytes4 = this.g.getBytes();
            bytes3 = new Object[(bytes4.length + 4)];
            System.arraycopy(b.a(bytes4.length), 0, bytes3, 0, MqttConnectOptions.MQTT_VERSION_3_1_1);
            System.arraycopy(bytes4, 0, bytes3, MqttConnectOptions.MQTT_VERSION_3_1_1, bytes4.length);
        }
        if (b == null || b.length() <= 0 || BuildConfig.VERSION_NAME.equals(b)) {
            bytes4 = new Object[4];
            Arrays.fill(bytes4, (byte) 0);
        } else {
            bytes5 = b.getBytes();
            a = b.a(bytes5.length);
            bytes4 = new Object[(bytes5.length + 4)];
            System.arraycopy(a, 0, bytes4, 0, a.length);
            System.arraycopy(bytes5, 0, bytes4, MqttConnectOptions.MQTT_VERSION_3_1_1, bytes5.length);
        }
        if (this.i == null || BuildConfig.VERSION_NAME.equals(this.i) || this.i.length() <= 0) {
            bytes5 = new Object[4];
            Arrays.fill(bytes5, (byte) 0);
        } else {
            a = this.m.installchannel.getBytes();
            bytes5 = new Object[(a.length + 4)];
            System.arraycopy(b.a(a.length), 0, bytes5, 0, MqttConnectOptions.MQTT_VERSION_3_1_1);
            System.arraycopy(a, 0, bytes5, MqttConnectOptions.MQTT_VERSION_3_1_1, a.length);
        }
        if (this.j == null || BuildConfig.VERSION_NAME.equals(this.j) || this.j.length() <= 0) {
            a = new Object[4];
            Arrays.fill(a, (byte) 0);
        } else {
            bytes6 = this.m.startupchannel.getBytes();
            a = new Object[(bytes6.length + 4)];
            System.arraycopy(b.a(bytes6.length), 0, a, 0, MqttConnectOptions.MQTT_VERSION_3_1_1);
            System.arraycopy(bytes6, 0, a, MqttConnectOptions.MQTT_VERSION_3_1_1, bytes6.length);
        }
        if (this.h == null || BuildConfig.VERSION_NAME.equals(this.h) || this.h.length() <= 0) {
            bytes6 = new Object[4];
            Arrays.fill(bytes6, (byte) 0);
        } else {
            bytes7 = this.h.getBytes();
            bytes6 = new Object[(bytes7.length + 4)];
            System.arraycopy(b.a(bytes7.length), 0, bytes6, 0, MqttConnectOptions.MQTT_VERSION_3_1_1);
            System.arraycopy(bytes7, 0, bytes6, MqttConnectOptions.MQTT_VERSION_3_1_1, bytes7.length);
        }
        bytes7 = b.a(this.k);
        int length = ((((((((obj.length + 2) + bytes4.length) + bytes.length) + bytes2.length) + bytes3.length) + bytes6.length) + bytes5.length) + a.length) + bytes7.length;
        this.o = new byte[(length + 8)];
        System.arraycopy(b.a(1), 0, this.o, 0, MqttConnectOptions.MQTT_VERSION_3_1_1);
        System.arraycopy(b.a(length), 0, this.o, MqttConnectOptions.MQTT_VERSION_3_1_1, MqttConnectOptions.MQTT_VERSION_3_1_1);
        System.arraycopy(obj2, 0, this.o, SpdyProtocol.PUBKEY_SEQ_ADASH, 2);
        System.arraycopy(obj, 0, this.o, SpdyProtocol.PUBKEY_SEQ_OPEN, obj.length);
        int length2 = obj.length + 10;
        System.arraycopy(bytes, 0, this.o, length2, bytes.length);
        length2 += bytes.length;
        System.arraycopy(bytes2, 0, this.o, length2, bytes2.length);
        length2 += bytes2.length;
        System.arraycopy(bytes3, 0, this.o, length2, bytes3.length);
        length2 += bytes3.length;
        System.arraycopy(bytes4, 0, this.o, length2, bytes4.length);
        length2 += bytes4.length;
        System.arraycopy(bytes5, 0, this.o, length2, bytes5.length);
        length2 += bytes5.length;
        System.arraycopy(a, 0, this.o, length2, a.length);
        length2 += a.length;
        System.arraycopy(bytes6, 0, this.o, length2, bytes6.length);
        length2 += bytes6.length;
        System.arraycopy(bytes7, 0, this.o, length2, bytes7.length);
        XLStatLog.d(a, "getBytes", new StringBuilder("totalLen: ").append(length).append("  templen: ").append(length2 + bytes7.length).append("\nsystem bytes: ").append(e.a(this.o)).toString());
        return this.o;
    }

    public static long e() {
        return System.currentTimeMillis() / 1000;
    }

    public static String f() {
        return b;
    }
}
