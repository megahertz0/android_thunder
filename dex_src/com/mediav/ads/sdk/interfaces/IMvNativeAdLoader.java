package com.mediav.ads.sdk.interfaces;

import java.util.HashSet;

public interface IMvNativeAdLoader {
    void clearAdAttributes();

    void clearKeywords();

    void loadAds();

    void loadAds(int i);

    void setAdAttributes(IMvAdAttributes iMvAdAttributes);

    void setKeywords(HashSet<String> hashSet);
}
