package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.module.d;

final class c implements Runnable {
    c() {
    }

    public final void run() {
        try {
            com.xiaomi.push.service.module.c.a(MiPushClient.access$000()).a(d.a).a().loadClass("com.xiaomi.push.mpcd.MpcdPlugin").getMethod("main", new Class[]{Context.class}).invoke(null, new Object[]{MiPushClient.access$000()});
        } catch (Exception e) {
            b.a("plugin load fail");
        }
    }
}
