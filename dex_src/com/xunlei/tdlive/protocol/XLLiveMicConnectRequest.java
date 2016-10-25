package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;

public class XLLiveMicConnectRequest extends XLLiveRequest {
    private String roomId;

    public static class MicConnectResp extends XLLiveRespBase {
    }

    public XLLiveMicConnectRequest(String str, String str2, String str3) {
        super(str, str2);
        this.roomId = str3;
    }

    protected String onGetURL() {
        return new StringBuilder("http://biz.live.xunlei.com/caller?c=voicecall&a=connection&roomid=").append(this.roomId).toString();
    }

    protected Class<?> onGetObjectClass() {
        return MicConnectResp.class;
    }
}
