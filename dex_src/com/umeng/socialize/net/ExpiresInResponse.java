package com.umeng.socialize.net;

import com.umeng.socialize.net.base.SocializeReseponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

public class ExpiresInResponse extends SocializeReseponse {
    public Map<String, Object> mExpiresMap;

    public ExpiresInResponse(JSONObject jSONObject) {
        super(jSONObject);
    }

    public void parseJsonObject() {
        super.parseJsonObject();
        JSONObject jSONObject = this.mJsonData;
        if (jSONObject != null) {
            this.mExpiresMap = new HashMap();
            Iterator keys = jSONObject.keys();
            Boolean.valueOf(false);
            while (keys.hasNext()) {
                String str = (String) keys.next();
                this.mExpiresMap.put(str, Boolean.valueOf(jSONObject.optBoolean(str, false)));
            }
        }
    }
}
