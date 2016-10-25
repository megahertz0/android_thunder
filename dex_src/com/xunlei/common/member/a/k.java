package com.xunlei.common.member.a;

import com.xunlei.common.member.XLAvatarItem;
import com.xunlei.common.member.XLBindedOtherAccountItem;
import com.xunlei.common.member.XLHspeedCapacity;
import com.xunlei.common.member.XLLixianCapacity;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.XLThirdUserInfo;
import com.xunlei.common.member.XLUserInfo;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.stat.XLStatPack;
import com.xunlei.common.stat.XLStatUtil;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.List;
import org.android.spdy.SpdyAgent;
import org.json.JSONObject;

// compiled from: XLStatMemberListener.java
public final class k implements XLOnUserListener {
    private XLStatUtil a;

    public k(XLStatUtil xLStatUtil) {
        this.a = null;
        this.a = xLStatUtil;
    }

    public final boolean onUserLogin(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mUserId = xLUserInfo.getIntValue(USERINFOKEY.UserID);
        xLStatPack.mErrorCode = i;
        g c = m.a().c(i2);
        if (c != null) {
            xLStatPack.mSvrDomain = c.a;
            xLStatPack.mRetryNum = c.b;
        }
        this.a.report(i2, xLStatPack, true);
        return false;
    }

