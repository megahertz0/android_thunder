package com.mediav.ads.sdk.adcore;

import android.app.Activity;
import com.mediav.ads.sdk.interfaces.DynamicObject;
import com.mediav.ads.sdk.interfaces.IMvAdEventListener;
import com.mediav.ads.sdk.interfaces.IMvNativeBannerAd;
import com.mediav.ads.sdk.log.MVLog;
import com.xunlei.tdlive.R;

class MvNativeBannerAdProxy implements IMvNativeBannerAd {
    private final DynamicObject dynamicObject;

    public MvNativeBannerAdProxy(DynamicObject dynamicObject) {
        this.dynamicObject = dynamicObject;
    }

    public void closeAds() {
        MVLog.d("ADSUPDATE", "MVNATIVEBANNERAD_closeAds");
        this.dynamicObject.invoke(R.styleable.AppCompatTheme_popupWindowStyle, null);
    }

    public void showAds(Activity activity) {
        MVLog.d("ADSUPDATE", "MVNATIVEBANNERAD_showAds");
        this.dynamicObject.invoke(R.styleable.AppCompatTheme_editTextColor, activity);
    }

    public void setAdEventListener(Object obj) {
        MVLog.d("ADSUPDATE", "MVNATIVEBANNERAD_setAdEventListener");
        this.dynamicObject.invoke(R.styleable.AppCompatTheme_editTextBackground, new MvAdEventListenerProxy((IMvAdEventListener) obj));
    }
}
