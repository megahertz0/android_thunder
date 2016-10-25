package com.mediav.ads.sdk.interfaces;

import android.app.Activity;
import org.json.JSONObject;

public interface IMvVideoAd {
    JSONObject getContent();

    void onAdClicked(Activity activity, int i, IMvVideoAdOnClickListener iMvVideoAdOnClickListener);

    void onAdPlayExit(int i);

    void onAdPlayFinshed(int i);

    void onAdPlayStarted();
}
