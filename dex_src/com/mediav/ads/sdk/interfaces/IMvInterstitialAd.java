package com.mediav.ads.sdk.interfaces;

import android.app.Activity;

public interface IMvInterstitialAd {
    void closeAds();

    void setAdEventListener(Object obj);

    void showAds(Activity activity);
}
