package com.xunlei.downloadprovider.homepage.recommend.feed;

import android.text.TextUtils;
import com.xunlei.downloadprovider.homepage.recommend.c.c;
import com.xunlei.downloadprovider.homepage.recommend.feed.o.a;

// compiled from: ChannelFeedVideoItemView.java
final class j implements a {
    final /* synthetic */ a a;

    j(a aVar) {
        this.a = aVar;
    }

    public final void a(boolean z, String str) {
        if (z && !TextUtils.isEmpty(str)) {
            c.a().b(str);
            new StringBuilder("\u70b9\u8d5e\u4e4b\u540e\u5220\u9664\u6570\u636e movieId, Long.parseLong(movieId) = ").append(str).append(", ").append(Long.parseLong(str));
        }
    }
}
