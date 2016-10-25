package android.support.v7.a.a;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.support.v4.graphics.drawable.DrawableCompat;

// compiled from: DrawableWrapper.java
public class a extends Drawable implements Callback {
    public Drawable m;

    public a(Drawable drawable) {
        if (this.m != null) {
            this.m.setCallback(null);
        }
        this.m = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
    }

    public void draw(Canvas canvas) {
        this.m.draw(canvas);
    }

    public void onBoundsChange(Rect rect) {
        this.m.setBounds(rect);
    }

    public void setChangingConfigurations(int i) {
        this.m.setChangingConfigurations(i);
    }

    public int getChangingConfigurations() {
        return this.m.getChangingConfigurations();
    }

    public void setDither(boolean z) {
        this.m.setDither(z);
    }

    public void setFilterBitmap(boolean z) {
        this.m.setFilterBitmap(z);
    }

    public void setAlpha(int i) {
        this.m.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.m.setColorFilter(colorFilter);
    }

    public boolean isStateful() {
        return this.m.isStateful();
    }

    public boolean setState(int[] iArr) {
        return this.m.setState(iArr);
    }

    public int[] getState() {
        return this.m.getState();
    }

    public void jumpToCurrentState() {
        DrawableCompat.jumpToCurrentState(this.m);
    }

    public Drawable getCurrent() {
        return this.m.getCurrent();
    }

    public boolean setVisible(boolean z, boolean z2) {
        return super.setVisible(z, z2) || this.m.setVisible(z, z2);
    }

    public int getOpacity() {
        return this.m.getOpacity();
    }

    public Region getTransparentRegion() {
        return this.m.getTransparentRegion();
    }

    public int getIntrinsicWidth() {
        return this.m.getIntrinsicWidth();
    }

    public int getIntrinsicHeight() {
        return this.m.getIntrinsicHeight();
    }

    public int getMinimumWidth() {
        return this.m.getMinimumWidth();
    }

    public int getMinimumHeight() {
        return this.m.getMinimumHeight();
    }

    public boolean getPadding(Rect rect) {
        return this.m.getPadding(rect);
    }

    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }

    protected boolean onLevelChange(int i) {
        return this.m.setLevel(i);
    }

    public void setAutoMirrored(boolean z) {
        DrawableCompat.setAutoMirrored(this.m, z);
    }

    public boolean isAutoMirrored() {
        return DrawableCompat.isAutoMirrored(this.m);
    }

    public void setTint(int i) {
        DrawableCompat.setTint(this.m, i);
    }

    public void setTintList(ColorStateList colorStateList) {
        DrawableCompat.setTintList(this.m, colorStateList);
    }

    public void setTintMode(Mode mode) {
        DrawableCompat.setTintMode(this.m, mode);
    }

    public void setHotspot(float f, float f2) {
        DrawableCompat.setHotspot(this.m, f, f2);
    }

    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        DrawableCompat.setHotspotBounds(this.m, i, i2, i3, i4);
    }
}
