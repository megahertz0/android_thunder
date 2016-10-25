package com.xunlei.downloadprovider.member.login;

import com.xunlei.common.member.XLAvatarItem;
import com.xunlei.common.member.XLBindedOtherAccountItem;
import com.xunlei.common.member.XLHspeedCapacity;
import com.xunlei.common.member.XLLixianCapacity;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.XLThirdUserInfo;
import com.xunlei.common.member.XLUserInfo;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.member.XLUserUtil;
import java.util.List;
import org.json.JSONObject;

// compiled from: XunleiLoginHelper.java
public class j {
    private static j b;
    final XLOnUserListener a;

    // compiled from: XunleiLoginHelper.java
    public static class a implements XLOnUserListener {
        public boolean onHighSpeedCatched(int i, XLUserInfo xLUserInfo, XLHspeedCapacity xLHspeedCapacity, Object obj, int i2) {
            return true;
        }

        public boolean onLixianCatched(int i, XLUserInfo xLUserInfo, XLLixianCapacity xLLixianCapacity, Object obj, int i2) {
            return true;
        }

        public boolean onUserActivated(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
            return true;
        }

        public boolean onUserInfoCatched(int i, List<USERINFOKEY> list, XLUserInfo xLUserInfo, Object obj, int i2) {
            return true;
        }

        public boolean onUserGetCityInfo(int i, JSONObject jSONObject, Object obj, String str, int i2) {
            return true;
        }

        public boolean onUserSetInfo(int i, Object obj, String str, int i2) {
            return true;
        }

        public boolean onUserLogin(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
            return true;
        }

        public boolean onUserThirdLogin(int i, XLUserInfo xLUserInfo, int i2, int i3, Object obj, String str, int i4) {
            return true;
        }

        public boolean onUserLogout(int i, XLUserInfo xLUserInfo, Object obj, int i2) {
            return true;
        }

        public boolean onUserPing(int i, Object obj, String str, int i2) {
            return true;
        }

        public boolean onUserPreVerifyedCode(int i, Object obj, String str, int i2) {
            return true;
        }

        public boolean onUserResumed(int i) {
            return true;
        }

        public boolean onUserQRCodeLoginAuth(int i, Object obj, String str, int i2) {
            return true;
        }

        public boolean onUserGetQRCode(int i, String str, byte[] bArr, Object obj, String str2, int i2) {
            return true;
        }

        public boolean onUserQRCodeLogin(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
            return true;
        }

        public boolean onUserGetRecommendAvatars(int i, XLAvatarItem[] xLAvatarItemArr, Object obj, String str, int i2) {
            return false;
        }

        public boolean onUserSelectRecommendAvatar(int i, Object obj, String str, int i2) {
            return false;
        }

        public boolean onUserSetAvatar(int i, Object obj, String str, int i2) {
            return true;
        }

        public boolean onUserGetBindedOtherAccount(int i, XLBindedOtherAccountItem[] xLBindedOtherAccountItemArr, Object obj, String str, int i2) {
            return true;
        }

        public boolean onUserGetOtherAccountInfo(int i, int i2, XLThirdUserInfo xLThirdUserInfo, Object obj, String str, int i3) {
            return true;
        }

        public boolean onUserBindedOtherAccount(int i, int i2, XLThirdUserInfo xLThirdUserInfo, Object obj, String str, int i3) {
            return true;
        }

        public boolean onUserUnBindeOtherAccount(int i, int i2, Object obj, String str, int i3) {
            return true;
        }

        public boolean onUserAqSendMessage(int i, String str, String str2, Object obj, int i2) {
            return false;
        }

        public boolean onUserAqBindMobile(int i, String str, String str2, Object obj, int i2) {
            return false;
        }

        public boolean onUserSessionidLogin(int i, String str, XLUserInfo xLUserInfo, Object obj, int i2) {
            return true;
        }

        public boolean onUserSuspended(int i) {
            return true;
        }

        public boolean onUserTokenLogin(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
            return true;
        }

        public boolean onUserVerifyCodeUpdated(int i, String str, int i2, byte[] bArr, Object obj, int i3) {
            return true;
        }

        public boolean onUserVerifyedCode(int i, Object obj, String str, int i2) {
            return true;
        }
    }

    private j() {
        this.a = new a();
    }

    static {
        b = null;
    }

    public static j a() {
        synchronized (j.class) {
            if (b == null) {
                b = new j();
            }
        }
        return b;
    }

    public static int a(String str, String str2, String str3, String str4, XLOnUserListener xLOnUserListener, Object obj) {
        return XLUserUtil.getInstance().userAccountLogin(str, str2, str3, str4, xLOnUserListener, obj);
    }

    public static int a(XLOnUserListener xLOnUserListener, Object obj) {
        return XLUserUtil.getInstance().userAutoLogin(null, null, xLOnUserListener, obj);
    }

    public static int a(XLOnUserListener xLOnUserListener) {
        return XLUserUtil.getInstance().userLogout(xLOnUserListener, null);
    }
}
