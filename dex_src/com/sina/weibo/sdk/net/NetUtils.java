package com.sina.weibo.sdk.net;

import android.content.Context;
import com.sina.weibo.sdk.exception.WeiboException;

public class NetUtils {
    public static String internalGetRedirectUri(Context context, String str, String str2, WeiboParameters weiboParameters) {
        return HttpManager.openRedirectUrl4LocationUri(context, str, str2, weiboParameters);
    }

    public static String internalDownloadFile(Context context, String str, String str2, String str3) throws WeiboException {
        return HttpManager.downloadFile(context, str, str2, str3);
    }

    public static String internalHttpRequest(Context context, String str, String str2, WeiboParameters weiboParameters) {
        return HttpManager.openUrl(context, str, str2, weiboParameters);
    }

    public static void internalHttpRequest(Context context, String str, WeiboParameters weiboParameters, String str2, RequestListener requestListener) {
        new RequestRunner(context, str, weiboParameters, str2, requestListener).execute(new Void[1]);
    }
}
