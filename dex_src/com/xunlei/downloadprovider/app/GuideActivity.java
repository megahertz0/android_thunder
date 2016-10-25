package com.xunlei.downloadprovider.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.app.ui.ScrollLayout;
import com.xunlei.downloadprovider.app.ui.ScrollLayout.c;
import com.xunlei.downloadprovider.frame.MainTabActivity;
import com.xunlei.downloadprovider.frame.advertisement.a.a;
import com.xunlei.downloadprovider.loading.k;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.ui.LoginActivity;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.model.protocol.report.b;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.downloadprovider.web.t;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.sdk.IHost;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xllib.b.d;
import java.util.ArrayList;
import org.android.agoo.message.MessageService;
import org.android.spdy.SpdyAgent;

public class GuideActivity extends BaseActivity implements OnClickListener, c {
    public static boolean a;
    public static boolean b;
    private static final int c;
    private ScrollLayout d;
    private ViewFlipper e;
    private LinearLayout f;
    private ImageView g;
    private ImageView h;
    private LinearLayout i;
    private ImageView[] j;
    private final boolean k;
    private int l;
    private int m;
    private TextView n;
    private TextView o;
    private TextView p;
    private TextView q;
    private CheckBox r;
    private View s;
    private TextView t;
    private TextView u;
    private String v;
    private LinearLayout w;
    private RelativeLayout x;
    private a y;
    private Handler z;

    public GuideActivity() {
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.k = false;
        this.l = -1;
        this.m = -1;
        this.v = com.umeng.a.d;
        this.z = new m(this);
    }

    static {
        a = false;
        c = com.xunlei.downloadprovider.i.a.a() ? XZBDevice.DOWNLOAD_LIST_RECYCLE : XZBDevice.DOWNLOAD_LIST_FAILED;
        b = false;
    }

