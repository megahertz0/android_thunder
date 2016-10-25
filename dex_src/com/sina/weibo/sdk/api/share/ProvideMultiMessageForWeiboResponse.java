package com.sina.weibo.sdk.api.share;

import android.content.Context;
import android.os.Bundle;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class ProvideMultiMessageForWeiboResponse extends BaseResponse {
    public WeiboMultiMessage multiMessage;

    public ProvideMultiMessageForWeiboResponse(Bundle bundle) {
        fromBundle(bundle);
    }

    public int getType() {
        return XZBDevice.DOWNLOAD_LIST_RECYCLE;
    }

    public void fromBundle(Bundle bundle) {
        super.fromBundle(bundle);
        this.multiMessage = new WeiboMultiMessage(bundle);
    }

    public void toBundle(Bundle bundle) {
        super.toBundle(bundle);
        bundle.putAll(this.multiMessage.toBundle(bundle));
    }

    final boolean check(Context context, VersionCheckHandler versionCheckHandler) {
        if (this.multiMessage == null) {
            return false;
        }
        return (versionCheckHandler == null || versionCheckHandler.checkResponse(context, this.reqPackageName, this.multiMessage)) ? this.multiMessage.checkArgs() : false;
    }
}
