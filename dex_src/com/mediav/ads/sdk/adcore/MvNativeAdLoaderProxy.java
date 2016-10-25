package com.mediav.ads.sdk.adcore;

import com.mediav.ads.sdk.interfaces.DynamicObject;
import com.mediav.ads.sdk.interfaces.IMvAdAttributes;
import com.mediav.ads.sdk.interfaces.IMvNativeAdLoader;
import com.mediav.ads.sdk.log.MVLog;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashSet;

class MvNativeAdLoaderProxy implements IMvNativeAdLoader {
    private final DynamicObject dynamicObject;

    public MvNativeAdLoaderProxy(DynamicObject dynamicObject) {
        this.dynamicObject = dynamicObject;
    }

    public void loadAds() {
        MVLog.d("ADSUPDATE", "MVNATIVEADLOADER_loadAds");
        this.dynamicObject.invoke(R.styleable.AppCompatTheme_actionModeCloseDrawable, null);
    }

    public void loadAds(int i) {
        MVLog.d("ADSUPDATE", "MVNATIVEADLOADER_loadAds_2");
        this.dynamicObject.invoke(R.styleable.AppCompatTheme_actionModeCutDrawable, Integer.valueOf(i));
    }

    public void setKeywords(HashSet<String> hashSet) {
        MVLog.d("ADSUPDATE", "MVNATIVEADLOADER_setKeywords");
        this.dynamicObject.invoke(R.styleable.AppCompatTheme_actionModePasteDrawable, hashSet);
    }

    public void clearKeywords() {
        MVLog.d("ADSUPDATE", "MVNATIVEADLOADER_clearKeywords");
        this.dynamicObject.invoke(R.styleable.AppCompatTheme_actionModeSelectAllDrawable, null);
    }

    public void setAdAttributes(IMvAdAttributes iMvAdAttributes) {
        MVLog.d("ADSUPDATE", "MVNATIVEADLOADER_setAdAttributes");
        this.dynamicObject.invoke(R.styleable.AppCompatTheme_actionModeShareDrawable, new MvAdAttributesProxy(iMvAdAttributes));
    }

    public void clearAdAttributes() {
        MVLog.d("ADSUPDATE", "MVNATIVEADLOADER_clearAdAttributes");
        this.dynamicObject.invoke(XZBDevice.WaitInServer, null);
    }
}