    protected void onCreate(Bundle bundle) {
        LayoutParams layoutParams;
        if (VERSION.SDK_INT < 16) {
            getWindow().setFlags(JsInterface.MSG_JS_COLLECT_WEBSITE, JsInterface.MSG_JS_COLLECT_WEBSITE);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(1284);
        }
        setContentView(2130968786);
        this.n = (TextView) findViewById(2131756103);
        this.o = (TextView) findViewById(2131756104);
        this.p = (TextView) findViewById(2131756099);
        this.q = (TextView) findViewById(2131756106);
        if (getResources().getDisplayMetrics().density < 2.0f) {
            this.n.setTextSize(1, 22.0f);
            this.o.setTextSize(1, 12.0f);
            this.p.setTextSize(1, 12.0f);
            layoutParams = (LayoutParams) this.q.getLayoutParams();
            layoutParams.setMargins(R.styleable.Toolbar_logoDescription, 0, 0, 0);
            this.q.setLayoutParams(layoutParams);
        } else if (getResources().getDisplayMetrics().density == 2.0f) {
            this.n.setTextSize(1, 22.0f);
            this.o.setTextSize(1, 12.0f);
            this.p.setTextSize(1, 12.0f);
            layoutParams = (LayoutParams) this.q.getLayoutParams();
            layoutParams.setMargins(R.styleable.Toolbar_logoDescription, 0, 0, 0);
            this.q.setLayoutParams(layoutParams);
        }
        this.x = (RelativeLayout) findViewById(2131756097);
        this.r = (CheckBox) findViewById(2131756098);
        this.x.setOnClickListener(new l(this));
        this.d = (ScrollLayout) findViewById(2131756090);
        this.d.setOnScrollPageChangeListener(this);
        this.s = findViewById(2131756111);
        this.e = (ViewFlipper) findViewById(2131756112);
        this.i = (LinearLayout) findViewById(2131756102);
        this.h = (ImageView) findViewById(2131756109);
        this.t = (TextView) findViewById(2131756094);
        this.t.setOnClickListener(this);
        LoginHelper.a();
        if (LoginHelper.d()) {
            this.t.setText("\u7acb\u5373\u4f53\u9a8c");
            n.a(MessageService.MSG_DB_NOTIFY_REACHED);
        } else {
            n.a(MessageService.MSG_DB_READY_REPORT);
        }
        this.u = (TextView) findViewById(2131756095);
        this.u.setOnClickListener(this);
        LoginHelper.a();
        if (LoginHelper.d()) {
            this.u.setVisibility(XZBDevice.Wait);
        }
        this.g = (ImageView) findViewById(2131756110);
        this.h.setOnClickListener(this);
        this.g.setOnClickListener(this);
        if (getResources().getDisplayMetrics().density < 2.0f) {
            layoutParams = (LayoutParams) this.i.getLayoutParams();
            layoutParams.setMargins(R.styleable.AppCompatTheme_actionMenuTextAppearance, 160, R.styleable.AppCompatTheme_actionMenuTextAppearance, 0);
            this.i.setLayoutParams(layoutParams);
            layoutParams = (LayoutParams) this.h.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 160);
            this.h.setLayoutParams(layoutParams);
        } else if (getResources().getDisplayMetrics().density == 2.0f) {
            layoutParams = (LayoutParams) this.i.getLayoutParams();
            layoutParams.setMargins(R.styleable.AppCompatTheme_actionMenuTextAppearance, 280, R.styleable.AppCompatTheme_actionMenuTextAppearance, 0);
            this.i.setLayoutParams(layoutParams);
            layoutParams = (LayoutParams) this.h.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 280);
            this.h.setLayoutParams(layoutParams);
        }
        this.f = (LinearLayout) findViewById(2131756116);
        this.j = new ImageView[3];
        for (int i = 0; i < 3; i++) {
            this.j[i] = (ImageView) this.f.getChildAt(i);
        }
        b(0);
        this.w = (LinearLayout) findViewById(2131756096);
        if (c == 2) {
            if (this.w != null) {
                this.w.setVisibility(XZBDevice.Wait);
            }
            if (this.w != null && this.w.getVisibility() == 8) {
                if (this.u == null || this.u.getVisibility() != 0) {
                    layoutParams = (LayoutParams) this.t.getLayoutParams();
                    layoutParams.addRule(XZBDevice.Fail);
                    layoutParams.setMargins(0, 0, 0, g.a(this, 100.0f));
                    this.t.setLayoutParams(layoutParams);
                    this.t.invalidate();
                } else {
                    layoutParams = (LayoutParams) this.u.getLayoutParams();
                    layoutParams.addRule(XZBDevice.Fail);
                    layoutParams.setMargins(0, 0, 0, g.a(this, 50.0f));
                    this.u.setLayoutParams(layoutParams);
                    this.u.invalidate();
                }
            }
        }
        super.onCreate(bundle);
    }

    protected void onResume() {
        if (a) {
            b();
        }
        b = true;
        super.onResume();
    }

    public final void a(int i) {
        b(i);
    }

    public void onClick(View view) {
        LoginHelper.a();
        String str = LoginHelper.c() ? MessageService.MSG_DB_NOTIFY_REACHED : MessageService.MSG_DB_READY_REPORT;
        String str2 = c == 2 ? MessageService.MSG_DB_READY_REPORT : this.r.isChecked() ? MessageService.MSG_DB_NOTIFY_REACHED : MessageService.MSG_DB_NOTIFY_CLICK;
        switch (view.getId()) {
            case 2131756094:
                LoginHelper.a();
                if (LoginHelper.d()) {
                    n.a(str, "start", str2);
                    a();
                    return;
                }
                if (this.y == null) {
                    this.v = com.xunlei.downloadprovider.i.a.e();
                } else if (this.y != null && !TextUtils.isEmpty(this.y.h)) {
                    this.v = this.y.h;
                } else {
                    return;
                }
                n.a(str, "login", str2);
                Intent intent = new Intent(this, LoginActivity.class);
                intent.putExtra("login_from", getClass().getSimpleName());
                intent.putExtra("login_type", 1);
                if (this.r.isChecked() && this.w != null && this.w.getVisibility() == 0) {
                    intent.putExtra("download_url", this.v);
                }
                startActivity(intent);
            case 2131756095:
                n.a(str, "try", str2);
                a();
            case 2131756109:
                a();
            case 2131756110:
                a();
            default:
                break;
        }
    }

    private void a() {
        if (this.y == null) {
            a(false);
            return;
        }
        String str = this.y.m;
        if (str.equals(MessageService.MSG_DB_READY_REPORT)) {
            b();
        } else if (str.equals(MessageService.MSG_DB_NOTIFY_REACHED)) {
            if (TextUtils.isEmpty(this.y.f)) {
                b();
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString(JsInterface.FROM_KEY, "adv_guide");
            bundle.putString("ad_id", this.y.a);
            BrowserUtil.a();
            BrowserUtil.a(this, this.y.f, this.y.g, R.styleable.AppCompatTheme_dialogPreferredPadding, bundle);
        } else if (str.equals(MessageService.MSG_DB_NOTIFY_CLICK)) {
            if (TextUtils.isEmpty(this.y.f)) {
                b();
            }
            BrowserUtil.a();
            BrowserUtil.a((Context) this, (int) R.styleable.AppCompatTheme_dialogPreferredPadding, this.y.f, true, null, false);
        } else if (str.equals(MessageService.MSG_DB_NOTIFY_DISMISS)) {
            a(true);
        }
    }

    private void b() {
        MainTabActivity.a((Context) this, "thunder");
        finish();
        k.b();
    }

    private void a(boolean z) {
        StatReporter.reportPromotionGuidePageClickDownload();
        this.v = com.umeng.a.d;
        if (!this.r.isChecked() || this.w == null || this.w.getVisibility() == 8) {
            b();
            return;
        }
        String str;
        if (!z) {
            this.v = com.xunlei.downloadprovider.i.a.e();
            str = com.xunlei.downloadprovider.service.a.b + 0;
        } else if (this.y == null) {
            b();
            return;
        } else {
            String str2 = com.xunlei.downloadprovider.service.a.b + this.y.a;
            if (TextUtils.isEmpty(this.y.h)) {
                b();
                return;
            }
            this.v = this.y.h;
            Object a = com.xunlei.downloadprovider.frame.advertisement.b.c.a("click", this.y.a);
            if (!TextUtils.isEmpty(a)) {
                new t(null).a(a);
            }
            StatReporter.reportAdEvent("adv_binding_click", com.umeng.a.d, MessageService.MSG_DB_NOTIFY_REACHED, com.umeng.a.d, com.umeng.a.d, com.umeng.a.d);
            com.xunlei.downloadprovider.model.protocol.report.a.a("adv_binding_click", new b.a().a("ad_id", this.y.a).a("is_download", MessageService.MSG_DB_NOTIFY_REACHED));
            str = str2;
        }
        MainTabActivity.a((Activity) this, this.v, str);
        finish();
        k.b();
    }

    private void b(int i) {
        this.m = this.l;
        this.l = i;
        if (this.m != this.l && this.l >= 0 && this.l < 3) {
            switch (i) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    if (this.s != null) {
                        this.s.setVisibility(XZBDevice.Wait);
                    }
                    this.j[i].setImageResource(2130838314);
                    break;
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    if (this.s != null) {
                        this.s.setVisibility(XZBDevice.Wait);
                    }
                    this.j[i].setImageResource(2130838314);
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    if (this.s != null) {
                        this.s.setVisibility(0);
                    }
                    ArrayList arrayList = (ArrayList) com.xunlei.downloadprovider.frame.advertisement.b.a.a().i;
                    if (!d.a(arrayList)) {
                        this.y = (a) arrayList.get(0);
                        if (this.y != null) {
                            Object a = com.xunlei.downloadprovider.frame.advertisement.b.c.a("show", this.y.a);
                            if (!TextUtils.isEmpty(a)) {
                                new t(null).a(a);
                            }
                            StatReporter.reportAdEvent("adv_binding_show", com.umeng.a.d, com.umeng.a.d, com.umeng.a.d, com.umeng.a.d, com.umeng.a.d);
                            com.xunlei.downloadprovider.model.protocol.report.a.a("adv_binding_show", new b.a().a("ad_id", this.y.a));
                            this.n.setText(this.y.c);
                            this.o.setText(this.y.n);
                            this.p.setText(this.y.d);
                        }
                    }
                    this.j[i].setImageResource(2130838314);
                    break;
            }
            if (i - 1 >= 0) {
                this.j[i - 1].setImageResource(2130838315);
            }
            if (i + 1 < 3) {
                this.j[i + 1].setImageResource(2130838315);
            }
            this.f.setVisibility(0);
            if (this.l > this.m) {
                this.z.sendMessageDelayed(this.z.obtainMessage(IHost.HOST_NOFITY_REFRESH_LIST, 0, this.l), 100);
            } else {
                this.z.sendMessageDelayed(this.z.obtainMessage(IHost.HOST_NOFITY_REFRESH_LIST, 1, this.l), 100);
            }
        }
    }

    public void onBackPressed() {
        k.b();
    }

    static /* synthetic */ int b(GuideActivity guideActivity) {
        int id = guideActivity.e.getCurrentView().getId();
        if (id == 2131756113) {
            return 0;
        }
        if (id == 2131756114) {
            return 1;
        }
        return id == 2131756115 ? XZBDevice.DOWNLOAD_LIST_RECYCLE : 0;
    }
}
