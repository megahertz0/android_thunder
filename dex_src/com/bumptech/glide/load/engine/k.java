package com.bumptech.glide.load.engine;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import com.bumptech.glide.h.h;

// compiled from: ResourceRecycler.java
final class k {
    private boolean a;
    private final Handler b;

    // compiled from: ResourceRecycler.java
    private static class a implements Callback {
        private a() {
        }

        public final boolean handleMessage(Message message) {
            if (message.what != 1) {
                return false;
            }
            ((j) message.obj).c();
            return true;
        }
    }

    k() {
        this.b = new Handler(Looper.getMainLooper(), new a());
    }

    public final void a(j<?> jVar) {
        h.a();
        if (this.a) {
            this.b.obtainMessage(1, jVar).sendToTarget();
            return;
        }
        this.a = true;
        jVar.c();
        this.a = false;
    }
}
