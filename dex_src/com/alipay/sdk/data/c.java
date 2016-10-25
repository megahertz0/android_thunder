package com.alipay.sdk.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.widget.TextView;
import anet.channel.util.HttpConstant;
import com.alipay.mobilesecuritysdk.face.SecurityClientMobile;
import com.alipay.sdk.cons.a;
import com.alipay.sdk.sys.b;
import com.alipay.sdk.util.h;
import com.alipay.sdk.util.j;
import com.alipay.sdk.util.k;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.taobao.accs.utl.UtilityImpl;
import com.uc.addon.sdk.remote.TabsImpl;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.android.agoo.message.MessageService;

public final class c {
    private static final String d = "virtualImeiAndImsi";
    private static final String e = "virtual_imei";
    private static final String f = "virtual_imsi";
    private static c g;
    public String a;
    public String b;
    public String c;

    private String c() {
        return this.c;
    }

    private c() {
        this.b = "sdk-and-lite";
    }

    public static synchronized c a() {
        c cVar;
        synchronized (c.class) {
            if (g == null) {
                g = new c();
            }
            cVar = g;
        }
        return cVar;
    }

    public final synchronized void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            PreferenceManager.getDefaultSharedPreferences(b.a().a).edit().putString(com.alipay.sdk.cons.b.i, str).commit();
            a.b = str;
        }
    }

    private static String d() {
        return MessageService.MSG_DB_NOTIFY_REACHED;
    }

    private static String a(Context context) {
        return Float.toString(new TextView(context).getTextSize());
    }

    private static String e() {
        return "-1;-1";
    }

    private String a(com.alipay.sdk.tid.b bVar) {
        String a;
        String b;
        String e;
        String a2;
        Context context = b.a().a;
        com.alipay.sdk.util.a a3 = com.alipay.sdk.util.a.a(context);
        if (TextUtils.isEmpty(this.a)) {
            a = k.a();
            b = k.b();
            e = k.e(context);
            a2 = j.a(context);
            this.a = "Msp/15.2.0" + " (" + a + h.b + b + h.b + e + h.b + a2.substring(0, a2.indexOf(HttpConstant.SCHEME_SPLIT)) + h.b + k.f(context) + h.b + Float.toString(new TextView(context).getTextSize());
        }
        e = com.alipay.sdk.util.a.b(context).p;
        a2 = "-1;-1";
        String str = MessageService.MSG_DB_NOTIFY_REACHED;
        String a4 = a3.a();
        String b2 = a3.b();
        Context context2 = b.a().a;
        SharedPreferences sharedPreferences = context2.getSharedPreferences(d, 0);
        String string = sharedPreferences.getString(f, null);
        if (TextUtils.isEmpty(string)) {
            if (TextUtils.isEmpty(com.alipay.sdk.tid.b.a().a)) {
                Object c = b.a().c();
                string = TextUtils.isEmpty(c) ? b() : c.substring(XZBDevice.DOWNLOAD_LIST_FAILED, R.styleable.Toolbar_collapseIcon);
            } else {
                string = com.alipay.sdk.util.a.a(context2).a();
            }
            sharedPreferences.edit().putString(f, string).commit();
        }
        a = string;
        Context context3 = b.a().a;
        SharedPreferences sharedPreferences2 = context3.getSharedPreferences(d, 0);
        string = sharedPreferences2.getString(e, null);
        if (TextUtils.isEmpty(string)) {
            if (TextUtils.isEmpty(com.alipay.sdk.tid.b.a().a)) {
                string = b();
            } else {
                string = com.alipay.sdk.util.a.a(context3).b();
            }
            sharedPreferences2.edit().putString(e, string).commit();
        }
        b = string;
        if (bVar != null) {
            this.c = bVar.b;
        }
        String replace = Build.MANUFACTURER.replace(h.b, " ");
        String replace2 = Build.MODEL.replace(h.b, " ");
        boolean b3 = b.b();
        String str2 = a3.a;
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI)).getConnectionInfo();
        String ssid = connectionInfo != null ? connectionInfo.getSSID() : WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
        connectionInfo = ((WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI)).getConnectionInfo();
        string = connectionInfo != null ? connectionInfo.getBSSID() : "00";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.a).append(h.b).append(e).append(h.b).append(a2).append(h.b).append(str).append(h.b).append(a4).append(h.b).append(b2).append(h.b).append(this.c).append(h.b).append(replace).append(h.b).append(replace2).append(h.b).append(b3).append(h.b).append(str2).append(";-1;-1;").append(this.b).append(h.b).append(a).append(h.b).append(b).append(h.b).append(ssid).append(h.b).append(string);
        if (bVar != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(com.alipay.sdk.cons.b.c, bVar.a);
            hashMap.put(MsgConstant.KEY_UTDID, b.a().c());
            c = b(context, hashMap);
            if (!TextUtils.isEmpty(c)) {
                stringBuilder.append(h.b).append(c);
            }
        }
        stringBuilder.append(SocializeConstants.OP_CLOSE_PAREN);
        return stringBuilder.toString();
    }

    private static String f() {
        Context context = b.a().a;
        SharedPreferences sharedPreferences = context.getSharedPreferences(d, 0);
        String string = sharedPreferences.getString(e, null);
        if (TextUtils.isEmpty(string)) {
            if (TextUtils.isEmpty(com.alipay.sdk.tid.b.a().a)) {
                string = b();
            } else {
                string = com.alipay.sdk.util.a.a(context).b();
            }
            sharedPreferences.edit().putString(e, string).commit();
        }
        return string;
    }

    private static String g() {
        Context context = b.a().a;
        SharedPreferences sharedPreferences = context.getSharedPreferences(d, 0);
        String string = sharedPreferences.getString(f, null);
        if (TextUtils.isEmpty(string)) {
            if (TextUtils.isEmpty(com.alipay.sdk.tid.b.a().a)) {
                Object c = b.a().c();
                if (TextUtils.isEmpty(c)) {
                    string = b();
                } else {
                    string = c.substring(XZBDevice.DOWNLOAD_LIST_FAILED, R.styleable.Toolbar_collapseIcon);
                }
            } else {
                string = com.alipay.sdk.util.a.a(context).a();
            }
            sharedPreferences.edit().putString(f, string).commit();
        }
        return string;
    }

    public static String b() {
        return Long.toHexString(System.currentTimeMillis()) + (new Random().nextInt(9000) + 1000);
    }

    private static String b(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI)).getConnectionInfo();
        return connectionInfo != null ? connectionInfo.getSSID() : WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
    }

    private static String c(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI)).getConnectionInfo();
        return connectionInfo != null ? connectionInfo.getBSSID() : "00";
    }

    static String a(Context context, HashMap<String, String> hashMap) {
        Object obj = com.umeng.a.d;
        try {
            obj = SecurityClientMobile.GetApdid(context, hashMap);
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.e, com.alipay.sdk.app.statistic.c.f, th);
        }
        if (TextUtils.isEmpty(obj)) {
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.e, com.alipay.sdk.app.statistic.c.g, "apdid == null");
        }
        return obj;
    }

    public final String b(Context context, HashMap<String, String> hashMap) {
        Future submit = Executors.newFixedThreadPool(XZBDevice.DOWNLOAD_LIST_RECYCLE).submit(new d(this, context, hashMap));
        String str = com.umeng.a.d;
        try {
            return (String) submit.get(TabsImpl.SYNC_TIME_OUT, TimeUnit.MILLISECONDS);
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.e, com.alipay.sdk.app.statistic.c.h, th);
            return str;
        }
    }
}
