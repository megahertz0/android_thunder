package com.xunlei.downloadprovider.web.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.AutoScrollHelper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.f;
import com.android.volley.toolbox.o;
import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;
import com.nostra13.universalimageloader.core.c;
import com.nostra13.universalimageloader.core.d;
import com.nostra13.universalimageloader.core.e;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.commonview.ErrorView;
import com.xunlei.downloadprovider.commonview.UnifiedLoadingView;
import com.xunlei.downloadprovider.homepage.b;
import com.xunlei.downloadprovider.vod.VodPlayerActivity;
import com.xunlei.downloadprovider.web.base.f.a;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.WebBrowserActivity;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;

public class KandanListActivity extends BaseActivity implements a {
    private static String a;
    private View A;
    private View B;
    private f C;
    private String D;
    private d E;
    private c F;
    private b G;
    private String b;
    private View c;
    private ImageView d;
    private TextView e;
    private View f;
    private String g;
    private boolean h;
    private int i;
    private ListView j;
    private UnifiedLoadingView k;
    private ErrorView l;
    private r m;
    private TextView n;
    private TextView o;
    private ImageView p;
    private View q;
    private boolean r;
    private View s;
    private LinearLayout t;
    private Handler u;
    private boolean v;
    private int w;
    private int x;
    private boolean y;
    private boolean z;

    public enum From {
        HOMEPAGE1("homepage_1"),
        HOMEPAGE3("homepage_3");
        private final String a;

        static {
            String str = "homepage_1";
            HOMEPAGE1 = new com.xunlei.downloadprovider.web.base.KandanListActivity.From("HOMEPAGE1", 0, "homepage_1");
            str = "homepage_3";
            HOMEPAGE3 = new com.xunlei.downloadprovider.web.base.KandanListActivity.From("HOMEPAGE3", 1, "homepage_3");
            b = new com.xunlei.downloadprovider.web.base.KandanListActivity.From[]{HOMEPAGE1, HOMEPAGE3};
        }

        private From(String str) {
            this.a = str;
        }

        public final String getName() {
            return this.a;
        }

        public final String toString() {
            return this.a;
        }
    }

    public KandanListActivity() {
        this.r = false;
        this.v = false;
        this.w = 8;
        this.y = false;
        this.z = true;
    }

    static /* synthetic */ void i(KandanListActivity kandanListActivity) {
        Drawable drawable;
        if (kandanListActivity.r) {
            drawable = kandanListActivity.getResources().getDrawable(2130837605);
            kandanListActivity.x = 2;
        } else {
            drawable = kandanListActivity.getResources().getDrawable(2130837603);
            kandanListActivity.x = kandanListActivity.w;
        }
        drawable.setBounds(0, 0, g.a(kandanListActivity, 9.0f), g.a(kandanListActivity, 6.0f));
        kandanListActivity.o.setCompoundDrawables(null, null, null, drawable);
        if (kandanListActivity.z) {
            kandanListActivity.u.post(new m(kandanListActivity));
            return;
        }
        if (kandanListActivity.r) {
            kandanListActivity.x = kandanListActivity.w;
        } else {
            kandanListActivity.x = 2;
        }
        kandanListActivity.o.setMaxLines(kandanListActivity.x);
        kandanListActivity.b();
    }

    static /* synthetic */ int l(KandanListActivity kandanListActivity) {
        int i = kandanListActivity.x;
        kandanListActivity.x = i + 1;
        return i;
    }

    static /* synthetic */ int p(KandanListActivity kandanListActivity) {
        int i = kandanListActivity.x;
        kandanListActivity.x = i - 1;
        return i;
    }

