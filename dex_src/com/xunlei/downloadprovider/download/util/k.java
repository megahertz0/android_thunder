package com.xunlei.downloadprovider.download.util;

import android.os.Handler;

// compiled from: HandlerTimer.java
public abstract class k implements Runnable {
    private long a;
    public Handler b;
    private boolean c;

    public abstract void a();

    public k(Handler handler) {
        this.b = handler;
    }

    public final void a(boolean z) {
        this.a = 1000;
        this.c = z;
        if (this.b != null) {
            this.b.removeCallbacks(this);
            this.b.postDelayed(this, 1000);
        }
    }

    public final void b() {
        if (this.b != null) {
            this.b.removeCallbacks(this);
            this.c = false;
        }
    }

    public final void run() {
        if (this.b != null) {
            this.b.removeCallbacks(this);
        }
        try {
            a();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.b != null) {
            this.b.removeCallbacks(this);
            if (this.c) {
                this.b.postDelayed(this, this.a);
            }
        }
    }
}
