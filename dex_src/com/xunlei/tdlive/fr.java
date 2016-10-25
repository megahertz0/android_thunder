package com.xunlei.tdlive;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.tdlive.play.view.ae;
import com.xunlei.tdlive.play.view.b.a;

// compiled from: WebBrowserActivity.java
class fr implements OnClickListener {
    final /* synthetic */ WebBrowserActivity a;

    fr(WebBrowserActivity webBrowserActivity) {
        this.a = webBrowserActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        if (i == 1) {
            this.a.mWebView.reload();
        } else if (i == 3) {
            this.a.finish();
        } else if (i == 2) {
            a aVar = new a();
            aVar.a(null);
            aVar.a(this.a.getWindow().getDecorView());
            String title = this.a.mWebView.getTitle();
            aVar.a(new ae.a(this.a.mWebView.getUrl(), com.umeng.a.d, "\u5206\u4eab\u94fe\u63a5", title, com.umeng.a.d, com.umeng.a.d));
            ae aeVar = new ae(this.a);
            aeVar.a(aVar);
            aeVar.b();
        }
    }
}
