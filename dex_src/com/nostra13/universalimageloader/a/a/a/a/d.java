package com.nostra13.universalimageloader.a.a.a.a;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;
import com.nostra13.universalimageloader.a.a.a;
import com.nostra13.universalimageloader.b.b;
import com.nostra13.universalimageloader.b.c;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

// compiled from: LruDiskCache.java
public final class d implements a {
    public static final CompressFormat a;
    protected a b;
    protected final com.nostra13.universalimageloader.a.a.b.a c;
    protected int d;
    protected CompressFormat e;
    protected int f;
    private File g;

    static {
        a = CompressFormat.PNG;
    }

    public d(File file, File file2, com.nostra13.universalimageloader.a.a.b.a aVar, long j, int i) throws IOException {
        this.d = 32768;
        this.e = a;
        this.f = 100;
        if (file == null) {
            throw new IllegalArgumentException("cacheDir argument must be not null");
        } else if (j < 0) {
            throw new IllegalArgumentException("cacheMaxSize argument must be positive number");
        } else if (i < 0) {
            throw new IllegalArgumentException("cacheMaxFileCount argument must be positive number");
        } else if (aVar == null) {
            throw new IllegalArgumentException("fileNameGenerator argument must be not null");
        } else {
            long j2;
            if (j == 0) {
                j2 = Long.MAX_VALUE;
            } else {
                j2 = j;
            }
            int i2 = i == 0 ? InMobiClientPositioning.NO_REPEAT : i;
            this.g = file2;
            this.c = aVar;
            a(file, file2, j2, i2);
        }
    }

    private void a(File file, File file2, long j, int i) throws IOException {
        try {
            this.b = a.a(file, j, i);
        } catch (Throwable e) {
            c.a(e);
            if (file2 != null) {
                a(file2, null, j, i);
            }
            if (this.b == null) {
                throw e;
            }
        }
    }

    public final File a(String str) {
        File file = null;
        try {
            a.c a = this.b.a(b(str));
            if (a != null) {
                try {
                    file = a.a[0];
                } catch (IOException e) {
                    e = e;
                    c.a(e);
                    if (a != null) {
                        a.close();
                    }
                    return file;
                }
            }
            if (a != null) {
                a.close();
            }
        } catch (IOException e2) {
            e = e2;
            a = null;
            try {
                Throwable e3;
                c.a(e3);
                if (a != null) {
                    a.close();
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                if (a != null) {
                    a.close();
                }
                throw th2;
            }
            return file;
        } catch (Throwable e32) {
            a = null;
            th2 = e32;
            if (a != null) {
                a.close();
            }
            throw th2;
        }
        return file;
    }

    public final boolean a(String str, InputStream inputStream, b.a aVar) throws IOException {
        a.a b = this.b.b(b(str));
        if (b == null) {
            return false;
        }
        Closeable bufferedOutputStream = new BufferedOutputStream(b.a(), this.d);
        boolean a = b.a(inputStream, bufferedOutputStream, aVar, this.d);
        b.a(bufferedOutputStream);
        if (a) {
            b.b();
            return a;
        }
        b.c();
        return a;
    }

    public final boolean a(String str, Bitmap bitmap) throws IOException {
        a.a b = this.b.b(b(str));
        if (b == null) {
            return false;
        }
        Closeable bufferedOutputStream = new BufferedOutputStream(b.a(), this.d);
        boolean compress = bitmap.compress(this.e, this.f, bufferedOutputStream);
        b.a(bufferedOutputStream);
        if (compress) {
            b.b();
            return compress;
        }
        b.c();
        return compress;
    }

    public final void a() {
        try {
            this.b.c();
        } catch (Throwable e) {
            c.a(e);
        }
        try {
            a(this.b.b, this.g, this.b.a(), this.b.b());
        } catch (Throwable e2) {
            c.a(e2);
        }
    }

    private String b(String str) {
        return this.c.a(str);
    }
}
