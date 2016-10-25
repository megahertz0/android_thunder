package com.tencent.stat;

import android.content.Context;
import com.tencent.stat.a.d;
import java.lang.Thread.UncaughtExceptionHandler;

final class g implements UncaughtExceptionHandler {
    final /* synthetic */ Context a;

    g(Context context) {
        this.a = context;
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        if (StatConfig.isEnableStatService()) {
            n.a(this.a).a(new d(this.a, StatService.a(this.a, false), 2, th), null);
            StatService.b().debug("MTA has caught the following uncaught exception:");
            StatService.b().error((Object) th);
            if (StatService.c() != null) {
                StatService.b().debug("Call the original uncaught exception handler.");
                StatService.c().uncaughtException(thread, th);
                return;
            }
            StatService.b().debug("Original uncaught exception handler not set.");
        }
    }
}