    static {
        a = KandanListActivity.class.getSimpleName();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.G = new b(this);
        Intent intent = getIntent();
        this.g = intent.getStringExtra(WebBrowserActivity.EXTRA_TITLE);
        this.D = intent.getStringExtra("list_id");
        this.b = intent.getStringExtra("from");
        this.u = new Handler();
        this.E = d.a();
        this.E.a(e.a(this));
        this.F = initDisplayOption();
        setContentView(2130968803);
        this.c = findViewById(2131756159);
        this.c.setOnClickListener(new o(this));
        this.d = (ImageView) findViewById(2131756160);
        this.e = (TextView) findViewById(2131756161);
        this.f = findViewById(2131756162);
        this.j = (ListView) findViewById(2131756158);
        this.j.setHeaderDividersEnabled(false);
        this.j.setFooterDividersEnabled(false);
        this.q = LayoutInflater.from(this).inflate(2130968862, null);
        this.t = (LinearLayout) this.q.findViewById(2131756474);
        this.n = (TextView) this.q.findViewById(R.id.tv_title);
        this.o = (TextView) this.q.findViewById(2131756475);
        this.o.getViewTreeObserver().addOnPreDrawListener(new p(this));
        this.p = (ImageView) this.q.findViewById(2131756030);
        this.s = this.q.findViewById(2131756471);
        this.B = this.q.findViewById(2131756472);
        this.A = this.q.findViewById(2131756473);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, com.xunlei.xllib.a.d.b(this));
        layoutParams.setMargins(0, g.a(this, 86.0f), 0, 0);
        this.A.setLayoutParams(layoutParams);
        this.A.setVisibility(0);
        this.A.setAlpha(AutoScrollHelper.RELATIVE_UNSPECIFIED);
        this.j.addHeaderView(this.q);
        this.m = new r(this, (byte) 0);
        this.j.setAdapter(this.m);
        this.k = (UnifiedLoadingView) findViewById(2131756046);
        this.k.setType(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        this.k.setBackgroundColor(Color.parseColor("#ffffff"));
        this.k.c();
        this.l = (ErrorView) findViewById(2131756034);
        this.l.setErrorType(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        this.l.setActionButtonListener(new q(this));
        this.j.setOnScrollListener(new j(this));
        this.j.setOnItemClickListener(new k(this));
        this.o.setOnClickListener(new l(this));
        if (!TextUtils.isEmpty(this.g)) {
            this.e.setText(this.g);
            this.n.setText(this.g);
        }
        this.C = new f();
        this.C.a = this;
        c();
        this.i = g.a(this, 187.0f);
        this.u.post(new i(this));
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        this.G.a(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    private void b() {
        this.u.post(new n(this));
    }

    private void c() {
        this.k.a();
        f fVar = this.C;
        String str = this.D;
        if (fVar.a != null) {
            Request oVar = new o(0, String.format("http://subject.m.sjzhushou.com/subject/list/%s?_=%s", new Object[]{str, Long.valueOf(System.currentTimeMillis() / 300000)}), null, new g(fVar), new h(fVar));
            oVar.setRetryPolicy(new f(10000, 1, 1.0f));
            fVar.b.a(oVar);
        }
    }

    public static void a(Context context, From from, String str, String str2) {
        Intent intent = new Intent(context, KandanListActivity.class);
        intent.putExtra("list_id", str);
        intent.putExtra(WebBrowserActivity.EXTRA_TITLE, str2);
        intent.putExtra("from", from.getName());
        context.startActivity(intent);
    }

    public void onClickGoback(View view) {
        onBackPressed();
    }

    public final void a(boolean z, t tVar, List<s> list) {
        this.k.b();
        if (z) {
            this.l.setVisibility(0);
            return;
        }
        if (tVar != null) {
            this.o.setText(com.umeng.a.d);
            this.o.setMaxLines(InMobiClientPositioning.NO_REPEAT);
            this.v = false;
            this.n.setText(tVar.b);
            this.e.setText(tVar.b);
            this.o.setText(tVar.c);
            this.E.a(tVar.f, this.p, this.F);
        }
        if (list == null || list.size() <= 0) {
            this.l.setErrorType(0);
            this.l.setVisibility(0);
            return;
        }
        r rVar = this.m;
        if (rVar.a == null) {
            rVar.a = new ArrayList();
        }
        rVar.a.clear();
        rVar.a.addAll(list);
        rVar.notifyDataSetChanged();
    }

    protected void onDestroy() {
        if (this.C != null) {
            this.C.b.a((Object) this);
        }
        super.onDestroy();
    }

    static /* synthetic */ void a(KandanListActivity kandanListActivity, float f) {
        if (f == 0.0f) {
            kandanListActivity.d.setImageResource(2130839158);
        } else {
            kandanListActivity.d.setImageResource(2130839458);
        }
        int i = (int) (255.0f * f);
        kandanListActivity.c.setBackgroundColor(Color.argb(i, VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX, VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX, VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX));
        kandanListActivity.e.setTextColor(Color.argb(i, R.styleable.AppCompatTheme_buttonBarButtonStyle, R.styleable.AppCompatTheme_buttonBarButtonStyle, R.styleable.AppCompatTheme_buttonBarButtonStyle));
        int color = kandanListActivity.getResources().getColor(2131689799);
        kandanListActivity.f.setBackgroundColor(Color.argb(i, Color.red(color), Color.green(color), Color.blue(color)));
    }

    static /* synthetic */ int d(KandanListActivity kandanListActivity) {
        View view = kandanListActivity.q;
        if (view == null) {
            return 0;
        }
        int firstVisiblePosition = kandanListActivity.j.getFirstVisiblePosition();
        int top = view.getTop();
        new StringBuilder("height=>").append(view.getHeight());
        return (view.getHeight() * firstVisiblePosition) + (-top);
    }
}
