package com.baidu.mobads.interfaces.utils;

import android.content.Context;
import java.net.HttpURLConnection;
import java.util.List;
import org.json.JSONArray;

public interface IXAdSystemUtils {
    public static final String NT_NONE = "none";
    public static final String NT_UNKNOWN = "unknown";
    public static final String NT_WIFI = "wifi";

    boolean canSupportSdcardStroage(Context context);

    long getAllExternalMemorySize();

    long getAllInternalMemorySize();

    String getAndroidId(Context context);

    String getAppSDC();

    long getAvailableExternalMemorySize();

    long getAvailableInternalMemorySize();

    JSONArray getBackgroundBrowsers(Context context);

    String getCUID(Context context);

    List<String[]> getCell(Context context);

    int getCurrentProcessId(Context context);

    String getCurrentProcessName(Context context);

    String getDeviceId(Context context);

    String getEncodedSN(Context context);

    double[] getGPS(Context context);

    String getGUID(Context context);

    HttpURLConnection getHttpConnection(Context context, String str, int i, int i2);

    String getIMEI(Context context);

    String getIp(Context context);

    String getMacAddress(Context context);

    String getMaxCpuFreq();

    String getMem();

    String getNetType(Context context);

    int getNetworkCatagory(Context context);

    String getNetworkOperator(Context context);

    String getNetworkOperatorName(Context context);

    String getNetworkType(Context context);

    String getPhoneOSBrand();

    String getPhoneOSBuildVersionSdk();

    String getSn(Context context);

    List<String[]> getWIFI(Context context);

    String getWifiConnected(Context context);

    JSONArray getWifiScans(Context context);

    Boolean is3GConnected(Context context);

    boolean isCurrentNetworkAvailable(Context context);

    boolean isTablet(Context context);

    boolean isUseOldStoragePath();

    Boolean isWifiConnected(Context context);
}
