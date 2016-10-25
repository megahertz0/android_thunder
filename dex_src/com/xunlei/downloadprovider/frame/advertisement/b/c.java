package com.xunlei.downloadprovider.frame.advertisement.b;

import android.content.Context;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.downloadprovider.a.b;
import com.xunlei.downloadprovider.a.j;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.frame.advertisement.ReviveAdActivity;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovider.util.r;
import com.xunlei.downloadprovider.web.DetailPageBrowserActivity;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity;
import org.json.JSONObject;

// compiled from: AdvertisementUtil.java
public class c {
    private static final String a;
    private static boolean b;

    static {
        a = c.class.getSimpleName();
        b = false;
    }

    public static String a(String str) {
        return new StringBuilder("http://market.m.sjzhushou.com/public/get?").append(new StringBuilder("page=").append(str).toString()).append(new StringBuilder("&version=").append(b.x()).toString()).append(new StringBuilder("&channel=").append(b.g()).toString()).append("&platform=android").append(new StringBuilder("&peerID=").append(b.d()).toString()).append(new StringBuilder("&productID=").append(b.h()).toString()).append(new StringBuilder("&imeiID=").append(b.f()).toString()).append(new StringBuilder("&versionName=").append(b.w()).toString()).toString();
    }

    public static String a(String str, String str2) {
        return new StringBuilder("http://market.m.sjzhushou.com/public/report?").append(new StringBuilder("action=").append(str).toString()).append(new StringBuilder("&id=").append(str2).toString()).append(new StringBuilder("&version=").append(b.x()).toString()).append(new StringBuilder("&channel=").append(b.g()).toString()).append("&platform=android").append(new StringBuilder("&peerid=").append(b.d()).toString()).append(new StringBuilder("&productID=").append(b.h()).toString()).append(new StringBuilder("&imeiID=").append(b.f()).toString()).append(new StringBuilder("&versionName=").append(b.w()).toString()).toString();
    }

    public static void a(Context context) {
        boolean z;
        long j = 15;
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        String name = context.getClass().getName();
        a a = a.a(context);
        new StringBuilder("[AD] OnForeground: ").append(currentTimeMillis).append(SocializeConstants.OP_OPEN_PAREN).append(a.a).append(") - ").append(name).append(" (").append(new j(a.b, "preference_revive_ad").b("last_context_name", a.d)).append(SocializeConstants.OP_CLOSE_PAREN);
        long b = new j(a.a(context).b, "preference_revive_ad").b("backgroud_moment", -1);
        long b2 = new j(a.a(context).b, "preference_revive_ad").b("backgroud_session", 0);
        if (name == null || !(name.contains("com.xunlei.downloadprovider.vod") || name.contains("com.xunlei.downloadprovider.task.create."))) {
            z = false;
        } else {
            ThunderReporter.a.a("player");
            z = true;
        }
        if (name.contains("com.xunlei.downloadprovider.member.payment.")) {
            ThunderReporter.a.a("vippay");
            z = true;
        }
        if (BaseActivity.checkRequiredPermissionsForLaunch(context) != 0) {
            z = true;
        }
        if (b > 0) {
            long j2;
            if (currentTimeMillis >= b) {
                j2 = currentTimeMillis - b;
            } else {
                j2 = 0;
            }
            if (a.a == 0) {
                a.a = currentTimeMillis;
            } else if (!z && a.a == b2) {
                a = a.a(context);
                r.a aVar = r.c().e;
                if (aVar.a != null) {
                    String g = b.g();
                    JSONObject optJSONObject;
                    if ("0x10800030".equals(g)) {
                        if (aVar.a != null) {
                            optJSONObject = aVar.a.optJSONObject("ad_baidu");
                            if (optJSONObject != null) {
                                j = (long) optJSONObject.optInt("revive_ad_time", 0);
                            }
                        }
                        j = 0;
                    } else if ("0x10800013".equals(g) || "0x10810054".equals(g)) {
                        if (aVar.a != null) {
                            optJSONObject = aVar.a.optJSONObject("ad_360");
                            if (optJSONObject != null) {
                                j = (long) optJSONObject.optInt("revive_ad_time", 0);
                            }
                        }
                        j = 0;
                    } else {
                        j = aVar.a.optLong("revive_ad_time", 15);
                    }
                }
                j *= 60;
                new StringBuilder("[AD] checkReviveAd - Background lifeTime: ").append(j2).append(", reviveTime: ").append(j);
                if ((j <= 0 || j2 < j) && !(b && a.b.getClass().getSimpleName().equals("DownloadCenterActivity"))) {
                    ThunderReporter.a.a("foreground");
                } else {
                    ReviveAdActivity.a(context);
                    b = false;
                }
            }
        } else {
            if (context instanceof ShortMovieDetailActivity) {
                ReviveAdActivity.a(context);
            }
            if ((context instanceof DetailPageBrowserActivity) && !BrothersApplication.a().c) {
                ReviveAdActivity.a(context);
            }
            if (context.getClass().getSimpleName().equals("DownloadCenterActivity")) {
                if (BaseActivity.checkRequiredPermissionsForLaunch(context) == 0) {
                    ReviveAdActivity.a(context);
                }
            } else if (context.getClass().getSimpleName().equals("ScanCodeResultActivity") || context.getClass().getSimpleName().equals("StorageTipActivity")) {
                b = true;
            }
            new StringBuilder("[AD] BackgroudMoment not exists ").append(context.getClass().getSimpleName());
        }
        a.a(context).a(0, a.a, a.d);
    }

    public static void b(Context context) {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        if (a.a == 0) {
            a.a = currentTimeMillis;
        }
        String name = context.getClass().getName();
        new StringBuilder("[AD] OnBackground: ").append(currentTimeMillis).append(SocializeConstants.OP_OPEN_PAREN).append(a.a).append(") - ").append(name);
        a.a(context).a(currentTimeMillis, a.a, name);
    }
}
