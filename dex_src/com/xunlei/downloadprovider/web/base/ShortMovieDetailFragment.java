package com.xunlei.downloadprovider.web.base;

import android.content.BroadcastReceiver;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.taobao.accs.common.Constants;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.a.b;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.c.a.c;
import com.xunlei.downloadprovider.c.a.f;
import com.xunlei.downloadprovider.c.a.n;
import com.xunlei.downloadprovider.commonview.ErrorView;
import com.xunlei.downloadprovider.commonview.UnifiedLoadingView;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.frame.BaseFragment;
import com.xunlei.downloadprovider.frame.user.PersonalSpaceActivity;
import com.xunlei.downloadprovider.homepage.recommend.ShortTimeVideoListActivity;
import com.xunlei.downloadprovider.homepage.recommend.feed.aa;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.search.ui.search.SearchActivity;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From;
import com.xunlei.downloadprovider.web.base.a.ae;
import com.xunlei.downloadprovider.web.base.a.ae$a;
import com.xunlei.downloadprovider.web.base.model.StateSyncManager;
import com.xunlei.downloadprovider.web.base.model.StateSyncManager.CommentSateInfo;
import com.xunlei.downloadprovider.web.base.model.StateSyncManager.SourceFrom;
import com.xunlei.downloadprovider.web.base.model.d;
import com.xunlei.downloadprovider.web.base.model.g;
import com.xunlei.downloadprovider.web.base.model.h;
import com.xunlei.downloadprovider.web.base.model.t;
import com.xunlei.downloadprovider.web.base.model.u;
import com.xunlei.downloadprovidershare.ba;
import com.xunlei.downloadprovidershare.data.ShareBean;
import com.xunlei.downloadprovidershare.data.ShareBean.OperationType;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.android.spdy.SpdyAgent;

public class ShortMovieDetailFragment extends BaseFragment implements OnClickListener, ae$a, com.xunlei.downloadprovider.web.base.model.d.a, com.xunlei.downloadprovidershare.d.a {
    private String A;
    private String B;
    private int C;
    private UnifiedLoadingView D;
    private int E;
    private int F;
    private ae G;
    private ArrayList<t> H;
    private t I;
    private ImageView J;
    private t K;
    private t L;
    private t M;
    private t N;
    private t O;
    private t P;
    private List<u> Q;
    private LinearLayoutManager R;
    private List<String> S;
    private List<u> T;
    private List<u> U;
    private String V;
    private String W;
    private String X;
    private String Y;
    private ClipboardManager Z;
    d a;
    private t aa;
    private int ab;
    private t ac;
    private boolean ad;
    private BroadcastReceiver ae;
    u b;
    boolean c;
    private ImageView d;
    private RecyclerView e;
    private int f;
    private List<t> g;
    private List<t> h;
    private List<t> i;
    private String j;
    private String k;
    private TextView l;
    private LinearLayout m;
    private Handler n;
    private ErrorView o;
    private String p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private TextView v;
    private a w;
    private d x;
    private boolean y;
    private String z;

    public static interface a {
        void a();

        void a(c cVar);

        void a(u uVar);

        void a(u uVar, boolean z, boolean z2);

        void a(String str, u uVar);

        void a(List<u> list);

        void b();

        void b(u uVar);

        void b(boolean z);

        void c();

        void c(boolean z);
    }

