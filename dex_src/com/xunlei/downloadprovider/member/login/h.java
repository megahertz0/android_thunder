package com.xunlei.downloadprovider.member.login;

import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.constants.ConstantsAPI.Token;
import com.umeng.message.MsgConstant;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.common.member.XLBindedOtherAccountItem;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLHspeedCapacity;
import com.xunlei.common.member.XLLixianCapacity;
import com.xunlei.common.member.XLThirdUserInfo;
import com.xunlei.common.member.XLUserInfo;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.downloadprovider.frame.user.account.l;
import com.xunlei.downloadprovider.member.login.LoginHelper.n;
import com.xunlei.downloadprovider.member.login.j.a;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.downloadprovider.wxapi.WXEntryActivity;
import com.xunlei.downloadprovidershare.d;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.List;
import java.util.Map;
import org.android.spdy.SpdyAgent;

// compiled from: LoginHelper.java
final class h extends a {
    final /* synthetic */ LoginHelper a;

    h(LoginHelper loginHelper) {
        this.a = loginHelper;
    }

    public final boolean onHighSpeedCatched(int i, XLUserInfo xLUserInfo, XLHspeedCapacity xLHspeedCapacity, Object obj, int i2) {
        if (i != 0 || xLUserInfo == null || xLHspeedCapacity == null) {
            new StringBuilder("onHighSpeedCatched() errorCode=").append(i).append(" errorDesc=").append(XLErrorCode.getErrorDesc(i));
        } else {
            Object stringValue = xLUserInfo.getStringValue(USERINFOKEY.UserID);
            if (!TextUtils.isEmpty(stringValue)) {
                long j = xLHspeedCapacity.total_capacity;
                long j2 = j - xLHspeedCapacity.used_capacity;
                new StringBuilder("onHighSpeedCatched() errorCode=").append(i).append(" errorDesc=").append(XLErrorCode.getErrorDesc(i)).append(" capacity=").append(j).append(" remain=").append(j2);
                LoginHelper loginHelper = this.a;
                Long.parseLong(stringValue);
                LoginHelper.a(loginHelper, i, j, j2);
            }
        }
        return false;
    }

    public final boolean onLixianCatched(int i, XLUserInfo xLUserInfo, XLLixianCapacity xLLixianCapacity, Object obj, int i2) {
        new StringBuilder("onLixianCatched() errorCode=").append(i).append(" errorDesc=").append(XLErrorCode.getErrorDesc(i));
        if (xLLixianCapacity != null) {
            b bVar = new b();
            bVar.a = i;
            if (i == 0) {
                bVar.e = (long) xLLixianCapacity.total_capacity;
                bVar.c = (long) xLLixianCapacity.used_capacity;
            }
            this.a.a(i, bVar);
        }
        return false;
    }

    public final boolean onUserActivated(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
        new StringBuilder("onUserActivated() errorCode=").append(i).append(" errorDesc=").append(str);
        return false;
    }

    public final boolean onUserInfoCatched(int i, List<USERINFOKEY> list, XLUserInfo xLUserInfo, Object obj, int i2) {
        new StringBuilder("onUserInfoCatched() errorCode=").append(i).append(" errorDesc=").append(XLErrorCode.getErrorDesc(i));
        if (xLUserInfo != null) {
            int intValue = xLUserInfo.getIntValue(USERINFOKEY.VasType);
            xLUserInfo.getIntValue(USERINFOKEY.VasId);
            int intValue2 = xLUserInfo.getIntValue(USERINFOKEY.vip_level);
            int intValue3 = xLUserInfo.getIntValue(USERINFOKEY.PayId);
            int intValue4 = xLUserInfo.getIntValue(USERINFOKEY.Account);
            String stringValue = xLUserInfo.getStringValue(USERINFOKEY.ExpireDate);
            LoginHelper.a(this.a, xLUserInfo.getStringValue(USERINFOKEY.ImgURL));
            LoginHelper.b(this.a, xLUserInfo.getIntValue(USERINFOKEY.IsVip));
            String stringValue2 = xLUserInfo.getStringValue(USERINFOKEY.NickName);
            LoginHelper loginHelper = this.a;
            new StringBuilder("onCompletedRefreshUserInfo() errCode=").append(i).append(" errorDesc=").append(XLErrorCode.getErrorDesc(i));
            if (i == 0 && LoginHelper.c()) {
                boolean z;
                loginHelper.h = intValue;
                loginHelper.m = stringValue;
                loginHelper.e = intValue2;
                loginHelper.b(loginHelper.e);
                loginHelper.f = intValue4;
                loginHelper.g = intValue3;
                loginHelper.i = stringValue2;
                if (loginHelper.q == loginHelper.h && loginHelper.s == loginHelper.e) {
                    if ((TextUtils.isEmpty(loginHelper.r) || loginHelper.m.compareTo(loginHelper.r) == 0) && loginHelper.t == loginHelper.g) {
                        z = false;
                        loginHelper.a(i, z);
                    }
                }
                z = true;
                loginHelper.a(i, z);
            } else {
                loginHelper.a(i, false);
            }
            LoginHelper loginHelper2 = this.a;
            loginHelper2.B = new n(loginHelper2);
            loginHelper2.B.a = xLUserInfo.getIntValue(USERINFOKEY.other_IsVip);
            loginHelper2.B.b = xLUserInfo.getIntValue(USERINFOKEY.other_VipLevel);
            loginHelper2.B.c = xLUserInfo.getStringValue(USERINFOKEY.other_ExpireDate);
            loginHelper2.B.d = xLUserInfo.getIntValue(USERINFOKEY.other_VasType);
            loginHelper2.B.e = xLUserInfo.getStringValue(USERINFOKEY.other_PayName);
            loginHelper2.B.f = xLUserInfo.getIntValue(USERINFOKEY.other_VipGrow);
            loginHelper2.B.g = xLUserInfo.getIntValue(USERINFOKEY.other_VipDayGrow);
            loginHelper2.B.h = xLUserInfo.getIntValue(USERINFOKEY.other_IsAutoDeduct);
            loginHelper2.B.i = xLUserInfo.getIntValue(USERINFOKEY.other_IsRemind);
            loginHelper2.B.j = xLUserInfo.getIntValue(USERINFOKEY.other_PayId);
            loginHelper2.B.k = xLUserInfo.getIntValue(USERINFOKEY.other_IsYear);
            loginHelper2.B.l = xLUserInfo.getStringValue(USERINFOKEY.other_Register);
        }
        return false;
    }

