package com.xunlei.downloadprovider.personal.settings;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.support.v4.widget.AutoScrollHelper;
import android.util.AttributeSet;
import android.view.View;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.app.BrothersApplication;

public class ArcProgressBarView extends View {
    private static final String a;
    private static final int b;
    private static final int c;
    private RectF d;
    private Paint e;
    private Paint f;
    private float g;

    static {
        a = ArcProgressBarView.class.getSimpleName();
        b = g.a(BrothersApplication.a().getApplicationContext(), 15.0f);
        c = g.a(BrothersApplication.a().getApplicationContext(), 30.0f);
    }

    public ArcProgressBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = 0.0f;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(this.d, 139.0f, 262.0f, false, this.e);
        canvas.drawArc(this.d, 139.0f, this.g, false, this.f);
        invalidate();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        new StringBuilder("func initPaint start : mPaintBackground = ").append(this.e).append(" , mPaintProgress = ").append(this.f);
        if (this.e == null && this.f == null) {
            Shader radialGradient = new RadialGradient(((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f, 260.0f, new int[]{getResources().getColor(2131689863), getResources().getColor(2131689862)}, null, TileMode.REPEAT);
            this.d = new RectF((float) b, (float) b, (float) (getWidth() - b), (float) (getWidth() - b));
            this.e = new Paint();
            this.e.setAntiAlias(true);
            this.e.setStyle(Style.STROKE);
            this.e.setStrokeWidth((float) c);
            this.e.setShader(radialGradient);
            radialGradient = new RadialGradient(((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f, 260.0f, new int[]{getResources().getColor(2131689864), getResources().getColor(2131689865)}, null, TileMode.REPEAT);
            this.f = new Paint();
            this.f.setAntiAlias(true);
            this.f.setStyle(Style.STROKE);
            this.f.setStrokeWidth((float) c);
            this.f.setShader(radialGradient);
        }
    }

    public void setProgress(float f) {
        float f2 = 262.0f;
        float f3 = AutoScrollHelper.RELATIVE_UNSPECIFIED;
        if (f > 0.0f) {
            f3 = f;
        }
        if (f3 < 262.0f) {
            f2 = f3;
        }
        this.g = f2;
    }
}
