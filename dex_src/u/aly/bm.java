package u.aly;

import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: SDKType.java
public enum bm {
    ANDROID(0),
    IOS(1),
    WINDOWS_PHONE(2),
    WINDOWS_RT(3);
    private final int e;

    static {
        a = new bm("ANDROID", 0, 0);
        b = new bm("IOS", 1, 1);
        c = new bm("WINDOWS_PHONE", 2, 2);
        d = new bm("WINDOWS_RT", 3, 3);
        f = new bm[]{a, b, c, d};
    }

    private bm(int i) {
        this.e = i;
    }

    public final int a() {
        return this.e;
    }

    public static bm a(int i) {
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
