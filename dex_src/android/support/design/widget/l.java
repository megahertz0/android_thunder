package android.support.design.widget;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.support.design.R;
import android.support.v4.text.TextDirectionHeuristicCompat;
import android.support.v4.text.TextDirectionHeuristicsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.AutoScrollHelper;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.animation.Interpolator;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: CollapsingTextHelper.java
final class l {
    private static final boolean k;
    private static final Paint l;
    private boolean A;
    private boolean B;
    private Bitmap C;
    private Paint D;
    private float E;
    private float F;
    private float G;
    private float H;
    private boolean I;
    private final TextPaint J;
    private Interpolator K;
    private float L;
    private float M;
    private float N;
    private int O;
    private float P;
    private float Q;
    private float R;
    private int S;
    float a;
    int b;
    int c;
    float d;
    float e;
    int f;
    Typeface g;
    Typeface h;
    CharSequence i;
    Interpolator j;
    private final View m;
    private boolean n;
    private final Rect o;
    private final Rect p;
    private final RectF q;
    private int r;
    private float s;
    private float t;
    private float u;
    private float v;
    private float w;
    private float x;
    private Typeface y;
    private CharSequence z;

    static {
        k = VERSION.SDK_INT < 18;
        l = null;
    }

    public l(View view) {
        this.b = 16;
        this.c = 16;
        this.d = 15.0f;
        this.e = 15.0f;
        this.m = view;
        this.J = new TextPaint(129);
        this.p = new Rect();
        this.o = new Rect();
        this.q = new RectF();
    }

    final void a(Interpolator interpolator) {
        this.K = interpolator;
        b();
    }

    final void a(int i) {
        if (this.f != i) {
            this.f = i;
            b();
        }
    }

    final void b(int i) {
        if (this.r != i) {
            this.r = i;
            b();
        }
    }

    final void a(int i, int i2, int i3, int i4) {
        if (!a(this.o, i, i2, i3, i4)) {
            this.o.set(i, i2, i3, i4);
            this.I = true;
            c();
        }
    }

    final void b(int i, int i2, int i3, int i4) {
        if (!a(this.p, i, i2, i3, i4)) {
            this.p.set(i, i2, i3, i4);
            this.I = true;
            c();
        }
    }

    private void c() {
        boolean z = this.p.width() > 0 && this.p.height() > 0 && this.o.width() > 0 && this.o.height() > 0;
        this.n = z;
    }

    final void c(int i) {
        if (this.b != i) {
            this.b = i;
            b();
        }
    }

    final void d(int i) {
        if (this.c != i) {
            this.c = i;
            b();
        }
    }

