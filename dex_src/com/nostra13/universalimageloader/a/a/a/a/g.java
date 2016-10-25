package com.nostra13.universalimageloader.a.a.a.a;

import com.sina.weibo.sdk.component.GameManager;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

// compiled from: Util.java
final class g {
    static final Charset a;
    static final Charset b;

    static {
        a = Charset.forName("US-ASCII");
        b = Charset.forName(GameManager.DEFAULT_CHARSET);
    }

    static void a(File file) throws IOException {
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            throw new IOException(new StringBuilder("not a readable directory: ").append(file).toString());
        }
        int length = listFiles.length;
        int i = 0;
        while (i < length) {
            File file2 = listFiles[i];
            if (file2.isDirectory()) {
                a(file2);
            }
            if (file2.delete()) {
                i++;
            } else {
                throw new IOException(new StringBuilder("failed to delete file: ").append(file2).toString());
            }
        }
    }

    static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }
}
