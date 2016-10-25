package com.tencent.stat.a;

import android.content.Context;
import com.alipay.sdk.sys.a;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.tencent.stat.StatAppMonitor;
import com.tencent.stat.common.k;
import org.json.JSONObject;

public class h extends e {
    private static String l;
    private static String m;
    private StatAppMonitor a;

    static {
        l = null;
        m = null;
    }

    public h(Context context, int i, StatAppMonitor statAppMonitor) {
        super(context, i);
        this.a = null;
        this.a = statAppMonitor.clone();
    }

    public f a() {
        return f.f;
    }

    public boolean a(JSONObject jSONObject) {
        if (this.a == null) {
            return false;
        }
        jSONObject.put("na", this.a.getInterfaceName());
        jSONObject.put("rq", this.a.getReqSize());
        jSONObject.put("rp", this.a.getRespSize());
        jSONObject.put("rt", this.a.getResultType());
        jSONObject.put(IXAdRequestInfo.MAX_TITLE_LENGTH, this.a.getMillisecondsConsume());
        jSONObject.put("rc", this.a.getReturnCode());
        jSONObject.put("sp", this.a.getSampling());
        if (m == null) {
            m = k.r(this.k);
        }
        k.a(jSONObject, a.k, m);
        if (l == null) {
            l = k.m(this.k);
        }
        k.a(jSONObject, "op", l);
        jSONObject.put("cn", k.p(this.k));
        return true;
    }
}
