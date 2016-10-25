package com.xunlei.common.accelerator.js;

import android.webkit.WebView;
import com.xunlei.common.accelerator.js.KNJsInterface.JumpLisenter;

public class KNJsUtils {
    private static KNJsUtils knJsUtils;

    private KNJsUtils() {
    }

    public static KNJsUtils getKnJsUtils() {
        if (knJsUtils == null) {
            synchronized (KNJsUtils.class) {
                if (knJsUtils == null) {
                    knJsUtils = new KNJsUtils();
                }
            }
        }
        return knJsUtils;
    }

    private KNJsProxy getProxy() {
        return KNJsProxy.getKNJsProxy();
    }

    public boolean initJsInterface(WebView webView, JumpLisenter jumpLisenter) {
        return getProxy().initJsInterface(webView, jumpLisenter);
    }

    public boolean uninitJsInterface() {
        return getProxy().uninitJsInterface();
    }

    public String getBandInfo() {
        return getProxy().getBandInfo();
    }

    public String getTryInfo() {
        return getProxy().getTryInfo();
    }

    public String getUserInfo() {
        return getProxy().getUserInfo();
    }

    public int getRemainTime() {
        return getProxy().getRemainTime();
    }

    public void updateUserInfo() {
        getProxy().updateUserInfo();
    }
}
