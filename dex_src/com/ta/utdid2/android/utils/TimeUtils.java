package com.ta.utdid2.android.utils;

public class TimeUtils {
    public static final String TAG;
    public static final int TOTAL_M_S_ONE_DAY = 86400000;

    static {
        TAG = TimeUtils.class.getName();
    }

    public static boolean isUpToDate(long j, int i) {
        boolean z = (System.currentTimeMillis() - j) / 86400000 < ((long) i);
        if (DebugUtils.DBG) {
            new StringBuilder("isUpToDate: ").append(z).append("; oldTimestamp: ").append(j).append("; currentTimestamp").append(System.currentTimeMillis());
        }
        return z;
    }
}
