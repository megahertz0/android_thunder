package anet.channel.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.security.ISecurity;
import anet.channel.security.c;
import anet.channel.statist.ExceptionStatistic;
import com.ta.utdid2.device.UTDevice;
import com.umeng.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.reflect.Method;
import java.util.List;

// compiled from: Taobao
public class Utils {
    private static final String SSL_TIKET_KEY2 = "accs_ssl_key2_";
    private static final String TAG = "awcn.Utils";
    public static int accsVersion;

    static {
        accsVersion = 0;
    }

    public static String getDeviceId(Context context) {
        return UTDevice.getUtdid(context);
    }

    public static boolean isDebugMode(Context context) {
        return false;
    }

    public static String getAppSign(Context context, String str, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str)) {
            ALog.e(TAG, "getAppSign appkey null", null, new Object[0]);
            return null;
        }
        String signAuth;
        try {
            signAuth = c.a().signAuth(context, ISecurity.SIGN_ALGORITHM_HMAC_SHA1, str, str2, GlobalAppRuntimeInfo.getAuthCode());
        } catch (Throwable th) {
            signAuth = null;
        }
        return signAuth;
    }

    public static byte[] staticBinarySafeDecryptNoB64(Context context, String str, byte[] bArr) {
        byte[] staticDecrypt;
        try {
            staticDecrypt = c.a().staticDecrypt(context, ISecurity.CIPHER_ALGORITHM_AES128, str, bArr, GlobalAppRuntimeInfo.getAuthCode());
            if (staticDecrypt != null) {
                try {
                    if (ALog.isPrintLog(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                        ALog.i("staticBinarySafeDecryptNoB64", null, "decrypt", new String(staticDecrypt));
                    }
                } catch (Throwable th) {
                    Throwable th2 = th;
                    ALog.e(TAG, "staticBinarySafeDecryptNoB64", null, th2, new Object[0]);
                    return staticDecrypt;
                }
            }
        } catch (Throwable th3) {
            th2 = th3;
            staticDecrypt = null;
            ALog.e(TAG, "staticBinarySafeDecryptNoB64", null, th2, new Object[0]);
            return staticDecrypt;
        }
        return staticDecrypt;
    }

    public static String getImsi(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
        } catch (Throwable th) {
            return null;
        }
    }

    public static String getOperator(Context context) {
        String imsi = getImsi(context);
        String str = "null";
        return (imsi == null || imsi.length() <= 5) ? str : imsi.substring(0, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
    }

    public static String getMainProcessName(Context context) {
        String str = a.d;
        if (context == null) {
            return str;
        }
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.processName;
        } catch (NameNotFoundException e) {
            return str;
        }
    }

    public static String getProcessName(Context context, int i) {
        String str = a.d;
        try {
            String str2;
            List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
            if (runningAppProcesses == null || runningAppProcesses.size() <= 0) {
                AppMonitor.getInstance().commitStat(new ExceptionStatistic(-108, ErrorConstant.formatMsg(ErrorConstant.ERROR_GET_PROCESS_NULL, new StringBuilder("BuildVersion=").append(String.valueOf(VERSION.SDK_INT)).toString()), "rt"));
                str2 = str;
                return TextUtils.isEmpty(str2) ? getProcessNameNew(i) : str2;
            } else {
                for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    if (runningAppProcessInfo.pid == i) {
                        str2 = runningAppProcessInfo.processName;
                        break;
                    }
                }
                str2 = str;
                if (TextUtils.isEmpty(str2)) {
                }
            }
        } catch (Exception e) {
            AppMonitor.getInstance().commitStat(new ExceptionStatistic(-108, e.toString(), "rt"));
            str2 = str;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getProcessNameNew(int r9) {
        throw new UnsupportedOperationException("Method not decompiled: anet.channel.util.Utils.getProcessNameNew(int):java.lang.String");
        /*
        r8 = 0;
        r2 = 0;
        r0 = new java.lang.StringBuilder;
        r1 = "ps  |  grep  ";
        r0.<init>(r1);
        r0 = r0.append(r9);
        r0 = r0.toString();
        r1 = java.lang.Runtime.getRuntime();	 Catch:{ Exception -> 0x00bc, all -> 0x00e4 }
        r3 = "sh";
        r4 = r1.exec(r3);	 Catch:{ Exception -> 0x00bc, all -> 0x00e4 }
        r3 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x00bc, all -> 0x00e4 }
        r1 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x00bc, all -> 0x00e4 }
        r5 = r4.getInputStream();	 Catch:{ Exception -> 0x00bc, all -> 0x00e4 }
        r1.<init>(r5);	 Catch:{ Exception -> 0x00bc, all -> 0x00e4 }
        r3.<init>(r1);	 Catch:{ Exception -> 0x00bc, all -> 0x00e4 }
        r1 = new java.io.DataOutputStream;	 Catch:{ Exception -> 0x0104, all -> 0x00ff }
        r5 = r4.getOutputStream();	 Catch:{ Exception -> 0x0104, all -> 0x00ff }
        r1.<init>(r5);	 Catch:{ Exception -> 0x0104, all -> 0x00ff }
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0107 }
        r5.<init>();	 Catch:{ Exception -> 0x0107 }
        r0 = r5.append(r0);	 Catch:{ Exception -> 0x0107 }
        r5 = "  &\n";
        r0 = r0.append(r5);	 Catch:{ Exception -> 0x0107 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0107 }
        r1.writeBytes(r0);	 Catch:{ Exception -> 0x0107 }
        r1.flush();	 Catch:{ Exception -> 0x0107 }
        r0 = "exit\n";
        r1.writeBytes(r0);	 Catch:{ Exception -> 0x0107 }
        r4.waitFor();	 Catch:{ Exception -> 0x0107 }
    L_0x0057:
        r0 = r3.readLine();	 Catch:{ Exception -> 0x0107 }
        if (r0 == 0) goto L_0x00a5;
    L_0x005d:
        r4 = "\\s+";
        r5 = "  ";
        r0 = r0.replaceAll(r4, r5);	 Catch:{ Exception -> 0x0107 }
        r4 = "  ";
        r0 = r0.split(r4);	 Catch:{ Exception -> 0x0107 }
        r4 = r0.length;	 Catch:{ Exception -> 0x0107 }
        r5 = 9;
        if (r4 < r5) goto L_0x0057;
    L_0x0073:
        r4 = 1;
        r4 = r0[r4];	 Catch:{ Exception -> 0x0107 }
        r4 = android.text.TextUtils.isEmpty(r4);	 Catch:{ Exception -> 0x0107 }
        if (r4 != 0) goto L_0x0057;
    L_0x007c:
        r4 = 1;
        r4 = r0[r4];	 Catch:{ Exception -> 0x0107 }
        r4 = r4.trim();	 Catch:{ Exception -> 0x0107 }
        r5 = java.lang.String.valueOf(r9);	 Catch:{ Exception -> 0x0107 }
        r4 = r4.equals(r5);	 Catch:{ Exception -> 0x0107 }
        if (r4 == 0) goto L_0x0057;
    L_0x008d:
        r4 = 8;
        r0 = r0[r4];	 Catch:{ Exception -> 0x0107 }
        r3.close();	 Catch:{ IOException -> 0x0098 }
        r1.close();	 Catch:{ IOException -> 0x0098 }
    L_0x0097:
        return r0;
    L_0x0098:
        r1 = move-exception;
        r3 = "awcn.Utils";
        r4 = "getProcessNameNew ";
        r5 = new java.lang.Object[r8];
        anet.channel.util.ALog.e(r3, r4, r2, r1, r5);
        goto L_0x0097;
    L_0x00a5:
        r3.close();	 Catch:{ IOException -> 0x00af }
        r1.close();	 Catch:{ IOException -> 0x00af }
    L_0x00ab:
        r0 = "";
        goto L_0x0097;
    L_0x00af:
        r0 = move-exception;
        r1 = "awcn.Utils";
        r3 = "getProcessNameNew ";
        r4 = new java.lang.Object[r8];
        anet.channel.util.ALog.e(r1, r3, r2, r0, r4);
        goto L_0x00ab;
    L_0x00bc:
        r0 = move-exception;
        r1 = r2;
        r3 = r2;
    L_0x00bf:
        r4 = "awcn.Utils";
        r5 = "getProcessNameNew ";
        r6 = 0;
        r7 = 0;
        r7 = new java.lang.Object[r7];	 Catch:{ all -> 0x0102 }
        anet.channel.util.ALog.e(r4, r5, r6, r0, r7);	 Catch:{ all -> 0x0102 }
        if (r3 == 0) goto L_0x00d1;
    L_0x00ce:
        r3.close();	 Catch:{ IOException -> 0x00d7 }
    L_0x00d1:
        if (r1 == 0) goto L_0x00ab;
    L_0x00d3:
        r1.close();	 Catch:{ IOException -> 0x00d7 }
        goto L_0x00ab;
    L_0x00d7:
        r0 = move-exception;
        r1 = "awcn.Utils";
        r3 = "getProcessNameNew ";
        r4 = new java.lang.Object[r8];
        anet.channel.util.ALog.e(r1, r3, r2, r0, r4);
        goto L_0x00ab;
    L_0x00e4:
        r0 = move-exception;
        r1 = r2;
        r3 = r2;
    L_0x00e7:
        if (r3 == 0) goto L_0x00ec;
    L_0x00e9:
        r3.close();	 Catch:{ IOException -> 0x00f2 }
    L_0x00ec:
        if (r1 == 0) goto L_0x00f1;
    L_0x00ee:
        r1.close();	 Catch:{ IOException -> 0x00f2 }
    L_0x00f1:
        throw r0;
    L_0x00f2:
        r1 = move-exception;
        r3 = "awcn.Utils";
        r4 = "getProcessNameNew ";
        r5 = new java.lang.Object[r8];
        anet.channel.util.ALog.e(r3, r4, r2, r1, r5);
        goto L_0x00f1;
    L_0x00ff:
        r0 = move-exception;
        r1 = r2;
        goto L_0x00e7;
    L_0x0102:
        r0 = move-exception;
        goto L_0x00e7;
    L_0x0104:
        r0 = move-exception;
        r1 = r2;
        goto L_0x00bf;
    L_0x0107:
        r0 = move-exception;
        goto L_0x00bf;
        */
    }

    public static String getAppVersion(Context context) {
        String str = a.d;
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public static int getAccsVersion() {
        if (accsVersion != 0) {
            return accsVersion;
        }
        try {
            accsVersion = ((Integer) Utils.class.getClassLoader().loadClass(com.taobao.accs.utl.a.channelService).getDeclaredField("SDK_VERSION_CODE").get(null)).intValue();
        } catch (Throwable e) {
            ALog.w(TAG, "getAccsVersion", null, e, new Object[0]);
        }
        return accsVersion;
    }

    public static int SecurityGuardPutSslTicket2(Context context, String str, byte[] bArr) {
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        try {
            if (!c.a().dynamicPutBytes(context, new StringBuilder(SSL_TIKET_KEY2).append(str).toString(), bArr)) {
                i = -1;
            }
        } catch (Throwable th) {
            ALog.e(TAG, "SecurityGuardPutSslTicket2", null, th, new Object[0]);
            i = -1;
        }
        return i;
    }

    public static byte[] SecurityGuardGetSslTicket2(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            ALog.i(TAG, "get sslticket host is null", null, new Object[0]);
            return null;
        }
        try {
            return c.a().dynamicGetBytes(context, new StringBuilder(SSL_TIKET_KEY2).append(str).toString());
        } catch (Throwable th) {
            ALog.e(TAG, "SecurityGuardGetSslTicket2", null, th, new Object[0]);
            return null;
        }
    }

    public static String getStackMsg(Throwable th) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null && stackTrace.length > 0) {
                for (int i = 0; i < stackTrace.length; i++) {
                    stringBuffer.append(stackTrace[i].toString() + "\n");
                }
            }
        } catch (Throwable e) {
            ALog.e(TAG, "getStackMsg", null, e, new Object[0]);
        }
        return stringBuffer.toString();
    }

    public static Object invokeStaticMethodThrowException(String str, String str2, Class<?>[] clsArr, Object... objArr) throws Exception {
        if (str == null || str2 == null) {
            return null;
        }
        Method declaredMethod;
        Class forName = Class.forName(str);
        if (clsArr != null) {
            declaredMethod = forName.getDeclaredMethod(str2, clsArr);
        } else {
            declaredMethod = forName.getDeclaredMethod(str2, new Class[0]);
        }
        if (declaredMethod == null) {
            return null;
        }
        declaredMethod.setAccessible(true);
        return objArr != null ? declaredMethod.invoke(forName, objArr) : declaredMethod.invoke(forName, new Object[0]);
    }
}
