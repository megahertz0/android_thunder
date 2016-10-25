package com.uc.addon.sdk.remote;

import android.webkit.ValueCallback;
import com.uc.addon.sdk.remote.protocol.JSParam;
import com.uc.addon.sdk.remote.protocol.TabProperties;
import com.uc.addon.sdk.remote.protocol.TabUpdateProperties;

public interface Tabs {
    public static final int TAB_CREATE_FAILED = -1;
    public static final int TAB_CREATE_REACH_MAX_COUNT = -2;

    int create(String str, boolean z);

    void getAllTabs(ValueCallback valueCallback);

    TabProperties getCurrentTab();

    TabProperties getTabProperties(int i);

    void goBack(int i);

    void goForward(int i);

    void loadJavascript(int i, JSParam jSParam, AbstractJSExtension abstractJSExtension);

    void pageDown(int i, boolean z);

    void pageUp(int i, boolean z);

    void remove(int i);

    void update(int i, TabUpdateProperties tabUpdateProperties);

    void zoomIn(int i);

    void zoomOut(int i);
}
