package com.xunlei.tdlive.protocol;

public class XLLiveQueryStreamInfoRequest extends XLLiveRequest {
    private int mPingAvg;

    public XLLiveQueryStreamInfoRequest(String str, String str2, int i) {
        super(str, str2);
        this.mPingAvg = i;
    }

    protected String onGetURL() {
        return new StringBuilder("http://biz.live.xunlei.com/caller?c=room&a=getstreaminfo&ping_avg=").append(this.mPingAvg).toString();
    }

    protected Class<?> onGetObjectClass() {
        return QueryStreamInfoResp.class;
    }
}
