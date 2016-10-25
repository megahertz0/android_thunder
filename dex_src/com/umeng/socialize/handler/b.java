package com.umeng.socialize.handler;

import android.app.Activity;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.QueuedWork;
import java.util.Map;

// compiled from: UMAPIShareHandler.java
class b implements UMAuthListener {
    final /* synthetic */ Activity a;
    final /* synthetic */ ShareContent b;
    final /* synthetic */ UMShareListener c;
    final /* synthetic */ UMAPIShareHandler d;

    b(UMAPIShareHandler uMAPIShareHandler, Activity activity, ShareContent shareContent, UMShareListener uMShareListener) {
        this.d = uMAPIShareHandler;
        this.a = activity;
        this.b = shareContent;
        this.c = uMShareListener;
    }

    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
        QueuedWork.runInBack(new c(this));
    }

    public void onError(SHARE_MEDIA share_media, int i, Throwable th) {
        this.c.onError(share_media, th);
    }

    public void onCancel(SHARE_MEDIA share_media, int i) {
        this.c.onCancel(share_media);
    }
}
