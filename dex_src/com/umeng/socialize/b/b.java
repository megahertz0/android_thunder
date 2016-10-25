package com.umeng.socialize.b;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import java.util.Map;

// compiled from: SocialRouter.java
class b implements UMAuthListener {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
    }

    public void onError(SHARE_MEDIA share_media, int i, Throwable th) {
    }

    public void onCancel(SHARE_MEDIA share_media, int i) {
    }
}
