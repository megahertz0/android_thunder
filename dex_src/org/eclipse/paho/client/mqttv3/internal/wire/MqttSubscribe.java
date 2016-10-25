package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.android.spdy.SpdyProtocol;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttSubscribe extends MqttWireMessage {
    private int count;
    private String[] names;
    private int[] qos;

    public MqttSubscribe(byte b, byte[] bArr) throws IOException {
        int i = 0;
        super((byte) 8);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        this.msgId = dataInputStream.readUnsignedShort();
        this.count = 0;
        this.names = new String[10];
        this.qos = new int[10];
        while (i == 0) {
            try {
                this.names[this.count] = decodeUTF8(dataInputStream);
                int[] iArr = this.qos;
                int i2 = this.count;
                this.count = i2 + 1;
                iArr[i2] = dataInputStream.readByte();
            } catch (Exception e) {
                i = 1;
            }
        }
        dataInputStream.close();
    }

    public MqttSubscribe(String[] strArr, int[] iArr) {
        super((byte) 8);
        this.names = strArr;
        this.qos = iArr;
        if (strArr.length != iArr.length) {
            throw new IllegalArgumentException();
        }
        for (int i : iArr) {
            MqttMessage.validateQos(i);
        }
    }

    public String toString() {
        int i = 0;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(super.toString());
        stringBuffer.append(" names:[");
        for (int i2 = 0; i2 < this.count; i2++) {
            if (i2 > 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append("\"").append(this.names[i2]).append("\"");
        }
        stringBuffer.append("] qos:[");
        while (i < this.count) {
            if (i > 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(this.qos[i]);
            i++;
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    protected byte getMessageInfo() {
        return (byte) ((this.duplicate ? SpdyProtocol.PUBKEY_SEQ_ADASH : 0) | 2);
    }

    protected byte[] getVariableHeader() throws MqttException {
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeShort(this.msgId);
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
            for (int i = 0; i < this.names.length; i++) {
                encodeUTF8(dataOutputStream, this.names[i]);
                dataOutputStream.writeByte(this.qos[i]);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable e) {
            throw new MqttException(e);
        }
    }

    public boolean isRetryable() {
        return true;
    }
}
