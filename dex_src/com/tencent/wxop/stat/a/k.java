package com.tencent.wxop.stat.a;

import android.content.Context;
import com.qq.e.comm.constants.Constants.KEYS;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.common.q;
import org.json.JSONObject;

public class k extends e {
    Long a;
    String m;
    String n;

    public k(Context context, String str, String str2, int i, Long l, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        this.a = null;
        this.n = str;
        this.m = str2;
        this.a = l;
    }

    public f a() {
        return f.a;
    }

    public boolean a(JSONObject jSONObject) {
        q.a(jSONObject, "pi", this.m);
        q.a(jSONObject, KEYS.Banner_RF, this.n);
        if (this.a != null) {
            jSONObject.put("du", this.a);
        }
        return true;
    }
}
