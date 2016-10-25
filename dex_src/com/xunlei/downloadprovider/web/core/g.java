package com.xunlei.downloadprovider.web.core;

import android.text.TextUtils;
import com.sina.weibo.sdk.register.mobile.SelectCountryActivity;
import com.tencent.open.GameAppOperation;
import com.tencent.open.SocialConstants;
import com.umeng.a;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.url.DownData;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: JsParamsParser.java
public final class g {
    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new JSONObject(str).optString("sourceUrl");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<DownData> b(String str) {
        int i = 0;
        List<DownData> arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                Object string = jSONObject.getString(JsInterface.FUNPLAY_AD_TRPE);
                int i2 = -1;
                if (!TextUtils.isEmpty(string)) {
                    i2 = Integer.parseInt(string);
                }
                JSONArray jSONArray;
                int length;
                JSONObject jSONObject2;
                DownData downData;
                if (i2 == 0) {
                    jSONArray = jSONObject.getJSONArray("list");
                    if (jSONArray != null) {
                        length = jSONArray.length();
                        while (i < length) {
                            jSONObject2 = jSONArray.getJSONObject(i);
                            downData = new DownData();
                            downData.d = jSONObject2.optString(Impl.COLUMN_GCID, a.d);
                            downData.c = jSONObject2.optString(Impl.COLUMN_CID, a.d);
                            downData.a = jSONObject2.optString(SelectCountryActivity.EXTRA_COUNTRY_NAME, a.d);
                            downData.q = jSONObject2.optString(GameAppOperation.QQFAV_DATALINE_IMAGEURL, a.d);
                            downData.r = jSONObject2.optLong("size", 0);
                            arrayList.add(downData);
                            i++;
                        }
                    }
                } else if (i2 == 1) {
                    jSONArray = jSONObject.getJSONArray("list");
                    if (jSONArray != null) {
                        length = jSONArray.length();
                        while (i < length) {
                            jSONObject2 = jSONArray.getJSONObject(i);
                            downData = new DownData();
                            downData.e = jSONObject2.optString(SocialConstants.PARAM_URL, a.d);
                            downData.b = jSONObject2.optString(SocialConstants.PARAM_URL, a.d);
                            downData.s = jSONObject2.optString("ref_url", a.d);
                            downData.a = jSONObject2.optString(SelectCountryActivity.EXTRA_COUNTRY_NAME, a.d);
                            downData.q = jSONObject2.optString(GameAppOperation.QQFAV_DATALINE_IMAGEURL, a.d);
                            downData.r = jSONObject2.optLong("size", 0);
                            arrayList.add(downData);
                            i++;
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
        return arrayList;
    }
}
