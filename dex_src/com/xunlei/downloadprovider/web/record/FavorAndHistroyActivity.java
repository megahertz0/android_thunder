package com.xunlei.downloadprovider.web.record;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.open.yyb.TitleBar;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.app.ui.ScrollLayout;
import com.xunlei.downloadprovider.commonview.a.a;
import com.xunlei.downloadprovider.commonview.dialog.XLAlarmDialog;
import com.xunlei.downloadprovider.commonview.f;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.web.record.aa.b;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FavorAndHistroyActivity extends BaseActivity {
    private TextView A;
    private LinearLayout B;
    private LinearLayout C;
    private LinearLayout D;
    private TextView E;
    private RelativeLayout F;
    private TextView G;
    private TextView H;
    private b I;
    boolean a;
    private f b;
    private View c;
    private Button d;
    private TextView e;
    private RecordPageView f;
    private RecordPageView g;
    private RecordPageView h;
    private boolean i;
    private View j;
    private View k;
    private View l;
    private View m;
    private TextView n;
    private TextView o;
    private ScrollLayout p;
    private XLAlarmDialog q;
    private TextView r;
    private ImageView s;
    private LinearLayout t;
    private TextView u;
    private TextView v;
    private ImageView w;
    private long x;
    private Dialog y;
    private TextView z;

    public FavorAndHistroyActivity() {
        this.i = false;
        this.n = null;
        this.o = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = 0;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = null;
        this.D = null;
        this.E = null;
        this.I = new l(this);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968747);
        this.x = getSharedPreferences("favorandhistory", 0).getLong("favortimestamp", 0);
        View view = (ViewGroup) findViewById(2131755844);
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), g.a(this, 7.0f), view.getPaddingBottom());
        this.b = new f(view);
        this.b.f = view;
        ImageView imageView = this.b.g;
        TextView textView = this.b.j;
        imageView.setOnClickListener(new m(this));
        this.b.i.setText(2131230899);
        textView.setText("\u7f16\u8f91");
        textView.setVisibility(0);
        textView.setTextColor(getResources().getColorStateList(R.color.text_appearance_entry_primary_title_selector));
        textView.setOnClickListener(new n(this));
        this.c = findViewById(2131755845);
        Button button = (Button) this.c.findViewById(2131755651);
        this.d = (Button) this.c.findViewById(2131755653);
        this.e = (TextView) this.c.findViewById(2131755652);
        button.setText(R.string.cancel);
        button.setOnClickListener(new o(this));
        this.d.setOnClickListener(new p(this));
        c();
        this.j = findViewById(2131755847);
        this.k = this.j.findViewById(2131756829);
        this.n = (TextView) this.j.findViewById(2131756828);
        this.n.setText("\u6536\u85cf");
        this.n.setSelected(true);
        this.l = findViewById(2131755848);
        this.m = this.l.findViewById(2131756829);
        this.o = (TextView) this.l.findViewById(2131756828);
        this.o.setText("\u6d4f\u89c8\u5386\u53f2");
        this.j.setOnClickListener(new b(this));
        this.l.setOnClickListener(new c(this));
        this.p = (ScrollLayout) findViewById(2131755855);
        this.p.setOnScrollPageChangeListener(new d(this));
        a eVar = new e(this);
        this.f = (RecordPageView) findViewById(2131755856);
        this.f.setTabType("favor");
        this.f.setDelCallback(eVar);
        RecordPageView recordPageView = this.f;
        recordPageView.b = this.x;
        recordPageView.e = this;
        this.g = (RecordPageView) findViewById(2131755857);
        this.g.setTabType("history");
        this.g.setDelCallback(eVar);
        this.h = this.f;
        b();
        e();
        this.t = (LinearLayout) findViewById(2131755858);
        this.A = (TextView) findViewById(2131755860);
        this.z = (TextView) findViewById(2131755861);
        this.t.setVisibility(0);
        LinearLayout linearLayout = (LinearLayout) findViewById(2131755859);
        this.B = (LinearLayout) findViewById(2131755862);
        this.w = (ImageView) findViewById(2131755863);
        this.u = (TextView) findViewById(2131755864);
        this.v = (TextView) findViewById(2131755865);
        this.F = (RelativeLayout) findViewById(2131755849);
        this.G = (TextView) findViewById(2131755851);
        this.H = (TextView) findViewById(2131755853);
        View inflate = LayoutInflater.from(this).inflate(2130968749, null);
        this.y = new Dialog(this, 2131427871);
        this.y.setContentView(inflate);
        this.y.setCanceledOnTouchOutside(true);
        this.E = (TextView) inflate.findViewById(2131755869);
        this.C = (LinearLayout) inflate.findViewById(2131755868);
        this.D = (LinearLayout) inflate.findViewById(2131755870);
        linearLayout.setOnClickListener(new a(this));
        Intent intent = getIntent();
        if (intent != null) {
            this.a = intent.getBooleanExtra("locate_to_history_key", false);
            if (this.a && !this.h.getTabType().equals("\u6d4f\u89c8\u5386\u53f2")) {
                ScrollLayout scrollLayout = this.p;
                new StringBuilder().append(scrollLayout.getClass()).append("---snapToScreenRightNow---").append(Thread.currentThread().getId());
                int max = Math.max(0, Math.min(1, scrollLayout.getChildCount() - 1));
                scrollLayout.b = max;
                new StringBuilder("curScreen: ").append(scrollLayout.b);
                scrollLayout.clearFocus();
                scrollLayout.invalidate();
                if (scrollLayout.c != null) {
                    scrollLayout.c.a(max);
                }
                this.t.setVisibility(XZBDevice.Wait);
                return;
            }
            return;
        }
        StatReporter.reportFavorClickSyn(16005, "favornumtotal");
    }

    public void onBackPressed() {
        if (!this.i) {
            super.onBackPressed();
            a.b(this);
        } else if (this.q == null || !this.q.isShowing()) {
            a(false);
        } else {
            this.q.dismiss();
            this.q = null;
        }
    }

    @SuppressLint({"SimpleDateFormat"})
    private static String c(long j) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(j));
    }

    protected final void a(long j) {
        this.x = j;
        Editor edit = getSharedPreferences("favorandhistory", 0).edit();
        if (edit != null) {
            edit.putLong("favortimestamp", this.x);
            edit.commit();
        }
    }

    protected final void a(long j, String str) {
        Editor edit;
        if (str == null) {
            edit = getSharedPreferences("favorsynhistorytime", 0).edit();
            if (edit != null) {
                if (!TextUtils.isEmpty(LoginHelper.a().i)) {
                    edit.putLong(LoginHelper.a().i, j);
                } else if (!TextUtils.isEmpty(LoginHelper.a().e())) {
                    edit.putLong(LoginHelper.a().e(), j);
                }
                edit.commit();
                return;
            }
            return;
        }
        edit = getSharedPreferences("favorsynhistory", 0).edit();
        if (edit != null) {
            edit.putString(str, str);
            edit.commit();
        }
    }

    private void a(boolean z) {
        this.i = z;
        this.h.a(this.i, null);
        b(!z);
        c();
    }

    private void b(boolean z) {
        this.p.setScrollEnable(z);
    }

    private void b() {
        this.r = (TextView) this.h.findViewById(R.id.common_delete_buttom_btn_text);
        this.s = (ImageView) this.h.findViewById(R.id.common_delete_buttom_btn_icon);
    }

    private void c() {
        if (this.i) {
            this.c.setVisibility(0);
            this.b.f.setVisibility(XZBDevice.Wait);
            a(getResources().getString(2131230862));
            d();
            if (this.h != null && this.h.getTabType().equals("favor") && this.t != null) {
                this.t.setVisibility(XZBDevice.Wait);
                return;
            }
            return;
        }
        this.c.setVisibility(XZBDevice.Wait);
        this.b.f.setVisibility(0);
        if (this.h != null && this.h.getTabType().equals("favor")) {
            LoginHelper.a();
            if (LoginHelper.c() && this.t != null) {
                this.t.setVisibility(0);
            }
        }
    }

    private void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.d.setText(str);
            LayoutParams layoutParams = (LayoutParams) this.d.getLayoutParams();
            layoutParams.setMargins(0, 0, g.a(this, TitleBar.SHAREBTN_RIGHT_MARGIN), 0);
            this.d.setLayoutParams(layoutParams);
        }
    }

    private void d() {
        if (this.i) {
            this.e.setText(String.format("\u5df2\u7ecf\u9009\u62e9%d\u4e2a", new Object[]{Integer.valueOf(this.h.getSelectedSize())}));
        }
    }

    private void e() {
        if (this.h.getTabType().equals("favor")) {
            this.k.setVisibility(0);
            this.m.setVisibility(XZBDevice.Wait);
            if (this.t != null) {
                this.t.setVisibility(0);
                return;
            }
            return;
        }
        this.k.setVisibility(XZBDevice.Wait);
        this.m.setVisibility(0);
        if (this.t != null) {
            this.t.setVisibility(XZBDevice.Wait);
        }
    }

    protected void onResume() {
        LoginHelper.a();
        if (LoginHelper.c()) {
            long j;
            if (!this.a) {
                this.t.setVisibility(0);
            }
            this.A.setVisibility(0);
            this.z.setVisibility(0);
            if (!TextUtils.isEmpty(LoginHelper.a().i)) {
                this.A.setText(new StringBuilder("\u540c\u6b65\u81f3").append(LoginHelper.a().i).toString());
                j = getSharedPreferences("favorsynhistorytime", 0).getLong(LoginHelper.a().i, 0);
            } else if (TextUtils.isEmpty(LoginHelper.a().e())) {
                this.A.setVisibility(XZBDevice.Wait);
                j = 0;
            } else {
                this.A.setText(new StringBuilder("\u540c\u6b65\u81f3").append(LoginHelper.a().e()).toString());
                j = getSharedPreferences("favorsynhistorytime", 0).getLong(LoginHelper.a().e(), 0);
            }
            if (j > 0) {
                String str = com.umeng.a.d;
                if (!TextUtils.isEmpty(LoginHelper.a().i)) {
                    str = getSharedPreferences("favorsynhistory", 0).getString(LoginHelper.a().i, com.umeng.a.d);
                } else if (!TextUtils.isEmpty(LoginHelper.a().e())) {
                    str = getSharedPreferences("favorsynhistory", 0).getString(LoginHelper.a().e(), com.umeng.a.d);
                }
                if (str.length() <= 0) {
                    this.z.setText("\u6682\u672a\u540c\u6b65\u8fc7");
                } else if (str.equals(LoginHelper.a().i) || str.equals(LoginHelper.a().e())) {
                    this.z.setText(new StringBuilder("\u4e0a\u6b21\u540c\u6b65:").append(c(j)).toString());
                } else {
                    this.z.setText("\u6682\u672a\u540c\u6b65\u8fc7");
                }
            } else {
                this.z.setText("\u6682\u672a\u540c\u6b65\u8fc7");
            }
            this.F.setVisibility(XZBDevice.Wait);
            this.w.setVisibility(0);
            this.u.setText("\u7acb\u5373\u540c\u6b65");
            this.B.setOnClickListener(new f(this));
            this.C.setOnClickListener(new g(this));
            this.D.setOnClickListener(new h(this));
            if (this.f != null) {
                this.f.setUpdateCallback(new i(this));
            }
        } else {
            f();
        }
        if (!this.i) {
            this.f.a();
            this.g.a();
        }
        super.onResume();
    }

    private void f() {
        if (this.p.getCurScreen() == 0) {
            LoginHelper.a();
            if (!LoginHelper.c()) {
                this.z.setVisibility(0);
                this.z.setText("\u767b\u5f55\u5e10\u53f7\uff0c\u4eab\u53d7\u6536\u85cf\u5939\u540c\u6b65");
                this.u.setText("\u7acb\u5373\u767b\u5f55");
                this.A.setVisibility(XZBDevice.Wait);
                this.w.setVisibility(XZBDevice.Wait);
                this.B.setOnClickListener(new j(this));
                if (com.xunlei.c.a.b.a(BrothersApplication.a())) {
                    this.F.setVisibility(0);
                    this.H.setText(2131231048);
                    this.G.setText(2131230898);
                    this.H.setOnClickListener(new k(this));
                } else {
                    this.F.setVisibility(XZBDevice.Wait);
                }
                LoginHelper.a();
                if (!LoginHelper.c()) {
                    this.t.setVisibility(XZBDevice.Wait);
                    return;
                }
                return;
            }
            return;
        }
        this.F.setVisibility(XZBDevice.Wait);
    }

    protected final void a() {
        if (!TextUtils.isEmpty(LoginHelper.a().i)) {
            a(0, LoginHelper.a().i);
        } else if (!TextUtils.isEmpty(LoginHelper.a().e())) {
            a(0, LoginHelper.a().e());
        }
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.f.setIsDestry(true);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    static /* synthetic */ void b(FavorAndHistroyActivity favorAndHistroyActivity, boolean z) {
        if (z) {
            favorAndHistroyActivity.r.setEnabled(true);
            favorAndHistroyActivity.s.setEnabled(true);
            return;
        }
        favorAndHistroyActivity.r.setEnabled(false);
        favorAndHistroyActivity.s.setEnabled(false);
    }

    static /* synthetic */ void a(FavorAndHistroyActivity favorAndHistroyActivity, int i) {
        if (favorAndHistroyActivity.q == null) {
            favorAndHistroyActivity.q = new XLAlarmDialog(favorAndHistroyActivity);
            favorAndHistroyActivity.q.setContent(String.format(BrothersApplication.a().getString(2131231063), new Object[]{Integer.valueOf(i)}));
            favorAndHistroyActivity.q.setConfirmButtonText(BrothersApplication.a().getString(R.string.ok));
            favorAndHistroyActivity.q.setCancelButtonText(BrothersApplication.a().getString(R.string.cancel));
            favorAndHistroyActivity.q.setOnClickConfirmButtonListener(new q(favorAndHistroyActivity));
            favorAndHistroyActivity.q.setOnClickCancelButtonListener(new r(favorAndHistroyActivity));
            favorAndHistroyActivity.q.setOnDismissListener(new s(favorAndHistroyActivity));
            favorAndHistroyActivity.q.show();
        }
    }

    static /* synthetic */ Animation t(FavorAndHistroyActivity favorAndHistroyActivity) {
        Animation loadAnimation = AnimationUtils.loadAnimation(favorAndHistroyActivity, 2131034153);
        loadAnimation.setInterpolator(new LinearInterpolator());
        return loadAnimation;
    }
}
