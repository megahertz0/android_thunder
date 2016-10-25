package com.tencent.open.utils;

import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import anet.channel.security.ISecurity;
import com.alipay.sdk.util.h;
import com.sina.weibo.sdk.component.GameManager;
import com.taobao.agoo.a.a.b;
import com.tencent.bugly.Bugly;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.tencent.open.a.f;
import com.umeng.a;
import com.umeng.socialize.editorpage.ShareActivity;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.regex.Pattern;
import org.android.agoo.message.MessageService;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ProGuard
public class Util {
    private static String a;
    private static String b;
    private static String c;
    private static String d;
    private static int e;
    private static String f;
    private static String g;

    // compiled from: ProGuard
    final class AnonymousClass_1 extends Thread {
        final /* synthetic */ Context a;
        final /* synthetic */ Bundle b;

        AnonymousClass_1(Context context, Bundle bundle) {
            this.a = context;
            this.b = bundle;
        }

        public final void run() {
            try {
                HttpUtils.openUrl2(this.a, "http://cgi.qplus.com/report/report", Constants.HTTP_GET, this.b);
            } catch (Exception e) {
                f.e("openSDK_LOG.Util", new StringBuilder("reportBernoulli has exception: ").append(e.getMessage()).toString());
            }
        }
    }

    // compiled from: ProGuard
    public static class Statistic {
        public long reqSize;
        public String response;
        public long rspSize;

        public Statistic(String str, int i) {
            this.response = str;
            this.reqSize = (long) i;
            if (this.response != null) {
                this.rspSize = (long) this.response.length();
            }
        }
    }

    static {
        a = a.d;
        b = a.d;
        c = a.d;
        d = a.d;
        e = -1;
        g = "0123456789ABCDEF";
    }

    public static Bundle decodeUrl(String str) {
        Bundle bundle = new Bundle();
        if (str == null) {
            return bundle;
        }
        try {
            for (String str2 : str.split(com.alipay.sdk.sys.a.b)) {
                String[] split = str2.split("=");
                if (split.length == 2) {
                    bundle.putString(URLDecoder.decode(split[0]), URLDecoder.decode(split[1]));
                }
            }
            return bundle;
        } catch (Exception e) {
            return null;
        }
    }

    public static JSONObject decodeUrlToJson(JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        if (str != null) {
            String[] split = str.split(com.alipay.sdk.sys.a.b);
            int length = split.length;
            for (int i = 0; i < length; i++) {
                String[] split2 = split[i].split("=");
                if (split2.length == 2) {
                    try {
                        split2[0] = URLDecoder.decode(split2[0]);
                        split2[1] = URLDecoder.decode(split2[1]);
                    } catch (Exception e) {
                    }
                    try {
                        jSONObject.put(split2[0], split2[1]);
                    } catch (JSONException e2) {
                        f.e("openSDK_LOG.Util", new StringBuilder("decodeUrlToJson has exception: ").append(e2.getMessage()).toString());
                    }
                }
            }
        }
        return jSONObject;
    }

    public static Bundle parseUrl(String str) {
        try {
            URL url = new URL(str.replace("auth://", "http://"));
            Bundle decodeUrl = decodeUrl(url.getQuery());
            decodeUrl.putAll(decodeUrl(url.getRef()));
            return decodeUrl;
        } catch (MalformedURLException e) {
            return new Bundle();
        }
    }

    public static JSONObject parseUrlToJson(String str) {
        try {
            URL url = new URL(str.replace("auth://", "http://"));
            JSONObject decodeUrlToJson = decodeUrlToJson(null, url.getQuery());
            decodeUrlToJson(decodeUrlToJson, url.getRef());
            return decodeUrlToJson;
        } catch (MalformedURLException e) {
            return new JSONObject();
        }
    }

    public static JSONObject parseJson(String str) throws JSONException {
        if (str.equals(Bugly.SDK_IS_DEV)) {
            str = "{value : false}";
        }
        if (str.equals("true")) {
            str = "{value : true}";
        }
        if (str.contains("allback(")) {
            str = str.replaceFirst("[\\s\\S]*allback\\(([\\s\\S]*)\\);[^\\)]*\\z", "$1").trim();
        }
        if (str.contains("online[0]=")) {
            str = new StringBuilder("{online:").append(str.charAt(str.length() - 2)).append(h.d).toString();
        }
        return new JSONObject(str);
    }

    public static void showAlert(Context context, String str, String str2) {
        Builder builder = new Builder(context);
        builder.setTitle(str);
        builder.setMessage(str2);
        builder.create().show();
    }

