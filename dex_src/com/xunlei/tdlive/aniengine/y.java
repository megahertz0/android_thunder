package com.xunlei.tdlive.aniengine;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import com.xunlei.xllib.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttTopic;

// compiled from: Sprite.java
public class y {
    private float A;
    private float B;
    private Xfermode C;
    private Animation D;
    private e E;
    private Transformation F;
    private Transformation G;
    private float[] H;
    private d a;
    private c b;
    private ArrayList<y> c;
    private com.xunlei.tdlive.aniengine.a.a d;
    private String[] e;
    private boolean f;
    private boolean g;
    private PointF h;
    private long i;
    private long j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;
    private float p;
    private float q;
    private float r;
    private float s;
    private int t;
    private int u;
    private int v;
    private int w;
    private Bitmap x;
    private int y;
    private boolean z;

    // compiled from: Sprite.java
    static class a extends AnimationDrawable {
        int a;

        a() {
            this.a = -1;
        }

        public void start() {
            run();
        }

        public void stop() {
            unscheduleSelf(this);
        }

        public void run() {
            unscheduleSelf(this);
            int i = this.a + 1;
            this.a = i;
            if (i < getNumberOfFrames() || !isOneShot()) {
                i = this.a % getNumberOfFrames();
                this.a = i;
                selectDrawable(i);
                scheduleSelf(this, SystemClock.uptimeMillis() + ((long) getDuration(this.a)));
            }
        }
    }

    // compiled from: Sprite.java
    static class b extends Drawable {
        private Paint a;
        private Bitmap b;
        private String c;
        private com.xunlei.tdlive.aniengine.a.a d;
        private int e;
        private float f;
        private float g;

        public b(Bitmap bitmap) {
            this.a = new Paint();
            this.e = 0;
            this.b = bitmap;
        }

        public b(com.xunlei.tdlive.aniengine.a.a aVar, String str) {
            this.a = new Paint();
            this.e = 0;
            this.d = aVar;
            this.c = str;
            this.a.setAntiAlias(true);
        }

        public void a(Xfermode xfermode) {
            this.a.setXfermode(xfermode);
        }

        public void a(float f, float f2) {
            this.f = f;
            this.g = f2;
        }

        public void draw(Canvas canvas) {
            if (this.b == null && this.c != null && this.d != null && this.e <= 0) {
                this.b = this.d.b(this.c);
                this.e++;
            }
            if (this.b != null) {
                Rect bounds = getBounds();
                if (this.f == 0.0f || this.g == 0.0f) {
                    canvas.drawBitmap(this.b, null, bounds, this.a);
                    return;
                }
                int saveLayer = canvas.saveLayer((float) bounds.left, (float) bounds.top, (float) bounds.right, (float) bounds.bottom, this.a, R.styleable.AppCompatTheme_actionModeCloseDrawable);
                int color = this.a.getColor();
                this.a.setColor(0);
                canvas.drawRect(bounds, this.a);
                float width = this.f == -1.0f ? ((float) bounds.width()) / 2.0f : this.f;
                float height = this.g == -1.0f ? ((float) bounds.height()) / 2.0f : this.g;
                this.a.setColor(-16777216);
                canvas.drawRoundRect(new RectF((float) bounds.left, (float) bounds.top, (float) bounds.right, (float) bounds.bottom), width, height, this.a);
                Xfermode xfermode = this.a.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
                canvas.drawBitmap(this.b, null, bounds, this.a);
                this.a.setXfermode(xfermode);
                this.a.setColor(color);
                canvas.restoreToCount(saveLayer);
            }
        }

        public void setAlpha(int i) {
            this.a.setAlpha(i);
        }

        public void setColorFilter(ColorFilter colorFilter) {
            this.a.setColorFilter(colorFilter);
        }

        public int getOpacity() {
            return 0;
        }
    }

    // compiled from: Sprite.java
    static class c implements Callback {
        private HashMap<Drawable, a> a;

        // compiled from: Sprite.java
        static class a {
            Runnable a;
            long b;

            a() {
            }
        }

        c() {
            this.a = new HashMap();
        }

