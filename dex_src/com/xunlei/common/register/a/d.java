package com.xunlei.common.register.a;

import com.umeng.socialize.bean.StatusCode;
import com.xunlei.common.register.XLRegisterListener;
import com.xunlei.common.stat.XLStatPack;
import com.xunlei.common.stat.XLStatUtil;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: XLStatRegisterListener.java
public final class d implements XLRegisterListener {
    private XLStatUtil a;

    public d(XLStatUtil xLStatUtil) {
        this.a = null;
        this.a = xLStatUtil;
    }

    public final boolean onPhoneRegister(int i, String str, int i2, int i3, String str2) {
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 200007;
        xLStatPack.mSvrDomain = "zhuce.xunlei.com";
        xLStatPack.mUserId = i3;
        xLStatPack.mErrorCode = i;
        this.a.report(i2, xLStatPack);
        XLStatPack xLStatPack2 = new XLStatPack();
        xLStatPack2.mCommandID = 200012;
        xLStatPack2.mSvrDomain = "zhuce.xunlei.com";
        xLStatPack.mUserId = i3;
        xLStatPack2.mErrorCode = 0;
        this.a.reportSpecialStat(SimpleLog.LOG_LEVEL_DEBUG, xLStatPack2);
        return false;
    }

    public final boolean onEmailRegister(int i, String str, int i2, int i3, String str2) {
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 200001;
        xLStatPack.mSvrDomain = "zhuce.xunlei.com";
        xLStatPack.mUserId = i3;
        xLStatPack.mErrorCode = i;
        this.a.report(i2, xLStatPack);
        return false;
    }

    public final boolean onSendMessage(int i, String str, int i2, int i3, String str2) {
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 200005;
        if (i3 == 1) {
            xLStatPack.mCommandID = 200006;
        }
        xLStatPack.mSvrDomain = "zhuce.xunlei.com";
        xLStatPack.mErrorCode = i;
        this.a.report(i2, xLStatPack);
        return false;
    }

    public final boolean onCheckNeedVerifyCode(int i, String str, int i2, int i3, String str2) {
        return false;
    }

    public final boolean onGetVerifyCode(int i, String str, int i2, byte[] bArr, String str2, String str3, String str4, String str5) {
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 200003;
        xLStatPack.mSvrDomain = "zhuce.xunlei.com";
        xLStatPack.mErrorCode = i;
        this.a.report(i2, xLStatPack);
        return false;
    }

    public final boolean onCheckBind(int i, String str, int i2, int i3) {
        return false;
    }

    public final boolean onCheckPassWordStrength(int i, String str, int i2, int i3) {
        return false;
    }

    public final boolean onPhoneRegAndLogin(int i, String str, int i2, int i3, String str2) {
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 200000;
        xLStatPack.mSvrDomain = "zhuce.xunlei.com";
        xLStatPack.mUserId = i3;
        xLStatPack.mErrorCode = i;
        this.a.report(i2, xLStatPack);
        XLStatPack xLStatPack2 = new XLStatPack();
        xLStatPack2.mCommandID = 200012;
        xLStatPack2.mSvrDomain = "zhuce.xunlei.com";
        xLStatPack.mUserId = i3;
        xLStatPack2.mErrorCode = 0;
        this.a.reportSpecialStat(1, xLStatPack2);
        return false;
    }

    public final boolean onOldUserNameRegister(int i, String str, int i2, int i3, String str2) {
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 200002;
        xLStatPack.mSvrDomain = "zhuce.xunlei.com";
        xLStatPack.mUserId = i3;
        xLStatPack.mErrorCode = i;
        this.a.report(i2, xLStatPack);
        return false;
    }

    public final boolean onMobileVerifyCodeAccept(String str, int i) {
        XLStatUtil.mAcceptPhoneCode = true;
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 200009;
        xLStatPack.mSvrDomain = "zhuce.xunlei.com";
        xLStatPack.mErrorCode = 0;
        this.a.reportSpecialStat(i, xLStatPack);
        this.a.registerSpecialStatReq(StatusCode.ST_CODE_NO_SMS, i);
        return false;
    }
}
