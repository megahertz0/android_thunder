package com.umeng.socialize.a;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.net.base.SocializeReseponse;
import java.util.Map;
import org.json.JSONObject;

// compiled from: AnalyticsResponse.java
public class b extends SocializeReseponse {
    public Map<SHARE_MEDIA, Integer> a;
    public String b;
    public SHARE_MEDIA c;

    public b(JSONObject jSONObject) {
        super(jSONObject);
    }

    public String toString() {
        return new StringBuilder("ShareMultiResponse [mInfoMap=").append(this.a).append(", mWeiboId=").append(this.b).append(", mMsg=").append(this.mMsg).append(", mStCode=").append(this.mStCode).append("]").toString();
    }
}
