package com.xunlei.downloadprovider.personal.playrecord;

// compiled from: PlayRecordFragment.java
final class k implements Runnable {
    final /* synthetic */ j a;

    k(j jVar) {
        this.a = jVar;
    }

    public final void run() {
        if (PlayRecordFragment.b(this.a.a) != PlayRecordFragment$PlayRecordListState.destroy) {
            PlayRecordFragment.g(this.a.a).b();
            PlayRecordFragment.e(this.a.a);
        }
    }
}
