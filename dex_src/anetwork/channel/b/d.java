package anetwork.channel.b;

import android.text.TextUtils;
import anet.channel.util.ALog;
import anetwork.channel.f.b;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONObject;

// compiled from: Taobao
final class d implements a {
    d() {
    }

    public final void a() {
        try {
            c.a(new String[]{"networkSdk"}, new e(this));
            c.a("networkSdk", "network_empty_scheme_https_switch", "true");
            b a = b.a();
            Object a2 = c.a("networkSdk", "network_monitor_whitelist_url", null);
            if (a.d == null) {
                a.d = new HashSet();
            } else {
                a.d.clear();
            }
            if (ALog.isPrintLog(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                ALog.i("awcn.StatisticReqTimes", new StringBuilder("urlsFromOrange: ").append(a2).toString(), null, new Object[0]);
            }
            if (!TextUtils.isEmpty(a2)) {
                try {
                    Iterator keys = new JSONObject(a2).keys();
                    while (keys.hasNext()) {
                        a.d.add((String) keys.next());
                    }
                } catch (Exception e) {
                    ALog.e("awcn.StatisticReqTimes", "whiteReqUrls from orange isnot json format", null, new Object[0]);
                }
            }
        } catch (Throwable e2) {
            ALog.e("awcn.OrangeConfigImpl", "register fail", null, e2, new Object[0]);
        }
    }
}
