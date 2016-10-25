package com.xunlei.downloadprovidershare.c;

import android.app.Activity;
import android.text.TextUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovidershare.az;
import com.xunlei.downloadprovidershare.data.ShareBean;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: WeixinShareProxy.java
public final class d extends a {
    public static void b(SHARE_MEDIA share_media, Activity activity, ShareBean shareBean, UMShareListener uMShareListener) {
        if (az.a().d.isWXAppInstalled()) {
            switch (AnonymousClass_1.a[share_media.ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    a(activity, shareBean, uMShareListener);
                    return;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    b(activity, shareBean, uMShareListener);
                    return;
                default:
                    return;
            }
        }
        XLToast.b(activity, XLToastType.XLTOAST_TYPE_ALARM, "\u5c1a\u672a\u5b89\u88c5\u5fae\u4fe1");
    }

    public final void a(SHARE_MEDIA share_media, Activity activity, ShareBean shareBean, UMShareListener uMShareListener) {
        super.a(share_media, activity, shareBean, uMShareListener);
        switch (AnonymousClass_1.a[share_media.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                a(activity, shareBean, uMShareListener);
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                b(activity, shareBean, uMShareListener);
            default:
                break;
        }
    }

    private static void a(Activity activity, ShareBean shareBean, UMShareListener uMShareListener) {
        shareBean.f = "wechart";
        String str = shareBean.c;
        String str2 = shareBean.d;
        if (TextUtils.isEmpty(str)) {
            str = "\u8fc5\u96f7\u5206\u4eab";
        }
        ShareAction shareAction = new ShareAction(activity);
        UMImage a = a(activity, shareBean);
        shareAction.setPlatform(SHARE_MEDIA.WEIXIN).setCallback(uMShareListener);
        new StringBuilder("ImageUrl: ").append(shareBean.b);
        if (shareBean.g) {
            shareAction.withTitle(str);
            shareAction.withText(str2);
            UMVideo uMVideo = new UMVideo(shareBean.a);
            uMVideo.setThumb(a);
            shareAction.withMedia(uMVideo);
        } else {
            shareAction.withTitle(str).withText(str2).withTargetUrl(shareBean.a).withMedia(a);
        }
        shareAction.share();
    }

    private static void b(Activity activity, ShareBean shareBean, UMShareListener uMShareListener) {
        shareBean.f = "pengyouquan";
        String str = shareBean.c;
        String str2 = shareBean.d;
        if (TextUtils.isEmpty(str)) {
            str = "\u8fc5\u96f7\u5206\u4eab";
        }
        ShareAction shareAction = new ShareAction(activity);
        UMImage a = a(activity, shareBean);
        shareAction.setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).setCallback(uMShareListener);
        if (shareBean.g) {
            shareAction.withTitle(str);
            shareAction.withText(str2);
            UMVideo uMVideo = new UMVideo(shareBean.a);
            uMVideo.setThumb(a);
            shareAction.withMedia(uMVideo);
        } else {
            shareAction.withTitle(str).withText(str2).withTargetUrl(shareBean.a).withMedia(a);
        }
        shareAction.share();
    }
}
