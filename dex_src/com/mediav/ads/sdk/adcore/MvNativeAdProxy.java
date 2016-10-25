package com.mediav.ads.sdk.adcore;

import android.app.Activity;
import com.mediav.ads.sdk.interfaces.DynamicObject;
import com.mediav.ads.sdk.interfaces.IMvNativeAd;
import com.mediav.ads.sdk.log.MVLog;
import com.xunlei.tdlive.R;
import org.json.JSONObject;

class MvNativeAdProxy implements IMvNativeAd {
    private final DynamicObject dynamicObject;

    public MvNativeAdProxy(DynamicObject dynamicObject) {
        this.dynamicObject = dynamicObject;
    }

    public JSONObject getContent() {
        MVLog.d("ADSUPDATE", "MVNATIVEAD_getContent");
        return (JSONObject) this.dynamicObject.invoke(R.styleable.AppCompatTheme_actionMenuTextColor, null);
    }

    public void onAdShowed() {
        MVLog.d("ADSUPDATE", "MVNATIVEAD_onAdShowed");
        this.dynamicObject.invoke(R.styleable.AppCompatTheme_actionModeStyle, null);
    }

    public void onAdClicked() {
        MVLog.d("ADSUPDATE", "MVNATIVEAD_onAdClicked");
        this.dynamicObject.invoke(R.styleable.AppCompatTheme_actionModeCloseButtonStyle, null);
    }

    public void onAdClicked(Activity activity) {
        MVLog.d("ADSUPDATE", "MVNATIVEAD_onAdClicked");
        this.dynamicObject.invoke(R.styleable.AppCompatTheme_actionModeCloseButtonStyle, activity);
    }
}
