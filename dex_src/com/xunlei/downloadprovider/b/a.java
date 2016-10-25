package com.xunlei.downloadprovider.b;

import android.os.Handler;

// compiled from: BpBox.java
public class a extends com.xunlei.downloadprovider.b.a.a {
    public Handler mListener;
    public Object mUserData;

    public a(Handler handler, Object obj) {
        this.mListener = handler;
        this.mUserData = obj;
    }

    public static int runBox(a aVar) {
        return b.a().a(aVar);
    }

    public static void cancel(int i) {
        b.a().a(i);
    }

    public static void cancelAll() {
        b a = b.a();
        synchronized (a) {
            for (com.xunlei.downloadprovider.b.a.a aVar : a.a) {
                aVar.stop();
            }
            a.a.clear();
            a.b.clear();
        }
    }

    public boolean isFinish() {
        int status = getStatus();
        return status == 3 || status == 4 || status == 2;
    }

    public void stop() {
        this.mUserData = null;
        this.mListener = null;
        super.stop();
    }
}
