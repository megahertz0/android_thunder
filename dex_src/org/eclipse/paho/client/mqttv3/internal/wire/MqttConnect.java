package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttConnect extends MqttWireMessage {
    public static final String KEY = "Con";
    private int MqttVersion;
    private boolean cleanSession;
    private String clientId;
    private int keepAliveInterval;
    private char[] password;
    private String userName;
    private String willDestination;
    private MqttMessage willMessage;

    public MqttConnect(byte b, byte[] bArr) throws IOException, MqttException {
        super((byte) 1);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        decodeUTF8(dataInputStream);
        dataInputStream.readByte();
        dataInputStream.readByte();
        this.keepAliveInterval = dataInputStream.readUnsignedShort();
        this.clientId = decodeUTF8(dataInputStream);
        dataInputStream.close();
    }

    public MqttConnect(String str, int i, boolean z, int i2, String str2, char[] cArr, MqttMessage mqttMessage, String str3) {
        super((byte) 1);
        this.clientId = str;
        this.cleanSession = z;
        this.keepAliveInterval = i2;
        this.userName = str2;
        this.password = cArr;
        this.willMessage = mqttMessage;
        this.willDestination = str3;
        this.MqttVersion = i;
    }

    public String toString() {
        return new StringBuffer(String.valueOf(super.toString())).append(" clientId ").append(this.clientId).append(" keepAliveInterval ").append(this.keepAliveInterval).toString();
    }

    protected byte getMessageInfo() {
        return (byte) 0;
    }

    public boolean isCleanSession() {
        return this.cleanSession;
    }

    protected byte[] getVariableHeader() throws MqttException {
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            if (this.MqttVersion == 3) {
                encodeUTF8(dataOutputStream, "MQIsdp");
            } else if (this.MqttVersion == 4) {
                encodeUTF8(dataOutputStream, "MQTT");
            }
            dataOutputStream.write(this.MqttVersion);
            int i = 0;
            if (this.cleanSession) {
                i = SimpleLog.LOG_LEVEL_DEBUG;
            }
            if (this.willMessage != null) {
                i = (byte) (((byte) (i | 4)) | (this.willMessage.getQos() << 3));
                if (this.willMessage.isRetained()) {
                    i = (byte) (i | 32);
                }
            }
            if (this.userName != null) {
                i = (byte) (i | 128);
                if (this.password != null) {
                    i = (byte) (i | 64);
                }
            }
            dataOutputStream.write(i);
            dataOutputStream.writeShort(this.keepAliveInterval);
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable e) {
            throw new MqttException(e);
        }
    }

    public byte[] getPayload() throws MqttException {
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            encodeUTF8(dataOutputStream, this.clientId);
            if (this.willMessage != null) {
                encodeUTF8(dataOutputStream, this.willDestination);
                dataOutputStream.writeShort(this.willMessage.getPayload().length);
                dataOutputStream.write(this.willMessage.getPayload());
            }
            if (this.userName != null) {
                encodeUTF8(dataOutputStream, this.userName);
                if (this.password != null) {
                    encodeUTF8(dataOutputStream, new String(this.password));
                }
            }
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable e) {
            throw new MqttException(e);
        }
    }

    public boolean isMessageIdRequired() {
        return false;
    }

    public String getKey() {
        return KEY;
    }
}
