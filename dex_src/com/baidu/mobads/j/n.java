package com.baidu.mobads.j;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.text.format.Formatter;
import com.alipay.sdk.util.h;
import com.baidu.mobads.interfaces.utils.IXAdCommonUtils;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.a;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.editorpage.ShareActivity;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import org.android.agoo.message.MessageService;
import org.android.spdy.SpdyAgent;
import org.json.JSONArray;

public class n implements IXAdSystemUtils {
    private static String b;
    public JSONArray a;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private int h;
    private String i;
    private String j;
    private String k;
    private String l;

    public n() {
        this.a = new JSONArray();
        this.h = -1;
    }

    @TargetApi(4)
    public boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    public String getIMEI(Context context) {
        if (TextUtils.isEmpty(this.c) && context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("__x_adsdk_agent_header__", 0);
            Object string = sharedPreferences.getString(m.a().e().decodeStr("pyd-pifb"), a.d);
            if (TextUtils.isEmpty(string)) {
                try {
                    String str = (String) m.a().m().a((TelephonyManager) context.getApplicationContext().getSystemService("phone"), m.a().e().decodeStr("uvNYwANvpyP-iyfb"), new Object[0]);
                    if (!TextUtils.isEmpty(str)) {
                        new Thread(new o(this, sharedPreferences, str)).start();
                        this.c = str;
                    }
                } catch (Throwable e) {
                    j.a().d(e);
                }
            } else {
                this.c = string;
            }
        }
        return m.a().m().b(this.c);
    }

    public String getSn(Context context) {
        try {
            if (TextUtils.isEmpty(this.d)) {
                String imei = getIMEI(context);
                if (TextUtils.isEmpty(imei)) {
                    imei = getMacAddress(context);
                }
                this.d = m.a().m().b(imei);
            }
            return this.d;
        } catch (Exception e) {
            return a.d;
        }
    }

    public String getCUID(Context context) {
        try {
            if (TextUtils.isEmpty(b)) {
                String string = System.getString(context.getContentResolver(), "com.baidu.deviceid");
                if (!(string == null || string.equals(a.d))) {
                    String string2 = System.getString(context.getContentResolver(), "bd_setting_i");
                    if (TextUtils.isEmpty(string2)) {
                        string2 = MessageService.MSG_DB_READY_REPORT;
                    }
                    b = string + "|" + new StringBuffer(string2).reverse().toString();
                }
            }
            return m.a().m().b(b);
        } catch (Exception e) {
            return m.a().m().b(b);
        }
    }

    public double[] getGPS(Context context) {
        Object a;
        double[] dArr;
        try {
            a = m.a().m().a("SYSGPS");
            if (a != null) {
                return (double[]) a;
            }
        } catch (Throwable e) {
            j.a().e(e);
        }
        if (m.a().m().hasPermission(context, "android.permission.ACCESS_FINE_LOCATION")) {
            Location lastKnownLocation;
            try {
                lastKnownLocation = ((LocationManager) context.getSystemService(ShareActivity.KEY_LOCATION)).getLastKnownLocation("gps");
            } catch (SecurityException e2) {
                dArr = null;
            }
            if (lastKnownLocation != null) {
                dArr = new double[3];
                try {
                    dArr[0] = (double) lastKnownLocation.getTime();
                    dArr[1] = lastKnownLocation.getLongitude();
                    dArr[2] = lastKnownLocation.getLatitude();
                } catch (SecurityException e3) {
                }
                m.a().m().a("SYSGPS", a);
                return a;
            }
        }
        dArr = null;
        m.a().m().a("SYSGPS", a);
        return a;
    }

    public String getGUID(Context context) {
        try {
            if (this.e != null || context == null) {
                return this.e;
            }
            IXAdCommonUtils m = m.a().m();
            this.e = context.getSharedPreferences("__x_adsdk_agent_header__", 0).getString("guid", a.d);
            if (this.e == null || this.e.length() <= 0) {
                this.e = m.md5(getMacAddress(context) + com.alipay.sdk.sys.a.b + getIMEI(context) + "&&");
                if (this.e == null || this.e.length() <= 0) {
                    return a.d;
                }
                context.getSharedPreferences("__x_adsdk_agent_header__", 0).edit().putString("guid", this.e).commit();
            }
            return this.e;
        } catch (Exception e) {
            return a.d;
        }
    }

