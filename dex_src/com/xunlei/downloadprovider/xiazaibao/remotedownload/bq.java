package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.net.Uri;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.c;

// compiled from: XZBWebviewActivity.java
final class bq implements c {
    final /* synthetic */ bp a;

    bq(bp bpVar) {
        this.a = bpVar;
    }

    public final void a(int i) {
        if (i == 0) {
            String decode = Uri.decode(XZBWebviewActivity.c(this.a.a.this$0));
            XZBWebviewActivity.d(this.a.a.this$0).loadUrl(String.format("http://jump.xunlei.com/jump/?jump_key=%s&u1=%s", new Object[]{LoginHelper.a().n, Uri.encode(decode)}));
            XZBWebviewActivity.a(this.a.a.this$0, false);
            XZBWebviewActivity.b(this.a.a.this$0, true);
            return;
        }
        XZBWebviewActivity.b(this.a.a.this$0, false);
    }
}
