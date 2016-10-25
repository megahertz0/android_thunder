package com.xunlei.downloadprovider.homepage.choiceness.ui;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import com.xunlei.downloadprovider.homepage.choiceness.ChoicenessReporter;
import com.xunlei.downloadprovider.player.a.a;
import com.xunlei.downloadprovider.player.ab;
import com.xunlei.downloadprovider.player.q;

// compiled from: HomeChoicenessFragment.java
final class x implements OnScrollListener {
    final /* synthetic */ HomeChoicenessFragment a;

    x(HomeChoicenessFragment homeChoicenessFragment) {
        this.a = homeChoicenessFragment;
    }

    public final void onScrollStateChanged(AbsListView absListView, int i) {
        if (this.a.getUserVisibleHint()) {
            a a;
            this.a.a.b(i);
            ab b = q.a().b("home_player");
            if (this.a.l.b() || b == null || !b.t()) {
                a = this.a.l;
                if (!a.c()) {
                    return;
                }
                if (a.a(a.b) || !a.a(i)) {
                    if (a.a(a.b) && !a.a(i)) {
                        if (a.f != null && a.a(a.f)) {
                            a.f = null;
                        }
                        if (a.f == null) {
                            a.a(a.a, a.c, a.f);
                        }
                    }
                } else if (a.a(a.f)) {
                    a.f = null;
                }
            } else {
                a = this.a.l;
            }
            a.b = i;
        }
    }

    public final void onScroll(AbsListView absListView, int i, int i2, int i3) {
        boolean z = true;
        if (this.a.getUserVisibleHint()) {
            if (this.a.v) {
                boolean z2 = i < 2 && !this.a.a.isEmpty();
                if (z2 && !this.a.w) {
                    ChoicenessReporter.a("login");
                }
                this.a.w = z2;
            }
            ab b = q.a().b("home_player");
            if (this.a.l.b() || b == null || !b.t()) {
                HomeChoicenessFragment.a(this.a, i, i2);
                a a = this.a.l;
                if (a.a(a.f)) {
                    a.f = null;
                }
                if (a.c()) {
                    if (absListView.getChildCount() > 0) {
                        int top = absListView.getChildAt(0).getTop();
                        if (i == a.d) {
                            if (a.e < top && a.e < top) {
                                z = false;
                            }
                            a.e = top;
                        } else {
                            if (i < a.d) {
                                z = false;
                            }
                            a.d = i;
                            a.e = top;
                        }
                    }
                    a.c = z;
                    return;
                }
                return;
            }
            int headerViewsCount = ((ListView) this.a.g.getRefreshableView()).getHeaderViewsCount();
            int i4 = b.A;
            if (i4 < i - headerViewsCount || i4 >= (i + i2) - headerViewsCount) {
                q.a().a(b);
            }
        }
    }
}
