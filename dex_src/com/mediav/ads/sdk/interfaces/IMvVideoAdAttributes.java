package com.mediav.ads.sdk.interfaces;

import java.util.HashSet;

public interface IMvVideoAdAttributes extends IMvAdAttributes {
    void setCast(HashSet<String> hashSet);

    void setCategory(int i);

    void setEpisode(int i);

    void setRegion(String str);

    void setSource(String str);

    void setTitle(String str);

    void setYear(int i);
}
