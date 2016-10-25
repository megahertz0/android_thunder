package com.tencent.bugly.crashreport.common.info;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.widget.AutoScrollHelper;
import android.telephony.TelephonyManager;
import com.taobao.accs.utl.UtilityImpl;
import com.tencent.bugly.proguard.w;
import com.umeng.a;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.common.SocializeConstants;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import org.android.spdy.SpdyAgent;

// compiled from: BUGLY
public final class b {
    private static String a;
    private static String b;

    public static String a() {
        try {
            return Build.MODEL;
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            return MsgConstant.KEY_FAIL;
        }
    }

    public static String b() {
        try {
            return VERSION.RELEASE;
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            return MsgConstant.KEY_FAIL;
        }
    }

    public static int c() {
        try {
            return VERSION.SDK_INT;
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            return -1;
        }
    }

    public static String a(Context context) {
        if (!AppInfo.a(context, MsgConstant.PERMISSION_READ_PHONE_STATE)) {
            w.d("no READ_PHONE_STATE permission to get IMEI", new Object[0]);
            return "null";
        } else if (context == null) {
            return null;
        } else {
            String deviceId;
            try {
                deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                if (deviceId == null) {
                    return deviceId;
                }
                try {
                    return deviceId.toLowerCase();
                } catch (Throwable th) {
                }
            } catch (Throwable th2) {
                deviceId = null;
                String str = w.a;
                return deviceId;
            }
        }
    }

