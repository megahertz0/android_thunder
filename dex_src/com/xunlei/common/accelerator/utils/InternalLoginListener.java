package com.xunlei.common.accelerator.utils;

import com.xunlei.common.member.XLAvatarItem;
import com.xunlei.common.member.XLBindedOtherAccountItem;
import com.xunlei.common.member.XLHspeedCapacity;
import com.xunlei.common.member.XLLixianCapacity;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.XLThirdUserInfo;
import com.xunlei.common.member.XLUserInfo;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import java.util.List;
import org.json.JSONObject;

public class InternalLoginListener implements XLOnUserListener {
    public boolean onUserLogin(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
        return false;
    }

    public boolean onUserThirdLogin(int i, XLUserInfo xLUserInfo, int i2, int i3, Object obj, String str, int i4) {
        return false;
    }

    public boolean onUserTokenLogin(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
        return false;
    }

    public boolean onUserSessionidLogin(int i, String str, XLUserInfo xLUserInfo, Object obj, int i2) {
        return false;
    }

    public boolean onUserLogout(int i, XLUserInfo xLUserInfo, Object obj, int i2) {
        return false;
    }

    public boolean onUserInfoCatched(int i, List<USERINFOKEY> list, XLUserInfo xLUserInfo, Object obj, int i2) {
        return false;
    }

    public boolean onUserGetCityInfo(int i, JSONObject jSONObject, Object obj, String str, int i2) {
        return false;
    }

    public boolean onUserSetInfo(int i, Object obj, String str, int i2) {
        return false;
    }

    public boolean onUserActivated(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
        return false;
    }

    public boolean onUserPing(int i, Object obj, String str, int i2) {
        return false;
    }

    public boolean onUserVerifyCodeUpdated(int i, String str, int i2, byte[] bArr, Object obj, int i3) {
        return false;
    }

    public boolean onUserPreVerifyedCode(int i, Object obj, String str, int i2) {
        return false;
    }

    public boolean onUserVerifyedCode(int i, Object obj, String str, int i2) {
        return false;
    }

    public boolean onHighSpeedCatched(int i, XLUserInfo xLUserInfo, XLHspeedCapacity xLHspeedCapacity, Object obj, int i2) {
        return false;
    }

    public boolean onLixianCatched(int i, XLUserInfo xLUserInfo, XLLixianCapacity xLLixianCapacity, Object obj, int i2) {
        return false;
    }

    public boolean onUserSuspended(int i) {
        return false;
    }

    public boolean onUserResumed(int i) {
        return false;
    }

    public boolean onUserQRCodeLoginAuth(int i, Object obj, String str, int i2) {
        return false;
    }

    public boolean onUserGetQRCode(int i, String str, byte[] bArr, Object obj, String str2, int i2) {
        return false;
    }

    public boolean onUserQRCodeLogin(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
        return false;
    }

    public boolean onUserGetRecommendAvatars(int i, XLAvatarItem[] xLAvatarItemArr, Object obj, String str, int i2) {
        return false;
    }

    public boolean onUserSelectRecommendAvatar(int i, Object obj, String str, int i2) {
        return false;
    }

    public boolean onUserSetAvatar(int i, Object obj, String str, int i2) {
        return false;
    }

    public boolean onUserGetBindedOtherAccount(int i, XLBindedOtherAccountItem[] xLBindedOtherAccountItemArr, Object obj, String str, int i2) {
        return false;
    }

    public boolean onUserBindedOtherAccount(int i, int i2, XLThirdUserInfo xLThirdUserInfo, Object obj, String str, int i3) {
        return false;
    }

    public boolean onUserGetOtherAccountInfo(int i, int i2, XLThirdUserInfo xLThirdUserInfo, Object obj, String str, int i3) {
        return false;
    }

    public boolean onUserUnBindeOtherAccount(int i, int i2, Object obj, String str, int i3) {
        return false;
    }

    public boolean onUserAqSendMessage(int i, String str, String str2, Object obj, int i2) {
        return false;
    }

    public boolean onUserAqBindMobile(int i, String str, String str2, Object obj, int i2) {
        return false;
    }
}
