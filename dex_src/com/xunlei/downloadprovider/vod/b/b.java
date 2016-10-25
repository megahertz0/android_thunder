package com.xunlei.downloadprovider.vod.b;

import android.text.TextUtils;
import com.xunlei.downloadprovider.service.downloads.kernel.f;
import com.xunlei.downloadprovider.vod.protocol.VodSourceType;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.android.spdy.SpdyAgent;

// compiled from: PlayRecordHelper.java
public final class b {
    private static b d;
    public a a;
    public Executor b;
    private final String c;

    // compiled from: PlayRecordHelper.java
    public static class a {
        public int a;
        public String b;
        public long c;
        public int d;
        public int e;
        public String f;
        public String g;
        public String h;
        public String i;
        public long j;
        public int k;
        public int l;
        public int m;
        public String n;
        public int o;
        public String p;
        public String q;
        public String r;
        public int s;

        public a() {
            this.d = 0;
            this.m = 105;
        }
    }

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            if (d == null) {
                d = new b();
            }
            bVar = d;
        }
        return bVar;
    }

    private b() {
        this.c = "pr-PlayRecordHelper";
        this.b = Executors.newSingleThreadExecutor();
        this.a = a.a();
    }

    public static String a(String str) {
        if (!str.contains("http://127.0.0.1")) {
            return str;
        }
        CharSequence a = f.a(str);
        return !TextUtils.isEmpty(a) ? a : str;
    }

    public final a b(String str) {
        return this.a.a(a(str));
    }

    public final void c(String str) {
        this.a.c(str);
    }

    public static int a(VodSourceType vodSourceType) {
        switch (AnonymousClass_1.a[vodSourceType.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
            case XZBDevice.DOWNLOAD_LIST_FAILED:
            case XZBDevice.DOWNLOAD_LIST_ALL:
                return 1;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
            case R.styleable.Toolbar_contentInsetEnd:
                return XZBDevice.DOWNLOAD_LIST_RECYCLE;
            case XZBDevice.Stop:
                return XZBDevice.DOWNLOAD_LIST_FAILED;
            default:
                return 0;
        }
    }
}
