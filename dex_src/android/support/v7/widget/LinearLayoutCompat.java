package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.AutoScrollHelper;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public class LinearLayoutCompat extends ViewGroup {
    private boolean a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private float g;
    private boolean h;
    private int[] i;
    private int[] j;
    private Drawable k;
    private int l;
    private int m;
    private int n;
    private int o;

    public static class LayoutParams extends MarginLayoutParams {
        public float g;
        public int h;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.h = -1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.LinearLayoutCompat_Layout);
            this.g = obtainStyledAttributes.getFloat(R.styleable.LinearLayoutCompat_Layout_android_layout_weight, AutoScrollHelper.RELATIVE_UNSPECIFIED);
            this.h = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.h = -1;
            this.g = 0.0f;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.h = -1;
        }
    }

    protected /* synthetic */ android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return c();
    }

    public /* synthetic */ android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return a(attributeSet);
    }

    protected /* synthetic */ android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return b(layoutParams);
    }

    public LinearLayoutCompat(Context context) {
        this(context, null);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = true;
        this.b = -1;
        this.c = 0;
        this.e = 8388659;
        cm a = cm.a(context, attributeSet, R.styleable.LinearLayoutCompat, i);
        int a2 = a.a(R.styleable.LinearLayoutCompat_android_orientation, -1);
        if (a2 >= 0) {
            setOrientation(a2);
        }
        a2 = a.a(R.styleable.LinearLayoutCompat_android_gravity, -1);
        if (a2 >= 0) {
            setGravity(a2);
        }
        boolean a3 = a.a(R.styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (!a3) {
            setBaselineAligned(a3);
        }
        this.g = a.a.getFloat(R.styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.b = a.a(R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.h = a.a(R.styleable.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(a.a(R.styleable.LinearLayoutCompat_divider));
        this.n = a.a(R.styleable.LinearLayoutCompat_showDividers, 0);
        this.o = a.c(R.styleable.LinearLayoutCompat_dividerPadding, 0);
        a.a.recycle();
    }

    public void setShowDividers(int i) {
        if (i != this.n) {
            requestLayout();
        }
        this.n = i;
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public int getShowDividers() {
        return this.n;
    }

    public Drawable getDividerDrawable() {
        return this.k;
    }

    public void setDividerDrawable(Drawable drawable) {
        boolean z = false;
        if (drawable != this.k) {
            this.k = drawable;
            if (drawable != null) {
                this.l = drawable.getIntrinsicWidth();
                this.m = drawable.getIntrinsicHeight();
            } else {
                this.l = 0;
                this.m = 0;
            }
            if (drawable == null) {
                z = true;
            }
            setWillNotDraw(z);
            requestLayout();
        }
    }

    public void setDividerPadding(int i) {
        this.o = i;
    }

    public int getDividerPadding() {
        return this.o;
    }

    public int getDividerWidth() {
        return this.l;
    }

    protected void onDraw(Canvas canvas) {
        if (this.k != null) {
            int virtualChildCount;
            int i;
            View childAt;
            int height;
            LayoutParams layoutParams;
            if (this.d == 1) {
                virtualChildCount = getVirtualChildCount();
                i = 0;
                while (i < virtualChildCount) {
                    View childAt2 = getChildAt(i);
                    if (childAt2 != null && childAt2.getVisibility() != 8 && a(i)) {
                        a(canvas, (childAt2.getTop() - ((LayoutParams) childAt2.getLayoutParams()).topMargin) - this.m);
                    }
                    i++;
                }
                if (a(virtualChildCount)) {
                    childAt = getChildAt(virtualChildCount - 1);
                    if (childAt == null) {
                        height = (getHeight() - getPaddingBottom()) - this.m;
                    } else {
                        layoutParams = (LayoutParams) childAt.getLayoutParams();
                        height = layoutParams.bottomMargin + childAt.getBottom();
                    }
                    a(canvas, height);
                    return;
                }
                return;
            }
            virtualChildCount = getVirtualChildCount();
            boolean a = cw.a(this);
            i = 0;
            while (i < virtualChildCount) {
                View childAt3 = getChildAt(i);
                if (childAt3 != null && childAt3.getVisibility() != 8 && a(i)) {
                    layoutParams = (LayoutParams) childAt3.getLayoutParams();
                    if (a) {
                        height = layoutParams.rightMargin + childAt3.getRight();
                    } else {
                        height = (childAt3.getLeft() - layoutParams.leftMargin) - this.l;
                    }
                    b(canvas, height);
                }
                i++;
            }
            if (a(virtualChildCount)) {
                childAt = getChildAt(virtualChildCount - 1);
                if (childAt != null) {
                    layoutParams = (LayoutParams) childAt.getLayoutParams();
                    if (a) {
                        height = (childAt.getLeft() - layoutParams.leftMargin) - this.l;
                    } else {
                        height = layoutParams.rightMargin + childAt.getRight();
                    }
                } else if (a) {
                    height = getPaddingLeft();
                } else {
                    height = (getWidth() - getPaddingRight()) - this.l;
                }
                b(canvas, height);
            }
        }
    }

    private void a(Canvas canvas, int i) {
        this.k.setBounds(getPaddingLeft() + this.o, i, (getWidth() - getPaddingRight()) - this.o, this.m + i);
        this.k.draw(canvas);
    }

    private void b(Canvas canvas, int i) {
        this.k.setBounds(i, getPaddingTop() + this.o, this.l + i, (getHeight() - getPaddingBottom()) - this.o);
        this.k.draw(canvas);
    }

    public void setBaselineAligned(boolean z) {
        this.a = z;
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.h = z;
    }

    public int getBaseline() {
        if (this.b < 0) {
            return super.getBaseline();
        }
        if (getChildCount() <= this.b) {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
        View childAt = getChildAt(this.b);
        int baseline = childAt.getBaseline();
        if (baseline != -1) {
            int i;
            int i2 = this.c;
            if (this.d == 1) {
                i = this.e & 112;
                if (i != 48) {
                    switch (i) {
                        case com.xunlei.tdlive.R.styleable.Toolbar_titleMarginBottom:
                            i = i2 + (((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.f) / 2);
                            return (((LayoutParams) childAt.getLayoutParams()).topMargin + i) + baseline;
                        case com.xunlei.tdlive.R.styleable.AppCompatTheme_panelMenuListTheme:
                            i = ((getBottom() - getTop()) - getPaddingBottom()) - this.f;
                            return (((LayoutParams) childAt.getLayoutParams()).topMargin + i) + baseline;
                    }
                }
            }
            i = i2;
            return (((LayoutParams) childAt.getLayoutParams()).topMargin + i) + baseline;
        } else if (this.b == 0) {
            return -1;
        } else {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
        }
    }

    public int getBaselineAlignedChildIndex() {
        return this.b;
    }

    public void setBaselineAlignedChildIndex(int i) {
        if (i < 0 || i >= getChildCount()) {
            throw new IllegalArgumentException(new StringBuilder("base aligned child index out of range (0, ").append(getChildCount()).append(SocializeConstants.OP_CLOSE_PAREN).toString());
        }
        this.b = i;
    }

    int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.g;
    }

    public void setWeightSum(float f) {
        this.g = Math.max(AutoScrollHelper.RELATIVE_UNSPECIFIED, f);
    }

    protected void onMeasure(int i, int i2) {
        if (this.d == 1) {
            int i3;
            int i4;
            int i5;
            int i6;
            int measuredWidth;
            int max;
            int i7;
            int i8;
            int i9;
            int i10;
            int i11;
            LayoutParams layoutParams;
            this.f = 0;
            Object obj = null;
            Object obj2 = null;
            Object obj3 = null;
            int i12 = 0;
            Object obj4 = 1;
            float f = AutoScrollHelper.RELATIVE_UNSPECIFIED;
            int virtualChildCount = getVirtualChildCount();
            int mode = MeasureSpec.getMode(i);
            int mode2 = MeasureSpec.getMode(i2);
            Object obj5 = null;
            Object obj6 = null;
            int i13 = this.b;
            boolean z = this.h;
            int i14 = ExploreByTouchHelper.INVALID_ID;
            int i15 = 0;
            while (i15 < virtualChildCount) {
                View childAt = getChildAt(i15);
                if (childAt == null) {
                    this.f += 0;
                    i3 = i15;
                } else {
                    Object obj7;
                    int a;
                    Object obj8;
                    float f2;
                    Object obj9;
                    if (childAt.getVisibility() != 8) {
                        Object obj10;
                        Object obj11;
                        if (a(i15)) {
                            this.f += this.m;
                        }
                        LayoutParams layoutParams2 = (LayoutParams) childAt.getLayoutParams();
                        float f3 = f + layoutParams2.g;
                        if (mode2 == 1073741824 && layoutParams2.height == 0 && layoutParams2.g > 0.0f) {
                            i3 = this.f;
                            this.f = Math.max(i3, (layoutParams2.topMargin + i3) + layoutParams2.bottomMargin);
                            i4 = i14;
                            i14 = 1;
                        } else {
                            obj10 = ExploreByTouchHelper.INVALID_ID;
                            if (layoutParams2.height == 0 && layoutParams2.g > 0.0f) {
                                obj10 = null;
                                layoutParams2.height = -2;
                            }
                            int i16 = i3;
                            a(childAt, i, 0, i2, f3 == 0.0f ? this.f : 0);
                            if (i16 != Integer.MIN_VALUE) {
                                layoutParams2.height = i16;
                            }
                            i3 = childAt.getMeasuredHeight();
                            i5 = this.f;
                            this.f = Math.max(i5, (((i5 + i3) + layoutParams2.topMargin) + layoutParams2.bottomMargin) + 0);
                            if (z) {
                                i4 = Math.max(i3, i14);
                                obj11 = obj6;
                            } else {
                                i4 = i14;
                                obj11 = obj6;
                            }
                        }
                        if (i13 >= 0 && i13 == i15 + 1) {
                            this.c = this.f;
                        }
                        if (i15 >= i13 || layoutParams2.g <= 0.0f) {
                            obj10 = null;
                            if (mode == 1073741824 || layoutParams2.width != -1) {
                                obj7 = obj5;
                            } else {
                                obj7 = 1;
                                obj10 = 1;
                            }
                            i6 = layoutParams2.leftMargin + layoutParams2.rightMargin;
                            measuredWidth = childAt.getMeasuredWidth() + i6;
                            max = Math.max(i7, measuredWidth);
                            a = cw.a(i8, ViewCompat.getMeasuredState(childAt));
                            obj8 = (obj4 == null || layoutParams2.width != -1) ? null : 1;
                            if (layoutParams2.g > 0.0f) {
                                if (obj10 != null) {
                                    i3 = i6;
                                } else {
                                    i3 = measuredWidth;
                                }
                                f2 = f3;
                                obj9 = obj8;
                                i9 = i10;
                                obj8 = obj11;
                                i14 = max;
                                int i17 = i4;
                                i4 = Math.max(i12, i3);
                                i3 = i17;
                            } else {
                                if (obj10 == null) {
                                    i6 = measuredWidth;
                                }
                                i3 = Math.max(i10, i6);
                                f2 = f3;
                                obj9 = obj8;
                                i9 = i3;
                                obj8 = obj11;
                                i3 = i4;
                                i4 = i12;
                                i14 = max;
                            }
                        } else {
                            throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
                        }
                    }
                    i3 = i14;
                    obj8 = obj6;
                    f2 = f;
                    obj9 = obj4;
                    i4 = i12;
                    i9 = i10;
                    obj7 = obj5;
                    i14 = i7;
                    a = i8;
                    obj4 = obj9;
                    i12 = i4;
                    i10 = i9;
                    i8 = a;
                    i7 = i14;
                    i14 = i3;
                    obj5 = obj7;
                    i3 = i15 + 0;
                    f = f2;
                    obj6 = obj8;
                }
                i15 = i3 + 1;
            }
            if (this.f > 0 && a(virtualChildCount)) {
                this.f += this.m;
            }
            if (z) {
                if (mode2 == Integer.MIN_VALUE || mode2 == 0) {
                    this.f = 0;
                    i11 = 0;
                    while (i11 < virtualChildCount) {
                        View childAt2 = getChildAt(i11);
                        if (childAt2 == null) {
                            this.f += 0;
                            i3 = i11;
                        } else if (childAt2.getVisibility() == 8) {
                            i3 = i11 + 0;
                        } else {
                            layoutParams = (LayoutParams) childAt2.getLayoutParams();
                            i6 = this.f;
                            this.f = Math.max(i6, (layoutParams.bottomMargin + ((i6 + i14) + layoutParams.topMargin)) + 0);
                            i3 = i11;
                        }
                        i11 = i3 + 1;
                    }
                }
            }
            this.f += getPaddingTop() + getPaddingBottom();
            i15 = ViewCompat.resolveSizeAndState(Math.max(this.f, getSuggestedMinimumHeight()), i2, 0);
            i6 = (16777215 & i15) - this.f;
            int i18;
            if (obj6 != null || (i6 != 0 && f > 0.0f)) {
                if (this.g > 0.0f) {
                    f = this.g;
                }
                this.f = 0;
                i12 = 0;
                obj6 = obj4;
                i18 = i10;
                i14 = i8;
                int i19 = i7;
                while (i12 < virtualChildCount) {
                    float f4;
                    Object obj12;
                    View childAt3 = getChildAt(i12);
                    if (childAt3.getVisibility() != 8) {
                        layoutParams = (LayoutParams) childAt3.getLayoutParams();
                        float f5 = layoutParams.g;
                        if (f5 > 0.0f) {
                            View view;
                            i11 = (int) ((((float) i6) * f5) / f);
                            f5 = f - f5;
                            i9 = i6 - i11;
                            i5 = getChildMeasureSpec(i, ((getPaddingLeft() + getPaddingRight()) + layoutParams.leftMargin) + layoutParams.rightMargin, layoutParams.width);
                            if (layoutParams.height != 0 || mode2 != 1073741824) {
                                i11 += childAt3.getMeasuredHeight();
                                if (i11 < 0) {
                                    i11 = 0;
                                }
                                view = childAt3;
                            } else if (i11 > 0) {
                                view = childAt3;
                            } else {
                                i11 = 0;
                                view = childAt3;
                            }
                            view.measure(i5, MeasureSpec.makeMeasureSpec(i11, 1073741824));
                            i5 = i9;
                            i6 = cw.a(i14, ViewCompat.getMeasuredState(childAt3) & -256);
                            f4 = f5;
                        } else {
                            f4 = f;
                            i5 = i6;
                            i6 = i14;
                        }
                        i4 = layoutParams.leftMargin + layoutParams.rightMargin;
                        i9 = childAt3.getMeasuredWidth() + i4;
                        i14 = Math.max(i19, i9);
                        obj4 = (mode == 1073741824 || layoutParams.width != -1) ? null : 1;
                        if (obj4 == null) {
                            i4 = i9;
                        }
                        i9 = Math.max(i18, i4);
                        obj12 = (obj6 == null || layoutParams.width != -1) ? null : 1;
                        max = this.f;
                        this.f = Math.max(max, (layoutParams.bottomMargin + ((childAt3.getMeasuredHeight() + max) + layoutParams.topMargin)) + 0);
                        i3 = i9;
                        measuredWidth = i14;
                    } else {
                        f4 = f;
                        obj12 = obj6;
                        i3 = i18;
                        measuredWidth = i19;
                        i5 = i6;
                        i6 = i14;
                    }
                    i12++;
                    obj6 = obj12;
                    i18 = i3;
                    i14 = i6;
                    i19 = measuredWidth;
                    i6 = i5;
                    f = f4;
                }
                this.f += getPaddingTop() + getPaddingBottom();
                i3 = i18;
                i8 = i14;
                i11 = i19;
                obj4 = obj6;
            } else {
                i18 = Math.max(i10, i12);
                if (z && mode2 != 1073741824) {
                    for (i11 = 0; i11 < virtualChildCount; i11++) {
                        View childAt4 = getChildAt(i11);
                        if (childAt4 != null && childAt4.getVisibility() != 8 && ((LayoutParams) childAt4.getLayoutParams()).g > 0.0f) {
                            childAt4.measure(MeasureSpec.makeMeasureSpec(childAt4.getMeasuredWidth(), 1073741824), MeasureSpec.makeMeasureSpec(i14, 1073741824));
                        }
                    }
                }
                i3 = i18;
                i11 = i7;
            }
            if (obj4 != null || mode == 1073741824) {
                i3 = i11;
            }
            setMeasuredDimension(ViewCompat.resolveSizeAndState(Math.max(i3 + (getPaddingLeft() + getPaddingRight()), getSuggestedMinimumWidth()), i, i8), i15);
            if (obj5 != null) {
                a(virtualChildCount, i2);
                return;
            }
            return;
        }
        b(i, i2);
    }

    private boolean a(int i) {
        if (i == 0) {
            return (this.n & 1) != 0;
        } else {
            if (i == getChildCount()) {
                return (this.n & 4) != 0;
            } else {
                if ((this.n & 2) == 0) {
                    return false;
                }
                for (int i2 = i - 1; i2 >= 0; i2--) {
                    if (getChildAt(i2).getVisibility() != 8) {
                        return true;
                    }
                }
                return false;
            }
        }
    }

    private void a(int i, int i2) {
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.width == -1) {
                    int i4 = layoutParams.height;
                    layoutParams.height = childAt.getMeasuredHeight();
                    measureChildWithMargins(childAt, makeMeasureSpec, 0, i2, 0);
                    layoutParams.height = i4;
                }
            }
        }
    }

    private void b(int i, int i2) {
        int i3;
        int i4;
        Object obj;
        int measuredHeight;
        int i5;
        Object obj2;
        int i6;
        int i7;
        LayoutParams layoutParams;
        this.f = 0;
        Object obj3 = null;
        int i8 = 0;
        Object obj4 = null;
        int i9 = 0;
        Object obj5 = 1;
        float f = AutoScrollHelper.RELATIVE_UNSPECIFIED;
        int virtualChildCount = getVirtualChildCount();
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        Object obj6 = null;
        Object obj7 = null;
        if (this.i == null || this.j == null) {
            this.i = new int[4];
            this.j = new int[4];
        }
        int[] iArr = this.i;
        int[] iArr2 = this.j;
        iArr[3] = -1;
        iArr[2] = -1;
        iArr[1] = -1;
        iArr[0] = -1;
        iArr2[3] = -1;
        iArr2[2] = -1;
        iArr2[1] = -1;
        iArr2[0] = -1;
        boolean z = this.a;
        boolean z2 = this.h;
        if (mode == 1073741824) {
            int i10 = 1;
        } else {
            Object obj8 = null;
        }
        int i11 = ExploreByTouchHelper.INVALID_ID;
        int i12 = 0;
        while (i12 < virtualChildCount) {
            LayoutParams layoutParams2;
            Object obj9;
            int i13;
            int a;
            int baseline;
            View childAt = getChildAt(i12);
            if (childAt == null) {
                this.f += 0;
                i3 = i12;
            } else {
                float f2;
                Object obj10;
                if (childAt.getVisibility() != 8) {
                    Object obj11;
                    if (a(i12)) {
                        this.f += this.l;
                    }
                    layoutParams2 = (LayoutParams) childAt.getLayoutParams();
                    float f3 = f + layoutParams2.g;
                    if (mode == 1073741824 && layoutParams2.width == 0 && layoutParams2.g > 0.0f) {
                        if (obj8 != null) {
                            this.f += layoutParams2.leftMargin + layoutParams2.rightMargin;
                        } else {
                            i3 = this.f;
                            this.f = Math.max(i3, (layoutParams2.leftMargin + i3) + layoutParams2.rightMargin);
                        }
                        if (z) {
                            i3 = MeasureSpec.makeMeasureSpec(0, 0);
                            childAt.measure(i3, i3);
                            i4 = i11;
                            obj = obj7;
                        } else {
                            i4 = i11;
                            i11 = 1;
                        }
                    } else {
                        obj11 = ExploreByTouchHelper.INVALID_ID;
                        if (layoutParams2.width == 0 && layoutParams2.g > 0.0f) {
                            obj11 = null;
                            layoutParams2.width = -2;
                        }
                        int i14 = i3;
                        a(childAt, i, f3 == 0.0f ? this.f : 0, i2, 0);
                        if (i14 != Integer.MIN_VALUE) {
                            layoutParams2.width = i14;
                        }
                        i3 = childAt.getMeasuredWidth();
                        if (obj8 != null) {
                            this.f += ((layoutParams2.leftMargin + i3) + layoutParams2.rightMargin) + 0;
                        } else {
                            int i15 = this.f;
                            this.f = Math.max(i15, (((i15 + i3) + layoutParams2.leftMargin) + layoutParams2.rightMargin) + 0);
                        }
                        if (z2) {
                            i4 = Math.max(i3, i11);
                            obj = obj7;
                        } else {
                            i4 = i11;
                            obj = obj7;
                        }
                    }
                    obj11 = null;
                    if (mode2 == 1073741824 || layoutParams2.height != -1) {
                        obj9 = obj6;
                    } else {
                        obj9 = 1;
                        obj11 = 1;
                    }
                    i13 = layoutParams2.topMargin + layoutParams2.bottomMargin;
                    measuredHeight = childAt.getMeasuredHeight() + i13;
                    a = cw.a(i8, ViewCompat.getMeasuredState(childAt));
                    if (z) {
                        baseline = childAt.getBaseline();
                        if (baseline != -1) {
                            int i16 = ((((layoutParams2.h < 0 ? this.e : layoutParams2.h) & 112) >> 4) & -2) >> 1;
                            iArr[i16] = Math.max(iArr[i16], baseline);
                            iArr2[i16] = Math.max(iArr2[i16], measuredHeight - baseline);
                        }
                    }
                    baseline = Math.max(i5, measuredHeight);
                    obj2 = (obj5 == null || layoutParams2.height != -1) ? null : 1;
                    if (layoutParams2.g > 0.0f) {
                        if (obj11 != null) {
                            i3 = i13;
                        } else {
                            i3 = measuredHeight;
                        }
                        f2 = f3;
                        obj10 = obj2;
                        i6 = i7;
                        obj2 = obj;
                        i11 = baseline;
                        int i17 = i4;
                        i4 = Math.max(i9, i3);
                        i3 = i17;
                    } else {
                        if (obj11 == null) {
                            i13 = measuredHeight;
                        }
                        i3 = Math.max(i7, i13);
                        f2 = f3;
                        obj10 = obj2;
                        i6 = i3;
                        obj2 = obj;
                        i3 = i4;
                        i4 = i9;
                        i11 = baseline;
                    }
                } else {
                    i3 = i11;
                    obj2 = obj7;
                    f2 = f;
                    obj10 = obj5;
                    i4 = i9;
                    i6 = i7;
                    obj9 = obj6;
                    i11 = i5;
                    a = i8;
                }
                obj5 = obj10;
                i9 = i4;
                i7 = i6;
                i8 = a;
                i5 = i11;
                i11 = i3;
                obj6 = obj9;
                i3 = i12 + 0;
                f = f2;
                obj7 = obj2;
            }
            i12 = i3 + 1;
        }
        if (this.f > 0 && a(virtualChildCount)) {
            this.f += this.l;
        }
        if (iArr[1] == -1 && iArr[0] == -1 && iArr[2] == -1 && iArr[3] == -1) {
            i13 = i5;
        } else {
            i13 = Math.max(i5, Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))) + Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))));
        }
        if (z2) {
            if (mode == Integer.MIN_VALUE || mode == 0) {
                this.f = 0;
                i16 = 0;
                while (i16 < virtualChildCount) {
                    View childAt2 = getChildAt(i16);
                    if (childAt2 == null) {
                        this.f += 0;
                        i3 = i16;
                    } else if (childAt2.getVisibility() == 8) {
                        i3 = i16 + 0;
                    } else {
                        layoutParams = (LayoutParams) childAt2.getLayoutParams();
                        if (obj8 != null) {
                            this.f = ((layoutParams.rightMargin + (layoutParams.leftMargin + i11)) + 0) + this.f;
                            i3 = i16;
                        } else {
                            measuredHeight = this.f;
                            this.f = Math.max(measuredHeight, (layoutParams.rightMargin + ((measuredHeight + i11) + layoutParams.leftMargin)) + 0);
                            i3 = i16;
                        }
                    }
                    i16 = i3 + 1;
                }
            }
        }
        this.f += getPaddingLeft() + getPaddingRight();
        i5 = ViewCompat.resolveSizeAndState(Math.max(this.f, getSuggestedMinimumWidth()), i, 0);
        measuredHeight = (16777215 & i5) - this.f;
        View view;
        if (obj7 != null || (measuredHeight != 0 && f > 0.0f)) {
            if (this.g > 0.0f) {
                f = this.g;
            }
            iArr[3] = -1;
            iArr[2] = -1;
            iArr[1] = -1;
            iArr[0] = -1;
            iArr2[3] = -1;
            iArr2[2] = -1;
            iArr2[1] = -1;
            iArr2[0] = -1;
            this.f = 0;
            i9 = 0;
            obj = obj5;
            baseline = i7;
            int i18 = -1;
            i4 = i8;
            while (i9 < virtualChildCount) {
                float f4;
                View childAt3 = getChildAt(i9);
                if (childAt3 == null || childAt3.getVisibility() == 8) {
                    f4 = f;
                    i16 = measuredHeight;
                    i13 = baseline;
                    obj9 = obj;
                    measuredHeight = i4;
                    i4 = i18;
                } else {
                    int childMeasureSpec;
                    float f5;
                    layoutParams = (LayoutParams) childAt3.getLayoutParams();
                    float f6 = layoutParams.g;
                    if (f6 > 0.0f) {
                        i16 = (int) ((((float) measuredHeight) * f6) / f);
                        f6 = f - f6;
                        measuredHeight -= i16;
                        childMeasureSpec = getChildMeasureSpec(i2, ((getPaddingTop() + getPaddingBottom()) + layoutParams.topMargin) + layoutParams.bottomMargin, layoutParams.height);
                        if (layoutParams.width != 0 || mode != 1073741824) {
                            i16 += childAt3.getMeasuredWidth();
                            if (i16 < 0) {
                                i16 = 0;
                            }
                            view = childAt3;
                        } else if (i16 > 0) {
                            view = childAt3;
                        } else {
                            i16 = 0;
                            view = childAt3;
                        }
                        view.measure(MeasureSpec.makeMeasureSpec(i16, 1073741824), childMeasureSpec);
                        childMeasureSpec = cw.a(i4, ViewCompat.getMeasuredState(childAt3) & -16777216);
                        f5 = f6;
                        i6 = measuredHeight;
                    } else {
                        i6 = measuredHeight;
                        childMeasureSpec = i4;
                        f5 = f;
                    }
                    if (obj8 != null) {
                        this.f += ((childAt3.getMeasuredWidth() + layoutParams.leftMargin) + layoutParams.rightMargin) + 0;
                    } else {
                        i16 = this.f;
                        this.f = Math.max(i16, (((childAt3.getMeasuredWidth() + i16) + layoutParams.leftMargin) + layoutParams.rightMargin) + 0);
                    }
                    obj2 = (mode2 == 1073741824 || layoutParams.height != -1) ? null : 1;
                    i15 = layoutParams.topMargin + layoutParams.bottomMargin;
                    measuredHeight = childAt3.getMeasuredHeight() + i15;
                    i18 = Math.max(i18, measuredHeight);
                    if (obj2 != null) {
                        i16 = i15;
                    } else {
                        i16 = measuredHeight;
                    }
                    i15 = Math.max(baseline, i16);
                    obj2 = (obj == null || layoutParams.height != -1) ? null : 1;
                    if (z) {
                        i13 = childAt3.getBaseline();
                        if (i13 != -1) {
                            i3 = ((((layoutParams.h < 0 ? this.e : layoutParams.h) & 112) >> 4) & -2) >> 1;
                            iArr[i3] = Math.max(iArr[i3], i13);
                            iArr2[i3] = Math.max(iArr2[i3], measuredHeight - i13);
                        }
                    }
                    f4 = f5;
                    i13 = i15;
                    measuredHeight = childMeasureSpec;
                    obj9 = obj2;
                    i4 = i18;
                    i16 = i6;
                }
                i9++;
                obj = obj9;
                baseline = i13;
                i18 = i4;
                i4 = measuredHeight;
                f = f4;
                measuredHeight = i16;
            }
            this.f += getPaddingLeft() + getPaddingRight();
            if (!(iArr[1] == -1 && iArr[0] == -1 && iArr[2] == -1 && iArr[3] == -1)) {
                i18 = Math.max(i18, Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))) + Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))));
            }
            i3 = baseline;
            i8 = i4;
            i16 = i18;
            obj5 = obj;
        } else {
            baseline = Math.max(i7, i9);
            if (z2 && mode != 1073741824) {
                for (i16 = 0; i16 < virtualChildCount; i16++) {
                    view = getChildAt(i16);
                    if (view != null && view.getVisibility() != 8 && ((LayoutParams) view.getLayoutParams()).g > 0.0f) {
                        view.measure(MeasureSpec.makeMeasureSpec(i11, 1073741824), MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), 1073741824));
                    }
                }
            }
            i3 = baseline;
            i16 = i13;
        }
        if (obj5 != null || mode2 == 1073741824) {
            i3 = i16;
        }
        setMeasuredDimension((-16777216 & i8) | i5, ViewCompat.resolveSizeAndState(Math.max(i3 + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight()), i2, i8 << 16));
        if (obj6 != null) {
            measuredHeight = MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
            for (i10 = 0; i10 < virtualChildCount; i10++) {
                childAt = getChildAt(i10);
                if (childAt.getVisibility() != 8) {
                    layoutParams2 = (LayoutParams) childAt.getLayoutParams();
                    if (layoutParams2.height == -1) {
                        a = layoutParams2.width;
                        layoutParams2.width = childAt.getMeasuredWidth();
                        measureChildWithMargins(childAt, i, 0, measuredHeight, 0);
                        layoutParams2.width = a;
                    }
                }
            }
        }
    }

    private static int getChildrenSkipCount$5359dca7() {
        return 0;
    }

    private void a(View view, int i, int i2, int i3, int i4) {
        measureChildWithMargins(view, i, i2, i3, i4);
    }

    private static int getLocationOffset$3c7ec8d0() {
        return 0;
    }

    private static int getNextLocationOffset$3c7ec8d0() {
        return 0;
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int paddingLeft;
        int i5;
        int paddingRight;
        int paddingRight2;
        int virtualChildCount;
        int i6;
        int i7;
        int i8;
        int measuredWidth;
        LayoutParams layoutParams;
        int i9;
        if (this.d == 1) {
            paddingLeft = getPaddingLeft();
            i5 = i3 - i;
            paddingRight = i5 - getPaddingRight();
            paddingRight2 = (i5 - paddingLeft) - getPaddingRight();
            virtualChildCount = getVirtualChildCount();
            i6 = 8388615 & this.e;
            switch (this.e & 112) {
                case com.xunlei.tdlive.R.styleable.Toolbar_titleMarginBottom:
                    i5 = getPaddingTop() + (((i4 - i2) - this.f) / 2);
                    break;
                case com.xunlei.tdlive.R.styleable.AppCompatTheme_panelMenuListTheme:
                    i5 = ((getPaddingTop() + i4) - i2) - this.f;
                    break;
                default:
                    i5 = getPaddingTop();
                    break;
            }
            i7 = 0;
            i8 = i5;
            while (i7 < virtualChildCount) {
                View childAt = getChildAt(i7);
                if (childAt == null) {
                    i8 += 0;
                    i5 = i7;
                } else if (childAt.getVisibility() != 8) {
                    measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    layoutParams = (LayoutParams) childAt.getLayoutParams();
                    i9 = layoutParams.h;
                    if (i9 < 0) {
                        i9 = i6;
                    }
                    switch (GravityCompat.getAbsoluteGravity(i9, ViewCompat.getLayoutDirection(this)) & 7) {
                        case SpdyAgent.ACCS_ONLINE_SERVER:
                            i9 = ((((paddingRight2 - measuredWidth) / 2) + paddingLeft) + layoutParams.leftMargin) - layoutParams.rightMargin;
                            break;
                        case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                            i9 = (paddingRight - measuredWidth) - layoutParams.rightMargin;
                            break;
                        default:
                            i9 = layoutParams.leftMargin + paddingLeft;
                            break;
                    }
                    if (a(i7)) {
                        i8 += this.m;
                    }
                    i8 += layoutParams.topMargin;
                    b(childAt, i9, i8 + 0, measuredWidth, measuredHeight);
                    i8 += (layoutParams.bottomMargin + measuredHeight) + 0;
                    i5 = i7 + 0;
                } else {
                    i5 = i7;
                }
                i7 = i5 + 1;
            }
            return;
        }
        boolean a = cw.a(this);
        paddingLeft = getPaddingTop();
        i5 = i4 - i2;
        int paddingBottom = i5 - getPaddingBottom();
        measuredWidth = (i5 - paddingLeft) - getPaddingBottom();
        measuredHeight = getVirtualChildCount();
        i5 = this.e & 8388615;
        virtualChildCount = this.e & 112;
        boolean z2 = this.a;
        int[] iArr = this.i;
        int[] iArr2 = this.j;
        switch (GravityCompat.getAbsoluteGravity(i5, ViewCompat.getLayoutDirection(this))) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                paddingRight = getPaddingLeft() + (((i3 - i) - this.f) / 2);
                break;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                paddingRight = ((getPaddingLeft() + i3) - i) - this.f;
                break;
            default:
                paddingRight = getPaddingLeft();
                break;
        }
        if (a) {
            i6 = measuredHeight - 1;
            i9 = -1;
        } else {
            i6 = 0;
            i9 = 1;
        }
        paddingRight2 = 0;
        while (paddingRight2 < measuredHeight) {
            int i10 = i6 + (i9 * paddingRight2);
            View childAt2 = getChildAt(i10);
            if (childAt2 == null) {
                paddingRight += 0;
                i5 = paddingRight2;
            } else if (childAt2.getVisibility() != 8) {
                int measuredWidth2 = childAt2.getMeasuredWidth();
                int measuredHeight2 = childAt2.getMeasuredHeight();
                i8 = -1;
                layoutParams = (LayoutParams) childAt2.getLayoutParams();
                if (z2 && layoutParams.height != -1) {
                    i8 = childAt2.getBaseline();
                }
                i7 = layoutParams.h;
                if (i7 < 0) {
                    i7 = virtualChildCount;
                }
                switch (i7 & 112) {
                    case com.xunlei.tdlive.R.styleable.Toolbar_titleMarginBottom:
                        i8 = ((((measuredWidth - measuredHeight2) / 2) + paddingLeft) + layoutParams.topMargin) - layoutParams.bottomMargin;
                        if (a(i10)) {
                            i7 = paddingRight;
                        } else {
                            i7 = this.l + paddingRight;
                        }
                        i7 += layoutParams.leftMargin;
                        b(childAt2, i7 + 0, i8, measuredWidth2, measuredHeight2);
                        paddingRight = i7 + ((layoutParams.rightMargin + measuredWidth2) + 0);
                        i5 = paddingRight2 + 0;
                        break;
                    case com.xunlei.tdlive.R.styleable.AppCompatTheme_homeAsUpIndicator:
                        i7 = layoutParams.topMargin + paddingLeft;
                        if (i8 != -1) {
                            i8 = (iArr[1] - i8) + i7;
                            if (a(i10)) {
                                i7 = this.l + paddingRight;
                            } else {
                                i7 = paddingRight;
                            }
                            i7 += layoutParams.leftMargin;
                            b(childAt2, i7 + 0, i8, measuredWidth2, measuredHeight2);
                            paddingRight = i7 + ((layoutParams.rightMargin + measuredWidth2) + 0);
                            i5 = paddingRight2 + 0;
                        }
                        break;
                    case com.xunlei.tdlive.R.styleable.AppCompatTheme_panelMenuListTheme:
                        i7 = (paddingBottom - measuredHeight2) - layoutParams.bottomMargin;
                        if (i8 != -1) {
                            i8 = i7 - (iArr2[2] - (childAt2.getMeasuredHeight() - i8));
                            if (a(i10)) {
                                i7 = paddingRight;
                            } else {
                                i7 = this.l + paddingRight;
                            }
                            i7 += layoutParams.leftMargin;
                            b(childAt2, i7 + 0, i8, measuredWidth2, measuredHeight2);
                            paddingRight = i7 + ((layoutParams.rightMargin + measuredWidth2) + 0);
                            i5 = paddingRight2 + 0;
                        }
                        break;
                    default:
                        i8 = paddingLeft;
                        if (a(i10)) {
                            i7 = this.l + paddingRight;
                        } else {
                            i7 = paddingRight;
                        }
                        i7 += layoutParams.leftMargin;
                        b(childAt2, i7 + 0, i8, measuredWidth2, measuredHeight2);
                        paddingRight = i7 + ((layoutParams.rightMargin + measuredWidth2) + 0);
                        i5 = paddingRight2 + 0;
                        break;
                }
                i8 = i7;
                if (a(i10)) {
                    i7 = this.l + paddingRight;
                } else {
                    i7 = paddingRight;
                }
                i7 += layoutParams.leftMargin;
                b(childAt2, i7 + 0, i8, measuredWidth2, measuredHeight2);
                paddingRight = i7 + ((layoutParams.rightMargin + measuredWidth2) + 0);
                i5 = paddingRight2 + 0;
            } else {
                i5 = paddingRight2;
            }
            paddingRight2 = i5 + 1;
        }
    }

    private static void b(View view, int i, int i2, int i3, int i4) {
        view.layout(i, i2, i + i3, i2 + i4);
    }

    public void setOrientation(int i) {
        if (this.d != i) {
            this.d = i;
            requestLayout();
        }
    }

    public int getOrientation() {
        return this.d;
    }

    public void setGravity(int i) {
        if (this.e != i) {
            int i2;
            if ((8388615 & i) == 0) {
                i2 = 8388611 | i;
            } else {
                i2 = i;
            }
            if ((i2 & 112) == 0) {
                i2 |= 48;
            }
            this.e = i2;
            requestLayout();
        }
    }

    public void setHorizontalGravity(int i) {
        int i2 = i & 8388615;
        if ((this.e & 8388615) != i2) {
            this.e = i2 | (this.e & -8388616);
            requestLayout();
        }
    }

    public void setVerticalGravity(int i) {
        int i2 = i & 112;
        if ((this.e & 112) != i2) {
            this.e = i2 | (this.e & -113);
            requestLayout();
        }
    }

    public LayoutParams a(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    protected LayoutParams c() {
        if (this.d == 0) {
            return new LayoutParams(-2, -2);
        }
        return this.d == 1 ? new LayoutParams(-1, -2) : null;
    }

    protected LayoutParams b(android.view.ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (VERSION.SDK_INT >= 14) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName(LinearLayoutCompat.class.getName());
        }
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (VERSION.SDK_INT >= 14) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName(LinearLayoutCompat.class.getName());
        }
    }
}
