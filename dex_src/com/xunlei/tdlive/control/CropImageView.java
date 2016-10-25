package com.xunlei.tdlive.control;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Region.Op;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.xunlei.downloadprovider.member.payment.external.PayBaseConstants;
import com.xunlei.tdlive.util.d;
import java.io.OutputStream;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

@SuppressLint({"ClickableViewAccessibility"})
public class CropImageView extends ImageView {
    boolean a;
    a b;
    Matrix c;
    Matrix d;
    PointF e;
    PointF f;
    float g;
    DashPathEffect h;
    Paint i;
    Path j;
    Bitmap k;
    b l;
    Rect m;

    enum a {
        None,
        Translate,
        Scale;

        static {
            a = new a("None", 0);
            b = new a("Translate", 1);
            c = new a("Scale", 2);
            d = new a[]{a, b, c};
        }
    }

    public static interface b {
        void a(Rect rect);
    }

    public enum c {
        None,
        Left_90,
        Right_90;

        static {
            a = new com.xunlei.tdlive.control.CropImageView.c("None", 0);
            b = new com.xunlei.tdlive.control.CropImageView.c("Left_90", 1);
            c = new com.xunlei.tdlive.control.CropImageView.c("Right_90", 2);
            d = new com.xunlei.tdlive.control.CropImageView.c[]{a, b, c};
        }
    }

    public CropImageView(Context context) {
        super(context);
        a();
    }

