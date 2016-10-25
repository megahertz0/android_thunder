package android.support.design.widget;

import android.support.design.widget.CoordinatorLayout.Behavior;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.AutoScrollHelper;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.view.MotionEvent;
import android.view.View;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public class SwipeDismissBehavior<V extends View> extends Behavior<V> {
    private ViewDragHelper a;
    a b;
    int c;
    float d;
    float e;
    private boolean f;
    private float g;
    private boolean h;
    private float i;
    private final Callback j;

    public static interface a {
        void a(int i);

        void a(View view);
    }

    private class b implements Runnable {
        private final View b;
        private final boolean c;

        b(View view, boolean z) {
            this.b = view;
            this.c = z;
        }

        public final void run() {
            if (SwipeDismissBehavior.this != null && SwipeDismissBehavior.this.continueSettling(true)) {
                ViewCompat.postOnAnimation(this.b, this);
            } else if (this.c && SwipeDismissBehavior.this.b != null) {
                SwipeDismissBehavior.this.b.a(this.b);
            }
        }
    }

    public SwipeDismissBehavior() {
        this.g = 0.0f;
        this.c = 2;
        this.i = 0.5f;
        this.d = 0.0f;
        this.e = 0.5f;
        this.j = new av(this);
    }

    public boolean a(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        switch (MotionEventCompat.getActionMasked(motionEvent)) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                if (this.f) {
                    this.f = false;
                    return false;
                }
            default:
                boolean z;
                if (coordinatorLayout.a((View) v, (int) motionEvent.getX(), (int) motionEvent.getY())) {
                    z = false;
                } else {
                    z = true;
                }
                this.f = z;
                break;
        }
        if (this.f) {
            return false;
        }
        if (this.a == null) {
            ViewDragHelper create;
            if (this.h) {
                create = ViewDragHelper.create(coordinatorLayout, this.g, this.j);
            } else {
                create = ViewDragHelper.create(coordinatorLayout, this.j);
            }
            this.a = create;
        }
        return this.a.shouldInterceptTouchEvent(motionEvent);
    }

    public final boolean b(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (this.a == null) {
            return false;
        }
        this.a.processTouchEvent(motionEvent);
        return true;
    }

    public boolean b(View view) {
        return true;
    }

    static float a(float f) {
        return Math.min(Math.max(AutoScrollHelper.RELATIVE_UNSPECIFIED, f), 1.0f);
    }

    static float a(float f, float f2, float f3) {
        return (f3 - f) / (f2 - f);
    }
}
