package com.umeng.socialize.net;

import anet.channel.util.ErrorConstant;
import com.umeng.socialize.net.base.SocializeReseponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class ShareMultiFollowResponse extends SocializeReseponse {
    public Map<String, Integer> mInfoMap;

    public ShareMultiFollowResponse(JSONObject jSONObject) {
        super(jSONObject);
    }

    public void parseJsonObject() {
        super.parseJsonObject();
        this.mInfoMap = new HashMap();
        Iterator keys = this.mJsonData.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            try {
                int optInt;
                JSONObject jSONObject = this.mJsonData.getJSONObject(str);
                if (jSONObject != null) {
                    optInt = jSONObject.optInt("st", ErrorConstant.ERROR_PARAM_ILLEGAL);
                } else {
                    optInt = -102;
                }
                this.mInfoMap.put(str, Integer.valueOf(optInt));
            } catch (JSONException e) {
            }
        }
    }
}
