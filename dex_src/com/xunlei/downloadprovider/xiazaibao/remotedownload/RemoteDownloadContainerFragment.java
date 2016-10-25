package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import com.xunlei.downloadprovider.download.center.widget.DownloadCenterSelectFileTitleView;
import com.xunlei.downloadprovider.download.center.widget.DownloadCenterSelectFileTitleView.b;
import com.xunlei.downloadprovider.download.center.widget.DownloadCenterTabLayout;
import com.xunlei.downloadprovider.xiazaibao.view.LoadingMoreRecyclerView;
import com.xunlei.downloadprovider.xiazaibao.view.XZBDownloadBottomView;
import com.xunlei.downloadprovider.xiazaibao.view.XZBDownloadBriefInfoHeaderView;
import com.xunlei.downloadprovider.xiazaibao.view.XZBDownloadBriefInfoHeaderView.StatusInfo.TasksStatus;
import com.xunlei.downloadprovider.xiazaibao.view.XZBDownloadTitleBar;
import com.xunlei.downloadprovider.xiazaibao.view.XZBDownloadViewPager;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;
import org.android.spdy.SpdyAgent;

public class RemoteDownloadContainerFragment extends Fragment implements android.support.design.widget.AppBarLayout.a {
    private static final String[] y;
    private static final String[] z;
    private com.xunlei.downloadprovider.xiazaibao.remotedownload.RemoteDownloadListFragment.a A;
    DownloadCenterTabLayout a;
    XZBDownloadViewPager b;
    DownloadCenterSelectFileTitleView c;
    XZBDownloadBottomView d;
    boolean e;
    int f;
    a g;
    com.xunlei.downloadprovider.xiazaibao.remotedownload.q.a h;
    private AppBarLayout i;
    private CoordinatorLayout j;
    private XZBDownloadBriefInfoHeaderView k;
    private FrameLayout l;
    private FrameLayout m;
    private XZBDownloadTitleBar n;
    private List<Fragment> o;
    private Activity p;
    private d q;
    private OnClickListener r;
    private int s;
    private boolean t;
    private OnClickListener u;
    private b v;
    private OnClickListener w;
    private OnClickListener x;

    private class a extends FragmentPagerAdapter {
        int a;

        public final void a(boolean z) {
            for (int i = 0; i < RemoteDownloadContainerFragment.this.o.size(); i++) {
                boolean z2;
                RemoteDownloadListFragment remoteDownloadListFragment = (RemoteDownloadListFragment) ((Fragment) RemoteDownloadContainerFragment.this.o.get(i));
                if (i == this.a) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                remoteDownloadListFragment.e = z2;
                remoteDownloadListFragment.g = RemoteDownloadContainerFragment.this.A;
                if (z) {
                    Object obj;
                    if (i == this.a) {
                        int i2 = 1;
                    } else {
                        obj = null;
                    }
                    if (obj != null) {
                        remoteDownloadListFragment.a();
                    }
                }
            }
        }

        public final void a() {
            q qVar = RemoteDownloadContainerFragment.this;
            for (al alVar : qVar.c) {
                alVar.a = false;
            }
            qVar.b();
            qVar.c();
        }

        public a(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public final int getCount() {
            return RemoteDownloadContainerFragment.this.o.size();
        }

        public final long getItemId(int i) {
            return super.getItemId(i);
        }

        public final Fragment getItem(int i) {
            return (Fragment) RemoteDownloadContainerFragment.this.o.get(i);
        }

        public final int getItemPosition(Object obj) {
            return super.getItemPosition(obj);
        }

        public final RemoteDownloadListFragment b() {
            try {
                return (RemoteDownloadListFragment) ((Fragment) RemoteDownloadContainerFragment.this.o.get(this.a));
            } catch (ClassCastException e) {
                return null;
            }
        }

        public final void b(boolean z) {
            RemoteDownloadContainerFragment.this.e = z;
            RemoteDownloadListFragment b = b();
            if (b != null) {
                boolean z2;
                b.d = z;
                q qVar = RemoteDownloadContainerFragment.this;
                qVar.d = z;
                if (!qVar.d) {
                    qVar.c.remove(RemoteDownloadContainerFragment.this);
                } else if (!qVar.c.isEmpty()) {
                    qVar.c.remove(RemoteDownloadContainerFragment.this);
                    qVar.c.add(RemoteDownloadContainerFragment.this);
                    if (qVar.a()) {
                        qVar.c.remove(qVar.a);
                        qVar.c.add(qVar.a);
                    }
                }
                qVar.b();
                LoadingMoreRecyclerView loadingMoreRecyclerView = b.a;
                if (z) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                loadingMoreRecyclerView.setFootEnable(z2);
            }
        }
    }

