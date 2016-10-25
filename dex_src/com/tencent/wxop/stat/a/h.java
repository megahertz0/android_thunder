package com.tencent.wxop.stat.a;

import android.content.Context;
import com.alipay.sdk.sys.a;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.tencent.wxop.stat.StatAppMonitor;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.common.k;
import com.tencent.wxop.stat.common.q;
import org.json.JSONObject;

public class h extends e {
    private static String m;
    private static String n;
    private StatAppMonitor a;

    static {
        m = null;
        n = null;
    }

    public h(Context context, int i, StatAppMonitor statAppMonitor, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
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
        if (n == null) {
            n = k.n(this.l);
        }
        q.a(jSONObject, a.k, n);
        if (m == null) {
            m = k.i(this.l);
        }
        q.a(jSONObject, "op", m);
        jSONObject.put("cn", com.tencent.wxop.stat.a.a(this.l).b());
        return true;
    }
}
