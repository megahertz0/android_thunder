package anetwork.channel.a;

import java.io.ByteArrayOutputStream;

// compiled from: Taobao
public final class b {
    public ByteArrayOutputStream a;

    public b(int i) {
        this.a = null;
        if (i <= 0) {
            i = 5120;
        }
        this.a = new ByteArrayOutputStream(i);
    }
}
