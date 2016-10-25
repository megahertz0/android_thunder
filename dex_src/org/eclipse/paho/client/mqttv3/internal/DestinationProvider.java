package org.eclipse.paho.client.mqttv3.internal;

import org.eclipse.paho.client.mqttv3.MqttTopic;

public interface DestinationProvider {
    MqttTopic getTopic(String str);
}
