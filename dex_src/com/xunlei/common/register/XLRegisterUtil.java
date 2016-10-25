package com.xunlei.common.register;

import android.app.Activity;
import android.content.Context;
import com.umeng.a;
import com.xunlei.common.register.a.c;

public class XLRegisterUtil {
    private static XLRegisterUtil mInstance;

    static {
        mInstance = null;
    }

    private XLRegisterUtil() {
    }

    private c getProxy() {
        return c.a();
    }

    public int getBusinessType() {
        return getProxy().d();
    }

    public String getAppPackageName() {
        return getProxy().h();
    }

    public Context getContext() {
        return getProxy().c();
    }

    public String getVersion() {
        return getProxy().e();
    }

    public String getFrom() {
        return getProxy().g();
    }

    public static XLRegisterUtil getInstance() {
        if (mInstance != null) {
            return mInstance;
        }
        synchronized (XLRegisterUtil.class) {
            if (mInstance == null) {
                mInstance = new XLRegisterUtil();
            }
        }
        return mInstance;
    }

    public void attachListener(XLRegisterListener xLRegisterListener) {
        getProxy().a(xLRegisterListener);
    }

    public void dettachListener(XLRegisterListener xLRegisterListener) {
        getProxy().b(xLRegisterListener);
    }

    public boolean init(Context context, int i, String str, String str2, String str3, String str4) {
        return getProxy().a(context, i, str, str2, str3, str4);
    }

    public boolean uninit() {
        return getProxy().b();
    }

    public int checkBind(String str, int i) {
        return getProxy().a(str, i);
    }

    public int checkIsNeedVerifyCode() {
        return getProxy().i();
    }

    public int checkPassWordStrength(String str) {
        return getProxy().b(str);
    }

    public int getVerifyCodeByType(String str) {
        return getProxy().a(str);
    }

    public int emailRegister(String str, String str2, String str3, String str4, String str5, String str6) {
        return getProxy().a(str, str2, str3, str4, str5, str6, a.d);
    }

    public int userNameRegister(String str, String str2, String str3, String str4, String str5, String str6) {
        return getProxy().b(str, str2, str3, str4, str5, str6, a.d);
    }

    public int phoneFastLogin(String str, String str2, String str3) {
        return getProxy().a(str, str2, str3);
    }

    public int phoneFastRegister(String str, String str2, String str3, String str4) {
        return getProxy().a(str, str2, str3, str4);
    }

    public int sendPhoneMessage(String str, int i, Activity activity, String str2, String str3, String str4) {
        return getProxy().a(str, i, activity, str2, str3, str4, true);
    }
}
