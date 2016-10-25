package com.xunlei.downloadprovider.web;

import android.os.Handler;
import android.text.TextUtils;
import com.sina.weibo.sdk.component.GameManager;
import com.sina.weibo.sdk.register.mobile.SelectCountryActivity;
import com.tencent.open.SocialConstants;
import com.umeng.a;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.downloadprovider.a.b;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.xllib.b.g;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: PublicReportUtil.java
public final class w {
    private static w a;

    public static w a() {
        if (a == null) {
            a = new w();
        }
        return a;
    }

    public static String a(String str, String str2, String str3, String str4) {
        StringBuilder stringBuilder = new StringBuilder("{\"");
        stringBuilder.append("action\":\"").append(str2).append("\",\"name\":\"").append(str3).append("\",\"url\":\"").append(str4).append("\"}");
        return a(str, stringBuilder.toString());
    }

    public static String a(String str, String str2, String str3, String str4, String str5) {
        StringBuilder stringBuilder = new StringBuilder("{\"");
        stringBuilder.append("action\":\"").append(str2).append("\",\"key\":\"").append(str3).append("\",\"url\":\"").append(str4).append("\",\"ts\":\"").append(str5).append("\"}");
        return a(str, stringBuilder.toString());
    }

    public static String a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("package", str);
            return a("AppInstalled", jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(JsInterface.KEY_ACTION, str);
            jSONObject.put(SocializeConstants.WEIBO_ID, str2);
            jSONObject.put("key", g.a((b.d() + System.currentTimeMillis()).getBytes()));
            jSONObject.put(SelectCountryActivity.EXTRA_COUNTRY_NAME, str3);
            jSONObject.put(SocialConstants.PARAM_URL, str4);
            jSONObject.put("ref", str5);
            jSONObject.put("videotype", str6);
            jSONObject.put("speed", str7);
            jSONObject.put(MsgConstant.KEY_TS, System.currentTimeMillis());
            jSONObject.put("from", str8);
            return a("Play", jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            return a.d;
        }
    }

    private static String a(String str, String str2) {
        StringBuilder stringBuilder = null;
        try {
            stringBuilder = new StringBuilder("http://quan.m.xunlei.com/cgi-bin/stat?").append(new StringBuilder("type=").append(str).toString()).append(new StringBuilder("&peerid=").append(b.d()).toString()).append(new StringBuilder("&userid=").append(LoginHelper.a().j).toString()).append(new StringBuilder("&productid=").append(b.h()).toString()).append(new StringBuilder("&version=").append(b.w()).toString()).append(new StringBuilder("&versionCode=").append(b.x()).toString()).append(new StringBuilder("&content=").append(URLEncoder.encode(str2, GameManager.DEFAULT_CHARSET)).toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            new t(null).a(str);
        }
    }

    public static void a(String str, Handler handler) {
        if (!TextUtils.isEmpty(str)) {
            new t(handler).a(str);
        }
    }
}
