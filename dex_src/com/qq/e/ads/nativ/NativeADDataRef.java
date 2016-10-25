package com.qq.e.ads.nativ;

import android.view.View;

public interface NativeADDataRef {
    double getAPPPrice();

    int getAPPScore();

    int getAPPStatus();

    String getDesc();

    long getDownloadCount();

    String getIconUrl();

    String getImgUrl();

    int getProgress();

    String getTitle();

    boolean isAPP();

    void onClicked(View view);

    void onExposured(View view);
}
