package com.xunlei.XLStat;

import android.content.Context;
import com.xunlei.XLStat.CommonStruct.XLStatInitStruct;
import com.xunlei.XLStat.XLStatLog.XLStatLog;
import com.xunlei.XLStat.j.b;
import com.xunlei.XLStat.j.e;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import java.util.Arrays;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class a {
    private static String a;
    private int b;
    private byte[] c;
    private String d;
    private byte[] e;
    private byte[] f;
    private String g;
    private String h;
    private String i;
    private String j;
    private byte[] k;
    private int l;
    private XLStatInitStruct m;
    private boolean n;
    private byte[] o;
    private Context p;

    static {
        a = "BusinessFields";
    }

    public a(Context context, XLStatInitStruct xLStatInitStruct) {
        this.b = 1001;
        this.l = -1;
        this.n = true;
        this.o = new byte[0];
        if (xLStatInitStruct == null || context == null) {
            this.n = false;
            return;
        }
        this.p = context;
        this.m = xLStatInitStruct;
        if (xLStatInitStruct.productVersion == null) {
            xLStatInitStruct.productVersion = BuildConfig.VERSION_NAME;
        }
        this.d = xLStatInitStruct.productVersion;
        if (xLStatInitStruct.serviceVersion == null) {
            xLStatInitStruct.serviceVersion = BuildConfig.VERSION_NAME;
        }
        this.g = xLStatInitStruct.serviceVersion;
        if (xLStatInitStruct.extData == null) {
            xLStatInitStruct.extData = new ArrayList();
        }
        this.h = d.a(xLStatInitStruct.extData);
        if (xLStatInitStruct.peerID == null) {
            xLStatInitStruct.peerID = BuildConfig.VERSION_NAME;
        }
        this.i = xLStatInitStruct.peerID;
        this.c = new byte[2];
        Arrays.fill(this.c, (byte) 0);
        this.e = new byte[4];
        Arrays.fill(this.e, (byte) 0);
        this.f = new byte[4];
        Arrays.fill(this.f, (byte) 0);
        this.k = new byte[4];
        Arrays.fill(this.k, (byte) 0);
        byte[] a = b.a(this.b);
        this.c[0] = a[0];
        this.c[1] = a[1];
        if (this.m.productName == null || BuildConfig.VERSION_NAME.equalsIgnoreCase(this.m.productName) || this.m.productKey == null || BuildConfig.VERSION_NAME.equalsIgnoreCase(this.d)) {
            this.n = false;
        }
        a(this.p);
        this.n = true;
    }

    public void a(Context context) {
        b(this.m.productName, this.m.productKey);
        c(this.m.serviceName, this.m.serviceKey);
        b(this.m.userID);
        b(context);
    }

    private void b(String str, String str2) {
        if (str == null || str.length() <= 0 || str2 == null || str2.length() <= 0 || !this.n) {
            this.n = false;
            XLStatLog.d(a, "figureProductid", new StringBuilder("product name or product key is invalid ... \nproductName: ").append(str).append(", productKey: ").append(str2).toString());
            return;
        }
        this.l = com.xunlei.XLStat.j.a.a(str2, str).shortValue();
        XLStatLog.d(a, "figureProductid", new StringBuilder("productName: ").append(str).append(", productKey: ").append(str2).toString());
        XLStatLog.d(a, "figureProductid", new StringBuilder("appid: ").append(this.l).toString());
        this.e = b.a(this.l);
        this.n = true;
    }

    public static boolean a(String str, String str2) {
        XLStatLog.d(a, "checkProductid", new StringBuilder("productName: ").append(str).append(", productKey: ").append(str2).toString());
        if (str == null || str.length() <= 0 || str2 == null || str2.length() <= 0) {
            XLStatLog.d(a, "checkProductid", new StringBuilder("product name or product key is invalid ... \nproductName: ").append(str).append(", productKey: ").append(str2).toString());
            return false;
        }
        short shortValue = com.xunlei.XLStat.j.a.a(str2, str).shortValue();
        XLStatLog.d(a, "checkProductid", new StringBuilder("id: ").append(shortValue).toString());
        if (shortValue > (short) 0) {
            return true;
        }
        XLStatLog.d(a, "checkProductid", new StringBuilder("productName: ").append(str).append(", productKey: ").append(str2).append(", id: ").append(shortValue).toString());
        return false;
    }

    private void c(String str, String str2) {
        if (str == null || str.length() <= 0 || str2 == null || str2.length() <= 0 || !this.n) {
            this.n = false;
            return;
        }
        this.f = b.a(com.xunlei.XLStat.j.a.a(str2, str).shortValue());
        this.n = true;
    }

    private void b(long j) {
        byte[] a = b.a(j);
        this.k[0] = a[0];
        this.k[1] = a[1];
        this.k[2] = a[2];
        this.k[3] = a[3];
        XLStatLog.d(a, "figureUserID", new StringBuilder("userid: ").append(e.a(this.k)).append("  userid(long to bytes): ").append(e.a(a)).toString());
    }

    public void a(long j) {
        b(j);
        synchronized (this.o) {
            e();
        }
    }

    public void a(String str) {
        XLStatLog.d(a, "set_extdata", new StringBuilder("extdata: ").append(str).toString());
        this.h = str;
        synchronized (this.o) {
            e();
        }
    }

    private void b(Context context) {
        this.j = com.xunlei.XLStat.i.a.a(context).c();
        XLStatLog.d(a, "figureGuid", new StringBuilder("guid: ").append(this.j).toString());
    }

    public byte[] a() {
        return (!this.n || this.o == null || this.o.length <= 0) ? e() : this.o;
    }

    public int b() {
        return this.l;
    }

    public String c() {
        return this.m.productKey;
    }

    public String d() {
        return this.i;
    }

    private byte[] e() {
        Object obj;
        Object bytes;
        Object a;
        Object a2;
        Object a3;
        Object a4;
        if (this.d == null || BuildConfig.VERSION_NAME.equals(this.d)) {
            obj = new Object[4];
            Arrays.fill(obj, (byte) 0);
        } else {
            bytes = this.d.getBytes();
            a = b.a(bytes.length);
            obj = new Object[(bytes.length + a.length)];
            System.arraycopy(a, 0, obj, 0, a.length);
            System.arraycopy(bytes, 0, obj, MqttConnectOptions.MQTT_VERSION_3_1_1, bytes.length);
        }
        if (this.g == null || BuildConfig.VERSION_NAME.equals(this.g)) {
            bytes = new Object[4];
            Arrays.fill(bytes, (byte) 0);
        } else {
            a = this.g.getBytes();
            a2 = b.a(a.length);
            bytes = new Object[(a.length + a2.length)];
            System.arraycopy(a2, 0, bytes, 0, a2.length);
            System.arraycopy(a, 0, bytes, MqttConnectOptions.MQTT_VERSION_3_1_1, a.length);
        }
        if (this.h == null || BuildConfig.VERSION_NAME.equals(this.h)) {
            a = new Object[4];
            Arrays.fill(a, (byte) 0);
        } else {
            a2 = this.h.getBytes();
            a3 = b.a(a2.length);
            a = new Object[(a2.length + a3.length)];
            System.arraycopy(a3, 0, a, 0, a3.length);
            System.arraycopy(a2, 0, a, MqttConnectOptions.MQTT_VERSION_3_1_1, a2.length);
        }
        if (this.i == null || BuildConfig.VERSION_NAME.equals(this.i)) {
            a2 = new Object[4];
            Arrays.fill(a2, (byte) 0);
        } else {
            a3 = this.i.getBytes();
            a4 = b.a(a3.length);
            a2 = new Object[(a3.length + a4.length)];
            System.arraycopy(a4, 0, a2, 0, a4.length);
            System.arraycopy(a3, 0, a2, MqttConnectOptions.MQTT_VERSION_3_1_1, a3.length);
        }
        XLStatLog.d(a, "computeBytes", new StringBuilder("peerid: ").append(this.i).append(", peeridbytes: ").append(e.a(a2)).toString());
        if (this.j == null || BuildConfig.VERSION_NAME.equals(this.j)) {
            b(this.p);
        }
        a3 = this.j.getBytes();
        a4 = b.a(a3.length);
        Object obj2 = new Object[(a3.length + a4.length)];
        System.arraycopy(a4, 0, obj2, 0, a4.length);
        System.arraycopy(a3, 0, obj2, MqttConnectOptions.MQTT_VERSION_3_1_1, a3.length);
        this.o = new byte[((((((((this.c.length + this.e.length) + obj.length) + this.f.length) + bytes.length) + a2.length) + obj2.length) + this.k.length) + a.length)];
        System.arraycopy(this.c, 0, this.o, 0, this.c.length);
        int length = this.c.length + 0;
        System.arraycopy(this.e, 0, this.o, length, this.e.length);
        length += this.e.length;
        System.arraycopy(obj, 0, this.o, length, obj.length);
        int length2 = obj.length + length;
        System.arraycopy(this.f, 0, this.o, length2, this.f.length);
        length2 += this.f.length;
        System.arraycopy(bytes, 0, this.o, length2, bytes.length);
        length2 += bytes.length;
        System.arraycopy(a2, 0, this.o, length2, a2.length);
        length2 += a2.length;
        System.arraycopy(obj2, 0, this.o, length2, obj2.length);
        length2 += obj2.length;
        System.arraycopy(this.k, 0, this.o, length2, this.k.length);
        System.arraycopy(a, 0, this.o, length2 + this.k.length, a.length);
        XLStatLog.d(a, "computeBytes", new StringBuilder("cmdid: ").append(this.b).append("  cmdid byte: ").append(e.a(this.c)).append("  product id: ").append(e.a(this.e)).append("  product version: ").append(this.d).append("  service id: ").append(e.a(this.f)).append("  service version: ").append(this.g).append("  ext data: ").append(this.h).append("  peer id: ").append(this.i).append("  guid: ").append(this.j).append("  user id: ").append(e.a(this.k)).append("\nbusiness fields bytes: ").append(e.a(this.o)).append("\nbusiness fields bytes size: ").append(this.o.length).toString());
        XLStatLog.d(a, "computeBytes", new StringBuilder("business bytes: ").append(e.a(this.o)).toString());
        return this.o;
    }
}
