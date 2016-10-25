package com.tencent.stat.a;

import android.content.Context;
import com.tencent.stat.StatGameUser;
import com.tencent.stat.common.k;
import org.json.JSONObject;

public class g extends e {
    private StatGameUser a;

    public g(Context context, int i, StatGameUser statGameUser) {
        super(context, i);
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
        k.a(jSONObject, "wod", this.a.getWorldName());
        k.a(jSONObject, "gid", this.a.getAccount());
        k.a(jSONObject, "lev", this.a.getLevel());
        return true;
    }
}
