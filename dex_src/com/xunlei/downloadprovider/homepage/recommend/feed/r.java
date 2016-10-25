package com.xunlei.downloadprovider.homepage.recommend.feed;

import android.content.SharedPreferences.Editor;
import com.android.volley.w;
import com.xunlei.downloadprovider.ad.common.b;
import com.xunlei.downloadprovider.homepage.recommend.VideoFeedReporter;
import com.xunlei.downloadprovider.homepage.recommend.VideoFeedReporter.RefreshType;

// compiled from: FeedDataManager.java
public final class r implements ah {
    final /* synthetic */ ag a;
    final /* synthetic */ RefreshType b;
    final /* synthetic */ o c;

    public r(o oVar, ag agVar, RefreshType refreshType) {
        this.c = oVar;
        this.a = agVar;
        this.b = refreshType;
    }

    public final void a(ai aiVar) {
        this.c.k = aiVar.b;
        this.c.l = aiVar.c;
        this.c.d.addAll(aiVar.a);
        o.a(this.c, aiVar.a, this.a);
        aa.a();
        if (aa.a != null) {
            Editor edit = aa.a.edit();
            edit.clear();
            edit.apply();
        }
        VideoFeedReporter.a(this.b, VideoFeedReporter.a(aiVar.a));
    }

    public final void a(w wVar) {
        this.a.a();
        VideoFeedReporter.a(this.b, b.a(wVar));
    }
}
