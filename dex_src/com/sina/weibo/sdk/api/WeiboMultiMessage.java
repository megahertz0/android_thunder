package com.sina.weibo.sdk.api;

import android.os.Bundle;
import com.sina.weibo.sdk.constant.WBConstants.Msg;
import com.sina.weibo.sdk.utils.LogUtil;

public final class WeiboMultiMessage {
    private static final String TAG = "WeiboMultiMessage";
    public ImageObject imageObject;
    public BaseMediaObject mediaObject;
    public TextObject textObject;

    public WeiboMultiMessage(Bundle bundle) {
        toBundle(bundle);
    }

    public final Bundle toBundle(Bundle bundle) {
        if (this.textObject != null) {
            bundle.putParcelable(Msg.TEXT, this.textObject);
            bundle.putString(Msg.TEXT_EXTRA, this.textObject.toExtraMediaString());
        }
        if (this.imageObject != null) {
            bundle.putParcelable(Msg.IMAGE, this.imageObject);
            bundle.putString(Msg.IMAGE_EXTRA, this.imageObject.toExtraMediaString());
        }
        if (this.mediaObject != null) {
            bundle.putParcelable(Msg.MEDIA, this.mediaObject);
            bundle.putString(Msg.MEDIA_EXTRA, this.mediaObject.toExtraMediaString());
        }
        return bundle;
    }

    public final WeiboMultiMessage toObject(Bundle bundle) {
        this.textObject = (TextObject) bundle.getParcelable(Msg.TEXT);
        if (this.textObject != null) {
            this.textObject.toExtraMediaObject(bundle.getString(Msg.TEXT_EXTRA));
        }
        this.imageObject = (ImageObject) bundle.getParcelable(Msg.IMAGE);
        if (this.imageObject != null) {
            this.imageObject.toExtraMediaObject(bundle.getString(Msg.IMAGE_EXTRA));
        }
        this.mediaObject = (BaseMediaObject) bundle.getParcelable(Msg.MEDIA);
        if (this.mediaObject != null) {
            this.mediaObject.toExtraMediaObject(bundle.getString(Msg.MEDIA_EXTRA));
        }
        return this;
    }

    public final boolean checkArgs() {
        if (this.textObject != null && !this.textObject.checkArgs()) {
            LogUtil.e(TAG, "checkArgs fail, textObject is invalid");
            return false;
        } else if (this.imageObject != null && !this.imageObject.checkArgs()) {
            LogUtil.e(TAG, "checkArgs fail, imageObject is invalid");
            return false;
        } else if (this.mediaObject != null && !this.mediaObject.checkArgs()) {
            LogUtil.e(TAG, "checkArgs fail, mediaObject is invalid");
            return false;
        } else if (this.textObject != null || this.imageObject != null || this.mediaObject != null) {
            return true;
        } else {
            LogUtil.e(TAG, "checkArgs fail, textObject and imageObject and mediaObject is null");
            return false;
        }
    }
}
