package com.xunlei.downloadprovider.homepage.recommend.a;

import android.os.Message;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.frame.user.a.a;
import java.util.ArrayList;

// compiled from: ShortTimeVideoListAdapter.java
final class f implements Runnable {
    final /* synthetic */ a a;

    f(a aVar) {
        this.a = aVar;
    }

    public final void run() {
        ArrayList a = a.a(BrothersApplication.a).a(a.b(this.a).a);
        Message message = new Message();
        message.what = 1001;
        message.obj = a;
        message.setTarget(a.c(this.a));
        message.sendToTarget();
    }
}
