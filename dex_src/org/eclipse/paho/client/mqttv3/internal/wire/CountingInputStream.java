package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.IOException;
import java.io.InputStream;

public class CountingInputStream extends InputStream {
    private int counter;
    private InputStream in;

    public CountingInputStream(InputStream inputStream) {
        this.in = inputStream;
        this.counter = 0;
    }

    public int read() throws IOException {
        int read = this.in.read();
        if (read != -1) {
            this.counter++;
        }
        return read;
    }

    public int getCounter() {
        return this.counter;
    }

    public void resetCounter() {
        this.counter = 0;
    }
}
