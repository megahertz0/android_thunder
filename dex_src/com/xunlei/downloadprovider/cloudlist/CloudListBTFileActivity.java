package com.xunlei.downloadprovider.cloudlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;
import com.xunlei.downloadprovider.commonview.UnifiedLoadingView;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.commonview.dialog.q;
import com.xunlei.downloadprovider.commonview.f;
import com.xunlei.downloadprovider.d.c;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.downloadprovider.vod.VodUtil;
import com.xunlei.downloadprovider.vod.protocol.VodVideoFormat;
import com.xunlei.downloadprovider.vod.protocol.i;
import com.xunlei.xiazaibao.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class CloudListBTFileActivity extends ThunderTask {
    private TextView A;
    private View B;
    private View C;
    private final String a;
    private Object b;
    private boolean c;
    private final a d;
    private a e;
    private ImageView f;
    private TextView g;
    private PullToRefreshListView h;
    private UnifiedLoadingView i;
    private View j;
    private TextView k;
    private TextView l;
    private Button m;
    private ImageView n;
    private q o;
    private final OnClickListener p;
    private final com.handmark.pulltorefresh.library.PullToRefreshBase.a q;
    private final com.xunlei.downloadprovider.cloudlist.a.b r;
    private final com.xunlei.downloadprovider.a.h.a s;
    private final Handler t;
    private q u;
    private RelativeLayout v;
    private ImageView w;
    private TextView x;
    private TextView y;
    private TextView z;

    private final class a extends BaseAdapter {
        public int a;
        private int c;
        private int d;

        private a() {
            this.a = -1;
            this.c = g.a(BrothersApplication.a().getApplicationContext(), 80.0f);
            this.d = com.xunlei.downloadprovider.a.b.u() - g.a(BrothersApplication.a().getApplicationContext(), 48.0f);
        }

        public final /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        public final int getCount() {
            return CloudListBTFileActivity.this.e != null ? CloudListBTFileActivity.this.e.b().size() : 0;
        }

        private com.xunlei.downloadprovider.cloudlist.a.a a(int i) {
            return (CloudListBTFileActivity.this.e == null || i < 0 || i >= CloudListBTFileActivity.this.e.b().size()) ? null : (com.xunlei.downloadprovider.cloudlist.a.a) CloudListBTFileActivity.this.e.b().get(i);
        }

        public final long getItemId(int i) {
            return 0;
        }

        public final View getView(int i, View view, ViewGroup viewGroup) {
            b bVar;
            if (view == null || !(view.getTag() instanceof b)) {
                b bVar2 = new b((byte) 0);
                view = LayoutInflater.from(BrothersApplication.a().getApplicationContext()).inflate(2130968681, null);
                bVar2.a = view.findViewById(2131755565);
                CloudListBTFileActivity.this = view.findViewById(2131755573);
                bVar2.o = (ImageView) view.findViewById(2131755566);
                bVar2.c = (TextView) view.findViewById(2131755568);
                bVar2.d = (TextView) view.findViewById(2131755569);
                bVar2.e = view.findViewById(2131755570);
                bVar2.g = (TextView) view.findViewById(2131755571);
                bVar2.h = view.findViewById(2131755574);
                bVar2.i = view.findViewById(2131755577);
                bVar2.j = (ImageView) view.findViewById(2131755578);
                bVar2.k = (TextView) view.findViewById(2131755579);
                bVar2.l = view.findViewById(2131755580);
                bVar2.m = (ImageView) view.findViewById(2131755581);
                bVar2.n = (TextView) view.findViewById(2131755582);
                view.setTag(bVar2);
                bVar = bVar2;
            } else {
                bVar = (b) view.getTag();
            }
            com.xunlei.downloadprovider.cloudlist.a.a a = a(i);
            if (a != null) {
                View view2 = CloudListBTFileActivity.this;
                if (this.a == i) {
                    view2.setVisibility(0);
                } else {
                    view2.setVisibility(XZBDevice.Wait);
                }
                bVar.c.setText(a.a);
                bVar.o.setImageResource(XLFileTypeUtil.d(a.a));
                bVar.d.setText(com.xunlei.downloadprovider.d.a.a(a.c, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE));
                bVar.a.setOnLongClickListener(new g(this, a));
                OnClickListener hVar = new h(this, a);
                bVar.h.setVisibility(XZBDevice.Wait);
                bVar.l.setVisibility(0);
                bVar.m.setImageResource(2130837834);
                bVar.n.setText(BrothersApplication.a().getString(2131231047));
                if (a.h) {
                    bVar.i.setVisibility(0);
                    bVar.j.setImageResource(2130837831);
                    bVar.k.setText(BrothersApplication.a().getString(2131231045));
                    bVar.g.setText(BrothersApplication.a().getString(2131231046));
                } else {
                    bVar.i.setVisibility(XZBDevice.Wait);
                    bVar.g.setText(BrothersApplication.a().getString(2131231045));
                }
                bVar.a.setOnClickListener(hVar);
                bVar.e.setOnClickListener(hVar);
                bVar.i.setOnClickListener(hVar);
                bVar.l.setOnClickListener(hVar);
            }
            return view;
        }
    }

    private final class b {
        View a;
        View b;
        TextView c;
        TextView d;
        View e;
        ImageView f;
        TextView g;
        View h;
        View i;
        ImageView j;
        TextView k;
        View l;
        ImageView m;
        TextView n;
        ImageView o;

        private b() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = null;
            this.i = null;
            this.j = null;
            this.k = null;
            this.l = null;
            this.m = null;
            this.n = null;
            this.o = null;
        }
    }

    public CloudListBTFileActivity() {
        this.a = CloudListBTFileActivity.class.getSimpleName();
        this.b = null;
        this.c = false;
        this.d = new a();
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = new b(this);
        this.q = new c(this);
        this.r = new d(this);
        this.s = new e(this);
        this.t = new com.xunlei.downloadprovider.a.h.b(this.s);
        this.u = null;
    }

    static /* synthetic */ void a(CloudListBTFileActivity cloudListBTFileActivity, com.xunlei.downloadprovider.cloudlist.a.a aVar) {
        cloudListBTFileActivity.u = null;
        View inflate = LayoutInflater.from(cloudListBTFileActivity).inflate(2130968685, null);
        cloudListBTFileActivity.v = (RelativeLayout) inflate.findViewById(2131755565);
        LayoutParams layoutParams = (LayoutParams) cloudListBTFileActivity.v.getLayoutParams();
        layoutParams.height = g.a(cloudListBTFileActivity, 90.0f);
        cloudListBTFileActivity.v.setLayoutParams(layoutParams);
        cloudListBTFileActivity.w = (ImageView) inflate.findViewById(2131755566);
        cloudListBTFileActivity.x = (TextView) inflate.findViewById(2131755596);
        cloudListBTFileActivity.y = (TextView) inflate.findViewById(2131755597);
        cloudListBTFileActivity.z = (TextView) inflate.findViewById(2131755599);
        cloudListBTFileActivity.A = (TextView) inflate.findViewById(2131755598);
        cloudListBTFileActivity.B = inflate.findViewById(2131755600);
        cloudListBTFileActivity.C = inflate.findViewById(2131755602);
        cloudListBTFileActivity.B.setVisibility(XZBDevice.Wait);
        cloudListBTFileActivity.C.setVisibility(XZBDevice.Wait);
        cloudListBTFileActivity.z.setVisibility(0);
        cloudListBTFileActivity.A.setVisibility(0);
        if (!TextUtils.isEmpty(aVar.a)) {
            cloudListBTFileActivity.x.setText(aVar.a);
        }
        if (!TextUtils.isEmpty(aVar.g)) {
            cloudListBTFileActivity.A.setText(String.format(BrothersApplication.a().getString(2131231069), new Object[]{aVar.g}));
        }
        if (aVar.c > 0) {
            cloudListBTFileActivity.y.setText(String.format(BrothersApplication.a().getString(2131231066), new Object[]{com.xunlei.downloadprovider.d.a.a(aVar.c, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE)}));
        } else {
            cloudListBTFileActivity.y.setText(2131231088);
        }
        cloudListBTFileActivity.w.setImageResource(XLFileTypeUtil.d(aVar.a));
        cloudListBTFileActivity.z.setOnClickListener(new f(cloudListBTFileActivity, aVar));
        if (cloudListBTFileActivity.u == null) {
            cloudListBTFileActivity.u = new q(cloudListBTFileActivity);
            cloudListBTFileActivity.u.setContentView(inflate);
            cloudListBTFileActivity.u.d(BrothersApplication.a().getString(2131231064));
            cloudListBTFileActivity.u.setCanceledOnTouchOutside(true);
        }
        if (cloudListBTFileActivity.u != null) {
            cloudListBTFileActivity.u.show();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968680);
        f fVar = new f(findViewById(2131755560));
        this.f = fVar.g;
        this.g = fVar.i;
        this.g = (TextView) findViewById(R.id.titlebar_title);
        this.h = (PullToRefreshListView) findViewById(2131755561);
        this.i = (UnifiedLoadingView) findViewById(2131755563);
        this.j = findViewById(2131755562);
        this.k = (TextView) findViewById(2131755657);
        this.l = (TextView) findViewById(2131755658);
        this.m = (Button) findViewById(2131755659);
        this.n = (ImageView) findViewById(2131755656);
        this.i.a();
        this.l.setVisibility(XZBDevice.Wait);
        this.m.setVisibility(XZBDevice.Wait);
        View inflate = LayoutInflater.from(getApplicationContext()).inflate(2130968682, null);
        TextView textView = (TextView) inflate.findViewById(2131755584);
        textView.setText(2131231041);
        textView.setVisibility(0);
        this.k.setText(2131231042);
        this.f.setOnClickListener(this.p);
        this.h.setAdapter(this.d);
        this.h.setOnLastItemVisibleListener(this.q);
        this.h.setEmptyView(inflate);
        this.h.setMode(Mode.PULL_FROM_END);
        Intent intent = getIntent();
        if (intent != null) {
            this.e = a.a(intent);
            if (this.e != null) {
                if (TextUtils.isEmpty(this.e.d())) {
                    this.g.setText(getString(2131231078));
                } else {
                    this.g.setText(this.e.d());
                }
                this.e.a(this.r);
            }
            a(true);
        }
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onCreateTask(boolean z, int i) {
    }

    private final void a(boolean z) {
        if (this.e != null) {
            if (z) {
                this.e.a();
            }
            this.b = new Object();
            this.h.setRefreshing(true);
            this.e.a(this.b);
        }
    }

    static /* synthetic */ void h(CloudListBTFileActivity cloudListBTFileActivity) {
        cloudListBTFileActivity.c = true;
        cloudListBTFileActivity.h.setOnLastItemVisibleListener(null);
        cloudListBTFileActivity.h.setMode(Mode.DISABLED);
        if (cloudListBTFileActivity.e != null && cloudListBTFileActivity.e.b().size() > 0) {
            XLToast.a(cloudListBTFileActivity.getApplicationContext(), XLToastType.XLTOAST_TYPE_NORMAL, cloudListBTFileActivity.getString(2131231083));
        }
    }

    static /* synthetic */ void i(CloudListBTFileActivity cloudListBTFileActivity) {
        cloudListBTFileActivity.i.b();
        cloudListBTFileActivity.h.m();
        cloudListBTFileActivity.h.setMode(Mode.PULL_FROM_END);
        if (cloudListBTFileActivity.e == null || cloudListBTFileActivity.e.b().size() <= 0) {
            cloudListBTFileActivity.h.setVisibility(XZBDevice.Wait);
            if (com.xunlei.xllib.a.b.a(cloudListBTFileActivity.getApplicationContext())) {
                cloudListBTFileActivity.n.setImageResource(2130837903);
                cloudListBTFileActivity.k.setText(2131231042);
            } else {
                cloudListBTFileActivity.n.setImageResource(com.xunlei.tdlive.R.drawable.bg_invalid_network);
                cloudListBTFileActivity.k.setText(2131231043);
            }
            cloudListBTFileActivity.j.setVisibility(0);
            return;
        }
        cloudListBTFileActivity.d.notifyDataSetChanged();
        XLToast.a(cloudListBTFileActivity.getApplicationContext(), XLToastType.XLTOAST_TYPE_NORMAL, cloudListBTFileActivity.getString(2131231084));
    }

    static /* synthetic */ void b(CloudListBTFileActivity cloudListBTFileActivity, com.xunlei.downloadprovider.cloudlist.a.a aVar) {
        if (aVar != null && cloudListBTFileActivity.e != null) {
            VodUtil.a();
            String a = VodUtil.a(aVar.d, aVar.b);
            i iVar = new i();
            iVar.a = aVar.a;
            iVar.e = a;
            iVar.b = aVar.e;
            iVar.c = aVar.f;
            iVar.d = aVar.c;
            iVar.h = cloudListBTFileActivity.e.f();
            iVar.g = VodVideoFormat.flv;
            VodUtil.a();
            VodUtil.a((Context) cloudListBTFileActivity, iVar);
        }
    }

    static /* synthetic */ void c(CloudListBTFileActivity cloudListBTFileActivity, com.xunlei.downloadprovider.cloudlist.a.a aVar) {
        if (aVar != null && cloudListBTFileActivity.e != null) {
            com.xunlei.downloadprovider.model.g gVar = new com.xunlei.downloadprovider.model.g(cloudListBTFileActivity.e.e(), null, null);
            if (cloudListBTFileActivity.e.c == 101) {
                gVar.d = "space/space_lixian(bt)";
            } else if (cloudListBTFileActivity.e.c == 102) {
                gVar.d = "space/space_tongbu(bt)";
            }
            if (XLFileTypeUtil.a(aVar.a) != EFileCategoryType.E_OTHER_CATEGORY) {
                gVar.g = c.c(aVar.a);
            }
            if (cloudListBTFileActivity.e != null && (cloudListBTFileActivity.e instanceof i)) {
                StatReporter.reportOverallDownload("space_lixian");
            } else if (cloudListBTFileActivity.e != null && (cloudListBTFileActivity.e instanceof l)) {
                StatReporter.reportOverallDownload("space_tongbu");
            }
            cloudListBTFileActivity.createLocalTaskByGcid(aVar.a, aVar.c, aVar.e, aVar.f, null, 1, gVar, cloudListBTFileActivity.t, false);
        }
    }
}
