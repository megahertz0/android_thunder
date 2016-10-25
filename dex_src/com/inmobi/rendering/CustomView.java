package com.inmobi.rendering;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.AutoScrollHelper;
import android.view.View;
import com.inmobi.rendering.CustomView.SwitchIconType;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public class CustomView extends View {
    private float a;
    private float b;
    private float c;
    private float d;
    private float e;
    private SwitchIconType f;
    private int g;
    private Paint h;
    private Path i;
    private RectF j;

    /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[SwitchIconType.values().length];
            try {
                a[SwitchIconType.CLOSE_BUTTON.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[SwitchIconType.CLOSE_TRANSPARENT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[SwitchIconType.FORWARD_ACTIVE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[SwitchIconType.FORWARD_INACTIVE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[SwitchIconType.BACK.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[SwitchIconType.REFRESH.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[SwitchIconType.CLOSE_ICON.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public enum SwitchIconType {
        CLOSE_BUTTON,
        CLOSE_TRANSPARENT,
        CLOSE_ICON,
        REFRESH,
        BACK,
        FORWARD_ACTIVE,
        FORWARD_INACTIVE
    }

    private CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, float f, SwitchIconType switchIconType) {
        this(context);
        this.f = switchIconType;
        this.a = f;
        this.g = 15;
        this.b = (50.0f * this.a) / 2.0f;
        this.c = (30.0f * this.a) / 2.0f;
        this.d = this.b - (this.c / 3.0f);
        this.e = this.b + (this.c / 3.0f);
        this.h = new Paint(1);
        this.j = new RectF();
        this.i = new Path();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.h.reset();
        switch (AnonymousClass_1.a[this.f.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                this.h.setAntiAlias(true);
                this.h.setColor(ViewCompat.MEASURED_STATE_MASK);
                this.h.setStrokeWidth(3.0f);
                this.h.setStyle(Style.FILL_AND_STROKE);
                canvas.drawCircle(this.b, this.b, this.c, this.h);
                this.h.setColor(-1);
                this.h.setStyle(Style.STROKE);
                canvas.drawLine(this.d, this.d, this.e, this.e, this.h);
                canvas.drawLine(this.d, this.e, this.e, this.d, this.h);
                canvas.drawCircle(this.b, this.b, this.c, this.h);
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                this.h.setAntiAlias(true);
                this.h.setColor(0);
                this.h.setStrokeWidth(3.0f);
                this.h.setStyle(Style.FILL_AND_STROKE);
                canvas.drawCircle(this.b, this.b, this.b, this.h);
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                this.i.reset();
                this.i.setFillType(FillType.EVEN_ODD);
                this.i.moveTo(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.g) * this.a) / 2.0f));
                this.i.lineTo(((float) (getWidth() / 2)) + ((((float) this.g) * this.a) / 2.0f), (float) (getHeight() / 2));
                this.i.lineTo(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) + ((((float) this.g) * this.a) / 2.0f));
                this.i.lineTo(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.g) * this.a) / 2.0f));
                this.i.close();
                this.h.setAntiAlias(true);
                this.h.setColor(ViewCompat.MEASURED_STATE_MASK);
                this.h.setStrokeWidth(3.0f);
                this.h.setStyle(Style.FILL_AND_STROKE);
                canvas.drawPath(this.i, this.h);
            case XZBDevice.DOWNLOAD_LIST_ALL:
                this.i.reset();
                this.i.setFillType(FillType.EVEN_ODD);
                this.i.moveTo(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.g) * this.a) / 2.0f));
                this.i.lineTo(((float) (getWidth() / 2)) + ((((float) this.g) * this.a) / 2.0f), (float) (getHeight() / 2));
                this.i.lineTo(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) + ((((float) this.g) * this.a) / 2.0f));
                this.i.lineTo(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.g) * this.a) / 2.0f));
                this.i.close();
                this.h.setAntiAlias(true);
                this.h.setColor(-12303292);
                this.h.setStrokeWidth(3.0f);
                this.h.setStyle(Style.FILL_AND_STROKE);
                canvas.drawPath(this.i, this.h);
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                this.i.reset();
                this.i.setFillType(FillType.EVEN_ODD);
                this.i.moveTo(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), (float) (getHeight() / 2));
                this.i.lineTo(((float) (getWidth() / 2)) + ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.g) * this.a) / 2.0f));
                this.i.lineTo(((float) (getWidth() / 2)) + ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) + ((((float) this.g) * this.a) / 2.0f));
                this.i.lineTo(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), (float) (getHeight() / 2));
                this.i.close();
                this.h.setAntiAlias(true);
                this.h.setColor(ViewCompat.MEASURED_STATE_MASK);
                this.h.setStrokeWidth(3.0f);
                this.h.setStyle(Style.FILL_AND_STROKE);
                canvas.drawPath(this.i, this.h);
            case R.styleable.Toolbar_contentInsetEnd:
                this.i.reset();
                this.h.setAntiAlias(true);
                this.h.setColor(ViewCompat.MEASURED_STATE_MASK);
                this.h.setStrokeWidth(5.0f);
                this.h.setStyle(Style.STROKE);
                this.j.set(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((float) (getWidth() / 2)) + ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) + ((((float) this.g) * this.a) / 2.0f));
                canvas.drawArc(this.j, AutoScrollHelper.RELATIVE_UNSPECIFIED, 270.0f, false, this.h);
                this.i.setFillType(FillType.EVEN_ODD);
                this.i.moveTo(((float) (getWidth() / 2)) + ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) - (this.a * 2.0f));
                this.i.lineTo((((float) (getWidth() / 2)) + ((((float) this.g) * this.a) / 2.0f)) - (this.a * 2.0f), (float) (getHeight() / 2));
                this.i.lineTo((((float) (getWidth() / 2)) + ((((float) this.g) * this.a) / 2.0f)) + (this.a * 2.0f), (float) (getHeight() / 2));
                this.i.lineTo(((float) (getWidth() / 2)) + ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) - (this.a * 2.0f));
                this.i.close();
                this.h.setStyle(Style.FILL_AND_STROKE);
                canvas.drawPath(this.i, this.h);
            case R.styleable.Toolbar_contentInsetLeft:
                this.h.setAntiAlias(true);
                this.h.setColor(ViewCompat.MEASURED_STATE_MASK);
                this.h.setStrokeWidth(5.0f);
                this.h.setStyle(Style.STROKE);
                Canvas canvas2 = canvas;
                canvas2.drawLine(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((((float) this.g) * this.a) / 2.0f) + ((float) (getWidth() / 2)), ((((float) this.g) * this.a) / 2.0f) + ((float) (getHeight() / 2)), this.h);
                canvas2 = canvas;
                canvas2.drawLine(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((((float) this.g) * this.a) / 2.0f) + ((float) (getHeight() / 2)), ((((float) this.g) * this.a) / 2.0f) + ((float) (getWidth() / 2)), ((float) (getHeight() / 2)) - ((((float) this.g) * this.a) / 2.0f), this.h);
            default:
                break;
        }
    }

    public void setSwitchInt(SwitchIconType switchIconType) {
        this.f = switchIconType;
    }
}
