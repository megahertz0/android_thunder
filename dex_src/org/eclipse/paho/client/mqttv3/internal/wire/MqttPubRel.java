package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import org.android.spdy.SpdyProtocol;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttPubRel extends MqttPersistableWireMessage {
    public MqttPubRel(MqttPubRec mqttPubRec) {
        super((byte) 6);
        setMessageId(mqttPubRec.getMessageId());
    }

    public MqttPubRel(byte b, byte[] bArr) throws IOException {
        super((byte) 6);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        this.msgId = dataInputStream.readUnsignedShort();
        dataInputStream.close();
    }

    protected byte[] getVariableHeader() throws MqttException {
        return encodeMessageId();
    }

    protected byte getMessageInfo() {
        return (byte) ((this.duplicate ? SpdyProtocol.PUBKEY_SEQ_ADASH : 0) | 2);
    }

    public String toString() {
        return new StringBuffer(String.valueOf(super.toString())).append(" msgId ").append(this.msgId).toString();
    }
}
