package com.xunlei.downloadprovider.frame.remotectrl.ui;

import com.xunlei.downloadprovider.frame.user.UserCenterFragment;
import com.xunlei.downloadprovider.member.login.LoginHelper.d;

// compiled from: RemoteDownloadActivity.java
final class c implements d {
    final /* synthetic */ RemoteDownloadActivity a;

    c(RemoteDownloadActivity remoteDownloadActivity) {
        this.a = remoteDownloadActivity;
    }

    public final void a(int i, int i2, boolean z, Object obj) {
        String str = RemoteDownloadActivity.a;
        new StringBuilder("OnLoginCompleted , event = ").append(i).append(" , errCode = ").append(i2);
        if (i == 0 && i2 == 0) {
            UserCenterFragment.a = true;
        }
    }
}
