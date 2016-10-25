package com.xunlei.downloadprovider.download.c;

import java.util.ArrayList;
import java.util.List;
import org.android.agoo.common.AgooConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: SpeedLimitModel.java
public final class g {
    public static int f;
    public static int g;
    String a;
    String b;
    int c;
    int d;
    int e;
    private int h;

    static {
        f = 0;
        g = 1;
    }

    public static List<g> a(JSONArray jSONArray) {
        List<g> arrayList = new ArrayList();
        new StringBuilder("jsonArray.length() == ").append(jSONArray.length());
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                g gVar;
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject == null) {
                    gVar = null;
                } else {
                    int optInt = jSONObject.optInt(AgooConstants.MESSAGE_ID);
                    String optString = jSONObject.optString("user_group");
                    String optString2 = jSONObject.optString("version");
                    int optInt2 = jSONObject.optInt("speed");
                    int optInt3 = jSONObject.optInt("is_speed");
                    int optInt4 = jSONObject.optInt("speed_type");
                    gVar = new g();
                    gVar.h = optInt;
                    gVar.a = optString;
                    gVar.b = optString2;
                    gVar.d = optInt2;
                    gVar.c = optInt3;
                    gVar.e = optInt4;
                }
                arrayList.add(gVar);
                new StringBuilder("speedLimitModel == ").append(gVar.toString());
                i++;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        return arrayList;
    }

    public final String toString() {
        return new StringBuilder("SpeedLimitModel{id=").append(this.h).append(", userGroup='").append(this.a).append('\'').append(", version='").append(this.b).append('\'').append(", isLimit=").append(this.c).append(", speedLimit=").append(this.d).append(", speedType=").append(this.e).append('}').toString();
    }
}
