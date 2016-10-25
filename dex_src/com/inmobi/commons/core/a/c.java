package com.inmobi.commons.core.a;

import com.inmobi.commons.core.configs.b.b;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.lang.Thread.UncaughtExceptionHandler;

// compiled from: InMobiCrashHandler.java
public class c implements UncaughtExceptionHandler {
    private static final String b;
    private static boolean c;
    private static a d;
    private UncaughtExceptionHandler a;

    // compiled from: InMobiCrashHandler.java
    static class a implements b {
        a() {
        }

        public void a(com.inmobi.commons.core.configs.a aVar) {
            com.inmobi.commons.core.c.a.a().a(aVar.a(), ((a) aVar).e());
        }
    }

    static {
        b = c.class.getSimpleName();
        c = false;
    }

    private c(UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.a = uncaughtExceptionHandler;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (a(th)) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Crash due to inmobi, will report it");
            com.inmobi.commons.core.c.a.a().a(new b(thread, th));
        }
        this.a.uncaughtException(thread, th);
    }

    private boolean a(Throwable th) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        int length = stackTrace.length;
        for (int i = 0; i < length; i++) {
            if (stackTrace[i].getClassName().contains("com.inmobi.")) {
                return true;
            }
        }
        return false;
    }

    public static synchronized void a() {
        synchronized (c.class) {
            if (!c) {
                com.inmobi.commons.core.configs.a aVar = new a();
                d = new a();
                com.inmobi.commons.core.configs.b.a().a(aVar, d);
                com.inmobi.commons.core.c.a.a().a(aVar.a(), aVar.e());
                Thread.setDefaultUncaughtExceptionHandler(new c(Thread.getDefaultUncaughtExceptionHandler()));
                c = true;
            }
        }
    }
}
