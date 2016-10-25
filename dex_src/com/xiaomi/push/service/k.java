package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.smack.packet.d;
import com.xiaomi.xmpush.thrift.a;
import com.xiaomi.xmpush.thrift.ad;
import com.xiaomi.xmpush.thrift.i;
import com.xiaomi.xmpush.thrift.o;
import com.xunlei.analytics.b.c;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.List;
import java.util.Map;
import org.android.spdy.SpdyProtocol;
import org.apache.thrift.f;

public class k {
    public static o a(Context context, o oVar) {
        com.xiaomi.xmpush.thrift.k kVar = new com.xiaomi.xmpush.thrift.k();
        kVar.b(oVar.h());
        i m = oVar.m();
        if (m != null) {
            kVar.a(m.b());
            kVar.a(m.d());
            if (!TextUtils.isEmpty(m.f())) {
                kVar.c(m.f());
            }
        }
        kVar.a(ad.a(context, oVar.f));
        o a = XMPushService.a(oVar.j(), oVar.h(), kVar, a.f);
        m = oVar.m().a();
        m.a("mat", Long.toString(System.currentTimeMillis()));
        a.a(m);
        return a;
    }

    private static void a(XMPushService xMPushService, o oVar) {
        xMPushService.a(new m(4, xMPushService, oVar));
    }

    private static void a(XMPushService xMPushService, o oVar, String str) {
        xMPushService.a(new p(4, xMPushService, oVar, str));
    }

    private static void a(XMPushService xMPushService, o oVar, String str, String str2) {
        xMPushService.a(new q(4, xMPushService, oVar, str, str2));
    }

    private static void a(XMPushService xMPushService, byte[] bArr, long j) {
        Long valueOf = Long.valueOf(System.currentTimeMillis());
        o oVar = new o();
        try {
            ad.a(oVar, bArr);
            if (TextUtils.isEmpty(oVar.f)) {
                b.a("receive a mipush message without package name");
                return;
            }
            Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
            intent.putExtra("mipush_payload", bArr);
            intent.putExtra("mrt", Long.toString(valueOf.longValue()));
            intent.setPackage(oVar.f);
            String a = r.a(oVar);
            com.xiaomi.smack.util.k.a(xMPushService, a, j, true, System.currentTimeMillis());
            i m = oVar.m();
            if (m != null) {
                m.a("mrt", Long.toString(valueOf.longValue()));
            }
            String str;
            if (a.e == oVar.a() && h.a(xMPushService).a(oVar.f) && !r.b(oVar)) {
                str = BuildConfig.VERSION_NAME;
                if (m != null) {
                    str = m.b();
                }
                b.a(new StringBuilder("Drop a message for unregistered, msgid=").append(str).toString());
                a(xMPushService, oVar, oVar.f);
            } else if (a.e != oVar.a() || TextUtils.equals(xMPushService.getPackageName(), "com.xiaomi.xmsf") || TextUtils.equals(xMPushService.getPackageName(), oVar.f)) {
                if (m != null) {
                    if (m.b() != null) {
                        b.a(String.format("receive a message, appid=%1$s, msgid= %2$s", new Object[]{oVar.h(), m.b()}));
                    }
                }
                if (m != null) {
                    Map s = m.s();
                    if (s != null && s.containsKey("hide") && "true".equalsIgnoreCase((String) s.get("hide"))) {
                        a(xMPushService, oVar);
                        return;
                    }
                }
                if (c(oVar) && a((Context) xMPushService, a)) {
                    b(xMPushService, oVar);
                } else if (a(oVar) && !a((Context) xMPushService, a) && !b(oVar)) {
                    c(xMPushService, oVar);
                } else if ((r.b(oVar) && b((Context) xMPushService, oVar.f)) || a((Context) xMPushService, intent)) {
                    if (a.a == oVar.a()) {
                        str = oVar.j();
                        Editor edit = xMPushService.getSharedPreferences("pref_registered_pkg_names", 0).edit();
                        edit.putString(str, oVar.e);
                        edit.commit();
                    }
                    if (m != null && !TextUtils.isEmpty(m.h()) && !TextUtils.isEmpty(m.j()) && m.h != 1 && (r.a(m.s()) || !r.a(xMPushService, oVar.f))) {
                        boolean a2;
                        str = null;
                        if (m != null) {
                            if (m.j != null) {
                                str = (String) m.j.get("jobkey");
                            }
                            if (TextUtils.isEmpty(str)) {
                                str = m.b();
                            }
                            a2 = s.a(xMPushService, oVar.f, str);
                        } else {
                            a2 = false;
                        }
                        if (a2) {
                            b.a(new StringBuilder("drop a duplicate message, key=").append(str).toString());
                        } else {
                            r.a(xMPushService, oVar, bArr);
                            if (!r.b(oVar)) {
                                Intent intent2 = new Intent("com.xiaomi.mipush.MESSAGE_ARRIVED");
                                intent2.putExtra("mipush_payload", bArr);
                                intent2.setPackage(oVar.f);
                                try {
                                    List queryBroadcastReceivers = xMPushService.getPackageManager().queryBroadcastReceivers(intent2, 0);
                                    if (!(queryBroadcastReceivers == null || queryBroadcastReceivers.isEmpty())) {
                                        xMPushService.sendBroadcast(intent2, b.a(oVar.f));
                                    }
                                } catch (Exception e) {
                                    xMPushService.sendBroadcast(intent2, b.a(oVar.f));
                                }
                            }
                        }
                        a(xMPushService, oVar);
                    } else if (!"com.xiaomi.xmsf".contains(oVar.f) || oVar.c() || m == null || !m.s().containsKey("ab")) {
                        xMPushService.sendBroadcast(intent, b.a(oVar.f));
                    } else {
                        a(xMPushService, oVar);
                        b.c(new StringBuilder("receive abtest message. ack it.").append(m.b()).toString());
                    }
                    if (oVar.a() == a.b && !"com.xiaomi.xmsf".equals(xMPushService.getPackageName())) {
                        xMPushService.stopSelf();
                    }
                } else if (b((Context) xMPushService, oVar.f)) {
                    b.a("receive a mipush message, we can see the app, but we can't see the receiver.");
                } else {
                    xMPushService.a(new l(4, xMPushService, oVar));
                }
            } else {
                b.a(new StringBuilder("Receive a message with wrong package name, expect ").append(xMPushService.getPackageName()).append(", received ").append(oVar.f).toString());
                a(xMPushService, oVar, "unmatched_package", new StringBuilder("package should be ").append(xMPushService.getPackageName()).append(", but got ").append(oVar.f).toString());
            }
        } catch (f e2) {
            b.a(e2);
        }
    }

