package com.tencent.wxop.stat;

import com.tencent.wxop.stat.a.d;
import java.lang.Thread.UncaughtExceptionHandler;

class ao implements UncaughtExceptionHandler {
    ao() {
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (StatConfig.isEnableStatService() && StatServiceImpl.t != null) {
            if (StatConfig.isAutoExceptionCaught()) {
                au.a(StatServiceImpl.t).a(new d(StatServiceImpl.t, StatServiceImpl.a(StatServiceImpl.t, false, null), 2, th, thread, null), null, false, true);
                StatServiceImpl.q.debug("MTA has caught the following uncaught exception:");
                StatServiceImpl.q.error(th);
            }
            StatServiceImpl.flushDataToDB(StatServiceImpl.t);
            if (StatServiceImpl.r != null) {
                StatServiceImpl.q.d("Call the original uncaught exception handler.");
                if (!(StatServiceImpl.r instanceof ao)) {
                    StatServiceImpl.r.uncaughtException(thread, th);
                }
            }
        }
    }
}
