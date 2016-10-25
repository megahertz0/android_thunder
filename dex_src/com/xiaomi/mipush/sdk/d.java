package com.xiaomi.mipush.sdk;

import com.xiaomi.xmpush.thrift.a;
import com.xiaomi.xmpush.thrift.r;
import java.util.HashMap;

final class d implements Runnable {
    d() {
    }

    public final void run() {
        if (com.xiaomi.push.service.d.c(MiPushClient.access$000()) != null) {
            r rVar = new r();
            rVar.b(a.a(MiPushClient.access$000()).c());
            rVar.c("client_info_update");
            rVar.a(MiPushClient.generatePacketID());
            rVar.a(new HashMap());
            rVar.h().put("imei_md5", com.xiaomi.channel.commonutils.string.d.a(com.xiaomi.push.service.d.c(MiPushClient.access$000())));
            j.a(MiPushClient.access$000()).a(rVar, a.i, false, null);
        }
    }
}
