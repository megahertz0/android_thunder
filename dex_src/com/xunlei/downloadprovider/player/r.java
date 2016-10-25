package com.xunlei.downloadprovider.player;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.a.b;

// compiled from: MediaPlayerManager.java
final class r extends BroadcastReceiver {
    final /* synthetic */ q a;

    r(q qVar) {
        this.a = qVar;
    }

    public final void onReceive(Context context, Intent intent) {
        if (!q.a(this.a) && !b.f(context)) {
            for (int i = 0; i < q.b(this.a).size(); i++) {
                ab abVar = (ab) q.b(this.a).valueAt(i);
                if (abVar != null && abVar.e.b.a.a() != MediaPlayerState.RELEASE && abVar != q.c(this.a)) {
                    abVar.e();
                    abVar.e.a();
                }
            }
            if (q.c(this.a) != null && q.c(this.a).t()) {
                ab c = q.c(this.a);
                if (b.a(c.d)) {
                    boolean z = true;
                    if (b.e(c.d)) {
                        String str = BuildConfig.VERSION_NAME;
                        if (!(c.g == null || c.g.d == null)) {
                            str = c.g.d.c;
                        }
                        z = q.a().a(str, c.d, new ah(c));
                    }
                    if (!z) {
                        c.e();
                        c.e.a();
                    }
                }
            }
        }
    }
}
