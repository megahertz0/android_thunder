package com.alipay.b.a.a.b;

import android.app.KeyguardManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.sina.weibo.sdk.register.mobile.SelectCountryActivity;
import com.taobao.accs.utl.UtilityImpl;
import com.tencent.open.GameAppOperation;
import com.umeng.a;
import com.umeng.message.MsgConstant;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.android.agoo.message.MessageService;
import org.json.JSONArray;
import org.json.JSONObject;

public final class b {
    private static b a;

    static {
        a = new b();
    }

    private b() {
    }

    public static b a() {
        return a;
    }

    public static String a(Context context) {
        if (a(context, MsgConstant.PERMISSION_READ_PHONE_STATE)) {
            return a.d;
        }
        String deviceId;
        if (context != null) {
            TelephonyManager telephonyManager;
            try {
                telephonyManager = (TelephonyManager) context.getSystemService("phone");
            } catch (Exception e) {
            }
            if (telephonyManager != null) {
                deviceId = telephonyManager.getDeviceId();
                return deviceId != null ? a.d : deviceId;
            }
        }
        deviceId = null;
        if (deviceId != null) {
        }
    }

    private static boolean a(Context context, String str) {
        boolean z;
        if (context.getPackageManager().checkPermission(str, context.getPackageName()) == 0) {
            z = true;
        } else {
            Object obj = null;
        }
        return !z;
    }

