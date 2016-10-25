package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.taobao.accs.common.Constants;
import com.taobao.accs.data.Message;
import com.tencent.open.SocialConstants;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.string.d;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.tdlive.R;
import java.util.Map;
import java.util.TreeMap;
import org.android.agoo.message.MessageService;
import org.json.JSONObject;

public class g {
    private static f a;
    private static a b;

    public static synchronized f a(Context context) {
        f fVar = null;
        synchronized (g.class) {
            if (a != null) {
                fVar = a;
            } else {
                SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_account", 0);
                Object string = sharedPreferences.getString("uuid", null);
                Object string2 = sharedPreferences.getString("token", null);
                Object string3 = sharedPreferences.getString("security", null);
                String string4 = sharedPreferences.getString("app_id", null);
                String string5 = sharedPreferences.getString("app_token", null);
                String string6 = sharedPreferences.getString("package_name", null);
                Object string7 = sharedPreferences.getString("device_id", null);
                int i = sharedPreferences.getInt("env_type", 1);
                if (!TextUtils.isEmpty(string7) && string7.startsWith("a-")) {
                    string7 = d.e(context);
                    sharedPreferences.edit().putString("device_id", string7).commit();
                }
                if (!(TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3))) {
                    CharSequence e = d.e(context);
                    if ("com.xiaomi.xmsf".equals(context.getPackageName()) || TextUtils.isEmpty(e) || TextUtils.isEmpty(r8) || r8.equals(e)) {
                        fVar = new f(string, string2, string3, string4, string5, string6, i);
                        a = fVar;
                    } else {
                        b.d("erase the old account.");
                        b(context);
                    }
                }
            }
        }
        return fVar;
    }

    public static synchronized f a(Context context, String str, String str2, String str3) {
        f fVar = null;
        synchronized (g.class) {
            PackageInfo packageInfo;
            Map treeMap = new TreeMap();
            treeMap.put("devid", d.a(context));
            String str4 = c(context) ? "1000271" : str2;
            String str5 = c(context) ? "420100086271" : str3;
            String str6 = c(context) ? "com.xiaomi.xmsf" : str;
            treeMap.put(SocialConstants.PARAM_APP_ID, str4);
            treeMap.put("apptoken", str5);
            try {
                packageInfo = context.getPackageManager().getPackageInfo(str6, Message.FLAG_REQ_BIT1);
            } catch (Throwable e) {
                b.a(e);
                packageInfo = null;
            }
            treeMap.put("appversion", packageInfo != null ? String.valueOf(packageInfo.versionCode) : MessageService.MSG_DB_READY_REPORT);
            treeMap.put("sdkversion", Integer.toString(R.styleable.Toolbar_collapseContentDescription));
            treeMap.put(LogBuilder.KEY_PACKAGE_NAME, str6);
            treeMap.put(Constants.KEY_MODEL, Build.MODEL);
            treeMap.put("imei_md5", d.a(d.c(context)));
            treeMap.put(Constants.KEY_OS_VERSION, VERSION.RELEASE + SocializeConstants.OP_DIVIDER_MINUS + VERSION.INCREMENTAL);
            int b = d.b();
            if (b >= 0) {
                treeMap.put("space_id", Integer.toString(b));
            }
            CharSequence a = d.a(d.g(context));
            if (!TextUtils.isEmpty(a)) {
                treeMap.put("mac_address", a);
            }
            treeMap.put("android_id", d.b(context));
            com.xiaomi.channel.commonutils.network.b a2 = com.xiaomi.channel.commonutils.network.d.a(context, a(), treeMap);
            String str7 = a.d;
            if (a2 != null) {
                str7 = a2.a();
            }
            if (!TextUtils.isEmpty(str7)) {
                JSONObject jSONObject = new JSONObject(str7);
                if (jSONObject.getInt(Constants.KEY_HTTP_CODE) == 0) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject(SocializeConstants.JSON_DATA);
                    fVar = new f(jSONObject2.getString("userId") + "@xiaomi.com/an" + d.a(R.styleable.Toolbar_contentInsetEnd), jSONObject2.getString("token"), jSONObject2.getString("ssecurity"), str4, str5, str6, com.xiaomi.channel.commonutils.misc.a.c());
                    a(context, fVar);
                    a = fVar;
                } else {
                    j.a(context, jSONObject.getInt(Constants.KEY_HTTP_CODE), jSONObject.optString(Impl.COLUMN_DESCRIPTION));
                    b.a(str7);
                }
            }
        }
        return fVar;
    }

    public static String a() {
        if (com.xiaomi.channel.commonutils.misc.a.b()) {
            return "http://10.237.14.141:9085/pass/register";
        }
        return new StringBuilder("https://").append(com.xiaomi.channel.commonutils.misc.a.a() ? "sandbox.xmpush.xiaomi.com" : "register.xmpush.xiaomi.com").append("/pass/register").toString();
    }

    public static void a(Context context, f fVar) {
        Editor edit = context.getSharedPreferences("mipush_account", 0).edit();
        edit.putString("uuid", fVar.a);
        edit.putString("security", fVar.c);
        edit.putString("token", fVar.b);
        edit.putString("app_id", fVar.d);
        edit.putString("package_name", fVar.f);
        edit.putString("app_token", fVar.e);
        edit.putString("device_id", d.e(context));
        edit.putInt("env_type", fVar.g);
        edit.commit();
        b();
    }

    public static void b() {
        if (b != null) {
            b.a();
        }
    }

    public static void b(Context context) {
        context.getSharedPreferences("mipush_account", 0).edit().clear().commit();
        a = null;
        b();
    }

    private static boolean c(Context context) {
        return context.getPackageName().equals("com.xiaomi.xmsf");
    }
}
