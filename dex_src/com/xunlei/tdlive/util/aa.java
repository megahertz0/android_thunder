package com.xunlei.tdlive.util;

import com.umeng.common.inter.ITagManager;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: UmengShareHelper.java
final class aa implements UMShareListener {
    aa() {
    }

    public final void onCancel(SHARE_MEDIA share_media) {
        String str = y.a(share_media) + "\u5206\u4eab\u53d6\u6d88";
        if (y.b() != null) {
            y.b().a(1, str);
        }
        XLog.d("UmengShareHelper", new StringBuilder("share canceled: ").append(str).toString());
    }

    public final void onError(SHARE_MEDIA share_media, Throwable th) {
        String str = y.a(share_media) + "\u5206\u4eab\u5931\u8d25";
        if (y.b() != null) {
            y.b().a(SimpleLog.LOG_LEVEL_DEBUG, str);
        }
        q.e("zb_share").b(ITagManager.FAIL).b(new String[0]);
        XLog.d("UmengShareHelper", new StringBuilder("share error: ").append(str).toString());
    }

    public final void onResult(SHARE_MEDIA share_media) {
        String toString = new StringBuilder("\u5df2\u5206\u4eab\u5230").append(y.a(share_media)).toString();
        if (y.b() != null) {
            y.b().a(0, toString);
        }
        q.e("zb_share").b("success").b(new String[0]);
        XLog.d("UmengShareHelper", new StringBuilder("share result: ").append(toString).toString());
    }
}
