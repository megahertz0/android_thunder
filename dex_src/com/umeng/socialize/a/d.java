package com.umeng.socialize.a;

import android.content.Context;
import com.umeng.socialize.media.UMediaObject;
import com.umeng.socialize.utils.Log;

// compiled from: SocialAnalytics.java
final class d implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ UMediaObject d;

    d(Context context, String str, String str2, UMediaObject uMediaObject) {
        this.a = context;
        this.b = str;
        this.c = str2;
        this.d = uMediaObject;
    }

    public final void run() {
        a aVar = new a(this.a, this.b, this.c);
        aVar.a(this.d);
        b bVar = (b) c.a.execute(aVar);
        if (bVar == null || !bVar.isOk()) {
            Log.d(" fail to send log");
        } else {
            Log.d(" send log succeed");
        }
    }
}
