package com.baidu.mobads;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.KeyEvent;
import com.baidu.mobads.ao.a;
import com.baidu.mobads.interfaces.error.XAdErrorCode;
import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.j.m;

class aj implements a {
    final /* synthetic */ Context a;
    final /* synthetic */ ao b;
    final /* synthetic */ String c;
    final /* synthetic */ boolean d;
    final /* synthetic */ SplashAd e;

    aj(SplashAd splashAd, Context context, ao aoVar, String str, boolean z) {
        this.e = splashAd;
        this.a = context;
        this.b = aoVar;
        this.c = str;
        this.d = z;
    }

    public void a(int i) {
        if (SplashAd.b(this.e) != null) {
            SplashAd.b(this.e).a(i);
        }
    }

    public void a(boolean z) {
        if (SplashAd.b(this.e) != null) {
            SplashAd.b(this.e).a(z);
        }
    }

    public void a(int i, int i2) {
        if (SplashAd.b(this.e) == null) {
            float screenDensity = m.a().m().getScreenDensity(this.a);
            if (((float) i) < 200.0f * screenDensity || ((float) i2) < screenDensity * 150.0f) {
                m.a().f().e(m.a().q().genCompleteErrorMessage(XAdErrorCode.SHOW_STANDARD_UNFIT, "\u5f00\u5c4f\u663e\u793a\u533a\u57df\u592a\u5c0f,\u5bbd\u5ea6\u81f3\u5c11200dp,\u9ad8\u5ea6\u81f3\u5c11150dp"));
                SplashAd.a(this.e).onAdDismissed();
                return;
            }
            SplashAd.a(this.e, new com.baidu.mobads.production.h.a(this.a, this.b, this.c, this.d, i, i2));
            SplashAd.b(this.e).addEventListener("AdUserClick", SplashAd.c(this.e));
            SplashAd.b(this.e).addEventListener(IXAdEvent.AD_LOADED, SplashAd.c(this.e));
            SplashAd.b(this.e).addEventListener(IXAdEvent.AD_STARTED, SplashAd.c(this.e));
            SplashAd.b(this.e).addEventListener(IXAdEvent.AD_STOPPED, SplashAd.c(this.e));
            SplashAd.b(this.e).addEventListener(IXAdEvent.AD_ERROR, SplashAd.c(this.e));
            SplashAd.b(this.e).request();
        }
    }

    @SuppressLint({"MissingSuperCall"})
    public void a() {
        if (SplashAd.b(this.e) != null) {
            SplashAd.b(this.e).k();
        }
    }

    public void b() {
        if (SplashAd.b(this.e) != null) {
            SplashAd.b(this.e).j();
        }
    }

    public boolean a(int i, KeyEvent keyEvent) {
        return false;
    }
}
