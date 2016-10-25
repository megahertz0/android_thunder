package com.xunlei.tdlive.protocol;

import android.net.Uri;

public class XLLiveClosePublishRoomRequest extends XLLiveRequest {
    private String mReason;
    private String mRoomId;

    public XLLiveClosePublishRoomRequest(String str, String str2, String str3, String str4) {
        super(str, str2);
        this.mRoomId = str3;
        this.mReason = str4;
    }

    protected String onGetURL() {
        return new StringBuilder("http://biz.live.xunlei.com/caller?c=room&a=close&roomid=").append(this.mRoomId).append("&msg=").append(Uri.encode(this.mReason)).toString();
    }

    protected Class<?> onGetObjectClass() {
        return ClosePublishRoomResp.class;
    }
}
