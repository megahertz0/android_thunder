package com.handmark.pulltorefresh.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;
import com.xunlei.downloadprovidercommon.R;

public class PullToRefreshScrollView extends PullToRefreshBase<ScrollView> {

    @TargetApi(9)
    final class a extends ScrollView {
        public a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        protected final boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
            boolean overScrollBy = super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z);
            PullToRefreshBase pullToRefreshBase = PullToRefreshScrollView.this;
            int i9 = 0;
            if (getChildCount() > 0) {
                i9 = Math.max(0, getChildAt(0).getHeight() - ((getHeight() - getPaddingBottom()) - getPaddingTop()));
            }
            c.a(pullToRefreshBase, i, i3, i2, i4, i9, z);
            return overScrollBy;
        }
    }

    public PullToRefreshScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    protected final boolean f() {
        return ((ScrollView) this.d).getScrollY() == 0;
    }

    protected final boolean g() {
        View childAt = ((ScrollView) this.d).getChildAt(0);
        if (childAt != null) {
            return ((ScrollView) this.d).getScrollY() >= childAt.getHeight() - getHeight();
        } else {
            return false;
        }
    }

    protected final /* synthetic */ View a(Context context, AttributeSet attributeSet) {
        View aVar;
        if (VERSION.SDK_INT >= 9) {
            aVar = new a(context, attributeSet);
        } else {
            aVar = new ScrollView(context, attributeSet);
        }
        aVar.setId(R.id.scrollview);
        return aVar;
    }
}
