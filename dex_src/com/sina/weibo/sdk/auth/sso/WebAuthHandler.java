package com.sina.weibo.sdk.auth.sso;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.component.AuthRequestParam;
import com.sina.weibo.sdk.component.WeiboSdkBrowser;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.utils.NetworkHelper;
import com.sina.weibo.sdk.utils.UIUtils;
import com.sina.weibo.sdk.utils.Utility;
import com.tencent.connect.common.Constants;
import com.tencent.open.GameAppOperation;
import com.tencent.stat.DeviceInfo;

class WebAuthHandler {
    private static final String NETWORK_NOT_AVAILABLE_EN = "Network is not available";
    private static final String NETWORK_NOT_AVAILABLE_ZH_CN = "\u65e0\u6cd5\u8fde\u63a5\u5230\u7f51\u7edc\uff0c\u8bf7\u68c0\u67e5\u7f51\u7edc\u914d\u7f6e";
    private static final String NETWORK_NOT_AVAILABLE_ZH_TW = "\u7121\u6cd5\u9023\u63a5\u5230\u7db2\u7edc\uff0c\u8acb\u6aa2\u67e5\u7db2\u7edc\u914d\u7f6e";
    private static final String OAUTH2_BASE_URL = "https://open.weibo.cn/oauth2/authorize?";
    private static final int OBTAIN_AUTH_CODE = 0;
    private static final int OBTAIN_AUTH_TOKEN = 1;
    private static final String TAG;
    private AuthInfo mAuthInfo;
    private Context mContext;

    static {
        TAG = WebAuthHandler.class.getName();
    }

    public WebAuthHandler(Context context, AuthInfo authInfo) {
        this.mContext = context;
        this.mAuthInfo = authInfo;
    }

    public AuthInfo getAuthInfo() {
        return this.mAuthInfo;
    }

    public void anthorize(WeiboAuthListener weiboAuthListener) {
        authorize(weiboAuthListener, OBTAIN_AUTH_TOKEN);
    }

    public void authorize(WeiboAuthListener weiboAuthListener, int i) {
        startDialog(weiboAuthListener, i);
    }

    private void startDialog(WeiboAuthListener weiboAuthListener, int i) {
        if (weiboAuthListener != null) {
            WeiboParameters weiboParameters = new WeiboParameters(this.mAuthInfo.getAppKey());
            weiboParameters.put(Constants.PARAM_CLIENT_ID, this.mAuthInfo.getAppKey());
            weiboParameters.put(WBConstants.AUTH_PARAMS_REDIRECT_URL, this.mAuthInfo.getRedirectUrl());
            weiboParameters.put(Constants.PARAM_SCOPE, this.mAuthInfo.getScope());
            weiboParameters.put(WBConstants.AUTH_PARAMS_RESPONSE_TYPE, com.taobao.accs.common.Constants.KEY_HTTP_CODE);
            weiboParameters.put(GameAppOperation.QQFAV_DATALINE_VERSION, WBConstants.WEIBO_SDK_VERSION_CODE);
            String aid = Utility.getAid(this.mContext, this.mAuthInfo.getAppKey());
            if (!TextUtils.isEmpty(aid)) {
                weiboParameters.put(DeviceInfo.TAG_ANDROID_ID, aid);
            }
            if (1 == i) {
                weiboParameters.put(LogBuilder.KEY_PACKAGE_NAME, this.mAuthInfo.getPackageName());
                weiboParameters.put(LogBuilder.KEY_HASH, this.mAuthInfo.getKeyHash());
            }
            String toString = new StringBuilder(OAUTH2_BASE_URL).append(weiboParameters.encodeUrl()).toString();
            if (NetworkHelper.hasInternetPermission(this.mContext)) {
                AuthRequestParam authRequestParam = new AuthRequestParam(this.mContext);
                authRequestParam.setAuthInfo(this.mAuthInfo);
                authRequestParam.setAuthListener(weiboAuthListener);
                authRequestParam.setUrl(toString);
                authRequestParam.setSpecifyTitle("\u5fae\u535a\u767b\u5f55");
                Bundle createRequestParamBundle = authRequestParam.createRequestParamBundle();
                Intent intent = new Intent(this.mContext, WeiboSdkBrowser.class);
                intent.putExtras(createRequestParamBundle);
                this.mContext.startActivity(intent);
                return;
            }
            UIUtils.showAlert(this.mContext, "Error", "Application requires permission to access the Internet");
        }
    }
}
