package com.sina.weibo.sdk.api.share;

import android.content.Context;
import android.os.Bundle;
import com.sina.weibo.sdk.api.WeiboMessage;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class ProvideMessageForWeiboResponse extends BaseResponse {
    public WeiboMessage message;

    public ProvideMessageForWeiboResponse(Bundle bundle) {
        fromBundle(bundle);
    }

    public int getType() {
        return XZBDevice.DOWNLOAD_LIST_RECYCLE;
    }

    public void fromBundle(Bundle bundle) {
        super.fromBundle(bundle);
        this.message = new WeiboMessage(bundle);
    }

    public void toBundle(Bundle bundle) {
        super.toBundle(bundle);
        bundle.putAll(this.message.toBundle(bundle));
    }

    final boolean check(Context context, VersionCheckHandler versionCheckHandler) {
        if (this.message == null) {
            return false;
        }
        return (versionCheckHandler == null || versionCheckHandler.checkResponse(context, this.reqPackageName, this.message)) ? this.message.checkArgs() : false;
    }
}
