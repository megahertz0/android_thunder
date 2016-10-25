package com.taobao.accs.utl;

import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.text.TextUtils;
import com.ta.utdid2.device.UTDevice;
import com.taobao.accs.common.Constants;
import java.io.File;

// compiled from: Taobao
public class a {
    public static String a = null;
    public static final String channelService = "com.taobao.accs.ChannelService";
    public static final String msgService = "com.taobao.accs.data.MsgDistributeService";

    static {
        a = com.umeng.a.d;
    }

    public static boolean a(Context context) {
        try {
            Object obj = TextUtils.isEmpty(com.taobao.accs.client.a.d) ? context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.processName : com.taobao.accs.client.a.d;
            Object a = a(context, Process.myPid());
            return (TextUtils.isEmpty(obj) || TextUtils.isEmpty(a)) ? true : a.equalsIgnoreCase(obj);
        } catch (Throwable th) {
            ALog.e("AdapterUtilityImpl", "isMainProcess", th, new Object[0]);
            return true;
        }
    }

    public static String a(Context context, int i) {
        if (com.taobao.accs.client.a.f != null) {
            return com.taobao.accs.client.a.f.getCurrProcessName();
        }
        String str = com.umeng.a.d;
        for (RunningAppProcessInfo runningAppProcessInfo : com.taobao.accs.client.a.a(context).b().getRunningAppProcesses()) {
            try {
                String str2;
                if (runningAppProcessInfo.pid == i) {
                    str2 = runningAppProcessInfo.processName;
                } else {
                    str2 = str;
                }
                str = str2;
            } catch (Exception e) {
            }
        }
        return str;
    }

    public static long a() {
        try {
            File dataDirectory = Environment.getDataDirectory();
            if (dataDirectory == null) {
                return -1;
            }
            if (VERSION.SDK_INT >= 9) {
                return dataDirectory.getUsableSpace();
            }
            if (!dataDirectory.exists()) {
                return -1;
            }
            StatFs statFs = new StatFs(dataDirectory.getPath());
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            ALog.e("AdapterUtilityImpl", "getUsableSpace", th, new Object[0]);
            return -1;
        }
    }

    public static String a(Throwable th) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null && stackTrace.length > 0) {
                for (StackTraceElement stackTraceElement : stackTrace) {
                    stringBuffer.append(stackTraceElement.toString() + "\n");
                }
            }
        } catch (Exception e) {
        }
        return stringBuffer.toString();
    }

    public static String b(Context context) {
        return UTDevice.getUtdid(context);
    }

    public static boolean c(Context context) {
        if (context != null) {
            try {
                NetworkInfo activeNetworkInfo = com.taobao.accs.client.a.a(context).c().getActiveNetworkInfo();
                if (activeNetworkInfo != null) {
                    return activeNetworkInfo.isConnected();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static String d(Context context) {
        String string;
        String str = a;
        try {
            string = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).getString(Constants.SP_KEY_APPKEY, a);
            try {
                if (!TextUtils.isEmpty(string)) {
                    a = string;
                }
                ALog.d("AdapterUtilityImpl", new StringBuilder("getAppkey APPKEY:").append(string).toString(), new Object[0]);
            } catch (Throwable th) {
                Throwable th2 = th;
                th2.printStackTrace();
                return string;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            string = str;
            th2 = th4;
            th2.printStackTrace();
            return string;
        }
        return string;
    }

    public static final boolean a(String str, int i) {
        if (str == null) {
            return false;
        }
        StatFs statFs = new StatFs(str);
        int blockSize = statFs.getBlockSize();
        long availableBlocks = (long) statFs.getAvailableBlocks();
        new StringBuilder("st.getAvailableBlocks()=").append(statFs.getAvailableBlocks()).append(",st.getAvailableBlocks() * blockSize=").append(((long) statFs.getAvailableBlocks()) * ((long) blockSize));
        return statFs.getAvailableBlocks() > 10 && ((long) blockSize) * availableBlocks > ((long) i);
    }
}
