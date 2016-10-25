package com.tencent.stat.a;

import android.content.Context;
import com.tencent.stat.StatConfig;
import com.tencent.stat.common.k;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

public class a extends e {
    Map<String, ?> a;

    public a(Context context, int i, Map<String, ?> map) {
        super(context, i);
        this.a = null;
        this.a = map;
    }

    public f a() {
        return f.e;
    }

    public boolean a(JSONObject jSONObject) {
        k.a(jSONObject, "qq", StatConfig.getQQ());
        if (this.a != null && this.a.size() > 0) {
            for (Entry entry : this.a.entrySet()) {
                jSONObject.put((String) entry.getKey(), entry.getValue());
            }
        }
        return true;
    }
}
