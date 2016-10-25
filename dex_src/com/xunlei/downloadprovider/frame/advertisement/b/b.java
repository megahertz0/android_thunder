package com.xunlei.downloadprovider.frame.advertisement.b;

import android.text.TextUtils;
import com.xunlei.common.base.XLLog;
import com.xunlei.downloadprovider.download.tasklist.list.a.a.h;
import com.xunlei.downloadprovider.frame.advertisement.a.a;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;
import com.xunlei.xllib.b.d;
import java.util.ArrayList;
import java.util.List;
import org.android.agoo.common.AgooConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: AdvertisementParser.java
class b {
    private static final String a;
    private static b b;
    private String c;

    static {
        a = b.class.getSimpleName();
    }

    public static b a(String str) {
        if (b == null) {
            b = new b();
        }
        b.c = str;
        return b;
    }

    private b() {
    }

    public final boolean a(JSONObject jSONObject) throws JSONException {
        XLLog.v(a, new StringBuilder("parseJson: mPageType --> ").append(this.c).append(", obj --> ").append(jSONObject).toString());
        try {
            if (jSONObject.optInt("ret") == 0) {
                com.xunlei.downloadprovider.frame.advertisement.a.b a = a(new com.xunlei.downloadprovider.frame.advertisement.a.b(), jSONObject);
                if (d.a(a.a)) {
                    a.a().c = null;
                } else {
                    a.a().c = a.a;
                }
                if (d.a(a.b)) {
                    a.a().a = null;
                } else {
                    a.a().a = a.b;
                }
                if (d.a(a.c)) {
                    a.a().b = null;
                } else {
                    a.a().b = a.c;
                }
                if (d.a(a.d)) {
                    a.a().d = null;
                } else {
                    a.a().d = a.d;
                }
                if (d.a(a.e)) {
                    a.a().e = null;
                } else {
                    a.a().e = a.e;
                }
                if (d.a(a.f)) {
                    a.a().f = null;
                } else {
                    a.a().f = a.f;
                }
                if (d.a(a.g)) {
                    a.a().g = null;
                } else {
                    a.a().g = a.g;
                }
                if (d.a(a.h)) {
                    a.a().h = null;
                } else {
                    a.a().h = a.h;
                }
                if (!d.a(a.i)) {
                    a.a().i = a.i;
                }
                if (d.a(a.j)) {
                    a.a().j = null;
                } else {
                    a.a().j = a.j;
                }
                if (d.a(a.k)) {
                    a.a().k = null;
                } else {
                    a.a().k = a.k;
                }
                if (d.a(a.l)) {
                    a.a().l = null;
                } else {
                    a.a().l = a.l;
                }
                if (d.a(a.m)) {
                    a.a().l = null;
                } else {
                    a.a().m = a.m;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private com.xunlei.downloadprovider.frame.advertisement.a.b a(com.xunlei.downloadprovider.frame.advertisement.a.b bVar, JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("resp_list") && !jSONObject.isNull("resp_list")) {
            JSONArray jSONArray = jSONObject.getJSONArray("resp_list");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                String string = jSONObject2.getString("position");
                JSONArray jSONArray2 = jSONObject2.getJSONArray("position_list");
                if (this.c.equals("android_home")) {
                    if (string.equals("android_search_hotword_3")) {
                        bVar.b = a(jSONArray2, string);
                    } else if (string.equals("android_home_banner")) {
                        bVar.c = a(jSONArray2, string);
                    }
                } else if (this.c.equals("android_app")) {
                    bVar.a = a(jSONArray2, string);
                } else if (this.c.equals("android_download")) {
                    if (string.equals("android_download_funplay_1")) {
                        bVar.d = a(jSONArray2, string);
                    } else if (string.equals("android_download_funplay_3")) {
                        bVar.e = a(jSONArray2, string);
                    } else if (string.equals("android_download_funplay_5")) {
                        bVar.f = a(jSONArray2, string);
                    } else if (string.equals("android_download_funplay_6")) {
                        bVar.g = a(jSONArray2, string);
                    } else if (string.equals("android_download_funplay_8")) {
                        bVar.h = a(jSONArray2, string);
                    } else if (string.equals("android_download_use_1")) {
                        bVar.j = a(jSONArray2, string);
                    } else if (string.equals("android_download_use_2")) {
                        bVar.k = a(jSONArray2, string);
                    } else if (string.equals("android_download_use_3")) {
                        bVar.l = a(jSONArray2, string);
                    }
                } else if (this.c.equals("android_guide")) {
                    bVar.i = a(jSONArray2, string);
                } else if (this.c.equals("android_downloadinfo") && string.equals("android_downloadinfo_sell")) {
                    bVar.m = a(jSONArray2, string);
                }
            }
        }
        return bVar;
    }

    private static List<a> a(JSONArray jSONArray, String str) throws JSONException {
        if (jSONArray == null || jSONArray.length() <= 0 || TextUtils.isEmpty(str)) {
            return null;
        }
        XLLog.d(a, new StringBuilder("array: ").append(jSONArray.toString()).toString());
        List<a> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            a aVar = new a();
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            aVar.a = String.valueOf(jSONObject.getInt(AgooConstants.MESSAGE_ID));
            aVar.b = str;
            aVar.c = jSONObject.getString(SetKey.TITLE);
            aVar.g = jSONObject.getString("jump_title");
            aVar.d = jSONObject.getString("desc");
            String optString = jSONObject.optString("desc_ext");
            if (!TextUtils.isEmpty(optString) && optString.split(":").length > 1) {
                aVar.j = optString.split(":")[0];
                aVar.e = optString.substring(optString.split(":")[0].length() + 1, optString.length());
            }
            aVar.f = jSONObject.getString("jump_url");
            aVar.h = jSONObject.getString("download_url");
            optString = jSONObject.optString("material_ext");
            if (!TextUtils.isEmpty(optString) && optString.split(":").length > 1) {
                aVar.j = optString.split(":")[0];
                aVar.k = optString.substring(optString.split(":")[0].length() + 1, optString.length());
            }
            aVar.i = jSONObject.getString("material");
            aVar.l = jSONObject.optString("show_time");
            aVar.m = jSONObject.getString("jump_type");
            aVar.n = jSONObject.getString("guiding_words");
            aVar.o = jSONObject.optInt("status");
            aVar.r = jSONObject.optString("star_count");
            aVar.q = h.a(jSONObject.optString("download_count", "0"));
            arrayList.add(aVar);
        }
        return arrayList;
    }
}
