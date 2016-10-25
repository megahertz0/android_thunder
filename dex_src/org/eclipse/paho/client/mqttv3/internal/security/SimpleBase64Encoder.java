package org.eclipse.paho.client.mqttv3.internal.security;

import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class SimpleBase64Encoder {
    private static final char[] PWDCHARS_ARRAY;
    private static final String PWDCHARS_STRING = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    static {
        PWDCHARS_ARRAY = PWDCHARS_STRING.toCharArray();
    }

    public static String encode(byte[] bArr) {
        int length = bArr.length;
        StringBuffer stringBuffer = new StringBuffer(((length + 2) / 3) * 4);
        int i = 0;
        while (length >= 3) {
            stringBuffer.append(to64((long) ((((bArr[i] & 255) << 16) | ((bArr[i + 1] & 255) << 8)) | (bArr[i + 2] & 255)), MqttConnectOptions.MQTT_VERSION_3_1_1));
            i += 3;
            length -= 3;
        }
        if (length == 2) {
            stringBuffer.append(to64((long) (((bArr[i] & 255) << 8) | (bArr[i + 1] & 255)), MqttConnectOptions.MQTT_VERSION_3_1));
        }
        if (length == 1) {
            stringBuffer.append(to64((long) (bArr[i] & 255), SimpleLog.LOG_LEVEL_DEBUG));
        }
        return stringBuffer.toString();
    }

    public static byte[] decode(String str) {
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        byte[] bArr = new byte[((length * 3) / 4)];
        int i = 0;
        int i2 = 0;
        int i3 = length;
        while (i3 >= 4) {
            long from64 = from64(bytes, i2, MqttConnectOptions.MQTT_VERSION_3_1_1);
            i3 -= 4;
            i2 += 4;
            for (length = SimpleLog.LOG_LEVEL_DEBUG; length >= 0; length--) {
                bArr[i + length] = (byte) ((int) (255 & from64));
                from64 >>= 8;
            }
            i += 3;
        }
        if (i3 == 3) {
            from64 = from64(bytes, i2, MqttConnectOptions.MQTT_VERSION_3_1);
            for (length = 1; length >= 0; length--) {
                bArr[i + length] = (byte) ((int) (255 & from64));
                from64 >>= 8;
            }
        }
        if (i3 == 2) {
            bArr[i] = (byte) ((int) (from64(bytes, i2, SimpleLog.LOG_LEVEL_DEBUG) & 255));
        }
        return bArr;
    }

    private static final String to64(long j, int i) {
        StringBuffer stringBuffer = new StringBuffer(i);
        while (i > 0) {
            i--;
            stringBuffer.append(PWDCHARS_ARRAY[(int) (63 & j)]);
            j >>= 6;
        }
        return stringBuffer.toString();
    }

    private static final long from64(byte[] bArr, int i, int i2) {
        long j = null;
        long j2 = 0;
        while (i2 > 0) {
            long j3;
            i2--;
            int i3 = i + 1;
            byte b = bArr[i];
            if (b == 47) {
                j3 = 1;
            } else {
                j3 = 0;
            }
            if (b >= 48 && b <= 57) {
                j3 = (long) ((b + 2) - 48);
            }
            if (b >= 65 && b <= 90) {
                j3 = (long) ((b + 12) - 65);
            }
            if (b >= 97 && b <= 122) {
                j3 = (long) ((b + 38) - 97);
            }
            j2 += j3 << j;
            j += 6;
            i = i3;
        }
        return j2;
    }
}
