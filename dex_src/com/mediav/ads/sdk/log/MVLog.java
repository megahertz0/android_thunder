package com.mediav.ads.sdk.log;

import android.content.Context;
import com.alipay.sdk.app.statistic.c;
import com.mediav.ads.sdk.adcore.Config;
import com.taobao.accs.common.Constants;
import java.util.HashMap;
import org.android.agoo.message.MessageService;

public class MVLog {
    private static final String TAG = "MEDIAV";
    private static Context context;
    public static boolean logcatSwitch;

    final class AnonymousClass_2 implements Runnable {
        final /* synthetic */ HashMap val$logdata;

        AnonymousClass_2(HashMap hashMap) {
            this.val$logdata = hashMap;
        }

        public final void run() {
            LogUploader.postLog(this.val$logdata, context, true);
        }
    }

    static {
        logcatSwitch = true;
    }

    private MVLog() {
    }

    public static void init(Context context) {
        context = context;
        Utils.init(context);
        new Thread(new Runnable() {
            public final void run() {
                LogFileManager.uploadAllLogs(context);
            }
        }).start();
    }

    public static void i(String str) {
    }

    public static void d(String str) {
    }

    public static void w(Exception exception) {
        if (exception != null && logcatSwitch) {
            exception.printStackTrace();
        }
    }

    public static void e(String str) {
    }

    public static void e(int i, String str) {
        e(i, str, null);
    }

    public static void e(int i, String str, Throwable th) {
        if (str != null && context != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("etype", String.valueOf(i / 100));
            hashMap.put("ecode", String.valueOf(i));
            hashMap.put("emsg", Utils.base64Encode(str));
            hashMap.put("etime", System.currentTimeMillis());
            if (th != null) {
                hashMap.put("exception", Utils.base64Encode(th.getMessage()));
                hashMap.put("trace", Utils.base64Encode(Utils.stackTraceToString(th)));
            }
            hashMap.putAll(getBasicParameters());
            new Thread(new AnonymousClass_2(hashMap)).start();
        }
    }

    public static void i(String str, String str2) {
    }

    public static void d(String str, String str2) {
    }

    public static void w(String str, String str2) {
    }

    public static void e(String str, String str2) {
    }

    private static HashMap<String, String> getBasicParameters() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("apppackagename", Utils.getAppPackageName());
        hashMap.put("appname", Utils.getAppname());
        hashMap.put("appv", Utils.getAppVersion());
        hashMap.put(Constants.KEY_ELECTION_SDKV, Config.DEFAULT_SDK_VER);
        hashMap.put(LogBuilder.KEY_CHANNEL, MessageService.MSG_DB_NOTIFY_REACHED);
        hashMap.put(Constants.KEY_OS_VERSION, Utils.getSysteminfo());
        hashMap.put(Constants.KEY_IMEI, Utils.getIMEI());
        hashMap.put("imei_md5", Utils.getIMEIWhitMD5());
        hashMap.put(Constants.KEY_IMSI, Utils.getIMSI());
        hashMap.put("imsi_md5", Utils.getIMSIWhitMD5());
        hashMap.put("mac", Utils.getMac());
        hashMap.put("mac_md5", Utils.getMacWhitMD5());
        hashMap.put(Constants.KEY_MODEL, Utils.getProductModel());
        hashMap.put("screenwidth", Utils.getDeviceScreenSizeWithString(Boolean.valueOf(true)));
        hashMap.put("screenheight", Utils.getDeviceScreenSizeWithString(Boolean.valueOf(false)));
        hashMap.put("so", Utils.getScreenOrientation());
        hashMap.put("density", Utils.getDeviceDensity());
        hashMap.put("appname", Utils.getAppname());
        hashMap.put("apppkg", Utils.getAppPackageName());
        hashMap.put(c.a, Utils.getCurrentNetWorkInfo());
        hashMap.put("androidid", Utils.getAndroidid());
        hashMap.put("androidid_md5", Utils.getAndroididWithMD5());
        hashMap.put(Constants.KEY_BRAND, Utils.getBrand());
        hashMap.put("carrier", Utils.getNetworkOperator());
        hashMap.put("m2id", Utils.getm2id());
        hashMap.put("serialid", Utils.getDeviceSerial());
        hashMap.put("devicetype", Utils.getDeviceType());
        hashMap.put("rmac", Utils.getRouteMac());
        hashMap.put("rssid", Utils.getRouteSSID());
        return hashMap;
    }
}
