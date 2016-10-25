package com.xunlei.downloadprovider.web;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ZoomButtonsController;
import com.tencent.open.SocialConstants;
import com.tencent.open.utils.SystemUtils;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.common.pay.XLOnPayListener;
import com.xunlei.common.pay.XLPayUtil;
import com.xunlei.common.pay.param.XLAlipayParam;
import com.xunlei.common.pay.param.XLPayParam;
import com.xunlei.common.pay.param.XLWxPayParam;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.a.h;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.businessutil.b;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.commonview.dialog.XLAlarmDialog;
import com.xunlei.downloadprovider.commonview.f;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;
import com.xunlei.downloadprovider.download.report.DLCenterEntry;
import com.xunlei.downloadprovider.frame.MainTabActivity;
import com.xunlei.downloadprovider.frame.user.bo;
import com.xunlei.downloadprovider.loading.LoadingActivity;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.payment.external.OperType;
import com.xunlei.downloadprovider.member.payment.external.PayEntryParam;
import com.xunlei.downloadprovider.member.payment.external.PayFrom;
import com.xunlei.downloadprovider.member.payment.external.PaymentEntryActivity;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.c;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.service.downloads.task.d;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.downloadprovider.util.aa;
import com.xunlei.downloadprovider.util.ai;
import com.xunlei.downloadprovider.web.base.core.BaseJsInterface;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.downloadprovider.web.core.ThunderWebView;
import com.xunlei.downloadprovider.web.core.p;
import com.xunlei.downloadprovidershare.ba;
import com.xunlei.downloadprovidershare.data.ShareBean;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.WebBrowserActivity;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.agoo.message.MessageService;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailPageBrowserActivity extends ThunderTask implements com.xunlei.downloadprovidershare.d.a {
    private static final String c;
    private ZoomButtonsController A;
    private boolean B;
    private XLOnPayListener C;
    com.xunlei.downloadprovider.a.h.a a;
    p b;
    private ThunderWebView d;
    private f e;
    private com.xunlei.downloadprovider.download.b.a f;
    private b g;
    private com.xunlei.downloadprovider.web.core.a h;
    private boolean i;
    private boolean j;
    private String k;
    private boolean l;
    private boolean m;
    private int n;
    private String o;
    private String p;
    private String q;
    private String r;
    private String s;
    private TaskInfo t;
    private String u;
    private boolean v;
    private String w;
    private com.xunlei.downloadprovider.a.h.a x;
    private Handler y;
    private XLAlarmDialog z;

    public static interface a {
    }

    public DetailPageBrowserActivity() {
        this.i = true;
        this.j = false;
        this.k = null;
        this.l = false;
        this.n = 0;
        this.o = com.umeng.a.d;
        this.p = com.umeng.a.d;
        this.q = com.umeng.a.d;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = com.umeng.a.d;
        this.a = new g(this);
        this.b = new j(this);
        this.x = new l(this);
        this.y = new h.b(this.x);
        this.z = null;
        this.C = new i(this);
    }

    static {
        c = DetailPageBrowserActivity.class.getSimpleName();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.v = true;
        new StringBuilder().append(getClass()).append("---onCreate------").append(Thread.currentThread().getId());
        this.g = b.a();
        setContentView(2130968779);
        this.d = (ThunderWebView) findViewById(R.id.webView);
        this.d.setIsReportPage(true);
        this.e = new f((Activity) this);
        this.e.g.setOnClickListener(new n(this));
        this.e.i.setText(com.umeng.a.d);
        this.h = new com.xunlei.downloadprovider.web.core.a(this);
        if (DownloadService.a() == null) {
            DownloadService.a(new m(this));
        } else {
            h();
            f();
            d();
            e();
        }
        if (!TextUtils.isEmpty(this.o) && this.o.equals("Usercenterfragment_2_feedback")) {
            this.e.j.setVisibility(0);
            this.e.j.setText("\u610f\u89c1\u53cd\u9988");
            this.e.j.setTextColor(getResources().getColor(2131689602));
            this.e.j.setOnClickListener(new p(this));
            this.e.p.setVisibility(XZBDevice.Wait);
        }
    }

    protected void onCreateTask(boolean z, int i) {
        Object obj = com.umeng.a.d;
        switch (this.n) {
            case R.styleable.AppCompatTheme_actionModeShareDrawable:
                obj = "break_friend";
                break;
            case 2064:
                obj = "break_shortvideo_hot";
                break;
            case 2065:
                obj = "fun_play_game_center";
                break;
            case 2066:
                obj = "guanggao";
                break;
        }
        if (!TextUtils.isEmpty(obj)) {
            StatReporter.reportOverallDownload(obj);
        }
    }

    public final void a() {
        if (this.n == 45) {
            finish();
            MainTabActivity.a((Context) this, "thunder");
        } else if (this.n == 43) {
            MainTabActivity.a((Context) this, "thunder");
            finish();
        } else {
            int i;
            int i2;
            if (this.n == 42) {
                if (TextUtils.isEmpty(this.q) || !"shortcut_download".equals(this.q)) {
                    MainTabActivity.a((Context) this, "thunder");
                    finish();
                } else {
                    DownloadCenterActivity.a((Context) this, DLCenterEntry.icon.toString());
                    finish();
                }
            }
            boolean z = getSharedPreferences("IsInstall", 0).getBoolean("_nx_shortcut", false);
            if (aa.c(BrothersApplication.a().getApplicationContext(), "no_first_enter_nx")) {
                i = 0;
            } else {
                i = 1;
            }
            if (TextUtils.isEmpty(this.r) || !this.r.equals("\u6e38\u620f\u4e2d\u5fc3")) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            i2 &= i;
            if (z) {
                i = 0;
            } else {
                i = 1;
            }
            i2 &= i;
            if (com.xunlei.downloadprovider.i.a.c()) {
                i = 0;
            } else {
                i = 1;
            }
            if ((i & i2) != 0) {
                aa.a(BrothersApplication.a().getApplicationContext(), "no_first_enter_nx", true);
                finish();
            } else if (this.n == 2065) {
                MainTabActivity.a((Context) this, "thunder");
                finish();
            } else if (getIntent().hasExtra("from") && LoadingActivity.b) {
                finish();
                MainTabActivity.a((Context) this, "thunder");
                LoadingActivity.b = false;
            } else {
                new StringBuilder().append(getClass()).append("---handleBack------").append(Thread.currentThread().getId());
                this.h.b();
                if (this.e.j != null) {
                    this.e.j.setVisibility(XZBDevice.Wait);
                }
                if (this.d.c) {
                    new StringBuilder().append(getClass()).append("---mWebView.hasLayer()------").append(Thread.currentThread().getId());
                    this.d.f();
                } else if (this.d.d()) {
                    new StringBuilder().append(getClass()).append("---mWebView.canGoBack()------").append(Thread.currentThread().getId());
                    this.d.e();
                    if (!(this.d.d() || TextUtils.isEmpty(this.r))) {
                        this.e.i.setText(this.r);
                    }
                } else if (LoadingActivity.b && TextUtils.isEmpty(this.q)) {
                    new StringBuilder().append(getClass()).append("---LoadingActivity.isFromSplash------").append(Thread.currentThread().getId());
                    MainTabActivity.a((Context) this, "thunder");
                    LoadingActivity.b = false;
                    finish();
                } else {
                    Bundle extras = getIntent().getExtras();
                    if (extras != null) {
                        this.B = extras.getBoolean("key_is_from_notification", false);
                        extras.getInt("from_entry");
                    }
                    if (this.B) {
                        MainTabActivity.a(this, "thunder", null, true);
                    }
                    finish();
                    ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.d.getWindowToken(), XZBDevice.DOWNLOAD_LIST_RECYCLE);
                }
                if (this.l) {
                    this.e.k.setVisibility(XZBDevice.Wait);
                }
            }
        }
    }

    public void onBackPressed() {
        a();
    }

    public void finish() {
        super.finish();
        overridePendingTransition(2131034124, 2131034125);
    }

    private boolean c() {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        if ((this.n & 2048) == 2048) {
            int i = 1;
        } else {
            z = false;
        }
        if (this.n == 31) {
            int i2 = 1;
        } else {
            z2 = false;
        }
        boolean z5 = this.j;
        if (this.n == 32) {
            int i3 = 1;
        } else {
            z3 = false;
        }
        if (this.n == 35) {
            int i4 = 1;
        } else {
            z4 = false;
        }
        return z || z2 || z5 || z3 || z4;
    }

    private void d() {
        if (c()) {
            this.e.p.setVisibility(0);
            this.e.p.setOnClickListener(new o(this));
            this.f = new com.xunlei.downloadprovider.download.b.a(this.e.p);
        }
    }

    private void e() {
        this.d.setEntry(this.n);
    }

    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        f();
        super.onNewIntent(intent);
    }

    private void f() {
        String path;
        String str = null;
        Intent intent = getIntent();
        Uri data = getIntent().getData();
        String str2;
        String str3;
        if (data != null) {
            path = data.getPath();
            if ("/shareDetail".equals(path)) {
                new StringBuilder().append(getClass()).append("---handleIntent()---getIntent().getData()---").append(data.getQueryParameter(SocialConstants.PARAM_URL)).append("---").append(Thread.currentThread().getId());
                path = data.getQueryParameter(SocialConstants.PARAM_URL);
                str2 = "\u8d44\u6e90\u8be6\u60c5";
            } else {
                if ("/hotTopic".equals(path)) {
                    str2 = data.getQueryParameter(SocialConstants.PARAM_URL);
                    path = data.getQueryParameter(WebBrowserActivity.EXTRA_TITLE);
                    this.n = 45;
                } else {
                    path = null;
                    str2 = null;
                }
                str3 = path;
                path = str2;
                str2 = str3;
            }
        } else {
            Bundle extras = intent.getExtras();
            new StringBuilder().append(getClass()).append("---handleIntent()---b---").append(extras).append("---").append(Thread.currentThread().getId());
            str2 = extras.getString("key_url");
            new StringBuilder().append(getClass()).append("---handleIntent()---url---").append(str2).append("---").append(Thread.currentThread().getId());
            path = extras.getString("key_title");
            this.j = extras.getBoolean("key_is_recommend");
            this.n = extras.getInt("from_entry", 0);
            this.o = extras.getString(JsInterface.FROM_KEY);
            new StringBuilder("handleIntent@@@@@@@@@@@@@@@@@:::  mfromKey").append(this.o).append("     fromEntry:   ").append(this.n);
            this.p = extras.getString("ad_id");
            this.q = extras.getString(com.xunlei.downloadprovider.thirdpart.a.a);
            com.xunlei.downloadprovider.util.sniff.f.a();
            com.xunlei.downloadprovider.util.sniff.f.a(this.n);
            this.B = extras.getBoolean("key_is_from_notification", false);
            String string = extras.getString("key_is_notification_tag");
            if (this.B) {
                StatReporter.reportPushResClick(string, extras.getInt("key_push_type", -1));
            }
            str3 = path;
            path = str2;
            str2 = str3;
        }
        if (path != null) {
            if (!(path.startsWith("http://") || path.startsWith("https://"))) {
                path = new StringBuilder("http://").append(path).toString();
            }
            str = path.replace("\r\n", com.umeng.a.d);
        }
        if (str != null) {
            b(str);
            if (this.k == null) {
                this.k = new String(str);
            }
        }
        if (!TextUtils.isEmpty(r2)) {
            this.e.i.setText(r2);
            this.i = false;
        }
    }

    public void onResume() {
        new StringBuilder().append(getClass()).append("---onResume------").append(Thread.currentThread().getId());
        d.a().c(50);
        if (getIntent().hasExtra("key_title")) {
            this.r = getIntent().getStringExtra("key_title");
        }
        super.onResume();
        if (this.d != null) {
            ThunderWebView thunderWebView = this.d;
            if (thunderWebView.a != null) {
                thunderWebView.a.onResume();
            }
        }
        this.d.a.getSettings().getUserAgentString();
        if (((this.f != null ? 1 : 0) & c()) != 0) {
            this.f.a();
        } else {
            this.e.p.setVisibility(XZBDevice.Wait);
        }
        if (!TextUtils.isEmpty(this.k)) {
            if (this.n == 7 || this.n == 40) {
                if (this.v) {
                    b(this.k);
                } else if (!TextUtils.isEmpty(this.u)) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(BaseJsInterface.JS_PREFIX).append(this.u).append("()");
                    b(stringBuffer.toString());
                }
            }
        }
        if (this.s != null) {
            b(String.format("javascript:%s()", new Object[]{this.s}));
            this.s = null;
        }
        if (this.t != null) {
            g();
        } else if (DownloadService.a() != null && DownloadService.a().b) {
            long j = DownloadService.a().a;
            g();
        }
    }

    private static void g() {
        try {
            if (DownloadService.a() != null) {
                DownloadService.a();
                DownloadService.d();
                ai.b(bo.a().a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onPause() {
        d.a().c(this.g.i() ? (long) this.g.k() : -1);
        if (this.d != null) {
            ThunderWebView thunderWebView = this.d;
            if (thunderWebView.a != null) {
                thunderWebView.a.onPause();
            }
        }
        super.onPause();
        if (((this.f != null ? 1 : 0) & c()) != 0) {
            this.f.b();
        }
    }

    protected void onDestroy() {
        try {
            if (this.d != null) {
                this.d.c();
            }
            DownloadService a = DownloadService.a();
            if (a != null) {
                a.c = null;
                a.b = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
        System.gc();
    }

    @TargetApi(11)
    private void h() {
        this.d.setJsCallbackMessageListener(this.a);
        this.d.setThunderWebViewClient(this.b);
        if (VERSION.SDK_INT >= 11) {
            this.d.a.getSettings().setBuiltInZoomControls(true);
            this.d.a.getSettings().setDisplayZoomControls(false);
            return;
        }
        try {
            this.A = (ZoomButtonsController) Class.forName("android.webkit.WebView").getMethod("getZoomButtonsController", new Class[0]).invoke(this, new Object[]{Boolean.valueOf(true)});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void b(String str) {
        if (this.d != null) {
            if (TextUtils.isEmpty(str)) {
                XLToast.a(this, XLToastType.XLTOAST_TYPE_ALARM, "\u94fe\u63a5\u5730\u5740\u4e0d\u80fd\u4e3a\u7a7a");
            } else {
                this.d.a(str);
            }
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        com.xunlei.downloadprovidershare.d.b().a(i, i2, intent);
    }

    private static void a(XLPayParam xLPayParam, String str, String str2, String str3, int i, int i2, int i3) {
        xLPayParam.mMonth = i;
        xLPayParam.mReferFrom = str3;
        xLPayParam.mOrderType = i2;
        xLPayParam.mSource = "shoulei_android";
        xLPayParam.mVasType = i3;
        xLPayParam.mOrderVoucher = str;
        xLPayParam.mOrderExtraParam = str2;
        xLPayParam.mUserId = (int) LoginHelper.a().j;
    }

    private static void a(int i, int i2) {
        int i3;
        int i4 = 0;
        g gVar = new g();
        if (LoginHelper.a().f()) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        LoginHelper.a();
        if (LoginHelper.c()) {
            i4 = 1;
        }
        gVar.a = "android_personal_click";
        gVar.b = "per_cl_sign";
        gVar.c = "per_cl_sign";
        gVar.b("is_vip", (long) i3);
        gVar.b(SystemUtils.IS_LOGIN, (long) i4);
        gVar.b("Sign_days", (long) i);
        gVar.b(MsgConstant.KEY_SUCCESS, (long) i2);
        ThunderReporter.a(gVar, true);
    }

    public void onShareTargetClicked(SHARE_MEDIA share_media, ShareBean shareBean) {
        c.a(shareBean.e, ba.a(share_media, shareBean), String.valueOf(shareBean.k));
    }

    public void onShareComplete(int i, SHARE_MEDIA share_media, ShareBean shareBean) {
        c.a(shareBean.e, ba.a(share_media, shareBean), com.xunlei.downloadprovidershare.d.a(i), i, String.valueOf(shareBean.k));
    }

    static /* synthetic */ void a(DetailPageBrowserActivity detailPageBrowserActivity, Message message) {
        Bundle data = message.getData();
        if (Integer.valueOf(data.getString("result", MessageService.MSG_DB_READY_REPORT)).intValue() == 0) {
            a(0, 1);
            return;
        }
        int intValue = Integer.valueOf(data.getString("days", MessageService.MSG_DB_READY_REPORT)).intValue();
        com.xunlei.downloadprovider.frame.user.a.b.a((Context) detailPageBrowserActivity).a(String.valueOf(LoginHelper.a().j), intValue, System.currentTimeMillis());
        a(intValue, 0);
    }

    static /* synthetic */ int a(String str, String str2, String str3, int i, int i2, int i3) {
        XLPayParam xLWxPayParam = new XLWxPayParam();
        a(xLWxPayParam, str, str2, str3, i, i2, i3);
        xLWxPayParam.mAppId = "wx3e6556568beeebdd";
        return XLPayUtil.getInstance().userWxPay(xLWxPayParam, xLWxPayParam);
    }

    static /* synthetic */ int a(DetailPageBrowserActivity detailPageBrowserActivity, String str, String str2, String str3, int i, int i2, int i3) {
        XLPayParam xLAlipayParam = new XLAlipayParam();
        a(xLAlipayParam, str, str2, str3, i, i2, i3);
        xLAlipayParam.mActivity = detailPageBrowserActivity;
        return XLPayUtil.getInstance().userAliPay(xLAlipayParam, xLAlipayParam);
    }

    static /* synthetic */ void b(DetailPageBrowserActivity detailPageBrowserActivity, String str) {
        new StringBuilder().append(detailPageBrowserActivity.getClass()).append("---hanldeLoginAndCallback---mTitle.mTitle.getText().toString()---").append(detailPageBrowserActivity.e.i.getText().toString()).append("---").append(Thread.currentThread().getId());
        if (str != null && !str.equals(com.umeng.a.d)) {
            try {
                LoginHelper.a().a((Context) detailPageBrowserActivity, new r(detailPageBrowserActivity, new JSONObject(str).getString(com.alipay.sdk.authjs.a.c)), (int) XZBDevice.Delete, detailPageBrowserActivity.e.i.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    static /* synthetic */ void c(DetailPageBrowserActivity detailPageBrowserActivity, String str) {
        if (str != null && !str.equals(com.umeng.a.d)) {
            try {
                LoginHelper.a().a(new q(detailPageBrowserActivity, new JSONObject(str).getString(com.alipay.sdk.authjs.a.c)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    static /* synthetic */ void a(DetailPageBrowserActivity detailPageBrowserActivity, String str, OperType operType) {
        PayEntryParam payEntryParam = new PayEntryParam(PayFrom.VIP_CENTER);
        payEntryParam.d = str;
        payEntryParam.b = operType;
        PaymentEntryActivity.a(detailPageBrowserActivity, payEntryParam);
        detailPageBrowserActivity.v = false;
    }

    static /* synthetic */ void b(DetailPageBrowserActivity detailPageBrowserActivity, long j) {
        StringBuffer stringBuffer = new StringBuffer();
        String str = "javascript:taskCallbackNx";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Impl.COLUMN_STATUS, "create");
            jSONObject.put("taskid", j);
        } catch (Exception e) {
            e.printStackTrace();
        }
        stringBuffer.append(str);
        stringBuffer.append(SocializeConstants.OP_OPEN_PAREN);
        stringBuffer.append(jSONObject.toString());
        stringBuffer.append(SocializeConstants.OP_CLOSE_PAREN);
        detailPageBrowserActivity.d.a(stringBuffer.toString());
    }

    static /* synthetic */ String a(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        long j = LoginHelper.a().j;
        String str2 = LoginHelper.a().n;
        String str3 = LoginHelper.a().k;
        String str4 = LoginHelper.a().i;
        String e = LoginHelper.a().e();
        stringBuffer.append(BaseJsInterface.JS_PREFIX).append(str + "('");
        stringBuffer.append(String.format("{\"userid\":\"%s\",\"jumpkey\":\"%s\",\"sessionid\":\"%s\",\"nickname\":\"%s\",\"username\":\"%s\"}", new Object[]{Long.valueOf(j), str2, str3, str4, e}));
        stringBuffer.append("')");
        return stringBuffer.toString();
    }
}
