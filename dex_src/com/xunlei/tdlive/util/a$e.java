package com.xunlei.tdlive.util;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.c.a;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

// compiled from: BitmapDisplay.java
private class a$e implements a {
    protected Reference<View> a;
    protected boolean b;

    public a$e(View view) {
        this(view, true);
    }

    public a$e(View view, boolean z) {
        if (view == null) {
            throw new IllegalArgumentException("view must not be null");
        }
        this.a = new WeakReference(view);
        this.b = z;
    }

    public int getWidth() {
        View view = (View) this.a.get();
        if (view == null) {
            return 0;
        }
        int i;
        LayoutParams layoutParams = view.getLayoutParams();
        if (!this.b || layoutParams == null || layoutParams.width == -2) {
            i = 0;
        } else {
            i = view.getWidth();
        }
        return (i > 0 || layoutParams == null) ? i : layoutParams.width;
    }

    public int getHeight() {
        View view = (View) this.a.get();
        if (view == null) {
            return 0;
        }
        int i;
        LayoutParams layoutParams = view.getLayoutParams();
        if (!this.b || layoutParams == null || layoutParams.height == -2) {
            i = 0;
        } else {
            i = view.getHeight();
        }
        return (i > 0 || layoutParams == null) ? i : layoutParams.height;
    }

    public ViewScaleType getScaleType() {
        return ViewScaleType.CROP;
    }

    public View getWrappedView() {
        return (View) this.a.get();
    }

    public boolean isCollected() {
        return this.a.get() == null;
    }

    public int getId() {
        View view = (View) this.a.get();
        return view == null ? super.hashCode() : view.hashCode();
    }

    public boolean setImageDrawable(Drawable drawable) {
        View view = (View) this.a.get();
        if (view != null && (view instanceof TextView)) {
            TextView textView = (TextView) view;
            Drawable[] compoundDrawables = textView.getCompoundDrawables();
            if (compoundDrawables != null && compoundDrawables.length > 0) {
                if (compoundDrawables[0] != null) {
                    textView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
                    return true;
                } else if (compoundDrawables[1] != null) {
                    textView.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
                    return true;
                } else if (compoundDrawables[2] != null) {
                    textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
                    return true;
                } else if (compoundDrawables[3] != null) {
                    textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, drawable);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean setImageBitmap(Bitmap bitmap) {
        View view = (View) this.a.get();
        return view != null ? setImageDrawable(new BitmapDrawable(view.getResources(), bitmap)) : false;
    }
}
