package com.bumptech.glide.load.b;

import com.bumptech.glide.load.a;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

// compiled from: StreamEncoder.java
public final class p implements a<InputStream> {
    private static boolean a(InputStream inputStream, OutputStream outputStream) {
        byte[] b = com.bumptech.glide.h.a.a().b();
        while (true) {
            try {
                int read = inputStream.read(b);
                if (read != -1) {
                    outputStream.write(b, 0, read);
                } else {
                    com.bumptech.glide.h.a.a().a(b);
                    return true;
                }
            } catch (IOException e) {
                com.bumptech.glide.h.a.a().a(b);
                return false;
            }
        }
    }

    public final String a() {
        return com.umeng.a.d;
    }
}
