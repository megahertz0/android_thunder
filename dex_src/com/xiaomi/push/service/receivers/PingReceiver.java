package com.xiaomi.push.service.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.timers.a;
import com.xiaomi.push.service.z;

public class PingReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        a.a(false);
        b.c(intent.getPackage() + " is the package name");
        if (!z.o.equals(intent.getAction())) {
            b.a("cancel the old ping timer");
            a.a();
        } else if (TextUtils.equals(context.getPackageName(), intent.getPackage())) {
            b.c("Ping XMChannelService on timer");
            try {
                Intent intent2 = new Intent(context, XMPushService.class);
                intent2.putExtra("time_stamp", System.currentTimeMillis());
                intent2.setAction("com.xiaomi.push.timer");
                context.startService(intent2);
            } catch (Throwable e) {
                b.a(e);
            }
        }
    }
}
