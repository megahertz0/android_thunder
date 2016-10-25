package com.inmobi.ads;

import android.app.Activity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.xunlei.tdlive.R;
import java.lang.ref.WeakReference;

// compiled from: DecorViewVisibilityTracker.java
final class m extends ap {
    private static final String b;
    private OnPreDrawListener c;
    private final WeakReference<View> d;

    static {
        b = m.class.getSimpleName();
    }

    public m(a aVar, Activity activity) {
        super(aVar);
        View decorView = activity.getWindow().getDecorView();
        this.d = new WeakReference(decorView);
        ViewTreeObserver viewTreeObserver = decorView.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            this.c = new OnPreDrawListener() {
                public boolean onPreDraw() {
                    m.this.h();
                    return true;
                }
            };
            viewTreeObserver.addOnPreDrawListener(this.c);
            return;
        }
        Logger.a(InternalLogLevel.INTERNAL, b, "Visibility Tracker was unable to track views because the root view tree observer was not alive");
    }

    protected final int a() {
        return R.styleable.AppCompatTheme_buttonStyle;
    }

    protected final void b() {
    }

    public final void c() {
        if (!f()) {
            j();
            super.c();
        }
    }

    public final void d() {
        if (f()) {
            i();
            super.d();
        }
    }

    private void i() {
        View view = (View) this.d.get();
        if (view != null) {
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnPreDrawListener(this.c);
            } else {
                Logger.a(InternalLogLevel.INTERNAL, b, "Visibility Tracker was unable to track views because the root view tree observer was not alive");
            }
        }
    }

    private void j() {
        View view = (View) this.d.get();
        if (view != null) {
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnPreDrawListener(this.c);
            }
        }
    }

    protected final void e() {
        j();
        super.e();
    }
}
