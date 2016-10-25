package com.mediav.ads.sdk.adcore;

import android.app.Activity;
import com.mediav.ads.sdk.interfaces.DynamicObject;
import com.mediav.ads.sdk.interfaces.IMvAdEventListener;
import com.mediav.ads.sdk.interfaces.IMvInterstitialAd;
import com.mediav.ads.sdk.log.MVLog;
import com.xunlei.tdlive.R;

class MvInterstitialAdProxy implements IMvInterstitialAd {
    private final DynamicObject dynamicObject;

    public MvInterstitialAdProxy(DynamicObject dynamicObject) {
        this.dynamicObject = dynamicObject;
    }

    public void closeAds() {
        MVLog.d("ADSUPDATE", "MVINTERSTITIALAD_closeAds");
        this.dynamicObject.invoke(R.styleable.Toolbar_titleTextColor, null);
    }

    public void showAds(Activity activity) {
        MVLog.d("ADSUPDATE", "MVINTERSTITIALAD_showAds");
        this.dynamicObject.invoke(R.styleable.Toolbar_subtitleTextColor, activity);
    }

    public void setAdEventListener(Object obj) {
        MVLog.d("ADSUPDATE", "MVINTERSTITIALAD_setAdEventListener");
        this.dynamicObject.invoke(R.styleable.AppCompatTheme_actionMenuTextAppearance, new MvAdEventListenerProxy((IMvAdEventListener) obj));
    }
}
