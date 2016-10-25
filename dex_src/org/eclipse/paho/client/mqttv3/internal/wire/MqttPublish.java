package org.eclipse.paho.client.mqttv3.internal.wire;

import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.xllib.R;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttPublish extends MqttPersistableWireMessage {
    private byte[] encodedPayload;
    private MqttMessage message;
    private String topicName;

    public MqttPublish(String str, MqttMessage mqttMessage) {
        super((byte) 3);
        this.encodedPayload = null;
        this.topicName = str;
        this.message = mqttMessage;
    }

    public MqttPublish(byte b, byte[] bArr) throws MqttException, IOException {
        super((byte) 3);
        this.encodedPayload = null;
        this.message = new MqttReceivedMessage();
        this.message.setQos((b >> 1) & 3);
        if ((b & 1) == 1) {
            this.message.setRetained(true);
        }
        if ((b & 8) == 8) {
            ((MqttReceivedMessage) this.message).setDuplicate(true);
        }
        InputStream countingInputStream = new CountingInputStream(new ByteArrayInputStream(bArr));
        DataInputStream dataInputStream = new DataInputStream(countingInputStream);
        this.topicName = decodeUTF8(dataInputStream);
        if (this.message.getQos() > 0) {
            this.msgId = dataInputStream.readUnsignedShort();
        }
        byte[] bArr2 = new byte[(bArr.length - countingInputStream.getCounter())];
        dataInputStream.readFully(bArr2);
        dataInputStream.close();
        this.message.setPayload(bArr2);
    }

    public String toString() {
        String toHexString;
        StringBuffer stringBuffer = new StringBuffer();
        byte[] payload = this.message.getPayload();
        int min = Math.min(payload.length, R.styleable.Toolbar_navigationIcon);
        for (int i = 0; i < min; i++) {
            toHexString = Integer.toHexString(payload[i]);
            if (toHexString.length() == 1) {
                toHexString = new StringBuffer("0").append(toHexString).toString();
            }
            stringBuffer.append(toHexString);
        }
        try {
            toHexString = new String(payload, 0, min, CharsetConvert.UTF_8);
        } catch (Exception e) {
            toHexString = "?";
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append(super.toString());
        stringBuffer2.append(" qos:").append(this.message.getQos());
        if (this.message.getQos() > 0) {
            stringBuffer2.append(" msgId:").append(this.msgId);
        }
        stringBuffer2.append(" retained:").append(this.message.isRetained());
        stringBuffer2.append(" dup:").append(this.duplicate);
        stringBuffer2.append(" topic:\"").append(this.topicName).append("\"");
        stringBuffer2.append(" payload:[hex:").append(stringBuffer);
        stringBuffer2.append(" utf8:\"").append(toHexString).append("\"");
        stringBuffer2.append(" length:").append(payload.length).append("]");
        return stringBuffer2.toString();
    }

    protected byte getMessageInfo() {
        byte qos = (byte) (this.message.getQos() << 1);
        if (this.message.isRetained()) {
            qos = (byte) (qos | 1);
        }
        return (this.message.isDuplicate() || this.duplicate) ? (byte) (qos | 8) : qos;
    }

    public String getTopicName() {
        return this.topicName;
    }

    public MqttMessage getMessage() {
        return this.message;
    }

    protected static byte[] encodePayload(MqttMessage mqttMessage) {
        return mqttMessage.getPayload();
    }

    public byte[] getPayload() throws MqttException {
        if (this.encodedPayload == null) {
            this.encodedPayload = encodePayload(this.message);
        }
        return this.encodedPayload;
    }

    public int getPayloadLength() {
        try {
            return getPayload().length;
        } catch (MqttException e) {
            return 0;
        }
    }

    public void setMessageId(int i) {
        super.setMessageId(i);
        if (this.message instanceof MqttReceivedMessage) {
            ((MqttReceivedMessage) this.message).setMessageId(i);
        }
    }

    protected byte[] getVariableHeader() throws MqttException {
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            encodeUTF8(dataOutputStream, this.topicName);
            if (this.message.getQos() > 0) {
                dataOutputStream.writeShort(this.msgId);
            }
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable e) {
            throw new MqttException(e);
        }
    }

    public boolean isMessageIdRequired() {
        return true;
    }
}
