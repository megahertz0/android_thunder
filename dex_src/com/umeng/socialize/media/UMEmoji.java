package com.umeng.socialize.media;

import android.content.Context;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class UMEmoji extends BaseMediaObject {
    public UMImage mSrcImage;
    public UMImage mThumb;

    public UMEmoji(Context context, String str) {
        super(str);
        this.mSrcImage = new UMImage(context, str);
    }

    public UMEmoji(Context context, File file) {
        this.mSrcImage = new UMImage(context, file);
    }

    public UMediaObject$MediaType getMediaType() {
        return UMediaObject$MediaType.IMAGE;
    }

    public final Map<String, Object> toUrlExtraParams() {
        Map<String, Object> hashMap = new HashMap();
        if (isUrlMedia()) {
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_FURL, this.a);
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_FTYPE, getMediaType());
        }
        return hashMap;
    }

    public void setThumb(UMImage uMImage) {
        this.mThumb = uMImage;
    }

    public byte[] toByte() {
        return this.mThumb != null ? this.mSrcImage.toByte() : null;
    }

    public String toString() {
        return new StringBuilder("UMEmoji [").append(this.mThumb.toString()).append("]").toString();
    }

    public boolean isMultiMedia() {
        return true;
    }

    public UMImage getThumbImage() {
        return this.mThumb;
    }
}
