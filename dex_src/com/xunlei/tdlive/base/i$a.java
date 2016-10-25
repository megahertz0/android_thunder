package com.xunlei.tdlive.base;

import java.util.TimerTask;

// compiled from: BaseFragment.java
private class i$a extends TimerTask {
    final /* synthetic */ i a;
    private int b;

    public i$a(i iVar, int i) {
        this.a = iVar;
        this.b = 0;
        this.b = i;
    }

    public void run() {
        i.a(this.a).obtainMessage(6535, this.b, this.b).sendToTarget();
    }
}
