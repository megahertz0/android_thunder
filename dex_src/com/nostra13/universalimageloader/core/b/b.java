package com.nostra13.universalimageloader.core.b;

import android.graphics.Bitmap;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.c.a;

// compiled from: FadeInBitmapDisplayer.java
public final class b implements a {
    private final int a;
    private final boolean b;
    private final boolean c;
    private final boolean d;

    public b() {
        this((byte) 0);
    }

    private b(byte b) {
        this.a = 200;
        this.b = true;
        this.c = true;
        this.d = true;
    }

    public final void a(Bitmap bitmap, a aVar, LoadedFrom loadedFrom) {
        aVar.setImageBitmap(bitmap);
        if (!(this.b && loadedFrom == LoadedFrom.NETWORK)) {
            if (!((this.c && loadedFrom == LoadedFrom.DISC_CACHE) || (this.d && loadedFrom == LoadedFrom.MEMORY_CACHE))) {
                return;
            }
        }
        View wrappedView = aVar.getWrappedView();
        int i = this.a;
        if (wrappedView != null) {
            Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration((long) i);
            alphaAnimation.setInterpolator(new DecelerateInterpolator());
            wrappedView.startAnimation(alphaAnimation);
        }
    }
}
