package com.nostra13.universalimageloader.core.c;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.nostra13.universalimageloader.b.c;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import java.lang.reflect.Field;

// compiled from: ImageViewAware.java
public final class b extends d {
    public b(ImageView imageView) {
        super(imageView);
    }

    public final int getWidth() {
        int width = super.getWidth();
        if (width <= 0) {
            Object obj = (ImageView) this.a.get();
            if (obj != null) {
                return a(obj, "mMaxWidth");
            }
        }
        return width;
    }

    public final int getHeight() {
        int height = super.getHeight();
        if (height <= 0) {
            Object obj = (ImageView) this.a.get();
            if (obj != null) {
                return a(obj, "mMaxHeight");
            }
        }
        return height;
    }

    public final ViewScaleType getScaleType() {
        ImageView imageView = (ImageView) this.a.get();
        return imageView != null ? ViewScaleType.fromImageView(imageView) : super.getScaleType();
    }

    protected final void a(Drawable drawable, View view) {
        ((ImageView) view).setImageDrawable(drawable);
        if (drawable instanceof AnimationDrawable) {
            ((AnimationDrawable) drawable).start();
        }
    }

    protected final void a(Bitmap bitmap, View view) {
        ((ImageView) view).setImageBitmap(bitmap);
    }

    private static int a(Object obj, String str) {
        try {
            Field declaredField = ImageView.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            int intValue = ((Integer) declaredField.get(obj)).intValue();
            if (intValue > 0 && intValue < Integer.MAX_VALUE) {
                return intValue;
            }
        } catch (Throwable e) {
            c.a(e);
        }
        return 0;
    }

    public final /* bridge */ /* synthetic */ View getWrappedView() {
        return (ImageView) super.getWrappedView();
    }
}
