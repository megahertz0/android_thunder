package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;

public class XLLiveBanRequest extends XLLiveRequest {
    private String mBanUserId;
    private String mRoomId;

    public static class BanResp extends XLLiveRespBase {
    }

    public XLLiveBanRequest(String str, String str2, String str3, String str4) {
        super(str, str2);
        this.mBanUserId = str3;
        this.mRoomId = str4;
    }

    protected String onGetURL() {
        return new StringBuilder("http://biz.live.xunlei.com/caller?c=player&a=deniedchat&userid=").append(this.mBanUserId).append("&roomid=").append(this.mRoomId).toString();
    }

    protected Class<?> onGetObjectClass() {
        return BanResp.class;
    }
}
