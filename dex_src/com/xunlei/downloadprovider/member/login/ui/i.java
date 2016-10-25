package com.xunlei.downloadprovider.member.login.ui;

import com.umeng.message.MsgConstant;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.d;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.model.protocol.report.b;
import com.xunlei.downloadprovider.model.protocol.report.b.a;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: LoginActivity.java
final class i implements d {
    final /* synthetic */ LoginActivity a;

    i(LoginActivity loginActivity) {
        this.a = loginActivity;
    }

    public final void a(int i, int i2, boolean z, Object obj) {
        new StringBuilder("OnLoginCompleted() errorCode=").append(i2).append(" errorDesc=").append(XLErrorCode.getErrorDesc(i2)).append(" isAutoLog=").append(z);
        this.a.l.b();
        this.a.c();
        if (i == 0 || 1 == i) {
            if (obj != null) {
                if (obj.equals(LoginHelper.c) || obj.equals(LoginHelper.b)) {
                    new StringBuilder("HubbleProxy---reportUserLogin_submit---").append(Thread.currentThread().getId());
                    b.a("android_user_login", "user_login_submit", null);
                    String c;
                    String a;
                    String a2;
                    String valueOf;
                    if (i2 == 0) {
                        StatReporter.reportNewLoginFromLoginPage(MsgConstant.KEY_SUCCESS, i2, this.a.B.f(), this.a.B.h);
                        StatReporter.reportLoginOrRegTimeRangeLoginPage(LoginActivity.a(this.a.a, System.currentTimeMillis()));
                        int i3 = this.a.B.f() ? 1 : 0;
                        c = com.xunlei.xllib.a.b.c(this.a);
                        a = this.a.K;
                        a2 = LoginActivity.a(this.a.a, System.currentTimeMillis());
                        String str = this.a.B.h;
                        valueOf = String.valueOf(i3);
                        String str2 = LoginHelper.a;
                        new StringBuilder("HubbleProxy---reportUserLogin_success---vip_type---is_vip---is_syscheck---").append(str).append("---").append(valueOf).append("---").append(str2).append("---").append(Thread.currentThread().getId());
                        b.a("android_user_login", "user_login_success", new a().a("nettype", c).a("from_src", a).a("login_time", a2).a("vip_type", str).a("is_vip", valueOf).a("is_syscheck", str2));
                    } else {
                        StatReporter.reportNewLoginFromLoginPage(MsgConstant.KEY_FAIL, i2, this.a.B.f(), this.a.B.h);
                        valueOf = com.xunlei.xllib.a.b.c(this.a);
                        c = LoginActivity.a(this.a.a, System.currentTimeMillis());
                        a = String.valueOf(i2);
                        a2 = LoginHelper.a;
                        new StringBuilder("HubbleProxy---reportUserLogin_fail---is_syscheck---").append(a2).append("---").append(Thread.currentThread().getId());
                        b.a("android_user_login", "user_login_fail", new a().a("nettype", valueOf).a("login_time", c).a("failtype", a).a("is_syscheck", a2));
                    }
                }
            }
            if (this.a.m.getVisibility() == 0) {
                if (i2 == 0) {
                    StatReporter.reportNewLoginFromLoginPageHasVerifyCode(MsgConstant.KEY_SUCCESS, i2, this.a.B.f(), this.a.B.h);
                } else {
                    StatReporter.reportNewLoginFromLoginPageHasVerifyCode(MsgConstant.KEY_FAIL, i2, this.a.B.f(), this.a.B.h);
                    if (i2 != 6) {
                        LoginActivity.m(this.a);
                    }
                }
            }
            switch (i2) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    this.a.a(false, true);
                    if (this.a.B.F && this.a.B.G) {
                        LoginActivity.r(this.a);
                    } else {
                        this.a.b();
                    }
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    this.a.a(false, false);
                    this.a.e.requestFocus();
                    this.a.a(this.a.getResources().getString(2131232951));
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    this.a.a(false, false);
                    this.a.a(2131232953);
                    this.a.f.setText(com.umeng.a.d);
                    LoginActivity.b(this.a, this.a.f);
                case R.styleable.Toolbar_contentInsetEnd:
                    this.a.a(false, false);
                    this.a.B.a(new j(this));
                case R.styleable.Toolbar_contentInsetLeft:
                    this.a.a(false, false);
                    this.a.e.requestFocus();
                    this.a.a(2131232934);
                default:
                    this.a.a(false, false);
                    LoginActivity.b(this.a, i2);
            }
        }
    }
}
