package com.tencent.wxop.stat.a;

import android.content.Context;
import com.tencent.wxop.stat.StatServiceImpl;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

public class b extends e {
    protected c a;
    private long m;

    public b(Context context, int i, String str, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        this.a = new c();
        this.m = -1;
        this.a.a = str;
    }

    private void h() {
        if (this.a.a != null) {
            Map commonKeyValueForKVEvent = StatServiceImpl.getCommonKeyValueForKVEvent(this.a.a);
            if (commonKeyValueForKVEvent != null && commonKeyValueForKVEvent.size() > 0) {
                if (this.a.c == null || this.a.c.length() == 0) {
                    this.a.c = new JSONObject(commonKeyValueForKVEvent);
                    return;
                }
                for (Entry entry : commonKeyValueForKVEvent.entrySet()) {
                    try {
                        this.a.c.put(entry.getKey().toString(), entry.getValue());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public f a() {
        return f.d;
    }

    public void a(long j) {
        this.m = j;
    }

    public boolean a(JSONObject jSONObject) {
        jSONObject.put("ei", this.a.a);
        if (this.m > 0) {
            jSONObject.put("du", this.m);
        }
        if (this.a.b == null) {
            h();
            jSONObject.put("kv", this.a.c);
        } else {
            jSONObject.put("ar", this.a.b);
        }
        return true;
    }

    public c b() {
        return this.a;
    }
}
