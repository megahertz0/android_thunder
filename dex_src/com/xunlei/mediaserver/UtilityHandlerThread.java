package com.xunlei.mediaserver;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

public class UtilityHandlerThread extends HandlerThread {
    private MyHandler mMyHandler;

    private class MyHandler extends Handler {
        public MyHandler(Looper looper) {
            super(looper);
        }
    }

    public UtilityHandlerThread() {
        super(UtilityHandlerThread.class.getName());
        this.mMyHandler = null;
    }

    public void init() {
        super.start();
        this.mMyHandler = new MyHandler(getLooper());
    }

    public void uninit() {
        super.quit();
    }

    public boolean ExecuteRunnable(Runnable runnable) {
        return this.mMyHandler.post(runnable);
    }
}
