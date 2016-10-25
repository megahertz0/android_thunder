package android.support.design.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.design.R;
import android.support.design.widget.CoordinatorLayout.b;
import android.support.design.widget.CoordinatorLayout.d;
import android.support.design.widget.Snackbar.SnackbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.AutoScrollHelper;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.widget.r;
import android.support.v7.widget.s;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.List;
import org.android.spdy.SpdyAgent;

@b(a = Behavior.class)
public class FloatingActionButton extends VisibilityAwareImageButton {
    private ColorStateList a;
    private Mode b;
    private int c;
    private int d;
    private int e;
    private int f;
    private boolean g;
    private final Rect h;
    private s i;
    private aa j;

    public static class Behavior extends android.support.design.widget.CoordinatorLayout.Behavior<FloatingActionButton> {
        private static final boolean a;
        private bf b;
        private float c;
        private Rect d;

        public final /* synthetic */ boolean a(CoordinatorLayout coordinatorLayout, View view, int i) {
            int i2;
            int i3 = 0;
            view = (FloatingActionButton) view;
            List a = coordinatorLayout.a(view);
            int size = a.size();
            for (i2 = 0; i2 < size; i2++) {
                View view2 = (View) a.get(i2);
                if ((view2 instanceof AppBarLayout) && a(coordinatorLayout, (AppBarLayout) view2, (FloatingActionButton) view)) {
                    break;
                }
            }
            coordinatorLayout.a(view, i);
            Rect c = view.h;
            if (c != null && c.centerX() > 0 && c.centerY() > 0) {
                d dVar = (d) view.getLayoutParams();
                if (view.getRight() >= coordinatorLayout.getWidth() - dVar.rightMargin) {
                    i2 = c.right;
                } else if (view.getLeft() <= dVar.leftMargin) {
                    i2 = -c.left;
                } else {
                    i2 = 0;
                }
                if (view.getBottom() >= coordinatorLayout.getBottom() - dVar.bottomMargin) {
                    i3 = c.bottom;
                } else if (view.getTop() <= dVar.topMargin) {
                    i3 = -c.top;
                }
                view.offsetTopAndBottom(i3);
                view.offsetLeftAndRight(i2);
            }
            return true;
        }

        public final /* synthetic */ boolean b(CoordinatorLayout coordinatorLayout, View view, View view2) {
            FloatingActionButton floatingActionButton = (FloatingActionButton) view;
            if (view2 instanceof SnackbarLayout) {
                a(coordinatorLayout, floatingActionButton);
            } else if (view2 instanceof AppBarLayout) {
                a(coordinatorLayout, (AppBarLayout) view2, floatingActionButton);
            }
            return false;
        }

        public final /* synthetic */ void c(CoordinatorLayout coordinatorLayout, View view, View view2) {
            FloatingActionButton floatingActionButton = (FloatingActionButton) view;
            if (view2 instanceof SnackbarLayout) {
                a(coordinatorLayout, floatingActionButton);
            }
        }

        static {
            a = VERSION.SDK_INT >= 11;
        }

        private boolean a(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, FloatingActionButton floatingActionButton) {
            if (((d) floatingActionButton.getLayoutParams()).f != appBarLayout.getId()) {
                return false;
            }
            if (floatingActionButton.getUserSetVisibility() != 0) {
                return false;
            }
            if (this.d == null) {
                this.d = new Rect();
            }
            Rect rect = this.d;
            bn.a(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                floatingActionButton.getImpl().c();
            } else {
                floatingActionButton.getImpl().d();
            }
            return true;
        }

        private void a(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton) {
            float f = AutoScrollHelper.RELATIVE_UNSPECIFIED;
            List a = coordinatorLayout.a((View) floatingActionButton);
            int size = a.size();
            int i = 0;
            while (i < size) {
                float min;
                View view = (View) a.get(i);
                if (view instanceof SnackbarLayout) {
                    Object obj;
                    if (floatingActionButton.getVisibility() == 0 && view.getVisibility() == 0) {
                        boolean z;
                        Rect rect = coordinatorLayout.h;
                        coordinatorLayout.a((View) floatingActionButton, floatingActionButton.getParent() != coordinatorLayout, rect);
                        Rect rect2 = coordinatorLayout.i;
                        if (view.getParent() != coordinatorLayout) {
                            z = true;
                        } else {
                            z = false;
                        }
                        coordinatorLayout.a(view, z, rect2);
                        if (rect.left > rect2.right || rect.top > rect2.bottom || rect.right < rect2.left || rect.bottom < rect2.top) {
                            obj = null;
                        } else {
                            int i2 = 1;
                        }
                    } else {
                        obj = null;
                    }
                    if (obj != null) {
                        min = Math.min(f, ViewCompat.getTranslationY(view) - ((float) view.getHeight()));
                        i++;
                        f = min;
                    }
                }
                min = f;
                i++;
                f = min;
            }
            if (this.c != f) {
                min = ViewCompat.getTranslationY(floatingActionButton);
                if (this.b != null && this.b.a.b()) {
                    this.b.a.e();
                }
                if (!floatingActionButton.isShown() || Math.abs(min - f) <= ((float) floatingActionButton.getHeight()) * 0.667f) {
                    ViewCompat.setTranslationY(floatingActionButton, f);
                } else {
                    if (this.b == null) {
                        this.b = bq.a();
                        this.b.a(a.b);
                        this.b.a(new t(this, floatingActionButton));
                    }
                    this.b.a(min, f);
                    this.b.a.a();
                }
                this.c = f;
            }
        }

        public final /* bridge */ /* synthetic */ boolean a_(View view) {
            return a && (view instanceof SnackbarLayout);
        }
    }

    private class a implements ah {
        private a() {
        }

