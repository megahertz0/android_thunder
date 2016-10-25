package com.inmobi.ads;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

public class NativeStrandContainerLayout extends ViewGroup {
    private static final String a;

    public static class a extends LayoutParams {
        public int a;
        public int b;

        public a(int i, int i2) {
            super(i, i2);
        }

        public void a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        public a(LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    static {
        a = NativeStrandContainerLayout.class.getSimpleName();
    }

    public NativeStrandContainerLayout(Context context) {
        super(context);
    }

    protected void onMeasure(int i, int i2) {
        measureChildren(i, i2);
        int childCount = getChildCount();
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i3 < childCount) {
            int measuredHeight;
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                a aVar = (a) childAt.getLayoutParams();
                int measuredWidth = aVar.a + childAt.getMeasuredWidth();
                measuredHeight = aVar.b + childAt.getMeasuredHeight();
                i5 = Math.max(i5, measuredWidth);
                measuredHeight = Math.max(i4, measuredHeight);
                i4 = i5;
            } else {
                measuredHeight = i4;
                i4 = i5;
            }
            i3++;
            i5 = i4;
            i4 = measuredHeight;
        }
        setMeasuredDimension(resolveSize(Math.max(i5, getSuggestedMinimumWidth()), i), resolveSize(Math.max(i4, getSuggestedMinimumHeight()), i2));
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new a(-2, -2);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                a aVar = (a) childAt.getLayoutParams();
                childAt.layout(aVar.a, aVar.b, aVar.a + childAt.getMeasuredWidth(), aVar.b + childAt.getMeasuredHeight());
            }
        }
    }

    protected boolean checkLayoutParams(LayoutParams layoutParams) {
        return layoutParams instanceof a;
    }

    protected LayoutParams generateLayoutParams(LayoutParams layoutParams) {
        return new a(layoutParams);
    }
}
