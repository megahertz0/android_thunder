package u.aly;

import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: AccessType.java
public enum ao {
    ACCESS_TYPE_UNKNOWN(0),
    ACCESS_TYPE_2G_3G(1),
    ACCESS_TYPE_WIFI(2),
    ACCESS_TYPE_ETHERNET(3);
    private final int e;

    static {
        a = new ao("ACCESS_TYPE_UNKNOWN", 0, 0);
        b = new ao("ACCESS_TYPE_2G_3G", 1, 1);
        c = new ao("ACCESS_TYPE_WIFI", 2, 2);
        d = new ao("ACCESS_TYPE_ETHERNET", 3, 3);
        f = new ao[]{a, b, c, d};
    }

    private ao(int i) {
        this.e = i;
    }

    public final int a() {
        return this.e;
    }

    public static ao a(int i) {
        switch (i) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                return a;
            case SimpleLog.LOG_LEVEL_TRACE:
                return b;
            case SimpleLog.LOG_LEVEL_DEBUG:
                return c;
            case MqttConnectOptions.MQTT_VERSION_3_1:
                return d;
            default:
                return null;
        }
    }
}
