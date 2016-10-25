package com.xunlei.downloadprovider.homepage.a.a;

import android.text.TextUtils;
import com.umeng.a;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.member.login.LoginHelper;

// compiled from: VipContinueDateUtil.java
public final class c {
    public static String a() {
        if (LoginHelper.a().f()) {
            return LoginHelper.a().n();
        }
        String n = LoginHelper.a().n();
        if (!TextUtils.isEmpty(n) && n.length() == 8) {
            return n;
        }
        n = BrothersApplication.a().getSharedPreferences("vip_continue", 0).getString(LoginHelper.a().j + "vip_expire_date", null);
        return n == null ? a.d : n;
    }
}
