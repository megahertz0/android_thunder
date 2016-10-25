package com.uc.addon.sdk.remote.protocol;

public class DebugUtil {
    public static final boolean DEBUG = false;
    public static final boolean REPORT_ADDON_CRASH = true;
    public static final boolean USE_TRACE_VIEW = false;

    public static void error(String str) {
    }

    public static long getCurrentThreadId() {
        return Thread.currentThread().getId();
    }

    public static void uc_assert(boolean z, String str) {
    }
}
