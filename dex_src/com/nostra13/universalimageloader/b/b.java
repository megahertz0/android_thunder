package com.nostra13.universalimageloader.b;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

// compiled from: IoUtils.java
public final class b {

    // compiled from: IoUtils.java
    public static interface a {
        boolean a(int i, int i2);
    }

    public static boolean a(InputStream inputStream, OutputStream outputStream, a aVar, int i) throws IOException {
        int available = inputStream.available();
        if (available <= 0) {
            available = 512000;
        }
        byte[] bArr = new byte[i];
        if (a(aVar, 0, available)) {
            return false;
        }
        int i2 = 0;
        do {
            int read = inputStream.read(bArr, 0, i);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
                i2 += read;
            } else {
                outputStream.flush();
                return true;
            }
        } while (!a(aVar, i2, available));
        return false;
    }

    private static boolean a(a aVar, int i, int i2) {
        return (aVar == null || aVar.a(i, i2) || (i * 100) / i2 >= 75) ? false : true;
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }
}
