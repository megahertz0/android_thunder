package com.xunlei.XLStat;

import com.xunlei.XLStat.j.b;
import com.xunlei.xiazaibao.BuildConfig;
import java.net.URLEncoder;
import java.util.Arrays;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class g {
    public int a;
    public int b;
    public long c;
    public String d;
    public short e;
    public long f;
    public String g;
    public int h;
    private byte[] i;

    public byte[] a() {
        if (this.i != null) {
            return this.i;
        }
        Object obj;
        Object bytes;
        if (this.d == null || BuildConfig.VERSION_NAME.equals(this.d)) {
            obj = new Object[4];
            Arrays.fill(obj, (byte) 0);
        } else {
            bytes = URLEncoder.encode(this.d).getBytes();
            obj = new Object[(bytes.length + 4)];
            System.arraycopy(b.a(bytes.length), 0, obj, 0, MqttConnectOptions.MQTT_VERSION_3_1_1);
            System.arraycopy(bytes, 0, obj, MqttConnectOptions.MQTT_VERSION_3_1_1, bytes.length);
        }
        Object obj2 = new Object[2];
        System.arraycopy(b.a((long) this.e), 0, obj2, 0, SimpleLog.LOG_LEVEL_DEBUG);
        Object obj3 = new Object[8];
        System.arraycopy(b.a(this.f), 0, obj3, 0, SpdyProtocol.PUBKEY_SEQ_ADASH);
        if (this.g == null || BuildConfig.VERSION_NAME.equals(this.g)) {
            bytes = new Object[4];
            Arrays.fill(bytes, (byte) 0);
        } else {
            Object bytes2 = URLEncoder.encode(this.g).getBytes();
            Object a = b.a(bytes2.length);
            bytes = new Object[(bytes2.length + a.length)];
            System.arraycopy(a, 0, bytes, 0, a.length);
            System.arraycopy(bytes2, 0, bytes, MqttConnectOptions.MQTT_VERSION_3_1_1, bytes2.length);
        }
        int length = ((obj.length + 2) + 8) + bytes.length;
        this.i = new byte[(length + 4)];
        System.arraycopy(b.a(length), 0, this.i, 0, MqttConnectOptions.MQTT_VERSION_3_1_1);
        System.arraycopy(obj, 0, this.i, MqttConnectOptions.MQTT_VERSION_3_1_1, obj.length);
        int length2 = obj.length + 4;
        System.arraycopy(obj2, 0, this.i, length2, SimpleLog.LOG_LEVEL_DEBUG);
        length2 += 2;
        System.arraycopy(obj3, 0, this.i, length2, SpdyProtocol.PUBKEY_SEQ_ADASH);
        System.arraycopy(bytes, 0, this.i, length2 + 8, bytes.length);
        return this.i;
    }
}
