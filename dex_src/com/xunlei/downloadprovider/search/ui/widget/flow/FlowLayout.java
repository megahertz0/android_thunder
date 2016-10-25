package com.xunlei.downloadprovider.search.ui.widget.flow;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import com.xunlei.downloadprovider.R;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.impl.SimpleLog;

public class FlowLayout extends ViewGroup {
    protected List<List<View>> a;
    protected List<Integer> b;
    private String c;
    private int d;

    public FlowLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new ArrayList();
        this.b = new ArrayList();
        this.d = -1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TagFlowLayout);
        this.c = obtainStyledAttributes.getString(SimpleLog.LOG_LEVEL_DEBUG);
        if (this.c == null) {
            this.c = getResources().getString(R.string.gravity_left);
        }
        obtainStyledAttributes.recycle();
    }

    public FlowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        int mode = MeasureSpec.getMode(i);
        int size2 = MeasureSpec.getSize(i2);
        int mode2 = MeasureSpec.getMode(i2);
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int childCount = getChildCount();
        int i7 = 1;
        int i8 = 0;
        while (i8 < childCount) {
            int measuredWidth;
            int measuredHeight;
            int i9;
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                measureChild(childAt, i, i2);
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childAt.getLayoutParams();
                measuredWidth = (childAt.getMeasuredWidth() + marginLayoutParams.leftMargin) + marginLayoutParams.rightMargin;
                measuredHeight = (childAt.getMeasuredHeight() + marginLayoutParams.topMargin) + marginLayoutParams.bottomMargin;
                if (i5 + measuredWidth > (size - getPaddingLeft()) - getPaddingRight()) {
                    i9 = i7 + 1;
                    if (this.d > 0 && i9 > this.d) {
                        break;
                    }
                    i6 = i4 + i6;
                    i7 = measuredHeight;
                    measuredHeight = measuredWidth;
                    measuredWidth = Math.max(i3, i5);
                } else {
                    measuredWidth += i5;
                    i9 = Math.max(i6, measuredHeight);
                    measuredHeight = measuredWidth;
                    i6 = i4;
                    measuredWidth = i3;
                    int i10 = i9;
                    i9 = i7;
                    i7 = i10;
                }
            } else {
                i9 = i7;
                measuredHeight = i5;
                measuredWidth = i3;
                i7 = i6;
                i6 = i4;
            }
            i8++;
            i4 = i6;
            i3 = measuredWidth;
            i5 = measuredHeight;
            i6 = i7;
            i7 = i9;
        }
        setMeasuredDimension(mode == 1073741824 ? size : (Math.max(i3, i5) + getPaddingLeft()) + getPaddingRight(), mode2 == 1073741824 ? size2 : (getPaddingTop() + (i4 + i6)) + getPaddingBottom());
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        this.a.clear();
        this.b.clear();
        int width = getWidth();
        int i6 = 0;
        int i7 = 0;
        Object arrayList = new ArrayList();
        int childCount = getChildCount();
        for (i5 = 0; i5 < childCount; i5++) {
            int measuredHeight;
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                int measuredWidth;
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childAt.getLayoutParams();
                measuredWidth = childAt.getMeasuredWidth();
                measuredHeight = childAt.getMeasuredHeight();
                if (((measuredWidth + i6) + marginLayoutParams.leftMargin) + marginLayoutParams.rightMargin > (width - getPaddingLeft()) - getPaddingRight()) {
                    this.b.add(Integer.valueOf(i7));
                    this.a.add(arrayList);
                    i6 = 0;
                    i7 = marginLayoutParams.bottomMargin + (marginLayoutParams.topMargin + measuredHeight);
                    arrayList = new ArrayList();
                }
                i6 += (measuredWidth + marginLayoutParams.leftMargin) + marginLayoutParams.rightMargin;
                i7 = Math.max(i7, marginLayoutParams.bottomMargin + (marginLayoutParams.topMargin + measuredHeight));
                arrayList.add(childAt);
            }
        }
        this.b.add(Integer.valueOf(i7));
        this.a.add(arrayList);
        getPaddingLeft();
        int paddingTop = getPaddingTop();
        int size = this.a.size();
        if (this.d > 0) {
            i6 = Math.min(this.d, size);
        } else {
            i6 = size;
        }
        childCount = 0;
        int i8 = paddingTop;
        while (childCount < i6) {
            List list = (List) this.a.get(childCount);
            measuredWidth = ((Integer) this.b.get(childCount)).intValue();
            i5 = a(list);
            width = 0;
            while (width < list.size()) {
                View view = (View) list.get(width);
                if (view.getVisibility() != 8) {
                    MarginLayoutParams marginLayoutParams2 = (MarginLayoutParams) view.getLayoutParams();
                    measuredHeight = marginLayoutParams2.leftMargin + i5;
                    int i9 = marginLayoutParams2.topMargin + i8;
                    view.layout(measuredHeight, i9, view.getMeasuredWidth() + measuredHeight, view.getMeasuredHeight() + i9);
                    size = ((view.getMeasuredWidth() + marginLayoutParams2.leftMargin) + marginLayoutParams2.rightMargin) + i5;
                } else {
                    size = i5;
                }
                width++;
                i5 = size;
            }
            childCount++;
            i8 += measuredWidth;
        }
    }

    private int a(List<View> list) {
        int paddingLeft = getPaddingLeft();
        int i = 0;
        int i2 = 0;
        while (i < list.size()) {
            int measuredWidth;
            View view = (View) list.get(i);
            if (view.getVisibility() != 8) {
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
                measuredWidth = i2 + ((view.getMeasuredWidth() + marginLayoutParams.leftMargin) + marginLayoutParams.rightMargin);
            } else {
                measuredWidth = i2;
            }
            i++;
            i2 = measuredWidth;
        }
        int paddingLeft2 = (getPaddingLeft() + getPaddingRight()) + i2;
        if (this.c.equals(getResources().getString(R.string.gravity_center))) {
            if (getMeasuredWidth() > paddingLeft2) {
                return ((getMeasuredWidth() - paddingLeft2) / 2) + paddingLeft;
            }
        } else if (this.c.equals(getResources().getString(R.string.gravity_right)) && getMeasuredWidth() > paddingLeft2) {
            return (getMeasuredWidth() - paddingLeft2) + paddingLeft;
        }
        return paddingLeft;
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new MarginLayoutParams(getContext(), attributeSet);
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(-2, -2);
    }

    protected LayoutParams generateLayoutParams(LayoutParams layoutParams) {
        return new MarginLayoutParams(layoutParams);
    }

    public void setMaxLines(int i) {
        this.d = i;
    }
}
