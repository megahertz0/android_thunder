package com.xunlei.downloadprovider.vod.protocol;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// compiled from: DownloadVodUtil.java
public final class b {
    private static b d;
    public Context a;
    public ExecutorService b;
    public final HashMap<Object, a> c;
    private Handler e;
    private Callback f;
    private c g;

    // compiled from: DownloadVodUtil.java
    public static interface a {
        void a(int i, String str, a aVar);
    }

    // compiled from: DownloadVodUtil.java
    public static class b {
        public String a;
        public String b;
        public String c;
        public long d;
        public String e;
        public Object f;

        public b(String str, String str2, String str3, long j, String str4) {
            this.d = 0;
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = j;
            this.e = str4;
        }
    }

    static {
        d = new b();
    }

    private b() {
        this.b = Executors.newCachedThreadPool();
        this.c = new HashMap();
    }

    public static b a() {
        return d;
    }

    protected final a a(Object obj) {
        a aVar;
        synchronized (this.c) {
            aVar = (a) this.c.remove(obj);
        }
        return aVar;
    }

    public final void a(int i, TaskInfo taskInfo) {
        if (this.g != null) {
            this.b.execute(new e(this, i, taskInfo));
        }
    }

    private synchronized Handler b() {
        if (this.e == null) {
            this.f = new g(this);
            this.e = new Handler(Looper.getMainLooper(), this.f);
        }
        return this.e;
    }

    static /* synthetic */ void a(b bVar, c cVar) {
        if (cVar != null) {
            bVar.b().post(new f(bVar, cVar));
        }
    }
}
