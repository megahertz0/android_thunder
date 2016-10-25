package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;

public class XLLiveShareNoticeRequest extends XLLiveRequest {
    private String mPlayerId;
    private String roomId;
    private int type;

    public static class ShareNoticeResp extends XLLiveRespBase {
    }

    public XLLiveShareNoticeRequest(String str, String str2, String str3, String str4, int i) {
        super(str, str2);
        this.roomId = str3;
        this.mPlayerId = str4;
        this.type = i;
    }

    protected String onGetURL() {
        return new StringBuilder("http://biz.live.xunlei.com/caller?c=site&a=sharenotice&roomid=").append(this.roomId).append("&playeruid=").append(this.mPlayerId).append("&type=").append(this.type).toString();
    }

    protected Class<?> onGetObjectClass() {
        return ShareNoticeResp.class;
    }
}
