package com.qq.e.ads.splash;

public interface SplashADListener {
    void onADClicked();

    void onADDismissed();

    void onADPresent();

    void onNoAD(int i);
}
