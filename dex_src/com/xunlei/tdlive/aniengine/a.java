package com.xunlei.tdlive.aniengine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

// compiled from: AniEngine.java
public class a implements OnTouchListener, Runnable {
    private SurfaceView a;
    private a b;
    private Thread c;
    private int d;
    private boolean e;
    private Paint f;
    private Paint g;
    private CopyOnWriteArrayList<y> h;

    // compiled from: AniEngine.java
    public static interface a {
        float a(float f);

        String[] a(String str);

        float b(float f);

        Bitmap b(String str);
    }

    public a() {
        this.d = 30;
        this.h = new CopyOnWriteArrayList();
    }

    public void a(SurfaceView surfaceView) {
        this.a = surfaceView;
        this.a.setOnTouchListener(this);
    }

    public void a() {
        if (this.a != null) {
            this.a.setOnTouchListener(null);
            this.a = null;
        }
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    public void b() {
        this.b = null;
    }

    public void c() {
        if (this.b != null && this.a != null && this.c == null) {
            this.c = new Thread(this, "AniEngine");
            if (this.c != null) {
                this.c.start();
            }
        }
    }

    public void d() {
        if (this.c != null) {
            this.c.interrupt();
        }
        this.c = null;
    }

    public boolean a(y yVar) {
        return this.h.add(yVar);
    }

    public int a(Class<? extends y>... clsArr) {
        if (clsArr.length <= 0) {
            return this.h.size();
        }
        Iterator it = this.h.iterator();
        int i = 0;
        while (it.hasNext()) {
            y yVar = (y) it.next();
            int length = clsArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                if (clsArr[i2].isAssignableFrom(yVar.getClass())) {
                    i++;
                    break;
                }
            }
        }
        return i;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        for (int i = 0; i < this.h.size(); i++) {
            if (((y) this.h.get(i)).a(motionEvent)) {
                return true;
            }
        }
        return false;
    }

    public void run() {
        long j = 41;
        while (this.c != null && this.a != null) {
            try {
                long uptimeMillis = SystemClock.uptimeMillis();
                SurfaceHolder holder = this.a.getHolder();
                if (holder.getSurface().isValid()) {
                    try {
                        Canvas lockCanvas = holder.lockCanvas();
                        if (lockCanvas != null) {
                            try {
                                a(lockCanvas, lockCanvas.getWidth(), lockCanvas.getHeight(), j);
                            } catch (Throwable th) {
                            }
                        }
                        holder.unlockCanvasAndPost(lockCanvas);
                    } catch (Throwable th2) {
                    }
                }
                long uptimeMillis2 = ((long) (1000 / this.d)) - (SystemClock.uptimeMillis() - uptimeMillis);
                if (uptimeMillis2 > 0) {
                    try {
                        Thread.sleep(uptimeMillis2);
                    } catch (InterruptedException e) {
                    }
                }
                j = SystemClock.uptimeMillis() - uptimeMillis;
            } catch (Throwable th3) {
            }
        }
        this.f = null;
        this.g = null;
        this.h.clear();
    }

    private void a(Canvas canvas, int i, int i2, long j) {
        float b = this.b.b(720.0f);
        if (this.f == null) {
            this.f = new Paint();
            this.f.setAntiAlias(true);
            this.g = new Paint();
            this.g.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
        }
        canvas.drawPaint(this.g);
        if (this.e && j != 0) {
            String toString = new StringBuilder("AniEngine Render FPS:").append(1000 / j).append(", SPRS:").append(this.h.size()).toString();
            float a = this.b.a(14.0f);
            this.f.setTextSize(a);
            this.f.setColor(-1);
            canvas.drawText(toString, ((float) i) - this.f.measureText(toString), a, this.f);
            this.f.reset();
        }
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < this.h.size(); i3++) {
            try {
                y yVar = (y) this.h.get(i3);
                if (yVar.g()) {
                    arrayList.add(Integer.valueOf(i3));
                } else {
                    yVar.d(this.e);
                    if (yVar.b(this.b)) {
                        yVar.a(j);
                        yVar.a(b, i, i2);
                        yVar.a(canvas, i, i2, this.f, this.g);
                    }
                }
            } catch (Throwable th) {
                arrayList.add(Integer.valueOf(i3));
            }
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            this.h.remove(((Integer) arrayList.get(size)).intValue());
        }
    }
}
