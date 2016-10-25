package com.xunlei.downloadprovider.ad.common;

import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;

/* synthetic */ class CommonConst$1 {
    static final /* synthetic */ int[] a;

    static {
        a = new int[AD_TYPE.values().length];
        try {
            a[AD_TYPE.SOURCE_GDT_FLAG.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[AD_TYPE.SOURCE_BAIDU_FLAG.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[AD_TYPE.SOURCE_QIHU_FLAG.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[AD_TYPE.SOURCE_INMOBI_NATIVE_FLAG.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            a[AD_TYPE.SOURCE_SSP_FLAG.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            a[AD_TYPE.SOURCE_SSP_DEFAULT_FLAG.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
    }
}
