package com.tencent.wxop.stat.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import com.sina.weibo.sdk.component.GameManager;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.a;
import com.umeng.message.MsgConstant;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class q {
    private static String a;

    static {
        a = a.d;
    }

    public static String a(Context context) {
        try {
            if (a(context, MsgConstant.PERMISSION_READ_PHONE_STATE)) {
                String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                if (deviceId != null) {
                    return deviceId;
                }
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public static String a(String str) {
        if (str == null) {
            return null;
        }
        if (VERSION.SDK_INT < 8) {
            return str;
        }
        try {
            str = new String(f.b(g.a(str.getBytes(GameManager.DEFAULT_CHARSET), 0)), GameManager.DEFAULT_CHARSET);
            return str;
        } catch (Throwable th) {
            return str;
        }
    }

    public static JSONArray a(Context context, int i) {
        try {
            if (a(context, MsgConstant.PERMISSION_INTERNET) && a(context, MsgConstant.PERMISSION_ACCESS_NETWORK_STATE)) {
                WifiManager wifiManager = (WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI);
                if (wifiManager != null) {
                    List scanResults = wifiManager.getScanResults();
                    if (scanResults != null && scanResults.size() > 0) {
                        Collections.sort(scanResults, new r());
                        JSONArray jSONArray = new JSONArray();
                        int i2 = 0;
                        while (i2 < scanResults.size() && i2 < i) {
                            ScanResult scanResult = (ScanResult) scanResults.get(i2);
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("bs", scanResult.BSSID);
                            jSONObject.put("ss", scanResult.SSID);
                            jSONArray.put(jSONObject);
                            i2++;
                        }
                        return jSONArray;
                    }
                }
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public static void a(JSONObject jSONObject, String str, String str2) {
        if (str2 != null) {
            try {
                if (str2.length() > 0) {
                    jSONObject.put(str, str2);
                }
            } catch (Throwable th) {
            }
        }
    }

    public static boolean a(Context context, String str) {
        try {
            return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
        } catch (Throwable th) {
            return false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String b(android.content.Context r1) {
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.common.q.b(android.content.Context):java.lang.String");
        /*
        r0 = "android.permission.ACCESS_WIFI_STATE";
        r0 = a(r1, r0);
        if (r0 == 0) goto L_0x0026;
    L_0x0009:
        r0 = "wifi";
        r0 = r1.getSystemService(r0);	 Catch:{ Exception -> 0x0021 }
        r0 = (android.net.wifi.WifiManager) r0;	 Catch:{ Exception -> 0x0021 }
        if (r0 != 0) goto L_0x0018;
    L_0x0014:
        r0 = "";
    L_0x0017:
        return r0;
    L_0x0018:
        r0 = r0.getConnectionInfo();	 Catch:{ Exception -> 0x0021 }
        r0 = r0.getMacAddress();	 Catch:{ Exception -> 0x0021 }
        goto L_0x0017;
    L_0x0021:
        r0 = move-exception;
        r0 = "";
        goto L_0x0017;
    L_0x0026:
        r0 = "";
        goto L_0x0017;
        */
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        if (VERSION.SDK_INT < 8) {
            return str;
        }
        try {
            str = new String(g.b(f.a(str.getBytes(GameManager.DEFAULT_CHARSET)), 0), GameManager.DEFAULT_CHARSET);
            return str;
        } catch (Throwable th) {
            return str;
        }
    }

    public static WifiInfo c(Context context) {
        if (a(context, MsgConstant.PERMISSION_ACCESS_WIFI_STATE)) {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(UtilityImpl.NET_TYPE_WIFI);
            if (wifiManager != null) {
                return wifiManager.getConnectionInfo();
            }
        }
        return null;
    }

    public static String d(Context context) {
        try {
            WifiInfo c = c(context);
            if (c != null) {
                return c.getBSSID();
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public static String e(Context context) {
        try {
            WifiInfo c = c(context);
            if (c != null) {
                return c.getSSID();
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public static boolean f(Context context) {
        try {
            if (a(context, MsgConstant.PERMISSION_INTERNET) && a(context, MsgConstant.PERMISSION_ACCESS_NETWORK_STATE)) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager != null) {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
                }
            }
        } catch (Throwable th) {
        }
        return false;
    }
}
