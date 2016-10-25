package com.xunlei.tdlive.protocol;

public class XLLiveDeleteLiveRecordRequest extends XLLiveRequest {
    private String mRoomId;

    public XLLiveDeleteLiveRecordRequest(String str, String str2, String str3) {
        super(str, str2);
        this.mRoomId = str3;
    }

    protected String onGetURL() {
        return new StringBuilder("http://biz.live.xunlei.com/caller?c=room&a=delrecord&roomid=").append(this.mRoomId).toString();
    }
}
