package com.xunlei.downloadprovider.member.login.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.umeng.message.MsgConstant;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.downloadprovider.commonview.AnimationDot;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivityFragment;
import com.xunlei.downloadprovider.frame.MainTabActivity;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.d;
import com.xunlei.downloadprovider.member.login.j;
import com.xunlei.downloadprovider.member.register.ui.BindMobileActivity;
import com.xunlei.downloadprovider.member.register.view.MailBoxAssociateView;
import com.xunlei.downloadprovider.member.register.view.c;
import com.xunlei.downloadprovider.model.g;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.downloadprovider.util.ai;
import com.xunlei.downloadprovider.vod.VodPlayerActivity;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.downloadprovider.web.base.WebViewNormalActivity;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xllib.a.b;
import java.util.ArrayList;
import java.util.List;
import org.android.spdy.SpdyAgent;

public class LoginActivity extends ThunderTask {
    private ImageView A;
    private LoginHelper B;
    private d C;
    private String D;
    private boolean E;
    private boolean F;
    private boolean G;
    private boolean H;
    private boolean I;
    private boolean J;
    private String K;
    private OnClickListener L;
    long a;
    private View b;
    private ImageView c;
    private View d;
    private MailBoxAssociateView e;
    private EditText f;
    private View g;
    private View h;
    private TextView i;
    private View j;
    private TextView k;
    private AnimationDot l;
    private View m;
    private EditText n;
    private ImageView o;
    private View p;
    private ImageView q;
    private ImageView r;
    private ImageView s;
    private TextView t;
    private TextView u;
    private EditText v;
    private ImageView w;
    private View x;
    private View y;
    private ImageView z;

    private class a implements TextWatcher {
        View a;

        a(View view) {
            this.a = view;
        }

        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (!TextUtils.isEmpty(charSequence) && charSequence.length() > 1) {
                LoginActivity.c(LoginActivity.this, charSequence.toString());
            }
            LoginActivity.this.c();
        }

