package com.xunlei.downloadprovider.qrcode.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.widget.AutoScrollHelper;
import android.util.AttributeSet;
import android.view.View;
import com.google.zxing.client.a.a.d;
import com.google.zxing.o;
import com.xunlei.downloadprovider.vod.VodPlayerActivity;
import java.util.Collection;
import java.util.HashSet;

public final class ViewfinderView extends View {
    private static final int[] b;
    private static Boolean p;
    public Bitmap a;
    private float c;
    private float d;
    private final Paint e;
    private final int f;
    private final int g;
    private final int h;
    private final int i;
    private final int j;
    private final int k;
    private final int l;
    private int m;
    private Collection<o> n;
    private boolean o;
    private int q;

    static {
        b = new int[]{0, 64, 128, 192, 255, 192, 128, 64};
        p = Boolean.valueOf(true);
    }

    public static void setShowCross(Boolean bool) {
        p = bool;
    }

    public ViewfinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = 0.8f;
        this.d = 0.9f;
        this.o = true;
        this.e = new Paint();
        Resources resources = getResources();
        this.f = resources.getColor(2131689976);
        this.g = resources.getColor(2131689791);
        this.h = resources.getColor(2131689974);
        this.i = resources.getColor(2131689975);
        this.k = resources.getColor(2131689973);
        this.j = resources.getColor(2131689747);
        this.l = resources.getColor(2131689973);
        this.m = 0;
        this.n = new HashSet(5);
    }

    public final void onDraw(Canvas canvas) {
        Rect d;
        d b = d.b();
        if (b != null) {
            d = b.d();
        } else {
            d = null;
        }
        if (d != null) {
            int width = canvas.getWidth();
            int height = canvas.getHeight();
            this.q = width;
            int i = width / 2;
            int height2 = d.top + (d.height() / 2);
            this.e.setColor(this.a != null ? this.g : this.f);
            canvas.drawRect(AutoScrollHelper.RELATIVE_UNSPECIFIED, 0.0f, (float) width, (float) d.top, this.e);
            canvas.drawRect(AutoScrollHelper.RELATIVE_UNSPECIFIED, (float) d.top, (float) d.left, (float) (d.bottom + 1), this.e);
            canvas.drawRect((float) (d.right + 1), (float) d.top, (float) width, (float) (d.bottom + 1), this.e);
            canvas.drawRect(AutoScrollHelper.RELATIVE_UNSPECIFIED, (float) (d.bottom + 1), (float) width, (float) height, this.e);
            if (this.a != null) {
                this.e.setAlpha(VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX);
                canvas.drawBitmap(this.a, (float) d.left, (float) d.top, this.e);
                return;
            }
            this.e.setColor(this.h);
            this.e.setAlpha((int) (255.0f * this.c));
            canvas.drawRect((float) d.left, (float) (d.top - 2), (float) (d.right + 1), (float) (d.top + 2), this.e);
            canvas.drawRect((float) (d.left - 2), (float) (d.top + 2), (float) (d.left + 2), (float) (d.bottom - 1), this.e);
            canvas.drawRect((float) (d.right - 2), (float) d.top, (float) (d.right + 2), (float) (d.bottom - 1), this.e);
            canvas.drawRect((float) d.left, (float) (d.bottom - 2), (float) (d.right + 1), (float) (d.bottom + 2), this.e);
            if (p.booleanValue()) {
                canvas.drawRect((float) (i - 25), (float) (height2 - 2), (float) (i + 25), (float) (height2 + 2), this.e);
                canvas.drawRect((float) (i - 2), (float) (height2 - 25), (float) (i + 2), (float) (height2 + 25), this.e);
                this.e.setColor(this.k);
            } else {
                this.e.setColor(this.l);
            }
            canvas.drawRect((float) (d.left - 4), (float) (d.top - 4), (float) (d.left + 40), (float) (d.top + 4), this.e);
            canvas.drawRect((float) (d.left - 4), (float) (d.top - 4), (float) (d.left + 4), (float) (d.top + 40), this.e);
            canvas.drawRect((float) (d.right - 40), (float) (d.top - 4), (float) (d.right + 4), (float) (d.top + 4), this.e);
            canvas.drawRect((float) (d.right - 4), (float) (d.top - 4), (float) (d.right + 4), (float) (d.top + 40), this.e);
            canvas.drawRect((float) (d.left - 4), (float) (d.bottom - 40), (float) (d.left + 4), (float) (d.bottom + 4), this.e);
            canvas.drawRect((float) (d.left - 4), (float) (d.bottom - 4), (float) (d.left + 40), (float) (d.bottom + 4), this.e);
            canvas.drawRect((float) (d.right - 40), (float) (d.bottom - 4), (float) (d.right + 4), (float) (d.bottom + 4), this.e);
            canvas.drawRect((float) (d.right - 4), (float) (d.bottom - 40), (float) (d.right + 4), (float) (d.bottom + 4), this.e);
            this.e.setColor(this.i);
            this.e.setAlpha(VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX);
            if (!this.o) {
                float f = (float) ((d.left + ((d.right - d.left) / 2)) - 2);
                int i2 = d.top;
                i2 = d.right;
                i2 = d.left;
                canvas.drawRect(f, (float) d.top, f + 2.0f, (float) (d.bottom - 2), this.e);
            }
            postInvalidateDelayed(100, d.left, d.top, d.right, d.bottom);
        }
    }

    public final int getActualWidth() {
        return this.q;
    }
}
