package com.xunlei.downloadprovider.personal.playrecord;

import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.p;

// compiled from: PlayRecordFragment.java
final class j implements p {
    final /* synthetic */ PlayRecordFragment a;

    j(PlayRecordFragment playRecordFragment) {
        this.a = playRecordFragment;
    }

    public final void OnRefreshUserInfoCompleted(int i, boolean z) {
        if (PlayRecordFragment.h(this.a) != null && PlayRecordFragment.b(this.a) != PlayRecordFragment$PlayRecordListState.destroy) {
            PlayRecordFragment.h(this.a).post(new k(this));
            LoginHelper.a().b(PlayRecordFragment.o(this.a));
        }
    }
}
