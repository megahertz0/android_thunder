package com.umeng.socialize;

import android.app.Activity;
import android.content.Context;
import com.umeng.socialize.b.a;
import com.umeng.socialize.common.QueuedWork.DialogThread;

// compiled from: UMShareAPI.java
class i extends DialogThread<Void> {
    final /* synthetic */ Activity a;
    final /* synthetic */ ShareAction b;
    final /* synthetic */ UMShareListener c;
    final /* synthetic */ UMShareAPI d;

    i(UMShareAPI uMShareAPI, Context context, Activity activity, ShareAction shareAction, UMShareListener uMShareListener) {
        this.d = uMShareAPI;
        this.a = activity;
        this.b = shareAction;
        this.c = uMShareListener;
        super(context);
    }

    protected /* synthetic */ Object doInBackground() {
        return a();
    }

    protected Void a() {
        if (this.d.a != null) {
            this.d.a.a(this.a, this.b, this.c);
        } else {
            this.d.a = new a(this.a);
            this.d.a.a(this.a, this.b, this.c);
        }
        return null;
    }
}
