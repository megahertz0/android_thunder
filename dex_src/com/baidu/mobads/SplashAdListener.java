package com.baidu.mobads;

public interface SplashAdListener {
    void onAdClick();

    void onAdDismissed();

    void onAdFailed(String str);

    void onAdPresent();
}
