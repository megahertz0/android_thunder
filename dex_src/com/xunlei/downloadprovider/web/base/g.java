package com.xunlei.downloadprovider.web.base;

import com.android.volley.r.b;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.editorpage.ShareActivity;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.tdlive.WebBrowserActivity;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

// compiled from: KandanDataLoader.java
final class g implements b<JSONObject> {
    final /* synthetic */ f a;

    g(f fVar) {
        this.a = fVar;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        if (jSONObject == null || jSONObject.length() <= 0) {
            this.a.a.a(true, null, null);
            return;
        }
        f.c;
        new StringBuilder("response=>").append(jSONObject.toString());
        if ("ok".contentEquals(jSONObject.optString("result"))) {
            JSONObject optJSONObject = jSONObject.optJSONObject(SocializeConstants.JSON_DATA);
            if (optJSONObject == null || optJSONObject.length() <= 0) {
                this.a.a.a(true, null, null);
                return;
            }
            List arrayList = new ArrayList();
            t tVar = new t();
            tVar.a = optJSONObject.optString(SocializeConstants.WEIBO_ID);
            tVar.b = optJSONObject.optString(WebBrowserActivity.EXTRA_TITLE);
            tVar.c = optJSONObject.optString("about");
            tVar.d = optJSONObject.optString("keywords");
            tVar.e = optJSONObject.optString("template_type");
            tVar.f = optJSONObject.optString("cover_url");
            tVar.g = optJSONObject.optInt("fav_count", 0);
            tVar.h = optJSONObject.optInt("sub_count", 0);
            tVar.i = optJSONObject.optInt(Impl.COLUMN_STATUS, 0);
            tVar.j = optJSONObject.optInt("is_sniff", 0) == 1;
            tVar.k = optJSONObject.optString("create_at");
            tVar.l = optJSONObject.optString("release_time");
            tVar.m = optJSONObject.optString("modified_time");
            JSONArray optJSONArray = optJSONObject.optJSONArray("resources");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                int length = optJSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                    s sVar = new s();
                    sVar.a = optJSONObject2.optString(SocializeConstants.WEIBO_ID);
                    sVar.b = optJSONObject2.optString("subject_list_id");
                    sVar.c = optJSONObject2.optInt("cate", -1);
                    sVar.d = optJSONObject2.optInt("sub_cate", -1);
                    sVar.e = optJSONObject2.optString("sub_cate_cn");
                    sVar.f = optJSONObject2.optInt("is_sniff", 0) == 1;
                    sVar.g = optJSONObject2.optString("res_id");
                    sVar.h = optJSONObject2.optString(Impl.COLUMN_GCID);
                    sVar.i = optJSONObject2.optString(WebBrowserActivity.EXTRA_TITLE);
                    sVar.j = optJSONObject2.optString("introduction");
                    sVar.k = optJSONObject2.optString("cover_pic");
                    sVar.l = (float) optJSONObject2.optDouble("douban_score", 0.0d);
                    sVar.m = optJSONObject2.optLong("duration", 0);
                    sVar.n = optJSONObject2.optLong("res_size", 0);
                    sVar.o = optJSONObject2.optString("download_url");
                    sVar.p = optJSONObject2.optInt("fav_count", 0);
                    sVar.q = optJSONObject2.optString("create_at");
                    sVar.r = optJSONObject2.optString("published_time");
                    sVar.s = optJSONObject2.optString("modified_time");
                    sVar.t = optJSONObject2.optString("actor");
                    sVar.u = optJSONObject2.optString("director");
                    sVar.v = optJSONObject2.optString("lang");
                    sVar.w = optJSONObject2.optString(ShareActivity.KEY_LOCATION);
                    arrayList.add(sVar);
                }
            }
            this.a.a.a(false, tVar, arrayList);
            return;
        }
        this.a.a.a(true, null, null);
    }
}
