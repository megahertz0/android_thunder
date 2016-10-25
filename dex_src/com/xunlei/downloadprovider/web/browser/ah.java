package com.xunlei.downloadprovider.web.browser;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.web.browser.InputAutoCompleteView.a;

// compiled from: InputAutoCompleteView.java
final class ah implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ a b;

    ah(a aVar, String str) {
        this.b = aVar;
        this.a = str;
    }

    public final void onClick(View view) {
        int i = ((InputAutoCompleteView$a$a) view.getTag()).a;
        if (i >= 0 && i < InputAutoCompleteView.c(this.b.a).size()) {
            String str = InputAutoCompleteView.e(this.b.a).a(i).b;
            if (InputAutoCompleteView.a(this.b.a) != null) {
                InputAutoCompleteView.a(this.b.a).a(str, !"-1".equals(this.a));
            }
        }
    }
}
