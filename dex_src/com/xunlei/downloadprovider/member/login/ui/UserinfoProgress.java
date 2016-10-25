package com.xunlei.downloadprovider.member.login.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class UserinfoProgress extends View {
    private Paint a;
    private float b;
    private RectF c;

    public UserinfoProgress(Context context) {
        super(context);
        this.a = null;
        this.b = 0.0f;
        this.c = null;
    }

    public UserinfoProgress(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = null;
        this.b = 0.0f;
        this.c = null;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.a == null) {
            this.a = new Paint();
            this.a.setColor(-9855279);
            this.a.setAntiAlias(true);
            this.a.setStyle(Style.FILL);
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.c = new RectF(0.0f, 0.0f, this.b, (float) getHeight());
        canvas.drawRoundRect(this.c, 13.0f, 13.0f, this.a);
        invalidate();
    }

    public void setProgressBar(float f) {
        this.b = f;
    }
}