    public static String b() {
        long j = 0;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            j = ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Exception e) {
        }
        return String.valueOf(j);
    }

    public static String b(Context context) {
        String str = a.d;
        if (a(context, MsgConstant.PERMISSION_READ_PHONE_STATE)) {
            return a.d;
        }
        String subscriberId;
        if (context != null) {
            TelephonyManager telephonyManager;
            try {
                telephonyManager = (TelephonyManager) context.getSystemService("phone");
            } catch (Exception e) {
            }
            if (telephonyManager != null) {
                subscriberId = telephonyManager.getSubscriberId();
                return subscriberId != null ? a.d : subscriberId;
            }
        }
        subscriberId = str;
        if (subscriberId != null) {
        }
    }

    public static String c() {
        long j = 0;
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                StatFs statFs = new StatFs(com.alipay.b.a.a.a.a.a().getPath());
                j = ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
            }
        } catch (Exception e) {
        }
        return String.valueOf(j);
    }

    public static String c(Context context) {
        int i = 0;
        try {
            i = System.getInt(context.getContentResolver(), "airplane_mode_on", 0);
        } catch (Exception e) {
        }
        return i == 1 ? MessageService.MSG_DB_NOTIFY_REACHED : MessageService.MSG_DB_READY_REPORT;
    }

    public static String d() {
        InputStreamReader inputStreamReader;
        LineNumberReader lineNumberReader;
        FileInputStream fileInputStream;
        LineNumberReader lineNumberReader2;
        String str;
        InputStreamReader inputStreamReader2 = null;
        String str2 = "0000000000000000";
        try {
            FileInputStream fileInputStream2 = new FileInputStream(new File("/proc/cpuinfo"));
            try {
                inputStreamReader = new InputStreamReader(fileInputStream2);
                try {
                    String trim;
                    lineNumberReader = new LineNumberReader(inputStreamReader);
                    int i = 1;
                    while (i < 100) {
                        try {
                            String readLine = lineNumberReader.readLine();
                            if (readLine == null) {
                                break;
                            } else if (readLine.indexOf("Serial") >= 0) {
                                trim = readLine.substring(readLine.indexOf(":") + 1, readLine.length()).trim();
                                break;
                            } else {
                                i++;
                            }
                        } catch (Exception e) {
                            inputStreamReader2 = inputStreamReader;
                            fileInputStream = fileInputStream2;
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            lineNumberReader2 = lineNumberReader;
                            Throwable th3 = th2;
                        }
                    }
                    trim = str2;
                    try {
                        lineNumberReader.close();
                    } catch (Exception e2) {
                    }
                    try {
                        inputStreamReader.close();
                    } catch (Exception e3) {
                    }
                    try {
                        fileInputStream2.close();
                        str = trim;
                    } catch (Exception e4) {
                        str = trim;
                    }
                } catch (Exception e5) {
                    Reader reader = r1;
                    inputStreamReader2 = inputStreamReader;
                    fileInputStream = fileInputStream2;
                    if (lineNumberReader != null) {
                        lineNumberReader.close();
                    }
                    if (inputStreamReader2 != null) {
                        inputStreamReader2.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                        str = str2;
                    } else {
                        str = str2;
                    }
                    return str != null ? str : a.d;
                } catch (Throwable th4) {
                    th3 = th4;
                    if (lineNumberReader2 != null) {
                        lineNumberReader2.close();
                    }
                    if (inputStreamReader != null) {
                        inputStreamReader.close();
                    }
                    if (fileInputStream2 != null) {
                        fileInputStream2.close();
                    }
                    throw th3;
                }
            } catch (Exception e6) {
                lineNumberReader = null;
                fileInputStream = fileInputStream2;
                if (lineNumberReader != null) {
                    lineNumberReader.close();
                }
                if (inputStreamReader2 != null) {
                    inputStreamReader2.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                    str = str2;
                } else {
                    str = str2;
                }
                if (str != null) {
                }
            } catch (Throwable th5) {
                th3 = th5;
                inputStreamReader = null;
                if (lineNumberReader2 != null) {
                    lineNumberReader2.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                throw th3;
            }
        } catch (Exception e7) {
            lineNumberReader = null;
            fileInputStream = null;
            if (lineNumberReader != null) {
                try {
                    lineNumberReader.close();
                } catch (Exception e8) {
                }
            }
            if (inputStreamReader2 != null) {
                try {
                    inputStreamReader2.close();
                } catch (Exception e9) {
                }
            }
            if (fileInputStream != null) {
                str = str2;
            } else {
                try {
                    fileInputStream.close();
                    str = str2;
                } catch (Exception e10) {
                    str = str2;
                }
            }
            if (str != null) {
            }
        } catch (Throwable th6) {
            th3 = th6;
            inputStreamReader = null;
            fileInputStream2 = null;
            if (lineNumberReader2 != null) {
                try {
                    lineNumberReader2.close();
                } catch (Exception e11) {
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (Exception e12) {
                }
            }
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (Exception e13) {
                }
            }
            throw th3;
        }
        if (str != null) {
        }
    }

    public static String d(Context context) {
        int i = 1;
        JSONObject jSONObject = new JSONObject();
        try {
            AudioManager audioManager = (AudioManager) context.getSystemService("audio");
            if (audioManager.getRingerMode() != 0) {
                i = 0;
            }
            int streamVolume = audioManager.getStreamVolume(0);
            int streamVolume2 = audioManager.getStreamVolume(1);
            int streamVolume3 = audioManager.getStreamVolume(XZBDevice.DOWNLOAD_LIST_RECYCLE);
            int streamVolume4 = audioManager.getStreamVolume(XZBDevice.DOWNLOAD_LIST_FAILED);
            int streamVolume5 = audioManager.getStreamVolume(XZBDevice.DOWNLOAD_LIST_ALL);
            jSONObject.put("ringermode", String.valueOf(i));
            jSONObject.put(com.alipay.sdk.authjs.a.b, String.valueOf(streamVolume));
            jSONObject.put("system", String.valueOf(streamVolume2));
            jSONObject.put("ring", String.valueOf(streamVolume3));
            jSONObject.put("music", String.valueOf(streamVolume4));
            jSONObject.put(NotificationCompatApi21.CATEGORY_ALARM, String.valueOf(streamVolume5));
        } catch (Exception e) {
        }
        return jSONObject.toString();
    }

    public static String e(Context context) {
        String networkOperatorName;
        if (context != null) {
            TelephonyManager telephonyManager;
            try {
                telephonyManager = (TelephonyManager) context.getSystemService("phone");
            } catch (Exception e) {
            }
            if (telephonyManager != null) {
                networkOperatorName = telephonyManager.getNetworkOperatorName();
                return (networkOperatorName != null || "null".equals(networkOperatorName)) ? a.d : networkOperatorName;
            }
        }
        networkOperatorName = null;
        if (networkOperatorName != null) {
        }
    }

    public static String f() {
        String w = w();
        return !com.alipay.b.a.a.a.a.a(w) ? w : x();
    }

    public static String f(Context context) {
        if (a(context, MsgConstant.PERMISSION_READ_PHONE_STATE)) {
            return a.d;
        }
        String line1Number;
        String str = a.d;
        if (context != null) {
            TelephonyManager telephonyManager;
            try {
                telephonyManager = (TelephonyManager) context.getSystemService("phone");
            } catch (Exception e) {
            }
            if (telephonyManager != null) {
                line1Number = telephonyManager.getLine1Number();
                return line1Number != null ? a.d : line1Number;
            }
        }
        line1Number = str;
        if (line1Number != null) {
        }
    }

    public static String g() {
        BufferedReader bufferedReader;
        FileReader fileReader;
        BufferedReader bufferedReader2 = null;
        try {
            FileReader fileReader2 = new FileReader("/proc/cpuinfo");
            try {
                bufferedReader = new BufferedReader(fileReader2);
                try {
                    String[] split = bufferedReader.readLine().split(":\\s+", XZBDevice.DOWNLOAD_LIST_RECYCLE);
                } catch (Exception e) {
                    bufferedReader2 = bufferedReader;
                    fileReader = fileReader2;
                    if (fileReader != null) {
                        fileReader.close();
                    }
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    return a.d;
                } catch (Throwable th) {
                    th = th;
                    if (fileReader2 != null) {
                        fileReader2.close();
                    }
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                fileReader = fileReader2;
                if (fileReader != null) {
                    fileReader.close();
                }
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                return a.d;
            } catch (Throwable th2) {
                Throwable th3;
                Throwable th4 = th2;
                bufferedReader = null;
                th3 = th4;
                if (fileReader2 != null) {
                    fileReader2.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw th3;
            }
            if (split == null || split.length <= 1) {
                try {
                    fileReader2.close();
                } catch (Exception e3) {
                }
                try {
                    bufferedReader.close();
                } catch (Exception e4) {
                }
                return a.d;
            }
            String str = split[1];
            try {
                fileReader2.close();
            } catch (Exception e5) {
            }
            try {
                bufferedReader.close();
                return str;
            } catch (Exception e6) {
                return str;
            }
        } catch (Exception e7) {
            fileReader = null;
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (Exception e8) {
                }
            }
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (Exception e9) {
                }
            }
            return a.d;
        } catch (Throwable th22) {
            fileReader2 = null;
            th3 = th22;
            bufferedReader = null;
            if (fileReader2 != null) {
                try {
                    fileReader2.close();
                } catch (Exception e10) {
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e11) {
                }
            }
            throw th3;
        }
    }

    public static String g(Context context) {
        String d;
        if (context != null) {
            SensorManager sensorManager;
            try {
                sensorManager = (SensorManager) context.getSystemService("sensor");
            } catch (Exception e) {
            }
            if (sensorManager != null) {
                List<Sensor> sensorList = sensorManager.getSensorList(-1);
                if (sensorList != null && sensorList.size() > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (Sensor sensor : sensorList) {
                        stringBuilder.append(sensor.getName());
                        stringBuilder.append(sensor.getVersion());
                        stringBuilder.append(sensor.getVendor());
                    }
                    d = com.alipay.b.a.a.a.a.d(stringBuilder.toString());
                    return d != null ? a.d : d;
                }
            }
        }
        d = null;
        if (d != null) {
        }
    }

    public static String h() {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        long j = 0;
        try {
            FileReader fileReader = new FileReader("/proc/meminfo");
            try {
                bufferedReader = new BufferedReader(fileReader, 8192);
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        j = (long) Integer.parseInt(readLine.split("\\s+")[1]);
                    }
                    try {
                        fileReader.close();
                    } catch (Exception e) {
                    }
                    try {
                        bufferedReader.close();
                    } catch (Exception e2) {
                    }
                } catch (Exception e3) {
                    bufferedReader2 = bufferedReader;
                    if (fileReader != null) {
                        fileReader.close();
                    }
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    return String.valueOf(j);
                } catch (Throwable th) {
                    Throwable th2 = th;
                    if (fileReader != null) {
                        fileReader.close();
                    }
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    throw th2;
                }
            } catch (Exception e4) {
                if (fileReader != null) {
                    fileReader.close();
                }
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                return String.valueOf(j);
            } catch (Throwable th3) {
                th2 = th3;
                bufferedReader = null;
                if (fileReader != null) {
                    fileReader.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw th2;
            }
        } catch (Exception e5) {
            fileReader = null;
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (Exception e6) {
                }
            }
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (Exception e7) {
                }
            }
            return String.valueOf(j);
        } catch (Throwable th4) {
            th2 = th4;
            fileReader = null;
            bufferedReader = null;
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (Exception e8) {
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e9) {
                }
            }
            throw th2;
        }
        return String.valueOf(j);
    }

    public static String h(Context context) {
        JSONArray jSONArray = new JSONArray();
        if (context != null) {
            try {
                SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
                if (sensorManager != null) {
                    List<Sensor> sensorList = sensorManager.getSensorList(-1);
                    if (sensorList != null && sensorList.size() > 0) {
                        for (Sensor sensor : sensorList) {
                            if (sensor != null) {
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put(SelectCountryActivity.EXTRA_COUNTRY_NAME, sensor.getName());
                                jSONObject.put(GameAppOperation.QQFAV_DATALINE_VERSION, sensor.getVersion());
                                jSONObject.put("vendor", sensor.getVendor());
                                jSONArray.put(jSONObject);
                            }
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
        return jSONArray.toString();
    }

    public static String i() {
        long j = 0;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            j = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (Exception e) {
        }
        return String.valueOf(j);
    }

    public static String i(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            return Integer.toString(displayMetrics.widthPixels) + "*" + Integer.toString(displayMetrics.heightPixels);
        } catch (Exception e) {
            return a.d;
        }
    }

    public static String j() {
        long j = 0;
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                j = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
            }
        } catch (Exception e) {
        }
        return String.valueOf(j);
    }

    public static String j(Context context) {
        try {
            return context.getResources().getDisplayMetrics().widthPixels;
        } catch (Exception e) {
            return a.d;
        }
    }

    public static String k() {
        String str;
        String str2 = a.d;
        try {
            Class forName = Class.forName("android.os.SystemProperties");
            str = (String) forName.getMethod("get", new Class[]{String.class, String.class}).invoke(forName.newInstance(), new Object[]{"gsm.version.baseband", "no message"});
        } catch (Exception e) {
            str = str2;
        }
        return str == null ? a.d : str;
    }

    public static String k(Context context) {
        try {
            return context.getResources().getDisplayMetrics().heightPixels;
        } catch (Exception e) {
            return a.d;
        }
    }

    public static String l() {
        String str = a.d;
        try {
            str = Build.SERIAL;
        } catch (Exception e) {
        }
        return str == null ? a.d : str;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String l(android.content.Context r2) {
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.b.a.a.b.b.l(android.content.Context):java.lang.String");
        /*
        r0 = "android.permission.ACCESS_WIFI_STATE";
        r0 = a(r2, r0);
        if (r0 == 0) goto L_0x000d;
    L_0x0009:
        r0 = "";
    L_0x000c:
        return r0;
    L_0x000d:
        r1 = "";
        r0 = "wifi";
        r0 = r2.getSystemService(r0);	 Catch:{ Exception -> 0x0037 }
        r0 = (android.net.wifi.WifiManager) r0;	 Catch:{ Exception -> 0x0037 }
        r0 = r0.getConnectionInfo();	 Catch:{ Exception -> 0x0037 }
        r0 = r0.getMacAddress();	 Catch:{ Exception -> 0x0037 }
        if (r0 == 0) goto L_0x0032;
    L_0x0023:
        r1 = r0.length();	 Catch:{ Exception -> 0x003a }
        if (r1 == 0) goto L_0x0032;
    L_0x0029:
        r1 = "02:00:00:00:00:00";
        r1 = r1.equals(r0);	 Catch:{ Exception -> 0x003a }
        if (r1 == 0) goto L_0x000c;
    L_0x0032:
        r0 = v();	 Catch:{ Exception -> 0x003a }
        goto L_0x000c;
    L_0x0037:
        r0 = move-exception;
        r0 = r1;
        goto L_0x000c;
    L_0x003a:
        r1 = move-exception;
        goto L_0x000c;
        */
    }

    public static String m() {
        String str = a.d;
        try {
            str = Locale.getDefault().toString();
        } catch (Exception e) {
        }
        return str == null ? a.d : str;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m(android.content.Context r2) {
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.b.a.a.b.b.m(android.content.Context):java.lang.String");
        /*
        r0 = "android.permission.READ_PHONE_STATE";
        r0 = a(r2, r0);
        if (r0 == 0) goto L_0x000d;
    L_0x0009:
        r0 = "";
    L_0x000c:
        return r0;
    L_0x000d:
        r1 = "";
        r0 = "phone";
        r0 = r2.getSystemService(r0);	 Catch:{ Exception -> 0x002b }
        r0 = (android.telephony.TelephonyManager) r0;	 Catch:{ Exception -> 0x002b }
        r0 = r0.getSimSerialNumber();	 Catch:{ Exception -> 0x002b }
        if (r0 == 0) goto L_0x0027;
    L_0x001f:
        if (r0 == 0) goto L_0x000c;
    L_0x0021:
        r1 = r0.length();	 Catch:{ Exception -> 0x002e }
        if (r1 != 0) goto L_0x000c;
    L_0x0027:
        r0 = "";
        goto L_0x000c;
    L_0x002b:
        r0 = move-exception;
        r0 = r1;
        goto L_0x000c;
    L_0x002e:
        r1 = move-exception;
        goto L_0x000c;
        */
    }

    public static String n() {
        String str = a.d;
        try {
            str = TimeZone.getDefault().getDisplayName(false, 0);
        } catch (Exception e) {
        }
        return str == null ? a.d : str;
    }

    public static String n(Context context) {
        String str = a.d;
        try {
            str = Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception e) {
        }
        return str == null ? a.d : str;
    }

    public static String o() {
        try {
            long currentTimeMillis = System.currentTimeMillis() - SystemClock.elapsedRealtime();
            return (currentTimeMillis - (currentTimeMillis % 1000));
        } catch (Exception e) {
            return a.d;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String o(android.content.Context r3) {
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.b.a.a.b.b.o(android.content.Context):java.lang.String");
        /*
        r0 = "android.permission.BLUETOOTH";
        r0 = a(r3, r0);
        if (r0 == 0) goto L_0x000d;
    L_0x0009:
        r0 = "";
    L_0x000c:
        return r0;
    L_0x000d:
        r0 = y();
        if (r0 == 0) goto L_0x0022;
    L_0x0013:
        r1 = r0.length();	 Catch:{ Exception -> 0x0033 }
        if (r1 == 0) goto L_0x0022;
    L_0x0019:
        r1 = "02:00:00:00:00:00";
        r1 = r1.equals(r0);	 Catch:{ Exception -> 0x0033 }
        if (r1 == 0) goto L_0x002d;
    L_0x0022:
        r1 = r3.getContentResolver();	 Catch:{ Exception -> 0x0033 }
        r2 = "bluetooth_address";
        r0 = android.provider.Settings.Secure.getString(r1, r2);	 Catch:{ Exception -> 0x0033 }
    L_0x002d:
        if (r0 != 0) goto L_0x000c;
    L_0x002f:
        r0 = "";
        goto L_0x000c;
    L_0x0033:
        r1 = move-exception;
        goto L_0x000c;
        */
    }

    public static String p() {
        try {
            return SystemClock.elapsedRealtime();
        } catch (Exception e) {
            return a.d;
        }
    }

    public static String p(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                return String.valueOf(telephonyManager.getNetworkType());
            }
        } catch (Exception e) {
        }
        return a.d;
    }

    public static String q() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            String[] strArr = new String[]{"/dev/qemu_pipe", "/dev/socket/qemud", "/system/lib/libc_malloc_debug_qemu.so", "/sys/qemu_trace", "/system/bin/qemu-props", "/dev/socket/genyd", "/dev/socket/baseband_genyd"};
            stringBuilder.append("00" + ":");
            for (int i = 0; i < 7; i++) {
                if (new File(strArr[i]).exists()) {
                    stringBuilder.append(MessageService.MSG_DB_NOTIFY_REACHED);
                } else {
                    stringBuilder.append(MessageService.MSG_DB_READY_REPORT);
                }
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            return a.d;
        }
    }

    public static String q(Context context) {
        String str = a.d;
        if (a(context, MsgConstant.PERMISSION_ACCESS_WIFI_STATE)) {
            return a.d;
        }
        String bssid;
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI);
            if (wifiManager.isWifiEnabled()) {
                bssid = wifiManager.getConnectionInfo().getBSSID();
                return bssid != null ? a.d : bssid;
            }
        } catch (Throwable th) {
        }
        bssid = str;
        if (bssid != null) {
        }
    }

    public static String r() {
        String[] strArr = new String[]{"dalvik.system.Taint"};
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("00");
        stringBuilder.append(":");
        for (int i = 0; i <= 0; i++) {
            try {
                Class.forName(strArr[0]);
                stringBuilder.append(MessageService.MSG_DB_NOTIFY_REACHED);
            } catch (Exception e) {
                stringBuilder.append(MessageService.MSG_DB_READY_REPORT);
            }
        }
        return stringBuilder.toString();
    }

    public static String r(Context context) {
        String str = a.d;
        try {
            String str2;
            if (a(context, MsgConstant.PERMISSION_ACCESS_NETWORK_STATE)) {
                str2 = a.d;
            } else {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo == null) {
                    str2 = null;
                } else if (activeNetworkInfo.getType() == 1) {
                    str2 = "WIFI";
                } else if (activeNetworkInfo.getType() == 0) {
                    int subtype = activeNetworkInfo.getSubtype();
                    str2 = (subtype == 4 || subtype == 1 || subtype == 2 || subtype == 7 || subtype == 11) ? "2G" : (subtype == 3 || subtype == 5 || subtype == 6 || subtype == 8 || subtype == 9 || subtype == 10 || subtype == 12 || subtype == 14 || subtype == 15) ? "3G" : subtype == 13 ? "4G" : "UNKNOW";
                } else {
                    str2 = null;
                }
            }
            String z = z();
            if (com.alipay.b.a.a.a.a.b(str2) && com.alipay.b.a.a.a.a.b(z)) {
                return str2 + ":" + z();
            }
        } catch (Exception e) {
        }
        return str;
    }

    public static java.lang.String s() {
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.b.a.a.b.b.s():java.lang.String");
        /* JADX: method processing error */
/*
        Error: jadx.core.utils.exceptions.JadxRuntimeException: Try/catch wrap count limit reached in com.alipay.b.a.a.b.b.s():java.lang.String
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:54)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:40)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:16)
	at jadx.core.ProcessClass.process(ProcessClass.java:22)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:209)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:133)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(Unknown Source)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(Unknown Source)
	at java.lang.Thread.run(Unknown Source)
*/
        /*
        r2 = 48;
        r0 = "00";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = new java.util.LinkedHashMap;
        r5.<init>();
        r1 = "/system/build.prop";
        r3 = "ro.product.name=sdk";
        r5.put(r1, r3);
        r1 = "/proc/tty/drivers";
        r3 = "goldfish";
        r5.put(r1, r3);
        r1 = "/proc/cpuinfo";
        r3 = "goldfish";
        r5.put(r1, r3);
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r0 = r1.append(r0);
        r1 = ":";
        r0 = r0.append(r1);
        r0 = r0.toString();
        r4.append(r0);
        r0 = r5.keySet();
        r6 = r0.iterator();
    L_0x0049:
        r0 = r6.hasNext();
        if (r0 == 0) goto L_0x009d;
    L_0x004f:
        r0 = r6.next();
        r0 = (java.lang.String) r0;
        r1 = 0;
        r3 = new java.io.LineNumberReader;	 Catch:{ Exception -> 0x0086, all -> 0x0093 }
        r7 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x0086, all -> 0x0093 }
        r8 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x0086, all -> 0x0093 }
        r8.<init>(r0);	 Catch:{ Exception -> 0x0086, all -> 0x0093 }
        r7.<init>(r8);	 Catch:{ Exception -> 0x0086, all -> 0x0093 }
        r3.<init>(r7);	 Catch:{ Exception -> 0x0086, all -> 0x0093 }
    L_0x0065:
        r1 = r3.readLine();	 Catch:{ Exception -> 0x00a7, all -> 0x00a4 }
        if (r1 == 0) goto L_0x00aa;
    L_0x006b:
        r7 = r1.toLowerCase();	 Catch:{ Exception -> 0x00a7, all -> 0x00a4 }
        r1 = r5.get(r0);	 Catch:{ Exception -> 0x00a7, all -> 0x00a4 }
        r1 = (java.lang.CharSequence) r1;	 Catch:{ Exception -> 0x00a7, all -> 0x00a4 }
        r1 = r7.contains(r1);	 Catch:{ Exception -> 0x00a7, all -> 0x00a4 }
        if (r1 == 0) goto L_0x0065;
    L_0x007b:
        r0 = 49;
    L_0x007d:
        r4.append(r0);
        r3.close();	 Catch:{ Exception -> 0x0084 }
        goto L_0x0049;
    L_0x0084:
        r0 = move-exception;
        goto L_0x0049;
    L_0x0086:
        r0 = move-exception;
        r0 = r1;
    L_0x0088:
        r4.append(r2);
        if (r0 == 0) goto L_0x0049;
    L_0x008d:
        r0.close();	 Catch:{ Exception -> 0x0091 }
        goto L_0x0049;
    L_0x0091:
        r0 = move-exception;
        goto L_0x0049;
    L_0x0093:
        r0 = move-exception;
    L_0x0094:
        r4.append(r2);
        if (r1 == 0) goto L_0x009c;
    L_0x0099:
        r1.close();	 Catch:{ Exception -> 0x00a2 }
    L_0x009c:
        throw r0;
    L_0x009d:
        r0 = r4.toString();
        return r0;
    L_0x00a2:
        r1 = move-exception;
        goto L_0x009c;
    L_0x00a4:
        r0 = move-exception;
        r1 = r3;
        goto L_0x0094;
    L_0x00a7:
        r0 = move-exception;
        r0 = r3;
        goto L_0x0088;
    L_0x00aa:
        r0 = r2;
        goto L_0x007d;
        */
    }

    public static String s(Context context) {
        if (!((KeyguardManager) context.getSystemService("keyguard")).isKeyguardSecure()) {
            return "0:0";
        }
        String[] strArr = new String[]{"/data/system/password.key", "/data/system/gesture.key", "/data/system/gatekeeper.password.key", "/data/system/gatekeeper.gesture.key", "/data/system/gatekeeper.pattern.key"};
        int i = 0;
        long j = 0;
        while (i < 5) {
            long j2 = -1;
            try {
                j2 = new File(strArr[i]).lastModified();
            } catch (Exception e) {
            }
            i++;
            j = Math.max(j2, j);
        }
        return new StringBuilder("1:").append(j).toString();
    }

    public static String t() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("00" + ":");
        Map linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("BRAND", "generic");
        linkedHashMap.put("BOARD", UtilityImpl.NET_TYPE_UNKNOWN);
        linkedHashMap.put("DEVICE", "generic");
        linkedHashMap.put("HARDWARE", "goldfish");
        linkedHashMap.put("PRODUCT", "sdk");
        linkedHashMap.put("MODEL", "sdk");
        for (String str : linkedHashMap.keySet()) {
            try {
                String str2 = (String) Build.class.getField(str).get(null);
                String str3 = (String) linkedHashMap.get(str3);
                str2 = str2 != null ? str2.toLowerCase() : null;
                char c = (str2 == null || !str2.contains(str3)) ? '0' : '1';
                stringBuilder.append(c);
            } catch (Exception e) {
                stringBuilder.append('0');
            }
        }
        return stringBuilder.toString();
    }

    public static String t(Context context) {
        try {
            Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            int intExtra = registerReceiver.getIntExtra("level", -1);
            int intExtra2 = registerReceiver.getIntExtra(Impl.COLUMN_STATUS, -1);
            Object obj = (intExtra2 == 2 || intExtra2 == 5) ? 1 : null;
            return (obj != null ? MessageService.MSG_DB_NOTIFY_REACHED : MessageService.MSG_DB_READY_REPORT) + ":" + intExtra;
        } catch (Exception e) {
            return a.d;
        }
    }

    public static String u() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("00" + ":");
        Map linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("ro.hardware", "goldfish");
        linkedHashMap.put("ro.kernel.qemu", MessageService.MSG_DB_NOTIFY_REACHED);
        linkedHashMap.put("ro.product.device", "generic");
        linkedHashMap.put("ro.product.model", "sdk");
        linkedHashMap.put("ro.product.brand", "generic");
        linkedHashMap.put("ro.product.name", "sdk");
        linkedHashMap.put("ro.build.fingerprint", "test-keys");
        linkedHashMap.put("ro.product.manufacturer", "unknow");
        for (String b : linkedHashMap.keySet()) {
            String str = (String) linkedHashMap.get(b);
            String b2 = com.alipay.b.a.a.a.a.b(b2, a.d);
            char c = (b2 == null || !b2.contains(str)) ? '0' : '1';
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    private static String v() {
        try {
            List<NetworkInterface> list = Collections.list(NetworkInterface.getNetworkInterfaces());
            if (list != null) {
                for (NetworkInterface networkInterface : list) {
                    if (networkInterface != null && networkInterface.getName() != null && networkInterface.getName().equalsIgnoreCase("wlan0")) {
                        byte[] hardwareAddress = networkInterface.getHardwareAddress();
                        if (hardwareAddress == null) {
                            return "02:00:00:00:00:00";
                        }
                        StringBuilder stringBuilder = new StringBuilder();
                        int length = hardwareAddress.length;
                        for (int i = 0; i < length; i++) {
                            stringBuilder.append(String.format("%02X:", new Object[]{Integer.valueOf(hardwareAddress[i] & 255)}));
                        }
                        if (stringBuilder.length() > 0) {
                            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                        }
                        return stringBuilder.toString();
                    }
                }
            }
        } catch (Exception e) {
        }
        return "02:00:00:00:00:00";
    }

    private static String w() {
        BufferedReader bufferedReader;
        FileReader fileReader;
        BufferedReader bufferedReader2 = null;
        try {
            FileReader fileReader2 = new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq");
            try {
                bufferedReader = new BufferedReader(fileReader2, 8192);
                try {
                    String readLine = bufferedReader.readLine();
                } catch (Exception e) {
                    bufferedReader2 = bufferedReader;
                    fileReader = fileReader2;
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    if (fileReader != null) {
                        fileReader.close();
                    }
                    return a.d;
                } catch (Throwable th) {
                    th = th;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (fileReader2 != null) {
                        fileReader2.close();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                fileReader = fileReader2;
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
                return a.d;
            } catch (Throwable th2) {
                Throwable th3;
                Throwable th4 = th2;
                bufferedReader = null;
                th3 = th4;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader2 != null) {
                    fileReader2.close();
                }
                throw th3;
            }
            if (com.alipay.b.a.a.a.a.a(readLine)) {
                try {
                    bufferedReader.close();
                } catch (Exception e3) {
                }
                try {
                    fileReader2.close();
                } catch (Exception e4) {
                }
                return a.d;
            }
            readLine = readLine.trim();
            try {
                bufferedReader.close();
            } catch (Exception e5) {
            }
            try {
                fileReader2.close();
                return readLine;
            } catch (Exception e6) {
                return readLine;
            }
        } catch (Exception e7) {
            fileReader = null;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (Exception e8) {
                }
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (Exception e9) {
                }
            }
            return a.d;
        } catch (Throwable th22) {
            fileReader2 = null;
            th3 = th22;
            bufferedReader = null;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e10) {
                }
            }
            if (fileReader2 != null) {
                try {
                    fileReader2.close();
                } catch (Exception e11) {
                }
            }
            throw th3;
        }
    }

    private static String x() {
        BufferedReader bufferedReader;
        FileReader fileReader = null;
        String str = "/proc/cpuinfo";
        String str2 = a.d;
        try {
            FileReader fileReader2 = new FileReader(str);
            try {
                String[] split;
                bufferedReader = new BufferedReader(fileReader2, 8192);
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            if (!com.alipay.b.a.a.a.a.a(readLine)) {
                                split = readLine.split(":");
                                if (split != null && split.length > 1 && split[0].contains("BogoMIPS")) {
                                    break;
                                }
                            }
                        }
                        try {
                            break;
                        } catch (Exception e) {
                        }
                    } catch (Exception e2) {
                        fileReader = fileReader2;
                    } catch (Throwable th) {
                        Throwable th2 = th;
                    }
                }
                str2 = split[1].trim();
                break;
                fileReader2.close();
                try {
                    bufferedReader.close();
                } catch (Exception e3) {
                }
            } catch (Exception e4) {
                bufferedReader = null;
                fileReader = fileReader2;
                if (fileReader != null) {
                    fileReader.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                return str2;
            } catch (Throwable th3) {
                th2 = th3;
                bufferedReader = null;
                if (fileReader2 != null) {
                    fileReader2.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw th2;
            }
        } catch (Exception e5) {
            bufferedReader = null;
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (Exception e6) {
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e7) {
                }
            }
            return str2;
        } catch (Throwable th4) {
            th2 = th4;
            bufferedReader = null;
            fileReader2 = null;
            if (fileReader2 != null) {
                try {
                    fileReader2.close();
                } catch (Exception e8) {
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e9) {
                }
            }
            throw th2;
        }
        return str2;
    }

    private static String y() {
        String str = a.d;
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter != null && !defaultAdapter.isEnabled()) {
                return a.d;
            }
            str = defaultAdapter.getAddress();
            return str == null ? a.d : str;
        } catch (Exception e) {
        }
    }

    private static String z() {
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception e) {
        }
        return a.d;
    }

    public final String e() {
        try {
            return String.valueOf(new File("/sys/devices/system/cpu/").listFiles(new c(this)).length);
        } catch (Exception e) {
            return MessageService.MSG_DB_NOTIFY_REACHED;
        }
    }
}
