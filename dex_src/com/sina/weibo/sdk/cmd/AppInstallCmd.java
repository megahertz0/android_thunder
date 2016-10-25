package com.sina.weibo.sdk.cmd;

import android.text.TextUtils;
import com.sina.weibo.sdk.exception.WeiboException;
import java.util.Arrays;
import java.util.List;
import org.android.agoo.common.b;
import org.json.JSONObject;

class AppInstallCmd extends BaseCmd {
    private List<String> appPackages;
    private String appSign;
    private long appVersion;
    private String downloadUrl;

    public AppInstallCmd(String str) throws WeiboException {
        super(str);
    }

    public AppInstallCmd(JSONObject jSONObject) {
        super(jSONObject);
    }

    public void initFromJsonObj(JSONObject jSONObject) {
        super.initFromJsonObj(jSONObject);
        this.downloadUrl = jSONObject.optString("download_url");
        Object optString = jSONObject.optString("app_package");
        if (!TextUtils.isEmpty(optString)) {
            this.appPackages = Arrays.asList(optString.split("\\|"));
        }
        this.appSign = jSONObject.optString("app_sign");
        this.appVersion = jSONObject.optLong(b.PROPERTY_APP_VERSION);
    }

    public long getAppVersion() {
        return this.appVersion;
    }

    public void setAppVersion(long j) {
        this.appVersion = j;
    }

    public List<String> getAppPackage() {
        return this.appPackages;
    }

    public void setAppPackage(List<String> list) {
        this.appPackages = list;
    }

    public String getAppSign() {
        return this.appSign;
    }

    public void setAppSign(String str) {
        this.appSign = str;
    }

    public String getDownloadUrl() {
        return this.downloadUrl;
    }

    public void setDownloadUrl(String str) {
        this.downloadUrl = str;
    }
}
