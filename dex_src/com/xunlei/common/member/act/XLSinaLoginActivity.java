package com.xunlei.common.member.act;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.sina.weibo.sdk.exception.WeiboException;
import com.tencent.connect.common.Constants;
import com.umeng.a;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.a.m;
import com.xunlei.common.member.c.p;

public class XLSinaLoginActivity extends Activity {
    public static final String REDIRECT_URL = "http://www.xunlei.com";
    public static final String SCOPE;
    private Oauth2AccessToken mAccessToken;
    private AuthInfo mAuthInfo;
    protected Handler mHandler;
    private String mSinaAppId;
    private String mSinaAppRedirectUrl;
    private SsoHandler mSsoHandler;
    private int mTaskId;

    public class AuthListener implements WeiboAuthListener {
        private XLSinaLoginActivity mLoginHost;

        public AuthListener(XLSinaLoginActivity xLSinaLoginActivity) {
            this.mLoginHost = null;
            this.mLoginHost = xLSinaLoginActivity;
        }

        public void onComplete(Bundle bundle) {
            XLLog.v("XLSinaLoginActivity", new StringBuilder("recall sina sdk to auth value = ").append(bundle.toString()).toString());
            XLSinaLoginActivity.this.mAccessToken = Oauth2AccessToken.parseAccessToken(bundle);
            if (XLSinaLoginActivity.this.mAccessToken.isSessionValid()) {
                XLLog.v("XLSinaLoginActivity", "recall sina sdk valid.");
                this.mLoginHost.acceptSianOauthToken(0, bundle.getString(ParamKey.UID), bundle.getString(Constants.PARAM_ACCESS_TOKEN), bundle.getString(Oauth2AccessToken.KEY_REFRESH_TOKEN), bundle.getString(Constants.PARAM_EXPIRES_IN));
                return;
            }
            XLLog.v("XLSinaLoginActivity", "recall sina sdk invalid.");
            this.mLoginHost.acceptSianOauthToken(XLErrorCode.AUTH_USER_ERROR, null, null, null, null);
        }

        public void onCancel() {
            this.mLoginHost.acceptSianOauthToken(XLErrorCode.AUTH_USER_CANCLE, null, null, null, null);
        }

        public void onWeiboException(WeiboException weiboException) {
            String message = weiboException.getMessage();
            XLLog.v("XLSinaLoginActivity", new StringBuilder("recall sina sdk error = ").append(message).toString());
            if (message.indexOf("not install") != -1) {
                XLSinaLoginActivity.this.mHandler.postDelayed(new AnonymousClass_1(this), 0);
            } else {
                this.mLoginHost.acceptSianOauthToken(XLErrorCode.AUTH_USER_ERROR, null, null, null, null);
            }
        }
    }

    static {
        SCOPE = null;
    }

    public XLSinaLoginActivity() {
        this.mHandler = new Handler();
        this.mTaskId = 0;
        this.mSinaAppId = a.d;
        this.mSinaAppRedirectUrl = REDIRECT_URL;
        this.mAuthInfo = null;
        this.mAccessToken = null;
        this.mSsoHandler = null;
    }

    public void acceptSianOauthToken(int i, String str, String str2, String str3, String str4) {
        p a = m.a().a(this.mTaskId);
        if (a != null) {
            a.a(i, str, str2, str3, str4);
        }
        finish();
        XLLog.v("XLSinaLoginActivity", "finish XLSinaLoginActivity");
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mTaskId = getIntent().getIntExtra("sina_task", 0);
        this.mSinaAppId = getIntent().getStringExtra("sina_app_id");
        Object stringExtra = getIntent().getStringExtra("sina_app_redirect");
        if (!TextUtils.isEmpty(stringExtra)) {
            this.mSinaAppRedirectUrl = stringExtra;
        }
        this.mAuthInfo = new AuthInfo(this, this.mSinaAppId, this.mSinaAppRedirectUrl, SCOPE);
        this.mSsoHandler = new SsoHandler(this, this.mAuthInfo);
        XLLog.v("XLSinaLoginActivity", "call sina sdk to auth.");
        this.mSsoHandler.authorizeClientSso(new AuthListener(this));
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.mSsoHandler != null) {
            this.mSsoHandler.authorizeCallBack(i, i2, intent);
        }
    }
}