    public String getAndroidId(Context context) {
        try {
            if (TextUtils.isEmpty(this.f)) {
                this.f = m.a().m().b(Secure.getString(context.getContentResolver(), "android_id"));
            }
            return this.f;
        } catch (Exception e) {
            return a.d;
        }
    }

    public String getAppSDC() {
        try {
            Object a = m.a().m().a("sysSdc");
            if (a != null) {
                return (String) a;
            }
        } catch (Throwable e) {
            j.a().e(e);
        }
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return "0,0";
        }
        String str = a.d;
        try {
            long availableExternalMemorySize = getAvailableExternalMemorySize();
            a = availableExternalMemorySize + MiPushClient.ACCEPT_TIME_SEPARATOR + getAllExternalMemorySize();
            m.a().m().a("sysSdc", a);
            return a;
        } catch (Exception e2) {
            return str;
        }
    }

    public String getMem() {
        try {
            Object a = m.a().m().a("sysMem");
            if (a != null) {
                return (String) a;
            }
        } catch (Throwable e) {
            j.a().e(e);
        }
        String str = a.d;
        try {
            long availableInternalMemorySize = getAvailableInternalMemorySize();
            a = availableInternalMemorySize + MiPushClient.ACCEPT_TIME_SEPARATOR + getAllInternalMemorySize();
            m.a().m().a("sysMem", a);
            return a;
        } catch (Exception e2) {
            return str;
        }
    }

    public long getAllExternalMemorySize() {
        try {
            return Environment.getExternalStorageState().equals("mounted") ? a(Environment.getExternalStorageDirectory()) : -1;
        } catch (Exception e) {
            return -1;
        }
    }

    public long getAllInternalMemorySize() {
        try {
            return a(Environment.getDataDirectory());
        } catch (Exception e) {
            return -1;
        }
    }

    public long getAvailableExternalMemorySize() {
        try {
            return Environment.getExternalStorageState().equals("mounted") ? b(Environment.getExternalStorageDirectory()) : -1;
        } catch (Exception e) {
            return -1;
        }
    }

    public long getAvailableInternalMemorySize() {
        try {
            return b(Environment.getDataDirectory());
        } catch (Exception e) {
            return -1;
        }
    }

    @TargetApi(18)
    private long a(File file) {
        try {
            StatFs statFs = new StatFs(file.getPath());
            return (long) (((statFs.getBlockSize() * statFs.getBlockCount()) / 1024) / 1024);
        } catch (Exception e) {
            return -1;
        }
    }

    @TargetApi(18)
    private long b(File file) {
        try {
            StatFs statFs = new StatFs(file.getPath());
            return (long) (((statFs.getBlockSize() * statFs.getAvailableBlocks()) / 1024) / 1024);
        } catch (Exception e) {
            return -1;
        }
    }

    public String getMacAddress(Context context) {
        if (TextUtils.isEmpty(this.g)) {
            IXAdLogger f = m.a().f();
            try {
                WifiManager wifiManager = (WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI);
                if (m.a().m().hasPermission(context, MsgConstant.PERMISSION_ACCESS_WIFI_STATE)) {
                    this.g = m.a().m().b(wifiManager.getConnectionInfo().getMacAddress());
                } else {
                    f.e(a.d, "Could not get mac address. no android.permission.ACCESS_WIFI_STATE");
                }
            } catch (Exception e) {
                f.e(a.d, new StringBuilder("Could not get mac address.").append(e.toString()).toString());
            }
        }
        return this.g;
    }

    @TargetApi(3)
    public String getIp(Context context) {
        String str = a.d;
        if (((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo() == null) {
            return a.d;
        }
        try {
            str = Formatter.formatIpAddress(((WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI)).getConnectionInfo().getIpAddress());
            if (!TextUtils.isEmpty(str)) {
                return "0.0.0.0".equals(str) ? a.d : str;
            } else {
                Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaces.hasMoreElements()) {
                    Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                        if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress() && (inetAddress instanceof Inet4Address)) {
                            return inetAddress.getHostAddress();
                        }
                    }
                }
                String str2 = str;
                return str2;
            }
        } catch (SocketException e) {
            str2 = str;
        } catch (Exception e2) {
            str2 = str;
        }
    }

    public String getMaxCpuFreq() {
        Throwable e;
        IXAdLogger f;
        FileReader fileReader = null;
        if (this.h < 0) {
            FileReader fileReader2;
            BufferedReader bufferedReader;
            try {
                fileReader2 = new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq");
                try {
                    bufferedReader = new BufferedReader(fileReader2);
                    try {
                        this.h = Integer.parseInt(bufferedReader.readLine().trim()) / 1000;
                        try {
                            fileReader2.close();
                            bufferedReader.close();
                        } catch (IOException e2) {
                            e = e2;
                            f = m.a().f();
                            f.d(e);
                            return this.h;
                        }
                    } catch (Exception e3) {
                        e = e3;
                        fileReader = fileReader2;
                        m.a().f().d(e);
                        fileReader.close();
                        bufferedReader.close();
                        return this.h;
                    } catch (Throwable th) {
                        e = th;
                        fileReader2.close();
                        bufferedReader.close();
                        throw e;
                    }
                } catch (Exception e4) {
                    e = e4;
                    bufferedReader = null;
                    fileReader = fileReader2;
                    m.a().f().d(e);
                    try {
                        fileReader.close();
                        bufferedReader.close();
                    } catch (IOException e5) {
                        e = e5;
                        f = m.a().f();
                        f.d(e);
                        return this.h;
                    }
                    return this.h;
                } catch (Throwable th2) {
                    e = th2;
                    bufferedReader = null;
                    fileReader2.close();
                    bufferedReader.close();
                    throw e;
                }
            } catch (Exception e6) {
                e = e6;
                bufferedReader = null;
                try {
                    m.a().f().d(e);
                    fileReader.close();
                    bufferedReader.close();
                } catch (Throwable th3) {
                    e = th3;
                    fileReader2 = fileReader;
                    fileReader2.close();
                    bufferedReader.close();
                    throw e;
                }
                return this.h;
            } catch (Throwable th4) {
                e = th4;
                bufferedReader = null;
                fileReader2 = null;
                try {
                    fileReader2.close();
                    bufferedReader.close();
                } catch (Throwable e7) {
                    m.a().f().d(e7);
                }
                throw e;
            }
        }
        return this.h;
    }

    public String getNetworkOperatorName(Context context) {
        if (TextUtils.isEmpty(this.i)) {
            try {
                IXAdCommonUtils m = m.a().m();
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                Object simOperatorName = telephonyManager.getSimOperatorName();
                StringBuilder stringBuilder = new StringBuilder();
                if (TextUtils.isEmpty(simOperatorName)) {
                    simOperatorName = telephonyManager.getNetworkOperatorName();
                    if (TextUtils.isEmpty(simOperatorName)) {
                        return a.d;
                    }
                    stringBuilder.append(simOperatorName);
                } else {
                    stringBuilder.append(simOperatorName);
                }
                stringBuilder.append("_");
                Object simOperator = telephonyManager.getSimOperator();
                if (!TextUtils.isEmpty(simOperator)) {
                    stringBuilder.append(simOperator);
                }
                if (stringBuilder.length() > 1) {
                    this.i = m.getTextEncoder(stringBuilder.toString());
                }
            } catch (Exception e) {
                m.a().f().e("Get operator failed", a.d);
            }
        }
        return this.i;
    }

    public String getNetworkOperator(Context context) {
        try {
            if (TextUtils.isEmpty(this.j)) {
                this.j = m.a().m().b(((TelephonyManager) context.getSystemService("phone")).getNetworkOperator());
            }
            return this.j;
        } catch (Exception e) {
            return this.j;
        }
    }

    public String getPhoneOSBuildVersionSdk() {
        return m.a().m().b(VERSION.SDK);
    }

    @SuppressLint({"DefaultLocale"})
    @TargetApi(3)
    public String getNetworkType(Context context) {
        String str = IXAdSystemUtils.NT_NONE;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
                return str;
            }
            if (activeNetworkInfo.getType() == 1) {
                return UtilityImpl.NET_TYPE_WIFI;
            }
            String str2 = UtilityImpl.NET_TYPE_UNKNOWN;
            try {
                return activeNetworkInfo.getSubtypeName() != null ? activeNetworkInfo.getSubtypeName().toLowerCase() : str2;
            } catch (Exception e) {
                Exception e2 = e;
                m.a().f().i(e2);
                return str2;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            str2 = str;
            e2 = exception;
            m.a().f().i(e2);
            return str2;
        }
    }

    public String getNetType(Context context) {
        String str = a.d;
        try {
            str = new StringBuilder("_").append(((TelephonyManager) context.getSystemService("phone")).getNetworkType()).toString();
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
            NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(1);
            if (networkInfo == null || !networkInfo.isAvailable()) {
                return (networkInfo2 == null || !networkInfo2.isAvailable()) ? str : new StringBuilder(UtilityImpl.NET_TYPE_WIFI).append(str).toString();
            } else {
                return networkInfo.getExtraInfo() + str;
            }
        } catch (Throwable e) {
            Throwable th = e;
            String str2 = str;
            j.a().e(th);
            return str2;
        }
    }

    public int getNetworkCatagory(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                if (activeNetworkInfo.getType() == 1) {
                    return R.styleable.AppCompatTheme_buttonStyle;
                }
                if (activeNetworkInfo.getType() == 0) {
                    String subtypeName = activeNetworkInfo.getSubtypeName();
                    switch (activeNetworkInfo.getSubtype()) {
                        case SpdyAgent.ACCS_TEST_SERVER:
                            return 1;
                        case SpdyAgent.ACCS_ONLINE_SERVER:
                        case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        case XZBDevice.DOWNLOAD_LIST_ALL:
                        case R.styleable.Toolbar_contentInsetLeft:
                        case XZBDevice.Success:
                            return XZBDevice.DOWNLOAD_LIST_RECYCLE;
                        case XZBDevice.DOWNLOAD_LIST_FAILED:
                        case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                        case R.styleable.Toolbar_contentInsetEnd:
                        case XZBDevice.Wait:
                        case XZBDevice.Pause:
                        case XZBDevice.Stop:
                            return 3;
                        default:
                            return (subtypeName == null || !(subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000"))) ? 1 : 3;
                    }
                }
            }
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }

    public Boolean isWifiConnected(Context context) {
        return a(context, 1);
    }

    public Boolean is3GConnected(Context context) {
        return a(context, 0);
    }

    private Boolean a(Context context, int i) {
        try {
            if (context.checkCallingOrSelfPermission(MsgConstant.PERMISSION_ACCESS_NETWORK_STATE) != 0) {
                m.a().f().e("Utils", "no permission android.permission.ACCESS_NETWORK_STATE");
                return Boolean.valueOf(false);
            }
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            boolean z = activeNetworkInfo != null && activeNetworkInfo.getType() == i && activeNetworkInfo.isConnected();
            return Boolean.valueOf(z);
        } catch (Exception e) {
            return Boolean.valueOf(false);
        }
    }

    public String getDeviceId(Context context) {
        return getIMEI(context);
    }

    public String getEncodedSN(Context context) {
        try {
            if (TextUtils.isEmpty(this.k)) {
                this.k = m.a().e().encode(getSn(context));
            }
            return this.k;
        } catch (Exception e) {
            return this.k;
        }
    }

    public String getPhoneOSBrand() {
        return m.a().m().b(Build.BRAND);
    }

    public List<String[]> getCell(Context context) {
        try {
            Object a = m.a().m().a("cell");
            if (a != null) {
                return (List) a;
            }
        } catch (Throwable e) {
            j.a().e(e);
        }
        List<String[]> arrayList = new ArrayList();
        try {
            CellLocation cellLocation = ((TelephonyManager) context.getSystemService("phone")).getCellLocation();
            if (cellLocation != null) {
                Object obj = new Object[3];
                if (cellLocation instanceof GsmCellLocation) {
                    GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                    obj[0] = gsmCellLocation.getCid();
                    obj[1] = gsmCellLocation.getLac();
                    obj[2] = MessageService.MSG_DB_READY_REPORT;
                } else {
                    String[] split = cellLocation.toString().replace("[", a.d).replace("]", a.d).split(MiPushClient.ACCEPT_TIME_SEPARATOR);
                    obj[0] = split[0];
                    obj[1] = split[3];
                    obj[2] = split[4];
                }
                arrayList.add(obj);
            }
            m.a().m().a("cell", (Object) arrayList);
        } catch (Exception e2) {
        }
        return arrayList;
    }

    public List<String[]> getWIFI(Context context) {
        int i = 0;
        IXAdCommonUtils m = m.a().m();
        try {
            Object a = ((d) m).a(UtilityImpl.NET_TYPE_WIFI);
            if (a != null) {
                return (List) a;
            }
        } catch (Throwable e) {
            j.a().e(e);
        }
        List<String[]> arrayList = new ArrayList();
        try {
            if (m.hasPermission(context, MsgConstant.PERMISSION_ACCESS_WIFI_STATE)) {
                WifiManager wifiManager = (WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI);
                if (wifiManager.isWifiEnabled()) {
                    List scanResults = wifiManager.getScanResults();
                    Collections.sort(scanResults, new p(this));
                    while (i < scanResults.size() && i < 5) {
                        String toLowerCase = ((ScanResult) scanResults.get(i)).BSSID.replace(":", a.d).toLowerCase(Locale.getDefault());
                        arrayList.add(new Object{toLowerCase, Math.abs(r1.level)});
                        i++;
                    }
                }
            }
        } catch (Throwable e2) {
            j.a().e(e2);
        }
        ((d) m).a(UtilityImpl.NET_TYPE_WIFI, (Object) arrayList);
        return arrayList;
    }

    public boolean canSupportSdcardStroage(Context context) {
        try {
            return m.a().m().hasPermission(context, MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE) || !isUseOldStoragePath();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isUseOldStoragePath() {
        return VERSION.SDK_INT < 23;
    }

    public String getWifiConnected(Context context) {
        String str = a.d;
        try {
            if (m.a().m().hasPermission(context, MsgConstant.PERMISSION_ACCESS_WIFI_STATE)) {
                WifiInfo connectionInfo = ((WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI)).getConnectionInfo();
                String ssid = connectionInfo.getSSID();
                if (ssid == null) {
                    ssid = a.d;
                } else if (ssid.length() > 2 && ssid.startsWith(h.f) && ssid.endsWith(h.f)) {
                    ssid = ssid.substring(1, ssid.length() - 1);
                }
                return connectionInfo.getBSSID() + "|" + m.a().e().encode(ssid);
            }
        } catch (Throwable e) {
            m.a().f().d(e);
        }
        return str;
    }

    public JSONArray getWifiScans(Context context) {
        try {
            Object a = m.a().m().a("wifiScans");
            if (a != null) {
                return (JSONArray) a;
            }
        } catch (Throwable e) {
            j.a().e(e);
        }
        JSONArray jSONArray = new JSONArray();
        try {
            if (m.a().m().hasPermission(context, MsgConstant.PERMISSION_ACCESS_WIFI_STATE)) {
                WifiManager wifiManager = (WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI);
                if (wifiManager.isWifiEnabled()) {
                    List scanResults = wifiManager.getScanResults();
                    Collections.sort(scanResults, new q(this));
                    int i = 0;
                    while (i < scanResults.size() && i < 50) {
                        ScanResult scanResult = (ScanResult) scanResults.get(i);
                        jSONArray.put(scanResult.BSSID + "|" + m.a().e().encode(scanResult.SSID));
                        i++;
                    }
                }
            }
        } catch (Throwable e2) {
            m.a().f().d(e2);
        }
        m.a().m().a("wifiScans", (Object) jSONArray);
        return jSONArray;
    }

    public JSONArray getBackgroundBrowsers(Context context) {
        IXAdLogger f = m.a().f();
        String[] supportedBrowsers = m.a().p().getSupportedBrowsers();
        try {
            List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
            PackageManager packageManager = context.getApplicationContext().getPackageManager();
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (packageManager.getLaunchIntentForPackage(runningAppProcessInfo.processName) != null && packageManager.getApplicationInfo(runningAppProcessInfo.processName, AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS) != null) {
                    for (Object obj : supportedBrowsers) {
                        if (runningAppProcessInfo.processName.equals(obj)) {
                            this.a.put(runningAppProcessInfo.processName);
                        }
                    }
                }
            }
        } catch (Throwable e) {
            f.d(e);
        }
        f.d(new StringBuilder("bgBrowsers:").append(this.a).toString());
        return this.a;
    }

    public HttpURLConnection getHttpConnection(Context context, String str, int i, int i2) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection.setConnectTimeout(i);
                httpURLConnection.setReadTimeout(i2);
                return httpURLConnection;
            } catch (Exception e) {
                return httpURLConnection;
            }
        } catch (Exception e2) {
            return null;
        }
    }

    public boolean isCurrentNetworkAvailable(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
        } catch (Throwable e) {
            m.a().f().d("isCurrentNetworkAvailable", e);
            return false;
        }
    }

    public String getCurrentProcessName(Context context) {
        try {
            if (this.l == null) {
                int myPid = Process.myPid();
                ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
                if (activityManager != null) {
                    List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
                    if (runningAppProcesses != null) {
                        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                            if (runningAppProcessInfo.pid == myPid) {
                                this.l = runningAppProcessInfo.processName;
                            }
                        }
                    }
                }
            }
            return this.l;
        } catch (Exception e) {
            return this.l;
        }
    }

    public int getCurrentProcessId(Context context) {
        try {
            return Process.myPid();
        } catch (Exception e) {
            return 0;
        }
    }
}
