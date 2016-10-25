package com.sina.weibo.sdk.auth;

import android.os.Bundle;
import android.text.TextUtils;
import com.umeng.a;
import org.android.agoo.message.MessageService;
import org.json.JSONException;
import org.json.JSONObject;

public class Oauth2AccessToken {
    public static final String KEY_ACCESS_TOKEN = "access_token";
    public static final String KEY_EXPIRES_IN = "expires_in";
    public static final String KEY_PHONE_NUM = "phone_num";
    public static final String KEY_REFRESH_TOKEN = "refresh_token";
    public static final String KEY_UID = "uid";
    private String mAccessToken;
    private long mExpiresTime;
    private String mPhoneNum;
    private String mRefreshToken;
    private String mUid;

    public Oauth2AccessToken() {
        this.mUid = a.d;
        this.mAccessToken = a.d;
        this.mRefreshToken = a.d;
        this.mExpiresTime = 0;
        this.mPhoneNum = a.d;
    }

    @Deprecated
    public Oauth2AccessToken(String str) {
        this.mUid = a.d;
        this.mAccessToken = a.d;
        this.mRefreshToken = a.d;
        this.mExpiresTime = 0;
        this.mPhoneNum = a.d;
        if (str != null && str.indexOf("{") >= 0) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                setUid(jSONObject.optString(KEY_UID));
                setToken(jSONObject.optString(KEY_ACCESS_TOKEN));
                setExpiresIn(jSONObject.optString(KEY_EXPIRES_IN));
                setRefreshToken(jSONObject.optString(KEY_REFRESH_TOKEN));
                setPhoneNum(jSONObject.optString(KEY_PHONE_NUM));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public Oauth2AccessToken(String str, String str2) {
        this.mUid = a.d;
        this.mAccessToken = a.d;
        this.mRefreshToken = a.d;
        this.mExpiresTime = 0;
        this.mPhoneNum = a.d;
        this.mAccessToken = str;
        this.mExpiresTime = System.currentTimeMillis();
        if (str2 != null) {
            this.mExpiresTime += Long.parseLong(str2) * 1000;
        }
    }

    public static Oauth2AccessToken parseAccessToken(String str) {
        if (!TextUtils.isEmpty(str) && str.indexOf("{") >= 0) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                Oauth2AccessToken oauth2AccessToken = new Oauth2AccessToken();
                oauth2AccessToken.setUid(jSONObject.optString(KEY_UID));
                oauth2AccessToken.setToken(jSONObject.optString(KEY_ACCESS_TOKEN));
                oauth2AccessToken.setExpiresIn(jSONObject.optString(KEY_EXPIRES_IN));
                oauth2AccessToken.setRefreshToken(jSONObject.optString(KEY_REFRESH_TOKEN));
                oauth2AccessToken.setPhoneNum(jSONObject.optString(KEY_PHONE_NUM));
                return oauth2AccessToken;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Oauth2AccessToken parseAccessToken(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        Oauth2AccessToken oauth2AccessToken = new Oauth2AccessToken();
        oauth2AccessToken.setUid(getString(bundle, KEY_UID, a.d));
        oauth2AccessToken.setToken(getString(bundle, KEY_ACCESS_TOKEN, a.d));
        oauth2AccessToken.setExpiresIn(getString(bundle, KEY_EXPIRES_IN, a.d));
        oauth2AccessToken.setRefreshToken(getString(bundle, KEY_REFRESH_TOKEN, a.d));
        oauth2AccessToken.setPhoneNum(getString(bundle, KEY_PHONE_NUM, a.d));
        return oauth2AccessToken;
    }

    public boolean isSessionValid() {
        return !TextUtils.isEmpty(this.mAccessToken);
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_UID, this.mUid);
        bundle.putString(KEY_ACCESS_TOKEN, this.mAccessToken);
        bundle.putString(KEY_REFRESH_TOKEN, this.mRefreshToken);
        bundle.putString(KEY_EXPIRES_IN, Long.toString(this.mExpiresTime));
        bundle.putString(KEY_PHONE_NUM, this.mPhoneNum);
        return bundle;
    }

    public String toString() {
        return new StringBuilder("uid: ").append(this.mUid).append(", access_token: ").append(this.mAccessToken).append(", refresh_token: ").append(this.mRefreshToken).append(", phone_num: ").append(this.mPhoneNum).append(", expires_in: ").append(Long.toString(this.mExpiresTime)).toString();
    }

    public String getUid() {
        return this.mUid;
    }

    public void setUid(String str) {
        this.mUid = str;
    }

    public String getToken() {
        return this.mAccessToken;
    }

    public void setToken(String str) {
        this.mAccessToken = str;
    }

    public String getRefreshToken() {
        return this.mRefreshToken;
    }

    public void setRefreshToken(String str) {
        this.mRefreshToken = str;
    }

    public long getExpiresTime() {
        return this.mExpiresTime;
    }

    public void setExpiresTime(long j) {
        this.mExpiresTime = j;
    }

    public void setExpiresIn(String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(MessageService.MSG_DB_READY_REPORT)) {
            setExpiresTime(System.currentTimeMillis() + (Long.parseLong(str) * 1000));
        }
    }

    private static String getString(Bundle bundle, String str, String str2) {
        if (bundle == null) {
            return str2;
        }
        String string = bundle.getString(str);
        return string != null ? string : str2;
    }

    public String getPhoneNum() {
        return this.mPhoneNum;
    }

    private void setPhoneNum(String str) {
        this.mPhoneNum = str;
    }
}
