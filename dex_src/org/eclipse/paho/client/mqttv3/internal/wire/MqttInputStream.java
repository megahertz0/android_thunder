package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.ClientState;
import org.eclipse.paho.client.mqttv3.internal.ExceptionHelper;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class MqttInputStream extends InputStream {
    private static final String CLASS_NAME;
    static Class class$0;
    private static final Logger log;
    private ClientState clientState;
    private DataInputStream in;

    static {
        Class cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.wire.MqttInputStream");
                class$0 = cls;
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        CLASS_NAME = cls.getName();
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, CLASS_NAME);
    }

    public MqttInputStream(ClientState clientState, InputStream inputStream) {
        this.clientState = null;
        this.clientState = clientState;
        this.in = new DataInputStream(inputStream);
    }

    public int read() throws IOException {
        return this.in.read();
    }

    public int available() throws IOException {
        return this.in.available();
    }

    public void close() throws IOException {
        this.in.close();
    }

    public MqttWireMessage readMqttWireMessage() throws IOException, MqttException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte readByte = this.in.readByte();
        this.clientState.notifyReceivedBytes(1);
        byte b = (byte) ((readByte >>> 4) & 15);
        if (b <= null || b > 14) {
            throw ExceptionHelper.createMqttException(32108);
        }
        long value = MqttWireMessage.readMBI(this.in).getValue();
        byteArrayOutputStream.write(readByte);
        byteArrayOutputStream.write(MqttWireMessage.encodeMBI(value));
        byte[] bArr = new byte[((int) (value + ((long) byteArrayOutputStream.size())))];
        readFully(bArr, byteArrayOutputStream.size(), bArr.length - byteArrayOutputStream.size());
        Object toByteArray = byteArrayOutputStream.toByteArray();
        System.arraycopy(toByteArray, 0, bArr, 0, toByteArray.length);
        log.fine(CLASS_NAME, "readMqttWireMessage", "501", new Object[]{MqttWireMessage.createWireMessage(bArr)});
        return MqttWireMessage.createWireMessage(bArr);
    }

    private void readFully(byte[] bArr, int i, int i2) throws IOException {
        if (i2 < 0) {
            throw new IndexOutOfBoundsException();
        }
        int i3 = 0;
        while (i3 < i2) {
            int read = this.in.read(bArr, i + i3, i2 - i3);
            this.clientState.notifyReceivedBytes(read);
            if (read < 0) {
                throw new EOFException();
            }
            i3 += read;
        }
    }
}
