package org.eclipse.paho.client.mqttv3;

import org.eclipse.paho.client.mqttv3.internal.Token;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

public class MqttToken implements IMqttToken {
    public Token internalTok;

    public MqttToken() {
        this.internalTok = null;
    }

    public MqttToken(String str) {
        this.internalTok = null;
        this.internalTok = new Token(str);
    }

    public MqttException getException() {
        return this.internalTok.getException();
    }

    public boolean isComplete() {
        return this.internalTok.isComplete();
    }

    public void setActionCallback(IMqttActionListener iMqttActionListener) {
        this.internalTok.setActionCallback(iMqttActionListener);
    }

    public IMqttActionListener getActionCallback() {
        return this.internalTok.getActionCallback();
    }

    public void waitForCompletion() throws MqttException {
        this.internalTok.waitForCompletion(-1);
    }

    public void waitForCompletion(long j) throws MqttException {
        this.internalTok.waitForCompletion(j);
    }

    public IMqttAsyncClient getClient() {
        return this.internalTok.getClient();
    }

    public String[] getTopics() {
        return this.internalTok.getTopics();
    }

    public Object getUserContext() {
        return this.internalTok.getUserContext();
    }

    public void setUserContext(Object obj) {
        this.internalTok.setUserContext(obj);
    }

    public int getMessageId() {
        return this.internalTok.getMessageID();
    }

    public int[] getGrantedQos() {
        return this.internalTok.getGrantedQos();
    }

    public boolean getSessionPresent() {
        return this.internalTok.getSessionPresent();
    }

    public MqttWireMessage getResponse() {
        return this.internalTok.getResponse();
    }
}
