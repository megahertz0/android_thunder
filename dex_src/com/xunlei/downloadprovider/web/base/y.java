package com.xunlei.downloadprovider.web.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.xunlei.downloadprovider.homepage.recommend.c.c;
import com.xunlei.xllib.a.b;

// compiled from: ShortMovieDetailActivity.java
final class y extends BroadcastReceiver {
    final /* synthetic */ ShortMovieDetailActivity a;

    y(ShortMovieDetailActivity shortMovieDetailActivity) {
        this.a = shortMovieDetailActivity;
    }

    public final void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            if (b.a(context)) {
                c.a().b();
            }
            if (this.a.q != null) {
                ShortMovieDetailFragment a = this.a.q;
                if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE") && !intent.getBooleanExtra("noConnectivity", false)) {
                    a.a();
                }
            }
        }
    }
}
