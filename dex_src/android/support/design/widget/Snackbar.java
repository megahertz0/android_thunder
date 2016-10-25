package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.support.design.R;
import android.support.design.widget.Snackbar.SnackbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.AutoScrollHelper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public final class Snackbar {
    private static final Handler d;
    final ViewGroup a;
    final SnackbarLayout b;
    final a c;
    private final AccessibilityManager e;

    public static class SnackbarLayout extends LinearLayout {
        TextView a;
        Button b;
        private int c;
        private int d;
        private b e;
        private a f;

        static interface a {
            void a();
        }

        static interface b {
            void a();
        }

        public SnackbarLayout(Context context) {
            this(context, null);
        }

        public SnackbarLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SnackbarLayout);
            this.c = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SnackbarLayout_android_maxWidth, -1);
            this.d = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SnackbarLayout_maxActionInlineWidth, -1);
            if (obtainStyledAttributes.hasValue(R.styleable.SnackbarLayout_elevation)) {
                ViewCompat.setElevation(this, (float) obtainStyledAttributes.getDimensionPixelSize(R.styleable.SnackbarLayout_elevation, 0));
            }
            obtainStyledAttributes.recycle();
            setClickable(true);
            LayoutInflater.from(context).inflate(R.layout.design_layout_snackbar_include, this);
            ViewCompat.setAccessibilityLiveRegion(this, 1);
            ViewCompat.setImportantForAccessibility(this, 1);
        }

        protected void onFinishInflate() {
            super.onFinishInflate();
            this.a = (TextView) findViewById(R.id.snackbar_text);
            this.b = (Button) findViewById(R.id.snackbar_action);
        }

        TextView getMessageView() {
            return this.a;
        }

        Button getActionView() {
            return this.b;
        }

        protected void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            if (this.c > 0 && getMeasuredWidth() > this.c) {
                i = MeasureSpec.makeMeasureSpec(this.c, 1073741824);
                super.onMeasure(i, i2);
            }
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.design_snackbar_padding_vertical_2lines);
            int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.design_snackbar_padding_vertical);
            int i3 = this.a.getLayout().getLineCount() > 1 ? 1 : 0;
            if (i3 == 0 || this.d <= 0 || this.b.getMeasuredWidth() <= this.d) {
                if (i3 == 0) {
                    dimensionPixelSize = dimensionPixelSize2;
                }
                if (a(0, dimensionPixelSize, dimensionPixelSize)) {
                    dimensionPixelSize = 1;
                }
                dimensionPixelSize = 0;
            } else {
                if (a(1, dimensionPixelSize, dimensionPixelSize - dimensionPixelSize2)) {
                    dimensionPixelSize = 1;
                }
                dimensionPixelSize = 0;
            }
            if (dimensionPixelSize != 0) {
                super.onMeasure(i, i2);
            }
        }

        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            if (this.e != null) {
                this.e.a();
            }
        }

        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
        }

        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            if (this.f != null) {
                this.f.a();
            }
        }

        void setOnLayoutChangeListener(b bVar) {
            this.e = bVar;
        }

        void setOnAttachStateChangeListener(a aVar) {
            this.f = aVar;
        }

        private boolean a(int i, int i2, int i3) {
            boolean z = false;
            if (i != getOrientation()) {
                setOrientation(i);
                z = true;
            }
            if (this.a.getPaddingTop() == i2 && this.a.getPaddingBottom() == i3) {
                return z;
            }
            View view = this.a;
            if (ViewCompat.isPaddingRelative(view)) {
                ViewCompat.setPaddingRelative(view, ViewCompat.getPaddingStart(view), i2, ViewCompat.getPaddingEnd(view), i3);
            } else {
                view.setPadding(view.getPaddingLeft(), i2, view.getPaddingRight(), i3);
            }
            return true;
        }
    }

    final class a extends SwipeDismissBehavior<SnackbarLayout> {
        a() {
        }

        public final boolean b(View view) {
            return view instanceof SnackbarLayout;
        }

        private boolean a(CoordinatorLayout coordinatorLayout, SnackbarLayout snackbarLayout, MotionEvent motionEvent) {
            if (coordinatorLayout.a((View) snackbarLayout, (int) motionEvent.getX(), (int) motionEvent.getY())) {
                switch (motionEvent.getActionMasked()) {
                    case SpdyAgent.ACCS_TEST_SERVER:
                        ar.a().a(Snackbar.this.c);
                        break;
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                    case XZBDevice.DOWNLOAD_LIST_FAILED:
                        ar.a().b(Snackbar.this.c);
                        break;
                }
            }
            return super.a(coordinatorLayout, (View) snackbarLayout, motionEvent);
        }
    }

    static {
        d = new Handler(Looper.getMainLooper(), new ai());
    }

    final void a() {
        if (VERSION.SDK_INT >= 14) {
            ViewCompat.setTranslationY(this.b, (float) this.b.getHeight());
            ViewCompat.animate(this.b).translationY(AutoScrollHelper.RELATIVE_UNSPECIFIED).setInterpolator(a.b).setDuration(250).setListener(new ao(this)).start();
            return;
        }
        Animation loadAnimation = AnimationUtils.loadAnimation(this.b.getContext(), R.anim.design_snackbar_in);
        loadAnimation.setInterpolator(a.b);
        loadAnimation.setDuration(250);
        loadAnimation.setAnimationListener(new ap(this));
        this.b.startAnimation(loadAnimation);
    }

    final void b() {
        ar a = ar.a();
        a aVar = this.c;
        synchronized (a.a) {
            if (a.d(aVar)) {
                a.b(a.b);
            }
        }
    }

    final void c() {
        ar a = ar.a();
        a aVar = this.c;
        synchronized (a.a) {
            if (a.d(aVar)) {
                a.b = null;
                if (!(a.c == null || a.c == null)) {
                    a.b = a.c;
                    a.c = null;
                    if (((a) a.b.a.get()) == null) {
                        a.b = null;
                    }
                }
            }
        }
        ViewParent parent = this.b.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this.b);
        }
    }

    final boolean d() {
        return !this.e.isEnabled();
    }

    static /* synthetic */ void a(Snackbar snackbar) {
        ar a = ar.a();
        a aVar = snackbar.c;
        synchronized (a.a) {
            if (a.d(aVar)) {
                a.a(a.b);
            } else if (a.e(aVar)) {
                a.a(a.c);
            }
        }
    }
}
