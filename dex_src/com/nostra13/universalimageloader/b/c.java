package com.nostra13.universalimageloader.b;

import android.util.Log;
import com.nostra13.universalimageloader.core.d;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: L.java
public final class c {
    private static volatile boolean a;
    private static volatile boolean b;

    static {
        a = false;
        b = true;
    }

    public static void a(boolean z) {
        a = z;
    }

    public static void a(String str, Object... objArr) {
        if (a) {
            a(XZBDevice.DOWNLOAD_LIST_FAILED, null, str, objArr);
        }
    }

    public static void b(String str, Object... objArr) {
        a(XZBDevice.DOWNLOAD_LIST_ALL, null, str, objArr);
    }

    public static void c(String str, Object... objArr) {
        a(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, null, str, objArr);
    }

    public static void a(Throwable th) {
        a(R.styleable.Toolbar_contentInsetEnd, th, null, new Object[0]);
    }

    public static void d(String str, Object... objArr) {
        a(R.styleable.Toolbar_contentInsetEnd, null, str, objArr);
    }

    private static void a(int i, Throwable th, String str, Object... objArr) {
        if (b) {
            String format;
            if (objArr.length > 0) {
                format = String.format(str, objArr);
            } else {
                format = str;
            }
            if (th != null) {
                if (format == null) {
                    format = th.getMessage();
                }
                String stackTraceString = Log.getStackTraceString(th);
                format = String.format("%1$s\n%2$s", new Object[]{format, stackTraceString});
            }
            Log.println(i, d.a, format);
        }
    }
}
