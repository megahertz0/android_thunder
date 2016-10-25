package com.mediav.ads.sdk.interfaces;

public interface IMvAdEventListener {
    void onAdviewClicked();

    void onAdviewClosed();

    void onAdviewDestroyed();

    void onAdviewDismissedLandpage();

    void onAdviewGotAdFail();

    void onAdviewGotAdSucceed();

    void onAdviewIntoLandpage();

    void onAdviewRendered();
}
