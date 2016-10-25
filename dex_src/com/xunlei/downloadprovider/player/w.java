package com.xunlei.downloadprovider.player;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.homepage.recommend.feed.ad;
import com.xunlei.downloadprovider.player.u.a;

// compiled from: MediaPlayerUtils.java
final class w implements OnClickListener {
    final /* synthetic */ a a;

    w(a aVar) {
        this.a = aVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        if (ad.a == null) {
            ad.a = new ad();
        }
        ad.a.b = true;
        dialogInterface.dismiss();
        this.a.b();
    }
}
