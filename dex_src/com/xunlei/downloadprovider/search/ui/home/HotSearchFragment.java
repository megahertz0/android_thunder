package com.xunlei.downloadprovider.search.ui.home;

import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ScrollView;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.commonview.ErrorView;
import com.xunlei.downloadprovider.frame.BasePageFragment;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.f;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.downloadprovider.search.a;
import com.xunlei.downloadprovider.search.bean.WestRankType;
import com.xunlei.downloadprovider.search.bean.b;
import com.xunlei.downloadprovider.search.ui.home.SearchBannerAdapter.AdLoadState;
import com.xunlei.downloadprovider.search.ui.search.SearchActivity;
import com.xunlei.downloadprovider.search.ui.search.t;
import com.xunlei.downloadprovider.search.ui.widget.HistoryView;
import com.xunlei.downloadprovider.search.ui.widget.ObservableScrollView;
import com.xunlei.downloadprovider.search.ui.widget.SearchBannerLayout;
import com.xunlei.xiazaibao.BuildConfig;
import org.android.spdy.SpdyProtocol;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class HotSearchFragment extends BasePageFragment implements OnClickListener, OnItemClickListener {
    private ObservableScrollView a;
    private ViewGroup b;
    private HistoryView c;
    private t d;
    private SearchBannerLayout e;
    private SearchBannerAdapter f;
    private SearchBannerLayout g;
    private SearchBannerAdapter h;
    private SearchBannerLayout i;
    private SearchBannerAdapter j;
    private SearchBannerLayout k;
    private SearchBannerAdapter l;
    private SearchBannerLayout m;
    private SearchBannerAdapter n;
    private ErrorView o;
    private int p;
    private final int[] q;
    private final int[] r;
    private final Rect s;
    private final Rect t;
    private a u;

    public HotSearchFragment() {
        this.p = 0;
        this.q = new int[2];
        this.r = new int[2];
        this.s = new Rect();
        this.t = new Rect();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a aVar = new a(getActivity());
        this.d = new t(getActivity());
        this.u = aVar;
    }

    protected View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.hot_search_fragment, viewGroup, false);
        this.a = (ObservableScrollView) inflate.findViewById(com.xunlei.downloadprovidershare.R.id.scrollview);
        this.a.setOnScrollListener(new d(this));
        this.b = (ViewGroup) inflate.findViewById(R.id.record_layout);
        this.b.findViewById(R.id.delete_btn).setOnClickListener(this);
        this.c = (HistoryView) this.b.findViewById(R.id.history_view);
        this.c.setOnItemClickListener(new e(this));
        this.c.setAdapter(this.d);
        this.e = (SearchBannerLayout) inflate.findViewById(R.id.banner_hot);
        this.e.setTitle$505cff1c("\u4eca\u65e5\u70ed\u641c");
        this.e.setOnItemClickListener(this);
        this.e.setAdapter(this.f);
        this.f.b = this.e;
        this.g = (SearchBannerLayout) inflate.findViewById(R.id.banner_movie);
        this.g.setTitle$505cff1c("\u7535\u5f71\u98ce\u4e91\u699c");
        this.g.setOnItemClickListener(this);
        this.g.setAdapter(this.h);
        this.h.b = this.g;
        this.i = (SearchBannerLayout) inflate.findViewById(R.id.banner_teleplay);
        this.i.setTitle$505cff1c("\u7535\u89c6\u5267\u98ce\u4e91\u699c");
        this.i.setOnItemClickListener(this);
        this.i.setAdapter(this.j);
        this.j.b = this.i;
        this.k = (SearchBannerLayout) inflate.findViewById(R.id.banner_variety);
        this.k.setTitle$505cff1c("\u7efc\u827a\u98ce\u4e91\u699c");
        this.k.setOnItemClickListener(this);
        this.k.setAdapter(this.l);
        this.l.b = this.k;
        this.m = (SearchBannerLayout) inflate.findViewById(R.id.banner_anime);
        this.m.setTitle$505cff1c("\u52a8\u6f2b\u98ce\u4e91\u699c");
        this.m.setOnItemClickListener(this);
        this.m.setAdapter(this.n);
        this.n.b = this.m;
        this.o = (ErrorView) inflate.findViewById(R.id.ev_error);
        this.o.setActionButtonListener(new f(this));
        return inflate;
    }

    private void a(ScrollView scrollView, SearchBannerLayout searchBannerLayout, SearchBannerAdapter searchBannerAdapter) {
        boolean z;
        View view = null;
        if (searchBannerAdapter.c == AdLoadState.LOADED) {
            z = true;
        } else {
            z = false;
        }
        if (z && searchBannerAdapter.d != null && searchBannerAdapter.a.size() > 3) {
            view = (View) searchBannerAdapter.a.get(MqttConnectOptions.MQTT_VERSION_3_1);
        }
        if (view != null) {
            Rect rect = this.s;
            Rect rect2 = this.t;
            scrollView.getLocationInWindow(this.q);
            view.getLocationInWindow(this.r);
            rect.set(this.q[0], this.q[1], this.q[0] + scrollView.getWidth(), this.q[1] + scrollView.getHeight());
            rect2.set(this.r[0], this.r[1], this.r[0] + view.getWidth(), view.getHeight() + this.r[1]);
            if (rect.contains(rect2)) {
                if (searchBannerAdapter.d != null && !searchBannerAdapter.g) {
                    searchBannerAdapter.g = true;
                    searchBannerAdapter.d.onExposured(searchBannerLayout);
                    WestRankType westRankType = searchBannerAdapter.h;
                    String str = SocializeProtocolConstants.PROTOCOL_KEY_TENCENT;
                    String str2 = "adv_hotsearch_show";
                    g a = g.a("android_advertise", str2, str2);
                    a.a("cardid", westRankType.getName(), MqttConnectOptions.MQTT_VERSION_3_1);
                    a.a("ad_id", "ad_tencent", MqttConnectOptions.MQTT_VERSION_3_1);
                    a.b("positionid", 4);
                    a.a("ad_type", str, MqttConnectOptions.MQTT_VERSION_3_1);
                    f.a(a);
                }
            } else if ((rect.top > rect2.bottom || rect.bottom < rect2.top) && searchBannerAdapter.g) {
                searchBannerAdapter.g = false;
            }
        }
    }

    public void onStart() {
        super.onStart();
        this.d.a(com.xunlei.downloadprovider.search.a.a.a().b());
        a();
    }

    public void onStop() {
        super.onStop();
        if (this.u != null) {
            this.u.c.clear();
        }
    }

    private void a() {
        Object obj = 1;
        if (this.b != null) {
            if (this.d.isEmpty()) {
                this.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                this.b.setVisibility(0);
                int i = 0;
            }
        }
        if (this.e != null) {
            if (this.f.isEmpty()) {
                this.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                this.e.setVisibility(0);
                i = 0;
            }
        }
        if (this.g != null) {
            if (this.h.isEmpty()) {
                this.g.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                this.g.setVisibility(0);
                i = 0;
            }
        }
        if (this.i != null) {
            if (this.j.isEmpty()) {
                this.i.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                this.i.setVisibility(0);
                i = 0;
            }
        }
        if (this.k != null) {
            if (this.l.isEmpty()) {
                this.k.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                this.k.setVisibility(0);
                i = 0;
            }
        }
        if (this.m != null) {
            if (this.n.isEmpty()) {
                this.m.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                this.m.setVisibility(0);
                i = 0;
            }
        }
        if (this.o == null) {
            return;
        }
        if (i == 0 || this.p > 0) {
            this.o.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.delete_btn:
                com.xunlei.downloadprovider.search.a.a.a().c();
                this.d.d();
                a();
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
                a = this.f.a(i);
                if (a.c) {
                    this.f.a(view);
                    return;
                }
                str = a.a;
                str2 = "hotword";
                str3 = "search_hot";
                break;
            case R.id.banner_movie:
                a = this.h.a(i);
                if (a.c) {
                    this.h.a(view);
                    return;
                }
                str = a.a;
                str2 = "movie";
                str3 = "search_hotvideo_movie";
                break;
            case R.id.banner_teleplay:
                a = this.j.a(i);
                if (a.c) {
                    this.j.a(view);
                    return;
                }
                str = a.a;
                str2 = "teleplay";
                str3 = "search_hotvideo_teleplay";
                break;
            case R.id.banner_variety:
                a = this.l.a(i);
                if (a.c) {
                    this.l.a(view);
                    return;
                }
                str = a.a;
                str2 = "comedy";
                str3 = "search_hotvideo_zongyi";
                break;
            case R.id.banner_anime:
                a = this.n.a(i);
                if (a.c) {
                    this.n.a(view);
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
        this.f.e();
        this.h.e();
        this.j.e();
        this.l.e();
        this.n.e();
    }

    public void onMainTabClick(boolean z) {
        if (!z && this.a != null) {
            this.a.smoothScrollTo(0, 0);
        }
    }

    public void onPageSelected() {
        super.onPageSelected();
        f.a("hotsearch");
        ScrollView scrollView = this.a;
        a(scrollView, this.e, this.f);
        a(scrollView, this.g, this.h);
        a(scrollView, this.i, this.j);
        a(scrollView, this.k, this.l);
        a(scrollView, this.m, this.n);
    }
}
