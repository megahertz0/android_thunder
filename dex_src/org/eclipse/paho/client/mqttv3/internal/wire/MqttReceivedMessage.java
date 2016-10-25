package org.eclipse.paho.client.mqttv3.internal.wire;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttReceivedMessage extends MqttMessage {
    private int messageId;

    public void setMessageId(int i) {
        this.messageId = i;
    }

    public int getMessageId() {
        return this.messageId;
    }

    public void setDuplicate(boolean z) {
        super.setDuplicate(z);
    }
}
