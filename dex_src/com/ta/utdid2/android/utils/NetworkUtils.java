package com.ta.utdid2.android.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.message.MsgConstant;

public class NetworkUtils {
    public static final String DEFAULT_WIFI_ADDRESS = "00-00-00-00-00-00";
    private static final String TAG = "NetworkUtils";
    private static final int[] WEAK_NETWORK_GROUP;
    public static final String WIFI = "Wi-Fi";
    private static ConnectivityManager sConnManager;

    static {
        sConnManager = null;
        WEAK_NETWORK_GROUP = new int[]{4, 7, 2, 1};
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager connManager = getConnManager(context);
        if (connManager != null) {
            try {
                NetworkInfo activeNetworkInfo = connManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null) {
                    return activeNetworkInfo.isConnected();
                }
            } catch (Exception e) {
                e.toString();
            }
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isConnectedToWeakNetwork(android.content.Context r6) {
        throw new UnsupportedOperationException("Method not decompiled: com.ta.utdid2.android.utils.NetworkUtils.isConnectedToWeakNetwork(android.content.Context):boolean");
        /*
        r0 = 0;
        r1 = getConnManager(r6);
        if (r1 == 0) goto L_0x0035;
    L_0x0007:
        r1 = r1.getActiveNetworkInfo();	 Catch:{ Exception -> 0x003f }
        if (r1 == 0) goto L_0x0035;
    L_0x000d:
        r2 = r1.getSubtype();	 Catch:{ Exception -> 0x003f }
        r3 = com.ta.utdid2.android.utils.DebugUtils.DBG;	 Catch:{ Exception -> 0x003f }
        if (r3 == 0) goto L_0x002f;
    L_0x0015:
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x003f }
        r4 = "subType:";
        r3.<init>(r4);	 Catch:{ Exception -> 0x003f }
        r3 = r3.append(r2);	 Catch:{ Exception -> 0x003f }
        r4 = ": name:";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x003f }
        r1 = r1.getSubtypeName();	 Catch:{ Exception -> 0x003f }
        r3.append(r1);	 Catch:{ Exception -> 0x003f }
    L_0x002f:
        r3 = WEAK_NETWORK_GROUP;	 Catch:{ Exception -> 0x003f }
        r4 = r3.length;	 Catch:{ Exception -> 0x003f }
        r1 = r0;
    L_0x0033:
        if (r1 < r4) goto L_0x0036;
    L_0x0035:
        return r0;
    L_0x0036:
        r5 = r3[r1];	 Catch:{ Exception -> 0x003f }
        if (r5 != r2) goto L_0x003c;
    L_0x003a:
        r0 = 1;
        goto L_0x0035;
    L_0x003c:
        r1 = r1 + 1;
        goto L_0x0033;
    L_0x003f:
        r1 = move-exception;
        r1.toString();
        goto L_0x0035;
        */
    }

    public static ConnectivityManager getConnManager(Context context) {
        if (context == null) {
            return null;
        }
        if (sConnManager == null) {
            sConnManager = (ConnectivityManager) context.getSystemService("connectivity");
        }
        return sConnManager;
    }

    public static String[] getNetworkState(Context context) {
        String[] strArr = new String[]{"Unknown", "Unknown"};
        try {
            if (context.getPackageManager().checkPermission(MsgConstant.PERMISSION_ACCESS_NETWORK_STATE, context.getPackageName()) != 0) {
                strArr[0] = "Unknown";
                return strArr;
            }
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                strArr[0] = "Unknown";
                return strArr;
            }
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
            if (networkInfo == null || networkInfo.getState() != State.CONNECTED) {
                NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
                if (networkInfo2 != null && networkInfo2.getState() == State.CONNECTED) {
                    strArr[0] = "2G/3G";
                    strArr[1] = networkInfo2.getSubtypeName();
                    return strArr;
                }
                return strArr;
            }
            strArr[0] = WIFI;
            return strArr;
        } catch (Exception e) {
        }
    }

    public static String getWifiAddress(Context context) {
        if (context == null) {
            return DEFAULT_WIFI_ADDRESS;
        }
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI)).getConnectionInfo();
        if (connectionInfo == null) {
            return DEFAULT_WIFI_ADDRESS;
        }
        String macAddress = connectionInfo.getMacAddress();
        return StringUtils.isEmpty(macAddress) ? DEFAULT_WIFI_ADDRESS : macAddress;
    }

    private static String _convertIntToIp(int i) {
        return new StringBuilder(String.valueOf(i & 255)).append(".").append((i >> 8) & 255).append(".").append((i >> 16) & 255).append(".").append((i >> 24) & 255).toString();
    }

    public static String getWifiIpAddress(Context context) {
        if (context != null) {
            try {
                WifiInfo connectionInfo = ((WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI)).getConnectionInfo();
                return connectionInfo != null ? _convertIntToIp(connectionInfo.getIpAddress()) : null;
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static boolean isWifi(Context context) {
        if (context == null) {
            return false;
        }
        try {
            return getNetworkState(context)[0].equals(WIFI);
        } catch (Exception e) {
            return false;
        }
    }
}
