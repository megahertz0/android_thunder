package com.android.volley;

import android.os.SystemClock;
import android.util.Log;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// compiled from: VolleyLog.java
public class x {
    public static String a;
    public static boolean b;

    // compiled from: VolleyLog.java
    static class a {
        public static final boolean a;
        private final List<a> b;
        private boolean c;

        // compiled from: VolleyLog.java
        private static class a {
            public final String a;
            public final long b;
            public final long c;

            public a(String str, long j, long j2) {
                this.a = str;
                this.b = j;
                this.c = j2;
            }
        }

        a() {
            this.b = new ArrayList();
            this.c = false;
        }

        static {
            a = b;
        }

        public final synchronized void a(String str, long j) {
            if (this.c) {
                throw new IllegalStateException("Marker added to finished log");
            }
            this.b.add(new a(str, j, SystemClock.elapsedRealtime()));
        }

        public final synchronized void a(String str) {
            long j;
            this.c = true;
            if (this.b.size() == 0) {
                j = 0;
            } else {
                j = ((a) this.b.get(this.b.size() - 1)).c - ((a) this.b.get(0)).c;
            }
            if (j > 0) {
                long j2 = ((a) this.b.get(0)).c;
                x.b("(%-4d ms) %s", Long.valueOf(j), str);
                j = j2;
                for (a aVar : this.b) {
                    x.b("(+%-4d) [%2d] %s", Long.valueOf(aVar.c - j), Long.valueOf(aVar.b), aVar.a);
                    j = r4;
                }
            }
        }

        protected final void finalize() throws Throwable {
            if (!this.c) {
                a("Request on the loose");
                x.c("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
            }
        }
    }

    static {
        String str = "Volley";
        a = str;
        b = Log.isLoggable(str, XZBDevice.DOWNLOAD_LIST_RECYCLE);
    }

    public static void a(String str, Object... objArr) {
        if (b) {
            f(str, objArr);
        }
    }

    public static void b(String str, Object... objArr) {
        f(str, objArr);
    }

    public static void c(String str, Object... objArr) {
        f(str, objArr);
    }

    public static void d(String str, Object... objArr) {
        f(str, objArr);
    }

    public static void e(String str, Object... objArr) {
        Log.wtf(a, f(str, objArr));
    }

    private static String f(String str, Object... objArr) {
        String str2;
        if (objArr != null) {
            str = String.format(Locale.US, str, objArr);
        }
        StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        String str3 = "<unknown>";
        for (int i = 2; i < stackTrace.length; i++) {
            if (!stackTrace[i].getClass().equals(x.class)) {
                str3 = stackTrace[i].getClassName();
                str3 = str3.substring(str3.lastIndexOf(R.styleable.AppCompatTheme_dropdownListPreferredItemHeight) + 1);
                str2 = str3.substring(str3.lastIndexOf(R.styleable.AppCompatTheme_actionModeShareDrawable) + 1) + "." + stackTrace[i].getMethodName();
                break;
            }
        }
        str2 = str3;
        return String.format(Locale.US, "[%d] %s: %s", new Object[]{Long.valueOf(Thread.currentThread().getId()), str2, str});
    }
}
