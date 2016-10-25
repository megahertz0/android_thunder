package com.umeng.socialize.media;

import com.xunlei.xiazaibao.BuildConfig;
import java.util.Map;

public class UMWebPage extends BaseMediaObject {
    private String i;
    private UMImage j;

    public UMWebPage(String str) {
        super(str);
        this.i = BuildConfig.VERSION_NAME;
        this.j = null;
        this.d = str;
    }

    public void setTargetUrl(String str) {
        super.setTargetUrl(str);
        this.a = str;
    }

    public String getDescription() {
        return this.i;
    }

    public void setDescription(String str) {
        this.i = str;
    }

    public void setThumb(UMImage uMImage) {
        this.j = uMImage;
    }

    public UMImage getThumbImage() {
        return this.j;
    }

    public UMediaObject$MediaType getMediaType() {
        return UMediaObject$MediaType.WEBPAGE;
    }

    public Map<String, Object> toUrlExtraParams() {
        return null;
    }

    public byte[] toByte() {
        return null;
    }

    public boolean isMultiMedia() {
        return true;
    }

    public String toString() {
        return new StringBuilder("UMWebPage [mDescription=").append(this.i).append(", mMediaTitle=").append(this.b).append(", mMediaThumb=").append(this.c).append(", mMediaTargetUrl=").append(this.d).append(", mLength=").append(this.g).append("]").toString();
    }
}
