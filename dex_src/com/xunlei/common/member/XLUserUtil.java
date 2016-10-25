package com.xunlei.common.member;

import android.content.Context;
import android.graphics.Bitmap;
import com.xunlei.common.httpclient.BaseHttpClient;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.member.a.d;
import com.xunlei.common.member.a.d.a;
import com.xunlei.common.member.a.m;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.List;
import org.json.JSONObject;

public class XLUserUtil {
    private static final XLUserUtil mInstance;

    static {
        mInstance = new XLUserUtil();
    }

    private XLUserUtil() {
    }

    private m getProxy() {
        return m.a();
    }

    public static XLUserUtil getInstance() {
        return mInstance;
    }

    public Context getContext() {
        return getProxy().h();
    }

    public String getAppPackageName() {
        return getProxy().m();
    }

    public int getBusinessType() {
        return getProxy().d();
    }

    public String getVersion() {
        return getProxy().c();
    }

    public int getVersionCode() {
        return getProxy().f();
    }

    public void setHttpClient(BaseHttpClient baseHttpClient) {
        getProxy().a(baseHttpClient);
    }

    public BaseHttpClient getHttpClient() {
        return getProxy().k();
    }

    public XLUserInfo getCurrentUser() {
        return getProxy().i();
    }

    public synchronized void attachListener(XLOnUserListener xLOnUserListener) {
        getProxy().a(xLOnUserListener);
    }

    public synchronized void detachListener(XLOnUserListener xLOnUserListener) {
        getProxy().b(xLOnUserListener);
    }

    public boolean Init(Context context, int i, String str, String str2, String str3) {
        return getProxy().a(context, i, str, str2, str3);
    }

    public boolean Uninit() {
        return getProxy().b();
    }

    public void setKeepAlive(boolean z, int i) {
        getProxy().a(z, i);
    }

    public void setKeepAliveListener(XLOnUserListener xLOnUserListener) {
        getProxy().c(xLOnUserListener);
    }

    public void clearAutoLoginPassword(boolean z) {
        d.a(getProxy().h(), a.c);
        if (z) {
            m.a().i().clearUserData();
        }
    }

    public void acceptWxCode(int i, String str, int i2) {
        getProxy();
        m.a(i, str, i2);
    }

    public int userAccountLogin(String str, String str2, String str3, String str4, XLOnUserListener xLOnUserListener, Object obj) {
        return getProxy().a(str, str2, str3, str4, xLOnUserListener, obj);
    }

    public int userAutoLogin(String str, String str2, XLOnUserListener xLOnUserListener, Object obj) {
        return getProxy().a(str, str2, xLOnUserListener, obj);
    }

    public int userLoginWithSessionid(int i, String str, int i2, int i3, XLOnUserListener xLOnUserListener, Object obj) {
        return getProxy().a(i, str, i2, i3, xLOnUserListener, obj, false);
    }

    public int userLoginWithToken(String str, String str2, String str3, String str4, XLOnUserListener xLOnUserListener, Object obj) {
        return getProxy().a(str, str2, str3, str4, xLOnUserListener, obj, false);
    }

    public int userThirdLogin(int i, Object obj, XLOnUserListener xLOnUserListener, Object obj2) {
        return getProxy().a(i, obj, xLOnUserListener, obj2);
    }

    public boolean userCancelLogin(int i) {
        getProxy();
        return m.o();
    }

    public int userLogout(XLOnUserListener xLOnUserListener, Object obj) {
        return getProxy().d(xLOnUserListener, obj);
    }

    public int userGetInfo(List<USERINFOKEY> list, XLOnUserListener xLOnUserListener, Object obj) {
        return getProxy().a((List) list, xLOnUserListener, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE, obj);
    }

    public int userGetInfo(List<USERINFOKEY> list, XLOnUserListener xLOnUserListener, int i, Object obj) {
        return getProxy().a((List) list, xLOnUserListener, i, obj);
    }

    public int userSetInfo(JSONObject jSONObject, String str, String str2, XLOnUserListener xLOnUserListener, Object obj) {
        return getProxy().a(jSONObject, str, str2, xLOnUserListener, obj);
    }

    public int userGetCityInfo(XLOnUserListener xLOnUserListener, Object obj) {
        return getProxy().a(xLOnUserListener, obj);
    }

    public int userGetHighSpeedCapacity(XLOnUserListener xLOnUserListener, Object obj) {
        return getProxy().b(xLOnUserListener, obj);
    }

    public int userGetLixianCapacity(XLOnUserListener xLOnUserListener, Object obj) {
        return getProxy().c(xLOnUserListener, obj);
    }

    public int userGetVerifyCode(XLOnUserListener xLOnUserListener, Object obj) {
        return getProxy().e(xLOnUserListener, obj);
    }

    public int userPreVerifyCode(String str, String str2, XLOnUserListener xLOnUserListener, Object obj) {
        return getProxy().b(str, str2, xLOnUserListener, obj);
    }

    public int userVerifyCode(String str, String str2, XLOnUserListener xLOnUserListener, Object obj) {
        return getProxy().c(str, str2, xLOnUserListener, obj);
    }

    public int userGetRecommendAvatars(XLOnUserListener xLOnUserListener, Object obj) {
        return getProxy().f(xLOnUserListener, obj);
    }

    public int userSelectRecommendAvatar(int i, XLOnUserListener xLOnUserListener, Object obj) {
        return getProxy().a(i, xLOnUserListener, obj);
    }

    public int userSetAvatar(Bitmap bitmap, XLOnUserListener xLOnUserListener, Object obj) {
        return getProxy().a(bitmap, xLOnUserListener, obj);
    }

    public boolean userIsLogined() {
        return getProxy().p();
    }

    public boolean userIsOnline() {
        return getProxy().q();
    }

    public boolean userIsQRCodeLoginAuthReq(String str) {
        getProxy();
        return m.a(str);
    }

    public int userQRCodeLoginAuth(String str, XLQRCodeAuthHandler xLQRCodeAuthHandler, XLOnUserListener xLOnUserListener, Object obj) {
        return getProxy().a(str, xLQRCodeAuthHandler, xLOnUserListener, obj);
    }

    public int userGetQRCode(int i, XLOnUserListener xLOnUserListener, Object obj) {
        return getProxy().b(i, xLOnUserListener, obj);
    }

    public int userQRCodeLogin(String str, int i, XLOnUserListener xLOnUserListener, Object obj) {
        return getProxy().a(str, i, xLOnUserListener, obj);
    }

    public int userGetBindedOtherAccount(XLOnUserListener xLOnUserListener, Object obj) {
        return getProxy().g(xLOnUserListener, obj);
    }

    public int userBindOtherAccount(int i, String str, String str2, XLOnUserListener xLOnUserListener, Object obj) {
        return getProxy().a(i, str, str2, xLOnUserListener, obj);
    }

    public int userGetOtherAccountInfo(int i, String str, XLOnUserListener xLOnUserListener, Object obj) {
        return getProxy().a(i, str, xLOnUserListener, obj);
    }

    public int userUnBindOtherAccount(int i, XLOnUserListener xLOnUserListener, Object obj) {
        return getProxy().c(i, xLOnUserListener, obj);
    }

    public int userAqSendMessage(String str, XLOnUserListener xLOnUserListener, Object obj) {
        return getProxy().a(str, xLOnUserListener, obj);
    }

    public int userAqBindMobile(String str, String str2, XLOnUserListener xLOnUserListener, Object obj) {
        return getProxy().d(str, str2, xLOnUserListener, obj);
    }
}
