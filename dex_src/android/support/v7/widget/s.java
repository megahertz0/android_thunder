package android.support.v7.widget;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.widget.ImageView;

// compiled from: AppCompatImageHelper.java
public final class s {
    private final ImageView a;
    private final r b;

    public s(ImageView imageView, r rVar) {
        this.a = imageView;
        this.b = rVar;
    }

    public final void a(AttributeSet attributeSet, int i) {
        cm a = cm.a(this.a.getContext(), attributeSet, R.styleable.AppCompatImageView, i);
        Drawable b = a.b(R.styleable.AppCompatImageView_android_src);
        if (b != null) {
            this.a.setImageDrawable(b);
        }
        int e = a.e(R.styleable.AppCompatImageView_srcCompat, -1);
        if (e != -1) {
            b = this.b.a(this.a.getContext(), e, false);
            if (b != null) {
                this.a.setImageDrawable(b);
            }
        }
        b = this.a.getDrawable();
        if (b != null) {
            ao.b(b);
        }
        a.a.recycle();
    }

    public final void a(int i) {
        if (i != 0) {
            Drawable a;
            if (this.b != null) {
                a = this.b.a(this.a.getContext(), i, false);
            } else {
                a = ContextCompat.getDrawable(this.a.getContext(), i);
            }
            if (a != null) {
                ao.b(a);
            }
            this.a.setImageDrawable(a);
            return;
        }
        this.a.setImageDrawable(null);
    }
}
