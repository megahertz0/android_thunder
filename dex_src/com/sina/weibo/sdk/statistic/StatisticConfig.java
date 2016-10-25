package com.sina.weibo.sdk.statistic;

import android.content.Context;

class StatisticConfig {
    public static boolean ACTIVITY_DURATION_OPEN = false;
    private static final long DEFAULT_UPLOAD_INTERVAL = 90000;
    private static final long MAX_UPLOAD_INTERVAL = 28800000;
    public static final long MIN_UPLOAD_INTERVAL = 30000;
    public static long kContinueSessionMillis;
    private static long kForceUploadInterval;
    private static long kUploadInterval;
    private static String mAppkey;
    private static String mChannel;
    private static boolean mNeedGizp;

    StatisticConfig() {
    }

    static {
        mAppkey = null;
        mChannel = null;
        ACTIVITY_DURATION_OPEN = true;
        mNeedGizp = true;
        kContinueSessionMillis = 30000;
        kUploadInterval = 90000;
        kForceUploadInterval = 30000;
    }

    public static void setAppkey(String str) {
        mAppkey = str;
    }

    public static void setChannel(String str) {
        mChannel = str;
    }

    public static String getAppkey(Context context) {
        if (mAppkey == null) {
            mAppkey = LogBuilder.getAppKey(context);
        }
        return mAppkey;
    }

    public static String getChannel(Context context) {
        if (mChannel == null) {
            mChannel = LogBuilder.getChannel(context);
        }
        return mChannel;
    }

    public static long getUploadInterval() {
        return kUploadInterval;
    }

    public static void setUploadInterval(long j) throws Exception {
        if (j < 30000 || j > 28800000) {
            throw new Exception("The interval must be between 30 seconds and 8 hours");
        }
        kUploadInterval = j;
    }

    public static boolean isNeedGizp() {
        return mNeedGizp;
    }

    public static void setNeedGizp(boolean z) {
        mNeedGizp = z;
    }

    public static long getForceUploadInterval() {
        return kForceUploadInterval;
    }

    public static void setForceUploadInterval(long j) {
        kForceUploadInterval = j;
    }
}
