package com.umeng.socialize.view.a;

import com.umeng.socialize.view.a.f.a;
import java.util.TimerTask;

// compiled from: ACProgressFlower.java
class h extends TimerTask {
    final /* synthetic */ f a;

    h(f fVar) {
        this.a = fVar;
    }

    public void run() {
        int b = f.b(this.a) % a.g(f.c(this.a));
        if (a.t(f.c(this.a)) == 100) {
            f.d(this.a).a(b);
        } else {
            f.d(this.a).a((a.g(f.c(this.a)) - 1) - b);
        }
        if (b == 0) {
            f.a(this.a, 1);
        } else {
            f.e(this.a);
        }
    }
}
