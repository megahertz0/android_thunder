package com.xunlei.downloadprovider.download.center;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.AppBarLayout.a;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import com.xunlei.common.accelerator.bean.KnParams;
import com.xunlei.common.accelerator.bean.XLAccelBandInfo;
import com.xunlei.downloadprovider.ad.common.d.b.b;
import com.xunlei.downloadprovider.ad.recommend.RecommendADConst.RecommendSSPAdMapping;
import com.xunlei.downloadprovider.discovery.kuainiao.e;
import com.xunlei.downloadprovider.download.a.o;
import com.xunlei.downloadprovider.download.center.widget.DownloadBriefInfoHeaderView;
import com.xunlei.downloadprovider.download.center.widget.DownloadBriefInfoHeaderView.StatusInfo;
import com.xunlei.downloadprovider.download.center.widget.DownloadBriefInfoHeaderView.StatusInfo.TasksStatus;
import com.xunlei.downloadprovider.download.center.widget.DownloadCenterBottomView;
import com.xunlei.downloadprovider.download.center.widget.DownloadCenterSelectFileTitleView;
import com.xunlei.downloadprovider.download.center.widget.DownloadCenterTabLayout;
import com.xunlei.downloadprovider.download.center.widget.DownloadCenterViewPager;
import com.xunlei.downloadprovider.download.center.widget.DownloadStorageView;
import com.xunlei.downloadprovider.download.center.widget.DownloadTitleBarView;
import com.xunlei.downloadprovider.download.center.widget.f;
import com.xunlei.downloadprovider.download.center.widget.l;
import com.xunlei.downloadprovider.download.taskDetail.DownloadCenterDetailFragment;
import com.xunlei.downloadprovider.download.tasklist.TaskListPageFragment;
import com.xunlei.downloadprovider.download.tasklist.TaskListPageFragment$a;
import com.xunlei.downloadprovider.download.tasklist.a.h;
import com.xunlei.downloadprovider.download.tasklist.list.a.a.m;
import com.xunlei.downloadprovider.download.tasklist.list.a.a.n;
import com.xunlei.downloadprovider.download.tasklist.list.a.a.u;
import com.xunlei.downloadprovider.download.tasklist.list.a.p;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.d;
import com.xunlei.downloadprovider.member.login.LoginHelper.g;
import com.xunlei.downloadprovider.service.downloads.task.k;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;
import org.android.spdy.SpdyAgent;

public class DownloadCenterActivityFragment extends Fragment implements a, e.a, TaskListPageFragment$a, d, g {
    @Deprecated
    public static boolean a;
    private static boolean y;
    private Runnable A;
    private int B;
    private boolean C;
    private int D;
    private a E;
    DownloadTitleBarView b;
    boolean c;
    final com.xunlei.downloadprovider.download.a.a d;
    private DownloadCenterSelectFileTitleView e;
    private DownloadCenterBottomView f;
    private f g;
    private l h;
    private AppBarLayout i;
    private CoordinatorLayout j;
    private DownloadCenterTabLayout k;
    private DownloadCenterViewPager l;
    private b m;
    private o n;
    private k o;
    private final c p;
    private DownloadStorageView q;
    private boolean r;
    private boolean s;
    private DownloadBriefInfoHeaderView t;
    private Handler u;
    private FrameLayout v;
    private FrameLayout w;
    private int x;
    private final int z;

