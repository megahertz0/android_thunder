package com.umeng.socialize.net.utils;

import com.tencent.connect.common.Constants;
import java.util.Map;
import org.json.JSONObject;

public abstract class URequest {
    public static String GET;
    public static String POST;
    public String mBaseUrl;

    public abstract String toGetUrl();

    public abstract JSONObject toJson();

    static {
        POST = Constants.HTTP_POST;
        GET = Constants.HTTP_GET;
    }

    public String getHttpMethod() {
        return POST;
    }

    public URequest(String str) {
        this.mBaseUrl = str;
    }

    public void setBaseUrl(String str) {
        this.mBaseUrl = str;
    }

    public String getBaseUrl() {
        return this.mBaseUrl;
    }

    public Map<String, Object> getBodyPair() {
        return null;
    }

    public Map<String, FilePair> getFilePair() {
        return null;
    }

    public void onPrepareRequest() {
    }
}
