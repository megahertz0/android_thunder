package com.xunlei.tdlive.control;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

@SuppressLint({"HandlerLeak"})
public class MagnetView extends ImageView {
    boolean a;
    float b;
    float c;
    a d;
    private boolean e;
    private int f;
    private PaintFlagsDrawFilter g;
    private boolean h;
    private float i;
    private int j;
    private int k;
    private boolean l;
    private boolean m;
    private boolean n;
    private Camera o;
    private Handler p;
    private Handler q;

    public static interface a {
        void a();
    }

    public MagnetView(Context context) {
        super(context);
        this.e = true;
        this.f = 10;
        this.h = true;
        this.i = 0.95f;
        this.l = true;
        this.m = false;
        this.n = false;
        this.a = false;
        this.b = 0.0f;
        this.c = 0.0f;
        this.d = null;
        this.p = new m(this);
        this.q = new n(this);
        this.o = new Camera();
        this.g = new PaintFlagsDrawFilter(0, 3);
    }

    public MagnetView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = true;
        this.f = 10;
        this.h = true;
        this.i = 0.95f;
        this.l = true;
        this.m = false;
        this.n = false;
        this.a = false;
        this.b = 0.0f;
        this.c = 0.0f;
        this.d = null;
        this.p = new m(this);
        this.q = new n(this);
        this.o = new Camera();
        this.g = new PaintFlagsDrawFilter(0, 3);
    }

    public void SetAnimationOnOff(boolean z) {
        this.e = z;
    }

    public void setOnClickIntent(a aVar) {
        this.d = aVar;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.h) {
            this.h = false;
            init();
        }
        canvas.setDrawFilter(this.g);
    }

    public void init() {
        this.j = (getWidth() - getPaddingLeft()) - getPaddingRight();
        this.k = (getHeight() - getPaddingTop()) - getPaddingBottom();
        ((BitmapDrawable) getDrawable()).setAntiAlias(true);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        if (this.e) {
            float x;
            switch (motionEvent.getAction() & 255) {
                case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                    boolean z;
                    x = motionEvent.getX();
                    float y = motionEvent.getY();
                    this.b = ((float) (this.j / 2)) - x;
                    this.c = ((float) (this.k / 2)) - y;
                    this.a = Math.abs(this.b) > Math.abs(this.c);
                    if (x <= ((float) (this.j / 3)) || x >= ((float) ((this.j * 2) / 3)) || y <= ((float) (this.k / 3)) || y >= ((float) ((this.k * 2) / 3))) {
                        z = false;
                    } else {
                        z = true;
                    }
                    this.n = z;
                    this.m = false;
                    if (this.n) {
                        this.q.sendEmptyMessage(1);
                    } else {
                        this.p.sendEmptyMessage(1);
                    }
                    break;
                case SimpleLog.LOG_LEVEL_TRACE:
                    if (this.n) {
                        this.q.sendEmptyMessage(SimpleLog.LOG_LEVEL_FATAL);
                    } else {
                        this.p.sendEmptyMessage(SimpleLog.LOG_LEVEL_FATAL);
                    }
                    break;
                case SimpleLog.LOG_LEVEL_DEBUG:
                    float x2 = motionEvent.getX();
                    x = motionEvent.getY();
                    if (x2 > ((float) this.j) || x > ((float) this.k) || x2 < 0.0f || x < 0.0f) {
                        this.m = true;
                    } else {
                        this.m = false;
                    }
                    break;
                default:
                    break;
            }
        }
        return true;
    }

    private synchronized void a(Matrix matrix, float f, float f2) {
        int i = (int) (((float) this.j) * 0.5f);
        int i2 = (int) (((float) this.k) * 0.5f);
        this.o.save();
        this.o.rotateX(this.c > 0.0f ? f2 : -f2);
        this.o.rotateY(this.b < 0.0f ? f : -f);
        this.o.getMatrix(matrix);
        this.o.restore();
        if (this.b > 0.0f && f != 0.0f) {
            matrix.preTranslate((float) (-this.j), (float) (-i2));
            matrix.postTranslate((float) this.j, (float) i2);
        } else if (this.c > 0.0f && f2 != 0.0f) {
            matrix.preTranslate((float) (-i), (float) (-this.k));
            matrix.postTranslate((float) i, (float) this.k);
        } else if (this.b < 0.0f && f != 0.0f) {
            matrix.preTranslate(0.0f, (float) (-i2));
            matrix.postTranslate(0.0f, (float) i2);
        } else if (this.c < 0.0f && f2 != 0.0f) {
            matrix.preTranslate((float) (-i), 0.0f);
            matrix.postTranslate((float) i, 0.0f);
        }
        setImageMatrix(matrix);
    }

    private synchronized void a(Matrix matrix, float f) {
        matrix.postScale(f, f, (float) ((int) (((float) this.j) * 0.5f)), (float) ((int) (((float) this.k) * 0.5f)));
        setImageMatrix(matrix);
    }

    public int getDegree() {
        return this.f;
    }

    public void setDegree(int i) {
        this.f = i;
    }

    public float getScale() {
        return this.i;
    }

    public void setScale(float f) {
        this.i = f;
    }
}
