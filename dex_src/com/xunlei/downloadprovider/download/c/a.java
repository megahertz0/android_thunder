package com.xunlei.downloadprovider.download.c;

import android.os.Handler;
import com.umeng.socialize.common.SocializeConstants;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.model.protocol.report.b;
import com.xunlei.downloadprovider.service.downloads.task.d;
import java.util.List;

// compiled from: SpeedLimitHelper.java
public final class a {
    private static a g;
    public int a;
    public int b;
    public int c;
    private final String d;
    private Runnable e;
    private Handler f;

    private a() {
        this.d = "SpeedLimit";
        this.f = new Handler();
        this.a = -1;
        this.b = 1;
        this.c = 0;
    }

    public static a a() {
        if (g == null) {
            g = new a();
        }
        return g;
    }

    public final boolean b() {
        String g = g();
        CharSequence h = BrothersApplication.a().h();
        new StringBuilder("lastGUid,versionCode == ").append(g).append(MiPushClient.ACCEPT_TIME_SEPARATOR).append(h);
        g f = f();
        if (f != null) {
            d.a();
            if (d.c() && e() > 0) {
                return g.equals(f.a) && f.b.contains(h) && f.c == 1;
            }
        }
        return false;
    }

    public final void a(boolean z) {
        if (z) {
            d();
            d.a().c((long) e());
            this.a = this.b;
            return;
        }
        d.a().c(-1);
        this.a = this.c;
    }

    public final void c() {
        d();
        this.e = new b(this);
        this.f.postDelayed(this.e, 10000);
    }

    private void d() {
        if (this.e != null) {
            this.f.removeCallbacks(this.e);
        }
    }

    private int e() {
        int i = -1;
        d();
        g f = f();
        if (f != null) {
            int i2 = f.d;
            d.a();
            long i3 = d.i();
            int i4 = f.e;
            if (i4 == g.f) {
                i = (int) ((i3 / 1024) - ((long) i2));
            } else if (i4 == g.g) {
                i = i2;
            }
            new StringBuilder("maxReachableDownloadSpeed - speedLimit ==> ").append(i3).append(SocializeConstants.OP_DIVIDER_MINUS).append(i2).append("=").append(i);
        }
        return i;
    }

    private static g f() {
        String g = g();
        CharSequence h = BrothersApplication.a().h();
        List<g> list = c.a().a;
        if (list != null) {
            for (g gVar : list) {
                if (!g.equals(gVar.a) || gVar.b.contains(h)) {
                    return gVar;
                }
            }
        }
        return null;
    }

    private static String g() {
        StringBuilder stringBuilder = new StringBuilder();
        String b = b.b();
        char charAt = b.charAt(b.length() - 1);
        if (Character.isLowerCase(charAt)) {
            charAt = Character.toUpperCase(charAt);
        }
        return stringBuilder.append("PF").append(charAt).toString();
    }
}
