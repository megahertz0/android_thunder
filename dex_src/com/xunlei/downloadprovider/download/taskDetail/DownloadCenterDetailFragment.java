package com.xunlei.downloadprovider.download.taskDetail;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.commonview.dialog.g;
import com.xunlei.downloadprovider.commonview.dialog.h;
import com.xunlei.downloadprovider.download.center.widget.DownloadCenterBottomView;
import com.xunlei.downloadprovider.download.center.widget.DownloadCenterSelectFileTitleView;
import com.xunlei.downloadprovider.download.center.widget.DownloadCenterTabLayout;
import com.xunlei.downloadprovider.download.center.widget.DownloadCenterViewPager;
import com.xunlei.downloadprovider.download.center.widget.af;
import com.xunlei.downloadprovider.download.taskDetail.bt.BtTaskItemFileInfo;
import com.xunlei.downloadprovider.download.util.m;
import com.xunlei.downloadprovider.download.util.n;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.service.downloads.task.b.c;
import com.xunlei.downloadprovider.service.downloads.task.info.TaskRunningInfo;
import com.xunlei.downloadprovidershare.ba;
import com.xunlei.downloadprovidershare.data.ShareBean;
import com.xunlei.downloadprovidershare.data.ShareBean.OperationType;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xllib.b.d;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DownloadCenterDetailFragment extends Fragment implements a, com.xunlei.downloadprovidershare.d.a {
    public static int b;
    public static int c;
    public static int d;
    private ImageView A;
    private ImageView B;
    private com.xunlei.downloadprovider.download.a.a C;
    private Animation D;
    private Animation E;
    private int F;
    String a;
    DownloadCenterSelectFileTitleView e;
    h f;
    DownloadCenterBottomView g;
    public boolean h;
    TextView i;
    View j;
    public com.xunlei.downloadprovider.download.tasklist.a.a k;
    public c l;
    TaskDetailDragView m;
    public DownloadCenterTabLayout n;
    public DownloadCenterViewPager o;
    public b p;
    Animation q;
    Animation r;
    public boolean s;
    public boolean t;
    com.xunlei.c.a.a.a u;
    public com.xunlei.c.a.a.b v;
    private ao w;
    private g x;
    private af y;
    private ImageView z;

    public static interface a {
        void a();
    }

    private class b extends FragmentPagerAdapter {
        protected DownloadTaskDetailNormalInfoFragment a;
        protected DownloadTaskDetailBtListFragment b;

        public final List<BtTaskItemFileInfo> a() {
            return this.b.a();
        }

        public final List<BtTaskItemFileInfo> b() {
            DownloadTaskDetailBtListFragment downloadTaskDetailBtListFragment = this.b;
            List arrayList = new ArrayList();
            Iterator it = downloadTaskDetailBtListFragment.e.iterator();
            while (it.hasNext()) {
                BtTaskItemFileInfo btTaskItemFileInfo = (BtTaskItemFileInfo) it.next();
                if (!downloadTaskDetailBtListFragment.a(btTaskItemFileInfo) && !btTaskItemFileInfo.isSelected) {
                    arrayList.add(btTaskItemFileInfo);
                }
            }
            return arrayList;
        }

        public final List<BtTaskItemFileInfo> c() {
            return this.b.b();
        }

        public final void a(boolean z) {
            if (this.b != null) {
                DownloadTaskDetailBtListFragment downloadTaskDetailBtListFragment = this.b;
                if (!d.a(downloadTaskDetailBtListFragment.e)) {
                    Iterator it = downloadTaskDetailBtListFragment.e.iterator();
                    while (it.hasNext()) {
                        BtTaskItemFileInfo btTaskItemFileInfo = (BtTaskItemFileInfo) it.next();
                        if (!downloadTaskDetailBtListFragment.a(btTaskItemFileInfo)) {
                            btTaskItemFileInfo.isSelected = z;
                        }
                    }
                    downloadTaskDetailBtListFragment.c();
                    if (downloadTaskDetailBtListFragment.b == null) {
                        return;
                    }
                    if (z) {
                        downloadTaskDetailBtListFragment.b.a(downloadTaskDetailBtListFragment.b());
                    } else {
                        downloadTaskDetailBtListFragment.b.a(new ArrayList());
                    }
                }
            }
        }

        public final boolean d() {
            DownloadTaskDetailBtListFragment downloadTaskDetailBtListFragment = this.b;
            Iterator it = downloadTaskDetailBtListFragment.e.iterator();
            while (it.hasNext()) {
                BtTaskItemFileInfo btTaskItemFileInfo = (BtTaskItemFileInfo) it.next();
                if (!downloadTaskDetailBtListFragment.a(btTaskItemFileInfo) && !btTaskItemFileInfo.isSelected) {
                    return false;
                }
            }
            return true;
        }

        public final void e() {
            if (this.a != null && DownloadCenterDetailFragment.this.o.getCurrentItem() == 1) {
                DownloadTaskDetailNormalInfoFragment downloadTaskDetailNormalInfoFragment = this.a;
                if (!(downloadTaskDetailNormalInfoFragment.g == null || downloadTaskDetailNormalInfoFragment.h == null)) {
                    downloadTaskDetailNormalInfoFragment.h.notifyDataSetChanged();
                }
            }
            if (this.b != null && DownloadCenterDetailFragment.this.o.getCurrentItem() == 0) {
                DownloadTaskDetailBtListFragment downloadTaskDetailBtListFragment = this.b;
                if (DownloadCenterDetailFragment.this != null) {
                    DownloadCenterDetailFragment.this.b(downloadTaskDetailBtListFragment.d);
                }
            }
        }

        public final void b(boolean z) {
            if (this.b != null) {
                DownloadTaskDetailBtListFragment downloadTaskDetailBtListFragment = this.b;
                downloadTaskDetailBtListFragment.g = z;
                downloadTaskDetailBtListFragment.c();
            }
        }

        public final void f() {
            if (this.b != null) {
                DownloadTaskDetailBtListFragment downloadTaskDetailBtListFragment = this.b;
                downloadTaskDetailBtListFragment.getLoaderManager().destroyLoader(1);
                downloadTaskDetailBtListFragment.f = false;
                downloadTaskDetailBtListFragment.d = null;
                if (downloadTaskDetailBtListFragment.e != null) {
                    downloadTaskDetailBtListFragment.e.clear();
                    downloadTaskDetailBtListFragment.c();
                }
            }
            if (this.a != null) {
                this.a.a.setSelection(0);
            }
        }

        public final void g() {
            String str = DownloadCenterDetailFragment.this.a;
            new StringBuilder("mViewPager.getCurrentItem(): ").append(DownloadCenterDetailFragment.this.o.getCurrentItem());
            if (this.a != null && DownloadCenterDetailFragment.this.o.getCurrentItem() == 1) {
                DownloadTaskDetailNormalInfoFragment downloadTaskDetailNormalInfoFragment = this.a;
                downloadTaskDetailNormalInfoFragment.j = true;
                downloadTaskDetailNormalInfoFragment.a();
                if (DownloadTaskDetailNormalInfoFragment.b()) {
                    downloadTaskDetailNormalInfoFragment.i = System.currentTimeMillis();
                    com.xunlei.downloadprovider.download.taskDetail.a.b.a().a(1, new am(downloadTaskDetailNormalInfoFragment, downloadTaskDetailNormalInfoFragment.i));
                }
            }
        }

        public final void h() {
            if (this.a != null && DownloadCenterDetailFragment.this.o.getCurrentItem() == 1) {
                DownloadTaskDetailNormalInfoFragment downloadTaskDetailNormalInfoFragment = this.a;
                downloadTaskDetailNormalInfoFragment.j = true;
                downloadTaskDetailNormalInfoFragment.a();
            }
        }

        public final void a(com.xunlei.downloadprovider.download.tasklist.a.a aVar, c cVar) {
            if (this.b != null && n.c((TaskRunningInfo) aVar)) {
                this.b.h = cVar;
                this.b.a(aVar);
            } else if (this.a != null) {
                this.a.f = cVar;
                this.a.a(aVar);
            }
        }

        public b(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public final Fragment getItem(int i) {
            if (i == 0) {
                if (this.b == null) {
                    this.b = new DownloadTaskDetailBtListFragment();
                    this.b.a = DownloadCenterDetailFragment.this.C;
                    this.b.h = DownloadCenterDetailFragment.this.l;
                    this.b.a(DownloadCenterDetailFragment.this.k);
                    this.b.b = DownloadCenterDetailFragment.this;
                }
                return this.b;
            } else if (i != 1) {
                return null;
            } else {
                if (this.a == null) {
                    this.a = new DownloadTaskDetailNormalInfoFragment();
                    this.a.b = DownloadCenterDetailFragment.this.C;
                    this.a.a(DownloadCenterDetailFragment.this.k);
                    this.a.f = DownloadCenterDetailFragment.this.l;
                    this.a.e = DownloadCenterDetailFragment.this;
                }
                return this.a;
            }
        }

        public final int getCount() {
            return XZBDevice.DOWNLOAD_LIST_RECYCLE;
        }

        public final CharSequence getPageTitle(int i) {
            if (i == 0) {
                return DownloadCenterDetailFragment.this.getString(2131231262);
            }
            return i == 1 ? DownloadCenterDetailFragment.this.getString(2131231263) : super.getPageTitle(i);
        }
    }

    public DownloadCenterDetailFragment() {
        this.a = "DownloadCenterDetailFragment";
        this.C = new com.xunlei.downloadprovider.download.a.a();
        this.F = d;
        this.t = false;
        this.u = new s(this);
        this.v = new com.xunlei.c.a.a.b(this.u);
    }

    static {
        b = 0;
        c = 1;
        d = 2;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.C.a = getActivity();
        View inflate = layoutInflater.inflate(2130968735, viewGroup, false);
        this.j = inflate.findViewById(2131755781);
        this.z = (ImageView) inflate.findViewById(2131755783);
        this.z.setOnClickListener(new z(this));
        this.B = (ImageView) inflate.findViewById(2131755784);
        this.B.setOnClickListener(new aa(this));
        this.i = (TextView) inflate.findViewById(2131755780);
        this.i.setOnClickListener(new ab(this));
        this.m = (TaskDetailDragView) inflate.findViewById(2131755785);
        this.m.setIdleY(com.xunlei.downloadprovider.a.g.a(getActivity(), 92.0f));
        this.m.setContentListId(2131757045);
        this.A = (ImageView) inflate.findViewById(R.id.close_btn);
        this.A.setOnClickListener(new c(this));
        this.m.setListener(new d(this));
        this.n = (DownloadCenterTabLayout) inflate.findViewById(2131755787);
        this.o = (DownloadCenterViewPager) inflate.findViewById(2131755788);
        this.p = new b(getFragmentManager());
        this.o.setPageMargin(getResources().getDimensionPixelOffset(2131362191));
        this.o.setAdapter(this.p);
        this.n.setupWithViewPager(this.o);
        this.o.addOnPageChangeListener(new y(this));
        this.o.setCanScroll(false);
        this.e = (DownloadCenterSelectFileTitleView) inflate.findViewById(2131755789);
        this.e.setCancelListener(new o(this));
        this.e.setOnClickListener(new v(this));
        this.e.setSelectAllListener(new w(this));
        this.e.setShowListener(new x(this));
        this.g = (DownloadCenterBottomView) inflate.findViewById(2131755790);
        this.g.setStoreTasksToXiaZaiBaoVisiable(false);
        this.g.setDeleteTasksListener(new b(this));
        return inflate;
    }

    private void f() {
        this.z.setImageResource(2130838076);
        this.B.setImageResource(2130838100);
        this.A.setImageResource(2130838068);
        this.j.setBackgroundColor(Color.parseColor("#ffffff"));
        com.xunlei.downloadprovider.download.report.a.a();
    }

    public final void c() {
        this.z.setImageResource(2130838081);
        this.B.setImageResource(2130838101);
        this.A.setImageResource(2130838073);
        this.j.setBackgroundColor(Color.parseColor("#00000000"));
    }

    final ArrayList<BtTaskItemFileInfo> d() {
        ArrayList<BtTaskItemFileInfo> arrayList = new ArrayList();
        arrayList.addAll(this.p.a());
        return arrayList;
    }

    final void a(int i) {
        this.F = i;
        BaseActivity baseActivity = (BaseActivity) getActivity();
        if (baseActivity != null) {
            if (i == b) {
                baseActivity.setStatusBarBgColr(2131689507);
            } else if (i == c) {
                baseActivity.setStatusBarBgColr(2131689628);
            } else {
                baseActivity.setStatusBarBgColr(2131689627);
            }
        }
    }

    public final void a(boolean z) {
        this.h = z;
        if (z) {
            TaskDetailDragView taskDetailDragView = this.m;
            taskDetailDragView.f = taskDetailDragView.e;
            taskDetailDragView.e = 12;
            taskDetailDragView.g = taskDetailDragView.getScrollY();
            taskDetailDragView.c = true;
            if (taskDetailDragView.d == 0) {
                taskDetailDragView.a.startScroll(0, taskDetailDragView.getScrollY(), 0, (taskDetailDragView.b / 2) + (-taskDetailDragView.g), XLErrorCode.OAUTH_SCOPE_EXIST);
            } else {
                taskDetailDragView.a.startScroll(0, taskDetailDragView.getScrollY(), 0, taskDetailDragView.d + (-taskDetailDragView.g), XLErrorCode.OAUTH_SCOPE_EXIST);
            }
            taskDetailDragView.invalidate();
            this.m.setScrollEnable(false);
            this.e.b(true);
            this.g.a();
            this.n.setTabLayoutEnable(false);
            a(b);
            this.o.setCanScroll(false);
            if (this.o.getCurrentItem() == 1) {
                this.o.setCurrentItem(0);
            }
        } else {
            this.m.setScrollEnable(true);
            this.g.a(true);
            this.e.c(true);
            this.p.a(false);
            a(c);
            this.n.setTabLayoutEnable(true);
            this.o.setCanScroll(false);
            f();
        }
        if (this.p != null) {
            this.p.b(z);
        }
    }

    public static void a(com.xunlei.downloadprovider.download.tasklist.a.a aVar) {
        if (TextUtils.isEmpty(aVar.mCreateOrigin)) {
            com.xunlei.downloadprovider.service.downloads.task.d.a();
            TaskInfo d = com.xunlei.downloadprovider.service.downloads.task.d.d(aVar.getTaskId());
            if (d != null) {
                aVar.mSniffKeyword = d.mSniffKeyword;
                aVar.mWebsiteName = d.mWebsiteName;
                aVar.mCreateOrigin = d.mCreateOrigin;
                aVar.mExtraInfo = d.mExtraInfo;
            }
        }
    }

    public final void b(boolean z) {
        if (z) {
            if (!this.t) {
                this.t = true;
            } else {
                return;
            }
        }
        if (this.h) {
            a(false);
        }
        if (z) {
            ((BaseActivity) getActivity()).animationBarAlpha(false);
            this.D = AnimationUtils.loadAnimation(getActivity(), 2131034127);
            this.D.setDuration(300);
            this.D.setAnimationListener(new n(this));
            this.i.startAnimation(this.D);
            this.E = AnimationUtils.loadAnimation(getActivity(), 2131034144);
            this.E.setDuration(300);
            this.E.setAnimationListener(new p(this));
            this.m.startAnimation(this.E);
        } else {
            a(b);
            getView().setVisibility(XZBDevice.Wait);
            if (this.p != null) {
                this.p.h();
            }
        }
        this.e.c(false);
        this.g.a(false);
        ((a) getActivity()).a();
    }

    public final void a() {
        b(false);
    }

    public final void a(List<BtTaskItemFileInfo> list) {
        b((List) list);
    }

    private void b(List<BtTaskItemFileInfo> list) {
        if (d.a(list)) {
            this.g.b();
            this.e.setTitle("\u8bf7\u9009\u62e9\u6587\u4ef6");
        } else {
            this.g.c();
            this.e.setTitle(new StringBuilder("\u5df2\u9009\u62e9").append(list.size()).append("\u4e2a\u9879\u76ee").toString());
        }
        this.e.a(!this.p.d());
    }

    public final void b() {
        a(true);
        b(d());
    }

    final void e() {
        if (this.f != null) {
            this.f.dismiss();
        }
    }

    public void onShareTargetClicked(SHARE_MEDIA share_media, ShareBean shareBean) {
        String a = ba.a(share_media, shareBean);
        if (shareBean.m == OperationType.None) {
            com.xunlei.downloadprovider.frame.user.a.a().a(String.valueOf(LoginHelper.a().j));
        } else if (shareBean.m == OperationType.Qr) {
            String str = shareBean.a;
            if (this.y != null) {
                this.y.dismiss();
            }
            try {
                getActivity().runOnUiThread(new t(this, new m().a(str)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        com.xunlei.downloadprovider.download.report.a.c(a, shareBean.e);
    }

    public void onShareComplete(int i, SHARE_MEDIA share_media, ShareBean shareBean) {
        com.xunlei.downloadprovider.download.report.a.d(ba.a(share_media, shareBean), com.xunlei.downloadprovidershare.d.a(i), shareBean.e);
    }

    static /* synthetic */ void g(DownloadCenterDetailFragment downloadCenterDetailFragment) {
        if (downloadCenterDetailFragment.w == null) {
            downloadCenterDetailFragment.w = new ao(downloadCenterDetailFragment.getActivity());
            downloadCenterDetailFragment.w.f = new e(downloadCenterDetailFragment);
            downloadCenterDetailFragment.w.d = new f(downloadCenterDetailFragment);
            downloadCenterDetailFragment.w.b = new i(downloadCenterDetailFragment);
            downloadCenterDetailFragment.w.c = new j(downloadCenterDetailFragment);
        }
        ao aoVar = downloadCenterDetailFragment.w;
        TaskRunningInfo taskRunningInfo = downloadCenterDetailFragment.k;
        if (taskRunningInfo != null) {
            aoVar.e = taskRunningInfo;
            aoVar.a(taskRunningInfo);
            if (aoVar.a != null) {
                if (n.c(taskRunningInfo)) {
                    aoVar.a.setText("\u5168\u90e8\u5220\u9664");
                } else {
                    aoVar.a.setText("\u5220\u9664\u4efb\u52a1");
                }
            }
        }
        downloadCenterDetailFragment.w.show();
    }

    static /* synthetic */ void h(DownloadCenterDetailFragment downloadCenterDetailFragment) {
        downloadCenterDetailFragment.getActivity();
        com.xunlei.downloadprovidershare.d.b().a(downloadCenterDetailFragment.getActivity(), au.a(downloadCenterDetailFragment.k, "download_detail_top"), (com.xunlei.downloadprovidershare.d.a) downloadCenterDetailFragment);
    }

    static /* synthetic */ void a(DownloadCenterDetailFragment downloadCenterDetailFragment, String str, String str2, List list) {
        try {
            if (downloadCenterDetailFragment.x == null) {
                downloadCenterDetailFragment.x = new g(downloadCenterDetailFragment.getActivity());
                downloadCenterDetailFragment.x.a(str);
                downloadCenterDetailFragment.x.a(str2);
                downloadCenterDetailFragment.x.setCanceledOnTouchOutside(false);
                downloadCenterDetailFragment.x.setCancelable(false);
                downloadCenterDetailFragment.x.a((long) list.size());
                downloadCenterDetailFragment.x.b(0);
                downloadCenterDetailFragment.x.setOnKeyListener(new r(downloadCenterDetailFragment));
                downloadCenterDetailFragment.x.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static /* synthetic */ void a(DownloadCenterDetailFragment downloadCenterDetailFragment, int i, int i2, List list) {
        int i3;
        for (i3 = 0; i3 < list.size(); i3++) {
            if (((BtTaskItemFileInfo) list.get(i3)).mLocalFileName != null) {
                File file = new File(((BtTaskItemFileInfo) list.get(i3)).mLocalFileName);
                if (file.exists()) {
                    file.delete();
                }
            }
        }
        List b = downloadCenterDetailFragment.p.b();
        long[] jArr = new long[b.size()];
        for (i3 = 0; i3 < b.size(); i3++) {
            jArr[i3] = (long) ((BtTaskItemFileInfo) b.get(i3)).mBTSubIndex;
        }
        if (i == 1) {
            com.xunlei.downloadprovider.service.downloads.kernel.c.b().remove(downloadCenterDetailFragment.k.mTaskId);
            downloadCenterDetailFragment.b(false);
        } else {
            com.xunlei.downloadprovider.service.downloads.kernel.c.b().selectBtSubTask(downloadCenterDetailFragment.k.mTaskId, jArr);
            downloadCenterDetailFragment.a(false);
        }
        if (downloadCenterDetailFragment.x != null) {
            downloadCenterDetailFragment.x.a(new StringBuilder("\u6b63\u5728\u5220\u9664\u4efb\u52a1  ").append(i2).append("/").append(i2).toString());
            downloadCenterDetailFragment.x.a((long) i2);
            downloadCenterDetailFragment.x.b((long) i2);
        }
        Message message = new Message();
        message.what = 1;
        downloadCenterDetailFragment.v.sendMessageDelayed(message, 400);
    }
}