    public static String getUserIp() {
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces != null && networkInterfaces.hasMoreElements()) {
                Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Throwable e) {
            f.a("openSDK_LOG.Util", "getUserIp SocketException ", e);
        }
        return a.d;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    private static boolean a(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.tencent.mtt", R.styleable.AppCompatTheme_imageButtonStyle);
            String str = packageInfo.versionName;
            if (SystemUtils.compareVersion(str, "4.3") < 0 || str.startsWith("4.4")) {
                return false;
            }
            Signature[] signatureArr = packageInfo.signatures;
            if (signatureArr == null) {
                return false;
            }
            try {
                MessageDigest instance = MessageDigest.getInstance(ISecurity.SIGN_ALGORITHM_MD5);
                instance.update(signatureArr[0].toByteArray());
                String toHexString = toHexString(instance.digest());
                instance.reset();
                return toHexString.equals("d8391a394d4a179e6fe7bdb8a301258b");
            } catch (NoSuchAlgorithmException e) {
                f.e("openSDK_LOG.Util", new StringBuilder("isQQBrowerAvailable has exception: ").append(e.getMessage()).toString());
                return false;
            }
        } catch (NameNotFoundException e2) {
            return false;
        }
    }

    public static boolean openBrowser(Context context, String str) {
        try {
            boolean a = a(context);
            if (a) {
                a(context, "com.tencent.mtt", "com.tencent.mtt.MainActivity", str);
            } else {
                a(context, "com.android.browser", "com.android.browser.BrowserActivity", str);
            }
        } catch (Exception e) {
            a = false;
            if (a) {
                a(context, "com.android.browser", "com.android.browser.BrowserActivity", str);
            } else {
                a(context, "com.google.android.browser", "com.android.browser.BrowserActivity", str);
            }
            return true;
        }
        return true;
    }

    private static void a(Context context, String str, String str2, String str3) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(str, str2));
        intent.setAction("android.intent.action.VIEW");
        intent.addFlags(1073741824);
        intent.addFlags(268435456);
        intent.setData(Uri.parse(str3));
        context.startActivity(intent);
    }

    public static boolean isMobileQQSupportShare(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (isTablet(context)) {
            try {
                packageManager.getPackageInfo(Constants.PACKAGE_QQ_PAD, 0);
                return true;
            } catch (NameNotFoundException e) {
            }
        }
        try {
            return SystemUtils.compareVersion(packageManager.getPackageInfo(Constants.PACKAGE_QQ, 0).versionName, "4.1") >= 0;
        } catch (Throwable e2) {
            f.b("openSDK_LOG.Util", "NameNotFoundException", e2);
            return false;
        }
    }

    public static String encrypt(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(ISecurity.SIGN_ALGORITHM_MD5);
            instance.update(getBytesUTF8(str));
            byte[] digest = instance.digest();
            if (digest == null) {
                return str;
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int i : digest) {
                stringBuilder.append(a(i >>> 4));
                stringBuilder.append(a(i));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            f.e("openSDK_LOG.Util", new StringBuilder("encrypt has exception: ").append(e.getMessage()).toString());
            return str;
        }
    }

    private static char a(int i) {
        int i2 = i & 15;
        return i2 < 10 ? (char) (i2 + 48) : (char) ((i2 - 10) + 97);
    }

    public static void reportBernoulli(Context context, String str, long j, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("appid_for_getting_config", str2);
        bundle.putString("strValue", str2);
        bundle.putString("nValue", str);
        bundle.putString("qver", Constants.SDK_VERSION);
        if (j != 0) {
            bundle.putLong("elt", j);
        }
        new AnonymousClass_1(context, bundle).start();
    }

    public static boolean hasSDCard() {
        File file = null;
        if (Environment.getExternalStorageState().equals("mounted")) {
            file = Environment.getExternalStorageDirectory();
        }
        return file != null;
    }

    public static String toHexString(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            String toString = Integer.toString(b & 255, R.styleable.Toolbar_titleMarginBottom);
            if (toString.length() == 1) {
                toString = new StringBuilder(MessageService.MSG_DB_READY_REPORT).append(toString).toString();
            }
            stringBuilder.append(toString);
        }
        return stringBuilder.toString();
    }

    public static String toHexString(String str) {
        byte[] bytesUTF8 = getBytesUTF8(str);
        StringBuilder stringBuilder = new StringBuilder(bytesUTF8.length * 2);
        for (int i = 0; i < bytesUTF8.length; i++) {
            stringBuilder.append(g.charAt((bytesUTF8[i] & 240) >> 4));
            stringBuilder.append(g.charAt((bytesUTF8[i] & 15) >> 0));
        }
        return stringBuilder.toString();
    }

    public static String hexToString(String str) {
        int i = 0;
        if ("0x".equals(str.substring(0, XZBDevice.DOWNLOAD_LIST_RECYCLE))) {
            str = str.substring(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        }
        byte[] bArr = new byte[(str.length() / 2)];
        while (i < bArr.length) {
            try {
                bArr[i] = (byte) (Integer.parseInt(str.substring(i * 2, (i * 2) + 2), R.styleable.Toolbar_titleMarginBottom) & 255);
            } catch (Exception e) {
                f.e("openSDK_LOG.Util", new StringBuilder("hexToString has exception: ").append(e.getMessage()).toString());
            }
            i++;
        }
        try {
            str = new String(bArr, "utf-8");
            return str;
        } catch (Exception e2) {
            f.e("openSDK_LOG.Util", new StringBuilder("hexToString has exception: ").append(e2.getMessage()).toString());
            return str;
        }
    }

    public static String getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            f.e("openSDK_LOG.Util", new StringBuilder("getAppVersion error").append(e.getMessage()).toString());
            return a.d;
        }
    }

    public static final String getApplicationLable(Context context) {
        if (context != null) {
            CharSequence applicationLabel = context.getPackageManager().getApplicationLabel(context.getApplicationInfo());
            if (applicationLabel != null) {
                return applicationLabel.toString();
            }
        }
        return null;
    }

    public static final boolean isValidUrl(String str) {
        if (str == null) {
            return false;
        }
        return str.startsWith("http://") || str.startsWith("https://");
    }

    public static boolean fileExists(String str) {
        return str != null && new File(str).exists();
    }

    public static final String subString(String str, int i, String str2, String str3) {
        int i2 = 0;
        if (TextUtils.isEmpty(str)) {
            return a.d;
        }
        String str4 = GameManager.DEFAULT_CHARSET;
        if (TextUtils.isEmpty(str2)) {
            str2 = str4;
        }
        try {
            if (str.getBytes(str2).length <= i) {
                return str;
            }
            int i3 = 0;
            while (i2 < str.length()) {
                int length = str.substring(i2, i2 + 1).getBytes(str2).length;
                if (i3 + length > i) {
                    String substring = str.substring(0, i2);
                    try {
                        if (!TextUtils.isEmpty(str3)) {
                            substring = substring + str3;
                        }
                        return substring;
                    } catch (Exception e) {
                        str = substring;
                        Exception exception = e;
                        System.out.println(new StringBuilder("StructMsg sSubString error : ").append(exception.getMessage()).toString());
                        return str;
                    }
                }
                i3 += length;
                i2++;
            }
            return str;
        } catch (Exception e2) {
            exception = e2;
            System.out.println(new StringBuilder("StructMsg sSubString error : ").append(exception.getMessage()).toString());
            return str;
        }
    }

    public static int parseIntValue(String str) {
        return parseIntValue(str, 0);
    }

    public static int parseIntValue(String str, int i) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return i;
        }
    }

    public static boolean checkNetWork(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return true;
        }
        NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
        if (allNetworkInfo == null) {
            return false;
        }
        for (int i = 0; i < allNetworkInfo.length; i++) {
            if (allNetworkInfo[i].isConnectedOrConnecting()) {
                return true;
            }
        }
        return false;
    }

    public static Bundle composeViaReportParams(String str, String str2, String str3, String str4, String str5, String str6) {
        return composeViaReportParams(str, str3, str4, str2, str5, str6, a.d, a.d, a.d, a.d, a.d, a.d);
    }

    public static Bundle composeViaReportParams(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12) {
        Bundle bundle = new Bundle();
        bundle.putString(SocialConstants.PARAM_OPEN_ID, str);
        bundle.putString("report_type", str2);
        bundle.putString("act_type", str3);
        bundle.putString("via", str4);
        bundle.putString("app_id", str5);
        bundle.putString("result", str6);
        bundle.putString(JsInterface.FUNPLAY_AD_TRPE, str7);
        bundle.putString("login_status", str8);
        bundle.putString("need_user_auth", str9);
        bundle.putString("to_uin", str10);
        bundle.putString("call_source", str11);
        bundle.putString("to_type", str12);
        return bundle;
    }

    public static Bundle composeHaboCgiReportParams(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.PARAM_PLATFORM, MessageService.MSG_DB_NOTIFY_REACHED);
        bundle.putString("result", str);
        bundle.putString(com.taobao.accs.common.Constants.KEY_HTTP_CODE, str2);
        bundle.putString("tmcost", str3);
        bundle.putString("rate", str4);
        bundle.putString(b.JSON_CMD, str5);
        bundle.putString("uin", str6);
        bundle.putString(SocialConstants.PARAM_APP_ID, str7);
        bundle.putString("share_type", str8);
        bundle.putString(JsInterface.PAGE_DETAIL, str9);
        bundle.putString("os_ver", VERSION.RELEASE);
        bundle.putString("network", com.tencent.open.b.a.a(Global.getContext()));
        bundle.putString("apn", com.tencent.open.b.a.b(Global.getContext()));
        bundle.putString("model_name", Build.MODEL);
        bundle.putString("sdk_ver", Constants.SDK_VERSION);
        bundle.putString(LogBuilder.KEY_PACKAGE_NAME, Global.getPackageName());
        bundle.putString("app_ver", getAppVersionName(Global.getContext(), Global.getPackageName()));
        return bundle;
    }

    public static String getLocation(Context context) {
        if (context == null) {
            return a.d;
        }
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService(ShareActivity.KEY_LOCATION);
            Criteria criteria = new Criteria();
            criteria.setCostAllowed(false);
            criteria.setAccuracy(XZBDevice.DOWNLOAD_LIST_RECYCLE);
            String bestProvider = locationManager.getBestProvider(criteria, true);
            if (bestProvider != null) {
                Location lastKnownLocation = locationManager.getLastKnownLocation(bestProvider);
                if (lastKnownLocation == null) {
                    return a.d;
                }
                double latitude = lastKnownLocation.getLatitude();
                String str = latitude + "*" + lastKnownLocation.getLongitude();
                f = str;
                return str;
            }
        } catch (Throwable e) {
            f.b("openSDK_LOG.Util", "getLocation>>>", e);
        }
        return a.d;
    }

    public static void getPackageInfo(Context context, String str) {
        if (context != null) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
                String str2 = packageInfo.versionName;
                b = str2;
                a = str2.substring(0, b.lastIndexOf(R.styleable.AppCompatTheme_dropdownListPreferredItemHeight));
                d = b.substring(b.lastIndexOf(R.styleable.AppCompatTheme_dropdownListPreferredItemHeight) + 1, b.length());
                e = packageInfo.versionCode;
            } catch (NameNotFoundException e) {
                f.e("openSDK_LOG.Util", new StringBuilder("getPackageInfo has exception: ").append(e.getMessage()).toString());
            } catch (Exception e2) {
                f.e("openSDK_LOG.Util", new StringBuilder("getPackageInfo has exception: ").append(e2.getMessage()).toString());
            }
        }
    }

    public static String getVersionName(Context context, String str) {
        if (context == null) {
            return a.d;
        }
        getPackageInfo(context, str);
        return b;
    }

    public static String getAppVersionName(Context context, String str) {
        if (context == null) {
            return a.d;
        }
        getPackageInfo(context, str);
        return a;
    }

    public static String getQUA3(Context context, String str) {
        if (context == null) {
            return a.d;
        }
        String appVersionName = getAppVersionName(context, str);
        c = appVersionName;
        return appVersionName;
    }

    public static byte[] getBytesUTF8(String str) {
        try {
            return str.getBytes(GameManager.DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static boolean isNumeric(String str) {
        return Pattern.compile("[0-9]*").matcher(str).matches();
    }

    public static boolean isTablet(Context context) {
        double d = 0.0d;
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float f = ((float) displayMetrics.widthPixels) / displayMetrics.xdpi;
            d = Math.sqrt(Math.pow((double) (((float) displayMetrics.heightPixels) / displayMetrics.ydpi), 2.0d) + Math.pow((double) f, 2.0d));
        } catch (Throwable th) {
        }
        return d > 6.5d;
    }

    public static boolean isQQVersionBelow(Context context, String str) {
        if (isTablet(context)) {
            if (SystemUtils.getAppVersionName(context, Constants.PACKAGE_QQ_PAD) == null && SystemUtils.compareQQVersion(context, str) < 0) {
                return true;
            }
        } else if (SystemUtils.compareQQVersion(context, str) < 0) {
            return true;
        }
        return false;
    }
}
