package com.xunlei.tdlive.play.view;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import com.xunlei.tdlive.R;
import java.lang.ref.WeakReference;

// compiled from: BaseWindowHelper.java
public abstract class b {
    protected PopupWindow a;
    protected WeakReference<Activity> b;
    protected a c;
    protected View d;

    // compiled from: BaseWindowHelper.java
    public static class a {
        protected Window a;
        protected View b;
        protected Object c;

        public a() {
            this.a = null;
            this.b = null;
            this.c = new Object();
        }

        public void a(Window window) {
            this.a = window;
        }

        public void a(View view) {
            this.b = view;
        }

        public void a(Object obj) {
            if (obj != null) {
                this.c = obj;
            }
        }
    }

    protected abstract View a(LayoutInflater layoutInflater);

    public b(Activity activity) {
        this.a = null;
        this.b = null;
        this.c = new a();
        a(activity);
    }

    private void a(Activity activity) {
        if (activity instanceof Activity) {
            this.b = new WeakReference(activity);
            return;
        }
        throw new IllegalArgumentException("context is not activity!");
    }

    public void a() {
        if (this.a != null) {
            this.a.dismiss();
            this.a = null;
        }
    }

    private void a(int i) {
        Activity activity = (Activity) this.b.get();
        if (activity != null && this.c.a != null) {
            View decorView = this.c.a.getDecorView();
            if (decorView instanceof FrameLayout) {
                FrameLayout frameLayout = (FrameLayout) decorView;
                Drawable drawable = activity.getResources().getDrawable(R.drawable.xllive_black_rectangle);
                drawable.setAlpha(i);
                frameLayout.setForeground(drawable);
            }
        }
    }

    public void b() {
        c();
        a(this.a);
        d();
    }

    protected void c() {
        if (this.a == null) {
            if (this.c.b == null) {
                throw new IllegalArgumentException("referenceView cannot be null!");
            }
            Activity activity = (Activity) this.b.get();
            if (activity != null) {
                this.d = a(LayoutInflater.from(activity));
                this.a = new PopupWindow(this.d, -1, -2);
                this.a.setBackgroundDrawable(new BitmapDrawable());
                this.a.setOutsideTouchable(true);
                this.a.setFocusable(true);
                this.a.setAnimationStyle(R.style.PopupAnimation2);
                this.a.setOnDismissListener(new c(this));
                a(0);
            }
        }
    }

    protected void a(PopupWindow popupWindow) {
    }

    protected void d() {
        try {
            this.a.showAtLocation(this.c.b, com.xunlei.xllib.R.styleable.AppCompatTheme_panelMenuListTheme, 0, 0);
        } catch (Exception e) {
        }
    }

    public void a(a aVar) {
        if (aVar != null) {
            this.c = aVar;
        }
    }

    protected Object e() {
        Object obj = this.c.c;
        if (obj != null) {
            return obj;
        }
        throw new IllegalStateException("unexpected exception!");
    }

    public void a(OnDismissListener onDismissListener) {
        if (this.a != null) {
            this.a.setOnDismissListener(onDismissListener);
        }
    }
}
