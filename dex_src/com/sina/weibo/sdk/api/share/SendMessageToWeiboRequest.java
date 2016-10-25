package com.sina.weibo.sdk.api.share;

import android.content.Context;
import android.os.Bundle;
import com.sina.weibo.sdk.WeiboAppManager.WeiboInfo;
import com.sina.weibo.sdk.api.WeiboMessage;

public class SendMessageToWeiboRequest extends BaseRequest {
    public WeiboMessage message;

    public SendMessageToWeiboRequest(Bundle bundle) {
        fromBundle(bundle);
    }

    public int getType() {
        return 1;
    }

    public void fromBundle(Bundle bundle) {
        super.fromBundle(bundle);
        this.message = new WeiboMessage(bundle);
    }

    public void toBundle(Bundle bundle) {
        super.toBundle(bundle);
        bundle.putAll(this.message.toBundle(bundle));
    }

    final boolean check(Context context, WeiboInfo weiboInfo, VersionCheckHandler versionCheckHandler) {
        if (this.message == null || weiboInfo == null || !weiboInfo.isLegal()) {
            return false;
        }
        return (versionCheckHandler == null || versionCheckHandler.checkRequest(context, weiboInfo, this.message)) ? this.message.checkArgs() : false;
    }
}
