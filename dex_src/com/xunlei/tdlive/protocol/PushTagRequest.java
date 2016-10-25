package com.xunlei.tdlive.protocol;

public class PushTagRequest extends XLLiveRequest {
    public static final String T_ADD = "add";
    public static final String T_REMOVE = "remove";
    private final String mPeerId;
    private final String mType;

    public PushTagRequest(String str, String str2, String str3, String str4) {
        super(str, str2);
        this.mType = str3;
        this.mPeerId = str4;
    }

    protected String onGetURL() {
        return new StringBuilder("http://biz.live.xunlei.com/caller?c=news&a=tagwithuserid&type=").append(this.mType).append("&peerid=").append(this.mPeerId).toString();
    }
}
