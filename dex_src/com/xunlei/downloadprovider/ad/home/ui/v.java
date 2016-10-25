package com.xunlei.downloadprovider.ad.home.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.xllib.R;

// compiled from: VideoInfoViewHolder.java
final class v implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ s b;

    v(s sVar, String str) {
        this.b = sVar;
        this.a = str;
    }

    public final void onClick(View view) {
        s.g(this.b).a(s.f(this.b), R.styleable.Toolbar_titleMarginStart, this.a);
        new StringBuilder("\u4f60\u9009\u62e9\u4e86\u6807\u7b7e").append(view.getTag());
    }
}
