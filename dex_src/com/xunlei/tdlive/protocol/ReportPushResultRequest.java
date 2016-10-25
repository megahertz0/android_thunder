package com.xunlei.tdlive.protocol;

public class ReportPushResultRequest extends XLLiveRequest {
    private final boolean mClicked;
    private final String mPlayerId;

    public ReportPushResultRequest(String str, String str2, String str3, boolean z) {
        super(str, str2);
        this.mPlayerId = str3;
        this.mClicked = z;
    }

    protected String onGetURL() {
        return new StringBuilder("http://biz.live.xunlei.com/caller?c=news&a=reportispushsuccess&playerid=").append(this.mPlayerId).append("&clicked=").append(this.mClicked).toString();
    }
}
