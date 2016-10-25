package com.inmobi.commons.core.configs;

import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: Config.java
public abstract class a {
    private a a;

    // compiled from: Config.java
    public static final class a {
        private HashMap<String, Boolean> a;

        public a() {
            this.a = new HashMap();
            this.a.put("O1", Boolean.valueOf(true));
            this.a.put("SID", Boolean.valueOf(true));
            this.a.put("LID", Boolean.valueOf(true));
            this.a.put("UM5", Boolean.valueOf(true));
            this.a.put("GPID", Boolean.valueOf(true));
            this.a.put("IMID", Boolean.valueOf(true));
            this.a.put("AIDL", Boolean.valueOf(true));
        }

        public final HashMap<String, Boolean> a() {
            return this.a;
        }
    }

    public abstract String a();

    public abstract boolean c();

    public abstract a d();

    public a() {
        this.a = new a();
    }

    public void a(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("includeIds");
        for (int i = 0; i < jSONObject2.length(); i++) {
            this.a.a.put("O1", Boolean.valueOf(jSONObject2.getBoolean("O1")));
            this.a.a.put("SID", Boolean.valueOf(jSONObject2.getBoolean("SID")));
            this.a.a.put("LID", Boolean.valueOf(jSONObject2.getBoolean("LID")));
            this.a.a.put("UM5", Boolean.valueOf(jSONObject2.getBoolean("UM5")));
            this.a.a.put("GPID", Boolean.valueOf(jSONObject2.getBoolean("GPID")));
            this.a.a.put("IMID", Boolean.valueOf(jSONObject2.getBoolean("IMID")));
            this.a.a.put("AIDL", Boolean.valueOf(jSONObject2.getBoolean("AIDL")));
        }
    }

    public JSONObject b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("O1", this.a.a.get("O1"));
        jSONObject2.put("SID", this.a.a.get("SID"));
        jSONObject2.put("LID", this.a.a.get("LID"));
        jSONObject2.put("UM5", this.a.a.get("UM5"));
        jSONObject2.put("GPID", this.a.a.get("GPID"));
        jSONObject2.put("IMID", this.a.a.get("IMID"));
        jSONObject2.put("AIDL", this.a.a.get("AIDL"));
        jSONObject.put("includeIds", jSONObject2);
        return jSONObject;
    }

    public a o() {
        return this.a;
    }

    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == getClass() && a().equals(((a) obj).a());
    }

    public int hashCode() {
        return a().hashCode();
    }
}
