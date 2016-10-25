package com.inmobi.signals;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.signals.b.a;
import com.umeng.message.MsgConstant;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONObject;

// compiled from: IceWifiSample.java
public class m {
    private static final String a;
    private long b;
    private a c;
    private List<a> d;
    private Map<String, String> e;

    static {
        a = m.class.getSimpleName();
    }

    public m() {
        a(Calendar.getInstance().getTimeInMillis());
    }

    public void a(long j) {
        this.b = j;
    }

    public void a(Map<String, String> map) {
        this.e = map;
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public void a(List<a> list) {
        this.d = list;
    }

    public boolean a() {
        return (this.c == null && this.d == null) ? false : true;
    }

    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(MsgConstant.KEY_TS, this.b);
            if (!(this.e == null || this.e.isEmpty())) {
                for (Entry entry : this.e.entrySet()) {
                    jSONObject.put((String) entry.getKey(), entry.getValue());
                }
            }
            if (this.c != null) {
                jSONObject.put("c-ap", this.c.b());
            }
            JSONArray jSONArray = new JSONArray();
            if (this.d != null) {
                for (int i = 0; i < this.d.size(); i++) {
                    jSONArray.put(((a) this.d.get(i)).b());
                }
                if (jSONArray.length() > 0) {
                    jSONObject.put("v-ap", jSONArray);
                }
            }
        } catch (Throwable e) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Error while converting IceWifiCellSample to string.", e);
        }
        return jSONObject;
    }
}
