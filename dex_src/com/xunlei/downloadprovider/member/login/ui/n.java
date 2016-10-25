package com.xunlei.downloadprovider.member.login.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.constants.ConstantsAPI.Token;
import com.tencent.mm.sdk.constants.ConstantsAPI.WXApp;
import com.umeng.a;
import com.umeng.message.MsgConstant;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.common.member.XLUserUtil;
import com.xunlei.common.member.act.XLQQParam;
import com.xunlei.common.member.act.XLSinaParam;
import com.xunlei.common.member.act.XLWxParam;
import com.xunlei.common.member.act.XLXmParam;
import com.xunlei.downloadprovider.a.c;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.wxapi.WXEntryActivity;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xllib.a.b;
import java.util.HashMap;
import java.util.Map;

// compiled from: LoginActivity.java
final class n implements OnClickListener {
    final /* synthetic */ LoginActivity a;

    n(LoginActivity loginActivity) {
        this.a = loginActivity;
    }

    public final void onClick(View view) {
        if (!LoginHelper.d) {
            Object obj = a.d;
            switch (view.getId()) {
                case 2131756465:
                case 2131756466:
                case 2131756467:
                case 2131756469:
                    obj = "user_login";
                    break;
                case 2131756796:
                case 2131756797:
                case 2131756798:
                case 2131756800:
                    obj = MiPushClient.COMMAND_REGISTER;
                    break;
            }
            switch (view.getId()) {
                case 2131756465:
                    com.xunlei.downloadprovider.member.register.a.a(Token.WX_TOKEN_PLATFORMID_VALUE, "user_login");
                    break;
                case 2131756466:
                    com.xunlei.downloadprovider.member.register.a.a(Constants.SOURCE_QQ, "user_login");
                    break;
                case 2131756467:
                    com.xunlei.downloadprovider.member.register.a.a("weibo", "user_login");
                    break;
                case 2131756469:
                    com.xunlei.downloadprovider.member.register.a.a(MsgConstant.MESSAGE_SYSTEM_SOURCE_XIAOMI, "user_login");
                    break;
                case 2131756796:
                    com.xunlei.downloadprovider.member.register.a.a(Token.WX_TOKEN_PLATFORMID_VALUE, MiPushClient.COMMAND_REGISTER);
                    break;
                case 2131756797:
                    com.xunlei.downloadprovider.member.register.a.a(Constants.SOURCE_QQ, MiPushClient.COMMAND_REGISTER);
                    break;
                case 2131756798:
                    com.xunlei.downloadprovider.member.register.a.a("weibo", MiPushClient.COMMAND_REGISTER);
                    break;
                case 2131756800:
                    com.xunlei.downloadprovider.member.register.a.a(MsgConstant.MESSAGE_SYSTEM_SOURCE_XIAOMI, MiPushClient.COMMAND_REGISTER);
                    break;
            }
            LoginHelper p;
            String a;
            Map hashMap;
            switch (view.getId()) {
                case 2131756465:
                case 2131756796:
                    if (c.a(WXApp.WXAPP_PACKAGE_NAME)) {
                        p = this.a.B;
                        a = this.a.K;
                        XLWxParam xLWxParam = new XLWxParam();
                        xLWxParam.mWxAppId = "wx3e6556568beeebdd";
                        hashMap = new HashMap();
                        hashMap.put("loginFrom", a);
                        hashMap.put("from", obj);
                        XLUserUtil.getInstance().userThirdLogin(XZBDevice.DOWNLOAD_LIST_FAILED, xLWxParam, p.I, hashMap);
                        WXEntryActivity.a = false;
                        this.a.w.setEnabled(false);
                        this.a.a(true, false);
                        return;
                    }
                    this.a.a(2131231680);
                case 2131756466:
                case 2131756797:
                    if (c.a(Constants.PACKAGE_QQ)) {
                        p = this.a.B;
                        a = this.a.K;
                        LoginHelper.d = true;
                        XLQQParam xLQQParam = new XLQQParam();
                        xLQQParam.mAppID = "1101105049";
                        hashMap = new HashMap();
                        hashMap.put("loginFrom", a);
                        hashMap.put("from", obj);
                        XLUserUtil.getInstance().userThirdLogin(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, xLQQParam, p.I, hashMap);
                        this.a.a(true, false);
                        return;
                    }
                    this.a.a(2131231670);
                case 2131756467:
                case 2131756798:
                    p = this.a.B;
                    a = this.a.K;
                    LoginHelper.d = true;
                    XLSinaParam xLSinaParam = new XLSinaParam();
                    xLSinaParam.mSinaAppId = "4286195229";
                    xLSinaParam.mRedirectUrl = "http://m.xunlei.com";
                    hashMap = new HashMap();
                    hashMap.put("loginFrom", a);
                    hashMap.put("from", obj);
                    XLUserUtil.getInstance().userThirdLogin(XZBDevice.DOWNLOAD_LIST_RECYCLE, xLSinaParam, p.I, hashMap);
                    this.a.a(true, false);
                case 2131756469:
                case 2131756800:
                    if (b.a(this.a)) {
                        p = this.a.B;
                        a = this.a.K;
                        LoginHelper.d = true;
                        XLXmParam xLXmParam = new XLXmParam();
                        xLXmParam.mLoginReqFrom = "shoulei_miui";
                        hashMap = new HashMap();
                        hashMap.put("loginFrom", a);
                        hashMap.put("from", obj);
                        XLUserUtil.getInstance().userThirdLogin(1, xLXmParam, p.I, hashMap);
                        this.a.a(true, false);
                        return;
                    }
                    this.a.a(2131231706);
                default:
                    break;
            }
        }
    }
}
