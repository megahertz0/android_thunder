package com.xunlei.downloadprovider.personal.playrecord;

import com.xunlei.downloadprovider.member.login.LoginHelper.d;

// compiled from: PlayRecordFragment.java
final class f implements d {
    final /* synthetic */ PlayRecordFragment a;

    f(PlayRecordFragment playRecordFragment) {
        this.a = playRecordFragment;
    }

    public final void a(int i, int i2, boolean z, Object obj) {
        PlayRecordFragment.a(this.a);
        new StringBuilder("OnLoginCompleted , event = ").append(i).append(" , errCode = ").append(i2);
        if (i == 0 && i2 == 0 && PlayRecordFragment.b(this.a) != PlayRecordFragment$PlayRecordListState.destroy) {
            PlayRecordFragment.d(this.a).a(PlayRecordFragment.c(this.a));
            PlayRecordFragment.e(this.a);
        }
    }
}
