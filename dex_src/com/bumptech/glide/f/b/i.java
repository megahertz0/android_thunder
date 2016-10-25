package com.bumptech.glide.f.b;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import com.bumptech.glide.load.resource.a.b;

// compiled from: SquaringDrawable.java
public final class i extends b {
    private b a;
    private a b;
    private boolean c;

    // compiled from: SquaringDrawable.java
    static class a extends ConstantState {
        private final ConstantState a;
        private final int b;

        a(a aVar) {
            this(aVar.a, aVar.b);
        }

        a(ConstantState constantState, int i) {
            this.a = constantState;
            this.b = i;
        }

        public final Drawable newDrawable() {
            return newDrawable(null);
        }

        public final Drawable newDrawable(Resources resources) {
            return new i(this, null, resources);
        }

        public final int getChangingConfigurations() {
            return 0;
        }
    }

    public i(b bVar, int i) {
        this(new a(bVar.getConstantState(), i), bVar, null);
    }

    i(a aVar, b bVar, Resources resources) {
        this.b = aVar;
        if (bVar != null) {
            this.a = bVar;
        } else if (resources != null) {
            this.a = (b) aVar.a.newDrawable(resources);
        } else {
            this.a = (b) aVar.a.newDrawable();
        }
    }

    public final void setBounds(int i, int i2, int i3, int i4) {
        super.setBounds(i, i2, i3, i4);
        this.a.setBounds(i, i2, i3, i4);
    }

    public final void setBounds(Rect rect) {
        super.setBounds(rect);
        this.a.setBounds(rect);
    }

    public final void setChangingConfigurations(int i) {
        this.a.setChangingConfigurations(i);
    }

    public final int getChangingConfigurations() {
        return this.a.getChangingConfigurations();
    }

    public final void setDither(boolean z) {
        this.a.setDither(z);
    }

    public final void setFilterBitmap(boolean z) {
        this.a.setFilterBitmap(z);
    }

    @TargetApi(11)
    public final Callback getCallback() {
        return this.a.getCallback();
    }

    @TargetApi(19)
    public final int getAlpha() {
        return this.a.getAlpha();
    }

    public final void setColorFilter(int i, Mode mode) {
        this.a.setColorFilter(i, mode);
    }

    public final void clearColorFilter() {
        this.a.clearColorFilter();
    }

    public final Drawable getCurrent() {
        return this.a.getCurrent();
    }

    public final boolean setVisible(boolean z, boolean z2) {
        return this.a.setVisible(z, z2);
    }

    public final int getIntrinsicWidth() {
        return this.b.b;
    }

    public final int getIntrinsicHeight() {
        return this.b.b;
    }

    public final int getMinimumWidth() {
        return this.a.getMinimumWidth();
    }

    public final int getMinimumHeight() {
        return this.a.getMinimumHeight();
    }

    public final boolean getPadding(Rect rect) {
        return this.a.getPadding(rect);
    }

    public final void invalidateSelf() {
        super.invalidateSelf();
        this.a.invalidateSelf();
    }

    public final void unscheduleSelf(Runnable runnable) {
        super.unscheduleSelf(runnable);
        this.a.unscheduleSelf(runnable);
    }

    public final void scheduleSelf(Runnable runnable, long j) {
        super.scheduleSelf(runnable, j);
        this.a.scheduleSelf(runnable, j);
    }

    public final void draw(Canvas canvas) {
        this.a.draw(canvas);
    }

    public final void setAlpha(int i) {
        this.a.setAlpha(i);
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.a.setColorFilter(colorFilter);
    }

    public final int getOpacity() {
        return this.a.getOpacity();
    }

    public final boolean a() {
        return this.a.a();
    }

    public final void a(int i) {
        this.a.a(i);
    }

    public final void start() {
        this.a.start();
    }

    public final void stop() {
        this.a.stop();
    }

    public final boolean isRunning() {
        return this.a.isRunning();
    }

    public final Drawable mutate() {
        if (!this.c && super.mutate() == this) {
            this.a = (b) this.a.mutate();
            this.b = new a(this.b);
            this.c = true;
        }
        return this;
    }

    public final ConstantState getConstantState() {
        return this.b;
    }
}