    static /* synthetic */ void f(DownloadCenterActivityFragment downloadCenterActivityFragment) {
        f fVar;
        boolean f;
        f fVar2;
        if (downloadCenterActivityFragment.g == null) {
            downloadCenterActivityFragment.g = new f(downloadCenterActivityFragment.getActivity());
            int a = com.xunlei.downloadprovider.a.g.a(downloadCenterActivityFragment.getActivity(), 78.0f);
            int a2 = com.xunlei.downloadprovider.a.g.a(downloadCenterActivityFragment.getActivity(), 2.0f);
            f fVar3 = downloadCenterActivityFragment.g;
            a = -a;
            fVar3.f = downloadCenterActivityFragment.b.getRightImageView2();
            fVar3.g = a;
            fVar3.h = a2;
            fVar = downloadCenterActivityFragment.g;
            fVar.d.setOnClickListener(new o(downloadCenterActivityFragment));
            fVar = downloadCenterActivityFragment.g;
            fVar.c.setOnClickListener(new p(downloadCenterActivityFragment));
            fVar = downloadCenterActivityFragment.g;
            fVar.a.setOnClickListener(new q(downloadCenterActivityFragment));
            fVar = downloadCenterActivityFragment.g;
            fVar.b.setOnClickListener(new s(downloadCenterActivityFragment));
            downloadCenterActivityFragment.g.setOnDismissListener(new t(downloadCenterActivityFragment));
        }
        LayoutParams attributes = downloadCenterActivityFragment.getActivity().getWindow().getAttributes();
        attributes.alpha = 0.6f;
        downloadCenterActivityFragment.getActivity().getWindow().setAttributes(attributes);
        if (!(downloadCenterActivityFragment.m == null || downloadCenterActivityFragment.m.a() == null)) {
            TaskListPageFragment a3 = downloadCenterActivityFragment.m.a();
            if (a3.d != null) {
                f = a3.d.f();
            } else {
                f = false;
            }
            if (f) {
                f = true;
                fVar2 = downloadCenterActivityFragment.g;
                fVar2.d.setEnabled(f);
                if (f) {
                    fVar2.e.setVisibility(0);
                    fVar2.d.setVisibility(XZBDevice.Wait);
                } else {
                    fVar2.d.setVisibility(0);
                    fVar2.e.setVisibility(XZBDevice.Wait);
                }
                fVar = downloadCenterActivityFragment.g;
                if (!fVar.isShowing() && fVar.f != null) {
                    fVar.showAsDropDown(fVar.f, fVar.g, fVar.h);
                    return;
                }
            }
        }
        f = false;
        fVar2 = downloadCenterActivityFragment.g;
        fVar2.d.setEnabled(f);
        if (f) {
            fVar2.e.setVisibility(0);
            fVar2.d.setVisibility(XZBDevice.Wait);
        } else {
            fVar2.d.setVisibility(0);
            fVar2.e.setVisibility(XZBDevice.Wait);
        }
        fVar = downloadCenterActivityFragment.g;
        if (!fVar.isShowing()) {
        }
    }

    static /* synthetic */ void j(DownloadCenterActivityFragment downloadCenterActivityFragment) {
        downloadCenterActivityFragment.c = true;
        downloadCenterActivityFragment.a(false);
        downloadCenterActivityFragment.k.setTabLayoutEnable(false);
        downloadCenterActivityFragment.l.setCanScroll(false);
        int a = com.xunlei.downloadprovider.a.g.a(downloadCenterActivityFragment.getActivity(), 74.0f) - Math.abs(downloadCenterActivityFragment.x);
        TaskListPageFragment a2 = downloadCenterActivityFragment.m.a();
        if (a2.d != null) {
            a2.d.l = a;
        }
        downloadCenterActivityFragment.m.a(true);
        downloadCenterActivityFragment.e.b(true);
        downloadCenterActivityFragment.e.setTitle(downloadCenterActivityFragment.getActivity().getResources().getString(2131231313));
        downloadCenterActivityFragment.f.b();
        downloadCenterActivityFragment.f.a();
    }

    static {
        y = false;
    }

    public DownloadCenterActivityFragment() {
        this.p = new c(this);
        this.r = false;
        this.s = false;
        this.u = new Handler();
        this.x = 0;
        this.z = 1000;
        this.A = new e(this);
        this.d = new com.xunlei.downloadprovider.download.a.a();
        this.B = 0;
        this.C = false;
        this.E = new a(this, (byte) 0);
    }

    public final void a(Runnable runnable, long j) {
        this.u.postDelayed(runnable, j);
    }

