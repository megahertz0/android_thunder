package com.tencent.connect.avatar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.v4.widget.AutoScrollHelper;
import android.view.View;
import com.xunlei.tdlive.R;

// compiled from: ProGuard
public class b extends View {
    private Rect a;
    private Paint b;

    public b(Context context) {
        super(context);
        b();
    }

    private void b() {
        this.b = new Paint();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect a = a();
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        this.b.setStyle(Style.FILL);
        this.b.setColor(Color.argb(R.styleable.AppCompatTheme_buttonStyle, 0, 0, 0));
        canvas.drawRect(AutoScrollHelper.RELATIVE_UNSPECIFIED, 0.0f, (float) measuredWidth, (float) a.top, this.b);
        canvas.drawRect(AutoScrollHelper.RELATIVE_UNSPECIFIED, (float) a.bottom, (float) measuredWidth, (float) measuredHeight, this.b);
        canvas.drawRect(AutoScrollHelper.RELATIVE_UNSPECIFIED, (float) a.top, (float) a.left, (float) a.bottom, this.b);
        canvas.drawRect((float) a.right, (float) a.top, (float) measuredWidth, (float) a.bottom, this.b);
        canvas.drawColor(Color.argb(R.styleable.AppCompatTheme_buttonStyle, 0, 0, 0));
        this.b.setStyle(Style.STROKE);
        this.b.setColor(-1);
        canvas.drawRect((float) a.left, (float) a.top, (float) (a.right - 1), (float) a.bottom, this.b);
    }

    public Rect a() {
        if (this.a == null) {
            this.a = new Rect();
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            int min = Math.min(Math.min((measuredHeight - 60) - 80, measuredWidth), 640);
            measuredWidth = (measuredWidth - min) / 2;
            measuredHeight = (measuredHeight - min) / 2;
            this.a.set(measuredWidth, measuredHeight, measuredWidth + min, min + measuredHeight);
        }
        return this.a;
    }
}
