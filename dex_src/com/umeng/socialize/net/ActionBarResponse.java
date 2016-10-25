package com.umeng.socialize.net;

import com.umeng.socialize.net.base.SocializeReseponse;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.Log;
import com.xunlei.download.proguard.c;
import org.json.JSONObject;

public class ActionBarResponse extends SocializeReseponse {
    public int mCommentCount;
    public String mEntityKey;
    public int mFavorite;
    public int mFirstTime;
    public int mLikeCount;
    public int mPv;
    public int mShareCount;
    public String mSid;
    public String mUid;
    public String mUk;

    public ActionBarResponse(JSONObject jSONObject) {
        super(jSONObject);
    }

    public void parseJsonObject() {
        JSONObject jSONObject = this.mJsonData;
        if (jSONObject == null) {
            Log.e("SocializeReseponse", "data json is null....");
            return;
        }
        try {
            if (jSONObject.has(SocializeProtocolConstants.PROTOCOL_KEY_COMMENT_COUNT)) {
                this.mCommentCount = jSONObject.getInt(SocializeProtocolConstants.PROTOCOL_KEY_COMMENT_COUNT);
            }
            if (jSONObject.has(SocializeProtocolConstants.PROTOCOL_KEY_ENTITY_KEY)) {
                this.mEntityKey = jSONObject.getString(SocializeProtocolConstants.PROTOCOL_KEY_ENTITY_KEY);
            }
            if (jSONObject.has(SocializeProtocolConstants.PROTOCOL_KEY_FRIST_TIME)) {
                this.mFirstTime = jSONObject.getInt(SocializeProtocolConstants.PROTOCOL_KEY_FRIST_TIME);
            }
            if (jSONObject.has(SocializeProtocolConstants.PROTOCOL_KEY_FR)) {
                this.mFavorite = jSONObject.optInt(SocializeProtocolConstants.PROTOCOL_KEY_FR, 0);
            }
            if (jSONObject.has(SocializeProtocolConstants.PROTOCOL_KEY_LIKE_COUNT)) {
                this.mLikeCount = jSONObject.getInt(SocializeProtocolConstants.PROTOCOL_KEY_LIKE_COUNT);
            }
            if (jSONObject.has(SocializeProtocolConstants.PROTOCOL_KEY_PV)) {
                this.mPv = jSONObject.getInt(SocializeProtocolConstants.PROTOCOL_KEY_PV);
            }
            if (jSONObject.has(SocializeProtocolConstants.PROTOCOL_KEY_SID)) {
                this.mSid = jSONObject.getString(SocializeProtocolConstants.PROTOCOL_KEY_SID);
            }
            if (jSONObject.has(c.f)) {
                this.mUid = jSONObject.getString(c.f);
            }
            if (jSONObject.has(SocializeProtocolConstants.PROTOCOL_KEY_SHARE_NUM)) {
                this.mShareCount = jSONObject.getInt(SocializeProtocolConstants.PROTOCOL_KEY_SHARE_NUM);
            }
        } catch (Exception e) {
            Log.e("SocializeReseponse", new StringBuilder("Parse json error[ ").append(jSONObject.toString()).append(" ]").toString(), e);
        }
    }
}
