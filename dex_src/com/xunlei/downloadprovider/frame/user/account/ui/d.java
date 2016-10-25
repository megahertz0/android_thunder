package com.xunlei.downloadprovider.frame.user.account.ui;

import android.widget.ImageView;
import com.xunlei.downloadprovider.frame.user.account.a.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: UserAccountInfoActivityNew.java
final class d implements a {
    final /* synthetic */ UserAccountInfoActivityNew a;

    d(UserAccountInfoActivityNew userAccountInfoActivityNew) {
        this.a = userAccountInfoActivityNew;
    }

    public final void a(ImageView imageView, ImageView imageView2) {
        if (this.a.l != -1) {
            if (this.a.l == 2131232976) {
                imageView.setVisibility(0);
            }
            if (this.a.l == 2131232975) {
                imageView2.setVisibility(0);
                return;
            }
            return;
        }
        imageView.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        imageView2.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
    }
}
