package com.xunlei.downloadprovider.frame.user;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;
import com.xunlei.downloadprovider.commonview.dialog.k;
import com.xunlei.downloadprovider.homepage.a.a.d;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: UserCenterFragment.java
final class ba implements OnClickListener {
    final /* synthetic */ k a;
    final /* synthetic */ UserCenterFragment b;

    ba(UserCenterFragment userCenterFragment, k kVar) {
        this.b = userCenterFragment;
        this.a = kVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.dismiss();
        UserCenterFragment.a("personal_center_tip");
        if (d.b == null || !d.b.containsKey("5")) {
            UserCenterFragment.z(this.b);
            return;
        }
        d dVar = (d) d.b.get("5");
        if (TextUtils.isEmpty(dVar.h)) {
            UserCenterFragment.z(this.b);
            return;
        }
        BrowserUtil.a();
        BrowserUtil.b(UserCenterFragment.y(this.b), dVar.h, BuildConfig.VERSION_NAME);
    }
}
