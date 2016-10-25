package com.umeng.socialize.common;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.umeng.socialize.utils.Log;

public class QueuedWork {
    private static Handler uiHandler;

    static {
        uiHandler = new Handler(Looper.getMainLooper());
    }

    public static void runInMain(Runnable runnable) {
        uiHandler.post(runnable);
    }

    public static void runInBack(Runnable runnable) {
        HandlerThread handlerThread = new HandlerThread(Log.TAG, 10);
        handlerThread.start();
        new Handler(handlerThread.getLooper()).post(runnable);
    }

    public static void removeInBack(Runnable runnable) {
    }

    public static void cancle(Runnable runnable) {
    }
}
