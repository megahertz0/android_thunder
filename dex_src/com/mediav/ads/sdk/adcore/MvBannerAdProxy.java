package com.mediav.ads.sdk.adcore;

import android.app.Activity;
import com.mediav.ads.sdk.interfaces.DynamicObject;
import com.mediav.ads.sdk.interfaces.IMvAdEventListener;
import com.mediav.ads.sdk.interfaces.IMvBannerAd;
import com.mediav.ads.sdk.log.MVLog;
import com.xunlei.xiazaibao.sdk.XZBDevice;

class MvBannerAdProxy implements IMvBannerAd {
    private final DynamicObject dynamicObject;

    public MvBannerAdProxy(DynamicObject dynamicObject) {
        this.dynamicObject = dynamicObject;
    }

    public void closeAds() {
        MVLog.d("ADSUPDATE", "MVBANNERAD_CLOSEADS");
        this.dynamicObject.invoke(1, null);
    }

    public void showAds(Activity activity) {
        MVLog.d("ADSUPDATE", "MVBANNERAD_SHOWADS");
        this.dynamicObject.invoke(XZBDevice.DOWNLOAD_LIST_RECYCLE, activity);
    }

    public void setAdEventListener(Object obj) {
        MVLog.d("ADSUPDATE", "MVBANNERAD_SETADEVENTLISTENER");
        this.dynamicObject.invoke(XZBDevice.DOWNLOAD_LIST_FAILED, new MvAdEventListenerProxy((IMvAdEventListener) obj));
    }
}
