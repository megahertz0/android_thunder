package com.xunlei.common.member;

import org.json.JSONObject;

public class XLThirdUserInfo {
    private JSONObject mThirdUserInfo;

    public XLThirdUserInfo(JSONObject jSONObject) {
        this.mThirdUserInfo = null;
        this.mThirdUserInfo = jSONObject;
    }

    public String getStringValue(String str) {
        return this.mThirdUserInfo == null ? null : this.mThirdUserInfo.optString(str);
    }
}
