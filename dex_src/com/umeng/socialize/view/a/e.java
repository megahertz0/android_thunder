package com.umeng.socialize.view.a;

import java.util.TimerTask;

// compiled from: ACProgressCustom.java
class e extends TimerTask {
    final /* synthetic */ c a;

    e(c cVar) {
        this.a = cVar;
    }

    public void run() {
        int c = c.c(this.a) % c.d(this.a);
        c.e(this.a).a(c);
        if (c == 0) {
            c.a(this.a, 1);
        } else {
            c.f(this.a);
        }
    }
}
