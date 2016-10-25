package com.sina.weibo.sdk.api.share;

import android.content.Context;
import android.os.Bundle;
import com.sina.weibo.sdk.WeiboAppManager.WeiboInfo;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class ProvideMessageForWeiboRequest extends BaseRequest {
    public ProvideMessageForWeiboRequest(Bundle bundle) {
        fromBundle(bundle);
    }

    public int getType() {
        return XZBDevice.DOWNLOAD_LIST_RECYCLE;
    }

    final boolean check(Context context, WeiboInfo weiboInfo, VersionCheckHandler versionCheckHandler) {
        return true;
    }
}
