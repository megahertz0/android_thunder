package com.xunlei.common.lixian.a;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public final class e extends OutputStream {
    private ByteArrayOutputStream a;
    private byte[] b;

    public e() {
        this.a = new ByteArrayOutputStream();
        this.b = new byte[8];
    }

    private void a(e eVar) {
        a(eVar.a.size());
        this.a.write(eVar.a.toByteArray());
    }

    private void a(e[] eVarArr) {
        a(eVarArr.length);
        for (e eVar : eVarArr) {
            a(eVar);
        }
    }

    private void b(String str) {
        a(str);
    }

    public final void a(int i) {
        Arrays.fill(this.b, (byte) 0);
        for (int i2 = 0; i2 < 4; i2++) {
            this.b[i2] = (byte) (i >>> (i2 << 3));
        }
        this.a.write(this.b, 0, MqttConnectOptions.MQTT_VERSION_3_1_1);
    }

    public final void a(long j) {
        Arrays.fill(this.b, (byte) 0);
        for (int i = 0; i < 8; i++) {
            this.b[i] = (byte) ((int) (j >>> (i << 3)));
        }
        this.a.write(this.b, 0, SpdyProtocol.PUBKEY_SEQ_ADASH);
    }

    public final void a(String str) {
        byte[] bytes = str.getBytes();
        a(bytes.length);
        this.a.write(bytes);
    }

    public final void a(String str, String str2) {
        byte[] bytes = str.getBytes(str2);
        a(bytes.length);
        this.a.write(bytes);
    }

    public final void a(List list) {
        a(list.size());
        for (int i = 0; i < list.size(); i++) {
            a((e) list.get(i));
        }
    }

    public final byte[] a() {
        return this.a.toByteArray();
    }

    public final void b(int i) {
        Arrays.fill(this.b, (byte) 0);
        for (int i2 = 0; i2 < 2; i2++) {
            this.b[i2] = (byte) (i >>> (i2 << 3));
        }
        this.a.write(this.b, 0, SimpleLog.LOG_LEVEL_DEBUG);
    }

    public final void c(int i) {
        Arrays.fill(this.b, (byte) 0);
        for (int i2 = 0; i2 <= 0; i2++) {
            this.b[0] = (byte) i;
        }
        this.a.write(this.b, 0, 1);
    }

    public final void close() {
        super.close();
        this.a.close();
    }

    public final void flush() {
        this.a.flush();
    }

    public final void write(int i) {
        this.a.write(i);
    }
}
