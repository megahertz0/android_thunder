package com.xunlei.downloadprovider.search.ui.home;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.GridView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.commonview.ErrorView;
import com.xunlei.downloadprovider.frame.BasePageFragment;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.f;
import com.xunlei.downloadprovider.search.a;
import com.xunlei.downloadprovider.search.bean.WestRankType;
import com.xunlei.downloadprovider.search.bean.b;
import com.xunlei.downloadprovider.search.ui.search.SearchActivity;
import com.xunlei.downloadprovider.search.ui.search.u;
import com.xunlei.downloadprovider.search.ui.widget.SearchBannerLayout;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xiazaibao.R$color;
import java.util.List;
import org.android.spdy.SpdyProtocol;

public class HotSearchFixFragment extends BasePageFragment implements OnClickListener, OnItemClickListener {
    private FrameLayout a;
    private HotSearchEmptyView b;
    private ViewGroup c;
    private GridView d;
    private u e;
    private SearchBannerLayout f;
    private SearchBannerAdapter g;
    private SearchBannerLayout h;
    private SearchBannerAdapter i;
    private SearchBannerLayout j;
    private SearchBannerAdapter k;
    private SearchBannerLayout l;
    private SearchBannerAdapter m;
    private SearchBannerLayout n;
    private SearchBannerAdapter o;
    private ErrorView p;
    private int q;
    private final int[] r;
    private final int[] s;
    private final Rect t;
    private final Rect u;
    private a v;

    public HotSearchFixFragment() {
        this.q = 0;
        this.r = new int[2];
        this.s = new int[2];
        this.t = new Rect();
        this.u = new Rect();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a aVar = new a(getActivity());
        this.e = new u(getActivity());
        this.g = new SearchBannerAdapter(getActivity(), aVar, WestRankType.HOT_SEARCH, this);
        this.i = new SearchBannerAdapter(getActivity(), aVar, WestRankType.MOVIE, this);
        this.k = new SearchBannerAdapter(getActivity(), aVar, WestRankType.TELEPLAY, this);
        this.m = new SearchBannerAdapter(getActivity(), aVar, WestRankType.VARIETY, this);
        this.o = new SearchBannerAdapter(getActivity(), aVar, WestRankType.ANIME, this);
        this.v = aVar;
    }

