package com.xunlei.downloadprovider.personal.playrecord;

// compiled from: PlayRecordFragment.java
final class h implements PlayRecordFragment$a {
    final /* synthetic */ PlayRecordFragment a;

    h(PlayRecordFragment playRecordFragment) {
        this.a = playRecordFragment;
    }

    public final void a() {
        if (PlayRecordFragment.b(this.a) != PlayRecordFragment$PlayRecordListState.destroy) {
            this.a.a();
            PlayRecordFragment.a(this.a, PlayRecordFragment$PlayRecordListState.inited);
            PlayRecordFragment.g(this.a).b();
        }
        this.a.a();
    }
}
