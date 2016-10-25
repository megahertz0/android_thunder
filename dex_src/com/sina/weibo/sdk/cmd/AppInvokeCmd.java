package com.sina.weibo.sdk.cmd;

import com.sina.weibo.sdk.exception.WeiboException;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

class AppInvokeCmd extends BaseCmd {
    private String appPackage;
    private String scheme;
    private String url;

    public AppInvokeCmd(String str) throws WeiboException {
        super(str);
    }

    public AppInvokeCmd(JSONObject jSONObject) {
        super(jSONObject);
    }

    public void initFromJsonObj(JSONObject jSONObject) {
        super.initFromJsonObj(jSONObject);
        this.appPackage = jSONObject.optString("package");
        this.scheme = jSONObject.optString("scheme");
        this.url = jSONObject.optString(SocialConstants.PARAM_URL);
    }

    public String getAppPackage() {
        return this.appPackage;
    }

    public void setAppPackage(String str) {
        this.appPackage = str;
    }

    public String getScheme() {
        return this.scheme;
    }

    public void setScheme(String str) {
        this.scheme = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
