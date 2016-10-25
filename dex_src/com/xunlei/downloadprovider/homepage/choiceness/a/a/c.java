package com.xunlei.downloadprovider.homepage.choiceness.a.a;

import org.json.JSONException;
import org.json.JSONObject;

// compiled from: SubChoicenessInfo.java
public final class c {
    public String a;
    public String b;
    public String c;
    public String d;
    public int e;
    public float f;
    public String g;
    public String h;
    public String i;
    public String j;

    public static c a(JSONObject jSONObject) {
        try {
            c cVar = new c();
            cVar.b = jSONObject.getString("res_id");
            cVar.d = jSONObject.getString("res_type");
            cVar.a = jSONObject.optString("res_title");
            cVar.c = jSONObject.optString("res_cover_url");
            cVar.e = jSONObject.optInt("f_count");
            cVar.f = (float) jSONObject.optDouble("douban_score");
            cVar.i = jSONObject.optString("jump_url");
            cVar.j = jSONObject.optString("play_url");
            return cVar;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public final String toString() {
        return new StringBuilder("SubChoicenessInfo{title='").append(this.a).append('\'').append(", id='").append(this.b).append('\'').append(", coverUrl='").append(this.c).append('\'').append(", resType='").append(this.d).append('\'').append(", likeCount=").append(this.e).append(", doubanScore=").append(this.f).append(", parentResId='").append(this.g).append('\'').append(", parentResType='").append(this.h).append('\'').append(", jumpUrl='").append(this.i).append('\'').append(", playUrl='").append(this.j).append('\'').append('}').toString();
    }
}
