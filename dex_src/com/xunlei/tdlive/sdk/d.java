package com.xunlei.tdlive.sdk;

import android.app.Activity;
import android.os.Handler;
import com.xunlei.tdlive.LivePlayerActivity;
import com.xunlei.tdlive.base.BaseActivity.a;
import com.xunlei.tdlive.modal.e;

// compiled from: XLLiveSDK.java
class d implements a {
    final /* synthetic */ b a;

    d(b bVar) {
        this.a = bVar;
    }

    public void a(Activity activity) {
        if (activity instanceof LivePlayerActivity) {
            com.xunlei.tdlive.a.a(XLLiveSDK.a(this.a.b), e.u);
            XLLiveSDK.a(this.a.b, true);
        }
    }

    public void b(Activity activity) {
        if (activity instanceof LivePlayerActivity) {
            com.xunlei.tdlive.a.a();
            XLLiveSDK.a(this.a.b, false);
        }
    }

    public void c(Activity activity) {
        new Handler().postDelayed(new e(this), 500);
    }

    public void d(Activity activity) {
    }
}
