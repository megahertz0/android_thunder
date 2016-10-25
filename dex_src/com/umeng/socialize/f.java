package com.umeng.socialize;

import android.app.Activity;
import android.content.Context;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.QueuedWork.DialogThread;

// compiled from: UMShareAPI.java
class f extends DialogThread<Void> {
    final /* synthetic */ Activity a;
    final /* synthetic */ SHARE_MEDIA b;
    final /* synthetic */ UMAuthListener c;
    final /* synthetic */ UMShareAPI d;

    f(UMShareAPI uMShareAPI, Context context, Activity activity, SHARE_MEDIA share_media, UMAuthListener uMAuthListener) {
        this.d = uMShareAPI;
        this.a = activity;
        this.b = share_media;
        this.c = uMAuthListener;
        super(context);
    }

    protected Object doInBackground() {
        if (this.d.a != null) {
            this.d.a.a(this.a, this.b, this.c);
        }
        return null;
    }
}
