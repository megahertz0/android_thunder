package com.xunlei.downloadprovider.web.base.a;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.xllib.R;

// compiled from: BaseInfoViewHolder.java
final class c implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ a b;

    c(a aVar, String str) {
        this.b = aVar;
        this.a = str;
    }

    public final void onClick(View view) {
        this.b.p.a(this.b.c, R.styleable.Toolbar_titleMarginStart, this.a);
        new StringBuilder("\u4f60\u9009\u62e9\u4e86\u6807\u7b7e").append(view.getTag());
    }
}
