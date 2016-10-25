package com.mediav.ads.sdk.interfaces;

import android.app.Activity;
import org.json.JSONObject;

public interface IMvNativeAd {
    JSONObject getContent();

    void onAdClicked();

    void onAdClicked(Activity activity);

    void onAdShowed();
}
