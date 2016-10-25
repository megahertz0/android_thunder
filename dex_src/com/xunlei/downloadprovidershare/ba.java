package com.xunlei.downloadprovidershare;

import com.taobao.accs.common.Constants;
import com.umeng.a;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xunlei.downloadprovidershare.data.ShareBean;
import com.xunlei.downloadprovidershare.data.ShareBean.OperationType;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: ShareReportHelper.java
public final class ba {
    public static String a(SHARE_MEDIA share_media, ShareBean shareBean) {
        String str = a.d;
        OperationType operationType = shareBean.m;
        if (operationType != OperationType.None) {
            switch (AnonymousClass_1.a[operationType.ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    return "copy";
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    return "system";
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    return Constants.KEY_HTTP_CODE;
                default:
                    return str;
            }
        }
        switch (AnonymousClass_1.b[share_media.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return "wechart";
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return "qq";
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return com.tencent.connect.common.Constants.SOURCE_QZONE;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                return "pengyouquan";
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                return "weibo";
            default:
                return str;
        }
    }
}
