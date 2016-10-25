package com.umeng.socialize.media;

import android.os.Parcel;
import android.text.TextUtils;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.util.HashMap;
import java.util.Map;

public class UMusic extends BaseMediaObject {
    private String i;
    private String j;
    private UMImage k;
    private String l;
    private String m;
    private String n;
    private String o;
    private int p;

    public int getDuration() {
        return this.p;
    }

    public void setDuration(int i) {
        this.p = i;
    }

    public String getLowBandUrl() {
        return this.o;
    }

    public void setLowBandUrl(String str) {
        this.o = str;
    }

    public UMusic(String str) {
        super(str);
        this.i = "\u672a\u77e5";
        this.j = "\u672a\u77e5";
    }

    public String getHighBandDataUrl() {
        return this.m;
    }

    public void setHighBandDataUrl(String str) {
        this.m = str;
    }

    public String getH5Url() {
        return this.n;
    }

    public void setH5Url(String str) {
        this.n = str;
    }

    public UMediaObject$MediaType getMediaType() {
        return UMediaObject$MediaType.MUSIC;
    }

    protected UMusic(Parcel parcel) {
        super(parcel);
        this.i = "\u672a\u77e5";
        this.j = "\u672a\u77e5";
        this.i = parcel.readString();
        this.j = parcel.readString();
    }

    public final Map<String, Object> toUrlExtraParams() {
        Map<String, Object> hashMap = new HashMap();
        if (isUrlMedia()) {
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_FURL, this.a);
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_FTYPE, getMediaType());
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_TITLE, this.i);
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_AUTHOR, this.j);
        }
        return hashMap;
    }

    public void setTitle(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.i = str;
        }
    }

    public void setAuthor(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.j = str;
        }
    }

    public String getTitle() {
        return this.i;
    }

    public String getAuthor() {
        return this.j;
    }

    public void setThumb(UMImage uMImage) {
        this.k = uMImage;
    }

    public byte[] toByte() {
        return this.k != null ? this.k.toByte() : null;
    }

    public String toString() {
        return new StringBuilder("UMusic [title=").append(this.i).append(", author=").append(this.j).append("media_url=").append(this.a).append(", qzone_title=").append(this.b).append(", qzone_thumb=").append(this.c).append("]").toString();
    }

    public boolean isMultiMedia() {
        return true;
    }

    public UMImage getThumbImage() {
        return this.k;
    }

    public String getLowBandDataUrl() {
        return this.l;
    }

    public void setLowBandDataUrl(String str) {
        this.l = str;
    }
}
