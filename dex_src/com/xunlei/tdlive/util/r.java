package com.xunlei.tdlive.util;

import android.os.Handler;
import com.xunlei.tdlive.im.ChatMessage;
import java.util.Timer;
import java.util.TimerTask;

// compiled from: StopWatch.java
public class r {
    private Handler a;
    private Timer b;
    private int c;
    private Runnable d;
    private boolean e;

    public r(Runnable runnable) {
        this.a = null;
        this.b = null;
        this.c = 1000;
        this.e = false;
        a(ChatMessage.FLAG_SYS_NOTIFY, runnable);
    }

    public r(int i, Runnable runnable) {
        this.a = null;
        this.b = null;
        this.c = 1000;
        this.e = false;
        a(i, runnable);
    }

    public void a(int i) {
        this.c = i;
    }

    public int a() {
        return this.c;
    }

    public void a(boolean z) {
        this.e = z;
    }

    private void a(int i, Runnable runnable) {
        if (i <= 0) {
            throw new IllegalArgumentException("interval <= 0");
        } else if (runnable == null) {
            throw new IllegalArgumentException("runnable cannot be null!");
        } else {
            this.c = i;
            this.d = runnable;
        }
    }

    public void b() {
        if (this.a == null) {
            this.a = new Handler();
        }
        if (this.b == null) {
            this.b = new Timer();
            TimerTask sVar = new s(this);
            if (this.e) {
                this.b.schedule(sVar, (long) this.c);
            } else {
                this.b.schedule(sVar, (long) this.c, (long) this.c);
            }
        }
    }

    public void c() {
        if (this.b != null) {
            this.b.cancel();
            this.b = null;
        }
        if (this.a != null) {
            this.a.removeCallbacksAndMessages(null);
            this.a = null;
        }
    }

    public void d() {
        c();
        b();
    }

    public void e() {
        if (this.d != null) {
            this.d.run();
        }
    }
}
