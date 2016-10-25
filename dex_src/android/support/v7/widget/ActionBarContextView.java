package android.support.v7.widget;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.appcompat.R;
import android.support.v7.view.b;
import android.support.v7.view.menu.f;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class ActionBarContextView extends a {
    public View g;
    public boolean h;
    private CharSequence i;
    private CharSequence j;
    private View k;
    private LinearLayout l;
    private TextView m;
    private TextView n;
    private int o;
    private int p;
    private int q;

    public final /* bridge */ /* synthetic */ ViewPropertyAnimatorCompat a(int i, long j) {
        return super.a(i, j);
    }

    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    public /* bridge */ /* synthetic */ int getContentHeight() {
        return super.getContentHeight();
    }

    public /* bridge */ /* synthetic */ boolean onHoverEvent(MotionEvent motionEvent) {
        return super.onHoverEvent(motionEvent);
    }

    public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    public /* bridge */ /* synthetic */ void setVisibility(int i) {
        super.setVisibility(i);
    }

    public ActionBarContextView(Context context) {
        this(context, null);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.actionModeStyle);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        cm a = cm.a(context, attributeSet, R.styleable.ActionMode, i);
        setBackgroundDrawable(a.a(R.styleable.ActionMode_background));
        this.o = a.e(R.styleable.ActionMode_titleTextStyle, 0);
        this.p = a.e(R.styleable.ActionMode_subtitleTextStyle, 0);
        this.e = a.d(R.styleable.ActionMode_height, 0);
        this.q = a.e(R.styleable.ActionMode_closeItemLayout, R.layout.abc_action_mode_close_item_material);
        a.a.recycle();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.d != null) {
            this.d.f();
            this.d.h();
        }
    }

    public void setContentHeight(int i) {
        this.e = i;
    }

    public void setCustomView(View view) {
        if (this.k != null) {
            removeView(this.k);
        }
        this.k = view;
        if (!(view == null || this.l == null)) {
            removeView(this.l);
            this.l = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setTitle(CharSequence charSequence) {
        this.i = charSequence;
        c();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.j = charSequence;
        c();
    }

    public CharSequence getTitle() {
        return this.i;
    }

    public CharSequence getSubtitle() {
        return this.j;
    }

    private void c() {
        int i;
        int i2 = XZBDevice.Wait;
        Object obj = 1;
        if (this.l == null) {
            LayoutInflater.from(getContext()).inflate(R.layout.abc_action_bar_title_item, this);
            this.l = (LinearLayout) getChildAt(getChildCount() - 1);
            this.m = (TextView) this.l.findViewById(R.id.action_bar_title);
            this.n = (TextView) this.l.findViewById(R.id.action_bar_subtitle);
            if (this.o != 0) {
                this.m.setTextAppearance(getContext(), this.o);
            }
            if (this.p != 0) {
                this.n.setTextAppearance(getContext(), this.p);
            }
        }
        this.m.setText(this.i);
        this.n.setText(this.j);
        if (TextUtils.isEmpty(this.i)) {
            Object obj2 = null;
        } else {
            int i3 = 1;
        }
        if (TextUtils.isEmpty(this.j)) {
            obj = null;
        }
        TextView textView = this.n;
        if (obj != null) {
            i = 0;
        } else {
            i = 8;
        }
        textView.setVisibility(i);
        LinearLayout linearLayout = this.l;
        if (!(obj2 == null && obj == null)) {
            i2 = 0;
        }
        linearLayout.setVisibility(i2);
        if (this.l.getParent() == null) {
            addView(this.l);
        }
    }

    public final void a(b bVar) {
        if (this.g == null) {
            this.g = LayoutInflater.from(getContext()).inflate(this.q, this, false);
            addView(this.g);
        } else if (this.g.getParent() == null) {
            addView(this.g);
        }
        this.g.findViewById(R.id.action_mode_close_button).setOnClickListener(new d(this, bVar));
        f fVar = (f) bVar.b();
        if (this.d != null) {
            this.d.g();
        }
        this.d = new ActionMenuPresenter(getContext());
        this.d.d();
        LayoutParams layoutParams = new LayoutParams(-2, -1);
        fVar.a(this.d, this.b);
        this.c = (ActionMenuView) this.d.a((ViewGroup) this);
        this.c.setBackgroundDrawable(null);
        addView(this.c, layoutParams);
    }

    public final void b() {
        removeAllViews();
        this.k = null;
        this.c = null;
    }

    public final boolean a() {
        return this.d != null ? this.d.e() : false;
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(-1, -2);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new MarginLayoutParams(getContext(), attributeSet);
    }

    protected void onMeasure(int i, int i2) {
        int i3 = 1073741824;
        int i4 = 0;
        if (MeasureSpec.getMode(i) != 1073741824) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with android:layout_width=\"match_parent\" (or fill_parent)");
        } else if (MeasureSpec.getMode(i2) == 0) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with android:layout_height=\"wrap_content\"");
        } else {
            int i5;
            int a;
            int size = MeasureSpec.getSize(i);
            if (this.e > 0) {
                i5 = this.e;
            } else {
                i5 = MeasureSpec.getSize(i2);
            }
            int paddingTop = getPaddingTop() + getPaddingBottom();
            int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
            int i6 = i5 - paddingTop;
            int makeMeasureSpec = MeasureSpec.makeMeasureSpec(i6, ExploreByTouchHelper.INVALID_ID);
            if (this.g != null) {
                a = a(this.g, paddingLeft, makeMeasureSpec);
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.g.getLayoutParams();
                paddingLeft = a - (marginLayoutParams.rightMargin + marginLayoutParams.leftMargin);
            }
            if (this.c != null && this.c.getParent() == this) {
                paddingLeft = a(this.c, paddingLeft, makeMeasureSpec);
            }
            if (this.l != null && this.k == null) {
                if (this.h) {
                    this.l.measure(MeasureSpec.makeMeasureSpec(0, 0), makeMeasureSpec);
                    a = this.l.getMeasuredWidth();
                    if (a <= paddingLeft) {
                        Object obj = 1;
                    } else {
                        makeMeasureSpec = 0;
                    }
                    if (makeMeasureSpec != 0) {
                        paddingLeft -= a;
                    }
                    this.l.setVisibility(makeMeasureSpec != 0 ? 0 : XZBDevice.Wait);
                } else {
                    paddingLeft = a(this.l, paddingLeft, makeMeasureSpec);
                }
            }
            if (this.k != null) {
                int min;
                LayoutParams layoutParams = this.k.getLayoutParams();
                if (layoutParams.width != -2) {
                    makeMeasureSpec = 1073741824;
                } else {
                    makeMeasureSpec = Integer.MIN_VALUE;
                }
                if (layoutParams.width >= 0) {
                    paddingLeft = Math.min(layoutParams.width, paddingLeft);
                }
                if (layoutParams.height == -2) {
                    i3 = Integer.MIN_VALUE;
                }
                if (layoutParams.height >= 0) {
                    min = Math.min(layoutParams.height, i6);
                } else {
                    min = i6;
                }
                this.k.measure(MeasureSpec.makeMeasureSpec(paddingLeft, makeMeasureSpec), MeasureSpec.makeMeasureSpec(min, i3));
            }
            if (this.e <= 0) {
                makeMeasureSpec = getChildCount();
                i5 = 0;
                while (i4 < makeMeasureSpec) {
                    paddingLeft = getChildAt(i4).getMeasuredHeight() + paddingTop;
                    if (paddingLeft <= i5) {
                        paddingLeft = i5;
                    }
                    i4++;
                    i5 = paddingLeft;
                }
                setMeasuredDimension(size, i5);
                return;
            }
            setMeasuredDimension(size, i5);
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        boolean a = cw.a(this);
        int paddingRight = a ? (i3 - i) - getPaddingRight() : getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i4 - i2) - getPaddingTop()) - getPaddingBottom();
        if (!(this.g == null || this.g.getVisibility() == 8)) {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.g.getLayoutParams();
            int i6 = a ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
            i5 = a ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
            paddingRight = a(paddingRight, i6, a);
            paddingRight = a(paddingRight + a(this.g, paddingRight, paddingTop, paddingTop2, a), i5, a);
        }
        if (!(this.l == null || this.k != null || this.l.getVisibility() == 8)) {
            paddingRight += a(this.l, paddingRight, paddingTop, paddingTop2, a);
        }
        if (this.k != null) {
            a(this.k, paddingRight, paddingTop, paddingTop2, a);
        }
        i5 = a ? getPaddingLeft() : (i3 - i) - getPaddingRight();
        if (this.c != null) {
            a(this.c, i5, paddingTop, paddingTop2, !a);
        }
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (VERSION.SDK_INT < 14) {
            return;
        }
        if (accessibilityEvent.getEventType() == 32) {
            accessibilityEvent.setSource(this);
            accessibilityEvent.setClassName(getClass().getName());
            accessibilityEvent.setPackageName(getContext().getPackageName());
            accessibilityEvent.setContentDescription(this.i);
            return;
        }
        super.onInitializeAccessibilityEvent(accessibilityEvent);
    }

    public void setTitleOptional(boolean z) {
        if (z != this.h) {
            requestLayout();
        }
        this.h = z;
    }
}
