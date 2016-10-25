package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;
import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;

public class XLLiveGetReplayRecordRequest extends XLLiveRequest {
    private int mLen;
    private int mPos;
    private String mRoomId;

    public static final class GetReplayRecordResp extends XLLiveRespBase {
    }

    public XLLiveGetReplayRecordRequest(String str, String str2, String str3) {
        super(str, str2);
        this.mRoomId = str3;
    }

    public void send(JsonCallBack jsonCallBack, int i, int i2) {
        this.mPos = i;
        this.mLen = i2;
        send(jsonCallBack);
    }

    protected String onGetURL() {
        return new StringBuilder("http://biz.live.xunlei.com/caller?c=room&a=getrecorddata&roomid=").append(this.mRoomId).append("&start=").append(this.mPos).append("&length=").append(this.mLen).toString();
    }

    protected Class<?> onGetObjectClass() {
        return GetReplayRecordResp.class;
    }
}
