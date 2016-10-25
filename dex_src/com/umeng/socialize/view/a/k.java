package com.umeng.socialize.view.a;

import java.util.TimerTask;

// compiled from: ACProgressPie.java
class k extends TimerTask {
    final /* synthetic */ i a;

    k(i iVar) {
        this.a = iVar;
    }

    public void run() {
        int b = this.a.d % (this.a.a.n + 1);
        this.a.b.a((360.0f / ((float) this.a.a.n)) * ((float) b));
        if (b == 0) {
            this.a.d = 1;
        } else {
            i.e(this.a);
        }
    }
}