    public final boolean onUserLogin(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
        new StringBuilder("onUserLogin() errorCode=").append(i).append(" errorDesc=").append(str);
        LoginHelper.a(this.a, i, xLUserInfo, obj);
        return false;
    }

    public final boolean onUserPing(int i, Object obj, String str, int i2) {
        new StringBuilder("onUserPing() errorCode=").append(i).append(" errorDesc=").append(str);
        return super.onUserPing(i, obj, str, i2);
    }

    public final boolean onUserPreVerifyedCode(int i, Object obj, String str, int i2) {
        new StringBuilder("onUserPreVerifyedCode() errorCode=").append(i).append(" errorDesc=").append(str);
        return super.onUserPreVerifyedCode(i, obj, str, i2);
    }

    public final boolean onUserResumed(int i) {
        new StringBuilder("onUserResumed() errorCode=").append(i).append(" errorDesc=").append(XLErrorCode.getErrorDesc(i));
        return super.onUserResumed(i);
    }

    public final boolean onUserSuspended(int i) {
        new StringBuilder("onUserSuspended() errorCode=").append(i).append(" errorDesc=").append(XLErrorCode.getErrorDesc(i));
        return super.onUserSuspended(i);
    }

    public final boolean onUserSessionidLogin(int i, String str, XLUserInfo xLUserInfo, Object obj, int i2) {
        new StringBuilder("onUserSessionidLogin() errorCode=").append(i).append(" errorDesc=").append(str);
        LoginHelper.a(this.a, i, xLUserInfo, obj);
        this.a.s();
        return false;
    }

