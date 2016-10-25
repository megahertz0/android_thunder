package com.xiaomi.push.service.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.ac;

public class PkgUninstallReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent != null && "android.intent.action.PACKAGE_REMOVED".equals(intent.getAction())) {
            Uri data = intent.getData();
            if (data != null) {
                try {
                    Intent intent2 = new Intent(context, XMPushService.class);
                    intent2.setAction(ac.a);
                    intent2.putExtra("uninstall_pkg_name", data.getEncodedSchemeSpecificPart());
                    context.startService(intent2);
                } catch (Throwable e) {
                    b.a(e);
                }
            }
        }
    }
}
