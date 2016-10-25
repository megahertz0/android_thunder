package com.xunlei.downloadprovider.pushmessage;

import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.umeng.message.PushAgent;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.downloadprovider.a.b;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.util.ai;
import com.xunlei.tdlive.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.android.agoo.message.MessageService;

// compiled from: PushManager.java
public final class a {
    public static void a(boolean z) {
        List arrayList;
        Object registrationId;
        String c;
        String str;
        String str2;
        if (!ai.a()) {
            String str3 = com.umeng.a.d;
            arrayList = new ArrayList();
            registrationId = PushAgent.getInstance(BrothersApplication.a).getRegistrationId();
            if (!TextUtils.isEmpty(registrationId)) {
                c = b.c();
                str = MessageService.MSG_DB_READY_REPORT;
                if (z) {
                    str = String.valueOf(LoginHelper.a().j);
                }
                str2 = Build.MODEL + VERSION.SDK_INT;
                arrayList.add("anonymous");
                arrayList.add(String.valueOf(new Random().nextInt(R.styleable.AppCompatTheme_buttonStyle) + 1));
                com.xunlei.downloadprovider.pushmessage.d.a.a(BrothersApplication.a).b(c, str, registrationId, str3, str2, arrayList, "upush");
            }
        } else if (ai.a()) {
            registrationId = MiPushClient.getRegId(BrothersApplication.a);
            if (!TextUtils.isEmpty(registrationId)) {
                c = b.c();
                str = MessageService.MSG_DB_READY_REPORT;
                if (z) {
                    str = String.valueOf(LoginHelper.a().j);
                }
                str2 = Build.MODEL + VERSION.SDK_INT;
                arrayList = new ArrayList();
                arrayList.add("default");
                arrayList.add(String.valueOf(new Random().nextInt(R.styleable.AppCompatTheme_buttonStyle) + 1));
                com.xunlei.downloadprovider.pushmessage.d.a.a(BrothersApplication.a).b(c, str, registrationId, com.umeng.a.d, str2, arrayList, "mipush");
            }
        }
    }
}
