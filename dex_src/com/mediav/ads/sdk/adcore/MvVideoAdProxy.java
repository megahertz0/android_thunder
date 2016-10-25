package com.mediav.ads.sdk.adcore;

import android.app.Activity;
import com.mediav.ads.sdk.interfaces.DynamicObject;
import com.mediav.ads.sdk.interfaces.IMvVideoAd;
import com.mediav.ads.sdk.interfaces.IMvVideoAdOnClickListener;
import com.mediav.ads.sdk.log.MVLog;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.json.JSONObject;

class MvVideoAdProxy implements IMvVideoAd {
    private final DynamicObject dynamicObject;

    public MvVideoAdProxy(DynamicObject dynamicObject) {
        this.dynamicObject = dynamicObject;
    }

    public JSONObject getContent() {
        return (JSONObject) this.dynamicObject.invoke(XZBDevice.FailInServer, null);
    }

    public void onAdPlayStarted() {
        MVLog.d("ADSUPDATE", "MVVIDEOAD_onAdPlayStarted");
        this.dynamicObject.invoke(R.styleable.AppCompatTheme_actionModePopupWindowStyle, null);
    }

    public void onAdPlayExit(int i) {
        MVLog.d("ADSUPDATE", "MVVIDEOAD_onAdPlayExit");
        this.dynamicObject.invoke(R.styleable.AppCompatTheme_textAppearanceLargePopupMenu, Integer.valueOf(i));
    }

    public void onAdPlayFinshed(int i) {
        MVLog.d("ADSUPDATE", "MVVIDEOAD_onAdPlayFinshed");
        this.dynamicObject.invoke(R.styleable.AppCompatTheme_textAppearanceSmallPopupMenu, Integer.valueOf(i));
    }

    public void onAdClicked(Activity activity, int i, IMvVideoAdOnClickListener iMvVideoAdOnClickListener) {
        MVLog.d("ADSUPDATE", "MVVIDEOAD_onAdClicked");
        this.dynamicObject.invoke(R.styleable.AppCompatTheme_dialogTheme, new Object{activity, Integer.valueOf(i), new MvVideoAdOnClickListenerProxy(iMvVideoAdOnClickListener)});
    }
}
