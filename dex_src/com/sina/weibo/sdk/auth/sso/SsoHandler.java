package com.sina.weibo.sdk.auth.sso;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;
import com.sina.sso.RemoteSSO;
import com.sina.sso.RemoteSSO.Stub;
import com.sina.weibo.sdk.WeiboAppManager;
import com.sina.weibo.sdk.WeiboAppManager.WeiboInfo;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.cmd.WbAppActivator;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.exception.WeiboDialogException;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.register.mobile.MobileRegisterActivity;
import com.sina.weibo.sdk.utils.AidTask;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.SecurityHelper;
import com.sina.weibo.sdk.utils.Utility;
import com.taobao.accs.flowcontrol.FlowControl;
import com.tencent.stat.DeviceInfo;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class SsoHandler {
    public static final String AUTH_FAILED_MSG = "auth failed!!!!!";
    public static final String AUTH_FAILED_NOT_INSTALL_MSG = "not install weibo client!!!!!";
    private static final String DEFAULT_WEIBO_REMOTE_SSO_SERVICE_NAME = "com.sina.weibo.remotessoservice";
    private static final int REQUEST_CODE_MOBILE_REGISTER = 40000;
    private static final int REQUEST_CODE_SSO_AUTH = 32973;
    private static final String TAG = "Weibo_SSO_login";
    private Activity mAuthActivity;
    private AuthInfo mAuthInfo;
    private WeiboAuthListener mAuthListener;
    private ServiceConnection mConnection;
    private int mSSOAuthRequestCode;
    private WebAuthHandler mWebAuthHandler;
    private WeiboInfo mWeiboInfo;

    private enum AuthType {
        ALL,
        SsoOnly,
        WebOnly;

        static {
            ALL = new AuthType(FlowControl.SERVICE_ALL, 0);
            SsoOnly = new AuthType("SsoOnly", 1);
            WebOnly = new AuthType("WebOnly", 2);
            ENUM$VALUES = new AuthType[]{ALL, SsoOnly, WebOnly};
        }
    }

    public SsoHandler(Activity activity, AuthInfo authInfo) {
        this.mConnection = new ServiceConnection() {
            public void onServiceDisconnected(ComponentName componentName) {
                SsoHandler.this.mWebAuthHandler.anthorize(SsoHandler.this.mAuthListener);
            }

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                RemoteSSO asInterface = Stub.asInterface(iBinder);
                try {
                    String packageName = asInterface.getPackageName();
                    String activityName = asInterface.getActivityName();
                    SsoHandler.this.mAuthActivity.getApplicationContext().unbindService(SsoHandler.this.mConnection);
                    if (!SsoHandler.this.startSingleSignOn(packageName, activityName)) {
                        SsoHandler.this.mWebAuthHandler.anthorize(SsoHandler.this.mAuthListener);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        };
        this.mAuthActivity = activity;
        this.mAuthInfo = authInfo;
        this.mWebAuthHandler = new WebAuthHandler(activity, authInfo);
        this.mWeiboInfo = WeiboAppManager.getInstance(activity).getWeiboInfo();
        AidTask.getInstance(this.mAuthActivity).aidTaskInit(authInfo.getAppKey());
    }

    public void authorize(WeiboAuthListener weiboAuthListener) {
        authorize(REQUEST_CODE_SSO_AUTH, weiboAuthListener, AuthType.ALL);
        WbAppActivator.getInstance(this.mAuthActivity, this.mAuthInfo.getAppKey()).activateApp();
    }

    public void authorizeClientSso(WeiboAuthListener weiboAuthListener) {
        authorize(REQUEST_CODE_SSO_AUTH, weiboAuthListener, AuthType.SsoOnly);
        WbAppActivator.getInstance(this.mAuthActivity, this.mAuthInfo.getAppKey()).activateApp();
    }

    public void authorizeWeb(WeiboAuthListener weiboAuthListener) {
        authorize(REQUEST_CODE_SSO_AUTH, weiboAuthListener, AuthType.WebOnly);
        WbAppActivator.getInstance(this.mAuthActivity, this.mAuthInfo.getAppKey()).activateApp();
    }

    private void authorize(int i, WeiboAuthListener weiboAuthListener, AuthType authType) {
        this.mSSOAuthRequestCode = i;
        this.mAuthListener = weiboAuthListener;
        Object obj = null;
        if (authType == AuthType.SsoOnly) {
            obj = 1;
        }
        if (authType == AuthType.WebOnly) {
            if (weiboAuthListener != null) {
                this.mWebAuthHandler.anthorize(weiboAuthListener);
            }
        } else if (!bindRemoteSSOService(this.mAuthActivity.getApplicationContext())) {
            if (obj == null) {
                this.mWebAuthHandler.anthorize(this.mAuthListener);
            } else if (this.mAuthListener != null) {
                this.mAuthListener.onWeiboException(new WeiboException(AUTH_FAILED_NOT_INSTALL_MSG));
            }
        }
    }

    public void authorizeCallBack(int i, int i2, Intent intent) {
        LogUtil.d(TAG, new StringBuilder("requestCode: ").append(i).append(", resultCode: ").append(i2).append(", data: ").append(intent).toString());
        String stringExtra;
        Bundle extras;
        Oauth2AccessToken parseAccessToken;
        if (i == this.mSSOAuthRequestCode) {
            if (i2 == -1) {
                if (SecurityHelper.checkResponseAppLegal(this.mAuthActivity, this.mWeiboInfo, intent)) {
                    stringExtra = intent.getStringExtra("error");
                    if (stringExtra == null) {
                        stringExtra = intent.getStringExtra("error_type");
                    }
                    if (stringExtra == null) {
                        extras = intent.getExtras();
                        parseAccessToken = Oauth2AccessToken.parseAccessToken(extras);
                        if (parseAccessToken == null || !parseAccessToken.isSessionValid()) {
                            LogUtil.d(TAG, "Failed to receive access token by SSO");
                            this.mWebAuthHandler.anthorize(this.mAuthListener);
                            return;
                        }
                        LogUtil.d(TAG, new StringBuilder("Login Success! ").append(parseAccessToken.toString()).toString());
                        this.mAuthListener.onComplete(extras);
                    } else if (stringExtra.equals("access_denied") || stringExtra.equals("OAuthAccessDeniedException")) {
                        LogUtil.d(TAG, "Login canceled by user.");
                        this.mAuthListener.onCancel();
                    } else {
                        String stringExtra2 = intent.getStringExtra("error_description");
                        if (stringExtra2 != null) {
                            stringExtra = new StringBuilder(String.valueOf(stringExtra)).append(":").append(stringExtra2).toString();
                        }
                        LogUtil.d(TAG, new StringBuilder("Login failed: ").append(stringExtra).toString());
                        this.mAuthListener.onWeiboException(new WeiboDialogException(stringExtra, i2, stringExtra2));
                    }
                }
            } else if (i2 != 0) {
            } else {
                if (intent != null) {
                    LogUtil.d(TAG, new StringBuilder("Login failed: ").append(intent.getStringExtra("error")).toString());
                    this.mAuthListener.onWeiboException(new WeiboDialogException(intent.getStringExtra("error"), intent.getIntExtra("error_code", -1), intent.getStringExtra("failing_url")));
                    return;
                }
                LogUtil.d(TAG, "Login canceled by user.");
                this.mAuthListener.onCancel();
            }
        } else if (i != 40000) {
        } else {
            if (i2 == -1) {
                extras = intent.getExtras();
                parseAccessToken = Oauth2AccessToken.parseAccessToken(extras);
                if (parseAccessToken != null && parseAccessToken.isSessionValid()) {
                    LogUtil.d(TAG, new StringBuilder("Login Success! ").append(parseAccessToken.toString()).toString());
                    this.mAuthListener.onComplete(extras);
                }
            } else if (i2 != 0) {
            } else {
                if (intent != null) {
                    LogUtil.d(TAG, new StringBuilder("Login failed: ").append(intent.getStringExtra("error")).toString());
                    stringExtra = intent.getStringExtra("error");
                    if (stringExtra == null) {
                        stringExtra = intent.getStringExtra("error_type");
                    }
                    if (stringExtra != null) {
                        this.mAuthListener.onWeiboException(new WeiboDialogException(intent.getStringExtra("error"), intent.getIntExtra("error_code", -1), intent.getStringExtra("error_description")));
                        return;
                    }
                    return;
                }
                LogUtil.d(TAG, "Login canceled by user.");
                this.mAuthListener.onCancel();
            }
        }
    }

    public static ComponentName isServiceExisted(Context context, String str) {
        for (RunningServiceInfo runningServiceInfo : ((ActivityManager) context.getSystemService("activity")).getRunningServices(InMobiClientPositioning.NO_REPEAT)) {
            ComponentName componentName = runningServiceInfo.service;
            if (componentName.getPackageName().equals(str) && componentName.getClassName().equals(new StringBuilder(String.valueOf(str)).append(".business.RemoteSSOService").toString())) {
                return componentName;
            }
        }
        return null;
    }

    private boolean bindRemoteSSOService(Context context) {
        if (!isWeiboAppInstalled()) {
            return false;
        }
        String packageName = this.mWeiboInfo.getPackageName();
        Intent intent = new Intent(DEFAULT_WEIBO_REMOTE_SSO_SERVICE_NAME);
        intent.setPackage(packageName);
        return context.bindService(intent, this.mConnection, 1);
    }

    private boolean startSingleSignOn(String str, String str2) {
        Object obj = 1;
        Intent intent = new Intent();
        intent.setClassName(str, str2);
        intent.putExtras(this.mWebAuthHandler.getAuthInfo().getAuthBundle());
        intent.putExtra(WBConstants.COMMAND_TYPE_KEY, XZBDevice.DOWNLOAD_LIST_FAILED);
        intent.putExtra(WBConstants.TRAN, String.valueOf(System.currentTimeMillis()));
        intent.putExtra(DeviceInfo.TAG_ANDROID_ID, Utility.getAid(this.mAuthActivity, this.mAuthInfo.getAppKey()));
        if (!SecurityHelper.validateAppSignatureForIntent(this.mAuthActivity, intent)) {
            return false;
        }
        boolean z;
        Object aid = Utility.getAid(this.mAuthActivity, this.mAuthInfo.getAppKey());
        if (!TextUtils.isEmpty(aid)) {
            intent.putExtra(DeviceInfo.TAG_ANDROID_ID, aid);
        }
        try {
            this.mAuthActivity.startActivityForResult(intent, this.mSSOAuthRequestCode);
        } catch (ActivityNotFoundException e) {
            z = false;
        }
        return z;
    }

    public boolean isWeiboAppInstalled() {
        return this.mWeiboInfo != null && this.mWeiboInfo.isLegal();
    }

    public void registerOrLoginByMobile(String str, WeiboAuthListener weiboAuthListener) {
        this.mAuthListener = weiboAuthListener;
        Intent intent = new Intent(this.mAuthActivity, MobileRegisterActivity.class);
        Bundle authBundle = this.mAuthInfo.getAuthBundle();
        authBundle.putString(MobileRegisterActivity.REGISTER_TITLE, str);
        intent.putExtras(authBundle);
        this.mAuthActivity.startActivityForResult(intent, REQUEST_CODE_MOBILE_REGISTER);
    }
}
