package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;

public class XLLiveComplainRequest extends XLLiveRequest {
    private String complainedUid;
    private int reason;
    private String roomId;
    private String type;

    public static class ComplainResp extends XLLiveRespBase {
    }

    public XLLiveComplainRequest(String str, String str2, boolean z, String str3, int i, String str4) {
        super(str, str2);
        this.type = z ? "player" : "user";
        this.complainedUid = str3;
        this.reason = i;
    }

    protected String onGetURL() {
        return new StringBuilder("http://biz.live.xunlei.com/caller?c=site&a=userreport&reportType=").append(this.type).append("&be_report_userid=").append(this.complainedUid).append("&reasonid=").append(this.reason).append("&roomId=").append(this.roomId).toString();
    }

    protected Class<?> onGetObjectClass() {
        return ComplainResp.class;
    }
}
