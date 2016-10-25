package com.xunlei.tdlive;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AbsListView.LayoutParams;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.e;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.taobao.accs.common.Constants;
import com.tencent.open.SocialConstants;
import com.umeng.a;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.a.j$a;
import com.xunlei.tdlive.a.l;
import com.xunlei.tdlive.a.n;
import com.xunlei.tdlive.base.i;
import com.xunlei.tdlive.control.TouchDetectorFrameLayout;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.sdk.IHost;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.ac;
import com.xunlei.tdlive.util.d;
import com.xunlei.tdlive.util.q.b;
import com.xunlei.tdlive.view.LiveListBannerView;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: LiveListFragment.java
public class q extends i implements OnClickListener, OnScrollListener, OnItemClickListener, e<ListView>, j$a {
    private l k;
    private PullToRefreshListView l;
    private h m;
    private View n;
    private View o;
    private LiveListBannerView p;
    private n q;
    private com.xunlei.tdlive.base.l r;
    private boolean s;
    private Handler t;
    private boolean u;
    private boolean v;
    private boolean w;
    private long x;
    private String y;

    public q() {
        this.s = true;
        this.u = true;
        this.v = false;
        this.w = true;
        this.x = 0;
        this.y = "down_refresh";
    }

    private View a(ViewGroup viewGroup) {
        if (this.p == null) {
            Point a = d.a(viewGroup.getContext());
            a.y = (int) (((double) a.x) / 3.5d);
            this.p = (LiveListBannerView) LayoutInflater.from(getActivity()).inflate(R.layout.xllive_livelist_banner_view, viewGroup, false);
            this.p.setLayoutParams(new LayoutParams(-1, a.y));
            this.p.setOnItemClickListener(this);
            this.p.autoStep(5000);
            LiveListBannerView liveListBannerView = this.p;
            n nVar = new n(new s(this));
            this.q = nVar;
            liveListBannerView.setAdapter(nVar);
            this.q.a(a.d);
        }
        return this.p;
    }

