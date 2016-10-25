package com.xiaomi.xmpush.thrift;

import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public enum d {
    INT(1),
    LONG(2),
    STRING(3),
    BOOLEAN(4);
    private final int e;

    static {
        a = new d("INT", 0, 1);
        b = new d("LONG", 1, 2);
        c = new d("STRING", 2, 3);
        d = new d("BOOLEAN", 3, 4);
        f = new d[]{a, b, c, d};
    }

    private d(int i) {
        this.e = i;
    }

    public static d a(int i) {
        switch (i) {
            case SimpleLog.LOG_LEVEL_TRACE:
                return a;
            case SimpleLog.LOG_LEVEL_DEBUG:
                return b;
            case MqttConnectOptions.MQTT_VERSION_3_1:
                return c;
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
                return d;
            default:
                return null;
        }
    }
}
