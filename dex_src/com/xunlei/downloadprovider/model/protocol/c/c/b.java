package com.xunlei.downloadprovider.model.protocol.c.c;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.downloadprovider.b.c.f;
import com.xunlei.downloadprovider.model.protocol.c.c;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: QueryGroupResExtendParser.java
public final class b extends f {
    public final Object parseJson(JSONObject jSONObject) throws JSONException {
        c cVar = new c();
        cVar.a = jSONObject.getInt("rtn");
        if (cVar.a == 0) {
            JSONArray optJSONArray = jSONObject.optJSONArray(SocializeProtocolConstants.PROTOCOL_KEY_DATA);
            if (optJSONArray != null) {
                cVar.c = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    try {
                        cVar.c.add(c.a(optJSONArray.getJSONObject(i)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            cVar.b = jSONObject.optString("errorReason", BuildConfig.VERSION_NAME);
        }
        return cVar;
    }
}
