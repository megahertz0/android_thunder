package com.xunlei.downloadprovider.search.ui.widget.flow;

import android.content.Context;
import android.view.View;
import android.widget.Checkable;
import android.widget.FrameLayout;

// compiled from: TagView.java
public final class b extends FrameLayout implements Checkable {
    private static final int[] b;
    private boolean a;

    static {
        b = new int[]{16842912};
    }

    public b(Context context) {
        super(context);
    }

    public final View getTagView() {
        return getChildAt(0);
    }

    public final int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (isChecked()) {
            mergeDrawableStates(onCreateDrawableState, b);
        }
        return onCreateDrawableState;
    }

    public final void setChecked(boolean z) {
        if (this.a != z) {
            this.a = z;
            refreshDrawableState();
        }
    }

    public final boolean isChecked() {
        return this.a;
    }

    public final void toggle() {
        setChecked(!this.a);
    }
}
