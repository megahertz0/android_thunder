package com.tencent.wxop.stat.a;

import android.content.Context;
import com.tencent.wxop.stat.StatAccount;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.common.q;
import org.json.JSONObject;

public class a extends e {
    private StatAccount a;

    public a(Context context, int i, StatAccount statAccount, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        this.a = null;
        this.a = statAccount;
    }

    public f a() {
        return f.e;
    }

    public boolean a(JSONObject jSONObject) {
        q.a(jSONObject, "qq", this.a.getAccount());
        jSONObject.put("acc", this.a.toJsonString());
        return true;
    }
}
