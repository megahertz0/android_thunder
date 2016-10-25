package com.umeng.analytics.social;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.mediaserver.Utility;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.android.spdy.SpdyProtocol;
import org.eclipse.paho.client.mqttv3.MqttTopic;

// compiled from: UMUtils.java
public abstract class f {
    private static Map<String, String> a;

    protected static String[] a(Context context, String str, UMPlatformData... uMPlatformDataArr) throws a {
        if (uMPlatformDataArr == null || uMPlatformDataArr.length == 0) {
            throw new a("platform data is null");
        }
        Object a = a(context);
        if (TextUtils.isEmpty(a)) {
            throw new a("can`t get appkey.");
        }
        List arrayList = new ArrayList();
        String toString = new StringBuilder("http://log.umsns.com/share/api/").append(a).append(MqttTopic.TOPIC_LEVEL_SEPARATOR).toString();
        if (a == null || a.isEmpty()) {
            a = c(context);
        }
        if (!(a == null || a.isEmpty())) {
            for (Entry entry : a.entrySet()) {
                arrayList.add(((String) entry.getKey()) + "=" + ((String) entry.getValue()));
            }
        }
        arrayList.add(new StringBuilder("date=").append(String.valueOf(System.currentTimeMillis())).toString());
        arrayList.add(new StringBuilder("channel=").append(e.e).toString());
        if (!TextUtils.isEmpty(str)) {
            arrayList.add(new StringBuilder("topic=").append(str).toString());
        }
        arrayList.addAll(a(uMPlatformDataArr));
        String b = b(uMPlatformDataArr);
        if (b == null) {
            b = "null";
        }
        String str2 = toString + "?" + a(arrayList);
        while (str2.contains("%2C+")) {
            str2 = str2.replace("%2C+", "&");
        }
        while (str2.contains("%3D")) {
            str2 = str2.replace("%3D", "=");
        }
        while (str2.contains("%5B")) {
            str2 = str2.replace("%5B", BuildConfig.VERSION_NAME);
        }
        while (str2.contains("%5D")) {
            str2 = str2.replace("%5D", BuildConfig.VERSION_NAME);
        }
        b.c("MobclickAgent", new StringBuilder("URL:").append(str2).toString());
        b.c("MobclickAgent", new StringBuilder("BODY:").append(b).toString());
        return new String[]{str2, b};
    }

