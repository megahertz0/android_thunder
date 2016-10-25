package com.xunlei.downloadprovider.member.register.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.common.member.XLUserUtil;
import com.xunlei.common.register.XLRegisterListener;
import com.xunlei.common.register.XLRegisterUtil;
import com.xunlei.downloadprovider.a.b;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.commonview.f;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.d;
import com.xunlei.downloadprovider.member.login.ui.LoginActivity;
import com.xunlei.downloadprovider.member.register.view.e;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class MobileSetupActivity extends BaseActivity {
    public static boolean a;
    private d A;
    private XLRegisterListener B;
    private f b;
    private EditText c;
    private TextView d;
    private TextView e;
    private EditText f;
    private EditText g;
    private ImageView h;
    private ImageView i;
    private ImageView j;
    private View k;
    private View l;
    private TextView m;
    private ProgressDialog n;
    private String o;
    private int p;
    private String q;
    private boolean r;
    private int s;
    private String t;
    private boolean u;
    private String v;
    private String w;
    private String x;
    private e y;
    private a z;

    private static class a {
        volatile boolean a;
        Handler b;
        Thread c;

        public static interface a {
            void a(int i);
        }

        public a(a aVar) {
            this.a = false;
            this.b = new t(this, Looper.getMainLooper(), aVar);
            this.c = new Thread(new u(this));
        }
    }

    public MobileSetupActivity() {
        this.r = false;
        this.s = 0;
        this.t = com.umeng.a.d;
        this.v = com.umeng.a.d;
        this.w = com.umeng.a.d;
        this.A = new e(this);
        this.B = new f(this);
    }

    static /* synthetic */ void i() {
    }

    static /* synthetic */ void o(MobileSetupActivity mobileSetupActivity) {
        if (mobileSetupActivity.y != null) {
            e eVar = mobileSetupActivity.y;
            eVar.b.setImageResource(2130837650);
            eVar.d.setVisibility(XZBDevice.Wait);
        }
        mobileSetupActivity.b(2131232112);
        mobileSetupActivity.v = null;
        mobileSetupActivity.w = null;
    }

    static /* synthetic */ void p(MobileSetupActivity mobileSetupActivity) {
        mobileSetupActivity.f();
        com.xunlei.downloadprovider.commonview.dialog.d dVar = new com.xunlei.downloadprovider.commonview.dialog.d(mobileSetupActivity);
        dVar.setTitle((CharSequence) "\u6ce8\u518c\u5931\u8d25");
        dVar.b((CharSequence) "\u8be5\u624b\u673a\u53f7\u5df2\u6ce8\u518c\uff0c\u8bf7\u4f7f\u7528\u624b\u673a\u5feb\u6377\u767b\u5f55");
        TextView textView = (TextView) dVar.findViewById(R.id.dlg_content);
        TextView textView2 = (TextView) dVar.findViewById(R.id.dlg_cancel_btn);
        TextView textView3 = (TextView) dVar.findViewById(R.id.dlg_confirm_btn);
        ((TextView) dVar.findViewById(R.id.dlg_title)).setTextSize(17.0f);
        textView.setTextSize(14.0f);
        textView2.setTextSize(15.0f);
        textView3.setTextSize(15.0f);
        dVar.c("\u53d6\u6d88");
        dVar.d("\u624b\u673a\u5feb\u6377\u767b\u5f55");
        dVar.a(new i(mobileSetupActivity));
        dVar.b(new j(mobileSetupActivity));
        dVar.show();
    }

    public static void a(Context context, int i, String str) {
        String str2 = com.umeng.a.d;
        Intent intent = new Intent(context, MobileSetupActivity.class);
        intent.putExtra(JsInterface.FUNPLAY_AD_TRPE, i);
        intent.putExtra("login_from", str);
        intent.putExtra("phone_number", str2);
        if (context instanceof LoginActivity) {
            intent.putExtra("from", "login_home");
        } else if (context instanceof MobileSetupActivity) {
            intent.putExtra("from", "register_alert");
        }
        context.startActivity(intent);
    }

    protected void onCreate(Bundle bundle) {
        int i;
        super.onCreate(bundle);
        setContentView(2130968874);
        XLRegisterUtil.getInstance().init(this, R.styleable.AppCompatTheme_textAppearanceLargePopupMenu, "shoulei_android", getString(R.string.version), b.d(), "34a062aaa22f906fca4fefe9fb3a3021");
        Intent intent = getIntent();
        this.p = intent.getIntExtra(JsInterface.FUNPLAY_AD_TRPE, 0);
        this.o = intent.getStringExtra("login_from");
        if (this.o == null) {
            this.o = com.umeng.a.d;
        }
        this.q = intent.getStringExtra("phone_number");
        this.r = intent.getBooleanExtra("fromHomeMemeberFree", false);
        this.c = (EditText) findViewById(2131756555);
        this.d = (TextView) findViewById(2131756556);
        this.e = (TextView) findViewById(2131756557);
        this.f = (EditText) findViewById(2131756558);
        this.k = findViewById(2131756562);
        this.m = (TextView) findViewById(2131756563);
        if (this.p == 1) {
            if (!(this.q == null || com.umeng.a.d.equals(this.q))) {
                this.c.setText(this.q);
                this.c.setSelection(this.q.length());
            }
            this.m.setVisibility(0);
            TextView textView = this.m;
            CharSequence spannableString = new SpannableString(getString(2131232167));
            spannableString.setSpan(new c(this), 0, spannableString.length(), R.styleable.AppCompatTheme_actionModeCopyDrawable);
            textView.setText(getString(2131232134));
            textView.append(spannableString);
            textView.setHighlightColor(0);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            this.m.setVisibility(XZBDevice.Wait);
        }
        this.l = findViewById(2131756559);
        View findViewById = findViewById(2131756560);
        if (this.p == 2) {
            i = 0;
        } else {
            i = 8;
        }
        findViewById.setVisibility(i);
        this.g = (EditText) findViewById(2131756561);
        this.h = (ImageView) findViewById(2131756554);
        this.i = (ImageView) findViewById(2131756455);
        this.j = (ImageView) findViewById(2131756450);
        this.d.setOnClickListener(new l(this));
        this.k.setOnClickListener(new m(this));
        this.l.setOnClickListener(new n(this));
        this.c.addTextChangedListener(new o(this));
        this.c.setOnFocusChangeListener(new p(this));
        this.f.setOnFocusChangeListener(new q(this));
        this.f.addTextChangedListener(new r(this));
        this.g.setOnFocusChangeListener(new s(this));
        this.b = new f((Activity) this);
        this.b.g.setOnClickListener(new d(this));
        this.b.i.setText(a());
        a(this.b.k);
        LoginHelper.a().a(this.A);
    }

    protected void b() {
        this.q = this.c.getText().toString();
        this.f.setText(com.umeng.a.d);
        if (this.p == 2) {
            XLRegisterUtil.getInstance().checkBind(this.q, 1);
            c(2131232144);
            return;
        }
        a(com.umeng.a.d, true);
    }

    protected void c() {
        String d = d();
        String e = e();
        String toString = this.g.getText().toString();
        if (!g()) {
            return;
        }
        if (this.p == 1) {
            XLRegisterUtil.getInstance().phoneFastLogin(d, e, com.umeng.a.d);
            c(2131232143);
            return;
        }
        XLRegisterUtil.getInstance().phoneFastRegister(d, e, toString, com.umeng.a.d);
        c(2131232142);
    }

    protected final String d() {
        return this.c.getText().toString();
    }

    protected final String e() {
        return this.f.getText().toString();
    }

    protected String a() {
        return getString(this.p == 2 ? 2131232141 : 2131231667);
    }

    protected void a(TextView textView) {
    }

    protected void onResume() {
        super.onResume();
        k();
        XLRegisterUtil.getInstance().attachListener(this.B);
    }

    protected void onStop() {
        super.onStop();
        f();
        XLRegisterUtil.getInstance().dettachListener(this.B);
        XLRegisterUtil.getInstance().uninit();
    }

    protected void onDestroy() {
        super.onDestroy();
        LoginHelper.a().v = null;
        LoginHelper.a().b(this.A);
        if (this.z != null) {
            this.z.a = true;
        }
    }

    protected final void a(boolean z) {
        LoginHelper a = LoginHelper.a();
        int i = this.s;
        String str = this.t;
        a.v = new g(this, z);
        XLUserUtil.getInstance().userLoginWithSessionid(i, str, R.styleable.AppCompatTheme_textAppearanceLargePopupMenu, 0, a.I, null);
    }

    protected final void a(int i) {
        a(getString(i));
    }

    protected final void a(String str) {
        com.xunlei.downloadprovider.member.register.view.a.a(this, 1, str).a();
    }

    protected final void b(String str) {
        f();
        com.xunlei.downloadprovider.member.register.view.a.a(this, XZBDevice.DOWNLOAD_LIST_RECYCLE, str).a();
    }

    protected final void b(int i) {
        b(getString(i));
    }

    protected final void c(int i) {
        f();
        this.n = new ProgressDialog(this);
        this.n.setProgressStyle(0);
        this.n.setMessage(getResources().getString(i));
        this.n.setCancelable(false);
        this.n.show();
    }

    protected final void f() {
        if (this.n != null) {
            this.n.dismiss();
            this.n = null;
        }
    }

    protected final boolean g() {
        if (com.xunlei.xllib.a.b.a(this)) {
            return true;
        }
        b(2131232950);
        return false;
    }

    private boolean j() {
        Object d = d();
        if (TextUtils.isEmpty(d)) {
            b(getString(2131232156));
            return false;
        } else if (d.matches("^1[0-9]{10}$")) {
            return true;
        } else {
            b(getString(2131232154));
            return false;
        }
    }

    private void a(String str, boolean z) {
        if (g()) {
            if (z) {
                c(2131232144);
            }
            XLRegisterUtil.getInstance().sendPhoneMessage(this.q, XZBDevice.DOWNLOAD_LIST_RECYCLE, null, this.v, str, this.w);
        }
    }

    protected final void h() {
        this.z = new a(new k(this));
        this.z.c.start();
    }

    private void a(View view) {
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void k() {
        if (this.c.getText().length() > 0) {
            if (this.f.getText().length() > 0) {
                this.k.setEnabled(true);
            } else {
                this.k.setEnabled(false);
            }
            this.d.setEnabled(true);
            return;
        }
        this.k.setEnabled(false);
        this.d.setEnabled(false);
    }

    static /* synthetic */ void a(MobileSetupActivity mobileSetupActivity, View view) {
        mobileSetupActivity.a(view);
        if (mobileSetupActivity.j()) {
            Object obj;
            if (TextUtils.isEmpty(mobileSetupActivity.e())) {
                mobileSetupActivity.b(mobileSetupActivity.getString(2131232157));
                obj = null;
            } else {
                obj = 1;
            }
            if (obj != null) {
                mobileSetupActivity.c();
                if (mobileSetupActivity.p == 2) {
                    String str = "phone_reg_submit";
                    com.xunlei.downloadprovider.member.register.b.a(g.a("android_phone_register", str, str));
                }
            }
        }
        mobileSetupActivity.f();
        if (mobileSetupActivity.p == 2) {
            String str2 = "phone_reg_submit";
            com.xunlei.downloadprovider.member.register.b.a(g.a("android_phone_register", str2, str2));
        }
    }

    static /* synthetic */ void c(MobileSetupActivity mobileSetupActivity, View view) {
        if (view.requestFocus()) {
            ((InputMethodManager) mobileSetupActivity.getSystemService("input_method")).showSoftInput(view, 1);
        }
    }

    static /* synthetic */ void m(MobileSetupActivity mobileSetupActivity) {
        if (mobileSetupActivity.y != null) {
            mobileSetupActivity.y.dismiss();
        }
    }

    static /* synthetic */ void n(MobileSetupActivity mobileSetupActivity) {
        mobileSetupActivity.y = new e(mobileSetupActivity);
        mobileSetupActivity.y.a = new h(mobileSetupActivity);
        mobileSetupActivity.y.show();
    }

    static /* synthetic */ void q(MobileSetupActivity mobileSetupActivity) {
        XLRegisterUtil.getInstance().dettachListener(mobileSetupActivity.B);
        mobileSetupActivity.startActivity(new Intent(mobileSetupActivity, RegisterSuccessActivity.class));
    }

    static /* synthetic */ void b(MobileSetupActivity mobileSetupActivity, int i) {
        if (i > 0) {
            mobileSetupActivity.e.setText(String.format("%s\u79d2\u540e\u91cd\u65b0\u83b7\u53d6", new Object[]{Integer.valueOf(i)}));
            mobileSetupActivity.e.setVisibility(0);
            mobileSetupActivity.d.setVisibility(XZBDevice.Wait);
            return;
        }
        mobileSetupActivity.e.setVisibility(XZBDevice.Wait);
        mobileSetupActivity.d.setVisibility(0);
    }
}
