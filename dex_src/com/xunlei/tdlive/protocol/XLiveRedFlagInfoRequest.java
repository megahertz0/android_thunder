package com.xunlei.tdlive.protocol;

public class XLiveRedFlagInfoRequest extends XLLiveRequest {
    private long mLastQueryTime;

    public XLiveRedFlagInfoRequest(String str, String str2, long j) {
        super(str, str2);
        this.mLastQueryTime = j;
    }

    protected String onGetURL() {
        return new StringBuilder("http://biz.live.xunlei.com/caller?c=room&a=newplayer&lasttime=").append(this.mLastQueryTime).toString();
    }
}