    private View b(ViewGroup viewGroup) {
        this.o = LayoutInflater.from(getActivity()).inflate(R.layout.xllive_livelist_footer_view, viewGroup, false);
        this.o.setOnClickListener(this);
        this.o.setVisibility(XZBDevice.Wait);
        return this.o;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.n != null ? this.n : layoutInflater.inflate(R.layout.xllive_fragment_livelist, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (this.n != view) {
            String string;
            boolean z;
            boolean z2;
            String str;
            this.n = view;
            String str2 = "\u52a8\u6001";
            String str3 = a.d;
            Bundle arguments = getArguments();
            if (arguments != null) {
                str3 = arguments.getString(SocialConstants.PARAM_URL, str3);
                string = arguments.getString(WebBrowserActivity.EXTRA_TITLE, str2);
                boolean z3 = arguments.getBoolean("left", false);
                z = arguments.getBoolean("right", true);
                this.s = arguments.getBoolean("banner", this.s);
                if (arguments.getBoolean("titlebar")) {
                    this.a.setVisibility(0);
                }
                String str4 = str3;
                z2 = z;
                z = z3;
                str = str4;
            } else {
                str = str3;
                string = str2;
                z = false;
                z2 = true;
            }
            b(string);
            e(z2);
            b(getResources().getDrawable(R.drawable.xllive_rank_icon_selector));
            this.c.setOnClickListener(this);
            this.g.setOnClickListener(this);
            this.a.setOnClickListener(this);
            ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            if (this.s && ac.j()) {
                layoutParams.topMargin = (int) d.a(getActivity(), -11.0f);
            }
            this.l = (PullToRefreshListView) b(R.id.list);
            this.l.setLayoutParams(layoutParams);
            if (this.s) {
                a((ViewGroup) this.l.getRefreshableView());
                d(true);
                a(getResources().getDrawable(R.drawable.xllive_search_icon_selector));
            } else {
                d(z);
                a(getResources().getDrawable(R.drawable.xllive_ic_back));
                this.v = true;
            }
            ((ListView) this.l.getRefreshableView()).addFooterView(b((ViewGroup) this.l.getRefreshableView()), null, false);
            this.l.setMode(Mode.PULL_FROM_START);
            PullToRefreshListView pullToRefreshListView = this.l;
            l lVar = new l(str, 10000, this);
            this.k = lVar;
            pullToRefreshListView.setAdapter(lVar);
            this.l.setOnScrollListener(this);
            this.l.setOnRefreshListener(this);
            this.l.setOnItemClickListener(this);
            ((TouchDetectorFrameLayout) b(R.id.container)).setITouchEventIntercept(new a(this, null));
        }
    }

    public void a_(int i) {
        if (i == 1000) {
            boolean a = ac.a();
            if (this.k != null) {
                if (this.k.getCount() <= 0 || a) {
                    this.k.a("timer");
                }
            }
            if (this.q != null && SystemClock.elapsedRealtime() - this.x > 60000) {
                this.x = SystemClock.elapsedRealtime();
                if (this.q.getCount() <= 0 || a) {
                    this.q.a("timer");
                }
            }
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.m = (h) activity;
        } catch (Exception e) {
            this.m = null;
        }
    }

    public void onResume() {
        super.onResume();
        a(false);
    }

    public void onPause() {
        super.onPause();
        b(true);
        b e = com.xunlei.tdlive.util.q.e("zb_content_read");
        if (!TextUtils.isEmpty(e.d("contentlist"))) {
            e.b("contentlist").a("contentlist");
        }
    }

    public void a(boolean z) {
        if (!ac.j() && !this.v) {
            return;
        }
        if (z) {
            this.y = "repeat_refresh";
            this.l.setRefreshing(true);
            return;
        }
        a(IHost.HOST_NOFITY_REFRESH_LIST, 0, 10000);
        if (this.m != null) {
            this.m.a("main");
        }
    }

    public void a() {
        super.a();
        b(false);
    }

    private void b(boolean z) {
        d(IHost.HOST_NOFITY_REFRESH_LIST);
        if (this.m != null && !z) {
            this.m.a(true, true);
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        XLog.d("TimeTrace", "onItemClick start");
        if (ac.a()) {
            if (adapterView.getId() == R.id.live_list_banner) {
                JsonWrapper d = this.q.d(i);
                int i2 = d.getInt(JsInterface.FUNPLAY_AD_TRPE, -1);
                String string = d.getString(SocialConstants.PARAM_URL, a.d);
                DispatcherActivity.a(getActivity(), i2, d.getString(WebBrowserActivity.EXTRA_TITLE, a.d), string, 0);
                com.xunlei.tdlive.util.q.e("banner").a(i + 1).a(Constants.KEY_TARGET, string).b(Constants.KEY_TARGET);
                return;
            }
            new t(this, adapterView, i).onClick(null, 1);
        } else if (ac.j()) {
            com.xunlei.tdlive.base.n.a(getActivity(), "\u65e0\u7f51\u7edc\u8fde\u63a5");
        } else if (this.t != null) {
            this.t.sendEmptyMessage(IHost.CLIENT_NOFITY_NO_NETWORK_ERROR);
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.right) {
            RankActivity.a(getActivity(), false, null);
        } else if (view == this.o) {
            this.y = "footer_refresh";
            this.l.setRefreshing(true);
        } else if (view == this.a) {
            ((ListView) this.l.getRefreshableView()).smoothScrollToPosition(0);
        } else if (view != this.c) {
        } else {
            if (this.s) {
                WebBrowserActivity.start(getActivity(), "http://h5.live.xunlei.com/active/search/index.html", null, true, false, false);
            } else {
                getActivity().finish();
            }
        }
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        this.k.onScrollStateChanged(absListView, i);
        if (i == 0) {
            a(IHost.HOST_NOFITY_REFRESH_LIST, 10000, 10000);
            return;
        }
        d(IHost.HOST_NOFITY_REFRESH_LIST);
        this.u = true;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        this.k.onScroll(absListView, i, i2, i3);
        if (this.k.getCount() != 0 && this.k.d() == 0 && this.u) {
            this.u = false;
            b e = com.xunlei.tdlive.util.q.e("zb_content_read");
            String d = e.d("contentlist");
            int i4 = 0;
            while (i4 < i2) {
                String str;
                JsonWrapper jsonWrapper = (JsonWrapper) ((ListAdapter) absListView.getAdapter()).getItem(i + i4);
                if (jsonWrapper != null) {
                    Object toString;
                    if (jsonWrapper.getInt(JsInterface.FUNPLAY_AD_TRPE, 0) == 1) {
                        toString = new StringBuilder("roomid=,hostid=,hosttype=,recommend=,follow=,sign=,viewernum=,rn=").append(jsonWrapper.getInt("position", 0)).append(",livestat=,is_dl=1;").toString();
                    } else {
                        toString = new StringBuilder("roomid=").append(jsonWrapper.getString("roomid", a.d)).append(",hostid=").append(jsonWrapper.getString("userid", a.d)).append(",hosttype=").append(jsonWrapper.getObject("seq2", "{}").getInt("hot_level", 0)).append(",recommend=").append(jsonWrapper.getObject("seq2", "{}").getInt("is_recommend", 0)).append(",follow=").append(jsonWrapper.getObject("seq2", "{}").getInt("is_follow", 0)).append(",sign=").append(jsonWrapper.getObject("seq2", "{}").getInt("is_sign", 0)).append(",viewernum=").append(jsonWrapper.getInt("onlinenum", 0)).append(",rn=").append(jsonWrapper.getInt("position", 0)).append(",livestat=").append(jsonWrapper.getInt(Impl.COLUMN_STATUS, 0) == 2 ? "replay" : "live").append(",is_dl=0;").toString();
                    }
                    if (!d.contains(toString)) {
                        str = d + toString;
                        i4++;
                        d = str;
                    }
                }
                str = d;
                i4++;
                d = str;
            }
            e.a("grayid", this.k.a()).a("contentlist", d);
            if (d.length() > 1000) {
                e.b("contentlist").a("contentlist");
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> void a(T r6, boolean r7, boolean r8) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.tdlive.q.a(java.lang.Object, boolean, boolean):void");
        /*
        this = this;
        r4 = 1;
        r3 = 0;
        if (r7 == 0) goto L_0x0088;
    L_0x0004:
        r0 = r5.k;
        r0 = r0.getCount();
        r1 = 2;
        if (r0 >= r1) goto L_0x0010;
    L_0x000d:
        r5.f(r4);
    L_0x0010:
        r0 = r5.k;
        r0 = r0.getCount();
        r1 = r5.t;
        if (r1 == 0) goto L_0x0027;
    L_0x001a:
        r1 = r5.t;
        r2 = 2001; // 0x7d1 float:2.804E-42 double:9.886E-321;
        r1 = r1.obtainMessage(r2, r0, r3);
        r1.sendToTarget();
        if (r0 <= 0) goto L_0x002c;
    L_0x0027:
        r0 = r5.o;
        r0.setVisibility(r3);
    L_0x002c:
        r0 = r5.l;
        r1 = new com.xunlei.tdlive.u;
        r1.<init>(r5);
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r0.postDelayed(r1, r2);
        r0 = "down_refresh";
        r0 = r0.equals(r6);	 Catch:{ Exception -> 0x00d3 }
        if (r0 == 0) goto L_0x0083;
    L_0x0041:
        r0 = "down_refresh";
        r0 = com.xunlei.tdlive.util.q.e(r0);	 Catch:{ Exception -> 0x00d3 }
        r1 = "last_count";
        r1 = r0.d(r1);	 Catch:{ Exception -> 0x00d3 }
        r2 = r5.k;	 Catch:{ Exception -> 0x00d3 }
        r2 = r2.getCount();	 Catch:{ Exception -> 0x00d3 }
        r1 = java.lang.Integer.parseInt(r1);	 Catch:{ Exception -> 0x00d3 }
        r1 = r2 - r1;
        if (r1 < 0) goto L_0x0083;
    L_0x005d:
        r2 = 1;
        r2 = new java.lang.String[r2];	 Catch:{ Exception -> 0x00d3 }
        r3 = 0;
        r4 = "last_count";
        r2[r3] = r4;	 Catch:{ Exception -> 0x00d3 }
        r0 = r0.a(r2);	 Catch:{ Exception -> 0x00d3 }
        r2 = "num";
        r2 = r0.a(r2, r1);	 Catch:{ Exception -> 0x00d3 }
        r3 = "result";
        if (r1 != 0) goto L_0x0084;
    L_0x0076:
        r0 = "norefresh";
    L_0x0079:
        r0 = r2.a(r3, r0);	 Catch:{ Exception -> 0x00d3 }
        r1 = 0;
        r1 = new java.lang.String[r1];	 Catch:{ Exception -> 0x00d3 }
        r0.b(r1);	 Catch:{ Exception -> 0x00d3 }
    L_0x0083:
        return;
    L_0x0084:
        r0 = "success";
        goto L_0x0079;
    L_0x0088:
        r0 = r5.o;
        r1 = 8;
        r0.setVisibility(r1);
        r0 = "zb_content_read";
        r0 = com.xunlei.tdlive.util.q.e(r0);
        r1 = "contentlist";
        r0 = r0.d(r1);
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 != 0) goto L_0x00bf;
    L_0x00a3:
        r0 = "zb_content_read";
        r0 = com.xunlei.tdlive.util.q.e(r0);
        r1 = new java.lang.String[r4];
        r2 = "contentlist";
        r1[r3] = r2;
        r0 = r0.b(r1);
        r1 = new java.lang.String[r4];
        r2 = "contentlist";
        r1[r3] = r2;
        r0.a(r1);
    L_0x00bf:
        r0 = "down_refresh";
        r0 = com.xunlei.tdlive.util.q.e(r0);
        r1 = "last_count";
        r2 = r5.k;
        r2 = r2.getCount();
        r0.a(r1, r2);
        goto L_0x0083;
    L_0x00d3:
        r0 = move-exception;
        goto L_0x0083;
        */
    }

    public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
        this.k.a(this.y);
        if (this.q != null) {
            this.q.a(this.y);
        }
        a(IHost.HOST_NOFITY_REFRESH_LIST, 10000, 10000);
    }

    public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
        pullToRefreshBase.m();
    }

    private void f(boolean z) {
        if (this.a != null && this.m != null) {
            Animation loadAnimation;
            if (z) {
                if (this.a.getVisibility() != 0 && this.a.getTag() == null) {
                    loadAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.xllive_slide_in_from_top);
                    loadAnimation.setDuration(Constants.ST_UPLOAD_MAX_COUNT);
                    loadAnimation.setAnimationListener(new v(this));
                    this.a.setTag("ani");
                    this.a.startAnimation(loadAnimation);
                    this.a.setVisibility(0);
                } else {
                    return;
                }
            } else if (this.a.getVisibility() != 8 && this.a.getTag() == null) {
                loadAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.xllive_slide_out_to_top);
                loadAnimation.setDuration(Constants.ST_UPLOAD_MAX_COUNT);
                loadAnimation.setAnimationListener(new w(this));
                this.a.setTag("ani");
                this.a.startAnimation(loadAnimation);
                this.a.setVisibility(XZBDevice.Wait);
            } else {
                return;
            }
            this.m.a(z, true);
        }
    }
}
