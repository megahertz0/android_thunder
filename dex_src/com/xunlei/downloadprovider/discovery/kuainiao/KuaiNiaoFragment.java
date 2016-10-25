package com.xunlei.downloadprovider.discovery.kuainiao;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import com.xunlei.common.accelerator.js.KNJsInterface$JumpLisenter;
import com.xunlei.common.accelerator.js.KNJsUtils;
import com.xunlei.downloadprovider.a.h.b;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.frame.BaseFragment;
import com.xunlei.downloadprovider.frame.MainTabActivity;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.d;
import com.xunlei.downloadprovider.member.login.LoginHelper.g;
import com.xunlei.downloadprovider.member.login.ui.LoginActivity;
import com.xunlei.downloadprovider.member.payment.external.PayEntryParam;
import com.xunlei.downloadprovider.member.payment.external.PayFrom;
import com.xunlei.downloadprovider.member.payment.external.PaymentEntryActivity;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.downloadprovider.web.core.ThunderWebView;
import com.xunlei.tdlive.R;

public class KuaiNiaoFragment extends BaseFragment implements OnClickListener, KNJsInterface$JumpLisenter {
    private static ThunderWebView f;
    private boolean a;
    private final int b;
    private final String c;
    private boolean d;
    private boolean e;
    private boolean g;
    private final b h;
    private final g i;
    private final d j;
    private boolean k;

    public KuaiNiaoFragment() {
        this.a = false;
        this.b = 20101;
        this.c = "KuaiNiaoFragment";
        this.d = false;
        this.e = true;
        this.g = true;
        this.h = new b(null);
        this.i = new a(this);
        this.j = new b(this);
    }

    public void jumpToLogin() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.putExtra("login_from", "KuaiNiaoFragment");
        intent.setFlags(268435456);
        intent.putExtra("login_type", 1);
        startActivity(intent);
    }

    public void jumpToPay() {
        PayEntryParam payEntryParam = new PayEntryParam(PayFrom.BIRD_PAGE);
        payEntryParam.c = com.xunlei.downloadprovider.homepage.a.a.d.a;
        PaymentEntryActivity.a(this.mActivity, payEntryParam);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mPageRoot == null) {
            this.mPageRoot = (ViewGroup) layoutInflater.inflate(2130968771, viewGroup, false);
            if (getExtras() != null) {
                this.a = getExtras().getBoolean("from_where", false);
                if (this.a) {
                    StatReporter.reportKuaiNiaoNotification("item");
                }
            }
            findViewById(2131756160).setOnClickListener(this);
            ((TextView) findViewById(R.id.title)).setText("\u8fc5\u96f7\u5feb\u9e1f");
            ThunderWebView thunderWebView = (ThunderWebView) findViewById(2131756036);
            f = thunderWebView;
            thunderWebView.a("http://k.xunlei.com/speed-mini-shoulei-524/");
            KNJsUtils.getKnJsUtils().initJsInterface(f.a, this);
            f.setThunderWebViewClient(new a(this));
            f.setIsReportPage(true);
            if (com.xunlei.xllib.a.b.a(getActivity())) {
                f.a(new StringBuilder("http://k.xunlei.com/speed-mini-shoulei-524/?version=").append(com.xunlei.downloadprovider.a.b.x()).toString());
            } else {
                XLToast.a(getActivity(), XLToastType.XLTOAST_TYPE_ALARM, "\u7f51\u7edc\u8fde\u63a5\u6709\u95ee\u9898\uff0c\u8bf7\u68c0\u67e5\u7f51\u7edc");
                f.a("http://swjsq.xunlei.com/mobile/shoulei/");
            }
            LoginHelper.a().a(this.i);
            LoginHelper.a().a(this.j);
        }
        ViewParent parent = this.mPageRoot.getParent();
        if (parent != null && (parent instanceof ViewGroup)) {
            ((ViewGroup) parent).removeView(this.mPageRoot);
        }
        return this.mPageRoot;
    }

    public void onResume() {
        super.onResume();
        BrothersApplication.i().a();
        BrothersApplication.i();
        com.xunlei.downloadprovider.discovery.kuainiao.a.b.b();
        if (!this.e) {
            if (((this.k != com.xunlei.xllib.a.b.a(getActivity()) ? 1 : 0) & com.xunlei.xllib.a.b.a(getActivity())) != 0) {
                f.b();
            } else if (com.xunlei.xllib.a.b.a(getActivity())) {
                f.a("javascript:refresh()");
            }
        }
        this.e = false;
        this.k = com.xunlei.xllib.a.b.a(getActivity());
        if (DownloadService.a() != null) {
            DownloadService.a().b(this.h);
        }
    }

    public void onDestroy() {
        this.d = true;
        LoginHelper.a().b(this.i);
        LoginHelper.a().b(this.j);
        KNJsUtils.getKnJsUtils().uninitJsInterface();
        super.onDestroy();
    }

    public void onDestroyView() {
        this.h.removeMessages(JsInterface.MSG_JS_GOTO_LOGIN_PAGE_AND_CALLBACK);
        this.h.removeMessages(JsInterface.MSG_JS_GO_TO_DOWNLOAD_LIST);
        this.h.removeMessages(JsInterface.MSG_JS_INSTALL_APK);
        this.h.removeMessages(JsInterface.MSG_JS_GO_TO_USER_INFO);
        super.onDestroyView();
    }

    public void onPause() {
        super.onPause();
        if (DownloadService.a() != null) {
            DownloadService.a().c(this.h);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case 2131756160:
                this.mActivity.finish();
            default:
                break;
        }
    }

    public boolean onBackPressed() {
        if (f.c) {
            f.f();
            return true;
        } else if (f.d()) {
            f.e();
            return true;
        } else {
            if (this.a) {
                MainTabActivity.a(this.mActivity, "thunder");
            }
            this.mActivity.finish();
            return false;
        }
    }
}
