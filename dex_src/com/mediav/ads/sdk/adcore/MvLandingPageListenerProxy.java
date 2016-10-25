package com.mediav.ads.sdk.adcore;

import com.mediav.ads.sdk.interfaces.DynamicObject;
import com.mediav.ads.sdk.interfaces.IMvLandingPageListener;
import com.mediav.ads.sdk.log.MVLog;
import com.xunlei.tdlive.R;

class MvLandingPageListenerProxy implements IMvLandingPageListener {
    private final DynamicObject dynamicObject;

    public MvLandingPageListenerProxy(DynamicObject dynamicObject) {
        this.dynamicObject = dynamicObject;
    }

    public void onPageClose() {
        MVLog.d("ADSUPDATE", "MVLANDINGPAGELISTENER_onPageClose");
        this.dynamicObject.invoke(R.styleable.AppCompatTheme_homeAsUpIndicator, null);
    }

    public void onPageLoadFinished() {
        MVLog.d("ADSUPDATE", "MVLANDINGPAGELISTENER_onPageLoadFinished");
        this.dynamicObject.invoke(R.styleable.AppCompatTheme_actionButtonStyle, null);
    }

    public void onPageLoadFailed() {
        MVLog.d("ADSUPDATE", "MVLANDINGPAGELISTENER_onPageLoadFailed");
        this.dynamicObject.invoke(R.styleable.AppCompatTheme_buttonBarStyle, null);
    }

    public boolean onAppDownload(String str) {
        MVLog.d("ADSUPDATE", "MVLANDINGPAGELISTENER_onAppDownload");
        return ((Boolean) this.dynamicObject.invoke(R.styleable.AppCompatTheme_toolbarNavigationButtonStyle, str)).booleanValue();
    }
}
