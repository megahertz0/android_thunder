package com.xunlei.downloadprovider.download.tasklist.list.a.a;

import android.content.Context;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: LoaderFactory.java
public final class n {
    public static b a(Context context, int i, int i2) {
        switch (i) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                return new j(context, i2);
            case SimpleLog.LOG_LEVEL_TRACE:
                return new q(context, i2);
            case SimpleLog.LOG_LEVEL_DEBUG:
                return new c(context, i2);
            default:
                return null;
        }
    }
}
