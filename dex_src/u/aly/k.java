package u.aly;

import org.json.JSONArray;

// compiled from: UPage.java
public final class k extends bi {
    public k(String str) throws Exception {
        JSONArray jSONArray = new JSONArray(str);
        this.a = jSONArray.getString(0);
        this.b = (long) jSONArray.getInt(1);
        a();
    }
}
