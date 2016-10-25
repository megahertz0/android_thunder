package com.umeng.socialize.utils;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Locale;

public class DeviceConfig {
    public static final int DEFAULT_TIMEZONE = 8;
    protected static final String LOG_TAG = "DeviceConfig";
    private static final String MOBILE_NETWORK = "2G/3G";
    protected static final String UNKNOW = "Unknown";
    private static final String WIFI = "Wi-Fi";
    public static Context context;
    public static final boolean isAndroidM = false;

    public static boolean isAppInstalled(String str, Context context) {
        Object obj = 1;
        if (context == null) {
            return false;
        }
        boolean z;
        try {
            context.getPackageManager().getPackageInfo(str, 1);
        } catch (NameNotFoundException e) {
            z = false;
        }
        return z;
    }

    public static String getAppVersion(String str, Context context) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isChinese(Context context) {
        return context.getResources().getConfiguration().locale.toString().equals(Locale.CHINA.toString());
    }

    public static boolean checkPermission(Context context, String str) {
        return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
    }

    public static String getDeviceId(Context context) {
        String deviceId;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            Log.w(LOG_TAG, "No IMEI.");
        }
        String str = BuildConfig.VERSION_NAME;
        try {
            if (checkPermission(context, "android.permission.READ_PHONE_STATE")) {
                deviceId = telephonyManager.getDeviceId();
                if (TextUtils.isEmpty(deviceId)) {
                    return deviceId;
                }
                Log.w(LOG_TAG, "No IMEI.");
                deviceId = getMac(context);
                if (TextUtils.isEmpty(deviceId)) {
                    return deviceId;
                }
                Log.w(LOG_TAG, "Failed to take mac as IMEI. Try to use Secure.ANDROID_ID instead.");
                deviceId = Secure.getString(context.getContentResolver(), "android_id");
                Log.w(LOG_TAG, new StringBuilder("getDeviceId: Secure.ANDROID_ID: ").append(deviceId).toString());
                return TextUtils.isEmpty(deviceId) ? getDeviceSN() : deviceId;
            }
        } catch (Exception e) {
            Log.w(LOG_TAG, "No IMEI.", e);
        }
        deviceId = str;
        if (TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }
        Log.w(LOG_TAG, "No IMEI.");
        deviceId = getMac(context);
        if (TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }
        Log.w(LOG_TAG, "Failed to take mac as IMEI. Try to use Secure.ANDROID_ID instead.");
        deviceId = Secure.getString(context.getContentResolver(), "android_id");
        Log.w(LOG_TAG, new StringBuilder("getDeviceId: Secure.ANDROID_ID: ").append(deviceId).toString());
        if (TextUtils.isEmpty(deviceId)) {
        }
    }

    public static String getDeviceSN() {
        try {
            Class forName = Class.forName("android.os.SystemProperties");
            return (String) forName.getMethod("get", new Class[]{String.class, String.class}).invoke(forName, new Object[]{"ro.serialno", "unknown"});
        } catch (Exception e) {
            return null;
        }
    }

    public static String[] getNetworkAccessMode(Context context) {
        String[] strArr = new String[]{UNKNOW, UNKNOW};
        if (context.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", context.getPackageName()) != 0) {
            strArr[0] = UNKNOW;
            return strArr;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            strArr[0] = UNKNOW;
            return strArr;
        } else if (connectivityManager.getNetworkInfo(1).getState() == State.CONNECTED) {
            strArr[0] = WIFI;
            return strArr;
        } else {
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
            if (networkInfo.getState() != State.CONNECTED) {
                return strArr;
            }
            strArr[0] = MOBILE_NETWORK;
            strArr[1] = networkInfo.getSubtypeName();
            return strArr;
        }
    }

    public static boolean isWiFiAvailable(Context context) {
        return WIFI.equals(getNetworkAccessMode(context)[0]);
    }

    public static boolean isOnline(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            return activeNetworkInfo != null ? activeNetworkInfo.isConnectedOrConnecting() : false;
        } catch (Exception e) {
            return true;
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        return checkPermission(context, "android.permission.ACCESS_NETWORK_STATE") && isOnline(context);
    }

    public static boolean isSdCardWrittenable() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static String getAndroidID(Context context) {
        return Secure.getString(context.getContentResolver(), "android_id");
    }

    public static String getMac(Context context) {
        String macShell;
        String str = BuildConfig.VERSION_NAME;
        try {
            macShell = getMacShell();
            try {
                if (TextUtils.isEmpty(macShell)) {
                    Log.w(LOG_TAG, "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
                }
            } catch (Exception e) {
                Exception e2 = e;
                Log.w(LOG_TAG, new StringBuilder("Could not get mac address.").append(e2.toString()).toString());
                return macShell;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            macShell = str;
            e2 = exception;
            Log.w(LOG_TAG, new StringBuilder("Could not get mac address.").append(e2.toString()).toString());
            return macShell;
        }
        return macShell;
    }

    public static String getPackageName(Context context) {
        return context.getPackageName();
    }

    private static String getMacShell() {
        String[] strArr = new String[]{"/sys/class/net/wlan0/address", "/sys/class/net/eth0/address", "/sys/devices/virtual/net/wlan0/address"};
        int i = 0;
        while (i < 3) {
            try {
                String reaMac = reaMac(strArr[i]);
                if (reaMac != null) {
                    return reaMac;
                }
                i++;
            } catch (Exception e) {
                Log.e(LOG_TAG, "open file  Failed", e);
            }
        }
        return null;
    }

    private static String reaMac(String str) throws FileNotFoundException {
        String str2 = null;
        Reader fileReader = new FileReader(str);
        try {
            BufferedReader bufferedReader = new BufferedReader(fileReader, 1024);
            try {
                str2 = bufferedReader.readLine();
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    bufferedReader.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } catch (IOException e3) {
                e = e3;
                Log.e(LOG_TAG, new StringBuilder("Could not read from file ").append(str).toString(), e);
                try {
                    fileReader.close();
                } catch (IOException e22) {
                    e22.printStackTrace();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                return str2;
            }
        } catch (IOException e4) {
            e = e4;
            bufferedReader = null;
            try {
                Exception e5;
                Log.e(LOG_TAG, new StringBuilder("Could not read from file ").append(str).toString(), e5);
                fileReader.close();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e222) {
                        e222.printStackTrace();
                    }
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                fileReader.close();
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw th2;
            }
            return str2;
        } catch (Throwable th3) {
            bufferedReader = null;
            th2 = th3;
            try {
                fileReader.close();
            } catch (IOException e2222) {
                e2222.printStackTrace();
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e22222) {
                    e22222.printStackTrace();
                }
            }
            throw th2;
        }
        return str2;
    }
}
