package com.xunlei.tdlive.util;

import java.util.TimerTask;

// compiled from: StopWatch.java
class s extends TimerTask {
    final /* synthetic */ r a;

    s(r rVar) {
        this.a = rVar;
    }

    public void run() {
        try {
            if (this.a.d != null && this.a.a != null) {
                this.a.a.post(this.a.d);
            }
        } catch (Exception e) {
        }
    }
}
