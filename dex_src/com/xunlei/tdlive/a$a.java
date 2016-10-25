package com.xunlei.tdlive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;

// compiled from: CollectService.java
public abstract class a$a extends BroadcastReceiver {
    public void a(float f, float f2, float f3, float f4) {
    }

    public void a(String str) {
    }

    public void onReceive(Context context, Intent intent) {
        if ("com.xunlei.tdlive.sdk.CollectService.CALLBACK_MONITOR_PSI".equals(intent.getAction())) {
            a(intent.getStringExtra(SocializeProtocolConstants.PROTOCOL_KEY_MSG));
        } else if ("com.xunlei.tdlive.sdk.CollectService.CALLBACK_MONITOR_SPD".equals(intent.getAction())) {
            a(intent.getFloatExtra("tdp", 0.0f), intent.getFloatExtra("tup", 0.0f), intent.getFloatExtra("udp", 0.0f), intent.getFloatExtra("uup", 0.0f));
        }
    }
}
