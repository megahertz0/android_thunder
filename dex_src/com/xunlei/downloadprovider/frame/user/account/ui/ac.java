package com.xunlei.downloadprovider.frame.user.account.ui;

import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import com.umeng.a;
import com.xunlei.downloadprovider.web.base.WebViewNormalActivity;

// compiled from: UserAccountSecurityActivity.java
final class ac implements OnClickListener {
    final /* synthetic */ UserAccountSecurityActivity a;

    ac(UserAccountSecurityActivity userAccountSecurityActivity) {
        this.a = userAccountSecurityActivity;
    }

    public final void onClick(View view) {
        WebViewNormalActivity.a(this.a, a.d, String.format("https://jump.xunlei.com/jump/?jump_key=%s&u1=%s", new Object[]{this.a.k.n, Uri.encode("http://aq.xunlei.com/wap/forgetPwd.html")}), this.a.getString(2131232947));
    }
}
