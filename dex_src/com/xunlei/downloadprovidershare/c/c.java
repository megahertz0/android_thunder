package com.xunlei.downloadprovidershare.c;

import android.app.Activity;
import android.text.TextUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovidershare.data.ShareBean;

// compiled from: SinaShareProxy.java
public final class c extends a {
    public final void a(SHARE_MEDIA share_media, Activity activity, ShareBean shareBean, UMShareListener uMShareListener) {
        super.a(share_media, activity, shareBean, uMShareListener);
        a(activity, shareBean, uMShareListener);
    }

    public static void a(Activity activity, ShareBean shareBean, UMShareListener uMShareListener) {
        shareBean.f = "webo";
        String str = shareBean.c;
        if (TextUtils.isEmpty(str)) {
            str = "\u8fc5\u96f7\u5206\u4eab";
        }
        ShareAction shareAction = new ShareAction(activity);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('\u3010').append(str).append('\u3011');
        stringBuilder.append("\u5206\u4eab\u81ea@\u624b\u673a\u8fc5\u96f7 ");
        UMImage a = a(activity, shareBean);
        shareAction.setPlatform(SHARE_MEDIA.SINA).setCallback(uMShareListener);
        shareAction.withText(stringBuilder.toString());
        shareAction.withTargetUrl(shareBean.a);
        shareAction.withMedia(a);
        try {
            shareAction.share();
        } catch (IllegalArgumentException e) {
            XLToast.b(activity, XLToastType.XLTOAST_TYPE_ALARM, "\u5206\u4eab\u5931\u8d25");
        }
    }
}
