package com.sina.weibo.sdk.component;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.text.TextUtils;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.constant.WBConstants;
import com.tencent.connect.common.Constants;
import com.tencent.open.GameAppOperation;
import com.tencent.open.SocialConstants;

public class GameRequestParam extends BrowserRequestParamBase {
    private String mAppKey;
    private WeiboAuthListener mAuthListener;
    private String mAuthListenerKey;
    private String mToken;

    public static interface WidgetRequestCallback {
        void onWebViewResult(String str);
    }

    public GameRequestParam(Context context) {
        super(context);
        this.mLaucher = BrowserLauncher.WIDGET;
    }

    protected void onSetupRequestParam(Bundle bundle) {
        this.mAppKey = bundle.getString(SocialConstants.PARAM_SOURCE);
        this.mToken = bundle.getString(Constants.PARAM_ACCESS_TOKEN);
        this.mAuthListenerKey = bundle.getString(AuthRequestParam.EXTRA_KEY_LISTENER);
        if (!TextUtils.isEmpty(this.mAuthListenerKey)) {
            this.mAuthListener = WeiboCallbackManager.getInstance(this.mContext).getWeiboAuthListener(this.mAuthListenerKey);
        }
        this.mUrl = buildUrl(this.mUrl);
    }

    public void onCreateRequestParamBundle(Bundle bundle) {
        bundle.putString(Constants.PARAM_ACCESS_TOKEN, this.mToken);
        bundle.putString(SocialConstants.PARAM_SOURCE, this.mAppKey);
        WeiboCallbackManager instance = WeiboCallbackManager.getInstance(this.mContext);
        if (this.mAuthListener != null) {
            this.mAuthListenerKey = instance.genCallbackKey();
            instance.setWeiboAuthListener(this.mAuthListenerKey, this.mAuthListener);
            bundle.putString(AuthRequestParam.EXTRA_KEY_LISTENER, this.mAuthListenerKey);
        }
    }

    private String buildUrl(String str) {
        Builder buildUpon = Uri.parse(str).buildUpon();
        buildUpon.appendQueryParameter(GameAppOperation.QQFAV_DATALINE_VERSION, WBConstants.WEIBO_SDK_VERSION_CODE);
        if (!TextUtils.isEmpty(this.mAppKey)) {
            buildUpon.appendQueryParameter(SocialConstants.PARAM_SOURCE, this.mAppKey);
        }
        if (!TextUtils.isEmpty(this.mToken)) {
            buildUpon.appendQueryParameter(Constants.PARAM_ACCESS_TOKEN, this.mToken);
        }
        return buildUpon.build().toString();
    }

    public String getToken() {
        return this.mToken;
    }

    public void setToken(String str) {
        this.mToken = str;
    }

    public String getAppKey() {
        return this.mAppKey;
    }

    public void setAppKey(String str) {
        this.mAppKey = str;
    }

    public WeiboAuthListener getAuthListener() {
        return this.mAuthListener;
    }

    public String getAuthListenerKey() {
        return this.mAuthListenerKey;
    }

    public void setAuthListener(WeiboAuthListener weiboAuthListener) {
        this.mAuthListener = weiboAuthListener;
    }

    public void execRequest(Activity activity, int i) {
    }
}
