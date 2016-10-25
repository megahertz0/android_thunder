package com.baidu.mobads.production.f;

import android.graphics.Color;
import com.xunlei.tdlive.R;

class c implements Runnable {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public void run() {
        if (this.a.o()) {
            b.a(this.a).setBackgroundColor(Color.argb(R.styleable.AppCompatTheme_buttonBarButtonStyle, 0, 0, 0));
        }
        if (this.a.h.getAdView() != null) {
            this.a.h.getAdView().setVisibility(0);
        }
        if (b.b(this.a)) {
            this.a.x.d("add countdown view");
            b.c(this.a);
            b.f(this.a).addView(b.d(this.a), b.e(this.a));
        }
    }
}
