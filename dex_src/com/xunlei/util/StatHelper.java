package com.xunlei.util;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.socialize.weixin.BuildConfig;
import com.xunlei.android.xlstat.XLLog;
import com.xunlei.android.xlstat.XLStatLoader;
import com.xunlei.android.xlstat.XLStatManager;
import com.xunlei.android.xlstat.XLUtil;
import com.xunlei.android.xlstat.param.XLStatKey;
import com.xunlei.android.xlstat.param.XLStatParam;
import com.xunlei.androidvip.XLAndroidVipManager;
import com.xunlei.download.proguard.am;
import com.xunlei.downloadlib.XLDownloadManager;
import com.xunlei.downloadlib.parameter.GetDownloadLibVersion;

public class StatHelper {
    private static final String a = "StatHelper";
    private static final String b = "eGxfZGxfc2RrX2FuZHJvaWQAEgAC";
    private static final String c = "xl_dl_sdk_android";
    private static final String d = "xl_dl_sdk_android";
    private static final String e = "download_sdk";
    private static final String f = "android_sdk_stat_config.xml";
    private static final String g = "<config><server><tcp host=\"dlandroid.rcv.sandai.net\" port=\"80\"/></server><priority><level id=\"0\" report_time=\"0\"/><level id=\"1\" report_time=\"0\"/><level id=\"2\" report_time=\"1\"/><level id=\"3\" report_time=\"-1\"/><level id=\"4\" report_time=\"-2\"/></priority><stat><event index=\"4711\" key=\"download_sdk\" priority=\"2\"/></stat><max_storage_records>2000</max_storage_records><max_send_records>200</max_send_records><storage_name>statstorage_andriod_sdk.xml</storage_name><seq_id_file_name>andriod_sdk_seq_id</seq_id_file_name></config>";
    private static StatHelper j;
    private Context h;
    private XLStatKey i;

    public StatHelper() {
        this.i = null;
    }

    static {
        j = null;
    }

    public static synchronized StatHelper instance(Context context) {
        StatHelper statHelper;
        synchronized (StatHelper.class) {
            if (j == null) {
                statHelper = new StatHelper();
                j = statHelper;
                statHelper.h = context.getApplicationContext();
                j.a();
                am.b(a, "instance()");
            }
            statHelper = j;
        }
        return statHelper;
    }

    private void a() {
        PackageInfo packageInfo;
        int i;
        Object obj = null;
        try {
            packageInfo = this.h.getPackageManager().getPackageInfo(this.h.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        String str = (packageInfo == null || packageInfo.versionName == null) ? "android sdk" : packageInfo.packageName;
        String str2 = new String(Base64.encode(str.getBytes(), 0));
        GetDownloadLibVersion getDownloadLibVersion = new GetDownloadLibVersion();
        XLDownloadManager.getInstance(this.h).getDownloadLibVersion(getDownloadLibVersion);
        XLStatParam xLStatParam = new XLStatParam();
        xLStatParam.mAppKey = b;
        xLStatParam.mAppName = d;
        xLStatParam.mAppVersion = getDownloadLibVersion.mVersion != null ? getDownloadLibVersion.mVersion : BuildConfig.VERSION_NAME;
        xLStatParam.mGuid = d;
        str = (packageInfo == null || packageInfo.versionName == null) ? BuildConfig.VERSION_NAME : packageInfo.versionName;
        xLStatParam.mProductVersion = str;
        xLStatParam.mProductKey = "Y29tLnh1bmxlaS5kb3dubG9hZHByb3ZpZGVyAHkXAQ==";
        xLStatParam.mProductName = "com.xunlei.downloadprovider";
        xLStatParam.mStoragePath = this.h.getFilesDir().getPath();
        xLStatParam.mConfigPath = this.h.getFilesDir().getPath();
        xLStatParam.mConfigFileName = f;
        xLStatParam.mConfigBuffer = g;
        this.i = new XLStatKey();
        XLStatManager a = XLStatManager.a(this.h);
        XLStatKey xLStatKey = this.i;
        if (xLStatKey == null || !xLStatParam.checkParam()) {
            XLLog.c("XLStatManager", "init param error");
            i = XLAndroidVipManager.PARAM_ERROR;
        } else if (a.a == null) {
            XLLog.c("XLStatManager", "init mLoader is null");
            i = XLAndroidVipManager.NOT_INIT;
        } else {
            int b = XLUtil.b(a.b);
            XLStatLoader xLStatLoader = a.a;
            String str3 = xLStatParam.mAppKey;
            str2 = xLStatParam.mAppName;
            String str4 = xLStatParam.mAppVersion;
            String a2 = XLUtil.a(a.b);
            if (a2 == null) {
                a2 = "000000000000000V";
            }
            i = xLStatLoader.init(str3, str2, str4, a2, xLStatParam.mGuid, xLStatParam.mProductKey, xLStatParam.mProductName, xLStatParam.mProductVersion, xLStatParam.mStoragePath, xLStatParam.mConfigPath, xLStatParam.mConfigFileName, xLStatParam.mConfigBuffer, b, xLStatKey);
            XLLog.a("XLStatManager", "doMonitorNetworkChange()");
            if (a.b != null && a.c == null) {
                a.c = new NetworkChangeReceiver((byte) 0);
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                XLLog.a("XLStatManager", "register Receiver");
                a.b.registerReceiver(a.c, intentFilter);
            }
            XLLog.b("XLStatManager", new StringBuilder("init ret=").append(i).append(", key=").append(xLStatKey.mKey).toString());
        }
        am.b(a, new StringBuilder("init ret = ").append(i).toString());
    }

    private void b() {
        XLStatManager a = XLStatManager.a(this.h);
        XLStatKey xLStatKey = this.i;
        if (xLStatKey == null || xLStatKey.mKey == 0) {
            XLLog.c("XLStatManager", "unInit key is null");
        } else if (a.a == null) {
            XLLog.c("XLStatManager", "unInit mLoader is null");
        } else {
            int unInit = a.a.unInit(xLStatKey.mKey);
            XLLog.a("XLStatManager", "undoMonitorNetworkChange()");
            if (!(a.b == null || a.c == null)) {
                a.b.unregisterReceiver(a.c);
                XLLog.a("XLStatManager", "unregister Receiver");
                a.c = null;
            }
            XLLog.b("XLStatManager", new StringBuilder("unInit ret = ").append(unInit).append(", key=").append(xLStatKey.mKey).toString());
        }
    }

    public void trackStatusChange(String str) {
        a(e, str);
    }

    private void a(String str, String str2) {
        XLStatManager a = XLStatManager.a(this.h);
        XLStatKey xLStatKey = this.i;
        if (xLStatKey == null || xLStatKey.mKey == 0 || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            XLLog.c("XLStatManager", "trackEvent param is null");
        } else if (a.a == null) {
            XLLog.c("XLStatManager", "trackEvent mLoader is null");
        } else {
            XLLog.b("XLStatManager", new StringBuilder("trackEvent ret=").append(a.a.trackEvent(xLStatKey.mKey, str, null, null, 0, 0, 0, 0, str2)).append(", event = ").append(str).append(", extraData = [").append(str2).append("]").toString());
        }
    }
}
