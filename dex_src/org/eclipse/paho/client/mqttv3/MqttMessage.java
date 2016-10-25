package org.eclipse.paho.client.mqttv3;

public class MqttMessage {
    private boolean dup;
    private boolean mutable;
    private byte[] payload;
    private int qos;
    private boolean retained;

    public static void validateQos(int i) {
        if (i < 0 || i > 2) {
            throw new IllegalArgumentException();
        }
    }

    public MqttMessage() {
        this.mutable = true;
        this.qos = 1;
        this.retained = false;
        this.dup = false;
        setPayload(new byte[0]);
    }

    public MqttMessage(byte[] bArr) {
        this.mutable = true;
        this.qos = 1;
        this.retained = false;
        this.dup = false;
        setPayload(bArr);
    }

    public byte[] getPayload() {
        return this.payload;
    }

    public void clearPayload() {
        checkMutable();
        this.payload = new byte[0];
    }

    public void setPayload(byte[] bArr) {
        checkMutable();
        if (bArr == null) {
            throw new NullPointerException();
        }
        this.payload = bArr;
    }

    public boolean isRetained() {
        return this.retained;
    }

    public void setRetained(boolean z) {
        checkMutable();
        this.retained = z;
    }

    public int getQos() {
        return this.qos;
    }

    public void setQos(int i) {
        checkMutable();
        validateQos(i);
        this.qos = i;
    }

    public String toString() {
        return new String(this.payload);
    }

    protected void setMutable(boolean z) {
        this.mutable = z;
    }

    protected void checkMutable() throws IllegalStateException {
        if (!this.mutable) {
            throw new IllegalStateException();
        }
    }

    public void setDuplicate(boolean z) {
        this.dup = z;
    }

    public boolean isDuplicate() {
        return this.dup;
    }
}
