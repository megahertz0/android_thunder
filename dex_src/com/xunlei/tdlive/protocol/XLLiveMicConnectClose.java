package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;

public class XLLiveMicConnectClose extends XLLiveRequest {
    private String cluserId;
    private String roomid;
    private String type;

    public static class MicConnectCloseResp extends XLLiveRespBase {
    }

    public XLLiveMicConnectClose(String str, String str2, String str3, String str4, String str5) {
        super(str, str3);
        this.type = str4;
        this.cluserId = str2;
        this.roomid = str5;
    }

    protected String onGetURL() {
        return new StringBuilder("http://biz.live.xunlei.com/caller?c=voicecall&a=close&type=").append(this.type).append("&userid=").append(this.cluserId).append("&roomid=").append(this.roomid).toString();
    }

    protected Class<?> onGetObjectClass() {
        return MicConnectCloseResp.class;
    }
}
