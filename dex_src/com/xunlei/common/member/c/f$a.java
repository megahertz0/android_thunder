package com.xunlei.common.member.c;

import com.xunlei.common.base.XLLog;
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

// compiled from: UserKeyLoginTask.java
public class f$a implements XLOnUserListener {
    private /* synthetic */ f a;

    public f$a(f fVar) {
        this.a = fVar;
    }

    public final boolean onUserLogin(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
        XLLog.v("XLOnUidLoginListener", new StringBuilder("onUserLogin errorcode = ").append(i).toString());
        f.a(this.a, i);
        return false;
    }

    public final boolean onUserThirdLogin(int i, XLUserInfo xLUserInfo, int i2, int i3, Object obj, String str, int i4) {
        return false;
    }

    public final boolean onUserTokenLogin(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
        return false;
    }

    public final boolean onUserSessionidLogin(int i, String str, XLUserInfo xLUserInfo, Object obj, int i2) {
        return false;
    }

    public final boolean onUserLogout(int i, XLUserInfo xLUserInfo, Object obj, int i2) {
        return false;
    }

    public final boolean onUserInfoCatched(int i, List<USERINFOKEY> list, XLUserInfo xLUserInfo, Object obj, int i2) {
        return false;
    }

    public final boolean onUserGetCityInfo(int i, JSONObject jSONObject, Object obj, String str, int i2) {
        return false;
    }

    public final boolean onUserSetInfo(int i, Object obj, String str, int i2) {
        return false;
    }

    public final boolean onUserActivated(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
        return false;
    }

    public final boolean onUserPing(int i, Object obj, String str, int i2) {
        return false;
    }

    public final boolean onUserVerifyCodeUpdated(int i, String str, int i2, byte[] bArr, Object obj, int i3) {
        return false;
    }

    public final boolean onUserPreVerifyedCode(int i, Object obj, String str, int i2) {
        return false;
    }

    public final boolean onUserVerifyedCode(int i, Object obj, String str, int i2) {
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