        public final float a() {
            return ((float) FloatingActionButton.this.getSizeDimension()) / 2.0f;
        }

        public final void a(int i, int i2, int i3, int i4) {
            FloatingActionButton.this.h.set(i, i2, i3, i4);
            FloatingActionButton.this.setPadding(FloatingActionButton.this.f + i, FloatingActionButton.this.f + i2, FloatingActionButton.this.f + i3, FloatingActionButton.this.f + i4);
        }

        public final void a(Drawable drawable) {
            super.setBackgroundDrawable(drawable);
        }

        public final boolean b() {
            return FloatingActionButton.this.g;
        }
    }

    public /* bridge */ /* synthetic */ void setVisibility(int i) {
        super.setVisibility(i);
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet, int i) {
        Mode mode;
        super(context, attributeSet, i);
        this.h = new Rect();
        be.a(context);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FloatingActionButton, i, R.style.Widget_Design_FloatingActionButton);
        this.a = obtainStyledAttributes.getColorStateList(R.styleable.FloatingActionButton_backgroundTint);
        switch (obtainStyledAttributes.getInt(R.styleable.FloatingActionButton_backgroundTintMode, -1)) {
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                mode = Mode.SRC_OVER;
                break;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                mode = Mode.SRC_IN;
                break;
            case XZBDevice.Pause:
                mode = Mode.SRC_ATOP;
                break;
            case XZBDevice.Predownload:
                mode = Mode.MULTIPLY;
                break;
            case XZBDevice.Delete:
                mode = Mode.SCREEN;
                break;
            default:
                mode = null;
                break;
        }
        this.b = mode;
        this.d = obtainStyledAttributes.getColor(R.styleable.FloatingActionButton_rippleColor, 0);
        this.e = obtainStyledAttributes.getInt(R.styleable.FloatingActionButton_fabSize, 0);
        this.c = obtainStyledAttributes.getDimensionPixelSize(R.styleable.FloatingActionButton_borderWidth, 0);
        float dimension = obtainStyledAttributes.getDimension(R.styleable.FloatingActionButton_elevation, AutoScrollHelper.RELATIVE_UNSPECIFIED);
        float dimension2 = obtainStyledAttributes.getDimension(R.styleable.FloatingActionButton_pressedTranslationZ, AutoScrollHelper.RELATIVE_UNSPECIFIED);
        this.g = obtainStyledAttributes.getBoolean(R.styleable.FloatingActionButton_useCompatPadding, false);
        obtainStyledAttributes.recycle();
        this.i = new s(this, r.a());
        this.i.a(attributeSet, i);
        this.f = (getSizeDimension() - ((int) getResources().getDimension(R.dimen.design_fab_image_size))) / 2;
        getImpl().a(this.a, this.b, this.d, this.c);
        getImpl().c(dimension);
        aa impl = getImpl();
        if (impl.g != dimension2) {
            impl.g = dimension2;
            impl.b(dimension2);
        }
        getImpl().h();
    }

    protected void onMeasure(int i, int i2) {
        int sizeDimension = getSizeDimension();
        sizeDimension = Math.min(a(sizeDimension, i), a(sizeDimension, i2));
        setMeasuredDimension((this.h.left + sizeDimension) + this.h.right, (sizeDimension + this.h.top) + this.h.bottom);
    }

    public void setRippleColor(int i) {
        if (this.d != i) {
            this.d = i;
            getImpl().a(i);
        }
    }

    public ColorStateList getBackgroundTintList() {
        return this.a;
    }

    public void setBackgroundTintList(ColorStateList colorStateList) {
        if (this.a != colorStateList) {
            this.a = colorStateList;
            getImpl().a(colorStateList);
        }
    }

    public Mode getBackgroundTintMode() {
        return this.b;
    }

    public void setBackgroundTintMode(Mode mode) {
        if (this.b != mode) {
            this.b = mode;
            getImpl().a(mode);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
    }

    public void setBackgroundResource(int i) {
    }

    public void setBackgroundColor(int i) {
    }

    public void setImageResource(int i) {
        this.i.a(i);
    }

    public void setUseCompatPadding(boolean z) {
        if (this.g != z) {
            this.g = z;
            getImpl().e();
        }
    }

    public boolean getUseCompatPadding() {
        return this.g;
    }

    final int getSizeDimension() {
        switch (this.e) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return getResources().getDimensionPixelSize(R.dimen.design_fab_size_mini);
            default:
                return getResources().getDimensionPixelSize(R.dimen.design_fab_size_normal);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        aa impl = getImpl();
        if (impl.f()) {
            if (impl.m == null) {
                impl.m = new ab(impl);
            }
            impl.k.getViewTreeObserver().addOnPreDrawListener(impl.m);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        aa impl = getImpl();
        if (impl.m != null) {
            impl.k.getViewTreeObserver().removeOnPreDrawListener(impl.m);
            impl.m = null;
        }
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        getImpl().a(getDrawableState());
    }

    @TargetApi(11)
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        getImpl().b();
    }

    public Drawable getContentBackground() {
        return getImpl().e;
    }

    private static int a(int i, int i2) {
        int mode = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i2);
        switch (mode) {
            case ExploreByTouchHelper.INVALID_ID:
                return Math.min(i, size);
            case 1073741824:
                return size;
            default:
                return i;
        }
    }

    public float getCompatElevation() {
        return getImpl().a();
    }

    public void setCompatElevation(float f) {
        getImpl().c(f);
    }

    private aa getImpl() {
        if (this.j == null) {
            aa acVar;
            int i = VERSION.SDK_INT;
            if (i >= 21) {
                acVar = new ac(this, new a());
            } else if (i >= 14) {
                acVar = new x(this, new a());
            } else {
                acVar = new u(this, new a());
            }
            this.j = acVar;
        }
        return this.j;
    }
}
