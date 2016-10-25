package com.xunlei.downloadprovider.member.b.a;

import android.text.TextUtils;
import com.umeng.socialize.media.WeiXinShareContent;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.common.yunbo.XLYunboMassage;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: DataPool.java
public final class a {
    private static long a;
    private static HashMap<String, d> b;

    static {
        a = -1;
        b = new HashMap();
    }

    public static synchronized String a(String str) throws JSONException {
        String str2;
        int i = 0;
        synchronized (a.class) {
            int i2;
            d dVar = new d();
            str2 = BuildConfig.VERSION_NAME;
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("result")) {
                i2 = jSONObject.getInt("result");
            } else {
                i2 = 0;
            }
            if (jSONObject.has("style")) {
                str2 = jSONObject.getString("style");
            }
            String[] split;
            if (i2 == 200) {
                if (jSONObject.has("bubble_list")) {
                    str2 = b(jSONObject);
                } else {
                    d dVar2 = new d();
                    if (jSONObject.has("result")) {
                        dVar2.b = jSONObject.getInt("result");
                    }
                    if (jSONObject.has(SocializeProtocolConstants.PROTOCOL_KEY_MSG)) {
                        dVar2.c = jSONObject.getString(SocializeProtocolConstants.PROTOCOL_KEY_MSG);
                    }
                    if (jSONObject.has("bubbleid")) {
                        dVar2.d = jSONObject.getString("bubbleid");
                    }
                    if (jSONObject.has(WeiXinShareContent.TYPE_TEXT)) {
                        String string = jSONObject.getString(WeiXinShareContent.TYPE_TEXT);
                        if (!TextUtils.isEmpty(string)) {
                            if (string.contains("&")) {
                                split = string.split("&");
                                dVar2.e = split[0];
                                dVar2.f = split[1];
                            } else {
                                dVar2.e = string;
                            }
                        }
                    }
                    if (jSONObject.has("button_text")) {
                        dVar2.g = jSONObject.getString("button_text");
                    }
                    if (jSONObject.has(SHubBatchQueryKeys.url)) {
                        dVar2.h = jSONObject.getString(SHubBatchQueryKeys.url);
                    }
                    if (jSONObject.has("show_time")) {
                        dVar2.i = jSONObject.getString("show_time");
                    }
                    if (jSONObject.has("style")) {
                        dVar2.j = jSONObject.getString("style");
                    }
                    if (jSONObject.has("expire_days")) {
                        dVar2.k = jSONObject.getInt("expire_days");
                    }
                    a(dVar2);
                }
            } else if (i2 == 404) {
                if (jSONObject.has("bubble_list")) {
                    str2 = a(jSONObject);
                    split = str2.split(";");
                    int length = split.length;
                    while (i < length) {
                        e(split[i]);
                        i++;
                    }
                } else {
                    e(str2);
                }
            }
        }
        return str2;
    }

    private static String a(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONArray("bubble_list");
        String str = BuildConfig.VERSION_NAME;
        int i = 0;
        while (i < jSONArray.length()) {
            String str2;
            JSONObject jSONObject2 = (JSONObject) jSONArray.get(i);
            if (jSONObject2.has("style")) {
                str2 = str + jSONObject2.getString("style") + ";";
            } else {
                str2 = str;
            }
            i++;
            str = str2;
        }
        return str.endsWith(";") ? str.substring(0, str.length() - 1) : str;
    }

    private static String b(JSONObject jSONObject) throws JSONException {
        int i;
        String str = BuildConfig.VERSION_NAME;
        int i2 = XLYunboMassage.MSG_TASKCREATED;
        String str2 = BuildConfig.VERSION_NAME;
        if (jSONObject.has("result")) {
            i = jSONObject.getInt("result");
        } else {
            i = 0;
        }
        if (jSONObject.has(SocializeProtocolConstants.PROTOCOL_KEY_MSG)) {
            str2 = jSONObject.getString(SocializeProtocolConstants.PROTOCOL_KEY_MSG);
        }
        if (jSONObject.has("expire_days")) {
            i2 = jSONObject.getInt("expire_days");
        }
        JSONArray jSONArray = jSONObject.getJSONArray("bubble_list");
        int i3 = 0;
        while (i3 < jSONArray.length()) {
            String string;
            d dVar = new d();
            dVar.b = i;
            dVar.c = str2;
            dVar.k = i2;
            JSONObject jSONObject2 = (JSONObject) jSONArray.get(i3);
            if (jSONObject2.has("bubbleid")) {
                dVar.d = jSONObject2.getString("bubbleid");
            }
            if (jSONObject2.has(WeiXinShareContent.TYPE_TEXT)) {
                String string2 = jSONObject2.getString(WeiXinShareContent.TYPE_TEXT);
                if (!TextUtils.isEmpty(string2)) {
                    if (string2.contains("&")) {
                        String[] split = string2.split("&");
                        dVar.e = split[0];
                        dVar.f = split[1];
                    } else {
                        dVar.e = string2;
                    }
                }
            }
            if (jSONObject2.has("button_text")) {
                dVar.g = jSONObject2.getString("button_text");
            }
            if (jSONObject2.has(SHubBatchQueryKeys.url)) {
                dVar.h = jSONObject2.getString(SHubBatchQueryKeys.url);
            }
            if (jSONObject2.has("show_time")) {
                dVar.i = jSONObject2.getString("show_time");
            }
            if (jSONObject2.has("style")) {
                string = jSONObject2.getString("style");
                dVar.j = string;
                string = str + string + ";";
            } else {
                string = str;
            }
            a(dVar);
            i3++;
            str = string;
        }
        return str.endsWith(";") ? str.substring(0, str.length() - 1) : str;
    }

    protected static synchronized d b(String str) {
        d dVar;
        synchronized (a.class) {
            dVar = (d) b.get(str);
        }
        return dVar;
    }

    private static synchronized void a(d dVar) {
        synchronized (a.class) {
            long j = LoginHelper.a().j;
            if (a != j) {
                a();
            }
            a = j;
            b.put(dVar.j, dVar);
        }
    }

    protected static synchronized void c(String str) {
        synchronized (a.class) {
            if (!TextUtils.isEmpty(str)) {
                Object split = str.split(";");
                new StringBuilder("deleteVipRenewalTip:").append(split);
                for (Object obj : split) {
                    b.remove(obj);
                }
            }
        }
    }

    protected static synchronized boolean d(String str) {
        boolean z = false;
        synchronized (a.class) {
            if (!TextUtils.isEmpty(str)) {
                if (a != LoginHelper.a().j) {
                    a();
                } else {
                    Object split = str.split(";");
                    new StringBuilder("hasTheVipnewalTip:").append(split);
                    int length = split.length;
                    int i = 0;
                    while (i < length) {
                        i++;
                        z = b.containsKey(split[i]);
                    }
                }
            }
        }
        return z;
    }

    private static void e(String str) {
        c(str);
        d dVar = new d();
        dVar.j = str;
        dVar.b = 404;
        a(dVar);
    }

    protected static synchronized void a() {
        synchronized (a.class) {
            b.clear();
        }
    }
}