    public RemoteDownloadContainerFragment() {
        this.f = 0;
        this.r = new h(this);
        this.h = new l(this);
        this.u = new m(this);
        this.v = new n(this);
        this.w = new o(this);
        this.x = new p(this);
        this.A = new j(this);
    }

    public static RemoteDownloadContainerFragment a() {
        Bundle bundle = new Bundle();
        RemoteDownloadContainerFragment remoteDownloadContainerFragment = new RemoteDownloadContainerFragment();
        remoteDownloadContainerFragment.setArguments(bundle);
        return remoteDownloadContainerFragment;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.p = activity;
        if (this.p instanceof RemoteDownloadListActivity) {
            this.q = ((RemoteDownloadListActivity) this.p).c;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.o = new ArrayList(3);
        RemoteDownloadListFragment a = RemoteDownloadListFragment.a(0);
        RemoteDownloadListFragment a2 = RemoteDownloadListFragment.a(1);
        RemoteDownloadListFragment a3 = RemoteDownloadListFragment.a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
        this.o.add(a);
        this.o.add(a2);
        this.o.add(a3);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2130968776, viewGroup, false);
        this.k = (XZBDownloadBriefInfoHeaderView) inflate.findViewById(2131755994);
        this.n = (XZBDownloadTitleBar) inflate.findViewById(2131755988);
        this.a = (DownloadCenterTabLayout) inflate.findViewById(2131755996);
        this.i = (AppBarLayout) inflate.findViewById(2131755993);
        this.i.a((android.support.design.widget.AppBarLayout.a) this);
        this.j = (CoordinatorLayout) inflate.findViewById(2131755992);
        this.m = (FrameLayout) inflate.findViewById(2131755995);
        this.l = (FrameLayout) inflate.findViewById(2131755990);
        this.c = (DownloadCenterSelectFileTitleView) inflate.findViewById(2131755989);
        this.b = (XZBDownloadViewPager) inflate.findViewById(2131755997);
        this.d = (XZBDownloadBottomView) inflate.findViewById(2131755999);
        this.d.setDeleteTasksListener(this.r);
        this.k.getStatusInfo().d = TasksStatus.Init;
        this.k.a();
        this.b.setOffscreenPageLimit(XZBDevice.DOWNLOAD_LIST_FAILED);
        this.b.setPageMargin(getResources().getDimensionPixelOffset(2131362191));
        this.g = new a(getFragmentManager());
        this.b.setAdapter(this.g);
        this.b.addOnPageChangeListener(new k(this));
        this.g.a(false);
        this.a.setupWithViewPager(this.b);
        this.a.a(0).a(y[0]);
        this.a.a(1).a(y[1]);
        this.a.a(XZBDevice.DOWNLOAD_LIST_RECYCLE).a(y[2]);
        ((ImageView) this.n.findViewById(2131755801)).setVisibility(XZBDevice.Wait);
        this.n.setLeftImageViewClickListener(this.w);
        this.n.setRightImageView2ClickListener(this.x);
        this.n.setMainCenterTitleVisibility(true);
        XZBDevice b = this.q.b();
        if (!(b == null || b.getDeviceName() == null)) {
            this.n.setMainCenterText(b.getDeviceName());
        }
        this.c.setCancelListener(this.u);
        this.c.setSelectAllListener(this.v);
        return inflate;
    }

