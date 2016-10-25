package com.taobao.accs.utl;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.os.Build.VERSION;
import android.os.Process;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import anet.channel.util.HMacUtil;
import anet.channel.util.HttpConstant;
import com.alibaba.wireless.security.open.SecurityGuardManager;
import com.alibaba.wireless.security.open.SecurityGuardParamContext;
import com.alibaba.wireless.security.open.securesignature.ISecureSignatureComponent;
import com.taobao.accs.client.AccsConfig;
import com.taobao.accs.client.AccsConfig.SECURITY_TYPE;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog.Level;
import com.tencent.open.GameAppOperation;
import com.umeng.a;
import com.umeng.message.MsgConstant;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.android.agoo.common.b;
import org.android.spdy.SpdyAgent;

// compiled from: Taobao
public class UtilityImpl {
    public static String BACK_APP_KEY = null;
    public static String BACK_TTID = null;
    private static String DEVICETOKEN = null;
    public static final String NET_TYPE_2G = "2g";
    public static final String NET_TYPE_3G = "3g";
    public static final String NET_TYPE_4G = "4g";
    public static final String NET_TYPE_UNKNOWN = "unknown";
    public static final String NET_TYPE_WIFI = "wifi";
    private static final String SSL_TIKET_KEY = "accs_ssl_ticket_key";
    private static final String TAG = "UtilityImpl";
    public static final int TNET_FILE_NUM = 5;
    public static final int TNET_FILE_SIZE = 5242880;

    static {
        BACK_APP_KEY = a.d;
        BACK_TTID = a.d;
        DEVICETOKEN = a.d;
    }

    public static String getProxyHost(Context context) {
        Object string = context.getSharedPreferences(Constants.SP_FILE_NAME, XZBDevice.DOWNLOAD_LIST_ALL).getString(Constants.KEY_PROXY_HOST, null);
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        String proxyIp = getProxyIp();
        return TextUtils.isEmpty(proxyIp) ? null : proxyIp;
    }

    public static int getProxyPort(Context context) {
        int i = context.getSharedPreferences(Constants.SP_FILE_NAME, XZBDevice.DOWNLOAD_LIST_ALL).getInt(Constants.KEY_PROXY_PORT, -1);
        if (i > 0) {
            return i;
        }
        if (getProxyHost(context) == null) {
            return -1;
        }
        try {
            return getProxyPort();
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static boolean appVersionChanged(Context context) {
        int i = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).getInt(Constants.KEY_APP_VERSION_CODE, -1);
        try {
            int i2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            i2 = 0;
        }
        if (i == i2) {
            return false;
        }
        saveAppVersionCode(context);
        return true;
    }

    public static void saveAppVersionCode(Context context) {
        try {
            Editor edit = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).edit();
            edit.putInt(Constants.KEY_APP_VERSION_CODE, context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
            edit.apply();
        } catch (NameNotFoundException e) {
        }
    }

    public static void focusDisableService(Context context) {
        Editor edit = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).edit();
        edit.putBoolean(Constants.KEY_FOUCE_DISABLE, true);
        edit.commit();
        disableService(context);
    }

