package com.baidu.mobads.interfaces.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import java.io.File;
import java.util.List;
import org.json.JSONArray;

public interface IXAdCommonUtils {
    public static final String APPSEC = "BaiduMobAd_APP_SEC";
    public static final String APPSID = "BaiduMobAd_APP_ID";
    public static final String DEBUG_TOKEN = "BaiduMobAd_DEBUG_TOKEN";
    public static final String PKGS_PREF_ACTIVATION = "__sdk_pasys_pkgs_2";
    public static final String PKGS_PREF_DOWNLOAD = "__sdk_remote_dl_2";
    @Deprecated
    public static final String PKGS_PREF_DOWNLOAD_KEY = "pkgs";
    public static final String PKGS_PREF_DOWNLOAD_STATUS = "dl";
    public static final int PREF_DOWNLOADED = 3;
    public static final int PREF_DOWNOADING = 1;
    public static final int PREF_DOWNOAD_CANCELED = 2;
    public static final int PREF_DOWNOAD_FAILED = 4;
    public static final int PREF_NOT_DOWNLOAD = 0;

    JSONArray array2Json(double[] dArr);

    String base64Encode(String str);

    boolean bitMaskContainsFlag(int i, int i2);

    void browserOutside(Context context, String str);

    boolean checkSelfPermission(Context context, String str);

    String createRequestId(Context context, String str);

    String decodeURIComponent(String str);

    String encodeURIComponent(String str);

    long generateUniqueId();

    int getApkDownloadStatus(Context context, String str, String str2);

    String getApkFileLocalPath(Context context, String str);

    String getAppId(Context context);

    String getAppPackage(Context context);

    String getAppSec(Context context);

    String getBaiduMapsInfo(Context context);

    String getChannelId();

    String getDebugToken(Context context);

    DisplayMetrics getDisplayMetrics(Context context);

    String getFileLocalFullPath(Context context, String str);

    String getLocationInfo(Context context);

    int getLogicalPixel(Context context, int i);

    String getMD5(String str);

    int getPixel(Context context, int i);

    float getScreenDensity(Context context);

    Rect getScreenRect(Context context);

    int getStatusBarHeight(Activity activity);

    String getStatusStr(Context context, String str, String str2);

    String getSubscriberId(Context context);

    String getTextEncoder(String str);

    Rect getWindowRect(Context context);

    boolean hasPermission(Context context, String str);

    boolean hasSupportedApps(Context context, int i);

    void installApp(Context context, String str, File file, boolean z);

    boolean isOldPermissionModel();

    boolean isStringAvailable(String str);

    JSONArray list2Json(List<String[]> list);

    void makeCall(Context context, String str);

    String md5(String str);

    void sendSMS(Context context, String str, String str2);

    void setAppId(String str);

    void setAppSec(String str);

    void setChannelId(String str);

    String vdUrl(String str, int i);
}
