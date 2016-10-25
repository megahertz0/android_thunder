package com.umeng.socialize.handler;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

public class WeixinPreferences {
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String KEY_ACCESS_TOKEN_TTL = "expires_in";
    private static final String KEY_EXPIRES_IN = "expires_in";
    private static final String KEY_OPENID = "openid";
    private static final String KEY_REFRESH_TOKEN = "refresh_token";
    private static final String KEY_REFRESH_TOKEN_TTL = "rt_expires_in";
    private String mAccessToken;
    private long mAccessTokenTTL;
    private String mRefreshToken;
    private long mRefreshTokenTTL;
    private String mUID;
    private long mexpirein;
    private SharedPreferences sharedPreferences;

    public WeixinPreferences(Context context, String str) {
        this.sharedPreferences = null;
        this.sharedPreferences = context.getSharedPreferences(str, 0);
        this.mUID = this.sharedPreferences.getString(KEY_OPENID, null);
        this.mAccessToken = this.sharedPreferences.getString(KEY_ACCESS_TOKEN, null);
        this.mAccessTokenTTL = this.sharedPreferences.getLong(KEY_EXPIRES_IN, 0);
        this.mRefreshToken = this.sharedPreferences.getString(KEY_REFRESH_TOKEN, null);
        this.mRefreshTokenTTL = this.sharedPreferences.getLong(KEY_REFRESH_TOKEN_TTL, 0);
        this.mexpirein = this.sharedPreferences.getLong(KEY_EXPIRES_IN, 0);
    }

    public WeixinPreferences setAuthData(Map<String, String> map) {
        this.mUID = (String) map.get(KEY_OPENID);
        this.mAccessToken = (String) map.get(KEY_ACCESS_TOKEN);
        this.mRefreshToken = (String) map.get(KEY_REFRESH_TOKEN);
        String str = (String) map.get(KEY_EXPIRES_IN);
        if (!TextUtils.isEmpty(str)) {
            this.mexpirein = (Long.valueOf(str).longValue() * 1000) + System.currentTimeMillis();
        }
        str = (String) map.get(KEY_EXPIRES_IN);
        if (!TextUtils.isEmpty(str)) {
            this.mAccessTokenTTL = (Long.valueOf(str).longValue() * 1000) + System.currentTimeMillis();
        }
        str = (String) map.get(KEY_REFRESH_TOKEN_TTL);
        if (!TextUtils.isEmpty(str)) {
            this.mRefreshTokenTTL = (Long.valueOf(str).longValue() * 1000) + System.currentTimeMillis();
        }
        return this;
    }

    public WeixinPreferences setBundle(Bundle bundle) {
        this.mUID = bundle.getString(KEY_OPENID);
        this.mAccessToken = bundle.getString(KEY_ACCESS_TOKEN);
        this.mRefreshToken = bundle.getString(KEY_REFRESH_TOKEN);
        Object string = bundle.getString(KEY_EXPIRES_IN);
        if (!TextUtils.isEmpty(string)) {
            this.mexpirein = (Long.valueOf(string).longValue() * 1000) + System.currentTimeMillis();
        }
        string = bundle.getString(KEY_EXPIRES_IN);
        if (!TextUtils.isEmpty(string)) {
            this.mAccessTokenTTL = (Long.valueOf(string).longValue() * 1000) + System.currentTimeMillis();
        }
        string = bundle.getString(KEY_REFRESH_TOKEN_TTL);
        if (!TextUtils.isEmpty(string)) {
            this.mRefreshTokenTTL = (Long.valueOf(string).longValue() * 1000) + System.currentTimeMillis();
        }
        commit();
        return this;
    }

    public String getUID() {
        return this.mUID;
    }

    public String getRefreshToken() {
        return this.mRefreshToken;
    }

    public Map<String, String> getmap() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put(KEY_ACCESS_TOKEN, this.mAccessToken);
        hashMap.put(KEY_OPENID, this.mUID);
        hashMap.put(KEY_REFRESH_TOKEN, this.mRefreshToken);
        return hashMap;
    }

    public boolean isAccessTokenAvailable() {
        boolean z;
        boolean isEmpty = TextUtils.isEmpty(this.mAccessToken);
        if (this.mexpirein - System.currentTimeMillis() <= 0) {
            z = true;
        } else {
            Object obj = null;
        }
        return (isEmpty || z) ? false : true;
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }

    public boolean isAuthValid() {
        boolean z;
        boolean isEmpty = TextUtils.isEmpty(this.mRefreshToken);
        if (this.mRefreshTokenTTL - System.currentTimeMillis() <= 0) {
            z = true;
        } else {
            Object obj = null;
        }
        return (isEmpty || z) ? false : true;
    }

    public boolean isAuth() {
        return !TextUtils.isEmpty(getAccessToken());
    }

    public void delete() {
        this.sharedPreferences.edit().clear().commit();
    }

    public void commit() {
        this.sharedPreferences.edit().putString(KEY_OPENID, this.mUID).putString(KEY_ACCESS_TOKEN, this.mAccessToken).putLong(KEY_EXPIRES_IN, this.mAccessTokenTTL).putString(KEY_REFRESH_TOKEN, this.mRefreshToken).putLong(KEY_REFRESH_TOKEN_TTL, this.mRefreshTokenTTL).putLong(KEY_EXPIRES_IN, this.mexpirein).commit();
    }
}
