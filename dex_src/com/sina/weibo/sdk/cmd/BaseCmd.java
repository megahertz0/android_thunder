package com.sina.weibo.sdk.cmd;

import com.sina.weibo.sdk.exception.WeiboException;
import org.json.JSONException;
import org.json.JSONObject;

class BaseCmd {
    private long mNotificationDelay;
    private String mNotificationText;
    private String mNotificationTitle;

    public BaseCmd(String str) throws WeiboException {
        initFromJsonStr(str);
    }

    public BaseCmd(JSONObject jSONObject) {
        initFromJsonObj(jSONObject);
    }

    protected void initFromJsonStr(String str) throws WeiboException {
        if (str != null) {
            try {
                initFromJsonObj(new JSONObject(str));
            } catch (JSONException e) {
                e.printStackTrace();
                throw new WeiboException("pase cmd has error !!!");
            }
        }
    }

    protected void initFromJsonObj(JSONObject jSONObject) {
        this.mNotificationText = jSONObject.optString("notification_text");
        this.mNotificationTitle = jSONObject.optString("notification_title");
        this.mNotificationDelay = jSONObject.optLong("notification_delay");
    }

    public String getNotificationText() {
        return this.mNotificationText;
    }

    public void setNotificationText(String str) {
        this.mNotificationText = str;
    }

    public String getNotificationTitle() {
        return this.mNotificationTitle;
    }

    public void setNotificationTitle(String str) {
        this.mNotificationTitle = str;
    }

    public long getNotificationDelay() {
        return this.mNotificationDelay;
    }

    public void setNotificationDelay(long j) {
        this.mNotificationDelay = j;
    }
}