    public final boolean onUserThirdLogin(int i, XLUserInfo xLUserInfo, int i2, int i3, Object obj, String str, int i4) {
        String str2;
        LoginHelper.f(this.a);
        LoginHelper loginHelper = this.a;
        boolean z = i == 0 && i3 == 1;
        LoginHelper.a(loginHelper, z);
        if (LoginHelper.g(this.a)) {
            LoginHelper.a(this.a, Long.valueOf(xLUserInfo.getStringValue(USERINFOKEY.UserID)).longValue());
            l.a().b(i2, xLUserInfo.getStringValue(USERINFOKEY.NickName));
        }
        String str3 = com.umeng.a.d;
        switch (i2) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                str2 = MsgConstant.MESSAGE_SYSTEM_SOURCE_XIAOMI;
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                str2 = "weibo";
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                WXEntryActivity.a = true;
                str2 = Token.WX_TOKEN_PLATFORMID_VALUE;
                break;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                str2 = Constants.SOURCE_QQ;
                break;
            default:
                str2 = str3;
                break;
        }
        str3 = (String) ((Map) obj).get("loginFrom");
        String str4 = (String) ((Map) obj).get("from");
        if (i == 0) {
            new StringBuilder("onUserThirdLogin() Login Success! ThirdType=").append(str2).append(" isThirdFirstLogin=").append(i3);
            LoginHelper.b(this.a, com.umeng.a.d);
            String str5 = LoginHelper.g(this.a) ? MiPushClient.COMMAND_REGISTER : "login";
            String str6 = "login_third_success";
            g a = g.a("android_login_third", str6, str6);
            a.a("from_src", str3);
            a.a("from", str4);
            a.a("login_account", str2);
            a.a("result_type", str5);
            com.xunlei.downloadprovider.member.register.a.a(a);
            if (i2 == 2) {
                d.b().d();
            }
        } else {
            new StringBuilder("onUserThirdLogin() Login Fail! errorCode=").append(i).append(" errorDesc=").append(str).append(" ThirdType=").append(str2);
            str3 = "login_third_fail";
            g a2 = g.a("android_login_third", str3, str3);
            a2.a("from", str4);
            a2.a("login_account", str2);
            a2.a("failtype", (long) i);
            com.xunlei.downloadprovider.member.register.a.a(a2);
        }
        LoginHelper.a(this.a, i, xLUserInfo, obj);
        this.a.s();
        return false;
    }

    public final boolean onUserTokenLogin(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
        new StringBuilder("onUserTokenLogin() errorCode=").append(i).append(" errorDesc=").append(str);
        LoginHelper.a(this.a, i, xLUserInfo, obj);
        this.a.s();
        return false;
    }

    public final boolean onUserVerifyCodeUpdated(int i, String str, int i2, byte[] bArr, Object obj, int i3) {
        new StringBuilder("onUserVerifyCodeUpdated() errorCode=").append(i).append(" errorDesc=").append(XLErrorCode.getErrorDesc(i));
        if (this.a.H != null) {
            this.a.H.a(i, str, bArr);
        }
        return true;
    }

    public final boolean onUserVerifyedCode(int i, Object obj, String str, int i2) {
        new StringBuilder("onUserVerifyedCode() errorCode=").append(i).append(" errorDesc=").append(str);
        return super.onUserVerifyedCode(i, obj, str, i2);
    }

    public final boolean onUserSetInfo(int i, Object obj, String str, int i2) {
        new StringBuilder("onUserSetInfo() errorCode=").append(i).append(" errorDesc=").append(str);
        LoginHelper.h(this.a).a(i);
        return super.onUserSetInfo(i, obj, str, i2);
    }

    public final boolean onUserSetAvatar(int i, Object obj, String str, int i2) {
        new StringBuilder("onUserSetAvatar() errorCode=").append(i).append(" errorDesc=").append(str);
        LoginHelper.i(this.a).a(i, (String) obj);
        return super.onUserSetAvatar(i, obj, str, i2);
    }

    public final boolean onUserGetBindedOtherAccount(int i, XLBindedOtherAccountItem[] xLBindedOtherAccountItemArr, Object obj, String str, int i2) {
        new StringBuilder("onUserGetBindedOtherAccount() errorCode=").append(i).append(" errorDesc=").append(str);
        if (i == 0) {
            LoginHelper.j(this.a).a(xLBindedOtherAccountItemArr);
        }
        return super.onUserGetBindedOtherAccount(i, xLBindedOtherAccountItemArr, obj, str, i2);
    }

    public final boolean onUserBindedOtherAccount(int i, int i2, XLThirdUserInfo xLThirdUserInfo, Object obj, String str, int i3) {
        new StringBuilder("onUserBindedOtherAccount() errorCode=").append(i).append(" errorDesc=").append(str).append(" thirdType=").append(i2);
        if (i == 0) {
            LoginHelper.k(this.a).a(i2, xLThirdUserInfo);
        } else {
            LoginHelper.k(this.a).a(i2, i);
        }
        return super.onUserBindedOtherAccount(i, i2, xLThirdUserInfo, obj, str, i3);
    }

    public final boolean onUserUnBindeOtherAccount(int i, int i2, Object obj, String str, int i3) {
        new StringBuilder("onUserUnBindeOtherAccount() errorCode=").append(i).append(" errorDesc=").append(str).append(" thirdType=").append(i2);
        if (i == 0) {
            LoginHelper.l(this.a).a(i2);
        } else {
            LoginHelper.l(this.a).a();
        }
        return super.onUserUnBindeOtherAccount(i, i2, obj, str, i3);
    }

    public final boolean onUserGetOtherAccountInfo(int i, int i2, XLThirdUserInfo xLThirdUserInfo, Object obj, String str, int i3) {
        new StringBuilder("onUserGetOtherAccountInfo() errorCode=").append(i).append(" errorDesc=").append(str).append(" thirdType=").append(i2);
        if (i == 0) {
            LoginHelper.m(this.a).a(xLThirdUserInfo);
        }
        return super.onUserGetOtherAccountInfo(i, i2, xLThirdUserInfo, obj, str, i3);
    }
}
