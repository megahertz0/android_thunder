package u.aly;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.umeng.analytics.a;
import com.xunlei.mediaserver.Utility;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Locale;
import javax.microedition.khronos.opengles.GL10;
import org.android.spdy.SpdyProtocol;

// compiled from: DeviceConfig.java
public class t {
    protected static final String a;

    static {
        a = t.class.getName();
    }

    public static String a(Context context) {
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
        } catch (NameNotFoundException e) {
            return BuildConfig.VERSION_NAME;
        }
    }

    public static String b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            return BuildConfig.VERSION_NAME;
        }
    }

    public static boolean a(Context context, String str) {
        if (VERSION.SDK_INT >= 23) {
            if (context.checkSelfPermission(str) == 0) {
                return true;
            }
        } else if (context.getPackageManager().checkPermission(str, context.getPackageName()) == 0) {
            return true;
        }
        return false;
    }

    public static String[] a(GL10 gl10) {
        try {
            String[] strArr = new String[2];
            String glGetString = gl10.glGetString(7936);
            String glGetString2 = gl10.glGetString(7937);
            strArr[0] = glGetString;
            strArr[1] = glGetString2;
            return strArr;
        } catch (Throwable e) {
            v.e("Could not read gpu infor:", e);
            return new String[0];
        }
    }

    private static String b() {
        if (a.f) {
            String[] strArr = new String[]{"/sys/class/net/wlan0/address", "/sys/class/net/eth0/address", "/sys/devices/virtual/net/wlan0/address"};
            int i = 0;
            while (i < 3) {
                try {
                    String a = a(strArr[i]);
                    if (a != null) {
                        return a;
                    }
                    i++;
                } catch (Throwable e) {
                    v.e("open file  Failed", e);
                }
            }
        }
        return null;
    }

    private static String a(String str) throws FileNotFoundException {
        Throwable e;
        String str2 = null;
        Reader fileReader = new FileReader(str);
        try {
            BufferedReader bufferedReader = new BufferedReader(fileReader, 1024);
            try {
                str2 = bufferedReader.readLine();
                try {
                    fileReader.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                try {
                    bufferedReader.close();
                } catch (IOException e22) {
                    e22.printStackTrace();
                }
            } catch (IOException e3) {
                e = e3;
                v.e(new StringBuilder("Could not read from file ").append(str).toString(), e);
                try {
                    fileReader.close();
                } catch (IOException e222) {
                    e222.printStackTrace();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                return str2;
            }
        } catch (IOException e4) {
            e = e4;
            bufferedReader = null;
            try {
                v.e(new StringBuilder("Could not read from file ").append(str).toString(), e);
                fileReader.close();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e2222) {
                        e2222.printStackTrace();
                    }
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                fileReader.close();
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw th2;
            }
            return str2;
        } catch (Throwable e5) {
            bufferedReader = null;
            th2 = e5;
            try {
                fileReader.close();
            } catch (IOException e22222) {
                e22222.printStackTrace();
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e222222) {
                    e222222.printStackTrace();
                }
            }
            throw th2;
        }
        return str2;
    }

    public static String a() {
        String str = null;
        try {
            Reader fileReader = new FileReader("/proc/cpuinfo");
            try {
                BufferedReader bufferedReader = new BufferedReader(fileReader, 1024);
                str = bufferedReader.readLine();
                bufferedReader.close();
                fileReader.close();
            } catch (Throwable e) {
                v.e("Could not read from file /proc/cpuinfo", e);
            }
        } catch (Throwable e2) {
            v.e("Could not open file /proc/cpuinfo", e2);
        }
        return str != null ? str.substring(str.indexOf(R.styleable.AppCompatTheme_toolbarStyle) + 1).trim() : BuildConfig.VERSION_NAME;
    }

    public static String c(Context context) {
        String deviceId;
        Throwable e;
        CharSequence charSequence = BuildConfig.VERSION_NAME;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (VERSION.SDK_INT >= 23) {
            CharSequence charSequence2;
            try {
                if (a(context, "android.permission.READ_PHONE_STATE")) {
                    deviceId = telephonyManager.getDeviceId();
                    try {
                        v.a(a, new StringBuilder("getDeviceId, IMEI: ").append(deviceId).toString());
                    } catch (Exception e2) {
                        e = e2;
                        v.d("No IMEI.", e);
                        if (TextUtils.isEmpty(deviceId)) {
                            return deviceId;
                        }
                        deviceId = b();
                        v.a(a, new StringBuilder("getDeviceId, mc: ").append(deviceId).toString());
                        if (TextUtils.isEmpty(deviceId)) {
                            return deviceId;
                        }
                        deviceId = Secure.getString(context.getContentResolver(), "android_id");
                        v.a(a, new StringBuilder("getDeviceId, android_id: ").append(deviceId).toString());
                        if (TextUtils.isEmpty(deviceId)) {
                            return deviceId;
                        }
                        if (VERSION.SDK_INT >= 9) {
                            deviceId = Build.SERIAL;
                        }
                        v.a(a, new StringBuilder("getDeviceId, serial no: ").append(deviceId).toString());
                        return deviceId;
                    }
                }
                charSequence2 = charSequence;
            } catch (Throwable e3) {
                Throwable th = e3;
                charSequence2 = charSequence;
                e = th;
                v.d("No IMEI.", e);
                if (TextUtils.isEmpty(deviceId)) {
                    return deviceId;
                }
                deviceId = b();
                v.a(a, new StringBuilder("getDeviceId, mc: ").append(deviceId).toString());
                if (TextUtils.isEmpty(deviceId)) {
                    return deviceId;
                }
                deviceId = Secure.getString(context.getContentResolver(), "android_id");
                v.a(a, new StringBuilder("getDeviceId, android_id: ").append(deviceId).toString());
                if (TextUtils.isEmpty(deviceId)) {
                    return deviceId;
                }
                if (VERSION.SDK_INT >= 9) {
                    deviceId = Build.SERIAL;
                }
                v.a(a, new StringBuilder("getDeviceId, serial no: ").append(deviceId).toString());
                return deviceId;
            }
            if (TextUtils.isEmpty(deviceId)) {
                return deviceId;
            }
            deviceId = b();
            v.a(a, new StringBuilder("getDeviceId, mc: ").append(deviceId).toString());
            if (TextUtils.isEmpty(deviceId)) {
                return deviceId;
            }
            deviceId = Secure.getString(context.getContentResolver(), "android_id");
            v.a(a, new StringBuilder("getDeviceId, android_id: ").append(deviceId).toString());
            if (TextUtils.isEmpty(deviceId)) {
                return deviceId;
            }
            if (VERSION.SDK_INT >= 9) {
                deviceId = Build.SERIAL;
            }
            v.a(a, new StringBuilder("getDeviceId, serial no: ").append(deviceId).toString());
            return deviceId;
        }
        if (telephonyManager == null) {
            v.c(a, "No IMEI.");
        }
        try {
            if (a(context, "android.permission.READ_PHONE_STATE")) {
                charSequence = telephonyManager.getDeviceId();
            }
        } catch (Throwable e32) {
            v.d("No IMEI.", e32);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            return charSequence;
        }
        v.c(a, "No IMEI.");
        deviceId = k(context);
        if (!TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }
        v.c(a, "Failed to take mac as IMEI. Try to use Secure.ANDROID_ID instead.");
        deviceId = Secure.getString(context.getContentResolver(), "android_id");
        v.a(a, new StringBuilder("getDeviceId: Secure.ANDROID_ID: ").append(deviceId).toString());
        return deviceId;
    }

    public static String d(Context context) {
        return u.b(c(context));
    }

    public static String[] e(Context context) {
        String[] strArr = new String[]{BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME};
        try {
            if (a(context, "android.permission.ACCESS_NETWORK_STATE")) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager == null) {
                    strArr[0] = BuildConfig.VERSION_NAME;
                    return strArr;
                } else if (connectivityManager.getNetworkInfo(1).getState() == State.CONNECTED) {
                    strArr[0] = "Wi-Fi";
                    return strArr;
                } else {
                    NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
                    if (networkInfo.getState() == State.CONNECTED) {
                        strArr[0] = "2G/3G";
                        strArr[1] = networkInfo.getSubtypeName();
                        return strArr;
                    }
                    return strArr;
                }
            }
            strArr[0] = BuildConfig.VERSION_NAME;
            return strArr;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean f(Context context) {
        return "Wi-Fi".equals(e(context)[0]);
    }

    public static boolean g(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            return activeNetworkInfo != null ? activeNetworkInfo.isConnectedOrConnecting() : false;
        } catch (Exception e) {
            return true;
        }
    }

    public static int h(Context context) {
        try {
            Calendar instance = Calendar.getInstance(s(context));
            if (instance != null) {
                return instance.getTimeZone().getRawOffset() / 3600000;
            }
        } catch (Throwable e) {
            v.c("error in getTimeZone", e);
        }
        return SpdyProtocol.PUBKEY_SEQ_ADASH;
    }

    public static String[] i(Context context) {
        String[] strArr = new String[2];
        try {
            Locale s = s(context);
            if (s != null) {
                strArr[0] = s.getCountry();
                strArr[1] = s.getLanguage();
            }
            if (TextUtils.isEmpty(strArr[0])) {
                strArr[0] = "Unknown";
            }
            if (TextUtils.isEmpty(strArr[1])) {
                strArr[1] = "Unknown";
            }
        } catch (Throwable e) {
            v.e("error in getLocaleInfo", e);
        }
        return strArr;
    }

    private static Locale s(Context context) {
        Locale locale = null;
        try {
            Configuration configuration = new Configuration();
            configuration.setToDefaults();
            System.getConfiguration(context.getContentResolver(), configuration);
            locale = configuration.locale;
        } catch (Exception e) {
            v.b(a, "fail to read user config locale");
        }
        return locale == null ? Locale.getDefault() : locale;
    }

    public static String j(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), SpdyProtocol.SLIGHTSSLV2);
            if (applicationInfo != null) {
                String string = applicationInfo.metaData.getString("UMENG_APPKEY");
                if (string != null) {
                    return string.trim();
                }
                v.b(a, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.");
            }
        } catch (Throwable e) {
            v.e("Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.", e);
        }
        return null;
    }

    public static String k(Context context) {
        if (VERSION.SDK_INT < 23) {
            return t(context);
        }
        String b = b();
        return b == null ? t(context) : b;
    }

    private static String t(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService(Utility.NETWORK_WIFI);
            if (a(context, "android.permission.ACCESS_WIFI_STATE")) {
                return wifiManager.getConnectionInfo().getMacAddress();
            }
            v.c(a, "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
            return BuildConfig.VERSION_NAME;
        } catch (Exception e) {
            v.c(a, new StringBuilder("Could not get mac address.").append(e.toString()).toString());
            return BuildConfig.VERSION_NAME;
        }
    }

    public static int[] l(Context context) {
        try {
            int a;
            int a2;
            int i;
            Object displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            if ((context.getApplicationInfo().flags & 8192) == 0) {
                a = a(displayMetrics, "noncompatWidthPixels");
                a2 = a(displayMetrics, "noncompatHeightPixels");
            } else {
                a2 = 0;
                a = -1;
            }
            if (a == -1 || a2 == -1) {
                i = displayMetrics.widthPixels;
                a = displayMetrics.heightPixels;
            } else {
                i = a;
                a = a2;
            }
            int[] iArr = new int[2];
            if (i > a) {
                iArr[0] = a;
                iArr[1] = i;
                return iArr;
            }
            iArr[0] = i;
            iArr[1] = a;
            return iArr;
        } catch (Throwable e) {
            v.e("read resolution fail", e);
            return null;
        }
    }

    private static int a(Object obj, String str) {
        try {
            Field declaredField = DisplayMetrics.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.getInt(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String m(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
        } catch (Throwable e) {
            v.c("read carrier fail", e);
            return "Unknown";
        }
    }

    public static String n(Context context) {
        String str = "Unknown";
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), SpdyProtocol.SLIGHTSSLV2);
            if (!(applicationInfo == null || applicationInfo.metaData == null)) {
                Object obj = applicationInfo.metaData.get("UMENG_CHANNEL");
                if (obj != null) {
                    String toString = obj.toString();
                    if (toString != null) {
                        return toString;
                    }
                    v.a(a, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
                }
            }
            return str;
        } catch (Exception e) {
            v.a(a, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
            e.printStackTrace();
            return str;
        }
    }

    public static String o(Context context) {
        return context.getPackageName();
    }

    public static String p(Context context) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), R.styleable.AppCompatTheme_imageButtonStyle);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        InputStream byteArrayInputStream = new ByteArrayInputStream(packageInfo.signatures[0].toByteArray());
        try {
            CertificateFactory instance = CertificateFactory.getInstance("X509");
        } catch (CertificateException e2) {
            e2.printStackTrace();
            instance = null;
        }
        try {
            X509Certificate x509Certificate = (X509Certificate) instance.generateCertificate(byteArrayInputStream);
        } catch (CertificateException e22) {
            e22.printStackTrace();
            x509Certificate = null;
        }
        try {
            return a(MessageDigest.getInstance("MD5").digest(x509Certificate.getEncoded()));
        } catch (NoSuchAlgorithmException e3) {
            e3.printStackTrace();
            return null;
        } catch (CertificateEncodingException e4) {
            e4.printStackTrace();
            return null;
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            String toHexString = Integer.toHexString(bArr[i]);
            int length = toHexString.length();
            if (length == 1) {
                toHexString = new StringBuilder("0").append(toHexString).toString();
            }
            if (length > 2) {
                toHexString = toHexString.substring(length - 2, length);
            }
            stringBuilder.append(toHexString.toUpperCase());
            if (i < bArr.length - 1) {
                stringBuilder.append(':');
            }
        }
        return stringBuilder.toString();
    }

    public static String q(Context context) {
        return context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
    }

    public static String r(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.loadLabel(context.getPackageManager()).toString();
        } catch (Throwable e) {
            v.a(a, e);
            return null;
        }
    }
}
