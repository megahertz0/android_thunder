package com.qq.e.ads.nativ;

public interface MediaListener {
    void onADButtonClicked();

    void onReplayButtonClicked();

    void onVideoComplete();

    void onVideoError(int i);

    void onVideoPause();

    void onVideoReady(long j);

    void onVideoStart();
}
