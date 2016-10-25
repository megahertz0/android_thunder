package com.sina.weibo.sdk.api.share;

import android.content.Context;
import android.text.TextUtils;
import com.sina.weibo.sdk.WeiboAppManager;
import com.sina.weibo.sdk.WeiboAppManager.WeiboInfo;
import com.sina.weibo.sdk.api.CmdObject;
import com.sina.weibo.sdk.api.VoiceObject;
import com.sina.weibo.sdk.api.WeiboMessage;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.utils.LogUtil;

public class VersionCheckHandler implements IVersionCheckHandler {
    private static final String TAG;

    static {
        TAG = VersionCheckHandler.class.getName();
    }

    public boolean checkRequest(Context context, WeiboInfo weiboInfo, WeiboMessage weiboMessage) {
        if (weiboInfo == null || !weiboInfo.isLegal()) {
            return false;
        }
        LogUtil.d(TAG, new StringBuilder("WeiboMessage WeiboInfo package : ").append(weiboInfo.getPackageName()).toString());
        LogUtil.d(TAG, new StringBuilder("WeiboMessage WeiboInfo supportApi : ").append(weiboInfo.getSupportApi()).toString());
        if (weiboInfo.getSupportApi() < 10351 && weiboMessage.mediaObject != null && (weiboMessage.mediaObject instanceof VoiceObject)) {
            weiboMessage.mediaObject = null;
        }
        if (weiboInfo.getSupportApi() < 10352 && weiboMessage.mediaObject != null && (weiboMessage.mediaObject instanceof CmdObject)) {
            weiboMessage.mediaObject = null;
        }
        return true;
    }

    public boolean checkRequest(Context context, WeiboInfo weiboInfo, WeiboMultiMessage weiboMultiMessage) {
        if (weiboInfo == null || !weiboInfo.isLegal()) {
            return false;
        }
        LogUtil.d(TAG, new StringBuilder("WeiboMultiMessage WeiboInfo package : ").append(weiboInfo.getPackageName()).toString());
        LogUtil.d(TAG, new StringBuilder("WeiboMultiMessage WeiboInfo supportApi : ").append(weiboInfo.getSupportApi()).toString());
        if (weiboInfo.getSupportApi() < 10351) {
            return false;
        }
        if (weiboInfo.getSupportApi() < 10352 && weiboMultiMessage.mediaObject != null && (weiboMultiMessage.mediaObject instanceof CmdObject)) {
            weiboMultiMessage.mediaObject = null;
        }
        return true;
    }

    public boolean checkResponse(Context context, String str, WeiboMessage weiboMessage) {
        return TextUtils.isEmpty(str) ? false : checkRequest(context, WeiboAppManager.getInstance(context).parseWeiboInfoByAsset(str), weiboMessage);
    }

    public boolean checkResponse(Context context, String str, WeiboMultiMessage weiboMultiMessage) {
        return TextUtils.isEmpty(str) ? false : checkRequest(context, WeiboAppManager.getInstance(context).parseWeiboInfoByAsset(str), weiboMultiMessage);
    }
}
