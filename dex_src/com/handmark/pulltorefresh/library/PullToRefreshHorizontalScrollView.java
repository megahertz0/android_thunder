package com.handmark.pulltorefresh.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;
import com.xunlei.downloadprovidercommon.R;

public class PullToRefreshHorizontalScrollView extends PullToRefreshBase<HorizontalScrollView> {

    @TargetApi(9)
    final class a extends HorizontalScrollView {
        public a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        protected final boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
            boolean overScrollBy = super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z);
            PullToRefreshBase pullToRefreshBase = PullToRefreshHorizontalScrollView.this;
            int i9 = 0;
            if (getChildCount() > 0) {
                i9 = Math.max(0, getChildAt(0).getWidth() - ((getWidth() - getPaddingLeft()) - getPaddingRight()));
            }
            c.a(pullToRefreshBase, i, i3, i2, i4, i9, z);
            return overScrollBy;
        }
    }

    public PullToRefreshHorizontalScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final Orientation getPullToRefreshScrollDirection() {
        return Orientation.HORIZONTAL;
    }

    protected final boolean f() {
        return ((HorizontalScrollView) this.d).getScrollX() == 0;
    }

    protected final boolean g() {
        View childAt = ((HorizontalScrollView) this.d).getChildAt(0);
        if (childAt != null) {
            return ((HorizontalScrollView) this.d).getScrollX() >= childAt.getWidth() - getWidth();
        } else {
            return false;
        }
    }

    protected final /* synthetic */ View a(Context context, AttributeSet attributeSet) {
        View aVar;
        if (VERSION.SDK_INT >= 9) {
            aVar = new a(context, attributeSet);
        } else {
            aVar = new HorizontalScrollView(context, attributeSet);
        }
        aVar.setId(R.id.scrollview);
        return aVar;
    }
}
