package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;

public class XLLiveUnBanRequest extends XLLiveRequest {
    private String mPlayeruid;
    private String mRoomId;
    private String mUnBanUserId;

    public static class UnBanResp extends XLLiveRespBase {
    }

    public XLLiveUnBanRequest(String str, String str2, String str3, String str4) {
        super(str, str2);
        this.mPlayeruid = str;
        this.mUnBanUserId = str3;
        this.mRoomId = str4;
    }

    protected String onGetURL() {
        return new StringBuilder("http://biz.live.xunlei.com/caller?c=player&a=allowchat&userid=").append(this.mUnBanUserId).append("&roomid=").append(this.mRoomId).append("&playeruid=").append(this.mPlayeruid).toString();
    }

    protected Class<?> onGetObjectClass() {
        return UnBanResp.class;
    }
}
