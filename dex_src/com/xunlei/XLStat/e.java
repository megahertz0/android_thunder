package com.xunlei.XLStat;

import com.xunlei.XLStat.XLStatLog.XLStatLog;
import com.xunlei.XLStat.b.c;
import com.xunlei.XLStat.j.b;
import com.xunlei.xiazaibao.BuildConfig;
import java.net.URLEncoder;
import java.util.Arrays;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class e {
    public int a;
    public int b;
    public String c;
    public String d;
    public int e;
    public int f;
    public int g;
    public int h;
    public String i;
    public long j;
    public int k;
    public int l;
    private byte[] m;

    public e(int i, int i2, String str, String str2, int i3, int i4, int i5, int i6, String str3, int i7) {
        this.m = null;
        this.a = i;
        this.b = i2;
        this.c = str;
        this.d = str2;
        this.e = i3;
        this.f = i4;
        this.g = i5;
        this.h = i6;
        this.i = str3;
        this.j = c.e();
        this.k = -1;
        this.l = i7;
        this.m = null;
    }

    public e() {
        this.m = null;
        this.a = -1;
        this.b = -1;
        this.c = BuildConfig.VERSION_NAME;
        this.d = BuildConfig.VERSION_NAME;
        this.e = -1;
        this.f = -1;
        this.g = -1;
        this.h = -1;
        this.i = BuildConfig.VERSION_NAME;
        this.j = -1;
        this.k = -1;
        this.l = -2;
        this.m = null;
    }

    public byte[] a() {
        if (this.m != null && this.m.length > 0) {
            return this.m;
        }
        Object obj;
        Object bytes;
        Object a;
        Object a2;
        Object bytes2;
        Object obj2 = new Object[2];
        System.arraycopy(b.a(this.a), 0, obj2, 0, SimpleLog.LOG_LEVEL_DEBUG);
        Object obj3 = new Object[4];
        System.arraycopy(b.a(this.b), 0, obj3, 0, MqttConnectOptions.MQTT_VERSION_3_1_1);
        if (this.c == null || BuildConfig.VERSION_NAME.equals(this.c)) {
            obj = new Object[4];
            Arrays.fill(obj, (byte) 0);
        } else {
            bytes = URLEncoder.encode(this.c).getBytes();
            a = b.a(bytes.length);
            obj = new Object[(bytes.length + a.length)];
            System.arraycopy(a, 0, obj, 0, a.length);
            System.arraycopy(bytes, 0, obj, MqttConnectOptions.MQTT_VERSION_3_1_1, bytes.length);
        }
        if (this.d == null || BuildConfig.VERSION_NAME.equals(this.d)) {
            bytes = new Object[4];
            Arrays.fill(bytes, (byte) 0);
        } else {
            a = URLEncoder.encode(this.d).getBytes();
            a2 = b.a(a.length);
            bytes = new Object[(a.length + a2.length)];
            System.arraycopy(a2, 0, bytes, 0, a2.length);
            System.arraycopy(a, 0, bytes, MqttConnectOptions.MQTT_VERSION_3_1_1, a.length);
        }
        a2 = new Object[4];
        System.arraycopy(b.a(this.e), 0, a2, 0, MqttConnectOptions.MQTT_VERSION_3_1_1);
        Object obj4 = new Object[4];
        System.arraycopy(b.a(this.f), 0, obj4, 0, MqttConnectOptions.MQTT_VERSION_3_1_1);
        Object obj5 = new Object[4];
        System.arraycopy(b.a(this.g), 0, obj5, 0, MqttConnectOptions.MQTT_VERSION_3_1_1);
        Object obj6 = new Object[4];
        System.arraycopy(b.a(this.h), 0, obj6, 0, MqttConnectOptions.MQTT_VERSION_3_1_1);
        if (this.i == null || BuildConfig.VERSION_NAME.equals(this.i)) {
            a = new Object[4];
            Arrays.fill(a, (byte) 0);
        } else {
            bytes2 = this.i.getBytes();
            Object a3 = b.a(bytes2.length);
            a = new Object[(bytes2.length + a3.length)];
            System.arraycopy(a3, 0, a, 0, a3.length);
            System.arraycopy(bytes2, 0, a, MqttConnectOptions.MQTT_VERSION_3_1_1, bytes2.length);
        }
        bytes2 = new Object[8];
        System.arraycopy(b.a(this.j), 0, bytes2, 0, SpdyProtocol.PUBKEY_SEQ_ADASH);
        int length = (((((((obj.length + 6) + bytes.length) + 4) + 4) + 4) + 4) + a.length) + 8;
        this.m = new byte[(length + 4)];
        System.arraycopy(b.a(length), 0, this.m, 0, MqttConnectOptions.MQTT_VERSION_3_1_1);
        System.arraycopy(obj2, 0, this.m, MqttConnectOptions.MQTT_VERSION_3_1_1, 2);
        System.arraycopy(obj3, 0, this.m, SimpleLog.LOG_LEVEL_FATAL, MqttConnectOptions.MQTT_VERSION_3_1_1);
        System.arraycopy(obj, 0, this.m, SpdyProtocol.PUBKEY_SEQ_OPEN, obj.length);
        int length2 = obj.length + 10;
        System.arraycopy(bytes, 0, this.m, length2, bytes.length);
        length2 += bytes.length;
        System.arraycopy(a2, 0, this.m, length2, MqttConnectOptions.MQTT_VERSION_3_1_1);
        length2 += 4;
        System.arraycopy(obj4, 0, this.m, length2, MqttConnectOptions.MQTT_VERSION_3_1_1);
        length2 += 4;
        System.arraycopy(obj5, 0, this.m, length2, MqttConnectOptions.MQTT_VERSION_3_1_1);
        length2 += 4;
        System.arraycopy(obj6, 0, this.m, length2, MqttConnectOptions.MQTT_VERSION_3_1_1);
        length2 += 4;
        System.arraycopy(a, 0, this.m, length2, a.length);
        length2 += a.length;
        System.arraycopy(bytes2, 0, this.m, length2, SpdyProtocol.PUBKEY_SEQ_ADASH);
        XLStatLog.d("XLStatEvent", "getBytes", new StringBuilder("totalLen: ").append(length).append("  templen: ").append(length2 + 8).append("event bytes: ").append(com.xunlei.XLStat.j.e.a(this.m)).toString());
        return this.m;
    }
}
