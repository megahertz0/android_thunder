package com.handmark.pulltorefresh.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;
import com.xunlei.downloadprovidercommon.R;

public class PullToRefreshGridView extends PullToRefreshAdapterViewBase<GridView> {

    class a extends GridView implements com.handmark.pulltorefresh.library.a.a {
        public a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public void setEmptyView(View view) {
            PullToRefreshGridView.this.setEmptyView(view);
        }

        public void setEmptyViewInternal(View view) {
            super.setEmptyView(view);
        }
    }

    @TargetApi(9)
    final class b extends a {
        public b(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        protected final boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
            boolean overScrollBy = super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z);
            c.a(PullToRefreshGridView.this, i, i3, i2, i4, z);
            return overScrollBy;
        }
    }

    public PullToRefreshGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    protected final /* synthetic */ View a(Context context, AttributeSet attributeSet) {
        View bVar;
        if (VERSION.SDK_INT >= 9) {
            bVar = new b(context, attributeSet);
        } else {
            bVar = new a(context, attributeSet);
        }
        bVar.setId(R.id.gridview);
        return bVar;
    }
}
