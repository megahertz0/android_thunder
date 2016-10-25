package com.sina.weibo.sdk.api.share;

import android.content.Context;

public class WeiboShareSDK {
    public static IWeiboShareAPI createWeiboAPI(Context context, String str, boolean z) {
        return new WeiboShareAPIImpl(context, str, false);
    }

    public static IWeiboShareAPI createWeiboAPI(Context context, String str) {
        return createWeiboAPI(context, str, false);
    }
}
