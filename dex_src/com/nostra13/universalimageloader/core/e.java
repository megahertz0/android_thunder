package com.nostra13.universalimageloader.core;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import com.nostra13.universalimageloader.core.e.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;
import org.android.spdy.SpdyAgent;

// compiled from: ImageLoaderConfiguration.java
public final class e {
    final Resources a;
    final int b;
    final int c;
    final int d;
    final int e;
    final a f;
    final Executor g;
    final Executor h;
    final boolean i;
    final boolean j;
    final int k;
    final int l;
    final QueueProcessingType m;
    public final com.nostra13.universalimageloader.a.b.a n;
    public final com.nostra13.universalimageloader.a.a.a o;
    final ImageDownloader p;
    final com.nostra13.universalimageloader.core.a.b q;
    public final c r;
    final ImageDownloader s;
    final ImageDownloader t;

    // compiled from: ImageLoaderConfiguration.java
    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[Scheme.values().length];
            try {
                a[Scheme.HTTP.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[Scheme.HTTPS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    // compiled from: ImageLoaderConfiguration.java
    public static class a {
        public static final QueueProcessingType a;
        public Executor b;
        public Executor c;
        public boolean d;
        public QueueProcessingType e;
        public int f;
        public com.nostra13.universalimageloader.a.b.a g;
        public c h;
        private Context i;
        private int j;
        private int k;
        private int l;
        private int m;
        private com.nostra13.universalimageloader.core.e.a n;
        private boolean o;
        private boolean p;
        private int q;
        private int r;
        private long s;
        private int t;
        private com.nostra13.universalimageloader.a.a.a u;
        private com.nostra13.universalimageloader.a.a.b.a v;
        private ImageDownloader w;
        private com.nostra13.universalimageloader.core.a.b x;
        private boolean y;

        static {
            a = QueueProcessingType.FIFO;
        }

        public a(Context context) {
            this.j = 0;
            this.k = 0;
            this.l = 0;
            this.m = 0;
            this.n = null;
            this.b = null;
            this.c = null;
            this.o = false;
            this.p = false;
            this.q = 3;
            this.r = 3;
            this.d = false;
            this.e = a;
            this.f = 0;
            this.s = 0;
            this.t = 0;
            this.g = null;
            this.u = null;
            this.v = null;
            this.w = null;
            this.h = null;
            this.y = false;
            this.i = context.getApplicationContext();
        }

        public final com.nostra13.universalimageloader.core.e.a a(int i) {
            if (!(this.b == null && this.c == null)) {
                com.nostra13.universalimageloader.b.c.c("threadPoolSize(), threadPriority() and tasksProcessingOrder() calls can overlap taskExecutor() and taskExecutorForCachedImages() calls.", new Object[0]);
            }
            if (i <= 0) {
                this.r = 1;
            } else if (i > 10) {
                this.r = 10;
            } else {
                this.r = i;
            }
            return this;
        }

        public final com.nostra13.universalimageloader.core.e.a a(com.nostra13.universalimageloader.a.a.b.a aVar) {
            if (this.u != null) {
                com.nostra13.universalimageloader.b.c.c("diskCache() and diskCacheFileNameGenerator() calls overlap each other", new Object[0]);
            }
            this.v = aVar;
            return this;
        }

        public final e a() {
            boolean z = true;
            if (this.b == null) {
                this.b = a.a(this.q, this.r, this.e);
            } else {
                this.o = true;
            }
            if (this.c == null) {
                this.c = a.a(this.q, this.r, this.e);
            } else {
                this.p = true;
            }
            if (this.u == null) {
                if (this.v == null) {
                    this.v = new com.nostra13.universalimageloader.a.a.b.b();
                }
                this.u = a.a(this.i, this.v, this.s, this.t);
            }
            if (this.g == null) {
                Context context = this.i;
                int i = this.f;
                if (i == 0) {
                    boolean z2;
                    ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
                    int memoryClass = activityManager.getMemoryClass();
                    if (VERSION.SDK_INT >= 11) {
                        z2 = true;
                    } else {
                        Object obj = (byte) 0;
                    }
                    if (z2) {
                        if ((context.getApplicationInfo().flags & 1048576) == 0) {
                            z = false;
                        }
                        if (z) {
                            i = activityManager.getLargeMemoryClass();
                            i = (i * 1048576) / 8;
                        }
                    }
                    i = memoryClass;
                    i = (i * 1048576) / 8;
                }
                this.g = new com.nostra13.universalimageloader.a.b.a.b(i);
            }
            if (this.d) {
                this.g = new com.nostra13.universalimageloader.a.b.a.a(this.g, new com.nostra13.universalimageloader.b.e());
            }
            if (this.w == null) {
                this.w = new com.nostra13.universalimageloader.core.download.a(this.i);
            }
            if (this.x == null) {
                this.x = new com.nostra13.universalimageloader.core.a.a(this.y);
            }
            if (this.h == null) {
                this.h = new com.nostra13.universalimageloader.core.c.a().b();
            }
            return new e();
        }
    }

    // compiled from: ImageLoaderConfiguration.java
    private static class b implements ImageDownloader {
        private final ImageDownloader a;

        public b(ImageDownloader imageDownloader) {
            this.a = imageDownloader;
        }

        public final InputStream a(String str, Object obj) throws IOException {
            switch (AnonymousClass_1.a[Scheme.ofUri(str).ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    throw new IllegalStateException();
                default:
                    return this.a.a(str, obj);
            }
        }
    }

    // compiled from: ImageLoaderConfiguration.java
    private static class c implements ImageDownloader {
        private final ImageDownloader a;

        public c(ImageDownloader imageDownloader) {
            this.a = imageDownloader;
        }

        public final InputStream a(String str, Object obj) throws IOException {
            InputStream a = this.a.a(str, obj);
            switch (AnonymousClass_1.a[Scheme.ofUri(str).ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    return new com.nostra13.universalimageloader.core.assist.b(a);
                default:
                    return a;
            }
        }
    }

    private e(a aVar) {
        this.a = a.a(aVar).getResources();
        this.b = a.b(aVar);
        this.c = a.c(aVar);
        this.d = a.d(aVar);
        this.e = a.e(aVar);
        this.f = a.f(aVar);
        this.g = a.g(aVar);
        this.h = a.h(aVar);
        this.k = a.i(aVar);
        this.l = a.j(aVar);
        this.m = a.k(aVar);
        this.o = a.l(aVar);
        this.n = a.m(aVar);
        this.r = a.n(aVar);
        this.p = a.o(aVar);
        this.q = a.p(aVar);
        this.i = a.q(aVar);
        this.j = a.r(aVar);
        this.s = new b(this.p);
        this.t = new c(this.p);
        com.nostra13.universalimageloader.b.c.a(a.s(aVar));
    }

    public static e a(Context context) {
        return new a(context).a();
    }

    final com.nostra13.universalimageloader.core.assist.c a() {
        DisplayMetrics displayMetrics = this.a.getDisplayMetrics();
        int i = this.b;
        if (i <= 0) {
            i = displayMetrics.widthPixels;
        }
        int i2 = this.c;
        if (i2 <= 0) {
            i2 = displayMetrics.heightPixels;
        }
        return new com.nostra13.universalimageloader.core.assist.c(i, i2);
    }
}
