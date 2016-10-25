package com.umeng.socialize.net;

import com.umeng.socialize.net.base.SocializeReseponse;
import org.json.JSONObject;

public class UploadImageResponse extends SocializeReseponse {
    public String largeImageUrl;
    public String thumbImageUrl;

    public UploadImageResponse(JSONObject jSONObject) {
        super(jSONObject);
    }

    public void parseJsonObject() {
        super.parseJsonObject();
        JSONObject jSONObject = this.mJsonData;
        if (jSONObject != null) {
            this.largeImageUrl = jSONObject.optString("large_url");
            this.thumbImageUrl = jSONObject.optString("small_url");
        }
    }
}
