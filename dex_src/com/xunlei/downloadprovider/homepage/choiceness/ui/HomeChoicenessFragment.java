package com.xunlei.downloadprovider.homepage.choiceness.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.ad.home.a.e;
import com.xunlei.downloadprovider.ad.home.ui.ADItemView.AD_LAYOUT_TYPE;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.commonview.ErrorView;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.frame.BasePageFragment;
import com.xunlei.downloadprovider.frame.af;
import com.xunlei.downloadprovider.frame.user.bx;
import com.xunlei.downloadprovider.homepage.a.a;
import com.xunlei.downloadprovider.homepage.choiceness.ChoicenessReporter;
import com.xunlei.downloadprovider.homepage.choiceness.ChoicenessReporter.RefreshType;
import com.xunlei.downloadprovider.homepage.interest.a.j;
import com.xunlei.downloadprovider.homepage.interest.ui.b;
import com.xunlei.downloadprovider.homepage.interest.ui.c;
import com.xunlei.downloadprovider.homepage.l;
import com.xunlei.downloadprovider.homepage.m;
import com.xunlei.downloadprovider.homepage.n;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.p;
import com.xunlei.downloadprovider.player.q;
import com.xunlei.downloadprovider.util.v;
import com.xunlei.xiazaibao.BuildConfig;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.android.agoo.common.AgooConstants;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;

public class HomeChoicenessFragment extends BasePageFragment implements af {
    private BroadcastReceiver A;
    private a B;
    private final p C;
    n a;
    public boolean b;
    public boolean c;
    boolean d;
    private RefreshPromptView e;
    private ErrorView f;
    private PullToRefreshListView g;
    private com.xunlei.downloadprovider.homepage.choiceness.a.a h;
    private Handler i;
    private boolean j;
    private com.xunlei.downloadprovider.homepage.a k;
    private com.xunlei.downloadprovider.player.a.a l;
    private boolean m;
    private RefreshType n;
    private com.xunlei.downloadprovider.member.b.a o;
    private com.xunlei.downloadprovider.member.b.a.a p;
    private boolean q;
    private boolean r;
    private View s;
    private j t;
    private View u;
    private boolean v;
    private boolean w;
    private bx x;
    private com.xunlei.downloadprovider.a.j y;
    private ChoicenessVipRenewalRemindItemView z;

    public HomeChoicenessFragment() {
        this.i = new Handler();
        this.n = RefreshType.manul_pull;
        this.b = false;
        this.c = false;
        this.q = false;
        this.r = false;
        this.v = false;
        this.x = new bx();
        this.A = new o(this);
        this.B = new r(this);
        this.C = new t(this);
    }

    static /* synthetic */ void v(HomeChoicenessFragment homeChoicenessFragment) {
        if (!homeChoicenessFragment.r) {
            ListView listView = (ListView) homeChoicenessFragment.g.getRefreshableView();
            FragmentActivity activity = homeChoicenessFragment.getActivity();
            View inflate = LayoutInflater.from(activity).inflate(R.layout.layout_interest_tag_entrance, null);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.iv_close);
            inflate.setOnClickListener(new b(activity));
            imageView.setOnClickListener(new c(homeChoicenessFragment));
            homeChoicenessFragment.s = inflate;
            listView.addHeaderView(homeChoicenessFragment.s);
            homeChoicenessFragment.g.setAdapter(homeChoicenessFragment.a);
            homeChoicenessFragment.t.b(true);
            homeChoicenessFragment.r = true;
            if (homeChoicenessFragment.z != null) {
                homeChoicenessFragment.z.a();
            }
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.h = com.xunlei.downloadprovider.homepage.choiceness.a.a.a((Context) activity);
        this.k = new com.xunlei.downloadprovider.homepage.a(this.B);
        this.t = new j();
    }

