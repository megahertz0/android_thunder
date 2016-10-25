package android.support.design.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.ColorUtils;
import com.tencent.open.yyb.AppbarJsBridge;
import com.uc.addon.sdk.remote.Tabs;

// compiled from: CircularBorderDrawable.java
class j extends Drawable {
    final Paint a;
    final Rect b;
    final RectF c;
    float d;
    int e;
    int f;
    int g;
    int h;
    boolean i;
    float j;
    private ColorStateList k;
    private int l;

    public j() {
        this.b = new Rect();
        this.c = new RectF();
        this.i = true;
        this.a = new Paint(1);
        this.a.setStyle(Style.STROKE);
    }

    public void draw(Canvas canvas) {
        float height;
        if (this.i) {
            Paint paint = this.a;
            Rect rect = this.b;
            copyBounds(rect);
            height = this.d / ((float) rect.height());
            float f = 0.0f;
            paint.setShader(new LinearGradient(0.0f, (float) rect.top, f, (float) rect.bottom, new int[]{ColorUtils.compositeColors(this.e, this.l), ColorUtils.compositeColors(this.f, this.l), ColorUtils.compositeColors(ColorUtils.setAlphaComponent(this.f, 0), this.l), ColorUtils.compositeColors(ColorUtils.setAlphaComponent(this.h, 0), this.l), ColorUtils.compositeColors(this.h, this.l), ColorUtils.compositeColors(this.g, this.l)}, new float[]{0.0f, height, 0.5f, 0.5f, 1.0f - height, 1.0f}, TileMode.CLAMP));
            this.i = false;
        }
        height = this.a.getStrokeWidth() / 2.0f;
        RectF rectF = this.c;
        copyBounds(this.b);
        rectF.set(this.b);
        rectF.left += height;
        rectF.top += height;
        rectF.right -= height;
        rectF.bottom -= height;
        canvas.save();
        canvas.rotate(this.j, rectF.centerX(), rectF.centerY());
        canvas.drawOval(rectF, this.a);
        canvas.restore();
    }

    public boolean getPadding(Rect rect) {
        int round = Math.round(this.d);
        rect.set(round, round, round, round);
        return true;
    }

    public void setAlpha(int i) {
        this.a.setAlpha(i);
        invalidateSelf();
    }

    final void a(ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.l = colorStateList.getColorForState(getState(), this.l);
        }
        this.k = colorStateList;
        this.i = true;
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.a.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public int getOpacity() {
        return this.d > 0.0f ? AppbarJsBridge.Code_Java_Exception : Tabs.TAB_CREATE_REACH_MAX_COUNT;
    }

    protected void onBoundsChange(Rect rect) {
        this.i = true;
    }

    public boolean isStateful() {
        return (this.k != null && this.k.isStateful()) || super.isStateful();
    }

    protected boolean onStateChange(int[] iArr) {
        if (this.k != null) {
            int colorForState = this.k.getColorForState(iArr, this.l);
            if (colorForState != this.l) {
                this.i = true;
                this.l = colorForState;
            }
        }
        if (this.i) {
            invalidateSelf();
        }
        return this.i;
    }
}
