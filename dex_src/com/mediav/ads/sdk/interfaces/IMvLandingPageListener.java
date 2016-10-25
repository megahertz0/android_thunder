package com.mediav.ads.sdk.interfaces;

public interface IMvLandingPageListener {
    boolean onAppDownload(String str);

    void onPageClose();

    void onPageLoadFailed();

    void onPageLoadFinished();
}
