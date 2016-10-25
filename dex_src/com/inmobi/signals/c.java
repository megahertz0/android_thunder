package com.inmobi.signals;

import com.inmobi.commons.core.c.a;
import com.inmobi.commons.core.c.e;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.taobao.accs.common.Constants;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.common.SocializeConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

// compiled from: CarbGetListNetworkResponse.java
public class c {
    private static final String a;
    private com.inmobi.commons.core.network.c b;
    private boolean c;
    private List<d> d;
    private String e;
    private int f;

    static {
        a = c.class.getSimpleName();
    }

    public c(com.inmobi.commons.core.network.c cVar) {
        this.c = true;
        this.f = 0;
        this.b = cVar;
        this.d = new ArrayList();
        f();
        if (this.c) {
            a.a().a(new e("signals", "InvalidCarbGetResponse"));
        }
    }

    private void f() {
        if (this.b.a()) {
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Error response for Carb list received. Error code:").append(this.b.c().a()).toString());
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(this.b.b());
            if (jSONObject.getBoolean(MsgConstant.KEY_SUCCESS)) {
                jSONObject = jSONObject.getJSONObject(SocializeConstants.JSON_DATA);
                this.e = jSONObject.getString("req_id");
                JSONArray jSONArray = jSONObject.getJSONArray("p_apps");
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    String optString = jSONObject2.optString("bid", null);
                    String optString2 = jSONObject2.optString("inm_id", null);
                    if (optString != null && optString2 != null && optString2.trim().length() > 0) {
                        this.d.add(new d(optString, optString2));
                    }
                    this.f = i + 1;
                }
            } else {
                Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Error response for Carb list received. Error code:").append(jSONObject.optInt(Constants.KEY_ERROR_CODE)).toString());
            }
            this.c = false;
        } catch (Throwable e) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Bad response for Carb list received.", e);
        }
    }

    public boolean a() {
        return this.c;
    }

    public List<d> b() {
        return this.d;
    }

    public String c() {
        return this.e;
    }

    public int d() {
        return this.f;
    }

    public boolean e() {
        return this.f == 0;
    }
}
