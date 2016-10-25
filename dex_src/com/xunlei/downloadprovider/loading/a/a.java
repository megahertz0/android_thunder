package com.xunlei.downloadprovider.loading.a;

import android.content.Context;
import android.os.Build.VERSION;
import android.provider.Settings.System;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.android.volley.r.b;
import com.android.volley.toolbox.t;
import com.umeng.message.util.HttpRequest;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.android.agoo.common.AgooConstants;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ADRequest.java
public final class a extends t {
    public a(Context context, b<String> bVar, com.android.volley.r.a aVar) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://market.m.sjzhushou.com/public/get?third=InMobi").append(new StringBuilder("&thirdReq=").append(a(context)).toString());
        super(0, stringBuilder.toString(), bVar, aVar);
    }

    protected final Map getParams() {
        Map hashMap = new HashMap();
        hashMap.put(HttpRequest.l, HttpRequest.c);
        return hashMap;
    }

    private static String a(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(AgooConstants.MESSAGE_ID, "1467854769562");
            jSONObject2.put("bundle", com.xunlei.downloadprovider.a.b.b());
            jSONObject.put("app", jSONObject2);
            jSONObject2 = new JSONObject();
            jSONObject2.put("layout", 0);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("ads", MqttConnectOptions.MQTT_VERSION_3_1);
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("trackertype", "url_ping");
            jSONObject4.put("native", jSONObject2);
            jSONObject4.put(SocializeProtocolConstants.PROTOCOL_KEY_EXTEND, jSONObject3);
            jSONObject.put("imp", jSONObject4);
            jSONObject2 = new JSONObject();
            jSONObject2.put("iem", c.a(context).a());
            jSONObject2.put("ip", BuildConfig.VERSION_NAME);
            String string = System.getString(context.getContentResolver(), "android_id");
            c.a(context);
            jSONObject2.put("o1", c.a(string));
            if (VERSION.SDK_INT >= 17) {
                jSONObject2.put("ua", WebSettings.getDefaultUserAgent(context));
            } else {
                jSONObject2.put("ua", new WebView(BrothersApplication.a()).getSettings().getUserAgentString());
            }
            jSONObject.put("device", jSONObject2);
            jSONObject2 = new JSONObject();
            jSONObject2.put("responseformat", "json");
            jSONObject.put(SocializeProtocolConstants.PROTOCOL_KEY_EXTEND, jSONObject2);
        } catch (JSONException e) {
        }
        try {
            return URLEncoder.encode(jSONObject.toString(), "utf-8");
        } catch (UnsupportedEncodingException e2) {
            return null;
        }
    }
}
