package com.mediav.ads.sdk.adcore;

import android.content.Context;
import com.mediav.ads.sdk.interfaces.DynamicObject;
import com.mediav.ads.sdk.interfaces.IMvLandingPageView;
import com.mediav.ads.sdk.log.MVLog;
import com.xunlei.tdlive.R;

class MvLandingPageViewProxy implements DynamicObject {
    private final IMvLandingPageView landingPageView;

    public MvLandingPageViewProxy(IMvLandingPageView iMvLandingPageView) {
        this.landingPageView = iMvLandingPageView;
    }

    public Object invoke(int i, Object obj) {
        switch (i) {
            case R.styleable.AppCompatTheme_spinnerDropDownItemStyle:
                MVLog.d("ADSUPDATE", "MVLANDINGPAGEVIEW_open");
                Object[] objArr = (Object[]) obj;
                this.landingPageView.open((Context) objArr[0], (String) objArr[1], new MvLandingPageListenerProxy((DynamicObject) objArr[2]));
                break;
        }
        return null;
    }
}
