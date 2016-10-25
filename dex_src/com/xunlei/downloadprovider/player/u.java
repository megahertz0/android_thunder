package com.xunlei.downloadprovider.player;

import android.os.Looper;

// compiled from: MediaPlayerUtils.java
public final class u {

    // compiled from: MediaPlayerUtils.java
    public static interface a {
        void a();

        void b();
    }

    public static boolean a() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public static String a(int i) {
        if (i < 0) {
            return "00:00";
        }
        if (i == 0) {
            return "00:00";
        }
        int i2 = i / 60;
        if (i2 == 0) {
            return new StringBuilder("00:").append(b(i)).toString();
        }
        int i3 = i % 60;
        if (i2 / 60 == 0) {
            return b(i2) + ":" + b(i3);
        }
        return b(i2 % 60) + ":" + b(i3);
    }

    private static String b(int i) {
        return i < 10 ? new StringBuilder("0").append(i).toString() : String.valueOf(i);
    }
}
