package com.mediav.ads.sdk.adcore;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.view.ViewGroup;
import com.mediav.ads.sdk.interfaces.DynamicObject;
import com.mediav.ads.sdk.interfaces.IBridge;
import com.mediav.ads.sdk.interfaces.IMvAdEventListener;
import com.mediav.ads.sdk.interfaces.IMvLandingPageView;
import com.mediav.ads.sdk.interfaces.IMvNativeAdListener;
import com.mediav.ads.sdk.interfaces.IMvVideoAdListener;
import com.mediav.ads.sdk.interfaces.ServiceBridge;
import com.mediav.ads.sdk.log.MVLog;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

class BridgeProxy implements IBridge {
    private final DynamicObject dynamicObject;

    public BridgeProxy(DynamicObject dynamicObject) {
        this.dynamicObject = dynamicObject;
    }

    public Object getInterstitial(Activity activity, String str, Boolean bool) {
        MVLog.d("ADSUPDATE", "BRIDGE_getInterstitial");
        return new MvInterstitialAdProxy((DynamicObject) this.dynamicObject.invoke(XZBDevice.Fail, new Object{activity, str, bool}));
    }

    public Object getBanner(ViewGroup viewGroup, Activity activity, String str, Boolean bool) {
        MVLog.d("ADSUPDATE", "BRIDGE_getBanner");
        return new MvBannerAdProxy((DynamicObject) this.dynamicObject.invoke(XZBDevice.Success, new Object{viewGroup, activity, str, bool}));
    }

    public Object getNativeBanner(ViewGroup viewGroup, Activity activity, String str, Boolean bool) {
        MVLog.d("ADSUPDATE", "BRIDGE_getNativeBanner");
        return new MvNativeBannerAdProxy((DynamicObject) this.dynamicObject.invoke(R.styleable.AppCompatTheme_popupMenuStyle, new Object{viewGroup, activity, str, bool}));
    }

    public Object getFloatingBanner(Activity activity, String str, Boolean bool, Integer num, Integer num2) {
        MVLog.d("ADSUPDATE", "BRIDGE_getFloatingBanner");
        return new MvFloatbannerAdProxy((DynamicObject) this.dynamicObject.invoke(XZBDevice.Upload, new Object{activity, str, bool, num, num2}));
    }

    public void getSplashAd(ViewGroup viewGroup, Activity activity, String str, IMvAdEventListener iMvAdEventListener, Boolean bool, Boolean bool2) {
        MVLog.d("ADSUPDATE", "BRIDGE_getSplashAd");
        this.dynamicObject.invoke(XZBDevice.Predownload, new Object{viewGroup, activity, str, new MvAdEventListenerProxy(iMvAdEventListener), bool, bool2});
    }

    public Object getNativeAdLoader(Activity activity, String str, IMvNativeAdListener iMvNativeAdListener, Boolean bool) {
        MVLog.d("ADSUPDATE", "BRIDGE_getNativeAdLoader");
        return new MvNativeAdLoaderProxy((DynamicObject) this.dynamicObject.invoke(XZBDevice.Delete, new Object{activity, str, new MvNativeAdListenerProxy(iMvNativeAdListener), bool}));
    }

    public Object getVideoAdLoader(Context context, String str, IMvVideoAdListener iMvVideoAdListener, Boolean bool) {
        MVLog.d("ADSUPDATE", "BRIDGE_getVideoAdLoader");
        return new MvVideoAdLoaderProxy((DynamicObject) this.dynamicObject.invoke(R.styleable.Toolbar_titleMarginBottom, new Object{context, str, new MvVideoAdListenerProxy(iMvVideoAdListener), bool}));
    }

    public void activityDestroy(Activity activity) {
        MVLog.d("ADSUPDATE", "BRIDGE_activityDestroy");
        this.dynamicObject.invoke(R.styleable.Toolbar_maxButtonHeight, activity);
    }

    public ServiceBridge getServiceBridge(Service service) {
        MVLog.d("ADSUPDATE", "BRIDGE_getServiceBridge");
        return (ServiceBridge) this.dynamicObject.invoke(R.styleable.Toolbar_collapseIcon, service);
    }

    public void setLogSwitch(boolean z) {
        MVLog.d("ADSUPDATE", "BRIDGE_setLogSwitch");
        this.dynamicObject.invoke(R.styleable.Toolbar_collapseContentDescription, Boolean.valueOf(z));
    }

    public void setLandingPageView(IMvLandingPageView iMvLandingPageView) {
        MVLog.d("ADSUPDATE", "BRIDGE_setLandingPageView");
        this.dynamicObject.invoke(R.styleable.Toolbar_navigationIcon, new MvLandingPageViewProxy(iMvLandingPageView));
    }
}
