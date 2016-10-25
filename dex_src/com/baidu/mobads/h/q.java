package com.baidu.mobads.h;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.util.Log;
import com.baidu.mobads.j.m;
import java.lang.Thread.UncaughtExceptionHandler;

public class q implements UncaughtExceptionHandler {
    private static UncaughtExceptionHandler a;
    private static volatile q b;
    private Context c;
    private a d;

    static interface a {
        void a(String str);
    }

    public static q a(Context context) {
        if (b == null) {
            synchronized (q.class) {
                if (b == null) {
                    b = new q(context);
                }
            }
        }
        return b;
    }

    private q(Context context) {
        this.c = context.getApplicationContext();
        a = Thread.getDefaultUncaughtExceptionHandler();
        new Thread(new r(this)).start();
    }

    public void a() {
        if (!(Thread.getDefaultUncaughtExceptionHandler() instanceof q)) {
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
    }

    public void uncaughtException(Thread thread, Throwable th) {
        try {
            String a = a(th);
            if (a != null) {
                a(a, Log.getStackTraceString(th));
                th.printStackTrace();
                if (this.d != null) {
                    this.d.a(a);
                }
            }
            if (a != null) {
                a.uncaughtException(thread, th);
            }
        } catch (Throwable e) {
            m.a().f().e(e);
        }
    }

    public void a(a aVar) {
        this.d = aVar;
    }

    private String a(Throwable th) {
        th.printStackTrace();
        Throwable cause = th.getCause();
        if (cause != null) {
            th = cause;
        }
        StackTraceElement[] stackTrace = th.getStackTrace();
        if (stackTrace != null && stackTrace.length > 0) {
            for (StackTraceElement stackTraceElement : stackTrace) {
                String className = stackTraceElement.getClassName();
                if (className.startsWith("junit.framework")) {
                    return null;
                }
                if (className.startsWith("com.baidu.mobads.container")) {
                    return "remote";
                }
                if (className.startsWith("com.baidu.mobads.loader")) {
                    return "loader";
                }
                if (className.startsWith("com.baidu.mobads")) {
                    return "proxy";
                }
            }
        }
        return null;
    }

    private SharedPreferences b() {
        return this.c.getSharedPreferences("baidu_mobads_crash", 0);
    }

    private Editor c() {
        return b().edit();
    }

    private void a(String str, String str2) {
        Editor c = c();
        c.putString("key_crash_source", str);
        c.putString("key_crash_trace", str2);
        c.putString("key_crash_ad", com.baidu.mobads.production.d.a.w);
        if (VERSION.SDK_INT >= 9) {
            c.apply();
        } else {
            c.commit();
        }
    }

    private String a(String str) {
        return b().getString(str, com.umeng.a.d);
    }

    private void d() {
        Editor c = c();
        c.clear();
        if (VERSION.SDK_INT >= 9) {
            c.apply();
        } else {
            c.commit();
        }
    }
}
