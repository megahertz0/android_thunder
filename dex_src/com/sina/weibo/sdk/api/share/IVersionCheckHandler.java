package com.sina.weibo.sdk.api.share;

import android.content.Context;
import com.sina.weibo.sdk.WeiboAppManager.WeiboInfo;
import com.sina.weibo.sdk.api.WeiboMessage;
import com.sina.weibo.sdk.api.WeiboMultiMessage;

interface IVersionCheckHandler {
    boolean checkRequest(Context context, WeiboInfo weiboInfo, WeiboMessage weiboMessage);

    boolean checkRequest(Context context, WeiboInfo weiboInfo, WeiboMultiMessage weiboMultiMessage);

    boolean checkResponse(Context context, String str, WeiboMessage weiboMessage);

    boolean checkResponse(Context context, String str, WeiboMultiMessage weiboMultiMessage);
}
