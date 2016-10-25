package com.mediav.ads.sdk.adcore;

import com.mediav.ads.sdk.interfaces.DynamicObject;
import com.mediav.ads.sdk.interfaces.IMvAdAttributes;
import com.mediav.ads.sdk.interfaces.IMvVideoAdLoader;
import com.mediav.ads.sdk.log.MVLog;
import com.xunlei.tdlive.R;

class MvVideoAdLoaderProxy implements IMvVideoAdLoader {
    private final DynamicObject dynamicObject;

    public MvVideoAdLoaderProxy(DynamicObject dynamicObject) {
        this.dynamicObject = dynamicObject;
    }

    public void loadAds() {
        MVLog.d("ADSUPDATE", "MVVIDEOADLOADER_loadAds");
        this.dynamicObject.invoke(R.styleable.AppCompatTheme_buttonBarButtonStyle, null);
    }

    public void setAdAttributes(IMvAdAttributes iMvAdAttributes) {
        MVLog.d("ADSUPDATE", "MVVIDEOADLOADER_setAdAttributes");
        this.dynamicObject.invoke(R.styleable.AppCompatTheme_selectableItemBackground, new MvAdAttributesProxy(iMvAdAttributes));
    }

    public void clearAdAttributes() {
        MVLog.d("ADSUPDATE", "MVVIDEOADLOADER_clearAdAttributes");
        this.dynamicObject.invoke(R.styleable.AppCompatTheme_selectableItemBackgroundBorderless, null);
    }
}
