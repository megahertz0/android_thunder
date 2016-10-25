package com.xunlei.downloadprovider.homepage.recommend.b;

import com.xunlei.download.DownloadManager;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.member.payment.a.e;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: CounterNetWorkHelper.java
public final class b extends e {
    static JSONObject a(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("res_id", str);
            jSONObject.put("res_type", 1);
            jSONObject.put(Impl.COLUMN_GCID, str2);
            jSONObject.put("peer_id", str3);
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    static JSONObject a(String str, String str2, String str3, String str4) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("res_id", str);
            jSONObject.put("res_type", 1);
            jSONObject.put(Impl.COLUMN_GCID, str2);
            jSONObject.put("peer_id", str3);
            jSONObject.put("share_type", str4);
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public static JSONObject a(String str, String str2, String str3, int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("res_id", str);
            jSONObject.put("res_type", 1);
            jSONObject.put(Impl.COLUMN_GCID, str2);
            jSONObject.put("peer_id", str3);
            jSONObject.put(DownloadManager.COLUMN_REASON, i);
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    static JSONObject a(String str, String str2, String str3, int i, String str4, int i2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("res_id", str);
            jSONObject.put("res_type", 1);
            jSONObject.put(Impl.COLUMN_GCID, str2);
            jSONObject.put("peer_id", str3);
            jSONObject.put("length", i);
            jSONObject.put("page_name", null);
            jSONObject.put("play_type", str4);
            jSONObject.put("tag", null);
            jSONObject.put("play_time", i2);
        } catch (JSONException e) {
        }
        return jSONObject;
    }
}
