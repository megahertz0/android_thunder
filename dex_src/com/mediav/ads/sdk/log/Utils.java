package com.mediav.ads.sdk.log;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import anet.channel.security.ISecurity;
import com.alipay.sdk.util.h;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.security.MessageDigest;
import org.android.agoo.message.MessageService;

public class Utils {
    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    public static void init(Context context) {
        mContext = context;
    }

    public static int getIdByName(Context context, String str, String str2) {
        return context.getResources().getIdentifier(str2, str, context.getPackageName());
    }

    public static String getAndroidid() {
        String str = a.d;
        try {
            return Secure.getString(getContext().getContentResolver(), "android_id");
        } catch (Exception e) {
            MVLog.e("\u83b7\u53d6AndroidId\u5931\u8d25");
            return str;
        }
    }

    public static String getDeviceSerial() {
        String str = a.d;
        try {
            Class forName = Class.forName("android.os.SystemProperties");
            return (String) forName.getMethod("get", new Class[]{String.class}).invoke(forName, new Object[]{"ro.serialno"});
        } catch (Exception e) {
            return str;
        }
    }

    public static String getm2id() {
        return MD5(getIMEI() + getAndroidid() + getDeviceSerial());
    }

    public static String getAndroididWithMD5() {
        String str = a.d;
        try {
            str = Secure.getString(getContext().getContentResolver(), "android_id");
            return str != null ? MD5(str) : str;
        } catch (Exception e) {
            MVLog.e("\u83b7\u53d6AndroidId\u5931\u8d25");
            return str;
        }
    }

