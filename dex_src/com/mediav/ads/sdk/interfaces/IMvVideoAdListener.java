package com.mediav.ads.sdk.interfaces;

import java.util.ArrayList;

public interface IMvVideoAdListener {
    void onVideoAdLoadFailed();

    void onVideoAdLoadSucceeded(ArrayList<IMvVideoAd> arrayList);
}
