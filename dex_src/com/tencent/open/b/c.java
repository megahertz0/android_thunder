package com.tencent.open.b;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.taobao.accs.utl.UtilityImpl;
import com.tencent.open.a.f;
import com.tencent.open.utils.Global;
import com.umeng.a;
import java.util.Locale;

// compiled from: ProGuard
public class c {
    static String a;
    static String b;
    static String c;
    private static String d;
    private static String e;

    public static String a() {
        try {
            Context context = Global.getContext();
            if (context == null) {
                return a.d;
            }
            WifiManager wifiManager = (WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI);
            if (wifiManager == null) {
                return a.d;
            }
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            return connectionInfo == null ? a.d : connectionInfo.getMacAddress();
        } catch (Throwable e) {
            f.b("openSDK_LOG.MobileInfoUtil", "getLocalMacAddress>>>", e);
            return a.d;
        }
    }

    public static String a(Context context) {
        if (!TextUtils.isEmpty(d)) {
            return d;
        }
        if (context == null) {
            return a.d;
        }
        d = a.d;
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager != null) {
            int width = windowManager.getDefaultDisplay().getWidth();
            d = width + "x" + windowManager.getDefaultDisplay().getHeight();
        }
        return d;
    }

    public static String b() {
        return Locale.getDefault().getLanguage();
    }

    static {
        a = null;
        b = null;
        c = null;
        e = null;
    }

    public static String b(Context context) {
        if (a != null && a.length() > 0) {
            return a;
        }
        if (context == null) {
            return a.d;
        }
        try {
            String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            a = deviceId;
            return deviceId;
        } catch (Exception e) {
            return a.d;
        }
    }

    public static String c(Context context) {
        if (b != null && b.length() > 0) {
            return b;
        }
        if (context == null) {
            return a.d;
        }
        try {
            String simSerialNumber = ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
            b = simSerialNumber;
            return simSerialNumber;
        } catch (Exception e) {
            return a.d;
        }
    }

    public static String d(Context context) {
        if (c != null && c.length() > 0) {
            return c;
        }
        if (context == null) {
            return a.d;
        }
        try {
            String string = Secure.getString(context.getContentResolver(), "android_id");
            c = string;
            return string;
        } catch (Exception e) {
            return a.d;
        }
    }

    public static String e(Context context) {
        try {
            if (e == null) {
                WindowManager windowManager = (WindowManager) context.getSystemService("window");
                DisplayMetrics displayMetrics = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("imei=").append(b(context)).append('&');
                stringBuilder.append("model=").append(Build.MODEL).append('&');
                stringBuilder.append("os=").append(VERSION.RELEASE).append('&');
                stringBuilder.append("apilevel=").append(VERSION.SDK_INT).append('&');
                String b = a.b(context);
                if (b == null) {
                    b = a.d;
                }
                stringBuilder.append("network=").append(b).append('&');
                stringBuilder.append("sdcard=").append(Environment.getExternalStorageState().equals("mounted") ? 1 : 0).append('&');
                stringBuilder.append("display=").append(displayMetrics.widthPixels).append('*').append(displayMetrics.heightPixels).append('&');
                stringBuilder.append("manu=").append(Build.MANUFACTURER).append(com.alipay.sdk.sys.a.b);
                stringBuilder.append("wifi=").append(a.e(context));
                e = stringBuilder.toString();
            }
            return e;
        } catch (Exception e) {
            return null;
        }
    }
}
