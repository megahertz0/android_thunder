package com.xunlei.downloadprovider.homepage.recommend;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.xunlei.c.a.b;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.commonview.ErrorView;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.homepage.a;
import com.xunlei.downloadprovider.homepage.choiceness.ui.RefreshPromptView;
import com.xunlei.downloadprovider.homepage.recommend.VideoFeedReporter.RefreshType;
import com.xunlei.downloadprovider.homepage.recommend.feed.ag;
import com.xunlei.downloadprovider.homepage.recommend.feed.aj;
import com.xunlei.downloadprovider.homepage.recommend.feed.o;
import com.xunlei.xllib.R;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: SummaryMoviesFeedView.java
public final class e extends FrameLayout {
    PullToRefreshListView a;
    aj b;
    a c;
    boolean d;
    boolean e;
    boolean f;
    private SummaryMoviesListFragment g;
    private ErrorView h;
    private RefreshPromptView i;
    private ag j;
    private ag k;
    private Handler l;
    private com.handmark.pulltorefresh.library.PullToRefreshBase.e<ListView> m;
    private RefreshType n;
    private a.a o;

    public final void setRefreshType(RefreshType refreshType) {
        this.n = refreshType;
    }

    public e(Context context, SummaryMoviesListFragment summaryMoviesListFragment) {
        super(context);
        this.l = new Handler();
        this.n = RefreshType.manul_pull;
        this.o = new p(this);
        this.c = new a(this.o);
        this.g = summaryMoviesListFragment;
        Context context2 = getContext();
        this.a = new PullToRefreshListView(context2);
        this.a.setLayoutParams(new LayoutParams(-1, -1));
        this.a.setMode(Mode.PULL_FROM_START);
        this.a.a = true;
        ((ListView) this.a.getRefreshableView()).setDividerHeight(0);
        this.b = new aj(context2, (ListView) this.a.getRefreshableView(), this.c);
        this.a.setAdapter(this.b);
        this.a.setScrollingWhileRefreshingEnabled(true);
        this.a.setDividerDrawable(null);
        this.a.setShowDividers(0);
        ((ListView) this.a.getRefreshableView()).setDividerHeight(0);
        this.b.a(o.a().d);
        this.d = q.d();
        if (!this.b.isEmpty() && this.d && this.d) {
            q.a(getContext(), (ListView) this.a.getRefreshableView());
        }
        addView(this.a);
        this.h = new ErrorView(context2);
        this.h.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.h.setActionButtonListener(new f(this));
        this.h.setOnTouchListener(new g(this));
        addView(this.h);
        View refreshPromptView = new RefreshPromptView(context2);
        refreshPromptView.setBackgroundColor(Color.parseColor("#f2dceffe"));
        refreshPromptView.setTextColor(Color.parseColor("#1294f6"));
        refreshPromptView.setTextSize(12.0f);
        refreshPromptView.setSingleLine(true);
        refreshPromptView.setGravity(R.styleable.Toolbar_maxButtonHeight);
        refreshPromptView.setTranslationY((float) (-g.a(getContext(), 37.0f)));
        addView(refreshPromptView, new ViewGroup.LayoutParams(-1, g.a(context2, 37.0f)));
        this.i = refreshPromptView;
        this.j = new h(this);
        this.k = new k(this);
        this.a.setOnRefreshListener(new n(this));
        this.a.setOnScrollListener(new o(this));
    }

    public final void a() {
        if (this.a != null && this.a.getRefreshableView() != null) {
            this.a.o();
        }
    }

    static /* synthetic */ void a(e eVar, boolean z, boolean z2, int i) {
        new StringBuilder("onRefreshComplete--isPullUpRefresh=").append(z).append("|isRefreshSuccess=").append(z2).append("|size=").append(i);
        if (!z) {
            o.a().b();
        }
        eVar.n = RefreshType.manul_pull;
        boolean isEmpty = eVar.b.isEmpty();
        eVar.a.m();
        eVar.c.a(z2);
        if (z2) {
            if (i >= 8) {
                eVar.a.setFooterNeedShow(true);
            } else {
                eVar.a.setFooterNeedShow(false);
            }
            eVar.b.a(o.a().d);
        }
        Context context = eVar.getContext();
        boolean a = b.a(context);
        if (eVar.b.isEmpty()) {
            if (a) {
                eVar.h.setErrorType(0);
            } else {
                eVar.h.setErrorType(SimpleLog.LOG_LEVEL_DEBUG);
            }
            eVar.h.setVisibility(0);
        } else {
            eVar.h.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
        if (!eVar.b.isEmpty()) {
            if (isEmpty && eVar.d) {
                q.a(eVar.getContext(), (ListView) eVar.a.getRefreshableView());
                if (eVar.f) {
                    q.a();
                }
            }
            if (!a) {
                XLToast.b(context, XLToastType.XLTOAST_TYPE_ALARM, "\u65e0\u7f51\u7edc\u8fde\u63a5");
            } else if (!z2) {
                XLToast.b(context, XLToastType.XLTOAST_TYPE_ALARM, "\u7f51\u7edc\u5f02\u5e38");
            } else if (z || i == 0) {
                eVar.i.a();
            } else {
                eVar.i.a(i);
            }
        }
    }
}
