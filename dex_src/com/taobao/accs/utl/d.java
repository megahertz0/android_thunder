package com.taobao.accs.utl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: Taobao
public class d {

    // compiled from: Taobao
    public static class a {
        JSONObject a;

        public a() {
            this.a = new JSONObject();
        }

        public com.taobao.accs.utl.d.a a(String str, String str2) {
            if (!(str2 == null || str == null)) {
                try {
                    this.a.put(str, str2);
                } catch (JSONException e) {
                }
            }
            return this;
        }

        public com.taobao.accs.utl.d.a a(String str, Integer num) {
            if (num != null) {
                try {
                    this.a.put(str, num);
                } catch (JSONException e) {
                }
            }
            return this;
        }

        public com.taobao.accs.utl.d.a a(String str, JSONArray jSONArray) {
            if (jSONArray != null) {
                try {
                    this.a.put(str, jSONArray);
                } catch (JSONException e) {
                }
            }
            return this;
        }

        public JSONObject a() {
            return this.a;
        }
    }

    public static String a(JSONObject jSONObject, String str, String str2) throws JSONException {
        return (jSONObject != null && jSONObject.has(str)) ? jSONObject.getString(str) : str2;
    }
}
