package com.sina.weibo.sdk.api;

import android.os.Bundle;
import com.sina.weibo.sdk.constant.WBConstants.Msg;
import com.sina.weibo.sdk.utils.LogUtil;

public final class WeiboMessage {
    public BaseMediaObject mediaObject;

    public WeiboMessage(Bundle bundle) {
        toBundle(bundle);
    }

    public final Bundle toBundle(Bundle bundle) {
        if (this.mediaObject != null) {
            bundle.putParcelable(Msg.MEDIA, this.mediaObject);
            bundle.putString(Msg.MEDIA_EXTRA, this.mediaObject.toExtraMediaString());
        }
        return bundle;
    }

    public final WeiboMessage toObject(Bundle bundle) {
        this.mediaObject = (BaseMediaObject) bundle.getParcelable(Msg.MEDIA);
        if (this.mediaObject != null) {
            this.mediaObject.toExtraMediaObject(bundle.getString(Msg.MEDIA_EXTRA));
        }
        return this;
    }

    public final boolean checkArgs() {
        if (this.mediaObject == null) {
            LogUtil.e("Weibo.WeiboMessage", "checkArgs fail, mediaObject is null");
            return false;
        } else if (this.mediaObject == null || this.mediaObject.checkArgs()) {
            return true;
        } else {
            LogUtil.e("Weibo.WeiboMessage", "checkArgs fail, mediaObject is invalid");
            return false;
        }
    }
}
