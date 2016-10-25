package com.baidu.mobads.j;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import anet.channel.security.ISecurity;
import com.baidu.mobads.interfaces.utils.IXAdCommonUtils;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.sina.weibo.sdk.component.GameManager;
import com.sina.weibo.sdk.register.mobile.SelectCountryActivity;
import com.umeng.a;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.common.SocializeConstants;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;
import org.android.agoo.message.MessageService;
import org.android.spdy.SpdyAgent;
import org.json.JSONArray;
import org.json.JSONObject;

public class d implements IXAdCommonUtils {
    private static String a;
    private static String b;
    private static String c;
    private static String d;
    private final String e;
    private final AtomicLong f;
    private Method g;
    private String h;
    private HashMap<String, Object> i;

    public d() {
        this.e = "_cpr";
        this.f = new AtomicLong(1);
        this.g = null;
        this.h = null;
        this.i = new HashMap();
    }

    public int getApkDownloadStatus(Context context, String str, String str2) {
        if (str == null || a.d.equals(str)) {
            return -1;
        }
        try {
            JSONObject optJSONObject = new JSONObject(context.getSharedPreferences(IXAdCommonUtils.PKGS_PREF_ACTIVATION, 0).getString(IXAdCommonUtils.PKGS_PREF_DOWNLOAD_KEY, "{}")).optJSONObject(str);
            if (optJSONObject != null && optJSONObject.optBoolean("a", false)) {
                return R.styleable.AppCompatTheme_buttonStyle;
            }
        } catch (Exception e) {
        }
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(IXAdCommonUtils.PKGS_PREF_DOWNLOAD, 0);
            JSONObject jSONObject = new JSONObject(sharedPreferences.getString(str + "#$#" + com.baidu.mobads.command.a.b(), "{}"));
            int i = jSONObject.getInt(IXAdCommonUtils.PKGS_PREF_DOWNLOAD_STATUS);
            String optString = jSONObject.optString(SelectCountryActivity.EXTRA_COUNTRY_NAME, null);
            if (optString == null) {
                return i;
            }
            File file = new File(m.a().k().getStoreagePath(context) + optString);
            if (i != 3) {
                return i;
            }
            Object obj = null;
            if (!file.exists() || file.length() == 0) {
                obj = 1;
            } else {
                long optLong = jSONObject.optLong("contentLength", -1);
                if (optLong != -1 && Math.abs(optLong - file.length()) >= 2) {
                    obj = 1;
                }
            }
            if (obj == null) {
                return i;
            }
            jSONObject.put(IXAdCommonUtils.PKGS_PREF_DOWNLOAD_STATUS, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
            Editor edit = sharedPreferences.edit();
            edit.putString(str, jSONObject.toString());
            if (VERSION.SDK_INT >= 9) {
                edit.apply();
                return XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
            }
            edit.commit();
            return XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
        } catch (Exception e2) {
            return -1;
        }
    }

    public String getStatusStr(Context context, String str, String str2) {
        try {
            int apkDownloadStatus = getApkDownloadStatus(context, str, str2);
            boolean isInstalled = m.a().l().isInstalled(context, str);
            switch (apkDownloadStatus) {
                case SpdyAgent.ACCS_TEST_SERVER:
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    return isInstalled ? "INSTALLED_BY_OTHER" : "DOWNLOADING";
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    return isInstalled ? "INSTALLED_BY_OTHER" : "DOWNLOAD_FAILED";
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    return isInstalled ? "INSTALLED" : "DOWNLOADED";
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    return isInstalled ? "DONE" : "NONE";
                case R.styleable.AppCompatTheme_buttonStyle:
                    return "DONE";
                default:
                    return isInstalled ? "INSTALLED_BY_OTHER" : "NONE";
            }
        } catch (Exception e) {
            return "NONE";
        }
    }

