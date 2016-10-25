package com.xunlei.downloadprovider.app.a.a;

import com.tencent.bugly.crashreport.CrashReport;
import java.lang.Thread.UncaughtExceptionHandler;

// compiled from: CrashHandler.java
public class a implements UncaughtExceptionHandler {
    private static a b;
    private UncaughtExceptionHandler a;

    private a() {
    }

    private static a b() {
        if (b == null) {
            synchronized (a.class) {
                b = new a();
            }
        }
        return b;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        new StringBuilder("uncaughtException: Thread:").append(thread).append(" Throwable:").append(th);
        UncaughtExceptionHandler uncaughtExceptionHandler = this.a;
        CrashReport.postCatchedException(th);
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        }
    }

    public static void a() {
        UncaughtExceptionHandler b = b();
        UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (!(defaultUncaughtExceptionHandler == null || defaultUncaughtExceptionHandler == b)) {
            b.a = defaultUncaughtExceptionHandler;
        }
        Thread.setDefaultUncaughtExceptionHandler(b);
    }
}