    protected View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.hot_search_fix_fragment, viewGroup, false);
        this.a = (FrameLayout) inflate.findViewById(R.id.rootView);
        this.b = (HotSearchEmptyView) inflate.findViewById(R.id.empty);
        this.b.setTitle("\u6682\u65e0\u641c\u7d22\u5386\u53f2");
        this.c = (ViewGroup) inflate.findViewById(R.id.record_layout);
        this.c.findViewById(R.id.delete_btn).setOnClickListener(this);
        this.d = (GridView) this.c.findViewById(R.id.history_view);
        this.d.setOnItemClickListener(new b(this));
        this.d.setAdapter(this.e);
        this.f = (SearchBannerLayout) inflate.findViewById(R.id.banner_hot);
        this.f.setTitle$505cff1c("\u4eca\u65e5\u70ed\u641c");
        this.f.setOnItemClickListener(this);
        this.f.setAdapter(this.g);
        this.g.b = this.f;
        this.h = (SearchBannerLayout) inflate.findViewById(R.id.banner_movie);
        this.h.setTitle$505cff1c("\u7535\u5f71\u98ce\u4e91\u699c");
        this.h.setOnItemClickListener(this);
        this.h.setAdapter(this.i);
        this.i.b = this.h;
        this.j = (SearchBannerLayout) inflate.findViewById(R.id.banner_teleplay);
        this.j.setTitle$505cff1c("\u7535\u89c6\u5267\u98ce\u4e91\u699c");
        this.j.setOnItemClickListener(this);
        this.j.setAdapter(this.k);
        this.k.b = this.j;
        this.l = (SearchBannerLayout) inflate.findViewById(R.id.banner_variety);
        this.l.setTitle$505cff1c("\u7efc\u827a\u98ce\u4e91\u699c");
        this.l.setOnItemClickListener(this);
        this.l.setAdapter(this.m);
        this.m.b = this.l;
        this.n = (SearchBannerLayout) inflate.findViewById(R.id.banner_anime);
        this.n.setTitle$505cff1c("\u52a8\u6f2b\u98ce\u4e91\u699c");
        this.n.setOnItemClickListener(this);
        this.n.setAdapter(this.o);
        this.o.b = this.n;
        this.p = (ErrorView) inflate.findViewById(R.id.ev_error);
        this.p.setActionButtonListener(new c(this));
        return inflate;
    }

    public static boolean a() {
        return false;
    }

    public void onStart() {
        super.onStart();
        List b = com.xunlei.downloadprovider.search.a.a.a().b();
        if (b.size() == 0) {
            this.b.setVisibility(0);
        } else {
            FragmentActivity activity = getActivity();
            if (!(this.a == null || activity == null)) {
                this.a.setBackgroundColor(getActivity().getResources().getColor(R$color.white));
            }
            this.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
        this.e.a(b);
        b();
    }

    public void onStop() {
        super.onStop();
        if (this.v != null) {
            this.v.c.clear();
        }
    }

    private void b() {
        Object obj = 1;
        if (this.c != null) {
            if (this.e.isEmpty()) {
                this.c.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                this.c.setVisibility(0);
                int i = 0;
            }
        }
        if (this.f != null) {
            if (this.g.isEmpty()) {
                this.f.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                this.f.setVisibility(0);
                i = 0;
            }
        }
        if (this.h != null) {
            if (this.i.isEmpty()) {
                this.h.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                this.h.setVisibility(0);
                i = 0;
            }
        }
        if (this.j != null) {
            if (this.k.isEmpty()) {
                this.j.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                this.j.setVisibility(0);
                i = 0;
            }
        }
        if (this.l != null) {
            if (this.m.isEmpty()) {
                this.l.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                this.l.setVisibility(0);
                i = 0;
            }
        }
        if (this.n != null) {
            if (this.o.isEmpty()) {
                this.n.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                this.n.setVisibility(0);
                i = 0;
            }
        }
        if (this.p == null) {
            return;
        }
        if (i == 0 || this.q > 0) {
            this.p.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.delete_btn:
                com.xunlei.downloadprovider.search.a.a.a().c();
                this.e.d();
                this.b.setVisibility(0);
                b();
                f.c("histroy", "delete", BuildConfig.VERSION_NAME);
            default:
                break;
        }
    }

    private void a(String str, String str2) {
        SearchActivity.a(getActivity(), str, str2);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        String str = BuildConfig.VERSION_NAME;
        String str2 = BuildConfig.VERSION_NAME;
        String str3 = BuildConfig.VERSION_NAME;
        b a;
        switch ((int) j) {
            case R.id.banner_hot:
                a = this.g.a(i);
                if (a.c) {
                    this.g.a(view);
                    return;
                }
                str = a.a;
                str2 = "hotword";
                str3 = "search_hot";
                break;
            case R.id.banner_movie:
                a = this.i.a(i);
                if (a.c) {
                    this.i.a(view);
                    return;
                }
                str = a.a;
                str2 = "movie";
                str3 = "search_hotvideo_movie";
                break;
            case R.id.banner_teleplay:
                a = this.k.a(i);
                if (a.c) {
                    this.k.a(view);
                    return;
                }
                str = a.a;
                str2 = "teleplay";
                str3 = "search_hotvideo_teleplay";
                break;
            case R.id.banner_variety:
                a = this.m.a(i);
                if (a.c) {
                    this.m.a(view);
                    return;
                }
                str = a.a;
                str2 = "comedy";
                str3 = "search_hotvideo_zongyi";
                break;
            case R.id.banner_anime:
                a = this.o.a(i);
                if (a.c) {
                    this.o.a(view);
                    return;
                }
                str = a.a;
                str2 = "anime";
                str3 = "search_hotvideo_catoon";
                break;
        }
        if (!TextUtils.isEmpty(str)) {
            a(str3, str);
            f.c(str2, "word", str);
        }
    }

    public void onPause() {
        super.onPause();
        this.g.e();
        this.i.e();
        this.k.e();
        this.m.e();
        this.o.e();
    }

    public void onMainTabClick(boolean z) {
    }

    public void onPageSelected() {
        super.onPageSelected();
        f.a("hotsearch");
    }
}
