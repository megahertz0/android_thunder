package com.xunlei.downloadprovider.thirdpart.thirdpartycallplay;

import android.content.Intent;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.xunlei.downloadprovider.app.BrothersApplication;

// compiled from: ThirdPartPlayUtil.java
public final class a {
    public static void a(String str) {
        try {
            Intent intent = new Intent();
            intent.setPackage(str);
            intent.setAction("com.UCMobile.intent.action.INVOKE");
            intent.putExtra(IXAdRequestInfo.PHONE_TYPE, "UCM_OPENURL");
            intent.putExtra("openurl", com.umeng.a.d);
            intent.addFlags(268435456);
            BrothersApplication.a().startActivity(intent);
        } catch (Exception e) {
        }
    }
}