    public static String getAppPackageName() {
        String str = a.d;
        try {
            return mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).packageName;
        } catch (Exception e) {
            MVLog.e(new StringBuilder("\u5de5\u5177-\u5e94\u7528\u5305\u540d Error=").append(e.getMessage()).toString());
            return str;
        }
    }

    public static String getScreenOrientation() {
        return String.valueOf(mContext.getResources().getConfiguration().orientation);
    }

    public static String getAppname() {
        String str = a.d;
        try {
            PackageManager packageManager = mContext.getPackageManager();
            return packageManager.getPackageInfo(mContext.getPackageName(), 0).applicationInfo.loadLabel(packageManager).toString();
        } catch (Exception e) {
            MVLog.e(new StringBuilder("\u5de5\u5177-AppName Error=").append(e.getMessage()).toString());
            return str;
        }
    }

    public static String getAppVersion() {
        String str = a.d;
        try {
            return mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionName;
        } catch (Exception e) {
            MVLog.e(new StringBuilder("\u5de5\u5177-AppVer Error=").append(e.getMessage()).toString());
            return str;
        }
    }

    public static String getAppVersionCode() {
        String str = a.d;
        try {
            return String.valueOf(mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionCode);
        } catch (Exception e) {
            MVLog.e(new StringBuilder("\u5de5\u5177-AppVer Error=").append(e.getMessage()).toString());
            return str;
        }
    }

    public static String getMac() {
        String str = a.d;
        try {
            String macAddress = ((WifiManager) mContext.getSystemService(UtilityImpl.NET_TYPE_WIFI)).getConnectionInfo().getMacAddress();
            if (macAddress != null) {
                return macAddress;
            }
        } catch (Exception e) {
            MVLog.e(new StringBuilder("\u5de5\u5177-Mac Error=").append(e.getMessage()).toString());
        }
        return str;
    }

    public static String getRouteMac() {
        try {
            WifiInfo connectionInfo = ((WifiManager) mContext.getSystemService(UtilityImpl.NET_TYPE_WIFI)).getConnectionInfo();
            return connectionInfo.getBSSID() == null ? a.d : connectionInfo.getBSSID();
        } catch (Exception e) {
            MVLog.e(new StringBuilder("\u5de5\u5177-BSSID Error=").append(e.getMessage()).toString());
            return a.d;
        }
    }

    public static String getRouteSSID() {
        try {
            WifiInfo connectionInfo = ((WifiManager) mContext.getSystemService(UtilityImpl.NET_TYPE_WIFI)).getConnectionInfo();
            return connectionInfo.getSSID().contains("<") ? a.d : connectionInfo.getSSID().replace(h.f, a.d);
        } catch (Exception e) {
            MVLog.e(new StringBuilder("\u5de5\u5177-SSID Error=").append(e.getMessage()).toString());
            return a.d;
        }
    }

    public static String getMacWhitMD5() {
        String str = a.d;
        try {
            String macAddress = ((WifiManager) mContext.getSystemService(UtilityImpl.NET_TYPE_WIFI)).getConnectionInfo().getMacAddress();
            if (macAddress != null && isNotEmpty(macAddress)) {
                return MD5(macAddress);
            }
        } catch (Exception e) {
            MVLog.e(new StringBuilder("\u5de5\u5177-Mac Error=").append(e.getMessage()).toString());
        }
        return str;
    }

    public static String getSysteminfo() {
        String str = a.d;
        try {
            return new StringBuilder("Android%20").append(VERSION.RELEASE).toString();
        } catch (Exception e) {
            MVLog.e(new StringBuilder("\u5de5\u5177-SysVer Error=").append(e.getMessage()).toString());
            return str;
        }
    }

    public static boolean isNotEmpty(String str) {
        return (str == null || a.d.equals(str)) ? false : true;
    }

    public static boolean isNetEnable() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) mContext.getSystemService("connectivity")).getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } catch (Exception e) {
            MVLog.e(new StringBuilder("\u5de5\u5177-NetIsOn Error=").append(e.getMessage()).toString());
            return false;
        }
    }

    public static String getIMEI() {
        String str = a.d;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService("phone");
            if (telephonyManager == null) {
                return str;
            }
            String deviceId = telephonyManager.getDeviceId();
            try {
                return TextUtils.isEmpty(deviceId) ? Secure.getString(mContext.getContentResolver(), "android_id") : deviceId;
            } catch (Exception e) {
                Exception e2 = e;
                MVLog.e(new StringBuilder("\u5de5\u5177-IMEI Error=").append(e2.getMessage()).toString());
                return deviceId;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            deviceId = str;
            e2 = exception;
            MVLog.e(new StringBuilder("\u5de5\u5177-IMEI Error=").append(e2.getMessage()).toString());
            return deviceId;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getIMEIWhitMD5() {
        throw new UnsupportedOperationException("Method not decompiled: com.mediav.ads.sdk.log.Utils.getIMEIWhitMD5():java.lang.String");
        /*
        r1 = "";
        r0 = mContext;	 Catch:{ Exception -> 0x0033 }
        r2 = "phone";
        r0 = r0.getSystemService(r2);	 Catch:{ Exception -> 0x0033 }
        r0 = (android.telephony.TelephonyManager) r0;	 Catch:{ Exception -> 0x0033 }
        if (r0 == 0) goto L_0x0051;
    L_0x0010:
        r1 = r0.getDeviceId();	 Catch:{ Exception -> 0x0033 }
        r0 = android.text.TextUtils.isEmpty(r1);	 Catch:{ Exception -> 0x0033 }
        if (r0 == 0) goto L_0x002e;
    L_0x001a:
        r0 = mContext;	 Catch:{ Exception -> 0x0033 }
        r0 = r0.getContentResolver();	 Catch:{ Exception -> 0x0033 }
        r2 = "android_id";
        r0 = android.provider.Settings.Secure.getString(r0, r2);	 Catch:{ Exception -> 0x0033 }
        if (r0 == 0) goto L_0x002d;
    L_0x0029:
        r0 = MD5(r0);	 Catch:{ Exception -> 0x004f }
    L_0x002d:
        return r0;
    L_0x002e:
        r0 = MD5(r1);	 Catch:{ Exception -> 0x0033 }
        goto L_0x002d;
    L_0x0033:
        r0 = move-exception;
        r4 = r0;
        r0 = r1;
        r1 = r4;
    L_0x0037:
        r2 = new java.lang.StringBuilder;
        r3 = "\u5de5\u5177-IMEI Error=";
        r2.<init>(r3);
        r1 = r1.getMessage();
        r1 = r2.append(r1);
        r1 = r1.toString();
        com.mediav.ads.sdk.log.MVLog.e(r1);
        goto L_0x002d;
    L_0x004f:
        r1 = move-exception;
        goto L_0x0037;
    L_0x0051:
        r0 = r1;
        goto L_0x002d;
        */
    }

    public static String getIMSI() {
        String str = a.d;
        try {
            String subscriberId;
            TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService("phone");
            if (telephonyManager != null) {
                subscriberId = telephonyManager.getSubscriberId();
            } else {
                subscriberId = str;
            }
            try {
                return TextUtils.isEmpty(subscriberId) ? "UNKNOWN" : subscriberId;
            } catch (Exception e) {
                Exception e2 = e;
                MVLog.e(new StringBuilder("\u5de5\u5177-IMSI Error=").append(e2.getMessage()).toString());
                return subscriberId;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            subscriberId = str;
            e2 = exception;
            MVLog.e(new StringBuilder("\u5de5\u5177-IMSI Error=").append(e2.getMessage()).toString());
            return subscriberId;
        }
    }

    public static String getIMSIWhitMD5() {
        String str = a.d;
        try {
            String MD5;
            TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService("phone");
            if (telephonyManager != null) {
                str = telephonyManager.getSubscriberId();
                if (str != null) {
                    MD5 = MD5(str);
                    try {
                        return TextUtils.isEmpty(MD5) ? "UNKNOWN" : MD5;
                    } catch (Exception e) {
                        Exception e2 = e;
                        MVLog.e(new StringBuilder("\u5de5\u5177-IMSI Error=").append(e2.getMessage()).toString());
                        return MD5;
                    }
                }
            }
            MD5 = str;
            if (TextUtils.isEmpty(MD5)) {
            }
        } catch (Exception e3) {
            Exception exception = e3;
            MD5 = str;
            e2 = exception;
            MVLog.e(new StringBuilder("\u5de5\u5177-IMSI Error=").append(e2.getMessage()).toString());
            return MD5;
        }
    }

    public static String MD5(String str) {
        int i = 0;
        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] bytes = str.getBytes();
            MessageDigest instance = MessageDigest.getInstance(ISecurity.SIGN_ALGORITHM_MD5);
            instance.update(bytes);
            byte[] digest = instance.digest();
            int length = digest.length;
            char[] cArr2 = new char[(length * 2)];
            int i2 = 0;
            while (i < length) {
                byte b = digest[i];
                int i3 = i2 + 1;
                cArr2[i2] = cArr[(b >>> 4) & 15];
                i2 = i3 + 1;
                cArr2[i3] = cArr[b & 15];
                i++;
            }
            return new String(cArr2);
        } catch (Exception e) {
            MVLog.e(new StringBuilder("\u5de5\u5177-fileName-MD5 Error=").append(e.getMessage()).toString());
            return null;
        }
    }

    public static String getBrand() {
        String str = Build.BRAND;
        return str == null ? a.d : str;
    }

    public static String getProductModel() {
        String str = a.d;
        try {
            return Build.MODEL;
        } catch (Exception e) {
            MVLog.e(new StringBuilder("\u5de5\u5177-PhoneModel Error=").append(e.getMessage()).toString());
            return str;
        }
    }

    public static boolean isSDCardEnable() {
        return "mounted".equals(Environment.getExternalStorageState()) && Environment.getExternalStorageDirectory().canWrite();
    }

    public static String getNetworkOperator() {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService("phone");
            if (telephonyManager.getNetworkOperator() != null) {
                return telephonyManager.getNetworkOperator();
            }
        } catch (Exception e) {
            MVLog.e(new StringBuilder("\u5de5\u5177-getCarrierName=").append(e.getMessage()).toString());
        }
        return a.d;
    }

    public static String getDeviceScreenSizeWithString(Boolean bool) {
        try {
            DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
            return bool.booleanValue() ? String.valueOf(displayMetrics.widthPixels) : String.valueOf(displayMetrics.heightPixels);
        } catch (Exception e) {
            MVLog.e(new StringBuilder("\u5de5\u5177-ScrrenSize Error=").append(e.getMessage()).toString());
            return a.d;
        }
    }

    public static double getDeviceDensity() {
        try {
            return (double) mContext.getResources().getDisplayMetrics().density;
        } catch (Exception e) {
            MVLog.e(new StringBuilder("\u5de5\u5177-Density Error=").append(e.getMessage()).toString());
            return -1.0d;
        }
    }

    public static String getCurrentNetWorkInfo() {
        String str = a.d;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) mContext.getSystemService("connectivity")).getActiveNetworkInfo();
            return activeNetworkInfo.getType() == 1 ? MessageService.MSG_DB_READY_REPORT : activeNetworkInfo.getExtraInfo();
        } catch (Exception e) {
            MVLog.e(new StringBuilder("\u5de5\u5177-CurrentNetInfo Error=").append(e.getMessage()).toString());
            return str;
        }
    }

    private static String getString(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(b);
        }
        return stringBuffer.toString();
    }

    public static int getDeviceType() {
        return (mContext.getResources().getConfiguration().screenLayout & 15) >= 3 ? XZBDevice.DOWNLOAD_LIST_RECYCLE : 1;
    }

    public static String getCacheDir() {
        Object obj = null;
        boolean equals = "mounted".equals(Environment.getExternalStorageState());
        if (!(VERSION.SDK_INT <= 8 || equals || Environment.isExternalStorageRemovable())) {
            obj = 1;
        }
        if (equals || r0 != null) {
            try {
                return mContext.getExternalCacheDir().getPath();
            } catch (Exception e) {
                MVLog.e(e.getMessage());
            }
        }
        return mContext.getCacheDir().getPath();
    }

    public static String stackTraceToString(Throwable th) {
        Writer stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.close();
        return stringWriter.toString();
    }

    public static String base64Encode(String str) {
        return str == null ? a.d : new String(Base64.encode(str.getBytes(), XZBDevice.DOWNLOAD_LIST_RECYCLE));
    }
}
