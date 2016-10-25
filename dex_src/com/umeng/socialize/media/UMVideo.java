package com.umeng.socialize.media;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.util.HashMap;
import java.util.Map;

public class UMVideo extends BaseMediaObject {
    private UMImage i;
    private String j;
    private String k;
    private String l;
    private String m;
    private int n;

    public int getDuration() {
        return this.n;
    }

    public void setDuration(int i) {
        this.n = i;
    }

    public UMVideo(String str) {
        super(str);
    }

    public String getLowBandUrl() {
        return this.j;
    }

    public String getLowBandDataUrl() {
        return this.k;
    }

    public void setLowBandDataUrl(String str) {
        this.k = str;
    }

    public String getHighBandDataUrl() {
        return this.l;
    }

    public void setHighBandDataUrl(String str) {
        this.l = str;
    }

    public String getH5Url() {
        return this.m;
    }

    public void setH5Url(String str) {
        this.m = str;
    }

    public void setLowBandUrl(String str) {
        this.j = str;
    }

    public UMediaObject$MediaType getMediaType() {
        return UMediaObject$MediaType.VEDIO;
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
        this.i = uMImage;
    }

    public byte[] toByte() {
        return this.i != null ? this.i.toByte() : null;
    }

    public String toString() {
        return new StringBuilder("UMVedio [media_url=").append(this.a).append(", qzone_title=").append(this.b).append(", qzone_thumb=").append(this.c).append("media_url=").append(this.a).append(", qzone_title=").append(this.b).append(", qzone_thumb=").append(this.c).append("]").toString();
    }

    public boolean isMultiMedia() {
        return true;
    }

    public UMImage getThumbImage() {
        return this.i;
    }
}
