package com.baidu.mobads.interfaces.download.activate;

import org.json.JSONObject;

public interface IXAppInfo {
    JSONObject convertToJsonObject();

    String getAdId();

    long getAppSize();

    long getClickTime();

    long getExpireTimestamp();

    String getPackageName();

    String getProd();

    String getQk();

    boolean isExpired();

    boolean isTooLarge();

    void setAdId(String str);

    void setAppSize(long j);

    void setClickTime(long j);

    void setPackageName(String str);

    void setProd(String str);

    void setQk(String str);

    void setTooLarge(boolean z);
}
