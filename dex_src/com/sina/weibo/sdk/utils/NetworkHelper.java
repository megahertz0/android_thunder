package com.sina.weibo.sdk.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.DetailedState;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.alipay.sdk.util.h;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.List;

public class NetworkHelper {
    public static boolean hasInternetPermission(Context context) {
        return context == null || context.checkCallingOrSelfPermission(MsgConstant.PERMISSION_INTERNET) == 0;
    }

    public static boolean isNetworkAvailable(Context context) {
        if (context == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static boolean isWifiValid(Context context) {
        if (context == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);
        return activeNetworkInfo != null && 1 == activeNetworkInfo.getType() && activeNetworkInfo.isConnected();
    }

    public static boolean isMobileNetwork(Context context) {
        if (context == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);
        return activeNetworkInfo != null && activeNetworkInfo != null && activeNetworkInfo.getType() == 0 && activeNetworkInfo.isConnected();
    }

    public static NetworkInfo getActiveNetworkInfo(Context context) {
        return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
    }

    public static NetworkInfo getNetworkInfo(Context context, int i) {
        return ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(i);
    }

    public static int getNetworkType(Context context) {
        if (context == null) {
            return -1;
        }
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);
        return activeNetworkInfo == null ? -1 : activeNetworkInfo.getType();
    }

    public static int getWifiState(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI);
        return wifiManager == null ? XZBDevice.DOWNLOAD_LIST_ALL : wifiManager.getWifiState();
    }

    public static DetailedState getWifiConnectivityState(Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context, 1);
        return networkInfo == null ? DetailedState.FAILED : networkInfo.getDetailedState();
    }

    public static boolean wifiConnection(Context context, String str, String str2) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI);
        String toString = new StringBuilder(h.f).append(str).append(h.f).toString();
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        if (connectionInfo != null && (str.equals(connectionInfo.getSSID()) || toString.equals(connectionInfo.getSSID()))) {
            return true;
        }
        List scanResults = wifiManager.getScanResults();
        if (!(scanResults == null || scanResults.size() == 0)) {
            int size = scanResults.size() - 1;
            while (size >= 0) {
                String str3 = ((ScanResult) scanResults.get(size)).SSID;
                if (!str.equals(str3) && !toString.equals(str3)) {
                    size--;
                }
                WifiConfiguration wifiConfiguration = new WifiConfiguration();
                wifiConfiguration.SSID = toString;
                wifiConfiguration.preSharedKey = new StringBuilder(h.f).append(str2).append(h.f).toString();
                wifiConfiguration.status = 2;
                return wifiManager.enableNetwork(wifiManager.addNetwork(wifiConfiguration), false);
            }
        }
        return false;
    }

    public static void clearCookies(Context context) {
        CookieSyncManager.createInstance(context);
        CookieManager.getInstance().removeAllCookie();
        CookieSyncManager.getInstance().sync();
    }

    public static String generateUA(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SocializeConstants.OS);
        stringBuilder.append("__");
        stringBuilder.append("weibo");
        stringBuilder.append("__");
        stringBuilder.append("sdk");
        stringBuilder.append("__");
        try {
            stringBuilder.append(context.getPackageManager().getPackageInfo(context.getPackageName(), R.styleable.Toolbar_titleMarginBottom).versionName.replaceAll("\\s+", "_"));
        } catch (Exception e) {
            stringBuilder.append(UtilityImpl.NET_TYPE_UNKNOWN);
        }
        return stringBuilder.toString();
    }
}
