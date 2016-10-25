package com.xunlei.downloadprovider.ad.recommend.view;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.ad.common.a;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.commonview.dialog.d;
import com.xunlei.downloadprovider.download.tasklist.TaskListPageFragment.LOAD_TAG;
import com.xunlei.downloadprovider.download.tasklist.list.a.n;
import com.xunlei.downloadprovider.download.tasklist.list.b.e;
import com.xunlei.downloadprovider.download.tasklist.list.b.f;
import com.xunlei.downloadprovider.util.r;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.android.spdy.SpdyProtocol;

// compiled from: RecommendAdViewHolder.java
public class b extends f implements OnClickListener, com.xunlei.downloadprovider.ad.recommend.a.b {
    private static final String b;
    public int a;
    private Context c;
    private LinearLayout d;
    private List<a> e;
    private List<RecommendAdItemView> f;
    private View g;
    private TextView h;
    private TextView k;
    private View l;
    private ImageView m;
    private Animation n;
    private d o;
    private com.xunlei.downloadprovider.ad.recommend.a.a p;
    private int q;
    private com.xunlei.downloadprovider.download.tasklist.list.a r;
    private boolean s;
    private Handler t;
    private final int u;
    private List<a> v;

    static {
        b = b.class.getSimpleName();
    }

    public static b a(Context context, ViewGroup viewGroup, com.xunlei.downloadprovider.download.a.a aVar, com.xunlei.downloadprovider.download.tasklist.list.a aVar2, com.xunlei.downloadprovider.ad.recommend.a.a aVar3, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_task_list_recommend_use_ad_card, viewGroup, false);
        inflate.setTag(R.id.about_user_exps, Integer.valueOf(i));
        return new b(context, inflate, aVar, aVar2, aVar3, i);
    }

    private b(Context context, View view, com.xunlei.downloadprovider.download.a.a aVar, com.xunlei.downloadprovider.download.tasklist.list.a aVar2, com.xunlei.downloadprovider.ad.recommend.a.a aVar3, int i) {
        super(view);
        this.c = null;
        this.a = 0;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.r = null;
        this.s = false;
        this.t = new Handler();
        this.u = 0;
        this.v = null;
        this.c = context;
        this.j = aVar;
        this.p = aVar3;
        this.q = i;
        this.r = aVar2;
        this.a = com.xunlei.downloadprovider.download.tasklist.list.e.a.a.a(i).size();
        this.e = new ArrayList();
        this.p.a(this);
        this.g = view.findViewById(R.id.root_view);
        this.d = (LinearLayout) view.findViewById(R.id.recommend_list_ll);
        this.l = view.findViewById(R.id.change_data_btn);
        this.m = (ImageView) view.findViewById(R.id.change_data_icon_iv);
        this.k = (TextView) view.findViewById(R.id.source_tv);
        this.h = (TextView) view.findViewById(R.id.title_tv);
        this.n = AnimationUtils.loadAnimation(this.c, R.anim.recommend_ad_change_data_rotate_anim);
        this.n.setFillAfter(true);
        this.n.setInterpolator(new LinearInterpolator());
        l();
        a((int) SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.h.setText(this.c.getResources().getString(R.string.task_list_recommend_use_title));
        this.l.setOnClickListener(this);
        this.v = new ArrayList();
    }

    public final void a(e eVar) {
        super.a(eVar);
        new StringBuilder("fillData pageIndex: ").append(this.q);
        int i = this.q;
        new StringBuilder("RecommendAdViewHolder.this.getLayoutPosition(): ").append(getLayoutPosition());
        if (getLayoutPosition() != -1) {
            n.a().a(new com.xunlei.downloadprovider.ad.recommend.c.d(i));
        }
        if (!this.s) {
            this.p.a();
            this.s = true;
        }
        List arrayList = new ArrayList(this.v.size());
        arrayList.addAll(this.v);
        this.v.clear();
        a(this.q, arrayList);
    }

    public final boolean a(List<a> list) {
        if (list.size() < this.e.size()) {
            return false;
        }
        this.e = list;
        List list2 = this.e;
        int i = 0;
        while (i < this.f.size()) {
            RecommendAdItemView recommendAdItemView = (RecommendAdItemView) this.f.get(i);
            if (i < list2.size()) {
                a aVar = (a) list2.get(i);
                recommendAdItemView.setData(aVar);
                if (((i >= list2.size() + -1 ? 1 : 0) | (i >= this.a + -1 ? 1 : 0)) != 0) {
                    recommendAdItemView.setDivideLineVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                } else {
                    recommendAdItemView.setDivideLineVisibility(0);
                }
                recommendAdItemView.setOnClickListener(new d(this, aVar, recommendAdItemView, i));
                recommendAdItemView.setVisibility(0);
            } else {
                recommendAdItemView.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            }
            i++;
        }
        a(this.q, this.e);
        return true;
    }

    public final void a(String str) {
        this.k.setText(str);
    }

    public final void a(boolean z) {
        this.l.setEnabled(z);
    }

    public final void a(int i) {
        this.g.setVisibility(i);
    }

    public final void a() {
        this.r.h.f.remove(LOAD_TAG.LOAD_RECOMMEND_AD);
        this.t.post(new c(this));
    }

    public final int b() {
        return this.q;
    }

    public final Context c() {
        return this.c;
    }

    public final void a(DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2) {
        if (this.o == null) {
            this.o = new d(this.c);
            this.o.setTitle("\u6e29\u99a8\u63d0\u793a");
            this.o.b("\u5f53\u524d\u4e3a\u79fb\u52a8\u7f51\u7edc\uff0c\u5f00\u59cb\u4e0b\u8f7d/\u5b89\u88c5\u5e94\u7528\uff1f");
            this.o.d("\u786e\u8ba4");
            this.o.c("\u53d6\u6d88");
        }
        this.o.b(onClickListener);
        this.o.a(onClickListener2);
    }

    public final void d() {
        if (this.o != null) {
            this.o.show();
        }
    }

    public final void e() {
        if (this.o != null) {
            this.o.dismiss();
        }
    }

    public final com.xunlei.downloadprovider.download.a.a h() {
        return this.j;
    }

    public final void f() {
        if (this.n != null) {
            this.m.startAnimation(this.n);
        }
    }

    public final void g() {
        this.m.clearAnimation();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.change_data_btn:
                int i = this.q;
                String a = com.xunlei.downloadprovider.ad.recommend.c.a.a();
                new StringBuilder("reportRecommendAdChangeClick attr: adv_downloadin_change_click tabId: ").append(i).append(" netType: ").append(a);
                com.xunlei.downloadprovidercommon.a.d.a(com.xunlei.downloadprovidercommon.a.a.a("android_advertise", "adv_downloadin_change_click").b("tabid", com.xunlei.downloadprovider.ad.recommend.c.a.a(i)).b("net_type", a));
                ((Set) n.a().a.get(Integer.valueOf(this.q))).clear();
                ((Set) n.a().b.get(Integer.valueOf(this.q))).clear();
                this.p.b();
            default:
                break;
        }
    }

    private void l() {
        this.f = new ArrayList(this.a);
        int i = this.a;
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        while (true) {
            int i2 = i - 1;
            if (i > 0) {
                View recommendAdItemView = new RecommendAdItemView(this.c);
                this.d.addView(recommendAdItemView, layoutParams);
                this.f.add(recommendAdItemView);
                i = i2;
            } else {
                return;
            }
        }
    }

    private void a(int i, List<a> list) {
        this.t.postDelayed(new e(this, list, i), 0);
    }

    public static boolean i() {
        boolean z;
        r.a aVar = r.c().e;
        r.a.a a = aVar.a();
        if (aVar.a == null || aVar.a.optInt("ad_type", 0) != 0) {
            z = true;
        } else {
            int i = 0;
        }
        if (i == 0 || a.c != 1) {
            i = 0;
        } else {
            z = true;
        }
        return z && com.xunlei.xllib.a.b.a(BrothersApplication.a());
    }
}
