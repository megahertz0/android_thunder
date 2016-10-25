package com.bumptech.glide.load.resource.b;

import com.bumptech.glide.load.d;
import com.bumptech.glide.load.engine.j;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

// compiled from: FileToStreamDecoder.java
public final class c<T> implements d<File, T> {
    private static final a a;
    private d<InputStream, T> b;
    private final a c;

    // compiled from: FileToStreamDecoder.java
    static class a {
        a() {
        }
    }

    static {
        a = new a();
    }

    public c(d<InputStream, T> dVar) {
        this(dVar, a);
    }

    private c(d<InputStream, T> dVar, a aVar) {
        this.b = dVar;
        this.c = aVar;
    }

    private j<T> a(File file, int i, int i2) throws IOException {
        try {
            InputStream fileInputStream = new FileInputStream(file);
            try {
                j<T> a = this.b.a(fileInputStream, i, i2);
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                }
                return a;
            } catch (Throwable th) {
                Throwable th2 = th;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th2;
            }
        } catch (Throwable th3) {
            th2 = th3;
            fileInputStream = null;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e2) {
                }
            }
            throw th2;
        }
    }

    public final String a() {
        return com.umeng.a.d;
    }
}
