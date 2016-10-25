package com.xunlei.tdlive.util;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.widget.TextView;
import com.xunlei.tdlive.util.a.a;
import com.xunlei.tdlive.util.a.b;
import com.xunlei.xllib.R;

// compiled from: SpannedStringUtil.java
final class p extends b<TextView> {
    final /* synthetic */ Spannable a;
    final /* synthetic */ Drawable b;
    final /* synthetic */ int c;
    final /* synthetic */ int d;
    final /* synthetic */ Runnable e;

    p(Spannable spannable, Drawable drawable, int i, int i2, Runnable runnable) {
        this.a = spannable;
        this.b = drawable;
        this.c = i;
        this.d = i2;
        this.e = runnable;
    }

    public final void a(TextView textView, String str, a aVar) {
        this.a.setSpan(n.b(textView, this.b), this.c, this.d + 1, R.styleable.Toolbar_collapseIcon);
        textView.setText(this.a);
    }

    public final void a(TextView textView, String str, Bitmap bitmap, a aVar) {
        this.a.setSpan(n.b(textView, new BitmapDrawable(textView.getResources(), bitmap)), this.c, this.d + 1, R.styleable.Toolbar_collapseIcon);
        textView.setText(this.a);
        if (this.e != null) {
            this.e.run();
        }
    }

    public final void a(TextView textView, String str, Drawable drawable) {
        if (this.e != null) {
            this.e.run();
        }
    }
}
