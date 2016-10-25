package com.xunlei.tdlive.util;

import com.umeng.socialize.bean.SHARE_MEDIA;

// compiled from: UmengShareHelper.java
/* synthetic */ class ab {
    static final /* synthetic */ int[] a;

    static {
        a = new int[SHARE_MEDIA.values().length];
        try {
            a[SHARE_MEDIA.WEIXIN_CIRCLE.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[SHARE_MEDIA.WEIXIN.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[SHARE_MEDIA.SINA.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[SHARE_MEDIA.QZONE.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            a[SHARE_MEDIA.QQ.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
    }
}
