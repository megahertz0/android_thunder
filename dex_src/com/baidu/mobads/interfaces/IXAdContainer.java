package com.baidu.mobads.interfaces;

import android.annotation.SuppressLint;
import android.view.KeyEvent;
import android.view.View;
import java.util.HashMap;

public interface IXAdContainer {
    void destroy();

    void dispose();

    IXAdContainerContext getAdContainerContext();

    View getAdView();

    double getDuration();

    HashMap<String, String> getParameters();

    double getPlayheadTime();

    String getRemoteVersion();

    void load();

    void onAttachedToWindow();

    @SuppressLint({"MissingSuperCall"})
    void onDetachedFromWindow();

    void onWindowFocusChanged(boolean z);

    void onWindowVisibilityChanged(int i);

    void pause();

    Boolean processKeyEvent(int i, KeyEvent keyEvent);

    void resize(int i, int i2);

    void resume();

    void setParameters(HashMap<String, String> hashMap);

    void start();

    void stop();
}
