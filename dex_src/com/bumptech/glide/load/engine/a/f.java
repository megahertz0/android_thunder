package com.bumptech.glide.load.engine.a;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Build.VERSION;
import android.util.Log;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

// compiled from: LruBitmapPool.java
public final class f implements c {
    private static final Config a;
    private final g b;
    private final Set<Config> c;
    private final int d;
    private final a e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;

    // compiled from: LruBitmapPool.java
    private static interface a {
    }

    // compiled from: LruBitmapPool.java
    private static class b implements a {
        private b() {
        }
    }

    static {
        a = Config.ARGB_8888;
    }

    private f(int i, g gVar, Set<Config> set) {
        this.d = i;
        this.f = i;
        this.b = gVar;
        this.c = set;
        this.e = new b();
    }

    public f(int i) {
        g iVar;
        if (VERSION.SDK_INT >= 19) {
            iVar = new i();
        } else {
            iVar = new a();
        }
        Set hashSet = new HashSet();
        hashSet.addAll(Arrays.asList(Config.values()));
        if (VERSION.SDK_INT >= 19) {
            hashSet.add(null);
        }
        this(i, iVar, Collections.unmodifiableSet(hashSet));
    }

    public final synchronized boolean a(Bitmap bitmap) {
        boolean z;
        if (bitmap == null) {
            throw new NullPointerException("Bitmap must not be null");
        } else if (bitmap.isMutable() && this.b.c(bitmap) <= this.f && this.c.contains(bitmap.getConfig())) {
            int c = this.b.c(bitmap);
            this.b.a(bitmap);
            this.j++;
            this.g = c + this.g;
            if (Log.isLoggable("LruBitmapPool", XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                new StringBuilder("Put bitmap in pool=").append(this.b.b(bitmap));
            }
            b();
            b(this.f);
            z = true;
        } else {
            if (Log.isLoggable("LruBitmapPool", XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                new StringBuilder("Reject bitmap from pool, bitmap: ").append(this.b.b(bitmap)).append(", is mutable: ").append(bitmap.isMutable()).append(", is allowed config: ").append(this.c.contains(bitmap.getConfig()));
            }
            z = false;
        }
        return z;
    }

    public final synchronized Bitmap a(int i, int i2, Config config) {
        Bitmap b;
        b = b(i, i2, config);
        if (b != null) {
            b.eraseColor(0);
        }
        return b;
    }

    @TargetApi(12)
    public final synchronized Bitmap b(int i, int i2, Config config) {
        Bitmap a;
        a = this.b.a(i, i2, config != null ? config : a);
        if (a == null) {
            if (Log.isLoggable("LruBitmapPool", XZBDevice.DOWNLOAD_LIST_FAILED)) {
                new StringBuilder("Missing bitmap=").append(this.b.b(i, i2, config));
            }
            this.i++;
        } else {
            this.h++;
            this.g -= this.b.c(a);
            if (VERSION.SDK_INT >= 12) {
                a.setHasAlpha(true);
            }
        }
        if (Log.isLoggable("LruBitmapPool", XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
            new StringBuilder("Get bitmap=").append(this.b.b(i, i2, config));
        }
        b();
        return a;
    }

    public final void a() {
        b(0);
    }

    @SuppressLint({"InlinedApi"})
    public final void a(int i) {
        if (i >= 60) {
            b(0);
        } else if (i >= 40) {
            b(this.f / 2);
        }
    }

    private synchronized void b(int i) {
        while (this.g > i) {
            Bitmap a = this.b.a();
            if (a == null) {
                if (Log.isLoggable("LruBitmapPool", XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED)) {
                    c();
                }
                this.g = 0;
            } else {
                this.g -= this.b.c(a);
                a.recycle();
                this.k++;
                if (Log.isLoggable("LruBitmapPool", XZBDevice.DOWNLOAD_LIST_FAILED)) {
                    new StringBuilder("Evicting bitmap=").append(this.b.b(a));
                }
                b();
            }
        }
    }

    private void b() {
        if (Log.isLoggable("LruBitmapPool", XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
            c();
        }
    }

    private void c() {
        new StringBuilder("Hits=").append(this.h).append(", misses=").append(this.i).append(", puts=").append(this.j).append(", evictions=").append(this.k).append(", currentSize=").append(this.g).append(", maxSize=").append(this.f).append("\nStrategy=").append(this.b);
    }
}
