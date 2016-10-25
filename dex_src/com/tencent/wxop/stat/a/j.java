package com.tencent.wxop.stat.a;

import android.content.Context;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.a;
import com.tencent.wxop.stat.common.k;
import com.tencent.wxop.stat.common.q;
import org.json.JSONObject;

public class j extends e {
    private static String a;
    private String m;
    private String n;

    static {
        a = null;
    }

    public j(Context context, int i, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        this.m = null;
        this.n = null;
        this.m = a.a(context).b();
        if (a == null) {
            a = k.i(context);
        }
    }

    public f a() {
        return f.h;
    }

    public void a(String str) {
        this.n = str;
    }

    public boolean a(JSONObject jSONObject) {
        q.a(jSONObject, "op", a);
        q.a(jSONObject, "cn", this.m);
        jSONObject.put("sp", this.n);
        return true;
    }
}