    public static String b(Context context) {
        if (!AppInfo.a(context, MsgConstant.PERMISSION_READ_PHONE_STATE)) {
            w.d("no READ_PHONE_STATE permission to get IMSI", new Object[0]);
            return "null";
        } else if (context == null) {
            return null;
        } else {
            String subscriberId;
            try {
                subscriberId = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
                if (subscriberId == null) {
                    return subscriberId;
                }
                try {
                    return subscriberId.toLowerCase();
                } catch (Throwable th) {
                }
            } catch (Throwable th2) {
                subscriberId = null;
                String str = w.a;
                return subscriberId;
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String c(android.content.Context r4) {
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.c(android.content.Context):java.lang.String");
        /*
        r0 = "fail";
        if (r4 != 0) goto L_0x0006;
    L_0x0005:
        return r0;
    L_0x0006:
        r1 = r4.getContentResolver();	 Catch:{ Throwable -> 0x001c }
        r2 = "android_id";
        r1 = android.provider.Settings.Secure.getString(r1, r2);	 Catch:{ Throwable -> 0x001c }
        if (r1 != 0) goto L_0x0017;
    L_0x0013:
        r0 = "null";
        goto L_0x0005;
    L_0x0017:
        r0 = r1.toLowerCase();	 Catch:{ Throwable -> 0x0026 }
        goto L_0x0005;
    L_0x001c:
        r1 = move-exception;
    L_0x001d:
        r1 = com.tencent.bugly.proguard.w.a(r1);
        if (r1 != 0) goto L_0x0005;
    L_0x0023:
        r1 = com.tencent.bugly.proguard.w.a;
        goto L_0x0005;
    L_0x0026:
        r0 = move-exception;
        r3 = r0;
        r0 = r1;
        r1 = r3;
        goto L_0x001d;
        */
    }

    public static String d(Context context) {
        String str = MsgConstant.KEY_FAIL;
        if (context == null) {
            return str;
        }
        try {
            str = ((WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI)).getConnectionInfo().getMacAddress();
            return str == null ? "null" : str.toLowerCase();
        } catch (Throwable th) {
            Throwable th2 = th;
            r0 = str;
            r1 = th2;
            Throwable th3;
            if (w.a(th3)) {
                String str2;
                return str2;
            }
            th3.printStackTrace();
            return str2;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String p() {
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.p():java.lang.String");
        /*
        r6 = 2;
        r1 = 0;
        r0 = "/system/build.prop";
        r3 = new java.io.FileReader;	 Catch:{ Throwable -> 0x006a, all -> 0x0098 }
        r3.<init>(r0);	 Catch:{ Throwable -> 0x006a, all -> 0x0098 }
        r2 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x00c1, all -> 0x00bc }
        r0 = 2048; // 0x800 float:2.87E-42 double:1.0118E-320;
        r2.<init>(r3, r0);	 Catch:{ Throwable -> 0x00c1, all -> 0x00bc }
    L_0x0011:
        r0 = r2.readLine();	 Catch:{ Throwable -> 0x00c4 }
        if (r0 == 0) goto L_0x00c6;
    L_0x0017:
        r4 = "=";
        r5 = 2;
        r0 = r0.split(r4, r5);	 Catch:{ Throwable -> 0x00c4 }
        r4 = r0.length;	 Catch:{ Throwable -> 0x00c4 }
        if (r4 != r6) goto L_0x0011;
    L_0x0022:
        r4 = 0;
        r4 = r0[r4];	 Catch:{ Throwable -> 0x00c4 }
        r5 = "ro.product.cpu.abilist";
        r4 = r4.equals(r5);	 Catch:{ Throwable -> 0x00c4 }
        if (r4 == 0) goto L_0x0044;
    L_0x002e:
        r4 = 1;
        r0 = r0[r4];	 Catch:{ Throwable -> 0x00c4 }
    L_0x0031:
        if (r0 == 0) goto L_0x003d;
    L_0x0033:
        r4 = ",";
        r0 = r0.split(r4);	 Catch:{ Throwable -> 0x00c4 }
        r4 = 0;
        r0 = r0[r4];	 Catch:{ Throwable -> 0x00c4 }
    L_0x003d:
        r2.close();	 Catch:{ IOException -> 0x0054 }
    L_0x0040:
        r3.close();	 Catch:{ IOException -> 0x005f }
    L_0x0043:
        return r0;
    L_0x0044:
        r4 = 0;
        r4 = r0[r4];	 Catch:{ Throwable -> 0x00c4 }
        r5 = "ro.product.cpu.abi";
        r4 = r4.equals(r5);	 Catch:{ Throwable -> 0x00c4 }
        if (r4 == 0) goto L_0x0011;
    L_0x0050:
        r4 = 1;
        r0 = r0[r4];	 Catch:{ Throwable -> 0x00c4 }
        goto L_0x0031;
    L_0x0054:
        r1 = move-exception;
        r2 = com.tencent.bugly.proguard.w.a(r1);
        if (r2 != 0) goto L_0x0040;
    L_0x005b:
        r1.printStackTrace();
        goto L_0x0040;
    L_0x005f:
        r1 = move-exception;
        r2 = com.tencent.bugly.proguard.w.a(r1);
        if (r2 != 0) goto L_0x0043;
    L_0x0066:
        r1.printStackTrace();
        goto L_0x0043;
    L_0x006a:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
    L_0x006d:
        r4 = com.tencent.bugly.proguard.w.a(r0);	 Catch:{ all -> 0x00bf }
        if (r4 != 0) goto L_0x0076;
    L_0x0073:
        r0.printStackTrace();	 Catch:{ all -> 0x00bf }
    L_0x0076:
        if (r2 == 0) goto L_0x007b;
    L_0x0078:
        r2.close();	 Catch:{ IOException -> 0x0082 }
    L_0x007b:
        if (r3 == 0) goto L_0x0080;
    L_0x007d:
        r3.close();	 Catch:{ IOException -> 0x008d }
    L_0x0080:
        r0 = r1;
        goto L_0x0043;
    L_0x0082:
        r0 = move-exception;
        r2 = com.tencent.bugly.proguard.w.a(r0);
        if (r2 != 0) goto L_0x007b;
    L_0x0089:
        r0.printStackTrace();
        goto L_0x007b;
    L_0x008d:
        r0 = move-exception;
        r2 = com.tencent.bugly.proguard.w.a(r0);
        if (r2 != 0) goto L_0x0080;
    L_0x0094:
        r0.printStackTrace();
        goto L_0x0080;
    L_0x0098:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
    L_0x009b:
        if (r2 == 0) goto L_0x00a0;
    L_0x009d:
        r2.close();	 Catch:{ IOException -> 0x00a6 }
    L_0x00a0:
        if (r3 == 0) goto L_0x00a5;
    L_0x00a2:
        r3.close();	 Catch:{ IOException -> 0x00b1 }
    L_0x00a5:
        throw r0;
    L_0x00a6:
        r1 = move-exception;
        r2 = com.tencent.bugly.proguard.w.a(r1);
        if (r2 != 0) goto L_0x00a0;
    L_0x00ad:
        r1.printStackTrace();
        goto L_0x00a0;
    L_0x00b1:
        r1 = move-exception;
        r2 = com.tencent.bugly.proguard.w.a(r1);
        if (r2 != 0) goto L_0x00a5;
    L_0x00b8:
        r1.printStackTrace();
        goto L_0x00a5;
    L_0x00bc:
        r0 = move-exception;
        r2 = r1;
        goto L_0x009b;
    L_0x00bf:
        r0 = move-exception;
        goto L_0x009b;
    L_0x00c1:
        r0 = move-exception;
        r2 = r1;
        goto L_0x006d;
    L_0x00c4:
        r0 = move-exception;
        goto L_0x006d;
    L_0x00c6:
        r0 = r1;
        goto L_0x0031;
        */
    }

    public static String d() {
        try {
            String p = p();
            return p == null ? System.getProperty("os.arch") : p;
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            return MsgConstant.KEY_FAIL;
        }
    }

    public static long e() {
        long j = -1;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            j = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
            return j;
        } catch (Throwable th) {
            if (w.a(th)) {
                return j;
            }
            th.printStackTrace();
            return j;
        }
    }

    public static long f() {
        long j = -1;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            j = ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
            return j;
        } catch (Throwable th) {
            if (w.a(th)) {
                return j;
            }
            th.printStackTrace();
            return j;
        }
    }

    public static long g() {
        Throwable th;
        FileReader fileReader;
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2;
            FileReader fileReader2 = new FileReader("/proc/meminfo");
            try {
                bufferedReader2 = new BufferedReader(fileReader2, 2048);
                try {
                    long parseLong = Long.parseLong(bufferedReader2.readLine().split(":\\s+", XZBDevice.DOWNLOAD_LIST_RECYCLE)[1].toLowerCase().replace("kb", a.d).trim()) << 10;
                    try {
                        bufferedReader2.close();
                    } catch (Throwable e) {
                        if (!w.a(e)) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        fileReader2.close();
                        return parseLong;
                    } catch (Throwable e2) {
                        if (w.a(e2)) {
                            return parseLong;
                        }
                        e2.printStackTrace();
                        return parseLong;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    if (fileReader2 != null) {
                        fileReader2.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedReader2 = null;
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (fileReader2 != null) {
                    fileReader2.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader2 = null;
            fileReader2 = null;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (Throwable e3) {
                    if (!w.a(e3)) {
                        e3.printStackTrace();
                    }
                }
            }
            if (fileReader2 != null) {
                try {
                    fileReader2.close();
                } catch (Throwable e32) {
                    if (!w.a(e32)) {
                        e32.printStackTrace();
                    }
                }
            }
            throw th;
        }
    }

    public static long h() {
        BufferedReader bufferedReader;
        Throwable th;
        BufferedReader bufferedReader2 = null;
        try {
            FileReader fileReader = new FileReader("/proc/meminfo");
            try {
                bufferedReader = new BufferedReader(fileReader, 2048);
                try {
                    bufferedReader.readLine();
                    long parseLong = ((Long.parseLong(bufferedReader.readLine().split(":\\s+", XZBDevice.DOWNLOAD_LIST_RECYCLE)[1].toLowerCase().replace("kb", a.d).trim()) << 10) + (Long.parseLong(bufferedReader.readLine().split(":\\s+", XZBDevice.DOWNLOAD_LIST_RECYCLE)[1].toLowerCase().replace("kb", a.d).trim()) << 10)) + (Long.parseLong(bufferedReader.readLine().split(":\\s+", XZBDevice.DOWNLOAD_LIST_RECYCLE)[1].toLowerCase().replace("kb", a.d).trim()) << 10);
                    try {
                        bufferedReader.close();
                    } catch (Throwable e) {
                        if (!w.a(e)) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        fileReader.close();
                        return parseLong;
                    } catch (Throwable e2) {
                        if (w.a(e2)) {
                            return parseLong;
                        }
                        e2.printStackTrace();
                        return parseLong;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (fileReader != null) {
                        fileReader.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
            fileReader = null;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Throwable e3) {
                    if (!w.a(e3)) {
                        e3.printStackTrace();
                    }
                }
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (Throwable e32) {
                    if (!w.a(e32)) {
                        e32.printStackTrace();
                    }
                }
            }
            throw th;
        }
    }

    public static long i() {
        if ((Environment.getExternalStorageState().equals("mounted") ? 1 : null) == null) {
            return 0;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getBlockSize()) * ((long) statFs.getBlockCount());
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            return -2;
        }
    }

    public static long j() {
        if ((Environment.getExternalStorageState().equals("mounted") ? 1 : null) == null) {
            return 0;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks());
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            return -2;
        }
    }

    public static String k() {
        String str = MsgConstant.KEY_FAIL;
        try {
            return Locale.getDefault().getCountry();
        } catch (Throwable th) {
            if (w.a(th)) {
                return str;
            }
            th.printStackTrace();
            return str;
        }
    }

    public static String l() {
        String str = MsgConstant.KEY_FAIL;
        try {
            return Build.BRAND;
        } catch (Throwable th) {
            if (w.a(th)) {
                return str;
            }
            th.printStackTrace();
            return str;
        }
    }

    public static String e(Context context) {
        String str = UtilityImpl.NET_TYPE_UNKNOWN;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            if (activeNetworkInfo.getType() == 1) {
                return "WIFI";
            }
            String toString;
            if (activeNetworkInfo.getType() == 0) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    int networkType = telephonyManager.getNetworkType();
                    switch (networkType) {
                        case SpdyAgent.ACCS_ONLINE_SERVER:
                            return "GPRS";
                        case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                            return "EDGE";
                        case XZBDevice.DOWNLOAD_LIST_FAILED:
                            return "UMTS";
                        case XZBDevice.DOWNLOAD_LIST_ALL:
                            return "CDMA";
                        case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                            return "EVDO_0";
                        case R.styleable.Toolbar_contentInsetEnd:
                            return "EVDO_A";
                        case R.styleable.Toolbar_contentInsetLeft:
                            return "1xRTT";
                        case XZBDevice.Wait:
                            return "HSDPA";
                        case XZBDevice.Pause:
                            return "HSUPA";
                        case XZBDevice.Stop:
                            return "HSPA";
                        case XZBDevice.Success:
                            return "iDen";
                        case XZBDevice.Fail:
                            return "EVDO_B";
                        case XZBDevice.Upload:
                            return "LTE";
                        case XZBDevice.Predownload:
                            return "eHRPD";
                        case XZBDevice.Delete:
                            return "HSPA+";
                        default:
                            toString = new StringBuilder("MOBILE(").append(networkType).append(SocializeConstants.OP_CLOSE_PAREN).toString();
                            return toString;
                    }
                }
            }
            toString = str;
            return toString;
        } catch (Throwable e) {
            if (w.a(e)) {
                return str;
            }
            e.printStackTrace();
            return str;
        }
    }

    public static String f(Context context) {
        Object obj = null;
        String a = com.tencent.bugly.proguard.a.a(context, "ro.miui.ui.version.name");
        if (a == null || a.trim().length() <= 0) {
            int i = 1;
        } else {
            Object obj2 = null;
        }
        if (obj2 == null && !a.equals(MsgConstant.KEY_FAIL)) {
            return new StringBuilder("XiaoMi/MIUI/").append(a).toString();
        }
        a = com.tencent.bugly.proguard.a.a(context, "ro.build.version.emui");
        if (a == null || a.trim().length() <= 0) {
            i = 1;
        } else {
            obj2 = null;
        }
        if (obj2 == null && !a.equals(MsgConstant.KEY_FAIL)) {
            return new StringBuilder("HuaWei/EMOTION/").append(a).toString();
        }
        a = com.tencent.bugly.proguard.a.a(context, "ro.lenovo.series");
        if (a == null || a.trim().length() <= 0) {
            i = 1;
        } else {
            obj2 = null;
        }
        if (obj2 != null || a.equals(MsgConstant.KEY_FAIL)) {
            a = com.tencent.bugly.proguard.a.a(context, "ro.build.nubia.rom.name");
            if (a == null || a.trim().length() <= 0) {
                i = 1;
            } else {
                obj2 = null;
            }
            if (obj2 == null && !a.equals(MsgConstant.KEY_FAIL)) {
                return new StringBuilder("Zte/NUBIA/").append(a).append("_").append(com.tencent.bugly.proguard.a.a(context, "ro.build.nubia.rom.code")).toString();
            }
            a = com.tencent.bugly.proguard.a.a(context, "ro.meizu.product.model");
            if (a == null || a.trim().length() <= 0) {
                i = 1;
            } else {
                obj2 = null;
            }
            if (obj2 == null && !a.equals(MsgConstant.KEY_FAIL)) {
                return new StringBuilder("Meizu/FLYME/").append(com.tencent.bugly.proguard.a.a(context, "ro.build.display.id")).toString();
            }
            a = com.tencent.bugly.proguard.a.a(context, "ro.build.version.opporom");
            if (a == null || a.trim().length() <= 0) {
                i = 1;
            } else {
                obj2 = null;
            }
            if (obj2 == null && !a.equals(MsgConstant.KEY_FAIL)) {
                return new StringBuilder("Oppo/COLOROS/").append(a).toString();
            }
            a = com.tencent.bugly.proguard.a.a(context, "ro.vivo.os.build.display.id");
            if (a == null || a.trim().length() <= 0) {
                i = 1;
            } else {
                obj2 = null;
            }
            if (obj2 == null && !a.equals(MsgConstant.KEY_FAIL)) {
                return new StringBuilder("vivo/FUNTOUCH/").append(a).toString();
            }
            a = com.tencent.bugly.proguard.a.a(context, "ro.aa.romver");
            if (a == null || a.trim().length() <= 0) {
                i = 1;
            } else {
                obj2 = null;
            }
            if (obj2 == null && !a.equals(MsgConstant.KEY_FAIL)) {
                return new StringBuilder("htc/").append(a).append("/").append(com.tencent.bugly.proguard.a.a(context, "ro.build.description")).toString();
            }
            a = com.tencent.bugly.proguard.a.a(context, "ro.lewa.version");
            if (a == null || a.trim().length() <= 0) {
                i = 1;
            } else {
                obj2 = null;
            }
            if (obj2 == null && !a.equals(MsgConstant.KEY_FAIL)) {
                return new StringBuilder("tcl/").append(a).append("/").append(com.tencent.bugly.proguard.a.a(context, "ro.build.display.id")).toString();
            }
            a = com.tencent.bugly.proguard.a.a(context, "ro.gn.gnromvernumber");
            if (a == null || a.trim().length() <= 0) {
                i = 1;
            } else {
                obj2 = null;
            }
            if (obj2 == null && !a.equals(MsgConstant.KEY_FAIL)) {
                return new StringBuilder("amigo/").append(a).append("/").append(com.tencent.bugly.proguard.a.a(context, "ro.build.display.id")).toString();
            }
            String a2 = com.tencent.bugly.proguard.a.a(context, "ro.build.tyd.kbstyle_version");
            if (a2 == null || a2.trim().length() <= 0) {
                int i2 = 1;
            }
            return (obj != null || a2.equals(MsgConstant.KEY_FAIL)) ? com.tencent.bugly.proguard.a.a(context, "ro.build.fingerprint") + "/" + com.tencent.bugly.proguard.a.a(context, "ro.build.rom.id") : new StringBuilder("dido/").append(a2).toString();
        } else {
            return new StringBuilder("Lenovo/VIBE/").append(com.tencent.bugly.proguard.a.a(context, "ro.build.version.incremental")).toString();
        }
    }

    public static String g(Context context) {
        return com.tencent.bugly.proguard.a.a(context, "ro.board.platform");
    }

    public static boolean h(Context context) {
        boolean exists;
        boolean z = Build.TAGS != null && Build.TAGS.contains("test-keys");
        try {
            exists = new File("/system/app/Superuser.apk").exists();
        } catch (Throwable th) {
            exists = false;
        }
        Boolean bool = null;
        ArrayList a = com.tencent.bugly.proguard.a.a(context, new String[]{"/system/bin/sh", "-c", "type su"});
        if (a != null && a.size() > 0) {
            Iterator it = a.iterator();
            while (it.hasNext()) {
                Boolean valueOf;
                String str = (String) it.next();
                w.c(str, new Object[0]);
                if (str.contains("not found")) {
                    valueOf = Boolean.valueOf(false);
                } else {
                    valueOf = bool;
                }
                bool = valueOf;
            }
            if (bool == null) {
                bool = Boolean.valueOf(true);
            }
        }
        return z || exists || Boolean.valueOf(bool == null ? false : bool.booleanValue()).booleanValue();
    }

    public static String m() {
        BufferedReader bufferedReader;
        Throwable th;
        String str = null;
        try {
            String readLine;
            StringBuilder stringBuilder = new StringBuilder();
            if (new File("/sys/block/mmcblk0/device/type").exists()) {
                bufferedReader = new BufferedReader(new FileReader("/sys/block/mmcblk0/device/type"));
                try {
                    readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        stringBuilder.append(readLine);
                    }
                    bufferedReader.close();
                    r3 = bufferedReader;
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    r3 = bufferedReader;
                    if (r3 != null) {
                        r3.close();
                    }
                    throw th3;
                }
            }
            r3 = null;
            try {
                stringBuilder.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
            } catch (Throwable th4) {
                th3 = th4;
                if (r3 != null) {
                    r3.close();
                }
                throw th3;
            }
            if (new File("/sys/block/mmcblk0/device/name").exists()) {
                bufferedReader = new BufferedReader(new FileReader("/sys/block/mmcblk0/device/name"));
                try {
                    readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        stringBuilder.append(readLine);
                    }
                    bufferedReader.close();
                    r3 = bufferedReader;
                } catch (Throwable th5) {
                    th3 = th5;
                    r3 = bufferedReader;
                    if (r3 != null) {
                        r3.close();
                    }
                    throw th3;
                }
            }
            try {
                stringBuilder.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                try {
                    str = stringBuilder.toString();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable th6) {
                            w.a(th6);
                        }
                    }
                } catch (Throwable th7) {
                    th6 = th7;
                    w.a(th6);
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    return str;
                }
            } catch (Throwable th42) {
                th3 = th42;
                if (r3 != null) {
                    r3.close();
                }
                throw th3;
            }
            if (new File("/sys/block/mmcblk0/device/cid").exists()) {
                bufferedReader = new BufferedReader(new FileReader("/sys/block/mmcblk0/device/cid"));
                try {
                    readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        stringBuilder.append(readLine);
                    }
                } catch (Throwable th8) {
                    th3 = th8;
                    r3 = bufferedReader;
                    if (r3 != null) {
                        r3.close();
                    }
                    throw th3;
                }
            }
            bufferedReader = r3;
        } catch (Throwable th62) {
            r3 = null;
            th3 = th62;
            if (r3 != null) {
                try {
                    r3.close();
                } catch (Throwable th622) {
                    w.a(th622);
                }
            }
            throw th3;
        }
        return str;
    }

    public static String i(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        String a = com.tencent.bugly.proguard.a.a(context, "ro.genymotion.version");
        if (a != null) {
            stringBuilder.append("ro.genymotion.version");
            stringBuilder.append("|");
            stringBuilder.append(a);
            stringBuilder.append("\n");
        }
        a = com.tencent.bugly.proguard.a.a(context, "androVM.vbox_dpi");
        if (a != null) {
            stringBuilder.append("androVM.vbox_dpi");
            stringBuilder.append("|");
            stringBuilder.append(a);
            stringBuilder.append("\n");
        }
        a = com.tencent.bugly.proguard.a.a(context, "qemu.sf.fake_camera");
        if (a != null) {
            stringBuilder.append("qemu.sf.fake_camera");
            stringBuilder.append("|");
            stringBuilder.append(a);
        }
        return stringBuilder.toString();
    }

    static {
        a = null;
        b = null;
    }

    public static String j(Context context) {
        Throwable th;
        StringBuilder stringBuilder = new StringBuilder();
        if (a == null) {
            a = com.tencent.bugly.proguard.a.a(context, "ro.secure");
        }
        if (a != null) {
            stringBuilder.append("ro.secure");
            stringBuilder.append("|");
            stringBuilder.append(a);
            stringBuilder.append("\n");
        }
        if (b == null) {
            b = com.tencent.bugly.proguard.a.a(context, "ro.debuggable");
        }
        if (b != null) {
            stringBuilder.append("ro.debuggable");
            stringBuilder.append("|");
            stringBuilder.append(b);
            stringBuilder.append("\n");
        }
        try {
            String readLine;
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/self/status"));
            do {
                try {
                    readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } while (!readLine.startsWith("TracerPid:"));
            if (readLine != null) {
                readLine = readLine.substring(XZBDevice.Stop).trim();
                stringBuilder.append("tracer_pid");
                stringBuilder.append("|");
                stringBuilder.append(readLine);
            }
            readLine = stringBuilder.toString();
            try {
                bufferedReader.close();
                return readLine;
            } catch (Throwable e) {
                w.a(e);
                return readLine;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Throwable e2) {
                    w.a(e2);
                }
            }
            throw th;
        }
    }

    public static String n() {
        BufferedReader bufferedReader;
        Throwable th;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader2 = null;
        try {
            String readLine;
            if (new File("/sys/class/power_supply/ac/online").exists()) {
                bufferedReader = new BufferedReader(new FileReader("/sys/class/power_supply/ac/online"));
                try {
                    readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        stringBuilder.append("ac_online");
                        stringBuilder.append("|");
                        stringBuilder.append(readLine);
                    }
                    bufferedReader.close();
                    bufferedReader2 = bufferedReader;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader2 = bufferedReader;
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    throw th;
                }
            }
            try {
                stringBuilder.append("\n");
            } catch (Throwable th3) {
                th = th3;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (Throwable e) {
                        w.a(e);
                    }
                }
                throw th;
            }
            if (new File("/sys/class/power_supply/usb/online").exists()) {
                bufferedReader = new BufferedReader(new FileReader("/sys/class/power_supply/usb/online"));
                try {
                    readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        stringBuilder.append("usb_online");
                        stringBuilder.append("|");
                        stringBuilder.append(readLine);
                    }
                    bufferedReader.close();
                    bufferedReader2 = bufferedReader;
                } catch (Throwable th4) {
                    th = th4;
                    bufferedReader2 = bufferedReader;
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    throw th;
                }
            }
            try {
                stringBuilder.append("\n");
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable th5) {
                        w.a(th5);
                    }
                }
            } catch (Throwable th32) {
                th5 = th32;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (Throwable e2) {
                        w.a(e2);
                    }
                }
                throw th5;
            }
            if (new File("/sys/class/power_supply/battery/capacity").exists()) {
                bufferedReader = new BufferedReader(new FileReader("/sys/class/power_supply/battery/capacity"));
                try {
                    readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        stringBuilder.append("battery_capacity");
                        stringBuilder.append("|");
                        stringBuilder.append(readLine);
                    }
                    bufferedReader.close();
                } catch (Throwable th6) {
                    th5 = th6;
                    bufferedReader2 = bufferedReader;
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    throw th5;
                }
            }
            bufferedReader = bufferedReader2;
        } catch (Throwable th322) {
            th5 = th322;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (Throwable e22) {
                    w.a(e22);
                }
            }
            throw th5;
        }
        return stringBuilder.toString();
    }

    public static String k(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        String a = com.tencent.bugly.proguard.a.a(context, "gsm.sim.state");
        if (a != null) {
            stringBuilder.append("gsm.sim.state");
            stringBuilder.append("|");
            stringBuilder.append(a);
        }
        stringBuilder.append("\n");
        a = com.tencent.bugly.proguard.a.a(context, "gsm.sim.state2");
        if (a != null) {
            stringBuilder.append("gsm.sim.state2");
            stringBuilder.append("|");
            stringBuilder.append(a);
        }
        return stringBuilder.toString();
    }

    public static long o() {
        Throwable e;
        float f = AutoScrollHelper.RELATIVE_UNSPECIFIED;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/uptime"));
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    f = ((float) (System.currentTimeMillis() / 1000)) - Float.parseFloat(readLine.split(" ")[0]);
                }
                try {
                    bufferedReader.close();
                } catch (Throwable e2) {
                    w.a(e2);
                }
            } catch (Throwable th) {
                e2 = th;
                w.a(e2);
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                return (long) f;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = null;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Throwable e22) {
                    w.a(e22);
                }
            }
            throw th;
        }
        return (long) f;
    }
}
