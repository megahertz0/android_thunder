package com.umeng.socialize.net;

import com.umeng.socialize.net.base.SocializeReseponse;
import java.util.Map;
import org.json.JSONObject;

public class UpdatePlatformKeyResponse extends SocializeReseponse {
    public Map<String, Object> mData;
    public Map<String, String> mExtraData;
    public Map<String, String> mSecrets;

    public UpdatePlatformKeyResponse(JSONObject jSONObject) {
        super(jSONObject);
    }
}
