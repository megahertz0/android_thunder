package com.tencent.bugly.crashreport.common.info;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Process;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.taobao.accs.data.Message;
import com.tencent.bugly.proguard.w;
import com.umeng.message.MsgConstant;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.tdlive.R;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// compiled from: BUGLY
public class AppInfo {
    private static ActivityManager a;

    static {
        "@buglyAllChannel@".split(MiPushClient.ACCEPT_TIME_SEPARATOR);
        "@buglyAllChannelPriority@".split(MiPushClient.ACCEPT_TIME_SEPARATOR);
    }

    public static String a(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return context.getPackageName();
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            return MsgConstant.KEY_FAIL;
        }
    }

    public static PackageInfo b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(a(context), 0);
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static String a(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return null;
        }
        try {
            return packageInfo.versionName;
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            return MsgConstant.KEY_FAIL;
        }
    }

    public static String b(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return null;
        }
        try {
            return Integer.toString(packageInfo.versionCode);
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            return MsgConstant.KEY_FAIL;
        }
    }

    public static boolean a(Context context, String str) {
        if (context == null || str == null || str.trim().length() <= 0) {
            return false;
        }
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), Message.FLAG_ERR).requestedPermissions;
            if (strArr == null) {
                return false;
            }
            int length = strArr.length;
            for (int i = 0; i < length; i++) {
                if (str.equals(strArr[i])) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            if (w.a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    public static String a(int i) {
        String substring;
        Throwable th;
        int i2 = 0;
        int i3 = 0;
        try {
            FileReader fileReader = new FileReader(new StringBuilder("/proc/").append(i).append("/cmdline").toString());
            i3 = AccessibilityNodeInfoCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY;
            try {
                char[] cArr = new char[512];
                fileReader.read(cArr);
                while (i2 < 512 && cArr[i2] != '\u0000') {
                    i2++;
                }
                substring = new String(cArr).substring(0, i2);
                try {
                    fileReader.close();
                } catch (Throwable th2) {
                }
            } catch (Throwable th3) {
                th = th3;
                if (!w.a(th)) {
                    th.printStackTrace();
                }
                substring = String.valueOf(i);
                if (fileReader != null) {
                    fileReader.close();
                }
                return substring;
            }
        } catch (Throwable th4) {
            th = th4;
            fileReader = i3;
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (Throwable th5) {
                }
            }
            throw th;
        }
        return substring;
    }

    public static String c(Context context) {
        try {
            return a(Process.myPid());
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static Map<String, String> d(Context context) {
        if (context == null) {
            return null;
        }
        try {
            HashMap hashMap;
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            if (applicationInfo.metaData != null) {
                hashMap = new HashMap();
                Object obj = applicationInfo.metaData.get("BUGLY_DISABLE");
                if (obj != null) {
                    hashMap.put("BUGLY_DISABLE", obj.toString());
                }
                obj = applicationInfo.metaData.get("BUGLY_APPID");
                if (obj != null) {
                    hashMap.put("BUGLY_APPID", obj.toString());
                }
                obj = applicationInfo.metaData.get("BUGLY_APP_CHANNEL");
                if (obj != null) {
                    hashMap.put("BUGLY_APP_CHANNEL", obj.toString());
                }
                obj = applicationInfo.metaData.get("BUGLY_APP_VERSION");
                if (obj != null) {
                    hashMap.put("BUGLY_APP_VERSION", obj.toString());
                }
                obj = applicationInfo.metaData.get("BUGLY_ENABLE_DEBUG");
                if (obj != null) {
                    hashMap.put("BUGLY_ENABLE_DEBUG", obj.toString());
                }
                Object obj2 = applicationInfo.metaData.get("com.tencent.rdm.uuid");
                if (obj2 != null) {
                    hashMap.put("com.tencent.rdm.uuid", obj2.toString());
                }
            } else {
                hashMap = null;
            }
            return hashMap;
        } catch (Throwable th) {
            if (w.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    public static List<String> a(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        try {
            String str = (String) map.get("BUGLY_DISABLE");
            if (str == null || str.length() == 0) {
                return null;
            }
            String[] split = str.split(MiPushClient.ACCEPT_TIME_SEPARATOR);
            for (int i = 0; i < split.length; i++) {
                split[i] = split[i].trim();
            }
            return Arrays.asList(split);
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(byte[] r5) {
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.AppInfo.a(byte[]):java.lang.String");
        /*
        r1 = 0;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        if (r5 == 0) goto L_0x00e1;
    L_0x0008:
        r0 = r5.length;
        if (r0 <= 0) goto L_0x00e1;
    L_0x000b:
        r0 = "X.509";
        r0 = java.security.cert.CertificateFactory.getInstance(r0);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        if (r0 != 0) goto L_0x0016;
    L_0x0014:
        r0 = r1;
    L_0x0015:
        return r0;
    L_0x0016:
        r3 = new java.io.ByteArrayInputStream;	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        r3.<init>(r5);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        r0 = r0.generateCertificate(r3);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        r0 = (java.security.cert.X509Certificate) r0;	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        if (r0 != 0) goto L_0x0025;
    L_0x0023:
        r0 = r1;
        goto L_0x0015;
    L_0x0025:
        r1 = "Issuer|";
        r2.append(r1);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        r1 = r0.getIssuerDN();	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        if (r1 == 0) goto L_0x00ec;
    L_0x0031:
        r3 = r1.toString();	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        r2.append(r3);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
    L_0x0038:
        r3 = "\n";
        r2.append(r3);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        r3 = "SerialNumber|";
        r2.append(r3);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        r3 = r0.getSerialNumber();	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        if (r1 == 0) goto L_0x00ff;
    L_0x004a:
        r4 = 16;
        r3 = r3.toString(r4);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        r2.append(r3);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
    L_0x0053:
        r3 = "\n";
        r2.append(r3);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        r3 = "NotBefore|";
        r2.append(r3);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        r3 = r0.getNotBefore();	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        if (r1 == 0) goto L_0x0112;
    L_0x0065:
        r3 = r3.toString();	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        r2.append(r3);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
    L_0x006c:
        r3 = "\n";
        r2.append(r3);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        r3 = "NotAfter|";
        r2.append(r3);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        r3 = r0.getNotAfter();	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        if (r1 == 0) goto L_0x011a;
    L_0x007e:
        r1 = r3.toString();	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        r2.append(r1);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
    L_0x0085:
        r1 = "\n";
        r2.append(r1);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        r1 = "SHA1|";
        r2.append(r1);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        r1 = "SHA1";
        r1 = java.security.MessageDigest.getInstance(r1);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        r3 = r0.getEncoded();	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        r1 = r1.digest(r3);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        r1 = com.tencent.bugly.proguard.a.b(r1);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        if (r1 == 0) goto L_0x0122;
    L_0x00a6:
        r3 = r1.length();	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        if (r3 <= 0) goto L_0x0122;
    L_0x00ac:
        r1 = r1.toString();	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        r2.append(r1);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
    L_0x00b3:
        r1 = "\n";
        r2.append(r1);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        r1 = "MD5|";
        r2.append(r1);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        r1 = "MD5";
        r1 = java.security.MessageDigest.getInstance(r1);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        r0 = r0.getEncoded();	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        r0 = r1.digest(r0);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        r0 = com.tencent.bugly.proguard.a.b(r0);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        if (r0 == 0) goto L_0x0129;
    L_0x00d4:
        r1 = r0.length();	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        if (r1 <= 0) goto L_0x0129;
    L_0x00da:
        r0 = r0.toString();	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        r2.append(r0);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
    L_0x00e1:
        r0 = r2.length();
        if (r0 != 0) goto L_0x0130;
    L_0x00e7:
        r0 = "unknown";
        goto L_0x0015;
    L_0x00ec:
        r3 = "unknown";
        r2.append(r3);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        goto L_0x0038;
    L_0x00f4:
        r0 = move-exception;
        r1 = com.tencent.bugly.proguard.w.a(r0);
        if (r1 != 0) goto L_0x00e1;
    L_0x00fb:
        r0.printStackTrace();
        goto L_0x00e1;
    L_0x00ff:
        r3 = "unknown";
        r2.append(r3);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        goto L_0x0053;
    L_0x0107:
        r0 = move-exception;
        r1 = com.tencent.bugly.proguard.w.a(r0);
        if (r1 != 0) goto L_0x00e1;
    L_0x010e:
        r0.printStackTrace();
        goto L_0x00e1;
    L_0x0112:
        r3 = "unknown";
        r2.append(r3);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        goto L_0x006c;
    L_0x011a:
        r1 = "unknown";
        r2.append(r1);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        goto L_0x0085;
    L_0x0122:
        r1 = "unknown";
        r2.append(r1);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        goto L_0x00b3;
    L_0x0129:
        r0 = "unknown";
        r2.append(r0);	 Catch:{ CertificateException -> 0x00f4, Throwable -> 0x0107 }
        goto L_0x00e1;
    L_0x0130:
        r0 = r2.toString();
        goto L_0x0015;
        */
    }

    public static String e(Context context) {
        String a = a(context);
        if (a == null) {
            return null;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(a, R.styleable.AppCompatTheme_imageButtonStyle);
            if (packageInfo == null) {
                return null;
            }
            Signature[] signatureArr = packageInfo.signatures;
            return (signatureArr == null || signatureArr.length == 0) ? null : a(packageInfo.signatures[0].toByteArray());
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    public static boolean f(Context context) {
        if (context == null) {
            return false;
        }
        if (a == null) {
            a = (ActivityManager) context.getSystemService("activity");
        }
        try {
            MemoryInfo memoryInfo = new MemoryInfo();
            a.getMemoryInfo(memoryInfo);
            if (!memoryInfo.lowMemory) {
                return false;
            }
            w.c("Memory is low.", new Object[0]);
            return true;
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            return false;
        }
    }
}
