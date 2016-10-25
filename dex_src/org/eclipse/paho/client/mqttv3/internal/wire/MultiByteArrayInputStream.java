package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.IOException;
import java.io.InputStream;

public class MultiByteArrayInputStream extends InputStream {
    private byte[] bytesA;
    private byte[] bytesB;
    private int lengthA;
    private int lengthB;
    private int offsetA;
    private int offsetB;
    private int pos;

    public MultiByteArrayInputStream(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        this.pos = 0;
        this.bytesA = bArr;
        this.bytesB = bArr2;
        this.offsetA = i;
        this.offsetB = i3;
        this.lengthA = i2;
        this.lengthB = i4;
    }

    public int read() throws IOException {
        int i;
        if (this.pos < this.lengthA) {
            i = this.bytesA[this.offsetA + this.pos];
        } else if (this.pos >= this.lengthA + this.lengthB) {
            return -1;
        } else {
            i = this.bytesB[(this.offsetB + this.pos) - this.lengthA];
        }
        if (i < 0) {
            i += 256;
        }
        this.pos++;
        return i;
    }
}
