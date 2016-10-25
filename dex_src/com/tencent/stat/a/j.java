package com.tencent.stat.a;

import android.content.Context;
import com.qq.e.comm.constants.Constants.KEYS;
import com.tencent.stat.common.k;
import org.json.JSONObject;

public class j extends e {
    Long a;
    String l;
    String m;

    public j(Context context, String str, String str2, int i, Long l) {
        super(context, i);
        this.a = null;
        this.m = str;
        this.l = str2;
        this.a = l;
    }

    public f a() {
        return f.a;
    }

    public boolean a(JSONObject jSONObject) {
        k.a(jSONObject, "pi", this.l);
        k.a(jSONObject, KEYS.Banner_RF, this.m);
        if (this.a != null) {
            jSONObject.put("du", this.a);
        }
        return true;
    }
}
