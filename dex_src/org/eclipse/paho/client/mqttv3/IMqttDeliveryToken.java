package org.eclipse.paho.client.mqttv3;

public interface IMqttDeliveryToken extends IMqttToken {
    MqttMessage getMessage() throws MqttException;
}
