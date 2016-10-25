package com.mediav.ads.sdk.interfaces;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.view.ViewGroup;

public interface IBridge {
    void activityDestroy(Activity activity);

    Object getBanner(ViewGroup viewGroup, Activity activity, String str, Boolean bool);

    Object getFloatingBanner(Activity activity, String str, Boolean bool, Integer num, Integer num2);

    Object getInterstitial(Activity activity, String str, Boolean bool);

    Object getNativeAdLoader(Activity activity, String str, IMvNativeAdListener iMvNativeAdListener, Boolean bool);

    Object getNativeBanner(ViewGroup viewGroup, Activity activity, String str, Boolean bool);

    ServiceBridge getServiceBridge(Service service);

    void getSplashAd(ViewGroup viewGroup, Activity activity, String str, IMvAdEventListener iMvAdEventListener, Boolean bool, Boolean bool2);

    Object getVideoAdLoader(Context context, String str, IMvVideoAdListener iMvVideoAdListener, Boolean bool);

    void setLandingPageView(IMvLandingPageView iMvLandingPageView);

    void setLogSwitch(boolean z);
}
