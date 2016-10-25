package cn.nodemedia;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;

public class WrapHandlerThread {
    private Callback mCallback;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private String mName;

    public WrapHandlerThread(String str) {
        this(str, null);
    }

    public WrapHandlerThread(String str, Callback callback) {
        this.mName = str;
        this.mCallback = callback;
    }

    public void start() {
        if (this.mHandler == null) {
            this.mHandlerThread = new HandlerThread(this.mName);
            this.mHandlerThread.start();
            this.mHandler = new Handler(this.mHandlerThread.getLooper(), this.mCallback);
        }
    }

    public void stop() {
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages(null);
            this.mHandlerThread.quit();
        }
        this.mHandler = null;
        this.mHandlerThread = null;
    }

    public boolean post(Runnable runnable) {
        return this.mHandler != null ? this.mHandler.post(runnable) : false;
    }

    public boolean send(Message message) {
        return this.mHandler != null ? this.mHandler.sendMessage(message) : false;
    }

    public boolean send(int i, int i2, int i3, Object obj) {
        return this.mHandler != null ? this.mHandler.sendMessage(this.mHandler.obtainMessage(i, i2, i3, obj)) : false;
    }
}
