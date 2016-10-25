package com.xunlei.XLStat;

import com.xunlei.XLStat.b.c;
import com.xunlei.XLStat.j.b;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.Arrays;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class f {
    public int a;
    public int b;
    public int c;
    public int d;
    public String e;
    public long f;
    public int g;
    public int h;
    private byte[] i;

    public f() {
        this.a = -1;
        this.b = -1;
        this.c = -1;
        this.d = -1;
        this.e = BuildConfig.VERSION_NAME;
        this.f = -1;
        this.g = -1;
        this.h = -1;
        this.i = null;
        this.a = -1;
        this.b = -1;
        this.c = -1;
        this.d = -1;
        this.e = BuildConfig.VERSION_NAME;
        this.f = c.e();
        this.g = -1;
        this.h = -2;
        this.i = new byte[0];
    }

    public f(int i, int i2, int i3, int i4, String str, int i5) {
        this.a = -1;
        this.b = -1;
        this.c = -1;
        this.d = -1;
        this.e = BuildConfig.VERSION_NAME;
        this.f = -1;
        this.g = -1;
        this.h = -1;
        this.i = null;
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = str;
        this.f = c.e();
        this.g = -1;
        this.h = i5;
        this.i = new byte[0];
    }

    public byte[] a() {
        Object obj;
        Object bytes;
        Object obj2 = new Object[2];
        System.arraycopy(b.a(this.a), 0, obj2, 0, SimpleLog.LOG_LEVEL_DEBUG);
        Object obj3 = new Object[4];
        System.arraycopy(b.a(this.b), 0, obj3, 0, MqttConnectOptions.MQTT_VERSION_3_1_1);
        Object obj4 = new Object[4];
        System.arraycopy(b.a(this.c), 0, obj4, 0, MqttConnectOptions.MQTT_VERSION_3_1_1);
        Object obj5 = new Object[4];
        System.arraycopy(b.a(this.d), 0, obj5, 0, MqttConnectOptions.MQTT_VERSION_3_1_1);
        if (this.e == null || BuildConfig.VERSION_NAME.equals(this.e)) {
            obj = new Object[4];
            Arrays.fill(obj, (byte) 0);
        } else {
            bytes = this.e.getBytes();
            Object a = b.a(bytes.length);
            obj = new Object[(bytes.length + a.length)];
            System.arraycopy(a, 0, obj, 0, a.length);
            System.arraycopy(bytes, 0, obj, MqttConnectOptions.MQTT_VERSION_3_1_1, bytes.length);
        }
        bytes = new Object[8];
        System.arraycopy(b.a(this.f), 0, bytes, 0, SpdyProtocol.PUBKEY_SEQ_ADASH);
        int length = (obj.length + 14) + 8;
        this.i = new byte[(length + 4)];
        System.arraycopy(b.a(length), 0, this.i, 0, b.a(length).length);
        length = b.a(length).length + 0;
        System.arraycopy(obj2, 0, this.i, length, SimpleLog.LOG_LEVEL_DEBUG);
        int i = length + 2;
        System.arraycopy(obj3, 0, this.i, i, MqttConnectOptions.MQTT_VERSION_3_1_1);
        i += 4;
        System.arraycopy(obj4, 0, this.i, i, MqttConnectOptions.MQTT_VERSION_3_1_1);
        i += 4;
        System.arraycopy(obj5, 0, this.i, i, MqttConnectOptions.MQTT_VERSION_3_1_1);
        i += 4;
        System.arraycopy(obj, 0, this.i, i, obj.length);
        System.arraycopy(bytes, 0, this.i, obj.length + i, SpdyProtocol.PUBKEY_SEQ_ADASH);
        return this.i;
    }
}
