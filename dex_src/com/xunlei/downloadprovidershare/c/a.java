package com.xunlei.downloadprovidershare.c;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.xunlei.downloadprovidershare.R;
import com.xunlei.downloadprovidershare.data.ShareBean;

// compiled from: AbsShareProxy.java
public abstract class a {
    public static int a(ShareBean shareBean) {
        return shareBean.l > 0 ? shareBean.i : R.drawable.wechat_icon;
    }

    protected void a(SHARE_MEDIA share_media, Activity activity, ShareBean shareBean, UMShareListener uMShareListener) {
    }

    protected static UMImage a(Context context, ShareBean shareBean) {
        String str = shareBean.b;
        if (!TextUtils.isEmpty(str)) {
            return new UMImage(context, str);
        }
        return new UMImage(context, BitmapFactory.decodeResource(context.getResources(), a(shareBean)));
    }
}
