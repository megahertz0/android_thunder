package com.umeng.socialize;

import android.app.Activity;
import android.content.Context;
import com.umeng.socialize.b.a;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.QueuedWork.DialogThread;

// compiled from: UMShareAPI.java
class e extends DialogThread<Void> {
    final /* synthetic */ Activity a;
    final /* synthetic */ SHARE_MEDIA b;
    final /* synthetic */ UMAuthListener c;
    final /* synthetic */ UMShareAPI d;

    e(UMShareAPI uMShareAPI, Context context, Activity activity, SHARE_MEDIA share_media, UMAuthListener uMAuthListener) {
        this.d = uMShareAPI;
        this.a = activity;
        this.b = share_media;
        this.c = uMAuthListener;
        super(context);
    }

    protected /* synthetic */ Object doInBackground() {
        return a();
    }

    protected Void a() {
        if (this.d.a != null) {
            this.d.a.c(this.a, this.b, this.c);
        } else {
            a aVar = new a(this.a);
            this.d.a.c(this.a, this.b, this.c);
        }
        return null;
    }
}
