package com.xunlei.downloadprovider.member.login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.xunlei.common.member.XLUserUtil;

// compiled from: LoginHelper.java
final class i extends BroadcastReceiver {
    final /* synthetic */ LoginHelper a;

    i(LoginHelper loginHelper) {
        this.a = loginHelper;
    }

    public final void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.intent.action.SCREEN_ON".equals(action)) {
            if (LoginHelper.c()) {
                XLUserUtil.getInstance().setKeepAlive(true, 300000);
            }
        } else if ("android.intent.action.SCREEN_OFF".equals(action) && LoginHelper.c()) {
            XLUserUtil.getInstance().setKeepAlive(false, 300000);
        }
    }
}
