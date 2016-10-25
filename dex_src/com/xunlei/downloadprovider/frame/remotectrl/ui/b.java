package com.xunlei.downloadprovider.frame.remotectrl.ui;

import android.text.TextUtils;
import com.xunlei.downloadprovider.frame.user.UserCenterFragment;
import com.xunlei.downloadprovider.member.login.LoginHelper.g;

// compiled from: RemoteDownloadActivity.java
final class b implements g {
    final /* synthetic */ RemoteDownloadActivity a;

    b(RemoteDownloadActivity remoteDownloadActivity) {
        this.a = remoteDownloadActivity;
    }

    public final void a() {
        String str = RemoteDownloadActivity.a;
        UserCenterFragment.a = false;
        if (TextUtils.isEmpty(this.a.g)) {
            RemoteDownloadActivity.b.a("http://wap.yuancheng.xunlei.com/index.html");
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.format("javascript:%s()", new Object[]{this.a.g}));
        RemoteDownloadActivity.b.a(stringBuffer.toString());
    }
}