    public final void a(AppBarLayout appBarLayout, int i) {
        this.f = i;
        if (Math.abs(i) > this.k.getHeight() - 18) {
            b(true);
            if (this.s != i) {
                d();
                this.s = i;
                return;
            }
            return;
        }
        b(false);
    }

    private void b(boolean z) {
        if (z) {
            this.t = true;
            if (this.n.a(0)) {
                d();
                return;
            }
            return;
        }
        if (this.n.a(XZBDevice.DOWNLOAD_LIST_ALL)) {
            d();
        }
        this.t = false;
    }

    private void d() {
        if (Math.abs(this.f) > this.k.getHeight() - 18) {
            TasksStatus tasksStatus = this.k.getStatusInfo().d;
            this.k.getStatusInfo();
            if (tasksStatus != null) {
                this.n.a(0);
                this.n.setTipIcon(-1);
                switch (AnonymousClass_1.a[tasksStatus.ordinal()]) {
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        this.n.setCenterTitle("\u4efb\u52a1\u52a0\u8f7d\u4e2d...");
                        this.n.setTipIcon(-1);
                        this.n.a(XZBDevice.Wait);
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        this.n.setCenterTitle(2131231167);
                    case XZBDevice.DOWNLOAD_LIST_FAILED:
                        this.n.setCenterTitle(2131231170);
                    case XZBDevice.DOWNLOAD_LIST_ALL:
                        this.n.setCenterTitle(2131231169);
                    case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                        this.n.setCenterTitle(com.xunlei.downloadprovider.download.util.a.a(this.q.a()));
                        this.n.setTipIcon(-1);
                    case R.styleable.Toolbar_contentInsetEnd:
                        String str = this.k.getStatusInfo().b;
                        if (TextUtils.isEmpty(str)) {
                            str = "\u83b7\u53d6\u4efb\u52a1\u5931\u8d25";
                        }
                        this.n.setCenterTitle(str);
                    default:
                        this.n.setCenterTitle(2131231170);
                }
            }
        }
    }

    static {
        y = new String[]{"\u5168\u90e8", "\u4e0b\u8f7d\u4e2d", "\u5df2\u5b8c\u6210"};
        z = new String[]{"\u5168\u90e8(%d)", "\u4e0b\u8f7d\u4e2d(%d)", "\u5df2\u5b8c\u6210(%d)"};
    }

    public final void b() {
        this.e = false;
        this.c.c(true);
        this.a.setTabLayoutEnable(true);
        this.b.setCanScroll(true);
        a(true);
        this.g.b(false);
        this.g.a();
        XZBDownloadBottomView xZBDownloadBottomView = this.d;
        xZBDownloadBottomView.o = false;
        xZBDownloadBottomView.startAnimation(xZBDownloadBottomView.n);
    }

    final void a(boolean z) {
        LayoutParams layoutParams;
        if (z) {
            if (this.i.getVisibility() != 0) {
                this.i.setVisibility(0);
                ((ViewGroup) this.a.getParent()).removeView(this.a);
                this.m.addView(this.a);
                this.l.setVisibility(XZBDevice.Wait);
                layoutParams = (LayoutParams) this.j.getLayoutParams();
                layoutParams.topMargin = 0;
                this.j.setLayoutParams(layoutParams);
            }
        } else if (this.i.getVisibility() != 8) {
            ((ViewGroup) this.a.getParent()).removeView(this.a);
            this.l.addView(this.a);
            if (!this.e) {
                this.l.setVisibility(0);
            }
            if (this.i.getVisibility() == 0) {
                layoutParams = (LayoutParams) this.j.getLayoutParams();
                layoutParams.topMargin = -(this.a.getHeight() + (this.k.getHeight() + this.f));
                this.j.setLayoutParams(layoutParams);
            }
            this.i.setVisibility(XZBDevice.Wait);
        }
    }
}
