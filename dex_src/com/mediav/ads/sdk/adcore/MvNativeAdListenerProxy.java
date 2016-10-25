package com.mediav.ads.sdk.adcore;

import com.mediav.ads.sdk.interfaces.DynamicObject;
import com.mediav.ads.sdk.interfaces.IMvNativeAdListener;
import com.mediav.ads.sdk.log.MVLog;
import com.xunlei.tdlive.R;
import java.util.ArrayList;
import java.util.List;

class MvNativeAdListenerProxy implements DynamicObject {
    private final IMvNativeAdListener listener;

    public MvNativeAdListenerProxy(IMvNativeAdListener iMvNativeAdListener) {
        this.listener = iMvNativeAdListener;
    }

    public Object invoke(int i, Object obj) {
        switch (i) {
            case R.styleable.AppCompatTheme_actionModeBackground:
                MVLog.d("ADSUPDATE", "MVNATIVEADLISTENER_onNativeAdLoadSucceeded");
                ArrayList arrayList = new ArrayList();
                for (DynamicObject dynamicObject : (List) obj) {
                    arrayList.add(new MvNativeAdProxy(dynamicObject));
                }
                this.listener.onNativeAdLoadSucceeded(arrayList);
                break;
            case R.styleable.AppCompatTheme_actionModeSplitBackground:
                MVLog.d("ADSUPDATE", "MVNATIVEADLISTENER_onNativeAdLoadFailed");
                this.listener.onNativeAdLoadFailed();
                break;
        }
        return null;
    }
}
