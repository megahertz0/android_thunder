package com.xunlei.XLStat.f;

import com.xunlei.XLStat.XLStatLog.XLStatLog;
import com.xunlei.XLStat.b.c;
import com.xunlei.XLStat.j.e;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import java.util.Arrays;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class b {
    public int a;
    public int b;
    public int c;
    public int d;
    public String e;
    public long f;
    public int g;
    public int h;
    private byte[] i;

    public b() {
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = BuildConfig.VERSION_NAME;
        this.f = -1;
        this.g = -1;
        this.h = -2;
        this.i = null;
    }

    public b(int i, int i2, int i3, int i4, String str, int i5) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = str;
        this.f = c.e();
        this.h = i5;
        this.i = null;
    }

    public byte[] a() {
        if (this.i != null && this.i.length > 0) {
            return this.i;
        }
        Object obj;
        Object bytes;
        Object obj2 = new Object[2];
        System.arraycopy(com.xunlei.XLStat.j.b.a(this.a), 0, obj2, 0, SimpleLog.LOG_LEVEL_DEBUG);
        Object obj3 = new Object[4];
        System.arraycopy(com.xunlei.XLStat.j.b.a(this.d), 0, obj3, 0, MqttConnectOptions.MQTT_VERSION_3_1_1);
        Object obj4 = new Object[4];
        System.arraycopy(com.xunlei.XLStat.j.b.a(this.c), 0, obj4, 0, MqttConnectOptions.MQTT_VERSION_3_1_1);
        Object obj5 = new Object[4];
        System.arraycopy(com.xunlei.XLStat.j.b.a(this.b), 0, obj5, 0, MqttConnectOptions.MQTT_VERSION_3_1_1);
        if (this.e == null || BuildConfig.VERSION_NAME.equals(this.e)) {
            obj = new Object[4];
            Arrays.fill(obj, (byte) 0);
        } else {
            bytes = this.e.getBytes();
            Object a = com.xunlei.XLStat.j.b.a(bytes.length);
            obj = new Object[(bytes.length + a.length)];
            System.arraycopy(a, 0, obj, 0, a.length);
            System.arraycopy(bytes, 0, obj, MqttConnectOptions.MQTT_VERSION_3_1_1, bytes.length);
        }
        bytes = new Object[8];
        System.arraycopy(com.xunlei.XLStat.j.b.a(this.f), 0, bytes, 0, SpdyProtocol.PUBKEY_SEQ_ADASH);
        int length = (obj.length + 14) + 8;
        this.i = new byte[(length + 4)];
        System.arraycopy(com.xunlei.XLStat.j.b.a(length), 0, this.i, 0, com.xunlei.XLStat.j.b.a(length).length);
        System.arraycopy(obj2, 0, this.i, MqttConnectOptions.MQTT_VERSION_3_1_1, SimpleLog.LOG_LEVEL_DEBUG);
        System.arraycopy(obj3, 0, this.i, SimpleLog.LOG_LEVEL_FATAL, MqttConnectOptions.MQTT_VERSION_3_1_1);
        System.arraycopy(obj4, 0, this.i, SpdyProtocol.PUBKEY_SEQ_OPEN, MqttConnectOptions.MQTT_VERSION_3_1_1);
        System.arraycopy(obj5, 0, this.i, R.styleable.Toolbar_titleMarginEnd, MqttConnectOptions.MQTT_VERSION_3_1_1);
        System.arraycopy(obj, 0, this.i, R.styleable.Toolbar_collapseIcon, obj.length);
        int length2 = obj.length + 18;
        System.arraycopy(bytes, 0, this.i, length2, SpdyProtocol.PUBKEY_SEQ_ADASH);
        XLStatLog.d("XLStatContext", "getBytes", new StringBuilder("totalLen: ").append(length).append("  templen: ").append(length2 + 8).append("\ncontext bytes: ").append(e.a(this.i)).toString());
        return this.i;
    }
}