    public ShortMovieDetailFragment() {
        this.f = 0;
        this.j = WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
        this.k = com.umeng.a.d;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.u = 2;
        this.E = 0;
        this.F = 0;
        this.S = new ArrayList(16);
        this.T = new ArrayList(4);
        this.U = new ArrayList(4);
        this.c = true;
        this.ab = 0;
        this.ad = true;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mPageRoot = (ViewGroup) layoutInflater.inflate(2130968773, null);
        this.e = (RecyclerView) findViewById(2131756039);
        this.R = new LinearLayoutManager(this.mActivity);
        this.e.setLayoutManager(this.R);
        this.G = new ae(this.mActivity, this);
        this.e.setAdapter(this.G);
        this.d = (ImageView) findViewById(2131756042);
        this.v = (TextView) findViewById(2131756043);
        this.J = (ImageView) findViewById(2131756044);
        if (From.HOME_PAGE_AD.getText().contentEquals(this.V)) {
            this.J.setVisibility(XZBDevice.Wait);
        } else {
            this.J.setVisibility(0);
        }
        this.m = (LinearLayout) findViewById(2131756040);
        this.l = (TextView) findViewById(2131756041);
        this.o = (ErrorView) findViewById(2131756045);
        this.o.setErrorType(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        this.o.setVisibility(XZBDevice.Wait);
        this.o.a("\u5237\u65b0", new bd(this));
        this.D = (UnifiedLoadingView) findViewById(2131756046);
        this.D.setBackgroundColor(Color.parseColor("#ffffff"));
        this.D.c();
        this.e.addOnScrollListener(new be(this));
        com.xunlei.downloadprovidershare.d.b().c = new bf(this);
        com.xunlei.downloadprovidershare.d.b().b = new bg(this);
        this.d.setOnClickListener(this);
        this.J.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.w = new a(this.mActivity);
        this.w.a(new bh(this));
        this.w.setOnDismissListener(new bi(this));
        this.w.setOnShowListener(new bj(this));
        this.m.setOnClickListener(new ao(this));
        this.x = new d(this.mActivity);
        this.x.a(new ap(this));
        this.x.b(new aq(this));
        this.x.c(new ar(this));
        this.x.d(new as(this));
        this.x.setOnDismissListener(new at(this));
        this.x.setOnShowListener(new au(this));
        this.H = new ArrayList();
        this.I = new t();
        this.I.b = this.b;
        if (this.V.equals(From.HOME_PAGE_AD.getText())) {
            this.I.a = 12;
        } else {
            this.I.a = 0;
        }
        this.K = new t();
        this.K.a = 6;
        com.xunlei.downloadprovider.web.base.model.c cVar = new com.xunlei.downloadprovider.web.base.model.c();
        cVar.a = "\u76f8\u5173\u63a8\u8350";
        cVar.b = com.xunlei.downloadprovider.util.aa.a.b(this.mActivity, "auto_play_next_shortmovie", true);
        this.K.b = cVar;
        this.g = new ArrayList();
        this.L = new t();
        this.L.a = 8;
        this.L.b = "\u67e5\u770b\u66f4\u591a";
        this.aa = new t();
        this.aa.a = 10;
        this.aa.b = "\u70ed\u95e8\u8bc4\u8bba";
        this.i = new ArrayList();
        this.M = new t();
        this.M.a = 1;
        this.M.b = "\u6700\u65b0\u8bc4\u8bba";
        this.h = new ArrayList();
        this.N = new t();
        this.N.a = 11;
        this.N.b = null;
        this.O = new t();
        this.O.a = 4;
        this.O.b = null;
        this.P = new t();
        this.P.a = 5;
        this.P.b = null;
        this.ac = new t();
        this.ac.a = 9;
        this.ac.b = null;
        this.G.a(0, this.I);
        this.G.a(1, this.M);
        this.G.a(XZBDevice.DOWNLOAD_LIST_RECYCLE, this.N);
        ((a) this.mActivity).c(false);
        return this.mPageRoot;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.n = new Handler();
        this.Z = (ClipboardManager) this.mActivity.getSystemService("clipboard");
        Bundle arguments = getArguments();
        this.j = arguments.getString("movie_id");
        this.k = arguments.getString(Impl.COLUMN_GCID);
        this.p = arguments.getString("movie_title");
        this.z = arguments.getString("category_icon");
        this.A = arguments.getString("category_name");
        this.B = arguments.getString("category_poster");
        this.C = arguments.getInt("category_type");
        this.V = arguments.getString("from");
        this.b = new u();
        this.b.a = this.j;
        this.b.g = this.k;
        this.b.b = this.p;
        this.b.l = arguments.getInt("movie_fav_count");
        this.b.k = arguments.getBoolean("movie_has_fav", false);
        this.b.e = arguments.getString("movie_url");
        this.b.h = this.z;
        this.b.i = this.A;
        this.b.j = this.B;
        this.b.n = this.C;
        this.y = arguments.getBoolean("movie_seek_to_comment", false);
        this.a = new d(this.mActivity);
        this.a.e = this;
        this.a.a(this.b);
        this.a.a();
        this.ae = new an(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action_comment_zan_state_changed");
        this.mActivity.registerReceiver(this.ae, intentFilter);
    }

    public void onResume() {
        super.onResume();
        this.c = com.xunlei.downloadprovider.util.aa.a.b(this.mActivity, "auto_play_next_shortmovie", true);
        f();
        if (this.G != null) {
            int i;
            ae aeVar = this.G;
            int itemCount = aeVar.getItemCount();
            for (int i2 = 0; i2 < itemCount; i2++) {
                if (((t) aeVar.a.get(i2)).a == 6) {
                    i = i2;
                    break;
                }
            }
            i = -1;
            if (i >= 0) {
                ae aeVar2 = this.G;
                t tVar = this.K;
                if (!(aeVar2.a == null || tVar == null || i >= aeVar2.a.size())) {
                    aeVar2.a.set(i, tVar);
                    aeVar2.notifyItemChanged(i);
                }
            }
            this.G.notifyDataSetChanged();
        }
        a();
    }

    private void f() {
        if (this.K == null) {
            this.K = new t();
            this.K.a = 6;
        }
        com.xunlei.downloadprovider.web.base.model.c cVar = new com.xunlei.downloadprovider.web.base.model.c();
        cVar.a = "\u76f8\u5173\u63a8\u8350";
        cVar.b = this.c;
        this.K.b = cVar;
    }

    public void onDestroy() {
        this.a.d();
        this.a.e();
        this.mActivity.unregisterReceiver(this.ae);
        a(this.R.j(), this.R.k());
        if (this.S.size() > 0) {
            new StringBuilder("onDestroy report size =>").append(this.S.size());
            bk.a(this.S, this.V, new String[]{this.W, this.X, this.Y, b.q(), com.xunlei.xllib.a.b.d(this.mActivity)});
            this.S.clear();
        }
        super.onDestroy();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (this.y) {
            i();
        }
        g();
    }

    final void a() {
        if (this.o.isShown()) {
            this.o.setVisibility(XZBDevice.Wait);
            ((a) this.mActivity).b(false);
            this.D.a();
        }
        if (this.q == 1) {
            this.q = 0;
            this.a.a(this.j);
        }
        if (this.t == 1) {
            this.t = 0;
            this.a.c();
        }
        if (this.s == 1) {
            this.s = 0;
            this.a.b();
        }
        if (this.r == 1) {
            this.r = 0;
            this.a.b(this.j);
        }
    }

    private void g() {
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.D.a();
        this.q = 0;
        this.a.a(this.j);
        if (this.V.equals(From.HOME_PAGE_AD.getText())) {
            ((a) this.mActivity).c(false);
        } else {
            this.a.b(this.j);
        }
        this.a.c();
        this.a.b();
    }

    private void b(boolean z) {
        this.f = 0;
        this.y = false;
        this.ad = true;
        this.u = 2;
        ae aeVar = this.G;
        if (!(aeVar.a == null || aeVar.a.isEmpty())) {
            aeVar.a.clear();
            aeVar.notifyDataSetChanged();
        }
        if (this.a != null) {
            this.a.d();
        }
        if (z) {
            this.b = new u();
            this.p = null;
            this.I.b = this.b;
            if (this.x != null && this.x.isShowing()) {
                this.x.dismiss();
            }
            if (this.w != null) {
                if (this.w.isShowing()) {
                    this.w.dismiss();
                }
                a aVar = this.w;
                if (aVar.c != null) {
                    aVar.c.clear();
                }
                aVar.b = null;
                aVar.a.setText(com.umeng.a.d);
                aVar.a.setHint("\u5199\u8bc4\u8bba...");
            }
            this.n.post(new ay(this));
        }
        if (this.Q != null) {
            this.Q.clear();
        }
        if (this.g != null) {
            this.g.clear();
        }
        if (this.h != null) {
            this.h.clear();
        }
        if (this.i != null) {
            this.i.clear();
        }
        this.G.a(0, this.I);
        this.G.a(1, this.M);
        this.G.a(XZBDevice.DOWNLOAD_LIST_RECYCLE, this.N);
        ((a) this.mActivity).c(false);
    }

    private void a(int i, int i2) {
        this.U.clear();
        this.U.addAll(this.T);
        this.T.clear();
        while (i <= i2) {
            t tVar;
            ae aeVar = this.G;
            if (i < 0 || i >= aeVar.getItemCount()) {
                tVar = null;
            } else {
                tVar = (t) aeVar.a.get(i);
            }
            if (tVar != null && tVar.a == 7) {
                this.T.add((u) tVar.b);
            }
            i++;
        }
        for (u uVar : this.T) {
            if (!this.U.contains(uVar)) {
                this.S.add(uVar.a);
            }
        }
        if (this.S.size() >= 16) {
            new StringBuilder("report size =>").append(this.S.size());
            bk.a(this.S, this.V, new String[]{this.W, this.X, this.Y, b.q(), com.xunlei.xllib.a.b.d(this.mActivity)});
            this.S.clear();
        }
    }

    public final void b() {
        String trim = this.w.a().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            XLToast.b(this.mActivity, XLToastType.XLTOAST_TYPE_NORMAL, "\u8bf7\u586b\u5199\u8bc4\u8bba\u5185\u5bb9");
        } else if (com.xunlei.xllib.a.b.a(this.mActivity)) {
            this.w.a(false);
            this.a.a(trim, b.k(), this.w.b);
        } else {
            XLToast.b(this.mActivity, XLToastType.XLTOAST_TYPE_ALARM, "\u65e0\u7f51\u7edc\u8fde\u63a5");
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == 2131756042) {
            boolean z;
            id = this.G.b();
            if (id == -1) {
                id = this.G.a();
            }
            if (this.R.j() < id) {
                LinearLayoutManager linearLayoutManager = this.R;
                View a = linearLayoutManager.a(linearLayoutManager.n() - 1, -1, true, false);
                if (a == null) {
                    id = -1;
                } else {
                    id = LinearLayoutManager.a(a);
                }
                if (id != this.G.getItemCount() - 1) {
                    z = true;
                    if (z) {
                        this.R.e(0, 0);
                        bk.a("top");
                    } else {
                        i();
                        bk.a("discuss_area");
                    }
                    this.n.post(new av(this));
                }
            }
            z = false;
            if (z) {
                i();
                bk.a("discuss_area");
            } else {
                this.R.e(0, 0);
                bk.a("top");
            }
            this.n.post(new av(this));
        } else if (id == 2131756044) {
            if (this.b != null) {
                a("detail_shortvideo_foot");
                bk.c("detail_shortvideo_foot");
            }
        } else if (id == 2131756041 && this.b != null) {
            this.w.b("\u5199\u8bc4\u8bba...");
            h();
            this.w.a(null);
            bk.d(this.j, "discuss_bar");
        }
    }

    private void h() {
        if (this.w == null) {
            this.w = new a(this.mActivity);
        }
        if (!this.w.isShowing()) {
            this.w.show();
        }
    }

    public final void a(String str, u uVar) {
        String str2 = null;
        if (!"ok".contentEquals(str)) {
            if (this.mActivity != null) {
                ((a) this.mActivity).a(str, uVar);
            }
            if (this.b != null) {
                this.I.b = this.b;
                this.G.b(this.I);
            }
            if ("reject".contentEquals(str)) {
                this.b = null;
            }
            this.q = 1;
            this.D.b();
        } else if (uVar != null) {
            boolean z;
            this.q = 2;
            if (this.b != null) {
                z = this.b.k;
            } else {
                z = false;
            }
            if (this.b != null) {
                str2 = this.b.b;
            }
            this.b = uVar;
            if (z) {
                this.b.k = true;
            }
            if (!(str2 == null || str2.trim().isEmpty())) {
                this.b.b = str2;
            }
            this.a.a(this.b);
            this.a.a();
            this.A = uVar.i;
            this.z = uVar.h;
            this.B = uVar.j;
            this.C = uVar.n;
            if (this.mActivity != null) {
                ((a) this.mActivity).a(str, uVar);
            }
            this.I.b = this.b;
            this.G.b(this.I);
            ((a) this.mActivity).a(this.b);
            this.D.b();
        }
    }

    public final void a(List<u> list) {
        boolean z = true;
        if (this.g == null) {
            this.g = new ArrayList(8);
        }
        if (this.Q == null) {
            this.Q = new ArrayList(8);
        }
        this.Q.clear();
        if (list != null && list.size() > 0) {
            this.Q.addAll(list);
            this.W = ((u) list.get(0)).p;
            this.X = ((u) list.get(0)).q;
            this.Y = ((u) list.get(0)).t;
            new StringBuilder("sparams=>").append(this.Y);
        }
        this.g.clear();
        if (list != null && list.size() > 0) {
            int min = Math.min(XZBDevice.Wait, list.size());
            for (int i = 0; i < min; i++) {
                u uVar = (u) list.get(i);
                t tVar = new t();
                tVar.a = 7;
                tVar.b = uVar;
                this.g.add(tVar);
            }
        }
        this.f = this.Q.size();
        if (this.Q.size() > 0) {
            this.r = 2;
            this.G.a(1, this.g);
            this.G.a(1, this.K);
            if (this.Q.size() > 8) {
                this.G.a(this.g.size() + 2, this.L);
            } else {
                this.G.c(this.L);
            }
        } else {
            this.r = 1;
            this.G.c(this.L);
            this.G.c(this.K);
            this.G.b(this.g);
        }
        if (this.mActivity != null) {
            ((a) this.mActivity).a(this.Q);
            a aVar = (a) this.mActivity;
            if (!this.c || d()) {
                z = false;
            }
            aVar.c(z);
        }
        this.n.postDelayed(new aw(this), Constants.ST_UPLOAD_MAX_COUNT);
    }

    public final void a(int i, f fVar) {
        if (this.h == null) {
            this.h = new ArrayList(5);
        }
        ArrayList arrayList = null;
        this.ab = 0;
        if (fVar != null) {
            arrayList = fVar.e;
            this.ab = fVar.c + fVar.d;
        }
        j();
        k();
        if (this.h.isEmpty() || r0 != null) {
            b(r0);
            this.G.b(this.h);
            this.h.clear();
            if (r0 != null && r0.size() > 0) {
                Iterator it = r0.iterator();
                while (it.hasNext()) {
                    c cVar = (c) it.next();
                    t tVar = new t();
                    tVar.a = 2;
                    tVar.b = cVar;
                    this.h.add(tVar);
                }
            }
            this.s = 2;
            if (i > 0) {
                boolean z;
                this.G.c(this.N);
                this.G.c(this.O);
                this.G.c(this.P);
                this.G.a(this.h);
                if (i < this.ab) {
                    z = true;
                } else {
                    z = false;
                }
                this.ad = z;
            } else if (i == 0) {
                this.G.c(this.N);
                this.G.c(this.O);
                this.G.c(this.P);
                this.G.a(this.O);
                this.ad = false;
            } else {
                this.s = 1;
                this.G.c(this.N);
                this.G.c(this.O);
                this.G.c(this.P);
                this.G.a(this.P);
                this.ad = false;
            }
            if (this.y) {
                this.n.postDelayed(new ax(this), Constants.ST_UPLOAD_MAX_COUNT);
            }
        }
    }

    private void i() {
        int b = this.G.b();
        if (b == -1) {
            b = this.G.a();
        }
        this.R.e(b, 0);
        this.n.post(new az(this));
    }

    private void j() {
        if (this.ab > 0) {
            this.v.setText(com.xunlei.downloadprovider.d.a.a((long) this.ab, "\u4e07"));
            this.v.setVisibility(0);
            return;
        }
        this.v.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
    }

    private void k() {
        if (this.b != null) {
            this.b.f = String.valueOf(this.ab);
            ((a) this.mActivity).b(this.b);
        }
    }

    private void c(c cVar) {
        if (cVar != null) {
            ((a) this.mActivity).a(cVar);
        }
    }

    public final void a(f fVar) {
        if (this.h != null && this.h.size() != 0) {
            List arrayList = new ArrayList();
            ArrayList arrayList2 = null;
            if (fVar != null) {
                arrayList2 = fVar.e;
            }
            b(r0);
            if (r0 == null || r0.size() <= 0) {
                XLToast.b(this.mActivity, XLToastType.XLTOAST_TYPE_NONE, "\u6ca1\u6709\u66f4\u591a\u8bc4\u8bba\u4e86");
                this.ad = false;
            } else {
                Iterator it = r0.iterator();
                while (it.hasNext()) {
                    c cVar = (c) it.next();
                    t tVar = new t();
                    tVar.a = 2;
                    tVar.b = cVar;
                    this.h.add(tVar);
                    arrayList.add(tVar);
                }
                if (this.h.size() >= this.ab) {
                    this.ad = false;
                }
            }
            this.u = 2;
            this.G.c(this.ac);
            this.G.a(this.G.getItemCount(), arrayList);
        }
    }

    public final void b(f fVar) {
        if (this.i == null) {
            this.i = new ArrayList(5);
        }
        ArrayList arrayList;
        if (fVar != null) {
            arrayList = fVar.e;
        } else {
            arrayList = null;
        }
        if (this.i.isEmpty() || r1 != null) {
            int i;
            b(r1);
            this.G.b(this.i);
            this.i.clear();
            if (r1 == null || r1.size() <= 0) {
                i = 0;
            } else {
                int size = r1.size();
                Iterator it = r1.iterator();
                while (it.hasNext()) {
                    c cVar = (c) it.next();
                    cVar.r = true;
                    t tVar = new t();
                    tVar.a = 2;
                    tVar.b = cVar;
                    this.i.add(tVar);
                }
                c((c) r1.get(0));
                i = size;
            }
            this.t = 2;
            if (i > 0) {
                i = this.G.a();
                if (i >= 0) {
                    this.G.a(i, this.aa);
                    this.G.a(i + 1, this.i);
                } else {
                    this.G.a(this.aa);
                    this.G.a(this.i);
                }
            } else {
                this.G.c(this.aa);
                this.G.b(this.i);
            }
            if (this.y) {
                this.n.postDelayed(new ba(this), Constants.ST_UPLOAD_MAX_COUNT);
            }
        }
    }

    public final void a(c cVar) {
        long j;
        XLToast.b(this.mActivity, XLToastType.XLTOAST_TYPE_SUC, "\u53d1\u9001\u8bc4\u8bba\u6210\u529f");
        this.w.a(false);
        this.w.dismiss();
        this.w.a(com.umeng.a.d);
        n nVar = cVar.q != null ? (n) cVar.q.get(0) : null;
        if (nVar == null) {
            j = -1;
        } else {
            j = nVar.a;
        }
        this.w.a(j);
        bk.a(this.j, true, "ok", j, cVar.a);
        this.G.c(this.N);
        this.G.c(this.O);
        this.G.c(this.P);
        t tVar = new t();
        tVar.a = 2;
        tVar.b = cVar;
        this.h.add(0, tVar);
        int a = this.G.a();
        this.G.a(a + 1, tVar);
        this.R.e(a, 0);
        this.ab++;
        j();
        k();
    }

    public final void a(long j) {
        t tVar;
        Iterator it;
        ArrayList arrayList = new ArrayList(1);
        for (t tVar2 : this.h) {
            ArrayList arrayList2;
            n nVar;
            c cVar = (c) tVar2.b;
            if (cVar.a == j) {
                arrayList.add(tVar2);
            } else {
                arrayList2 = cVar.q;
                if (arrayList2 != null && arrayList2.size() > 0) {
                    nVar = (n) arrayList2.get(0);
                    if (nVar.a == j) {
                        nVar.a = -1;
                        this.G.b(tVar2);
                    }
                }
            }
        }
        if (arrayList.size() > 0) {
            this.ab -= arrayList.size();
            j();
            k();
            it = arrayList.iterator();
            while (it.hasNext()) {
                tVar2 = (t) it.next();
                this.h.remove(tVar2);
                this.G.c(tVar2);
            }
            arrayList.clear();
            if (this.h.isEmpty()) {
                this.G.a(this.G.a() + 1, this.O);
            }
        }
        if (!(this.i == null || this.i.isEmpty())) {
            for (t tVar22 : this.i) {
                cVar = (c) tVar22.b;
                if (cVar.a == j) {
                    arrayList.add(tVar22);
                } else {
                    arrayList2 = cVar.q;
                    if (arrayList2 != null && arrayList2.size() > 0) {
                        nVar = (n) arrayList2.get(0);
                        if (nVar.a == j) {
                            nVar.a = -1;
                            this.G.b(tVar22);
                        }
                    }
                }
            }
            it = arrayList.iterator();
            while (it.hasNext()) {
                tVar22 = (t) it.next();
                this.i.remove(tVar22);
                this.G.c(tVar22);
            }
            if (this.i != null && this.i.isEmpty()) {
                this.G.c(this.aa);
            }
        }
        XLToast.b(this.mActivity, XLToastType.XLTOAST_TYPE_SUC, "\u5220\u9664\u8bc4\u8bba\u6210\u529f");
    }

    private void b(List<c> list) {
        if (list != null && !list.isEmpty()) {
            List<com.xunlei.downloadprovider.web.base.model.a.a> list2 = this.a.h;
            if (list2 != null && list2.size() > 0) {
                for (com.xunlei.downloadprovider.web.base.model.a.a aVar : list2) {
                    for (c cVar : list) {
                        if (cVar.a == aVar.a) {
                            cVar.m = true;
                            if (!aVar.d) {
                                cVar.n++;
                            }
                        }
                    }
                }
            }
        }
    }

    public final void a(boolean z, Message message) {
        if (z) {
            XLToast.b(this.mActivity, XLToastType.XLTOAST_TYPE_SUC, "\u521b\u5efa\u4e0b\u8f7d\u6210\u529f");
            return;
        }
        TaskInfo taskInfo = (TaskInfo) message.obj;
        ((ThunderTask) this.mActivity).handleTaskOperator(message.what, message.arg1, taskInfo.mTaskId, taskInfo);
    }

    public final void a(int i, String str) {
        if (i == 1) {
            if (this.b != null) {
                this.I.b = this.b;
                this.G.b(this.I);
                if (this.mActivity != null) {
                    ((a) this.mActivity).a("ok", this.b);
                }
            }
            this.q = 1;
            if (l()) {
                this.o.setVisibility(0);
                ((a) this.mActivity).b(true);
            }
            this.D.b();
        }
        if (i == 2) {
            if (this.f > 0) {
                XLToast.b(this.mActivity, XLToastType.XLTOAST_TYPE_ALARM, "\u65e0\u6cd5\u83b7\u53d6\u66f4\u591a\u63a8\u8350");
            } else {
                this.r = 1;
                if (l()) {
                    this.D.b();
                    this.o.setVisibility(0);
                    ((a) this.mActivity).b(true);
                }
            }
            a(null);
        } else if (i == 4) {
            long j;
            if (TextUtils.isEmpty(this.w.a())) {
                this.w.a(false);
            } else {
                this.w.a(true);
            }
            XLToast.b(this.mActivity, XLToastType.XLTOAST_TYPE_ALARM, "\u53d1\u9001\u8bc4\u8bba\u5931\u8d25");
            c cVar = this.w.b;
            if (cVar == null) {
                j = -1;
            } else {
                j = cVar.a;
            }
            bk.a(this.j, false, str, j, -1);
        } else if (i == 9) {
            XLToast.b(this.mActivity, XLToastType.XLTOAST_TYPE_ALARM, "\u5220\u9664\u8bc4\u8bba\u5931\u8d25");
        } else if (i == 10) {
            if (this.F <= 0) {
                r0 = true;
            } else {
                r0 = false;
            }
            if (r0) {
                this.F++;
                new StringBuilder("retry load hot comment time=>").append(this.F);
                this.t = 0;
                this.a.c();
                return;
            }
            this.F = 0;
            this.t = 1;
            if (this.y) {
                this.n.postDelayed(new bb(this), Constants.ST_UPLOAD_MAX_COUNT);
            }
            bk.e("hot", str);
        } else if (i == 12) {
            this.G.c(this.ac);
            this.u = 1;
            XLToast.b(this.mActivity, XLToastType.XLTOAST_TYPE_ALARM, "\u52a0\u8f7d\u66f4\u591a\u8bc4\u8bba\u5931\u8d25");
            bk.e("morenew", str);
        } else if (i == 3) {
            if (this.E <= 0) {
                r0 = true;
            } else {
                r0 = false;
            }
            if (r0) {
                this.E++;
                new StringBuilder("retry load comment time=>").append(this.E);
                this.s = 0;
                this.a.b();
                return;
            }
            this.E = 0;
            this.s = 1;
            if (l()) {
                this.D.b();
                this.o.setVisibility(0);
                ((a) this.mActivity).b(true);
            }
            bk.e("new", str);
            a(-1, null);
        } else if (i != 5 && i != 8 && i == 7) {
            this.v.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        }
    }

    private boolean l() {
        return this.o.getVisibility() != 0 && TextUtils.isEmpty(this.p) && this.q == 1 && this.r == 1 && this.s == 1;
    }

    public final void c() {
        if (this.mActivity != null && !this.mActivity.isFinishing()) {
        }
    }

    public final void a(String str) {
        if (this.b != null) {
            ShareBean shareBean = new ShareBean(str, this.b.a(), this.b.c, this.b.b, com.umeng.a.d);
            shareBean.g = true;
            com.xunlei.downloadprovidershare.d.b().a(this.mActivity, shareBean, (com.xunlei.downloadprovidershare.d.a) this);
        }
    }

    public final void a(int i, SHARE_MEDIA share_media) {
        String a = com.xunlei.downloadprovidershare.d.a(i);
        String str = this.j;
        String str2 = com.umeng.a.d;
        switch (AnonymousClass_1.a[share_media.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                str2 = "wechart";
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                str2 = "pengyouquan";
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                str2 = "weibo";
                break;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                str2 = "qq";
                break;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                str2 = com.tencent.connect.common.Constants.SOURCE_QZONE;
                break;
        }
        bk.a(str, str2, a, i, "detail_shortvideo");
        if (i == 0) {
            com.xunlei.downloadprovider.homepage.recommend.b.a.a(BrothersApplication.a).a(this.j, this.k, b.d(), "share_success");
        }
    }

    public void onShareComplete(int i, SHARE_MEDIA share_media, ShareBean shareBean) {
        bk.a(this.j, ba.a(share_media, shareBean), com.xunlei.downloadprovidershare.d.a(i), i, shareBean.e);
        if (i == 0) {
            com.xunlei.downloadprovider.homepage.recommend.b.a.a(BrothersApplication.a).a(this.j, this.k, b.d(), "share_success");
        }
    }

    public final void a(View view, int i, Object obj) {
        boolean z = true;
        c cVar;
        switch (i) {
            case SpdyAgent.ACCS_TEST_SERVER:
                if (this.b != null) {
                    if (this.h != null) {
                        this.h.clear();
                    }
                    this.s = 0;
                    this.a.b();
                    if (this.t == 1) {
                        this.t = 0;
                        this.a.c();
                    }
                }
            case SpdyAgent.ACCS_ONLINE_SERVER:
                if (this.b == null) {
                    return;
                }
                if (this.b.k) {
                    XLToast.b(this.mActivity, XLToastType.XLTOAST_TYPE_ALARM, "\u60a8\u5df2\u70b9\u8d5e\u8fc7");
                    return;
                }
                this.b.k = true;
                d dVar = this.a;
                com.xunlei.downloadprovider.homepage.recommend.b.a.a(BrothersApplication.a).a(this.j, this.k, b.d(), new g(dVar), new h(dVar));
                if (!(com.xunlei.xllib.a.b.a(getApplicationContext()) || TextUtils.isEmpty(this.j))) {
                    com.xunlei.downloadprovider.homepage.recommend.c.c.a().a(this.p, null, this.j, this.k);
                    new StringBuilder("\u8be6\u60c5\u9875\u6dfb\u52a0\u70b9\u8d5e\u5230\u6570\u636e\u5e93 title, name, movieId == ").append(this.p).append(MiPushClient.ACCEPT_TIME_SEPARATOR).append(null).append(MiPushClient.ACCEPT_TIME_SEPARATOR).append(Long.parseLong(this.j));
                }
                ((a) this.mActivity).a(this.b);
                bk.b(this.b.a, "video");
                aa.a();
                aa.a(this.j, this.b.l);
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                if (this.b != null && !TextUtils.isEmpty(this.b.a())) {
                    this.a.a(this.mActivity, 1, this.b);
                    bk.a(this.j, "wechart", "detail_shortvideo");
                }
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                if (this.b != null && !TextUtils.isEmpty(this.b.e)) {
                    this.a.a(this.mActivity, (int) XZBDevice.DOWNLOAD_LIST_FAILED, this.b);
                    bk.a(this.j, com.tencent.connect.common.Constants.SOURCE_QZONE, "detail_shortvideo");
                    if (this.mActivity instanceof a) {
                        ((a) this.mActivity).a();
                    }
                }
            case XZBDevice.DOWNLOAD_LIST_ALL:
                if (this.b != null && !TextUtils.isEmpty(this.b.e)) {
                    this.a.a(this.mActivity, (int) XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, this.b);
                    bk.a(this.j, "qq", "detail_shortvideo");
                }
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                if (this.b != null && !TextUtils.isEmpty(this.b.e)) {
                    this.a.a(this.mActivity, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE, this.b);
                    bk.a(this.j, "pengyouquan", "detail_shortvideo");
                }
            case R.styleable.Toolbar_contentInsetEnd:
                if (this.b != null && this.b.n != -1) {
                    ShortTimeVideoListActivity.a(this.mActivity, this.b.n, this.b.h, this.b.i, "shortvideo_detail");
                }
            case R.styleable.Toolbar_contentInsetLeft:
                u uVar = (u) obj;
                bk.a(this.j, uVar.a, new String[]{uVar.p, uVar.q, uVar.t, b.q(), com.xunlei.xllib.a.b.d(this.mActivity)});
                a(uVar, true, false);
                bk.a(this.j, From.VIDEO_REC.getText());
            case XZBDevice.Wait:
                ((TextView) view).setText("\u6b63\u5728\u52a0\u8f7d...");
                for (int i2 = XZBDevice.Wait; i2 < this.Q.size(); i2++) {
                    t tVar = new t();
                    tVar.a = 7;
                    tVar.b = this.Q.get(i2);
                    this.g.add(tVar);
                    this.G.a(this.g.size() + 2, tVar);
                }
                this.G.c(this.L);
                bk.b(this.j);
            case XZBDevice.Pause:
                this.x.a((c) obj);
                this.x.show();
            case XZBDevice.Stop:
                bk.a(((Boolean) obj).booleanValue());
            case XZBDevice.Success:
                if (this.b != null) {
                    cVar = (c) obj;
                    this.a.a(cVar);
                    CommentSateInfo commentSateInfo = new CommentSateInfo();
                    commentSateInfo.a = cVar.a;
                    commentSateInfo.d = cVar.f;
                    commentSateInfo.b = cVar.m;
                    commentSateInfo.e = cVar.i;
                    commentSateInfo.c = cVar.n;
                    StateSyncManager.a(this.mActivity, SourceFrom.PAGE_SHORMOVIE_DETAIL, commentSateInfo);
                    d(cVar);
                    if (cVar != null) {
                        c(cVar);
                        aa.a();
                        aa.a(String.valueOf(cVar.a), cVar.n);
                    }
                    String str = this.j;
                    long j = cVar.a;
                    LoginHelper.a();
                    bk.a(str, j, LoginHelper.c());
                }
            case XZBDevice.Fail:
                if (this.b != null) {
                    cVar = (c) obj;
                    if (cVar.l) {
                        XLToast.b(this.mActivity, XLToastType.XLTOAST_TYPE_ALARM, "\u6b64\u8bc4\u8bba\u6682\u65f6\u65e0\u6cd5\u56de\u590d");
                        return;
                    }
                    this.w.b(new StringBuilder("\u56de\u590d ").append(cVar.j).toString());
                    h();
                    this.w.a(cVar);
                    bk.d(this.j, "discuss");
                }
            case XZBDevice.Upload:
                String str2 = (String) obj;
                SearchActivity.a(this.mActivity, "video_tag", str2);
                bk.c(this.b.a, str2);
            case XZBDevice.Predownload:
            case XZBDevice.Delete:
                cVar = (c) obj;
                if (cVar != null) {
                    PersonalSpaceActivity.From from;
                    this.a.d();
                    this.a.e();
                    if (i == 14) {
                        from = PersonalSpaceActivity.From.VIDEO_DETAIL_DISCUSS_HEAD;
                    } else {
                        from = PersonalSpaceActivity.From.VIDEO_DETAIL_DISCUSS_NAME;
                    }
                    PersonalSpaceActivity.a(this.mActivity, from, cVar.i, cVar.j, cVar.k);
                }
            case R.styleable.Toolbar_titleMarginBottom:
                n nVar = (n) obj;
                if (nVar != null) {
                    this.a.d();
                    this.a.e();
                    PersonalSpaceActivity.a(this.mActivity, PersonalSpaceActivity.From.VIDEO_DETAIL_DISCUSS_NAME, nVar.c, nVar.d, nVar.e);
                }
            case R.styleable.Toolbar_maxButtonHeight:
                this.c = ((Boolean) obj).booleanValue();
                f();
                com.xunlei.downloadprovider.util.aa.a.a(this.mActivity, "auto_play_next_shortmovie", this.c);
                a aVar = (a) this.mActivity;
                if (!this.c || d()) {
                    z = false;
                }
                aVar.c(z);
            default:
                break;
        }
    }

    private void a(u uVar, boolean z, boolean z2) {
        m();
        if (this.mActivity instanceof a) {
            this.e.scrollToPosition(0);
            ((a) this.mActivity).a(uVar, z, z2);
            b(true);
            this.b = uVar;
            this.V = From.VIDEO_REC.getText();
            this.j = uVar.a;
            this.k = uVar.g;
            this.a.a(uVar);
            this.a.a();
            g();
        }
    }

    private void m() {
        a(this.R.j(), this.R.k());
        if (this.S.size() > 0) {
            new StringBuilder("onItemClick report size =>").append(this.S.size());
            bk.a(this.S, this.V, new String[]{this.W, this.X, this.Y, b.q(), com.xunlei.xllib.a.b.d(this.mActivity)});
            this.S.clear();
        }
        this.U.clear();
        this.T.clear();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final boolean d() {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.web.base.ShortMovieDetailFragment.d():boolean");
        /*
        this = this;
        r0 = 1;
        r1 = 0;
        r2 = com.xunlei.downloadprovidershare.d.b();
        r3 = r2.a;
        if (r3 == 0) goto L_0x004a;
    L_0x000a:
        r2 = r2.a;
        r2 = r2.isShowing();
        if (r2 == 0) goto L_0x004a;
    L_0x0012:
        r2 = r0;
    L_0x0013:
        if (r2 != 0) goto L_0x0041;
    L_0x0015:
        r2 = r4.x;
        if (r2 == 0) goto L_0x0021;
    L_0x0019:
        r2 = r4.x;
        r2 = r2.isShowing();
        if (r2 != 0) goto L_0x0041;
    L_0x0021:
        r2 = r4.w;
        if (r2 == 0) goto L_0x002d;
    L_0x0025:
        r2 = r4.w;
        r2 = r2.isShowing();
        if (r2 != 0) goto L_0x0041;
    L_0x002d:
        r2 = r4.Q;
        if (r2 == 0) goto L_0x0041;
    L_0x0031:
        r2 = r4.Q;
        r2 = r2.isEmpty();
        if (r2 != 0) goto L_0x0041;
    L_0x0039:
        r2 = r4.R;
        r2 = r2.j();
        if (r2 <= 0) goto L_0x004c;
    L_0x0041:
        r2 = r4.e;
        r2 = r2.isShown();
        if (r2 == 0) goto L_0x004c;
    L_0x0049:
        return r0;
    L_0x004a:
        r2 = r1;
        goto L_0x0013;
    L_0x004c:
        r0 = r1;
        goto L_0x0049;
        */
    }

    public final void a(u uVar) {
        if (uVar != null) {
            m();
            if (this.mActivity instanceof a) {
                this.e.scrollToPosition(0);
                b(true);
                this.b = uVar;
                this.V = From.VIDEO_REC.getText();
                this.j = uVar.a;
                this.k = uVar.g;
                this.a.a(uVar);
                this.a.a();
                g();
                this.l.setText(com.umeng.a.d);
            }
            this.V = From.VIDEO_SCREEN.getText();
            bk.a(this.j, this.V);
        }
    }

    public final boolean a(boolean z) {
        if (this.Q == null || this.Q.size() <= 0) {
            return false;
        }
        a((u) this.Q.get(0), false, z);
        this.V = z ? From.VIDEO_SCREEN_AUTO.getText() : From.VIDEO_SCREEN.getText();
        bk.a(this.j, this.V);
        return true;
    }

    public final void e() {
        this.n.post(new bc(this));
    }

    public final void b(c cVar) {
        cVar.m = true;
    }

    public void onShareTargetClicked(SHARE_MEDIA share_media, ShareBean shareBean) {
        OperationType operationType = shareBean.m;
        String a = ba.a(share_media, shareBean);
        if (operationType == OperationType.None) {
            bk.a(this.j, a, shareBean.e);
        } else if (OperationType.Download == shareBean.m) {
            ((a) this.mActivity).b();
            bk.e("download");
        } else if (OperationType.Accuse == shareBean.m) {
            ((a) this.mActivity).c();
            bk.e("jubao");
        } else if (OperationType.Upload == shareBean.m) {
            bm.a(getActivity(), "videodetail");
        } else {
            bk.a(this.j, a, shareBean.e);
        }
    }

    private void d(c cVar) {
        c cVar2;
        if (cVar.r) {
            for (t tVar : this.h) {
                cVar2 = (c) tVar.b;
                if (cVar2.a == cVar.a) {
                    cVar2.m = cVar.m;
                    cVar2.n = cVar.n;
                    this.G.b(tVar);
                    return;
                }
            }
        } else if (this.i != null && this.i.size() > 0) {
            for (t tVar2 : this.i) {
                cVar2 = (c) tVar2.b;
                if (cVar2.a == cVar.a) {
                    cVar2.m = cVar.m;
                    cVar2.n = cVar.n;
                    this.G.b(tVar2);
                    return;
                }
            }
        }
    }

    static /* synthetic */ void a(ShortMovieDetailFragment shortMovieDetailFragment, Intent intent) {
        if (((SourceFrom) SourceFrom.valueOf(SourceFrom.class, intent.getStringExtra("source_from"))) != SourceFrom.PAGE_SHORMOVIE_DETAIL) {
            CommentSateInfo commentSateInfo = (CommentSateInfo) intent.getParcelableExtra("comment_info");
            if (shortMovieDetailFragment.b != null && TextUtils.equals(commentSateInfo.d, shortMovieDetailFragment.b.g)) {
                c cVar;
                for (t tVar : shortMovieDetailFragment.h) {
                    cVar = (c) tVar.b;
                    if (cVar.a == commentSateInfo.a) {
                        cVar.m = commentSateInfo.b;
                        cVar.n = commentSateInfo.c;
                        shortMovieDetailFragment.G.b(tVar);
                        break;
                    }
                }
                if (shortMovieDetailFragment.i != null && shortMovieDetailFragment.i.size() > 0) {
                    for (t tVar2 : shortMovieDetailFragment.i) {
                        cVar = (c) tVar2.b;
                        if (cVar.a == commentSateInfo.a) {
                            cVar.m = commentSateInfo.b;
                            cVar.n = commentSateInfo.c;
                            shortMovieDetailFragment.G.b(tVar2);
                            return;
                        }
                    }
                }
            }
        }
    }
}
