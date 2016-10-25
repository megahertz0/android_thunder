package com.mediav.ads.sdk.interfaces;

public interface IMvVideoAdLoader {
    void clearAdAttributes();

    void loadAds();

    void setAdAttributes(IMvAdAttributes iMvAdAttributes);
}