    public static void focusEnableService(Context context) {
        Editor edit = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).edit();
        edit.putBoolean(Constants.KEY_FOUCE_DISABLE, false);
        edit.commit();
        enableService(context);
    }

    public static boolean getFocusDisableStatus(Context context) {
        if (context == null) {
            return false;
        }
        try {
            return context.getSharedPreferences(Constants.SP_FILE_NAME, 0).getBoolean(Constants.KEY_FOUCE_DISABLE, false);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean getServiceEnabled(Context context) {
        try {
            return context.getPackageManager().getServiceInfo(new ComponentName(context, a.channelService), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS).enabled;
        } catch (Throwable e) {
            e.printStackTrace();
            ALog.e(TAG, getStackMsg(e), new Object[0]);
            return false;
        }
    }

    public static boolean getAgooServiceEnabled(Context context) {
        ComponentName componentName = new ComponentName(context, com.taobao.accs.client.a.b(context.getPackageName()));
        PackageManager packageManager = context.getPackageManager();
        try {
            if (!componentName.getPackageName().equals("!")) {
                return packageManager.getServiceInfo(componentName, AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS).enabled;
            } else {
                ALog.e(TAG, new StringBuilder("getAgooServiceEnabled,exception,comptName.getPackageName()=").append(componentName.getPackageName()).toString(), new Object[0]);
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static void enableService(Context context) {
        ComponentName componentName = new ComponentName(context, a.channelService);
        ALog.d(TAG, new StringBuilder("enableService,comptName=").append(componentName.toString()).toString(), new Object[0]);
        try {
            context.getPackageManager().setComponentEnabledSetting(componentName, 1, 1);
        } catch (Exception e) {
        }
    }

    public static void disableService(Context context) {
        ComponentName componentName = new ComponentName(context, a.channelService);
        PackageManager packageManager = context.getPackageManager();
        try {
            ALog.d(TAG, new StringBuilder("disableService,comptName=").append(componentName.toString()).toString(), new Object[0]);
            if (packageManager.getServiceInfo(componentName, AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS).enabled) {
                packageManager.setComponentEnabledSetting(componentName, XZBDevice.DOWNLOAD_LIST_RECYCLE, 1);
                killService(context);
            }
        } catch (NameNotFoundException e) {
        }
    }

    public static void killService(Context context) {
        int myUid = Process.myUid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager != null) {
            for (RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
                if (runningAppProcessInfo.uid == myUid) {
                    if (!TextUtils.isEmpty(com.taobao.accs.client.a.e) && com.taobao.accs.client.a.e.equals(runningAppProcessInfo.processName)) {
                        ALog.e(TAG, new StringBuilder("kill1 ").append(runningAppProcessInfo.processName).toString(), new Object[0]);
                        Process.killProcess(runningAppProcessInfo.pid);
                        return;
                    } else if (runningAppProcessInfo.processName.endsWith(":channel")) {
                        ALog.e(TAG, new StringBuilder("kill ").append(runningAppProcessInfo.processName).toString(), new Object[0]);
                        Process.killProcess(runningAppProcessInfo.pid);
                        return;
                    }
                }
            }
            ALog.e(TAG, "kill nothing", new Object[0]);
        }
    }

    public static String getNetworkType(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return NET_TYPE_UNKNOWN;
        }
        if (activeNetworkInfo.getType() == 1) {
            return NET_TYPE_WIFI;
        }
        Object subtypeName = activeNetworkInfo.getSubtypeName();
        return !TextUtils.isEmpty(subtypeName) ? subtypeName.replaceAll(" ", a.d) : a.d;
    }

    public static String getNetworkTypeExt(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return NET_TYPE_UNKNOWN;
            }
            if (activeNetworkInfo.getType() == 1) {
                return NET_TYPE_WIFI;
            }
            switch (activeNetworkInfo.getSubtype()) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                case XZBDevice.DOWNLOAD_LIST_ALL:
                case R.styleable.Toolbar_contentInsetLeft:
                case XZBDevice.Success:
                    return NET_TYPE_2G;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                case TNET_FILE_NUM:
                case R.styleable.Toolbar_contentInsetEnd:
                case XZBDevice.Wait:
                case XZBDevice.Pause:
                case XZBDevice.Stop:
                case XZBDevice.Fail:
                case XZBDevice.Predownload:
                case XZBDevice.Delete:
                    return NET_TYPE_3G;
                case XZBDevice.Upload:
                    return NET_TYPE_4G;
                default:
                    String subtypeName = activeNetworkInfo.getSubtypeName();
                    if (TextUtils.isEmpty(subtypeName)) {
                        return NET_TYPE_UNKNOWN;
                    }
                    return (subtypeName.equalsIgnoreCase("td-scdma") || subtypeName.equalsIgnoreCase("td_scdma") || subtypeName.equalsIgnoreCase("tds_hsdpa")) ? NET_TYPE_3G : NET_TYPE_UNKNOWN;
            }
        } catch (Throwable e) {
            ALog.e(TAG, "getNetworkTypeExt", e, new Object[0]);
            return null;
        }
    }

    public static String getImsi(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
        } catch (Throwable th) {
            return null;
        }
    }

    public static long String2Time(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.US).parse(str).getTime();
        } catch (Throwable th) {
            return 0;
        }
    }

    public static String formatTime(long j) {
        String str = a.d;
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.US).format(new Date(j));
        } catch (Throwable th) {
            ALog.e(TAG, "formatTime", th, new Object[0]);
            return str;
        }
    }

    public static String formatDay(long j) {
        String str = a.d;
        try {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Long.valueOf(j));
        } catch (Throwable th) {
            ALog.e(TAG, "formatDay", th, new Object[0]);
            return str;
        }
    }

    public static boolean isForeground(Context context) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Object packageName = ((RunningTaskInfo) GlobalClientInfo.getInstance(context).getActivityManager().getRunningTasks(1).get(0)).topActivity.getPackageName();
            if (!TextUtils.isEmpty(packageName) && packageName.equals(context.getPackageName())) {
                return true;
            }
            if (ALog.isPrintLog(Level.D)) {
                ALog.d(TAG, new StringBuilder("isForeground time ").append(System.currentTimeMillis() - currentTimeMillis).toString(), new Object[0]);
            }
            return false;
        } catch (Throwable th) {
            ALog.e(TAG, "isForeground error ", th, new Object[0]);
        }
    }

    public static String getAppsign(Context context, String str, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str)) {
            ALog.e(TAG, "getAppsign appkey null", new Object[0]);
            return null;
        }
        try {
            if (AccsConfig.mSecurityType != SECURITY_TYPE.SECURITY_OFF) {
                SecurityGuardManager instance = SecurityGuardManager.getInstance(context);
                if (instance != null) {
                    ALog.d(TAG, "SecurityGuardManager not null!", new Object[0]);
                    ISecureSignatureComponent secureSignatureComp = instance.getSecureSignatureComp();
                    SecurityGuardParamContext securityGuardParamContext = new SecurityGuardParamContext();
                    securityGuardParamContext.appKey = str;
                    securityGuardParamContext.paramMap.put("INPUT", str2 + str);
                    securityGuardParamContext.requestType = 3;
                    return secureSignatureComp.signRequest(securityGuardParamContext, com.taobao.accs.client.a.c);
                }
                ALog.d(TAG, "SecurityGuardManager is null", new Object[0]);
                return null;
            } else if (!TextUtils.isEmpty(GlobalClientInfo.getInstance(context).getAppSecret())) {
                return HMacUtil.hmacSha1Hex(GlobalClientInfo.getInstance(context).getAppSecret().getBytes(), (str + str2).getBytes());
            } else {
                ALog.e(TAG, "getAppsign secret null", new Object[0]);
                return null;
            }
        } catch (Throwable th) {
            ALog.e(TAG, "getAppsign", th, new Object[0]);
            return null;
        }
    }

    public static String getAppkey(Context context) {
        String d = a.d(context);
        return TextUtils.isEmpty(d) ? BACK_APP_KEY : d;
    }

    public static void saveAppkey(Context context, String str) {
        BACK_APP_KEY = str;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SP_FILE_NAME, 0);
            if (!sharedPreferences.getString(Constants.SP_KEY_APPKEY, a.d).equals(str)) {
                Editor edit = sharedPreferences.edit();
                if (!TextUtils.isEmpty(str)) {
                    edit.putString(Constants.SP_KEY_APPKEY, str);
                }
                edit.commit();
                ALog.d(TAG, new StringBuilder("saveAppkey APPKEY:").append(str).toString(), new Object[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            try {
                NetworkInfo activeNetworkInfo = GlobalClientInfo.getInstance(context).getConnectivityManager().getActiveNetworkInfo();
                if (activeNetworkInfo != null) {
                    return activeNetworkInfo.isConnected();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static String getDeviceId(Context context) {
        return a.b(context);
    }

    public static void setDeviceToken(Context context, String str) {
        ALog.i(TAG, "setDeviceToken", "token", str);
        if (!TextUtils.isEmpty(str)) {
            DEVICETOKEN = str;
            try {
                Editor edit = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).edit();
                edit.putString(Constants.KEY_DEVICE_TOKEN, str);
                edit.apply();
            } catch (Throwable th) {
                ALog.e(TAG, "setDeviceToken", th, new Object[0]);
            }
        }
    }

    public static String getDeviceToken(Context context) {
        String str = DEVICETOKEN;
        try {
            str = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).getString(Constants.KEY_DEVICE_TOKEN, DEVICETOKEN);
        } catch (Throwable th) {
            ALog.e(TAG, "getDeviceToken", th, new Object[0]);
        }
        ALog.i(TAG, "getDeviceToken", "token", str);
        return str;
    }

    public static boolean isServiceRunning(Context context, String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        for (RunningServiceInfo runningServiceInfo : ((ActivityManager) context.getSystemService("activity")).getRunningServices(R.styleable.AppCompatTheme_actionModeSplitBackground)) {
            if (runningServiceInfo.service.getPackageName().equals(str) && runningServiceInfo.service.getClassName().equals(a.channelService)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isFirstStart(Context context) {
        return context.getSharedPreferences(Constants.SP_FILE_NAME, 0).getInt(GameAppOperation.QQFAV_DATALINE_VERSION, -1) != 212;
    }

    public static void setSdkStart(Context context) {
        try {
            Editor edit = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).edit();
            edit.putInt(GameAppOperation.QQFAV_DATALINE_VERSION, Constants.SDK_VERSION_CODE);
            edit.apply();
            ALog.i(TAG, new StringBuilder("setSdkStart isFirstStart:").append(isFirstStart(context)).toString(), new Object[0]);
        } catch (Throwable th) {
            ALog.e(TAG, "setSdkStart", th, new Object[0]);
        }
    }

    public static boolean packageExist(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (NameNotFoundException e) {
            ALog.e(TAG, "package not exist", Constants.KEY_ELECTION_PKG, str);
            return false;
        }
    }

    public static boolean utdidChanged() {
        return false;
    }

    public static void saveUtdid(Context context) {
        try {
            Editor edit = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).edit();
            edit.putString(MsgConstant.KEY_UTDID, getDeviceId(context));
            edit.apply();
        } catch (Throwable th) {
            ALog.e(TAG, "saveUtdid", th, new Object[0]);
        }
    }

    public static boolean channelServiceExist(Context context, String str) {
        try {
            return context.getPackageManager().getServiceInfo(new ComponentName(str, a.channelService), 0).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public static void setMode(Context context, int i) {
        Editor edit = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).edit();
        edit.putInt(Constants.SP_KEY_DEBUG_MODE, i);
        edit.commit();
        ALog.d(TAG, new StringBuilder("setMode:").append(i).toString(), new Object[0]);
    }

    public static int getMode(Context context) {
        try {
            return context.getSharedPreferences(Constants.SP_FILE_NAME, 0).getInt(Constants.SP_KEY_DEBUG_MODE, 0);
        } catch (Throwable th) {
            ALog.e(TAG, "getMode", th, new Object[0]);
            return 0;
        }
    }

    public static boolean isDebugMode(Context context) {
        return getMode(context) == 2;
    }

    public static boolean isPreviewMode(Context context) {
        return getMode(context) == 1;
    }

    public static boolean isReleaseMode(Context context) {
        return getMode(context) == 0;
    }

    public static void setAppInfo(Context context, String str, String str2, String str3) {
        try {
            if (!TextUtils.isEmpty(str)) {
                BACK_APP_KEY = str;
            }
            if (!TextUtils.isEmpty(str3)) {
                BACK_TTID = str3;
            }
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SP_FILE_NAME, 0);
            String string = sharedPreferences.getString(Constants.SP_KEY_APPKEY, a.d);
            String string2 = sharedPreferences.getString(b.PROPERTY_TT_ID, a.d);
            if (!string.equals(str) || !string2.equals(str3)) {
                Editor edit = sharedPreferences.edit();
                if (!TextUtils.isEmpty(str)) {
                    edit.putString(Constants.SP_KEY_APPKEY, str);
                }
                if (!TextUtils.isEmpty(str2)) {
                    edit.putString(Constants.SP_APP_SECRET, str2);
                }
                if (!TextUtils.isEmpty(str3)) {
                    edit.putString(b.PROPERTY_TT_ID, str3);
                }
                edit.commit();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static String getTtId(Context context) {
        try {
            return context.getSharedPreferences(Constants.SP_FILE_NAME, 0).getString(b.PROPERTY_TT_ID, a.d);
        } catch (Throwable th) {
            return BACK_TTID;
        }
    }

    public static String getAppSecret(Context context) {
        try {
            return context.getSharedPreferences(Constants.SP_FILE_NAME, 0).getString(Constants.SP_APP_SECRET, a.d);
        } catch (Throwable th) {
            return a.d;
        }
    }

    public static void setServiceTime(Context context, String str, long j) {
        try {
            Editor edit = context.getSharedPreferences(Constants.SP_CHANNEL_FILE_NAME, 0).edit();
            edit.putLong(str, j);
            edit.commit();
            ALog.d(TAG, new StringBuilder("setServiceTime:").append(j).toString(), new Object[0]);
        } catch (Throwable th) {
            ALog.e(TAG, "setServiceTime:", th, new Object[0]);
        }
    }

    public static long getServiceAliveTime(Context context) {
        long j;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SP_CHANNEL_FILE_NAME, 0);
            j = sharedPreferences.getLong(Constants.SP_KEY_SERVICE_START, 0);
            long j2 = sharedPreferences.getLong(Constants.SP_KEY_SERVICE_END, 0);
            if (j > 0) {
                j = j2 - j;
            } else {
                j = 0;
            }
            try {
                Editor edit = sharedPreferences.edit();
                edit.putLong(Constants.SP_KEY_SERVICE_START, 0);
                edit.putLong(Constants.SP_KEY_SERVICE_END, 0);
                edit.commit();
            } catch (Throwable th) {
                Throwable th2 = th;
                ALog.e(TAG, "getServiceAliveTime:", th2, new Object[0]);
                return j;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            j = 0;
            th2 = th4;
            ALog.e(TAG, "getServiceAliveTime:", th2, new Object[0]);
            return j;
        }
        return j;
    }

    public static boolean isAccsStatisticsOff(Context context) {
        return true;
    }

    public static void clearSharePreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SP_FILE_NAME, 0);
        Object string = sharedPreferences.getString(Constants.SP_KEY_APPKEY, BACK_APP_KEY);
        Object string2 = sharedPreferences.getString(Constants.SP_APP_SECRET, a.d);
        Object string3 = sharedPreferences.getString(b.PROPERTY_TT_ID, a.d);
        Object string4 = sharedPreferences.getString(Constants.KEY_PROXY_HOST, null);
        int i = sharedPreferences.getInt(Constants.KEY_PROXY_PORT, -1);
        int i2 = sharedPreferences.getInt(GameAppOperation.QQFAV_DATALINE_VERSION, -1);
        int i3 = sharedPreferences.getInt(Constants.SP_KEY_DEBUG_MODE, 0);
        Editor edit = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).edit();
        edit.clear();
        if (!TextUtils.isEmpty(string)) {
            edit.putString(Constants.SP_KEY_APPKEY, string);
        }
        if (!TextUtils.isEmpty(string2)) {
            edit.putString(Constants.SP_APP_SECRET, string2);
        }
        if (!TextUtils.isEmpty(string3)) {
            edit.putString(b.PROPERTY_TT_ID, string3);
        }
        if (!TextUtils.isEmpty(string4)) {
            edit.putString(Constants.KEY_PROXY_HOST, string4);
        }
        if (i > 0) {
            edit.putInt(Constants.KEY_PROXY_PORT, i);
        }
        if (i2 > 0) {
            edit.putInt(GameAppOperation.QQFAV_DATALINE_VERSION, i2);
        }
        if (i3 == 2 || i3 == 1) {
            edit.putInt(Constants.SP_KEY_DEBUG_MODE, i3);
        }
        edit.commit();
    }

    public static int praseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
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

    public static String getProcessName(Context context, int i) {
        return a.a(context, i);
    }

    public static boolean isMainProcess(Context context) {
        return a.a(context);
    }

    public static boolean isChannelProcess(Context context) {
        return false;
    }

    public static int getByteLen(String str) {
        if (str == null) {
            return 0;
        }
        try {
            return str.getBytes("utf-8").length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getStackMsg(Throwable th) {
        return a.a(th);
    }

    public static File getExternalFilesDir(Context context) {
        return context.getExternalFilesDir(null);
    }

    public static File getCacheFilesDir(Context context) {
        return context.getExternalCacheDir();
    }

    public static void storeCookie(Context context, String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                GlobalClientInfo.a = str;
                Editor edit = context.getSharedPreferences(Constants.SP_COOKIE_FILE_NAME, 0).edit();
                edit.putString(Constants.SP_KEY_COOKIE_SEC, str);
                edit.apply();
            }
        } catch (Throwable e) {
            ALog.e(TAG, "storeCookie fail", e, new Object[0]);
        }
    }

    public static String restoreCookie(Context context) {
        try {
            return context.getSharedPreferences(Constants.SP_COOKIE_FILE_NAME, XZBDevice.DOWNLOAD_LIST_ALL).getString(Constants.SP_KEY_COOKIE_SEC, null);
        } catch (Throwable e) {
            ALog.e(TAG, "reStoreCookie fail", e, new Object[0]);
            return null;
        }
    }

    public static void clearCookie(Context context) {
        try {
            GlobalClientInfo.a = null;
            Editor edit = context.getSharedPreferences(Constants.SP_COOKIE_FILE_NAME, 0).edit();
            edit.clear();
            edit.apply();
        } catch (Throwable th) {
            ALog.e(TAG, "clearCookie fail", th, new Object[0]);
        }
    }

    public static long getUsableSpace() {
        return a.a();
    }

    public static String convertHost(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            if (str.startsWith("//")) {
                return new StringBuilder("https:").append(str).toString();
            }
            int indexOf = str.indexOf(HttpConstant.SCHEME_SPLIT);
            if (indexOf == -1) {
                return new StringBuilder("https://").append(str).toString();
            }
            Object substring = str.substring(indexOf + 3);
            return !TextUtils.isEmpty(substring) ? new StringBuilder("https://").append(substring).toString() : null;
        } catch (Throwable th) {
            ALog.e(TAG, "convertHost", th, new Object[0]);
            return null;
        }
    }

    public static String getProxyIp() {
        return VERSION.SDK_INT < 11 ? Proxy.getDefaultHost() : System.getProperty("http.proxyHost");
    }

    public static int getProxyPort() {
        if (VERSION.SDK_INT < 11) {
            return Proxy.getDefaultPort();
        }
        try {
            return Integer.parseInt(System.getProperty("http.proxyPort"));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static String getProxy() {
        String str = getProxyIp() + ":" + getProxyPort();
        if (ALog.isPrintLog(Level.D)) {
            ALog.d(TAG, new StringBuilder("getProxy:").append(str).toString(), new Object[0]);
        }
        return str;
    }

    public static String getTnetLogFilePath(Context context, String str) {
        try {
            Object externalFilesDir = context.getExternalFilesDir("tnetlogs");
            if (!(externalFilesDir != null && externalFilesDir.exists() && externalFilesDir.canWrite())) {
                externalFilesDir = context.getDir("logs", 0);
            }
            ALog.d(TAG, new StringBuilder("getTnetLogFilePath :").append(externalFilesDir).toString(), new Object[0]);
            return externalFilesDir + "/" + str.toLowerCase();
        } catch (Throwable th) {
            ALog.e(TAG, "getTnetLogFilePath", th, new Object[0]);
            return null;
        }
    }

    public static String int2String(int i) {
        try {
            return String.valueOf(i);
        } catch (Throwable e) {
            ALog.e(TAG, "int2String", e, new Object[0]);
            return null;
        }
    }

    public static int String2Int(String str) {
        try {
            return Integer.valueOf(str).intValue();
        } catch (Throwable e) {
            ALog.e(TAG, "String2Int", e, new Object[0]);
            return 0;
        }
    }
}
