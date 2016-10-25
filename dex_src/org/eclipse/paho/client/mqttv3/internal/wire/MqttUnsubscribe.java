package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.android.spdy.SpdyProtocol;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttUnsubscribe extends MqttWireMessage {
    private int count;
    private String[] names;

    public MqttUnsubscribe(String[] strArr) {
        super((byte) 10);
        this.names = strArr;
    }

    public MqttUnsubscribe(byte b, byte[] bArr) throws IOException {
        int i = 0;
        super((byte) 10);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        this.msgId = dataInputStream.readUnsignedShort();
        this.count = 0;
        this.names = new String[10];
        while (i == 0) {
            try {
                this.names[this.count] = decodeUTF8(dataInputStream);
            } catch (Exception e) {
                i = 1;
            }
        }
        dataInputStream.close();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(super.toString());
        stringBuffer.append(" names:[");
        for (int i = 0; i < this.count; i++) {
            if (i > 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(new StringBuffer("\"").append(this.names[i]).append("\"").toString());
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
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        for (int i = 0; i < this.names.length; i++) {
            encodeUTF8(dataOutputStream, this.names[i]);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public boolean isRetryable() {
        return true;
    }
}
