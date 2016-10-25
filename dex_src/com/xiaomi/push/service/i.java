package com.xiaomi.push.service;

import com.tencent.connect.common.Constants;
import com.xiaomi.push.service.XMPushService.g;
import com.xiaomi.push.service.x.b;
import com.xiaomi.push.service.x.c;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Collection;

public class i extends g {
    private XMPushService b;
    private byte[] c;
    private String d;
    private String e;
    private String f;

    public i(XMPushService xMPushService, String str, String str2, String str3, byte[] bArr) {
        super(9);
        this.b = xMPushService;
        this.d = str;
        this.c = bArr;
        this.e = str2;
        this.f = str3;
    }

    public void a() {
        f a;
        Collection c;
        b bVar;
        f a2 = g.a(this.b);
        if (a2 == null) {
            try {
                a = g.a(this.b, this.d, this.e, this.f);
            } catch (Throwable e) {
                com.xiaomi.channel.commonutils.logger.b.a(e);
                a = a2;
            } catch (Throwable e2) {
                com.xiaomi.channel.commonutils.logger.b.a(e2);
            }
            if (a != null) {
                com.xiaomi.channel.commonutils.logger.b.d("no account for mipush");
                j.a(this.b, 70000002, "no account.");
            }
            c = x.a().c(Constants.VIA_SHARE_TYPE_TEXT);
            if (c.isEmpty()) {
                bVar = (b) c.iterator().next();
            } else {
                bVar = a.a(this.b);
                this.b.a(bVar);
                x.a().a(bVar);
            }
            if (this.b.e()) {
                this.b.a(true);
                return;
            }
            try {
                if (bVar.m == c.c) {
                    this.b.a(this.d, this.c);
                    return;
                } else if (bVar.m == c.a) {
                    XMPushService xMPushService = this.b;
                    XMPushService xMPushService2 = this.b;
                    xMPushService2.getClass();
                    xMPushService.a(new a(xMPushService2, bVar));
                    return;
                } else {
                    return;
                }
            } catch (Exception e3) {
                com.xiaomi.channel.commonutils.logger.b.a((Throwable) e3);
                this.b.a((int) XZBDevice.Stop, e3);
            }
        }
        a = a2;
        if (a != null) {
            c = x.a().c(Constants.VIA_SHARE_TYPE_TEXT);
            if (c.isEmpty()) {
                bVar = (b) c.iterator().next();
            } else {
                bVar = a.a(this.b);
                this.b.a(bVar);
                x.a().a(bVar);
            }
            if (this.b.e()) {
                this.b.a(true);
                return;
            } else if (bVar.m == c.c) {
                this.b.a(this.d, this.c);
                return;
            } else if (bVar.m == c.a) {
                XMPushService xMPushService3 = this.b;
                XMPushService xMPushService22 = this.b;
                xMPushService22.getClass();
                xMPushService3.a(new a(xMPushService22, bVar));
                return;
            } else {
                return;
            }
        }
        com.xiaomi.channel.commonutils.logger.b.d("no account for mipush");
        j.a(this.b, 70000002, "no account.");
    }

    public String b() {
        return "register app";
    }
}
