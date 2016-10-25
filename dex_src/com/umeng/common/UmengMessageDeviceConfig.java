package com.umeng.common;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.ta.utdid2.device.UTDevice;
import com.umeng.message.proguard.g;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.microedition.khronos.opengles.GL10;
import org.android.spdy.SpdyProtocol;

public class UmengMessageDeviceConfig {
    public static final int DEFAULT_TIMEZONE = 8;
    protected static final String a;
    protected static final String b = "Unknown";
    private static final String c = "2G/3G";
    private static final String d = "Wi-Fi";

    static {
        a = UmengMessageDeviceConfig.class.getName();
    }

    public static boolean isAppInstalled(String str, Context context) {
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static boolean isChinese(Context context) {
        return context.getResources().getConfiguration().locale.toString().equals(Locale.CHINA.toString());
    }

    public static boolean isScreenPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }

    public static String getAppVersionCode(Context context) {
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
        } catch (NameNotFoundException e) {
            return b;
        }
    }

    public static String getAppVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            return b;
        }
    }

    public static boolean checkPermission(Context context, String str) {
        return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
    }

    public static String getAppLabel(Context context) {
        ApplicationInfo applicationInfo;
        PackageManager packageManager = context.getPackageManager();
        try {
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            applicationInfo = null;
        }
        return (String) (applicationInfo != null ? packageManager.getApplicationLabel(applicationInfo) : BuildConfig.VERSION_NAME);
    }

    public static String[] getGPU(GL10 gl10) {
        try {
            String[] strArr = new String[2];
            String glGetString = gl10.glGetString(7936);
            String glGetString2 = gl10.glGetString(7937);
            strArr[0] = glGetString;
            strArr[1] = glGetString2;
            return strArr;
        } catch (Exception e) {
            UmLog.e(a, "Could not read gpu infor:", e);
            return new String[0];
        }
    }

    public static String getCPU() {
        String str;
        String str2 = null;
        try {
            Reader fileReader = new FileReader("/proc/cpuinfo");
            try {
                BufferedReader bufferedReader = new BufferedReader(fileReader, 1024);
                str2 = bufferedReader.readLine();
                bufferedReader.close();
                fileReader.close();
                str = str2;
            } catch (Exception e) {
                UmLog.e(a, "Could not read from file /proc/cpuinfo", e);
                str = str2;
            }
        } catch (Exception e2) {
            UmLog.e(a, "Could not open file /proc/cpuinfo", e2);
            str = str2;
        }
        return str != null ? str.substring(str.indexOf(R.styleable.AppCompatTheme_toolbarStyle) + 1).trim() : str;
    }

    public static String getDeviceId(Context context) {
        String deviceId;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            UmLog.w(a, "No IMEI.");
        }
        String str = BuildConfig.VERSION_NAME;
        try {
            if (checkPermission(context, "android.permission.READ_PHONE_STATE")) {
                deviceId = telephonyManager.getDeviceId();
                if (TextUtils.isEmpty(deviceId)) {
                    return deviceId;
                }
                UmLog.w(a, "No IMEI.");
                deviceId = getMac(context);
                if (TextUtils.isEmpty(deviceId)) {
                    return deviceId;
                }
                UmLog.w(a, "Failed to take mac as IMEI. Try to use Secure.ANDROID_ID instead.");
                deviceId = Secure.getString(context.getContentResolver(), "android_id");
                UmLog.i(a, new StringBuilder("getDeviceId: Secure.ANDROID_ID: ").append(deviceId).toString());
                if (TextUtils.isEmpty(deviceId)) {
                    return deviceId;
                }
                UmLog.w(a, "Failed to take Secure.ANDROID_ID as IMEI. Try to use Serial_number instead.");
                deviceId = getSerial_number();
                UmLog.i(a, new StringBuilder("getDeviceId: Serial_number: ").append(deviceId).toString());
                return deviceId;
            }
        } catch (Exception e) {
            UmLog.w(a, "No IMEI.", e);
        }
        deviceId = str;
        if (TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }
        UmLog.w(a, "No IMEI.");
        deviceId = getMac(context);
        if (TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }
        UmLog.w(a, "Failed to take mac as IMEI. Try to use Secure.ANDROID_ID instead.");
        deviceId = Secure.getString(context.getContentResolver(), "android_id");
        UmLog.i(a, new StringBuilder("getDeviceId: Secure.ANDROID_ID: ").append(deviceId).toString());
        if (TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }
        UmLog.w(a, "Failed to take Secure.ANDROID_ID as IMEI. Try to use Serial_number instead.");
        deviceId = getSerial_number();
        UmLog.i(a, new StringBuilder("getDeviceId: Serial_number: ").append(deviceId).toString());
        return deviceId;
    }

    public static String getAndroidId(Context context) {
        String string = System.getString(context.getContentResolver(), "android_id");
        return string == null ? BuildConfig.VERSION_NAME : string;
    }

    public static String getSerial_number() {
        String str = Build.SERIAL;
        return str == null ? BuildConfig.VERSION_NAME : str;
    }

    public static String getDeviceIdUmengMD5(Context context) {
        return g.b(getDeviceId(context));
    }

    public static String getDeviceIdMD5(Context context) {
        return g.a(getDeviceId(context));
    }

    public static String getNetworkOperatorName(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            return telephonyManager == null ? b : telephonyManager.getNetworkOperatorName();
        } catch (Exception e) {
            e.printStackTrace();
            return b;
        }
    }

    public static String getDisplayResolution(Context context) {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            return String.valueOf(displayMetrics.heightPixels) + "*" + String.valueOf(i);
        } catch (Exception e) {
            e.printStackTrace();
            return b;
        }
    }

    public static String[] getNetworkAccessMode(Context context) {
        String[] strArr = new String[]{b, b};
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return strArr;
            }
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
            if (networkInfo == null || networkInfo.getState() != State.CONNECTED) {
                NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
                if (networkInfo2 != null && networkInfo2.getState() == State.CONNECTED) {
                    strArr[0] = c;
                    strArr[1] = networkInfo2.getSubtypeName();
                    return strArr;
                }
                return strArr;
            }
            strArr[0] = d;
            return strArr;
        } catch (Exception e) {
        }
    }

    public static boolean isWiFiAvailable(Context context) {
        return d.equals(getNetworkAccessMode(context)[0]);
    }

    public static Location getLocation(Context context) {
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            if (checkPermission(context, "android.permission.ACCESS_FINE_LOCATION")) {
                Location lastKnownLocation = locationManager.getLastKnownLocation("gps");
                if (lastKnownLocation != null) {
                    UmLog.d(a, new StringBuilder("get location from gps:").append(lastKnownLocation.getLatitude()).append(",").append(lastKnownLocation.getLongitude()).toString());
                    return lastKnownLocation;
                }
            }
            if (checkPermission(context, "android.permission.ACCESS_COARSE_LOCATION")) {
                Location lastKnownLocation2 = locationManager.getLastKnownLocation("network");
                if (lastKnownLocation2 != null) {
                    UmLog.d(a, new StringBuilder("get location from network:").append(lastKnownLocation2.getLatitude()).append(",").append(lastKnownLocation2.getLongitude()).toString());
                    return lastKnownLocation2;
                }
            }
            UmLog.d(a, "Could not get location from GPS or Cell-id, lack ACCESS_COARSE_LOCATION or ACCESS_COARSE_LOCATION permission?");
            return null;
        } catch (Exception e) {
            UmLog.e(a, e.getMessage());
            return null;
        }
    }

    public static boolean isOnline(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            return activeNetworkInfo != null ? activeNetworkInfo.isConnectedOrConnecting() : false;
        } catch (Exception e) {
            return true;
        }
    }

    public static boolean isSdCardWrittenable() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static int getTimeZone(Context context) {
        try {
            Calendar instance = Calendar.getInstance(a(context));
            if (instance != null) {
                return instance.getTimeZone().getRawOffset() / 3600000;
            }
        } catch (Exception e) {
            UmLog.i(a, "error in getTimeZone", e);
        }
        return DEFAULT_TIMEZONE;
    }

    public static String[] getLocaleInfo(Context context) {
        String[] strArr = new String[2];
        try {
            Locale a = a(context);
            if (a != null) {
                strArr[0] = a.getCountry();
                strArr[1] = a.getLanguage();
            }
            if (TextUtils.isEmpty(strArr[0])) {
                strArr[0] = b;
            }
            if (TextUtils.isEmpty(strArr[1])) {
                strArr[1] = b;
            }
        } catch (Exception e) {
            UmLog.e(a, "error in getLocaleInfo", e);
        }
        return strArr;
    }

    private static Locale a(Context context) {
        Locale locale = null;
        try {
            Configuration configuration = new Configuration();
            System.getConfiguration(context.getContentResolver(), configuration);
            locale = configuration.locale;
        } catch (Exception e) {
            UmLog.e(a, "fail to read user config locale");
        }
        return locale == null ? Locale.getDefault() : locale;
    }

    public static String getAppkey(Context context) {
        return getMetaData(context, "UMENG_APPKEY");
    }

    public static String getMetaData(Context context, String str) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), SpdyProtocol.SLIGHTSSLV2);
            if (applicationInfo != null) {
                String string = applicationInfo.metaData.getString(str);
                if (string != null) {
                    return string.trim();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        UmLog.e(a, String.format("Could not read meta-data %s from AndroidManifest.xml.", new Object[]{str}));
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getMac(android.content.Context r4) {
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.common.UmengMessageDeviceConfig.getMac(android.content.Context):java.lang.String");
        /*
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 22;
        if (r0 > r1) goto L_0x0048;
    L_0x0006:
        r0 = "wifi";
        r0 = r4.getSystemService(r0);	 Catch:{ Exception -> 0x002d }
        r0 = (android.net.wifi.WifiManager) r0;	 Catch:{ Exception -> 0x002d }
        r1 = "android.permission.ACCESS_WIFI_STATE";
        r1 = checkPermission(r4, r1);	 Catch:{ Exception -> 0x002d }
        if (r1 == 0) goto L_0x0021;
    L_0x0018:
        r0 = r0.getConnectionInfo();	 Catch:{ Exception -> 0x002d }
        r0 = r0.getMacAddress();	 Catch:{ Exception -> 0x002d }
    L_0x0020:
        return r0;
    L_0x0021:
        r0 = a;	 Catch:{ Exception -> 0x002d }
        r1 = "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE";
        com.umeng.common.UmLog.w(r0, r1);	 Catch:{ Exception -> 0x002d }
    L_0x0029:
        r0 = "";
        goto L_0x0020;
    L_0x002d:
        r0 = move-exception;
        r1 = a;
        r2 = new java.lang.StringBuilder;
        r3 = "Could not get mac address.";
        r2.<init>(r3);
        r0 = r0.toString();
        r0 = r2.append(r0);
        r0 = r0.toString();
        com.umeng.common.UmLog.w(r1, r0);
        goto L_0x0029;
    L_0x0048:
        r1 = "";
        r0 = "";
        r2 = java.lang.Runtime.getRuntime();	 Catch:{ Exception -> 0x009c }
        r3 = "cat /sys/class/net/wlan0/address";
        r2 = r2.exec(r3);	 Catch:{ Exception -> 0x009c }
        r3 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x009c }
        r2 = r2.getInputStream();	 Catch:{ Exception -> 0x009c }
        r3.<init>(r2);	 Catch:{ Exception -> 0x009c }
        r2 = new java.io.LineNumberReader;	 Catch:{ Exception -> 0x009c }
        r2.<init>(r3);	 Catch:{ Exception -> 0x009c }
    L_0x0067:
        if (r1 == 0) goto L_0x0073;
    L_0x0069:
        r1 = r2.readLine();	 Catch:{ Exception -> 0x009c }
        if (r1 == 0) goto L_0x0067;
    L_0x006f:
        r0 = r1.trim();	 Catch:{ Exception -> 0x009c }
    L_0x0073:
        r1 = "";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0020;
    L_0x007c:
        r0 = new java.io.File;	 Catch:{ IOException -> 0x00a1 }
        r1 = "/sys/class/net/eth0/address";
        r0.<init>(r1);	 Catch:{ IOException -> 0x00a1 }
        r1 = r0.isFile();	 Catch:{ IOException -> 0x00a1 }
        if (r1 == 0) goto L_0x0029;
    L_0x008a:
        r1 = r0.exists();	 Catch:{ IOException -> 0x00a1 }
        if (r1 == 0) goto L_0x0029;
    L_0x0090:
        r0 = com.umeng.message.proguard.g.b(r0);	 Catch:{ IOException -> 0x00a1 }
        r1 = 0;
        r2 = 17;
        r0 = r0.substring(r1, r2);	 Catch:{ IOException -> 0x00a1 }
        goto L_0x0020;
    L_0x009c:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0073;
    L_0x00a1:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0029;
        */
    }

    public static String getResolution(Context context) {
        try {
            int a;
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            int i = 0;
            if ((context.getApplicationInfo().flags & 8192) == 0) {
                a = a(displayMetrics, "noncompatWidthPixels");
                i = a(displayMetrics, "noncompatHeightPixels");
            } else {
                a = -1;
            }
            if (a == -1 || r0 == -1) {
                a = displayMetrics.widthPixels;
                i = displayMetrics.heightPixels;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(a);
            stringBuffer.append("*");
            stringBuffer.append(i);
            return stringBuffer.toString();
        } catch (Exception e) {
            UmLog.e(a, "read resolution fail", e);
            return b;
        }
    }

    private static int a(Object obj, String str) {
        try {
            Field declaredField = DisplayMetrics.class.getDeclaredField(str);
            if (declaredField != null) {
                declaredField.setAccessible(true);
                return declaredField.getInt(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String getOperator(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
        } catch (Exception e) {
            UmLog.i(a, "read carrier fail", e);
            return b;
        }
    }

    public static String getTimeString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(date);
    }

    public static String getToday() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(new Date());
    }

    public static Date toTime(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static int getIntervalSeconds(Date date, Date date2) {
        if (!date.after(date2)) {
            Date date3 = date2;
            date2 = date;
            date = date3;
        }
        return (int) ((date.getTime() - date2.getTime()) / 1000);
    }

    public static String getChannel(Context context) {
        String str = b;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), SpdyProtocol.SLIGHTSSLV2);
            if (!(applicationInfo == null || applicationInfo.metaData == null)) {
                Object obj = applicationInfo.metaData.get("UMENG_CHANNEL");
                if (obj != null) {
                    String toString = obj.toString();
                    if (toString != null) {
                        return toString;
                    }
                    UmLog.i(a, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
                }
            }
            return str;
        } catch (Exception e) {
            UmLog.i(a, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
            e.printStackTrace();
            return str;
        }
    }

    public static String getPackageName(Context context) {
        return context.getPackageName();
    }

    public static String getApplicationLable(Context context) {
        return context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
    }

    public static boolean isDebug(Context context) {
        try {
            return (context.getApplicationInfo().flags & 2) != 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getUtdid(Context context) {
        try {
            return UTDevice.getUtdid(context);
        } catch (Throwable th) {
            UmLog.e(a, new StringBuilder("fail to get utdid. ").append(th.getMessage()).toString());
            return BuildConfig.VERSION_NAME;
        }
    }
}
