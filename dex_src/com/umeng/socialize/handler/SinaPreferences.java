package com.umeng.socialize.handler;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import com.umeng.socialize.utils.Log;
import java.util.HashMap;
import java.util.Map;

public class SinaPreferences {
    private static final String FOLLOW = "isfollow";
    private static final String KEY_ACCESS_KEY = "access_key";
    private static final String KEY_ACCESS_SECRET = "access_secret";
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String KEY_REFRESH_TOKEN = "refresh_token";
    private static final String KEY_SSO_TTL = "expires_in";
    private static final String KEY_SSO_UID = "uid";
    private static final String KEY_TTL = "expires_in";
    private static final String KEY_UID = "uid";
    private static final String KEY_USER_NAME = "userName";
    private boolean isfollow;
    private String mAccessKey;
    private String mAccessSecret;
    private String mAccessToken;
    private String mRefreshToken;
    private long mTTL;
    private String mUID;
    private String mUserName;
    private SharedPreferences sharedPreferences;

    public SinaPreferences(Context context, String str) {
        this.mAccessKey = null;
        this.mAccessSecret = null;
        this.mUID = null;
        this.mTTL = 0;
        this.mAccessToken = null;
        this.mRefreshToken = null;
        this.mUserName = null;
        this.isfollow = false;
        this.sharedPreferences = null;
        this.sharedPreferences = context.getSharedPreferences(str, 0);
        this.mAccessKey = this.sharedPreferences.getString(KEY_ACCESS_KEY, null);
        this.mRefreshToken = this.sharedPreferences.getString(KEY_REFRESH_TOKEN, null);
        this.mAccessSecret = this.sharedPreferences.getString(KEY_ACCESS_SECRET, null);
        this.mAccessToken = this.sharedPreferences.getString(KEY_ACCESS_TOKEN, null);
        this.mUID = this.sharedPreferences.getString(KEY_UID, null);
        this.mTTL = this.sharedPreferences.getLong(KEY_TTL, 0);
        this.isfollow = this.sharedPreferences.getBoolean(FOLLOW, false);
    }

    public SinaPreferences setAuthData(Map<String, String> map) {
        this.mAccessKey = (String) map.get(KEY_ACCESS_KEY);
        this.mAccessSecret = (String) map.get(KEY_ACCESS_SECRET);
        this.mAccessToken = (String) map.get(KEY_ACCESS_TOKEN);
        this.mRefreshToken = (String) map.get(KEY_REFRESH_TOKEN);
        this.mUID = (String) map.get(KEY_UID);
        if (!TextUtils.isEmpty((CharSequence) map.get(KEY_TTL))) {
            this.mTTL = (Long.valueOf((String) map.get(KEY_TTL)).longValue() * 1000) + System.currentTimeMillis();
        }
        return this;
    }

    public String getmAccessToken() {
        return this.mAccessToken;
    }

    public String getmRefreshToken() {
        return this.mRefreshToken;
    }

    public SinaPreferences setAuthData(Bundle bundle) {
        this.mAccessToken = bundle.getString(KEY_ACCESS_TOKEN);
        this.mRefreshToken = bundle.getString(KEY_REFRESH_TOKEN);
        this.mUID = bundle.getString(KEY_UID);
        Log.v(new StringBuilder("xxxx authend = ").append(this.mAccessToken).toString());
        if (!TextUtils.isEmpty(bundle.getString(KEY_TTL))) {
            this.mTTL = (Long.valueOf(bundle.getString(KEY_TTL)).longValue() * 1000) + System.currentTimeMillis();
        }
        return this;
    }

    public Map<String, String> getAuthData() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put(KEY_ACCESS_KEY, this.mAccessKey);
        hashMap.put(KEY_ACCESS_SECRET, this.mAccessSecret);
        hashMap.put(KEY_UID, this.mUID);
        hashMap.put(KEY_TTL, String.valueOf(this.mTTL));
        return hashMap;
    }

    public String getUID() {
        return this.mUID;
    }

    public boolean isAuthorized() {
        boolean z;
        StringBuilder append = new StringBuilder("xxxx auth = ").append(this.mAccessToken).append("   ");
        if (TextUtils.isEmpty(this.mAccessToken)) {
            z = false;
        } else {
            z = true;
        }
        Log.v(append.append(z).toString());
        return !TextUtils.isEmpty(this.mAccessToken);
    }

    public boolean isAuthValid() {
        boolean z;
        boolean isAuthorized = isAuthorized();
        if (this.mTTL - System.currentTimeMillis() <= 0) {
            z = true;
        } else {
            Object obj = null;
        }
        return isAuthorized && !z;
    }

    public boolean Isfollow() {
        return this.isfollow;
    }

    public void setIsfollow(boolean z) {
        this.sharedPreferences.edit().putBoolean(FOLLOW, z).commit();
    }

    public void commit() {
        this.sharedPreferences.edit().putString(KEY_ACCESS_KEY, this.mAccessKey).putString(KEY_ACCESS_SECRET, this.mAccessSecret).putString(KEY_ACCESS_TOKEN, this.mAccessToken).putString(KEY_REFRESH_TOKEN, this.mRefreshToken).putString(KEY_UID, this.mUID).putLong(KEY_TTL, this.mTTL).commit();
        Log.i("save auth succeed");
    }

    public void delete() {
        this.mAccessKey = null;
        this.mAccessSecret = null;
        this.mAccessToken = null;
        this.mUID = null;
        this.mTTL = 0;
        this.sharedPreferences.edit().clear().commit();
        Log.v(new StringBuilder("xxxx dele = ").append(this.mAccessToken).toString());
    }
}
