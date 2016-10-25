package com.inmobi.signals;

import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.f;
import com.inmobi.signals.activityrecognition.a;
import com.umeng.message.MsgConstant;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

// compiled from: IceSample.java
public class l {
    private static final String a;
    private long b;
    private Map<String, Object> c;
    private f d;
    private List<m> e;
    private List<a> f;

    static {
        a = l.class.getSimpleName();
    }

    public l() {
        a(Calendar.getInstance().getTimeInMillis());
    }

    public void a(long j) {
        this.b = j;
    }

    public void a(Map<String, Object> map) {
        this.c = map;
    }

    public void a(f fVar) {
        this.d = fVar;
    }

    public void a(List<m> list) {
        this.e = list;
    }

    public void b(List<a> list) {
        this.f = list;
    }

    public JSONObject a() {
        int i = 0;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(MsgConstant.KEY_TS, this.b);
            if (!(this.c == null || this.c.isEmpty())) {
                jSONObject.put("l", new JSONObject(this.c));
            }
            if (this.d != null) {
                jSONObject.put("s", this.d.b());
            }
            JSONArray jSONArray = new JSONArray();
            for (int i2 = 0; i2 < this.e.size(); i2++) {
                jSONArray.put(((m) this.e.get(i2)).b());
            }
            if (jSONArray.length() > 0) {
                jSONObject.put(IXAdRequestInfo.WIDTH, jSONArray);
            }
            if (this.f != null) {
                JSONArray jSONArray2 = new JSONArray();
                while (i < this.f.size()) {
                    jSONArray2.put(((a) this.f.get(i)).a());
                    i++;
                }
                if (jSONArray2.length() > 0) {
                    jSONObject.put("ar", jSONArray2);
                }
            }
        } catch (Throwable e) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Error while converting IceSample to string.", e);
        }
        return jSONObject;
    }
}
