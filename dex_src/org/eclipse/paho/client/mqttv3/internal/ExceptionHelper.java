package org.eclipse.paho.client.mqttv3.internal;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

public class ExceptionHelper {
    public static MqttException createMqttException(int i) {
        return (i == 4 || i == 5) ? new MqttSecurityException(i) : new MqttException(i);
    }

    public static MqttException createMqttException(Throwable th) {
        return th.getClass().getName().equals("java.security.GeneralSecurityException") ? new MqttSecurityException(th) : new MqttException(th);
    }

    public static boolean isClassAvailable(String str) {
        boolean z = false;
        try {
            Class.forName(str);
            z = true;
            return true;
        } catch (ClassNotFoundException e) {
            return z;
        }
    }

    private ExceptionHelper() {
    }
}
