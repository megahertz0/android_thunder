package u.aly;

import com.umeng.analytics.AnalyticsConfig;
import java.lang.Thread.UncaughtExceptionHandler;

// compiled from: CrashHandler.java
public final class cz implements UncaughtExceptionHandler {
    public dh a;
    private UncaughtExceptionHandler b;

    public cz() {
        if (Thread.getDefaultUncaughtExceptionHandler() != this) {
            this.b = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        if (AnalyticsConfig.CATCH_EXCEPTION) {
            this.a.a(th);
        } else {
            this.a.a(null);
        }
        if (this.b != null && this.b != Thread.getDefaultUncaughtExceptionHandler()) {
            this.b.uncaughtException(thread, th);
        }
    }
}