    public final boolean onUserThirdLogin(int i, XLUserInfo xLUserInfo, int i2, int i3, Object obj, String str, int i4) {
        XLStatPack xLStatPack = new XLStatPack();
        switch (i2) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                xLStatPack.mCommandID = 100302;
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                xLStatPack.mCommandID = 100202;
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                xLStatPack.mCommandID = 100102;
                break;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                xLStatPack.mCommandID = 100602;
                break;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                xLStatPack.mCommandID = 100702;
                break;
        }
        xLStatPack.mErrorCode = i;
        xLStatPack.mUserId = xLUserInfo.getIntValue(USERINFOKEY.UserID);
        g c = m.a().c(i4);
        if (c != null) {
            xLStatPack.mSvrDomain = c.a;
            xLStatPack.mRetryNum = c.b;
        }
        this.a.report(i4, xLStatPack);
        return false;
    }

    public final boolean onUserTokenLogin(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 105000;
        xLStatPack.mUserId = xLUserInfo.getIntValue(USERINFOKEY.UserID);
        xLStatPack.mErrorCode = i;
        g c = m.a().c(i2);
        if (c != null) {
            xLStatPack.mSvrDomain = c.a;
            xLStatPack.mRetryNum = c.b;
        }
        this.a.report(i2, xLStatPack);
        return false;
    }

    public final boolean onUserSessionidLogin(int i, String str, XLUserInfo xLUserInfo, Object obj, int i2) {
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 104000;
        xLStatPack.mUserId = xLUserInfo.getIntValue(USERINFOKEY.UserID);
        xLStatPack.mErrorCode = i;
        g c = m.a().c(i2);
        if (c != null) {
            xLStatPack.mSvrDomain = c.a;
            xLStatPack.mRetryNum = c.b;
        }
        this.a.report(i2, xLStatPack);
        return false;
    }

    public final boolean onUserLogout(int i, XLUserInfo xLUserInfo, Object obj, int i2) {
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 100008;
        xLStatPack.mUserId = xLUserInfo.getIntValue(USERINFOKEY.UserID);
        xLStatPack.mErrorCode = i;
        g c = m.a().c(i2);
        if (c != null) {
            xLStatPack.mSvrDomain = c.a;
            xLStatPack.mRetryNum = c.b;
        }
        this.a.report(i2, xLStatPack);
        return false;
    }

    public final boolean onUserInfoCatched(int i, List<USERINFOKEY> list, XLUserInfo xLUserInfo, Object obj, int i2) {
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 100007;
        xLStatPack.mUserId = xLUserInfo.getIntValue(USERINFOKEY.UserID);
        xLStatPack.mErrorCode = i;
        g c = m.a().c(i2);
        if (c != null) {
            xLStatPack.mSvrDomain = c.a;
            xLStatPack.mRetryNum = c.b;
        }
        this.a.report(i2, xLStatPack);
        return false;
    }

    public final boolean onUserGetCityInfo(int i, JSONObject jSONObject, Object obj, String str, int i2) {
        return false;
    }

    public final boolean onUserSetInfo(int i, Object obj, String str, int i2) {
        return false;
    }

    public final boolean onUserActivated(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 100009;
        xLStatPack.mUserId = xLUserInfo.getIntValue(USERINFOKEY.UserID);
        xLStatPack.mErrorCode = i;
        g c = m.a().c(i2);
        if (c != null) {
            xLStatPack.mSvrDomain = c.a;
            xLStatPack.mRetryNum = c.b;
        }
        this.a.report(i2, xLStatPack);
        return false;
    }

    public final boolean onUserPing(int i, Object obj, String str, int i2) {
        return false;
    }

    public final boolean onUserVerifyCodeUpdated(int i, String str, int i2, byte[] bArr, Object obj, int i3) {
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 200003;
        xLStatPack.mErrorCode = i;
        g c = m.a().c(i3);
        if (c != null) {
            xLStatPack.mSvrDomain = c.a;
            xLStatPack.mRetryNum = c.b;
        }
        this.a.report(i3, xLStatPack);
        return false;
    }

    public final boolean onUserPreVerifyedCode(int i, Object obj, String str, int i2) {
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 100010;
        xLStatPack.mErrorCode = i;
        g c = m.a().c(i2);
        if (c != null) {
            xLStatPack.mSvrDomain = c.a;
            xLStatPack.mRetryNum = c.b;
        }
        this.a.report(i2, xLStatPack);
        return false;
    }

    public final boolean onUserVerifyedCode(int i, Object obj, String str, int i2) {
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 200004;
        xLStatPack.mErrorCode = i;
        g c = m.a().c(i2);
        if (c != null) {
            xLStatPack.mSvrDomain = c.a;
            xLStatPack.mRetryNum = c.b;
        }
        this.a.report(i2, xLStatPack);
        return false;
    }

    public final boolean onHighSpeedCatched(int i, XLUserInfo xLUserInfo, XLHspeedCapacity xLHspeedCapacity, Object obj, int i2) {
        return false;
    }

    public final boolean onLixianCatched(int i, XLUserInfo xLUserInfo, XLLixianCapacity xLLixianCapacity, Object obj, int i2) {
        return false;
    }

    public final boolean onUserSuspended(int i) {
        return false;
    }

    public final boolean onUserResumed(int i) {
        return false;
    }

    public final boolean onUserQRCodeLoginAuth(int i, Object obj, String str, int i2) {
        return false;
    }

    public final boolean onUserGetQRCode(int i, String str, byte[] bArr, Object obj, String str2, int i2) {
        return false;
    }

    public final boolean onUserQRCodeLogin(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
        return false;
    }

    public final boolean onUserGetRecommendAvatars(int i, XLAvatarItem[] xLAvatarItemArr, Object obj, String str, int i2) {
        return false;
    }

    public final boolean onUserSelectRecommendAvatar(int i, Object obj, String str, int i2) {
        return false;
    }

    public final boolean onUserSetAvatar(int i, Object obj, String str, int i2) {
        return false;
    }

    public final boolean onUserGetBindedOtherAccount(int i, XLBindedOtherAccountItem[] xLBindedOtherAccountItemArr, Object obj, String str, int i2) {
        return false;
    }

    public final boolean onUserBindedOtherAccount(int i, int i2, XLThirdUserInfo xLThirdUserInfo, Object obj, String str, int i3) {
        return false;
    }

    public final boolean onUserGetOtherAccountInfo(int i, int i2, XLThirdUserInfo xLThirdUserInfo, Object obj, String str, int i3) {
        return false;
    }

    public final boolean onUserUnBindeOtherAccount(int i, int i2, Object obj, String str, int i3) {
        return false;
    }

    public final boolean onUserAqSendMessage(int i, String str, String str2, Object obj, int i2) {
        return false;
    }

    public final boolean onUserAqBindMobile(int i, String str, String str2, Object obj, int i2) {
        return false;
    }
}
