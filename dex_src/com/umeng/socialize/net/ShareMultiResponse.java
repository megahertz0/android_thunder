package com.umeng.socialize.net;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.net.base.SocializeReseponse;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.HashMap;
import java.util.Map;
import org.android.agoo.common.AgooConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class ShareMultiResponse extends SocializeReseponse {
    public Map<SHARE_MEDIA, Integer> mInfoMap;
    public String mWeiboId;
    public SHARE_MEDIA platform;

    public ShareMultiResponse(JSONObject jSONObject) {
        super(jSONObject);
    }

    public void parseJsonObject() {
        super.parseJsonObject();
        this.mInfoMap = new HashMap();
        SHARE_MEDIA[] defaultPlatform = SHARE_MEDIA.getDefaultPlatform();
        if (defaultPlatform != null) {
            int length = defaultPlatform.length;
            for (int i = 0; i < length; i++) {
                SHARE_MEDIA share_media = defaultPlatform[i];
                String toString = share_media.toString();
                if (this.mJsonData.has(toString)) {
                    try {
                        JSONObject jSONObject = this.mJsonData.getJSONObject(toString);
                        JSONObject jSONObject2 = jSONObject.getJSONObject(SocializeProtocolConstants.PROTOCOL_KEY_DATA);
                        if (jSONObject2 != null && jSONObject2.has("send_result")) {
                            JSONObject jSONObject3 = null;
                            try {
                                jSONObject3 = jSONObject2.getJSONObject("send_result");
                            } catch (Exception e) {
                            }
                            if (jSONObject3 != null) {
                                this.mWeiboId = jSONObject3.optString(AgooConstants.MESSAGE_ID, BuildConfig.VERSION_NAME);
                                this.platform = share_media;
                            }
                        }
                        this.mInfoMap.put(share_media, Integer.valueOf(jSONObject.optInt(SocializeProtocolConstants.PROTOCOL_KEY_ST)));
                    } catch (JSONException e2) {
                    }
                }
            }
        }
    }

    public String toString() {
        return new StringBuilder("ShareMultiResponse [mInfoMap=").append(this.mInfoMap).append(", mWeiboId=").append(this.mWeiboId).append(", mMsg=").append(this.mMsg).append(", mStCode=").append(this.mStCode).append("]").toString();
    }
}
