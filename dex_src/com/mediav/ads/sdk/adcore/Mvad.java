package com.mediav.ads.sdk.adcore;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import com.mediav.ads.sdk.interfaces.IBridge;
import com.mediav.ads.sdk.interfaces.IMvAdEventListener;
import com.mediav.ads.sdk.interfaces.IMvBannerAd;
import com.mediav.ads.sdk.interfaces.IMvFloatbannerAd;
import com.mediav.ads.sdk.interfaces.IMvInterstitialAd;
import com.mediav.ads.sdk.interfaces.IMvLandingPageView;
import com.mediav.ads.sdk.interfaces.IMvNativeAdListener;
import com.mediav.ads.sdk.interfaces.IMvNativeAdLoader;
import com.mediav.ads.sdk.interfaces.IMvNativeBannerAd;
import com.mediav.ads.sdk.interfaces.IMvVideoAdListener;
import com.mediav.ads.sdk.interfaces.IMvVideoAdLoader;
import com.mediav.ads.sdk.log.MVLog;

public class Mvad {
    private static IMvFloatbannerAd floatbannerAd;
    private static IMvInterstitialAd interstitialAd;
    private static IBridge mvad;

    public enum FLOAT_BANNER_SIZE {
        SIZE_DEFAULT,
        SIZE_MATCH_PARENT,
        SIZE_640X100,
        SIZE_936x120,
        SIZE_728x90
    }

    public enum FLOAT_LOCATION {
        TOP,
        BOTTOM
    }

    static {
        interstitialAd = null;
        floatbannerAd = null;
        mvad = null;
    }

    public static IMvBannerAd showBanner(ViewGroup viewGroup, Activity activity, String str, Boolean bool) {
        MVLog.i("MVAD", "Show Banner");
        if (mvad == null) {
            IBridge bridge = UpdateBridge.getBridge(activity);
            mvad = bridge;
            if (bridge == null) {
                return null;
            }
        }
        try {
            return (IMvBannerAd) mvad.getBanner(viewGroup, activity, str, bool);
        } catch (Throwable th) {
            MVLog.e(new StringBuilder("\u521d\u59cb\u5316\u6a2a\u5e45\u5931\u8d25:").append(th.getMessage()).toString());
            return null;
        }
    }

    public static IMvNativeBannerAd showNativeBanner(ViewGroup viewGroup, Activity activity, String str, Boolean bool) {
        MVLog.i("MVAD", "Show NativeBanner");
        if (mvad == null) {
            IBridge bridge = UpdateBridge.getBridge(activity);
            mvad = bridge;
            if (bridge == null) {
                return null;
            }
        }
        try {
            return (IMvNativeBannerAd) mvad.getNativeBanner(viewGroup, activity, str, bool);
        } catch (Throwable th) {
            MVLog.e(new StringBuilder("\u521d\u59cb\u5316\u5355\u54c1\u5931\u8d25:").append(th.getMessage()).toString());
            return null;
        }
    }

    public static IMvFloatbannerAd showFloatbannerAd(Activity activity, String str, Boolean bool, FLOAT_BANNER_SIZE float_banner_size, FLOAT_LOCATION float_location) {
        MVLog.i("MVAD", "Show Float Banner");
        if (mvad == null) {
            IBridge bridge = UpdateBridge.getBridge(activity);
            mvad = bridge;
            if (bridge == null) {
                return null;
            }
        }
        try {
            if (floatbannerAd == null) {
                floatbannerAd = (IMvFloatbannerAd) mvad.getFloatingBanner(activity, str, bool, Integer.valueOf(float_banner_size.ordinal()), Integer.valueOf(float_location.ordinal()));
            } else {
                mvad.getFloatingBanner(activity, str, bool, Integer.valueOf(float_banner_size.ordinal()), Integer.valueOf(float_location.ordinal()));
            }
            return floatbannerAd;
        } catch (Throwable th) {
            MVLog.e(new StringBuilder("\u521d\u59cb\u5316\u6d6e\u52a8\u6a2a\u5e45\u5931\u8d25:").append(th.getMessage()).toString());
            return null;
        }
    }

    public static IMvFloatbannerAd closeFloatbannerAd(Activity activity) {
        MVLog.i("MVAD", "Close Float Banner");
        if (floatbannerAd != null) {
            floatbannerAd.closeAds();
        }
        return null;
    }