    public void onCreate(Bundle bundle) {
        int i = 0;
        super.onCreate(bundle);
        this.d.a = getActivity();
        this.n = new u(this);
        if (this.d != null) {
            com.xunlei.downloadprovider.download.a.a.a(this.n);
        }
        this.o = new y(this);
        com.xunlei.downloadprovider.service.downloads.task.d a = com.xunlei.downloadprovider.service.downloads.task.d.a();
        a.a.registerObserver(this.o);
        h.a().a(0);
        h.a().d();
        getLoaderManager().initLoader(0, null, this.p.a());
        this.p.d = true;
        this.p.b = true;
        this.p.b();
        com.xunlei.downloadprovider.download.tasklist.list.a.a.f a2 = com.xunlei.downloadprovider.download.tasklist.list.a.a.f.a();
        if (!a2.f) {
            a2.f = true;
            b.a().a(p.a, new com.xunlei.downloadprovider.download.tasklist.list.a.a.g(a2));
        }
        n nVar = new n();
        n.a(getActivity(), 1, -1).a(u.a());
        if (com.xunlei.downloadprovider.ad.recommend.view.b.i()) {
            com.xunlei.downloadprovider.ad.recommend.a.k a3 = com.xunlei.downloadprovider.ad.recommend.a.k.a();
            int[] iArr = new int[]{0, 1, 2};
            while (i < 3) {
                int i2 = iArr[i];
                a3.a(i2, new com.xunlei.downloadprovider.ad.recommend.a.l(a3, i2), "show", XZBDevice.Delete);
                i++;
            }
            com.xunlei.downloadprovider.ad.recommend.a.d a4 = com.xunlei.downloadprovider.ad.recommend.a.d.a();
            List arrayList = new ArrayList(9);
            arrayList.addAll(RecommendSSPAdMapping.getAllPositionIds());
            b.a().a(arrayList, new com.xunlei.downloadprovider.ad.recommend.a.e(a4));
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2130968767, viewGroup, false);
        this.b = (DownloadTitleBarView) inflate.findViewById(2131755988);
        this.b.setLeftImageViewClickListener(new af(this));
        this.b.setRightImageView1ClickListener(new ag(this));
        this.b.setRightImageView2ClickListener(new ah(this));
        this.b.setTouchListener(new ai(this));
        this.e = (DownloadCenterSelectFileTitleView) inflate.findViewById(2131755989);
        this.e.setCancelListener(new f(this));
        this.e.setSelectAllListener(new g(this));
        this.f = (DownloadCenterBottomView) inflate.findViewById(2131755999);
        this.f.setStoreTasksToXiaZaiBaoListener(new h(this));
        this.f.setDeleteTasksListener(new i(this));
        this.f.setPauseTasksListener(new l(this));
        this.f.setStartTasksListener(new m(this));
        this.q = (DownloadStorageView) inflate.findViewById(2131755998);
        this.t = (DownloadBriefInfoHeaderView) inflate.findViewById(2131755994);
        this.t.setActionListener(new ad(this));
        DownloadBriefInfoHeaderView downloadBriefInfoHeaderView = this.t;
        LoginHelper.a();
        boolean c = LoginHelper.c();
        boolean f = LoginHelper.a().f();
        downloadBriefInfoHeaderView.getStatusInfo().a = c;
        downloadBriefInfoHeaderView.getStatusInfo().b = f;
        this.k = (DownloadCenterTabLayout) inflate.findViewById(2131755996);
        this.l = (DownloadCenterViewPager) inflate.findViewById(2131755997);
        this.l.setOffscreenPageLimit(XZBDevice.DOWNLOAD_LIST_FAILED);
        this.l.setPageMargin(getResources().getDimensionPixelOffset(2131362191));
        this.m = new b(this, getFragmentManager());
        this.l.setAdapter(this.m);
        this.k.setupWithViewPager(this.l);
        c(this.l.getCurrentItem());
        this.l.addOnPageChangeListener(new ac(this));
        this.i = (AppBarLayout) inflate.findViewById(2131755993);
        this.i.a((a) this);
        g();
        this.j = (CoordinatorLayout) inflate.findViewById(2131755992);
        this.w = (FrameLayout) inflate.findViewById(2131755995);
        this.v = (FrameLayout) inflate.findViewById(2131755990);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        getActivity().registerReceiver(this.E, intentFilter);
        e.a().a(this);
        e.a();
        e.c();
        if (!y) {
            a(new r(this), 1000);
        }
        return inflate;
    }

