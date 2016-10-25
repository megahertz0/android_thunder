package com.nostra13.universalimageloader.core.c;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.nostra13.universalimageloader.b.c;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

// compiled from: ViewAware.java
public abstract class d implements a {
    protected Reference<View> a;
    protected boolean b;

    protected abstract void a(Bitmap bitmap, View view);

    protected abstract void a(Drawable drawable, View view);

    public d(View view) {
        this(view, (byte) 0);
    }

    private d(View view, byte b) {
        if (view == null) {
            throw new IllegalArgumentException("view must not be null");
        }
        this.a = new WeakReference(view);
        this.b = true;
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
        if (Looper.myLooper() == Looper.getMainLooper()) {
            View view = (View) this.a.get();
            if (view != null) {
                a(drawable, view);
                return true;
            }
        }
        c.c("Can't set a drawable into view. You should call ImageLoader on UI thread for it.", new Object[0]);
        return false;
    }

    public boolean setImageBitmap(Bitmap bitmap) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            View view = (View) this.a.get();
            if (view != null) {
                a(bitmap, view);
                return true;
            }
        }
        c.c("Can't set a bitmap into view. You should call ImageLoader on UI thread for it.", new Object[0]);
        return false;
    }
}