    final void e(int i) {
        TypedArray obtainStyledAttributes = this.m.getContext().obtainStyledAttributes(i, R.styleable.TextAppearance);
        if (obtainStyledAttributes.hasValue(R.styleable.TextAppearance_android_textColor)) {
            this.f = obtainStyledAttributes.getColor(R.styleable.TextAppearance_android_textColor, this.f);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.TextAppearance_android_textSize)) {
            this.e = (float) obtainStyledAttributes.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, (int) this.e);
        }
        this.O = obtainStyledAttributes.getInt(R.styleable.TextAppearance_android_shadowColor, 0);
        this.M = obtainStyledAttributes.getFloat(R.styleable.TextAppearance_android_shadowDx, AutoScrollHelper.RELATIVE_UNSPECIFIED);
        this.N = obtainStyledAttributes.getFloat(R.styleable.TextAppearance_android_shadowDy, AutoScrollHelper.RELATIVE_UNSPECIFIED);
        this.L = obtainStyledAttributes.getFloat(R.styleable.TextAppearance_android_shadowRadius, AutoScrollHelper.RELATIVE_UNSPECIFIED);
        obtainStyledAttributes.recycle();
        if (VERSION.SDK_INT >= 16) {
            this.g = g(i);
        }
        b();
    }

    final void f(int i) {
        TypedArray obtainStyledAttributes = this.m.getContext().obtainStyledAttributes(i, R.styleable.TextAppearance);
        if (obtainStyledAttributes.hasValue(R.styleable.TextAppearance_android_textColor)) {
            this.r = obtainStyledAttributes.getColor(R.styleable.TextAppearance_android_textColor, this.r);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.TextAppearance_android_textSize)) {
            this.d = (float) obtainStyledAttributes.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, (int) this.d);
        }
        this.S = obtainStyledAttributes.getInt(R.styleable.TextAppearance_android_shadowColor, 0);
        this.Q = obtainStyledAttributes.getFloat(R.styleable.TextAppearance_android_shadowDx, AutoScrollHelper.RELATIVE_UNSPECIFIED);
        this.R = obtainStyledAttributes.getFloat(R.styleable.TextAppearance_android_shadowDy, AutoScrollHelper.RELATIVE_UNSPECIFIED);
        this.P = obtainStyledAttributes.getFloat(R.styleable.TextAppearance_android_shadowRadius, AutoScrollHelper.RELATIVE_UNSPECIFIED);
        obtainStyledAttributes.recycle();
        if (VERSION.SDK_INT >= 16) {
            this.h = g(i);
        }
        b();
    }

    private Typeface g(int i) {
        TypedArray obtainStyledAttributes = this.m.getContext().obtainStyledAttributes(i, new int[]{16843692});
        String string = obtainStyledAttributes.getString(0);
        if (string != null) {
            Typeface create = Typeface.create(string, 0);
            obtainStyledAttributes.recycle();
            return create;
        }
        obtainStyledAttributes.recycle();
        return null;
    }

    final void a(Typeface typeface) {
        this.h = typeface;
        this.g = typeface;
        b();
    }

    final Typeface a() {
        return this.g != null ? this.g : Typeface.DEFAULT;
    }

    final void a(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        } else if (f > 1.0f) {
            f = 1.0f;
        }
        if (f != this.a) {
            this.a = f;
            d();
        }
    }

    private void d() {
        b(this.a);
    }

    private void b(float f) {
        this.q.left = a((float) this.o.left, (float) this.p.left, f, this.j);
        this.q.top = a(this.s, this.t, f, this.j);
        this.q.right = a((float) this.o.right, (float) this.p.right, f, this.j);
        this.q.bottom = a((float) this.o.bottom, (float) this.p.bottom, f, this.j);
        this.w = a(this.u, this.v, f, this.j);
        this.x = a(this.s, this.t, f, this.j);
        c(a(this.d, this.e, f, this.K));
        if (this.f != this.r) {
            this.J.setColor(a(this.r, this.f, f));
        } else {
            this.J.setColor(this.f);
        }
        this.J.setShadowLayer(a(this.P, this.L, f, null), a(this.Q, this.M, f, null), a(this.R, this.N, f, null), a(this.S, this.O, f));
        ViewCompat.postInvalidateOnAnimation(this.m);
    }

    public final void a(Canvas canvas) {
        int save = canvas.save();
        if (this.z != null && this.n) {
            float f;
            float f2 = this.w;
            float f3 = this.x;
            int i = (!this.B || this.C == null) ? 0 : 1;
            if (i != 0) {
                f = this.E * this.G;
            } else {
                this.J.ascent();
                f = AutoScrollHelper.RELATIVE_UNSPECIFIED;
                this.J.descent();
            }
            if (i != 0) {
                f3 += f;
            }
            if (this.G != 1.0f) {
                canvas.scale(this.G, this.G, f2, f3);
            }
            if (i != 0) {
                canvas.drawBitmap(this.C, f2, f3, this.D);
            } else {
                canvas.drawText(this.z, 0, this.z.length(), f2, f3, this.J);
            }
        }
        canvas.restoreToCount(save);
    }

    private void c(float f) {
        d(f);
        boolean z = k && this.G != 1.0f;
        this.B = z;
        if (this.B && this.C == null && !this.o.isEmpty() && !TextUtils.isEmpty(this.z)) {
            b((float) AutoScrollHelper.RELATIVE_UNSPECIFIED);
            this.E = this.J.ascent();
            this.F = this.J.descent();
            int round = Math.round(this.J.measureText(this.z, 0, this.z.length()));
            int round2 = Math.round(this.F - this.E);
            if (round > 0 && round2 > 0) {
                this.C = Bitmap.createBitmap(round, round2, Config.ARGB_8888);
                new Canvas(this.C).drawText(this.z, 0, this.z.length(), AutoScrollHelper.RELATIVE_UNSPECIFIED, ((float) round2) - this.J.descent(), this.J);
                if (this.D == null) {
                    this.D = new Paint(3);
                }
            }
        }
        ViewCompat.postInvalidateOnAnimation(this.m);
    }

    private void d(float f) {
        Object obj = 1;
        if (this.i != null) {
            float width;
            float f2;
            int i;
            boolean z;
            if (a(f, this.e)) {
                width = (float) this.p.width();
                float f3 = this.e;
                this.G = 1.0f;
                if (this.y != this.g) {
                    this.y = this.g;
                    f2 = width;
                    width = f3;
                    i = 1;
                } else {
                    f2 = width;
                    width = f3;
                    z = false;
                }
            } else {
                f2 = (float) this.o.width();
                width = this.d;
                if (this.y != this.h) {
                    this.y = this.h;
                    i = 1;
                } else {
                    z = false;
                }
                if (a(f, this.d)) {
                    this.G = 1.0f;
                } else {
                    this.G = f / this.d;
                }
            }
            if (f2 > 0.0f) {
                if (this.H != width || this.I || r0) {
                    i = 1;
                } else {
                    z = false;
                }
                this.H = width;
                this.I = false;
            }
            if (this.z == null || r0) {
                this.J.setTextSize(this.H);
                this.J.setTypeface(this.y);
                TextPaint textPaint = this.J;
                if (this.G != 1.0f) {
                    z = true;
                } else {
                    z = false;
                }
                textPaint.setLinearText(z);
                CharSequence ellipsize = TextUtils.ellipsize(this.i, this.J, f2, TruncateAt.END);
                if (!TextUtils.equals(ellipsize, this.z)) {
                    boolean z2;
                    TextDirectionHeuristicCompat textDirectionHeuristicCompat;
                    this.z = ellipsize;
                    CharSequence charSequence = this.z;
                    if (ViewCompat.getLayoutDirection(this.m) != 1) {
                        z2 = false;
                    }
                    if (z2) {
                        textDirectionHeuristicCompat = TextDirectionHeuristicsCompat.FIRSTSTRONG_RTL;
                    } else {
                        textDirectionHeuristicCompat = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
                    }
                    this.A = textDirectionHeuristicCompat.isRtl(charSequence, 0, charSequence.length());
                }
            }
        }
    }

    public final void b() {
        int i = 1;
        float f = AutoScrollHelper.RELATIVE_UNSPECIFIED;
        if (this.m.getHeight() > 0 && this.m.getWidth() > 0) {
            int i2;
            float f2 = this.H;
            d(this.e);
            float measureText = this.z != null ? this.J.measureText(this.z, 0, this.z.length()) : 0.0f;
            int i3 = this.c;
            if (this.A) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            i2 = GravityCompat.getAbsoluteGravity(i3, i2);
            switch (i2 & 112) {
                case com.xunlei.tdlive.R.styleable.AppCompatTheme_homeAsUpIndicator:
                    this.t = ((float) this.p.top) - this.J.ascent();
                    break;
                case com.xunlei.tdlive.R.styleable.AppCompatTheme_panelMenuListTheme:
                    this.t = (float) this.p.bottom;
                    break;
                default:
                    this.t = (((this.J.descent() - this.J.ascent()) / 2.0f) - this.J.descent()) + ((float) this.p.centerY());
                    break;
            }
            switch (i2 & 8388615) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    this.v = ((float) this.p.centerX()) - (measureText / 2.0f);
                    break;
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    this.v = ((float) this.p.right) - measureText;
                    break;
                default:
                    this.v = (float) this.p.left;
                    break;
            }
            d(this.d);
            if (this.z != null) {
                f = this.J.measureText(this.z, 0, this.z.length());
            }
            int i4 = this.b;
            if (!this.A) {
                i = 0;
            }
            i4 = GravityCompat.getAbsoluteGravity(i4, i);
            switch (i4 & 112) {
                case com.xunlei.tdlive.R.styleable.AppCompatTheme_homeAsUpIndicator:
                    this.s = ((float) this.o.top) - this.J.ascent();
                    break;
                case com.xunlei.tdlive.R.styleable.AppCompatTheme_panelMenuListTheme:
                    this.s = (float) this.o.bottom;
                    break;
                default:
                    this.s = (((this.J.descent() - this.J.ascent()) / 2.0f) - this.J.descent()) + ((float) this.o.centerY());
                    break;
            }
            switch (i4 & 8388615) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    this.u = ((float) this.o.centerX()) - (f / 2.0f);
                    break;
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    this.u = ((float) this.o.right) - f;
                    break;
                default:
                    this.u = (float) this.o.left;
                    break;
            }
            e();
            c(f2);
            d();
        }
    }

    final void a(CharSequence charSequence) {
        if (charSequence == null || !charSequence.equals(this.i)) {
            this.i = charSequence;
            this.z = null;
            e();
            b();
        }
    }

    private void e() {
        if (this.C != null) {
            this.C.recycle();
            this.C = null;
        }
    }

    private static boolean a(float f, float f2) {
        return Math.abs(f - f2) < 0.001f;
    }

    private static int a(int i, int i2, float f) {
        float f2 = 1.0f - f;
        return Color.argb((int) ((((float) Color.alpha(i)) * f2) + (((float) Color.alpha(i2)) * f)), (int) ((((float) Color.red(i)) * f2) + (((float) Color.red(i2)) * f)), (int) ((((float) Color.green(i)) * f2) + (((float) Color.green(i2)) * f)), (int) ((f2 * ((float) Color.blue(i))) + (((float) Color.blue(i2)) * f)));
    }

    private static float a(float f, float f2, float f3, Interpolator interpolator) {
        if (interpolator != null) {
            f3 = interpolator.getInterpolation(f3);
        }
        return a.a(f, f2, f3);
    }

    private static boolean a(Rect rect, int i, int i2, int i3, int i4) {
        return rect.left == i && rect.top == i2 && rect.right == i3 && rect.bottom == i4;
    }
}
