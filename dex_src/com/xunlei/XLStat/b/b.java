package com.xunlei.XLStat.b;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.xunlei.XLStat.XLStatLog.XLStatLog;
import com.xunlei.mediaserver.Utility;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class b {
    private static String a;
    private static String b;

    static {
        a = "999999999999";
        b = "999999999999999";
    }

    public static String a() {
        String str;
        String str2 = BuildConfig.VERSION_NAME;
        File file = new File("/proc/cpuinfo");
        if (!file.exists()) {
            return BuildConfig.VERSION_NAME;
        }
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file));
            try {
                String readLine = bufferedReader2.readLine();
                try {
                    bufferedReader2.close();
                    str = readLine;
                } catch (IOException e) {
                    str = readLine;
                }
            } catch (Exception e2) {
                bufferedReader2.close();
                str = str2;
                return str == null ? str : BuildConfig.VERSION_NAME;
            } catch (Throwable th) {
                Throwable th2 = th;
                bufferedReader = bufferedReader2;
                Throwable th3 = th2;
                bufferedReader.close();
                throw th3;
            }
        } catch (Exception e3) {
            bufferedReader2 = null;
            try {
                bufferedReader2.close();
                str = str2;
            } catch (IOException e4) {
                str = str2;
            }
            if (str == null) {
            }
        } catch (Throwable th4) {
            th3 = th4;
            try {
                bufferedReader.close();
            } catch (IOException e5) {
            }
            throw th3;
        }
        if (str == null) {
        }
    }

    public static String b() {
        String str = "/proc/version";
        String str2 = BuildConfig.VERSION_NAME;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
            str2 = bufferedReader.readLine().split("\\s+")[2];
            bufferedReader.close();
            str = str2;
        } catch (Exception e) {
            e.printStackTrace();
            str = str2;
        }
        return str == null ? BuildConfig.VERSION_NAME : str;
    }

    public static String c() {
        return VERSION.RELEASE == null ? BuildConfig.VERSION_NAME : VERSION.RELEASE;
    }

    public static String d() {
        return Build.MODEL == null ? BuildConfig.VERSION_NAME : Build.MODEL;
    }

    public static double[] a(Context context) {
        double[] dArr = new double[]{0.0d, 0.0d, 0.0d, 0.0d};
        if (context != null) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            if (displayMetrics != null) {
                try {
                    dArr[0] = (double) displayMetrics.heightPixels;
                    dArr[1] = (double) displayMetrics.widthPixels;
                    dArr[2] = (double) displayMetrics.density;
                    dArr[3] = Math.sqrt(Math.pow((double) displayMetrics.heightPixels, 2.0d) + Math.pow((double) displayMetrics.widthPixels, 2.0d)) / ((double) (displayMetrics.density * 160.0f));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return dArr;
    }

    public static String b(Context context) {
        String str = BuildConfig.VERSION_NAME;
        if (context == null) {
            return str;
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            return str;
        }
        String deviceId;
        try {
            deviceId = telephonyManager.getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
            deviceId = str;
        }
        return deviceId == null ? BuildConfig.VERSION_NAME : deviceId;
    }

    public static String c(Context context) {
        if (context == null) {
            return BuildConfig.VERSION_NAME;
        }
        WifiManager wifiManager = (WifiManager) context.getSystemService(Utility.NETWORK_WIFI);
        if (wifiManager == null) {
            return BuildConfig.VERSION_NAME;
        }
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        String str = BuildConfig.VERSION_NAME;
        if (connectionInfo != null) {
            str = connectionInfo.getMacAddress();
        }
        return str == null ? BuildConfig.VERSION_NAME : str;
    }

    public static String d(Context context) {
        if (context == null) {
            XLStatLog.d("SystemEnvironment", "getIMSI", new StringBuilder("context: ").append(context).toString());
            return BuildConfig.VERSION_NAME;
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            XLStatLog.d("SystemEnvironment", "getIMSI", new StringBuilder("mTelephonyMgr: ").append(telephonyManager).toString());
            return BuildConfig.VERSION_NAME;
        }
        String subscriberId;
        String str = BuildConfig.VERSION_NAME;
        try {
            subscriberId = telephonyManager.getSubscriberId();
        } catch (Exception e) {
            e.printStackTrace();
            subscriberId = str;
        }
        if (subscriberId == null || BuildConfig.VERSION_NAME.equals(subscriberId)) {
            subscriberId = BuildConfig.VERSION_NAME;
        }
        XLStatLog.d("SystemEnvironment", "getIMSI", new StringBuilder("imsi: ").append(subscriberId).toString());
        return subscriberId;
    }

    public static String e(Context context) {
        if (context == null) {
            XLStatLog.d("SystemEnvironment", "getProvidersName", new StringBuilder("context: ").append(context).toString());
            return BuildConfig.VERSION_NAME;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        } catch (Exception e) {
            e.printStackTrace();
            telephonyManager = null;
        }
        if (telephonyManager == null) {
            XLStatLog.d("SystemEnvironment", "getProvidersName", new StringBuilder("telephonyManager: ").append(telephonyManager).toString());
            return BuildConfig.VERSION_NAME;
        }
        try {
            String subscriberId = telephonyManager.getSubscriberId();
        } catch (Exception e2) {
            e2.printStackTrace();
            subscriberId = null;
        }
        if (subscriberId == null) {
            XLStatLog.d("SystemEnvironment", "getProvidersName", new StringBuilder("IMSI: ").append(subscriberId).toString());
            return BuildConfig.VERSION_NAME;
        }
        try {
            if (subscriberId.startsWith("46000") || subscriberId.startsWith("46002")) {
                subscriberId = "\ufffd\u0439\ufffd\ufffd\u01b6\ufffd";
                if (subscriberId == null || BuildConfig.VERSION_NAME.equals(subscriberId)) {
                    subscriberId = BuildConfig.VERSION_NAME;
                }
                XLStatLog.d("SystemEnvironment", "getProvidersName", new StringBuilder("provider name: ").append(subscriberId).toString());
                return subscriberId;
            }
            if (subscriberId.startsWith("46001")) {
                subscriberId = "\ufffd\u0439\ufffd\ufffd\ufffd\u0368";
            } else {
                subscriberId = subscriberId.startsWith("46003") ? "\ufffd\u0439\ufffd\ufffd\ufffd\ufffd\ufffd" : BuildConfig.VERSION_NAME;
            }
            subscriberId = BuildConfig.VERSION_NAME;
            XLStatLog.d("SystemEnvironment", "getProvidersName", new StringBuilder("provider name: ").append(subscriberId).toString());
            return subscriberId;
        } catch (Exception e22) {
            e22.printStackTrace();
            subscriberId = null;
        }
    }

    public static int f(Context context) {
        if (context == null) {
            return 0;
        }
        int myPid = Process.myPid();
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null || runningAppProcesses.size() == 0) {
            return 0;
        }
        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == myPid) {
                return myPid;
            }
        }
        return 0;
    }
}
