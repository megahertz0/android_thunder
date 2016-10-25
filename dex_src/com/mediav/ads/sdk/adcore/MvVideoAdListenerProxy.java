package com.mediav.ads.sdk.adcore;

import com.mediav.ads.sdk.interfaces.DynamicObject;
import com.mediav.ads.sdk.interfaces.IMvVideoAdListener;
import com.mediav.ads.sdk.log.MVLog;
import com.xunlei.tdlive.R;
import java.util.ArrayList;
import java.util.List;

class MvVideoAdListenerProxy implements DynamicObject {
    private final IMvVideoAdListener listener;

    public MvVideoAdListenerProxy(IMvVideoAdListener iMvVideoAdListener) {
        this.listener = iMvVideoAdListener;
    }

    public Object invoke(int i, Object obj) {
        switch (i) {
            case R.styleable.AppCompatTheme_borderlessButtonStyle:
                MVLog.d("ADSUPDATE", "MVVIDEOADLISTENER_onVideoAdLoadSucceeded");
                ArrayList arrayList = new ArrayList();
                for (DynamicObject dynamicObject : (List) obj) {
                    arrayList.add(new MvVideoAdProxy(dynamicObject));
                }
                this.listener.onVideoAdLoadSucceeded(arrayList);
                break;
            case R.styleable.AppCompatTheme_dividerVertical:
                MVLog.d("ADSUPDATE", "MVVIDEOADLISTENER_onVideoAdLoadFailed");
                this.listener.onVideoAdLoadFailed();
                break;
        }
        return null;
    }
}
