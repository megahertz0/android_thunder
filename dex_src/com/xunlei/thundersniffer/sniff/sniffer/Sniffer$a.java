package com.xunlei.thundersniffer.sniff.sniffer;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

private class Sniffer$a {
    public ArrayList<String> a;
    public int b;
    final /* synthetic */ Sniffer c;

    public Sniffer$a(Sniffer sniffer, String str) {
        this.c = sniffer;
        this.a = new ArrayList();
        this.b = -1;
        a(str);
    }

    private void a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONArray optJSONArray = jSONObject.optJSONArray("urls");
            this.b = jSONObject.optInt("pageNo", -1);
            if (this.b == 0) {
                this.b = 1;
            }
            JSONArray jSONArray = optJSONArray;
        } catch (JSONException e) {
            try {
                jSONArray = new JSONArray(str);
            } catch (Exception e2) {
                jSONArray = null;
            }
        }
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    if (!this.a.contains(jSONArray.optString(i))) {
                        new StringBuilder("url --> ").append(jSONArray.optString(i));
                        this.a.add(jSONArray.optString(i));
                    }
                } catch (Exception e3) {
                }
            }
        }
    }
}
