package com.sina.weibo.sdk.auth;

import android.content.Context;
import android.os.Bundle;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.utils.Utility;
import com.taobao.accs.common.Constants;
import com.umeng.a;

public class AuthInfo {
    private String mAppKey;
    private String mKeyHash;
    private String mPackageName;
    private String mRedirectUrl;
    private String mScope;

    public AuthInfo(Context context, String str, String str2, String str3) {
        this.mAppKey = a.d;
        this.mRedirectUrl = a.d;
        this.mScope = a.d;
        this.mPackageName = a.d;
        this.mKeyHash = a.d;
        this.mAppKey = str;
        this.mRedirectUrl = str2;
        this.mScope = str3;
        this.mPackageName = context.getPackageName();
        this.mKeyHash = Utility.getSign(context, this.mPackageName);
    }

    public String getAppKey() {
        return this.mAppKey;
    }

    public String getRedirectUrl() {
        return this.mRedirectUrl;
    }

    public String getScope() {
        return this.mScope;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public String getKeyHash() {
        return this.mKeyHash;
    }

    public Bundle getAuthBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_APP_KEY, this.mAppKey);
        bundle.putString(WBConstants.SSO_REDIRECT_URL, this.mRedirectUrl);
        bundle.putString(com.tencent.connect.common.Constants.PARAM_SCOPE, this.mScope);
        bundle.putString(LogBuilder.KEY_PACKAGE_NAME, this.mPackageName);
        bundle.putString(LogBuilder.KEY_HASH, this.mKeyHash);
        return bundle;
    }

    public static AuthInfo parseBundleData(Context context, Bundle bundle) {
        return new AuthInfo(context, bundle.getString(Constants.KEY_APP_KEY), bundle.getString(WBConstants.SSO_REDIRECT_URL), bundle.getString(com.tencent.connect.common.Constants.PARAM_SCOPE));
    }
}
