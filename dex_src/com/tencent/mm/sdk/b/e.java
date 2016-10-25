package com.tencent.mm.sdk.b;

import android.os.Debug;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.alipay.sdk.util.h;
import junit.framework.Assert;

final class e extends Handler implements com.tencent.mm.sdk.b.g.a {
    private Looper aN;
    private Callback aO;
    a aP;

    public static interface a {
        void a(Runnable runnable, g gVar);

        void b(Runnable runnable, g gVar);
    }

    e(Looper looper, a aVar) {
        super(looper);
        this.aN = getLooper();
        this.aP = aVar;
    }

    e(a aVar) {
        this.aN = getLooper();
        this.aP = aVar;
    }

    public final void c(Runnable runnable, g gVar) {
        if (this.aP != null) {
            this.aP.b(runnable, gVar);
        }
    }

    public final void dispatchMessage(Message message) {
        if (message.getCallback() == null && this.aO == null) {
            System.currentTimeMillis();
            Debug.threadCpuTimeNanos();
            handleMessage(message);
            if (this.aP != null) {
                this.aN.getThread();
                System.currentTimeMillis();
                Debug.threadCpuTimeNanos();
                return;
            }
            return;
        }
        super.dispatchMessage(message);
    }

    public final void handleMessage(Message message) {
    }

    public final boolean sendMessageAtTime(Message message, long j) {
        Assert.assertTrue("msg is null", message != null);
        Runnable callback = message.getCallback();
        if (callback == null) {
            return super.sendMessageAtTime(message, j);
        }
        long uptimeMillis = j - SystemClock.uptimeMillis();
        g gVar = new g(this.aN.getThread(), message.getTarget() == null ? this : message.getTarget(), callback, message.obj, this);
        if (uptimeMillis > 0) {
            gVar.aY = uptimeMillis;
        }
        Message obtain = Message.obtain(message.getTarget(), gVar);
        obtain.what = message.what;
        obtain.arg1 = message.arg1;
        obtain.arg2 = message.arg2;
        obtain.obj = message.obj;
        obtain.replyTo = message.replyTo;
        obtain.setData(message.getData());
        message.recycle();
        if (this.aP != null) {
            this.aP.a(callback, gVar);
        }
        boolean sendMessageAtTime = super.sendMessageAtTime(obtain, j);
        if (!(sendMessageAtTime || this.aP == null)) {
            this.aP.b(callback, gVar);
        }
        return sendMessageAtTime;
    }

    public final String toString() {
        return new StringBuilder("MMInnerHandler{listener = ").append(this.aP).append(h.d).toString();
    }
}
