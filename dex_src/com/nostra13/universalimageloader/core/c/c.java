package com.nostra13.universalimageloader.core.c;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;

// compiled from: NonViewAware.java
public final class c implements a {
    protected final String a;
    protected final com.nostra13.universalimageloader.core.assist.c b;
    protected final ViewScaleType c;

    public c(String str, com.nostra13.universalimageloader.core.assist.c cVar, ViewScaleType viewScaleType) {
        if (cVar == null) {
            throw new IllegalArgumentException("imageSize must not be null");
        } else if (viewScaleType == null) {
            throw new IllegalArgumentException("scaleType must not be null");
        } else {
            this.a = str;
            this.b = cVar;
            this.c = viewScaleType;
        }
    }

    public final int getWidth() {
        return this.b.a;
    }

    public final int getHeight() {
        return this.b.b;
    }

    public final ViewScaleType getScaleType() {
        return this.c;
    }

    public final View getWrappedView() {
        return null;
    }

    public final boolean isCollected() {
        return false;
    }

    public final int getId() {
        return TextUtils.isEmpty(this.a) ? super.hashCode() : this.a.hashCode();
    }

    public final boolean setImageDrawable(Drawable drawable) {
        return true;
    }

    public final boolean setImageBitmap(Bitmap bitmap) {
        return true;
    }
}
