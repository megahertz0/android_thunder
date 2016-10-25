package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;

public class PullToRefreshRecyclerView extends PullToRefreshBase<RecyclerView> {

    final class a extends RecyclerView {
        public a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        protected final boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
            boolean overScrollBy = super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z);
            c.a(PullToRefreshRecyclerView.this, i, i3, i2, i4, z);
            return overScrollBy;
        }
    }

    public PullToRefreshRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    protected final boolean g() {
        android.support.v7.widget.RecyclerView.a adapter = ((RecyclerView) this.d).getAdapter();
        if (adapter == null || adapter.getItemCount() == 0) {
            return true;
        }
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) ((RecyclerView) this.d).getLayoutManager();
        int itemCount = adapter.getItemCount();
        int k = linearLayoutManager.k();
        if (k >= itemCount - 1) {
            View childAt = ((RecyclerView) this.d).getChildAt(k - linearLayoutManager.j());
            if (childAt != null) {
                return childAt.getBottom() + -1 <= ((RecyclerView) this.d).getBottom();
            }
        }
        return false;
    }

    protected final boolean f() {
        android.support.v7.widget.RecyclerView.a adapter = ((RecyclerView) this.d).getAdapter();
        if (adapter == null || adapter.getItemCount() == 0) {
            return true;
        }
        if (((LinearLayoutManager) ((RecyclerView) this.d).getLayoutManager()).j() == 0) {
            View childAt = ((RecyclerView) this.d).getChildAt(0);
            if (childAt != null) {
                return childAt.getTop() >= ((RecyclerView) this.d).getTop();
            }
        }
        return false;
    }

    protected final /* synthetic */ View a(Context context, AttributeSet attributeSet) {
        return new a(context, attributeSet);
    }
}
