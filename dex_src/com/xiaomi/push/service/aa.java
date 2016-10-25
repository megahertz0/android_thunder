package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.network.Fallback;
import com.xiaomi.network.HostFilter;
import com.xiaomi.network.HostManager;
import com.xiaomi.network.HostManager.HostManagerFactory;
import com.xiaomi.network.HostManager.HttpGet;
import com.xiaomi.network.HostManagerV2;
import com.xiaomi.push.service.ag.a;
import java.util.ArrayList;
import java.util.Iterator;
import org.android.agoo.message.MessageService;

public class aa extends a implements HostManagerFactory {
    private XMPushService a;
    private long b;

    aa(XMPushService xMPushService) {
        this.a = xMPushService;
    }

    public static void a(XMPushService xMPushService) {
        a aaVar = new aa(xMPushService);
        ag.a().a(aaVar);
        com.xiaomi.push.protobuf.a.a d = ag.a().d();
        boolean z = true;
        if (d != null && d.f()) {
            z = d.f();
        }
        if (z) {
            HostManager.setHostManagerFactory(aaVar);
        }
        HostManager.init(xMPushService, null, new a(), MessageService.MSG_DB_READY_REPORT, "push", "2.2");
    }

    public HostManager a(Context context, HostFilter hostFilter, HttpGet httpGet, String str) {
        return new b(context, hostFilter, httpGet, str);
    }

    public void a(com.xiaomi.push.protobuf.a.a aVar) {
        if (aVar.f()) {
            b.a(new StringBuilder("Switch to BucketV2 :").append(aVar.e()).toString());
            HostManager instance = HostManager.getInstance();
            synchronized (HostManager.class) {
                if (aVar.e()) {
                    if (!(instance instanceof HostManagerV2)) {
                        HostManager.setHostManagerFactory(this);
                        HostManager.init(this.a, null, new a(), MessageService.MSG_DB_READY_REPORT, "push", "2.2");
                    }
                } else if (HostManager.getInstance() instanceof HostManagerV2) {
                    HostManager.setHostManagerFactory(null);
                    HostManager.init(this.a, null, new a(), MessageService.MSG_DB_READY_REPORT, "push", "2.2");
                }
            }
        }
    }

    public void a(com.xiaomi.push.protobuf.b.a aVar) {
        if (aVar.d() && System.currentTimeMillis() - this.b > 3600000) {
            b.a(new StringBuilder("fetch bucket :").append(aVar.c()).toString());
            this.b = System.currentTimeMillis();
            HostManager instance = HostManager.getInstance();
            instance.clear();
            instance.refreshFallbacks();
            com.xiaomi.smack.a g = this.a.g();
            if (g != null) {
                Fallback fallbacksByHost = instance.getFallbacksByHost(g.a().f());
                if (fallbacksByHost != null) {
                    boolean z;
                    ArrayList d = fallbacksByHost.d();
                    Iterator it = d.iterator();
                    while (it.hasNext()) {
                        if (((String) it.next()).equals(g.c())) {
                            z = false;
                            break;
                        }
                    }
                    int i = 1;
                    if (z && !d.isEmpty()) {
                        b.a("bucket changed, force reconnect");
                        this.a.a(0, null);
                        this.a.a(false);
                    }
                }
            }
        }
    }
}