    private static boolean a(Context context, Intent intent) {
        try {
            List queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, org.android.agoo.common.a.ORDERED);
            return (queryBroadcastReceivers == null || queryBroadcastReceivers.isEmpty()) ? false : true;
        } catch (Exception e) {
            return true;
        }
    }

    private static boolean a(Context context, String str) {
        Intent intent = new Intent("com.xiaomi.mipush.miui.CLICK_MESSAGE");
        intent.setPackage(str);
        Intent intent2 = new Intent("com.xiaomi.mipush.miui.RECEIVE_MESSAGE");
        intent2.setPackage(str);
        PackageManager packageManager = context.getPackageManager();
        try {
            return (packageManager.queryBroadcastReceivers(intent2, org.android.agoo.common.a.ORDERED).isEmpty() && packageManager.queryIntentServices(intent, org.android.agoo.common.a.ORDERED).isEmpty()) ? false : true;
        } catch (Throwable e) {
            b.a(e);
            return false;
        }
    }

    private static boolean a(o oVar) {
        return "com.xiaomi.xmsf".equals(oVar.f) && oVar.m() != null && oVar.m().s() != null && oVar.m().s().containsKey("miui_package_name");
    }

    private static void b(XMPushService xMPushService, o oVar) {
        xMPushService.a(new n(4, xMPushService, oVar));
    }

    private static boolean b(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, SpdyProtocol.SLIGHTSSL_1_RTT_MODE);
        } catch (NameNotFoundException e) {
            packageInfo = null;
        }
        return packageInfo != null;
    }

    private static boolean b(o oVar) {
        return oVar.m().s().containsKey("notify_effect");
    }

    private static void c(XMPushService xMPushService, o oVar) {
        xMPushService.a(new o(4, xMPushService, oVar));
    }

    private static boolean c(o oVar) {
        return (oVar.m() == null || oVar.m().s() == null) ? false : c.f.equals(oVar.m().s().get("obslete_ads_message"));
    }

    public void a(Context context, x.b bVar, boolean z, int i, String str) {
        if (!z) {
            f a = g.a(context);
            if (a != null && "token-expired".equals(str)) {
                try {
                    g.a(context, a.d, a.e, a.f);
                } catch (Throwable e) {
                    b.a(e);
                } catch (Throwable e2) {
                    b.a(e2);
                }
            }
        }
    }

    public void a(XMPushService xMPushService, d dVar, x.b bVar) {
        if (dVar instanceof com.xiaomi.smack.packet.c) {
            com.xiaomi.smack.packet.c cVar = (com.xiaomi.smack.packet.c) dVar;
            com.xiaomi.smack.packet.a p = cVar.p("s");
            if (p != null) {
                try {
                    a(xMPushService, ad.b(ad.a(bVar.i, cVar.k()), p.c()), (long) com.xiaomi.smack.util.k.a(dVar.a()));
                    return;
                } catch (Throwable e) {
                    b.a(e);
                }
            }
            return;
        }
        b.a("not a mipush message");
    }
}
