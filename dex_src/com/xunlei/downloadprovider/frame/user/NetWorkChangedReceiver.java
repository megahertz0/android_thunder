package com.xunlei.downloadprovider.frame.user;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.umeng.a;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.homepage.recommend.c.c;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.util.aa;
import com.xunlei.xllib.a.b;

public class NetWorkChangedReceiver extends BroadcastReceiver {
    private static final String a;

    static {
        a = NetWorkChangedReceiver.class.getSimpleName();
    }

    private static void a(boolean z) {
        BrothersApplication.a().getSharedPreferences("shared_for_last_last_network_status", 0).edit().putBoolean("last_network_status", z).commit();
    }

    public void onReceive(Context context, Intent intent) {
        if (!intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            return;
        }
        if (b.a(context)) {
            if ((b.f(context) & aa.c(context, "is_unactive_pause")) != 0) {
                new StringBuilder().append(getClass()).append("---NetHelper.isActiveNetworkWifi(context)---").append(Thread.currentThread().getId());
                if (BrothersApplication.j != null) {
                    BrothersApplication.j.obtainMessage(0).sendToTarget();
                }
                aa.a(context, "is_unactive_pause", false);
                XLToast.a(context, XLToastType.XLTOAST_TYPE_ALARM, "wifi\u72b6\u6001\u4e0b\u7ee7\u7eed\u4e0b\u8f7d");
            }
            bo.a();
            for (ag agVar : bo.b()) {
                if (agVar != null && agVar.a != null && !agVar.a.equals(a.d) && !agVar.d.equals(a.d) && agVar.g.equals("failed_report")) {
                    new StringBuilder("\u7f51\u7edc\u6062\u590d\u6b63\u5e38\uff0c\u91cd\u65b0\u4e0a\u62a5\u4e0a\u6b21\u4e0a\u62a5\u5931\u8d25\u7684\u725b\u53c9\u4efb\u52a1\u6570\u636eNXTaskInfo --> ").append(agVar);
                    bo.a().b(agVar);
                }
            }
            if (BrothersApplication.a().getSharedPreferences("shared_for_last_last_network_status", 0).getBoolean("last_network_status", false)) {
                LoginHelper.a();
                if (!LoginHelper.c()) {
                    a();
                }
            } else {
                a(true);
                LoginHelper.a();
                if (!LoginHelper.c()) {
                    a();
                }
            }
            c.a().b();
            if (!com.xunlei.downloadprovider.download.c.c.a().b) {
                com.xunlei.downloadprovider.download.c.c.a().b();
                com.xunlei.downloadprovider.download.c.c.a().b = true;
                return;
            }
            return;
        }
        a(false);
    }

    private void a() {
        LoginHelper.a().a(false);
        new StringBuilder().append(getClass()).append("---LoginHelper.getInstance().userLoginWithStoredInfo()---").append(Thread.currentThread().getId());
    }
}
