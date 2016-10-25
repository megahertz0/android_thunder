package com.tencent.stat.common;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import anet.channel.security.ISecurity;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;
import com.sina.weibo.sdk.component.GameManager;
import com.taobao.accs.utl.UtilityImpl;
import com.tencent.stat.StatConfig;
import com.tencent.wxop.stat.common.StatConstants;
import com.umeng.a;
import com.umeng.message.MsgConstant;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import org.android.agoo.message.MessageService;
import org.apache.http.HttpHost;
import org.json.JSONObject;

public class k {
    private static String a;
    private static String b;
    private static String c;
    private static String d;
    private static Random e;
    private static StatLogger f;
    private static String g;
    private static l h;
    private static n i;
    private static String j;
    private static int k;

    static {
        a = null;
        b = null;
        c = null;
        d = null;
        e = null;
        f = null;
        g = null;
        h = null;
        i = null;
        j = "__MTA_FIRST_ACTIVATE__";
        k = -1;
    }

    public static String A(Context context) {
        try {
            SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
            if (sensorManager != null) {
                List sensorList = sensorManager.getSensorList(-1);
                if (sensorList != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < sensorList.size(); i++) {
                        stringBuilder.append(((Sensor) sensorList.get(i)).getType());
                        if (i != sensorList.size() - 1) {
                            stringBuilder.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                        }
                    }
                    return stringBuilder.toString();
                }
            }
        } catch (Object th) {
            f.e(th);
        }
        return a.d;
    }

    public static WifiInfo B(Context context) {
        if (a(context, MsgConstant.PERMISSION_ACCESS_WIFI_STATE)) {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(UtilityImpl.NET_TYPE_WIFI);
            if (wifiManager != null) {
                return wifiManager.getConnectionInfo();
            }
        }
        return null;
    }

    public static String C(Context context) {
        try {
            WifiInfo B = B(context);
            if (B != null) {
                return B.getBSSID();
            }
        } catch (Object th) {
            f.e(th);
        }
        return null;
    }

    public static String D(Context context) {
        try {
            WifiInfo B = B(context);
            if (B != null) {
                return B.getSSID();
            }
        } catch (Object th) {
            f.e(th);
        }
        return null;
    }

    public static synchronized int E(Context context) {
        int i;
        synchronized (k.class) {
            if (k != -1) {
                i = k;
            } else {
                F(context);
                i = k;
            }
        }
        return i;
    }

    public static void F(Context context) {
        k = p.a(context, j, 1);
        f.e(Integer.valueOf(k));
        if (k == 1) {
            p.b(context, j, 0);
        }
    }

    private static long G(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        MemoryInfo memoryInfo = new MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    public static int a() {
        return h().nextInt(InMobiClientPositioning.NO_REPEAT);
    }

    public static Long a(String str, String str2, int i, int i2, Long l) {
        if (str == null || str2 == null) {
            return l;
        }
        if (str2.equalsIgnoreCase(".") || str2.equalsIgnoreCase("|")) {
            str2 = new StringBuilder("\\").append(str2).toString();
        }
        String[] split = str.split(str2);
        if (split.length != i2) {
            return l;
        }
        try {
            Long valueOf = Long.valueOf(0);
            int i3 = 0;
            while (i3 < split.length) {
                i3++;
                valueOf = Long.valueOf(((long) i) * (valueOf.longValue() + Long.valueOf(split[i3]).longValue()));
            }
            return valueOf;
        } catch (NumberFormatException e) {
            return l;
        }
    }

    public static String a(long j) {
        return new SimpleDateFormat("yyyyMMdd").format(new Date(j));
    }

    public static String a(String str) {
        if (str == null) {
            return MessageService.MSG_DB_READY_REPORT;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance(ISecurity.SIGN_ALGORITHM_MD5);
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                int i = b & 255;
                if (i < 16) {
                    stringBuffer.append(MessageService.MSG_DB_READY_REPORT);
                }
                stringBuffer.append(Integer.toHexString(i));
            }
            return stringBuffer.toString();
        } catch (Throwable th) {
            return MessageService.MSG_DB_READY_REPORT;
        }
    }

    public static HttpHost a(Context context) {
        if (context == null) {
            return null;
        }
        try {
            if (context.getPackageManager().checkPermission(MsgConstant.PERMISSION_ACCESS_NETWORK_STATE, context.getPackageName()) != 0) {
                return null;
            }
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            if (activeNetworkInfo.getTypeName() != null && activeNetworkInfo.getTypeName().equalsIgnoreCase("WIFI")) {
                return null;
            }
            String extraInfo = activeNetworkInfo.getExtraInfo();
            if (extraInfo == null) {
                return null;
            }
            if (extraInfo.equals("cmwap") || extraInfo.equals("3gwap") || extraInfo.equals("uniwap")) {
                return new HttpHost("10.0.0.172", 80);
            }
            if (extraInfo.equals("ctwap")) {
                return new HttpHost("10.0.0.200", 80);
            }
            return null;
        } catch (Object th) {
            f.e(th);
        }
    }

    public static void a(JSONObject jSONObject, String str, String str2) {
        if (str2 != null) {
            try {
                if (str2.length() > 0) {
                    jSONObject.put(str, str2);
                }
            } catch (Object th) {
                f.e(th);
            }
        }
    }

    public static boolean a(Context context, String str) {
        try {
            return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
        } catch (Object th) {
            f.e(th);
            return false;
        }
    }

    public static byte[] a(byte[] bArr) {
        InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
        byte[] bArr2 = new byte[4096];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length * 2);
        while (true) {
            int read = gZIPInputStream.read(bArr2);
            if (read != -1) {
                byteArrayOutputStream.write(bArr2, 0, read);
            } else {
                bArr2 = byteArrayOutputStream.toByteArray();
                byteArrayInputStream.close();
                gZIPInputStream.close();
                byteArrayOutputStream.close();
                return bArr2;
            }
        }
    }

    public static long b(String str) {
        return a(str, ".", R.styleable.AppCompatTheme_buttonStyle, XZBDevice.DOWNLOAD_LIST_FAILED, Long.valueOf(0)).longValue();
    }

    public static synchronized StatLogger b() {
        StatLogger statLogger;
        synchronized (k.class) {
            if (f == null) {
                statLogger = new StatLogger(StatConstants.LOG_TAG);
                f = statLogger;
                statLogger.setDebugEnable(false);
            }
            statLogger = f;
        }
        return statLogger;
    }

    public static synchronized String b(Context context) {
        String l;
        synchronized (k.class) {
            if (a == null || a.trim().length() == 0) {
                l = l(context);
                a = l;
                if (l == null || a.trim().length() == 0) {
                    a = Integer.toString(h().nextInt(InMobiClientPositioning.NO_REPEAT));
                }
                l = a;
            } else {
                l = a;
            }
        }
        return l;
    }

    public static String b(Context context, String str) {
        if (!StatConfig.isEnableConcurrentProcess()) {
            return str;
        }
        if (g == null) {
            g = u(context);
        }
        return g != null ? str + "_" + g : str;
    }

    public static long c() {
        try {
            Calendar instance = Calendar.getInstance();
            instance.set(XZBDevice.Success, 0);
            instance.set(XZBDevice.Fail, 0);
            instance.set(XZBDevice.Upload, 0);
            instance.set(XZBDevice.Predownload, 0);
            return instance.getTimeInMillis() + 86400000;
        } catch (Object th) {
            f.e(th);
            return System.currentTimeMillis() + 86400000;
        }
    }

    public static synchronized String c(Context context) {
        String str;
        synchronized (k.class) {
            if (c == null || a.d == c) {
                c = f(context);
            }
            str = c;
        }
        return str;
    }

    public static String c(String str) {
        if (str == null) {
            return null;
        }
        if (VERSION.SDK_INT < 8) {
            return str;
        }
        try {
            str = new String(g.b(e.a(str.getBytes(GameManager.DEFAULT_CHARSET)), 0), GameManager.DEFAULT_CHARSET);
            return str;
        } catch (Object th) {
            f.e(th);
            return str;
        }
    }

    public static int d() {
        return VERSION.SDK_INT;
    }

    public static DisplayMetrics d(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static String d(String str) {
        if (str == null) {
            return null;
        }
        if (VERSION.SDK_INT < 8) {
            return str;
        }
        try {
            str = new String(e.b(g.a(str.getBytes(GameManager.DEFAULT_CHARSET), 0)), GameManager.DEFAULT_CHARSET);
            return str;
        } catch (Object th) {
            f.e(th);
            return str;
        }
    }

    public static String e() {
        long f = f() / 1000000;
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return String.valueOf((((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / 1000000) + "/" + String.valueOf(f);
    }

    public static boolean e(Context context) {
        try {
            if (a(context, MsgConstant.PERMISSION_ACCESS_WIFI_STATE)) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
                if (connectivityManager != null) {
                    NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
                    if (allNetworkInfo != null) {
                        int i = 0;
                        while (i < allNetworkInfo.length) {
                            if (allNetworkInfo[i].getTypeName().equalsIgnoreCase("WIFI") && allNetworkInfo[i].isConnected()) {
                                return true;
                            }
                            i++;
                        }
                    }
                }
                return false;
            }
            f.warn("can not get the permission of android.permission.ACCESS_WIFI_STATE");
            return false;
        } catch (Object th) {
            f.e(th);
        }
    }

    public static long f() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String f(android.content.Context r2) {
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.stat.common.k.f(android.content.Context):java.lang.String");
        /*
        r0 = "android.permission.ACCESS_WIFI_STATE";
        r0 = a(r2, r0);
        if (r0 == 0) goto L_0x002b;
    L_0x0009:
        r0 = "wifi";
        r0 = r2.getSystemService(r0);	 Catch:{ Exception -> 0x0021 }
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
        r1 = f;
        r1.e(r0);
        r0 = "";
        goto L_0x0017;
    L_0x002b:
        r0 = f;
        r1 = "Could not get permission of android.permission.ACCESS_WIFI_STATE";
        r0.e(r1);
        r0 = "";
        goto L_0x0017;
        */
    }

    public static boolean g(Context context) {
        try {
            if (a(context, MsgConstant.PERMISSION_INTERNET) && a(context, MsgConstant.PERMISSION_ACCESS_NETWORK_STATE)) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager != null) {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getTypeName().equalsIgnoreCase("WIFI");
                }
                return false;
            }
            f.warn("can not get the permisson of android.permission.INTERNET");
            return false;
        } catch (Object th) {
            f.e(th);
        }
    }

    private static synchronized Random h() {
        Random random;
        synchronized (k.class) {
            if (e == null) {
                e = new Random();
            }
            random = e;
        }
        return random;
    }

    public static boolean h(Context context) {
        try {
            if (a(context, MsgConstant.PERMISSION_INTERNET) && a(context, MsgConstant.PERMISSION_ACCESS_NETWORK_STATE)) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager != null) {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                        return true;
                    }
                    f.w("Network error");
                    return false;
                }
                return false;
            }
            f.warn("can not get the permisson of android.permission.INTERNET");
            return false;
        } catch (Throwable th) {
        }
    }

    private static long i() {
        long j = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            j = (long) (Integer.valueOf(bufferedReader.readLine().split("\\s+")[1]).intValue() * 1024);
            bufferedReader.close();
            return j;
        } catch (IOException e) {
            return j;
        }
    }

    public static String i(Context context) {
        if (b != null) {
            return b;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            if (applicationInfo != null) {
                String string = applicationInfo.metaData.getString("TA_APPKEY");
                if (string != null) {
                    b = string;
                    return string;
                }
                f.w("Could not read APPKEY meta-data from AndroidManifest.xml");
            }
        } catch (Throwable th) {
            f.w("Could not read APPKEY meta-data from AndroidManifest.xml");
        }
        return null;
    }

    public static String j(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            if (applicationInfo != null) {
                Object obj = applicationInfo.metaData.get("InstallChannel");
                if (obj != null) {
                    return obj.toString();
                }
                f.w("Could not read InstallChannel meta-data from AndroidManifest.xml");
            }
        } catch (Throwable th) {
            f.e((Object) "Could not read InstallChannel meta-data from AndroidManifest.xml");
        }
        return null;
    }

    public static String k(Context context) {
        return context == null ? null : context.getClass().getName();
    }

    public static String l(Context context) {
        try {
            if (a(context, MsgConstant.PERMISSION_READ_PHONE_STATE)) {
                String str = a.d;
                if (o(context)) {
                    str = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                }
                if (str != null) {
                    return str;
                }
            }
            f.e((Object) "Could not get permission of android.permission.READ_PHONE_STATE");
        } catch (Object th) {
            f.e(th);
        }
        return null;
    }

    public static String m(Context context) {
        try {
            if (!a(context, MsgConstant.PERMISSION_READ_PHONE_STATE)) {
                f.e((Object) "Could not get permission of android.permission.READ_PHONE_STATE");
                return null;
            } else if (!o(context)) {
                return null;
            } else {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                return telephonyManager != null ? telephonyManager.getSimOperator() : null;
            }
        } catch (Object th) {
            f.e(th);
            return null;
        }
    }

    public static String n(Context context) {
        String str = a.d;
        try {
            String str2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            if (str2 != null) {
                return str2;
            }
            try {
                return a.d;
            } catch (Throwable th) {
                Object th2 = th;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            str2 = str;
            Throwable th5 = th4;
            f.e(th2);
            return str2;
        }
    }

    public static boolean o(Context context) {
        return context.getPackageManager().checkPermission(MsgConstant.PERMISSION_READ_PHONE_STATE, context.getPackageName()) == 0;
    }

    public static String p(Context context) {
        try {
            if (a(context, MsgConstant.PERMISSION_INTERNET) && a(context, MsgConstant.PERMISSION_ACCESS_NETWORK_STATE)) {
                String typeName;
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                    typeName = activeNetworkInfo.getTypeName();
                    String extraInfo = activeNetworkInfo.getExtraInfo();
                    if (typeName != null) {
                        if (typeName.equalsIgnoreCase("WIFI")) {
                            return "WIFI";
                        }
                        if (typeName.equalsIgnoreCase("MOBILE")) {
                            return extraInfo != null ? extraInfo : "MOBILE";
                        } else {
                            if (extraInfo != null) {
                                return extraInfo;
                            }
                            return typeName;
                        }
                    }
                }
                typeName = null;
                return typeName;
            }
            f.e((Object) "can not get the permission of android.permission.ACCESS_WIFI_STATE");
            return null;
        } catch (Object th) {
            f.e(th);
            return null;
        }
    }

    public static Integer q(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                return Integer.valueOf(telephonyManager.getNetworkType());
            }
        } catch (Throwable th) {
        }
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String r(android.content.Context r5) {
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.stat.common.k.r(android.content.Context):java.lang.String");
        /*
        r1 = "";
        r0 = r5.getPackageManager();	 Catch:{ Throwable -> 0x001e }
        r2 = r5.getPackageName();	 Catch:{ Throwable -> 0x001e }
        r3 = 0;
        r0 = r0.getPackageInfo(r2, r3);	 Catch:{ Throwable -> 0x001e }
        r0 = r0.versionName;	 Catch:{ Throwable -> 0x001e }
        if (r0 == 0) goto L_0x001a;
    L_0x0014:
        r1 = r0.length();	 Catch:{ Throwable -> 0x0028 }
        if (r1 != 0) goto L_0x001d;
    L_0x001a:
        r0 = "unknown";
    L_0x001d:
        return r0;
    L_0x001e:
        r0 = move-exception;
        r4 = r0;
        r0 = r1;
        r1 = r4;
    L_0x0022:
        r2 = f;
        r2.e(r1);
        goto L_0x001d;
    L_0x0028:
        r1 = move-exception;
        goto L_0x0022;
        */
    }

    public static int s(Context context) {
        try {
            if (o.a()) {
                return 1;
            }
        } catch (Object th) {
            f.e(th);
        }
        return 0;
    }

    public static String t(Context context) {
        try {
            if (a(context, MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE)) {
                String externalStorageState = Environment.getExternalStorageState();
                if (externalStorageState == null || !externalStorageState.equals("mounted")) {
                    return null;
                }
                externalStorageState = Environment.getExternalStorageDirectory().getPath();
                if (externalStorageState == null) {
                    return null;
                }
                StatFs statFs = new StatFs(externalStorageState);
                long blockCount = (((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize())) / 1000000;
                return String.valueOf((((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks())) / 1000000) + "/" + String.valueOf(blockCount);
            }
            f.warn("can not get the permission of android.permission.WRITE_EXTERNAL_STORAGE");
            return null;
        } catch (Object th) {
            f.e(th);
            return null;
        }
    }

    static String u(Context context) {
        try {
            int myPid = Process.myPid();
            for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == myPid) {
                    return runningAppProcessInfo.processName;
                }
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public static String v(Context context) {
        return b(context, StatConstants.a);
    }

    public static synchronized Integer w(Context context) {
        Integer valueOf;
        int i = 0;
        synchronized (k.class) {
            try {
                int a = p.a(context, "MTA_EVENT_INDEX", 0);
                if (a < 2147483646) {
                    i = a;
                }
                p.b(context, "MTA_EVENT_INDEX", i + 1);
            } catch (Object th) {
                f.e(th);
            }
            valueOf = Integer.valueOf(i + 1);
        }
        return valueOf;
    }

    public static String x(Context context) {
        return String.valueOf(G(context) / 1000000) + "/" + String.valueOf(i() / 1000000);
    }

    public static synchronized l y(Context context) {
        l lVar;
        synchronized (k.class) {
            if (h == null) {
                h = new l();
            }
            lVar = h;
        }
        return lVar;
    }

    public static JSONObject z(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            y(context);
            int b = l.b();
            if (b > 0) {
                jSONObject.put("fx", b / 1000000);
            }
            y(context);
            b = l.c();
            if (b > 0) {
                jSONObject.put("fn", b / 1000000);
            }
            y(context);
            b = l.a();
            if (b > 0) {
                jSONObject.put(IXAdRequestInfo.AD_COUNT, b);
            }
            y(context);
            String d = l.d();
            if (d != null && d.length() == 0) {
                y(context);
                jSONObject.put("na", l.d());
            }
        } catch (Exception e) {
            f.e(e);
        }
        return jSONObject;
    }
}
