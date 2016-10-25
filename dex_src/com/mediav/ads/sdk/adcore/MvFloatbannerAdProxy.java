package com.mediav.ads.sdk.adcore;

import com.mediav.ads.sdk.interfaces.DynamicObject;
import com.mediav.ads.sdk.interfaces.IMvAdEventListener;
import com.mediav.ads.sdk.interfaces.IMvFloatbannerAd;
import com.mediav.ads.sdk.log.MVLog;
import com.xunlei.tdlive.R;

class MvFloatbannerAdProxy implements IMvFloatbannerAd {
    private final DynamicObject dynamicObject;

    public MvFloatbannerAdProxy(DynamicObject dynamicObject) {
        this.dynamicObject = dynamicObject;
    }

    public void closeAds() {
        MVLog.d("ADSUPDATE", "MVFLOATBANNERAD_closeAds");
        this.dynamicObject.invoke(R.styleable.Toolbar_navigationContentDescription, null);
    }

    public void setAdEventListener(Object obj) {
        MVLog.d("ADSUPDATE", "MVFLOATBANNERAD_setAdEventListener");
        this.dynamicObject.invoke(R.styleable.Toolbar_logoDescription, new MvAdEventListenerProxy((IMvAdEventListener) obj));
    }
}