    private static String a(List<String> list) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(URLEncoder.encode(list.toString()).getBytes());
            return byteArrayOutputStream.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static List<String> a(UMPlatformData... uMPlatformDataArr) {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        StringBuilder stringBuilder3 = new StringBuilder();
        for (UMPlatformData uMPlatformData : uMPlatformDataArr) {
            stringBuilder.append(uMPlatformData.getMeida().toString());
            stringBuilder.append(',');
            stringBuilder2.append(uMPlatformData.getUsid());
            stringBuilder2.append(',');
            stringBuilder3.append(uMPlatformData.getWeiboId());
            stringBuilder3.append(',');
        }
        if (uMPlatformDataArr.length > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder2.deleteCharAt(stringBuilder2.length() - 1);
            stringBuilder3.deleteCharAt(stringBuilder3.length() - 1);
        }
        List<String> arrayList = new ArrayList();
        arrayList.add(new StringBuilder("platform=").append(stringBuilder.toString()).toString());
        arrayList.add(new StringBuilder("usid=").append(stringBuilder2.toString()).toString());
        if (stringBuilder3.length() > 0) {
            arrayList.add(new StringBuilder("weiboid=").append(stringBuilder3.toString()).toString());
        }
        return arrayList;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String b(com.umeng.analytics.social.UMPlatformData... r8) {
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.social.f.b(com.umeng.analytics.social.UMPlatformData[]):java.lang.String");
        /*
        r2 = new org.json.JSONObject;
        r2.<init>();
        r3 = r8.length;
        r0 = 0;
        r1 = r0;
    L_0x0008:
        if (r1 >= r3) goto L_0x005c;
    L_0x000a:
        r4 = r8[r1];
        r0 = r4.getGender();
        r5 = r4.getName();
        if (r0 != 0) goto L_0x001c;
    L_0x0016:
        r6 = android.text.TextUtils.isEmpty(r5);	 Catch:{ Exception -> 0x0052 }
        if (r6 != 0) goto L_0x0042;
    L_0x001c:
        r6 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0052 }
        r6.<init>();	 Catch:{ Exception -> 0x0052 }
        r7 = "gender";
        if (r0 != 0) goto L_0x0046;
    L_0x0026:
        r0 = "";
    L_0x0029:
        r6.put(r7, r0);	 Catch:{ Exception -> 0x0052 }
        r7 = "name";
        if (r5 != 0) goto L_0x004d;
    L_0x0031:
        r0 = "";
    L_0x0034:
        r6.put(r7, r0);	 Catch:{ Exception -> 0x0052 }
        r0 = r4.getMeida();	 Catch:{ Exception -> 0x0052 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0052 }
        r2.put(r0, r6);	 Catch:{ Exception -> 0x0052 }
    L_0x0042:
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x0008;
    L_0x0046:
        r0 = r0.value;	 Catch:{ Exception -> 0x0052 }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ Exception -> 0x0052 }
        goto L_0x0029;
    L_0x004d:
        r0 = java.lang.String.valueOf(r5);	 Catch:{ Exception -> 0x0052 }
        goto L_0x0034;
    L_0x0052:
        r0 = move-exception;
        r1 = new com.umeng.analytics.social.a;
        r2 = "build body exception";
        r1.<init>(r2, r0);
        throw r1;
    L_0x005c:
        r0 = r2.length();
        if (r0 != 0) goto L_0x0064;
    L_0x0062:
        r0 = 0;
    L_0x0063:
        return r0;
    L_0x0064:
        r0 = r2.toString();
        goto L_0x0063;
        */
    }

    private static Map<String, String> c(Context context) throws a {
        Map<String, String> hashMap = new HashMap();
        Map b = b(context);
        if (b == null || b.isEmpty()) {
            throw new a("can`t get device id.");
        }
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        for (Entry entry : b.entrySet()) {
            if (!TextUtils.isEmpty((CharSequence) entry.getValue())) {
                stringBuilder2.append((String) entry.getKey()).append(",");
                stringBuilder.append((String) entry.getValue()).append(",");
            }
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            hashMap.put("deviceid", stringBuilder.toString());
        }
        if (stringBuilder2.length() > 0) {
            stringBuilder2.deleteCharAt(stringBuilder2.length() - 1);
            hashMap.put("idtype", stringBuilder2.toString());
        }
        return hashMap;
    }

    protected static String a(Context context) {
        Object obj = e.d;
        if (TextUtils.isEmpty(obj)) {
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), SpdyProtocol.SLIGHTSSLV2);
                if (applicationInfo != null) {
                    String string = applicationInfo.metaData.getString("UMENG_APPKEY");
                    if (string != null) {
                        return string.trim();
                    }
                    b.b("MobclickAgent", "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.");
                }
            } catch (Exception e) {
                b.b("MobclickAgent", "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.", e);
            }
            return null;
        }
        b.b("MobclickAgent", "use usefully appkey from constant field.");
        return obj;
    }

    public static Map<String, String> b(Context context) {
        CharSequence deviceId;
        CharSequence d;
        CharSequence string;
        Map<String, String> hashMap = new HashMap();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            b.e("MobclickAgent", "No IMEI.");
        }
        try {
            if (a(context, "android.permission.READ_PHONE_STATE")) {
                deviceId = telephonyManager.getDeviceId();
                d = d(context);
                string = Secure.getString(context.getContentResolver(), "android_id");
                if (!TextUtils.isEmpty(d)) {
                    hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_MAC, d);
                }
                if (!TextUtils.isEmpty(deviceId)) {
                    hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_IMEI, deviceId);
                }
                if (!TextUtils.isEmpty(string)) {
                    hashMap.put("android_id", string);
                }
                return hashMap;
            }
        } catch (Exception e) {
            b.e("MobclickAgent", "No IMEI.", e);
        }
        deviceId = null;
        d = d(context);
        string = Secure.getString(context.getContentResolver(), "android_id");
        if (TextUtils.isEmpty(d)) {
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_MAC, d);
        }
        if (TextUtils.isEmpty(deviceId)) {
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_IMEI, deviceId);
        }
        if (TextUtils.isEmpty(string)) {
            hashMap.put("android_id", string);
        }
        return hashMap;
    }

    private static boolean a(Context context, String str) {
        return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
    }

    private static String d(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService(Utility.NETWORK_WIFI);
            if (a(context, "android.permission.ACCESS_WIFI_STATE")) {
                return wifiManager.getConnectionInfo().getMacAddress();
            }
            b.e("MobclickAgent", "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
            return BuildConfig.VERSION_NAME;
        } catch (Exception e) {
            b.e("MobclickAgent", new StringBuilder("Could not get mac address.").append(e.toString()).toString());
        }
    }
}
