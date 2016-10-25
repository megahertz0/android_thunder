package com.baidu.mobads;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.widget.ImageView;

class ae extends ImageView {
    int a;
    RectF b;
    private final Paint c;
    private final Context d;

    public ae(Context context) {
        super(context);
        this.a = 0;
        this.b = new RectF();
        this.d = context;
        this.c = new Paint();
        this.c.setAntiAlias(true);
        this.c.setStyle(Style.STROKE);
    }

    protected void onDraw(Canvas canvas) {
        int width = getWidth() / 2;
        int a = a(this.d, 15.0f);
        int a2 = a(this.d, 4.0f);
        this.b.left = (float) (width - ((a + 1) + (a2 / 2)));
        this.b.top = (float) (width - ((a + 1) + (a2 / 2)));
        this.b.right = (float) (((a + 1) + (a2 / 2)) + width);
        this.b.bottom = (float) (width + ((a + 1) + (a2 / 2)));
        this.c.setColor(-1907998);
        this.c.setStrokeWidth((float) a2);
        canvas.drawArc(this.b, (float) (this.a + 0), 72.0f, false, this.c);
        this.c.setColor(-1594427658);
        canvas.drawArc(this.b, (float) (this.a + 72), 270.0f, false, this.c);
        this.a += 10;
        if (this.a >= 360) {
            this.a = 0;
        }
        super.onDraw(canvas);
        invalidate();
    }

    public static int a(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }
}
