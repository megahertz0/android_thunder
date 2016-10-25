package com.umeng.socialize.handler;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.umeng.socialize.utils.Log;

public class QQPreferences {
    private static final String ACCESS_TOKEN = "access_token";
    private static final String EXPIRES_IN = "expires_in";
    private static final String OPENID = "uid";
    private static String mtl;
    private String mAccessToken;
    private String mUID;
    private SharedPreferences sharedPreferences;

    static {
        mtl = null;
    }

    public QQPreferences(Context context, String str) {
        this.mAccessToken = null;
        this.mUID = null;
        this.sharedPreferences = null;
        this.sharedPreferences = context.getSharedPreferences(str, 0);
        this.mAccessToken = this.sharedPreferences.getString(ACCESS_TOKEN, null);
        this.mUID = this.sharedPreferences.getString(OPENID, null);
        mtl = this.sharedPreferences.getString(EXPIRES_IN, null);
    }

    public String getmAccessToken() {
        return this.mAccessToken;
    }

    public static String getExpiresIn() {
        return mtl;
    }

    public String getmUID() {
        return this.mUID;
    }

    public QQPreferences setAuthData(Bundle bundle) {
        this.mAccessToken = bundle.getString(ACCESS_TOKEN);
        mtl = bundle.getString(EXPIRES_IN);
        this.mUID = bundle.getString(OPENID);
        return this;
    }

    public String getuid() {
        return this.mUID;
    }

    public boolean isAuthValid() {
        return this.mAccessToken != null;
    }

    public void commit() {
        this.sharedPreferences.edit().putString(ACCESS_TOKEN, this.mAccessToken).putString(EXPIRES_IN, mtl).putString(OPENID, this.mUID).commit();
        Log.i("save auth succeed");
    }

    public void delete() {
        this.sharedPreferences.edit().clear().commit();
    }
}