        public void a() {
            for (Drawable drawable : this.a.keySet()) {
                a aVar = (a) this.a.get(drawable);
                if (aVar != null && SystemClock.uptimeMillis() >= aVar.b) {
                    aVar.a.run();
                }
            }
        }

        public void invalidateDrawable(Drawable drawable) {
        }

        public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
            a aVar = new a();
            aVar.a = runnable;
            aVar.b = j;
            this.a.put(drawable, aVar);
        }

        public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            this.a.remove(drawable);
        }
    }

    // compiled from: Sprite.java
    public static interface d {
        void a(y yVar);
    }

    // compiled from: Sprite.java
    static class e {
        private Drawable a;
        private boolean b;
        private boolean c;

        public e(Drawable drawable) {
            this.b = false;
            this.c = false;
            this.a = drawable;
        }

        public e(Bitmap bitmap) {
            this(null, false, 0, bitmap);
        }

        public e(Callback callback, boolean z, int i, com.xunlei.tdlive.aniengine.a.a aVar, String... strArr) {
            ArrayList arrayList;
            this.b = false;
            this.c = false;
            ArrayList arrayList2 = new ArrayList();
            int i2;
            if (strArr.length != 1) {
                int length = strArr.length;
                for (i2 = 0; i2 < length; i2++) {
                    arrayList2.add(strArr[i2]);
                }
                arrayList = arrayList2;
            } else if (strArr[0].startsWith("dir:")) {
                String substring = strArr[0].substring(MqttConnectOptions.MQTT_VERSION_3_1_1);
                String[] a = aVar.a(substring);
                List arrayList3 = new ArrayList();
                int length2 = a.length;
                for (i2 = 0; i2 < length2; i2++) {
                    arrayList3.add(substring + MqttTopic.TOPIC_LEVEL_SEPARATOR + a[i2]);
                }
                Collections.sort(arrayList3);
                List list = arrayList3;
            } else {
                arrayList2.add(strArr[0]);
                arrayList = arrayList2;
            }
            if (arrayList.size() > 1) {
                Drawable aVar2 = new a();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    aVar2.addFrame(new b(aVar, (String) it.next()), i);
                }
                aVar2.setCallback(callback);
                aVar2.setOneShot(z);
                aVar2.start();
                this.a = aVar2;
            } else {
                this.a = new b(aVar, (String) arrayList.get(0));
            }
            Bitmap b = aVar.b((String) arrayList.get(0));
            if (b != null) {
                this.a.setBounds(0, 0, b.getWidth(), b.getHeight());
            }
        }

        public e(Callback callback, boolean z, int i, Bitmap... bitmapArr) {
            this.b = false;
            this.c = false;
            if (bitmapArr.length > 1) {
                Drawable aVar = new a();
                int length = bitmapArr.length;
                for (int i2 = 0; i2 < length; i2++) {
                    aVar.addFrame(new b(bitmapArr[i2]), i);
                }
                aVar.setCallback(callback);
                aVar.setOneShot(z);
                aVar.start();
                this.a = aVar;
            } else {
                this.a = new b(bitmapArr[0]);
            }
            this.a.setBounds(0, 0, bitmapArr[0].getWidth(), bitmapArr[0].getHeight());
        }

        public void a(boolean z) {
            this.b = z;
        }

        public void a(Xfermode xfermode) {
            if (this.a instanceof b) {
                ((b) this.a).a(xfermode);
            }
        }

        public void a(float f, float f2) {
            if (this.a instanceof b) {
                ((b) this.a).a(f, f2);
            }
        }

        public void a(int i) {
            this.a.setAlpha(i);
        }

        public void a(float f, int i, int i2, boolean z) {
            if (!this.c || z) {
                this.c = true;
                Rect a = a();
                if (i != 0) {
                    a.right = a.left + i;
                }
                if (i2 != 0) {
                    a.bottom = a.top + i2;
                }
                a.set((int) (((float) a.left) * f), (int) (((float) a.top) * f), (int) (((float) a.right) * f), (int) (((float) a.bottom) * f));
                a(a);
                if (this.a instanceof AnimationDrawable) {
                    int numberOfFrames = ((AnimationDrawable) this.a).getNumberOfFrames();
                    for (int i3 = 0; i3 < numberOfFrames; i3++) {
                        ((AnimationDrawable) this.a).getFrame(i3).setBounds(a);
                    }
                }
            }
        }

        public void a(Canvas canvas) {
            if (this.b) {
                canvas.save();
                canvas.translate((float) (a().right - a().left), 0.0f);
                canvas.scale(-1.0f, 1.0f);
            }
            this.a.draw(canvas);
            if (this.b) {
                canvas.restore();
            }
        }

        public void a(int i, int i2, int i3, int i4) {
            this.a.setBounds(i, i2, i3, i4);
        }

        public void a(Rect rect) {
            this.a.setBounds(rect);
        }

        public Rect a() {
            return this.a.getBounds();
        }

        public Drawable b() {
            return this.a;
        }
    }

    public y() {
        this.b = new c();
        this.c = new ArrayList();
        this.d = null;
        this.e = null;
        this.i = 0;
        this.j = 0;
        this.k = false;
        this.l = false;
        this.m = true;
        this.n = true;
        this.o = true;
        this.p = 0.0f;
        this.q = 0.0f;
        this.r = 0.0f;
        this.s = 0.0f;
        this.t = 0;
        this.u = 0;
        this.v = 0;
        this.w = 0;
        this.x = null;
        this.y = 0;
        this.z = false;
        this.F = new Transformation();
        this.G = new Transformation();
        this.H = new float[9];
    }

    public y(boolean z, int i, String... strArr) {
        this.b = new c();
        this.c = new ArrayList();
        this.d = null;
        this.e = null;
        this.i = 0;
        this.j = 0;
        this.k = false;
        this.l = false;
        this.m = true;
        this.n = true;
        this.o = true;
        this.p = 0.0f;
        this.q = 0.0f;
        this.r = 0.0f;
        this.s = 0.0f;
        this.t = 0;
        this.u = 0;
        this.v = 0;
        this.w = 0;
        this.x = null;
        this.y = 0;
        this.z = false;
        this.F = new Transformation();
        this.G = new Transformation();
        this.H = new float[9];
        this.z = z;
        this.y = i;
        this.e = strArr;
    }

    public y a(boolean z) {
        this.o = z;
        return this;
    }

    public y b(boolean z) {
        if (this.l != z) {
            this.l = z;
        }
        return this;
    }

    public boolean a() {
        return this.l;
    }

    public y a(Xfermode xfermode) {
        this.C = xfermode;
        return this;
    }

    public y a(float f, float f2) {
        this.A = f;
        this.B = f2;
        return this;
    }

    public y a(String... strArr) {
        if (strArr != null) {
            this.E = new e(this.b, this.z, this.y, this.d, strArr);
            this.m = true;
        }
        return this;
    }

    public y a(Bitmap... bitmapArr) {
        if (bitmapArr != null) {
            this.E = new e(this.b, this.z, this.y, bitmapArr);
            this.m = true;
        }
        return this;
    }

    public y a(Drawable drawable) {
        this.E = new e(drawable);
        this.m = true;
        return this;
    }

    public Drawable b() {
        return this.E == null ? null : this.E.b();
    }

    public Animation c() {
        return this.D;
    }

    public y a(Animation animation) {
        this.D = animation;
        return this;
    }

    public y c(boolean z) {
        if (this.D != null) {
            if (z) {
                this.D.cancel();
                this.D.reset();
                this.D.start();
            } else {
                this.D.cancel();
            }
        }
        return this;
    }

    public long d() {
        return this.i;
    }

    public y b(float f, float f2) {
        return a(f, f2, this.p, this.q);
    }

    public y a(float f, float f2, float f3, float f4) {
        if (!(this.r == f && this.s == f2 && this.p == f3 && this.q == f4)) {
            this.r = f;
            this.s = f2;
            this.p = f3;
            this.q = f4;
            this.n = true;
        }
        return this;
    }

    public y a(int i, int i2) {
        this.t = i;
        this.u = i2;
        this.m = true;
        return this;
    }

    public int a(y yVar) {
        return this.c.add(yVar) ? this.c.size() - 1 : -1;
    }

    public int a(y yVar, Animation animation) {
        return a(yVar, yVar.r, yVar.s, animation);
    }

    public int a(y yVar, float f, float f2) {
        return a(yVar, f, f2, null);
    }

    public int a(y yVar, float f, float f2, Animation animation) {
        int repeatCount;
        int i = 0;
        long j = 0;
        if (animation != null) {
            j = animation.getDuration();
            repeatCount = animation.getRepeatCount();
            i = animation.getRepeatMode();
        } else {
            repeatCount = 0;
        }
        return a(yVar, f, f2, animation, j, repeatCount, i);
    }

    public int a(y yVar, float f, float f2, Animation animation, long j, int i, int i2) {
        yVar.b(f, f2);
        yVar.a(animation);
        if (animation != null) {
            animation.setDuration(j);
            animation.setRepeatCount(i);
            animation.setRepeatMode(i2);
            animation.start();
        }
        return a(yVar);
    }

    public y a(int i) {
        try {
            return (y) this.c.get(i);
        } catch (Throwable th) {
            return null;
        }
    }

    protected boolean e() {
        return (this.D == null || !(this.D instanceof aa)) ? false : this.D.hasEnded();
    }

    protected boolean a(com.xunlei.tdlive.aniengine.a.a aVar) {
        if (this.e == null || this.e.length <= 0 || b() != null) {
            return true;
        }
        a(this.e);
        return b() != null;
    }

    protected boolean a(int i, float f, float f2) {
        if (!f()) {
            return false;
        }
        if (i == 0) {
            this.g = true;
            this.h = new PointF(f, f2);
        } else if (i == 1) {
            this.g = false;
            this.h = null;
        } else if (i == 2 && this.g) {
            float f3 = f - this.h.x;
            float f4 = f2 - this.h.y;
            float[] fArr = new float[9];
            this.F.getMatrix().getValues(fArr);
            fArr[2] = f3 + fArr[2];
            fArr[5] = f4 + fArr[5];
            this.F.getMatrix().setValues(fArr);
        }
        return this.g;
    }

    public float a(float f) {
        return f;
    }

    protected boolean f() {
        return this.f;
    }

    final boolean g() {
        int i;
        ArrayList arrayList = new ArrayList();
        for (i = 0; i < this.c.size(); i++) {
            if (((y) this.c.get(i)).g()) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        for (i = arrayList.size() - 1; i >= 0; i--) {
            this.c.remove(((Integer) arrayList.get(i)).intValue());
        }
        boolean e = e();
        if (e) {
            if (!(this.k || this.a == null)) {
                this.a.a(this);
            }
            this.k = true;
        }
        return e;
    }

    final boolean b(com.xunlei.tdlive.aniengine.a.a aVar) {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            if (!((y) it.next()).b(aVar)) {
                return false;
            }
        }
        this.d = aVar;
        return a(aVar);
    }

    final void a(long j) {
        this.j += j;
        this.i++;
        this.b.a();
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            ((y) it.next()).a(j);
        }
    }

    final void a(float f, int i, int i2) {
        if (this.o) {
            float a = a(f);
            if (a == 0.0f) {
                a = 1.0f;
            }
            if (this.E != null) {
                this.E.a(a, this.t, this.u, this.m);
            }
            if (this.m) {
                this.m = false;
                if (this.t == 0) {
                    this.v = i;
                } else {
                    this.v = (int) (((float) this.t) * a);
                }
                if (this.u == 0) {
                    this.w = i2;
                } else {
                    this.w = (int) (((float) this.u) * a);
                }
                if (this.E != null) {
                    this.v = this.E.a().width();
                    this.w = this.E.a().height();
                }
                if (this.D == null) {
                    this.n = true;
                }
            }
            if (this.n) {
                this.n = false;
                this.F.getMatrix().postTranslate(a(a, this.r, this.p, this.v, i) / a, a(a, this.s, this.q, this.w, i2) / a);
            }
            if (this.D != null) {
                if (!this.D.isInitialized()) {
                    this.D.initialize((int) (((float) this.v) / a), (int) (((float) this.w) / a), (int) (((float) i) / a), (int) (((float) i2) / a));
                }
                if (this.D instanceof aa) {
                    this.D.getTransformation(d(), this.F);
                } else {
                    this.D.getTransformation(AnimationUtils.currentAnimationTimeMillis(), this.F);
                }
            }
            this.G.set(this.F);
            this.G.getMatrix().getValues(this.H);
            float[] fArr = this.H;
            fArr[2] = fArr[2] * a;
            fArr = this.H;
            fArr[5] = fArr[5] * a;
            this.G.getMatrix().setValues(this.H);
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                ((y) it.next()).a(a, this.v, this.w);
            }
        }
    }

    final void a(Canvas canvas, int i, int i2, Paint paint, Paint paint2) {
        if (this.o) {
            e eVar;
            e eVar2 = this.E;
            if (this.c.isEmpty()) {
                eVar = eVar2;
            } else {
                int width;
                int height;
                if (this.x != null) {
                    width = this.x.getWidth();
                    height = this.x.getHeight();
                } else {
                    height = 0;
                    width = 0;
                }
                if (!(this.v == width && this.w == r0)) {
                    try {
                        this.x.recycle();
                        this.x = null;
                    } catch (Throwable th) {
                    }
                    this.x = Bitmap.createBitmap(this.v, this.w, Config.ARGB_8888);
                }
                Canvas canvas2 = new Canvas(this.x);
                canvas2.drawPaint(paint2);
                if (eVar2 != null) {
                    eVar2.a(canvas2);
                }
                Iterator it = this.c.iterator();
                while (it.hasNext()) {
                    ((y) it.next()).a(canvas2, this.v, this.w, paint, paint2);
                }
                eVar = new e(this.x);
                eVar.a(0, 0, this.v, this.w);
            }
            int save = canvas.save();
            canvas.concat(this.G.getMatrix());
            if (eVar != null) {
                eVar.a(this.A, this.B);
                eVar.a(this.C);
                eVar.a(a());
                eVar.a((int) (this.G.getAlpha() * 255.0f));
                eVar.a(canvas);
            }
            if (f()) {
                int color = paint.getColor();
                float strokeWidth = paint.getStrokeWidth();
                Style style = paint.getStyle();
                paint.setColor(this.g ? -16711936 : -65536);
                paint.setStrokeWidth(2.0f);
                paint.setStyle(Style.STROKE);
                if (eVar != null) {
                    canvas.drawRect(eVar.a(), paint);
                } else {
                    canvas.drawRect(new Rect(0, 0, this.v, this.w), paint);
                }
                paint.setColor(color);
                paint.setStrokeWidth(strokeWidth);
                paint.setStyle(style);
            }
            canvas.restoreToCount(save);
        }
    }

    final boolean a(MotionEvent motionEvent) {
        if (f()) {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                if (((y) it.next()).a(motionEvent)) {
                    return true;
                }
            }
            RectF rectF = new RectF(0.0f, 0.0f, (float) this.v, (float) this.w);
            if (this.E != null) {
                rectF.left = (float) this.E.a().left;
                rectF.right = (float) this.E.a().right;
                rectF.top = (float) this.E.a().top;
                rectF.bottom = (float) this.E.a().bottom;
            }
            this.F.getMatrix().mapRect(rectF);
            if (rectF.contains(motionEvent.getX(), motionEvent.getY())) {
                return a(motionEvent.getAction(), motionEvent.getX(), motionEvent.getY());
            }
        }
        return false;
    }

    final void d(boolean z) {
        this.f = z;
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            ((y) it.next()).d(z);
        }
    }

    private float a(float f, float f2, float f3, int i, int i2) {
        float f4;
        float f5;
        if (Math.abs(f2) <= 1.0f) {
            f4 = ((float) i2) * f2;
        } else {
            f4 = f2 * f;
        }
        if (Math.abs(f3) <= 1.0f) {
            f5 = ((float) i) * f3;
        } else {
            f5 = f3 * f;
        }
        return f4 - f5;
    }

    public static y b(String... strArr) {
        return a(0, strArr);
    }

    public static y a(int i, String... strArr) {
        return a(false, i, strArr);
    }

    public static y a(boolean z, int i, String... strArr) {
        return new y(z, i, strArr);
    }
}
