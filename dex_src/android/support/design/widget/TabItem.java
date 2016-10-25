package android.support.design.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.R;
import android.support.v7.widget.cm;
import android.util.AttributeSet;
import android.view.View;

public final class TabItem extends View {
    final CharSequence a;
    final Drawable b;
    final int c;

    public TabItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        cm a = cm.a(context, attributeSet, R.styleable.TabItem);
        this.a = a.c(R.styleable.TabItem_android_text);
        this.b = a.a(R.styleable.TabItem_android_icon);
        this.c = a.e(R.styleable.TabItem_android_layout, 0);
        a.a.recycle();
    }
}