    public String getMD5(String str) {
        byte[] bytes = str.getBytes();
        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest instance = MessageDigest.getInstance(ISecurity.SIGN_ALGORITHM_MD5);
            instance.update(bytes);
            byte[] digest = instance.digest();
            char[] cArr2 = new char[32];
            int i = 0;
            for (int i2 = 0; i2 < 16; i2++) {
                byte b = digest[i2];
                int i3 = i + 1;
                cArr2[i] = cArr[(b >>> 4) & 15];
                i = i3 + 1;
                cArr2[i3] = cArr[b & 15];
            }
            return new String(cArr2);
        } catch (NoSuchAlgorithmException e) {
            m.a().f().e("AdUtil.getMD5", a.d, e);
            return null;
        }
    }

    private String c(String str) {
        return getMD5(str);
    }

    private String a(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String toString = context.getFilesDir().toString();
            stringBuilder.append(toString.toString().substring(0, toString.toString().lastIndexOf(File.separator)));
        } catch (Exception e) {
        }
        stringBuilder.append(File.separator);
        stringBuilder.append("bddownload");
        return stringBuilder.toString();
    }

    public String getFileLocalFullPath(Context context, String str) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(a(context));
            stringBuilder.append(File.separator);
            stringBuilder.append(c(str));
            return stringBuilder.toString();
        } catch (Exception e) {
            return a.d;
        }
    }

    public boolean isStringAvailable(String str) {
        return str != null && str.length() > 0;
    }

    public void makeCall(Context context, String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                Intent intent = new Intent("android.intent.action.CALL", Uri.parse(new StringBuilder("tel:").append(str).toString().toString()));
                intent.addFlags(268435456);
                a(context, intent);
            }
        } catch (Throwable e) {
            m.a().f().d(e);
        }
    }

    public void sendSMS(Context context, String str, String str2) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.putExtra("address", str);
            intent.putExtra("sms_body", str2);
            intent.setType("vnd.android-dir/mms-sms");
            intent.addFlags(268435456);
            a(context, intent);
        } catch (Throwable e) {
            m.a().f().d(e);
        }
    }

    @TargetApi(4)
    private void a(Context context, Intent intent) {
        try {
            if (VERSION.SDK_INT < 19) {
                context.startActivity(intent);
            } else {
                new Handler(context.getMainLooper()).post(new e(this, context, intent));
            }
        } catch (Throwable e) {
            m.a().f().d(e);
        }
    }

    public String getDebugToken(Context context) {
        try {
            if (b == null) {
                b = a(context, IXAdCommonUtils.DEBUG_TOKEN);
            }
            return b;
        } catch (Exception e) {
            return a.d;
        }
    }

    public String getAppId(Context context) {
        try {
            if (a == null) {
                a = a(context, IXAdCommonUtils.APPSID);
            }
            return a;
        } catch (Exception e) {
            return a.d;
        }
    }

    public String getAppSec(Context context) {
        if (c == null || c.length() == 0 || c.startsWith("null")) {
            setAppSec(getAppId(context));
        }
        return c;
    }

    public void setAppSec(String str) {
        c = str + "_cpr";
    }

    private String a(Context context, String str) {
        String str2 = "error";
        try {
            str2 = String.valueOf(context.getPackageManager().getApplicationInfo(context.getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS).metaData.get(str));
            if (str2.trim().equals(a.d)) {
                throw new Exception();
            }
        } catch (Exception e) {
            String.format("Could not read %s meta-data from AndroidManifest.xml", new Object[]{str});
        }
        return str2;
    }

    public String md5(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(ISecurity.SIGN_ALGORITHM_MD5);
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < digest.length; i++) {
                String toHexString = Integer.toHexString(digest[i] & 255);
                while (toHexString.length() < 2) {
                    toHexString = new StringBuilder(MessageService.MSG_DB_READY_REPORT).append(toHexString).toString();
                }
                stringBuffer.append(toHexString);
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            return a.d;
        }
    }

    public long generateUniqueId() {
        long j;
        long j2;
        do {
            j = this.f.get();
            j2 = j + 1;
            if (j2 > 9223372036854775806L) {
                j2 = 1;
            }
        } while (!this.f.compareAndSet(j, j2));
        return j;
    }

    public boolean bitMaskContainsFlag(int i, int i2) {
        return (i & i2) != 0;
    }

    @TargetApi(17)
    public Rect getScreenRect(Context context) {
        DisplayMetrics displayMetrics = getDisplayMetrics(context);
        try {
            return displayMetrics.widthPixels > displayMetrics.heightPixels ? new Rect(0, 0, displayMetrics.heightPixels, displayMetrics.widthPixels) : new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        } catch (Exception e) {
            return null;
        }
    }

    public Rect getWindowRect(Context context) {
        DisplayMetrics displayMetrics = getDisplayMetrics(context);
        return new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    @TargetApi(4)
    public float getScreenDensity(Context context) {
        return getDisplayMetrics(context).density;
    }

    @TargetApi(17)
    public DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (VERSION.SDK_INT >= 17) {
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
        } else {
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        }
        return displayMetrics;
    }

    public int getLogicalPixel(Context context, int i) {
        try {
            i = (int) (((float) i) / getScreenDensity(context));
            return i;
        } catch (Exception e) {
            return i;
        }
    }

    public int getPixel(Context context, int i) {
        try {
            i = (int) (((float) i) * getScreenDensity(context));
            return i;
        } catch (Exception e) {
            return i;
        }
    }

    public String getTextEncoder(String str) {
        if (str == null || a.d.equals(str)) {
            return a.d;
        }
        try {
            return URLEncoder.encode(str, GameManager.DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            return str;
        } catch (NullPointerException e2) {
            return str;
        }
    }

    public String getSubscriberId(Context context) {
        if (this.h == null) {
            try {
                if (hasPermission(context, MsgConstant.PERMISSION_READ_PHONE_STATE)) {
                    this.h = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
                }
            } catch (Exception e) {
            }
        }
        return b(this.h);
    }

    public String getAppPackage(Context context) {
        return context.getPackageName();
    }

    public boolean hasPermission(Context context, String str) {
        try {
            return context.checkCallingOrSelfPermission(str) == 0;
        } catch (Throwable e) {
            j.a().e(e);
            return false;
        }
    }

    public String encodeURIComponent(String str) {
        try {
            return URLEncoder.encode(str, GameManager.DEFAULT_CHARSET).replaceAll("\\+", "%20").replaceAll("\\%21", "!").replaceAll("\\%27", "'").replaceAll("\\%28", SocializeConstants.OP_OPEN_PAREN).replaceAll("\\%29", SocializeConstants.OP_CLOSE_PAREN).replaceAll("\\%7E", "~");
        } catch (Exception e) {
            return str;
        }
    }

    public String decodeURIComponent(String str) {
        if (str == null) {
            return null;
        }
        try {
            return URLDecoder.decode(str, GameManager.DEFAULT_CHARSET);
        } catch (Exception e) {
            return str;
        }
    }

    public String vdUrl(String str, int i) {
        int i2 = 0;
        m.a().i();
        JSONObject jSONObject = new JSONObject();
        String[] split = str.substring(str.indexOf("?") + 1).split(com.alipay.sdk.sys.a.b);
        while (i2 < split.length) {
            try {
                String[] split2 = split[i2].split("=");
                if (split2.length <= 1 || split2[0].equals(JsInterface.FUNPLAY_AD_TRPE)) {
                    i2++;
                } else {
                    jSONObject.putOpt(split2[0], split2[1]);
                    i2++;
                }
            } catch (Exception e) {
            }
        }
        StringBuilder stringBuilder = new StringBuilder(new StringBuilder("type=").append(i).append(com.alipay.sdk.sys.a.b).toString());
        Map treeMap = new TreeMap();
        StringBuilder stringBuilder2 = new StringBuilder();
        try {
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                try {
                    String str2 = (String) keys.next();
                    if (str2 != null && !str2.equals(a.d)) {
                        treeMap.put(str2, jSONObject.optString(str2));
                    }
                } catch (Exception e2) {
                }
            }
        } catch (Exception e3) {
        }
        treeMap.put(MsgConstant.KEY_TS, System.currentTimeMillis());
        for (String str22 : treeMap.keySet()) {
            String str3 = (String) treeMap.get(str22);
            if (str22 != null && str3 != null) {
                if (!str22.equals("targetscheme")) {
                    str22 = encodeURIComponent(str22);
                    str3 = encodeURIComponent(str3);
                }
                stringBuilder.append(str22 + "=" + str3 + com.alipay.sdk.sys.a.b);
                stringBuilder2.append(str3 + MiPushClient.ACCEPT_TIME_SEPARATOR);
            }
        }
        stringBuilder2.append("mobads,");
        stringBuilder.append(new StringBuilder("vd=").append(getMD5(stringBuilder2.toString())).append(com.alipay.sdk.sys.a.b).toString());
        return new StringBuilder("https://mobads-logs.baidu.com/dz.zb?").append(stringBuilder.toString()).toString();
    }

    public String getChannelId() {
        return d;
    }

    public void setChannelId(String str) {
        d = str;
    }

    public String getBaiduMapsInfo(Context context) {
        Object a = a("mapinfo");
        if (a != null) {
            return String.valueOf(a);
        }
        String str = a.d;
        try {
            str = new com.baidu.mobads.i.a(context).a();
        } catch (Throwable e) {
            j.a().e(e);
        }
        a("mapinfo", a);
        return a;
    }

    public void a(String str, Object obj) {
        this.i.put(str + "_E", Long.valueOf(System.currentTimeMillis() + 5000));
        this.i.put(str + "_V", obj);
    }

    public Object a(String str) {
        try {
            Object obj = this.i.get(str + "_E");
            if (obj != null) {
                if (System.currentTimeMillis() < ((Long) obj).longValue()) {
                    return this.i.get(str + "_V");
                }
            }
        } catch (Throwable e) {
            j.a().e(e);
        }
        return null;
    }

    public void setAppId(String str) {
        a = str;
    }

    @TargetApi(3)
    private static String b(Context context) {
        String[] supportedBrowsers = m.a().p().getSupportedBrowsers();
        try {
            int i;
            PackageManager packageManager = context.getPackageManager();
            List arrayList = new ArrayList();
            List<ComponentName> arrayList2 = new ArrayList();
            packageManager.getPreferredActivities(arrayList, arrayList2, null);
            for (ComponentName componentName : arrayList2) {
                for (i = 0; i < supportedBrowsers.length; i++) {
                    String str = supportedBrowsers[i];
                    if (str.equals(componentName.getPackageName())) {
                        m.a().f().d(str, "\u89c4\u52191 hit!");
                        return str;
                    }
                }
            }
            try {
                int i2 = -1;
                for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                    try {
                        if (packageManager.getLaunchIntentForPackage(runningAppProcessInfo.processName) != null && packageManager.getApplicationInfo(runningAppProcessInfo.processName, AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS) != null) {
                            for (i = 0; i < supportedBrowsers.length; i++) {
                                if (runningAppProcessInfo.processName.equals(supportedBrowsers[i])) {
                                    if (i2 == -1) {
                                        i2 = i;
                                    } else if (i < i2) {
                                        i2 = i;
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                    }
                }
            } catch (Exception e2) {
                i2 = -1;
            }
            if (i2 != -1) {
                return supportedBrowsers[i2];
            }
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse("http://m.baidu.com"));
            List queryIntentActivities = packageManager.queryIntentActivities(intent, R.styleable.AppCompatTheme_imageButtonStyle);
            if (queryIntentActivities != null) {
                for (i2 = 0; i2 < supportedBrowsers.length; i2++) {
                    for (i = 0; i < queryIntentActivities.size(); i++) {
                        String str2 = ((ResolveInfo) queryIntentActivities.get(i)).activityInfo.packageName;
                        String str3 = supportedBrowsers[i2];
                        if (str3.equals(str2)) {
                            m.a().f().d(str3, "\u89c4\u52192 hit!");
                            return str3;
                        }
                    }
                }
            }
            if (queryIntentActivities != null) {
                if (queryIntentActivities.size() > 0) {
                    return ((ResolveInfo) queryIntentActivities.get(0)).activityInfo.packageName;
                }
            }
            return a.d;
        } catch (Exception e3) {
            com.baidu.mobads.c.a.a().a(new StringBuilder("open browser outside failed: ").append(e3.toString()).toString());
        }
    }

    @TargetApi(3)
    public void browserOutside(Context context, String str) {
        if (str.startsWith("wtai://wp/mc;")) {
            str = new StringBuilder("tel:").append(str.substring(XZBDevice.Upload)).toString();
        }
        try {
            Intent intent;
            Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(str));
            String b;
            if (m.a().i().isHttpProtocol(str).booleanValue()) {
                b = b(context);
                m.a().f().d("Utils", new StringBuilder("AdUtil.browserOutside pkgOfBrowser=").append(b).toString());
                if (b.equals(a.d)) {
                    intent = intent2;
                } else {
                    intent = context.getPackageManager().getLaunchIntentForPackage(b);
                    intent.setData(Uri.parse(str));
                    intent.setAction("android.intent.action.VIEW");
                }
                intent2 = intent;
            } else if (((r) m.a().i()).a(str).booleanValue()) {
                intent2.setType("vnd.android-dir/mms-sms");
                String substring = str.substring(XZBDevice.DOWNLOAD_LIST_ALL, str.indexOf(R.styleable.AppCompatTheme_editTextBackground) > 0 ? str.indexOf(R.styleable.AppCompatTheme_editTextBackground) : str.length());
                intent2.putExtra("address", substring);
                int indexOf = str.indexOf("body=") + 5;
                b = a.d;
                if (indexOf > 5) {
                    int indexOf2 = str.indexOf(XZBDevice.FailInServer, indexOf);
                    if (indexOf2 <= 0) {
                        indexOf2 = str.length();
                    }
                    b = str.substring(indexOf, indexOf2);
                    intent2.putExtra("sms_body", Uri.decode(b));
                }
                m.a().f().d(substring, b);
            }
            if (context.getPackageManager().resolveActivity(intent2, AccessibilityNodeInfoCompat.ACTION_CUT) != null) {
                context.startActivity(intent2);
            }
        } catch (Exception e) {
            m.a().f().d("XAdCommonUtils.browserOutside 1", str, e);
            try {
                intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                intent.addFlags(268435456);
                context.startActivity(intent);
            } catch (Exception e2) {
                m.a().f().d("XAdCommonUtils.browserOutside 2", str, e2);
            }
        }
    }

    public String a() {
        return "android_8.27_4.0.0";
    }

    public boolean hasSupportedApps(Context context, int i) {
        try {
            Intent intent;
            IXAdSystemUtils n = m.a().n();
            switch (i) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    intent = new Intent("android.intent.action.SENDTO");
                    intent.setData(Uri.parse("mailto:baidumobadstest@baidu.com"));
                    break;
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    intent = new Intent("android.intent.action.SENDTO");
                    intent.setData(Uri.parse("sms:12345678"));
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    return m.a().m().hasPermission(context, MsgConstant.PERMISSION_ACCESS_WIFI_STATE) && m.a().m().hasPermission(context, "android.permission.CHANGE_WIFI_STATE");
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    return n.canSupportSdcardStroage(context);
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    intent = new Intent("android.intent.action.EDIT");
                    intent.setType("vnd.android.cursor.item/event");
                    break;
                default:
                    return false;
            }
            ArrayList arrayList = new ArrayList();
            List queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, R.styleable.AppCompatTheme_imageButtonStyle);
            return (queryIntentActivities == null || queryIntentActivities.size() <= 0 || ((ResolveInfo) queryIntentActivities.get(0)).activityInfo.packageName.equals("com.android.fallback")) ? false : true;
        } catch (Exception e) {
            return false;
        }
    }

    public JSONArray list2Json(List<String[]> list) {
        JSONArray jSONArray = new JSONArray();
        int i = 0;
        while (i < list.size()) {
            try {
                JSONArray jSONArray2 = new JSONArray();
                for (int i2 = 0; i2 < ((String[]) list.get(i)).length; i2++) {
                    jSONArray2.put(((String[]) list.get(i))[i2]);
                }
                jSONArray.put(jSONArray2);
                i++;
            } catch (Throwable e) {
                m.a().f().d(e);
            }
        }
        return jSONArray;
    }

    public String base64Encode(String str) {
        return m.a().e().encode(str);
    }

    public JSONArray array2Json(double[] dArr) {
        Object obj = null;
        if (dArr == null) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray();
            int i = 0;
            while (i < dArr.length) {
                try {
                    jSONArray.put(dArr[i]);
                    i++;
                } catch (Exception e) {
                    Throwable e2 = e;
                }
            }
            return jSONArray;
        } catch (Throwable e3) {
            Throwable th = e3;
            jSONArray = i;
            e2 = th;
            m.a().f().d(e2);
            return jSONArray;
        }
    }

    public String getLocationInfo(Context context) {
        return getBaiduMapsInfo(context);
    }

    public String getApkFileLocalPath(Context context, String str) {
        try {
            Object string = context.getSharedPreferences(IXAdCommonUtils.PKGS_PREF_DOWNLOAD, 0).getString(str + "#$#" + com.baidu.mobads.command.a.b(), a.d);
            if (!TextUtils.isEmpty(string)) {
                JSONObject jSONObject = new JSONObject(string);
                string = jSONObject.optString("folder");
                Object optString = jSONObject.optString("filename");
                if (!(TextUtils.isEmpty(string) || TextUtils.isEmpty(optString))) {
                    return string + optString;
                }
            }
        } catch (Throwable e) {
            m.a().f().d(e);
        }
        return a.d;
    }

    public void installApp(Context context, String str, File file, boolean z) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addFlags(268435456);
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            context.startActivity(intent);
        } catch (Throwable e) {
            m.a().f().d(e);
        }
    }

    public boolean isOldPermissionModel() {
        return VERSION.SDK_INT < 23;
    }

    public boolean checkSelfPermission(Context context, String str) {
        try {
            return ((Integer) Context.class.getMethod("checkSelfPermission", new Class[]{String.class}).invoke(context, new Object[]{str})).intValue() == 0;
        } catch (Throwable e) {
            m.a().f().d(e);
            return true;
        }
    }

    public void a(Context context, String[] strArr, int i) {
        try {
            Activity.class.getMethod("requestPermissions", new Class[]{String[].class, Integer.TYPE}).invoke(context, new Object[]{strArr, Integer.valueOf(i)});
        } catch (Throwable e) {
            m.a().f().d(e);
        }
    }

    public String createRequestId(Context context, String str) {
        return getMD5(m.a().n().getIMEI(context) + getAppId(context) + str + System.currentTimeMillis());
    }

    public String b(String str) {
        return str == null ? a.d : str;
    }

    public void a(Runnable runnable) {
        if (runnable != null) {
            try {
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    runnable.run();
                } else {
                    new Handler(Looper.getMainLooper()).post(new f(this, runnable));
                }
            } catch (Throwable e) {
                m.a().f().d(e);
            }
        }
    }

    public int getStatusBarHeight(Activity activity) {
        try {
            Rect rect = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            return rect.top;
        } catch (Exception e) {
            return 0;
        }
    }

    public Class<?> a(Object obj) {
        try {
            return Class.forName(obj.getClass().getName());
        } catch (Throwable e) {
            m.a().f().d(e);
            return null;
        }
    }

    public Method a(Object obj, String str, Class<?>... clsArr) {
        try {
            Method declaredMethod = a(obj).getDeclaredMethod(str, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (Throwable e) {
            m.a().f().d(e);
            return null;
        }
    }

    public Object a(Object obj, String str, Object... objArr) {
        try {
            Class[] clsArr = new Class[objArr.length];
            for (int i = 0; i < clsArr.length; i++) {
                clsArr[i] = objArr[i].getClass();
            }
            return a(obj, str, clsArr).invoke(obj, objArr);
        } catch (Throwable e) {
            m.a().f().d(e);
            return null;
        }
    }

    public void a(View view) {
        if (view != null) {
            try {
                if (view.getParent() != null) {
                    ((ViewGroup) view.getParent()).removeView(view);
                }
            } catch (Throwable e) {
                m.a().f().d(e);
            }
        }
    }
}
