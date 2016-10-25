package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;

public class XLLiveMicConnectReply extends XLLiveRequest {
    private String replyFlag;
    private String roomId;
    private String userId;

    public static class MicConnectReplyResp extends XLLiveRespBase {
    }

    public XLLiveMicConnectReply(String str, String str2, String str3, String str4, String str5) {
        super(str, str3);
        this.userId = str2;
        this.roomId = str4;
        this.replyFlag = str5;
    }

    protected String onGetURL() {
        return new StringBuilder("http://biz.live.xunlei.com/caller?c=voicecall&a=replay&userid=").append(this.userId).append("&roomid=").append(this.roomId).append("&flag=").append(this.replyFlag).toString();
    }

    protected Class<?> onGetObjectClass() {
        return MicConnectReplyResp.class;
    }
}
