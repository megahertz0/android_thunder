package com.xunlei.common.member;

import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import java.util.List;
import org.json.JSONObject;

public interface XLOnUserListener {
    boolean onHighSpeedCatched(int i, XLUserInfo xLUserInfo, XLHspeedCapacity xLHspeedCapacity, Object obj, int i2);

    boolean onLixianCatched(int i, XLUserInfo xLUserInfo, XLLixianCapacity xLLixianCapacity, Object obj, int i2);

    boolean onUserActivated(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2);

    boolean onUserAqBindMobile(int i, String str, String str2, Object obj, int i2);

    boolean onUserAqSendMessage(int i, String str, String str2, Object obj, int i2);

    boolean onUserBindedOtherAccount(int i, int i2, XLThirdUserInfo xLThirdUserInfo, Object obj, String str, int i3);

    boolean onUserGetBindedOtherAccount(int i, XLBindedOtherAccountItem[] xLBindedOtherAccountItemArr, Object obj, String str, int i2);

    boolean onUserGetCityInfo(int i, JSONObject jSONObject, Object obj, String str, int i2);

    boolean onUserGetOtherAccountInfo(int i, int i2, XLThirdUserInfo xLThirdUserInfo, Object obj, String str, int i3);

    boolean onUserGetQRCode(int i, String str, byte[] bArr, Object obj, String str2, int i2);

    boolean onUserGetRecommendAvatars(int i, XLAvatarItem[] xLAvatarItemArr, Object obj, String str, int i2);

    boolean onUserInfoCatched(int i, List<USERINFOKEY> list, XLUserInfo xLUserInfo, Object obj, int i2);

    boolean onUserLogin(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2);

    boolean onUserLogout(int i, XLUserInfo xLUserInfo, Object obj, int i2);

    boolean onUserPing(int i, Object obj, String str, int i2);

    boolean onUserPreVerifyedCode(int i, Object obj, String str, int i2);

    boolean onUserQRCodeLogin(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2);

    boolean onUserQRCodeLoginAuth(int i, Object obj, String str, int i2);

    boolean onUserResumed(int i);

    boolean onUserSelectRecommendAvatar(int i, Object obj, String str, int i2);

    boolean onUserSessionidLogin(int i, String str, XLUserInfo xLUserInfo, Object obj, int i2);

    boolean onUserSetAvatar(int i, Object obj, String str, int i2);

    boolean onUserSetInfo(int i, Object obj, String str, int i2);

    boolean onUserSuspended(int i);

    boolean onUserThirdLogin(int i, XLUserInfo xLUserInfo, int i2, int i3, Object obj, String str, int i4);

    boolean onUserTokenLogin(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2);

    boolean onUserUnBindeOtherAccount(int i, int i2, Object obj, String str, int i3);

    boolean onUserVerifyCodeUpdated(int i, String str, int i2, byte[] bArr, Object obj, int i3);

    boolean onUserVerifyedCode(int i, Object obj, String str, int i2);
}
