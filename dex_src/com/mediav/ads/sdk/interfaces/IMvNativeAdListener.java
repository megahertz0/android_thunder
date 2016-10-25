package com.mediav.ads.sdk.interfaces;

import java.util.ArrayList;

public interface IMvNativeAdListener {
    void onNativeAdLoadFailed();

    void onNativeAdLoadSucceeded(ArrayList<IMvNativeAd> arrayList);
}
