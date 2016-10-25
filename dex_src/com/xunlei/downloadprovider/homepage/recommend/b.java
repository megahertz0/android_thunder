package com.xunlei.downloadprovider.homepage.recommend;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import com.xunlei.downloadprovider.player.ab;
import com.xunlei.downloadprovider.player.q;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: ShortTimeVideoListActivity.java
final class b implements OnScrollListener {
    final /* synthetic */ ShortTimeVideoListActivity a;

    b(ShortTimeVideoListActivity shortTimeVideoListActivity) {
        this.a = shortTimeVideoListActivity;
    }

    public final void onScrollStateChanged(AbsListView absListView, int i) {
        if (ShortTimeVideoListActivity.a(this.a) != null) {
            int count = ShortTimeVideoListActivity.a(this.a).getCount() - 1;
            int lastVisiblePosition = ShortTimeVideoListActivity.b(this.a).getLastVisiblePosition() - 2;
            if ((i == 0 && lastVisiblePosition == count) || lastVisiblePosition == count - 1) {
                ShortTimeVideoListActivity.a(this.a).a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
            }
            switch (i) {
                case SpdyAgent.ACCS_TEST_SERVER:
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    ShortTimeVideoListActivity.a(this.a).a(false);
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    ShortTimeVideoListActivity.a(this.a).a(true);
                default:
                    break;
            }
        }
    }

    public final void onScroll(AbsListView absListView, int i, int i2, int i3) {
        float f = 1.0f;
        float c = ShortTimeVideoListActivity.c(this.a) * 0.24f;
        if (i == 1 || i == 0) {
            c = ((float) (-ShortTimeVideoListActivity.b(this.a).getChildAt(0).getTop())) / c;
            if (c <= 1.0f) {
                f = c;
            }
        }
        this.a.l.setAlpha(f);
        if (f == 0.0f) {
            this.a.k.setImageResource(2130839158);
            this.a.m.setImageResource(2130838459);
        } else {
            this.a.k.setImageResource(2130839308);
            this.a.m.setImageResource(2130838456);
        }
        ab b = q.a().b("channel_player");
        if (b != null && !b.l && !b.m()) {
            int headerViewsCount = ((ListView) ShortTimeVideoListActivity.d(this.a).getRefreshableView()).getHeaderViewsCount();
            int i4 = b.A;
            if (i4 < i - headerViewsCount || i4 >= (i + i2) - headerViewsCount) {
                q.a().a(b);
            }
        }
    }
}
