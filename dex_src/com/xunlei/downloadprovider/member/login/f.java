package com.xunlei.downloadprovider.member.login;

import com.xunlei.common.lixian.XLLX_USERINFO;
import com.xunlei.common.lixian.XLLixianListener;

// compiled from: LoginHelper.java
final class f extends XLLixianListener {
    final /* synthetic */ LoginHelper a;

    f(LoginHelper loginHelper) {
        this.a = loginHelper;
    }

    public final boolean OnObtainLixianUserInfo(int i, String str, int i2, XLLX_USERINFO xllx_userinfo, Object obj) {
        b bVar = null;
        if (xllx_userinfo != null) {
            bVar = new b();
            bVar.a = i;
            bVar.b = xllx_userinfo.Max_task_num;
            bVar.c = xllx_userinfo.Available_sapce;
            bVar.e = xllx_userinfo.Max_store;
            bVar.d = xllx_userinfo.Max_store - xllx_userinfo.Available_sapce;
        }
        this.a.a(i, bVar);
        return super.OnObtainLixianUserInfo(i, str, i2, xllx_userinfo, obj);
    }
}
