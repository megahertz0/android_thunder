package com.umeng.fb.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import com.umeng.common.a;
import com.umeng.fb.model.Constants;
import com.umeng.fb.model.Store;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.OauthHelper;
import com.xunlei.xiazaibao.BuildConfig;
import java.security.MessageDigest;
import org.json.JSONObject;

// compiled from: Helper.java
public class c {
    public static final String a;
    private static final String b;

    static {
        b = c.class.getName();
        a = System.getProperty("line.separator");
    }

    public static JSONObject a(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("device_id", b.f(context));
            jSONObject.put(a.e, b.g(context));
            jSONObject.put("device_model", Build.MODEL);
            jSONObject.put(OauthHelper.APP_KEY, b.p(context));
            jSONObject.put(a.d, b.t(context));
            jSONObject.put("app_version", b.d(context));
            jSONObject.put(a.f, b.c(context));
            jSONObject.put("sdk_type", Constants.SDK_TYPE);
            jSONObject.put(a.h, Constants.SDK_VERSION);
            jSONObject.put(SocializeProtocolConstants.PROTOCOL_KEY_OS, Constants.SDK_TYPE);
            jSONObject.put("os_version", VERSION.RELEASE);
            jSONObject.put("country", b.o(context)[0]);
            jSONObject.put("language", b.o(context)[1]);
            jSONObject.put("timezone", b.n(context));
            jSONObject.put("resolution", b.r(context));
            jSONObject.put("access", b.j(context)[0]);
            jSONObject.put("access_subtype", b.j(context)[1]);
            jSONObject.put("carrier", b.h(context));
            jSONObject.put("cpu", b.a());
            jSONObject.put(a.c, b.u(context));
            jSONObject.put(com.xunlei.download.proguard.c.f, Store.getInstance(context).getUid());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public static String a(String str) {
        if (str == null) {
            return null;
        }
        try {
            byte[] bytes = str.getBytes();
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(bytes);
            bytes = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < bytes.length; i++) {
                stringBuffer.append(String.format("%02X", new Object[]{Byte.valueOf(bytes[i])}));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            return str.replaceAll("[^[a-z][A-Z][0-9][.][_]]", BuildConfig.VERSION_NAME);
        }
    }

    public static String b(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                stringBuffer.append(Integer.toHexString(b & 255));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            Log.a(b, "getMD5 error", e);
            return BuildConfig.VERSION_NAME;
        }
    }

    public static void a(Context context, String str) {
        context.startActivity(context.getPackageManager().getLaunchIntentForPackage(str));
    }

    public static boolean b(Context context, String str) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean c(String str) {
        return str == null || str.length() == 0;
    }
}
