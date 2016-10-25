package com.qq.e.ads.nativ;

import android.content.res.Configuration;
import android.view.View;

public interface NativeMediaADData {
    void bindView(MediaView mediaView, boolean z);

    void destroy();

    double getAPPPrice();

    int getAPPScore();

    int getAPPStatus();

    int getCurrentPosition();

    String getDesc();

    long getDownloadCount();

    int getDuration();

    String getIconUrl();

    String getImgUrl();

    int getProgress();

    String getTitle();

    boolean isAPP();

    boolean isPlaying();

    boolean isVideoAD();

    boolean isVideoLoaded();

    void onClicked(View view);

    void onConfigurationChanged(Configuration configuration);

    void onExposured(View view);

    void onScroll(int i, View view);

    void play();

    void preLoadVideo();

    void resume();

    void setMediaListener(MediaListener mediaListener);

    void stop();
}