    public void onResume() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onResume();
        Bundle arguments = getArguments();
        long j = arguments.getLong(com.umeng.message.proguard.k.l, -1);
        arguments.putLong(com.umeng.message.proguard.k.l, -1);
        boolean z = arguments.getBoolean("extra_key_should_open_detailpage", false);
        if (j >= 0) {
            this.l.setCurrentItem(0);
            this.m.a(j, z);
        }
        if (this.u != null) {
            this.u.removeCallbacks(this.A);
            this.u.postDelayed(this.A, 1000);
        }
        d();
        h.a().b(0);
        h.a().b(2000);
        a(getActivity());
        f();
        g();
        if (this.p.d) {
            this.p.d = false;
        } else {
            this.p.b = true;
            getLoaderManager().restartLoader(0, null, this.p.a());
        }
        LoginHelper.a().a((d) this);
        LoginHelper.a().a((g) this);
        new StringBuilder("onResume : ").append(SystemClock.elapsedRealtime() - elapsedRealtime).append("ms");
    }

    public void onDestroyView() {
        super.onDestroyView();
        h();
    }

    private void d() {
        if (this.q != null) {
            DownloadStorageView downloadStorageView = this.q;
            if (!downloadStorageView.isInEditMode()) {
                try {
                    if (!(downloadStorageView.a == null || downloadStorageView.a.isCancelled())) {
                        downloadStorageView.a.cancel(false);
                    }
                    downloadStorageView.a = new com.xunlei.downloadprovider.download.center.widget.u(downloadStorageView);
                    downloadStorageView.a.execute(new Long[]{Long.valueOf(0)});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void e() {
        boolean z = false;
        Object obj = 1;
        if (this.t != null) {
            StatusInfo statusInfo = this.t.getStatusInfo();
            if (statusInfo != null) {
                boolean z2;
                int i;
                TasksStatus tasksStatus;
                boolean z3;
                h a = h.a();
                if (!a.e.b || ((Boolean) a.e.i.a()).booleanValue()) {
                    z2 = a.e.b;
                } else {
                    z2 = false;
                }
                boolean b = h.a().b();
                h a2 = h.a();
                boolean z4 = (!a2.e.d || ((Boolean) a2.e.i.a()).booleanValue()) ? a2.e.d : false;
                if (z4 && this.s) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (statusInfo.d != z2) {
                    statusInfo.d = z2;
                    i = 1;
                } else {
                    z2 = false;
                }
                if (this.s) {
                    statusInfo.f = this.s;
                }
                if (statusInfo.e != z4) {
                    statusInfo.e = z4;
                    i = 1;
                }
                com.xunlei.downloadprovider.service.downloads.task.d.a();
                com.xunlei.downloadprovider.service.downloads.task.info.b m = com.xunlei.downloadprovider.service.downloads.task.d.m();
                if (m.c > 0) {
                    tasksStatus = null;
                } else if (m.d > 0 || m.e > 0) {
                    tasksStatus = TasksStatus.TasksPaused;
                    if (m.d <= 0) {
                        tasksStatus = TasksStatus.TasksFailed;
                    }
                } else if (m.b > 0) {
                    tasksStatus = TasksStatus.TasksFinished;
                } else {
                    tasksStatus = TasksStatus.NoTasks;
                }
                if (statusInfo.l != tasksStatus) {
                    statusInfo.l = tasksStatus;
                    i = 1;
                }
                if (statusInfo.h != b) {
                    statusInfo.h = b;
                    i = 1;
                }
                LoginHelper.a();
                b = LoginHelper.c();
                z4 = b && LoginHelper.a().f();
                if (b) {
                    if ((LoginHelper.a().g() || LoginHelper.a().i()) && z4 && !LoginHelper.a().l()) {
                        z = true;
                    }
                }
                if (statusInfo.a != b) {
                    statusInfo.a = b;
                    i = 1;
                }
                if (statusInfo.b != z4) {
                    statusInfo.b = z4;
                    i = 1;
                }
                if (statusInfo.c != z) {
                    statusInfo.c = z;
                } else {
                    z3 = z2;
                }
                if (z3) {
                    this.t.a();
                }
            }
        }
    }

    public void onDestroy() {
        if (this.u != null) {
            this.u.removeCallbacks(this.A);
        }
        if (this.o != null) {
            com.xunlei.downloadprovider.service.downloads.task.d a = com.xunlei.downloadprovider.service.downloads.task.d.a();
            a.a.unregisterObserver(this.o);
            this.o = null;
        }
        com.xunlei.downloadprovider.frame.advertisement.b.a a2 = com.xunlei.downloadprovider.frame.advertisement.b.a.a();
        a2.d = null;
        a2.e = null;
        a2.f = null;
        a2.g = null;
        a2.h = null;
        this.d.a = null;
        com.xunlei.downloadprovider.ad.recommend.a.k.b();
        com.xunlei.downloadprovider.ad.recommend.a.d.a = null;
        com.xunlei.downloadprovider.download.tasklist.list.a.a.f.a();
        com.xunlei.downloadprovider.download.tasklist.list.a.a.f.a = null;
        com.xunlei.downloadprovider.download.tasklist.list.a.b.a.a();
        com.xunlei.downloadprovider.download.tasklist.list.a.b.a.j = null;
        m.a();
        m.a = null;
        u.a();
        u.a = null;
        com.xunlei.downloadprovider.download.tasklist.list.d.a.a();
        com.xunlei.downloadprovider.download.tasklist.list.d.a.a = null;
        if (com.xunlei.downloadprovider.download.tasklist.list.f.a.i == null) {
            com.xunlei.downloadprovider.download.tasklist.list.f.a.i = new com.xunlei.downloadprovider.download.tasklist.list.f.a();
        }
        com.xunlei.downloadprovider.download.tasklist.list.f.a aVar = com.xunlei.downloadprovider.download.tasklist.list.f.a.i;
        com.xunlei.downloadprovider.download.tasklist.list.f.a.i = null;
        if (aVar.j != null) {
            aVar.j.b();
        }
        aVar.j = null;
        aVar.d = null;
        if (aVar.c != null) {
            aVar.c.a(XZBDevice.Wait);
        }
        if (aVar.b != null) {
            aVar.b.s = false;
        }
        getLoaderManager().destroyLoader(0);
        h();
        LoginHelper.a().b((d) this);
        LoginHelper.a().b((g) this);
        super.onDestroy();
    }

    public void onDetach() {
        h();
        super.onDetach();
    }

    private static void c(int i) {
        com.xunlei.downloadprovider.service.downloads.task.d.a();
        com.xunlei.downloadprovider.service.downloads.task.info.b m = com.xunlei.downloadprovider.service.downloads.task.d.m();
        if (i == 0) {
            com.xunlei.downloadprovider.download.report.a.a("total", m.a);
        } else if (i == 1) {
            com.xunlei.downloadprovider.download.report.a.a("downloading", m.a());
        } else if (i == 2) {
            com.xunlei.downloadprovider.download.report.a.a("finish", m.b);
        }
    }

    private void a(boolean z) {
        FrameLayout.LayoutParams layoutParams;
        if (z) {
            if (this.i.getVisibility() != 0) {
                this.i.setVisibility(0);
                ((ViewGroup) this.k.getParent()).removeView(this.k);
                this.w.addView(this.k);
                this.v.setVisibility(XZBDevice.Wait);
                layoutParams = (FrameLayout.LayoutParams) this.j.getLayoutParams();
                layoutParams.topMargin = 0;
                this.j.setLayoutParams(layoutParams);
            }
        } else if (this.i.getVisibility() != 8) {
            ((ViewGroup) this.k.getParent()).removeView(this.k);
            this.v.addView(this.k);
            if (!this.c) {
                this.v.setVisibility(0);
            }
            if (this.i.getVisibility() == 0) {
                layoutParams = (FrameLayout.LayoutParams) this.j.getLayoutParams();
                layoutParams.topMargin = -(this.k.getHeight() + (this.t.getHeight() + this.x));
                this.j.setLayoutParams(layoutParams);
            }
            this.i.setVisibility(XZBDevice.Wait);
        }
    }

    public final void a(AppBarLayout appBarLayout, int i) {
        this.x = i;
        if (Math.abs(i) > this.t.getHeight() - 18) {
            b(true);
            if (this.D != i) {
                c(false);
                this.D = i;
            }
            if (!this.c && this.m.c()) {
                this.q.setVisibility(XZBDevice.Wait);
            }
            com.xunlei.downloadprovider.download.tasklist.list.a.n.a().a(true);
        } else {
            com.xunlei.downloadprovider.download.tasklist.list.a.n.a().a(false);
            b(false);
            this.q.setVisibility(0);
        }
        d();
    }

    private void b(boolean z) {
        if (z) {
            this.C = true;
            if (this.b.a(0, true)) {
                c(false);
                return;
            }
            return;
        }
        if (this.b.a((int) XZBDevice.DOWNLOAD_LIST_ALL, true)) {
            c(false);
        }
        this.C = false;
    }

    private void c(boolean z) {
        d(false);
        if (Math.abs(this.x) > this.t.getHeight() - 18 || z) {
            boolean z2;
            TasksStatus tasksStatus = this.t.getStatusInfo().l;
            DownloadBriefInfoHeaderView downloadBriefInfoHeaderView = this.t;
            if (downloadBriefInfoHeaderView.b.getVisibility() == 0 && (downloadBriefInfoHeaderView.c == DownloadBriefInfoHeaderView.b.c || downloadBriefInfoHeaderView.c == DownloadBriefInfoHeaderView.b.d)) {
                z2 = true;
            } else {
                z2 = false;
            }
            DownloadBriefInfoHeaderView downloadBriefInfoHeaderView2 = this.t;
            boolean z3;
            if (downloadBriefInfoHeaderView2.b.getVisibility() == 0 && (downloadBriefInfoHeaderView2.c == DownloadBriefInfoHeaderView.b.f || downloadBriefInfoHeaderView2.c == DownloadBriefInfoHeaderView.b.g)) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (tasksStatus != null) {
                this.b.setTipIcon(-1);
                switch (AnonymousClass_1.a[tasksStatus.ordinal()]) {
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        this.b.setCenterTitle(2131231167);
                        LoginHelper.a();
                        if (!(LoginHelper.c() && LoginHelper.a().f())) {
                            this.b.setTipIcon(2130838084);
                            d(true);
                        }
                        break;
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        this.b.setCenterTitle(2131231170);
                        break;
                    case XZBDevice.DOWNLOAD_LIST_FAILED:
                        this.b.setCenterTitle(2131231168);
                        break;
                    case XZBDevice.DOWNLOAD_LIST_ALL:
                        this.b.setCenterTitle(2131231169);
                        if (z2) {
                            LoginHelper.a();
                            if (!(LoginHelper.c() && LoginHelper.a().f())) {
                                this.b.setTipIcon(2130838084);
                                d(true);
                            }
                        }
                        break;
                }
                this.b.a(0, false);
                return;
            }
            long j = h.a().e.h;
            CharSequence a = com.xunlei.downloadprovider.download.util.a.a(j);
            if (j <= 0) {
                a = "0KB/s";
            }
            this.b.setCenterTitle(r2);
            if (!TextUtils.isEmpty(r2) && r3) {
                this.b.setTipIcon(2130838084);
                d(true);
            } else if (TextUtils.isEmpty(r2) || !z2) {
                LoginHelper a2 = LoginHelper.a();
                h a3 = h.a();
                if (LoginHelper.c() && a2.f() && a3.b()) {
                    this.b.setTipIcon(2130838436);
                } else {
                    this.b.setTipIcon(-1);
                }
            } else {
                LoginHelper.a();
                if (!(LoginHelper.c() && LoginHelper.a().f())) {
                    this.b.setTipIcon(2130838084);
                    d(true);
                }
            }
            if (!TextUtils.isEmpty(r2) && !r3) {
                com.xunlei.downloadprovider.download.tasklist.a.a a4 = h.a().a(com.xunlei.downloadprovider.download.util.g.a().d);
                if (a4 != null && a4.mIsEnteredHighSpeedTrial && a4.mTaskStatus == 2 && a4.mVipChannelStatus != 16 && !com.xunlei.downloadprovider.download.util.n.a(a4, com.xunlei.downloadprovider.download.util.n.g(a4))) {
                    this.b.setTipIcon(2130838436);
                }
            }
        }
    }

    private void d(boolean z) {
        if (z) {
            this.b.setIconContainerListener(new ae(this));
        } else {
            this.b.setIconContainerListener(null);
        }
    }

    private void f() {
        com.xunlei.downloadprovider.service.downloads.task.d.a();
        com.xunlei.downloadprovider.service.downloads.task.info.b m = com.xunlei.downloadprovider.service.downloads.task.d.m();
        if (this.t != null) {
            if (m.c > 0) {
                if (!this.c) {
                    a(true);
                }
                this.t.getStatusInfo().l = null;
                this.t.setDownloadSpeed(h.a().e.h);
                DownloadBriefInfoHeaderView downloadBriefInfoHeaderView = this.t;
                LoginHelper.a();
                downloadBriefInfoHeaderView.a(LoginHelper.c(), LoginHelper.a().f());
                e();
            } else {
                if (m.d > 0 || m.e > 0) {
                    this.t.getStatusInfo().l = TasksStatus.TasksPaused;
                    if (m.d <= 0) {
                        this.t.getStatusInfo().l = TasksStatus.TasksFailed;
                    }
                } else if (m.b > 0) {
                    this.t.getStatusInfo().l = TasksStatus.TasksFinished;
                } else {
                    this.t.getStatusInfo().l = TasksStatus.NoTasks;
                }
                this.t.a();
            }
        }
        this.r = false;
    }

    private void g() {
        int i = com.xunlei.downloadprovider.b.c.a.DATA_TOO_LARGE_ERROR;
        if (this.k != null) {
            int i2;
            com.xunlei.downloadprovider.service.downloads.task.d.a();
            com.xunlei.downloadprovider.service.downloads.task.info.b m = com.xunlei.downloadprovider.service.downloads.task.d.m();
            if (m == null) {
                m = new com.xunlei.downloadprovider.service.downloads.task.info.b();
            }
            TabLayout.d a = this.k.a(0);
            if (a != null) {
                i2 = m.a;
                if (i2 > 0) {
                    if (i2 > 999) {
                        i2 = 999;
                    }
                    a.a(String.format("%s(%d)", new Object[]{getResources().getString(2131231354), Integer.valueOf(i2)}));
                } else {
                    a.a(2131231354);
                }
            }
            a = this.k.a(1);
            if (a != null) {
                i2 = m.a();
                if (i2 > 0) {
                    if (i2 > 999) {
                        i2 = 999;
                    }
                    a.a(String.format("%s(%d)", new Object[]{getResources().getString(2131231356), Integer.valueOf(i2)}));
                } else {
                    a.a(2131231356);
                }
            }
            TabLayout.d a2 = this.k.a(XZBDevice.DOWNLOAD_LIST_RECYCLE);
            if (a2 != null) {
                int i3 = m.b;
                if (i3 > 0) {
                    if (i3 <= 999) {
                        i = i3;
                    }
                    a2.a(String.format("%s(%d)", new Object[]{getResources().getString(2131231355), Integer.valueOf(i)}));
                    return;
                }
                a2.a(2131231355);
            }
        }
    }

    public final void b() {
        a(true);
        this.c = false;
        this.k.setTabLayoutEnable(true);
        this.l.setCanScroll(true);
        this.m.a(false);
        this.e.c(true);
        this.f.a(true);
    }

    public final void a(List<com.xunlei.downloadprovider.download.tasklist.list.b.e> list) {
        boolean d;
        if (list == null || list.size() <= 0) {
            this.f.b();
            this.e.setTitle(getActivity().getResources().getString(2131231313));
        } else {
            this.e.setTitle(getActivity().getResources().getString(2131231314, new Object[]{Integer.valueOf(list.size())}));
            this.f.c();
        }
        DownloadCenterSelectFileTitleView downloadCenterSelectFileTitleView = this.e;
        b bVar = this.m;
        TaskListPageFragment taskListPageFragment = (TaskListPageFragment) bVar.getItem(bVar.g.l.getCurrentItem());
        if (taskListPageFragment.d != null) {
            d = taskListPageFragment.d.d();
        } else {
            d = false;
        }
        downloadCenterSelectFileTitleView.a(!d);
    }

    public final void a(int i) {
        if (this.l.getCurrentItem() == i) {
            Object obj = 1;
        } else {
            int i2 = 0;
        }
        if (i2 != 0 && !this.m.c()) {
            this.q.setVisibility(0);
        }
    }

    public final void a(int i, int i2, KnParams knParams) {
    }

    public final void a(int i, XLAccelBandInfo xLAccelBandInfo) {
        if (i == 0 && xLAccelBandInfo != null) {
            this.s = true;
        }
    }

    private void a(Context context) {
        if (this.t != null && isAdded()) {
            StatusInfo statusInfo = this.t.getStatusInfo();
            if (com.xunlei.xllib.a.b.a(context)) {
                statusInfo.a(true, com.xunlei.xllib.a.b.e(context));
                statusInfo.k = com.xunlei.xllib.a.b.d(context);
            } else {
                statusInfo.a(false, false);
                statusInfo.k = null;
            }
            this.t.a();
        }
    }

    public final void a(int i, int i2, boolean z, Object obj) {
        a(new aa(this), 0);
    }

    public final void a() {
        a(new ab(this), 0);
    }

    private void h() {
        if (getActivity() != null) {
            try {
                getActivity().unregisterReceiver(this.E);
            } catch (Exception e) {
            }
        }
    }

    static /* synthetic */ void b(DownloadCenterActivityFragment downloadCenterActivityFragment) {
        if (downloadCenterActivityFragment.r) {
            downloadCenterActivityFragment.f();
            downloadCenterActivityFragment.g();
        } else {
            downloadCenterActivityFragment.e();
        }
        if (downloadCenterActivityFragment.C) {
            downloadCenterActivityFragment.c(false);
        }
        DownloadCenterActivity downloadCenterActivity = (DownloadCenterActivity) downloadCenterActivityFragment.getActivity();
        if (downloadCenterActivity.b != null) {
            DownloadCenterDetailFragment downloadCenterDetailFragment = downloadCenterActivity.b;
            if (downloadCenterDetailFragment.p != null && downloadCenterDetailFragment.isVisible()) {
                downloadCenterDetailFragment.p.e();
            }
        }
    }

    static /* synthetic */ void e(DownloadCenterActivityFragment downloadCenterActivityFragment) {
        if (downloadCenterActivityFragment.h == null) {
            downloadCenterActivityFragment.h = new l(downloadCenterActivityFragment.getActivity());
        }
        downloadCenterActivityFragment.h.show();
    }
}
