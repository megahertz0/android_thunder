package com.xunlei.downloadprovider.frame.user.account.ui;

import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import com.umeng.a;
import com.xunlei.downloadprovider.web.base.WebViewNormalActivity;

// compiled from: UserAccountSecurityActivity.java
final class ab implements OnClickListener {
    final /* synthetic */ UserAccountSecurityActivity a;

    ab(UserAccountSecurityActivity userAccountSecurityActivity) {
        this.a = userAccountSecurityActivity;
    }

    public final void onClick(View view) {
        WebViewNormalActivity.a(this.a, a.d, String.format("https://jump.xunlei.com/jump/?jump_key=%s&u1=%s", new Object[]{this.a.k.n, Uri.encode("http://aq.xunlei.com/wap/setMobile.html")}), "\u7ed1\u5b9a\u624b\u673a\u53f7");
    }
}
