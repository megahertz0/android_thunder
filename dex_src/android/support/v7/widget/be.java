package android.support.v7.widget;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.widget.RecyclerView.t;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

// compiled from: RecyclerView.java
final class be implements b {
    final /* synthetic */ RecyclerView a;

    be(RecyclerView recyclerView) {
        this.a = recyclerView;
    }

    public final int a() {
        return this.a.getChildCount();
    }

    public final void a(View view, int i) {
        this.a.addView(view, i);
        this.a.dispatchChildAttached(view);
    }

    public final int a(View view) {
        return this.a.indexOfChild(view);
    }

    public final void a(int i) {
        View childAt = this.a.getChildAt(i);
        if (childAt != null) {
            this.a.dispatchChildDetached(childAt);
        }
        this.a.removeViewAt(i);
    }

    public final View b(int i) {
        return this.a.getChildAt(i);
    }

    public final t b(View view) {
        return RecyclerView.getChildViewHolderInt(view);
    }

    public final void a(View view, int i, LayoutParams layoutParams) {
        t childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            if (childViewHolderInt.isTmpDetached() || childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.clearTmpDetachFlag();
            } else {
                throw new IllegalArgumentException(new StringBuilder("Called attach on a child which is not detached: ").append(childViewHolderInt).toString());
            }
        }
        this.a.attachViewToParent(view, i, layoutParams);
    }

    public final void c(int i) {
        View b = b(i);
        if (b != null) {
            t childViewHolderInt = RecyclerView.getChildViewHolderInt(b);
            if (childViewHolderInt != null) {
                if (!childViewHolderInt.isTmpDetached() || childViewHolderInt.shouldIgnore()) {
                    childViewHolderInt.addFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY);
                } else {
                    throw new IllegalArgumentException(new StringBuilder("called detach on an already detached child ").append(childViewHolderInt).toString());
                }
            }
        }
        this.a.detachViewFromParent(i);
    }

    public final void c(View view) {
        t childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            childViewHolderInt.onEnteredHiddenState();
        }
    }

    public final void d(View view) {
        t childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            childViewHolderInt.onLeftHiddenState();
        }
    }

    public final void b() {
        int childCount = this.a.getChildCount();
        for (int i = 0; i < childCount; i++) {
            this.a.dispatchChildDetached(b(i));
        }
        this.a.removeAllViews();
    }
}
