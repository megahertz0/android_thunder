package android.support.design.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.design.widget.CoordinatorLayout.d;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import java.util.List;

abstract class HeaderScrollingViewBehavior extends ViewOffsetBehavior<View> {
    int a;
    int b;
    private final Rect c;
    private final Rect d;

    abstract View a(List<View> list);

    public HeaderScrollingViewBehavior() {
        this.c = new Rect();
        this.d = new Rect();
        this.a = 0;
    }

    public HeaderScrollingViewBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = new Rect();
        this.d = new Rect();
        this.a = 0;
    }

    public boolean a(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int i4) {
        int i5 = view.getLayoutParams().height;
        if (i5 == -1 || i5 == -2) {
            View a = a(coordinatorLayout.a(view));
            if (a != null) {
                if (ViewCompat.getFitsSystemWindows(a) && !ViewCompat.getFitsSystemWindows(view)) {
                    ViewCompat.setFitsSystemWindows(view, true);
                    if (ViewCompat.getFitsSystemWindows(view)) {
                        view.requestLayout();
                        return true;
                    }
                }
                if (ViewCompat.isLaidOut(a)) {
                    int size = MeasureSpec.getSize(i3);
                    if (size == 0) {
                        size = coordinatorLayout.getHeight();
                    }
                    coordinatorLayout.a(view, i, i2, MeasureSpec.makeMeasureSpec(c(a) + (size - a.getMeasuredHeight()), i5 == -1 ? 1073741824 : ExploreByTouchHelper.INVALID_ID), i4);
                    return true;
                }
            }
        }
        return false;
    }

    protected final void c(CoordinatorLayout coordinatorLayout, View view, int i) {
        View a = a(coordinatorLayout.a(view));
        if (a != null) {
            d dVar = (d) view.getLayoutParams();
            Rect rect = this.c;
            rect.set(coordinatorLayout.getPaddingLeft() + dVar.leftMargin, a.getBottom() + dVar.topMargin, (coordinatorLayout.getWidth() - coordinatorLayout.getPaddingRight()) - dVar.rightMargin, ((coordinatorLayout.getHeight() + a.getBottom()) - coordinatorLayout.getPaddingBottom()) - dVar.bottomMargin);
            Rect rect2 = this.d;
            int i2 = dVar.c;
            if (i2 == 0) {
                i2 = 8388659;
            }
            GravityCompat.apply(i2, view.getMeasuredWidth(), view.getMeasuredHeight(), rect, rect2, i);
            i2 = d(a);
            view.layout(rect2.left, rect2.top - i2, rect2.right, rect2.bottom - i2);
            this.a = rect2.top - a.getBottom();
            return;
        }
        super.c(coordinatorLayout, view, i);
        this.a = 0;
    }

    float b(View view) {
        return 1.0f;
    }

    final int d(View view) {
        return this.b == 0 ? 0 : ad.a(Math.round(b(view) * ((float) this.b)), 0, this.b);
    }

    int c(View view) {
        return view.getMeasuredHeight();
    }
}
