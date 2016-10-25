package com.xunlei.downloadprovider.homepage.recommend;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import com.xunlei.downloadprovider.player.ab;
import com.xunlei.downloadprovider.player.q;

// compiled from: SummaryMoviesFeedView.java
final class o implements OnScrollListener {
    final /* synthetic */ e a;

    o(e eVar) {
        this.a = eVar;
    }

    public final void onScrollStateChanged(AbsListView absListView, int i) {
        this.a.b.b(i);
    }

    public final void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.a.d) {
            boolean z = i < 2 && this.a.f && !this.a.b.isEmpty();
            if (z && !this.a.e) {
                VideoFeedReporter.d();
            }
            this.a.e = z;
        }
        if (this.a.g.getUserVisibleHint()) {
            ab b = q.a().b("feed_player");
            if (b != null && !b.l && !b.m()) {
                int headerViewsCount = ((ListView) this.a.a.getRefreshableView()).getHeaderViewsCount();
                int i4 = b.A;
                if (i4 < i - headerViewsCount || i4 >= (i + i2) - headerViewsCount) {
                    q.a().a(b);
                }
            }
        }
    }
}
