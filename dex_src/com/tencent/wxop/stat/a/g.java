package com.tencent.wxop.stat.a;

import android.content.Context;
import com.tencent.wxop.stat.StatGameUser;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.common.q;
import org.json.JSONObject;

public class g extends e {
    private StatGameUser a;

    public g(Context context, int i, StatGameUser statGameUser, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        this.a = null;
        this.a = statGameUser.clone();
    }

    public f a() {
        return f.g;
    }

    public boolean a(JSONObject jSONObject) {
        if (this.a == null) {
            return false;
        }
        q.a(jSONObject, "wod", this.a.getWorldName());
        q.a(jSONObject, "gid", this.a.getAccount());
        q.a(jSONObject, "lev", this.a.getLevel());
        return true;
    }
}
