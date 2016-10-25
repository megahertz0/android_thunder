package android.support.a.a;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.graphics.drawable.TintAwareDrawable;
import android.util.AttributeSet;

@TargetApi(21)
// compiled from: VectorDrawableCommon.java
abstract class f extends Drawable implements TintAwareDrawable {
    Drawable a;

    f() {
    }

    static TypedArray a(Resources resources, Theme theme, AttributeSet attributeSet, int[] iArr) {
        return theme == null ? resources.obtainAttributes(attributeSet, iArr) : theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }

    public void setColorFilter(int i, Mode mode) {
        if (this.a != null) {
            this.a.setColorFilter(i, mode);
        } else {
            super.setColorFilter(i, mode);
        }
    }

    public ColorFilter getColorFilter() {
        return this.a != null ? DrawableCompat.getColorFilter(this.a) : null;
    }

    protected boolean onLevelChange(int i) {
        return this.a != null ? this.a.setLevel(i) : super.onLevelChange(i);
    }

    protected void onBoundsChange(Rect rect) {
        if (this.a != null) {
            this.a.setBounds(rect);
        } else {
            super.onBoundsChange(rect);
        }
    }

    public void setHotspot(float f, float f2) {
        if (this.a != null) {
            DrawableCompat.setHotspot(this.a, f, f2);
        }
    }

    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        if (this.a != null) {
            DrawableCompat.setHotspotBounds(this.a, i, i2, i3, i4);
        }
    }

    public void setFilterBitmap(boolean z) {
        if (this.a != null) {
            this.a.setFilterBitmap(z);
        }
    }

    public void jumpToCurrentState() {
        if (this.a != null) {
            DrawableCompat.jumpToCurrentState(this.a);
        }
    }

    public void setAutoMirrored(boolean z) {
        if (this.a != null) {
            DrawableCompat.setAutoMirrored(this.a, z);
        }
    }

    public boolean isAutoMirrored() {
        if (this.a != null) {
            DrawableCompat.isAutoMirrored(this.a);
        }
        return false;
    }

    public void applyTheme(Theme theme) {
        if (this.a != null) {
            DrawableCompat.applyTheme(this.a, theme);
        }
    }

    public int getLayoutDirection() {
        if (this.a != null) {
            DrawableCompat.getLayoutDirection(this.a);
        }
        return 0;
    }

    public void clearColorFilter() {
        if (this.a != null) {
            this.a.clearColorFilter();
        } else {
            super.clearColorFilter();
        }
    }

    public Drawable getCurrent() {
        return this.a != null ? this.a.getCurrent() : super.getCurrent();
    }

    public int getMinimumWidth() {
        return this.a != null ? this.a.getMinimumWidth() : super.getMinimumWidth();
    }

    public int getMinimumHeight() {
        return this.a != null ? this.a.getMinimumHeight() : super.getMinimumHeight();
    }

    public boolean getPadding(Rect rect) {
        return this.a != null ? this.a.getPadding(rect) : super.getPadding(rect);
    }

    public int[] getState() {
        return this.a != null ? this.a.getState() : super.getState();
    }

    public Region getTransparentRegion() {
        return this.a != null ? this.a.getTransparentRegion() : super.getTransparentRegion();
    }

    public void setChangingConfigurations(int i) {
        if (this.a != null) {
            this.a.setChangingConfigurations(i);
        } else {
            super.setChangingConfigurations(i);
        }
    }

    public boolean setState(int[] iArr) {
        return this.a != null ? this.a.setState(iArr) : super.setState(iArr);
    }
}