    public CropImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public CropImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        this.h = new DashPathEffect(new float[]{8.0f, 16.0f}, 0.0f);
        this.i = new Paint();
        this.j = new Path();
        this.a = false;
        this.b = a.a;
        this.c = new Matrix();
        this.d = new Matrix();
        this.e = new PointF();
        this.f = new PointF();
        this.g = 0.0f;
        setScaleType(ScaleType.MATRIX);
    }

    public void setImageDrawable(Drawable drawable) {
        this.a = false;
        super.setImageDrawable(drawable);
    }

    public boolean getCropBitmap(CompressFormat compressFormat, int i, OutputStream outputStream) {
        return getCropBitmap(compressFormat, i, outputStream, true);
    }

    public boolean getCropBitmap(CompressFormat compressFormat, int i, OutputStream outputStream, boolean z) {
        boolean z2 = false;
        if (this.k != null) {
            try {
                z2 = this.k.compress(compressFormat, i, outputStream);
            } catch (Exception e) {
            }
            if (z) {
                this.k.recycle();
                this.k = null;
            }
        }
        return z2;
    }

    public Rect getCropRect(b bVar) {
        this.l = bVar;
        if (!(this.m == null || this.l == null)) {
            this.l.a(this.m);
        }
        return this.m;
    }

    public void rotate(c cVar) {
        Rect rect = new Rect();
        getDrawingRect(rect);
        Matrix matrix = new Matrix();
        matrix.set(getImageMatrix());
        switch (g.a[cVar.ordinal()]) {
            case SimpleLog.LOG_LEVEL_TRACE:
                matrix.postRotate(-90.0f, ((float) rect.width()) / 2.0f, ((float) rect.height()) / 2.0f);
                break;
            case SimpleLog.LOG_LEVEL_DEBUG:
                matrix.postRotate(90.0f, ((float) rect.width()) / 2.0f, ((float) rect.height()) / 2.0f);
                break;
        }
        if (cVar != c.a) {
            setImageMatrix(matrix);
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    @SuppressLint({"DrawAllocation"})
    protected void onDraw(Canvas canvas) {
        int width;
        if (getWidth() > 640) {
            width = getWidth() - ((int) d.a(getContext(), 40.0f));
        } else {
            width = getWidth();
        }
        int width2 = (getWidth() - width) / 2;
        int height = (getHeight() - width) / 2;
        if (this.m == null) {
            this.m = new Rect(width2, height, width2 + width, height + width);
            if (this.l != null) {
                this.l.a(this.m);
            }
        }
        if (getDrawable() != null) {
            if (!this.a) {
                this.a = true;
                int minimumWidth = getDrawable().getMinimumWidth();
                int minimumHeight = getDrawable().getMinimumHeight();
                float f = (((float) width) * 1.0f) / ((float) minimumWidth);
                float f2 = (((float) width) * 1.0f) / ((float) minimumHeight);
                if (f >= f2 || f2 <= 1.0f) {
                    if (f > 1.0f) {
                        f2 = f;
                    } else {
                        f2 = 1.0f;
                    }
                }
                Matrix matrix = new Matrix();
                matrix.postScale(f2, f2);
                matrix.postTranslate((((float) getWidth()) - (((float) minimumWidth) * f2)) / 2.0f, (((float) getHeight()) - (f2 * ((float) minimumHeight))) / 2.0f);
                setImageMatrix(matrix);
            }
            Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
            Canvas canvas2 = new Canvas(createBitmap);
            super.onDraw(canvas2);
            this.i.reset();
            if (this.k != null) {
                this.k.recycle();
                this.k = null;
            }
            this.k = Bitmap.createBitmap(width, width, Config.ARGB_8888);
            canvas2.setBitmap(this.k);
            canvas2.drawBitmap(createBitmap, new Rect(width2, height, width2 + width, height + width), new Rect(0, 0, width, width), this.i);
            canvas.save();
            canvas.drawBitmap(createBitmap, 0.0f, 0.0f, this.i);
            this.j.reset();
            this.j.addCircle((float) (getWidth() / 2), (float) (getHeight() / 2), (float) (width / 2), Direction.CCW);
            canvas.clipPath(this.j, Op.DIFFERENCE);
            canvas.drawColor(-1358954496);
            canvas.restore();
            this.i.reset();
            this.i.setColor(-1);
            this.i.setStrokeWidth(d.a(getContext(), PayBaseConstants.HALF_OF_FLOAT));
            this.i.setAntiAlias(true);
            this.i.setStyle(Style.STROKE);
            canvas.drawRect((float) width2, (float) height, (float) (width2 + width), (float) (height + width), this.i);
            this.i.reset();
            this.i.setAntiAlias(true);
            this.i.setStyle(Style.STROKE);
            this.i.setStrokeWidth(d.a(getContext(), PayBaseConstants.HALF_OF_FLOAT));
            this.i.setColor(-1);
            this.i.setPathEffect(this.h);
            canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), (float) ((width / 2) - 2), this.i);
            createBitmap.recycle();
            if (width != 640) {
                Bitmap createScaledBitmap = Bitmap.createScaledBitmap(this.k, 640, 640, true);
                if (createScaledBitmap != null) {
                    this.k.recycle();
                    this.k = createScaledBitmap;
                }
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & 255) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                this.b = a.b;
                this.d.set(getImageMatrix());
                this.e.set(motionEvent.getX(), motionEvent.getY());
                break;
            case SimpleLog.LOG_LEVEL_TRACE:
                this.b = a.a;
                break;
            case SimpleLog.LOG_LEVEL_DEBUG:
                float x;
                if (this.b == a.b) {
                    x = motionEvent.getX() - this.e.x;
                    float y = motionEvent.getY() - this.e.y;
                    this.c.set(this.d);
                    this.c.postTranslate(x, y);
                    setImageMatrix(this.c);
                } else if (this.b == a.c) {
                    x = a(motionEvent);
                    if (x > 10.0f) {
                        x /= this.g;
                        this.c.set(this.d);
                        this.c.postScale(x, x, this.f.x, this.f.y);
                        setImageMatrix(this.c);
                    }
                }
                break;
            case SimpleLog.LOG_LEVEL_ERROR:
                this.b = a.c;
                this.g = a(motionEvent);
                if (this.g > 10.0f) {
                    this.f = b(motionEvent);
                    this.d.set(getImageMatrix());
                }
                break;
            case SimpleLog.LOG_LEVEL_FATAL:
                this.b = a.a;
                break;
        }
        return true;
    }

    private static float a(MotionEvent motionEvent) {
        float x = motionEvent.getX(1) - motionEvent.getX(0);
        float y = motionEvent.getY(1) - motionEvent.getY(0);
        return (float) Math.sqrt((double) ((x * x) + (y * y)));
    }

    private static PointF b(MotionEvent motionEvent) {
        return new PointF((motionEvent.getX(1) + motionEvent.getX(0)) / 2.0f, (motionEvent.getY(1) + motionEvent.getY(0)) / 2.0f);
    }
}
