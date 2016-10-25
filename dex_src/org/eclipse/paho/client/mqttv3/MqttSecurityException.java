package org.eclipse.paho.client.mqttv3;

public class MqttSecurityException extends MqttException {
    private static final long serialVersionUID = 300;

    public MqttSecurityException(int i) {
        super(i);
    }

    public MqttSecurityException(Throwable th) {
        super(th);
    }

    public MqttSecurityException(int i, Throwable th) {
        super(i, th);
    }
}
