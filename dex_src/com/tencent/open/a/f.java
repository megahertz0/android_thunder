package com.tencent.open.a;

import android.os.Environment;
import android.text.TextUtils;
import com.tencent.open.a.d.a;
import com.tencent.open.a.d.b;
import com.tencent.open.a.d.c;
import com.tencent.open.utils.Global;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;

// compiled from: ProGuard
public class f {
    public static f a;
    protected static final b c;
    private static boolean d;
    protected a b;

    static {
        a = null;
        d = false;
        c = new b(c(), c.m, c.g, c.h, c.c, (long) c.i, 10, c.e, c.n);
    }

    public static f a() {
        if (a == null) {
            synchronized (f.class) {
                if (a == null) {
                    a = new f();
                    d = true;
                }
            }
        }
        return a;
    }

    private f() {
        this.b = new a(c);
    }

    protected void a(int i, String str, String str2, Throwable th) {
        if (d) {
            Object packageName = Global.getPackageName();
            if (!TextUtils.isEmpty(packageName)) {
                String str3 = packageName + " SDK_VERSION:3.1.0";
                if (this.b != null) {
                    e.a.b(R.styleable.AppCompatTheme_actionModeCutDrawable, Thread.currentThread(), System.currentTimeMillis(), "openSDK_LOG", str3, null);
                    this.b.b(R.styleable.AppCompatTheme_actionModeCutDrawable, Thread.currentThread(), System.currentTimeMillis(), "openSDK_LOG", str3, null);
                    d = false;
                } else {
                    return;
                }
            }
        }
        e.a.b(i, Thread.currentThread(), System.currentTimeMillis(), str, str2, th);
        if (a.a(c.b, i) && this.b != null) {
            this.b.b(i, Thread.currentThread(), System.currentTimeMillis(), str, str2, th);
        }
    }

    public static final void a(String str, String str2) {
        a().a(1, str, str2, null);
    }

    public static final void b(String str, String str2) {
        a().a(XZBDevice.DOWNLOAD_LIST_RECYCLE, str, str2, null);
    }

    public static final void a(String str, String str2, Throwable th) {
        a().a(XZBDevice.DOWNLOAD_LIST_RECYCLE, str, str2, th);
    }

    public static final void c(String str, String str2) {
        a().a(XZBDevice.DOWNLOAD_LIST_ALL, str, str2, null);
    }

    public static final void d(String str, String str2) {
        a().a(XZBDevice.Wait, str, str2, null);
    }

    public static final void e(String str, String str2) {
        a().a(R.styleable.Toolbar_titleMarginBottom, str, str2, null);
    }

    public static final void b(String str, String str2, Throwable th) {
        a().a(R.styleable.Toolbar_titleMarginBottom, str, str2, th);
    }

    public static void b() {
        synchronized (f.class) {
            a().d();
            if (a != null) {
                a = null;
            }
        }
    }

    protected static File c() {
        Object obj = null;
        String str = c.d;
        c b = b.b();
        if (b != null && b.c() > c.f) {
            obj = 1;
        }
        return obj != null ? new File(Environment.getExternalStorageDirectory(), str) : new File(Global.getFilesDir(), str);
    }

    protected void d() {
        if (this.b != null) {
            this.b.a();
            this.b.b();
            this.b = null;
        }
    }
}
