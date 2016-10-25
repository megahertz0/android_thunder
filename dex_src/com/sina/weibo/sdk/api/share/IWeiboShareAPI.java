package com.sina.weibo.sdk.api.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.sina.weibo.sdk.api.share.IWeiboHandler.Request;
import com.sina.weibo.sdk.api.share.IWeiboHandler.Response;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.WeiboAuthListener;

public interface IWeiboShareAPI {
    int getWeiboAppSupportAPI();

    boolean handleWeiboRequest(Intent intent, Request request);

    boolean handleWeiboResponse(Intent intent, Response response);

    boolean isSupportWeiboPay();

    boolean isWeiboAppInstalled();

    boolean isWeiboAppSupportAPI();

    boolean launchWeibo(Activity activity);

    boolean launchWeiboPay(Activity activity, String str);

    boolean launchWeiboPayLogin(Activity activity, String str);

    boolean registerApp();

    boolean sendRequest(Activity activity, BaseRequest baseRequest);

    boolean sendRequest(Activity activity, BaseRequest baseRequest, AuthInfo authInfo, String str, WeiboAuthListener weiboAuthListener);

    boolean sendResponse(BaseResponse baseResponse);

    void shareMessageToWeiyou(Context context, Bundle bundle);
}
