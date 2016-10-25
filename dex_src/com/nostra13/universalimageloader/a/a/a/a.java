package com.nostra13.universalimageloader.a.a.a;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import com.nostra13.universalimageloader.b.b;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

// compiled from: BaseDiskCache.java
public abstract class a implements com.nostra13.universalimageloader.a.a.a {
    public static final CompressFormat a;
    protected final File b;
    protected final File c;
    protected final com.nostra13.universalimageloader.a.a.b.a d;
    protected int e;
    protected CompressFormat f;
    protected int g;

    static {
        a = CompressFormat.PNG;
    }

    public a(File file, File file2, com.nostra13.universalimageloader.a.a.b.a aVar) {
        this.e = 32768;
        this.f = a;
        this.g = 100;
        if (file == null) {
            throw new IllegalArgumentException("cacheDir argument must be not null");
        } else if (aVar == null) {
            throw new IllegalArgumentException("fileNameGenerator argument must be not null");
        } else {
            this.b = file;
            this.c = file2;
            this.d = aVar;
        }
    }

    public final File a(String str) {
        return b(str);
    }

    public final boolean a(String str, InputStream inputStream, com.nostra13.universalimageloader.b.b.a aVar) throws IOException {
        File b = b(str);
        File file = new File(b.getAbsolutePath() + ".tmp");
        try {
            Closeable bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), this.e);
            boolean a = b.a(inputStream, bufferedOutputStream, aVar, this.e);
            try {
                b.a(bufferedOutputStream);
                if (a && !file.renameTo(b)) {
                    a = false;
                }
                if (!a) {
                    file.delete();
                }
                return a;
            } catch (Throwable th) {
                Throwable th2 = th;
                if (a && !file.renameTo(b)) {
                    a = false;
                }
                if (!a) {
                    file.delete();
                }
                throw th2;
            }
        } catch (Throwable th3) {
            th2 = th3;
            a = false;
        }
    }

    public final boolean a(String str, Bitmap bitmap) throws IOException {
        File b = b(str);
        File file = new File(b.getAbsolutePath() + ".tmp");
        Closeable bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), this.e);
        boolean compress = bitmap.compress(this.f, this.g, bufferedOutputStream);
        b.a(bufferedOutputStream);
        if (compress && !file.renameTo(b)) {
            compress = false;
        }
        if (!compress) {
            file.delete();
        }
        bitmap.recycle();
        return compress;
    }

    public final void a() {
        File[] listFiles = this.b.listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                file.delete();
            }
        }
    }

    private File b(String str) {
        String a = this.d.a(str);
        File file = this.b;
        if (!(this.b.exists() || this.b.mkdirs() || this.c == null)) {
            if (this.c.exists() || this.c.mkdirs()) {
                file = this.c;
            }
        }
        return new File(file, a);
    }
}
