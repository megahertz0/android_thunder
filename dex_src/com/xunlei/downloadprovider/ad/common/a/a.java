package com.xunlei.downloadprovider.ad.common.a;

import android.content.Context;
import com.baidu.mobad.feeds.BaiduNative;
import com.xunlei.downloadprovider.ad.common.c;

// compiled from: BaiduNavLoader.java
public final class a implements c {
    private String a;
    private Context b;

    public a(Context context, String str) {
        this.b = context;
        this.a = str;
    }

    public final void a(com.xunlei.downloadprovider.ad.common.c.a aVar, String str) {
        new BaiduNative(this.b, this.a, new b(this, aVar, str)).makeRequest();
    }
}
