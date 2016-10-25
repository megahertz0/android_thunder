package com.xunlei.downloadprovider.pushmessage;

import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.umeng.a;
import com.umeng.message.IUmengRegisterCallback;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.tdlive.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// compiled from: PushManager.java
public final class b implements IUmengRegisterCallback {
    public final void onFailure(String str, String str2) {
    }

    public final void onSuccess(String str) {
        if (!TextUtils.isEmpty(str)) {
            String a = com.xunlei.downloadprovider.pushmessage.c.b.a().a("umeng_register_id");
            if (a == null || !a.equals(str)) {
                com.xunlei.downloadprovider.pushmessage.c.b.a().a("umeng_register_id", str);
                String str2 = a.d;
                List arrayList = new ArrayList();
                if (!TextUtils.isEmpty(str)) {
                    String c = com.xunlei.downloadprovider.a.b.c();
                    String valueOf = String.valueOf(LoginHelper.a().j);
                    String str3 = Build.MODEL + VERSION.SDK_INT;
                    arrayList.add("anonymous");
                    arrayList.add(String.valueOf(new Random().nextInt(R.styleable.AppCompatTheme_buttonStyle) + 1));
                    com.xunlei.downloadprovider.pushmessage.d.a.a(BrothersApplication.a).a(c, valueOf, str, str2, str3, arrayList, "upush");
                }
            }
        }
    }
}