        public final void afterTextChanged(Editable editable) {
            this.a.setVisibility(editable.length() > 0 ? 0 : XZBDevice.DOWNLOAD_LIST_ALL);
        }
    }

    public LoginActivity() {
        this.F = true;
        this.H = false;
        this.I = false;
        this.K = com.umeng.a.d;
        this.L = new n(this);
    }

    static /* synthetic */ void c(LoginActivity loginActivity, String str) {
        MailBoxAssociateView mailBoxAssociateView = loginActivity.e;
        int indexOf = str.indexOf("@");
        if (indexOf == -1 || str.length() <= indexOf + 1) {
            mailBoxAssociateView.a = new c(loginActivity, mailBoxAssociateView.getResources().getStringArray(2131623940), str.substring(0, str.length() - 1));
        } else {
            String substring = str.substring(indexOf + 1);
            String[] stringArray = loginActivity.getResources().getStringArray(2131623940);
            List arrayList = new ArrayList();
            String toString = new StringBuilder("@").append(substring).toString();
            int length = stringArray.length;
            for (int i = 0; i < length; i++) {
                String str2 = stringArray[i];
                if (str2.startsWith(toString)) {
                    arrayList.add(str2);
                }
            }
            String[] strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
            if (strArr.length > 0) {
                mailBoxAssociateView.a = new c(loginActivity, strArr, str.substring(0, indexOf));
            } else {
                mailBoxAssociateView.a = null;
                mailBoxAssociateView.dismissDropDown();
            }
        }
        mailBoxAssociateView.setAdapter(mailBoxAssociateView.a);
    }

    static /* synthetic */ void k(LoginActivity loginActivity) {
        if (b.a(loginActivity)) {
            String trim = loginActivity.e.getText().toString().trim();
            String trim2 = loginActivity.f.getText().toString().trim();
            Object trim3 = loginActivity.n.getText().toString().trim();
            loginActivity.a();
            if (loginActivity.D == null) {
                loginActivity.D = com.umeng.a.d;
            }
            LoginHelper loginHelper = loginActivity.B;
            String str = loginActivity.D;
            if (!trim.equalsIgnoreCase(loginHelper.e())) {
                loginHelper.t();
            }
            if (!(TextUtils.isEmpty(trim) || TextUtils.isEmpty(trim2))) {
                loginHelper.p = false;
                Object obj = LoginHelper.c;
                if (!TextUtils.isEmpty(trim3)) {
                    obj = LoginHelper.b;
                }
                LoginHelper.d = true;
                loginHelper.A = trim;
                loginHelper.a(trim2);
                j.a();
                j.a(trim, trim2, str, trim3, loginHelper.I, obj);
                loginHelper.F = false;
            }
            loginActivity.a(true, false);
            return;
        }
        loginActivity.a(2131232950);
        loginActivity.a();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent.hasExtra("download_url")) {
            String string = intent.getExtras().getString("download_url");
            createLocalTask(string, null, 0, null, null, null, 1, new g(37, string, string), null, false);
        }
        LoginHelper.d = false;
        this.a = System.currentTimeMillis();
        StatReporter.reportEnterLoginpage();
        if (intent.hasExtra("login_from")) {
            this.K = intent.getExtras().getString("login_from");
            StatReporter.reportEnterLoginpageFrom(this.K);
        }
        if (intent.hasExtra("bar_or_button")) {
            StatReporter.reportNotiAccelerateButton("loginPage");
        }
        this.B = LoginHelper.a();
        this.C = new i(this);
        this.B.a(this.C);
        setContentView(2130968860);
        this.b = findViewById(2131756443);
        this.b.setOnClickListener(new a(this));
        this.c = (ImageView) findViewById(2131756444);
        this.t = (TextView) findViewById(2131756462);
        this.t.setOnClickListener(new m(this));
        this.p = findViewById(2131756458);
        this.d = findViewById(2131756445);
        this.e = (MailBoxAssociateView) findViewById(2131756447);
        this.f = (EditText) findViewById(2131756451);
        this.h = findViewById(2131756449);
        this.g = findViewById(2131756453);
        this.k = (TextView) findViewById(2131756460);
        this.j = findViewById(2131756459);
        this.q = (ImageView) findViewById(2131756446);
        this.r = (ImageView) findViewById(2131756450);
        this.s = (ImageView) findViewById(2131756455);
        this.i = (TextView) findViewById(2131756452);
        this.i.setOnClickListener(new q(this));
        this.h.setOnClickListener(new r(this));
        this.g.setOnClickListener(new s(this));
        this.e.addTextChangedListener(new a(this.h));
        this.e.setOnFocusChangeListener(new t(this));
        this.e.setOnClickListener(new u(this));
        this.f.setOnFocusChangeListener(new v(this));
        this.f.addTextChangedListener(new w(this));
        this.e.setOnEditorActionListener(new b(this));
        this.f.setOnEditorActionListener(new c(this));
        this.j.setOnClickListener(new d(this));
        this.l = (AnimationDot) findViewById(2131756461);
        this.m = findViewById(2131756454);
        this.n = (EditText) findViewById(2131756456);
        this.n.setOnFocusChangeListener(new e(this));
        this.n.addTextChangedListener(new f(this));
        this.o = (ImageView) findViewById(2131756457);
        this.o.setOnClickListener(new g(this));
        this.w = (ImageView) findViewById(2131756465);
        this.w.setOnClickListener(this.L);
        this.x = findViewById(2131756467);
        this.x.setOnClickListener(this.L);
        this.y = findViewById(2131756468);
        this.z = (ImageView) findViewById(2131756469);
        if (ai.a()) {
            this.y.setVisibility(0);
            String str = MsgConstant.MESSAGE_SYSTEM_SOURCE_XIAOMI;
            String str2 = "login_button_show";
            ThunderReporter.g a = ThunderReporter.g.a("android_login_third", str2, str2);
            a.a("login_account", str);
            com.xunlei.downloadprovider.member.register.a.a(a);
        }
        this.z.setOnClickListener(this.L);
        this.A = (ImageView) findViewById(2131756466);
        this.A.setOnClickListener(this.L);
        this.u = (TextView) findViewById(2131756464);
        this.u.setOnClickListener(new h(this));
        this.v = (EditText) findViewById(2131756470);
        this.v.requestFocus();
    }

    protected void onResume() {
        super.onResume();
        c();
        if (!this.E) {
            CharSequence e = this.B.e();
            if (!(e == null || com.umeng.a.d.equals(e))) {
                this.e.setText(e);
                this.h.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
                this.q.setImageDrawable(getResources().getDrawable(2130838531));
                this.f.requestFocus();
            }
        }
        if (LoginHelper.p()) {
            a(true, true);
        } else {
            a(false, LoginHelper.c());
        }
        if (this.H) {
            this.H = false;
            b();
        }
    }

    protected void onPause() {
        super.onPause();
        this.E = true;
    }

    protected void onDestroy() {
        this.B.b(this.C);
        super.onDestroy();
    }

    public void onBackPressed() {
        boolean z = false;
        DownloadCenterActivityFragment.a = false;
        if (this.K.equals("GuideActivity")) {
            MainTabActivity.a((Context) this, "thunder");
            return;
        }
        a();
        if (getIntent().hasExtra(VodPlayerActivity.BUNDEL_KEY_IS_FROM_NOTIFICATION)) {
            z = getIntent().getExtras().getBoolean(VodPlayerActivity.BUNDEL_KEY_IS_FROM_NOTIFICATION, false);
        }
        if (z) {
            MainTabActivity.a((Activity) this);
        } else {
            new StringBuilder("doFinish() Logged=").append(LoginHelper.c());
            finish();
        }
        StatReporter.reportBackLoginPage();
    }

    public static void a(Context context, String str) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("login_from", str);
        context.startActivity(intent);
    }

    private void a(String str) {
        com.xunlei.downloadprovider.member.register.view.a.a(this, XZBDevice.DOWNLOAD_LIST_RECYCLE, str).a();
    }

    private void a(int i) {
        a(getString(i));
    }

    public final void a(boolean z, boolean z2) {
        if (z) {
            this.h.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
            this.j.setEnabled(false);
            this.k.setEnabled(false);
            this.k.setText(R.string.logining);
            this.l.a();
            this.e.setTextColor(-7829368);
            this.e.setFocusable(false);
            this.e.setEnabled(false);
            this.f.setTextColor(-7829368);
            this.f.setFocusable(false);
            this.f.setEnabled(false);
            this.t.setEnabled(false);
            this.u.setEnabled(false);
            this.A.setEnabled(false);
            this.w.setEnabled(false);
            this.x.setEnabled(false);
            this.z.setEnabled(false);
            return;
        }
        this.l.b();
        if (!z2) {
            this.k.setText(R.string.login);
            this.e.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            this.e.setEnabled(true);
            this.e.setFocusableInTouchMode(true);
            this.f.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            this.f.setEnabled(true);
            this.f.setFocusableInTouchMode(true);
            this.t.setEnabled(true);
            this.u.setEnabled(true);
            this.A.setEnabled(true);
            this.w.setEnabled(true);
            this.x.setEnabled(true);
            this.z.setEnabled(true);
        }
    }

    public final void a() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        inputMethodManager.hideSoftInputFromWindow(this.e.getWindowToken(), 0);
        inputMethodManager.hideSoftInputFromWindow(this.f.getWindowToken(), 0);
    }

    private void c() {
        boolean z = false;
        if (TextUtils.isEmpty(this.e.getText().toString().trim()) || this.f.length() <= 0) {
            a(false);
            return;
        }
        if (this.m.getVisibility() != 0 || (this.m.getVisibility() == 0 && this.n.length() > 0)) {
            z = true;
        }
        a(z);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(2131034124, 2131034125);
    }

    public static String a(long j, long j2) {
        int i = 1;
        long j3 = (j2 - j) / 1000;
        if (((j3 < 5 ? 1 : 0) & (0 <= j3 ? 1 : 0)) != 0) {
            return "[0s,5s)";
        }
        int i2;
        if (5 <= j3) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        if (((j3 < 10 ? 1 : 0) & i2) != 0) {
            return "[5s,10s)";
        }
        int i3;
        if (10 <= j3) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        if (j3 >= 15) {
            i = 0;
        }
        if ((i & i3) != 0) {
            return "[10s,20s)";
        }
        return 20 <= j3 ? "[20s,~)" : com.umeng.a.d;
    }

    private void a(boolean z) {
        this.j.setEnabled(z);
        this.k.setEnabled(z);
    }

    private void b() {
        a();
        finish();
        Class cls = (Class) getIntent().getSerializableExtra("SuccessDestination");
        Intent intent = getIntent();
        if (cls != null) {
            intent.setClass(this, cls);
            startActivity(intent);
            finish();
        } else if (intent.hasExtra("to_downloadlist")) {
            if (intent.getExtras().getString("to_downloadlist").equals("to_downloadlist")) {
                DownloadCenterActivity.a((Context) this, com.umeng.a.d);
            }
        } else if (intent.hasExtra("to_signpage")) {
            if (intent.getExtras().getString("to_signpage").equals("to_signpage")) {
                BrowserUtil.a();
                BrowserUtil.a(this, "http://m.sjzhushou.com/v2/store/task_list.html?sign=1", "\u4efb\u52a1", R.styleable.AppCompatTheme_textAppearanceLargePopupMenu, null);
            }
        } else if (intent.hasExtra("login_from")) {
            String string = intent.getExtras().getString("login_from");
            if ("GuideActivity".equals(string)) {
                MainTabActivity.a((Context) this, "thunder");
                finish();
            } else if ("ShortMovieDetailActivity".equals(string)) {
                finish();
            }
        }
    }

    static /* synthetic */ void b(LoginActivity loginActivity) {
        WebViewNormalActivity.a(loginActivity, com.umeng.a.d, "http://aq.xunlei.com/wap/forgetPwd.html", loginActivity.getString(2131233058));
        StatReporter.reportForgetPwdClick("click_forget_pwd");
        new StringBuilder("HubbleProxy---reportUserLogin_forget_password---").append(Thread.currentThread().getId());
        com.xunlei.downloadprovider.model.protocol.report.b.a("android_forget_password", "forget_password", null);
    }

    static /* synthetic */ void m(LoginActivity loginActivity) {
        if (!loginActivity.I) {
            loginActivity.I = true;
            if (loginActivity.J) {
                loginActivity.p.setVisibility(0);
            }
            loginActivity.B.a(new p(loginActivity));
        }
    }

    static /* synthetic */ void n(LoginActivity loginActivity) {
        String str = "phone_register_show";
        com.xunlei.downloadprovider.member.register.b.a(ThunderReporter.g.a("android_phone_register", str, str));
        Dialog dialog = new Dialog(loginActivity, 2131427577);
        dialog.requestWindowFeature(1);
        dialog.setContentView(2130968927);
        Window window = dialog.getWindow();
        LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.gravity = 80;
        window.setAttributes(attributes);
        window.findViewById(2131756793).setOnClickListener(new k(loginActivity, dialog));
        View findViewById = window.findViewById(2131756799);
        if (!TextUtils.isEmpty(Build.MANUFACTURER) && Build.MANUFACTURER.equals("Xiaomi")) {
            findViewById.setVisibility(0);
        }
        findViewById = window.findViewById(2131756796);
        View findViewById2 = window.findViewById(2131756798);
        View findViewById3 = window.findViewById(2131756797);
        View findViewById4 = window.findViewById(2131756800);
        View findViewById5 = window.findViewById(2131756801);
        findViewById.setOnClickListener(loginActivity.L);
        findViewById2.setOnClickListener(loginActivity.L);
        findViewById3.setOnClickListener(loginActivity.L);
        findViewById4.setOnClickListener(loginActivity.L);
        findViewById5.setOnClickListener(new l(loginActivity, dialog));
        TextView textView = (TextView) window.findViewById(2131756563);
        CharSequence spannableString = new SpannableString(loginActivity.getString(2131232167));
        spannableString.setSpan(new o(loginActivity), 0, spannableString.length(), R.styleable.AppCompatTheme_actionModeCopyDrawable);
        textView.setText(loginActivity.getString(2131232134));
        textView.append(spannableString);
        textView.setHighlightColor(0);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        dialog.show();
    }

    static /* synthetic */ void r(LoginActivity loginActivity) {
        loginActivity.H = true;
        loginActivity.startActivity(new Intent(loginActivity, BindMobileActivity.class));
    }

    static /* synthetic */ void b(LoginActivity loginActivity, View view) {
        if (view.requestFocus()) {
            ((InputMethodManager) loginActivity.getSystemService("input_method")).showSoftInput(view, 1);
        }
    }

    static /* synthetic */ void b(LoginActivity loginActivity, int i) {
        new StringBuilder("showErrorTipByCode() errorCode=").append(i).append(" errorDesc=").append(XLErrorCode.getErrorDesc(i));
        switch (i) {
            case SpdyAgent.ACCS_TEST_SERVER:
            case XLErrorCode.AUTH_USER_CANCLE:
            case XLErrorCode.QQ_AUTH_CAN:
            case XLErrorCode.GET_WXCODE_ERROR:
                break;
            case SpdyAgent.ACCS_ONLINE_SERVER:
            case XLErrorCode.GET_WEB_SESSIONID_ERROR:
                loginActivity.a(2131232968);
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                loginActivity.a(2131232933);
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                loginActivity.a(2131232953);
            case XZBDevice.DOWNLOAD_LIST_ALL:
                loginActivity.a(2131232967);
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                loginActivity.a(2131232969);
            case R.styleable.Toolbar_contentInsetEnd:
                if (loginActivity.G) {
                    loginActivity.a(loginActivity.getString(2131231663));
                    loginActivity.n.setText(com.umeng.a.d);
                    return;
                }
                loginActivity.a(2131233082);
            case R.styleable.Toolbar_contentInsetLeft:
                loginActivity.a(2131232934);
            case XZBDevice.Wait:
                loginActivity.a(2131232980);
            case XZBDevice.Pause:
                loginActivity.a(2131232959);
            case XZBDevice.Stop:
                loginActivity.a(2131232984);
            case XZBDevice.Success:
                loginActivity.a(2131232935);
            case XZBDevice.Fail:
                loginActivity.a(2131232939);
            case XZBDevice.Predownload:
                loginActivity.a(2131232946);
            case XLErrorCode.SINA_AUTH_ERROR:
                loginActivity.a(2131232978);
            case XLErrorCode.QQ_AUTH_WEB_SESSION_ERROR:
                loginActivity.a(2131232957);
            case XLErrorCode.GET_XMTOKEN_ERROR:
                loginActivity.a(2131232985);
            case XLErrorCode.SOCKET_ERROR:
            case XLErrorCode.UNKNOWN_HOST_ERROR:
                loginActivity.a(2131232937);
            default:
                loginActivity.a(2131232936);
        }
    }

    static /* synthetic */ void D(LoginActivity loginActivity) {
        if (!loginActivity.F) {
            loginActivity.a(2131232112);
        }
        loginActivity.F = false;
        loginActivity.o.setImageResource(2130837650);
        loginActivity.J = true;
    }
}