    protected View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.home_choiceness_fragment, viewGroup, false);
        this.e = (RefreshPromptView) inflate.findViewById(R.id.refresh_prompt);
        this.e.setTranslationY((float) (-g.a(getActivity(), 37.0f)));
        this.f = (ErrorView) inflate.findViewById(R.id.error_layout);
        this.f.setActionButtonListener(new u(this));
        this.f.setOnTouchListener(new v(this));
        this.g = (PullToRefreshListView) inflate.findViewById(R.id.choiceness_list);
        ((ListView) this.g.getRefreshableView()).setHeaderDividersEnabled(false);
        ((ListView) this.g.getRefreshableView()).setFooterDividersEnabled(false);
        this.g.setMode(Mode.PULL_FROM_START);
        this.g.a = true;
        ListView listView = (ListView) this.g.getRefreshableView();
        listView.setDivider(getActivity().getResources().getDrawable(R.drawable.custom_list_divider));
        this.l = new com.xunlei.downloadprovider.player.a.a();
        this.l.a = listView;
        n nVar = new n(getActivity(), (ListView) this.g.getRefreshableView(), this.k, this.l);
        this.g.setAdapter(nVar);
        com.xunlei.downloadprovider.ad.home.a.c.a(getActivity().getApplicationContext()).d = nVar;
        ((ListView) this.g.getRefreshableView()).setOnTouchListener(new w(this));
        this.g.setOnScrollListener(new x(this));
        this.g.setOnRefreshListener(new y(this));
        this.g.setOnItemClickListener(new ad(this, nVar));
        this.a = nVar;
        com.xunlei.downloadprovider.homepage.choiceness.a.a aVar = this.h;
        aVar.e.execute(new com.xunlei.downloadprovider.homepage.choiceness.a.a.b(aVar, new q(this)));
        LoginHelper.a().a(new ae(this));
        LoginHelper.a().a(this.C);
        com.xunlei.downloadprovider.ad.home.a.c a = com.xunlei.downloadprovider.ad.home.a.c.a(getActivity());
        com.xunlei.downloadprovider.ad.home.a.g.a(a.b).a(0, AD_LAYOUT_TYPE.IMAGE_TYPE_VIEW).a(new e(a, AD_LAYOUT_TYPE.IMAGE_TYPE_VIEW), "1116");
        com.xunlei.downloadprovider.ad.home.a.g.a(a.b).a(0, AD_LAYOUT_TYPE.SHORT_VOD_TYPE_VIEW).a(new e(a, AD_LAYOUT_TYPE.SHORT_VOD_TYPE_VIEW), "1117");
        return inflate;
    }

    private void e() {
        if (this.g != null && this.g.getRefreshableView() != null) {
            this.g.o();
        }
    }

    public void onPageOff() {
        super.onPageOff();
        q.a().a("home_player");
    }

    public void onResume() {
        boolean z = false;
        super.onResume();
        j jVar = this.t;
        if (jVar.a.getBoolean("key_is_recommend", false) && !jVar.b() && jVar.a() <= 10) {
            z = true;
        }
        if (!z) {
            c();
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public boolean onBackPressed() {
        ChoicenessReporter.a();
        ChoicenessReporter.b();
        return super.onBackPressed();
    }

    public void onUserVisible(boolean z) {
        String str;
        super.onUserVisible(z);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        BrothersApplication.a().registerReceiver(this.A, intentFilter);
        if (this.a != null) {
            this.a.a(this.m);
            this.m = false;
        }
        if (!(this.g == null || this.g.k())) {
            this.k.a();
        }
        if (v.a().b("choiceness")) {
            str = "point";
        } else {
            str = "0";
        }
        com.xunlei.downloadprovider.homepage.j.a(str);
        if (this.w) {
            ChoicenessReporter.a("login");
        }
    }

    public void onUserInvisible(boolean z) {
        super.onUserInvisible(z);
        if (this.a != null) {
            this.a.a();
        }
        BrothersApplication.a().unregisterReceiver(this.A);
    }

    public void onMainTabClick(boolean z) {
        super.onMainTabClick(z);
        this.m = z;
        if (z) {
            q.a().a("home_player");
            return;
        }
        this.n = RefreshType.single_click_bottom_rec;
        e();
    }

    public final void a() {
        this.n = RefreshType.single_click_top_tab;
        e();
    }

    protected void onFullScreenChange(boolean z) {
        super.onFullScreenChange(z);
        if (this.a != null) {
            this.a.d = System.currentTimeMillis();
        }
    }

    private void f() {
        if (!this.v && !this.q && !this.t.b()) {
            new com.xunlei.downloadprovider.homepage.interest.a.a().a(0, new s(this));
        }
    }

    private void g() {
        boolean z = false;
        n nVar = new n();
        if (!(nVar.a.getBoolean("is_logged", false) || nVar.a.getBoolean("is_closed", false))) {
            z = true;
        }
        if (z && !this.v && this.u == null) {
            ListView listView = (ListView) this.g.getRefreshableView();
            FragmentActivity activity = getActivity();
            View inflate = LayoutInflater.from(activity).inflate(R.layout.layout_login_header_view, null);
            inflate.findViewById(R.id.iv_close).setOnClickListener(new l(this));
            inflate.setOnClickListener(new m(activity));
            this.u = inflate;
            listView.addHeaderView(this.u);
            this.v = true;
        }
    }

    public final void b() {
        if (this.u != null) {
            ((ListView) this.g.getRefreshableView()).removeHeaderView(this.u);
            this.u = null;
            this.v = false;
        }
    }

    public final void c() {
        if (this.s != null) {
            ((ListView) this.g.getRefreshableView()).removeHeaderView(this.s);
            this.s = null;
            this.t.b(false);
            this.r = false;
        }
    }

    static /* synthetic */ void a(HomeChoicenessFragment homeChoicenessFragment, int i, int i2) {
        com.xunlei.downloadprovider.player.a.b bVar = homeChoicenessFragment.l.f;
        if (bVar instanceof com.xunlei.downloadprovider.ad.home.ui.a) {
            int headerViewsCount = ((ListView) homeChoicenessFragment.g.getRefreshableView()).getHeaderViewsCount();
            int position = bVar.getPosition();
            if (position < i - headerViewsCount || position >= (i + i2) - headerViewsCount) {
                bVar.d();
            }
        }
    }

    static /* synthetic */ void a(HomeChoicenessFragment homeChoicenessFragment, boolean z, List list, com.xunlei.downloadprovider.homepage.choiceness.a.a.b bVar) {
        List arrayList;
        int i = 0;
        boolean z2 = (list == null || list.isEmpty()) ? false : true;
        homeChoicenessFragment.k.a(z2);
        if (homeChoicenessFragment.g != null) {
            homeChoicenessFragment.g.m();
        }
        if (bVar != null) {
            if (bVar.g) {
                ChoicenessReporter.a(homeChoicenessFragment.n, com.xunlei.downloadprovider.ad.common.b.a(bVar.f));
            } else {
                arrayList = new ArrayList();
                List list2 = bVar.c;
                if (bVar.b != null) {
                    arrayList.addAll(bVar.b);
                }
                if (list2 != null) {
                    arrayList.addAll(bVar.c);
                    if (list.size() > 0) {
                        homeChoicenessFragment.g.setFooterNeedShow(true);
                    } else {
                        homeChoicenessFragment.g.setFooterNeedShow(false);
                    }
                }
                ChoicenessReporter.a(homeChoicenessFragment.n, arrayList);
            }
        }
        homeChoicenessFragment.j = false;
        homeChoicenessFragment.n = RefreshType.manul_pull;
        FragmentActivity activity = homeChoicenessFragment.getActivity();
        if (homeChoicenessFragment.a != null && activity != null) {
            boolean isEmpty = homeChoicenessFragment.a.isEmpty();
            homeChoicenessFragment.a.a(list);
            boolean a = com.xunlei.c.a.b.a(BrothersApplication.a());
            if (homeChoicenessFragment.a.isEmpty()) {
                if (a) {
                    homeChoicenessFragment.f.setErrorType(0);
                } else {
                    homeChoicenessFragment.f.setErrorType(SimpleLog.LOG_LEVEL_DEBUG);
                }
                homeChoicenessFragment.f.setVisibility(0);
            } else {
                homeChoicenessFragment.f.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            }
            if (!homeChoicenessFragment.a.isEmpty()) {
                if (isEmpty) {
                    homeChoicenessFragment.g();
                    homeChoicenessFragment.f();
                }
                if (!homeChoicenessFragment.isResumed()) {
                    return;
                }
                if (!a) {
                    XLToast.b(activity, XLToastType.XLTOAST_TYPE_ALARM, "\u65e0\u7f51\u7edc\u8fde\u63a5");
                } else if (bVar == null) {
                    XLToast.b(activity, XLToastType.XLTOAST_TYPE_ALARM, "\u7f51\u7edc\u5f02\u5e38");
                } else {
                    arrayList = bVar.c;
                    if (arrayList != null) {
                        i = arrayList.size();
                    }
                    if (z || i == 0) {
                        homeChoicenessFragment.e.a();
                    } else {
                        homeChoicenessFragment.e.a(i);
                    }
                }
            }
        }
    }

    static /* synthetic */ void d() {
        com.xunlei.downloadprovider.ad.home.b a = com.xunlei.downloadprovider.ad.home.b.a();
        if (!a.e) {
            a.e = true;
            com.xunlei.downloadprovider.ad.common.d.b.b.a().a(a.a, new com.xunlei.downloadprovider.ad.home.c(a));
        }
    }

    static /* synthetic */ void n(HomeChoicenessFragment homeChoicenessFragment) {
        homeChoicenessFragment.x = new bx();
        if (homeChoicenessFragment.y == null) {
            homeChoicenessFragment.y = new com.xunlei.downloadprovider.a.j(BrothersApplication.a().n.getApplicationContext(), "vip_renew_homePage");
        }
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        CharSequence b = homeChoicenessFragment.y.b(new StringBuilder("dateAndUser").append(homeChoicenessFragment.x.d).toString(), BuildConfig.VERSION_NAME);
        if (homeChoicenessFragment.x != null) {
            if (TextUtils.equals(format + homeChoicenessFragment.x.d, b)) {
                homeChoicenessFragment.b = false;
            } else {
                homeChoicenessFragment.b = true;
            }
        }
        if (homeChoicenessFragment.b && !homeChoicenessFragment.c && !homeChoicenessFragment.v && !homeChoicenessFragment.r) {
            if (homeChoicenessFragment.p == null) {
                homeChoicenessFragment.p = new p(homeChoicenessFragment);
            }
            homeChoicenessFragment.o = com.xunlei.downloadprovider.member.b.b.a(homeChoicenessFragment.p);
            homeChoicenessFragment.o.c(AgooConstants.ACK_FLAG_NULL);
        }
    }

    static /* synthetic */ void p(HomeChoicenessFragment homeChoicenessFragment) {
        if (homeChoicenessFragment.o == null) {
            homeChoicenessFragment.o = com.xunlei.downloadprovider.member.b.b.a(homeChoicenessFragment.p);
        }
        com.xunlei.downloadprovider.member.b.c b = homeChoicenessFragment.o.b(AgooConstants.ACK_FLAG_NULL);
        if (!(b.a() || homeChoicenessFragment.z == null)) {
            homeChoicenessFragment.z.a();
        }
        if (b != null && b.a()) {
            if (homeChoicenessFragment.z != null) {
                homeChoicenessFragment.z.a();
            }
            homeChoicenessFragment.z = new ChoicenessVipRenewalRemindItemView(homeChoicenessFragment.getActivity(), b);
            homeChoicenessFragment.z.setListView(homeChoicenessFragment.g);
            homeChoicenessFragment.z.setmFragment(homeChoicenessFragment);
            homeChoicenessFragment.d = true;
            ((ListView) homeChoicenessFragment.g.getRefreshableView()).addHeaderView(homeChoicenessFragment.z);
            if (homeChoicenessFragment.a != null) {
                ((ListView) homeChoicenessFragment.g.getRefreshableView()).setAdapter(homeChoicenessFragment.a);
            }
        }
    }
}
