package com.xunlei.xllib.b;

import com.xunlei.xiazaibao.BuildConfig;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

class c$a {
    private static final char[] c;
    private static final char[] d;
    private static final char[] e;
    private static final char[] f;
    final char[] a;
    final char[] b;

    static {
        c = new char[]{'\u96f6', '\u4e00', '\u4e8c', '\u4e09', '\u56db', '\u4e94', '\u516d', '\u4e03', '\u516b', '\u4e5d'};
        d = new char[]{'\u5341', '\u767e', '\u5343', '\u4e07', '\u4ebf', '\u5146', '\u4eac'};
        e = new char[]{'\u96f6', '\u58f9', '\u8d30', '\u53c1', '\u8086', '\u4f0d', '\u9646', '\u67d2', '\u634c', '\u7396'};
        f = new char[]{'\u62fe', '\u4f70', '\u4edf', '\u4e07', '\u4ebf', '\u5146', '\u4eac'};
    }

    public c$a() {
        this.a = c;
        this.b = d;
    }

    final String a(int i) {
        String str = BuildConfig.VERSION_NAME;
        if (i < 0) {
            i = -i;
        }
        int i2 = i % 10000;
        String valueOf = String.valueOf(this.b[0]);
        String valueOf2 = String.valueOf(this.b[1]);
        String valueOf3 = String.valueOf(this.b[2]);
        char[] cArr = this.a;
        String valueOf4 = String.valueOf(cArr[0]);
        if (i2 < 10) {
            return String.valueOf(cArr[i2]);
        }
        int i3;
        if (i2 < 100) {
            i3 = (i2 / 10) % 10;
            if (i3 == 1) {
                str = str + valueOf;
            } else if (i3 > 0) {
                str = str + String.valueOf(cArr[i3]) + valueOf;
            }
            return i2 % 10 > 0 ? str + String.valueOf(cArr[i2 % 10]) : str;
        } else {
            int[] iArr = new int[4];
            int i4 = i2;
            for (i3 = MqttConnectOptions.MQTT_VERSION_3_1; i3 >= 0; i3--) {
                iArr[i3] = i4 % 10;
                i4 /= 10;
            }
            if (iArr[0] > 0) {
                str = str + String.valueOf(cArr[iArr[0]]) + valueOf3;
            }
            i3 = i2 % 1000;
            if (i3 == 0) {
                return str;
            }
            if (i3 < 100) {
                str = str + valueOf4;
            }
            if (iArr[1] > 0) {
                str = str + String.valueOf(cArr[iArr[1]]) + valueOf2;
            }
            i3 %= 100;
            if (i3 == 0) {
                return str;
            }
            if (i3 < 10) {
                if (iArr[1] > 0) {
                    str = str + valueOf4;
                }
                return str + String.valueOf(cArr[iArr[3]]);
            }
            if (iArr[2] > 0) {
                str = str + String.valueOf(cArr[iArr[2]]) + valueOf;
            }
            return iArr[3] > 0 ? str + String.valueOf(cArr[iArr[3]]) : str;
        }
    }
}
