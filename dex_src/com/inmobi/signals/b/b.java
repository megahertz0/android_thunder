package com.inmobi.signals.b;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.alipay.sdk.util.h;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.utilities.d;
import com.inmobi.signals.o;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.message.MsgConstant;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// compiled from: WifiInfoUtil.java
public class b {
    public static a a() {
        if (!e() || !o.a().e().l()) {
            return null;
        }
        int j = o.a().e().j();
        return a(a(j), b(j));
    }

    public static Map<String, String> b() {
        a a = a();
        Map hashMap = new HashMap();
        if (a != null) {
            hashMap.put("c-ap-bssid", String.valueOf(a.a()));
        }
        return hashMap;
    }

    private static boolean a(int i) {
        return !a(i, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
    }

    private static boolean b(int i) {
        return a(i, 1);
    }

    private static boolean e() {
        return d.a("signals", MsgConstant.PERMISSION_ACCESS_WIFI_STATE);
    }

    private static a a(boolean z, boolean z2) {
        WifiInfo connectionInfo = ((WifiManager) a.b().getSystemService(UtilityImpl.NET_TYPE_WIFI)).getConnectionInfo();
        if (connectionInfo == null) {
            return null;
        }
        String bssid = connectionInfo.getBSSID();
        String ssid = connectionInfo.getSSID();
        if (bssid == null || a(z, ssid)) {
            return null;
        }
        a aVar = new a();
        aVar.a(a(bssid));
        if (ssid != null && ssid.startsWith(h.f) && ssid.endsWith(h.f)) {
            ssid = ssid.substring(1, ssid.length() - 1);
        }
        if (z2) {
            ssid = null;
        }
        aVar.a(ssid);
        aVar.a(connectionInfo.getRssi());
        aVar.b(connectionInfo.getIpAddress());
        return aVar;
    }

    private static boolean a(boolean z, String str) {
        return z && str != null && str.endsWith("_nomap");
    }

    private static long a(String str) {
        String[] split = str.split("\\:");
        byte[] bArr = new byte[6];
        int i = 0;
        while (i < 6) {
            try {
                bArr[i] = (byte) Integer.parseInt(split[i], R.styleable.Toolbar_titleMarginBottom);
                i++;
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return a(bArr);
    }

    private static long a(byte[] bArr) {
        return (bArr == null || bArr.length != 6) ? 0 : ((((a(bArr[5]) | (a(bArr[4]) << 8)) | (a(bArr[3]) << 16)) | (a(bArr[2]) << 24)) | (a(bArr[1]) << 32)) | (a(bArr[0]) << 40);
    }

    private static long a(byte b) {
        return ((long) b) & 255;
    }

    private static boolean a(int i, int i2) {
        return (i & i2) == i2;
    }

    public static boolean c() {
        String[] strArr = new String[]{MsgConstant.PERMISSION_ACCESS_WIFI_STATE, "android.permission.CHANGE_WIFI_STATE", "android.permission.ACCESS_COARSE_LOCATION"};
        for (int i = 0; i < 3; i++) {
            if (!d.a("signals", strArr[i])) {
                return false;
            }
        }
        return true;
    }

    public static List<a> a(List<ScanResult> list) {
        int j = o.a().e().j();
        boolean a = a(j);
        boolean b = b(j);
        List arrayList = new ArrayList();
        if (list != null) {
            for (ScanResult scanResult : list) {
                if (!a(a, scanResult.SSID)) {
                    arrayList.add(a(scanResult, b));
                }
            }
        }
        return arrayList;
    }

    private static a a(ScanResult scanResult, boolean z) {
        String str = null;
        if (scanResult == null) {
            return null;
        }
        a aVar = new a();
        aVar.a(a(scanResult.BSSID));
        if (!z) {
            str = scanResult.SSID;
        }
        aVar.a(str);
        aVar.a(scanResult.level);
        return aVar;
    }

    public static Map<String, String> d() {
        ArrayList arrayList = (ArrayList) c.a();
        Map hashMap = new HashMap();
        if (arrayList != null && arrayList.size() > 0) {
            hashMap.put("v-ap-bssid", String.valueOf(((a) arrayList.get(arrayList.size() - 1)).a()));
        }
        return hashMap;
    }
}
