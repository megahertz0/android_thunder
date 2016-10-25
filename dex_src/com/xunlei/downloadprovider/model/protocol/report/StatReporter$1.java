package com.xunlei.downloadprovider.model.protocol.report;

import com.xunlei.downloadprovider.vod.ui.VodPlayerMenuPopupWindow.VideoSize;

/* synthetic */ class StatReporter$1 {
    static final /* synthetic */ int[] a;

    static {
        a = new int[VideoSize.values().length];
        try {
            a[VideoSize.SIZE_FULL.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[VideoSize.SIZE_100.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[VideoSize.SIZE_75.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[VideoSize.SIZE_50.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
    }
}