    public static void showSplashAd(ViewGroup viewGroup, Activity activity, String str, IMvAdEventListener iMvAdEventListener, Boolean bool, Boolean bool2) {
        MVLog.i("MVAD", "Show SplashAd");
        if (mvad == null) {
            IBridge bridge = UpdateBridge.getBridge(activity);
            mvad = bridge;
            if (bridge == null) {
                return;
            }
        }
        try {
            mvad.getSplashAd(viewGroup, activity, str, iMvAdEventListener, bool, bool2);
        } catch (Throwable th) {
            MVLog.e(new StringBuilder("\u521d\u59cb\u5316\u5f00\u5c4f\u5931\u8d25:").append(th.getMessage()).toString());
        }
    }

    public static IMvInterstitialAd showInterstitial(Activity activity, String str, Boolean bool) {
        MVLog.i("MVAD", "Show Interstitial");
        if (mvad == null) {
            IBridge bridge = UpdateBridge.getBridge(activity);
            mvad = bridge;
            if (bridge == null) {
                return null;
            }
        }
        try {
            if (interstitialAd == null) {
                interstitialAd = (IMvInterstitialAd) mvad.getInterstitial(activity, str, bool);
            } else {
                interstitialAd.showAds(activity);
            }
            return interstitialAd;
        } catch (Throwable th) {
            MVLog.e(new StringBuilder("\u521d\u59cb\u5316\u63d2\u5c4f\u5931\u8d25:").append(th.getMessage()).toString());
            return null;
        }
    }

    public static IMvInterstitialAd closeInterstitial(Activity activity) {
        MVLog.i("MVAD", "Close Interstitial");
        if (interstitialAd != null) {
            interstitialAd.closeAds();
        }
        return null;
    }

    public static IMvNativeAdLoader initNativeAdLoader(Activity activity, String str, IMvNativeAdListener iMvNativeAdListener, Boolean bool) {
        MVLog.i("MVAD", new StringBuilder("initNativeAdLoader ").append(str).toString());
        if (mvad == null) {
            IBridge bridge = UpdateBridge.getBridge(activity.getApplicationContext());
            mvad = bridge;
            if (bridge == null) {
                return null;
            }
        }
        try {
            return (IMvNativeAdLoader) mvad.getNativeAdLoader(activity, str, iMvNativeAdListener, bool);
        } catch (Throwable th) {
            MVLog.e(new StringBuilder("\u521d\u59cb\u5316\u539f\u751f\u5e7f\u544a\u5931\u8d25:").append(th.getMessage()).toString());
            return null;
        }
    }

    public static IMvVideoAdLoader initVideoAdLoader(Context context, String str, IMvVideoAdListener iMvVideoAdListener, Boolean bool) {
        MVLog.i("MVAD", new StringBuilder("initVideoAdLoader ").append(str).toString());
        if (mvad == null) {
            IBridge bridge = UpdateBridge.getBridge(context);
            mvad = bridge;
            if (bridge == null) {
                return null;
            }
        }
        try {
            return (IMvVideoAdLoader) mvad.getVideoAdLoader(context, str, iMvVideoAdListener, bool);
        } catch (Throwable th) {
            MVLog.d(new StringBuilder("\u521d\u59cb\u5316\u89c6\u9891\u5e7f\u544a\u5931\u8d25\uff1a").append(th.getMessage()).toString());
            return null;
        }
    }

    public static void activityDestroy(Activity activity) {
        if (mvad != null) {
            mvad.activityDestroy(activity);
            floatbannerAd = null;
            interstitialAd = null;
        }
    }

    public static void setLogSwitch(Context context, boolean z) {
        MVLog.i("MVAD", new StringBuilder("Set log switch: ").append(z).toString());
        if (mvad == null) {
            IBridge bridge = UpdateBridge.getBridge(context);
            mvad = bridge;
            if (bridge == null) {
                return;
            }
        }
        MVLog.logcatSwitch = z;
        mvad.setLogSwitch(z);
    }

    public static void setLandingPageView(Context context, IMvLandingPageView iMvLandingPageView) {
        MVLog.i("MVAD", "Set custom landing page view");
        if (mvad == null) {
            IBridge bridge = UpdateBridge.getBridge(context);
            mvad = bridge;
            if (bridge == null) {
                return;
            }
        }
        mvad.setLandingPageView(iMvLandingPageView);
    }
}
