package com.xunlei.downloadprovider.web.sniff.widget;

import com.xunlei.downloadprovider.web.sniff.SniffSuffixTypeDataManager.SuffixDataType;

/* synthetic */ class SniffSuffixSettingItemView$1 {
    static final /* synthetic */ int[] a;

    static {
        a = new int[SuffixDataType.values().length];
        try {
            a[SuffixDataType.video.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[SuffixDataType.torrent.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[SuffixDataType.zip.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[SuffixDataType.music.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            a[SuffixDataType.app.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            a[SuffixDataType.doc.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
    }
}
