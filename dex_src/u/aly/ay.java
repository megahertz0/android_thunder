package u.aly;

import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: Gender.java
public enum ay {
    MALE(0),
    FEMALE(1),
    UNKNOWN(2);
    private final int d;

    static {
        a = new ay("MALE", 0, 0);
        b = new ay("FEMALE", 1, 1);
        c = new ay("UNKNOWN", 2, 2);
        e = new ay[]{a, b, c};
    }

    private ay(int i) {
        this.d = i;
    }

    public final int a() {
        return this.d;
    }

    public static ay a(int i) {
        switch (i) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                return a;
            case SimpleLog.LOG_LEVEL_TRACE:
                return b;
            case SimpleLog.LOG_LEVEL_DEBUG:
                return c;
            default:
                return null;
        }
    }
}
