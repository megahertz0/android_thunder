package com.inmobi.commons.core.utilities.info;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.utilities.d;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.message.MsgConstant;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

// compiled from: DeviceInfo.java
public class b {
    private static String b() {
        return Locale.getDefault().toString();
    }

    private static String c() {
        Context b = a.b();
        String str = com.umeng.a.d;
        if (d.a("root", MsgConstant.PERMISSION_ACCESS_NETWORK_STATE)) {
            ConnectivityManager connectivityManager = (ConnectivityManager) b.getSystemService("connectivity");
            if (connectivityManager != null) {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null) {
                    int type = activeNetworkInfo.getType();
                    int subtype = activeNetworkInfo.getSubtype();
                    if (type == 1) {
                        return UtilityImpl.NET_TYPE_WIFI;
                    }
                    if (type == 0) {
                        String str2 = "carrier";
                        if (subtype == 1) {
                            return "gprs";
                        }
                        if (subtype == 2) {
                            return "edge";
                        }
                        if (subtype == 3) {
                            return "umts";
                        }
                        return subtype == 0 ? "carrier" : str2;
                    }
                }
            }
        }
        return str;
    }

    public static Map<String, String> a() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("d-netType", c());
        hashMap.put("d-localization", b());
        return hashMap;
    }
}
