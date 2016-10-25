package com.xunlei.downloadprovider.pushmessage.xiaomi;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.pushmessage.a.a;
import com.xunlei.downloadprovider.pushmessage.c.b;
import com.xunlei.downloadprovider.pushmessage.d;
import com.xunlei.downloadprovider.pushmessage.d.g;
import com.xunlei.downloadprovider.pushmessage.f;
import com.xunlei.downloadprovider.pushmessage.h;
import com.xunlei.tdlive.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class XiaoMiPushReceiver extends PushMessageReceiver {
    private static final String TAG = "XiaoMiPushReceiver";
    private String mRegId;

    public XiaoMiPushReceiver() {
        this.mRegId = null;
    }

    public void onCommandResult(Context context, MiPushCommandMessage miPushCommandMessage) {
        new StringBuilder("Receiver commandResult is:").append(miPushCommandMessage.toString());
        String command = miPushCommandMessage.getCommand();
        List commandArguments = miPushCommandMessage.getCommandArguments();
        String str = (commandArguments == null || commandArguments.size() <= 0) ? null : (String) commandArguments.get(0);
        if (commandArguments != null && commandArguments.size() > 1) {
            commandArguments.get(1);
        }
        if (MiPushClient.COMMAND_REGISTER.equals(command) && miPushCommandMessage.getResultCode() == 0) {
            this.mRegId = str;
        }
    }

    public void onReceiveMessage(Context context, MiPushMessage miPushMessage) {
        int passThrough = miPushMessage.getPassThrough();
        new StringBuilder("Receive Message is:").append(miPushMessage.getContent()).append(",passThrough=").append(passThrough);
        if (passThrough == 0) {
            if (miPushMessage.getContent() != null) {
                a a = h.a(miPushMessage.getContent());
                if (a != null) {
                    g.a(a.r, a.l);
                    a.u = 1;
                    Intent a2 = f.a(context, a);
                    if (a2 != null) {
                        context.startActivity(a2);
                    }
                }
            }
        } else if (passThrough == 1) {
            String content = miPushMessage.getContent();
            a a3 = h.a(content);
            if (a3 != null) {
                a3.u = 1;
                StatReporter.reportPushResRecv(a3.b);
                d.a(a3);
                if (!com.xunlei.downloadprovider.pushmessage.c.a.a().a(a3.l)) {
                    if (!a3.l.equals(com.umeng.a.d)) {
                        com.xunlei.downloadprovider.pushmessage.c.a.a().a(a3.l, content);
                    }
                    CharSequence charSequence = a3.m;
                    if (TextUtils.isEmpty(charSequence)) {
                        charSequence = a3.n;
                    }
                    if (TextUtils.isEmpty(charSequence)) {
                        com.xunlei.downloadprovider.pushmessage.g.a(context, a3, null);
                    } else {
                        d.a(context, a3);
                    }
                }
            }
        }
    }

    public void onReceiveRegisterResult(Context context, MiPushCommandMessage miPushCommandMessage) {
        String command = miPushCommandMessage.getCommand();
        List commandArguments = miPushCommandMessage.getCommandArguments();
        String str = (commandArguments == null || commandArguments.size() <= 0) ? null : (String) commandArguments.get(0);
        if (commandArguments != null && commandArguments.size() > 1) {
            commandArguments.get(1);
        }
        if (MiPushClient.COMMAND_REGISTER.equals(command) && miPushCommandMessage.getResultCode() == 0) {
            this.mRegId = str;
            new StringBuilder("onReceiveRegisterResult RegId is:").append(this.mRegId);
            str = b.a().a("xiao_mi_register_id");
            if (str == null || !str.equals(this.mRegId)) {
                new StringBuilder("onReceiveRegisterResult saveRegisterId:").append(this.mRegId);
                b.a().a("xiao_mi_register_id", this.mRegId);
                String str2 = com.umeng.a.d;
                List arrayList = new ArrayList();
                Object regId = MiPushClient.getRegId(BrothersApplication.a);
                if (!TextUtils.isEmpty(regId)) {
                    command = com.xunlei.downloadprovider.a.b.c();
                    String valueOf = String.valueOf(LoginHelper.a().j);
                    String str3 = Build.MODEL + VERSION.SDK_INT;
                    arrayList.add("anonymous");
                    arrayList.add(String.valueOf(new Random().nextInt(R.styleable.AppCompatTheme_buttonStyle) + 1));
                    com.xunlei.downloadprovider.pushmessage.d.a.a(BrothersApplication.a).a(command, valueOf, regId, str2, str3, arrayList, "mipush");
                }
            }
        }
    }
}
