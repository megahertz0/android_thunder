package com.umeng.socialize;

import android.app.Activity;
import android.content.Context;
import com.umeng.socialize.b.a;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.QueuedWork.DialogThread;
import com.umeng.socialize.view.UMFriendListener;

// compiled from: UMShareAPI.java
class h extends DialogThread<Void> {
    final /* synthetic */ Activity a;
    final /* synthetic */ SHARE_MEDIA b;
    final /* synthetic */ UMFriendListener c;
    final /* synthetic */ UMShareAPI d;

    h(UMShareAPI uMShareAPI, Context context, Activity activity, SHARE_MEDIA share_media, UMFriendListener uMFriendListener) {
        this.d = uMShareAPI;
        this.a = activity;
        this.b = share_media;
        this.c = uMFriendListener;
        super(context);
    }

    protected Object doInBackground() {
        if (this.d.a != null) {
            this.d.a.a(this.a, this.b, this.c);
        } else {
            this.d.a = new a(this.a);
            this.d.a.a(this.a, this.b, this.c);
        }
        return null;
    }
}
