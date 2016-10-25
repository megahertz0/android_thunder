package com.xunlei.downloadprovidershare;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xunlei.downloadprovidershare.data.ShareBean.OperationType;

// compiled from: ShareReportHelper.java
/* synthetic */ class ba$1 {
    static final /* synthetic */ int[] a;
    static final /* synthetic */ int[] b;

    static {
        b = new int[SHARE_MEDIA.values().length];
        try {
            b[SHARE_MEDIA.WEIXIN.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            b[SHARE_MEDIA.QQ.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            b[SHARE_MEDIA.QZONE.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            b[SHARE_MEDIA.WEIXIN_CIRCLE.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            b[SHARE_MEDIA.SINA.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        a = new int[OperationType.values().length];
        try {
            a[OperationType.CopyUrl.ordinal()] = 1;
        } catch (NoSuchFieldError e6) {
        }
        try {
            a[OperationType.SystemShare.ordinal()] = 2;
        } catch (NoSuchFieldError e7) {
        }
        try {
            a[OperationType.Qr.ordinal()] = 3;
        } catch (NoSuchFieldError e8) {
        }
    }
}
